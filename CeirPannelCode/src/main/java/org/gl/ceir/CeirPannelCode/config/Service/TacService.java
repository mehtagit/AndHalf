package org.gl.ceir.CeirPannelCode.config.Service;

import org.springframework.web.multipart.MultipartFile;

import org.gl.ceir.CeirPannelCode.config.Model.Tac;
import org.gl.ceir.CeirPannelCode.config.Model.UploadFileResponse;

public interface TacService extends RestServices<Tac> {
	public Tac get(String id);
	public UploadFileResponse upload(MultipartFile file);
}
