package de.windler.funky.examples;

import de.windler.funky.annotations.Funky;
import de.windler.funky.core.FunkyBridge;
import de.windler.funky.core.FunkyException;
import de.windler.funky.core.FunkyInvocationFinishedListener;
import de.windler.funky.drivers.FunkyDriver;

@Funky(driver = FunkyDriver.CLOJURE, target = "de/windler/funky/examples/kalkulation.clj")
public class SimpleEngine extends FunkyBridge {

	public SimpleEngine() throws FunkyException {
		super();
	}

	public long calc() throws FunkyException {
		return getFunkyWrapper().invoke(ExampleFunctions.MY_FUNCTION,
				Long.class, 10, 10).getValue();
	}

	public void calcAsync(FunkyInvocationFinishedListener<?> listener)
			throws FunkyException {
		getFunkyWrapper().invokeAndNotify(listener, ExampleFunctions.ASYNC,
				1000);
	}

}
