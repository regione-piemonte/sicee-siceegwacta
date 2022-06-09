/*******************************************************************************
 * SPDX-License-Identifier: EUPL-1.2
 * Copyright Regione Piemonte - 2020
 *******************************************************************************/
package it.csi.sicee.siceegwacta.business.operazioni;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

//import javax.wsdl.OperationType;


import it.csi.csi.porte.InfoPortaDelegata;
import it.csi.csi.porte.proxy.PDProxy;
import it.csi.csi.util.xml.PDConfigReader;
import it.csi.csi.wrapper.CSIException;
import it.csi.csi.wrapper.SystemException;
import it.csi.csi.wrapper.UnrecoverableException;
import it.csi.sicee.siceegwacta.business.dto.Certificato;
import it.csi.sicee.siceegwacta.business.dto.Mail;
import it.csi.sicee.siceegwacta.exception.ACTAInvocationException;
import it.csi.sicee.siceegwacta.integration.db.SiceeTCertificato;
import it.csi.sicee.siceegwacta.integration.db.SiceeTParametriActa;
import it.csi.sicee.siceegwacta.util.ACTAConstants;
import it.csi.sicee.siceegwacta.util.GenericUtil;
import it.csi.sicee.siceeorch.dto.siceeorch.Documento;
import it.csi.sicee.siceeorch.dto.siceeorch.Regione;
import it.csi.sicee.siceeorch.exception.siceeorch.DocumentoException;
import it.csi.sicee.siceeorch.interfacecsi.siceeorch.SiceeorchSrv;
import it.csi.sicee.siceews.stubs.SiceewsMgrLocator;
import it.csi.sicee.siceews.stubs.SiceewsMgrSoapBindingStub;
import it.doqui.acta.acaris.backofficeservice.BackOfficeServicePort;
import it.doqui.acta.acaris.documentservice.DocumentServicePort;
import it.doqui.acta.acaris.navigationservice.NavigationServicePort;
import it.doqui.acta.acaris.objectservice.ObjectServicePort;
import it.doqui.acta.acaris.officialbookservice.OfficialBookServicePort;
import it.doqui.acta.acaris.repositoryservice.RepositoryServicePort;
import it.doqui.acta.actasrv.business.util.type.AcarisUtils;
import it.doqui.acta.actasrv.client.AcarisServiceClient;
import it.doqui.acta.actasrv.dto.acaris.type.archive.AcarisRepositoryEntryType;
import it.doqui.acta.actasrv.dto.acaris.type.archive.ClassificazionePropertiesType;
import it.doqui.acta.actasrv.dto.acaris.type.archive.ContenutoFisicoPropertiesType;
import it.doqui.acta.actasrv.dto.acaris.type.archive.DocumentoFisicoPropertiesType;
import it.doqui.acta.actasrv.dto.acaris.type.archive.DocumentoSemplicePropertiesType;
import it.doqui.acta.actasrv.dto.acaris.type.archive.EnumDocPrimarioType;
import it.doqui.acta.actasrv.dto.acaris.type.archive.EnumFolderObjectType;
import it.doqui.acta.actasrv.dto.acaris.type.archive.EnumTipoDocumentoType;
import it.doqui.acta.actasrv.dto.acaris.type.archive.IdFormaDocumentariaType;
import it.doqui.acta.actasrv.dto.acaris.type.archive.IdStatoDiEfficaciaType;
import it.doqui.acta.actasrv.dto.acaris.type.archive.VolumeSerieTipologicaDocumentiPropertiesType;
import it.doqui.acta.actasrv.dto.acaris.type.backoffice.ClientApplicationInfo;
import it.doqui.acta.actasrv.dto.acaris.type.backoffice.EnumBackOfficeObjectType;
import it.doqui.acta.actasrv.dto.acaris.type.backoffice.PrincipalExtResponseType;
import it.doqui.acta.actasrv.dto.acaris.type.common.AcarisContentStreamType;
import it.doqui.acta.actasrv.dto.acaris.type.common.CodiceFiscaleType;
import it.doqui.acta.actasrv.dto.acaris.type.common.EnumMimeTypeType;
import it.doqui.acta.actasrv.dto.acaris.type.common.EnumObjectType;
import it.doqui.acta.actasrv.dto.acaris.type.common.EnumPropertyFilter;
import it.doqui.acta.actasrv.dto.acaris.type.common.EnumQueryOperator;
import it.doqui.acta.actasrv.dto.acaris.type.common.EnumStreamId;
import it.doqui.acta.actasrv.dto.acaris.type.common.IdAOOType;
import it.doqui.acta.actasrv.dto.acaris.type.common.IdNodoType;
import it.doqui.acta.actasrv.dto.acaris.type.common.IdStrutturaType;
import it.doqui.acta.actasrv.dto.acaris.type.common.IdVitalRecordCodeType;
import it.doqui.acta.actasrv.dto.acaris.type.common.NavigationConditionInfoType;
import it.doqui.acta.actasrv.dto.acaris.type.common.ObjectIdType;
import it.doqui.acta.actasrv.dto.acaris.type.common.ObjectResponseType;
import it.doqui.acta.actasrv.dto.acaris.type.common.PagingResponseType;
import it.doqui.acta.actasrv.dto.acaris.type.common.PrincipalIdType;
import it.doqui.acta.actasrv.dto.acaris.type.common.PropertyFilterType;
import it.doqui.acta.actasrv.dto.acaris.type.common.PropertyType;
import it.doqui.acta.actasrv.dto.acaris.type.common.QueryConditionType;
import it.doqui.acta.actasrv.dto.acaris.type.common.QueryNameType;
import it.doqui.acta.actasrv.dto.acaris.type.common.QueryableObjectType;
import it.doqui.acta.actasrv.dto.acaris.type.common.SimpleResponseType;
import it.doqui.acta.actasrv.dto.acaris.type.document.ContenutoFisicoIRC;
import it.doqui.acta.actasrv.dto.acaris.type.document.DocumentoArchivisticoIRC;
import it.doqui.acta.actasrv.dto.acaris.type.document.DocumentoFisicoIRC;
import it.doqui.acta.actasrv.dto.acaris.type.document.EnumStepErrorAction;
import it.doqui.acta.actasrv.dto.acaris.type.document.EnumTipoDocumentoArchivistico;
import it.doqui.acta.actasrv.dto.acaris.type.document.EnumTipoOperazione;
import it.doqui.acta.actasrv.dto.acaris.type.document.FailedStepsInfo;
import it.doqui.acta.actasrv.dto.acaris.type.document.IdentificatoreDocumento;
import it.doqui.acta.actasrv.dto.acaris.type.officialbook.DestinatarioInterno;
import it.doqui.acta.actasrv.dto.acaris.type.officialbook.EnumTipoAPI;
import it.doqui.acta.actasrv.dto.acaris.type.officialbook.EnumTipoRegistrazioneDaCreare;
import it.doqui.acta.actasrv.dto.acaris.type.officialbook.EnumTipologiaSoggettoAssociato;
import it.doqui.acta.actasrv.dto.acaris.type.officialbook.IdentificazioneProtocollante;
import it.doqui.acta.actasrv.dto.acaris.type.officialbook.IdentificazioneRegistrazione;
import it.doqui.acta.actasrv.dto.acaris.type.officialbook.InfoCreazioneCorrispondente;
import it.doqui.acta.actasrv.dto.acaris.type.officialbook.InfoCreazioneRegistrazione;
import it.doqui.acta.actasrv.dto.acaris.type.officialbook.MittenteEsterno;
import it.doqui.acta.actasrv.dto.acaris.type.officialbook.ProtocollazioneDocumentoEsistente;
import it.doqui.acta.actasrv.dto.acaris.type.officialbook.RegistrazioneArrivo;
import it.doqui.acta.actasrv.dto.acaris.type.officialbook.RiferimentoSoggettoEsistente;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
//import it.doqui.index.ecmengine.client.webservices.dto.Node;
//import it.doqui.index.ecmengine.client.webservices.dto.OperationContext;
//import it.doqui.index.ecmengine.client.webservices.dto.engine.management.Content;
//import it.doqui.index.ecmengine.client.webservices.engine.EcmEngineWebServiceDelegate;
//import it.doqui.index.ecmengine.client.webservices.engine.EcmEngineWebServiceDelegateServiceLocator;

import javax.activation.CommandMap;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.activation.MailcapCommandMap;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

	
public class ACTAService {

	private static Logger log = Logger.getLogger(ACTAConstants.LOGGER_PREFIX);
	//final static   SiceeorchSrv srvSiceeorch;

   public static ACTAServiceFactory factory = new ACTAServiceFactory();

	final static String SERVER = factory.getActaHost();
	final static int PORT = factory.getActaPort();
	final static String CONTEXT = "/"+factory.getActaContext();
	
	final static String SERVER_SICEEWS = factory.getSiceewsUrl();

	
	/** The Constant PD_SORCH_RES. */
	public final static String PD_SORCH_RES = "/META-INF/defpd_siceeorch.xml";

	protected static SiceewsMgrSoapBindingStub srvSiceews;

	public SiceeorchSrv getSiceeorchSrv() {
		log.debug("[ACTAService::getSiceeorchSrv] BEGIN");
		SiceeorchSrv srvSiceeorch = null;
		if (srvSiceeorch == null )
		{
			InputStream is = getClass().getResourceAsStream(PD_SORCH_RES);
			
			if (is != null) {
				try {

					InfoPortaDelegata info = PDConfigReader.read(is);
						
					srvSiceeorch = (SiceeorchSrv) PDProxy.newInstance(info);

					return srvSiceeorch;

				} 
				catch (Exception e) {
					log.error("[ACTAService::getSiceeorchSrv] errore nella parsificazione della configurazione di SICEEORCH:"+ e, e);
					throw new IllegalArgumentException("errore nella parsificazione della configurazione di SICEEORCH");
				}
			} else{
				log.error("[ACTAService::getSiceeorchSrv] configurazione di SICEEORCH non trovata");
			}

			throw new IllegalArgumentException("configurazione di SICEEORCH non trovata");
		}

		log.debug("[ACTAService::getSiceeorchSrv] END");

		return srvSiceeorch;

	}
	
	public SiceewsMgrSoapBindingStub getSiceewsSrv() {
		log.debug("[ACTAService::getSiceewsSrv] BEGIN");
		
		log.debug("Stampo srvSiceews: "+srvSiceews);
		
		if (srvSiceews == null )
		{
				try {

					SiceewsMgrLocator loc = new SiceewsMgrLocator();
					//+Constants.WS_SICEEWS_URL
					log.debug("SOAIntegrationMgr - PASSO 1: "+SERVER_SICEEWS);
					loc.setSiceewsMgrWSPortEndpointAddress(SERVER_SICEEWS);
					log.debug("SOAIntegrationMgr - PASSO 2");

					srvSiceews = (SiceewsMgrSoapBindingStub) loc.getSiceewsMgrWSPort();

					return srvSiceews;

				} 
				catch (Exception e) {
					log.error("[ACTAService::getSiceeorchSrv] errore nella parsificazione della configurazione di SICEEWS:"+ e, e);
					throw new IllegalArgumentException("errore nella parsificazione della configurazione di SICEEWS");
				}
				catch (Throwable t)
				{
					log.error("[ACTAService::getSiceeorchSrv] errore nella parsificazione della configurazione di SICEEWS:"+ t, t);
					throw new IllegalArgumentException("errore nella parsificazione della configurazione di SICEEWS");

				}

		}

		log.debug("[ACTAService::getSiceewsSrv] END");

		return srvSiceews;

	}
	
	public static ObjectIdType getRepository(String repositoryName) throws ACTAInvocationException {
		
		log.debug("[ACTAService::getRepository] BEGIN");
		ObjectIdType repositoryIdentificato = null;
		AcarisRepositoryEntryType[] elencoRepository = null;
		try {
			
			log.debug("[ACTAService::getRepository] - ACTA - stampo il server: "+SERVER);
			log.debug("[ACTAService::getRepository] - ACTA - stampo il context: "+CONTEXT);
			log.debug("[ACTAService::getRepository] - ACTA - stampo la porta: "+PORT);
			
			RepositoryServicePort repositoryService = AcarisServiceClient.getRepositoryServiceAPI(SERVER, CONTEXT, PORT);
			elencoRepository = repositoryService.getRepositories();
			
			log.debug("[ACTAService::getRepository] ho recuperato i repository: "+elencoRepository);

			
		} catch (it.doqui.acta.acaris.repositoryservice.AcarisException acEx) {
			log.error("[ACTAService::getRepository] acEx.getMessage(): " + acEx.getMessage());
			if(acEx.getFaultInfo() != null)
			{
				log.error("[ACTAService::getRepository] acEx.getFaultInfo().getErrorCode(): " + acEx.getFaultInfo().getErrorCode());
				log.error("[ACTAService::getRepository] acEx.getFaultInfo().getPropertyName(): " + acEx.getFaultInfo().getPropertyName());
				log.error("[ACTAService::getRepository] acEx.getFaultInfo().getObjectId(): " + acEx.getFaultInfo().getObjectId());
				log.error("[ACTAService::getRepository] acEx.getFaultInfo().getExceptionType(): " + acEx.getFaultInfo().getExceptionType());
				log.error("[ACTAService::getRepository] acEx.getFaultInfo().getClassName(): " + acEx.getFaultInfo().getClassName());
				log.error("[ACTAService::getRepository] acEx.getFaultInfo().getTechnicalInfo: " + acEx.getFaultInfo().getTechnicalInfo());
			}
			throw new ACTAInvocationException("Errore nel metodo getRepository: "+acEx.getMessage(), acEx);

		} catch (Exception e) {
			log.error("[ACTAService::getRepository] e.getMessage(): " + e.getMessage());
			
			throw new ACTAInvocationException("Errore GENERICO nel metodo getRepository", e);

		}

		for (AcarisRepositoryEntryType repository : elencoRepository) {
			 
			log.debug("[ACTAService::getRepository] repository:" + repository.getRepositoryName());
			
			 if (repository.getRepositoryName().startsWith(repositoryName, 0))
				repositoryIdentificato = repository.getRepositoryId();
		}

		log.debug("[ACTAService::getRepository] repositoryIdentificato: "+repositoryIdentificato.getValue());
		
		log.debug("[ACTAService::getRepository] END");

		return repositoryIdentificato;

	}
		
	public static PrincipalIdType getPrincipalIdAcaris(List<SiceeTParametriActa> paramActa ) throws ACTAInvocationException
	{
		
		log.debug("[ACTAService::getPrincipalIdAcaris] BEGIN");
		
		CodiceFiscaleType codFiscale = new CodiceFiscaleType();
		codFiscale.setValue(GenericUtil.recuperaValParametro(paramActa, ACTAConstants.COD_FISCALE));

		ClientApplicationInfo appKey = new ClientApplicationInfo();
		appKey.setAppKey(GenericUtil.recuperaValParametro(paramActa, ACTAConstants.APP_KEY));

		
		IdAOOType idAoo = new IdAOOType();
		idAoo.setValue(GenericUtil.convertToLong(GenericUtil.recuperaValParametro(paramActa, ACTAConstants.ID_AOO)));

		IdStrutturaType idStruttura = new IdStrutturaType();
		idStruttura.setValue(GenericUtil.convertToLong(GenericUtil.recuperaValParametro(paramActa, ACTAConstants.ID_STRUTTURA)));

		IdNodoType idNodo = new IdNodoType();
		idNodo.setValue(GenericUtil.convertToLong(GenericUtil.recuperaValParametro(paramActa, ACTAConstants.ID_NODO)));
		
		ObjectIdType repositoryId = new ObjectIdType();
		repositoryId.setValue(GenericUtil.recuperaValParametro(paramActa, ACTAConstants.REP_ID));
		
		PrincipalIdType principalId = null;
		
		
		// Genera Principal
		BackOfficeServicePort backOfficeServicePortWS = null;
		try {
			backOfficeServicePortWS = AcarisServiceClient.getBackofficeServiceAPI(SERVER, CONTEXT, PORT);

		
			PrincipalExtResponseType[] principal = backOfficeServicePortWS
					.getPrincipalExt(repositoryId, codFiscale, idAoo, idStruttura,
							idNodo, appKey);
			principalId = principal[0].getPrincipalId();
			log.debug("Principal: " + principalId.getValue());
		} catch (it.doqui.acta.acaris.backofficeservice.AcarisException acEx) {
			log.error("[ACTAService::getPrincipalIdAcaris] acEx.getMessage(): " + acEx.getMessage());
			if(acEx.getFaultInfo() != null)
			{
				log.error("[ACTAService::getPrincipalIdAcaris] acEx.getFaultInfo().getErrorCode(): " + acEx.getFaultInfo().getErrorCode());
				log.error("[ACTAService::getPrincipalIdAcaris] acEx.getFaultInfo().getPropertyName(): " + acEx.getFaultInfo().getPropertyName());
				log.error("[ACTAService::getPrincipalIdAcaris] acEx.getFaultInfo().getObjectId(): " + acEx.getFaultInfo().getObjectId());
				log.error("[ACTAService::getPrincipalIdAcaris] acEx.getFaultInfo().getExceptionType(): " + acEx.getFaultInfo().getExceptionType());
				log.error("[ACTAService::getPrincipalIdAcaris] acEx.getFaultInfo().getClassName(): " + acEx.getFaultInfo().getClassName());
			}
			throw new ACTAInvocationException("Errore nel metodo archiviaDocumento: "+acEx.getMessage(), acEx);
		}

		log.debug("[ACTAService::getPrincipalIdAcaris] END");

		return principalId;
	}
	
	public static ObjectIdType cercaCatener(ObjectIdType repositoryId, PrincipalIdType principalId) throws ACTAInvocationException
	{
		log.debug("[ACTAService::cercaCatener] BEGIN");
		
		ObjectIdType catenerRepId = null; 
		
		//PrincipalIdType principalId = getPrincipalIdAcaris();

		QueryableObjectType target = new QueryableObjectType();
		target.setObject(EnumObjectType.SERIE_TIPOLOGICA_DOCUMENTI_PROPERTIES_TYPE.value());

		QueryConditionType[] criteria = getCriteria(new EnumQueryOperator[] { EnumQueryOperator.LIKE },
				new String[] { "codice" }, new String[] { "CATENER*" });
		
		try
		{
			//PropertyFilterType filter = getPropertyFilter(EnumPropertyFilter.ALL, null, null, null);
			PropertyFilterType filter = getPropertyFilter(EnumPropertyFilter.LIST, new String[] { "" }, new String[] { "objectId" }, null);

			PagingResponseType pagingResponse = new PagingResponseType();
			
		
			ObjectServicePort objectService = AcarisServiceClient.getObjectServiceAPI(SERVER, CONTEXT, PORT, true); 

			// con queste impostazioni la paginazione e' gestita direttamente dal
			// sistema
			Integer maxItems = null;
			Integer skipCount = 0;
			
			log.debug("[ACTAService::cercaCatener] repositoryId: "+repositoryId.getValue());
			log.debug("[ACTAService::cercaCatener] principalId: "+principalId.getValue());
			log.debug("[ACTAService::cercaCatener] target: "+target.getObject());

			pagingResponse = objectService.query(repositoryId, principalId, target, filter, criteria, null, maxItems,
					skipCount);

			log.debug("[ACTAService::cercaCatener] pagingResponse: "+pagingResponse);
			log.debug("[ACTAService::cercaCatener] pagingResponse.getObjects(): "+pagingResponse.getObjects());
			log.debug("[ACTAService::cercaCatener] pagingResponse.getObjectsLength(): "+pagingResponse.getObjectsLength());
			
			//stampa(pagingResponse);
			
			if (pagingResponse.getObjectsLength() > 0)
			{
				catenerRepId = pagingResponse.getObjects(0).getObjectId();
			}

		} catch (it.doqui.acta.acaris.objectservice.AcarisException acEx) {
			// TODO Auto-generated catch block
			log.error("[ACTAService::cercaCatener] acEx.getMessage(): " + acEx.getMessage());
			if(acEx.getFaultInfo() != null)
			{
				log.error("[ACTAService::cercaCatener] acEx.getFaultInfo().getErrorCode(): " + acEx.getFaultInfo().getErrorCode());
				log.error("[ACTAService::cercaCatener] acEx.getFaultInfo().getPropertyName(): " + acEx.getFaultInfo().getPropertyName());
				log.error("[ACTAService::cercaCatener] acEx.getFaultInfo().getObjectId(): " + acEx.getFaultInfo().getObjectId());
				log.error("[ACTAService::cercaCatener] acEx.getFaultInfo().getExceptionType(): " + acEx.getFaultInfo().getExceptionType());
				log.error("[ACTAService::cercaCatener] acEx.getFaultInfo().getClassName(): " + acEx.getFaultInfo().getClassName());
			}
			throw new ACTAInvocationException("Errore nel metodo archiviaDocumento: "+acEx.getMessage(), acEx);

		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
			
			throw new ACTAInvocationException("Errore GENERICO nel metodo archiviaDocumento", e);

		}
		
		log.debug("[ACTAService::cercaCatener] END");

		return catenerRepId;

	}
	
	public static ObjectIdType cercaVolume(ObjectIdType repositoryId, PrincipalIdType principalId, ObjectIdType catenerRepId, String volume) throws ACTAInvocationException
	{
		log.debug("[ACTAService::cercaVolume] BEGIN");

		ObjectIdType volumeRepId = null; 
		QueryableObjectType target = new QueryableObjectType();
		target.setObject(EnumObjectType.VOLUME_SERIE_TIPOLOGICA_DOCUMENTI_PROPERTIES_TYPE.value());
		
		
		QueryConditionType[] criteria = getCriteria(new EnumQueryOperator[] { EnumQueryOperator.EQUALS },
				new String[] { "descrizione" }, new String[] { volume });
		
		PropertyFilterType filter = getPropertyFilter(EnumPropertyFilter.LIST, new String[] { "" }, new String[] { "objectId" }, null);

		PagingResponseType pagingResponse = new PagingResponseType();
		
		try
		{
			ObjectServicePort objectService = AcarisServiceClient.getObjectServiceAPI(SERVER, CONTEXT, PORT, true); 

			// con queste impostazioni la paginazione e' gestita direttamente dal
			// sistema
			Integer maxItems = null;
			Integer skipCount = 0;
			
			NavigationConditionInfoType navigation = new NavigationConditionInfoType();
			navigation.setParentNodeId(catenerRepId);
			navigation.setLimitToChildren(true);
			
			pagingResponse = objectService.query(repositoryId, principalId, target, filter, criteria, navigation, maxItems,
					skipCount);

			log.debug("[ACTAService::cercaVolume] pagingResponse: "+pagingResponse);
			log.debug("[ACTAService::cercaVolume] pagingResponse.getObjects(): "+pagingResponse.getObjects());
			log.debug("[ACTAService::cercaVolume] pagingResponse.getObjectsLength(): "+pagingResponse.getObjectsLength());
			
			if (pagingResponse.getObjectsLength() > 0)
			{
				volumeRepId = pagingResponse.getObjects(0).getObjectId();
			}

		} catch (it.doqui.acta.acaris.objectservice.AcarisException acEx) {
			// TODO Auto-generated catch block
			log.error("[ACTAService::cercaVolume] acEx.getMessage(): " + acEx.getMessage());
			if(acEx.getFaultInfo() != null)
			{
				log.error("[ACTAService::cercaVolume] acEx.getFaultInfo().getErrorCode(): " + acEx.getFaultInfo().getErrorCode());
				log.error("[ACTAService::cercaVolume] acEx.getFaultInfo().getPropertyName(): " + acEx.getFaultInfo().getPropertyName());
				log.error("[ACTAService::cercaVolume] acEx.getFaultInfo().getObjectId(): " + acEx.getFaultInfo().getObjectId());
				log.error("[ACTAService::cercaVolume] acEx.getFaultInfo().getExceptionType(): " + acEx.getFaultInfo().getExceptionType());
				log.error("[ACTAService::cercaVolume] acEx.getFaultInfo().getClassName(): " + acEx.getFaultInfo().getClassName());
			}			
			throw new ACTAInvocationException("Errore nel metodo cercaVolume: "+acEx.getMessage(), acEx);

		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
			
			throw new ACTAInvocationException("Errore GENERICO nel metodo cercaVolume", e);

		}
		
		log.debug("[ACTAService::cercaVolume] END");

		return volumeRepId;

	}
	
	public static void chiudiVolume(ObjectIdType repositoryId, PrincipalIdType principalId, ObjectIdType volumeRepId) throws ACTAInvocationException
	{
		log.debug("[ACTAService::chiudiVolume] BEGIN");
		try
		{
			ObjectServicePort objectService = AcarisServiceClient.getObjectServiceAPI(SERVER, CONTEXT, PORT, true); 
			
			SimpleResponseType ret = objectService.closeFolder(repositoryId, volumeRepId, principalId);

			log.debug("[ACTAService::chiudiVolume] ret: "+ret);
			log.debug("[ACTAService::chiudiVolume] ret.getValue(): "+ret.getChangeToken().getValue()); // Orario di chiusura del volume


		} catch (it.doqui.acta.acaris.objectservice.AcarisException acEx) {
			// TODO Auto-generated catch block
			log.error("[ACTAService::chiudiVolume] acEx.getMessage(): " + acEx.getMessage());
			if(acEx.getFaultInfo() != null)
			{
				log.error("[ACTAService::chiudiVolume] acEx.getFaultInfo().getErrorCode(): " + acEx.getFaultInfo().getErrorCode());
				log.error("[ACTAService::chiudiVolume] acEx.getFaultInfo().getPropertyName(): " + acEx.getFaultInfo().getPropertyName());
				log.error("[ACTAService::chiudiVolume] acEx.getFaultInfo().getObjectId(): " + acEx.getFaultInfo().getObjectId());
				log.error("[ACTAService::chiudiVolume] acEx.getFaultInfo().getExceptionType(): " + acEx.getFaultInfo().getExceptionType());
				log.error("[ACTAService::chiudiVolume] acEx.getFaultInfo().getClassName(): " + acEx.getFaultInfo().getClassName());
			}
			throw new ACTAInvocationException("Errore nel metodo chiudiVolume: "+acEx.getMessage(), acEx);
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
			throw new ACTAInvocationException("Errore GENERICO nel metodo chiudiVolume", e);

		}
		
		log.debug("[ACTAService::chiudiVolume] END");

		//return isVolumePresente;

	}
	
	public static ObjectIdType creaVolume(ObjectIdType repositoryId, PrincipalIdType principalId, ObjectIdType catenerRepId, String volume) throws ACTAInvocationException
	{
		log.debug("[ACTAService::creaVolume] BEGIN");
		
		ObjectIdType ret = null;
		try
		{
			ObjectServicePort objectService = AcarisServiceClient.getObjectServiceAPI(SERVER, CONTEXT, PORT, true); 
			
			VolumeSerieTipologicaDocumentiPropertiesType properties = new VolumeSerieTipologicaDocumentiPropertiesType();

			properties.setDescrizione(volume);
			properties.setArchivioCorrente(true);
			properties.setDatiPersonali(true);
			properties.setDatiRiservati(false);
			properties.setDatiSensibili(false);
			//properties.setParoleChiave(volume);
			properties.setDataCreazione(new Date());
			properties.setParentId(repositoryId);
			properties.setConservazioneCorrente(5);
			properties.setConservazioneGenerale(10);
			
			ret = objectService.createFolder(repositoryId, EnumFolderObjectType.VOLUME_SERIE_TIPOLOGICA_DOCUMENTI_PROPERTIES_TYPE, principalId, properties, catenerRepId);

			log.debug("[ACTAService::creaVolume] ret: "+ret);
			log.debug("[ACTAService::creaVolume] ret.getValue(): "+ret.getValue());


		} catch (it.doqui.acta.acaris.objectservice.AcarisException acEx) {
			// TODO Auto-generated catch block
			log.error("[ACTAService::creaVolume] acEx.getMessage(): " + acEx.getMessage());
			if(acEx.getFaultInfo() != null)
			{
				log.error("[ACTAService::creaVolume] acEx.getFaultInfo().getErrorCode(): " + acEx.getFaultInfo().getErrorCode());
				log.error("[ACTAService::creaVolume] acEx.getFaultInfo().getPropertyName(): " + acEx.getFaultInfo().getPropertyName());
				log.error("[ACTAService::creaVolume] acEx.getFaultInfo().getObjectId(): " + acEx.getFaultInfo().getObjectId());
				log.error("[ACTAService::creaVolume] acEx.getFaultInfo().getExceptionType(): " + acEx.getFaultInfo().getExceptionType());
				log.error("[ACTAService::creaVolume] acEx.getFaultInfo().getClassName(): " + acEx.getFaultInfo().getClassName());
			}
			throw new ACTAInvocationException("Errore nel metodo creaVolume: "+acEx.getMessage(), acEx);
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
			throw new ACTAInvocationException("Errore GENERICO nel metodo creaVolume", e);

		}
		
		log.debug("[ACTAService::chiudiVolume] END");

		return ret;

	}
	
	public static PagingResponseType cercaDocumentoArchiviatoByCertificato(ObjectIdType catenerRepId, ObjectIdType volumeId, ObjectIdType repositoryId, PrincipalIdType principalId, Certificato certificato) throws ACTAInvocationException {

		
		log.info("[ACTAService::cercaDocumentoArchiviatoByCertificato] BEGIN");
		
		log.info("[ACTAService::cercaDocumentoArchiviatoByCertificato] catenerRepId: "+catenerRepId);
		log.info("[ACTAService::cercaDocumentoArchiviatoByCertificato] volumeId: "+volumeId);

		PagingResponseType response = cercaDocumentoArchiviato(volumeId, repositoryId, principalId, certificato, null);

		/*
		log.info("[ACTAService::cercaDocumentoArchiviatoByCertificato] Risultato ricerca x volumeId: : "+response);

		log.info("[ACTAService::cercaDocumentoArchiviatoByCertificato] Risultato response.getObjectsLength(): : "+response.getObjectsLength());

		log.info("[ACTAService::cercaDocumentoArchiviatoByCertificato] Stampo responde - BEGIN");

		stampa(response);
		
		log.info("[ACTAService::cercaDocumentoArchiviatoByCertificato] Stampo responde - END");

		if (response.getObjectsLength() == 0)
		{
			response = cercaDocumentoArchiviato(catenerRepId, repositoryId, principalId, certificato, null);

			log.info("[ACTAService::cercaDocumentoArchiviatoByCertificato] Risultato ricerca x catenerRepId: : "+response);
		}
		*/
		
		log.info("[ACTAService::cercaDocumentoArchiviatoByCertificato] END");
		
		return response;
	}
	
	public static PagingResponseType cercaDocumentoArchiviatoByIdDoc(ObjectIdType catenerRepId, ObjectIdType repositoryId, PrincipalIdType principalId, String idDocumento) throws ACTAInvocationException {

		return cercaDocumentoArchiviato(catenerRepId, repositoryId, principalId, null, idDocumento);
		
	}
	
	private static PagingResponseType cercaDocumentoArchiviato(ObjectIdType parentId, ObjectIdType repositoryId, PrincipalIdType principalId, Certificato certificato, String idDocumento) throws ACTAInvocationException {

		log.debug("[ACTAService::cercaDocumentoArchiviato] BEGIN");

		PagingResponseType pagingResponse = null;
		try
		{
			ObjectServicePort objectService = AcarisServiceClient.getObjectServiceAPI(SERVER, CONTEXT, PORT, true); 

		QueryableObjectType query = new QueryableObjectType();
		query.setObject(EnumObjectType.DOCUMENTO_SEMPLICE_PROPERTIES_TYPE.value());
		
		String propertyName = null;
		String valore = null;
		
		if (certificato != null)
		{
			propertyName = "oggetto";
			valore = certificato.getAnno() + " " + certificato.getNumCertificatore() + " " + certificato.getProgrCertificato();
		}
		else if (idDocumento != null)
		{
			propertyName = "objectId";
			valore = idDocumento;
		}
		
		QueryConditionType[] criteria = getCriteria(new EnumQueryOperator[] { EnumQueryOperator.EQUALS },
				new String[] { propertyName }, new String[] { valore });
		
		PropertyFilterType filter = getPropertyFilter(EnumPropertyFilter.LIST, new String[] { "","" }, new String[] { "objectId","idProtocolloList" }, null);
		
		
//		  catenerRepId = new ObjectIdType();
//		  catenerRepId.setValue(GenericUtil.recuperaValParametro(paramActa, ACTAConstants.ID_SERIE_CATENER));
		NavigationConditionInfoType nav = new NavigationConditionInfoType();
		
		nav.setParentNodeId(parentId);
		nav.setLimitToChildren(false);
		
		
		pagingResponse = objectService.query(repositoryId, principalId, query, filter, criteria, nav, null, null);
		
		/*
		if (pagingResponse.getObjectsLength() > 0)
		{
			stampa(pagingResponse);
		}
		*/
		
		} catch (it.doqui.acta.acaris.objectservice.AcarisException acEx) {
			log.error("[ACTAService::cercaDocumentoArchiviato] acEx.getMessage(): " + acEx.getMessage());
			if(acEx.getFaultInfo() != null)
			{
				log.error("[ACTAService::cercaDocumentoArchiviato] acEx.getFaultInfo().getErrorCode(): " + acEx.getFaultInfo().getErrorCode());
				log.error("[ACTAService::cercaDocumentoArchiviato] acEx.getFaultInfo().getPropertyName(): " + acEx.getFaultInfo().getPropertyName());
				log.error("[ACTAService::cercaDocumentoArchiviato] acEx.getFaultInfo().getObjectId(): " + acEx.getFaultInfo().getObjectId());
				log.error("[ACTAService::cercaDocumentoArchiviato] acEx.getFaultInfo().getExceptionType(): " + acEx.getFaultInfo().getExceptionType());
				log.error("[ACTAService::cercaDocumentoArchiviato] acEx.getFaultInfo().getClassName(): " + acEx.getFaultInfo().getClassName());
			}			
			throw new ACTAInvocationException("Errore nell'esecuzione della query richiamo di cercaDocumentoArchiviato: "+acEx.getMessage(), acEx);
		}

		log.debug("[ACTAService::cercaDocumentoArchiviato] END");

		return pagingResponse;
	}
	
	public static IdentificatoreDocumento archiviaDocumento(ObjectIdType repositoryId, PrincipalIdType principalId, ObjectIdType parentFolder, Certificato certificato, List<SiceeTParametriActa> paramActa, byte[] file) throws ACTAInvocationException {

		log.debug("[ACTAService::archiviaDocumento] BEGIN");

		
		/**********************************************************************************************************
		 * principal part228
		 *********************************************************************************************************/
		IdentificatoreDocumento identDoc = null;
		//ObjectIdType documentId = null;
		DocumentoArchivisticoIRC datiCreazione = new DocumentoArchivisticoIRC();
		try {
			
			DocumentServicePort documentService = AcarisServiceClient.getDocumentServiceAPI(SERVER, CONTEXT, PORT, true);

			datiCreazione.setParentFolderId(parentFolder);
			AcarisContentStreamType contentStream = null;
			
			log.debug("Stampo file: "+file);
			log.debug("Stampo certificato.getNomeFile(): "+certificato.getNomeFile());
			
			if (file != null)
			{
				contentStream = creaContentStream(file, certificato.getNomeFile(), "p7m", EnumMimeTypeType.APPLICATION_PKCS_7_MIME);
			}

			String codiceIdentificativo = certificato.getAnno() + " " + certificato.getNumCertificatore() + " " + certificato.getProgrCertificato();
			String firmaApe = certificato.getCognomeCertificatore() + " " + certificato.getNomeCertificatore() + " N. " + certificato.getNumCertificatore();
			String cfFirmaApe = certificato.getCfCertificatore();
			
			// Dati di Classificazione
			ClassificazionePropertiesType associativeObjectProperties = new ClassificazionePropertiesType();
			//associativeObjectProperties.setCollocazioneCartacea("NO"); 
			associativeObjectProperties.setCartaceo(false);
			associativeObjectProperties.setCopiaCartacea(false);
			datiCreazione.setPropertiesClassificazione(associativeObjectProperties);

			// Dati Principali
			
			DocumentoSemplicePropertiesType properties = new DocumentoSemplicePropertiesType();
			datiCreazione.setTipoDocumento(EnumTipoDocumentoArchivistico.DOCUMENTO_SEMPLICE); 
			properties.setOggetto("APE: "+codiceIdentificativo);

			properties.setOrigineInterna(false);
			//properties.setPresenzaFile(true);
			
			properties.setDatiPersonali(true);
			properties.setDatiRiservati(false);
			properties.setDatiSensibili(false);
			
			properties.setParoleChiave(certificato.getIdComune() + "-" + certificato.getAnnoCostruzione() + "-" +certificato.getDestUso() + "-" +GenericUtil.convertToString(certificato.getDtScadenza()) + "-" +certificato.getClasse());

			properties.setRegistrato(true);
			properties.setModificabile(false);
			properties.setDefinitivo(true);
			
			properties.setDataCreazione(certificato.getDtConsolidamento());
			//properties.set
			
			
			// Dati di Identita'
			String[] autore = { firmaApe};
			//properties.setAutoreGiuridico(autore);
			properties.setAutoreFisico(autore);
			//properties.setScrittore(autore);
			//properties.setOriginatore(autore);

			String[] destinatario = { ACTAConstants.REGIONE_PIEMONTE_DESC};
			properties.setDestinatarioGiuridico(destinatario);
			
			//properties.setDestinatarioFisico(autore);
			
			//properties.setDataDocTopica(ACTAConstants.COMUNE_DESC);
			
			properties.setDataDocCronica(certificato.getDtConsolidamento());
			

			properties.setApplicativoAlimentante(ACTAConstants.COD_APPLICATIVO);
			properties.setNumRepertorio(codiceIdentificativo);

			properties.setDocConAllegati(false);

			properties.setDocAutenticato(false);
			properties.setDocAutenticatoFirmaAutenticata(false);
			properties.setDocAutenticatoCopiaAutentica(false);
			
			String[] soggProduttore = { firmaApe + " CF:" + cfFirmaApe};
			properties.setSoggettoProduttore(soggProduttore);
			
			IdFormaDocumentariaType idFormaDoc = new IdFormaDocumentariaType();
			// valore indicato in sede di analisi 
			idFormaDoc.setValue(GenericUtil.convertToLong(GenericUtil.recuperaValParametro(paramActa, ACTAConstants.IDFORMADOCUMENTARIA)));
			properties.setIdFormaDocumentaria(idFormaDoc);
			
			// Dati di Integrita'

			IdVitalRecordCodeType idVrc = new IdVitalRecordCodeType();
			idVrc.setValue(GenericUtil.convertToLong(GenericUtil.recuperaValParametro(paramActa, ACTAConstants.IDVITALRECORDCODE)));// valore indicato in sede di analisi archivistica
			properties.setIdVitalRecordCode(idVrc);
			
			// Conservazione sostitutiva
			properties.setDaConservare(true);
			properties.setProntoPerConservazione(false);

			// Protocollo

			
			// Documento elettronico
			IdStatoDiEfficaciaType idStatoE = new IdStatoDiEfficaciaType();
			idStatoE.setValue(GenericUtil.convertToLong(GenericUtil.recuperaValParametro(paramActa, ACTAConstants.IDSTATOEFFICACIA))); //valore indicato in sede di analisi
			// archivistica
			properties.setIdStatoDiEfficacia(idStatoE);
			
			properties.setTipoDocFisico(EnumTipoDocumentoType.FIRMATO);
			
			properties.setComposizione(EnumDocPrimarioType.DOCUMENTO_SINGOLO);
			properties.setMultiplo(false);
			
			properties.setAnalogico(false);



			//properties.setContentStreamFilename("AllegatoTecnicoAcarisEstrattoServizi");



			// properties.setTipoDocFisico(EnumTipoDocumentoType.FIRMATO);
			// properties.setCodBarre("codiceBARRE");

			

			// associativeObjectProperties.setNumeroInput("1234124");
			// per doc elettronico
			properties.setRappresentazioneDigitale(true); 

			datiCreazione.setPropertiesDocumento(properties);

			
			DocumentoFisicoIRC[] documenti = new DocumentoFisicoIRC[1];
			documenti[0] = new DocumentoFisicoIRC();
			DocumentoFisicoPropertiesType documentoFisicoProperty = new DocumentoFisicoPropertiesType();
			documentoFisicoProperty.setDescrizione(certificato.getNomeFile()); 
			documentoFisicoProperty.setDataMemorizzazione(new Date());
			//documentoFisicoProperty.setDocMimeTypes(EnumMimeTypeType.APPLICATION_PKCS_7_MIME.value());
			//documentoFisicoProperty.setDocMimeTypes("90");// in base al tipo di // ??????
															// documento
															// inserito
			documentoFisicoProperty.setProgressivoPerDocumento(1);
			documenti[0].setPropertiesDocumentoFisico(documentoFisicoProperty);
			ContenutoFisicoIRC[] contenuti = new ContenutoFisicoIRC[1];
			contenuti[0] = new ContenutoFisicoIRC();
			ContenutoFisicoPropertiesType contenutoFisicoPropertiesType = new ContenutoFisicoPropertiesType();
			contenutoFisicoPropertiesType.setSbustamento(true);
			contenuti[0].setPropertiesContenutoFisico(contenutoFisicoPropertiesType);
			contenuti[0].setStream(contentStream);
			// contenuti[0].setTipo(EnumStreamId.SIGNATURE);
			contenuti[0].setTipo(EnumStreamId.PRIMARY);
			documenti[0].setContenutiFisici(contenuti);
			documenti[0].setAzioniVerificaFirma(getDatiTestAzioniVerificaFirma());

			datiCreazione.setDocumentiFisici(documenti);
			
			
			EnumTipoOperazione tipoOperazione = EnumTipoOperazione.ELETTRONICO;
			
			
			identDoc = documentService.creaDocumento(repositoryId, principalId, tipoOperazione,
					datiCreazione);
			
			/*
			if (identDoc != null) {
				stampa(identDoc);
			}
			*/
			
			
		} 
		catch (it.doqui.acta.acaris.documentservice.AcarisException acEx) {
			log.error("[ACTAService::archiviaDocumento] acEx.getMessage(): " + acEx.getMessage());
			if(acEx.getFaultInfo() != null)
			{
				log.error("[ACTAService::archiviaDocumento] acEx.getFaultInfo().getErrorCode(): " + acEx.getFaultInfo().getErrorCode());
				log.error("[ACTAService::archiviaDocumento] acEx.getFaultInfo().getPropertyName(): " + acEx.getFaultInfo().getPropertyName());
				log.error("[ACTAService::archiviaDocumento] acEx.getFaultInfo().getObjectId(): " + acEx.getFaultInfo().getObjectId());
				log.error("[ACTAService::archiviaDocumento] acEx.getFaultInfo().getExceptionType(): " + acEx.getFaultInfo().getExceptionType());
				log.error("[ACTAService::archiviaDocumento] acEx.getFaultInfo().getClassName(): " + acEx.getFaultInfo().getClassName());
			}
			throw new ACTAInvocationException("Errore nel metodo archiviaDocumento: "+acEx.getMessage(), acEx);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ACTAInvocationException("Errore GENERICO nel metodo archiviaDocumento", e);

		} 

		
		log.debug("[ACTAService::archiviaDocumento] END");

		return identDoc;

	}
	
	public static String cercaDocumentoClassificazioneByIdDoc(ObjectIdType repositoryId, PrincipalIdType principalId, String idDocumento) throws ACTAInvocationException {

		log.debug("[ACTAService::cercaDocumentoClassificazioneByIdDoc] BEGIN");

		String idClassificazione = null;
		try
		{
			NavigationServicePort objectNavigationService = AcarisServiceClient.getNavigationServiceAPI(SERVER, CONTEXT, PORT); 


			PropertyFilterType filter = getPropertyFilter(EnumPropertyFilter.ALL, null, null, null);

			ObjectResponseType[] objectResponse = null;


//				ObjectIdType repositoryId: Repository e quindi ente di riferimento.
//				PrincipalIdType principalId: Utente che richiede il servizio.
//				ObjectIdType rootNodeId: identificatore dell'unita' organizzativa a partire dalla quale iniziare la navigazione. 
//				date date: serve per limitare la navigazione alle sole unita' attive (valide) alla data passata come parametro.
//				PropertyFilterType filter: permette di indicare tipologie di unita' organizzative e proprieta' da restituire

			ObjectIdType objectIdDocumentoDoc = new ObjectIdType();
			objectIdDocumentoDoc.setValue(idDocumento);
			objectResponse = objectNavigationService.getObjectParents(repositoryId, objectIdDocumentoDoc, principalId, filter);
			
			//stampa(objectResponse);
			
			if (objectResponse != null && objectResponse[0] != null)
			{
				idClassificazione = objectResponse[0].getObjectId().getValue();
			}
			
		} catch (it.doqui.acta.acaris.navigationservice.AcarisException acEx) {
			log.error("[ACTAService::cercaDocumentoClassificazioneByIdDoc] acEx.getMessage(): " + acEx.getMessage());
			if(acEx.getFaultInfo() != null)
			{
				log.error("[ACTAService::cercaDocumentoClassificazioneByIdDoc] acEx.getFaultInfo().getErrorCode(): " + acEx.getFaultInfo().getErrorCode());
				log.error("[ACTAService::cercaDocumentoClassificazioneByIdDoc] acEx.getFaultInfo().getPropertyName(): " + acEx.getFaultInfo().getPropertyName());
				log.error("[ACTAService::cercaDocumentoClassificazioneByIdDoc] acEx.getFaultInfo().getObjectId(): " + acEx.getFaultInfo().getObjectId());
				log.error("[ACTAService::cercaDocumentoClassificazioneByIdDoc] acEx.getFaultInfo().getExceptionType(): " + acEx.getFaultInfo().getExceptionType());
				log.error("[ACTAService::cercaDocumentoClassificazioneByIdDoc] acEx.getFaultInfo().getClassName(): " + acEx.getFaultInfo().getClassName());
			}			
			throw new ACTAInvocationException("Errore nel metodo cercaDocumentoClassificazioneByIdDoc: "+acEx.getMessage(), acEx);
		}
		log.debug("[ACTAService::cercaDocumentoClassificazioneByIdDoc] END");

		return idClassificazione;
	}
	
	public static IdentificazioneRegistrazione protocollaDocumento(ObjectIdType repositoryId, PrincipalIdType principalId, Certificato certificato, String idClassificazione, List<SiceeTParametriActa> paramActa) throws ACTAInvocationException {

		log.debug("[ACTAService::protocollaDocumento] BEGIN");

		IdentificazioneRegistrazione idenReg = null;
		try
		{
			String codiceIdentificativo = certificato.getAnno() + " " + certificato.getNumCertificatore() + " " + certificato.getProgrCertificato();

			OfficialBookServicePort	bookService = AcarisServiceClient.getOfficialBookServiceAPI(SERVER, CONTEXT, PORT);

			EnumTipoRegistrazioneDaCreare tipoReg = EnumTipoRegistrazioneDaCreare.PROTOCOLLAZIONE_DOCUMENTO_ESISTENTE;

			ProtocollazioneDocumentoEsistente proDocEs = new ProtocollazioneDocumentoEsistente();

			ObjectIdType aooProtId = new ObjectIdType();
			aooProtId.setValue(AcarisUtils.createObjectId(EnumBackOfficeObjectType.AOO_PROPERTIES_TYPE.value(), GenericUtil.recuperaValParametro(paramActa, ACTAConstants.ID_AOO)));
			proDocEs.setAooProtocollanteId(aooProtId);

			RegistrazioneArrivo regApi = new RegistrazioneArrivo();

			MittenteEsterno mitt = new MittenteEsterno();
			InfoCreazioneCorrispondente infoCorr = new InfoCreazioneCorrispondente();
			infoCorr.setCognome(certificato.getCognomeCertificatore());
			infoCorr.setNome(certificato.getNomeCertificatore());

			mitt.setCorrispondente(infoCorr);
			MittenteEsterno[] mittEsterni = new MittenteEsterno[1];
			mittEsterni[0] = mitt;
			regApi.setMittenteEsterno(mittEsterni);

			regApi.setTipoRegistrazione(EnumTipoAPI.ARRIVO);

			InfoCreazioneRegistrazione creaReg = new InfoCreazioneRegistrazione();

			DestinatarioInterno[] destInterni = new DestinatarioInterno[1];
			DestinatarioInterno destInterno = new DestinatarioInterno();
			InfoCreazioneCorrispondente corrispondente = new  InfoCreazioneCorrispondente();

			RiferimentoSoggettoEsistente rifSogEs = new RiferimentoSoggettoEsistente();
			ObjectIdType idNodo = new ObjectIdType();
			//idNodo.setValue(ID_NODO+"");
			idNodo.setValue(AcarisUtils.createObjectId(EnumBackOfficeObjectType.NODO_PROPERTIES_TYPE.value(),GenericUtil.recuperaValParametro(paramActa, ACTAConstants.ID_NODO)));
			rifSogEs.setSoggettoId(idNodo);
			rifSogEs.setTipologia(EnumTipologiaSoggettoAssociato.NODO);
			corrispondente.setInfoSoggettoAssociato(rifSogEs);

			corrispondente.setDenominazione("Responsabile");
			destInterno.setCorrispondente(corrispondente);
			destInterno.setIdRuoloCorrispondente(GenericUtil.convertToLong(GenericUtil.recuperaValParametro(paramActa, ACTAConstants.IDRUOLOCORRISPONDENTE))); 

			destInterni[0] = destInterno;
			creaReg.setDestinatarioInterno(destInterni);


			creaReg.setDataDocumento(certificato.getDtConsolidamento());
			//creaReg.setdataRicezione // Questo campo non lo vedo 

			creaReg.setMezzoTrasmissivoId(GenericUtil.convertToLong(GenericUtil.recuperaValParametro(paramActa, ACTAConstants.IDMEZZOTRASMISSIVO)));
			creaReg.setDocumentoRiservato(false);
			creaReg.setRegistrazioneRiservata(false);
			creaReg.setForzareSeRegistrazioneSimile(true);
			
			creaReg.setOggetto("APE: "+codiceIdentificativo);

			IdentificazioneProtocollante identProt = new IdentificazioneProtocollante();
			ObjectIdType idStrutt = new ObjectIdType();
			//idStrutt.setValue(ID_STRUTTURA+"");
			idStrutt.setValue(AcarisUtils.createObjectId(EnumBackOfficeObjectType.STRUTTURA_PROPERTIES_TYPE.value(), GenericUtil.recuperaValParametro(paramActa, ACTAConstants.ID_STRUTTURA)));


			identProt.setNodoId(idNodo);
			identProt.setStrutturaId(idStrutt);


			creaReg.setProtocollante(identProt);

			regApi.setInfoCreazione(creaReg);


			proDocEs.setRegistrazioneAPI(regApi);
			ObjectIdType objectIdClassificazione = new ObjectIdType();
			objectIdClassificazione.setValue(idClassificazione);
			proDocEs.setClassificazioneId(objectIdClassificazione);
			proDocEs.setSenzaCreazioneSoggettiEsterni(true);

			idenReg = bookService.creaRegistrazione(repositoryId, principalId, tipoReg, proDocEs);

		} catch (it.doqui.acta.acaris.officialbookservice.AcarisException acEx) {
			log.error("[ACTAService::protocollaDocumento] acEx.getMessage(): " + acEx.getMessage());
			if(acEx.getFaultInfo() != null)
			{
				log.error("[ACTAService::protocollaDocumento] acEx.getFaultInfo().getErrorCode(): " + acEx.getFaultInfo().getErrorCode());
				log.error("[ACTAService::protocollaDocumento] acEx.getFaultInfo().getPropertyName(): " + acEx.getFaultInfo().getPropertyName());
				log.error("[ACTAService::protocollaDocumento] acEx.getFaultInfo().getObjectId(): " + acEx.getFaultInfo().getObjectId());
				log.error("[ACTAService::protocollaDocumento] acEx.getFaultInfo().getExceptionType(): " + acEx.getFaultInfo().getExceptionType());
				log.error("[ACTAService::protocollaDocumento] acEx.getFaultInfo().getClassName(): " + acEx.getFaultInfo().getClassName());
			}
			throw new ACTAInvocationException("Errore nel metodo protocollaDocumento: "+acEx.getMessage(), acEx);

		}

		/*
		if (idenReg != null) {
			stampa(idenReg);
		}
		*/
		
		log.debug("[ACTAService::protocollaDocumento] END");

		return idenReg;

	}
	
	
	public static PagingResponseType cercaProtocollo(ObjectIdType repositoryId, PrincipalIdType principalId, String idProtocollazione) throws ACTAInvocationException {

		log.debug("[ACTAService::cercaProtocollo] BEGIN");

		PagingResponseType pagingResponse = null;
		try
		{
			ObjectServicePort objectService = AcarisServiceClient.getObjectServiceAPI(SERVER, CONTEXT, PORT, true); 

		QueryableObjectType query = new QueryableObjectType();
		//query.setObject(EnumObjectType.PROTOCOLLO_PROPERTIES_TYPE.value());
		query.setObject(EnumObjectType.PROTOCOLLO_PROPERTIES_TYPE.value());


		QueryConditionType[] criteria = getCriteria(new EnumQueryOperator[] { EnumQueryOperator.EQUALS },
				new String[] { "objectId" }, new String[] { idProtocollazione });
		
		PropertyFilterType filter = getPropertyFilter(EnumPropertyFilter.LIST, new String[] { "","" }, new String[] { "idRegistrazione","dataProtocollo" }, null);
		
		pagingResponse = objectService.query(repositoryId, principalId, query, filter, criteria, null, null, null);
		
		/*
		if (pagingResponse.getObjectsLength() > 0)
		{
			stampa(pagingResponse);
		}
		*/
		
		} catch (it.doqui.acta.acaris.objectservice.AcarisException acEx) {
			log.error("[ACTAService::cercaProtocollo] acEx.getMessage(): " + acEx.getMessage());
			if(acEx.getFaultInfo() != null)
			{
				log.error("[ACTAService::cercaProtocollo] acEx.getFaultInfo().getErrorCode(): " + acEx.getFaultInfo().getErrorCode());
				log.error("[ACTAService::cercaProtocollo] acEx.getFaultInfo().getPropertyName(): " + acEx.getFaultInfo().getPropertyName());
				log.error("[ACTAService::cercaProtocollo] acEx.getFaultInfo().getObjectId(): " + acEx.getFaultInfo().getObjectId());
				log.error("[ACTAService::cercaProtocollo] acEx.getFaultInfo().getExceptionType(): " + acEx.getFaultInfo().getExceptionType());
				log.error("[ACTAService::cercaProtocollo] acEx.getFaultInfo().getClassName(): " + acEx.getFaultInfo().getClassName());
			}			
			throw new ACTAInvocationException("Errore nel metodo cercaProtocolloNew: "+acEx.getMessage(), acEx);

		}

		log.debug("[ACTAService::cercaProtocollo] END");

		return pagingResponse;
	}
	
	private static it.doqui.acta.actasrv.dto.acaris.type.document.StepErrorAction[] getDatiTestAzioniVerificaFirma() {

		it.doqui.acta.actasrv.dto.acaris.type.document.StepErrorAction[] azioniVerificaFirma = new it.doqui.acta.actasrv.dto.acaris.type.document.StepErrorAction[7];
		for (int i = 0; i < 7; i++) {
			azioniVerificaFirma[i] = new it.doqui.acta.actasrv.dto.acaris.type.document.StepErrorAction();
			azioniVerificaFirma[i].setAction(EnumStepErrorAction.EXCEPTION);
			azioniVerificaFirma[i].setStep(i + 1);
		}

		return azioniVerificaFirma;
	}
	
	private static AcarisContentStreamType creaContentStream(byte[] fileByte, final String fileName,
			final String estensioneFile, EnumMimeTypeType mimeType) throws IOException {
		AcarisContentStreamType contentStream = new AcarisContentStreamType();
		//byte[] stream = getBytesFromFile(filePath + fileName);
		final InputStream iS = new ByteArrayInputStream(fileByte);
		final OutputStream oS = new ByteArrayOutputStream(fileByte.length);

		javax.activation.DataSource a = new javax.activation.DataSource() {

			public OutputStream getOutputStream() throws IOException {
				return oS;
			}

			public String getName() {
				return fileName;
			}

			public InputStream getInputStream() throws IOException {
				return iS;
			}

			public String getContentType() {
				return estensioneFile;
			}
		};
		contentStream.setStreamMTOM(new DataHandler(a));
		// contentStream.setStream(stream);
		contentStream.setFilename(a.getName());
		contentStream.setMimeType(mimeType);
		return contentStream;
	}
	

	private static AcarisContentStreamType creaContentStreamOld(String filePath, final String fileName,
			final String estensioneFile, EnumMimeTypeType mimeType) throws IOException {
		AcarisContentStreamType contentStream = new AcarisContentStreamType();
		byte[] stream = getBytesFromFileOld(filePath + fileName);
		final InputStream iS = new ByteArrayInputStream(stream);
		final OutputStream oS = new ByteArrayOutputStream(stream.length);

		javax.activation.DataSource a = new javax.activation.DataSource() {

			public OutputStream getOutputStream() throws IOException {
				return oS;
			}

			public String getName() {
				return fileName;
			}

			public InputStream getInputStream() throws IOException {
				return iS;
			}

			public String getContentType() {
				return estensioneFile;
			}
		};
		contentStream.setStreamMTOM(new DataHandler(a));
		// contentStream.setStream(stream);
		contentStream.setFilename(a.getName());
		contentStream.setMimeType(mimeType);
		return contentStream;
	}
	
	
	private static byte[] getBytesFromFileOld(String filePath) throws IOException {
		File file = new File(filePath);

		InputStream is = new FileInputStream(file);

		long length = file.length();

		if (length > Integer.MAX_VALUE) {
			log.error("File is too large");
		}

		byte[] bytes = new byte[(int) length];
		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
			offset += numRead;
		}

		if (offset < bytes.length) {
			log.error("File is too large" + file.getName());
		}

		is.close();
		return bytes;
	}
	
	/**
	 * Recupera documento.
	 *
	 * @param uid the uid
	 * @return the documento
	 */
	public byte[] recuperaDocumentoIndex(String uid) throws ACTAInvocationException {
		log.debug("[ACTAService::recuperaDocumentoIndex] BEGIN");

		byte[] ret = null;
		
		Documento doc = new Documento();
		doc.setUid(uid);

		try {
			
			doc = getSiceeorchSrv().ricercaDocumento(doc);
			
		} catch (Exception e) {
			log.error("[ACTAService::recuperaDocumentoIndex] e.getMessage(): " + e.getMessage());

			throw new ACTAInvocationException("Errore nel recupero del documento INDEX", e);

		} 
		
		log.debug("[ACTAService::recuperaDocumentoIndex] END");

		if (doc != null)
		{
			log.debug("[ACTAService::recuperaDocumentoIndex] documento recuperato correttamente");

			ret = doc.getDoc();
		}
		
		return ret;
	}
	
	
	public byte[] recuperaFileRicevuta(String numCerificatore, String progrCertificato, String annoCertificato)  throws ACTAInvocationException
	{
		log.debug("[ACTAService::recuperaFileRicevuta] BEGIN");
		byte[] docByte = null;
		try
		{
			
			docByte = getSiceeorchSrv().getStampaRicevutaInvioAce(numCerificatore, progrCertificato, annoCertificato);

		} catch (Exception e) {
			log.error("[ACTAService::recuperaFileRicevuta] e.getMessage(): " + e.getMessage());

			throw new ACTAInvocationException("Errore nel recupero della ricevuta", e);
		}
		
		log.debug("[ACTAService::recuperaFileRicevuta] END");

		return docByte;
	}
	
	public void sendMailProtocollazione(String mittente, Certificato certificato, String numProtocollo) throws ACTAInvocationException, Exception {
		log.debug("[ACTAService::sendMailProtocollazione] BEGIN");
		//GenericUtil.stampaVO(emailVO);	
		// Create a mail session
		try {

			Mail mail = new Mail();

			mail.setMittente(mittente);
			mail.setDestinatario(certificato.getEmail());
			
			mail.setOggetto("SIPEE: avvenuta protocollazione  APE " + GenericUtil.getOggettoCertificato(certificato));

			StringBuffer messaggio = new StringBuffer();
			messaggio.append("Con la presente si informa che l'attestato n. "+GenericUtil.getOggettoCertificato(certificato)+" trasmesso in data "+ GenericUtil.convertToString(certificato.getDtUpload()) +" e' stato protocollato con Rif. Protocollo: ");
			messaggio.append(numProtocollo);
			messaggio.append("\n\nIn allegato si trasmette ricevuta aggiornata.");

			mail.setTesto(messaggio.toString());


			byte[] doc = recuperaFileRicevuta(certificato.getNumCertificatore(), certificato.getProgrCertificato(), certificato.getAnno());

			sendMail(mail, "ricevuta_trasmissione.pdf", doc);

		}
		catch (ACTAInvocationException e) {
			log.debug("[ACTAService::sendMailProtocollazione] Errore nell'invio della mail (ACTAInvocationException)");

			throw e;
		}
		catch (Exception e) {
			log.debug("[ACTAService::sendMailProtocollazione] Errore nell'invio della mail (Exception)");
			throw e;
		} finally {
			log.debug("[ACTAService::sendMailProtocollazione] END");
		}

	}
	
	public void sendMailRiepilogo(Mail mail) throws Exception {
		log.debug("[ACTAService::sendMailRiepilogo] BEGIN");
		//GenericUtil.stampaVO(emailVO);	
		// Create a mail session
		try {
			//mail.setDestinatario("giuseppe.todaro@csi.it");
			
			mail.setOggetto("SIPEE Archiviazione e Protocollazione ACTA "+GenericUtil.convertToString(mail.getInizioProcesso()));

			StringBuffer messaggio = new StringBuffer();
			messaggio.append("Il Timer Service di Archiviazione e Protocollazione certificati APE su ACTA avviato alle ");
			messaggio.append(GenericUtil.convertToString(mail.getInizioProcesso()));
			messaggio.append(" ha eseguito le seguenti operazioni:");


			messaggio.append("\n\n");
			messaggio.append("Numero APE elaborati: ");
			messaggio.append(mail.getApeGestiti());

			messaggio.append("\n");
			messaggio.append("Numero APE archiviati su ACTA: ");
			messaggio.append(mail.getApeArchiviati());

			messaggio.append("\n");
			messaggio.append("Numero APE protocollati su ACTA: ");
			messaggio.append(mail.getApeProtocollati());


			ArrayList<String> listError = mail.getElencoErrori();

			if (listError != null && listError.size() > 0)
			{
				messaggio.append("\n\n\n");
				messaggio.append("Sono stati rilevati i seguenti errori in fase di Archiviazione/Protocollazione:");

				for (String errore : listError) {
					messaggio.append("\n");
					messaggio.append(errore);
				}
			}

			mail.setTesto(messaggio.toString());

			// Send the message
			sendMail(mail, null, null);

		} catch (Exception e) {
			log.error("Errore nell'invio della mail");
			throw e;
		} finally {
			log.debug("[ACTAService::sendMailRiepilogo] END");
		}

	}

	public void sendMail(Mail mail, String nomeFile, byte[] doc) throws Exception {
		log.debug("[ACTAService::sendMail] BEGIN");
		//GenericUtil.stampaVO(emailVO);	
		// Create a mail session
		File allegato = null;
		
		try {
			java.util.Properties props = new java.util.Properties();        
			
//			props.put("mail.smtp.host", factory.getMailHost());
//			props.put("mail.smtp.port", factory.getMailPort());
//			props.put("mail.debug", "true"); // Aggiunto per verifica problema della Mail
//
//			Session session = Session.getDefaultInstance(props, null);

			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.host", factory.getMailHost());
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", factory.getMailPort());
			props.put("mail.smtp.starttls.enable", "true");

			Authenticator auth = new SMTPAuthenticator(factory.getMailUser(), factory.getMailPwd());

			Session session=Session.getInstance(props,auth);
			
			
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(mail.getMittente()));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(mail.getDestinatario()));
			//        if(StringUtils.isNotEmpty(emailVo.getDestinatarioCC())){
			//        	msg.setRecipient(Message.RecipientType.CC, new InternetAddress(emailVo.getDestinatarioCC()));
			//        }
			msg.setSubject(mail.getOggetto());
			MimeMultipart  mp = new MimeMultipart();



			MimeBodyPart html = new MimeBodyPart();
			html.setText(mail.getTesto(), "text/plain");                
			html.setContent(mail.getTesto().replace("\n", "</br>"),"text/html");



			// create and fill the second message part
			if (doc != null) {
				MimeBodyPart attachmentPart = new MimeBodyPart();

//				JIRA SICEE-515
				allegato = createFileWithData(nomeFile, doc);
				
				FileDataSource fileDataSource = new FileDataSource(allegato) {
					@Override
					public String getContentType() {
						return "application/pdf";
					}
				};
				attachmentPart.setDataHandler(new DataHandler(fileDataSource));
				attachmentPart.setFileName(nomeFile);        	            
				mp.addBodyPart(attachmentPart);
			}
			// create the Multipart and its parts to it

			//mp.addBodyPart(text);
			mp.addBodyPart(html);

			// add the Multipart to the message
			msg.setContent(mp);        

			// 18/06/19 SICEE 10.0.0
			// Codice inserito per (cercare di) risolvere il problema dell'invio mail - inizio
			gestisciMimeTypes();
			// Codice inserito per (cercare di) risolvere il problema dell'invio mail - fine

			// Aggiunto questo comando per risolvere il problema di invio mail
			// Ho scommentato questa riga il 14/11/18 per provare a risolvere i problemi in prod
			// SIAPE aveva questa riga gia' scommentata
			// SICEEWS ha questa siga commentata e funziona
			// Quindi non sono sicuro che sia la soluzione
			
			log.info("[ACTAService::sendMail] - stampo il ContextClassLoader: "+Thread.currentThread().getContextClassLoader());
			if (Thread.currentThread().getContextClassLoader() == null)
			{
				log.info("[ACTAService::sendMail] - stampo il this.getClass().getClassLoader(): "+this.getClass().getClassLoader());

				Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
				
				log.info("[ACTAService::sendMail] - stampo il ContextClassLoader - dopo: "+Thread.currentThread().getContextClassLoader());
			}
			
			try
			{
				// Send the message
				Transport.send(msg);
				
			}
			catch (Exception e) {
				// ho ricevuto un'eccezione nell'invio mail
				// provo a mandare la mail con il servizio siceews
				log.error("Errore nell'invio della mail", e);

				sendMailService(mail, nomeFile, doc);
			}
			
		} catch (Exception e) {
			log.error("Errore nell'invio della mail");
			throw e;
		} finally {
//			JIRA SICEE-515
			if (allegato != null && allegato.exists()) {
				boolean isDelete = allegato.delete();
				
				if (log.isDebugEnabled()) {					
					log.debug("[MailSender::sendMail] "+allegato+": file.delete(): "+isDelete);
				}
			}
			log.debug("[ACTAService::sendMail] END");
		}

	}

	private class SMTPAuthenticator extends javax.mail.Authenticator {
		
		private String user;
		private String pwd;
		
		public SMTPAuthenticator(String user, String pwd)
		{
			this.user = user;
			this.pwd = pwd;
		}
		
		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(user, pwd);
		}
	}

	private static void gestisciMimeTypes()
	{
	    MailcapCommandMap mc = (MailcapCommandMap) CommandMap
	            .getDefaultCommandMap();

	    boolean isTextHtml = false;
	    boolean isTextXml = false;
	    boolean isTextPlain = false;
	    boolean isMultipartAll = false;
	    boolean isMessageRfc822 = false;

	    
	    String[] mimeTypes = mc.getMimeTypes();
	    log.debug("###########################");
	    log.debug("STAMPO I MIMETYPES - INIZIO");
	    log.debug("STAMPO I MIMETYPE array: "+mimeTypes);
	    
	    for (String mimeType : mimeTypes) {
			log.debug("STAMPO IL MIMETYPE (prima): *"+mimeType+"*");

			if (mimeType.equals("text/html"))
			{
				isTextHtml = true;
			}
			
			if (mimeType.equals("text/xml"))
			{
				isTextXml= true;
			}
			
			if (mimeType.equals("text/plain"))
			{
				isTextPlain = true;
			}
			
			if (mimeType.equals("multipart/*"))
			{
				isMultipartAll = true;
			}

			if (mimeType.equals("message/rfc822"))
			{
				isMessageRfc822 = true;
			}
			
	    }

	    if (!isTextHtml)
	    {
			log.info("MIMETYPE NON PRESENTE: text/html - l'aggiungo");
		    mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
		}
	    
	    if (!isTextXml)
	    {
			log.info("MIMETYPE NON PRESENTE: text/xml - l'aggiungo");
		    mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
		}
	    
	    if (!isTextPlain)
	    {
			log.info("MIMETYPE NON PRESENTE: text/plain - l'aggiungo");
		    mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
		}
	    
	    if (!isMultipartAll)
	    {
			log.info("MIMETYPE NON PRESENTE: multipart/* - l'aggiungo");
		    mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
		}
	    
	    if (!isMessageRfc822)
	    {
			log.info("MIMETYPE NON PRESENTE: message/rfc822 - l'aggiungo");
		    mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
		}
	    
	    if (!isTextHtml || !isTextXml || !isTextPlain || !isMultipartAll || !isMessageRfc822)
	    {
	    	log.info("MANCA QUALCHE MIMETYPE");

	    	CommandMap.setDefaultCommandMap(mc);

	    	log.info("STAMPO I MIMETYPES - DOPO SETTAGGIO");

	    	String[] mimeTypesNew = mc.getMimeTypes();

	    	for (String mimeType : mimeTypesNew) {
	    		log.info("STAMPO IL MIMETYPE (dopo): *"+mimeType+"*");

	    	}
	    }
	    else
	    {
	    	log.info("TUTTI I MIMETYPE SONO PRESENTI");
	    }
	    
	    log.debug("STAMPO I MIMETYPES - FINE");
	    log.debug("###########################");
	}
	
	
	private static File createFileWithData(String nome, byte[] doc) throws IOException
	{		
		int pos = nome.lastIndexOf(".");
		File file = File.createTempFile(nome, nome);
//		file.deleteOnExit();
		OutputStream src = new FileOutputStream(file);
		src.write(doc);
		src.close();
		return file;
	}
	
	private static QueryConditionType[] getCriteria(EnumQueryOperator[] operator, String[] propertyName, String[] value) {
		QueryConditionType[] criteria = null;
		if ((operator != null && operator.length > 0) && (propertyName != null && propertyName.length > 0)
				&& (value != null && value.length > 0)
				&& (operator.length == propertyName.length && operator.length == value.length)) {
			List<QueryConditionType> criteri = new ArrayList<QueryConditionType>();
			QueryConditionType criterio = null;
			for (int i = 0; i < propertyName.length; i++) {
				criterio = new QueryConditionType();
				criterio.setOperator(operator[i]);
				criterio.setPropertyName(propertyName[i]);
				criterio.setValue(value[i]);
				criteri.add(criterio);
			}
			criteria = criteri.toArray(new QueryConditionType[0]);
		}
		return criteria;
	}
	
	private static PropertyFilterType getPropertyFilter(EnumPropertyFilter type, String[] className, String[] propertyName,
			PropertyFilterType prevFilter) {
		PropertyFilterType filter = null;
		if (type != null) {
			if (type.value().equals(EnumPropertyFilter.LIST.value())) {
				
				
				filter = (prevFilter != null) ? prevFilter : new PropertyFilterType();
				filter.setFilterType(type);
				List<QueryNameType> properties = new ArrayList<QueryNameType>();
				QueryNameType property = null;
				if (className.length == propertyName.length) {
					if (prevFilter != null && prevFilter.getFilterType().value().equals(EnumPropertyFilter.LIST.value())
							&& prevFilter.getPropertyListLength() > 0) {
						for (int j = 0; j < prevFilter.getPropertyListLength(); j++) {
							properties.add(prevFilter.getPropertyList(j));
						}
					}
					for (int i = 0; i < propertyName.length; i++) {
						property = new QueryNameType();
						property.setClassName(className[i]);
						property.setPropertyName(propertyName[i]);
						properties.add(property);
					}
					filter.setPropertyList(properties.toArray(new QueryNameType[0]));
				} else
					return null;

			} else {
				filter = new PropertyFilterType();
				filter.setFilterType(type);
			}
		}
		return filter;
	}
	
	public static String getPropertyValueByName(PagingResponseType pagingResponse, String propertyName)
	{
		String propertyValue = null;
		
		if (pagingResponse.getObjectsLength() > 0)
		{
			// stampa(pagingResponse);

			ObjectResponseType[] objectResponseList = pagingResponse.getObjects();
			
			propertyValue = getPropertyValueByName(objectResponseList, propertyName);
			
		}
		
		return propertyValue;
	}
	
	public static String[] getPropertyValueListByName(PagingResponseType pagingResponse, String propertyName)
	{
		String[] propertyValue = null;
		
		if (pagingResponse.getObjectsLength() > 0)
		{
			// stampa(pagingResponse);

			ObjectResponseType[] objectResponseList = pagingResponse.getObjects();
			
			propertyValue = getPropertyValueListByName(objectResponseList, propertyName);
			
		}
		
		return propertyValue;
	}
	
	private static String getPropertyValueByName(ObjectResponseType[] objectResponseList, String propertyName)
	{
		String propertyValue = null;
		
		if (objectResponseList != null && objectResponseList.length > 0)
		{
			
			PropertyType[] propertyList = objectResponseList[0].getProperties();
			
			
			for (PropertyType objectPropertyType : propertyList) {
				
				if (objectPropertyType.getValue() != null && objectPropertyType.getValue().getContentLength() > 0)
				{
					// Stampa tutti i valori
				}
				
				if (propertyName != null && propertyName.equals(objectPropertyType.getQueryName().getPropertyName()) && objectPropertyType.getValue() != null&& objectPropertyType.getValue().getContent().length > 0)
				{
					
					propertyValue = objectPropertyType.getValue().getContent(0);

					
					// Stampa il valore ricercato
					log.info("[ACTAService::cercaDocumentoArchiviato] STAMPO propertyValue (TROVATA) : "+propertyValue);
				}
				
			}
			
		}
		
		return propertyValue;
	}
	
	private static String[] getPropertyValueListByName(ObjectResponseType[] objectResponseList, String propertyName)
	{
		String[] propertyValueList = null;
		
		if (objectResponseList != null && objectResponseList.length > 0)
		{
			
			PropertyType[] propertyList = objectResponseList[0].getProperties();
			
			
			for (PropertyType objectPropertyType : propertyList) {
				
				if (objectPropertyType.getValue() != null && objectPropertyType.getValue().getContentLength() > 0)
				{
					// Stampa tutti i valori
				}
				
				if (propertyName != null && propertyName.equals(objectPropertyType.getQueryName().getPropertyName()) && objectPropertyType.getValue() != null&& objectPropertyType.getValue().getContent().length > 0)
				{
					
					propertyValueList = objectPropertyType.getValue().getContent();
					
					
					// Stampa il valore ricercato
					log.info("[ACTAService::cercaDocumentoArchiviato] STAMPO propertyValue (TROVATA) : "+propertyValueList);
				}
				
			}
			
		}
		
		return propertyValueList;
	}
	
	public boolean sendMailService(Mail mail, String nomeFile, byte[] doc) throws Exception{
		
		log.info("[SOAIntegrationMgr::sendMailService] START");

		boolean isMailInviata = false;
		try {
			log.info("Prima di getWSSicee");
			
//			boolean isAlive = getSiceewsSrv().isAlive();
//
//			log.debug("isAlive: "+isAlive);

			it.csi.sicee.siceews.stubs.Mail mailService = new it.csi.sicee.siceews.stubs.Mail();
			
			mailService.setMittente(mail.getMittente());
			mailService.setDestinatario(mail.getDestinatario());
			mailService.setDestinatarioCC(mail.getDestinatarioCC());
			mailService.setOggetto(mail.getOggetto());
			mailService.setTesto(mail.getTesto());
			mailService.setHtml(mail.getTesto().replace("\n", "</br>"));
			
			//html.setText(mail.getTesto(), "text/plain");                
			//html.setContent(mail.getTesto().replace("\n", "</br>"),"text/html");
			
			ArrayList<it.csi.sicee.siceews.stubs.Allegato> elencoAllegati = new ArrayList<it.csi.sicee.siceews.stubs.Allegato>();

			if (doc != null)
			{
				
				it.csi.sicee.siceews.stubs.Allegato allegato = new it.csi.sicee.siceews.stubs.Allegato();
				allegato.setNomeFile(nomeFile);
				allegato.setContentType("application/pdf");
				allegato.setFile(doc);
				elencoAllegati.add(allegato);

			}
			
			
			
			if (elencoAllegati.size() > 0)
			{
				
				it.csi.sicee.siceews.stubs.Allegato[] elencoAllegatiNew = new it.csi.sicee.siceews.stubs.Allegato[elencoAllegati.size()]; 
				elencoAllegatiNew = elencoAllegati.toArray(elencoAllegatiNew);
				
				mailService.setElencoAllegati(elencoAllegatiNew);
			
			}

			isMailInviata = getSiceewsSrv().invioMail(mailService);

			log.info("Esito invio mail sendMailService: "+isMailInviata);

			
			log.info("Dopo della chiamta SICEEWS");
			
		} catch (Exception e) {
			log.error("[SOAIntegrationMgr::sendMailService] - Errore nell'invio della mail", e);
			throw e;
		}
		finally {
			log.info("[SOAIntegrationMgr::sendMailService] END");
		}
		return isMailInviata;
	}
	
	
	/**********************************************************************************************************
	 * visualizzazione risultati
	 *********************************************************************************************************/

	private static void stampa(IdentificatoreDocumento iDoc) {
		log.debug("---------------------- IdentificazioneDocumento --------------------");
		String objectIdValue = iDoc.getObjectIdDocumento().getValue();
		log.debug("objectIdDocumento: " + objectIdValue);
		log.debug("objectIdDocumento decifrato: " + AcarisUtils.pseudoDecipher(objectIdValue));
		log.debug("objectIdClassificazione: " + iDoc.getObjectIdClassificazione().getValue());
		
		log.debug("tipo documento: " + iDoc.getTipoDocumento().toString());
		log.debug("data ultimo aggiornamento: " + iDoc.getDataUltimoAggiornamento().getValue());

		FailedStepsInfo[] err =  iDoc.getFailedStepsInfo(); 
		
		if (err != null && err.length > 0)
		{
			for (FailedStepsInfo failedStepsInfo : err) {
				
				log.debug("failedStepsInfo.getFileName: "+failedStepsInfo.getFileName());
				int[] fil = failedStepsInfo.getFailedSteps();
				for (int i : fil) {
					log.debug("Step: "+i);
				}
			}
		}
		
	}
	
	private static void stampa(PagingResponseType pagingResponseType) {
		if (pagingResponseType == null) {
			log.debug("ATTENZIONE: recordset null");
		} else {
			int max = pagingResponseType.getObjectsLength();
			for (int i = 0; i < max; i++) {
				log.debug("--------------" + (i + 1) + "--------------");
				ObjectResponseType ort = pagingResponseType.getObjects(i);
				for (int j = 0; j < ort.getPropertiesLength(); j++) {
					PropertyType pt = ort.getProperties(j);
					log.debug(
							pt.getQueryName().getClassName() + "." + pt.getQueryName().getPropertyName() + ": ");
					for (int k = 0; k < pt.getValue().getContentLength(); k++) {
						log.debug("    " + pt.getValue().getContent(k));
					}
					
				}
				log.debug(null);
			}
		}
	}
	
	private static void stampa(ObjectResponseType[] objectResponseList)
	{
		if (objectResponseList != null && objectResponseList.length > 0)
		{
			
			log.debug("STAMPO objectResponseList[0].getObjectId(): "+objectResponseList[0].getObjectId());

			PropertyType[] propertyList = objectResponseList[0].getProperties();
			
			for (PropertyType objectPropertyType : propertyList) {
				
				log.debug("STAMPO propertyName: "+objectPropertyType.getQueryName().getPropertyName());
				
				if (objectPropertyType.getValue() != null && objectPropertyType.getValue().getContentLength() > 0)
				{
					// Stampa tutti i valori
					log.debug("STAMPO propertyValue: "+objectPropertyType.getValue().getContent(0));
				}
			}
			
		}
	}

	public String getMailHost() throws Exception {
		Properties properties = new Properties();
		InputStream stream = this.getClass().getResourceAsStream("/contants.properties");
		properties.load(stream);
		return properties.getProperty("mail.host");
	}

	private static void stampa(IdentificazioneRegistrazione iDoc) {
		log.debug("---------------------- ArchiviazioneDocumento --------------------");
		log.debug("registrazione: " + iDoc.getRegistrazioneId().getValue());
		log.debug("classificazioneId: " + iDoc.getClassificazioneId().getValue());
		log.debug("numero: " + iDoc.getNumero());
		log.debug("folderId: " + iDoc.getFolderId());
		log.debug("folderType: " + iDoc.getFolderType());
		log.debug("data ultimo aggiornamento: " + iDoc.getDataUltimoAggiornamento().getValue());
	}
}
