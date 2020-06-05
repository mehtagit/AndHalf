$('#langlist').on('change', function() {
	lang=$('#langlist').val() == 'km' ? 'km' : 'en';
	var url_string = window.location.href;
	var url = new URL(url_string);
	var type = url.searchParams.get("type");
	window.location.assign("checkDeviceslogin?lang="+lang);			
});   
$(document).ready(function () {
	$('#langlist').val(data_lang_param);
	var lang=$('#langlist').val() == 'km' ? 'km' : 'en';
	$.i18n().locale = lang;		
	$.i18n().load( {
		'en': './resources/i18n/en.json',
		'km': './resources/i18n/km.json'
	} ).done( function() { 

	});

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


function setInvalidData(response){
	/*$.i18n('validationIMEI')*/
	$('#checkDevicesMsg').text($.i18n(response.tag));	
	//$("#InvalidImeiNumber").text(response.data.imei)
	$("#invalidTac").val(response.tacNumber);
	$("#invalidRemark").val(response.brandName);


}

$.getJSON('./getDropdownList/DEVICE_ID_TYPE', function(data) {
	for (i = 0; i < data.length; i++) {
		$('<option>').val(data[i].value).text(data[i].interp)
		.appendTo('#deviceIdType');
	}
});


$('#deviceIdType').on('change', function() {
	var value=parseInt($(this).val());

	switch (value) {
	case 0:
		$("#DeviceID").attr("pattern","[0-9]{15,16}");
		$("#DeviceID").attr("maxlength","16");
		$("#DeviceID").removeAttr("onkeyup");
		$("#DeviceID").attr("oninput","InvalidMsg(this,'input','"+$.i18n('validationIMEI')+"')");
		$("#DeviceID").attr("oninvalid","InvalidMsg(this,'input','"+$.i18n('validationIMEI')+"')");
		$('#errorMsgOnModal').text($.i18n('IMEIMsg'));
		break;
	case 1:
		$("#DeviceID").attr("pattern","[A-F0-9]{15,16}");
		$("#DeviceID").attr("maxlength","16");
		$("#DeviceID").removeAttr("onkeyup");
		$("#DeviceID").attr("oninput","InvalidMsg(this,'input','"+$.i18n('validationMEID')+"')");
		$("#DeviceID").attr("oninvalid","InvalidMsg(this,'input','"+$.i18n('validationMEID')+"')");
		$('#errorMsgOnModal').text($.i18n('MEIDMsg'));
		break;
	case 2:
		$('#DeviceID').val('');
		$("#DeviceID").attr("pattern","[0-9]{8,11}");
		$("#DeviceID").attr("onkeyup","isLengthValid(this.value)");
		$("#DeviceID").attr("maxlength","11");	
		$("#DeviceID").attr("oninput","InvalidMsg(this,'input','"+$.i18n('validationESN11')+"')");
		$("#DeviceID").attr("oninvalid","InvalidMsg(this,'input','"+$.i18n('validationESN11')+"')");
		$('#errorMsgOnModal').text($.i18n('ESNMsg'));
		break;
	}

}); 

function isLengthValid(val){
	var deviceIDLength=val.length;
	if(!isNaN(val)){
		$("#DeviceID").attr("pattern","[0-9]{11,11}");
		$("#DeviceID").attr("maxlength","11");
		$("#DeviceID").attr("oninput","InvalidMsg(this,'input','"+$.i18n('validationESN11')+"')");
		$("#DeviceID").attr("oninvalid","InvalidMsg(this,'input','"+$.i18n('validationESN11')+"')");
	}
	else if(typeof val == 'string' || val instanceof String){
		$("#DeviceID").attr("maxlength","8");
		$("#DeviceID").attr("pattern","[A-F0-9]{8,8}");
		$("#DeviceID").attr("oninput","InvalidMsg(this,'input','"+$.i18n('validationESN8')+"')");
		$("#DeviceID").attr("oninvalid","InvalidMsg(this,'input','"+$.i18n('validationESN8')+"')");

	}
}