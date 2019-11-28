package com.gl.ceir.config.specificationsbuilder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.jpa.domain.Specification;

import com.gl.ceir.config.controller.ConsignmentController;
import com.gl.ceir.config.model.ConsignmentMgmt;
import com.gl.ceir.config.model.SearchCriteria;
import com.gl.ceir.config.model.constants.Datatype;
import com.gl.ceir.config.model.constants.SearchOperation;
import com.gl.ceir.config.util.DbFunctions;

public class ConsignmentMgmtSpecificationBuilder {

	private static final Logger logger = LogManager.getLogger(ConsignmentController.class);

	private final List<SearchCriteria> params;
	private final String dialect;
	private List<Specification<ConsignmentMgmt>> specifications;


	public ConsignmentMgmtSpecificationBuilder(String dialect) {
		params = new ArrayList<>();
		specifications = new LinkedList<>();
		this.dialect = dialect;
	}

	public final ConsignmentMgmtSpecificationBuilder with(SearchCriteria criteria) { 
		params.add(criteria);
		return this;
	}



	public Specification<ConsignmentMgmt> build() { 
		// convert each of SearchCriteria params to Specification and construct combined specification based on custom rules.

		Specification<ConsignmentMgmt> finalSpecification = null;

		createSpecifications();

		if(!specifications.isEmpty()) {
			finalSpecification = Specification.where(specifications.get(0));

			for(int i = 1; i<specifications.size(); i++) {
				finalSpecification = finalSpecification.and(specifications.get(i));
			}
		}

		return finalSpecification;
	}



	public void addSpecification(Specification<ConsignmentMgmt> specification) { 
		specifications.add(specification);
	}


	private List<Specification<ConsignmentMgmt>> createSpecifications(){
		//	List<Specification<ConsignmentMgmt>> specifications = new ArrayList<Specification<ConsignmentMgmt>>();
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

	public Specification<ConsignmentMgmt> joinWithUserIN(SearchCriteria searchCriteria,List<Integer> consignmentStatus){
		return (root, query, cb) -> {
			//root.in(consignmentStatus);
			logger.info("In query save ");
			return cb.in(root.get(searchCriteria.getKey())).value(consignmentStatus);
		};

	}
}
