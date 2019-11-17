package com.gl.ceir.config.specificationsbuilder;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Expression;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.jpa.domain.Specification;

import com.gl.ceir.config.controller.GrievanceController;
import com.gl.ceir.config.model.Grievance;
import com.gl.ceir.config.model.SearchCriteria;
import com.gl.ceir.config.model.constants.Datatype;
import com.gl.ceir.config.model.constants.SearchOperation;
import com.gl.ceir.config.util.DbFunctions;

public class GrievanceSpecificationBuilder {
	private static final Logger logger = LogManager.getLogger(GrievanceController.class);

	private final List<SearchCriteria> params;
	private final String dialect;
	

	public GrievanceSpecificationBuilder(String dialect) {
		params = new ArrayList<>();
		this.dialect = dialect;
	}

	public final GrievanceSpecificationBuilder with(SearchCriteria criteria) { 
		params.add(criteria);
		return this;
	}

	public Specification<Grievance> build() { 
		// convert each of SearchCriteria params to Specification and construct combined specification based on custom rules.
		Specification<Grievance> finalSpecification = null;
		List<Specification<Grievance>> specifications = createSpecifications();
		if(!specifications.isEmpty()) {
			finalSpecification = Specification.where(specifications.get(0));
			for(int i = 1; i<specifications.size() ;i++) {
				finalSpecification = finalSpecification.and(specifications.get(i));
			}
		}
		return finalSpecification;
	}

	private List<Specification<Grievance>> createSpecifications(){
		List<Specification<Grievance>> specifications = new ArrayList<Specification<Grievance>>();
		// Path<Tuple> tuple = null;
		try {
		for(SearchCriteria searchCriteria : params) {
			specifications.add((root, query, cb)-> {
				// Path<Tuple> tuple = root.<Tuple>get(searchCriteria);
				if(SearchOperation.GREATER_THAN.equals(searchCriteria.getSearchOperation())
						&& Datatype.STRING.equals(searchCriteria.getDatatype())) {
					return cb.greaterThan(root.get(searchCriteria.getKey()), searchCriteria.getValue().toString());
				}
				else if(SearchOperation.LESS_THAN.equals(searchCriteria.getSearchOperation())
						&& Datatype.STRING.equals(searchCriteria.getDatatype())) {
					return cb.lessThan(root.get(searchCriteria.getKey()), searchCriteria.getValue().toString());
				}
				else if(SearchOperation.EQUALITY.equals(searchCriteria.getSearchOperation())
						&& Datatype.STRING.equals(searchCriteria.getDatatype())) {
					return cb.equal(root.get(searchCriteria.getKey()), searchCriteria.getValue().toString());
				}
				else if(SearchOperation.GREATER_THAN.equals(searchCriteria.getSearchOperation())
						&& Datatype.DATE.equals(searchCriteria.getDatatype())){
					Expression<String> dateStringExpr = cb.function(DbFunctions.getDate(dialect), String.class, root.get(searchCriteria.getKey()), cb.literal(DbFunctions.getDateFormat(dialect)));
					return cb.greaterThan(cb.lower(dateStringExpr), searchCriteria.getValue().toString());
				}
				else if(SearchOperation.LESS_THAN.equals(searchCriteria.getSearchOperation())
						&& Datatype.DATE.equals(searchCriteria.getDatatype())){
					Expression<String> dateStringExpr = cb.function(DbFunctions.getDate(dialect), String.class, root.get(searchCriteria.getKey()), cb.literal(DbFunctions.getDateFormat(dialect)));
					return cb.lessThan(cb.lower(dateStringExpr), searchCriteria.getValue().toString());
				}else {
					return null;
				}
			});
		}
		}catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(), e);
		}

		return specifications;
	}
}
