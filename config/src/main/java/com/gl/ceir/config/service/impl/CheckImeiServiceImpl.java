package com.gl.ceir.config.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.Rule_engine.RuleEngineApplication;
import com.gl.ceir.config.exceptions.ResourceServicesException;
import com.gl.ceir.config.model.RuleEngineMapping;
import com.gl.ceir.config.repository.CheckImeiRepository;

@Service
public class CheckImeiServiceImpl {

	private static final Logger logger = LogManager.getLogger(CheckImeiServiceImpl.class);

	@Autowired
	CheckImeiRepository checkImeiRepository;


	public String  getResult(String user_type,  String feature , Long imei, Long imei_type) {
		String rulePass = "true";
		try {
			logger.info("Going to get All Values   for ;; " + feature   +  user_type);
			
			ArrayList<Rule>   rule_details = new ArrayList<Rule>();
			List<RuleEngineMapping> ruleList = checkImeiRepository.getByFeatureAndUserTypeOrderByRuleOrder(feature , user_type);
			System.out.println("result is " + ruleList);
			for (RuleEngineMapping cim : ruleList) {
				System.out.println("Rulz "+  cim.getName());
				Rule rule = new Rule(cim.getName(),cim.getOutput());
				rule_details.add(rule);
			}
			logger.info("rules Polpulated");
			RuleEngineApplication rea = new RuleEngineApplication();
			String expOutput = "";
			for (Rule rule : rule_details) { 
				String[] my_arr = { rule.rule_name, "1",  "NONCDR",imei.toString() ,"4","5","6","7","8","9","GSM"," "," ","" };

				expOutput = rea.startRuleEngine(my_arr);

				if (rule.output.equalsIgnoreCase(expOutput)) {    // go to next rule(  rule_engine _mapping )
					logger.info("Rule Passed");
				} else {
					logger.info("Rule failed at "  + rule.rule_name );
					rulePass = rule.rule_name;
					break;
				}
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
		return rulePass ;
	}

}