package com.gl.ceir.evaluator.pojo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.gl.ceir.config.model.DeviceSnapShot;
import com.gl.ceir.config.model.PendingActions;

@Component
public class Result {
	private List<PendingActions> pendingBatch;
	private List<DeviceSnapShot> deviceSnapshotBatch;

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
	}
}
