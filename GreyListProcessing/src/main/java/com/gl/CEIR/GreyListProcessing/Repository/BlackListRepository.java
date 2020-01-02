package com.gl.CEIR.GreyListProcessing.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.gl.CEIR.GreyListProcessing.Beans.ConfigurationDetails;
import com.gl.CEIR.GreyListProcessing.Beans.DumpDetails;

@Repository
public class BlackListRepository {


	private Logger log = LoggerFactory.getLogger(getClass());


	@Autowired
	JdbcTemplate jdbcTemplate;

	public ConfigurationDetails getConfigurationDetails() {
		try {

			String query="select listType,time,units,dumpType from system_list_mapping where listType='blackList'";
			log.info(query);
			return jdbcTemplate.queryForObject(query, blackListMapper);

		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
			log.info("No data found ="+e);
			return null;
		}
	}


	public List<String> getImeidetails(){

		try {
			String query ="select imei from blackList_imei ";

			log.info(query);

			return jdbcTemplate.queryForList(query,String.class);

		} catch (EmptyResultDataAccessException e) {

			e.printStackTrace();
			log.info("Data No found="+e);
			return null;
		}
	}

	public List<DumpDetails> getpartiallyDumpDetails(String units,int time) {
		try {
			String query=null;
			if(units.equalsIgnoreCase("minute")) {

				query="select imei,operation from black_list_track_details  where createdon > date_sub(now(), interval "+time+" minute)";

			}else if(units.equalsIgnoreCase("day")) {
				query="select imei,operation from black_list_track_details  where createdon > date_sub(now(), interval "+time+" day)";

			}else if(units.equalsIgnoreCase("hour"))
			{
				query="select imei,operation from black_list_track_details  where createdon > date_sub(now(), interval "+time+" hour)";
			}

			log.info(query);
			return  jdbcTemplate.query(query, dumpDetails);

		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
			log.info("NO data found ="+e);
			return null;
		}
	}




	private final RowMapper <ConfigurationDetails> blackListMapper = new RowMapper<ConfigurationDetails>() {

		public ConfigurationDetails mapRow(ResultSet rs , int i) throws SQLException {

			ConfigurationDetails info=new ConfigurationDetails();

			info.setListType(rs.getString("listType"));
			info.setDumptype(rs.getString("dumpType"));
			info.setTime(rs.getInt("time"));
			info.setUnit(rs.getString("units"));

			return info ;
		}
	};


	public int insertGreyListDetails(String listType, String fileName,String dumpType) {

		String query = "insert into list_file_Details (listType,fileName,dump_Type) values(?,?,?)";
		log.info(query);

		KeyHolder holder = new GeneratedKeyHolder();
		final PreparedStatementCreator psc = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(final Connection connection) throws SQLException {
				final PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

				ps.setString(1, listType);
				ps.setString(2, fileName);
				ps.setString(3, dumpType);
				return ps;
			}
		};
		jdbcTemplate.update(psc, holder);
		return holder.getKey().intValue();

	}



	private final RowMapper <DumpDetails> dumpDetails = new RowMapper<DumpDetails>() {

		public DumpDetails mapRow(ResultSet rs , int i) throws SQLException {

			DumpDetails info=new DumpDetails();

			info.setImei(rs.getString("imei"));
			info.setOperation(rs.getString("operation"));

			return info ;
		}
	};




}
