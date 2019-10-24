package org.gl.ceir.CeirPannelCode.config.Service;

import java.util.List;

import org.gl.ceir.CeirPannelCode.config.Model.NullMsisdnRegularized;

public interface NullMsisdnRegularizedService {
	public NullMsisdnRegularized get(Long msisdn);
	
	public NullMsisdnRegularized save(NullMsisdnRegularized nullMsisdnRegularized);

	public List<NullMsisdnRegularized> saveAll(List<NullMsisdnRegularized> nullMsisdnRegularizeds);
}
