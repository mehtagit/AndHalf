package com.gl.ceir.webactionadapter.factory;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component
public class PrototypeBeanProvider<T> {
	
	@Lookup
    public T getBean() {
        return null;
    }
}
