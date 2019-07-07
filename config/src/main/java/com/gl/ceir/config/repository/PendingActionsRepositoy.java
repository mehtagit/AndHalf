package com.gl.ceir.config.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.ceir.config.model.PendingActions;
import com.gl.ceir.config.model.constants.TransactionState;

public interface PendingActionsRepositoy extends JpaRepository<PendingActions, String> {

	public PendingActions findByMsisdn(Long msisdn);

	public PendingActions findByImei(Long imei);

	public PendingActions findByMsisdnAndImei(Long msisdn, Long imei);

	public List<PendingActions> findByTransactionState(TransactionState transactionState);
}
