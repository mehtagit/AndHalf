/**
 * 
 */
function saveIndivisualRecoveryRequest()
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
	var sigleRecoveryBlockPeriod=$('#singleRecoveryDatePeriod').val();
	var blockingType =$('.blocktypeRadio:checked').val();
	var IndivisualRecoveryDevice=$('#IndivisualRecoveryDevice').val();

	 
	
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
			"dateOfRecovery":IndivisualRecoveryDevice,
			"blockingTimePeriod":sigleRecoveryBlockPeriod,
			"blockingType":blockingType,
			"requestType":1,
			"sourceType":4,
			"stolenIndividualUserDB":stolenIndividualUserDB
	}

	formData.append("request",JSON.stringify(request));

	$.ajax({
		url: './lawfulIndivisualRecovery',
		type: 'POST',
		data: formData,
		processData: false,
		contentType: false,
		success: function (response, textStatus, jqXHR) {
		
			if(response.errorCode=='0'){
				$("#indivisualStolenButton").prop('disabled', true);
				$('#IndivisualStolenSucessPopup').openModal();
				$('#IndivisualStolenTxnId').text(response.txnId)
			}
			else{
//				$('#sucessMessage').text('');
				$('#IndivisualStolenSucessPopup').openModal();
				$('#sucessMessage').text('');
				$('#sucessMessage').text(response.message);
			}
		},
		error: function (jqXHR, textStatus, errorThrown) {
			console.log("error in ajax")

		}
	});
	return false;


}

function saveCompanyRecoveryRequest(){
	var formData= new FormData();
	var bulkRecoveryquantity=$('#bulkRecoveryquantity').val();
	var bulkRecoveryRemark=$('#bulkRecoveryRemark').val();
	var bulkRecoveryaddress=$('#bulkRecoveryaddress').val();
	var bulkRecoverystreetNumber=$('#bulkRecoverystreetNumber').val();
	var bulkRecoveryvillage=$('#bulkRecoveryvillage').val();
    var bulkRecoverylocality=$('#bulkRecoverylocality').val();
	
	var bulkRecoverydistrict=$('#bulkRecoverydistrict').val();
	var bulkRecoverycommune=$('#bulkRecoverycommune').val();
	var bulkRecoverypin=$('#bulkRecoverypin').val();
	var bulkRecoverycountry=$('#bulkRecoverycountry').val();
	var bulkRecoverystate=$('#bulkRecoverystate').val();
	
	var bulkRecoveryDate =$('#bulkRecoveryDate').val();
   /* var deviceRecoveryDate=$('#deviceRecoveryDevice').val();*/

	var sigleRecoveryBlockPeriod=$('#stolenDatePeriod').val();
	var blockingType =$('.blocktypeRadio:checked').val();
	
	var stolenOrganizationUserDB= {
		   
		    "incidentCommune": bulkRecoverycommune,
		    "incidentCountry": bulkRecoverycountry,
		    "incidentDistrict": bulkRecoverydistrict,
		    "incidentLocality": bulkRecoverylocality,
		    "incidentPostalCode": bulkRecoverypin,
		    "incidentProvince": bulkRecoverystate,
		    "incidentStreet": bulkRecoverystreetNumber,
		    "incidentVillage": bulkRecoveryvillage,
		    "incidentPropertyLocation": bulkRecoveryaddress,
		    "remark":bulkRecoveryRemark
		  }
	
	var request={
			"dateOfRecovery":bulkRecoveryDate,
			"qty":bulkRecoveryquantity,
			"blockingTimePeriod":sigleRecoveryBlockPeriod,
			"blockingType":blockingType,
			"requestType":1,
			"sourceType":6,
			"stolenOrganizationUserDB":stolenOrganizationUserDB
	}

	formData.append('file', $('#bulkRecoveryFile')[0].files[0]);
	formData.append("request",JSON.stringify(request));

	$.ajax({
		url: './lawfulIndivisualRecovery',
		type: 'POST',
		data: formData,
		processData: false,
		contentType: false,
		success: function (response, textStatus, jqXHR) {
		console.log(JSON.stringify(response));
		var resp= JSON.stringify(response);
		console.log(resp.errorCode);
		console.log(response.errorCode);
		if(response.errorCode=='0'){
				$("#indivisualStolenButton").prop('disabled', true);
				$('#IndivisualStolenSucessPopup').openModal();
				$('#IndivisualStolenTxnId').text(response.txnId)
			}
			else{
//				$('#sucessMessage').text('');
				$('#IndivisualStolenSucessPopup').openModal();
				$('#sucessMessage').text('');
				$('#sucessMessage').text(response.message);
			}
		},
		error: function (jqXHR, textStatus, errorThrown) {
			console.log("error in ajax")

		}
	});
	return false;

}