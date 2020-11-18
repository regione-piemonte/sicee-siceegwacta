/*******************************************************************************
 * SPDX-License-Identifier: EUPL-1.2
 * Copyright Regione Piemonte - 2020
 *******************************************************************************/
package it.csi.sicee.siceegwacta.business.dto;

import java.util.Date;

public class Certificato {

	/** 
	 * This attribute maps to the column ID_CERTIFICATORE in the SICEE_T_CERTIFICATO table.
	 */
	protected String idCertificatore;

	/** 
	 * This attribute maps to the column PROGR_CERTIFICATO in the SICEE_T_CERTIFICATO table.
	 */
	protected String progrCertificato;

	/** 
	 * This attribute maps to the column ANNO in the SICEE_T_CERTIFICATO table.
	 */
	protected String anno;
	
	/** 
	 * This attribute maps to the column NUM_CERTIFICATORE in the SICEE_T_CERTIFICATORE table.
	 */
	protected String numCertificatore;

	/** 
	 * This attribute maps to the column COGNOME in the SICEE_T_CERTIFICATORE table.
	 */
	protected String cognomeCertificatore;

	/** 
	 * This attribute maps to the column NOME in the SICEE_T_CERTIFICATORE table.
	 */
	protected String nomeCertificatore;

	/** 
	 * This attribute maps to the column CODICE_FISCALE in the SICEE_T_CERTIFICATORE table.
	 */
	protected String cfCertificatore;
	
	/** 
	 * This attribute maps to the column EMAIL in the SICEE_T_CERTIFICATORE table.
	 */
	protected String email;

	/** 
	 * This attribute maps to the column DT_INIZIO in the SICEE_T_CERTIFICATO table.
	 */
	protected Date dtConsolidamento;
	
	
	/** 
	 * This attribute maps to the column DT_SCADENZA in the SICEE_T_CERTIFICATO table.
	 */
	protected Date dtScadenza;
	
	/** 
	 * This attribute maps to the column ID_COMUNE in the SICEE_T_CERTIFICATO table.
	 */
	protected String idComune;

	/** 
	 * This attribute maps to the column NOME_FILE in the SICEE_T_RIF_INDEX_2015 table.
	 */
	protected String nomeFile;

	/** 
	 * This attribute maps to the column UID_INDEX in the SICEE_T_RIF_INDEX_2015 table.
	 */
	protected String uidIndex;
	
	/** 
	 * This attribute maps to the column ANNO_COSTRUZIONE in the SICEE_T_DATI_GENERALI table.
	 */
	protected Integer annoCostruzione;

	/** 
	 * This attribute maps to the column DESCR in the SICEE_D_DEST_USO_2015 table.
	 */
	protected String destUso;
	
	/** 
	 * This attribute maps to the column DESCR in the SICEE_D_CLASSE_ENERGETICA table.
	 */
	protected String classe;

	/** 
	 * This attribute maps to the column VOLUME in the SICEE_T_ACTA table.
	 */
	protected String volume;
	
	
	/** 
	 * This attribute maps to the column ID_DOC_ACTA in the SICEE_T_ACTA table.
	 */
	protected String idDocActa;
	
	/** 
	 * This attribute maps to the column ID_CLASSIFICAZIONE_ACTA in the SICEE_T_ACTA table.
	 */
	protected String idClassificazioneActa;
	
	/** 
	 * This attribute maps to the column ID_PROTOCOLLO_ACTA in the SICEE_T_ACTA table.
	 */
	protected String idProtocolloActa;
	
	/** 
	 * This attribute maps to the column TIPO_DOCUMENTO_ACTA in the SICEE_T_ACTA table.
	 */
	protected String tipoDocumentoActa;
	
	/** 
	 * This attribute maps to the column FAILED_STEP_ACTA in the SICEE_T_ACTA table.
	 */
	protected String failedStepActa;
	
	/** 
	 * This attribute maps to the column TIMESTAMP_ARCHIVIAZIONE in the SICEE_T_ACTA table.
	 */
	protected Date timestampArchiviazione;
	
	/** 
	 * This attribute maps to the column NUMERO_PROTOCOLLO in the SICEE_T_ACTA table.
	 */
	protected String numeroProtocollo;
	

	/** 
	 * This attribute maps to the column MAIL_INVIATA in the SICEE_T_ACTA table.
	 */
	protected String mailInviata;

	/** 
	 * This attribute maps to the column TIMESTAMP_PROTOCOLLO in the SICEE_T_ACTA table.
	 */
	protected Date timestampProtocollo;

	/** 
	 * This attribute maps to the column DT_INIZIO in the SICEE_T_CERTIFICATO table.
	 */
	protected Date dtUpload;
	
	
	public String getIdCertificatore() {
		return idCertificatore;
	}

	public void setIdCertificatore(String idCertificatore) {
		this.idCertificatore = idCertificatore;
	}

	public String getProgrCertificato() {
		return progrCertificato;
	}

	public void setProgrCertificato(String progrCertificato) {
		this.progrCertificato = progrCertificato;
	}

	public String getAnno() {
		return anno;
	}

	public void setAnno(String anno) {
		this.anno = anno;
	}
	
	public String getCognomeCertificatore() {
		return cognomeCertificatore;
	}

	public void setCognomeCertificatore(String cognomeCertificatore) {
		this.cognomeCertificatore = cognomeCertificatore;
	}

	public String getNomeCertificatore() {
		return nomeCertificatore;
	}

	public void setNomeCertificatore(String nomeCertificatore) {
		this.nomeCertificatore = nomeCertificatore;
	}

	public String getCfCertificatore() {
		return cfCertificatore;
	}

	public void setCfCertificatore(String cfCertificatore) {
		this.cfCertificatore = cfCertificatore;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDtScadenza() {
		return dtScadenza;
	}

	public void setDtScadenza(Date dtScadenza) {
		this.dtScadenza = dtScadenza;
	}

	public String getIdComune() {
		return idComune;
	}

	public void setIdComune(String idComune) {
		this.idComune = idComune;
	}

	public String getNomeFile() {
		return nomeFile;
	}

	public void setNomeFile(String nomeFile) {
		this.nomeFile = nomeFile;
	}

	public String getUidIndex() {
		return uidIndex;
	}

	public void setUidIndex(String uidIndex) {
		this.uidIndex = uidIndex;
	}

	public Integer getAnnoCostruzione() {
		return annoCostruzione;
	}

	public void setAnnoCostruzione(Integer annoCostruzione) {
		this.annoCostruzione = annoCostruzione;
	}

	public String getDestUso() {
		return destUso;
	}

	public void setDestUso(String destUso) {
		this.destUso = destUso;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public String getNumCertificatore() {
		return numCertificatore;
	}

	public void setNumCertificatore(String numCertificatore) {
		this.numCertificatore = numCertificatore;
	}

	public String getIdDocActa() {
		return idDocActa;
	}

	public void setIdDocActa(String idDocActa) {
		this.idDocActa = idDocActa;
	}

	public String getIdClassificazioneActa() {
		return idClassificazioneActa;
	}

	public void setIdClassificazioneActa(String idClassificazioneActa) {
		this.idClassificazioneActa = idClassificazioneActa;
	}

	public String getIdProtocolloActa() {
		return idProtocolloActa;
	}

	public void setIdProtocolloActa(String idProtocolloActa) {
		this.idProtocolloActa = idProtocolloActa;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getTipoDocumentoActa() {
		return tipoDocumentoActa;
	}

	public void setTipoDocumentoActa(String tipoDocumentoActa) {
		this.tipoDocumentoActa = tipoDocumentoActa;
	}

	public String getFailedStepActa() {
		return failedStepActa;
	}

	public void setFailedStepActa(String failedStepActa) {
		this.failedStepActa = failedStepActa;
	}

	public Date getTimestampArchiviazione() {
		return timestampArchiviazione;
	}

	public void setTimestampArchiviazione(Date timestampArchiviazione) {
		this.timestampArchiviazione = timestampArchiviazione;
	}

	public String getNumeroProtocollo() {
		return numeroProtocollo;
	}

	public void setNumeroProtocollo(String numeroProtocollo) {
		this.numeroProtocollo = numeroProtocollo;
	}

	public String getMailInviata() {
		return mailInviata;
	}

	public void setMailInviata(String mailInviata) {
		this.mailInviata = mailInviata;
	}

	public Date getTimestampProtocollo() {
		return timestampProtocollo;
	}

	public void setTimestampProtocollo(Date timestampProtocollo) {
		this.timestampProtocollo = timestampProtocollo;
	}

	public Date getDtConsolidamento() {
		return dtConsolidamento;
	}

	public void setDtConsolidamento(Date dtConsolidamento) {
		this.dtConsolidamento = dtConsolidamento;
	}

	public Date getDtUpload() {
		return dtUpload;
	}

	public void setDtUpload(Date dtUpload) {
		this.dtUpload = dtUpload;
	}
	
}
