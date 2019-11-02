package com.gl.ceir.config.specificationsbuilder;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.jpa.domain.Specification;

import com.gl.ceir.config.controller.ConsignmentController;
import com.gl.ceir.config.model.ConsignmentMgmt;
import com.gl.ceir.config.model.SearchCriteria;
import com.gl.ceir.config.model.constants.SearchOperation;

public class ConsignmentMgmtSpecificationBuilder {

	private static final Logger logger = LogManager.getLogger(ConsignmentController.class);
	
	private final List<SearchCriteria> params;

	public ConsignmentMgmtSpecificationBuilder() {
		params = new ArrayList<>();
	}

	public Specification<ConsignmentMgmt> build() { 
		// convert each of SearchCriteria params to Specification and construct combined specification based on custom rules.

		Specification<ConsignmentMgmt> finalSpecification = null;
		List<Specification<ConsignmentMgmt>> specifications = new ArrayList<Specification<ConsignmentMgmt>>();

		for(SearchCriteria searchCriteria : params) {
			specifications.add((root, query, cb)-> {
				if(SearchOperation.GREATER_THAN.equals(searchCriteria.getSearchOperation()))
					return cb.greaterThan(root.get(searchCriteria.getKey()), searchCriteria.getValue().toString());
				else
					return cb.equal(root.get(searchCriteria.getKey()), searchCriteria.getValue().toString());
			});
		}
		
		finalSpecification = Specification.where(specifications.get(0));
		
		for(int i = 1; i<specifications.size();i++) {
			finalSpecification = finalSpecification.and(specifications.get(i));
		}
		
		return finalSpecification;
	}

	public final ConsignmentMgmtSpecificationBuilder with(SearchCriteria criteria) { 
		params.add(criteria);
		return this;
	}

}
