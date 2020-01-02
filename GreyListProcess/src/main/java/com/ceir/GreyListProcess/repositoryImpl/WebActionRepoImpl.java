//package com.ceir.GreyListProcess.repositoryImpl;
//
//import java.util.List;
//import java.util.Objects;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//
//import com.ceir.GreyListProcess.configuration.PropertiesReader;
//import com.ceir.GreyListProcess.model.FilterRequest;
//import com.ceir.GreyListProcess.model.SearchCriteria;
//import com.ceir.GreyListProcess.model.WebActionDb;
//import com.ceir.GreyListProcess.model.constants.Datatype;
//import com.ceir.GreyListProcess.model.constants.SearchOperation;
//import com.ceir.GreyListProcess.specificationsbuilder.SpecificationBuilder;
//import com.gl.ceir.config.exceptions.ResourceServicesException;
//import com.gl.ceir.config.repository.WebActionDbRepository;
//
//
//public class WebActionRepoImpl {
//
//	@Autowired
//	PropertiesReader propertiesReader;
//	 
//	@Autowired
//	WebActionDbRepository webActionRepo;
//	public List<WebActionDb> getFilterPaginationReport(FilterRequest filterData, Integer pageNo, 
//			Integer pageSize) {
//	
//		//Pageable pageable = PageRequest.of(pageNo, pageSize);
//		SpecificationBuilder<WebActionDb> cmsb = new SpecificationBuilder<WebActionDb>(propertiesReader.dialect);
//
//		if(Objects.nonNull(filterData.getStartDate()))
//			cmsb.with(new SearchCriteria("createdOn",filterData.getStartDate() , SearchOperation.GREATER_THAN, Datatype.DATE));
//		if(Objects.nonNull(filterData.getEndDate()))
//			cmsb.with(new SearchCriteria("createdOn", filterData.getEndDate(), SearchOperation.EQUALITY, Datatype.DATE));
//		try {
//		return webActionRepo.findAll(cmsb.build());	
//		}
//		catch(Exception e) {
//			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
//		}
//	}
//}