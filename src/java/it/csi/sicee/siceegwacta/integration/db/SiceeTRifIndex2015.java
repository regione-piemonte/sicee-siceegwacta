/*******************************************************************************
 * SPDX-License-Identifier: EUPL-1.2
 * Copyright Regione Piemonte - 2020
 *******************************************************************************/
package it.csi.sicee.siceegwacta.integration.db;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the SICEE_T_RIF_INDEX_2015 database table.
 * 
 */
@Entity
@Table(name="SICEE_T_RIF_INDEX_2015")
public class SiceeTRifIndex2015 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_RIF_INDEX")
	private Integer idRifIndex;

	@Temporal(TemporalType.DATE)
	@Column(name="DT_CARICAMENTO")
	private Date dtCaricamento;

	@Column(name="FK_TIPO_DOC")
	private java.math.BigDecimal fkTipoDoc;

	@Column(name="\"HASH\"")
	private String hash;

	@Column(name="NOME_FILE")
	private String nomeFile;

	@Column(name="UID_INDEX")
	private String uidIndex;

	//bi-directional many-to-one association to SiceeTCertificato
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="ANNO", referencedColumnName="ANNO"),
		@JoinColumn(name="ID_CERTIFICATORE", referencedColumnName="ID_CERTIFICATORE"),
		@JoinColumn(name="PROGR_CERTIFICATO", referencedColumnName="PROGR_CERTIFICATO")
		})
	private SiceeTCertificato siceeTCertificato;

	public SiceeTRifIndex2015() {
	}

	public Integer getIdRifIndex() {
		return this.idRifIndex;
	}

	public void setIdRifIndex(Integer idRifIndex) {
		this.idRifIndex = idRifIndex;
	}

	public Date getDtCaricamento() {
		return this.dtCaricamento;
	}

	public void setDtCaricamento(Date dtCaricamento) {
		this.dtCaricamento = dtCaricamento;
	}

	public java.math.BigDecimal getFkTipoDoc() {
		return this.fkTipoDoc;
	}

	public void setFkTipoDoc(java.math.BigDecimal fkTipoDoc) {
		this.fkTipoDoc = fkTipoDoc;
	}

	public String getHash() {
		return this.hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getNomeFile() {
		return this.nomeFile;
	}

	public void setNomeFile(String nomeFile) {
		this.nomeFile = nomeFile;
	}

	public String getUidIndex() {
		return this.uidIndex;
	}

	public void setUidIndex(String uidIndex) {
		this.uidIndex = uidIndex;
	}

	public SiceeTCertificato getSiceeTCertificato() {
		return this.siceeTCertificato;
	}

	public void setSiceeTCertificato(SiceeTCertificato siceeTCertificato) {
		this.siceeTCertificato = siceeTCertificato;
	}

}