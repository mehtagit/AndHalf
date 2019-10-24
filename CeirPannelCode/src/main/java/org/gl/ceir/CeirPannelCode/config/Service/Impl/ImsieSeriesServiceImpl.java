package org.gl.ceir.CeirPannelCode.config.Service.Impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.gl.ceir.CeirPannelCode.config.Model.ImsieSeries;
import org.gl.ceir.CeirPannelCode.config.Repository.ImsieSeriesRepository;
import org.gl.ceir.CeirPannelCode.config.Service.ImsieSeriesService;

@Service
public class ImsieSeriesServiceImpl implements ImsieSeriesService {

	private static final Logger logger = LogManager.getLogger(ImsieSeriesServiceImpl.class);

	@Autowired
	private ImsieSeriesRepository imsieSeriesRepository;

	@Override
	public List<ImsieSeries> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ImsieSeries save(ImsieSeries t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ImsieSeries get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ImsieSeries update(ImsieSeries t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long t) {
		// TODO Auto-generated method stub

	}

}
