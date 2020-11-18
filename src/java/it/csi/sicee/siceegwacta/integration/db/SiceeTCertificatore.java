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
 * The persistent class for the SICEE_T_CERTIFICATORE database table.
 * 
 */
@Entity
@Table(name="SICEE_T_CERTIFICATORE")
public class SiceeTCertificatore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_CERTIFICATORE")
	private String idCertificatore;

	@Column(name="ACCETTAZIONE_TECNICO")
	private String accettazioneTecnico;

	private String cap;

	@Column(name="CAP_ESTERO")
	private String capEstero;

	private String cell;

	@Column(name="CITTA_ESTERA")
	private String cittaEstera;

	@Column(name="CIVICO_ESTERO")
	private String civicoEstero;

	@Column(name="CODICE_FISCALE")
	private String codiceFiscale;

	private String cognome;

	@Column(name="COMPETENZE_NAZIONALI")
	private String competenzeNazionali;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_BLOCCO_CERTIFICATORE")
	private Date dataBloccoCertificatore;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_ISCR_ALBO")
	private Date dataIscrAlbo;

	@Column(name="DESC_COMUNE_NASCITA")
	private String descComuneNascita;

	@Column(name="DESC_COMUNE_RESIDENZA")
	private String descComuneResidenza;

	@Column(name="DESC_INDIRIZZO")
	private String descIndirizzo;

	@Column(name="DESC_PROV_NASCITA")
	private String descProvNascita;

	@Column(name="DESC_PROV_ORDINE")
	private String descProvOrdine;

	@Column(name="DESC_PROV_RESIDENZA")
	private String descProvResidenza;

	@Column(name="DESC_REGIONE_NASCITA")
	private String descRegioneNascita;

	@Column(name="DESC_REGIONE_RESIDENZA")
	private String descRegioneResidenza;

	@Temporal(TemporalType.DATE)
	@Column(name="DT_AGG_DATI")
	private Date dtAggDati;

	@Temporal(TemporalType.DATE)
	@Column(name="DT_AGG_EMAIL")
	private Date dtAggEmail;

	@Temporal(TemporalType.DATE)
	@Column(name="DT_EMAIL_VERIFICA")
	private Date dtEmailVerifica;

	@Temporal(TemporalType.DATE)
	@Column(name="DT_ISCR_ELENCO")
	private Date dtIscrElenco;

	@Temporal(TemporalType.DATE)
	@Column(name="DT_NASCITA")
	private Date dtNascita;

	@Temporal(TemporalType.DATE)
	@Column(name="DT_SCADENZA_ELENCO")
	private Date dtScadenzaElenco;

	private String email;

	@Column(name="EMAIL_PEC")
	private String emailPec;

	private String fax;

	@Column(name="FK_AZIENDA")
	private BigDecimal fkAzienda;

	@Column(name="FK_BLOCCO_CERTIFICATORE")
	private BigDecimal fkBloccoCertificatore;

	@Column(name="FK_ISTRUZIONE")
	private BigDecimal fkIstruzione;

	@Column(name="FK_TITOLO")
	private String fkTitolo;

	@Column(name="FLG_AGG_DATI")
	private String flgAggDati;

	@Column(name="FLG_AGG_EMAIL")
	private String flgAggEmail;

	@Column(name="FLG_CANC_ELENCO")
	private String flgCancElenco;

	@Column(name="FLG_CONSENSO_CELL")
	private String flgConsensoCell;

	@Column(name="FLG_CONSENSO_EMAIL")
	private String flgConsensoEmail;

	@Column(name="FLG_CONSENSO_TEL")
	private String flgConsensoTel;

	@Column(name="FLG_EDIFICI")
	private String flgEdifici;

	@Column(name="FLG_ESAME_SOSTENUTO")
	private String flgEsameSostenuto;

	@Column(name="FLG_IMPIANTI")
	private String flgImpianti;

	@Column(name="FLG_NEWSLETTER")
	private String flgNewsletter;

	@Column(name="FLG_PAGAMENTO")
	private String flgPagamento;

	@Column(name="FLG_RESIDENZA_ITALIA")
	private String flgResidenzaItalia;

	@Column(name="FLG_VERIFICATO")
	private String flgVerificato;

	@Column(name="ID_COMUNE_NASCITA")
	private String idComuneNascita;

	@Column(name="ID_COMUNE_RESIDENZA")
	private String idComuneResidenza;

	@Column(name="ID_INDIRIZZO")
	private BigDecimal idIndirizzo;

	@Column(name="ID_PROV_NASCITA")
	private String idProvNascita;

	@Column(name="ID_PROV_ORDINE")
	private String idProvOrdine;

	@Column(name="ID_PROV_RESIDENZA")
	private String idProvResidenza;

	@Column(name="ID_REGIONE_NASCITA")
	private String idRegioneNascita;

	@Column(name="ID_REGIONE_RESIDENZA")
	private String idRegioneResidenza;

	@Column(name="ISCRITTO_ORDINE")
	private String iscrittoOrdine;

	private String nome;

	private String note;

	@Column(name="NOTE_BLOCCO_CERTIFICATORE")
	private String noteBloccoCertificatore;

	@Column(name="NUM_CERTIFICATORE")
	private String numCertificatore;

	@Column(name="NUM_CIVICO_RESIDENZA")
	private String numCivicoResidenza;

	@Column(name="NUM_ISCR_ALBO")
	private String numIscrAlbo;

	@Column(name="NUM_TOT_ACE")
	private BigDecimal numTotAce;

	private String ordine;

	private String settore;

	private String sezione;

	@Column(name="SITO_WEB")
	private String sitoWeb;

	@Column(name="STATO_ESTERO")
	private String statoEstero;

	@Column(name="STATO_RES_ESTERO")
	private String statoResEstero;

	private String telefono;

	@Column(name="VIA_ESTERA")
	private String viaEstera;

	//bi-directional many-to-one association to SiceeTCertificato
	@OneToMany(mappedBy="siceeTCertificatore", fetch=FetchType.LAZY)
	private Set<SiceeTCertificato> siceeTCertificatos;

	public SiceeTCertificatore() {
	}

	public String getIdCertificatore() {
		return this.idCertificatore;
	}

	public void setIdCertificatore(String idCertificatore) {
		this.idCertificatore = idCertificatore;
	}

	public String getAccettazioneTecnico() {
		return this.accettazioneTecnico;
	}

	public void setAccettazioneTecnico(String accettazioneTecnico) {
		this.accettazioneTecnico = accettazioneTecnico;
	}

	public String getCap() {
		return this.cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getCapEstero() {
		return this.capEstero;
	}

	public void setCapEstero(String capEstero) {
		this.capEstero = capEstero;
	}

	public String getCell() {
		return this.cell;
	}

	public void setCell(String cell) {
		this.cell = cell;
	}

	public String getCittaEstera() {
		return this.cittaEstera;
	}

	public void setCittaEstera(String cittaEstera) {
		this.cittaEstera = cittaEstera;
	}

	public String getCivicoEstero() {
		return this.civicoEstero;
	}

	public void setCivicoEstero(String civicoEstero) {
		this.civicoEstero = civicoEstero;
	}

	public String getCodiceFiscale() {
		return this.codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getCognome() {
		return this.cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getCompetenzeNazionali() {
		return this.competenzeNazionali;
	}

	public void setCompetenzeNazionali(String competenzeNazionali) {
		this.competenzeNazionali = competenzeNazionali;
	}

	public Date getDataBloccoCertificatore() {
		return this.dataBloccoCertificatore;
	}

	public void setDataBloccoCertificatore(Date dataBloccoCertificatore) {
		this.dataBloccoCertificatore = dataBloccoCertificatore;
	}

	public Date getDataIscrAlbo() {
		return this.dataIscrAlbo;
	}

	public void setDataIscrAlbo(Date dataIscrAlbo) {
		this.dataIscrAlbo = dataIscrAlbo;
	}

	public String getDescComuneNascita() {
		return this.descComuneNascita;
	}

	public void setDescComuneNascita(String descComuneNascita) {
		this.descComuneNascita = descComuneNascita;
	}

	public String getDescComuneResidenza() {
		return this.descComuneResidenza;
	}

	public void setDescComuneResidenza(String descComuneResidenza) {
		this.descComuneResidenza = descComuneResidenza;
	}

	public String getDescIndirizzo() {
		return this.descIndirizzo;
	}

	public void setDescIndirizzo(String descIndirizzo) {
		this.descIndirizzo = descIndirizzo;
	}

	public String getDescProvNascita() {
		return this.descProvNascita;
	}

	public void setDescProvNascita(String descProvNascita) {
		this.descProvNascita = descProvNascita;
	}

	public String getDescProvOrdine() {
		return this.descProvOrdine;
	}

	public void setDescProvOrdine(String descProvOrdine) {
		this.descProvOrdine = descProvOrdine;
	}

	public String getDescProvResidenza() {
		return this.descProvResidenza;
	}

	public void setDescProvResidenza(String descProvResidenza) {
		this.descProvResidenza = descProvResidenza;
	}

	public String getDescRegioneNascita() {
		return this.descRegioneNascita;
	}

	public void setDescRegioneNascita(String descRegioneNascita) {
		this.descRegioneNascita = descRegioneNascita;
	}

	public String getDescRegioneResidenza() {
		return this.descRegioneResidenza;
	}

	public void setDescRegioneResidenza(String descRegioneResidenza) {
		this.descRegioneResidenza = descRegioneResidenza;
	}

	public Date getDtAggDati() {
		return this.dtAggDati;
	}

	public void setDtAggDati(Date dtAggDati) {
		this.dtAggDati = dtAggDati;
	}

	public Date getDtAggEmail() {
		return this.dtAggEmail;
	}

	public void setDtAggEmail(Date dtAggEmail) {
		this.dtAggEmail = dtAggEmail;
	}

	public Date getDtEmailVerifica() {
		return this.dtEmailVerifica;
	}

	public void setDtEmailVerifica(Date dtEmailVerifica) {
		this.dtEmailVerifica = dtEmailVerifica;
	}

	public Date getDtIscrElenco() {
		return this.dtIscrElenco;
	}

	public void setDtIscrElenco(Date dtIscrElenco) {
		this.dtIscrElenco = dtIscrElenco;
	}

	public Date getDtNascita() {
		return this.dtNascita;
	}

	public void setDtNascita(Date dtNascita) {
		this.dtNascita = dtNascita;
	}

	public Date getDtScadenzaElenco() {
		return this.dtScadenzaElenco;
	}

	public void setDtScadenzaElenco(Date dtScadenzaElenco) {
		this.dtScadenzaElenco = dtScadenzaElenco;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailPec() {
		return this.emailPec;
	}

	public void setEmailPec(String emailPec) {
		this.emailPec = emailPec;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public BigDecimal getFkAzienda() {
		return this.fkAzienda;
	}

	public void setFkAzienda(BigDecimal fkAzienda) {
		this.fkAzienda = fkAzienda;
	}

	public BigDecimal getFkBloccoCertificatore() {
		return this.fkBloccoCertificatore;
	}

	public void setFkBloccoCertificatore(BigDecimal fkBloccoCertificatore) {
		this.fkBloccoCertificatore = fkBloccoCertificatore;
	}

	public BigDecimal getFkIstruzione() {
		return this.fkIstruzione;
	}

	public void setFkIstruzione(BigDecimal fkIstruzione) {
		this.fkIstruzione = fkIstruzione;
	}

	public String getFkTitolo() {
		return this.fkTitolo;
	}

	public void setFkTitolo(String fkTitolo) {
		this.fkTitolo = fkTitolo;
	}

	public String getFlgAggDati() {
		return this.flgAggDati;
	}

	public void setFlgAggDati(String flgAggDati) {
		this.flgAggDati = flgAggDati;
	}

	public String getFlgAggEmail() {
		return this.flgAggEmail;
	}

	public void setFlgAggEmail(String flgAggEmail) {
		this.flgAggEmail = flgAggEmail;
	}

	public String getFlgCancElenco() {
		return this.flgCancElenco;
	}

	public void setFlgCancElenco(String flgCancElenco) {
		this.flgCancElenco = flgCancElenco;
	}

	public String getFlgConsensoCell() {
		return this.flgConsensoCell;
	}

	public void setFlgConsensoCell(String flgConsensoCell) {
		this.flgConsensoCell = flgConsensoCell;
	}

	public String getFlgConsensoEmail() {
		return this.flgConsensoEmail;
	}

	public void setFlgConsensoEmail(String flgConsensoEmail) {
		this.flgConsensoEmail = flgConsensoEmail;
	}

	public String getFlgConsensoTel() {
		return this.flgConsensoTel;
	}

	public void setFlgConsensoTel(String flgConsensoTel) {
		this.flgConsensoTel = flgConsensoTel;
	}

	public String getFlgEdifici() {
		return this.flgEdifici;
	}

	public void setFlgEdifici(String flgEdifici) {
		this.flgEdifici = flgEdifici;
	}

	public String getFlgEsameSostenuto() {
		return this.flgEsameSostenuto;
	}

	public void setFlgEsameSostenuto(String flgEsameSostenuto) {
		this.flgEsameSostenuto = flgEsameSostenuto;
	}

	public String getFlgImpianti() {
		return this.flgImpianti;
	}

	public void setFlgImpianti(String flgImpianti) {
		this.flgImpianti = flgImpianti;
	}

	public String getFlgNewsletter() {
		return this.flgNewsletter;
	}

	public void setFlgNewsletter(String flgNewsletter) {
		this.flgNewsletter = flgNewsletter;
	}

	public String getFlgPagamento() {
		return this.flgPagamento;
	}

	public void setFlgPagamento(String flgPagamento) {
		this.flgPagamento = flgPagamento;
	}

	public String getFlgResidenzaItalia() {
		return this.flgResidenzaItalia;
	}

	public void setFlgResidenzaItalia(String flgResidenzaItalia) {
		this.flgResidenzaItalia = flgResidenzaItalia;
	}

	public String getFlgVerificato() {
		return this.flgVerificato;
	}

	public void setFlgVerificato(String flgVerificato) {
		this.flgVerificato = flgVerificato;
	}

	public String getIdComuneNascita() {
		return this.idComuneNascita;
	}

	public void setIdComuneNascita(String idComuneNascita) {
		this.idComuneNascita = idComuneNascita;
	}

	public String getIdComuneResidenza() {
		return this.idComuneResidenza;
	}

	public void setIdComuneResidenza(String idComuneResidenza) {
		this.idComuneResidenza = idComuneResidenza;
	}

	public BigDecimal getIdIndirizzo() {
		return this.idIndirizzo;
	}

	public void setIdIndirizzo(BigDecimal idIndirizzo) {
		this.idIndirizzo = idIndirizzo;
	}

	public String getIdProvNascita() {
		return this.idProvNascita;
	}

	public void setIdProvNascita(String idProvNascita) {
		this.idProvNascita = idProvNascita;
	}

	public String getIdProvOrdine() {
		return this.idProvOrdine;
	}

	public void setIdProvOrdine(String idProvOrdine) {
		this.idProvOrdine = idProvOrdine;
	}

	public String getIdProvResidenza() {
		return this.idProvResidenza;
	}

	public void setIdProvResidenza(String idProvResidenza) {
		this.idProvResidenza = idProvResidenza;
	}

	public String getIdRegioneNascita() {
		return this.idRegioneNascita;
	}

	public void setIdRegioneNascita(String idRegioneNascita) {
		this.idRegioneNascita = idRegioneNascita;
	}

	public String getIdRegioneResidenza() {
		return this.idRegioneResidenza;
	}

	public void setIdRegioneResidenza(String idRegioneResidenza) {
		this.idRegioneResidenza = idRegioneResidenza;
	}

	public String getIscrittoOrdine() {
		return this.iscrittoOrdine;
	}

	public void setIscrittoOrdine(String iscrittoOrdine) {
		this.iscrittoOrdine = iscrittoOrdine;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getNoteBloccoCertificatore() {
		return this.noteBloccoCertificatore;
	}

	public void setNoteBloccoCertificatore(String noteBloccoCertificatore) {
		this.noteBloccoCertificatore = noteBloccoCertificatore;
	}

	public String getNumCertificatore() {
		return this.numCertificatore;
	}

	public void setNumCertificatore(String numCertificatore) {
		this.numCertificatore = numCertificatore;
	}

	public String getNumCivicoResidenza() {
		return this.numCivicoResidenza;
	}

	public void setNumCivicoResidenza(String numCivicoResidenza) {
		this.numCivicoResidenza = numCivicoResidenza;
	}

	public String getNumIscrAlbo() {
		return this.numIscrAlbo;
	}

	public void setNumIscrAlbo(String numIscrAlbo) {
		this.numIscrAlbo = numIscrAlbo;
	}

	public BigDecimal getNumTotAce() {
		return this.numTotAce;
	}

	public void setNumTotAce(BigDecimal numTotAce) {
		this.numTotAce = numTotAce;
	}

	public String getOrdine() {
		return this.ordine;
	}

	public void setOrdine(String ordine) {
		this.ordine = ordine;
	}

	public String getSettore() {
		return this.settore;
	}

	public void setSettore(String settore) {
		this.settore = settore;
	}

	public String getSezione() {
		return this.sezione;
	}

	public void setSezione(String sezione) {
		this.sezione = sezione;
	}

	public String getSitoWeb() {
		return this.sitoWeb;
	}

	public void setSitoWeb(String sitoWeb) {
		this.sitoWeb = sitoWeb;
	}

	public String getStatoEstero() {
		return this.statoEstero;
	}

	public void setStatoEstero(String statoEstero) {
		this.statoEstero = statoEstero;
	}

	public String getStatoResEstero() {
		return this.statoResEstero;
	}

	public void setStatoResEstero(String statoResEstero) {
		this.statoResEstero = statoResEstero;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getViaEstera() {
		return this.viaEstera;
	}

	public void setViaEstera(String viaEstera) {
		this.viaEstera = viaEstera;
	}

	public Set<SiceeTCertificato> getSiceeTCertificatos() {
		return this.siceeTCertificatos;
	}

	public void setSiceeTCertificatos(Set<SiceeTCertificato> siceeTCertificatos) {
		this.siceeTCertificatos = siceeTCertificatos;
	}

	public SiceeTCertificato addSiceeTCertificato(SiceeTCertificato siceeTCertificato) {
		getSiceeTCertificatos().add(siceeTCertificato);
		siceeTCertificato.setSiceeTCertificatore(this);

		return siceeTCertificato;
	}

	public SiceeTCertificato removeSiceeTCertificato(SiceeTCertificato siceeTCertificato) {
		getSiceeTCertificatos().remove(siceeTCertificato);
		siceeTCertificato.setSiceeTCertificatore(null);

		return siceeTCertificato;
	}

}