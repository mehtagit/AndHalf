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
		
		$('#sigleRecoverydeviceBrandName').val(response.stolenIndividualUserDB.deviceBrandName);
		$('#sigleRecoveryimeiNumber').val(response.stolenIndividualUserDB.imeiEsnMeid);
		$('#sigleRecoverydeviceIDType').val(response.stolenIndividualUserDB.deviceIdType);
		$('#sigleRecoverydeviceType').val(response.stolenIndividualUserDB.deviceType);
		$('#sigleRecoverydeviceSimStatus').val(response.stolenIndividualUserDB.multiSimStatus);
		$('#sigleRecoveryserialNumber').val(response.stolenIndividualUserDB.deviceSerialNumber);
		$('#sigleRecoveryaddress').val(response.stolenIndividualUserDB.deviceStolenPropertyLocation);
		$('#sigleRecoverystreetNumber').val(response.stolenIndividualUserDB.deviceStolenStreet);
		$('#sigleRecoveryvillage').val(response.stolenIndividualUserDB.deviceStolenVillage);
		$('#sigleRecoverylocality').val(response.stolenIndividualUserDB.deviceStolenLocality);
		$('#sigleRecoverydistrict').val(response.stolenIndividualUserDB.deviceStolenDistrict);
		$('#sigleRecoverycommune').val(response.stolenIndividualUserDB.deviceStolenCommune);
		$('#sigleRecoverypin').val(response.stolenIndividualUserDB.deviceStolenPostalCode);
		$('#country1').val(response.stolenIndividualUserDB.deviceStolenCountry).change();
		$('#state1').val(response.stolenIndividualUserDB.deviceStolenProvince);
		//$('#sigleRecoverydeviceStatus').val(response.stolenIndividualUserDB.deviceBrandName);
		$('#sigleRecovery').val(response.stolenIndividualUserDB.remark);
		$('#bulkRecoveryDate').val(response.dateOfRecovery);
		
		
		},
		error: function (jqXHR, textStatus, errorThrown) {
			console.log("error in ajax")

		}
	});
}






function updateIndivisualRecovery()
{
  var formData= new FormData();
	 
	var sigleRecoverydeviceBrandName=$('#sigleRecoverydeviceBrandName').val();
	var sigleRecoveryimeiNumber=$('#sigleRecoveryimeiNumber').val();
	var sigleRecoverydeviceIDType=$('#sigleRecoverydeviceIDType').val();
	var sigleRecoverydeviceType=$('#sigleRecoverydeviceType').val();
	var sigleRecoverydeviceSimStatus=$('#sigleRecoverydeviceSimStatus').val();
    var sigleRecoveryserialNumber=$('#sigleRecoveryserialNumber').val();
	var sigleRecoveryaddress=$('#sigleRecoveryaddress').val();
	var sigleRecoverystreetNumber=$('#sigleRecoverystreetNumber').val();
	var sigleRecoveryvillage=$('#sigleRecoveryvillage').val();
	var sigleRecoverylocality=$('#sigleRecoverylocality').val();
	var sigleRecoverydistrict=$('#sigleRecoverydistrict').val();
	var sigleRecoverycommune=$('#sigleRecoverycommune').val();
	var sigleRecoverypin=$('#sigleRecoverypin').val();
   /* var deviceRecoveryDate=$('#deviceRecoveryDevice').val();*/
	var sigleRecovery =$('#sigleRecovery').val();
	var country1=$('#country1').val();
	var state1=$('#state1').val();
	var sigleRecoverydeviceStatus=$('#sigleRecoverydeviceStatus').val();
	var sigleRecoveryBlockPeriod=$('#stolenDatePeriod').val();
	var blockingType =$('.blocktypeRadio:checked').val();
	var IndivisualRecoveryDevice=$('#bulkRecoveryDate').val();
	var txnid=$('#existingStolenTxnId').val();
	 
	
	var stolenIndividualUserDB={
			"deviceBrandName": sigleRecoverydeviceBrandName,
			"deviceIdType": sigleRecoverydeviceIDType,
			"deviceStolenCommune": sigleRecoverycommune,
			"deviceStolenDistrict": sigleRecoverydistrict,
			"deviceStolenLocality": sigleRecoverylocality,
			"deviceStolenPostalCode": sigleRecoverypin,
			"deviceStolenPropertyLocation": sigleRecoveryaddress,
			"deviceStolenStreet": sigleRecoverystreetNumber,
			"deviceStolenVillage": sigleRecoveryvillage,
			"deviceType":sigleRecoverydeviceType,
			"imeiEsnMeid": sigleRecoveryimeiNumber,
			"deviceStolenProvince": state1,
			"remark": sigleRecovery,
			"multiSimStatus":sigleRecoverydeviceSimStatus,
			"deviceStolenCountry":country1,
			"deviceSerialNumber":sigleRecoveryserialNumber
			
	}
	

	var request={
			"txnId":txnid,
			"dateOfRecovery":IndivisualRecoveryDevice,
			"blockingTimePeriod":sigleRecoveryBlockPeriod,
			"blockingType":blockingType,
			"requestType":1,
			"sourceType":4,
			"stolenIndividualUserDB":stolenIndividualUserDB
	}

	formData.append("request",JSON.stringify(request));

	$.ajax({
		url: './lawfulIndivisualStolenUpdate',
		type: 'POST',
		data: formData,
		processData: false,
		contentType: false,
		success: function (response, textStatus, jqXHR) {
		
			if(response.errorCode=='0'){
				$("#indivisualStolenButton").prop('disabled', true);
				$('#stolenSucessPopUp').openModal();
			
			}
			else{
//				$('#sucessMessage').text('');
				$('#indivisualStolenButton').openModal();
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



