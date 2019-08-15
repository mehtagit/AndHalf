package com.gl.ceir.evaluator.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.model.BlackList;
import com.gl.ceir.config.model.DuplicateImeiMsisdn;
import com.gl.ceir.config.model.ImeiMsisdnIdentity;
import com.gl.ceir.config.model.NullMsisdnRegularized;
import com.gl.ceir.config.model.PendingActions;
import com.gl.ceir.config.model.Rules;
import com.gl.ceir.config.model.Tac;
import com.gl.ceir.config.model.VipList;
import com.gl.ceir.config.model.constants.ImeiStatus;
import com.gl.ceir.config.repository.DuplicateImeiMsisdnRepository;
import com.gl.ceir.config.repository.TacRepository;
import com.gl.ceir.config.service.BlackListService;
import com.gl.ceir.config.service.DuplicateImeiMsisdnService;
import com.gl.ceir.config.service.NullMsisdnRegularizedService;
import com.gl.ceir.config.service.PendingActionsService;
import com.gl.ceir.config.service.TacService;
import com.gl.ceir.config.service.VipListService;
import com.gl.ceir.config.system.request.Request;
import com.gl.ceir.evaluator.services.RuleSolver;

@Service
public class InRuleSolver implements RuleSolver {

	private Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private BlackListService blackListService;

	@Autowired
	private VipListService vipListService;

	@Autowired
	private PendingActionsService pendingActionsService;

	@Autowired
	private DuplicateImeiMsisdnService duplicateImeiMsisdnService;

	@Autowired
	private NullMsisdnRegularizedService nullMsisdnRegularizedService;

	@Autowired
	private TacService tacService;

	@Override
	public boolean solve(Rules rule, Request request) {
		boolean result = false;
		try {
			logger.debug("RuleSolver " + rule + ", " + request);
			switch (rule.getParameters()) {
			case IMEI:
				logger.warn("IMEI, InRuleSolver not available");
				break;
			case IMEI_LENGTH:
				logger.warn("IMEI_LENGTH, InRuleSolver not available");
				break;
			case IMEI_MSISDN:
				if ("VIP".equals(rule.getMin())) {
					VipList vipList = vipListService
							.getByMsisdnAndImei(new ImeiMsisdnIdentity(request.getImei(), request.getMsisdn()));
					result = vipList == null ? false : true;
				} else if ("BLACK".equals(rule.getMin())) {
					BlackList blackList = blackListService
							.getByMsisdnAndImei(new ImeiMsisdnIdentity(request.getImei(), request.getMsisdn()));
					result = blackList == null ? false : true;
				} else if ("PENDING".equals(rule.getMin())) {
					PendingActions pendingActions = pendingActionsService
							.getByMsisdnAndImei(new ImeiMsisdnIdentity(request.getImei(), request.getMsisdn()));
					result = pendingActions == null ? false : true;
				}
				break;
			case MSISDN:
				logger.warn("MSISDN, InRuleSolver not available");
				break;
			case IMEI_COUNT:
				logger.warn("IMEI_COUNT, InRuleSolver not available");

				break;
			case IMEI_STATUS:

				if (request.getImei().equals(Long.valueOf(0))) {
					try {
						NullMsisdnRegularized nullMsisdnRegularized = nullMsisdnRegularizedService
								.get(request.getMsisdn());

						if (nullMsisdnRegularized == null) {
							logger.info("Not Found in NullMsisdnRegularized, MSISDN ....");
							result = false;
						} else {
							logger.info("Found in NullMsisdnRegularized, MSISDN ....");
							result = true;
						}

					} catch (com.gl.ceir.config.exceptions.ResourceNotFoundException e) {
						result = false;
					}

				} else {
					try {
						DuplicateImeiMsisdn duplicateImeiMsisdn = duplicateImeiMsisdnService
								.get(new ImeiMsisdnIdentity(request.getImei(), request.getMsisdn()));

						if (duplicateImeiMsisdn == null) {
							result = false;
						} else {
							result = checkImeiStatus(rule, duplicateImeiMsisdn.getImeiStatus());
						}

					} catch (com.gl.ceir.config.exceptions.ResourceNotFoundException e) {
						logger.info("Not Found in DuplicateImeiMsisdn, By IMEI and MSISDN");
						result = false;
					}
				}

				break;
			case IMEI_TAX:
				break;
			case TAC:
				if ("TAC_LIST".equals(rule.getMin())) {
					String imeiTac = request.getImei().toString().substring(0, 8);
					logger.info("imeiTac from Imei : " + imeiTac);
					try {
						Tac tac = tacService.get(imeiTac);
						if (tac == null)
							result = true;
						else
							result = false;
					} catch (com.gl.ceir.config.exceptions.ResourceNotFoundException e) {
						logger.info("Not Found in Tac, for imeiTac:" + imeiTac);
						result = true;
					}
				}
				break;
			default:
				break;
			}

			return result;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	private boolean checkImeiStatus(Rules rule, ImeiStatus imeiStatus) {
		boolean retVal = false;
		for (String status : rule.getMin().split(",")) {
			logger.info("status:" + status + ", imeiStatus:" + imeiStatus);
			if (imeiStatus == ImeiStatus.getImeiStatus(status)) {
				retVal = true;
				logger.info("Checked status for status:" + status);
				break;
			}
		}
		return retVal;
	}

}
