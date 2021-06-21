package com.gl.ceirreportbuilder.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.EntityManagerFactoryInfo;
import org.springframework.stereotype.Repository;

import com.gl.ceirreportbuilder.configuration.PropertiesReader;
import com.gl.ceirreportbuilder.model.ReportColumnDb;
import com.gl.ceirreportbuilder.model.ReportDb;

@Repository
public class DatabaseTablesRepository {
	private static final Logger logger = LogManager.getLogger(DatabaseTablesRepository.class);
	
	@Autowired
	PropertiesReader propertiesReader;
	
	@PersistenceContext
    private EntityManager em;
	
	public Map< String, String > getColumnDataBasedOnKey( ReportColumnDb reportColumnDb, String typeFlag, String reportDate ) {
        //SessionImplementor sessionImp = (org.hibernate.engine.spi.SessionImplementor) em.getDelegate();
        Map< String, String > resultData = null;
        String[] formatedDate = null;
        Connection conn = null;
        Statement stmt = null;
        ResultSet res  = null;
        String query = null;
        String key = null;
        try {
        	if( propertiesReader.dialect.toLowerCase().contains("mysql") ) {
        		query = reportColumnDb.getColumnQuery();
        	}else {
        		query = reportColumnDb.getOracleQuery();
        	}
        	formatedDate = this.getStartAndEndDate(typeFlag, reportDate);
        	query = query.replace( "$startDate", formatedDate[0]);
        	query = query.replace( "$endDate", formatedDate[1]);
            logger.info("Query:["+query+"]");
        	conn = this.getConnection();
            stmt = conn.createStatement();//sessionImp.connection().createStatement();
            res  = stmt.executeQuery( query );
            resultData    = new HashMap< String, String>();
            while (res.next()) {
            	key = "";
		        for( int i = 1; i <= res.getMetaData().getColumnCount(); i++) {
		        	logger.info("Column name:["+res.getMetaData().getColumnName(i)+"]");
		        	if(res.getMetaData().getColumnName(i).equals(reportColumnDb.getColumnName())) {
		        		logger.info("Key:["+key+"] and value:["+res.getString(i)+"]");
		        		resultData.put( key , res.getString(i) );
		        	}else {
		        		if(key.equals("")) {
		        			key = res.getString(i);
		        		}else {
		        			key += "="+res.getString(i);
		        		}
		        	}
		        }
		        key = null;
            }
        }catch (Exception ex) {
        	logger.error(ex.getMessage(), ex);
        }finally {
			try {
				if( res != null )
					res.close();
				if( stmt != null )
					stmt.close();
				if( conn != null )
					conn.close();
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
			}
        }
        return resultData;
    }
	
	public Map< String,List< String> > getEmptyRowData( List<ReportColumnDb> columnDbs, String typeFlag, String reportDate ) {
        //SessionImplementor sessionImp = (org.hibernate.engine.spi.SessionImplementor) em.getDelegate();
        Map< String,List< String> > resultData = null;
        List< String > colData = null;
        String[] formatedDate = null;
        Connection conn = null;
        Statement stmt = null;
        ResultSet res  = null;
        String query = null;
        try {
        	formatedDate = this.getStartAndEndDate(typeFlag, reportDate);
        	query = "select "+formatedDate[0]+" as temp from dual";
            logger.info("Query:["+query+"]");
        	conn = this.getConnection();
            stmt = conn.createStatement();
            res  = stmt.executeQuery( query );
            resultData    = new LinkedHashMap< String, List< String> >();
            while (res.next()) {
		        for( ReportColumnDb column : columnDbs ) {
		        	colData = new ArrayList<String>();
		        	if( column.getColumnDataType().equalsIgnoreCase("Date") || column.getColumnDataType().equalsIgnoreCase("Month") 
		        			|| column.getColumnDataType().equalsIgnoreCase("Year") ) {
		        		colData.add("to_date('"+res.getString(1)+"','YYYY-MM-DD')");
		        	}else if( column.getColumnDataType().equalsIgnoreCase("String") ) {
		        		colData.add("NA");
		        	}else {
		        		colData.add("0");
		        	}
	        		resultData.put( column.getColumnName(), colData);
	        		colData = null;
		        }
            }
        }catch (Exception ex) {
        	logger.error(ex.getMessage(), ex);
        	resultData = null;
        }finally {
			try {
				if( res != null )
					res.close();
				if( stmt != null )
					stmt.close();
				if( conn != null )
					conn.close();
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
			}
        }
        return resultData;
    }
	
	public Map< String,List< String> > getDataSingleQueryReport( String query, String typeFlag, String reportDate ) {
        //SessionImplementor sessionImp = (org.hibernate.engine.spi.SessionImplementor) em.getDelegate();
        Map< String,List< String> > resultData = null;
        List< String > colData = null;
        String[] formatedDate = null;
        Connection conn = null;
        Statement stmt = null;
        ResultSet res  = null;
        try {
        	formatedDate = this.getStartAndEndDate(typeFlag, reportDate);
        	query = query.replace( "$startDate", formatedDate[0]);
        	query = query.replace( "$endDate", formatedDate[1]);
            logger.info("Query:["+query+"]");
        	conn = this.getConnection();
            stmt = conn.createStatement();
            res  = stmt.executeQuery( query );
            resultData    = new LinkedHashMap< String, List< String> >();
            while (res.next()) {
		        for( int i = 1; i <= res.getMetaData().getColumnCount(); i++) {
		        	if( res.isFirst()) {
		        		colData = new ArrayList<String>();
		        	}else {
		        		colData = resultData.get( res.getMetaData().getColumnName(i));
		        	}
		        	colData.add( res.getString(i));
		        	resultData.put( res.getMetaData().getColumnName(i), colData);
		        	colData = null;
		        }
            }
        }catch (Exception ex) {
        	logger.error(ex.getMessage(), ex);
        	resultData = null;
        }finally {
			try {
				if( res != null )
					res.close();
				if( stmt != null )
					stmt.close();
				if( conn != null )
					conn.close();
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
			}
        }
        return resultData;
    }
	

	public Map< String,String > getKeyQueryData( String query, String typeFlag, String reportDate, String keyColumns, String column ) {
//        SessionImplementor sessionImp = (org.hibernate.engine.spi.SessionImplementor) em.getDelegate();
        Map< String, String > resultData = null;
        List< String > columnData = null;
        String[] formatedDate = null;
        Connection conn = null;
        Statement stmt = null;
        ResultSet res  = null;
        String[] keys = null;
        String key = null;
        try {
        	formatedDate = this.getStartAndEndDate(typeFlag, reportDate);
        	query = query.replace( "$startDate", formatedDate[0]);
        	query = query.replace( "$endDate", formatedDate[1]);
            logger.info("Query:["+query+"]");
        	conn = this.getConnection();
            stmt = conn.createStatement();//sessionImp.connection().createStatement();
            res  = stmt.executeQuery( query );
            resultData    = new HashMap<String, String>();
            while (res.next()) {
            	if( keyColumns.contains(",")) {
            		keys = keyColumns.split(",");
            		key = "";
            		for( String k : keys ) {
            			key += res.getString(k.toUpperCase())+"=";
            		}
            		key = key.substring(0, key.lastIndexOf("=")-1);
            	}else
            		key = res.getString(keyColumns.toUpperCase());
	        	resultData.put( key, res.getString(column.toUpperCase()) );
		        key = null;
		        columnData = null;
            }
        }catch (Exception ex) {
        	logger.error(ex.getMessage(), ex);
        }finally {
			try {
				if( res != null )
					res.close();
				if( stmt != null )
					stmt.close();
				if( conn != null )
					conn.close();
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
			}
        }
        return resultData;
    }
	
	
	public String getColumnDataDateBasedSingleRow( String query, String typeFlag, String reportDate ) {
        //SessionImplementor sessionImp = (org.hibernate.engine.spi.SessionImplementor) em.getDelegate();
        String[] formatedDate = null;
        String resultData = null;
        Statement stmt = null;
        ResultSet res  = null;
        Connection conn = null;
        try {
        	formatedDate = this.getStartAndEndDate(typeFlag, reportDate);
        	query = query.replace( "$startDate", formatedDate[0]);
        	query = query.replace( "$endDate", formatedDate[1]);
            logger.info("Query:["+query+"]");
        	conn = this.getConnection();
        	stmt = conn.createStatement();//sessionImp.connection().createStatement();
            res  = stmt.executeQuery( query );
            while (res.next()) {
            	resultData = res.getString(1);
            }
            logger.info("Query:["+query+"]");
        }catch (Exception ex) {
        	logger.error(ex.getMessage(), ex);
        	resultData = "query error";
        }finally {
			try {
				if( res != null )
					res.close();
				if( stmt != null )
					stmt.close();
				if( conn != null )
					conn.close();
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
			}
        }
        return resultData;
    }
	
	public boolean insertDataInReport( String query ) {
        //SessionImplementor sessionImp = (org.hibernate.engine.spi.SessionImplementor) em.getDelegate();
		Connection conn = null;
        Statement stmt = null;
        boolean status = false;
        try {
        	conn = this.getConnection();
            stmt = conn.createStatement();//sessionImp.connection().createStatement();
            if(stmt.executeUpdate(query)>0)
            	status = true;
        }catch (Exception ex) {
        	try { conn.rollback(); }catch( Exception e) {}
        	logger.error(ex.getMessage(), ex);
        }finally {
			try {
				if( stmt != null )
					stmt.close();
				if( conn != null )
					conn.close();
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
			}
        }
        return status;
    }
	
	public boolean insertMultipleRowInReport( List<String> queries ) {
        Connection conn = null;
        Statement stmt = null;
        boolean status = false;
        try {
        	conn = this.getConnection();
            stmt = conn.createStatement();//sessionImp.connection().createStatement();
            for( String query : queries ) {
            	stmt.executeUpdate(query);
//	            if(stmt.executeUpdate(query)>0)
//	            	status = true;
//	            else {
//	            	status = false;
//	            	conn.rollback();
//	            	break;
//	            }
            }
            status = true;
        }catch (Exception ex) {
//        	try { conn.rollback(); }catch( Exception e) {}
        	status = false;
        	logger.error(ex.getMessage(), ex);
        }finally {
			try {
				if( stmt != null )
					stmt.close();
				if( conn != null )
					conn.close();
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
			}
        }
        return status;
    }
	
	public Connection getConnection() {
		EntityManagerFactoryInfo info = (EntityManagerFactoryInfo) em.getEntityManagerFactory();
	    try {
			return info.getDataSource().getConnection();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}
	
	public String[] getStartAndEndDate( String typeFlag, String reportDate ) {
		String startDate = null;
		String endDate = null;
		if( propertiesReader.dialect.toLowerCase().contains("mysql")) {
    		endDate = "DATE('"+reportDate+"')";
        	if( typeFlag.equalsIgnoreCase("Daily") || typeFlag.equalsIgnoreCase("Till Date")  ) {
        		startDate = "DATE(ADDDATE('"+reportDate+"',-1))";
        	}else if( typeFlag.equalsIgnoreCase("Monthly") ) {
        		startDate = "DATE(concat(DATE_FORMAT('"+reportDate+"','%Y-%m'),'-01'))";
        	}else {
        		startDate = "DATE(concat(DATE_FORMAT('"+reportDate+"','%Y'),'-01-01'))";
        	}
    	}else {
    		endDate = "to_char(to_date('"+reportDate+"','YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD')";
        	if( typeFlag.equalsIgnoreCase("Daily") || typeFlag.equalsIgnoreCase("Till Date") ) {
        		startDate = "to_char(to_date('"+reportDate+"','YYYY-MM-DD HH24:MI:SS') + interval '-1' day,'YYYY-MM-DD')";
        	}else if( typeFlag.equalsIgnoreCase("Monthly") ) {
        		startDate = "concat(to_char(to_date('"+reportDate+"','YYYY-MM-DD HH24:MI:SS'),'YYYY-MM'),'-01')";
        	}else {
        		startDate = "concat(to_char(to_date('"+reportDate+"','YYYY-MM-DD HH24:MI:SS'),'YYYY'),'-01-01')";
        	}
    	}
		return new String[] { startDate, endDate };
	}
	
}
