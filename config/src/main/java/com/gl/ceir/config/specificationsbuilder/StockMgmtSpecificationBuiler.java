package com.gl.ceir.config.specificationsbuilder;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.jpa.domain.Specification;

import com.gl.ceir.config.model.SearchCriteria;
import com.gl.ceir.config.model.StockMgmt;
import com.gl.ceir.config.model.constants.SearchOperation;

public class StockMgmtSpecificationBuiler {




	private static final Logger logger = LogManager.getLogger(StockMgmtSpecificationBuiler.class);

	private final List<SearchCriteria> params;

	public StockMgmtSpecificationBuiler() {
		params = new ArrayList<>();
	}

	public final StockMgmtSpecificationBuiler with(SearchCriteria criteria) { 
		params.add(criteria);
		return this;
	}

	public Specification<StockMgmt> build() { 
		// convert each of SearchCriteria params to Specification and construct combined specification based on custom rules.

		Specification<StockMgmt> finalSpecification = null;

		List<Specification<StockMgmt>> specifications = createSpecifications();

		if(!specifications.isEmpty()) {
			finalSpecification = Specification.where(specifications.get(0));

			for(int i = 1; i<specifications.size() ;i++) {
				finalSpecification = finalSpecification.and(specifications.get(i));
			}
		}

		return finalSpecification;
	}

	private List<Specification<StockMgmt>> createSpecifications(){
		List<Specification<StockMgmt>> specifications = new ArrayList<Specification<StockMgmt>>();

		for(SearchCriteria searchCriteria : params) {
			specifications.add((root, query, cb)-> {
				if(SearchOperation.GREATER_THAN.equals(searchCriteria.getSearchOperation()))
					return cb.greaterThan(root.get(searchCriteria.getKey()), searchCriteria.getValue().toString());

				else if(SearchOperation.LESS_THAN.equals(searchCriteria.getSearchOperation()))
					return cb.lessThan(root.get(searchCriteria.getKey()), searchCriteria.getValue().toString());

				else
					return cb.equal(root.get(searchCriteria.getKey()), searchCriteria.getValue().toString());


			});
		}

		return specifications;
	}
}




