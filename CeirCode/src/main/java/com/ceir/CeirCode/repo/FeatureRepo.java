package com.ceir.CeirCode.repo; 
//package com.ceir.CeirCode.repo;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.stereotype.Repository;
//
//import com.ceir.CeirCode.model.feature;
//import com.ceir.CeirCode.model.Subfeature;
//import com.ceir.CeirCode.model.UserFeature;
//@Repository
//public class FeatureRepo {
//	private final Logger log = LoggerFactory.getLogger(getClass());	
//	@Autowired
//	@Qualifier("postgresJdbcTemplate")	
//	protected JdbcTemplate postgresJdbcTemplate;
//	public List<feature> getFeatures(UserFeature user){ 
//		String query="select f.id,f.name,f.category  from user_to_stakehoderfeature_mapping ufm"
//				+ " inner join stakeholder_feature f  on ufm.feature_id=f.id and ufm.usertype_id=? and"
//				+ " ufm.user_id=? order by f.category"; 
//		log.info("query to get features:  "+query);
//	       try{ 
//	    	   return  postgresJdbcTemplate.query(query,new Object[]{user.getUsertypeId(),user.getUserId()}, featureData);
//	    	   
//	        } 
//	        catch(Exception e) {
//	        	e.printStackTrace();
//	        	return null;
//	        }
//		
//	}
//	
//	public List<Subfeature> getSubFeatures(UserFeature user){ 
//		String query="select sf.name,sf.id from user_shsubfeature_mapping"
//				+ " shm inner join subfeature sf on shm.subfeature_id=sf.id and"
//				+ " shm.usertype_id=? and shm.user_id=? and shm.feature_id=?";
//		log.info("query to get features:  "+query);
//	       try{
//	          return postgresJdbcTemplate.query(query,new Object[]{user.getUsertypeId(),user.getUserId(),user.getFeatureId()}, subFeatureData);
//	        }
//	        catch(Exception e) { 
//	        	e.printStackTrace();    
//	        	return null; 
//	        }
//	}
//	
//	public List<Subfeature> getActions(UserFeature user){ 
//		String query="select ufam.action_id id, a.name from user_shfeature_action_mapping ufam inner join actions a on " + 
//				"ufam.action_id=a.id where ufam.feature_id=? and ufam.usertype_id=? and ufam.user_id=?";
//		log.info("query to get actions:  "+query);
//	       try{
//	          return postgresJdbcTemplate.query(query,new Object[]{user.getFeatureId(),user.getUsertypeId(),user.getUserId()}, subFeatureData);
//	        }
//	        catch(Exception e) {
//	        	e.printStackTrace();
//	        	return null; 
//	        }
//	} 
//	  private final RowMapper<feature> featureData=new RowMapper<feature>() {
//			@Override
//			public feature mapRow(ResultSet rs, int rowNum) throws SQLException {
//		         feature feature=new feature();
//		         feature.setFeatureName(rs.getString("name"));
//		         feature.setId(rs.getInt("id"));
//		      //   feature.setCategory(rs.getString("category"));
//				return feature;
//			}
//		  }; 
//		  
//		  private final RowMapper<Subfeature> subFeatureData=new RowMapper<Subfeature>() {
//				@Override
//				public Subfeature mapRow(ResultSet rs, int rowNum) throws SQLException {
//			         Subfeature feature=new Subfeature();
//			         feature.setName(rs.getString("name"));
//			         feature.setId(rs.getInt("id"));
//					return feature; 
//				}
//			  }; 
//}
