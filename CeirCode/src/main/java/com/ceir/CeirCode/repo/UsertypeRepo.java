package com.ceir.CeirCode.repo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ceir.CeirCode.model.Usertype;

public interface UsertypeRepo extends JpaRepository<Usertype, Long>{
	public List<Usertype> findAll();
	public Usertype findById(long id); 
	public Usertype findByUsertypeName(String usertype);
}
