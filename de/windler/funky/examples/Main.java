package de.windler.funky.examples;

import de.windler.funky.core.FunkyException;
import de.windler.funky.core.FunkyImmutableValue;
import de.windler.funky.core.FunkyInitializedListener;
import de.windler.funky.core.FunkyInvocationFinishedListener;

public class Main {
	public static void main(String[] args) throws FunkyException {

		// create a new KalkEngine - this will be initialized in thread
		final KalkEngine engine = new KalkEngine();
		engine.addInitializedListener(new FunkyInitializedListener() {

			@Override
			public void onInitialized() {
				try {
					System.out.println(engine.kalkSmth(1, 120));
					System.out.println(engine.kalkSmth(4, 120));
				} catch (FunkyException e) {
					e.printStackTrace();
				}

			}
		});

		// create a FakEngine - will we initialized in thread. lazy
		final FakEngine engineFak = new FakEngine();
		engineFak.addInitializedListener(new FunkyInitializedListener() {

			@Override
			public void onInitialized() throws FunkyException {
				System.out.println(engineFak.calcFak(10));
				System.out.println(engineFak.calcFak(12));
			}
		});

		// create a SimpleEngine. Will be loaded on construction
		final SimpleEngine simpleEngine = new SimpleEngine();
		System.out.println(simpleEngine.calc());

		// invoke a method and notify a listener when finished
		simpleEngine.calcAsync(new FunkyInvocationFinishedListener<Long>() {

			@Override
			public void onInvocationFinished(FunkyImmutableValue<Long> value) {
				System.out.println(value.getValue());
			}
		});
	}
}
