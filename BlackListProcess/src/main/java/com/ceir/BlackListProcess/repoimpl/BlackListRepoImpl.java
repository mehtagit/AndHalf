package com.ceir.BlackListProcess.repoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ceir.BlackListProcess.model.BlackList;
import com.ceir.BlackListProcess.model.BlacklistDbHistory;
import com.ceir.BlackListProcess.repository.BlackListRepository;
import com.ceir.BlackListProcess.repository.BlackListTrackDetailsRepository;

@Service
public class BlackListRepoImpl {

	@Autowired
	BlackListRepository blackListRepo;

	@Autowired
	BlackListTrackDetailsRepository blackListHistoryRepo;

	public BlackList saveBlackList(BlackList blackList) {
		BlackList output=new BlackList();
		try {
			output=blackListRepo.save(blackList);
			return output;
		}
		catch(Exception e) {
			return null;
		}
	}

	public BlacklistDbHistory saveBlackListHistory(BlacklistDbHistory blackListHistory) {
		BlacklistDbHistory output=new BlacklistDbHistory();
		try {
			output=blackListHistoryRepo.save(blackListHistory);
			return output;
		}
		catch(Exception e) {
			return null;
		}
	}

}
