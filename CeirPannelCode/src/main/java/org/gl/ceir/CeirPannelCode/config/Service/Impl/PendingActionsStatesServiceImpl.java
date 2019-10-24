package org.gl.ceir.CeirPannelCode.config.Service.Impl;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import org.gl.ceir.CeirPannelCode.config.exceptions.ResourceNotFoundException;
import org.gl.ceir.CeirPannelCode.config.exceptions.ResourceServicesException;
import org.gl.ceir.CeirPannelCode.config.Model.Action;
import org.gl.ceir.CeirPannelCode.config.Model.DeviceSnapShot;
import org.gl.ceir.CeirPannelCode.config.Model.DuplicateImeiMsisdn;
import org.gl.ceir.CeirPannelCode.config.Model.ImeiMsisdnIdentity;
import org.gl.ceir.CeirPannelCode.config.Model.PendingActionStates;
import org.gl.ceir.CeirPannelCode.config.Model.PendingActions;
import org.gl.ceir.CeirPannelCode.config.Model.Constants.TransactionState;
import org.gl.ceir.CeirPannelCode.config.Repository.DuplicateImeiMsisdnRepository;
import org.gl.ceir.CeirPannelCode.config.Repository.PendingActionsRepositoy;
import org.gl.ceir.CeirPannelCode.config.Repository.PendingActionsStatesRepositoy;
import org.gl.ceir.CeirPannelCode.config.Service.DeviceSnapShotService;
import org.gl.ceir.CeirPannelCode.config.Service.DuplicateImeiMsisdnService;
import org.gl.ceir.CeirPannelCode.config.Service.PendingActionsService;
import org.gl.ceir.CeirPannelCode.config.Service.PendingActionsStatesService;

@Service
public class PendingActionsStatesServiceImpl implements PendingActionsStatesService {
	private static final Logger logger = LogManager.getLogger(PendingActionsStatesServiceImpl.class);

	@Autowired
	private PendingActionsStatesRepositoy pendingActionsStatesRepositoy;

	@Override
	public List<PendingActionStates> getAll() {
		return pendingActionsStatesRepositoy.findAll();
	}

	@Override
	public PendingActionStates save(PendingActionStates t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PendingActionStates get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long t) {
		// TODO Auto-generated method stub

	}

	@Override
	public PendingActionStates update(PendingActionStates t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isStateChangeAllowed(TransactionState currentState, TransactionState nextState, Action action) {
		PendingActionStates pendingActionStates = pendingActionsStatesRepositoy
				.findByCurrentStateAndNextStateAndAction(currentState, nextState, action);
		if (pendingActionStates == null)
			return false;
		else
			return true;
	}

}
