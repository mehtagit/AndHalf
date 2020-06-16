package org.gl.ceir.CeirPannelCode.Model;

public class CustomerCareByTxnId {

	private String date, imei, name, status, txnId;
	private int featureId;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CustomerCareByTxnId [date=");
		builder.append(date);
		builder.append(", imei=");
		builder.append(imei);
		builder.append(", name=");
		builder.append(name);
		builder.append(", status=");
		builder.append(status);
		builder.append(", txnId=");
		builder.append(txnId);
		builder.append(", featureId=");
		builder.append(featureId);
		builder.append("]");
		return builder.toString();
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTxnId() {
		return txnId;
	}

	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}

	public int getFeatureId() {
		return featureId;
	}

	public void setFeatureId(int featureId) {
		this.featureId = featureId;
	}

}
