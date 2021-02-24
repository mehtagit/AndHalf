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
import org.springframework.stereotype.Service;

import com.ceir.CeirCode.Constants.Datatype;
import com.ceir.CeirCode.Constants.SearchOperation;
import com.ceir.CeirCode.SpecificationBuilder.GenericSpecificationBuilder;
import com.ceir.CeirCode.configuration.PropertiesReaders;
import com.ceir.CeirCode.exceptions.ResourceServicesException;
import com.ceir.CeirCode.filemodel.LocalityFile;
import com.ceir.CeirCode.model.AddressObject;
import com.ceir.CeirCode.model.FileDetails;
import com.ceir.CeirCode.model.Locality;
import com.ceir.CeirCode.model.SearchCriteria;
import com.ceir.CeirCode.model.SystemConfigurationDb;
import com.ceir.CeirCode.model.UserProfileFileModel;
import com.ceir.CeirCode.repo.LocalityRepo;
import com.ceir.CeirCode.repo.SystemConfigDbListRepository;
import com.ceir.CeirCode.repoService.SystemConfigDbRepoService;
import com.ceir.CeirCode.util.CustomMappingStrategy;
import com.opencsv.CSVWriter;
import com.opencsv.bean.MappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
@Service
public class LocalityService {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	PropertiesReaders propertiesReader;
	@Autowired LocalityRepo localityRepo;
	
	@Autowired
	SystemConfigDbRepoService systemConfigurationDbRepoImpl;
	
	@Autowired
	SystemConfigDbListRepository systemConfigRepo;
	public Page<Locality>  viewAll(AddressObject filterRequest, Integer pageNo, Integer pageSize){
		try { 
			log.info("filter data:  "+filterRequest);
			Pageable pageable = PageRequest.of(pageNo, pageSize, new Sort(Sort.Direction.DESC, "modifiedOn"));
            Page<Locality> page=localityRepo.findAll(buildSpecification(filterRequest).build(),pageable);
			return page;

		} catch (Exception e) {
			log.info("Exception found ="+e.getMessage());
			log.info(e.getClass().getMethods().toString());
			log.info(e.toString());
			return null;

		}
	}

	
	private GenericSpecificationBuilder<Locality> buildSpecification(AddressObject filterRequest){
		GenericSpecificationBuilder<Locality> uPSB = new GenericSpecificationBuilder<Locality>(propertiesReader.dialect);	

		if(Objects.nonNull(filterRequest.getStartDate()) && filterRequest.getStartDate()!="")
			uPSB.with(new SearchCriteria("createdOn",filterRequest.getStartDate(), SearchOperation.GREATER_THAN, Datatype.DATE));

		
		  if(Objects.nonNull(filterRequest.getEndDate()) &&
		  filterRequest.getEndDate()!="") uPSB.with(new
		  SearchCriteria("createdOn",filterRequest.getEndDate(),
		  SearchOperation.LESS_THAN, Datatype.DATE));
		 
		if(Objects.nonNull(filterRequest.getProvince()) && filterRequest.getProvince()!="")
			uPSB.with(new SearchCriteria("province",filterRequest.getProvince(), SearchOperation.EQUALITY, Datatype.STRING));

		
		if(Objects.nonNull(filterRequest.getDistrict()) && filterRequest.getDistrict()!="")
			uPSB.with(new SearchCriteria("district",filterRequest.getDistrict(), SearchOperation.EQUALITY, Datatype.STRING));	
		
		if(Objects.nonNull(filterRequest.getCommune()) && filterRequest.getCommune()!="")
			uPSB.with(new SearchCriteria("commune",filterRequest.getCommune(), SearchOperation.EQUALITY, Datatype.STRING));
		if(Objects.nonNull(filterRequest.getVillage()) && filterRequest.getVillage()!="")
			uPSB.with(new SearchCriteria("village",filterRequest.getVillage(), SearchOperation.EQUALITY, Datatype.STRING));

		
		
		if(Objects.nonNull(filterRequest.getSearchString()) && !filterRequest.getSearchString().isEmpty()){
		uPSB.orSearch(new SearchCriteria("country", filterRequest.getSearchString(), SearchOperation.LIKE, Datatype.STRING));
		uPSB.orSearch(new SearchCriteria("province", filterRequest.getSearchString(), SearchOperation.LIKE, Datatype.STRING));
		uPSB.orSearch(new SearchCriteria("district", filterRequest.getSearchString(), SearchOperation.LIKE, Datatype.STRING));
		uPSB.orSearch(new SearchCriteria("commune", filterRequest.getSearchString(), SearchOperation.LIKE, Datatype.STRING));
		uPSB.orSearch(new SearchCriteria("village", filterRequest.getSearchString(), SearchOperation.LIKE, Datatype.STRING));
		
		}

		return uPSB;
	}
	

	public List<Locality> getAll(AddressObject filterRequest) {

		try {
			List<Locality> response = localityRepo.findAll( buildSpecification(filterRequest).build());

			return response;

		} catch (Exception e) {
			log.info("Exception found ="+e.getMessage());
			log.info(e.getClass().getMethods().toString());
			log.info(e.toString());
			return null;
		}

	}
	
	
	public FileDetails getFile(AddressObject filter) {
		log.info("inside export locality service");
		log.info("filter data:  "+filter);
		String fileName = null;
		Writer writer   = null;
		LocalityFile uPFm = null;
		SystemConfigurationDb dowlonadDir=systemConfigurationDbRepoImpl.getDataByTag("file.download-dir");
		SystemConfigurationDb dowlonadLink=systemConfigurationDbRepoImpl.getDataByTag("file.download-link");
		DateTimeFormatter dtf  = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		String filePath  = dowlonadDir.getValue();
		StatefulBeanToCsvBuilder<LocalityFile> builder = null;
		StatefulBeanToCsv<LocalityFile> csvWriter      = null;
		List<LocalityFile> fileRecords       = null;
		MappingStrategy<LocalityFile> mapStrategy = new CustomMappingStrategy<>();
		
		
		try {
			
			mapStrategy.setType(LocalityFile.class);

			List<Locality> list = getAll(filter);

			if( list.size()> 0 ) {
				fileName = LocalDateTime.now().format(dtf).replace(" ", "_")+"_UserProfile.csv";
			}else {
				fileName = LocalDateTime.now().format(dtf).replace(" ", "_")+"_UserProfile.csv";
			}
			log.info(" file path plus file name: "+Paths.get(filePath+fileName));
			writer = Files.newBufferedWriter(Paths.get(filePath+fileName));
//			builder = new StatefulBeanToCsvBuilder<UserProfileFileModel>(writer);
//			csvWriter = builder.withQuotechar(CSVWriter.DEFAULT_QUOTE_CHARACTER).build();
//			
			builder = new StatefulBeanToCsvBuilder<>(writer);
			csvWriter = builder.withMappingStrategy(mapStrategy).withSeparator(',').withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).build();

			if( list.size() > 0 ) {
				//List<SystemConfigListDb> systemConfigListDbs = configurationManagementServiceImpl.getSystemConfigListByTag("GRIEVANCE_CATEGORY");
				fileRecords = new ArrayList<LocalityFile>(); 
				for( Locality locality : list ) {
					uPFm = new LocalityFile();
					uPFm.setCreatedOn(locality.getCreatedOn().format(dtf));
					uPFm.setModifiedOn(locality.getModifiedOn().format(dtf));
//					uPFm.setCountry(locality.getCountry());
					uPFm.setProvince(locality.getProvince());
					uPFm.setDistrict(locality.getDistrict());
					uPFm.setCommune(locality.getCommune());
					uPFm.setVillage(locality.getVillage());
					
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
	
}
