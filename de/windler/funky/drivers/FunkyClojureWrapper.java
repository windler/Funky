package de.windler.funky.drivers;

import java.io.IOException;

import clojure.lang.RT;
import clojure.lang.Var;
import de.windler.funky.core.FunkyException;
import de.windler.funky.core.FunkyFunction;
import de.windler.funky.core.FunkyImmutableValue;
import de.windler.funky.core.FunkyInvocationFinishedListener;
import de.windler.funky.core.IFunkyCoreWrapper;

/**
 * Clojure-Implementation
 * 
 * @since 02.12.2012
 * @author Nico Windler
 * 
 */
public class FunkyClojureWrapper implements IFunkyCoreWrapper {

	private boolean initialized;
	private final String target;

	public FunkyClojureWrapper(final String target) throws IOException {
		this.target = target;
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public final <T> FunkyImmutableValue<T> invoke(
			final FunkyFunction function, Class<T> clazz,
			final Object... params) throws FunkyException {
		Object invocation = invokeInternal(function, params);
		return invocation != null ? new FunkyImmutableValue<T>((T) invocation)
				: null;
	}

	private Object invokeInternal(final FunkyFunction function,
			final Object[] params) throws FunkyException {
		Object invocation = null;
		Var var = null;

		if (!isInitialized()) {
			throw new FunkyException(
					"For some reason...the ClojureWrapper is not initialized :( Maybe you annotated @FunkyInitializeInThread and did no register a listener?");
		}
		var = RT.var(function.getNamespace(), function.getName());

		switch (params.length) {
		case 0:
			invocation = var.invoke();
			break;
		case 1:
			invocation = var.invoke(params[0]);
			break;
		case 2:
			invocation = var.invoke(params[0], params[1]);
			break;
		case 3:
			invocation = var.invoke(params[0], params[1], params[2]);
			break;
		case 4:
			invocation = var.invoke(params[0], params[1], params[2], params[3]);
			break;
		case 5:
			invocation = var.invoke(params[0], params[1], params[2], params[3],
					params[4]);
			break;
		case 6:
			invocation = var.invoke(params[0], params[1], params[2], params[3],
					params[4], params[5]);
			break;
		case 7:
			invocation = var.invoke(params[0], params[1], params[2], params[3],
					params[4], params[5], params[6]);
			break;
		case 8:
			invocation = var.invoke(params[0], params[1], params[2], params[3],
					params[4], params[5], params[6], params[7]);
			break;
		default:
			throw new FunkyException(
					"The number of paramters has to be >=0 and <=8");
		}
		return invocation;
	}

	@Override
	public final void initialize() throws FunkyException {
		try {
			RT.loadResourceScript(target);
		} catch (IOException e) {
			throw new FunkyException("sorry, could not find target '" + target
					+ "'");
		}
		initialized = true;
	}

	@Override
	public final boolean isInitialized() {
		return initialized;
	}

	@Override
	public final void invokeAndNotify(
			final FunkyInvocationFinishedListener<?> listener,
			final FunkyFunction function, final Object... params)
			throws FunkyException {
		new Thread(new Runnable() {

			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public void run() {
				try {
					Object result = invokeInternal(function, params);
					listener.onInvocationFinished(new FunkyImmutableValue(
							result));
				} catch (FunkyException e) {
					// TODO
				}
			}
		}).start();
	}
}
