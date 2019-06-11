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
