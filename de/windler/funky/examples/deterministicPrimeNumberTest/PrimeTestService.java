package de.windler.funky.examples.deterministicPrimeNumberTest;

import de.windler.funky.annotations.Funky;
import de.windler.funky.core.FunkyBridge;
import de.windler.funky.core.FunkyException;
import de.windler.funky.core.FunkyInvocationFinishedListener;
import de.windler.funky.drivers.FunkyDriver;

/**
 * This is the bridge to the clojure site. In clojure we will do the test.
 * 
 * @author Nico Windler
 * @since 19.01.2013
 */
@Funky(driver = FunkyDriver.CLOJURE, target = "de/windler/funky/examples/deterministicPrimeNumberTest/primeTest.clj")
public class PrimeTestService extends FunkyBridge {

	public PrimeTestService() throws FunkyException {
		super();
	}

	public void testPrimeNumber(long numberToTest,
			FunkyInvocationFinishedListener<Boolean> listener)
			throws FunkyException {
		invokeAndNotify(listener, ClojureFunctions.PRIME_TEST, numberToTest);
	}
}
