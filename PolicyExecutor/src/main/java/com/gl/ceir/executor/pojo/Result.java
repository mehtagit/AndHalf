package com.gl.ceir.executor.pojo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.gl.ceir.config.model.DeviceSnapShot;
import com.gl.ceir.config.model.DuplicateImeiMsisdn;
import com.gl.ceir.config.model.NullMsisdnRegularized;
import com.gl.ceir.config.model.PendingActions;

@Component
public class Result {
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

}
