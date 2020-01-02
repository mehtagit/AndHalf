package com.gl.CEIR.FileProcess.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.CEIR.FileProcess.model.entity.ErrorCodes;

public interface ErrorCodesRepository extends JpaRepository<ErrorCodes, Long> {

	public ErrorCodes findByErrorCode(String errorCode);

}
