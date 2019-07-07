package com.gl.ceir.evaluator.pojo;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gl.ceir.config.model.DeviceSnapShot;
import com.gl.ceir.config.model.DuplicateImeiMsisdn;
import com.gl.ceir.config.model.NullMsisdnRegularized;
import com.gl.ceir.config.model.PendingActions;
import com.gl.ceir.config.service.DeviceSnapShotService;
import com.gl.ceir.config.service.DuplicateImeiMsisdnService;
import com.gl.ceir.config.service.NullMsisdnRegularizedService;
import com.gl.ceir.config.service.PendingActionsService;
import com.gl.ceir.config.service.impl.NullMsisdnRegularizedServiceImpl;

@Component
public class Result {

	private Logger logger = LogManager.getLogger(NullMsisdnRegularizedServiceImpl.class);

	@Autowired
	private DeviceSnapShotService deviceSnapShotService;

	@Autowired
	private PendingActionsService pendingActionsService;

	@Autowired
	private DuplicateImeiMsisdnService duplicateImeiMsisdnService;

	@Autowired
	private NullMsisdnRegularizedService nullMsisdnRegularizedService;

	private List<PendingActions> pendingBatch;
	private List<DeviceSnapShot> deviceSnapshotBatch;
	private List<DuplicateImeiMsisdn> duplicateImeiMsisdnsBatch;
	private List<NullMsisdnRegularized> nullMsisdnRegularizeds;

	public List<PendingActions> getPendingBatch() {
		return pendingBatch;
	}

	public void setPendingBatch(List<PendingActions> pendingBatch) {
		this.pendingBatch = pendingBatch;
	}

	public List<DeviceSnapShot> getDeviceSnapshotBatch() {
		return deviceSnapshotBatch;
	}

	public void setDeviceSnapshotBatch(List<DeviceSnapShot> deviceSnapshotBatch) {
		this.deviceSnapshotBatch = deviceSnapshotBatch;
	}

	public void reset() {
		this.deviceSnapshotBatch = new ArrayList<>();
		this.pendingBatch = new ArrayList<>();
		this.duplicateImeiMsisdnsBatch = new ArrayList<>();
		this.nullMsisdnRegularizeds = new ArrayList<>();
	}

	public List<DuplicateImeiMsisdn> getDuplicateImeiMsisdnsBatch() {
		return duplicateImeiMsisdnsBatch;
	}

	public void setDuplicateImeiMsisdnsBatch(List<DuplicateImeiMsisdn> duplicateImeiMsisdnsBatch) {
		this.duplicateImeiMsisdnsBatch = duplicateImeiMsisdnsBatch;
	}

	public List<NullMsisdnRegularized> getNullMsisdnRegularizeds() {
		return nullMsisdnRegularizeds;
	}

	public void setNullMsisdnRegularizeds(List<NullMsisdnRegularized> nullMsisdnRegularizeds) {
		this.nullMsisdnRegularizeds = nullMsisdnRegularizeds;
	}

	public void insertDeviceSnapShot(DeviceSnapShot deviceSnapShot) {
		logger.info("Insert into DeviceSnapShot: IMEI:" + deviceSnapShot.getImei() + ", Msisdn:"
				+ deviceSnapShot.getMsisdn());
		try {
			deviceSnapShotService.save(deviceSnapShot);
		} catch (Exception e) {
			logger.error("Insert into DeviceSnapShot: IMEI:" + deviceSnapShot.getImei() + ", Msisdn:"
					+ deviceSnapShot.getMsisdn() + ", Exception(" + e.getMessage() + ")");
		}
	}

	public void insertPendingActions(PendingActions pendingActions) {
		logger.info("Insert into PendingActions: IMEI:" + pendingActions.getImei() + ", Msisdn:"
				+ pendingActions.getMsisdn());
		try {
			pendingActionsService.save(pendingActions);
		} catch (Exception e) {
			logger.error("Insert into PendingActions: IMEI:" + pendingActions.getImei() + ", Msisdn:"
					+ pendingActions.getMsisdn() + ", Exception(" + e.getMessage() + ")");
		}
	}

	public void insertDuplicateImeiMsisdn(DuplicateImeiMsisdn duplicateImeiMsisdn) {
		logger.info("Insert into DuplicateImeiMsisdn: IMEI:" + duplicateImeiMsisdn.getImeiMsisdnIdentity().getImei()
				+ ", Msisdn:" + duplicateImeiMsisdn.getImeiMsisdnIdentity().getMsisdn());
		try {
			this.duplicateImeiMsisdnService.save(duplicateImeiMsisdn);
		} catch (Exception e) {
			logger.error("Insert into DuplicateImeiMsisdn: IMEI:"
					+ duplicateImeiMsisdn.getImeiMsisdnIdentity().getImei() + ", Msisdn:"
					+ duplicateImeiMsisdn.getImeiMsisdnIdentity().getMsisdn() + ", Exception(" + e.getMessage() + ")");
		}
	}

	public void insertNullMsisdnRegularized(NullMsisdnRegularized nullMsisdnRegularized) {
		logger.info("Insert into NullMsisdnRegularized: Msisdn:" + nullMsisdnRegularized.getMsisdn());
		try {
			this.nullMsisdnRegularizedService.save(nullMsisdnRegularized);
		} catch (Exception e) {
			logger.error("Exception into NullMsisdnRegularized: Msisdn:" + nullMsisdnRegularized.getMsisdn()
					+ ", Exception(" + e.getMessage() + ")");
		}
	}
}
