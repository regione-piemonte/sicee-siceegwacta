/*******************************************************************************
 * SPDX-License-Identifier: EUPL-1.2
 * Copyright Regione Piemonte - 2020
 *******************************************************************************/
package it.csi.sicee.siceegwacta.util;

import java.sql.Timestamp;
import java.util.Set;

import it.csi.sicee.siceegwacta.business.dto.Certificato;
import it.csi.sicee.siceegwacta.exception.ACTAInvocationException;
import it.csi.sicee.siceegwacta.integration.db.SiceeTActa;
import it.csi.sicee.siceegwacta.integration.db.SiceeTActaLog;
import it.csi.sicee.siceegwacta.integration.db.SiceeTActaLogPK;
import it.csi.sicee.siceegwacta.integration.db.SiceeTActaPK;
import it.csi.sicee.siceegwacta.integration.db.SiceeTCertificato;
import it.csi.sicee.siceegwacta.integration.db.SiceeTDatiEner2015;
import it.csi.sicee.siceegwacta.integration.db.SiceeTDatiGenerali;
import it.csi.sicee.siceegwacta.integration.db.SiceeTRifIndex2015;

import org.apache.log4j.Logger;

public class MapDto {
	  private static Logger logger = Logger.getLogger(ACTAConstants.LOGGER_PREFIX);

	  
	  public static Certificato mapToCertificato(SiceeTCertificato certificatoDb)
	  {
		  Certificato certificato = new Certificato();
		  
		  certificato.setIdCertificatore(certificatoDb.getId().getIdCertificatore());
		  certificato.setProgrCertificato(certificatoDb.getId().getProgrCertificato());
		  certificato.setAnno(certificatoDb.getId().getAnno());
		  
		  certificato.setNumCertificatore(certificatoDb.getSiceeTCertificatore().getNumCertificatore());
		  certificato.setCognomeCertificatore(certificatoDb.getSiceeTCertificatore().getCognome());
		  certificato.setNomeCertificatore(certificatoDb.getSiceeTCertificatore().getNome());
		  certificato.setCfCertificatore(certificatoDb.getSiceeTCertificatore().getCodiceFiscale());

		  certificato.setEmail(certificatoDb.getSiceeTCertificatore().getEmail());

		  certificato.setDtConsolidamento(certificatoDb.getDtInizio());
		  certificato.setDtScadenza(certificatoDb.getDtScadenza());
		  certificato.setDtUpload(certificatoDb.getDtUpload());
		  certificato.setIdComune(certificatoDb.getIdComune());
		  
		  Set<SiceeTRifIndex2015> rifIndex2015List = certificatoDb.getSiceeTRifIndex2015s();

		  if (rifIndex2015List != null && rifIndex2015List.size() > 0)
		  {
			  for (SiceeTRifIndex2015 siceeTRifIndex2015 : rifIndex2015List) {

				  if (siceeTRifIndex2015.getFkTipoDoc().intValue() == ACTAConstants.ID_TIPO_DOC_APE_FIRMATO)
				  {
					  certificato.setNomeFile(siceeTRifIndex2015.getNomeFile());
					  certificato.setUidIndex(siceeTRifIndex2015.getUidIndex());
				  }
			  }



		  }

		  Set<SiceeTDatiGenerali> datiGeneraliList = certificatoDb.getSiceeTDatiGeneralis();

		  SiceeTDatiGenerali datiGenerali = null;

		  if (datiGeneraliList != null && datiGeneraliList.size() > 0)
		  {
			  datiGenerali = (SiceeTDatiGenerali) datiGeneraliList.toArray()[0];

			  if (datiGenerali != null)
			  {
				  certificato.setAnnoCostruzione(datiGenerali.getAnnoCostruzione());

				  certificato.setDestUso(datiGenerali.getSiceeDDestUso2015().getSigla());
			  }


		  }

		  Set<SiceeTDatiEner2015> datiEner2015List = certificatoDb.getSiceeTDatiEner2015s();

		  SiceeTDatiEner2015 datiEner2015 = null;

		  if (datiEner2015List != null && datiEner2015List.size() > 0)
		  {
			  datiEner2015 = (SiceeTDatiEner2015) datiEner2015List.toArray()[0];
			  if (datiEner2015 != null)
			  {
				  certificato.setClasse(datiEner2015.getSiceeDClasseEnergetica1().getDescr());
			  }
		  }


		  Set<SiceeTActa> actaList = certificatoDb.getSiceeTActas();

		  SiceeTActa acta = null;

		  if (actaList != null && actaList.size() > 0)
		  {
			  acta = (SiceeTActa) actaList.toArray()[0];

			  if (acta != null)
			  {
				  certificato.setIdDocActa(acta.getIdDocActa());
				  certificato.setIdClassificazioneActa(acta.getIdClassificazioneActa());
				  certificato.setIdProtocolloActa(acta.getIdProtocolloActa());

				  certificato.setVolume(acta.getVolume());
				  certificato.setTipoDocumentoActa(acta.getTipoDocumentoActa());
				  certificato.setFailedStepActa(acta.getFailedStepActa());
				  certificato.setTimestampArchiviazione(acta.getTimestampArchiviazione());
				  certificato.setNumeroProtocollo(acta.getNumeroProtocollo());
				  certificato.setMailInviata(acta.getMailInviata());
				  certificato.setTimestampProtocollo(acta.getTimestampProtocollo());
			  }

		  }

		  return certificato;
	  }
	  
	  public static SiceeTActa mapToSiceeTActa(Certificato certificato)
	  {
		  SiceeTActa acta = new SiceeTActa();

		  SiceeTActaPK actaPk = new SiceeTActaPK();
		  actaPk.setIdCertificatore(certificato.getIdCertificatore());
		  actaPk.setProgrCertificato(certificato.getProgrCertificato());
		  actaPk.setAnno(certificato.getAnno());
		  
		  acta.setId(actaPk);
		  acta.setIdDocActa(certificato.getIdDocActa());
		  acta.setIdClassificazioneActa(certificato.getIdClassificazioneActa());
		  acta.setIdProtocolloActa(certificato.getIdProtocolloActa());
		  
//		  acta.setIdDocActa(certificato.getIdDocActa());
//		  acta.setIdDocActa(certificato.getIdDocActa());
//		  acta.setIdClassificazioneActa(certificato.getIdClassificazioneActa());
//		  acta.setIdProtocolloActa(certificato.getIdProtocolloActa());
		  
		  acta.setVolume(certificato.getVolume());
		  acta.setTipoDocumentoActa(certificato.getTipoDocumentoActa());
		  acta.setFailedStepActa(certificato.getFailedStepActa());
		  acta.setTimestampArchiviazione(certificato.getTimestampArchiviazione());
		  acta.setNumeroProtocollo(certificato.getNumeroProtocollo());
		  acta.setMailInviata(certificato.getMailInviata());
		  acta.setTimestampProtocollo(certificato.getTimestampProtocollo());
		  
		  return acta;
	  }
	  
	  public static SiceeTActaLog mapToSiceeTActaLog(Certificato certificato, Timestamp inizioProcesso, ACTAInvocationException ex)
	  {
		  
		  SiceeTActaLogPK actaLogPk = new SiceeTActaLogPK();
		  
		  actaLogPk.setIdCertificatore(certificato.getIdCertificatore());
		  actaLogPk.setProgrCertificato(certificato.getProgrCertificato());
		  actaLogPk.setAnno(certificato.getAnno());
		  actaLogPk.setTimestampLog(inizioProcesso);
		  
		  SiceeTActaLog actaLog = new SiceeTActaLog();
		  actaLog.setId(actaLogPk);
		  actaLog.setDescLog("Errore: "+ex.getMessage());

		  return actaLog;
	  }
}
