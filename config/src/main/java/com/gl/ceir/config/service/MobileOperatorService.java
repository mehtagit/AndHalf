package com.gl.ceir.config.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.model.MobileOperator;
import com.gl.ceir.config.repository.MobileOperatorRepository;

@Service
public class MobileOperatorService {
	
	@Autowired
	MobileOperatorRepository mobileOperatorRepository;

	public List<MobileOperator> getAll()
	{
		return mobileOperatorRepository.findAll();
	}
	
	public MobileOperator save(MobileOperator mobileOperator)
	{
		return mobileOperatorRepository.save(mobileOperator);
	}
	
	public MobileOperator findById(Integer iD)
	{
		Optional<MobileOperator> mo = mobileOperatorRepository.findById(iD);
		return mo.get();
	}
	
	public void delete(Integer id)
	{
		mobileOperatorRepository.deleteById(id);
	}
	
	public MobileOperator update(Integer id, MobileOperator mobileOperator)
	{
		MobileOperator moSaved = findById(id);
		if (moSaved == null)
		{
			return null;
		}
		else
		{
			mobileOperator.setiD(moSaved.getiD());
			return mobileOperatorRepository.save(mobileOperator);
		}
	}
}

