package com.example;




import com.example.GsmaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GsmaRepository extends JpaRepository<GsmaEntity, Long>{

}