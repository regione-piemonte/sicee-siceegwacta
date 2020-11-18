/*******************************************************************************
 * SPDX-License-Identifier: EUPL-1.2
 * Copyright Regione Piemonte - 2020
 *******************************************************************************/
package it.csi.sicee.siceegwacta.integration.db;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SICEE_T_DATI_ENER_2015 database table.
 * 
 */
@Entity
@Table(name="SICEE_T_DATI_ENER_2015")
public class SiceeTDatiEner2015 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SiceeTDatiEner2015PK id;

	@Column(name="ASOL_ASUP")
	private BigDecimal asolAsup;

	@Column(name="EMISSIONI_CO2")
	private BigDecimal emissioniCo2;

	@Column(name="ENERGIA_ESPORTATA")
	private BigDecimal energiaEsportata;

	@Column(name="EPGL_NREN_ESISTENTI")
	private BigDecimal epglNrenEsistenti;

	@Column(name="EPGL_NREN_GLOBALE")
	private BigDecimal epglNrenGlobale;

	@Column(name="EPGL_NREN_NUOVI")
	private BigDecimal epglNrenNuovi;

	@Column(name="EPGL_NREN_RACC_TOTALE")
	private BigDecimal epglNrenRaccTotale;

	@Column(name="EPGL_NREN_RIF")
	private BigDecimal epglNrenRif;

	@Column(name="EPGL_REN_GLOBALE")
	private BigDecimal epglRenGlobale;

	private BigDecimal eph;

	@Column(name="EPH_LIMITE")
	private BigDecimal ephLimite;

	@Column(name="FLG_CLIMAT_ESTIVA")
	private String flgClimatEstiva;

	@Column(name="FLG_CLIMAT_INVERNALE")
	private String flgClimatInvernale;

	@Column(name="FLG_EDIF_E0")
	private String flgEdifE0;

	@Column(name="FLG_ILLUMINAZIONE")
	private String flgIlluminazione;

	@Column(name="FLG_PROD_H2O_SANITARIA")
	private String flgProdH2oSanitaria;

	@Column(name="FLG_SMILE_ESTATE")
	private BigDecimal flgSmileEstate;

	@Column(name="FLG_SMILE_INVERNO")
	private BigDecimal flgSmileInverno;

	@Column(name="FLG_TRASPORTO_PERS_COSE")
	private String flgTrasportoPersCose;

	@Column(name="FLG_VENTIL_MECCANICA")
	private String flgVentilMeccanica;

	@Column(name="RAPPORTO_SV")
	private BigDecimal rapportoSv;

	private BigDecimal yie;

	//bi-directional many-to-one association to SiceeDClasseEnergetica
	@ManyToOne
	@JoinColumn(name="FK_CLASSE_ENERGETICA")
	private SiceeDClasseEnergetica siceeDClasseEnergetica1;

	//bi-directional many-to-one association to SiceeDClasseEnergetica
	@ManyToOne
	@JoinColumn(name="FK_CLASSE_ENER_NUOVI")
	private SiceeDClasseEnergetica siceeDClasseEnergetica2;

	//bi-directional many-to-one association to SiceeDClasseEnergetica
	@ManyToOne
	@JoinColumn(name="FK_CLASSE_ENER_ESISTENTI")
	private SiceeDClasseEnergetica siceeDClasseEnergetica3;

	//bi-directional many-to-one association to SiceeDClasseEnergetica
	@ManyToOne
	@JoinColumn(name="FK_CE_RACC_TOTALE")
	private SiceeDClasseEnergetica siceeDClasseEnergetica4;

	//bi-directional many-to-one association to SiceeTCertificato
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="ANNO", referencedColumnName="ANNO", insertable = false, updatable = false),
		@JoinColumn(name="ID_CERTIFICATORE", referencedColumnName="ID_CERTIFICATORE", insertable = false, updatable = false),
		@JoinColumn(name="PROGR_CERTIFICATO", referencedColumnName="PROGR_CERTIFICATO", insertable = false, updatable = false)
		})
	private SiceeTCertificato siceeTCertificato;

	public SiceeTDatiEner2015() {
	}

	public SiceeTDatiEner2015PK getId() {
		return this.id;
	}

	public void setId(SiceeTDatiEner2015PK id) {
		this.id = id;
	}

	public BigDecimal getAsolAsup() {
		return this.asolAsup;
	}

	public void setAsolAsup(BigDecimal asolAsup) {
		this.asolAsup = asolAsup;
	}

	public BigDecimal getEmissioniCo2() {
		return this.emissioniCo2;
	}

	public void setEmissioniCo2(BigDecimal emissioniCo2) {
		this.emissioniCo2 = emissioniCo2;
	}

	public BigDecimal getEnergiaEsportata() {
		return this.energiaEsportata;
	}

	public void setEnergiaEsportata(BigDecimal energiaEsportata) {
		this.energiaEsportata = energiaEsportata;
	}

	public BigDecimal getEpglNrenEsistenti() {
		return this.epglNrenEsistenti;
	}

	public void setEpglNrenEsistenti(BigDecimal epglNrenEsistenti) {
		this.epglNrenEsistenti = epglNrenEsistenti;
	}

	public BigDecimal getEpglNrenGlobale() {
		return this.epglNrenGlobale;
	}

	public void setEpglNrenGlobale(BigDecimal epglNrenGlobale) {
		this.epglNrenGlobale = epglNrenGlobale;
	}

	public BigDecimal getEpglNrenNuovi() {
		return this.epglNrenNuovi;
	}

	public void setEpglNrenNuovi(BigDecimal epglNrenNuovi) {
		this.epglNrenNuovi = epglNrenNuovi;
	}

	public BigDecimal getEpglNrenRaccTotale() {
		return this.epglNrenRaccTotale;
	}

	public void setEpglNrenRaccTotale(BigDecimal epglNrenRaccTotale) {
		this.epglNrenRaccTotale = epglNrenRaccTotale;
	}

	public BigDecimal getEpglNrenRif() {
		return this.epglNrenRif;
	}

	public void setEpglNrenRif(BigDecimal epglNrenRif) {
		this.epglNrenRif = epglNrenRif;
	}

	public BigDecimal getEpglRenGlobale() {
		return this.epglRenGlobale;
	}

	public void setEpglRenGlobale(BigDecimal epglRenGlobale) {
		this.epglRenGlobale = epglRenGlobale;
	}

	public BigDecimal getEph() {
		return this.eph;
	}

	public void setEph(BigDecimal eph) {
		this.eph = eph;
	}

	public BigDecimal getEphLimite() {
		return this.ephLimite;
	}

	public void setEphLimite(BigDecimal ephLimite) {
		this.ephLimite = ephLimite;
	}

	public String getFlgClimatEstiva() {
		return this.flgClimatEstiva;
	}

	public void setFlgClimatEstiva(String flgClimatEstiva) {
		this.flgClimatEstiva = flgClimatEstiva;
	}

	public String getFlgClimatInvernale() {
		return this.flgClimatInvernale;
	}

	public void setFlgClimatInvernale(String flgClimatInvernale) {
		this.flgClimatInvernale = flgClimatInvernale;
	}

	public String getFlgEdifE0() {
		return this.flgEdifE0;
	}

	public void setFlgEdifE0(String flgEdifE0) {
		this.flgEdifE0 = flgEdifE0;
	}

	public String getFlgIlluminazione() {
		return this.flgIlluminazione;
	}

	public void setFlgIlluminazione(String flgIlluminazione) {
		this.flgIlluminazione = flgIlluminazione;
	}

	public String getFlgProdH2oSanitaria() {
		return this.flgProdH2oSanitaria;
	}

	public void setFlgProdH2oSanitaria(String flgProdH2oSanitaria) {
		this.flgProdH2oSanitaria = flgProdH2oSanitaria;
	}

	public BigDecimal getFlgSmileEstate() {
		return this.flgSmileEstate;
	}

	public void setFlgSmileEstate(BigDecimal flgSmileEstate) {
		this.flgSmileEstate = flgSmileEstate;
	}

	public BigDecimal getFlgSmileInverno() {
		return this.flgSmileInverno;
	}

	public void setFlgSmileInverno(BigDecimal flgSmileInverno) {
		this.flgSmileInverno = flgSmileInverno;
	}

	public String getFlgTrasportoPersCose() {
		return this.flgTrasportoPersCose;
	}

	public void setFlgTrasportoPersCose(String flgTrasportoPersCose) {
		this.flgTrasportoPersCose = flgTrasportoPersCose;
	}

	public String getFlgVentilMeccanica() {
		return this.flgVentilMeccanica;
	}

	public void setFlgVentilMeccanica(String flgVentilMeccanica) {
		this.flgVentilMeccanica = flgVentilMeccanica;
	}

	public BigDecimal getRapportoSv() {
		return this.rapportoSv;
	}

	public void setRapportoSv(BigDecimal rapportoSv) {
		this.rapportoSv = rapportoSv;
	}

	public BigDecimal getYie() {
		return this.yie;
	}

	public void setYie(BigDecimal yie) {
		this.yie = yie;
	}

	public SiceeDClasseEnergetica getSiceeDClasseEnergetica1() {
		return this.siceeDClasseEnergetica1;
	}

	public void setSiceeDClasseEnergetica1(SiceeDClasseEnergetica siceeDClasseEnergetica1) {
		this.siceeDClasseEnergetica1 = siceeDClasseEnergetica1;
	}

	public SiceeDClasseEnergetica getSiceeDClasseEnergetica2() {
		return this.siceeDClasseEnergetica2;
	}

	public void setSiceeDClasseEnergetica2(SiceeDClasseEnergetica siceeDClasseEnergetica2) {
		this.siceeDClasseEnergetica2 = siceeDClasseEnergetica2;
	}

	public SiceeDClasseEnergetica getSiceeDClasseEnergetica3() {
		return this.siceeDClasseEnergetica3;
	}

	public void setSiceeDClasseEnergetica3(SiceeDClasseEnergetica siceeDClasseEnergetica3) {
		this.siceeDClasseEnergetica3 = siceeDClasseEnergetica3;
	}

	public SiceeDClasseEnergetica getSiceeDClasseEnergetica4() {
		return this.siceeDClasseEnergetica4;
	}

	public void setSiceeDClasseEnergetica4(SiceeDClasseEnergetica siceeDClasseEnergetica4) {
		this.siceeDClasseEnergetica4 = siceeDClasseEnergetica4;
	}

	public SiceeTCertificato getSiceeTCertificato() {
		return this.siceeTCertificato;
	}

	public void setSiceeTCertificato(SiceeTCertificato siceeTCertificato) {
		this.siceeTCertificato = siceeTCertificato;
	}

}