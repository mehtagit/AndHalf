package com.ceir.CeirCode.repo; 

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ceir.CeirCode.model.RunningAlertDb;
import com.ceir.CeirCode.model.StakeholderFeature;
import com.ceir.CeirCode.model.Usertype;

@Repository
public interface FeatureRepo extends JpaRepository<StakeholderFeature, Long>,JpaSpecificationExecutor<StakeholderFeature>{
	
	public List<StakeholderFeature> findAll();
}
