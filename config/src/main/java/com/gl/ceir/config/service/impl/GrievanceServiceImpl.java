package com.gl.ceir.config.service.impl;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.configuration.FileStorageProperties;
import com.gl.ceir.config.configuration.PropertiesReader;
import com.gl.ceir.config.exceptions.ResourceServicesException;
import com.gl.ceir.config.model.FileDetails;
import com.gl.ceir.config.model.Grievance;
import com.gl.ceir.config.model.GrievanceFilterRequest;
import com.gl.ceir.config.model.GrievanceGenricResponse;
import com.gl.ceir.config.model.GrievanceHistory;
import com.gl.ceir.config.model.GrievanceMsg;
import com.gl.ceir.config.model.GrievanceMsgWithUser;
import com.gl.ceir.config.model.GrievanceReply;
import com.gl.ceir.config.model.RequestCountAndQuantity;
import com.gl.ceir.config.model.ResponseCountAndQuantity;
import com.gl.ceir.config.model.SearchCriteria;
import com.gl.ceir.config.model.WebActionDb;
import com.gl.ceir.config.model.constants.Datatype;
import com.gl.ceir.config.model.constants.GrievanceStatus;
import com.gl.ceir.config.model.constants.SearchOperation;
import com.gl.ceir.config.model.constants.WebActionDbFeature;
import com.gl.ceir.config.repository.GrievanceHistoryRepository;
import com.gl.ceir.config.repository.GrievanceMsgRepository;
import com.gl.ceir.config.repository.GrievanceRepository;
import com.gl.ceir.config.repository.WebActionDbRepository;
import com.gl.ceir.config.specificationsbuilder.GrievanceHistorySpecificationBuilder;
import com.gl.ceir.config.specificationsbuilder.GrievanceSpecificationBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

@Service
public class GrievanceServiceImpl{

	private static final Logger logger = LogManager.getLogger(GrievanceServiceImpl.class);
	@Autowired
	GrievanceRepository grievanceRepository;
	@Autowired
	GrievanceMsgRepository grievanceMsgRepository;
	@Autowired
	GrievanceHistoryRepository grievanceHistoryRepository;
	@Autowired
	WebActionDbRepository webActionDbRepository;
	@Autowired
	PropertiesReader propertiesReader;
	@Autowired
	StateMgmtServiceImpl stateMgmtServiceImpl;
	@Autowired
	FileStorageProperties fileStorageProperties;
	
	@Transactional
	public GrievanceGenricResponse save(Grievance grievance) {
		try {

			WebActionDb webActionDb = new WebActionDb();
			webActionDb.setFeature(WebActionDbFeature.GRIEVANCE.getName());
			webActionDb.setState(GrievanceStatus.NEW.getCode());
			webActionDb.setTxnId(grievance.getTxnId());
			grievance.setGrievanceStatus( GrievanceStatus.NEW.getCode() );
			grievanceRepository.save(grievance);
			webActionDbRepository.save(webActionDb);
			return new GrievanceGenricResponse(0,"Grievance registered successfuly",grievance.getGrievanceId());

		}catch (Exception e) {
			logger.error("Grievance Registration failed="+e.getMessage());
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	public List<Grievance> getGrievanceByUserId(Integer userId) {
		try {
			logger.info("Going to get All grievances List ");
			return grievanceRepository.getGrievanceByUserId(userId);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}
	
	public List<Grievance> getAllGrievanceStatusNotClosed(Integer userId) {
		try {
			logger.info("Going to get All grievances List ");
			return grievanceRepository.getAllGrievanceStatusNotClosed(userId, GrievanceStatus.CLOSED.getCode());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}
	
	public List<Grievance> getAllGrievanceStatusNotClosedForAdmin() {
		try {
			logger.info("Going to get All grievances List ");
			return grievanceRepository.getAllGrievanceStatusNotClosedForAdmin(GrievanceStatus.CLOSED.getCode());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	public Grievance getByGrievanceTxnId(String txnId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Grievance> getFilterGrievances(GrievanceFilterRequest grievance, Integer pageNo, Integer pageSize) {
		try {
			Pageable pageable = PageRequest.of(pageNo, pageSize, new Sort(Sort.Direction.DESC, "grievanceId"));
			System.out.println("dialect : " + propertiesReader.dialect);
			GrievanceSpecificationBuilder gsb = new GrievanceSpecificationBuilder(propertiesReader.dialect);
			if(Objects.nonNull(grievance.getUserId()) && (grievance.getUserId() != -1 && grievance.getUserId() != 0))
				gsb.with(new SearchCriteria("userId", grievance.getUserId(), SearchOperation.EQUALITY, Datatype.INT));
			if(Objects.nonNull(grievance.getStartDate()))
				gsb.with(new SearchCriteria("createdOn", grievance.getStartDate() , SearchOperation.GREATER_THAN, Datatype.DATE));
			if(Objects.nonNull(grievance.getEndDate()))
				gsb.with(new SearchCriteria("createdOn",grievance.getEndDate() , SearchOperation.LESS_THAN, Datatype.DATE));
			if(Objects.nonNull(grievance.getGrievanceStatus()) && grievance.getGrievanceStatus() != -1)
				gsb.with(new SearchCriteria("grievanceStatus", grievance.getGrievanceStatus(), SearchOperation.EQUALITY, Datatype.INT));
			else
				gsb.with(new SearchCriteria("grievanceStatus", GrievanceStatus.CLOSED.getCode(), SearchOperation.NEGATION, Datatype.INT));
			if(Objects.nonNull(grievance.getGrievanceId()))
				gsb.with(new SearchCriteria("grievanceId", grievance.getGrievanceId(), SearchOperation.EQUALITY, Datatype.LONG));
			if(Objects.nonNull(grievance.getTxnId()))
				gsb.with(new SearchCriteria("txnId", grievance.getTxnId(), SearchOperation.EQUALITY, Datatype.STRING));
			//List<Grievance> data = grievanceRepository.getGrievanceByUserId(grievance.getUserId());
			//logger.info("Data to be fetch in db using jioin ="+data);
			return grievanceRepository.findAll(gsb.build(), pageable).getContent();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}

	}
	
	public Page<Grievance> getFilterPaginationGrievances(GrievanceFilterRequest grievance, Integer pageNo, Integer pageSize) {
		try {
			Pageable pageable = PageRequest.of(pageNo, pageSize, new Sort(Sort.Direction.DESC, "grievanceId"));
			GrievanceSpecificationBuilder gsb = new GrievanceSpecificationBuilder(propertiesReader.dialect);
			if(Objects.nonNull(grievance.getUserId()) && (grievance.getUserId() != -1 && grievance.getUserId() != 0))
				gsb.with(new SearchCriteria("userId", grievance.getUserId(), SearchOperation.EQUALITY, Datatype.INT));
			if(Objects.nonNull(grievance.getStartDate()))
				gsb.with(new SearchCriteria("createdOn", grievance.getStartDate() , SearchOperation.GREATER_THAN, Datatype.DATE));
			if(Objects.nonNull(grievance.getEndDate()))
				gsb.with(new SearchCriteria("createdOn",grievance.getEndDate() , SearchOperation.LESS_THAN, Datatype.DATE));
			if(Objects.nonNull(grievance.getGrievanceStatus()) && grievance.getGrievanceStatus() != -1)
				gsb.with(new SearchCriteria("grievanceStatus", grievance.getGrievanceStatus(), SearchOperation.EQUALITY, Datatype.INT));
			else
				gsb.with(new SearchCriteria("grievanceStatus", GrievanceStatus.CLOSED.getCode(), SearchOperation.NEGATION, Datatype.INT));
			if(Objects.nonNull(grievance.getGrievanceId()))
				gsb.with(new SearchCriteria("grievanceId", grievance.getGrievanceId(), SearchOperation.EQUALITY, Datatype.LONG));
			if(Objects.nonNull(grievance.getTxnId()))
				gsb.with(new SearchCriteria("txnId", grievance.getTxnId(), SearchOperation.EQUALITY, Datatype.STRING));
			return grievanceRepository.findAll(gsb.build(), pageable);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}

	}
	
	public FileDetails getFilterGrievancesInFile(GrievanceFilterRequest grievance, Integer pageNo, Integer pageSize) {
		String fileName = null;
		Writer writer   = null;
		//String[] columns = new String[]{"grievanceId","userId","userType","grievanceStatus","txnId","categoryId","fileName","createdOn","modifiedOn","remarks"};
		String filePath  = fileStorageProperties.getGrievanceDownloadDir();
		StatefulBeanToCsvBuilder<Grievance> builder = null;
		StatefulBeanToCsv<Grievance> csvWriter = null;
		//ColumnPositionMappingStrategy<Grievance> mapStrategy = null;
		HeaderColumnNameTranslateMappingStrategy<Grievance> mapStrategy = null;
		try {
			List<Grievance> grievances = this.getFilterPaginationGrievances(grievance, pageNo, pageSize).getContent();
			fileName = "User_"+grievance.getUserId()+"_Grievances.csv";
			if( grievances.size() > 0 ) {
				Map<String, String> columnMapping = new HashMap<String, String>();
				columnMapping.put("grievanceId", "grievanceId");
				columnMapping.put("userId", "userId");
				columnMapping.put("userType", "userType");
				columnMapping.put("grievanceStatus", "grievanceStatus");
				columnMapping.put("txnId", "txnId");
				columnMapping.put("categoryId", "categoryId");
				columnMapping.put("fileName", "fileName");
				columnMapping.put("createdOn", "createdOn");
				columnMapping.put("modifiedOn", "modifiedOn");
				columnMapping.put("remarks", "remarks");
				//mapStrategy = new ColumnPositionMappingStrategy<Grievance>();
				mapStrategy = new HeaderColumnNameTranslateMappingStrategy<Grievance>();
				mapStrategy.setType( Grievance.class );
				mapStrategy.setColumnMapping(columnMapping);
				writer = Files.newBufferedWriter(Paths.get(filePath+fileName));
				builder = new StatefulBeanToCsvBuilder<>(writer);
				csvWriter = builder.withMappingStrategy(mapStrategy)
	                    .withQuotechar(CSVWriter.DEFAULT_QUOTE_CHARACTER)
	                    .build();
				
				csvWriter.write(grievances);
			}
			return new FileDetails( fileName, filePath, fileStorageProperties.getGrievanceDownloadLink()+fileName ); 
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}finally {
			try {

				if( writer != null )
				writer.close();
			} catch (IOException e) {}
		}

	}
	
	@Transactional
	public GrievanceGenricResponse saveGrievanceMsg(GrievanceReply grievanceReply) {
		GrievanceMsg grievanceMsg = null;
		GrievanceHistory grievanceHistory = null;
		try {
			Grievance grievance = grievanceRepository.getBygrievanceId( grievanceReply.getGrievanceId() );
			/*****Grievance Message new object*****/
			grievanceMsg = new GrievanceMsg();
			grievanceMsg.setGrievanceId( grievance.getGrievanceId() );
			grievanceMsg.setUserId( grievanceReply.getUserId() );
			grievanceMsg.setUserType( grievanceReply.getUserType());
			grievanceMsg.setFileName( grievanceReply.getFileName());
			grievanceMsg.setReply( grievanceReply.getReply());
			/*****Grievance Message new object all parameters set*****/
			WebActionDb webActionDb = new WebActionDb();
			webActionDb.setFeature(WebActionDbFeature.GRIEVANCE.getName());
			/**Grievance status update**/
			if( grievanceReply.getGrievanceStatus() != GrievanceStatus.CLOSED.getCode() ) {
				if( grievance.getUserId() != grievanceReply.getUserId() ) { 
					webActionDb.setState(GrievanceStatus.PENDING_WITH_USER.getCode());
					grievance.setGrievanceStatus( GrievanceStatus.PENDING_WITH_USER.getCode() );
				}else{
					webActionDb.setState(GrievanceStatus.PENDING_WITH_ADMIN.getCode());
					grievance.setGrievanceStatus( GrievanceStatus.PENDING_WITH_ADMIN.getCode() );
				}
			}else{
				webActionDb.setState(GrievanceStatus.CLOSED.getCode());
				grievance.setGrievanceStatus( GrievanceStatus.CLOSED.getCode() );
				/**Grievance History object**/
				grievanceHistory = new GrievanceHistory();
				grievanceHistory.setGrievanceId( grievance.getGrievanceId());
				grievanceHistory.setCategoryId( grievance.getCategoryId());
				grievanceHistory.setUserId( grievance.getUserId());
				grievanceHistory.setUserType( grievance.getUserType() );
				grievanceHistory.setCategoryId( grievance.getCategoryId());
				grievanceHistory.setFileName( grievance.getFileName() );
				grievanceHistory.setGrievanceStatus( GrievanceStatus.CLOSED.getCode() );
				grievanceHistory.setCreatedOn(grievance.getCreatedOn());
				grievanceHistory.setTxnId( grievance.getTxnId() );
				grievanceHistory.setRemarks( grievance.getRemarks() );
				/**Grievance History object all parameters set**/
			}
			/**Grievance status update end**/
			webActionDb.setTxnId(grievanceReply.getTxnId());
			webActionDbRepository.save(webActionDb);
			grievanceRepository.save(grievance);
			grievanceMsgRepository.save(grievanceMsg);
			if( grievanceHistory != null )
				grievanceHistoryRepository.save(grievanceHistory);
			return new GrievanceGenricResponse(0,"Grievance Message saved successfuly.",grievance.getGrievanceId());

		}catch (Exception e) {
			logger.error("Grievance Message update failed"+e.getMessage());

			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}
	
	public List<GrievanceMsgWithUser> getAllGrievanceMessagesByGrievanceId( String grievanceId, Integer recordLimit, Integer userId ){
		List<GrievanceMsg> messages = null;
		List<GrievanceMsgWithUser> messagesWithUser = null;
		try {
			logger.info("Going to get All grievance Messages List ");
			if( recordLimit == -1) {
				messages = grievanceMsgRepository.getGrievanceMsgByGrievanceId(grievanceId );
			}else {
				messages =  grievanceMsgRepository.getGrievanceMsgByGrievanceIdOrderByIdDesc(grievanceId, new PageRequest(0, recordLimit) );
			}
			messagesWithUser = new ArrayList<GrievanceMsgWithUser>();
			if( messages.size() > 0 ) {
				GrievanceMsgWithUser msgWithUser = null;
				for( GrievanceMsg msg : messages ) {
					msgWithUser = new GrievanceMsgWithUser();
					msgWithUser.setId(msg.getId());
					msgWithUser.setGrievance( msg.getGrievance());
					msgWithUser.setGrievanceId(msg.getGrievanceId());
					msgWithUser.setCreatedOn(msg.getCreatedOn());
					msgWithUser.setModifiedOn(msg.getModifiedOn());
					msgWithUser.setFileName(msg.getFileName());
					msgWithUser.setReply(msg.getReply());
					msgWithUser.setUserId(msg.getUserId());
					msgWithUser.setUserType(msg.getUserType());
					if( messages.get(0).getGrievance().getUserId() == userId ) {
						if( msg.getUserId() == userId )
							msgWithUser.setUserDisplayName("You");
						else
							msgWithUser.setUserDisplayName("Admin");
					}else {
						if( msg.getUserId() == userId )
							msgWithUser.setUserDisplayName("You");
						else
							msgWithUser.setUserDisplayName("User");
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
		return messagesWithUser;
	}
	
	public Page<GrievanceHistory> getFilterPaginationGrievanceHistory(GrievanceFilterRequest grievance, Integer pageNo, Integer pageSize) {
		try {
			Pageable pageable = PageRequest.of(pageNo, pageSize, new Sort(Sort.Direction.DESC, "id"));
			GrievanceHistorySpecificationBuilder gsb = new GrievanceHistorySpecificationBuilder(propertiesReader.dialect);
			if(Objects.nonNull(grievance.getUserId()) && (grievance.getUserId() != -1 && grievance.getUserId() != 0))
				gsb.with(new SearchCriteria("userId", grievance.getUserId(), SearchOperation.EQUALITY, Datatype.INT));
			if(Objects.nonNull(grievance.getStartDate()))
				gsb.with(new SearchCriteria("createdOn", grievance.getStartDate() , SearchOperation.GREATER_THAN, Datatype.DATE));
			if(Objects.nonNull(grievance.getEndDate()))
				gsb.with(new SearchCriteria("createdOn",grievance.getEndDate() , SearchOperation.LESS_THAN, Datatype.DATE));
			if(Objects.nonNull(grievance.getGrievanceId()))
				gsb.with(new SearchCriteria("grievanceId", grievance.getGrievanceId(), SearchOperation.EQUALITY, Datatype.LONG));
			if(Objects.nonNull(grievance.getTxnId()))
				gsb.with(new SearchCriteria("txnId", grievance.getTxnId(), SearchOperation.EQUALITY, Datatype.STRING));
			return grievanceHistoryRepository.findAll(gsb.build(), pageable);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}

	}
	
	public ResponseCountAndQuantity getGrievanceCount( RequestCountAndQuantity request ) {
		ResponseCountAndQuantity cq = null;
		try {
			logger.info("Going to get Grievance count.");
			return grievanceRepository.getGrievanceCount( request.getUserId(), request.getStatus());
		} catch (Exception e) {
			//logger.error(e.getMessage(), e);
			//throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
			return new ResponseCountAndQuantity(0,0);
		}
	}

}
