package org.file.repo;

import org.file.entity.CDRFileRecords;
import org.springframework.data.jpa.repository.JpaRepository;
public interface FileRepo extends JpaRepository<CDRFileRecords, Long>{

}
