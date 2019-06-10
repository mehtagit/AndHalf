package com.gl.ceir.evaluator.services;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.gl.ceir.config.controller.BlackListController;
import com.gl.ceir.config.model.BlackList;
import com.gl.ceir.config.model.ImeiMsisdnIdentity;
import com.gl.ceir.config.model.Rules;
import com.gl.ceir.config.model.VipList;
import com.gl.ceir.config.repository.BlackListRepository;
import com.gl.ceir.config.repository.VipListRepository;
import com.gl.ceir.config.service.BlackListService;
import com.gl.ceir.config.service.VipListService;
import com.gl.ceir.config.system.request.Request;

public interface RuleSolver {

	public boolean solve(Rules rule, Request request);
}

class InRuleSolver implements RuleSolver {

	private static final Logger logger = LogManager.getLogger(InRuleSolver.class);

	@Autowired
	private BlackListService blackListService;

	@Autowired
	private VipListService vipListService;

	@Override
	public boolean solve(Rules rule, Request request) {
		try {
			if (rule.getName().contains("BLACK")) {
				BlackList blackList = blackListService
						.getByMsisdnAndImei(new ImeiMsisdnIdentity(request.getImei(), request.getMsisdn()));
				return blackList == null ? false : true;
			} else if (rule.getName().contains("VIP")) {
				VipList vipList = vipListService
						.getByMsisdnAndImei(new ImeiMsisdnIdentity(request.getImei(), request.getMsisdn()));
				return vipList == null ? false : true;
			} else {
				return false;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

}
