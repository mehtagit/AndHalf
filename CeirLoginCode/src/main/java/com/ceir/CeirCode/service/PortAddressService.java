package com.ceir.CeirCode.service;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ceir.CeirCode.Constants.Datatype;
import com.ceir.CeirCode.Constants.SearchOperation;
import com.ceir.CeirCode.SpecificationBuilder.GenericSpecificationBuilder;
import com.ceir.CeirCode.SpecificationBuilder.SpecificationBuilder;
import com.ceir.CeirCode.configuration.PropertiesReaders;
import com.ceir.CeirCode.configuration.SortDirection;
import com.ceir.CeirCode.exceptions.ResourceServicesException;
import com.ceir.CeirCode.filemodel.CustomPortFile;
import com.ceir.CeirCode.filemodel.CustomPortFile;
import com.ceir.CeirCode.filtermodel.PortAddressFilter;
import com.ceir.CeirCode.model.AddressObject;
import com.ceir.CeirCode.model.AllRequest;
import com.ceir.CeirCode.model.AuditTrail;
import com.ceir.CeirCode.model.FileDetails;
import com.ceir.CeirCode.model.Locality;
import com.ceir.CeirCode.model.ModemInfoTable;
import com.ceir.CeirCode.model.PortAddress;
import com.ceir.CeirCode.model.RequestHeaders;
import com.ceir.CeirCode.model.SearchCriteria;
import com.ceir.CeirCode.model.StatesInterpretationDb;
import com.ceir.CeirCode.model.SystemConfigListDb;
import com.ceir.CeirCode.model.SystemConfigurationDb;
import com.ceir.CeirCode.model.WebActionTbl;
import com.ceir.CeirCode.model.constants.Features;
import com.ceir.CeirCode.model.constants.SubFeatures;
import com.ceir.CeirCode.repo.LocalityRepo;
import com.ceir.CeirCode.repo.PortAddressRepo;
import com.ceir.CeirCode.repo.SystemConfigDbListRepository;
import com.ceir.CeirCode.repo.SystemConfigDbRepository;
import com.ceir.CeirCode.repoService.PortAddressRepoService;
import com.ceir.CeirCode.repoService.ReqHeaderRepoService;
import com.ceir.CeirCode.repoService.SystemConfigDbRepoService;
import com.ceir.CeirCode.repoService.SystemConfigurationDbRepoService;
import com.ceir.CeirCode.response.GenricResponse;
import com.ceir.CeirCode.response.tags.PortAddsTags;
import com.ceir.CeirCode.util.CustomMappingStrategy;
import com.ceir.CeirCode.util.HttpResponse;
import com.opencsv.CSVWriter;
import com.opencsv.bean.MappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;


@Service
public class PortAddressService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	PortAddressRepoService portService;
	
	@Autowired
	PropertiesReaders propertiesReader;
	
	@Autowired
	PortAddressRepo portAddressRepo;
	
	@Autowired
	SystemConfigurationDbRepoService systemConfigDbRepoService;

	@Autowired
	ReqHeaderRepoService headerService;

	@Autowired
	UserService userService;
	
	@Autowired
	SystemConfigDbRepoService systemConfigurationDbRepoImpl;
	
	@Autowired
	SystemConfigDbListRepository systemConfigRepo;
	@Autowired
	SystemConfigDbRepository systemConfigDbRepository;
	
	@Autowired
	StateMgmtServiceImpl stateMgmtServiceImpl;
	public ResponseEntity<?> getDataByPort(String port){
		log.info("inside getDataByPort controller");
		List<ModemInfoTable> portAddressList=portService.getByPort(port);
		if(portAddressList!=null) {
			log.info("exit from getDataByPort controller");
			return  new ResponseEntity<>(portAddressList,HttpStatus.OK);

		}
		else {
			HttpResponse response=new HttpResponse("Address data failed to find by port",204);
			log.info("exit from getDataByPort controller");
			return  new ResponseEntity<>(response,HttpStatus.OK);
		}
	}
	public ResponseEntity<?> saveAddressPort(ModemInfoTable portAddress){
		log.info("inside save address port controller and data is portAddress");
		log.info("port data: "+portAddress);
//		RequestHeaders header=new RequestHeaders(portAddress.getUserAgent(),portAddress.getPublicIp(),portAddress.getUsername());
//		headerService.saveRequestHeader(header);
		/*
		 * userService.saveUserTrail(portAddress.getUserId(),portAddress.getUsername(),
		 * portAddress.getUserType(),portAddress.getUserTypeId(),Features.
		 * Port_Management,SubFeatures.Add,portAddress.getFeatureId(),portAddress.
		 * getPublicIp(),portAddress.getBrowser());
		 */
		ModemInfoTable output=portService.save(portAddress);

		if(output!=null) {
			GenricResponse response=new GenricResponse(200,PortAddsTags.PAdd_Save_Sucess.getMessage(),PortAddsTags.PAdd_Save_Sucess.getTag());
			return  new ResponseEntity<>(response,HttpStatus.OK);
		}
		else {
			GenricResponse response=new GenricResponse(500,PortAddsTags.PAdd_Save_Fail.getMessage(),PortAddsTags.PAdd_Save_Fail.getTag());
			return  new ResponseEntity<>(response,HttpStatus.OK);
		}

	}
	
	public ResponseEntity<?> viewById(long id){
		log.info("inside view by id port controller");
		log.info("id given : "+id);
		ModemInfoTable output=portService.getById(id);
		if(output!=null) {
			log.info("output : "+output);
			/*
			 * List<StatesInterpretationDb> states =
			 * stateMgmtServiceImpl.findByFeatureId(2); for(ModemInfoTable
			 * modemInfoTable:portAddressData.getContent()) { for(StatesInterpretationDb
			 * list:states) { Integer value = list.getState();
			 * if(modemInfoTable.getStatusInt()==value) {
			 * modemInfoTable.setPortInterp(list.getInterp()); } } }
			 */
			GenricResponse response=new GenricResponse(200,"","",output);		
		
			return  new ResponseEntity<>(response,HttpStatus.OK);
		}
		else {
			GenricResponse response=new GenricResponse(500,PortAddsTags.PAdd_Data_By_Id_Fail.getMessage(),PortAddsTags.PAdd_Data_By_Id_Fail.getTag());
			return  new ResponseEntity<>(response,HttpStatus.OK);
		}

	}

	
	public ResponseEntity<?> viewPortById(PortAddressFilter request){
		log.info("inside view by address port controller");
		log.info("data given : "+request);
		ModemInfoTable output=portService.getById(request.getId());
		
		if(output!=null) {
			log.info("Response : "+output);
			GenricResponse response=new GenricResponse(200,"","",output);		
			return  new ResponseEntity<>(response,HttpStatus.OK);
		}
		else {
			GenricResponse response=new GenricResponse(500,PortAddsTags.PAdd_Data_By_Id_Fail.getMessage(),PortAddsTags.PAdd_Data_By_Id_Fail.getTag());
			return  new ResponseEntity<>(response,HttpStatus.OK);
		}

	}
	
	public ResponseEntity<?> updateAddressPort(ModemInfoTable portAddress){
		log.info("inside update address port controller");
		log.info("address port data "+portAddress);
//		RequestHeaders header=new RequestHeaders(portAddress.getUserAgent(),portAddress.getPublicIp(),portAddress.getUsername());
//		headerService.saveRequestHeader(header);
		/*
		 * userService.saveUserTrail(portAddress.getUserId(),portAddress.getUsername(),
		 * portAddress.getUserType(),portAddress.getUserTypeId(),Features.
		 * Port_Management,SubFeatures.UPDATE,portAddress.getFeatureId(),portAddress.
		 * getPublicIp(),portAddress.getBrowser());
		 */
        
		ModemInfoTable data=portService.getById(portAddress.getId());
		//String username = portAddress.getUsername();
		//log.info("Modifeid By Username "+username);
		if(data!=null) {
			/*
			 * portAddress.setCreatedOn(data.getCreatedOn());
			 * portAddress.setApprovedBy(username);
			 */
			ModemInfoTable output=portService.save(portAddress);
		
		if(output!=null) {
			GenricResponse response=new GenricResponse(200,PortAddsTags.PAdd_Update_Sucess.getMessage(),PortAddsTags.PAdd_Save_Sucess.getTag());
			log.info("exit from  update address port controller");
			return  new ResponseEntity<>(response,HttpStatus.OK);
		}
		else {
			GenricResponse response=new GenricResponse(500,PortAddsTags.PAdd_Update_Fail.getMessage(),PortAddsTags.PAdd_Save_Fail.getTag());
			return  new ResponseEntity<>(response,HttpStatus.OK);
		}
		}
		else {
			GenricResponse response=new GenricResponse(500,PortAddsTags.PAdd_Wrong_PORT_ID.getMessage(),PortAddsTags.PAdd_Wrong_PORT_ID.getTag());
			return  new ResponseEntity<>(response,HttpStatus.OK);		
		}

	}
	
	public ResponseEntity<?> deleteAddressPort(AllRequest request){
		log.info("inside save address port controller");
//		RequestHeaders header=new RequestHeaders(request.getUserAgent(),request.getPublicIp(),request.getUsername());
//		headerService.saveRequestHeader(header);
		userService.saveUserTrail(request.getUserId(),request.getUsername(),
				request.getUserType(),request.getUserTypeId(),Features.Port_Management,SubFeatures.DELETE,request.getFeatureId(),request.getPublicIp(),request.getBrowser());

		int output=portService.deleteById(request.getDataId());
		if(output==1) {
			GenricResponse response=new GenricResponse(200,PortAddsTags.PAdd_Del_Sucess.getMessage(),PortAddsTags.PAdd_Save_Sucess.getTag());
			return  new ResponseEntity<>(response,HttpStatus.OK);
		}
		else {
			GenricResponse response=new GenricResponse(500,PortAddsTags.PAdd_Del_Fail.getMessage(),PortAddsTags.PAdd_Save_Fail.getTag());
			return  new ResponseEntity<>(response,HttpStatus.OK);
		}

	}
	
	
	public Page<ModemInfoTable> portAddressInfo(PortAddressFilter filter, Integer pageNo, Integer pageSize, String export){
		log.info("inside portAddress view  controller");
		log.info("portAddressInfo : "+filter);
		
		/*
		 * String orderColumn;
		 * 
		 * if(export.equals("Export")) { orderColumn = "modifiedOn";
		 * 
		 * }else { orderColumn =
		 * "Created On".equalsIgnoreCase(filter.getOrderColumnName()) ? "createdOn" :
		 * "Modified On".equalsIgnoreCase(filter.getOrderColumnName()) ? "modifiedOn" :
		 * "Port Address".equalsIgnoreCase(filter.getOrderColumnName()) ? "address"
		 * :"Port Type".equalsIgnoreCase(filter.getOrderColumnName()) ? "port" :
		 * "modifiedOn"; } Sort.Direction direction;
		 * 
		 * direction= SortDirection.getSortDirection(filter.getOrder() == null ? "desc"
		 * : filter.getOrder());
		 * 
		 * log.info("orderColumn is : "+orderColumn+ " & direction is : "+direction);
		 * Pageable pageable = PageRequest.of(pageNo, pageSize, new Sort(direction,
		 * orderColumn));
		 */
		
		
//		RequestHeaders header=new RequestHeaders(filter.getUserAgent(),filter.getPublicIp(),filter.getUsername());
//		headerService.saveRequestHeader(header);
		//String operationType = "Export".equalsIgnoreCase(export) ? SubFeatures.EXPORT :SubFeatures.VIEW_ALL ;
		
		/*
		 * userService.saveUserTrail(filter.getUserId(),filter.getUsername(),
		 * filter.getUserType(),filter.getUserTypeId(),Features.Port_Management,
		 * operationType,filter.getFeatureId(),filter.getPublicIp(),filter.getBrowser())
		 * ;
		 */

		Pageable pageable = PageRequest.of(pageNo, pageSize, new Sort(Sort.Direction.DESC, "portId"));
		GenericSpecificationBuilder<ModemInfoTable> specification=new GenericSpecificationBuilder<ModemInfoTable>(propertiesReader.dialect) ;
		
		if (filter.getId()==0) {
			log.info("inside If Block");
		}else {
			specification.with(new SearchCriteria("id",filter.getId(),SearchOperation.EQUALITY,Datatype.INTEGER));
		}
		
		/*
		 * if(Objects.nonNull(filter.getId())) specification.with(new
		 * SearchCriteria("id",filter.getId(),SearchOperation.EQUALITY,Datatype.INTEGER)
		 * );
		 */
		
		if(Objects.nonNull(filter.getStatusint())) 
			specification.with(new SearchCriteria("statusint",filter.getStatusint(),SearchOperation.EQUALITY,Datatype.INTEGER));
		
		if(Objects.nonNull(filter.getPortId()) && !filter.getPortId().isEmpty()){
			 specification.orSearch(new SearchCriteria("portId", filter.getPortId(),
			 SearchOperation.LIKE, Datatype.STRING)); 
		}
		
		log.info("portAddress Pageble Response : "+pageable);
		return portAddressRepo.findAll(specification.build(),pageable);
		//return userProfileRepo.findAll(specification.build());
		
	}
	
	
	public FileDetails getFile(PortAddressFilter filter) {
		log.info("inside export custom Port service");
		log.info("filter data:  "+filter);
		String fileName = null;
		Writer writer   = null;
		CustomPortFile uPFm = null;
		SystemConfigurationDb dowlonadDir=systemConfigurationDbRepoImpl.getDataByTag("file.download-dir");
		SystemConfigurationDb dowlonadLink=systemConfigurationDbRepoImpl.getDataByTag("file.download-link");
		DateTimeFormatter dtf  = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		Integer pageNo = 0;
		Integer pageSize = Integer.valueOf(systemConfigDbRepository.findByTag("file.max-file-record").getValue());
		String filePath  = dowlonadDir.getValue();
		StatefulBeanToCsvBuilder<CustomPortFile> builder = null;
		StatefulBeanToCsv<CustomPortFile> csvWriter      = null;
		List<CustomPortFile> fileRecords       = null;
		CustomMappingStrategy<CustomPortFile> mapStrategy = new CustomMappingStrategy<>();
		
		
		try {
			
			mapStrategy.setType(CustomPortFile.class);
			List<SystemConfigListDb> portList=systemConfigDbRepoService.getDataByTag("CUSTOMS_PORT");
			List<ModemInfoTable> list = portAddressInfo(filter, pageNo, pageSize,"Export").getContent();
			for(ModemInfoTable portAddress:list) {
				for(SystemConfigListDb asType:portList) {
					Integer value=asType.getValue();
					/*
					 * if(portAddress.getPort()==value) {
					 * portAddress.setPortInterp(asType.getInterp()); }
					 */
				}
			}

			if( list.size()> 0 ) {
				fileName = LocalDateTime.now().format(dtf).replace(" ", "_")+"_CustomPort.csv";
			}else {
				fileName = LocalDateTime.now().format(dtf).replace(" ", "_")+"_CustomPort.csv";
			}
			log.info(" file path plus file name: "+Paths.get(filePath+fileName));
			writer = Files.newBufferedWriter(Paths.get(filePath+fileName));
//			builder = new StatefulBeanToCsvBuilder<UserProfileFileModel>(writer);
//			csvWriter = builder.withQuotechar(CSVWriter.DEFAULT_QUOTE_CHARACTER).build();
//			
			builder = new StatefulBeanToCsvBuilder<>(writer);
			csvWriter = builder.withMappingStrategy(mapStrategy).withSeparator(',')
					.withQuotechar(CSVWriter.DEFAULT_QUOTE_CHARACTER).build();
			if( list.size() > 0 ) {
				//List<SystemConfigListDb> systemConfigListDbs = configurationManagementServiceImpl.getSystemConfigListByTag("GRIEVANCE_CATEGORY");
				fileRecords = new ArrayList<CustomPortFile>(); 
				for( ModemInfoTable portAddress : list ) {
					uPFm = new CustomPortFile();
					/*
					 * uPFm.setCreatedOn(portAddress.getCreatedOn().format(dtf));
					 * uPFm.setModifiedOn(portAddress.getModifiedOn().format(dtf));
					 * uPFm.setPortInterp(portAddress.getPortInterp());
					 * uPFm.setAddress(portAddress.getAddress());
					 */
					fileRecords.add(uPFm);
				}
				csvWriter.write(fileRecords);
			}
			log.info("fileName::"+fileName);
			log.info("filePath::::"+filePath);
			log.info("link:::"+dowlonadLink.getValue());
			return new FileDetails(fileName, filePath,dowlonadLink.getValue().replace("$LOCAL_IP",propertiesReader.localIp)+fileName ); 
		
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}finally {
			try {

				if( writer != null )
					writer.close();
			} catch (IOException e) {}
		}

	}

	public GenricResponse portAction(String modemId,String status,String action,String clientId, String operation){
		log.info("inside Port Action Service for performing ["+operation+"] for modemID ["+modemId+"]");
		if("Run".equals(operation)) {
			userService.saveWebAction(modemId,status,action,clientId);

		}else {
			userService.saveWebAction(modemId,status,action,clientId);

		}
		return  new GenricResponse(200,"Your request has been recevied successfully.","");
	}
	
}
