/**
 * 
 */

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
		console.log(response.stolenIndividualUserDB.firstName)
		$('#singleStolenfirstName').val(response.stolenIndividualUserDB.firstName);
		$('#singleStolenmiddleName').val(response.stolenIndividualUserDB.middleName);
		$('#singleStolenlastName').val(response.stolenIndividualUserDB.lastName);
		$('#singleStolennIDPassportNumber').val(response.stolenIndividualUserDB.nid);
		$('#singleStolenemail').val(response.stolenIndividualUserDB.email);
		$('#singleStolenphone1').val(response.stolenIndividualUserDB.alternateContactNumber);
		$('#singleStolenaddress').val(response.stolenIndividualUserDB.propertyLocation);
		$('#singleStolenstreetNumber').val(response.stolenIndividualUserDB.street);
		$('#singleStolenvillage').val(response.stolenIndividualUserDB.village);
		$('#singleStolenlocality').val(response.stolenIndividualUserDB.locality);
		$('#singleStolendistrict').val(response.stolenIndividualUserDB.district);
		$('#singleStolencommune').val(response.stolenIndividualUserDB.commune);
		$('#singleStolenpin').val(response.stolenIndividualUserDB.postalCode);
		$('#country').val(response.stolenIndividualUserDB.country).change();
		$('#state').val(response.stolenIndividualUserDB.province);
		$('#singleStolendeviceBrandName').val(response.stolenIndividualUserDB.deviceBrandName);
		$('#singleStolenimeiNumber').val(response.stolenIndividualUserDB.imeiEsnMeid);
		$('#singleStolendeviceIDType').val(response.stolenIndividualUserDB.deviceIdType);
		$('#singleStolendeviceType').val(response.stolenIndividualUserDB.deviceType);
		$('#singleStolenmodalNumber').val(response.stolenIndividualUserDB.modelNumber);
		$('#singleStolenFileName').val(response.fileName);

		$('#singleStolenphone2').val(response.stolenIndividualUserDB.alternateContactNumber);
		$('#singleStolenOperator').val(response.stolenIndividualUserDB.operator);
		$('#singleStolenSimStatus').val(response.stolenIndividualUserDB.multiSimStatus);
		$('#singleStolenComplaintType').val(response.stolenIndividualUserDB.complaintType);
		$('#singleDeviceAddress').val(response.stolenIndividualUserDB.deviceStolenPropertyLocation);
		$('#singleDevicestreetNumber').val(response.stolenIndividualUserDB.deviceStolenStreet);
		$('#singleDevicevillage').val(response.stolenIndividualUserDB.deviceStolenVillage);
		$('#singleDevicelocality').val(response.stolenIndividualUserDB.deviceStolenLocality);
		$('#singleDevicedistrict').val(response.stolenIndividualUserDB.deviceStolenDistrict);
		$('#singleDevicecommune').val(response.stolenIndividualUserDB.deviceStolenCommune);
		$('#singleDevicepin').val(response.stolenIndividualUserDB.deviceStolenPostalCode);
		
		$('#singleDevicecountry').val(response.stolenIndividualUserDB.deviceStolenCountry).change();
		$('#singleDevicestate').val(response.stolenIndividualUserDB.deviceStolenProvince);
		$('#singleDeviceRemark').val(response.stolenIndividualUserDB.remark);
		
		
		},
		error: function (jqXHR, textStatus, errorThrown) {
			console.log("error in ajax")

		}
	});
}






function updateIndivisualStolen()
{


var formData= new FormData();
	
	var singleStolenfirstName=$('#singleStolenfirstName').val();
	var singleStolenmiddleName=$('#singleStolenmiddleName').val();
	var singleStolenlastName=$('#singleStolenlastName').val();
	var singleStolennIDPassportNumber=$('#singleStolennIDPassportNumber').val();
	var singleStolenemail=$('#singleStolenemail').val();
	var singleStolenphone1=$('#singleStolenphone1').val();
	var singleStolenaddress=$('#singleStolenaddress').val();
	var singleStolenstreetNumber=$('#singleStolenstreetNumber').val();
	var singleStolenvillage=$('#singleStolenvillage').val();
	var singleStolenlocality=$('#singleStolenlocality').val();
	var singleStolendistrict=$('#singleStolendistrict').val();
	var singleStolencommune=$('#singleStolencommune').val();
	var singleStolenpin=$('#singleStolenpin').val();
	var country=$('#country').val();
	var state=$('#state').val();
	var blockingTimePeriod=$('#stolenDatePeriod').val();
	var blockingType =$('.blocktypeRadio:checked').val();
	
	var singleStolendeviceBrandName=$('#singleStolendeviceBrandName').val();
	var singleStolenimeiNumber=$('#singleStolenimeiNumber').val();
	var singleStolendeviceIDType=$('#singleStolendeviceIDType').val();
	var singleStolendeviceType=$('#singleStolendeviceType').val();
	var singleStolenOperator=$('#singleStolenOperator').val();
	var singleStolenSimStatus=$('#singleStolenSimStatus').val();
	var singleStolenComplaintType=$('#singleStolenComplaintType').val();
	var singleStolenphone2 = $('#singleStolenphone2').val();
	var singleStolenmodalNumber= $('#singleStolenmodalNumber').val();
	
	var singleDeviceAddress=$('#singleDeviceAddress').val();
	var singleDevicestreetNumber=$('#singleDevicestreetNumber').val();
	var singleDevicevillage=$('#singleDevicevillage').val();
	var singleDevicelocality=$('#singleDevicelocality').val();
	var singleDevicedistrict=$('#singleDevicedistrict').val();
	var singleDevicecommune=$('#singleDevicecommune').val();
	var singleDevicepin=$('#singleDevicepin').val();
	var singleDevicecountry=$('#singleDevicecountry').val();
	var singleDevicestate=$('#singleDevicestate').val();
	var singleDeviceRemark=$('#singleDeviceRemark').val();
	var IndivisualStolenDate=$('#IndivisualStolenDate').val();	
	var txnid=$('#existingStolenTxnId').val();
	var indivisualStolenfileName=$('#singleStolenFileName').val();
	
	var stolenIndividualUserDB={
			"alternateContactNumber": singleStolenphone1,
			"commune": singleStolencommune,
			"complaintType": singleStolenComplaintType,
			"contactNumber": singleStolenphone2,
			"country": country,
			"deviceBrandName": singleStolendeviceBrandName,
			"deviceIdType": singleStolendeviceIDType,
			"deviceStolenCommune": singleDevicecommune,
			"deviceStolenDistrict": singleDevicedistrict,
			"deviceStolenLocality": singleDevicelocality,
			"deviceStolenPostalCode": singleDevicepin,
			"deviceStolenPropertyLocation": singleDeviceAddress,
			"deviceStolenStreet": singleDevicestreetNumber,
			"deviceStolenVillage": singleDevicevillage,
			"deviceStolenCountry": singleDevicecountry,
			"deviceStolenProvince": singleDevicestate,
			"deviceType":singleStolendeviceType,
			"district": singleStolendistrict,
			"email":singleStolenemail,
			"firstName":singleStolenfirstName,
			"imeiEsnMeid": singleStolenimeiNumber,
			"lastName": singleStolenlastName,
			"locality": singleStolenlocality,
			"multiSimStatus": singleStolenSimStatus,
			"middleName": singleStolenmiddleName,
			"modelNumber":singleStolenmodalNumber,
			"nid": singleStolennIDPassportNumber,
			"operator": singleStolenOperator,
			"phoneNo": singleStolenphone2,
			"postalCode": singleDevicepin,
			"propertyLocation": singleStolenaddress,
			"province": state,
			"remark": singleDeviceRemark,
			"street": singleStolenstreetNumber,
			"village":singleStolenvillage,
			
	}
	
	
	var request={
			"txnId":txnid,
			"fileName":indivisualStolenfileName,
			"dateOfStolen":IndivisualStolenDate,
			"blockingTimePeriod":blockingTimePeriod,
			"blockingType":blockingType,
			"requestType":0,
			"sourceType":5,
			"stolenIndividualUserDB":stolenIndividualUserDB
	}
	formData.append('file', $('#singleStolenFile')[0].files[0]);
	formData.append("request",JSON.stringify(request));

	$.ajax({
		url: './lawfulIndivisualStolenUpdate',
		type: 'POST',
		data: formData,
		processData: false,
		contentType: false,
		success: function (response, textStatus, jqXHR) {
		console.log(response);
			
			if(response.errorCode==0){
				$("#IndivisualUpdateStolen").prop('disabled', true);
				$('#stolenSucessPopUp').openModal();
				}
			else{
				$('#stolenSucessPopUp').openModal();
				$('#dynamicMessage').text('');
				$('#dynamicMessage').text(response.message);
			}
		},
		error: function (jqXHR, textStatus, errorThrown) {
			console.log("error in ajax")

		}
	});
	return false;
		
		
	}



