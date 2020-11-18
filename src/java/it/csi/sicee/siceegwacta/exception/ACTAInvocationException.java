/*******************************************************************************
 * SPDX-License-Identifier: EUPL-1.2
 * Copyright Regione Piemonte - 2020
 *******************************************************************************/
package it.csi.sicee.siceegwacta.exception;

/**
 * Eccezione rilanciata in caso di eccezione nell'invocazione dei servizi ACTA.
 * 
 * 
 *
 */
public class ACTAInvocationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * @see Exception#Exception()
	 */
	public ACTAInvocationException() {
		super();
	}

	/**
	 * @see Exception#Exception(String, Throwable)
	 */
	public ACTAInvocationException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @see Exception#Exception(String)
	 */
	public ACTAInvocationException(String message) {
		super(message);
	}

	/**
	 * @see Exception#Exception(Throwable)
	 */
	public ACTAInvocationException(Throwable cause) {
		super(cause);
	}

}
