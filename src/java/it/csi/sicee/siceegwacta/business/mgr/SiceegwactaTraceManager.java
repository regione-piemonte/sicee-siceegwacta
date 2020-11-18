/*******************************************************************************
 * SPDX-License-Identifier: EUPL-1.2
 * Copyright Regione Piemonte - 2020
 *******************************************************************************/
package it.csi.sicee.siceegwacta.business.mgr;


import it.csi.sicee.siceegwacta.exception.ACTAInvocationException;
import it.csi.sicee.siceegwacta.integration.db.*;
import it.csi.sicee.siceegwacta.util.ACTAConstants;
import it.csi.sicee.siceegwacta.util.GenericUtil;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;

import org.apache.log4j.Logger;

@WebService(name = "SiceegwactaTraceManagerWS", targetNamespace = "http://it/csi/sicee/siceegwacta/business/mgr", serviceName = "ISiceegwactaTraceManager")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@Remote(ISiceegwactaTraceManager.class)
@Stateless(name = "SiceegwactaTraceManagerSL")
public class SiceegwactaTraceManager implements ISiceegwactaTraceManager {
	
	@PersistenceContext(unitName = "SICEEGWACTA")
    private EntityManager entityManager;
	
	private static volatile SiceegwactaTraceManager instance; 
	
	private Logger logger = Logger.getLogger(ACTAConstants.LOGGER_PREFIX);
	
	
	public static SiceegwactaTraceManager getInstance() {
		// Evito instanziazione multipla
		if(instance==null) {
			synchronized(SiceegwactaTraceManager.class) {
				if(instance == null) {
					// FIXME: essendo un EJB dovrei farne il lookup corretto. Questa versione non inizializza aluni dati (datasource / entityManager)
					instance = new SiceegwactaTraceManager();
				}
			}
		}	
		return instance;
	}


	@Override
	public String findSiceeTParametriActaByCodice(String codice) {
		// TODO Auto-generated method stub
		String q = "SELECT p from " + SiceeTParametriActa.class.getName() + " p where codice=:codice";
        TypedQuery<SiceeTParametriActa> query = entityManager.createQuery(q, SiceeTParametriActa.class);
        query.setParameter("codice", codice);
        List<SiceeTParametriActa> result=query.getResultList();
        if(result.size()>0) return result.get(0).getValore();
        return null;
        
	}
	

	@Override
	public List<SiceeTParametriActa> findAllSiceeTParametriActa() {
		// TODO Auto-generated method stub
		String q = "SELECT p from " + SiceeTParametriActa.class.getName() + " p";
        TypedQuery<SiceeTParametriActa> query = entityManager.createQuery(q, SiceeTParametriActa.class);
        return query.getResultList();
	}

	
	@Override
	public List<SiceeTCertificato> findCertificatoByChiave() {
		// TODO Auto-generated method stub
		String q = "SELECT p from " + SiceeTCertificato.class.getName() + " p where id_Certificatore = 386 ";
        TypedQuery<SiceeTCertificato> query = entityManager.createQuery(q, SiceeTCertificato.class);
        
        
        List<SiceeTCertificato> list = query.getResultList();
        
        for (SiceeTCertificato siceeTCertificato : list) {
			logger.debug("Stampo il certificato");
			logger.debug("Stampo ind: "+siceeTCertificato.getDescIndirizzo());
			logger.debug("Stampo stato: "+siceeTCertificato.getSiceeDStatoCert());
			logger.debug("Stampo stato id: "+siceeTCertificato.getSiceeDStatoCert().getIdStato());
			logger.debug("Stampo stato desc: "+siceeTCertificato.getSiceeDStatoCert().getDescr());
		}
        
        return list;
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void updateSiceeTParametriActaValueByCodice(String codice, String value) {
		
		// FUNZIONA
		//entityManager.createQuery("UPDATE " + SiceeTParametriActa.class.getName() + " SET VALORE = 'PROVA' WHERE ID_PARAMETRI_ACTA = 15 ").executeUpdate();
		
		Query query = entityManager.createQuery("UPDATE " + SiceeTParametriActa.class.getName() + " SET VALORE = :valore WHERE CODICE = :codice ");
		query.setParameter("codice", codice);
		query.setParameter("valore", value);
		query.executeUpdate();
		
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public int updateParamDataElaborazione(String dataProc)
	{
		// FUNZIONA

		StringBuffer sbQuery = new StringBuffer();
		sbQuery.append("UPDATE " + SiceeTParametriActa.class.getName()); 
		sbQuery.append(" SET VALORE = '"+dataProc+"'");
		sbQuery.append(" WHERE CODICE = 'DATA_ELABORAZIONE' ");
		sbQuery.append(" AND (VALORE is null "); 
		sbQuery.append(" OR (24 * (to_date('"+dataProc+"', 'DD/MM/YYYY hh24:mi:ss') - to_date(VALORE, 'DD/MM/YYYY hh24:mi:ss'))) > 5) ");

		Query query = entityManager.createQuery(sbQuery.toString());

		
		return query.executeUpdate();
	}
	
	
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void updateSiceeTParametriActa(SiceeTParametriActa siceeTParametriActa)
	{
		// FUNZIONA
//		Query query = entityManager.createQuery("UPDATE " + SiceeTParametriActa.class.getName() + " SET VALORE = :valore WHERE CODICE = :codice ");
//		query.setParameter("codice", parametri.getCodice());
//		query.setParameter("valore", parametri.getValore());
//		query.executeUpdate();
		
		// FUNZIONA
		entityManager.merge(siceeTParametriActa);
		
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void updateSiceeTActa(SiceeTActa acta) {
		
		// FUNZIONA
		entityManager.merge(acta);
		
	}

	@Override
	public List<SiceeTCertificato> findCertificatiDaGestire(int maxRow) {
		// TODO Auto-generated method stub
		
		// FUNZIONA
//		StringBuffer qB = new StringBuffer();
//		qB.append("SELECT c FROM ");
//		qB.append(SiceeTCertificato.class.getName() + " c ");
//		qB.append(" WHERE SICEE_T_CERTIFICATO.ID_CERTIFICATORE = 1441 ");

		
		
		
		
		// FUUNZIONA
		StringBuffer qB = new StringBuffer();
		qB.append("SELECT c FROM ");
		qB.append(SiceeTCertificato.class.getName() + " c, "+ " "+SiceeTActa.class.getName()+ " a , "+SiceeTRifIndex2015.class.getName() + " i ");
		qB.append("WHERE a = c.id");
		qB.append(" AND i.siceeTCertificato = c.id");
		//qB.append(" AND c.id.idCertificatore = 1441");
		qB.append(" AND i.fkTipoDoc = 2 ");
		qB.append(" AND (a.idDocActa IS NULL OR a.idProtocolloActa IS NULL) ");
		qB.append(" ORDER BY c.dtUpload ASC ");

		/*
		 Query:
		 SELECT
			    * 
			FROM
			    Sicee_T_Certificato c,
			    Sicee_T_Acta a ,
			    Sicee_T_Rif_Index_2015 i 
			WHERE
			    c.ID_CERTIFICATORE = a.Id_Certificatore
			    and c.PROGR_CERTIFICATO = a.PROGR_CERTIFICATO
			    and c.anno = a.anno
			    and c.ID_CERTIFICATORE = i.Id_Certificatore
			    and c.PROGR_CERTIFICATO = i.PROGR_CERTIFICATO
			    and c.anno = i.anno
			    AND i.fk_tipo_doc = 2  
			    AND (
			        a.id_doc_acta IS NULL 
			        OR a.id_protocollo_acta IS NULL
			    ) 
			    order by c.dt_upload asc
		 */
		
//		StringBuffer qB = new StringBuffer();
//		qB.append("SELECT c FROM ");
//		qB.append(SiceeTCertificato.class.getName() + " c, "+SiceeTActa.class.getName()+ " a, "+SiceeTRifIndex2015.class.getName() + " i ");
//		qB.append(" WHERE c.id.idCertificatore = a.id.idCertificatore ");
//		qB.append(" AND c.id.progrCertificato = a.id.progrCertificato ");
//		qB.append(" AND c.id.anno = a.id.anno ");
//		qB.append(" AND c.id.idCertificatore = i.siceeTCertificato.id.idCertificatore ");
//		qB.append(" AND c.id.progrCertificato = i.siceeTCertificato.id.progrCertificato ");
//		qB.append(" AND c.id.anno = i.siceeTCertificato.id.anno ");
//		qB.append(" AND i.fkTipoDoc = 2 ");
//		qB.append(" AND (a.idDocActa IS NULL OR a.idProtocolloActa IS NULL) ");
//		qB.append(" AND c.id.idCertificatore = 1441");
		
		//TypedQuery<SiceeTCertificato> query = entityManager.createQuery(qB.toString(), SiceeTCertificato.class);
		Query query = entityManager.createQuery(qB.toString(), SiceeTCertificato.class);
		
		if (maxRow > 0)
		{
			query = query.setMaxResults(maxRow);
		}
		
		List<SiceeTCertificato> list =query.getResultList();

		logger.debug("Stampo il risultato: "+list);
		logger.debug("Stampo il risultato.size(): "+list.size());

        for (SiceeTCertificato siceeTCertificato : list) {
        	siceeTCertificato.getSiceeTActas();
        	
        	// Forzo il recupero degli altri dati - NON E' DA CANCELLARE LE STAMPE SOTTO
        	logger.debug("Stampo siceeTCertificato.getSiceeTCertificatore(): "+siceeTCertificato.getSiceeTCertificatore());
        	
        	logger.debug("Stampo siceeTCertificato.getSiceeTRifIndex2015s(): "+siceeTCertificato.getSiceeTRifIndex2015s());
        	logger.debug("Stampo siceeTCertificato.getSiceeTDatiGeneralis(): "+siceeTCertificato.getSiceeTDatiGeneralis());
        	logger.debug("Stampo siceeTCertificato.getSiceeTDatiEner2015s(): "+siceeTCertificato.getSiceeTDatiEner2015s());
        	logger.debug("Stampo siceeTCertificato.getSiceeTActas(): "+siceeTCertificato.getSiceeTActas());

			
        }
        
        return list;
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void insertSiceeTActaLog(SiceeTActaLog actaLog)
	{
		entityManager.persist(actaLog);
		
	}
	
	
}
