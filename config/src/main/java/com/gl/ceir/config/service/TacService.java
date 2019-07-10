package com.gl.ceir.config.service;

import com.gl.ceir.config.model.Tac;

public interface TacService extends RestServices<Tac> {
	public Tac get(String id);
}
