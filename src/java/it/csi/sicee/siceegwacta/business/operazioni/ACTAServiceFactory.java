/*******************************************************************************
 * SPDX-License-Identifier: EUPL-1.2
 * Copyright Regione Piemonte - 2020
 *******************************************************************************/
package it.csi.sicee.siceegwacta.business.operazioni;

import it.csi.sicee.siceegwacta.util.ACTAConstants;
import it.csi.sicee.siceegwacta.util.GenericUtil;

import java.io.InputStream;
import java.util.Map;
import java.util.Properties;


import org.apache.log4j.Logger;



public class ACTAServiceFactory {
	
	private static final Logger log = Logger.getLogger(ACTAConstants.LOGGER_PREFIX);

	//private Service s = null;

	private String mailHost;
	private String mailPort;
	private String actaHost;
	private int actaPort = 0;
	private String actaContext;

	private String siceewsUrl;

	public String getMailHost() throws Exception {
		if (mailHost == null)
		{
			Properties properties = new Properties();
			InputStream stream = this.getClass().getResourceAsStream("/contants.properties");
			properties.load(stream);
			mailHost = properties.getProperty("mail.host");
			
			log.debug("[ACTAServiceFactory::getMailHost] Stampo mailHost: "+mailHost);
		}
		return mailHost;
	}

	public String getMailPort() throws Exception {
		if (mailPort == null)
		{
			Properties properties = new Properties();
			InputStream stream = this.getClass().getResourceAsStream("/contants.properties");
			properties.load(stream);
			mailPort = properties.getProperty("mail.port");
			
			log.debug("[ACTAServiceFactory::getMailPort] Stampo mailPort: "+mailPort);

		}
		return mailPort;
	}

	public String getActaHost() {
		log.debug("[ACTAServiceFactory::getActaHost] BEGIN");

		if (actaHost == null)
		{
			try
			{
				Properties properties = new Properties();
				InputStream stream = this.getClass().getResourceAsStream("/contants.properties");
				properties.load(stream);
				actaHost = properties.getProperty("acta.host");
			}
			catch (Exception e)
			{
				log.error("[ACTAServiceFactory::getActaHost] si e' verificato un errore nel reperimento della risorsa");
			}
		}
		log.debug("[ACTAServiceFactory::getActaHost] END");

		return actaHost;
	}
	
	

	public int getActaPort() {
		log.debug("[ACTAServiceFactory::getActaPort] BEGIN");

		if (actaPort == 0)
		{  
			try
			{
				Properties properties = new Properties();
				InputStream stream = this.getClass().getResourceAsStream("/contants.properties");
				properties.load(stream);
				actaPort = GenericUtil.convertToInt(properties.getProperty("acta.port"));
			}
			catch (Exception e)
			{
				log.error("[ACTAServiceFactory::getActaPort] si e' verificato un errore nel reperimento della risorsa");
			}
		}
		log.debug("[ACTAServiceFactory::getActaPort] END");

		return actaPort;
	}

	public String getActaContext() {
		log.debug("[ACTAServiceFactory::getActaContext] BEGIN");

		if (actaContext == null)
		{  
			try
			{
				Properties properties = new Properties();
				InputStream stream = this.getClass().getResourceAsStream("/contants.properties");
				properties.load(stream);
				actaContext = properties.getProperty("acta.context");
			}
			catch (Exception e)
			{
				log.error("[ACTAServiceFactory::getActaContext] si e' verificato un errore nel reperimento della risorsa");
			}
		}
		log.debug("[ACTAServiceFactory::getActaContext] END");

		return actaContext;
	}

	public String getSiceewsUrl() {
		log.debug("[ACTAServiceFactory::getSiceewsUrl] BEGIN");

		if (siceewsUrl == null)
		{
			try
			{
				Properties properties = new Properties();
				InputStream stream = this.getClass().getResourceAsStream("/contants.properties");
				properties.load(stream);
				siceewsUrl = properties.getProperty("siceews.url");
			}
			catch (Exception e)
			{
				log.error("[ACTAServiceFactory::getSiceewsUrl] si e' verificato un errore nel reperimento della risorsa");
			}
		}
		log.debug("[ACTAServiceFactory::getSiceewsUrl] END");

		return siceewsUrl;
	}
	  /*
	  public String getWsSecurityUserName() throws Exception {
		    Properties properties = new Properties();
		    InputStream stream = this.getClass().getResourceAsStream("/wsEndpointUrls.properties");
		    properties.load(stream);
		    wsSecurityUsername = properties.getProperty("ws-security.username");
		    return wsSecurityUsername;
	  }
	  
	  public String getWsSecurityPassword() throws Exception {
		    Properties properties = new Properties();
		    InputStream stream = this.getClass().getResourceAsStream("/wsEndpointUrls.properties");
		    properties.load(stream);
		    wsSecurityPassword = properties.getProperty("ws-security.password");
		    return wsSecurityPassword;
	  }
	  
	  public String getWsSecurityEncriptionUserName() throws Exception {
		    Properties properties = new Properties();
		    InputStream stream = this.getClass().getResourceAsStream("/wsEndpointUrls.properties");
		    properties.load(stream);
		    wsSecurityEncryptionUsername = properties.getProperty("ws-security.encryption.username");
		    return wsSecurityEncryptionUsername;
	  }
	  
	  public String getKeyStoreLocalPath() throws Exception {
		    Properties properties = new Properties();
		    InputStream stream = this.getClass().getResourceAsStream("/wsEndpointUrls.properties");
		    properties.load(stream);
		    keystoreLocalPath = properties.getProperty("keystore.local.path");
		    return keystoreLocalPath;
	  }
	  
	  public String getKeyStoreLocalEnabled() throws Exception {
		    Properties properties = new Properties();
		    InputStream stream = this.getClass().getResourceAsStream("/wsEndpointUrls.properties");
		    properties.load(stream);
		    keystoreLocalEnabled = properties.getProperty("keystore.local.enabled");
		    return keystoreLocalEnabled;
	  }
	  
	  */

}
