/**
 *
 */
package org.desz.longtoword.exceptions;

/**
 *
 */
public class BuildWordException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	public BuildWordException() {
	}

	/**
	 * @param message
	 */
	public BuildWordException(String message) {
		super(message);

	}

	/**
	 * @param cause
	 */
	public BuildWordException(Throwable cause) {
		super(cause);

	}

	/**
	 * @param message
	 * @param cause
	 */
	public BuildWordException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public BuildWordException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

}
