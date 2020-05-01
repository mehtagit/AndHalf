package com.functionapps.parser.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.log4j.Logger;

import com.functionapps.constants.Usertypes;
import com.functionapps.dao.ManagementAudTableDao;
import com.functionapps.dao.ManagementTableDao;
import com.functionapps.dao.StockMgmtDao;
import com.functionapps.parser.Rule;
import com.functionapps.pojo.ManagementDb;
import com.functionapps.pojo.ManagementTable;
import com.functionapps.pojo.StockMgmt;


public class StockDelete {
	static Logger logger = Logger.getLogger(StockDelete.class);

	public void process(Connection conn, String operator, String subFeature, ArrayList<Rule> rulelist, String txnId, 
			String operator_tag, String usertypeName, String roleType ){
		
		System.out.println("Delete will perform for usertypeName[" + usertypeName + "] and roleType [" + roleType + "].");
		logger.info("Delete will perform for usertypeName[" + usertypeName + "] and roleType [" + roleType + "].");
		
		ManagementTableDao managementTableDao = new ManagementTableDao();
		ManagementAudTableDao managementAudTableDao = new ManagementAudTableDao();
		StockMgmtDao stockMgmtDao = new StockMgmtDao();

		StockMgmt stockMgmt =  stockMgmtDao.getStockByTxnId(conn, txnId);
		System.out.println(stockMgmt);
		logger.debug(stockMgmt);
		
		try{
			List<ManagementTable> managementTables = getTableNameByUserType(usertypeName, stockMgmt.getRoleType());
			System.out.println(managementTables);
			logger.debug(managementTables);

			if(managementTables.isEmpty()) {
				System.out.println("No management table found for usertype[" + usertypeName + "]");
				logger.info("No management table found for usertype[" + usertypeName + "]");
				return;
			}else {

				for(ManagementTable managementTable : managementTables) {
					List<ManagementDb> managementDbs = managementTableDao.getManagementDbByTxnId(conn, txnId, 
							managementTable.getName());
					logger.debug(managementDbs);
					System.out.println(managementDbs);

					managementAudTableDao.insertManagementDbAud(conn, managementDbs, managementTable.getAudName(), managementTable.getAudSequenceName());

					managementTableDao.deleteDevicesFromManagementDb(conn, txnId, managementTable.getName());
				}
			}

			managementTableDao.updateMgmtDeleteFlag(conn, "stock_mgmt", txnId, 2);

		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
	}

	private List<ManagementTable> getTableNameByUserType(String userType, String roleType) {
		List<ManagementTable> managementTables = new ArrayList<>();

		if(Usertypes.IMPORTER.equalsIgnoreCase(userType)) {
			managementTables.add(new ManagementTable("device_importer_db", "device_importer_db_aud", "device_importer_db_aud_seq"));
		}
		else if(Usertypes.DISTRIBUTOR.equalsIgnoreCase(userType)) {
			managementTables.add(new ManagementTable("device_distributer_db", "device_distributer_db_aud", ""));
		}
		else if(Usertypes.RETAIILER.equalsIgnoreCase(userType)) {
			managementTables.add(new ManagementTable("device_retailer_db", "device_retailer_db_aud", ""));
		}
		else if(Usertypes.CUSTOM.equalsIgnoreCase(userType)) {
			managementTables.add(new ManagementTable("device_custom_db", "device_custom_db_aud", ""));
			if(Usertypes.RETAIILER.equalsIgnoreCase(roleType)) {
				managementTables.add(new ManagementTable("device_retailer_db", "device_retailer_db_aud", ""));
			}else if(Usertypes.DISTRIBUTOR.equalsIgnoreCase(roleType)) {
				managementTables.add(new ManagementTable("device_distributor_db", "device_distributor_db_aud", ""));
			}
		}
		else if(Usertypes.MANUFACTURER.equalsIgnoreCase(userType)) {
			managementTables.add(new ManagementTable("device_manufacturer_db", "device_manufacturer_db_aud", ""));
		}
		else if(Usertypes.END_USER.equalsIgnoreCase(userType)) {
			managementTables.add(new ManagementTable("device_end_user_db", "device_end_user_db_aud", ""));
		}

		managementTables.add(new ManagementTable("device_db", "device_db_aud", "DEVICE_DB_AUD_seq"));

		return managementTables;
	}
}