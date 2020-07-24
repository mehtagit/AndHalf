package com.ceir.CeirCode.repo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ceir.CeirCode.model.UserProfile;
import com.ceir.CeirCode.model.Usertype;


public interface UsertypeRepo extends JpaRepository<Usertype, Long> ,JpaSpecificationExecutor<Usertype>{
	public List<Usertype> findAll();
	public Usertype findById(long id);
	public List<Usertype> findBySelfRegister(Integer id);
	public Usertype findByUsertypeName(String usertype);
	public List<Usertype> findBySelfRegisterOrSelfRegister(Integer id,Integer usertype);
}
