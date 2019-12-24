package com.gl.ceir.config.service.impl;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

import com.gl.ceir.config.configuration.FileStorageProperties;
import com.gl.ceir.config.configuration.PropertiesReader;
import com.gl.ceir.config.exceptions.ResourceServicesException;
import com.gl.ceir.config.model.FileDetails;
import com.gl.ceir.config.model.GrievanceGenricResponse;
import com.gl.ceir.config.model.ResponseCountAndQuantity;
import com.gl.ceir.config.model.SearchCriteria;
import com.gl.ceir.config.model.TypeApproveFileModel;
import com.gl.ceir.config.model.TypeApproveFilter;
import com.gl.ceir.config.model.TypeApprovedDb;
import com.gl.ceir.config.model.constants.Datatype;
import com.gl.ceir.config.model.constants.SearchOperation;
import com.gl.ceir.config.repository.TypeApproveRepository;
import com.gl.ceir.config.specificationsbuilder.TypeApprovedSpecificationBuilder;
import com.gl.ceir.config.util.HttpResponse;

import java.nio.file.Files;
import java.nio.file.Paths;
@Service
public class TypeApproveServiceImpl {

	private static final Logger log = LogManager.getLogger(TypeApproveServiceImpl.class);
	
	@Autowired
	
	TypeApproveRepository typeApproveRepo;
	
	@Autowired
	PropertiesReader propertiesReader;

	@Autowired
	FileStorageProperties fileStorageProperties;
	
	
	public GrievanceGenricResponse saveTypeApprove(TypeApprovedDb typeApprove) {
		log.info("inside type approved db controller");
		typeApprove.setCreatedOn(new Date());
		typeApprove.setModifiedOn(new Date());  
		log.info("typeApprove data:  "+typeApprove);

		try 
		{   
			TypeApprovedDb output=typeApproveRepo.save(typeApprove);
			if(output!=null) {
//				HttpResponse response=new HttpResponse();
//				response.setStatusCode(200);
//				response.setResponse("Type Approved data has been sucessfully saved");
//				return response;
				return new GrievanceGenricResponse(200,"Type Approved data has been sucessfully saved",String.valueOf(output.getId()));
			}
			else {
//				HttpResponse response=new HttpResponse();
//				response.setStatusCode(500);
//				response.setResponse("Type Approved data failed to saved");
//				return response;
				return new GrievanceGenricResponse(500,"Type Approved data failed to saved","0");
			} 
		}
		catch(Exception e) {
			log.info("Exception found ="+e.getMessage());
//			HttpResponse response=new HttpResponse();
//			response.setStatusCode(409);
//			response.setResponse("Oops something wrong happened");
//			return response;
			return new GrievanceGenricResponse(409,"Oops something wrong happened","0");
		}
	}
	public ResponseEntity<?> viewTypeApproveById(long id) {
		log.info("inside type approve view data by Id");
		try {
			log.info("type approve id: "+id);
			TypeApprovedDb output=typeApproveRepo.findById(id);
			if(output==null) {
				HttpResponse response=new HttpResponse();
				response.setResponse("please enter correct Id");
				response.setStatusCode(204);
				return new ResponseEntity<>(response,HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>(output,HttpStatus.OK);
			}
		}
		catch(Exception e) {
			log.info("Exception found ="+e.getMessage());
			HttpResponse response=new HttpResponse();
			response.setResponse("Oops something wrong happened");
			response.setStatusCode(409);     
			return new ResponseEntity<>(response,HttpStatus.OK);
		}

	
	}
	
	
	public HttpResponse deleteTypeApprove(long id) {
		log.info("inside type approve db controller");
		try 
		{   
            log.info("type approve id:  "+id); 
			TypeApprovedDb typeApprovedDb=typeApproveRepo.findById(id);
			if(typeApprovedDb!=null) {
				TypeApprovedDb output=typeApproveRepo.save(typeApprovedDb);
				if(output!=null) {
					HttpResponse response=new HttpResponse();
					response.setStatusCode(200);
					response.setResponse("Type approve data has been sucessfully delete");
					return response;	
				}
				else {
					HttpResponse response=new HttpResponse();
					response.setStatusCode(500);
					response.setResponse("Type approve data failed to delete");
					return response;
				}
			}
			else {
				HttpResponse response=new HttpResponse();
				response.setStatusCode(204);
				response.setResponse("type approve id is wrong");
				return response;
			} 
		}
		catch(Exception e) {
			log.info("Exception found ="+e.getMessage());
			HttpResponse response=new HttpResponse();
			response.setStatusCode(409);
			response.setResponse("Oops something wrong happened");
			return response;
		}
	}
	
	public GrievanceGenricResponse updateTypeApprove(TypeApprovedDb typeApprove) {
		log.info("inside update type approved db controller");
		typeApprove.setModifiedOn(new Date());  
		log.info("typeApprove data:  "+typeApprove);

		try 
		{   
			TypeApprovedDb typeApprovedDb=typeApproveRepo.findById(typeApprove.getId());
			if(typeApprovedDb==null) {
//				HttpResponse response=new HttpResponse();
//				response.setResponse("please enter correct Id");
//				response.setStatusCode(204);
//				return response;

				return new GrievanceGenricResponse(204,"please enter correct Id","0");
			}
			else {
				typeApprove.setCreatedOn(typeApprovedDb.getCreatedOn());
				typeApprove.setModifiedOn(new Date());
			TypeApprovedDb output=typeApproveRepo.save(typeApprove);
			if(output!=null) {
//				HttpResponse response=new HttpResponse();
//				response.setStatusCode(200);
//				response.setResponse("Type Approved data has been sucessfully update");
//				return response; 
				return new GrievanceGenricResponse(200,"Type Approved data has been sucessfully update",String.valueOf(typeApprove.getId()));
			}
			else {
//				HttpResponse response=new HttpResponse();
//				response.setStatusCode(500);
//				response.setResponse("Type Approved data failed to update");
//				return response;
				return new GrievanceGenricResponse(500,"Type Approved data failed to update",String.valueOf(typeApprove.getId()));
			} 
		}
		}
		catch(Exception e) {
			log.info("Exception found ="+e.getMessage());
//			HttpResponse response=new HttpResponse();
//			response.setStatusCode(409);
//			response.setResponse("Oops something wrong happened");
//			return response;
			return new GrievanceGenricResponse(409,"Oops something wrong happened","0");
		}
	}
	
	public Page<TypeApprovedDb>  viewTypeApprovdeData(TypeApproveFilter filterRequest, Integer pageNo, Integer pageSize){
		log.info("inside type approve db view  data controller");
		try {
             log.info("TypeApproveFilter data: "+filterRequest );
             log.info("pageNo= "+pageNo +"and pageSize="+pageSize);
             Pageable pageable = PageRequest.of(pageNo, pageSize, new Sort(Sort.Direction.DESC, "createdOn"));

			TypeApprovedSpecificationBuilder uPSB = new TypeApprovedSpecificationBuilder(propertiesReader.dialect);	

			if(Objects.nonNull(filterRequest.getStartDate()) && !filterRequest.getStartDate().equals(""))
				uPSB.with(new SearchCriteria("requestDate",filterRequest.getStartDate(), SearchOperation.GREATER_THAN, Datatype.DATE));

			if(Objects.nonNull(filterRequest.getEndDate()) && !filterRequest.getEndDate().equals(""))
				uPSB.with(new SearchCriteria("requestDate",filterRequest.getEndDate(), SearchOperation.LESS_THAN, Datatype.DATE));

			if(Objects.nonNull(filterRequest.getTac()) && !filterRequest.getTac().equals(""))
				uPSB.with(new SearchCriteria("tac",filterRequest.getTac(), SearchOperation.EQUALITY, Datatype.STRING));
			
			if(Objects.nonNull(filterRequest.getStatus()) && !filterRequest.getStatus().equals(-1))
				uPSB.with(new SearchCriteria("approveStatus",filterRequest.getStatus(), SearchOperation.EQUALITY, Datatype.INT));
			
			if(Objects.nonNull(filterRequest.getSearchString()) && !filterRequest.getSearchString().equals("")){
				uPSB.orSearch(new SearchCriteria("manufacturerName", filterRequest.getSearchString(), SearchOperation.LIKE, Datatype.STRING));
				uPSB.orSearch(new SearchCriteria("country", filterRequest.getSearchString(), SearchOperation.LIKE, Datatype.STRING));
				uPSB.orSearch(new SearchCriteria("tac", filterRequest.getSearchString(), SearchOperation.LIKE, Datatype.STRING));
			}
            log.info("going to fetch TypeApprovedDb data");
			return typeApproveRepo.findAll(uPSB.build(),pageable);

		} catch (Exception e) {
			log.info("Exception found ="+e.getMessage());
			return null;
		}
	}
	
	public FileDetails getFilterTACInFile(TypeApproveFilter filterRequest, Integer pageNo, Integer pageSize) {
		String fileName = null;
		Writer writer   = null;
		TypeApproveFileModel tfm = null;
		DateTimeFormatter dtf    = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String filePath  = fileStorageProperties.getTacDownloadDir();
		StatefulBeanToCsvBuilder<TypeApproveFileModel> builder = null;
		StatefulBeanToCsv<TypeApproveFileModel> csvWriter      = null;
		List< TypeApproveFileModel> fileRecords                = null;
		try {
			List<TypeApprovedDb> typeApproveList = this.viewTypeApprovdeData(filterRequest, pageNo, pageSize).getContent();
			fileName = LocalDateTime.now().format(dtf).replace(" ", "_")+"_TACsInfo.csv";
			writer = Files.newBufferedWriter(Paths.get(filePath+fileName));
			builder = new StatefulBeanToCsvBuilder<TypeApproveFileModel>(writer);
			csvWriter = builder.withQuotechar(CSVWriter.DEFAULT_QUOTE_CHARACTER).build();
			if( typeApproveList.size() > 0 ) {
				fileRecords = new ArrayList< TypeApproveFileModel >();
				for( TypeApprovedDb tad : typeApproveList ) {
					tfm = new TypeApproveFileModel();
					if( tad.getRequestDate() != null ) {
						tfm.setRequestDate( tad.getRequestDate().toString());
					}
					tfm.setManufacturerName( tad.getManufacturerName());
					tfm.setCountry( tad.getCountry());
					tfm.setTac( tad.getTac());
					tfm.setStatus( tad.getStateInterp());
					tfm.setUsername( tad.getUserForTypeApprove().getUsername());
					tfm.setTxnId( tad.getTxnId());
					tfm.setCreatedOn( tad.getCreatedOn().toString());
					tfm.setModifiedOn( tad.getModifiedOn().toString());
					tfm.setFileName( tad.getFile());
					tfm.setRemark( tad.getRemark());
					//System.out.println(tfm.toString());
					fileRecords.add(tfm);
				}
				csvWriter.write(fileRecords);
			}
			return new FileDetails( fileName, filePath, fileStorageProperties.getTacDownloadLink()+fileName ); 
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
	
	public FileDetails getFile(String fileName) {
		String filePath  = fileStorageProperties.getTacDownloadDir();
		try {
			return new FileDetails( fileName, filePath, fileStorageProperties.getTacDownloadLink()+fileName ); 
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}
	
	public ResponseCountAndQuantity getTACCount( Integer userId, Integer approveStatus ) {
		try {
			log.info("Going to get TAC count for userId["+userId+"] and approveStatus:["+approveStatus+"].");
			return typeApproveRepo.getTypeApproveCount(approveStatus, userId);
			//return new ResponseCountAndQuantity(0,0);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseCountAndQuantity(0,0);
		}
	}
	
}



