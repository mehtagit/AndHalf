package com.ceir.CeirCode.repo;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ceir.CeirCode.model.Securityquestion;
public interface SecurityQuestionRepo extends JpaRepository<Securityquestion, Long>{
  public List<Securityquestion> findAll();
                                      
}
