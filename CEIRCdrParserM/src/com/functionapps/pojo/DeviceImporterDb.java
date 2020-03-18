package com.functionapps.pojo;

import java.io.Serializable;

public class DeviceImporterDb  implements Serializable{

	private static final long serialVersionUID = 1L;

	private String imeiEsnMeid;
	private String txnId;
	
	public String getImeiEsnMeid() {
		return imeiEsnMeid;
	}
	public void setImeiEsnMeid(String imeiEsnMeid) {
		this.imeiEsnMeid = imeiEsnMeid;
	}
	public String getTxnId() {
		return txnId;
	}
	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DeviceImporterDb [imeiEsnMeid=");
		builder.append(imeiEsnMeid);
		builder.append(", txnId=");
		builder.append(txnId);
		builder.append("]");
		return builder.toString();
	}

}
