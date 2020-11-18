/*******************************************************************************
 * SPDX-License-Identifier: EUPL-1.2
 * Copyright Regione Piemonte - 2020
 *******************************************************************************/
package it.csi.sicee.siceegwacta.business.mgr;

import java.sql.Timestamp;
import java.util.List;

import it.csi.sicee.siceegwacta.exception.ACTAInvocationException;
import it.csi.sicee.siceegwacta.integration.db.*;

public interface ISiceegwactaTraceManager {


	List<SiceeTParametriActa> findAllSiceeTParametriActa();
	
	String findSiceeTParametriActaByCodice(String codice) ;
	
	void updateSiceeTParametriActaValueByCodice(String codice, String value);
	
	int updateParamDataElaborazione(String dataProc);
	
	void updateSiceeTParametriActa(SiceeTParametriActa siceeTParametriActa);
	
	void insertSiceeTActaLog(SiceeTActaLog actaLog);
	
	List<SiceeTCertificato> findCertificatoByChiave();
	
	List<SiceeTCertificato> findCertificatiDaGestire(int maxRow);
	
	public void updateSiceeTActa(SiceeTActa acta);
}
