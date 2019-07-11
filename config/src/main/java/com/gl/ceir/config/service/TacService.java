package com.gl.ceir.config.service;

import org.springframework.web.multipart.MultipartFile;

import com.gl.ceir.config.model.Tac;
import com.gl.ceir.config.model.UploadFileResponse;

public interface TacService extends RestServices<Tac> {
	public Tac get(String id);
	public UploadFileResponse upload(MultipartFile file);
}
