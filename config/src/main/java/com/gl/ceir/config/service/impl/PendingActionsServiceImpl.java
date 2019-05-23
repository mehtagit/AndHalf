package com.gl.ceir.config.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.exceptions.ResourceNotFoundException;
import com.gl.ceir.config.model.MediationSource;
import com.gl.ceir.config.model.PendingActions;
import com.gl.ceir.config.repository.PendingActionsRepositoy;
import com.gl.ceir.config.service.PendingActionsService;

@Service
public class PendingActionsServiceImpl implements PendingActionsService {

	@Autowired
	private PendingActionsRepositoy pendingActionsRepositoy;

	@Override
	public List<PendingActions> getAll() {
		return pendingActionsRepositoy.findAll();
	}

	@Override
	public PendingActions save(PendingActions pendingActions) {
		return pendingActionsRepositoy.save(pendingActions);
	}

	@Override
	public PendingActions update(PendingActions t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long t) {
		// TODO Auto-generated method stub

	}

	@Override
	public PendingActions get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PendingActions get(String ticketId) {
		PendingActions pendingActions = pendingActionsRepositoy.findById(ticketId)
				.orElseThrow(() -> new ResourceNotFoundException("Pending Actions ", "ticketId", ticketId));
		return pendingActions;
	}

	@Override
	public void delete(String ticketId) {

	}

}
