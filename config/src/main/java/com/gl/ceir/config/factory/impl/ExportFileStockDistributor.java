package com.gl.ceir.config.factory.impl;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gl.ceir.config.exceptions.ResourceServicesException;
import com.gl.ceir.config.factory.ExportFile;
import com.gl.ceir.config.model.FileDetails;
import com.gl.ceir.config.model.FilterRequest;
import com.gl.ceir.config.model.StockMgmt;
import com.gl.ceir.config.model.SystemConfigurationDb;
import com.gl.ceir.config.model.constants.Alerts;
import com.gl.ceir.config.model.constants.SubFeatures;
import com.gl.ceir.config.model.file.StockDistribitorFileModel;
import com.gl.ceir.config.service.impl.AlertServiceImpl;
import com.gl.ceir.config.service.impl.StockServiceImpl;
import com.gl.ceir.config.util.CustomMappingStrategy;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

@Component
public class ExportFileStockDistributor implements ExportFile{

	private static final Logger logger = LogManager.getLogger(ExportFileStockDistributor.class);

	@Autowired
	StockServiceImpl stockServiceImpl;

	@Autowired
	AlertServiceImpl alertServiceImpl;

	@Override
	public FileDetails export(FilterRequest filterRequest, String source, DateTimeFormatter dtf, DateTimeFormatter dtf2,
			String filePath, SystemConfigurationDb link) {
		Writer writer   = null;
		String fileName = null;
		
		StockDistribitorFileModel stockDistribitorFileModel = null;
		List<StockDistribitorFileModel> fileRecords = null;
		StatefulBeanToCsvBuilder<StockDistribitorFileModel> builder = null;
		StatefulBeanToCsv<StockDistribitorFileModel> csvWriter = null;
		CustomMappingStrategy<StockDistribitorFileModel> mappingStrategy = new CustomMappingStrategy<>();
		mappingStrategy.setType(StockDistribitorFileModel.class);

		try {
			fileName = LocalDateTime.now().format(dtf2).replace(" ", "_") + "_Distributor_Stock.csv";
			writer = Files.newBufferedWriter(Paths.get(filePath+fileName));

			builder = new StatefulBeanToCsvBuilder<>(writer);
			csvWriter = builder.withMappingStrategy(mappingStrategy).withSeparator(',').withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).build();

			List<StockMgmt> stockMgmts = stockServiceImpl.getAll(filterRequest, source);

			if(stockMgmts.isEmpty()) {
				csvWriter.write(new StockDistribitorFileModel());
			}else {
				fileRecords = new ArrayList<>();
				
				for(StockMgmt stockMgmt : stockMgmts) {
					stockDistribitorFileModel = new StockDistribitorFileModel();
					stockDistribitorFileModel.setStockStatus(stockMgmt.getStateInterp());
					stockDistribitorFileModel.setTxnId( stockMgmt.getTxnId());
					stockDistribitorFileModel.setCreatedOn(stockMgmt.getCreatedOn().format(dtf));
					stockDistribitorFileModel.setModifiedOn( stockMgmt.getModifiedOn().format(dtf));
					stockDistribitorFileModel.setFileName( stockMgmt.getFileName());
					stockDistribitorFileModel.setSupplierName(stockMgmt.getSuplierName());
					stockDistribitorFileModel.setQuantity(stockMgmt.getQuantity());
					stockDistribitorFileModel.setDeviceQuantity(stockMgmt.getDeviceQuantity());

					fileRecords.add(stockDistribitorFileModel);
				}

				csvWriter.write(fileRecords);
			}

			stockServiceImpl.addInAuditTrail(Long.valueOf(filterRequest.getUserId()), "NA", SubFeatures.EXPORT,filterRequest.getRoleType());

			return new FileDetails( fileName, filePath, link.getValue() + fileName ); 
		}catch (Exception e) {
			logger.error(e.getMessage(), e);

			Map<String, String> bodyPlaceHolderMap = new HashMap<>();
			bodyPlaceHolderMap.put("<feature>", "Stock Management");
			bodyPlaceHolderMap.put("<sub_feature>", SubFeatures.EXPORT);
			alertServiceImpl.raiseAnAlert(Alerts.ALERT_011, filterRequest.getUserId(), bodyPlaceHolderMap);

			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}finally {
			try {

				if( Objects.nonNull(writer) )
					writer.close();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}
}
