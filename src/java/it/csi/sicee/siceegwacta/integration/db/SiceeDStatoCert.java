/*******************************************************************************
 * SPDX-License-Identifier: EUPL-1.2
 * Copyright Regione Piemonte - 2020
 *******************************************************************************/
package it.csi.sicee.siceegwacta.integration.db;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the SICEE_D_STATO_CERT database table.
 * 
 */
@Entity
@Table(name="SICEE_D_STATO_CERT")
public class SiceeDStatoCert implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_STATO")
	private Integer idStato;

	private String descr;

	@Column(name="FLG_RICERCA")
	private String flgRicerca;

	//bi-directional many-to-one association to SiceeTCertificato
	@OneToMany(mappedBy="siceeDStatoCert", fetch=FetchType.LAZY)
	private Set<SiceeTCertificato> siceeTCertificatos;

	public SiceeDStatoCert() {
	}

	public Integer getIdStato() {
		return this.idStato;
	}

	public void setIdStato(Integer idStato) {
		this.idStato = idStato;
	}

	public String getDescr() {
		return this.descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getFlgRicerca() {
		return this.flgRicerca;
	}

	public void setFlgRicerca(String flgRicerca) {
		this.flgRicerca = flgRicerca;
	}

	public Set<SiceeTCertificato> getSiceeTCertificatos() {
		return this.siceeTCertificatos;
	}

	public void setSiceeTCertificatos(Set<SiceeTCertificato> siceeTCertificatos) {
		this.siceeTCertificatos = siceeTCertificatos;
	}

	public SiceeTCertificato addSiceeTCertificato(SiceeTCertificato siceeTCertificato) {
		getSiceeTCertificatos().add(siceeTCertificato);
		siceeTCertificato.setSiceeDStatoCert(this);

		return siceeTCertificato;
	}

	public SiceeTCertificato removeSiceeTCertificato(SiceeTCertificato siceeTCertificato) {
		getSiceeTCertificatos().remove(siceeTCertificato);
		siceeTCertificato.setSiceeDStatoCert(null);

		return siceeTCertificato;
	}

}