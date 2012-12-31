package de.windler.funky.core;

/**
 * If this is thrown something funky happend!
 * 
 * @since 02.12.2012
 * @author Nico Windler
 * 
 */
public class FunkyException extends Exception {

	private static final long serialVersionUID = 4710600477837933321L;
	private final String msg;

	public FunkyException(final String msg) {
		this.msg = msg;
	}

	@Override
	public String getMessage() {
		return msg;
	}
}
