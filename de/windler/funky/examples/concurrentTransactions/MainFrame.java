package de.windler.funky.examples.concurrentTransactions;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import de.windler.funky.core.FunkyException;

/**
 * Frame for a transaction example. This will be a simple, little
 * bank-transaction program. The concurrency and calculation will be handled in
 * Clojure. Since we just have one application and no database the calculation
 * on Clojure-site will get a delay so that we can concurrency
 * 
 * @author Nico Windler
 * @since 20.01.2013
 */
public class MainFrame extends JFrame {

	private static final long serialVersionUID = 4648428952989520259L;

	public MainFrame() {
		setTitle("Bank Transaction Example");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500, 120);
		try {
			add(new MainPanel());
		} catch (FunkyException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				MainFrame frame = new MainFrame();
				frame.setVisible(true);
			}
		});
	}
}
