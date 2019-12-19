package org.gl.ceir.interfacepackage;

import javax.servlet.http.HttpServletRequest;


import org.gl.ceir.CeirPannelCode.Model.UplodPaidStatusModel;

public interface UploadPaidStatus {
	public UplodPaidStatusModel saveUploadPaidStatus(HttpServletRequest request);

}
