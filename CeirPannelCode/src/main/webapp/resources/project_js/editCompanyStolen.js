
$(document).ready(function() {
 // executes when HTML-Document is loaded and DOM is ready

 viewIndivisualStolen()
 
});


function viewIndivisualStolen()
{


var txnid=$('#existingStolenTxnId').val();
	
	$.ajax({
		url: './openStolenAndRecoveryPage?txnId='+txnid,
		type: 'POST',
		processData: false,
		contentType: false,
		success: function (response, textStatus, jqXHR) {
		console.log(response)
		
		$('#bulkStolencompanyName').val(response.stolenOrganizationUserDB.companyName);
		$('#bulkStolenaddress').val(response.stolenOrganizationUserDB.propertyLocation);
		$('#bulkStolenstreetNumber').val(response.stolenOrganizationUserDB.street);
		$('#bulkStolenvillage').val(response.stolenOrganizationUserDB.village);
		$('#bulkStolenlocality').val(response.stolenOrganizationUserDB.locality);
		$('#bulkStolendistrict').val(response.stolenOrganizationUserDB.district);
		$('#bulkStolencommune').val(response.stolenOrganizationUserDB.commune);
		$('#bulkStolenpin').val(response.stolenOrganizationUserDB.postalCode);
		$('#country2').val(response.stolenOrganizationUserDB.country).change();
		$('#state2').val(response.stolenOrganizationUserDB.province);
		
		$('#firstName').val(response.stolenOrganizationUserDB.personnelFirstName);
		$('#bulkStolenmiddleName').val(response.stolenOrganizationUserDB.personnelMiddleName);
		$('#bulkStolenlastName').val(response.stolenOrganizationUserDB.personnelLastName);
		$('#deviceBulkStolenaddress').val(response.stolenOrganizationUserDB.incidentPropertyLocation)
		$('#deviceBulkStolenstreetNumber').val(response.stolenOrganizationUserDB.incidentStreet);
		$('#deviceBulkStolenvillage').val(response.stolenOrganizationUserDB.incidentVillage);
		$('#deviceBulkStolenlocality').val(response.stolenOrganizationUserDB.incidentLocality);
		$('#deviceBulkStolendistrict').val(response.stolenOrganizationUserDB.incidentDistrict);
		$('#deviceBulkStolencommune').val(response.stolenOrganizationUserDB.incidentCommune);
		$('#deviceBulkStolenpin').val(response.stolenOrganizationUserDB.incidentPostalCode);
		$('#country3').val(response.stolenOrganizationUserDB.incidentCountry).change();
		
		$('#deviceBulkStolenComplaint').val(response.complaintType);
		$('#bulkStolenofficeEmail').val(response.stolenOrganizationUserDB.email);
		$('#bulkStolenContact').val(response.stolenOrganizationUserDB.phoneNo);
		
		$('#state3').val(response.stolenOrganizationUserDB.incidentProvince);
		$('#singleStolenComplaintType').val(response.complaintType);
		$('#deviceBulkStolenquantity').val(response.qty);
		$('#deviceBulkStolenRemark').val(response.remark);
		$('#stolenFileName').val(response.fileName);
		
		$('#IndivisualStolenDate').val(response.dateOfStolen);
		$('#uploadFirSingleName').val(response.firFileName);
		$('#bulkDeviceRejectRemark').val(response.rejectedRemark);
		$("label[for='IndivisualStolenDate']").addClass('active');
		
		$('input[name=stolenBulkBlockPeriod][value='+response.blockingType+']').attr('checked', true); 
		
		if(response.blockingType=='tilldate')
			{
			$("#stolenCalender").css("display", "block"); 
			
			$("#stolenBulkDatePeriod").val(response.blockingTimePeriod);
			}
		else{
			$("#calender").css("display", "none"); 
		}
		
		$('#firFilePreview').attr("onclick",'previewFile("'+response.fileLink+'","'+response.firFileName+'","'+response.txnId+'")');
		//$('#deviceListlink').attr("onclick",'previewFile("'+response.fileLink+'","'+response.fileName+'","'+response.txnId+'")');
		},
		error: function (jqXHR, textStatus, errorThrown) {
			console.log("error in ajax")

		}
	});
}



function updateCompanyStolenDetails(){
	
	var formData= new FormData();
	
	var bulkStolencompanyName=$('#bulkStolencompanyName').val();
	var bulkStolenaddress=$('#bulkStolenaddress').val();
	var bulkStolenstreetNumber=$('#bulkStolenstreetNumber').val();
	var bulkStolenvillage=$('#bulkStolenvillage').val();
	var bulkStolenlocality=$('#bulkStolenlocality').val();
	var bulkStolendistrict=$('#bulkStolendistrict').val();
	var bulkStolencommune=$('#bulkStolencommune').val();
	var bulkStolenpin=$('#bulkStolenpin').val();
	var country2=$('#country2').val();
	var state2=$('#state2').val();

	var txnid=$('#existingStolenTxnId').val();
	var blockingTimePeriod=$('#stolenBulkDatePeriod').val();
	var blockingType =$('.blocktypeRadio:checked').val();
	var fileName=$('#stolenFileName').val();
	
	var firstName=$('#firstName').val();
	var bulkStolenmiddleName=$('#bulkStolenmiddleName').val();
	var bulkStolenlastName=$('#bulkStolenlastName').val();
	var bulkStolenofficeEmail=$('#bulkStolenofficeEmail').val();
	var trimContactNumber=$('#bulkStolenContact').val();
	var bulkStolenContact =trimContactNumber.replace(/[^A-Z0-9]/ig, "");

	
	var deviceBulkStolenaddress=$('#deviceBulkStolenaddress').val();
	var deviceBulkStolenstreetNumber=$('#deviceBulkStolenstreetNumber').val();
	var deviceBulkStolenvillage=$('#deviceBulkStolenvillage').val();
	var deviceBulkStolenlocality=$('#deviceBulkStolenlocality').val();
	var deviceBulkStolendistrict=$('#deviceBulkStolendistrict').val();
	var deviceBulkStolencommune=$('#deviceBulkStolencommune').val();
	var deviceBulkStolenpin=$('#deviceBulkStolenpin').val();
	var country3 = $('#country3').val();
	var state3= $('#state3').val();
	var deviceBulkStolenComplaint=$('#deviceBulkStolenComplaint').val();
	var deviceBulkStolenquantity=$('#deviceBulkStolenquantity').val();
	var deviceBulkStolenRemark=$('#deviceBulkStolenRemark').val();
	var bulkStolenDate=$('#IndivisualStolenDate').val();
	var uploadFirSingle=$('#uploadFirSingleName').val();

	
	var stolenOrganizationUserDB= {
    "commune": bulkStolencommune,
    "companyName": bulkStolencompanyName,
    "country": country2,
    "district": bulkStolendistrict,
    "email": bulkStolenofficeEmail,
    "incidentCommune": deviceBulkStolencommune,
    "incidentCountry": country3,
    "incidentDistrict": deviceBulkStolendistrict,
    "incidentLocality": deviceBulkStolenlocality,
    "incidentPostalCode": deviceBulkStolenpin,
    "incidentPropertyLocation":deviceBulkStolenaddress,
    "incidentProvince": state3,
    "incidentStreet": deviceBulkStolenstreetNumber,
    "incidentVillage": deviceBulkStolenvillage,
    "locality": deviceBulkStolenlocality ,
    "personnelFirstName": firstName,
    "personnelLastName":bulkStolenlastName ,
    "personnelMiddleName":bulkStolenmiddleName ,
    "phoneNo": bulkStolenContact,
    "postalCode": bulkStolenpin,
    "propertyLocation": bulkStolenaddress,
    "province": state2,
    "street": bulkStolenstreetNumber,
    "village": bulkStolenvillage
  }
	
	
	var request={
			"txnId":txnid,
			"fileName":fileName,
			"firFileName":uploadFirSingle,
			"remark":deviceBulkStolenRemark,
			"qty":deviceBulkStolenquantity,
			"dateOfStolen":bulkStolenDate,
			"complaintType":deviceBulkStolenComplaint,
			"blockingTimePeriod":blockingTimePeriod,
			"blockingType":blockingType,
			"requestType":0,
			"sourceType":6,
			"stolenOrganizationUserDB":stolenOrganizationUserDB
	}
	formData.append('file', $('#deviceBulkStolenFile')[0].files[0]);
	formData.append('firFileName',$('#uploadFirSingle')[0].files[0]);
	formData.append("request",JSON.stringify(request));

	$.ajax({
		url: './lawfulIndivisualStolenUpdate',
		type: 'POST',
		data: formData,
		processData: false,
		contentType: false,
		success: function (response, textStatus, jqXHR) {
		console.log(response)
		
		if(response.errorCode==0){
			$("#companyStolenButton").prop('disabled', true);
			$('#stolenSucessPopUp').openModal({dismissible:false});;
			}
		else{
			$('#stolenSucessPopUp').openModal({dismissible:false});;
			$('#dynamicMessage').text('');
			$('#dynamicMessage').text(response.message);
		}
		/*	if(response.errorCode==0){
				$("#bulkStolenButton").prop('disabled', true);
				$('#IndivisualStolenSucessPopup').openModal();
				$('#IndivisualStolenTxnId').text(response.txnId)
			}
			else{
//				$('#sucessMessage').text('');
				$('#regularisedDevice').openModal();
				$('#dynamicTxnId').text(data.txnId);
			}*/
		},
		error: function (jqXHR, textStatus, errorThrown) {
			console.log("error in ajax");

		}
	});
	return false;
}

function clearFileName() {
	var fieldId=$('#FilefieldId').val();
	
	 if(fieldId=='deviceBulkStolenFile')
		{
		$('#'+fieldId).val('');
		$('#stolenFileName').val('');
		}
	else if(fieldId=='uploadFirSingle')
	{
		$('#'+fieldId).val('');
	$('#uploadFirSingleName').val('');
	}
	
	$('#fileFormateModal').closeModal();
	$('#FilefieldId').val('');
}