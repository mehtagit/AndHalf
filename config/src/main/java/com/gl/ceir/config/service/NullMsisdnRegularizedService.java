package com.gl.ceir.config.service;

import java.util.List;

import com.gl.ceir.config.model.NullMsisdnRegularized;

public interface NullMsisdnRegularizedService {
	public NullMsisdnRegularized get(Long msisdn);

	public List<NullMsisdnRegularized> saveAll(List<NullMsisdnRegularized> nullMsisdnRegularizeds);
}
