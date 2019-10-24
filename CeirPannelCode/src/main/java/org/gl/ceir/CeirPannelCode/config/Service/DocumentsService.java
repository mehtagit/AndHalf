package org.gl.ceir.CeirPannelCode.config.Service;

import java.util.List;

import org.gl.ceir.CeirPannelCode.config.Model.DocumentStatus;
import org.gl.ceir.CeirPannelCode.config.Model.Documents;

public interface DocumentsService {
	public Documents get(Long id);

	public Documents save(Documents documents);

	public Documents updateStatus(DocumentStatus documentStatus, Long id);

	public List<Documents> getByMsisdnAndImei(Long imei, Long msisdn);
}
