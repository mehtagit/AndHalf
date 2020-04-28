package com.ceir.CeirCode.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.ceir.CeirCode.model.SystemConfigurationDb;
import com.ceir.CeirCode.model.User;
import com.ceir.CeirCode.model.UserPayment;
import com.ceir.CeirCode.model.UserToStakehoderfeatureMapping;
import com.ceir.CeirCode.model.constants.Paymentstatus;
import com.ceir.CeirCode.othermodel.ChangePeriod;
import com.ceir.CeirCode.othermodel.PaymentParam;
import com.ceir.CeirCode.othermodel.PaymentResponse;
import com.ceir.CeirCode.repo.PaymentRepo;
import com.ceir.CeirCode.repoService.SystemConfigDbRepoService;
import com.ceir.CeirCode.response.GenricResponse;
import com.ceir.CeirCode.response.tags.PaymentTags;
import com.ceir.CeirCode.response.tags.PortAddsTags;
import com.ceir.CeirCode.response.tags.UserTypeFeatureTags;
import com.ceir.CeirCode.util.GenerateRandomDigits;
import com.ceir.CeirCode.util.HttpResponse;
import com.ceir.CeirCode.util.Utility;

@Service
public class PaymentService {

	@Autowired
	GenerateRandomDigits randomDigits;
	
	@Autowired
	Utility utility;
	
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	SystemConfigDbRepoService systemConfigService;
	
	@Autowired
	PaymentRepo paymentRepo;
	
	public ResponseEntity<?> payment(PaymentParam payment){
		log.info("inside payment controller");
		log.info("data given: "+payment);
		String txnId=utility.currentDateTimeInSeconds()+randomDigits.getNumericString(3);
		log.info("here generated txn id: "+txnId);
		SystemConfigurationDb urlDb=systemConfigService.getDataByTag("Payment_Url");
		log.info("url from db:  "+urlDb.getValue());
		String url=urlDb.getValue().replaceAll("T", txnId);
		UserPayment userPayment=new UserPayment();
	
			User user=new User(payment.getUserId());
			UserPayment paymentData=new UserPayment(txnId,payment.getAmount(),user,Paymentstatus.Fail.getCode());
			try {
			 userPayment=paymentRepo.save(paymentData);
			}
			catch(Exception e) {
				 log.info("error occurs while add userPayment entry in db:  ");
			}
		
		if(userPayment!=null) {
			PaymentResponse paymentResponse=new PaymentResponse(url);
			GenricResponse response=new GenricResponse(200,PaymentTags.url_Sucess.getMessage(),PaymentTags.url_Sucess.getTag(),paymentResponse);
			return  new ResponseEntity<>(response,HttpStatus.OK);
		}
		else {
			GenricResponse response=new GenricResponse(500,PaymentTags.url_Fail.getMessage(),PaymentTags.url_Fail.getTag());
			return  new ResponseEntity<>(response,HttpStatus.OK);
		}

	}
	
	public ResponseEntity<?> paymentStatus(String txnId,String status){
		log.info("inside  paymentStatus   controller");  
		log.info(" txnId:  "+txnId +"status: "+status);      
		log.info("get userTypeFeature  data by  id below"); 
		UserPayment paymentData=new UserPayment();
		try {
			paymentData=paymentRepo.findByTxnId(txnId);	
		}
		catch(Exception e) {
			log.info(e.getMessage());
			log.info(e.toString());
		}
		if(paymentData!=null) {
		   Integer paymentStatus;
			if(Paymentstatus.Success.getDescription().equalsIgnoreCase(status)) {
				paymentStatus=Paymentstatus.Success.getCode();
				/*
				 * } else { paymentStatus=Paymentstatus.Fail.getCode(); }
				 */
			paymentData.setStatus(paymentStatus);
		    UserPayment output=new UserPayment();
			try {
				output=paymentRepo.save(paymentData);
			}
			catch(Exception e) {
				log.info(e.getMessage());
				log.info(e.toString());
			}
			
			if(output!=null) {
				HttpResponse response=new HttpResponse("Payment status successfully update",
						200);
				log.info("response send:  "+response);
				return new ResponseEntity<>(response,HttpStatus.OK);	
			}
			else {
				HttpResponse response=new HttpResponse("Something wrong happened..  please try after some time",
						500);
				log.info("response send"+response);
				return new ResponseEntity<>(response,HttpStatus.OK);	
			} 
		}
		else {
			//paymentStatus=Paymentstatus.Fail.getCode();
			HttpResponse response=new HttpResponse("Payment failed",
					422);
			log.info("response send:  "+response);
			return new ResponseEntity<>(response,HttpStatus.OK);	

		}
		}    
		else { 
			HttpResponse response=new HttpResponse("transation id id wrong",422);
			log.info("response send"+response);
			return new ResponseEntity<>(response,HttpStatus.OK);	
		}
	}
	
	
}
