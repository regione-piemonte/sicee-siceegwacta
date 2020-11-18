/*******************************************************************************
 * SPDX-License-Identifier: EUPL-1.2
 * Copyright Regione Piemonte - 2020
 *******************************************************************************/
/*
 * 
 */
package it.csi.sicee.siceegwacta.util;


import it.csi.sicee.siceegwacta.business.dto.Certificato;
import it.csi.sicee.siceegwacta.integration.db.SiceeTParametriActa;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

//import acaris.dto.Certificato;


/**
 * The Class GenericUtil.
 */
public class GenericUtil {

	private static Logger logger = Logger.getLogger(ACTAConstants.LOGGER_PREFIX);

	/** The Constant BEGIN. */
	static final int BEGIN = 1;

	/** The Constant END. */
	static final int END = 2;

	/** The Constant VALUE. */
	static final int VALUE = 3;

	/** The Constant TEST. */
	static final int TEST = 4;

	/** The Constant SIMPLE. */
	static final int SIMPLE = 5;
	
	public final static java.text.SimpleDateFormat FORMATTER_ANNO_WEB = new java.text.SimpleDateFormat(
			"yyyy");

	public final static java.text.SimpleDateFormat FORMATTER_DATA_WEB = new java.text.SimpleDateFormat(
			"dd/MM/yyyy");

	public final static java.text.SimpleDateFormat FORMATTER_DATA_COMPLETA = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	public final static java.text.SimpleDateFormat FORMATTER_DATA_ACTA = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

	/**
	 * Stampa.
	 * 
	 * @param o
	 *            the o
	 * @param useLog4j
	 *            the use log4j
	 * @param depth
	 *            the depth
	 * @param testName
	 *            the test name
	 */
	public static void stampa(Object o, boolean useLog4j, int depth,
			String testName) {
		try {
			if (useLog4j) {
				logger.debug(testName + " BEGIN");
			} else {
				System.out.println(testName + " BEGIN");
			}
			if (o != null) {
				if (o.getClass().isArray()) {
					Object[] a = (Object[]) o;
					stampa(a, useLog4j, depth);
				} else {
					stampa(o, useLog4j, depth);
				}
			}
			if (useLog4j) {
				logger.debug(testName + " END");
			} else {
				System.out.println(testName + " END");
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	/**
	 * Stampa.
	 * 
	 * @param o
	 *            the o
	 * @param useLog4j
	 *            the use log4j
	 * @param depth
	 *            the depth
	 */
	public static void stampa(Object o, boolean useLog4j, int depth) {

		try {
			if (o == null) {
				print(o, null, useLog4j, depth, BEGIN);
			} else {
				if (o instanceof String) {
					print(o, o, useLog4j, depth, SIMPLE);
				} else {
					print(o, null, useLog4j, depth, BEGIN);
					callGetMethods(o, useLog4j, depth + 1);
					print(o, null, useLog4j, depth, END);
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	/**
	 * Prints the.
	 * 
	 * @param o
	 *            the o
	 * @param value
	 *            the value
	 * @param useLog4j
	 *            the use log4j
	 * @param depth
	 *            the depth
	 * @param type
	 *            the type
	 * @throws Exception
	 *             the exception
	 */
	private static void print(Object o, Object value, boolean useLog4j,
			int depth, int type) throws Exception {

		StringBuffer tab = new StringBuffer();
		for (int i = 0; i < depth; i++) {
			tab.append("\t");
		}
		if (o != null) {
			String className = o.getClass().getName();
			switch (type) {
			case BEGIN:
				tab.append(className);
				tab.append(" BEGIN");
				break;
			case END:
				tab.append(className);
				tab.append(" END");
				break;
			case VALUE:
				tab.append(((Method) o).getName());
				tab.append(" == ");
				tab.append(value);
				break;
			case SIMPLE:
				tab.append(o);
				tab.append(" == ");
				tab.append(value);
				break;
			default:

			}
		} else if (type == TEST) {
			tab.append("");
		} else {
			tab.append("Oggetto nullo!!");
		}

		if (useLog4j) {
			logger.debug(tab);
		} else {
			System.out.println(tab);
		}

	}

	/**
	 * Call get methods.
	 * 
	 * @param o
	 *            the o
	 * @param useLog4j
	 *            the use log4j
	 * @param depth
	 *            the depth
	 */
	private static void callGetMethods(Object o, boolean useLog4j, int depth) {
		try {
			Method[] meth = o.getClass().getDeclaredMethods();
			for (int i = 0; i < meth.length; i++) {
				Method thisM = meth[i];
				if (thisM.getName().startsWith("get")) {
					if (!thisM.getName().equals("get")) {
						Object result = thisM.invoke(o, new Object[] {});
						if (result != null && result.getClass().isArray()) {
							Object[] a = (Object[]) result;
							stampa(a, useLog4j, depth);
						} else {
							print(thisM, result, useLog4j, depth, VALUE);
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	/**
	 * Stampa.
	 * 
	 * @param o
	 *            the o
	 * @param useLog4j
	 *            the use log4j
	 * @param depth
	 *            the depth
	 * @throws Exception
	 *             the exception
	 */
	public static void stampa(Object[] o, boolean useLog4j, int depth)
			throws Exception {
		String className = o.getClass().getSimpleName();
		for (int i = 0; i < o.length; i++) {
			stampa(o[i], false, depth);
		}

		if (o.length == 0) {
			System.out.println(className + " vuoto");
		}

	}
	
	/**
	 * Gets the anno corrente.
	 * 
	 * @return the anno corrente
	 */
	public static String getAnnoCorrente() {
		return FORMATTER_ANNO_WEB.format(new Date(System
				.currentTimeMillis()));
	}

	/**
	 * Convert to timestamp.
	 *
	 * @param s the s
	 * @return the java.sql. timestamp
	 * @throws BEException the bE exception
	 */
	public static java.sql.Timestamp getInizioProcesso()
	{
		
		Date s = new Date();

		Timestamp time = null;
		if (s != null) {
			time = new Timestamp(s.getTime());
		}
		
		return time;
	}
	
	public static String convertToString(java.sql.Timestamp time) {
		if (time != null) {
			return FORMATTER_DATA_COMPLETA.format(time);
		} else
			return null;
	}
	
	public static java.util.Date convertToDateCompleta(String time) {
		if (time != null) {
			try {
				return FORMATTER_DATA_COMPLETA.parse(time);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				return null;
			}
		} else
			return null;
	}
	
	public static Date convertToDateActa(String dataActa) {
		if (dataActa != null) {
			try {
				return FORMATTER_DATA_ACTA.parse(dataActa);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				return null;
			}
		} else
			return null;
	}
	
	/**
	 * Convert to string.
	 *
	 * @param dt the dt
	 * @return the string
	 */
	public static String convertToString(java.util.Date dt) {
		if (dt != null) {
			
			return FORMATTER_DATA_WEB.format(dt);
		} else
			return null;
	}

	public static String convertToString(Long l) {
		String converted = null;

		if(l != null) {
			try {
				converted = l.toString();
			}
			catch(Exception e) {
				logger.error("Errore durante la conversione di '" + l + "' in Integer: " + l);
			}
		}
		return converted;
	}
	
	public static Long convertToLong(String s) {
		Long converted = null;

		if(s != null) {
			try {
				converted = new Long(s);
			}
			catch(Exception e) {
				logger.error("Errore durante la conversione di '" + s + "' in long: " + s);
			}
		}
		return converted;
	}
	
	public static int convertToInt(String s) {
		int converted = 0;

		if(s != null) {
			try {
				converted = new Integer(s);
			}
			catch(Exception e) {
				logger.error("Errore durante la conversione di '" + s + "' in long: " + s);
			}
		}
		return converted;
	}

	/**
	 * Checks if is null or empty.
	 * 
	 * @param s
	 *            the s
	 * @return true, if is null or empty
	 */
	public static boolean isNullOrEmpty(String s) {
		return s == null || s.trim().length() == 0;
	}

	/**
	 * Checks if is null or empty.
	 * 
	 * @param s
	 *            the s
	 * @return true, if is null or empty
	 */
	public static boolean isNullOrEmpty(Integer s) {
		return s == null;
	}

	/**
	 * Checks if is null or empty.
	 * 
	 * @param s
	 *            the s
	 * @return true, if is null or empty
	 */
	public static boolean isNullOrEmpty(Double s) {
		return s == null;
	}

	/**
	 * Checks if is null or empty.
	 * 
	 * @param s
	 *            the s
	 * @return true, if is null or empty
	 */
	public static boolean isNullOrEmpty(Boolean s) {
		return s == null;
	}

	/**
	 * Checks if is null or empty.
	 * 
	 * @param s
	 *            the s
	 * @return true, if is null or empty
	 */
	public static boolean isNullOrEmpty(Object s) {
		return s == null;
	}

	public static boolean isNullOrEmpty(List<?> s) {
		if (s != null) {
			return s.isEmpty();
		}

		return s == null;
	}

	
	public static String recuperaValParametro(List<SiceeTParametriActa> paramActa, String codParam)
	{
		String val = null;
		for (SiceeTParametriActa siceeTParametriActa : paramActa) {

			if (siceeTParametriActa.getCodice().equalsIgnoreCase(codParam))
			{
				val = siceeTParametriActa.getValore();
				break;
			}
		}

		return val;
	}

	public static SiceeTParametriActa recuperaTParametriActa(List<SiceeTParametriActa> paramActa, String codParam)
	{
		SiceeTParametriActa val = null;
		for (SiceeTParametriActa siceeTParametriActa : paramActa) {

			if (siceeTParametriActa.getCodice().equalsIgnoreCase(codParam))
			{
				val = siceeTParametriActa;
				break;
			}
		}

		return val;
	}
	
	public static String getOggettoCertificato(Certificato certificato)
	{
		String oggetto = null;
		
		if (certificato != null)
		{
			oggetto = certificato.getAnno() + " " + certificato.getNumCertificatore() + " " + certificato.getProgrCertificato();
		}
		
		return oggetto;
	}
	
}