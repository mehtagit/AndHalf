package com.gl.ceirreportbuilder.service.impl;

import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gl.ceirreportbuilder.configuration.PropertiesReader;
import com.gl.ceirreportbuilder.model.ReportColumnDb;
import com.gl.ceirreportbuilder.model.ReportStatusDb;
import com.gl.ceirreportbuilder.model.SystemConfigListDb;
import com.gl.ceirreportbuilder.repository.DatabaseTablesRepository;
import com.gl.ceirreportbuilder.repository.ReportColumnDbRepository;
import com.gl.ceirreportbuilder.repository.SystemConfigListRepository;


@Service
public class ReportBuilderServiceImpl {
	private static final Logger logger = LogManager.getLogger(ReportBuilderServiceImpl.class);
	@Autowired
	PropertiesReader propertiesReader;
	@Autowired
	ReportColumnDbRepository reportColumnDbRepository;
	@Autowired
	DatabaseTablesRepository databaseTablesRepository;
	@Autowired
	SystemConfigListRepository systemConfigListRepository;
	
	public boolean processReport( ReportStatusDb reportStatusDb ) {
		Map< String,Map< String, String> > multiQueryReportData = null;
		Map< String, List<String>> singleQueryReportData = null;
		List<ReportColumnDb> reportColumnDetails = null;
		List< String > monthlyYearlyInsertQuery = null;
		DateTimeFormatter dtf  = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		List<SystemConfigListDb> flags = null;
		List<String> keyColumns = new LinkedList<String>();
		boolean processStatus = false;
		String insertValues = "values(";
		String updateValues = null;
		String tempValues  = null;
		String insertQuery = "insert into "+reportStatusDb.getReport().getOutputTable()+"(";
		String dateValue = null;
		String typeFlag = null;
		try {
			flags = systemConfigListRepository.findByTag( "Type_Flag", Sort.by( "id"));
			for( SystemConfigListDb flag : flags ){
				if( flag.getValue().equals( reportStatusDb.getTypeFlag() )) {
					typeFlag = flag.getInterp();
				}
			}
			reportColumnDetails = reportColumnDbRepository.findByReportnameIdAndTypeFlag( reportStatusDb.getReportnameId(), reportStatusDb.getTypeFlag() );
			if( typeFlag.equalsIgnoreCase("Monthly") || typeFlag.equalsIgnoreCase("Yearly") )
				monthlyYearlyInsertQuery = new ArrayList<String>();
			if( Objects.nonNull( reportStatusDb.getReport().getMysqlInputQuery() ) || Objects.nonNull( reportStatusDb.getReport().getOracleInputQuery() )) {
				String[] columns = null;
				String inputQuery = null;
				if( propertiesReader.dialect.toLowerCase().contains("mysql") ) {
					inputQuery = reportStatusDb.getReport().getMysqlInputQuery();
	        	}else {
	        		if( typeFlag.equalsIgnoreCase("Monthly") )
	        			inputQuery = reportStatusDb.getReport().getMonthlyInputQuery();
	        		else
	        			inputQuery = reportStatusDb.getReport().getOracleInputQuery();
	        	}
				singleQueryReportData = databaseTablesRepository.getDataSingleQueryReport( inputQuery,
						typeFlag, reportStatusDb.getRundate().format(dtf));
				if( Objects.isNull(singleQueryReportData) || singleQueryReportData.isEmpty() || singleQueryReportData.size() == 0 )
					singleQueryReportData = databaseTablesRepository.getEmptyRowData(reportColumnDetails, typeFlag, reportStatusDb.getRundate().format(dtf));
				if( singleQueryReportData != null && singleQueryReportData.size() > 0) {
//					logger.info(singleQueryReportData.toString());
					columns = new String[singleQueryReportData.keySet().size()];
					columns = (singleQueryReportData.keySet()).toArray(columns);
					for( int i = 0; i < singleQueryReportData.get(columns[0]).size(); i++ ) {
						if( typeFlag.equalsIgnoreCase("Monthly") || typeFlag.equalsIgnoreCase("Yearly") ) {
							insertQuery  = "MERGE INTO "+reportStatusDb.getReport().getOutputTable()+"_"+typeFlag.toLowerCase()+" table1 USING (SELECT ";
							insertValues = " values(";
							updateValues = " when matched then update set ";
							tempValues = " when not matched then insert(";
							for( String column : columns ) {
								insertQuery += "'"+singleQueryReportData.get(column).get(i)+"' as "+column+",";
								insertValues += "table2."+column+",";
								if( !column.equalsIgnoreCase("created_on")) {
									if( Objects.nonNull( reportStatusDb.getReport().getKeyColumn()) 
											&& !column.equalsIgnoreCase(reportStatusDb.getReport().getKeyColumn()) )
										updateValues += "table1."+column+"=table2."+column+",";
								}
								tempValues += "table1."+column+",";
							}
//							if( typeFlag.equalsIgnoreCase("Monthly") ) {
//								insertQuery = insertQuery.substring( 0, insertQuery.lastIndexOf(","))+" from dual) table2 on(table1.created_on=table2.created_on)";
//							}else {
//								insertQuery = insertQuery.substring( 0, insertQuery.lastIndexOf(","))+" from dual) table2 on(table1.Year=table2.Year)";
//							}
							if( Objects.nonNull( reportStatusDb.getReport().getKeyColumn()) ) {
								insertQuery = insertQuery.substring( 0, insertQuery.lastIndexOf(","))+" from dual) table2 on(table1.created_on=table2.created_on"
										+ " and table1."+reportStatusDb.getReport().getKeyColumn()+"=table2."+reportStatusDb.getReport().getKeyColumn()+")";
							} else {
								insertQuery = insertQuery.substring( 0, insertQuery.lastIndexOf(","))+" from dual) table2 on(table1.created_on=table2.created_on)";
							}
							updateValues = updateValues.substring( 0, updateValues.lastIndexOf(","));
							updateValues += tempValues.substring( 0, tempValues.lastIndexOf(","))+") "
									+insertValues.substring( 0, insertValues.lastIndexOf(","))+")";
//							insertQuery = insertQuery + updateValues + tempValues;
							insertQuery = insertQuery + updateValues;
							logger.info("Monthly/Yearly report row query:["+insertQuery+"]");
							monthlyYearlyInsertQuery.add( insertQuery );
							insertQuery = null;
							insertValues = null;
							updateValues = null;
							tempValues   = null;
						}else {
							if( !propertiesReader.dialect.toLowerCase().contains("mysql") && !insertValues.equals("values(")) {
								insertValues = insertValues.substring( 0, insertValues.lastIndexOf(","))+
										insertQuery.substring( insertQuery.indexOf("insert")+6, insertQuery.lastIndexOf(","))+") values(";
							}
							for( String column : columns ) {
								if( i == 0 ) {
									insertQuery += column+",";
								}
								if( !propertiesReader.dialect.toLowerCase().contains("mysql") && column.equalsIgnoreCase("created_on"))
									insertValues += singleQueryReportData.get(column).get(i)+",";
								else
									insertValues += "'"+singleQueryReportData.get(column).get(i)+"',";
							}
							insertValues = insertValues.substring( 0, insertValues.lastIndexOf(","))+"),(";
						}
					}
				}
				else
					return false;
			}else {
				if( Objects.nonNull( reportStatusDb.getReport().getKeyColumn()) ) {
//					keyColumns.add("date");
					multiQueryReportData = new HashMap<String, Map<String,String>>();
					if( Objects.nonNull( reportStatusDb.getReport().getKeyColumn()) ) {
						keyColumns.addAll( Arrays.asList( reportStatusDb.getReport().getKeyColumn().split(",") ) );
					}
					for( ReportColumnDb reportColumnDb : reportColumnDetails ) {
						if( (Objects.nonNull( reportColumnDb.getColumnQuery()) || Objects.nonNull( reportColumnDb.getOracleQuery())) 
								&& ( reportColumnDb.getColumnDataType().equalsIgnoreCase("String") 
										|| reportColumnDb.getColumnDataType().equalsIgnoreCase("Number")) ) {
							if( propertiesReader.dialect.toLowerCase().contains("mysql") ) {
								multiQueryReportData.put( reportColumnDb.getColumnName(), databaseTablesRepository.getKeyQueryData( reportColumnDb.getColumnQuery(),
										typeFlag, reportStatusDb.getRundate().format(dtf), reportStatusDb.getReport().getKeyColumn(),
										reportColumnDb.getColumnName() ));
							}else {
								multiQueryReportData.put( reportColumnDb.getColumnName(), databaseTablesRepository.getKeyQueryData( reportColumnDb.getOracleQuery(),
										typeFlag, reportStatusDb.getRundate().format(dtf), reportStatusDb.getReport().getKeyColumn(),
										reportColumnDb.getColumnName() ));
							}
						}else {
							if( propertiesReader.dialect.toLowerCase().contains("mysql") )
								dateValue = databaseTablesRepository.getColumnDataDateBasedSingleRow(reportColumnDb.getColumnQuery(),
										typeFlag, reportStatusDb.getRundate().format(dtf));
							else
								dateValue = databaseTablesRepository.getColumnDataDateBasedSingleRow(reportColumnDb.getOracleQuery(),
										typeFlag, reportStatusDb.getRundate().format(dtf));
						}
					}
					logger.info("Multi report query data:["+multiQueryReportData.toString()+"]");
					int i = 0;
					for( String key : multiQueryReportData.get(keyColumns.get(0)).keySet()) {
						if( !propertiesReader.dialect.toLowerCase().contains("mysql") && !insertValues.equals("values(")) {
							insertValues = insertValues.substring( 0, insertValues.lastIndexOf(","))+
									insertQuery.substring( insertQuery.indexOf("insert")+6, insertQuery.lastIndexOf(","))+") values(";
						}
						for( ReportColumnDb reportColumnDb : reportColumnDetails ) {
							if( i == 0 ) {
								insertQuery += reportColumnDb.getColumnName()+",";
							}
							if( reportColumnDb.getColumnDataType().equalsIgnoreCase("String")
									|| reportColumnDb.getColumnDataType().equalsIgnoreCase("Number")) {
								if( multiQueryReportData.get( reportColumnDb.getColumnName()).containsKey( key ) && 
										Objects.nonNull( multiQueryReportData.get( reportColumnDb.getColumnName() ).get(key) )) {
									insertValues += "'"+multiQueryReportData.get( reportColumnDb.getColumnName() )
									.get(key)+"',";
								}else {
									if(reportColumnDb.getColumnDataType().equalsIgnoreCase("Number"))
										insertValues += "'0',";
									else
										insertValues += "'NA',";
								}
							}else {
								insertValues += dateValue+",";
							}
						}
						insertValues = insertValues.substring( 0, insertValues.lastIndexOf(","))+"),(";
						i++;
					}
				}else {
					String data = null;
					if( typeFlag.equalsIgnoreCase("Monthly") || typeFlag.equalsIgnoreCase("Yearly") ) {
						insertQuery  = "MERGE INTO "+reportStatusDb.getReport().getOutputTable()+"_"+typeFlag.toLowerCase()+" table1 USING (SELECT ";
						insertValues = " values(";
						updateValues = " when matched then update set ";
						tempValues = " when not matched then insert(";
						for( ReportColumnDb reportColumnDb : reportColumnDetails ) {
							if( propertiesReader.dialect.toLowerCase().contains("mysql") ) {
								data = databaseTablesRepository.getColumnDataDateBasedSingleRow( reportColumnDb.getColumnQuery(), typeFlag,
										reportStatusDb.getRundate().format(dtf));
							}else {
								data = databaseTablesRepository.getColumnDataDateBasedSingleRow( reportColumnDb.getOracleQuery(), typeFlag,
										reportStatusDb.getRundate().format(dtf));
							}
							if( data != null && !data.equals("query error") ) {
								insertQuery += "'"+data+"' as "+reportColumnDb.getColumnName()+",";
							}else if( data != null && data.equals("query error") ){
								return false;
							}else {
								insertQuery += "'0' as "+reportColumnDb.getColumnName()+",";
							}
							insertValues += "table2."+reportColumnDb.getColumnName()+",";
							if( !reportColumnDb.getColumnName().equalsIgnoreCase("created_on") )
								updateValues += "table1."+reportColumnDb.getColumnName()+"=table2."+reportColumnDb.getColumnName()+",";
							tempValues += "table1."+reportColumnDb.getColumnName()+",";
						}
//						if( typeFlag.equalsIgnoreCase("Monthly") ) {
//							insertQuery = insertQuery.substring( 0, insertQuery.lastIndexOf(","))+" from dual) table2 on(table1.Month=table2.Month)";
//						}else {
//							insertQuery = insertQuery.substring( 0, insertQuery.lastIndexOf(","))+" from dual) table2 on(table1.Year=table2.Year)";
//						}
						insertQuery = insertQuery.substring( 0, insertQuery.lastIndexOf(","))+" from dual) table2 on(table1.created_on=table2.created_on)";
						updateValues = updateValues.substring( 0, updateValues.lastIndexOf(","));
						updateValues += tempValues.substring( 0, tempValues.lastIndexOf(","))+") "
								+insertValues.substring( 0, insertValues.lastIndexOf(","))+")";
//						insertQuery = insertQuery + updateValues + tempValues;
						insertQuery = insertQuery + updateValues ;
						logger.info("Monthly/Yearly report row query:["+insertQuery+"]");
						monthlyYearlyInsertQuery.add( insertQuery );
						insertQuery = null;
						insertValues = null;
						updateValues = null;
						tempValues   = null;
					}else{
						for( ReportColumnDb reportColumnDb : reportColumnDetails ) {
							if( propertiesReader.dialect.toLowerCase().contains("mysql") ) {
								data = databaseTablesRepository.getColumnDataDateBasedSingleRow( reportColumnDb.getColumnQuery(), typeFlag,
										reportStatusDb.getRundate().format(dtf));
							}else {
								data = databaseTablesRepository.getColumnDataDateBasedSingleRow( reportColumnDb.getOracleQuery(), typeFlag,
										reportStatusDb.getRundate().format(dtf));
							}
							insertQuery += reportColumnDb.getColumnName()+",";
							if( !propertiesReader.dialect.toLowerCase().contains("mysql") && reportColumnDb.getColumnName().equalsIgnoreCase("created_on"))
								insertValues += data+",";
							else {
								if( data != null && !data.equals("query error") ) {
									insertValues += "'"+data+"',";
								}else if( data != null && data.equals("query error") ){
									return false;
								}else {
									if( reportColumnDb.getColumnDataType().equalsIgnoreCase("String"))
										insertValues += "'NA',";
									else
										insertValues += "'0',";
								}
							}
						}
						data = null;
						insertValues = insertValues.substring( 0, insertValues.lastIndexOf(","))+"),(";
					}
				}
				
			}
			if( typeFlag.equalsIgnoreCase("Monthly") || typeFlag.equalsIgnoreCase("Yearly") ) {
				if( monthlyYearlyInsertQuery != null && monthlyYearlyInsertQuery.isEmpty() )
					return true;
				else
					processStatus = databaseTablesRepository.insertMultipleRowInReport(monthlyYearlyInsertQuery);
			}else {
				if( propertiesReader.dialect.toLowerCase().contains("mysql")){
					insertQuery = insertQuery.substring( 0, insertQuery.lastIndexOf(","))+") "+
							insertValues.substring( 0, insertValues.lastIndexOf(","));
				}else {
					insertQuery = "insert all"+insertQuery.substring( insertQuery.indexOf("insert")+6, insertQuery.lastIndexOf(","))+") "+
							insertValues.substring( 0, insertValues.lastIndexOf(",")) + " select * from dual";
				}
				logger.info("Final insert query:["+insertQuery+"]");
				processStatus = databaseTablesRepository.insertDataInReport(insertQuery);
			}
		}catch( Exception ex) {
			logger.error("Error in process report", ex.getMessage(), ex);
		}
		return processStatus;
	}
	
	public boolean createDirectory( String path ) {
		File file = null;
		boolean dirStatus = false;
		try {
			file = new File(path);
			if( file.exists() ) {
				logger.info("Path already exist. Path:["+path+"]");
				dirStatus = true;
			}else {
				file.mkdirs();
				logger.info("Path created. Path:["+path+"]");
				dirStatus = true;
			}
		}catch( Exception ex) {
			logger.error("Error in createDirectory method:", ex.getMessage(), ex);
		}
		return dirStatus;
	}
}
