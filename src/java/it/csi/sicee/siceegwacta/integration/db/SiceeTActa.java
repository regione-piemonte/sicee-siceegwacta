/*******************************************************************************
 * SPDX-License-Identifier: EUPL-1.2
 * Copyright Regione Piemonte - 2020
 *******************************************************************************/
package it.csi.sicee.siceegwacta.integration.db;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the SICEE_T_ACTA database table.
 * 
 */
@Entity
@Table(name="SICEE_T_ACTA")
public class SiceeTActa implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SiceeTActaPK id;

	@Column(name="FAILED_STEP_ACTA")
	private String failedStepActa;

	@Column(name="ID_CLASSIFICAZIONE_ACTA")
	private String idClassificazioneActa;

	@Column(name="ID_DOC_ACTA")
	private String idDocActa;

	@Column(name="ID_PROTOCOLLO_ACTA")
	private String idProtocolloActa;

	@Column(name="MAIL_INVIATA")
	private String mailInviata;

	@Column(name="NUMERO_PROTOCOLLO")
	private String numeroProtocollo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="TIMESTAMP_ARCHIVIAZIONE")
	private Date timestampArchiviazione;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="TIMESTAMP_PROTOCOLLO")
	private Date timestampProtocollo;

	@Column(name="TIPO_DOCUMENTO_ACTA")
	private String tipoDocumentoActa;

	private String volume;

	//bi-directional many-to-one association to SiceeTCertificato
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="ANNO", referencedColumnName="ANNO", insertable = false, updatable = false),
		@JoinColumn(name="ID_CERTIFICATORE", referencedColumnName="ID_CERTIFICATORE", insertable = false, updatable = false),
		@JoinColumn(name="PROGR_CERTIFICATO", referencedColumnName="PROGR_CERTIFICATO", insertable = false, updatable = false)
		})
	private SiceeTCertificato siceeTCertificato;

	public SiceeTActa() {
	}

	public SiceeTActaPK getId() {
		return this.id;
	}

	public void setId(SiceeTActaPK id) {
		this.id = id;
	}

	public String getFailedStepActa() {
		return this.failedStepActa;
	}

	public void setFailedStepActa(String failedStepActa) {
		this.failedStepActa = failedStepActa;
	}

	public String getIdClassificazioneActa() {
		return this.idClassificazioneActa;
	}

	public void setIdClassificazioneActa(String idClassificazioneActa) {
		this.idClassificazioneActa = idClassificazioneActa;
	}

	public String getIdDocActa() {
		return this.idDocActa;
	}

	public void setIdDocActa(String idDocActa) {
		this.idDocActa = idDocActa;
	}

	public String getIdProtocolloActa() {
		return this.idProtocolloActa;
	}

	public void setIdProtocolloActa(String idProtocolloActa) {
		this.idProtocolloActa = idProtocolloActa;
	}

	public String getMailInviata() {
		return this.mailInviata;
	}

	public void setMailInviata(String mailInviata) {
		this.mailInviata = mailInviata;
	}

	public String getNumeroProtocollo() {
		return this.numeroProtocollo;
	}

	public void setNumeroProtocollo(String numeroProtocollo) {
		this.numeroProtocollo = numeroProtocollo;
	}

	public Date getTimestampArchiviazione() {
		return this.timestampArchiviazione;
	}

	public void setTimestampArchiviazione(Date timestampArchiviazione) {
		this.timestampArchiviazione = timestampArchiviazione;
	}

	public Date getTimestampProtocollo() {
		return this.timestampProtocollo;
	}

	public void setTimestampProtocollo(Date timestampProtocollo) {
		this.timestampProtocollo = timestampProtocollo;
	}

	public String getTipoDocumentoActa() {
		return this.tipoDocumentoActa;
	}

	public void setTipoDocumentoActa(String tipoDocumentoActa) {
		this.tipoDocumentoActa = tipoDocumentoActa;
	}

	public String getVolume() {
		return this.volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public SiceeTCertificato getSiceeTCertificato() {
		return this.siceeTCertificato;
	}

	public void setSiceeTCertificato(SiceeTCertificato siceeTCertificato) {
		this.siceeTCertificato = siceeTCertificato;
	}

}