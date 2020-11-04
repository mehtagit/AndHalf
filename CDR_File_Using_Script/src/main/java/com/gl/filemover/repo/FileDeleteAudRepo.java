package com.gl.filemover.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gl.filemover.entity.CDRFilesDeleteAud;
@Repository
public interface FileDeleteAudRepo extends JpaRepository<CDRFilesDeleteAud, Long>{

}
