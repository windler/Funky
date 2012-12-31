package de.windler.funky.examples;

import de.windler.funky.annotations.Funky;
import de.windler.funky.annotations.FunkyConstant;
import de.windler.funky.annotations.FunkyImmutableConstant;
import de.windler.funky.annotations.FunkyInitializeInThread;
import de.windler.funky.core.FunkyBridge;
import de.windler.funky.core.FunkyException;
import de.windler.funky.core.FunkyImmutableValue;
import de.windler.funky.drivers.FunkyDriver;

@Funky(driver = FunkyDriver.CLOJURE, target = "de/windler/funky/examples/kalkulation.clj")
@FunkyInitializeInThread
public class KalkEngine extends FunkyBridge {

	public KalkEngine() throws FunkyException {
		super();
	}

	@FunkyConstant
	private Double pi;

	@FunkyImmutableConstant
	private FunkyImmutableValue<Double> pi2;

	@Override
	protected String getConstantsNamespace() {
		return ExampleFunctions.MY_FUNCTION.getNamespace();
	}

	public double kalkSmth(final int i, final int j) throws FunkyException {
		long res = getFunkyWrapper().invoke(ExampleFunctions.MY_FUNCTION,
				Long.class, i, j).getValue();
		return res * pi2.getValue() * pi;
	}
}
