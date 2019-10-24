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

import org.gl.ceir.CeirPannelCode.config.exceptions.OperationNotAllowedException;
import org.gl.ceir.CeirPannelCode.config.exceptions.ResourceNotFoundException;
import org.gl.ceir.CeirPannelCode.config.exceptions.ResourceServicesException;
import org.gl.ceir.CeirPannelCode.config.Model.DeviceSnapShot;
import org.gl.ceir.CeirPannelCode.config.Model.DuplicateImeiMsisdn;
import org.gl.ceir.CeirPannelCode.config.Model.ImeiMsisdnIdentity;
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
public class PendingActionsServiceImpl implements PendingActionsService {
	private static final Logger logger = LogManager.getLogger(PendingActionsServiceImpl.class);

	@Autowired
	private PendingActionsRepositoy pendingActionsRepositoy;

	@Autowired
	private DuplicateImeiMsisdnService duplicateImeiMsisdnService;

	@Autowired
	private DuplicateImeiMsisdnRepository duplicateImeiMsisdnRepository;

	@Autowired
	private PendingActionsStatesService pendingActionsStatesService;

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
		pendingActionsRepositoy.deleteById(ticketId);
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
	@Cacheable(value = "pendingActionsByMsisdnAndImei", key = "#imeiMsisdnIdentity")
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

	@Override
	public List<PendingActions> getReadyToBlocked() {
		return pendingActionsRepositoy
				.findByTransactionStateAndEndDateforUserAction(TransactionState.WAITING_FOR_USER_ACTION, new Date());
	}

	@Override
	public List<PendingActions> getReminderList() {
		return pendingActionsRepositoy.findByTransactionStateAndReminderDate(TransactionState.WAITING_FOR_USER_ACTION,
				new Date());
	}

	private int updateTransactionState(TransactionState transactionState, String ticketId) {
		int result = -1;
		try {
			result = pendingActionsRepositoy.updateTransactionStateByTicketID(transactionState, ticketId);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		logger.info("PendingActions updateTransactionState " + transactionState + ", ticketId:" + ticketId + ", Result:"
				+ result);
		return result;
	}

	@Override
	public boolean regularizedTicket(String ticketId) {
		PendingActions pendingActions = pendingActionsRepositoy.findById(ticketId).orElse(null);
		if (pendingActions == null) {
			logger.info("regularizedTicket PendingActions Not found for TicketID:" + ticketId);
			return false;
		} else {
			try {
				DuplicateImeiMsisdn duplicateImeiMsisdn = duplicateImeiMsisdnService
						.get(new ImeiMsisdnIdentity(pendingActions.getImei(), pendingActions.getMsisdn()));

				duplicateImeiMsisdn.setRegulizedByUser(Boolean.TRUE);
				logger.info("duplicateImeiMsisdnRepository going to updated");
				duplicateImeiMsisdnRepository.save(duplicateImeiMsisdn);
				logger.info("duplicateImeiMsisdnRepository is updated Ticket ID:" + pendingActions.getTicketId());

				pendingActionsRepositoy.deleteById(pendingActions.getTicketId());
				logger.info("Pending Actions is deleted Ticket ID:" + pendingActions.getTicketId());
				return true;
			} catch (ResourceNotFoundException e) {
				return false;
			}
		}
	}

	@Override
	public List<PendingActions> getApprovedList() {
		return pendingActionsRepositoy.findByTransactionState(TransactionState.DOCUMENT_APPROVED);
	}

	@Override
	public void changeTransactionState(String ticketId, TransactionState nextState)
			throws OperationNotAllowedException {
		PendingActions pendingActions = get(ticketId);
		TransactionState currentState = pendingActions.getTransactionState();
		if (pendingActionsStatesService.isStateChangeAllowed(currentState, nextState, pendingActions.getAction())) {
			updateTransactionState(nextState, ticketId);
		} else {
			throw new OperationNotAllowedException(nextState.name(), "PendingActions");
		}
	}

}
