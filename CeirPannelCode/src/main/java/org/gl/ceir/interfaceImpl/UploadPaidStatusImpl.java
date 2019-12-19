package org.gl.ceir.interfaceImpl;

import javax.servlet.http.HttpServletRequest;

import org.gl.ceir.CeirPannelCode.Model.UplodPaidStatusModel;
import org.gl.ceir.interfacepackage.UploadPaidStatus;
import org.springframework.beans.factory.annotation.Autowired;

public class UploadPaidStatusImpl implements UploadPaidStatus {

	@Autowired
	UplodPaidStatusModel model;
	
	@Override
	public UplodPaidStatusModel saveUploadPaidStatus(HttpServletRequest request) {
		// TODO Auto-generated method stub
		/*
		 * model.setNid(request.getParameter(""));
		 * model.setFileName(request.getParameter(""));
		 * model.setFirstName(request.getParameter(""));
		 * model.setMiddleName(request.getParameter(""));
		 * model.setLastName(request.getParameter(""));
		 * model.setLocality(request.getParameter(""));
		 * model.setStreet(request.getParameter(""));
		 * model.setCountry(request.getParameter(""));
		 * model.setProvince(request.getParameter(""));
		 * model.setEmail(request.getParameter(""));
		 * 
		 */
		
		return model;
	}

	

}
