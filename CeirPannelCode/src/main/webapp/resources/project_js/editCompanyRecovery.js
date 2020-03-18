

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
		$('#bulkRecoveryquantity').val(response.qty);
		$('#bulkRecoveryRemark').val(response.remark);
		$('#bulkRecoveryFileName').val(response.fileName);
		$('#bulkRecoveryDate').val(response.dateOfRecovery);
		$('#bulkRecoveryaddress').val(response.stolenOrganizationUserDB.incidentPropertyLocation);
		$('#bulkRecoverystreetNumber').val(response.stolenOrganizationUserDB.incidentStreet);
		$('#bulkRecoveryvillage').val(response.stolenOrganizationUserDB.incidentVillage);
		$('#bulkRecoverylocality').val(response.stolenOrganizationUserDB.incidentLocality);
		$('#bulkRecoverydistrict').val(response.stolenOrganizationUserDB.incidentDistrict);
		$('#bulkRecoverycommune').val(response.stolenOrganizationUserDB.incidentCommune);
		$('#bulkRecoverypin').val(response.stolenOrganizationUserDB.incidentPostalCode);
		$('#bulkRecoverycountry').val(response.stolenOrganizationUserDB.incidentCountry).change();
		$('#bulkRecoverystate').val(response.stolenOrganizationUserDB.incidentProvince);
		
		//$('#bulkRecoveryFileLink').attr("onclick",'previewFile("'+response.fileLink+'","'+response.fileName+'","'+response.txnId+'")');
		
		},
		error: function (jqXHR, textStatus, errorThrown) {
			console.log("error in ajax")

		}
	});
}







function updateCompanyRecoveryRequest(){
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
	var fileName=$('#bulkRecoveryFileName').val();
	var txnid=$('#existingStolenTxnId').val();
	
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
		    
		  }
	
	var request={
			"txnId":txnid,
			"fileName":fileName,
			"dateOfRecovery":bulkRecoveryDate,
			"qty":bulkRecoveryquantity,
			"blockingTimePeriod":sigleRecoveryBlockPeriod,
			"blockingType":blockingType,
			"requestType":1,
			"sourceType":6,
			"remark":bulkRecoveryRemark,
			"stolenOrganizationUserDB":stolenOrganizationUserDB
	}

	formData.append('file', $('#bulkRecoveryFile')[0].files[0]);
	formData.append("request",JSON.stringify(request));

	$.ajax({
		url: './lawfulIndivisualStolenUpdate',
		type: 'POST',
		data: formData,
		processData: false,
		contentType: false,
		success: function (response, textStatus, jqXHR) {
		console.log(JSON.stringify(response));
		var resp= JSON.stringify(response);
		console.log(resp.errorCode);
		console.log(response.errorCode);

		if(response.errorCode==0){
			$("#IndivisualUpdateStolen").prop('disabled', true);
			$('#stolenSucessPopUp').openModal({dismissible:false});;
			}
		else{
			$('#stolenSucessPopUp').openModal({dismissible:false});;
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
		$('#fileFormateModal').openModal({dismissible:false});;
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
	$('#bulkRecoveryFile,#uploadFirSingle').val('');
	$("#bulkRecoveryFileName,#uploadFirSingleName").val('');
	$('#fileFormateModal').closeModal();
}
