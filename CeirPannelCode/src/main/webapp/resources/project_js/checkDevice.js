$('#langlist').on('change', function() {
	lang=$('#langlist').val() == 'km' ? 'km' : 'en';
	var url_string = window.location.href;
	var url = new URL(url_string);
	var type = url.searchParams.get("type");
	window.location.assign("updateVisaValidaity?lang="+lang);			
	});   
$(document).ready(function () {
$('#langlist').val(data_lang_param);
});

function DeviceDetails(){
	
	var RequestData={
			"deviceIdType":parseInt($("#deviceIdType").val()),
			"deviceId":$("#DeviceID").val()
	}

	$.ajax({
		
		url : "./checkDevice",
		data :	JSON.stringify(RequestData),
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		type : 'POST',
		success : function(response) {
			console.log(response);
			
			if (response.errorCode == 200) {
				$("#singleInput").css("display", "none");
				$("#validDetails").css("display", "block");
				setvalidData(response)
			}else{
				
				$("#singleInput").css("display", "none");
				$("#invalidDetails").css("display", "block");
				setInvalidData(response)
			}
			
		},
		error : function() {
			$('#errorModal').openModal();
		}
	});
	return false;
}


function setvalidData(response){
	
	$("#validTac").val(response.data.tacNumber);
	$("#validbrandName").val(response.data.brandName);
	$("#validModelName").val(response.data.modelName);
	
}


function setInvalidData(data){
	
	$("#InvalidImeiNumber").text(response.data.imei)
	$("#invalidTac").val(response.data.tacNumber);
	$("#invalidRemark").val(response.data.brandName);
	
	
}

$.getJSON('./getDropdownList/DEVICE_ID_TYPE', function(data) {
	for (i = 0; i < data.length; i++) {
		$('<option>').val(data[i].value).text(data[i].interp)
		.appendTo('#deviceIdType');
	}
});

