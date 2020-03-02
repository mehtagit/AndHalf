package com.gl.ceir.config.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.exceptions.ResourceNotFoundException;
import com.gl.ceir.config.exceptions.ResourceServicesException;
import com.gl.ceir.config.model.Action;
import com.gl.ceir.config.model.DeviceSnapShot;
import com.gl.ceir.config.model.DuplicateImeiMsisdn;
import com.gl.ceir.config.model.ImeiMsisdnIdentity;
import com.gl.ceir.config.model.PendingActionStates;
import com.gl.ceir.config.model.PendingActions;
import com.gl.ceir.config.model.constants.TransactionState;
import com.gl.ceir.config.repository.DuplicateImeiMsisdnRepository;
import com.gl.ceir.config.repository.PendingActionsRepositoy;
import com.gl.ceir.config.repository.PendingActionsStatesRepositoy;
import com.gl.ceir.config.service.DeviceSnapShotService;
import com.gl.ceir.config.service.DuplicateImeiMsisdnService;
import com.gl.ceir.config.service.PendingActionsService;
import com.gl.ceir.config.service.PendingActionsStatesService;

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

	@Override
	public PendingActionStates get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
