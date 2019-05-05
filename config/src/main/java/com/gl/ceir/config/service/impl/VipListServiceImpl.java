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
	public VipList save(VipList t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VipList get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VipList update(VipList t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long t) {
		// TODO Auto-generated method stub

	}

}
