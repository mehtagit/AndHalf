package com.gl.ceir.config.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.model.BlackList;
import com.gl.ceir.config.repository.BlackListRepository;
import com.gl.ceir.config.service.BlackListService;

@Service
public class BlackListServiceImpl implements BlackListService {

	@Autowired
	private BlackListRepository blackListRepository;

	@Override
	public List<BlackList> getAll() {
		return blackListRepository.findAll();
	}

	@Override
	public BlackList save(BlackList blackList) {
		return blackListRepository.save(blackList);
	}

	@Override
	public BlackList get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		blackListRepository.deleteById(id);
	}

	@Override
	public BlackList update(BlackList blackList) {
		return blackListRepository.save(blackList);
	}

	@Override
	public BlackList getByMsisdn(Long msisdn) {
		return blackListRepository.findByMsisdn(msisdn);
	}

	@Override
	public BlackList getByImei(Long imei) {
		return blackListRepository.findByImei(imei);
	}

	@Override
	public BlackList getByMsisdnAndImei(Long msisdn, Long imei) {
		return blackListRepository.findByMsisdnAndImei(msisdn, imei);
	}

}
