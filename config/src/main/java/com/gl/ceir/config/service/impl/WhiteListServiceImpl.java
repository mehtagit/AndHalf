package com.gl.ceir.config.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.model.WhiteList;
import com.gl.ceir.config.repository.WhiteListRepository;
import com.gl.ceir.config.service.WhiteListService;

@Service
public class WhiteListServiceImpl implements WhiteListService {

	@Autowired
	private WhiteListRepository whiteListRepository;

	@Override
	public List<WhiteList> getAll() {
		return whiteListRepository.findAll();
	}

	@Override
	public WhiteList save(WhiteList whiteList) {
		return whiteListRepository.save(whiteList);
	}

	@Override
	public WhiteList get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		whiteListRepository.deleteById(id);
	}

	@Override
	public WhiteList update(WhiteList whiteList) {
		return whiteListRepository.save(whiteList);
	}

	@Override
	public WhiteList getByMsisdn(Long msisdn) {
		return whiteListRepository.findByMsisdn(msisdn);
	}

	@Override
	public WhiteList getByImei(Long imei) {
		return whiteListRepository.findByImei(imei);
	}

	@Override
	public WhiteList getByMsisdnAndImei(Long msisdn, Long imei) {
		return whiteListRepository.findByMsisdnAndImei(msisdn, imei);
	}

}
