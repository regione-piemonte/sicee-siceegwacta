/*******************************************************************************
 * SPDX-License-Identifier: EUPL-1.2
 * Copyright Regione Piemonte - 2020
 *******************************************************************************/
package it.csi.sicee.siceegwacta.integration.db;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the SICEE_D_CLASSE_ENERGETICA database table.
 * 
 */
@Entity
@Table(name="SICEE_D_CLASSE_ENERGETICA")
public class SiceeDClasseEnergetica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_CLASSE")
	private Integer idClasse;

	private String descr;

	@Column(name="FLG_ATTIVO")
	private Integer flgAttivo;

	@Column(name="FLG_NAZIONALE_19205")
	private String flgNazionale19205;

	//bi-directional many-to-one association to SiceeTDatiEner2015
	@OneToMany(mappedBy="siceeDClasseEnergetica1", fetch=FetchType.LAZY)
	private Set<SiceeTDatiEner2015> siceeTDatiEner2015s1;

	//bi-directional many-to-one association to SiceeTDatiEner2015
	@OneToMany(mappedBy="siceeDClasseEnergetica2", fetch=FetchType.LAZY)
	private Set<SiceeTDatiEner2015> siceeTDatiEner2015s2;

	//bi-directional many-to-one association to SiceeTDatiEner2015
	@OneToMany(mappedBy="siceeDClasseEnergetica3", fetch=FetchType.LAZY)
	private Set<SiceeTDatiEner2015> siceeTDatiEner2015s3;

	//bi-directional many-to-one association to SiceeTDatiEner2015
	@OneToMany(mappedBy="siceeDClasseEnergetica4", fetch=FetchType.LAZY)
	private Set<SiceeTDatiEner2015> siceeTDatiEner2015s4;

	public SiceeDClasseEnergetica() {
	}

	public Integer getIdClasse() {
		return this.idClasse;
	}

	public void setIdClasse(Integer idClasse) {
		this.idClasse = idClasse;
	}

	public String getDescr() {
		return this.descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public Integer getFlgAttivo() {
		return this.flgAttivo;
	}

	public void setFlgAttivo(Integer flgAttivo) {
		this.flgAttivo = flgAttivo;
	}

	public String getFlgNazionale19205() {
		return this.flgNazionale19205;
	}

	public void setFlgNazionale19205(String flgNazionale19205) {
		this.flgNazionale19205 = flgNazionale19205;
	}

	public Set<SiceeTDatiEner2015> getSiceeTDatiEner2015s1() {
		return this.siceeTDatiEner2015s1;
	}

	public void setSiceeTDatiEner2015s1(Set<SiceeTDatiEner2015> siceeTDatiEner2015s1) {
		this.siceeTDatiEner2015s1 = siceeTDatiEner2015s1;
	}

	public SiceeTDatiEner2015 addSiceeTDatiEner2015s1(SiceeTDatiEner2015 siceeTDatiEner2015s1) {
		getSiceeTDatiEner2015s1().add(siceeTDatiEner2015s1);
		siceeTDatiEner2015s1.setSiceeDClasseEnergetica1(this);

		return siceeTDatiEner2015s1;
	}

	public SiceeTDatiEner2015 removeSiceeTDatiEner2015s1(SiceeTDatiEner2015 siceeTDatiEner2015s1) {
		getSiceeTDatiEner2015s1().remove(siceeTDatiEner2015s1);
		siceeTDatiEner2015s1.setSiceeDClasseEnergetica1(null);

		return siceeTDatiEner2015s1;
	}

	public Set<SiceeTDatiEner2015> getSiceeTDatiEner2015s2() {
		return this.siceeTDatiEner2015s2;
	}

	public void setSiceeTDatiEner2015s2(Set<SiceeTDatiEner2015> siceeTDatiEner2015s2) {
		this.siceeTDatiEner2015s2 = siceeTDatiEner2015s2;
	}

	public SiceeTDatiEner2015 addSiceeTDatiEner2015s2(SiceeTDatiEner2015 siceeTDatiEner2015s2) {
		getSiceeTDatiEner2015s2().add(siceeTDatiEner2015s2);
		siceeTDatiEner2015s2.setSiceeDClasseEnergetica2(this);

		return siceeTDatiEner2015s2;
	}

	public SiceeTDatiEner2015 removeSiceeTDatiEner2015s2(SiceeTDatiEner2015 siceeTDatiEner2015s2) {
		getSiceeTDatiEner2015s2().remove(siceeTDatiEner2015s2);
		siceeTDatiEner2015s2.setSiceeDClasseEnergetica2(null);

		return siceeTDatiEner2015s2;
	}

	public Set<SiceeTDatiEner2015> getSiceeTDatiEner2015s3() {
		return this.siceeTDatiEner2015s3;
	}

	public void setSiceeTDatiEner2015s3(Set<SiceeTDatiEner2015> siceeTDatiEner2015s3) {
		this.siceeTDatiEner2015s3 = siceeTDatiEner2015s3;
	}

	public SiceeTDatiEner2015 addSiceeTDatiEner2015s3(SiceeTDatiEner2015 siceeTDatiEner2015s3) {
		getSiceeTDatiEner2015s3().add(siceeTDatiEner2015s3);
		siceeTDatiEner2015s3.setSiceeDClasseEnergetica3(this);

		return siceeTDatiEner2015s3;
	}

	public SiceeTDatiEner2015 removeSiceeTDatiEner2015s3(SiceeTDatiEner2015 siceeTDatiEner2015s3) {
		getSiceeTDatiEner2015s3().remove(siceeTDatiEner2015s3);
		siceeTDatiEner2015s3.setSiceeDClasseEnergetica3(null);

		return siceeTDatiEner2015s3;
	}

	public Set<SiceeTDatiEner2015> getSiceeTDatiEner2015s4() {
		return this.siceeTDatiEner2015s4;
	}

	public void setSiceeTDatiEner2015s4(Set<SiceeTDatiEner2015> siceeTDatiEner2015s4) {
		this.siceeTDatiEner2015s4 = siceeTDatiEner2015s4;
	}

	public SiceeTDatiEner2015 addSiceeTDatiEner2015s4(SiceeTDatiEner2015 siceeTDatiEner2015s4) {
		getSiceeTDatiEner2015s4().add(siceeTDatiEner2015s4);
		siceeTDatiEner2015s4.setSiceeDClasseEnergetica4(this);

		return siceeTDatiEner2015s4;
	}

	public SiceeTDatiEner2015 removeSiceeTDatiEner2015s4(SiceeTDatiEner2015 siceeTDatiEner2015s4) {
		getSiceeTDatiEner2015s4().remove(siceeTDatiEner2015s4);
		siceeTDatiEner2015s4.setSiceeDClasseEnergetica4(null);

		return siceeTDatiEner2015s4;
	}

}