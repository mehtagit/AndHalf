package org.gl.ceir.CeirPannelCode.Model;

import java.util.HashMap;
import java.util.Map;

public class AttachedFile {

	private String docType;
	private String docTypeInterp;
	private String fileName;
	private String txnId;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	public String getDocType() {
		return docType;
	}
	public void setDocType(String docType) {
		this.docType = docType;
	}
	public String getDocTypeInterp() {
		return docTypeInterp;
	}
	public void setDocTypeInterp(String docTypeInterp) {
		this.docTypeInterp = docTypeInterp;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getTxnId() {
		return txnId;
	}
	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}
	public Map<String, Object> getAdditionalProperties() {
		return additionalProperties;
	}
	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}
	@Override
	public String toString() {
		return "AttachedFile [docType=" + docType + ", docTypeInterp=" + docTypeInterp + ", fileName=" + fileName
				+ ", txnId=" + txnId + ", additionalProperties=" + additionalProperties + "]";
	}
	
	
	
}
