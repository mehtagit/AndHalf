package com.gl.CEIR.FileProcess.factory;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

import com.gl.CEIR.FileProcess.parse.impl.ConsignmentFileParser;

@Component
public class PrototypeBeanProvider {
	
	@Lookup
    public ConsignmentFileParser getConsignmentFileParserBean() {
        return null;
    }
}
