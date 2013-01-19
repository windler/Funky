package de.windler.funky.examples.deterministicPrimeNumberTest;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.windler.funky.core.FunkyException;
import de.windler.funky.core.FunkyImmutableValue;
import de.windler.funky.core.FunkyInvocationFinishedListener;

/**
 * The panel on which all components are
 * 
 * @author Nico Windler
 * @since 19.01.2013
 * 
 */
public class MainPanel extends JPanel {

	private static final long serialVersionUID = 6673035421648006609L;

	private static final String NOT_INITIALIZED = "Wrapper is not initialized yet.";
	private static final String IS_NOT_PRIME = "This is not a prime number";
	private static final String IS_PRIME = "This is a prime number.";
	private static final String ERROR_TEXT = "Error. Check console output.";
	private static final String TESTING = "testing....please wait.";
	private static final String TYPE_NUMBER = "Type in a number and run test";

	private PrimeTestService service;

	public MainPanel() throws FunkyException {
		service = new PrimeTestService();

		final JTextField input = new JTextField();
		JButton submit = new JButton("start test");
		final JLabel label = new JLabel(TYPE_NUMBER);
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(1, 2));
		inputPanel.add(input);
		inputPanel.add(submit);

		submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				label.setText(TESTING);
				try {
					// because we will initialize the wrapper in a thread and
					// there are tests in the clj which last long we have to
					// check here if already initialized
					if (service.isInitialized()) {
						service.testPrimeNumber(
								Long.parseLong(input.getText()),
								new FunkyInvocationFinishedListener<Boolean>() {

									@Override
									public void onInvocationFinished(
											FunkyImmutableValue<Boolean> value) {
										label.setText(value.getValue() ? IS_PRIME
												: IS_NOT_PRIME);
									}
								});
					} else {
						label.setText(NOT_INITIALIZED);
					}
				} catch (Exception e) {
					label.setText(ERROR_TEXT);
					e.printStackTrace();
				}
			}
		});

		setLayout(new BorderLayout());
		add(inputPanel, BorderLayout.CENTER);
		add(label, BorderLayout.SOUTH);
	}

}
