package de.windler.funky.examples.concurrentTransactions;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.windler.funky.core.FunkyException;
import de.windler.funky.core.FunkyImmutableValue;
import de.windler.funky.core.FunkyInvocationFinishedListener;

/**
 * The panel on which our transaction can be startet
 * 
 * @author Nico Windler
 * @since 20.01.2013
 * 
 */
public class MainPanel extends JPanel {

	private static final long serialVersionUID = -8068556298807706697L;
	private TransactionService service;
	private JLabel ammountLbl;
	private JLabel resultLbl;

	public MainPanel() throws FunkyException {
		service = new TransactionService();
		setLayout(new BorderLayout());

		ammountLbl = new JLabel();
		add(ammountLbl, BorderLayout.NORTH);

		JPanel transactionPanel = new JPanel();
		transactionPanel.setLayout(new GridLayout(1, 2));

		JButton incBtn = new JButton("Push 10 dollars to account");
		JButton decBtn = new JButton("transfer 20 dollars");
		transactionPanel.add(incBtn);
		transactionPanel.add(decBtn);

		add(transactionPanel, BorderLayout.CENTER);

		resultLbl = new JLabel("Click on any button to start a transaction.");
		add(resultLbl, BorderLayout.SOUTH);

		service.initAccount(10);
		showAmmount();

		incBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				transferInternal(10);
			}

		});

		decBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				transferInternal(-20);
			}
		});
	}

	private void transferInternal(int money) {
		try {
			service.transfer(money,
					new FunkyInvocationFinishedListener<Boolean>() {

						@Override
						public void onInvocationFinished(
								FunkyImmutableValue<Boolean> value) {
							showTransactionResult(value.getValue());
							showAmmount();

						}
					});
		} catch (FunkyException e) {
			e.printStackTrace();
		}
	}

	private void showTransactionResult(boolean transfer) {
		resultLbl.setText(transfer ? "Success!" : "Not possible!");
	}

	private void showAmmount() {
		try {
			ammountLbl.setText("Current money: " + service.getCurrentMoney());
		} catch (FunkyException e) {
			e.printStackTrace();
		}
	}

}
