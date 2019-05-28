package com.learning.demo.services;

import java.util.List;
import java.util.Set;

import com.learning.demo.resource.Documents;
import com.learning.demo.resource.ImeiMsisdnIdentity;

public interface DocumentsService {
	public Set<Documents> get(ImeiMsisdnIdentity imeiMsisdnIdentity);

	public Documents save(Documents documents);

	public Documents updateStatus(Documents documents);

	public List<Documents> getByTicketId(String ticketId);
}
