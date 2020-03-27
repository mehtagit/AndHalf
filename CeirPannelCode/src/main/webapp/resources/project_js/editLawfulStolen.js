/**
 * 
 */


$(document).ready(function() {
	// executes when HTML-Document is loaded and DOM is ready
	$('div#initialloader').fadeIn('fast');

	viewIndivisualStolen();

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

			$('#singleStolenfirstName').val(response.stolenIndividualUserDB.firstName);
			$('#singleStolenmiddleName').val(response.stolenIndividualUserDB.middleName);
			$('#singleStolenlastName').val(response.stolenIndividualUserDB.lastName);
			$('#singleStolennIDPassportNumber').val(response.stolenIndividualUserDB.nid);
			$('#singleStolenemail').val(response.stolenIndividualUserDB.email);
		
			
			 $('#singleStolenphone1').val(response.stolenIndividualUserDB.alternateContactNumber);
	
		        //var e = jQuery.Event("keypress");e.which = 27; $("#singleStolenphone1").trigger(e);

			
			
			$('#singleStolenaddress').val(response.stolenIndividualUserDB.propertyLocation);
			//alert(response.stolenIndividualUserDB.street+"-----"+response.stolenIndividualUserDB.alternateContactNumber)
			$('#singleStolenstreetNumber').val(response.stolenIndividualUserDB.street);
			$('#singleStolenvillage').val(response.stolenIndividualUserDB.village);
			$('#singleStolenlocality').val(response.stolenIndividualUserDB.locality);
			$('#singleStolendistrict').val(response.stolenIndividualUserDB.district);
			$('#singleStolencommune').val(response.stolenIndividualUserDB.commune);
			$('#singleStolenpin').val(response.stolenIndividualUserDB.postalCode);
			$('#country').val(response.stolenIndividualUserDB.country).change();
			$('#state').val(response.stolenIndividualUserDB.province);
			$('#singleStolendeviceBrandName').val(response.stolenIndividualUserDB.deviceBrandName);
			//alert(response.stolenIndividualUserDB.nidFileName);
			$('#singleStolenFileName').val(response.fileName);
			$('#updatesingleStolenimei1').val(response.stolenIndividualUserDB.imeiEsnMeid1);
			$('#updatesingleStolenimei2').val(response.stolenIndividualUserDB.imeiEsnMeid2);
			$('#updatesingleStolenimei3').val(response.stolenIndividualUserDB.imeiEsnMeid3);
			$('#updatesingleStolenimei4').val(response.stolenIndividualUserDB.imeiEsnMeid4);

			$('#singleStolendeviceIDType').val(response.stolenIndividualUserDB.deviceIdType);
			$('#singleStolendeviceType').val(response.stolenIndividualUserDB.deviceType);
			$('#singleStolenmodalNumber').val(response.stolenIndividualUserDB.modelNumber);
			/*$('#singleStolenFileName').val(response.fileName);*/

			$('#singleStolenphone2').val(response.stolenIndividualUserDB.alternateContactNumber);
			$('#singleStolenOperator').val(response.stolenIndividualUserDB.operator);
			$('#singleStolenSimStatus').val(response.stolenIndividualUserDB.multiSimStatus);
			$('#singleStolenComplaintType').val(response.complaintType);
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
			$('#IndivisualStolenDate').val(response.dateOfStolen);
			$('#uploadFirSingleName').val(response.firFileName);
			//$('#singleStolenFileName').val(response.firFileName);

			$("label[for='IndivisualStolenDate']").addClass('active');
			$("label[for='updatesingleStolenimei1']").addClass('active');
			$("label[for='updatesingleStolenimei1']").addClass('active');
			$("label[for='updatesingleStolenimei2']").addClass('active');
			$("label[for='updatesingleStolenimei3']").addClass('active');
			$("label[for='updatesingleStolenimei4']").addClass('active');
			
			$('#PassportNidLink').attr("onclick",'previewFile("'+response.fileLink+'","'+response.fileName+'","'+response.txnId+'")');
			$('#firImageLink').attr("onclick",'previewFile("'+response.fileLink+'","'+response.firFileName+'","'+response.txnId+'")');
			$('div#initialloader').delay(300).fadeOut('slow');
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
	var updatesingleStolenimei1=$('#updatesingleStolenimei1').val();
	var updatesingleStolenimei2=$('#updatesingleStolenimei2').val();
	var updatesingleStolenimei3=$('#updatesingleStolenimei3').val();
	var updatesingleStolenimei4=$('#updatesingleStolenimei4').val();

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
	var uploadFirFile=$('#uploadFirSingleName').val();
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
			"imeiEsnMeid1": updatesingleStolenimei1,
			"imeiEsnMeid2": updatesingleStolenimei2,
			"imeiEsnMeid3": updatesingleStolenimei3,
			"imeiEsnMeid4": updatesingleStolenimei4,
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
			"firFileName":uploadFirFile,
			"complaintType":$('#singleStolenComplaintType').val(),
			"stolenIndividualUserDB":stolenIndividualUserDB
	}
	formData.append('file', $('#singleStolenFile')[0].files[0]);
	formData.append('firFileName', $('#uploadFirSingle')[0].files[0]);
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
				$('#stolenSucessPopUp').openModal({dismissible:false});
			}
			else{
				$('#stolenSucessPopUp').openModal({dismissible:false});
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



function isImageValid(id) {
	var uploadedFileName = $("#"+id).val();
	uploadedFileName = uploadedFileName.replace(/^.*[\\\/]/, '');
	//alert("file extension=="+uploadedFileName)
	var ext = uploadedFileName.split('.').pop();

	var fileSize = ($("#"+id)[0].files[0].size);
	/*fileSize = (Math.round((fileSize / 100000) * 100) / 100)
	alert("----"+fileSize);*/
	fileSize = Math.floor(fileSize/1000) + 'KB';
   
	//alert(uploadedFileName+"----------"+ext+"----"+fileSize)
	var areEqual =ext.toLowerCase()=='png';
	//alert(areEqual);
	if(areEqual==true)
		{
		ext='PNG';
		}
	
	if (uploadedFileName.length > 30) {
		$('#fileFormateModal').openModal();
		$('#fileErrormessage').text('');
		$('#fileErrormessage').text($.i18n('imageMessage'));
	} 
	else if(ext !='PNG')
	{
		
		$('#fileFormateModal').openModal({
			dismissible:false
		});
		$('#fileErrormessage').text('');
		$('#fileErrormessage').text($.i18n('imageMessage'));

	}
	else if(fileSize>=100){
		$('#fileFormateModal').openModal({
			dismissible:false
		});
		$('#fileErrormessage').text('');
		$('#fileErrormessage').text($.i18n('imageSize'));	
	}
}


function clearFileName() {
	$('#singleStolenFile,#uploadFirSingle').val('');
	$("#singleStolenFileName,#uploadFirSingleName").val('');
	$('#fileFormateModal').closeModal();
}

