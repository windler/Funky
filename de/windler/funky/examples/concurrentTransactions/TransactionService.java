package de.windler.funky.examples.concurrentTransactions;

import de.windler.funky.annotations.Funky;
import de.windler.funky.core.FunkyBridge;
import de.windler.funky.core.FunkyException;
import de.windler.funky.core.FunkyInvocationFinishedListener;
import de.windler.funky.drivers.FunkyDriver;

/**
 * This service will call the Clojure-functions to initialize the account and
 * transfer money bidirectional
 * 
 * @author Nico Windler
 * @since 20.01.2013
 */
@Funky(driver = FunkyDriver.CLOJURE, target = "de/windler/funky/examples/concurrentTransactions/transactions.clj")
public class TransactionService extends FunkyBridge {

	public TransactionService() throws FunkyException {
		super();
	}

	public void initAccount(long initMoney) throws FunkyException {
		invoke(AccountFunctions.INIT_ACCOUNT, Long.class, initMoney);
	}

	public Long getCurrentMoney() throws FunkyException {
		return invoke(AccountFunctions.CURRENT_AMOUNT, Long.class).getValue();
	}

	public void transfer(int money,
			FunkyInvocationFinishedListener<Boolean> listener)
			throws FunkyException {
		invokeAndNotify(listener, AccountFunctions.TRANSFER, money);
	}

}
