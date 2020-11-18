/*******************************************************************************
 * SPDX-License-Identifier: EUPL-1.2
 * Copyright Regione Piemonte - 2020
 *******************************************************************************/
/*
 * 
 */
package it.csi.sicee.siceegwacta.business.dto;

import java.sql.Timestamp;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class Mail.
 */
public class Mail implements java.io.Serializable {

	
	/// Field [mittente]
	/** The _mittente. */
	private java.lang.String _mittente = null;

	/**
	 * Sets the mittente.
	 *
	 * @param val the new mittente
	 */
	public void setMittente(java.lang.String val) {
		_mittente = val;
	}

	/**
	 * Gets the mittente.
	 *
	 * @return the mittente
	 */
	public java.lang.String getMittente() {
		return _mittente;
	}

	/// Field [oggetto]
	/** The _oggetto. */
	private java.lang.String _oggetto = null;

	/**
	 * Sets the oggetto.
	 *
	 * @param val the new oggetto
	 */
	public void setOggetto(java.lang.String val) {
		_oggetto = val;
	}

	/**
	 * Gets the oggetto.
	 *
	 * @return the oggetto
	 */
	public java.lang.String getOggetto() {
		return _oggetto;
	}

	/// Field [testo]
	/** The _testo. */
	private java.lang.String _testo = null;

	/**
	 * Sets the testo.
	 *
	 * @param val the new testo
	 */
	public void setTesto(java.lang.String val) {
		_testo = val;
	}

	/**
	 * Gets the testo.
	 *
	 * @return the testo
	 */
	public java.lang.String getTesto() {
		return _testo;
	}
	
	/// Field [html]
	/** The _html. */
	private java.lang.String _html = null;

	/**
	 * Sets the html.
	 *
	 * @param val the new html
	 */
	public void setHtml(java.lang.String val) {
		_html = val;
	}

	/**
	 * Gets the html.
	 *
	 * @return the html
	 */
	public java.lang.String getHtml() {
		return _html;
	}

	/// Field [destinatario]
	/** The _destinatario. */
	private java.lang.String _destinatario = null;

	/**
	 * Sets the destinatario.
	 *
	 * @param val the new destinatario
	 */
	public void setDestinatario(java.lang.String val) {
		_destinatario = val;
	}

	/**
	 * Gets the destinatario.
	 *
	 * @return the destinatario
	 */
	public java.lang.String getDestinatario() {
		return _destinatario;
	}

	/// Field [host]
	/** The _host. */
	private java.lang.String _host = null;

	/**
	 * Sets the host.
	 *
	 * @param val the new host
	 */
	public void setHost(java.lang.String val) {
		_host = val;
	}

	/**
	 * Gets the host.
	 *
	 * @return the host
	 */
	public java.lang.String getHost() {
		return _host;
	}

	/// Field [port]
	/** The _port. */
	private java.lang.String _port = null;

	/**
	 * Sets the port.
	 *
	 * @param val the new port
	 */
	public void setPort(java.lang.String val) {
		_port = val;
	}

	/**
	 * Gets the port.
	 *
	 * @return the port
	 */
	public java.lang.String getPort() {
		return _port;
	}

	

	/// Field [protocol]
	/** The _protocol. */
	private java.lang.String _protocol = null;

	/**
	 * Sets the protocol.
	 *
	 * @param val the new protocol
	 */
	public void setProtocol(java.lang.String val) {
		_protocol = val;
	}

	/**
	 * Gets the protocol.
	 *
	 * @return the protocol
	 */
	public java.lang.String getProtocol() {
		return _protocol;
	}

	/// Field [pathAllegato]
	/** The _path allegato. */
	private java.lang.String _pathAllegato = null;

	/**
	 * Sets the path allegato.
	 *
	 * @param val the new path allegato
	 */
	public void setPathAllegato(java.lang.String val) {
		_pathAllegato = val;
	}

	/**
	 * Gets the path allegato.
	 *
	 * @return the path allegato
	 */
	public java.lang.String getPathAllegato() {
		return _pathAllegato;
	}

	/// Field [pathAllegatoXML]
	/** The _path allegato xml. */
	private java.lang.String _pathAllegatoXML = null;

	/**
	 * Sets the path allegato xml.
	 *
	 * @param val the new path allegato xml
	 */
	public void setPathAllegatoXML(java.lang.String val) {
		_pathAllegatoXML = val;
	}

	/**
	 * Gets the path allegato xml.
	 *
	 * @return the path allegato xml
	 */
	public java.lang.String getPathAllegatoXML() {
		return _pathAllegatoXML;
	}


	/// Field [nomeAllegato]
	/** The _nome allegato. */
	private java.lang.String _nomeAllegato = null;

	/**
	 * Sets the nome allegato.
	 *
	 * @param val the new nome allegato
	 */
	public void setNomeAllegato(java.lang.String val) {
		_nomeAllegato = val;
	}

	/**
	 * Gets the nome allegato.
	 *
	 * @return the nome allegato
	 */
	public java.lang.String getNomeAllegato() {
		return _nomeAllegato;
	}

	/// Field [destinatarioCC]
	/** The _destinatario cc. */
	private java.lang.String _destinatarioCC = null;

	/**
	 * Sets the destinatario cc.
	 *
	 * @param val the new destinatario cc
	 */
	public void setDestinatarioCC(java.lang.String val) {
		_destinatarioCC = val;
	}

	/**
	 * Gets the destinatario cc.
	 *
	 * @return the destinatario cc
	 */
	public java.lang.String getDestinatarioCC() {
		return _destinatarioCC;
	}

	
	Integer apeGestiti = null;
	
	
	/**
	 * @return the apeGestiti
	 */
	public Integer getApeGestiti() {
		return apeGestiti;
	}

	/**
	 * @param apeGestiti the apeGestiti to set
	 */
	public void setApeGestiti(Integer apeGestiti) {
		this.apeGestiti = apeGestiti;
	}

	Integer apeArchiviati = null;

	/**
	 * @return the apeArchiviati
	 */
	public Integer getApeArchiviati() {
		return apeArchiviati;
	}

	/**
	 * @param apeArchiviati the apeArchiviati to set
	 */
	public void setApeArchiviati(Integer apeArchiviati) {
		this.apeArchiviati = apeArchiviati;
	}

	
	Integer apeProtocollati = null;

	/**
	 * @return the apeProtocollati
	 */
	public Integer getApeProtocollati() {
		return apeProtocollati;
	}

	/**
	 * @param apeProtocollati the apeProtocollati to set
	 */
	public void setApeProtocollati(Integer apeProtocollati) {
		this.apeProtocollati = apeProtocollati;
	}

	Timestamp inizioProcesso = null;
	
	/**
	 * @return the inizioProcesso
	 */
	public Timestamp getInizioProcesso() {
		return inizioProcesso;
	}

	/**
	 * @param inizioProcesso the inizioProcesso to set
	 */
	public void setInizioProcesso(Timestamp inizioProcesso) {
		this.inizioProcesso = inizioProcesso;
	}
	
	ArrayList<String> elencoErrori = null;

	/**
	 * @return the elencoErrori
	 */
	public ArrayList<String> getElencoErrori() {
		return elencoErrori;
	}

	/**
	 * @param elencoErrori the elencoErrori to set
	 */
	public void setElencoErrori(ArrayList<String> elencoErrori) {
		this.elencoErrori = elencoErrori;
	}

	public void addElencoErrori(String errore) {
		if (elencoErrori == null)
		{
			elencoErrori = new ArrayList<String>();
		}
		elencoErrori.add(errore);
	}

	String numCerificatore = null;
	
	/**
	 * @return the numCerificatore
	 */
	public String getNumCerificatore() {
		return numCerificatore;
	}

	/**
	 * @param numCerificatore the numCerificatore to set
	 */
	public void setNumCerificatore(String numCerificatore) {
		this.numCerificatore = numCerificatore;
	}


	String progrCertificato = null;

	/**
	 * @return the progrCertificato
	 */
	public String getProgrCertificato() {
		return progrCertificato;
	}

	/**
	 * @param progrCertificato the progrCertificato to set
	 */
	public void setProgrCertificato(String progrCertificato) {
		this.progrCertificato = progrCertificato;
	}

	String annoCertificato = null;

	/**
	 * @return the annoCertificato
	 */
	public String getAnnoCertificato() {
		return annoCertificato;
	}

	/**
	 * @param annoCertificato the annoCertificato to set
	 */
	public void setAnnoCertificato(String annoCertificato) {
		this.annoCertificato = annoCertificato;
	}

	String numProtocollo = null;
	
	/**
	 * @return the numProtocollo
	 */
	public String getNumProtocollo() {
		return numProtocollo;
	}

	/**
	 * @param numProtocollo the numProtocollo to set
	 */
	public void setNumProtocollo(String numProtocollo) {
		this.numProtocollo = numProtocollo;
	}
	
	// il serial version uid e' fisso in quanto la classe in oggetto e' serializzabile
	// solo per la clusterizzazione della sessione web e non viene scambiata con altre
	// componenti.
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Costruttore vuoto del DTO.
	 */
	public Mail() {
		super();

	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		/*PROTECTED REGION ID(R271254851) ENABLED START*/
		/// inserire qui la logica desiderata per la rappresenatazione a stringa
		return super.toString();
		/*PROTECTED REGION END*/
	}
}
