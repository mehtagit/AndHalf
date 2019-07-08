package com.gl.ceir.config.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.exceptions.ResourceNotFoundException;
import com.gl.ceir.config.exceptions.ResourceServicesException;
import com.gl.ceir.config.model.ImeiMsisdnIdentity;
import com.gl.ceir.config.model.PendingActions;
import com.gl.ceir.config.model.constants.TransactionState;
import com.gl.ceir.config.repository.PendingActionsRepositoy;
import com.gl.ceir.config.service.PendingActionsService;

@Service
public class PendingActionsServiceImpl implements PendingActionsService {
	private static final Logger logger = LogManager.getLogger(PendingActionsServiceImpl.class);

	@Autowired
	private PendingActionsRepositoy pendingActionsRepositoy;

	@Override
	public List<PendingActions> getAll() {

		try {
			return pendingActionsRepositoy.findAll();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	@Override
	public PendingActions save(PendingActions pendingActions) {

		try {
			return pendingActionsRepositoy.save(pendingActions);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
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

		try {
			PendingActions pendingActions = pendingActionsRepositoy.findById(ticketId)
					.orElseThrow(() -> new ResourceNotFoundException("Pending Actions ", "ticketId", ticketId));
			return pendingActions;
		} catch (ResourceNotFoundException e) {
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	@Override
	public void delete(String ticketId) {

	}

	@Override
	public PendingActions getByMsisdn(Long msisdn) {
		try {
			PendingActions pendingActions = pendingActionsRepositoy.findByMsisdn(msisdn);
			if (pendingActions == null)
				new ResourceNotFoundException("Pending Actions ", "msisdn", msisdn);
			return pendingActions;
		} catch (ResourceNotFoundException e) {
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	@Override
	public PendingActions getByImei(Long imei) {
		try {
			PendingActions pendingActions = pendingActionsRepositoy.findByImei(imei);
			if (pendingActions == null)
				new ResourceNotFoundException("Pending Actions ", "imei", imei);
			return pendingActions;
		} catch (ResourceNotFoundException e) {
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	@Override
	public PendingActions getByMsisdnAndImei(ImeiMsisdnIdentity imeiMsisdnIdentity) {
		try {
			if (imeiMsisdnIdentity.getMsisdn() == null && imeiMsisdnIdentity.getImei() == null) {
				return null;
			} else if (imeiMsisdnIdentity.getMsisdn() != null && imeiMsisdnIdentity.getImei() != null) {
				return pendingActionsRepositoy.findByMsisdnAndImei(imeiMsisdnIdentity.getMsisdn(),
						imeiMsisdnIdentity.getImei());
			} else if (imeiMsisdnIdentity.getMsisdn() != null) {
				return getByMsisdn(imeiMsisdnIdentity.getMsisdn());
			} else {
				return getByImei(imeiMsisdnIdentity.getImei());
			}

		} catch (ResourceNotFoundException e) {
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	@Override
	public List<PendingActions> saveAll(List<PendingActions> pendingActions) {
		return pendingActionsRepositoy.saveAll(pendingActions);
	}

	@Override
	public List<PendingActions> getByTransactionState(TransactionState transactionState) {
		return pendingActionsRepositoy.findByTransactionState(transactionState);
	}

}
