package com.ceir.CeirCode.repoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceir.CeirCode.model.PeriodValidate;
import com.ceir.CeirCode.model.UserToStakehoderfeatureMapping;
import com.ceir.CeirCode.repo.UserToStakehoderfeatureMappingRepo;
@Service
public class UserFeatureRepoImpl {

	@Autowired
	UserToStakehoderfeatureMappingRepo userfeatureRepo;
	
	public UserToStakehoderfeatureMapping getByUsertypeIdAndFeatureId(PeriodValidate periodValidate) 
	{
		try 
		{
			return userfeatureRepo.findByUserTypeFeature_IdAndStakeholderFeature_Id(periodValidate.getUsertypeId(),periodValidate.getFeatureID());
		}
		catch(Exception e) {
			return null;
		}
		
	}
}
