/*******************************************************************************
 * SPDX-License-Identifier: EUPL-1.2
 * Copyright Regione Piemonte - 2020
 *******************************************************************************/
package it.csi.sicee.siceegwacta.integration.db;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the SICEE_D_DEST_USO_2015 database table.
 * 
 */
@Entity
@Table(name="SICEE_D_DEST_USO_2015")
public class SiceeDDestUso2015 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_DEST_USO_2015")
	private Integer idDestUso2015;

	private String descr;

	@Column(name="FLG_ATTIVO")
	private Integer flgAttivo;

	@Column(name="FLG_RESIDENZIALE")
	private String flgResidenziale;

	private String sigla;

	//bi-directional many-to-one association to SiceeTDatiGenerali
	@OneToMany(mappedBy="siceeDDestUso2015", fetch=FetchType.LAZY)
	private Set<SiceeTDatiGenerali> siceeTDatiGeneralis;

	public SiceeDDestUso2015() {
	}

	public Integer getIdDestUso2015() {
		return this.idDestUso2015;
	}

	public void setIdDestUso2015(Integer idDestUso2015) {
		this.idDestUso2015 = idDestUso2015;
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

	public String getFlgResidenziale() {
		return this.flgResidenziale;
	}

	public void setFlgResidenziale(String flgResidenziale) {
		this.flgResidenziale = flgResidenziale;
	}

	public String getSigla() {
		return this.sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public Set<SiceeTDatiGenerali> getSiceeTDatiGeneralis() {
		return this.siceeTDatiGeneralis;
	}

	public void setSiceeTDatiGeneralis(Set<SiceeTDatiGenerali> siceeTDatiGeneralis) {
		this.siceeTDatiGeneralis = siceeTDatiGeneralis;
	}

	public SiceeTDatiGenerali addSiceeTDatiGenerali(SiceeTDatiGenerali siceeTDatiGenerali) {
		getSiceeTDatiGeneralis().add(siceeTDatiGenerali);
		siceeTDatiGenerali.setSiceeDDestUso2015(this);

		return siceeTDatiGenerali;
	}

	public SiceeTDatiGenerali removeSiceeTDatiGenerali(SiceeTDatiGenerali siceeTDatiGenerali) {
		getSiceeTDatiGeneralis().remove(siceeTDatiGenerali);
		siceeTDatiGenerali.setSiceeDDestUso2015(null);

		return siceeTDatiGenerali;
	}

}