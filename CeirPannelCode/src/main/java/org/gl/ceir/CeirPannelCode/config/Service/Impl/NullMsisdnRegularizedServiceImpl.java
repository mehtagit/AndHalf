package org.gl.ceir.CeirPannelCode.config.Service.Impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.gl.ceir.CeirPannelCode.config.exceptions.ResourceNotFoundException;
import org.gl.ceir.CeirPannelCode.config.exceptions.ResourceServicesException;
import org.gl.ceir.CeirPannelCode.config.Model.NullMsisdnRegularized;
import org.gl.ceir.CeirPannelCode.config.Repository.NullMsisdnRegularizedRepository;
import org.gl.ceir.CeirPannelCode.config.Service.NullMsisdnRegularizedService;

@Service
public class NullMsisdnRegularizedServiceImpl implements NullMsisdnRegularizedService {

	private static final Logger logger = LogManager.getLogger(NullMsisdnRegularizedServiceImpl.class);

	@Autowired
	private NullMsisdnRegularizedRepository repo;

	@Override
	public NullMsisdnRegularized get(Long msisdn) {
		try {
			NullMsisdnRegularized nullMsisdnRegularized = repo.findById(msisdn)
					.orElseThrow(() -> new ResourceNotFoundException("NullMsisdnRegularized", "msisdn", msisdn));
			return nullMsisdnRegularized;
		} catch (ResourceNotFoundException e) {
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	@Override
	public List<NullMsisdnRegularized> saveAll(List<NullMsisdnRegularized> nullMsisdnRegularizeds) {
		return repo.saveAll(nullMsisdnRegularizeds);
	}

	@Override
	public NullMsisdnRegularized save(NullMsisdnRegularized nullMsisdnRegularized) {
		return repo.save(nullMsisdnRegularized);
	}

}
