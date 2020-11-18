/*******************************************************************************
 * SPDX-License-Identifier: EUPL-1.2
 * Copyright Regione Piemonte - 2020
 *******************************************************************************/
package it.csi.sicee.siceegwacta.util;

public interface ACTAConstants {

  String LOGGER_PREFIX = "siceegwacta";

	final static String COD_APPLICATIVO = "SIPEE";
  	final static String COD_FISCALE = "CODICEFISCALE";
	final static String ID_AOO = "IDAOO";
	final static String ID_STRUTTURA = "IDSTRUTTURA";
	final static String ID_NODO = "IDNODORESP";
	final static String REP_ID = "REPOSITORYID";
	final static String REP_NAME = "REPOSITORYNAME";
	final static String APP_KEY = "APPKEY";
	final static String ID_SERIE_CATENER = "ID_SERIE_CATENER";
	final static String ID_VOLUME_CORRENTE = "ID_VOLUME_CORRENTE";
	final static String VOLUME_CORRENTE = "VOLUME_CORRENTE";
	final static String IDFORMADOCUMENTARIA = "IDFORMADOCUMENTARIA";
	final static String IDVITALRECORDCODE = "IDVITALRECORDCODE";
	final static String IDSTATOEFFICACIA = "IDSTATOEFFICACIA";
	final static String IDMEZZOTRASMISSIVO = "IDMEZZOTRASMISSIVO";
	final static String IDRUOLOCORRISPONDENTE = "IDRUOLOCORRISPONDENTE";
	
	
	
	final static String MITTENTE_MAIL = "MITTENTE_MAIL";
	final static String DATA_ELABORAZIONE = "DATA_ELABORAZIONE";
	final static String DESTINATARIO_MAIL_RIASSUNTIVA = "DESTINATARIO_MAIL_RIASSUNTIVA";
	final static String DIRIGENTE_REGIONALE = "DIRIGENTE_REGIONALE";
	final static String SERVIZIO_ATTIVO = "SERVIZIO_ATTIVO";




	public static final String COD_S = "S";
	public static final String COD_N = "N";

	final static String REGIONE_PIEMONTE_DESC = "REGIONE PIEMONTE";
	final static String COMUNE_DESC = "Torino";

	//final static long ID_MEZZO_TRASM_INTER = 3;
	//final static long ID_RUOLO_COMPETENZA = 1;

	final static int ID_TIPO_DOC_APE_FIRMATO = 2;

	final static public String NUMERO_MAX_RECORD = "NUMERO_MAX_RECORD";

	

	
	/*
	
	
	static final String ANPR_MUTAZIONE_FAMIGLIA_CONVIVENZA = "5001";
//	static final String ANPR_MUTAZIONE_AIRE = "5A06";
	static final String ANPR_MUTAZIONE_AIRE = "A006";
	static final String ANPR_MUTAZIONE_RESIDENZA = "5005";
	static final String ANPR_ANNULLAMENTO_MUTAZIONE = "5012";
	static final String ANPR_MUTAZIONE_STATO_CIVILE = "5008";
	static final String ANPR_MUTAZIONE_SCHEDA = "5008";
	static final String ANPR_RICHIESTA_PATERNITA_MATERNITA = "3001";
	static final String ANPR_INTERROGAZIONE_CITTADINO_FAMIGLIA_CONVIVENZA = "3002";
	static final String ANPR_GESTIONE_RICHIESTE = "3003";
	static final String ANPR_CONSULTAZIONE_NOTIFICHE = "3004";
	static final String ANPR_VISUALIZZAZIONE_ELABORATI = "3005";
	static final String ANPR_ISCRIZIONE_NASCITA = "1001";
	static final String ANPR_ISCRIZIONE_ALTRI_MOTIVI = "1002";
//	static final String ANPR_ISCRIZIONE_AIRE_NASCITA = "1A01";
//	static final String ANPR_ISCRIZIONE_AIRE_ALTRI_MOTIVI = "1A02";
	static final String ANPR_ISCRIZIONE_AIRE_NASCITA = "A001";
	static final String ANPR_ISCRIZIONE_AIRE_ALTRI_MOTIVI = "A002";
	static final String ANPR_ANNULLAMENTO_ISCRIZIONE = "1013";
	static final String ANPR_PROCEDIMENTO = "1014";
	static final String ANPR_CANCELLAZIONE_MORTE = "2001";
	static final String ANPR_CANCELLAZIONE_ALTRI_MOTIVI = "2003";
	static final String ANPR_ANNULLAMENTO_CANCELLAZIONE = "2011";
	static final String ANPR_CERTIFICAZIONE = "6001";
	static final String ANPR_OPERAZIONE_CUSTOM = "-";
	
	static final String STATO_ANPR_OK = "OK";
	static final String STATO_ANPR_KO = "KO";
	static final String STATO_ANPR_RITRASMETTI = "RT";
	static final String STATO_ANPR_IN_ELABORAZIONE = "EL";
  static final String STATO_SERVANPR_OK = "OK";
  static final String STATO_SERVANPR_KO = "KO";
	static final String STATO_SERVANPR_RITRASMETTI = "RT";
	static final String STATO_ANPR_OK_CON_WARNING = "WN";
	static final String STATO_ANPR_KO_MULTIPLI = "KM";
	
	static final String COD_DESTINATARIO= "ANPR00";
	static final String COD_ISTAT_MITTENTE= "001272";
	static final String FORNITORE_APPLICATIVO= "CSI-PIEMONTE";
	static final String NOME_APPLICATIVO= "NAO";
	static final String VERSIONE_APPLICATIVO= "13.0.0";
	
	static final String TIPO_INVIO_TEST= "TEST"; // IDENTIFICA AMBIENTE (PRE_SUB, PROD)
	static final String TIPO_INVIO_PRE_SUB= "PRE_SUB";
	static final String TIPO_INVIO_PROD= "PROD";
  static final String TIPO_INVIO_CURRENT= TIPO_INVIO_TEST;

	static final String TIPO_OPERAZIONE_RICHIESTA_CITTADINO= "C"; // CITTADINO
	static final String TIPO_OPERAZIONE_VARIAZIONE= "V"; // Variazione per correzioni di errori o anomalie dei dati
	static final String TIPO_OPERAZIONE_RETTIFICA= "R"; // Rettifica di una precedente operazione
	
	static final String ORIGINE_COGNOME_PADRE= "1"; 
	static final String ORIGINE_COGNOME_MADRE= "2"; 
  static final String ORIGINE_COGNOME_ENTRAMBI= "3"; 
	static final String ORIGINE_COGNOME_ALTRO= "4";
	
	static final String RICHIESTA_ATTRIBUZIONE_CODICE_FISCALE= "1";
	static final String RICHIESTA_SENZA_VALIDAZIONE_CODICE_FISCALE = "3";
	
  static final String ATTRIBUZIONE_FAMIGLIA_DEI_GENITORI = "1";
  static final String ATTRIBUZIONE_FAMIGLIA_DEL_PADRE = "2";
  static final String ATTRIBUZIONE_FAMIGLIA_DELLA_MADRE = "3";
  static final String ATTRIBUZIONE_FAMIGLIA_CONVIVENZA = "4";
	static final String ATTRIBUZIONE_FAMIGLIA_ALTRO = "5";
	
  static final String TIPO_SCHEDA_FAMIGLIA = "1";
  static final String TIPO_SCHEDA_CONVIVENZA = "2";

  static final String SCHEDA_ANAGRAFICA_INDIVIDUALE = "1";
  static final String SCHEDA_ANAGRAFICA_FAMIGLIA_CONVIVENZA = "2";
  static final String SCHEDA_ANAGRAFICA_FAMIGLIA = "3";
  static final String SCHEDA_ANAGRAFICA_CONVIVENZA = "4";

  static final String PARAM_DISABILITA_VERIFICA_XSD = "DisabilitaVerificaXSD";
	

  static final String SAML2_UTENTE = "SAML2Utente";
  static final String SAML2_PWD_KEYSTORE = "SAML2PwdKeystore";
  static final String SAML2_ID_POSTAZIONE_SERVER = "SAML2IdPostazioneServer";
  static final String SAML2_ALIAS_CERTIFICATO_SERVER = "SAML2AliasCertificatoServer";
  static final String SAML2_USER_ANPR = "SAML2UserAnpr";
  static final String SAML2_PWD_CERTIFICATO_SERVER = "SAML2PwdCertificatoServer";
  static final String SAML2_UTENTI_TEST = "SAML2UtentiTest";
*/
}
