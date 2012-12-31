package de.windler.funky.core;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import de.windler.funky.annotations.Funky;
import de.windler.funky.annotations.FunkyConstant;
import de.windler.funky.annotations.FunkyImmutableConstant;
import de.windler.funky.annotations.FunkyInitializeInThread;
import de.windler.funky.annotations.FunkyLoadOnDemand;
import de.windler.funky.drivers.FunkyClojureWrapper;

/**
 * The FunkyBridge is the bridge (you dont say!) between Java and the chosen
 * functional Programming language.
 * 
 * @ensure Funky-Annotation
 * @since 02.12.2012
 * @author Nico Windler
 * 
 */
public class FunkyBridge {

	private List<FunkyInitializedListener> initListeners;
	private IFunkyCoreWrapper wrapper;
	private boolean isConstantsSet = false;

	/**
	 * creates a new bridge to the functional programming language.
	 * 
	 * @throws FunkyException
	 */
	public FunkyBridge() throws FunkyException {
		initListeners = new LinkedList<FunkyInitializedListener>();

		createWrapper();
		if (getClass().isAnnotationPresent(FunkyInitializeInThread.class)) {
			initListeners.add(new FunkyInitializedListener() {

				@Override
				public void onInitialized() throws FunkyException {
					if (!isConstantsSet) {
						setConstants();
					}
				}
			});
		} else {
			setConstants();
		}
	}

	@SuppressWarnings("rawtypes")
	private void setConstants() throws FunkyException {
		for (final Field f : getClass().getDeclaredFields()) {
			try {
				boolean isAccessible = f.isAccessible();
				if (f.isAnnotationPresent(FunkyImmutableConstant.class)) {
					if (!isAccessible) {
						f.setAccessible(true);
					}
					Class<?> clazz = (Class<?>) ((ParameterizedType) f
							.getGenericType()).getActualTypeArguments()[0];
					FunkyImmutableValue funkyImmutableValue = getInvokedValueFor(
							f, clazz);
					f.set(this, funkyImmutableValue);
					if (!isAccessible) {
						f.setAccessible(false);
					}
				}
				if (f.isAnnotationPresent(FunkyConstant.class)) {
					if (!isAccessible) {
						f.setAccessible(true);
					}
					f.set(this, (getInvokedValueFor(f, f.getDeclaringClass()))
							.getValue());
					if (!isAccessible) {
						f.setAccessible(false);
					}
				}
			} catch (IllegalAccessException e) {
				throw new FunkyException(
						"The Constant "
								+ f.getName()
								+ " could not be set. Did you try to infer a wildcard? Or maybe does the called function return a value that can not be cast to the infered generic value?");
			}
		}
		isConstantsSet = true;
	}

	/**
	 * adds a new listener to notify
	 * 
	 * @param listener
	 */
	public final void addInitializedListener(
			final FunkyInitializedListener listener) {
		initListeners.add(listener);
	}

	/**
	 * removes a listener
	 * 
	 * @param listener
	 */
	public final void removeInitializedListener(
			final FunkyInitializedListener listener) {
		initListeners.remove(listener);
	}

	private FunkyImmutableValue<?> getInvokedValueFor(final Field f,
			final Class<?> clazz) throws FunkyException {
		return getFunkyWrapper().invoke(new FunkyFunction() {

			@Override
			public String getNamespace() {
				return getConstantsNamespace();
			}

			@Override
			public String getName() {
				return f.getName();
			}
		}, clazz);
	}

	/**
	 * override if you plan to use constants for your fields!
	 * 
	 * @return namespace of the constants
	 */
	protected String getConstantsNamespace() {
		return "";
	}

	/**
	 * gets the wrapper for the functional programing language, which can invoke
	 * functional functions. Funky!
	 * 
	 * @return Wrapper dependent of chosen driver
	 * @throws FunkyException
	 */
	protected final IFunkyWrapper getFunkyWrapper() throws FunkyException {
		if (!wrapper.isInitialized()) {
			Funky funky = getClass().getAnnotation(Funky.class);

			switch (funky.driver()) {
			case CLOJURE:
				initializeClojure();
				break;
			}
		}
		return wrapper;
	}

	private void createWrapper() throws FunkyException {

		if (getClass().isAnnotationPresent(Funky.class)) {
			Funky funky = getClass().getAnnotation(Funky.class);

			switch (funky.driver()) {
			case CLOJURE:
				createClojureWrapper(funky);
				break;
			}
		}
	}

	private void createClojureWrapper(final Funky funky) throws FunkyException {
		try {
			wrapper = new FunkyClojureWrapper(funky.target());
			if (!getClass().isAnnotationPresent(FunkyLoadOnDemand.class)) {
				initializeClojure();
			}
		} catch (IOException e) {
			throw new FunkyException(
					"Sorry, could not create the clojure-wrapper :( Maybe you targeted a non-exisiting clj?");
		}
	}

	private void initializeClojure() throws FunkyException {
		if (getClass().isAnnotationPresent(FunkyInitializeInThread.class)) {
			new FunkyInitThread<FunkyClojureWrapper>(
					(FunkyClojureWrapper) wrapper,
					Collections.synchronizedList(initListeners)).start();
		} else {
			wrapper.initialize();
		}
	}

	private class FunkyInitThread<T extends IFunkyCoreWrapper> extends Thread {
		private final T wrapper;
		private final List<FunkyInitializedListener> listener;

		public FunkyInitThread(final T wrapper,
				final List<FunkyInitializedListener> listener) {
			this.wrapper = wrapper;
			this.listener = listener;
		}

		@Override
		public void run() {
			try {
				wrapper.initialize();
				for (FunkyInitializedListener l : listener) {
					l.onInitialized();
				}
			} catch (FunkyException e) {
				// TODO
			}
		}
	}

}
