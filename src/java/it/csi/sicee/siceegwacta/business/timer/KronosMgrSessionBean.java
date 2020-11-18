/*******************************************************************************
 * SPDX-License-Identifier: EUPL-1.2
 * Copyright Regione Piemonte - 2020
 *******************************************************************************/
package it.csi.sicee.siceegwacta.business.timer;

import it.csi.sicee.siceegwacta.business.dto.Certificato;
import it.csi.sicee.siceegwacta.business.dto.Mail;
import it.csi.sicee.siceegwacta.business.mgr.ISiceegwactaTraceManager;
import it.csi.sicee.siceegwacta.business.operazioni.ACTAService;
import it.csi.sicee.siceegwacta.exception.ACTAInvocationException;
import it.csi.sicee.siceegwacta.integration.db.SiceeTActa;
import it.csi.sicee.siceegwacta.integration.db.SiceeTActaLog;
import it.csi.sicee.siceegwacta.integration.db.SiceeTActaLogPK;
import it.csi.sicee.siceegwacta.integration.db.SiceeTActaPK;
import it.csi.sicee.siceegwacta.integration.db.SiceeTCertificato;
import it.csi.sicee.siceegwacta.integration.db.SiceeTParametriActa;
import it.csi.sicee.siceegwacta.integration.db.SiceeTRifIndex2015;
import it.csi.sicee.siceegwacta.util.ACTAConstants;
import it.csi.sicee.siceegwacta.util.GenericUtil;
import it.csi.sicee.siceegwacta.util.MapDto;
import it.doqui.acta.actasrv.dto.acaris.type.common.ObjectIdType;
import it.doqui.acta.actasrv.dto.acaris.type.common.PagingResponseType;
import it.doqui.acta.actasrv.dto.acaris.type.common.PrincipalIdType;
import it.doqui.acta.actasrv.dto.acaris.type.document.EnumTipoDocumentoArchivistico;
import it.doqui.acta.actasrv.dto.acaris.type.document.IdentificatoreDocumento;
import it.doqui.acta.actasrv.dto.acaris.type.officialbook.IdentificazioneRegistrazione;

import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.cxf.continuations.SuspendedInvocationException;
import org.apache.cxf.transport.http.auth.DefaultBasicAuthSupplier;
import org.apache.log4j.Logger;

/**
 * Bean per le operazioni schdulate.
 */
@Singleton
public class KronosMgrSessionBean {

  private static Logger logger = Logger.getLogger(ACTAConstants.LOGGER_PREFIX);
  
  @EJB
  private ISiceegwactaTraceManager traceMgr;

  
	//final static String REP_NAME = "RP201209 Regione Piemonte - Agg. 09/2012";

//  @EJB
//  private UtilMgr utilMgr;
//
//  @EJB
//  private IOrchanprTraceManager traceMgr;

  //@Resource(lookup = "java:jboss/mail/Default")
  //private Session mailSession;
  
  private static final String RECUPERO_EVENTI_NAO="RecuperoEventiNao";
  
  private static final String RECUPERO_EVENTI_NAO_MAIL_FROM = "RecuperoEventiNaoMailFrom";

  private static final String RECUPERO_EVENTI_NAO_MAIL_TO = "RecuperoEventiNaoMailTo";

  private static final String RECUPERO_EVENTI_NAO_RANGE = "RecuperoEventiNaoRange";

  private static final String RECUPERO_EVENTI_NAO_MAIL_HOUR = "RecuperoEventiNaoMailHour";

  private static final String RECUPERO_EVENTI_NAO_LAST_EXECUTION = "RecuperoEventiNaoLastExec";

  
  public static void main(String[] args) {
	  logger.debug("[KronosMgrSessionBean::main] START");

	  try
	  {
		  
		  
		  
		  System.out.println("passo 1");
		  ACTAService actaSrv = new ACTAService();
		  System.out.println("passo 2");
		  //actaSrv.recuperaRegioniTESTMain();

		  String dataProtocollo = "2017-07-18T15:34:23.000+02:00";
		  
		  Date dataProt = GenericUtil.convertToDateCompleta(dataProtocollo);
		  System.out.println("dataProt: "+dataProt);
		  
		  
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		  Date dataProt2 = sdf.parse(dataProtocollo);
		  System.out.println("dataProt2: "+dataProt2);

		  System.out.println("passo 3");
		  
		  
		  
		  
		 // it.csi.coopdiag.api.CalledResource
		  
		  //DBServiceTmp.findCertificatoByChiave
		  
		  /*
		  SiceeTParametriActa[] paramActa = DBServiceTmp.findAllSiceeTParametriActa();

		  ACTAService.getRepository(REP_NAME);


		  ObjectIdType repositoryId = new ObjectIdType();
		  repositoryId.setValue(GenericUtil.recuperaValParametro(paramActa, ACTAConstants.REP_ID));

		  PrincipalIdType principalId = ACTAService.getPrincipalIdAcaris(paramActa);

		  ObjectIdType volumeId  = new ObjectIdType();
		  volumeId.setValue(GenericUtil.recuperaValParametro(paramActa, ACTAConstants.ID_VOLUME_CORRENTE));

		  ArrayList<Certificato> certList = DBServiceTmp.getCertificatoDB();

		  Certificato cert = certList.get(0);
		  cert.setProgrCertificato(cert.getProgrCertificato() + " - 4"); // Se devo manipolare il progressivo per fare delle prove
		  */
	  }
	  catch (Exception e)
	  {
		  e.printStackTrace();
		  logger.error(e);
	  }
	  logger.debug("[KronosMgrSessionBean::main] END");

  }

  private SiceeTParametriActa aggiornaParametroOK(List<SiceeTParametriActa> paramActa, String codice, String valoreNew)
  {

	  for (SiceeTParametriActa siceeTParametriActa : paramActa) {
		  if (siceeTParametriActa.getCodice().equals(codice))
		  {
			  siceeTParametriActa.setValore(valoreNew);

			  return siceeTParametriActa;
		  }
	  }

	  return null;
  }

  private void aggiornaParametro(List<SiceeTParametriActa> paramActa, String codice, String valoreNew)
  {

	  for (SiceeTParametriActa siceeTParametriActa : paramActa) {
		  if (siceeTParametriActa.getCodice().equals(codice))
		  {
			  siceeTParametriActa.setValore(valoreNew);

			  traceMgr.updateSiceeTParametriActa(siceeTParametriActa);
		  }
	  }
  }
  
  //@Schedule(minute = "*/1", hour = "*", persistent = false)
  //@Schedule(hour="*/1", persistent=false)
  public void gestisciProtocollazioneACTA_TST() {
	  
	  //ACTAService actaSrv = new ACTAService();
	  logger.info("[KronosMgrSessionBean::gestisciProtocollazioneACTA_TST] START");

	  try
	  {
		  ACTAService actaSrv = new ACTAService();

		  Timestamp inizioProcesso = GenericUtil.getInizioProcesso();

		  boolean isProcessoAttivo = false;
		  boolean isGestibile = false;
		  
		  
		  // Recupero tutti i parametri della configurazione
		  List<SiceeTParametriActa> paramActa = traceMgr.findAllSiceeTParametriActa();

		  logger.info("STAMPO DATA_ELABORAZIONE - prima: "+GenericUtil.recuperaValParametro(paramActa, ACTAConstants.DATA_ELABORAZIONE));
		  
		  SiceeTParametriActa actaDataElab = GenericUtil.recuperaTParametriActa(paramActa, ACTAConstants.DATA_ELABORAZIONE);
		  
		  //isGestibile = isGestibile(actaDataElab, inizioProcesso);
		  
		  
		  
		  int gest = traceMgr.updateParamDataElaborazione(GenericUtil.convertToString(inizioProcesso));
		  
		  logger.info("STAMPO IL RISULTATO DELL?UPDATE: "+gest);
		  
		  
		  if (gest != 0)
		  {
			  logger.info("E' GESTIBILE");
			  
			  aggiornaParametro(paramActa, ACTAConstants.DATA_ELABORAZIONE, GenericUtil.convertToString(inizioProcesso));

			  //TimeUnit.SECONDS.sleep(10);

			  //traceMgr.updateSiceeTParametriActaNew2(aggiornaParametroOK(paramActa, ACTAConstants.DATA_ELABORAZIONE, GenericUtil.convertToString(inizioProcesso)));
			  //traceMgr.updateSiceeTParametriActaNew(paramActa, ACTAConstants.DATA_ELABORAZIONE, GenericUtil.convertToString(inizioProcesso));
			  
			  
			  
		  }
		  else
		  {
			  logger.info("NON E' GESTIBILE");
		  }
		  
		  logger.info("STAMPO DATA_ELABORAZIONE - dopo: "+GenericUtil.recuperaValParametro(paramActa, ACTAConstants.DATA_ELABORAZIONE));
		  
		  /*
		  logger.info("RIPROVO");
		  
		  
		  for (SiceeTParametriActa siceeTParametriActa : paramActa) {
				if (siceeTParametriActa.getCodice().equals(ACTAConstants.DATA_ELABORAZIONE))
				{
					siceeTParametriActa.setValore(GenericUtil.convertToString(inizioProcesso));


				}
			}
			
			for (SiceeTParametriActa siceeTParametriActa : paramActa) {
				if (siceeTParametriActa.getCodice().equals(ACTAConstants.DATA_ELABORAZIONE))
				{
					logger.info("STAMPO DATA_ELABORAZIONE - KRONOS: "+siceeTParametriActa.getValore());
				}
			}
		  */
		  
		  
		  //SiceeTParametriActa[] paramActa = DBServiceTmp.findAllSiceeTParametriActa();

		  
		  
		  /*
		  logger.info("PRIMA DELL'AGGIORNA PARAMETRO");

		  for (SiceeTParametriActa siceeTParametriActa : paramActa) {
			  
			if (siceeTParametriActa.getCodice().equals(ACTAConstants.DATA_ELABORAZIONE))
			{
				  logger.info("TROVATO IL PARAMETRO new: "+siceeTParametriActa.getIdParametriActa());

				  logger.info("Stampo inizioProcesso: "+inizioProcesso);
				 siceeTParametriActa.setValore(GenericUtil.convertToString(inizioProcesso));
				  
//				  SiceeTParametriActa siceeTParametriActaNew = new SiceeTParametriActa();
//				  siceeTParametriActaNew.setIdParametriActa(siceeTParametriActa.getIdParametriActa());
//				  siceeTParametriActaNew.setCodice(siceeTParametriActa.getCodice());
//				  siceeTParametriActaNew.setValore(GenericUtil.convertToString(inizioProcesso));
//				  
				  
				traceMgr.updateSiceeTParametriActa(siceeTParametriActa);
				
			}
			
		}
		  System.out.println("PRIMA DI DORMIRE - 1 volta");

		  TimeUnit.SECONDS.sleep(10);
//		  Thread.sleep(60 *   // seconds to a minute
//		             1000); // milliseconds to a second

		  System.out.println("DOPO AVER DORMITO - 1 volta");

		  List<SiceeTCertificato> siceeTCertificatoList = traceMgr.findCertificatiDaGestireProva();

		  for (SiceeTCertificato siceeTCertificato : siceeTCertificatoList) {

			  Certificato certificato = new Certificato();
			  certificato = MapDto.mapToCertificato(siceeTCertificato); 

			  certificato.setVolume("1999");

			  traceMgr.updateSiceeTActa(MapDto.mapToSiceeTActa(certificato));
		  }

		  System.out.println("PRIMA DI DORMIRE - 2 volta");

		  TimeUnit.SECONDS.sleep(10);

		  System.out.println("DOPO AVER DORMITO - 2 volta");
		  */
		  
		  // Recupero tutti i parametri della configurazione
		  //SiceeTParametriActa[] paramActa = DBServiceTmp.findAllSiceeTParametriActa();
	
		  //traceMgr.findCertificatiDaGestireProva();
		  
		  // FUNZIONA
		  /*
		  List<SiceeTCertificato> certificati = traceMgr.findCertificatiDaGestire();

		  for (SiceeTCertificato siceeTCertificato : certificati) {
			  System.out.println("--------------------");

			  Set<SiceeTActa> acta = siceeTCertificato.getSiceeTActas();

			  System.out.println("Stampo  acta: "+acta);

			  for (SiceeTActa siceeTActa : acta) {
				  System.out.println("Acta.getIdDocActa: "+siceeTActa.getIdDocActa());
				  System.out.println("Acta.getIdProtocolloActa: "+siceeTActa.getIdProtocolloActa());
			  }
			  System.out.println("--------------------");

		  }
			*/


		  /*
		  List<SiceeTParametriActa> paramActa = traceMgr.findAllSiceeTParametriActa();
		  
		  System.out.println("PROVA - init");
		  System.out.println(paramActa.size());
		  System.out.println("PROVA - end");

		  logger.info("#### Stampo l'elenco: "+paramActa);
		  
		  for (SiceeTParametriActa siceeTParametriActa : paramActa) {
			  logger.info("--------------");
			  logger.info("codice: "+siceeTParametriActa.getCodice());
			  logger.info("valore: "+siceeTParametriActa.getValore());
			  logger.info("--------------");
		}
		  
		  String mitt = GenericUtil.recuperaValParametro(paramActa, ACTAConstants.MITTENTE_MAIL);
		  logger.info("mitt: "+mitt);

		  logger.info("#############################");
		  SiceeTParametriActa siceeTParametriActa = GenericUtil.recuperaTParametriActa(paramActa, ACTAConstants.DATA_ELABORAZIONE);
		  logger.info("siceeTParametriActa.getIdParametriActa(): "+siceeTParametriActa.getIdParametriActa());
		  logger.info("siceeTParametriActa.getCodice(): "+siceeTParametriActa.getCodice());
		  logger.info("siceeTParametriActa.getValore(): "+siceeTParametriActa.getValore());

		  traceMgr.updateSiceeTParametriActaValueByCodice(siceeTParametriActa.getCodice(), siceeTParametriActa.getValore());
		  
		  logger.info("#############################");
		  
		  
		  
		  traceMgr.findCertificatoByChiave();
		  
		  PrincipalIdType principalId = actaSrv.getPrincipalIdAcaris(paramActa);
		  
		  logger.info("Stampo il principal: "+principalId);
		  
		  
		  
		  */

		  /*
		  byte[] fileIndex = actaSrv.recuperaFileRicevuta("301441", "0023", "2014");
		  
		  logger.info("Stampo il file INDEX: "+fileIndex);
		  
		  logger.info("inizioProcesso: "+inizioProcesso);
		  
		  Mail mail = new Mail();

		  mail.setInizioProcesso(inizioProcesso);
		  mail.setMittente("assistenza.energia@csi.it");
		  mail.setDestinatario("giuseppe.todaro@csi.it");
		  mail.setOggetto("ACTA - errore grave");
		  mail.setTesto("Si e' verificato un errore GRAVE, prima dell'elaborazione dei certificati: ");
		  
		  try
		  {
			  logger.info("PRIMA di inviare mail");
			  actaSrv.sendMail(mail, "prova.pdf", fileIndex);
			  logger.info("DOPO aver inviato mail");
		  }
		  catch (Exception e)
		  {
			  // C'e' stata un'eccezione nell'invio della mail di errore grave, l'unica cosa che posso fare e' scriverlo nei log
			  logger.fatal("Si e' verificato un errore grave alivello generale", e);
		  }
		  */
		  
		  /*
		  SiceeTActa acta = new SiceeTActa();
		  
		  SiceeTActaLogPK actaPk = new SiceeTActaLogPK();
		  
		  actaPk.setIdCertificatore("1441");
		  actaPk.setProgrCertificato("0007");
		  actaPk.setAnno("2016");
		  actaPk.setTimestampLog(inizioProcesso);
		  
		  
		  SiceeTActaLog actaLog = new SiceeTActaLog();
		  actaLog.setId(actaPk);
		  actaLog.setDescLog("Prova");
		  
		  ACTAInvocationException ex = new ACTAInvocationException("Inserimento log");
		  
		  traceMgr.insertSiceeTActaLog(actaLog);
		  */
		  
		  
		  //actaSrv.recuperaRegioniTEST();
					  

		
		 
	  
	  }
	  catch (Exception e)
	  {
		  // C'e' stata un'eccezione nell'invio della mail di errore grave, l'unica cosa che posso fare e' scriverlo nei log
		  logger.fatal("Si e' verificato un errore grave a livello generale", e);
	  }
	  
	  logger.info("[KronosMgrSessionBean::gestisciProtocollazioneACTA_TST] END");

  }


  /**
   * Metodo richiamato automaticamente ogni ora per il reinoltro delle
   * comunicazioni perse nelle chiamate NAO-ANPR e per un report (giornaliero)
   * sulle chiamate non recuperabili.
   */
  //@Schedule(hour="*/1", persistent=false)

  // Commentato per provare ogni 30 minuti
  //@Schedule(minute = "*/2", hour = "*", persistent = false)
  @Schedule(minute = "*/30", hour = "*", persistent = false)
  public void gestisciProtocollazioneACTA() {

	  Calendar now = Calendar.getInstance();
	  int hour = now.get(Calendar.HOUR_OF_DAY);

	  logger.info("[KronosMgrSessionBean::gestisciProtocollazioneACTA] STARTED! " + now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE));

	  Timestamp inizioProcesso = GenericUtil.getInizioProcesso();
	  String inizioProcessoFormat = GenericUtil.convertToString(inizioProcesso);

	  ACTAService actaSrv = new ACTAService();

	  String mittente = null;
	  String destinatarioMailRiass = null;
	  List<SiceeTParametriActa> paramActa = null;

	  try
	  {
		  int apeGestiti = 0;
		  int apeArchiviati = 0;
		  int apeProtocollati = 0;

		  ObjectIdType repositoryId = null;
		  ObjectIdType catenerRepId = null;
		  ObjectIdType volumeId = null;
		  //String volumeSys = "2000";
		  String volumeSys = GenericUtil.getAnnoCorrente();
		  String volumeDB = null;


		  // Recupero tutti i parametri della configurazione
		  paramActa = traceMgr.findAllSiceeTParametriActa();
		  //SiceeTParametriActa[] paramActa = DBServiceTmp.findAllSiceeTParametriActa();

		  boolean isServizioAttivo = ACTAConstants.COD_S.equalsIgnoreCase(GenericUtil.recuperaValParametro(paramActa, ACTAConstants.SERVIZIO_ATTIVO));

		  if (isServizioAttivo)
		  {
			  logger.info("STAMPO DATA_ELABORAZIONE - prima: "+GenericUtil.recuperaValParametro(paramActa, ACTAConstants.DATA_ELABORAZIONE));

			  //SiceeTParametriActa actaDataElab = GenericUtil.recuperaTParametriActa(paramActa, ACTAConstants.DATA_ELABORAZIONE);

			  boolean isGestibile = isGestibile(inizioProcesso);
			  if (isGestibile)
			  {
				  logger.info("E' GESTIBILE - non c'e' nessun processo attivo");

				  logger.debug("[KronosMgrSessionBean::gestisciProtocollazioneACTA] - aggiorno DATA_ELABORAZIONE con: "+GenericUtil.convertToString(inizioProcesso));
				  
				  aggiornaParametro(paramActa, ACTAConstants.DATA_ELABORAZIONE, GenericUtil.convertToString(inizioProcesso));

				  mittente = GenericUtil.recuperaValParametro(paramActa, ACTAConstants.MITTENTE_MAIL);
				  destinatarioMailRiass = GenericUtil.recuperaValParametro(paramActa, ACTAConstants.DESTINATARIO_MAIL_RIASSUNTIVA);

				  logger.debug("[KronosMgrSessionBean::gestisciProtocollazioneACTA] - stampo mittente (DB): "+mittente);
				  logger.debug("[KronosMgrSessionBean::gestisciProtocollazioneACTA] - stampo destinatarioMailRiass (DB): "+destinatarioMailRiass);
				  
				  // Verifico se tutti i paramentri del DB sono valorizzati
				  
				  // Verifico il Repository
				  if (GenericUtil.recuperaValParametro(paramActa, ACTAConstants.REP_ID) == null)
				  {
					  
					  logger.debug("[KronosMgrSessionBean::gestisciProtocollazioneACTA] - recupero il repositoryId da ACTA");
					  
					  // Devo recuperare il repositoryId
					  repositoryId = actaSrv.getRepository(GenericUtil.recuperaValParametro(paramActa, ACTAConstants.REP_NAME));

					  logger.debug("[KronosMgrSessionBean::gestisciProtocollazioneACTA] - recuperato il repositoryId da ACTA: "+repositoryId.getValue());

					  // Setto il valore da salvare sul DB
					  aggiornaParametro(paramActa, ACTAConstants.REP_ID, repositoryId.getValue());
				  }
				  else
				  {
					  repositoryId = new ObjectIdType();
					  repositoryId.setValue(GenericUtil.recuperaValParametro(paramActa, ACTAConstants.REP_ID));
					  
					  logger.debug("[KronosMgrSessionBean::gestisciProtocollazioneACTA] - recuperato il repositoryId da DB: "+repositoryId.getValue());

				  }

				  PrincipalIdType principalId = actaSrv.getPrincipalIdAcaris(paramActa);


				  logger.debug("[KronosMgrSessionBean::gestisciProtocollazioneACTA] - Stampo principalId: "+principalId.getValue());

				  // Verifico il Catener
				  if (GenericUtil.recuperaValParametro(paramActa, ACTAConstants.ID_SERIE_CATENER) == null)
				  {
					  
					  logger.debug("[KronosMgrSessionBean::gestisciProtocollazioneACTA] - recupero il catenerRepId da ACTA");

					  // Devo recuperare l'Id del volume corrente
					  // Lo prendo dal DB, se non c'e' lo cerco su ACTA
					  catenerRepId = actaSrv.cercaCatener(repositoryId, principalId);

					  logger.debug("[KronosMgrSessionBean::gestisciProtocollazioneACTA] - recuperato il repositoryId da ACTA: "+catenerRepId.getValue());

					  // Setto il valore da salvare sul DB
					  aggiornaParametro(paramActa, ACTAConstants.ID_SERIE_CATENER, catenerRepId.getValue());

				  }
				  else
				  {
					  catenerRepId = new ObjectIdType();
					  catenerRepId.setValue(GenericUtil.recuperaValParametro(paramActa, ACTAConstants.ID_SERIE_CATENER));
					  
					  logger.debug("[KronosMgrSessionBean::gestisciProtocollazioneACTA] - recuperato il catenerRepId da DB: "+catenerRepId.getValue());

				  }


				  // Verifico il Volume

				  // recupero il volume su ACTA
				  ObjectIdType volumeRepId = actaSrv.cercaVolume(repositoryId, principalId, catenerRepId, volumeSys);
				  volumeDB = GenericUtil.recuperaValParametro(paramActa, ACTAConstants.VOLUME_CORRENTE);

				  if (volumeDB != null)
				  {
					  volumeId = new ObjectIdType();
					  // Se esiste il volume sul DB esistera' "sicuramente" anche l'ID
					  volumeId.setValue(GenericUtil.recuperaValParametro(paramActa, ACTAConstants.ID_VOLUME_CORRENTE));
				  }

				  // Verifico se il volume DB e' diverso dal volume attuale
				  logger.debug("[KronosMgrSessionBean::gestisciProtocollazioneACTA] - volumeSys: "+volumeSys);
				  logger.debug("[KronosMgrSessionBean::gestisciProtocollazioneACTA] - volumeDB: "+volumeDB);

				  if (!volumeSys.equals(volumeDB))
				  {
					  logger.debug("Il volume e' diverso");
					  if (volumeDB != null)
					  {

						  // Il volume DB e' valorizzato, quindi devo chiuderlo
						  logger.debug("[KronosMgrSessionBean::gestisciProtocollazioneACTA] - Il volume DB e' valorizzato, quindi devo chiuderlo");

						  // Devo chiudere il volumeDB
						  actaSrv.chiudiVolume(repositoryId, principalId, volumeId);

					  }

					  logger.debug("[KronosMgrSessionBean::gestisciProtocollazioneACTA] - Creo il volume: "+volumeSys);

					  // Se esiste  gia' il volume su ACTA non lo devo creare
					  if (volumeRepId == null)
					  {
						  // Devo creare il volume - (PRIMA di creare il volume lo cerco, se lo trovo lo ritorno - non dovrebbe succdere tranne nel caso in cui qualcuno inserisca a mano il volume su ACTA)
						  volumeId = actaSrv.creaVolume(repositoryId, principalId, catenerRepId, volumeSys);
					  }
					  else 
					  {
						  volumeId = volumeRepId;
					  }

					  // Setto il valore da salvare sul DB
					  aggiornaParametro(paramActa, ACTAConstants.VOLUME_CORRENTE, volumeSys);

					  aggiornaParametro(paramActa, ACTAConstants.ID_VOLUME_CORRENTE, volumeId.getValue());

				  }

				  // Adesso devo recuperare tutti glia APE da archiviare/protocollare
				  logger.debug("Devo archiviare il documento");

				  String numMaxRecords = GenericUtil.recuperaValParametro(paramActa, ACTAConstants.NUMERO_MAX_RECORD);
				  
				  logger.debug("[KronosMgrSessionBean::gestisciProtocollazioneACTA] - stampo numMaxRecords (DB): "+numMaxRecords);

				  List<SiceeTCertificato> certificatiList = traceMgr.findCertificatiDaGestire(GenericUtil.convertToInt(numMaxRecords));
				  ArrayList<String> elencoErrori = new ArrayList<String>();

				  if (certificatiList != null && certificatiList.size() > 0)
				  {


					  // Per sicurezza mi riufaccio dare il principal
					  principalId = actaSrv.getPrincipalIdAcaris(paramActa);

					  apeGestiti = certificatiList.size();

					  String idDocumento = null;
					  String idClassificazione = null;
					  String idProtocollo = null;
					  String[] idProtocolloList = null;
					  String dataProtocollo = null;
					  String numProtocollo = null;

					  PagingResponseType pagingResponse = null;
					  Certificato certificato = null;
					  boolean isArchiviato = false;
					  
					  for (SiceeTCertificato certificatoDb : certificatiList) {

						  certificato = MapDto.mapToCertificato(certificatoDb); 

						  //GenericUtil.stampa(certificato, false, 3);

						  idDocumento = null;
						  idClassificazione = null;
						  idProtocollo = null;
						  idProtocolloList = null;

						  numProtocollo = null;
						  dataProtocollo = null;
						  isArchiviato = false;

						  // recupero l'ape su acta (db)
						  // Son sicuro che c'e' solo
						  //SiceeTActa actaDb = (SiceeTActa) certificato.getSiceeTActas().toArray()[0];

						  idDocumento = certificato.getIdDocActa();
						  idClassificazione = certificato.getIdClassificazioneActa();

						  // Questo non dovrebbe mai esserci, altrimenti il documento e' gia' protocollato e quindi non dovrei trattarlo 
						  idProtocollo = certificato.getIdProtocolloActa();

						  logger.debug("[KronosMgrSessionBean::gestisciProtocollazioneACTA] - --------------------------------------");
						  logger.debug("[KronosMgrSessionBean::gestisciProtocollazioneACTA] - gestisco certificatoDb.getId.getIdCertificatore(): "+certificatoDb.getId().getIdCertificatore());
						  logger.debug("[KronosMgrSessionBean::gestisciProtocollazioneACTA] - gestisco certificatoDb.getId.getProgrCertificato(): "+certificatoDb.getId().getProgrCertificato());
						  logger.debug("[KronosMgrSessionBean::gestisciProtocollazioneACTA] - gestisco certificatoDb.getId.getAnno(): "+certificatoDb.getId().getAnno());
						  
						  logger.debug("[KronosMgrSessionBean::gestisciProtocollazioneACTA] - DB idDocumento: "+idDocumento);
						  logger.debug("[KronosMgrSessionBean::gestisciProtocollazioneACTA] - DB idClassificazione: "+idClassificazione);
						  logger.debug("[KronosMgrSessionBean::gestisciProtocollazioneACTA] - DB idProtocollo: "+idProtocollo);

						  
						  pagingResponse = null; 
						  
						  try
						  {
							  if (idDocumento == null)
							  {
								  // Devo archiviare il documento

								  // per sicurezza prima lo cerco su ACTA, per essere sicuro che non ci sia. Ci potrebbe essere il caso (remoto) in cui il file e' stato archiviato ma c'e' stato un problema nell'aggiornamento del DB
								  pagingResponse = actaSrv.cercaDocumentoArchiviatoByCertificato(repositoryId, principalId, certificato);

								  logger.debug("Stampo pagingResponse - recupera documento: "+pagingResponse);			
								  logger.debug("Stampo pagingResponse.getObjectsLength() - recupera documento: "+pagingResponse.getObjectsLength());	

								  if (pagingResponse.getObjectsLength() > 0)
								  {
									  // Ho trovato il documento su ACTA

									  idDocumento = actaSrv.getPropertyValueByName(pagingResponse, "objectId");
									  //idProtocollo = actaSrv.getPropertyValueByName(pagingResponse, "idProtocolloList");

									  certificato.setIdDocActa(idDocumento);
									  certificato.setIdProtocolloActa(idProtocollo);

									  logger.debug("[KronosMgrSessionBean::gestisciProtocollazioneACTA] - Acta (cercaDocumentoArchiviatoByCertificato) idDocumento: "+idDocumento);
									  logger.debug("[KronosMgrSessionBean::gestisciProtocollazioneACTA] - Acta (cercaDocumentoArchiviatoByCertificato) idProtocollo: "+idProtocollo);

									  //							  actaDb.setIdDocActa(idDocumento);
									  //							  actaDb.setIdProtocolloActa(idProtocollo);

									  // Nel caso in cui l'id protocollo e' valorizzato devo andare a recuperare la classificazione
									  // devo ricercare la classificazione (attraverso il documento) per poter salvare sul DB l'idClassificazione
									  idClassificazione = actaSrv.cercaDocumentoClassificazioneByIdDoc(repositoryId, principalId, idDocumento);

									  logger.debug("[KronosMgrSessionBean::gestisciProtocollazioneACTA] - Acta (da rec doc) idClassificazione: "+idClassificazione);

									  certificato.setIdClassificazioneActa(idClassificazione);
									  //actaDb.setIdClassificazioneActa(idClassificazione);

									  logger.debug("Stampo l'objectId: "+pagingResponse.getObjects(0).getObjectId().getValue());
									  logger.debug("Stampo idDocumentoList: "+idDocumento);			
									  logger.debug("Stampo idProtocolloList: "+idProtocollo);			

								  }
								  else
								  {
									  // Non c'e' il documento su ACTA (giustamente) e quindi devo archiviarlo

									  byte[] fileIndex = actaSrv.recuperaDocumentoIndex(certificato.getUidIndex());

									  logger.info("HO RECUPERATO IL FILE");

									  IdentificatoreDocumento identDoc = actaSrv.archiviaDocumento(repositoryId, principalId, volumeId, certificato, paramActa, fileIndex);

									  idDocumento = identDoc.getObjectIdDocumento().getValue();
									  idClassificazione = identDoc.getObjectIdClassificazione().getValue();

									  certificato.setIdDocActa(idDocumento);
									  certificato.setIdClassificazioneActa(idClassificazione);
									  
									  logger.debug("[KronosMgrSessionBean::gestisciProtocollazioneACTA] - Acta (archiviaDocumento) idDocumento: "+idDocumento);
									  logger.debug("[KronosMgrSessionBean::gestisciProtocollazioneACTA] - Acta (archiviaDocumento) idClassificazione: "+idClassificazione);

									  isArchiviato = true;

								  }

								  // Inizio ad aggiornare il DB con l'archiviazione
								  certificato.setVolume(volumeSys);
								  certificato.setTimestampArchiviazione(new Date());
								  certificato.setTipoDocumentoActa(EnumTipoDocumentoArchivistico.DOCUMENTO_SEMPLICE.value());

								  logger.debug("[KronosMgrSessionBean::gestisciProtocollazioneACTA] - aggiorno DB con l'archiviazione");

								  traceMgr.updateSiceeTActa(MapDto.mapToSiceeTActa(certificato));

								  apeArchiviati++;
							  }
							  
							  if (idProtocollo == null)
							  {
								  // Devo protocollare il documento

								  if (idClassificazione == null)
								  {
									  // devo ricercare la classificazione (attraverso il documento) per poter protocollare
									  idClassificazione = actaSrv.cercaDocumentoClassificazioneByIdDoc(repositoryId, principalId, idDocumento);
									  certificato.setIdClassificazioneActa(idClassificazione);

									  logger.debug("[KronosMgrSessionBean::gestisciProtocollazioneACTA] - Acta (cercaDocumentoClassificazioneByIdDoc) idClassificazione: "+idClassificazione);

									  logger.debug("[KronosMgrSessionBean::gestisciProtocollazioneACTA] - aggiorno DB con la classificazione");

									  // Aggiorno la classificazione
									  traceMgr.updateSiceeTActa(MapDto.mapToSiceeTActa(certificato));

								  }

								  logger.debug("Stampo l'idClassificazione: "+idClassificazione);
								  logger.debug("[KronosMgrSessionBean::gestisciProtocollazioneACTA] - !isArchiviato: "+(!isArchiviato));

								  if (!isArchiviato)
								  {
									  // se mi trovo qui vuol dire che in questo processo non ho archiviato il documento (quindi idDocumento e' != null) 
									  // mentre idProtocollo == null
									  // questo vuol dire che nel processo precedente e' andato a buon fine l'archiviazione, mentre e' fallita la protocollazione (oppure il salvataggio sul DB),
									  // quindi provo a recuperare la protocollazione, se non c'e' vuol dire che e' fallita la protocollazione, se invece c'e' vuol dire che e' fallito il salvataggio sul DB

									// Devo recuperare nuovamente il DOC su ACTA per recuperare l'idProtocollo
									  pagingResponse = actaSrv.cercaDocumentoArchiviatoByIdDoc(repositoryId, principalId, idDocumento);
									  
									  // (*)
									  // la Property idProtocolloList potrebbe ritornare una lista di protocolli,
									  // ad esempio si e' verificato che abbiamo ricevuto per un certo id il numeroProt XXXXXXXXXX/2020
									  // il num XXX e' un numero temporaneo, in seguito viene settato quello corretto ma continua ad esistere l'XXX
									  // prima noi prendevamo il primo della lista ed e' successo che fosse l'XXX
									  // adesso li prendiamo tutti e nel codice sotto cicliamo x escludere quello non corretto
									  idProtocolloList = actaSrv.getPropertyValueListByName(pagingResponse, "idProtocolloList");
									  
									  // Se dentro l'idProtocolloList c'e' SOLO XXX
									  // devo settare idProtocolloList = null
									  boolean isProtValidoPresente = false;
									  
									  if (idProtocolloList != null && idProtocolloList.length > 0)
									  {
										  for (String idProtocolloTmp : idProtocolloList) 
										  {
		
											  PagingResponseType pagingResponseProt = actaSrv.cercaProtocollo(repositoryId, principalId, idProtocolloTmp);
											  
											  String numProtocolloTmp = actaSrv.getPropertyValueByName(pagingResponseProt, "idRegistrazione");
											  
											  if (numProtocolloTmp != null && !numProtocolloTmp.toUpperCase().contains("X")) {
												  
												  isProtValidoPresente = true;
												  break;
											  }
										  }

										  logger.debug("[KronosMgrSessionBean::gestisciProtocollazioneACTA] - isProtValidoPresente: "+isProtValidoPresente);

										  if (!isProtValidoPresente)
										  {
											  idProtocolloList = null;
										  }
									  }									  

								  }
								  
								  logger.debug("[KronosMgrSessionBean::gestisciProtocollazioneACTA] - idProtocolloList: "+idProtocolloList);

								  if (idProtocolloList == null)
								  {
									  // ATTENZIONE: non e' un controllo replicato
									  // perche' nel caso in cui si entra nell'if (!isArchiviato) e lo si trova non si entra in questa parte di codice)
									  // ma si sfrutta la modifica del db con l'invio della mail

									  logger.debug("[KronosMgrSessionBean::gestisciProtocollazioneACTA] - repositoryId: "+repositoryId);
									  logger.debug("[KronosMgrSessionBean::gestisciProtocollazioneACTA] - principalId: "+principalId);
									  logger.debug("[KronosMgrSessionBean::gestisciProtocollazioneACTA] - certificato: "+certificato);
									  logger.debug("[KronosMgrSessionBean::gestisciProtocollazioneACTA] - idClassificazione: "+idClassificazione);
									  logger.debug("[KronosMgrSessionBean::gestisciProtocollazioneACTA] - paramActa: "+paramActa);

									  
									  IdentificazioneRegistrazione identReg = actaSrv.protocollaDocumento(repositoryId, principalId, certificato, idClassificazione, paramActa);

									  logger.debug("[KronosMgrSessionBean::gestisciProtocollazioneACTA] - Acta ho protocollato!");

									  // Devo recuperare nuovamente il DOC su ACTA per recuperare l'idProtocollo
									  pagingResponse = actaSrv.cercaDocumentoArchiviatoByIdDoc(repositoryId, principalId, idDocumento);

									  // idProtocolloList puo' contenere piu' protocolli, quindi devo recuperare quello valido
									  // Vedi nota sopra (*)
									  idProtocolloList = actaSrv.getPropertyValueListByName(pagingResponse, "idProtocolloList");
									  
									  logger.debug("[KronosMgrSessionBean::gestisciProtocollazioneACTA] - Acta (cercaDocumentoArchiviatoByIdDoc) idProtocolloList: "+idProtocolloList);


								  }
								  
								  if (logger.isDebugEnabled())
									  GenericUtil.stampa(certificato, true, 3);
								  
								  if (idProtocolloList != null && idProtocolloList.length > 0)
								  {
									  for (String idProtocolloTmp : idProtocolloList) 
									  {
	
										  PagingResponseType pagingResponseProt = actaSrv.cercaProtocollo(repositoryId, principalId, idProtocolloTmp);
										  
										  String numProtocolloTmp = actaSrv.getPropertyValueByName(pagingResponseProt, "idRegistrazione");
										  
										  if (numProtocolloTmp != null && !numProtocolloTmp.toUpperCase().contains("X")) {
											  
											  idProtocollo = idProtocolloTmp;
											  dataProtocollo = actaSrv.getPropertyValueByName(pagingResponseProt, "dataProtocollo");
											  numProtocollo = actaSrv.getPropertyValueByName(pagingResponseProt, "idRegistrazione");
											  break;
										  }
									  }
								  }
								  
								  if (idProtocollo != null)
								  {
									  /*
									  GenericUtil.stampa(certificato, false, 3);
	
									  PagingResponseType pagingResponseProt = actaSrv.cercaProtocollo(repositoryId, principalId, idProtocollo);
									  numProtocollo = actaSrv.getPropertyValueByName(pagingResponseProt, "idRegistrazione");
									  dataProtocollo = actaSrv.getPropertyValueByName(pagingResponseProt, "dataProtocollo");
									  */
									  
									  logger.debug("[KronosMgrSessionBean::gestisciProtocollazioneACTA] - Acta (cercaProtocollo) numProtocollo: "+numProtocollo);
									  logger.debug("[KronosMgrSessionBean::gestisciProtocollazioneACTA] - Acta (cercaProtocollo) dataProtocollo: "+dataProtocollo);
	
									  certificato.setIdProtocolloActa(idProtocollo);
									  certificato.setTimestampProtocollo(GenericUtil.convertToDateActa(dataProtocollo));
									  certificato.setNumeroProtocollo(numProtocollo);
									  certificato.setMailInviata(certificato.getEmail());
									  
									  logger.debug("[KronosMgrSessionBean::gestisciProtocollazioneACTA] - aggiorno DB con la protocollazione");
	
									  // Aggiorno il DB con la protocollazione
									  traceMgr.updateSiceeTActa(MapDto.mapToSiceeTActa(certificato));
	
									  // Devo mandare la mail al certificatore
									  actaSrv.sendMailProtocollazione(mittente, certificato, numProtocollo);
	
									  
									  apeProtocollati++;
								  }
								  else
								  {
									  // Id Protocolo e' == NULL evidentemente pur avendo  protocollato, non c'e' un protocollo valido
									  ACTAInvocationException ex = new ACTAInvocationException("Attenzione, l'APE e' protocollato ma con un numero protocollo non VALIDO (verra' ritentata la protocollazione alla prossima esecuzione).");

									  // Devo salvare nel DB 
									  traceMgr.insertSiceeTActaLog(MapDto.mapToSiceeTActaLog(certificato, inizioProcesso, ex));
									  elencoErrori.add(GenericUtil.getOggettoCertificato(certificato) + ": " + inizioProcessoFormat + " " + ex.getMessage());

								  }
							  }
						  }
						  catch (ACTAInvocationException ex)
						  {
							  // C'e' stata un'eccezione nella registrazione/protocollazione
							  // Devo salvare nel DB 
							  traceMgr.insertSiceeTActaLog(MapDto.mapToSiceeTActaLog(certificato, inizioProcesso, ex));

							  logger.error("Si e' verificata un'eccezione gestita: "+ex.getMessage(),ex);

							  elencoErrori.add(GenericUtil.getOggettoCertificato(certificato) + ": " + inizioProcessoFormat + " " + ex.getMessage());
						  }
						  catch (Exception ex)
						  {
							  // C'e' stata un'eccezione nella registrazione/protocollazione
							  // Devo salvare nel DB 

							  traceMgr.insertSiceeTActaLog(MapDto.mapToSiceeTActaLog(certificato, inizioProcesso, new ACTAInvocationException(ex)));

							  logger.error("Si e' verificata un'eccezione NON gestita: "+ex.getMessage(), ex);
							  elencoErrori.add(GenericUtil.getOggettoCertificato(certificato) + ": " + inizioProcessoFormat + " " + ex.getMessage());

						  }
					  }
				  }

				  // devo gestire il risultato mandando una mail

				  Mail mail = new Mail();

				  mail.setInizioProcesso(inizioProcesso);
				  mail.setMittente(mittente);
				  mail.setDestinatario(destinatarioMailRiass);
				  mail.setApeGestiti(apeGestiti);
				  mail.setApeArchiviati(apeArchiviati);
				  mail.setApeProtocollati(apeProtocollati);
				  mail.setElencoErrori(elencoErrori);

				  logger.debug("[KronosMgrSessionBean::gestisciProtocollazioneACTA] - mando mail di riepilogo");

				  actaSrv.sendMailRiepilogo(mail);

				  
				  logger.debug("[KronosMgrSessionBean::gestisciProtocollazioneACTA] - aggiorno DATA_ELABORAZIONE con NULL");

				  // Ripulisco la data elaborazione, in modo che il prossimo processo possa lavorare
				  aggiornaParametro(paramActa, ACTAConstants.DATA_ELABORAZIONE, null);
			  }
			  else 
			  {
				  logger.info("NON E' GESTIBILE - c'e' ancora un processo attivo");
			  }
		  }
		  else 
		  {
			  logger.info("NON E' ATTIVO IL SERVIZIO (paramentro sul DB)");
		  }
	  }
	  catch (Exception ex)
	  {
		  logger.error("[KronosMgrSessionBean::gestisciProtocollazioneACTA] - eccezione GRAVE: ", ex);

		  // E fallito qualcosa a livello generale, devo mandare una mail ad assistenza
		  Mail mail = new Mail();
		  mail.setOggetto("SIPEE - ERRORE grave - Archiviazione e Protocollazione ACTA "+GenericUtil.convertToString(inizioProcesso));
		  mail.setInizioProcesso(inizioProcesso);
		  mail.setMittente(mittente);
		  mail.setDestinatario(destinatarioMailRiass);
		  mail.setTesto("Si e' verificato un errore GRAVE, prima dell'elaborazione dei certificati: \n"+ex.getMessage());

		  try
		  {
			  // Ripulisco la data elaborazione, in modo che il prossimo processo possa lavorare
			  aggiornaParametro(paramActa, ACTAConstants.DATA_ELABORAZIONE, null);

			  actaSrv.sendMail(mail, null, null);
		  }
		  catch (Exception e)
		  {
			  // C'e' stata un'eccezione nell'invio della mail di errore grave, l'unica cosa che posso fare e' scriverlo nei log
			  logger.fatal("Si e' verificato un errore grave a livello generale", e);
		  }
	  }

	  logger.info("ELABORAZIONE FINITA");

  }

  //@Schedule(minute = "*/1", hour = "*", persistent = false)
  public void sendMailTest() {

	  Calendar now = Calendar.getInstance();
	  int hour = now.get(Calendar.HOUR_OF_DAY);

	  logger.info("[KronosMgrSessionBean::sendMailTest] STARTED! " + now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE));

	  Timestamp inizioProcesso = GenericUtil.getInizioProcesso();
	  String inizioProcessoFormat = GenericUtil.convertToString(inizioProcesso);

	  ACTAService actaSrv = new ACTAService();

	  String mittente = "certificazione.energetica@regione.piemonte.it";
	  String destinatarioMailRiass = "giuseppe.todaro@csi.it";

	  try
	  {
		  int apeGestiti = 0;
		  int apeArchiviati = 0;
		  int apeProtocollati = 0;


		  /*
			  Certificato certificato = new Certificato();
			  certificato.setAnno("2009");
			  certificato.setNumCertificatore("100132");
			  certificato.setProgrCertificato("0014");
			  certificato.setEmail("giuseppe.todaro@csi.it");
			  certificato.setDtUpload(new Date());

			  // Devo mandare la mail al certificatore
			  actaSrv.sendMailProtocollazione(mittente, certificato, "00000242/2019");
		   */

		  // devo gestire il risultato mandando una mail

		  Mail mail = new Mail();

		  mail.setInizioProcesso(inizioProcesso);
		  mail.setMittente(mittente);
		  mail.setDestinatario(destinatarioMailRiass);
		  
		  mail.setDestinatarioCC(destinatarioMailRiass);
		  
		  mail.setApeGestiti(apeGestiti);
		  mail.setApeArchiviati(apeArchiviati);
		  mail.setApeProtocollati(apeProtocollati);
		  mail.setElencoErrori(null);

		  logger.info("[KronosMgrSessionBean::gestisciProtocollazioneACTA] - mando mail di riepilogo - prima");

		  actaSrv.sendMailRiepilogo(mail);

		  logger.info("[KronosMgrSessionBean::gestisciProtocollazioneACTA] - mando mail di riepilogo - dopo");


	  }
	  catch (Exception ex)
	  {
		  logger.error("[KronosMgrSessionBean::gestisciProtocollazioneACTA] - eccezione GRAVE: ", ex);


	  }

	  logger.info("ELABORAZIONE FINITA");

  }

  private void sendMail(String subject, String text) throws Exception {
//    String addressFrom = traceMgr.findParametro(RECUPERO_EVENTI_NAO_MAIL_FROM, null);
//    if (addressFrom == null) {
//      logger.info("[KronosMgrSessionBean::sendMail] RECUPERO_EVENTI_NAO_MAIL_FROM non configurato, impossibile inviare la mail");
//      return;
//    }
//    String addressTo = traceMgr.findParametro(RECUPERO_EVENTI_NAO_MAIL_TO, null);
//    if (addressTo == null) {
//      logger.info("[KronosMgrSessionBean::sendMail] RECUPERO_EVENTI_NAO_MAIL_TO non configurato, impossibile inviare la mail");
//      return;
//    }
//    Properties properties = new Properties();
//    String[] hostAndPort = getMailHostAndPort();
//    String host = hostAndPort[0];
//    String port = hostAndPort[1];
//    properties.setProperty("mail.smtp.host", host);
//    properties.setProperty("mail.smtp.port", port);
//    logger.info("[KronosMgrSessionBean::sendMail] Invio mail su "+host+":"+port+" a " + addressTo);
//    Session session = Session.getDefaultInstance(properties);
//    MimeMessage m = new MimeMessage(session);
//    Address from = new InternetAddress(addressFrom);
//    Address[] to = new InternetAddress[] { new InternetAddress(addressTo) };
//    m.setFrom(from);
//    m.setRecipients(Message.RecipientType.TO, to);
//    m.setSubject(subject);
//    m.setSentDate(new Date());
//    m.setContent(text, "text/plain");
//    Transport.send(m);
  }

  

  private boolean isGestibile(Timestamp inizioProcesso)
  {
	  boolean isProcessabile = false;

	  int resUpdate = traceMgr.updateParamDataElaborazione(GenericUtil.convertToString(inizioProcesso));

	  if (resUpdate == 1)
	  {
		  // e' stato fatto l'update con il nuovo inizioProcesso,
		  // questo vuol dire che non c'era nessun processo attivo (DATA_ELABORAZIONE = null), oppure c'era un processo piu' vecchio di 5 ore, quindi procedo ugualmente  

		  isProcessabile = true;
	  }
	  else
	  {
		  // c'e' un processo attivo
		  isProcessabile = false;
	  }

	  return isProcessabile;
  }
  /*
  private boolean isGestibileOld(SiceeTParametriActa actaDataElab, Timestamp inizioProcesso)
  {
	  boolean isProcessabile = false;

	  String dataProcessoDb = actaDataElab.getValore();

	  if (!GenericUtil.isNullOrEmpty(dataProcessoDb))
	  {
		  try
		  {
			  String strDate1 = dataProcessoDb;
			  //String strDate2 = "2007/04/15 13:45:00";
			  //String strDate2 = "2009/08/02 20:45:07";

//			  SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//			  fmt.setLenient(false);

			  // Parses the two strings.
			  Date d1 = GenericUtil.convertToDateCompleta(strDate1);
			  Date d2 = inizioProcesso;

			  // Calculates the difference in milliseconds.
			  long millisDiff = d2.getTime() - d1.getTime();

			  // Calculates days/hours/minutes/seconds.
			  int seconds = (int) (millisDiff / 1000 % 60);
			  int minutes = (int) (millisDiff / 60000 % 60);
			  int hours = (int) (millisDiff / 3600000 % 24);
			  int days = (int) (millisDiff / 86400000);

			  System.out.println("Between " + strDate1 + " and " + inizioProcesso + " there are:");
			  System.out.print(days + " days, ");
			  System.out.print(hours + " hours, ");
			  System.out.print(minutes + " minutes, ");
			  System.out.println(seconds + " seconds");

			  long diffHours = millisDiff / (60 * 60 * 1000); //differenza in ore
			  
			  if (diffHours < 5)
			  {
				  isProcessabile = false;
			  }
			  else
			  {
				  // E' passato troppo tempo dall'ultimo processo evidentemente c'e' stato un problema
				  // Cosa devo fare???
				  
				  // Per adesso procedo con l'elaborazione
				  
				  
				  isProcessabile = true;
			  }
			  System.out.println(diffHours + " diffHours");
		  } catch (Exception e) {
			  System.err.println(e);
			  e.printStackTrace();
		  }
	  }
	  else
	  {
		  // Non c'e' nessun processo attivo
		  isProcessabile = true;
	  }
	  
	  
	  return isProcessabile;
  }
  */
  
  /**
   * Verifica se il range (nel formato "from-to,interval" con valori espressi in
   * ore) include l'istante corrente.
   */
  private boolean checkRange(String range, int lastExec, int hourNow) {
    int[] rangeData = splitRange(range);
    int from = rangeData[0];
    int to = rangeData[1];
    int interval = rangeData[2];
    int elapsed = hourNow-lastExec;
    if(elapsed<0) elapsed += 24;
    if(elapsed<interval) return false;
    if(hourNow<from && hourNow>to) return false;
    return true;
  }
  
  private int[] splitRange(String range) {
    String[] s1 = range.split(",");
    String[] s2 = s1[0].split("-");
    int[] result = new int[] {Integer.parseInt(s2[0]),Integer.parseInt(s2[1]),Integer.parseInt(s1[1])};
    return result;
  }

  private String[] getMailHostAndPort() throws Exception {
    Properties properties = new Properties();
    InputStream stream = this.getClass().getResourceAsStream("/wsEndpointUrls.properties");
    properties.load(stream);
    return new String[] {properties.getProperty("mail.host"),properties.getProperty("mail.port")};
  }
  
 
}
