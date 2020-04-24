package com.gl.ceir.config.service.impl;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

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
import com.gl.ceir.config.model.FileDumpMgmt;
import com.gl.ceir.config.model.FileDumpFilterRequest;
import com.gl.ceir.config.model.ResponseCountAndQuantity;
import com.gl.ceir.config.model.SearchCriteria;
import com.gl.ceir.config.model.constants.Datatype;
import com.gl.ceir.config.model.constants.FileType;
import com.gl.ceir.config.model.constants.SearchOperation;
import com.gl.ceir.config.repository.FileDumpMgmtRepository;
import com.gl.ceir.config.specificationsbuilder.FileDumpSpecificationBuilder;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

@Service
public class ListFileDetailsImpl {

	private static final Logger logger = LogManager.getLogger(ListFileDetailsImpl.class);

	@Autowired
	FileDumpMgmtRepository fileDumpMgmtRepository;

	@Autowired
	PropertiesReader propertiesReader;

	@Autowired
	FileStorageProperties fileStorageProperties;

	public List<FileDumpMgmt> getByListType(String listType){
		try {

			return fileDumpMgmtRepository.getByServiceDump(listType);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	public FileDumpMgmt topDataByDumpType(String dumpType) {
		try {
			return fileDumpMgmtRepository.findTopByDumpTypeOrderByIdDesc(dumpType);
		}
		catch(Exception e) {
			e.printStackTrace();
			FileDumpMgmt fileDumpMgmt=new FileDumpMgmt();
			return fileDumpMgmt;
		}
	}

	public FileDumpMgmt saveFileDumpMgmt(FileDumpMgmt fileDumpMgmt) {
		try {
			return fileDumpMgmtRepository.save(fileDumpMgmt);
		}
		catch(Exception e) {
			e.printStackTrace();
			FileDumpMgmt fileData=new FileDumpMgmt();
			return fileData;
		}
	}

	public Page<FileDumpMgmt> getFilterPagination( FileDumpFilterRequest filterRequest, Integer pageNo, Integer pageSize) {
		try {
			Pageable pageable = PageRequest.of(pageNo, pageSize, new Sort(Sort.Direction.DESC, "createdOn"));
			FileDumpSpecificationBuilder osb = new FileDumpSpecificationBuilder(propertiesReader.dialect);

			if(Objects.nonNull(filterRequest.getStartDate()) && !filterRequest.getStartDate().equals(""))
				osb.with(new SearchCriteria("createdOn", filterRequest.getStartDate() , SearchOperation.GREATER_THAN, Datatype.DATE));

			if(Objects.nonNull(filterRequest.getEndDate()) && !filterRequest.getEndDate().equals(""))
				osb.with(new SearchCriteria("createdOn",filterRequest.getEndDate() , SearchOperation.LESS_THAN, Datatype.DATE));

			if(Objects.nonNull(filterRequest.getFileType()) && !filterRequest.getFileType().equals(-1))
				osb.with(new SearchCriteria("fileType", filterRequest.getFileType(), SearchOperation.EQUALITY, Datatype.INT));

			if(Objects.nonNull(filterRequest.getServiceDump()) && !filterRequest.getServiceDump().equals(-1))
				osb.with(new SearchCriteria("serviceDump", filterRequest.getServiceDump(), SearchOperation.EQUALITY, Datatype.INT));

			if(Objects.nonNull(filterRequest.getSearchString()) && !filterRequest.getSearchString().equals("")){
				if( filterRequest.getFileType() == null || filterRequest.getFileType().equals(-1) ) {
					for( FileType fileType : FileType.values()) {
						if( fileType.toString().toLowerCase().contains( filterRequest.getSearchString().toLowerCase() )) {
							osb.orSearch(new SearchCriteria("fileType", fileType.getCode(), SearchOperation.EQUALITY, Datatype.INT));
						}
					}
				}
				osb.orSearch(new SearchCriteria("fileName", filterRequest.getSearchString(), SearchOperation.LIKE, Datatype.STRING));
			}
			return fileDumpMgmtRepository.findAll(osb.build(), pageable);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}

	}

	public FileDetails getFilterInFile(FileDumpFilterRequest filterRequest, Integer pageNo, Integer pageSize) {
		String fileName = null;
		Writer writer   = null;
		DateTimeFormatter dtf  = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String filePath  = fileStorageProperties.getFileDumpDownloadDir();
		StatefulBeanToCsvBuilder<FileDumpMgmt> builder = null;
		StatefulBeanToCsv<FileDumpMgmt> csvWriter      = null;
		try {
			pageSize = fileStorageProperties.getMaxFileRecord();
			List<FileDumpMgmt> fileRecords = this.getFilterPagination(filterRequest, pageNo, pageSize).getContent();
			fileName = LocalDateTime.now().format(dtf).replace(" ", "_")+"_file_dump.csv";
			writer = Files.newBufferedWriter(Paths.get(filePath+fileName));
			builder = new StatefulBeanToCsvBuilder<FileDumpMgmt>(writer);
			csvWriter = builder.withQuotechar(CSVWriter.DEFAULT_QUOTE_CHARACTER).build();
			if( fileRecords.size() > 0 ) {
				csvWriter.write(fileRecords);
			}
			return new FileDetails( fileName, filePath, fileStorageProperties.getFileDumpDownloadLink()+fileName ); 
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

	public FileDetails getFile(String fileName) {
		String filePath  = fileStorageProperties.getFileDumpDownloadDir();
		try {
			return new FileDetails( fileName, filePath, fileStorageProperties.getFileDumpDownloadLink()+fileName ); 
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	public ResponseCountAndQuantity getFileDumpCount( Integer serviceDump ) {
		try {
			logger.info("Going to get FileDump count for serviceDump["+serviceDump+"].");
			return fileDumpMgmtRepository.getFileDumpCount(serviceDump);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseCountAndQuantity(0,0);
		}
	}

}