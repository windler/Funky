package de.windler.funky.examples;

import de.windler.funky.annotations.Funky;
import de.windler.funky.annotations.FunkyInitializeInThread;
import de.windler.funky.annotations.FunkyLoadOnDemand;
import de.windler.funky.core.FunkyBridge;
import de.windler.funky.core.FunkyException;
import de.windler.funky.drivers.FunkyDriver;

@Funky(driver = FunkyDriver.CLOJURE, target = "de/windler/funky/examples/kalkulation.clj")
@FunkyLoadOnDemand
@FunkyInitializeInThread
public class FakEngine extends FunkyBridge {

	public FakEngine() throws FunkyException {
		super();
	}

	public Long calcFak(int i) throws FunkyException {
		return getFunkyWrapper().invoke(ExampleFunctions.FACTORIAL, Long.class,
				i).getValue();
	}
}
