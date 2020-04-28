package com.ceir.CeirCode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ceir.CeirCode.model.PortAddress;
import com.ceir.CeirCode.model.constants.Paymentstatus;
import com.ceir.CeirCode.othermodel.ChangePeriod;
import com.ceir.CeirCode.othermodel.PaymentParam;
import com.ceir.CeirCode.service.PaymentService;
import com.ceir.CeirCode.util.HttpResponse;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
@RequestMapping("/payment")
public class PaymentGateway {

	@Autowired
	PaymentService paymentService;
	
//	@ApiOperation(value="send params to third party url for payment")
//	@PostMapping("/callUrl")
//	public ResponseEntity<?> saveAddressPort(@RequestBody PortAddress portAddress){
//		return portAddressService.saveAddressPort(portAddress);
//	}

	@ApiOperation(value = "call payment url", response = HttpResponse.class)
	@PostMapping("/url")
	public ResponseEntity<?> paymentUrl(@RequestBody PaymentParam paymentParam){
		return paymentService.payment(paymentParam);
	}   
	
	@ApiOperation(value = "change payment status", response = HttpResponse.class)
	@PostMapping("/status")
	public ResponseEntity<?> updateUserTypeFeaturePeriod(@RequestParam(name ="status",required = true)String status
			                                             ,@RequestParam(name ="txnId",required = true)String txnId){
		return   paymentService.paymentStatus(txnId, status);
	}   
}
