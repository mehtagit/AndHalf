package com.gl.ceir.config.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.model.VipList;
import com.gl.ceir.config.repository.VipListRepository;
import com.gl.ceir.config.service.VipListService;

@Service
public class VipListServiceImpl implements VipListService {

	@Autowired
	private VipListRepository vipListRepository;

	@Override
	public List<VipList> getAll() {
		return vipListRepository.findAll();
	}

	@Override
	public VipList save(VipList vipList) {
		return vipListRepository.save(vipList);
	}

	@Override
	public VipList get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VipList update(VipList vipList) {
		return vipListRepository.save(vipList);
	}

	@Override
	public void delete(Long id) {
		vipListRepository.deleteById(id);
	}

	@Override
	public VipList getByMsisdn(Long msisdn) {
		return vipListRepository.findByMsisdn(msisdn);
	}

	@Override
	public VipList getByImei(Long imei) {
		return vipListRepository.findByImei(imei);
	}

	@Override
	public VipList getByMsisdnAndImei(Long msisdn, Long imei) {
		return vipListRepository.findByMsisdnAndImei(msisdn, imei);
	}

}
