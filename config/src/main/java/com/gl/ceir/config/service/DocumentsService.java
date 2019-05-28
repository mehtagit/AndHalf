package com.gl.ceir.config.service;

import java.util.List;

import com.gl.ceir.config.model.Documents;
import com.gl.ceir.config.model.ImeiMsisdnIdentity;

public interface DocumentsService {
	public Documents get(Long id);

	public Documents save(Documents documents);

	public Documents updateStatus(Documents documents);

	public List<Documents> getByTicketId(String ticketId);

	public List<Documents> getByMsisdnAndImei(ImeiMsisdnIdentity imeiMsisdnIdentity);
}
