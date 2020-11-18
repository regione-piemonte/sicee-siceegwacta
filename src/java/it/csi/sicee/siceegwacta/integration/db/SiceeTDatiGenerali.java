/*******************************************************************************
 * SPDX-License-Identifier: EUPL-1.2
 * Copyright Regione Piemonte - 2020
 *******************************************************************************/
package it.csi.sicee.siceegwacta.integration.db;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SICEE_T_DATI_GENERALI database table.
 * 
 */
@Entity
@Table(name="SICEE_T_DATI_GENERALI")
public class SiceeTDatiGenerali implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SiceeTDatiGeneraliPK id;

	@Column(name="ANNO_COSTRUZIONE")
	private Integer annoCostruzione;

	@Column(name="ANNO_ULT_RIST")
	private Integer annoUltRist;

	@Column(name="CODICE_EDIFICIO_SCOLASTICO")
	private String codiceEdificioScolastico;

	@Column(name="DESC_TIPO_EDIFICIO")
	private String descTipoEdificio;

	@Column(name="EMISSIONI_SERRA")
	private BigDecimal emissioniSerra;

	@Column(name="ETTARI_BOSCO")
	private BigDecimal ettariBosco;

	@Column(name="FATTORE_FORMA")
	private BigDecimal fattoreForma;

	@Column(name="FK_CARATT_EDIFICIO")
	private BigDecimal fkCarattEdificio;

	@Column(name="FK_DEST_USO")
	private BigDecimal fkDestUso;

	@Column(name="FK_OGGETTO_APE")
	private BigDecimal fkOggettoApe;

	@Column(name="FK_PROPRIETA_EDI")
	private BigDecimal fkProprietaEdi;

	@Column(name="FK_TIPO_RISTRUTTURAZ")
	private BigDecimal fkTipoRistrutturaz;

	@Column(name="FK_TIPOL_COSTRUTTIVA")
	private BigDecimal fkTipolCostruttiva;

	@Column(name="FK_TIPOL_EDILIZIA")
	private BigDecimal fkTipolEdilizia;

	@Column(name="FLG_USO_PUBBLICO")
	private String flgUsoPubblico;

	@Column(name="GRADI_GIORNO")
	private BigDecimal gradiGiorno;

	@Column(name="NR_UM")
	private BigDecimal nrUm;

	private BigDecimal su;

	@Column(name="SUP_DISPERDENTE")
	private BigDecimal supDisperdente;

	@Column(name="SUP_DISPERDENTE_TOT")
	private BigDecimal supDisperdenteTot;

	@Column(name="SUP_RAFFRESCATA")
	private BigDecimal supRaffrescata;

	@Column(name="SUP_RISCALDATA")
	private BigDecimal supRiscaldata;

	@Column(name="TRASM_OPACHE")
	private BigDecimal trasmOpache;

	@Column(name="TRASM_TRASP")
	private BigDecimal trasmTrasp;

	@Column(name="VOL_LORDO_RAFFRESCATO")
	private BigDecimal volLordoRaffrescato;

	@Column(name="VOL_LORDO_RISCALDATO")
	private BigDecimal volLordoRiscaldato;

	@Column(name="ZONA_CLIMATICA")
	private String zonaClimatica;

	//bi-directional many-to-one association to SiceeDDestUso2015
	@ManyToOne
	@JoinColumn(name="FK_DEST_USO_2015")
	private SiceeDDestUso2015 siceeDDestUso2015;

	//bi-directional many-to-one association to SiceeTCertificato
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="ANNO", referencedColumnName="ANNO", insertable = false, updatable = false),
		@JoinColumn(name="ID_CERTIFICATORE", referencedColumnName="ID_CERTIFICATORE", insertable = false, updatable = false),
		@JoinColumn(name="PROGR_CERTIFICATO", referencedColumnName="PROGR_CERTIFICATO", insertable = false, updatable = false)
		})
	private SiceeTCertificato siceeTCertificato;

	public SiceeTDatiGenerali() {
	}

	public SiceeTDatiGeneraliPK getId() {
		return this.id;
	}

	public void setId(SiceeTDatiGeneraliPK id) {
		this.id = id;
	}

	public Integer getAnnoCostruzione() {
		return this.annoCostruzione;
	}

	public void setAnnoCostruzione(Integer annoCostruzione) {
		this.annoCostruzione = annoCostruzione;
	}

	public Integer getAnnoUltRist() {
		return this.annoUltRist;
	}

	public void setAnnoUltRist(Integer annoUltRist) {
		this.annoUltRist = annoUltRist;
	}

	public String getCodiceEdificioScolastico() {
		return this.codiceEdificioScolastico;
	}

	public void setCodiceEdificioScolastico(String codiceEdificioScolastico) {
		this.codiceEdificioScolastico = codiceEdificioScolastico;
	}

	public String getDescTipoEdificio() {
		return this.descTipoEdificio;
	}

	public void setDescTipoEdificio(String descTipoEdificio) {
		this.descTipoEdificio = descTipoEdificio;
	}

	public BigDecimal getEmissioniSerra() {
		return this.emissioniSerra;
	}

	public void setEmissioniSerra(BigDecimal emissioniSerra) {
		this.emissioniSerra = emissioniSerra;
	}

	public BigDecimal getEttariBosco() {
		return this.ettariBosco;
	}

	public void setEttariBosco(BigDecimal ettariBosco) {
		this.ettariBosco = ettariBosco;
	}

	public BigDecimal getFattoreForma() {
		return this.fattoreForma;
	}

	public void setFattoreForma(BigDecimal fattoreForma) {
		this.fattoreForma = fattoreForma;
	}

	public BigDecimal getFkCarattEdificio() {
		return this.fkCarattEdificio;
	}

	public void setFkCarattEdificio(BigDecimal fkCarattEdificio) {
		this.fkCarattEdificio = fkCarattEdificio;
	}

	public BigDecimal getFkDestUso() {
		return this.fkDestUso;
	}

	public void setFkDestUso(BigDecimal fkDestUso) {
		this.fkDestUso = fkDestUso;
	}

	public BigDecimal getFkOggettoApe() {
		return this.fkOggettoApe;
	}

	public void setFkOggettoApe(BigDecimal fkOggettoApe) {
		this.fkOggettoApe = fkOggettoApe;
	}

	public BigDecimal getFkProprietaEdi() {
		return this.fkProprietaEdi;
	}

	public void setFkProprietaEdi(BigDecimal fkProprietaEdi) {
		this.fkProprietaEdi = fkProprietaEdi;
	}

	public BigDecimal getFkTipoRistrutturaz() {
		return this.fkTipoRistrutturaz;
	}

	public void setFkTipoRistrutturaz(BigDecimal fkTipoRistrutturaz) {
		this.fkTipoRistrutturaz = fkTipoRistrutturaz;
	}

	public BigDecimal getFkTipolCostruttiva() {
		return this.fkTipolCostruttiva;
	}

	public void setFkTipolCostruttiva(BigDecimal fkTipolCostruttiva) {
		this.fkTipolCostruttiva = fkTipolCostruttiva;
	}

	public BigDecimal getFkTipolEdilizia() {
		return this.fkTipolEdilizia;
	}

	public void setFkTipolEdilizia(BigDecimal fkTipolEdilizia) {
		this.fkTipolEdilizia = fkTipolEdilizia;
	}

	public String getFlgUsoPubblico() {
		return this.flgUsoPubblico;
	}

	public void setFlgUsoPubblico(String flgUsoPubblico) {
		this.flgUsoPubblico = flgUsoPubblico;
	}

	public BigDecimal getGradiGiorno() {
		return this.gradiGiorno;
	}

	public void setGradiGiorno(BigDecimal gradiGiorno) {
		this.gradiGiorno = gradiGiorno;
	}

	public BigDecimal getNrUm() {
		return this.nrUm;
	}

	public void setNrUm(BigDecimal nrUm) {
		this.nrUm = nrUm;
	}

	public BigDecimal getSu() {
		return this.su;
	}

	public void setSu(BigDecimal su) {
		this.su = su;
	}

	public BigDecimal getSupDisperdente() {
		return this.supDisperdente;
	}

	public void setSupDisperdente(BigDecimal supDisperdente) {
		this.supDisperdente = supDisperdente;
	}

	public BigDecimal getSupDisperdenteTot() {
		return this.supDisperdenteTot;
	}

	public void setSupDisperdenteTot(BigDecimal supDisperdenteTot) {
		this.supDisperdenteTot = supDisperdenteTot;
	}

	public BigDecimal getSupRaffrescata() {
		return this.supRaffrescata;
	}

	public void setSupRaffrescata(BigDecimal supRaffrescata) {
		this.supRaffrescata = supRaffrescata;
	}

	public BigDecimal getSupRiscaldata() {
		return this.supRiscaldata;
	}

	public void setSupRiscaldata(BigDecimal supRiscaldata) {
		this.supRiscaldata = supRiscaldata;
	}

	public BigDecimal getTrasmOpache() {
		return this.trasmOpache;
	}

	public void setTrasmOpache(BigDecimal trasmOpache) {
		this.trasmOpache = trasmOpache;
	}

	public BigDecimal getTrasmTrasp() {
		return this.trasmTrasp;
	}

	public void setTrasmTrasp(BigDecimal trasmTrasp) {
		this.trasmTrasp = trasmTrasp;
	}

	public BigDecimal getVolLordoRaffrescato() {
		return this.volLordoRaffrescato;
	}

	public void setVolLordoRaffrescato(BigDecimal volLordoRaffrescato) {
		this.volLordoRaffrescato = volLordoRaffrescato;
	}

	public BigDecimal getVolLordoRiscaldato() {
		return this.volLordoRiscaldato;
	}

	public void setVolLordoRiscaldato(BigDecimal volLordoRiscaldato) {
		this.volLordoRiscaldato = volLordoRiscaldato;
	}

	public String getZonaClimatica() {
		return this.zonaClimatica;
	}

	public void setZonaClimatica(String zonaClimatica) {
		this.zonaClimatica = zonaClimatica;
	}

	public SiceeDDestUso2015 getSiceeDDestUso2015() {
		return this.siceeDDestUso2015;
	}

	public void setSiceeDDestUso2015(SiceeDDestUso2015 siceeDDestUso2015) {
		this.siceeDDestUso2015 = siceeDDestUso2015;
	}

	public SiceeTCertificato getSiceeTCertificato() {
		return this.siceeTCertificato;
	}

	public void setSiceeTCertificato(SiceeTCertificato siceeTCertificato) {
		this.siceeTCertificato = siceeTCertificato;
	}

}