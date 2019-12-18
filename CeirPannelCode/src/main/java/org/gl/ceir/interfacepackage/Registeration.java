package org.gl.ceir.interfacepackage;

import javax.servlet.http.HttpServletRequest;

import org.gl.ceir.CeirPannelCode.Model.TRCRegisteration;

public interface Registeration {
public TRCRegisteration register(HttpServletRequest request,String fileName);
public TRCRegisteration updateRegister(HttpServletRequest request);
}
