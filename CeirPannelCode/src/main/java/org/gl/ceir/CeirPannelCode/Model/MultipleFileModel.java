package org.gl.ceir.CeirPannelCode.Model;

import org.springframework.web.multipart.MultipartFile;

public class MultipleFileModel {
	private String docTypeInterp;
	private MultipartFile fileName;
	@Override
	public String toString() {
		return "MultipleFileModel [docTypeInterp=" + docTypeInterp + ", fileName=" + fileName + "]";
	}
	public String getDocTypeInterp() {
		return docTypeInterp;
	}
	public void setDocTypeInterp(String docTypeInterp) {
		this.docTypeInterp = docTypeInterp;
	}
	public MultipartFile getFileName() {
		return fileName;
	}
	public void setFileName(MultipartFile fileName) {
		this.fileName = fileName;
	}
	



}
