package de.windler.funky.examples.deterministicPrimeNumberTest;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import de.windler.funky.core.FunkyException;

/**
 * This is the Frame for the deterministic prime number test. The test will be
 * very dump. Its designed bruteforce
 * 
 * @author Nico Windler
 * @since 19.01.2013
 * 
 */
public class MainFrame extends JFrame {

	private static final long serialVersionUID = 4648428952989520259L;

	public MainFrame() {
		setTitle("Deterministic Prime Test");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500, 80);
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
