/*******************************************************************************
 * SPDX-License-Identifier: EUPL-1.2
 * Copyright Regione Piemonte - 2020
 *******************************************************************************/
package it.csi.sicee.siceegwacta.integration.db;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SICEE_T_ACTA_LOG database table.
 * 
 */
@Entity
@Table(name="SICEE_T_ACTA_LOG")
public class SiceeTActaLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SiceeTActaLogPK id;

	@Column(name="DESC_LOG")
	private String descLog;

	public SiceeTActaLog() {
	}

	public SiceeTActaLogPK getId() {
		return this.id;
	}

	public void setId(SiceeTActaLogPK id) {
		this.id = id;
	}

	public String getDescLog() {
		return this.descLog;
	}

	public void setDescLog(String descLog) {
		this.descLog = descLog;
	}

}