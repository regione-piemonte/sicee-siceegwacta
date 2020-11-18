/*******************************************************************************
 * SPDX-License-Identifier: EUPL-1.2
 * Copyright Regione Piemonte - 2020
 *******************************************************************************/
package it.csi.sicee.siceegwacta.integration.db;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the SICEE_T_CERTIFICATO database table.
 * 
 */
@Entity
@Table(name="SICEE_T_CERTIFICATO")
public class SiceeTCertificato implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SiceeTCertificatoPK id;

	private String cap;

	@Column(name="COD_FISC_COCERTIFICATORE")
	private String codFiscCocertificatore;

	@Column(name="CODICE_COMUNE_CATASTALE")
	private String codiceComuneCatastale;

	@Column(name="COGNOME_COCERTIFICATORE")
	private String cognomeCocertificatore;

	@Column(name="COORD_E")
	private BigDecimal coordE;

	@Column(name="COORD_N")
	private BigDecimal coordN;

	@Column(name="DESC_COMUNE")
	private String descComune;

	@Column(name="DESC_INDIRIZZO")
	private String descIndirizzo;

	@Column(name="DESC_PROV")
	private String descProv;

	@Column(name="DESC_REGIONE")
	private String descRegione;

	@Temporal(TemporalType.DATE)
	@Column(name="DT_ACQUISTO")
	private Date dtAcquisto;

	@Temporal(TemporalType.DATE)
	@Column(name="DT_FOTO")
	private Date dtFoto;

	@Temporal(TemporalType.DATE)
	@Column(name="DT_INIZIO")
	private Date dtInizio;

	@Temporal(TemporalType.DATE)
	@Column(name="DT_SCADENZA")
	private Date dtScadenza;

	@Temporal(TemporalType.DATE)
	@Column(name="DT_SOSTITUZIONE")
	private Date dtSostituzione;

	@Temporal(TemporalType.DATE)
	@Column(name="DT_UPLOAD")
	private Date dtUpload;

	@Column(name="FK_DICHIARAZIONE")
	private BigDecimal fkDichiarazione;

	@Column(name="FK_MOTIVO_SOST")
	private BigDecimal fkMotivoSost;

	@Column(name="FK_SCADENZA_CERT")
	private BigDecimal fkScadenzaCert;

	@Column(name="FK_TRANSAZIONE_ACE")
	private BigDecimal fkTransazioneAce;

	@Column(name="FLG_DICHIARAZIONE")
	private String flgDichiarazione;

	@Column(name="FLG_EDIFICIO_PERFORMANTE")
	private String flgEdificioPerformante;

	@Column(name="FLG_NO_RACCOMAND")
	private String flgNoRaccomand;

	@Column(name="FLG_OLD")
	private String flgOld;

	@Column(name="FLG_SIGMATER")
	private String flgSigmater;

	@Column(name="FLG_STAMPA_DEF")
	private String flgStampaDef;

	private String foglio;

	@Column(name="ID_COMUNE")
	private String idComune;

	@Column(name="ID_INDIRIZZO")
	private BigDecimal idIndirizzo;

	@Column(name="ID_PROV")
	private String idProv;

	@Column(name="ID_REGIONE")
	private String idRegione;

	@Column(name="IDENTIFIC_FOTO")
	private String identificFoto;

	@Column(name="IDENTIFIC_PDF")
	private String identificPdf;

	private String interno;

	@Column(name="LUOGO_DICHIARAZIONE")
	private String luogoDichiarazione;

	@Column(name="NOME_ACE_FIRMATO")
	private String nomeAceFirmato;

	@Column(name="NOME_COCERTIFICATORE")
	private String nomeCocertificatore;

	@Column(name="NOME_FOTO")
	private String nomeFoto;

	@Column(name="NOME_PDF")
	private String nomePdf;

	private String note;

	@Column(name="NOTE_SOSTITUZIONE")
	private String noteSostituzione;

	@Column(name="NUM_APPARTAMENTI")
	private BigDecimal numAppartamenti;

	@Column(name="NUM_CIVICO")
	private String numCivico;

	@Column(name="NUM_COCERTIFICATORE")
	private String numCocertificatore;

	@Column(name="NUM_PIANI_COMPLESSIVI")
	private BigDecimal numPianiComplessivi;

	@Column(name="NUM_PIANI_FT_RISC")
	private BigDecimal numPianiFtRisc;

	private String particella;

	private BigDecimal piano;

	@Column(name="RIF_CATASTO")
	private String rifCatasto;

	private String scala;

	private String sezione;

	private String subalterno;

	//bi-directional many-to-one association to SiceeDStatoCert
	@ManyToOne
	@JoinColumn(name="FK_STATO")
	private SiceeDStatoCert siceeDStatoCert;

	//bi-directional many-to-one association to SiceeTActa
	@OneToMany(mappedBy="siceeTCertificato", fetch=FetchType.LAZY)
	private Set<SiceeTActa> siceeTActas;

	//bi-directional many-to-one association to SiceeTCertificatore
	@ManyToOne
	@JoinColumn(name="ID_CERTIFICATORE", insertable = false, updatable = false)
	private SiceeTCertificatore siceeTCertificatore;

	//bi-directional many-to-one association to SiceeTDatiEner2015
	@OneToMany(mappedBy="siceeTCertificato", fetch=FetchType.LAZY)
	private Set<SiceeTDatiEner2015> siceeTDatiEner2015s;

	//bi-directional many-to-one association to SiceeTDatiGenerali
	@OneToMany(mappedBy="siceeTCertificato", fetch=FetchType.LAZY)
	private Set<SiceeTDatiGenerali> siceeTDatiGeneralis;

	//bi-directional many-to-one association to SiceeTRifIndex2015
	@OneToMany(mappedBy="siceeTCertificato", fetch=FetchType.LAZY)
	private Set<SiceeTRifIndex2015> siceeTRifIndex2015s;

	public SiceeTCertificato() {
	}

	public SiceeTCertificatoPK getId() {
		return this.id;
	}

	public void setId(SiceeTCertificatoPK id) {
		this.id = id;
	}

	public String getCap() {
		return this.cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getCodFiscCocertificatore() {
		return this.codFiscCocertificatore;
	}

	public void setCodFiscCocertificatore(String codFiscCocertificatore) {
		this.codFiscCocertificatore = codFiscCocertificatore;
	}

	public String getCodiceComuneCatastale() {
		return this.codiceComuneCatastale;
	}

	public void setCodiceComuneCatastale(String codiceComuneCatastale) {
		this.codiceComuneCatastale = codiceComuneCatastale;
	}

	public String getCognomeCocertificatore() {
		return this.cognomeCocertificatore;
	}

	public void setCognomeCocertificatore(String cognomeCocertificatore) {
		this.cognomeCocertificatore = cognomeCocertificatore;
	}

	public BigDecimal getCoordE() {
		return this.coordE;
	}

	public void setCoordE(BigDecimal coordE) {
		this.coordE = coordE;
	}

	public BigDecimal getCoordN() {
		return this.coordN;
	}

	public void setCoordN(BigDecimal coordN) {
		this.coordN = coordN;
	}

	public String getDescComune() {
		return this.descComune;
	}

	public void setDescComune(String descComune) {
		this.descComune = descComune;
	}

	public String getDescIndirizzo() {
		return this.descIndirizzo;
	}

	public void setDescIndirizzo(String descIndirizzo) {
		this.descIndirizzo = descIndirizzo;
	}

	public String getDescProv() {
		return this.descProv;
	}

	public void setDescProv(String descProv) {
		this.descProv = descProv;
	}

	public String getDescRegione() {
		return this.descRegione;
	}

	public void setDescRegione(String descRegione) {
		this.descRegione = descRegione;
	}

	public Date getDtAcquisto() {
		return this.dtAcquisto;
	}

	public void setDtAcquisto(Date dtAcquisto) {
		this.dtAcquisto = dtAcquisto;
	}

	public Date getDtFoto() {
		return this.dtFoto;
	}

	public void setDtFoto(Date dtFoto) {
		this.dtFoto = dtFoto;
	}

	public Date getDtInizio() {
		return this.dtInizio;
	}

	public void setDtInizio(Date dtInizio) {
		this.dtInizio = dtInizio;
	}

	public Date getDtScadenza() {
		return this.dtScadenza;
	}

	public void setDtScadenza(Date dtScadenza) {
		this.dtScadenza = dtScadenza;
	}

	public Date getDtSostituzione() {
		return this.dtSostituzione;
	}

	public void setDtSostituzione(Date dtSostituzione) {
		this.dtSostituzione = dtSostituzione;
	}

	public Date getDtUpload() {
		return this.dtUpload;
	}

	public void setDtUpload(Date dtUpload) {
		this.dtUpload = dtUpload;
	}

	public BigDecimal getFkDichiarazione() {
		return this.fkDichiarazione;
	}

	public void setFkDichiarazione(BigDecimal fkDichiarazione) {
		this.fkDichiarazione = fkDichiarazione;
	}

	public BigDecimal getFkMotivoSost() {
		return this.fkMotivoSost;
	}

	public void setFkMotivoSost(BigDecimal fkMotivoSost) {
		this.fkMotivoSost = fkMotivoSost;
	}

	public BigDecimal getFkScadenzaCert() {
		return this.fkScadenzaCert;
	}

	public void setFkScadenzaCert(BigDecimal fkScadenzaCert) {
		this.fkScadenzaCert = fkScadenzaCert;
	}

	public BigDecimal getFkTransazioneAce() {
		return this.fkTransazioneAce;
	}

	public void setFkTransazioneAce(BigDecimal fkTransazioneAce) {
		this.fkTransazioneAce = fkTransazioneAce;
	}

	public String getFlgDichiarazione() {
		return this.flgDichiarazione;
	}

	public void setFlgDichiarazione(String flgDichiarazione) {
		this.flgDichiarazione = flgDichiarazione;
	}

	public String getFlgEdificioPerformante() {
		return this.flgEdificioPerformante;
	}

	public void setFlgEdificioPerformante(String flgEdificioPerformante) {
		this.flgEdificioPerformante = flgEdificioPerformante;
	}

	public String getFlgNoRaccomand() {
		return this.flgNoRaccomand;
	}

	public void setFlgNoRaccomand(String flgNoRaccomand) {
		this.flgNoRaccomand = flgNoRaccomand;
	}

	public String getFlgOld() {
		return this.flgOld;
	}

	public void setFlgOld(String flgOld) {
		this.flgOld = flgOld;
	}

	public String getFlgSigmater() {
		return this.flgSigmater;
	}

	public void setFlgSigmater(String flgSigmater) {
		this.flgSigmater = flgSigmater;
	}

	public String getFlgStampaDef() {
		return this.flgStampaDef;
	}

	public void setFlgStampaDef(String flgStampaDef) {
		this.flgStampaDef = flgStampaDef;
	}

	public String getFoglio() {
		return this.foglio;
	}

	public void setFoglio(String foglio) {
		this.foglio = foglio;
	}

	public String getIdComune() {
		return this.idComune;
	}

	public void setIdComune(String idComune) {
		this.idComune = idComune;
	}

	public BigDecimal getIdIndirizzo() {
		return this.idIndirizzo;
	}

	public void setIdIndirizzo(BigDecimal idIndirizzo) {
		this.idIndirizzo = idIndirizzo;
	}

	public String getIdProv() {
		return this.idProv;
	}

	public void setIdProv(String idProv) {
		this.idProv = idProv;
	}

	public String getIdRegione() {
		return this.idRegione;
	}

	public void setIdRegione(String idRegione) {
		this.idRegione = idRegione;
	}

	public String getIdentificFoto() {
		return this.identificFoto;
	}

	public void setIdentificFoto(String identificFoto) {
		this.identificFoto = identificFoto;
	}

	public String getIdentificPdf() {
		return this.identificPdf;
	}

	public void setIdentificPdf(String identificPdf) {
		this.identificPdf = identificPdf;
	}

	public String getInterno() {
		return this.interno;
	}

	public void setInterno(String interno) {
		this.interno = interno;
	}

	public String getLuogoDichiarazione() {
		return this.luogoDichiarazione;
	}

	public void setLuogoDichiarazione(String luogoDichiarazione) {
		this.luogoDichiarazione = luogoDichiarazione;
	}

	public String getNomeAceFirmato() {
		return this.nomeAceFirmato;
	}

	public void setNomeAceFirmato(String nomeAceFirmato) {
		this.nomeAceFirmato = nomeAceFirmato;
	}

	public String getNomeCocertificatore() {
		return this.nomeCocertificatore;
	}

	public void setNomeCocertificatore(String nomeCocertificatore) {
		this.nomeCocertificatore = nomeCocertificatore;
	}

	public String getNomeFoto() {
		return this.nomeFoto;
	}

	public void setNomeFoto(String nomeFoto) {
		this.nomeFoto = nomeFoto;
	}

	public String getNomePdf() {
		return this.nomePdf;
	}

	public void setNomePdf(String nomePdf) {
		this.nomePdf = nomePdf;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getNoteSostituzione() {
		return this.noteSostituzione;
	}

	public void setNoteSostituzione(String noteSostituzione) {
		this.noteSostituzione = noteSostituzione;
	}

	public BigDecimal getNumAppartamenti() {
		return this.numAppartamenti;
	}

	public void setNumAppartamenti(BigDecimal numAppartamenti) {
		this.numAppartamenti = numAppartamenti;
	}

	public String getNumCivico() {
		return this.numCivico;
	}

	public void setNumCivico(String numCivico) {
		this.numCivico = numCivico;
	}

	public String getNumCocertificatore() {
		return this.numCocertificatore;
	}

	public void setNumCocertificatore(String numCocertificatore) {
		this.numCocertificatore = numCocertificatore;
	}

	public BigDecimal getNumPianiComplessivi() {
		return this.numPianiComplessivi;
	}

	public void setNumPianiComplessivi(BigDecimal numPianiComplessivi) {
		this.numPianiComplessivi = numPianiComplessivi;
	}

	public BigDecimal getNumPianiFtRisc() {
		return this.numPianiFtRisc;
	}

	public void setNumPianiFtRisc(BigDecimal numPianiFtRisc) {
		this.numPianiFtRisc = numPianiFtRisc;
	}

	public String getParticella() {
		return this.particella;
	}

	public void setParticella(String particella) {
		this.particella = particella;
	}

	public BigDecimal getPiano() {
		return this.piano;
	}

	public void setPiano(BigDecimal piano) {
		this.piano = piano;
	}

	public String getRifCatasto() {
		return this.rifCatasto;
	}

	public void setRifCatasto(String rifCatasto) {
		this.rifCatasto = rifCatasto;
	}

	public String getScala() {
		return this.scala;
	}

	public void setScala(String scala) {
		this.scala = scala;
	}

	public String getSezione() {
		return this.sezione;
	}

	public void setSezione(String sezione) {
		this.sezione = sezione;
	}

	public String getSubalterno() {
		return this.subalterno;
	}

	public void setSubalterno(String subalterno) {
		this.subalterno = subalterno;
	}

	public SiceeDStatoCert getSiceeDStatoCert() {
		return this.siceeDStatoCert;
	}

	public void setSiceeDStatoCert(SiceeDStatoCert siceeDStatoCert) {
		this.siceeDStatoCert = siceeDStatoCert;
	}

	public Set<SiceeTActa> getSiceeTActas() {
		return this.siceeTActas;
	}

	public void setSiceeTActas(Set<SiceeTActa> siceeTActas) {
		this.siceeTActas = siceeTActas;
	}

	public SiceeTActa addSiceeTActa(SiceeTActa siceeTActa) {
		getSiceeTActas().add(siceeTActa);
		siceeTActa.setSiceeTCertificato(this);

		return siceeTActa;
	}

	public SiceeTActa removeSiceeTActa(SiceeTActa siceeTActa) {
		getSiceeTActas().remove(siceeTActa);
		siceeTActa.setSiceeTCertificato(null);

		return siceeTActa;
	}

	public SiceeTCertificatore getSiceeTCertificatore() {
		return this.siceeTCertificatore;
	}

	public void setSiceeTCertificatore(SiceeTCertificatore siceeTCertificatore) {
		this.siceeTCertificatore = siceeTCertificatore;
	}

	public Set<SiceeTDatiEner2015> getSiceeTDatiEner2015s() {
		return this.siceeTDatiEner2015s;
	}

	public void setSiceeTDatiEner2015s(Set<SiceeTDatiEner2015> siceeTDatiEner2015s) {
		this.siceeTDatiEner2015s = siceeTDatiEner2015s;
	}

	public SiceeTDatiEner2015 addSiceeTDatiEner2015(SiceeTDatiEner2015 siceeTDatiEner2015) {
		getSiceeTDatiEner2015s().add(siceeTDatiEner2015);
		siceeTDatiEner2015.setSiceeTCertificato(this);

		return siceeTDatiEner2015;
	}

	public SiceeTDatiEner2015 removeSiceeTDatiEner2015(SiceeTDatiEner2015 siceeTDatiEner2015) {
		getSiceeTDatiEner2015s().remove(siceeTDatiEner2015);
		siceeTDatiEner2015.setSiceeTCertificato(null);

		return siceeTDatiEner2015;
	}

	public Set<SiceeTDatiGenerali> getSiceeTDatiGeneralis() {
		return this.siceeTDatiGeneralis;
	}

	public void setSiceeTDatiGeneralis(Set<SiceeTDatiGenerali> siceeTDatiGeneralis) {
		this.siceeTDatiGeneralis = siceeTDatiGeneralis;
	}

	public SiceeTDatiGenerali addSiceeTDatiGenerali(SiceeTDatiGenerali siceeTDatiGenerali) {
		getSiceeTDatiGeneralis().add(siceeTDatiGenerali);
		siceeTDatiGenerali.setSiceeTCertificato(this);

		return siceeTDatiGenerali;
	}

	public SiceeTDatiGenerali removeSiceeTDatiGenerali(SiceeTDatiGenerali siceeTDatiGenerali) {
		getSiceeTDatiGeneralis().remove(siceeTDatiGenerali);
		siceeTDatiGenerali.setSiceeTCertificato(null);

		return siceeTDatiGenerali;
	}

	public Set<SiceeTRifIndex2015> getSiceeTRifIndex2015s() {
		return this.siceeTRifIndex2015s;
	}

	public void setSiceeTRifIndex2015s(Set<SiceeTRifIndex2015> siceeTRifIndex2015s) {
		this.siceeTRifIndex2015s = siceeTRifIndex2015s;
	}

	public SiceeTRifIndex2015 addSiceeTRifIndex2015(SiceeTRifIndex2015 siceeTRifIndex2015) {
		getSiceeTRifIndex2015s().add(siceeTRifIndex2015);
		siceeTRifIndex2015.setSiceeTCertificato(this);

		return siceeTRifIndex2015;
	}

	public SiceeTRifIndex2015 removeSiceeTRifIndex2015(SiceeTRifIndex2015 siceeTRifIndex2015) {
		getSiceeTRifIndex2015s().remove(siceeTRifIndex2015);
		siceeTRifIndex2015.setSiceeTCertificato(null);

		return siceeTRifIndex2015;
	}

}