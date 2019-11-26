package com.gl.CEIR.CDRPicker.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.gl.CEIR.CDRPicker.beans.ConfigurationDetails;

@Repository
public class CdrRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	private Logger log = LoggerFactory.getLogger(getClass());


	public ConfigurationDetails getConfigDetails() {
		try {

			String query="select AggregatorType,AggegationValue from file_configuration";
			log.info(query);			
			return jdbcTemplate.queryForObject(query,configurationMapper);

		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
			log.info("Empty Data="+e);
			return null;
		}


	}

	private final RowMapper <ConfigurationDetails> configurationMapper = new RowMapper<ConfigurationDetails>() {

		public ConfigurationDetails mapRow(ResultSet rs , int i) throws SQLException {

			ConfigurationDetails packsDetails=new ConfigurationDetails();

			packsDetails.setAggregationType(rs.getString("AggregatorType"));
			packsDetails.setAggregationValue(rs.getInt("AggegationValue"));

			return packsDetails;
		}
	};





}
