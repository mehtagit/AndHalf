package org.gl.ceir.interfaceImpl;

import javax.servlet.http.HttpServletRequest;

import org.gl.ceir.CeirPannelCode.Model.TRCRegisteration;
import org.gl.ceir.interfacepackage.Registeration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class RegisterationImpl implements Registeration{
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	
	@Autowired
	TRCRegisteration model;
	
	@Override
	public TRCRegisteration register(HttpServletRequest request,String fileName) {
		// TODO Auto-generated method stub
		
		model.setManufacturerId(request.getParameter("manufacturerId"));
		model.setManufacturerName(request.getParameter("manufacturerName"));
		model.setCountry(request.getParameter("country"));
		model.setTac(request.getParameter("tac"));
		model.setStateInterp(request.getParameter("stateInterp"));
		model.setApproveDisapproveDate(request.getParameter("approveDisapproveDate"));
		model.setRemark(request.getParameter("remark"));
		model.setFile(fileName);
		log.info("---------modelmodel---------"+request.getParameter("manufacturerId"));
		log.info("---------modelmodel---------"+model);
		return model;
	}


}
