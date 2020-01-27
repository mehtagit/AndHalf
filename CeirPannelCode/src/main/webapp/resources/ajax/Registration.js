$('#langlist').on('change', function() {
	window.lang=$('#langlist').val() == 'km' ? 'km' : 'en';
	var url_string = window.location.href;
	var url = new URL(url_string);
	var type = url.searchParams.get("type");
	window.location.assign("registration?type="+type+"&lang="+window.lang);			
	}); 
	
	var langParam=$('#langlist').val() == 'km' ? 'km' : 'en';
	$.i18n().locale = langParam;
	var successMsg;
	$.i18n().load( {
		'en': './resources/i18n/en.json',
		'km': './resources/i18n/km.json'
	} ).done( function() { 
		successMsg=$.i18n('successMsg');
	});

	
	
        $(document).ready(function () {
        	var url = new URL( window.location.href);
    		var langParameter = url.searchParams.get("lang");
            	$('#langlist').val(langParameter == 'km' ? 'km' : 'en');
        	$('.modal-trigger').leanModal({
        		dismissible: false
        	});
        	
        	asTypeData();       	
            questionDataByCategory();
            usertypeData2("${usertypeId}");
        }); 
        populateCountries("country",    "state");
        
       $("#country").val("Cambodia");
       
       populateStates( "country","state" );
       
       
       function validatePassword(){
           if(password.value != confirm_password.value) {
             confirm_password.setCustomValidity("Passwords Don't Match");
           } else {
             confirm_password.setCustomValidity('');
           }
         }

     password.onchange = validatePassword;
     confirm_password.onkeyup = validatePassword;

      
        function myFunction() {
            var x = document.getElementById("type").value;
            if (x == '0') {
                document.getElementById("uploadFile").style.display = "block";
                document.getElementById("passportNumberDiv").style.display = "block";
                document.getElementById("companyNames").style.display = "none";
                $("#passportNo").prop('required',true);
                $("#companyName").prop('required',false);
                $("#companyName").val("");
                $("#file").prop('required',true);
            } else {
                document.getElementById("uploadFile").style.display = "none";
                document.getElementById("passportNumberDiv").style.display = "none";
                document.getElementById("companyNames").style.display = "block";
                $("#companyName").prop('required',true);
                $("#passportNo").prop('required',false);
                $("#passportNo").val("");
                $("#file").prop('required',false);
            }
        }
       
        
        function vatChecked(){
        	var radioValue = $("input[name='vatStatus']:checked").val();
        	if(radioValue==1){
        		$("#vatNo").prop('required',true);
        		$("#vatFile").prop('required',true);
        	}
        	else{
        		$("#vatNo").prop('required',false);
        		$("#vatFile").prop('required',false);
        		$("#vatNo").val("");
        		$("#vatFile").val("");
        	}
		}
		


function openRegistrationPage(usertype){
	window.location.href=contextpath+"/registration?type="+usertype;
}
function portDropDownData(){ 
	$.ajax({
		type : 'GET',
		url : contextpath + '/getDropdownList/CUSTOMS_PORT',
		contentType : "application/json",
		dataType : 'html',
		async:false,
		success : function(data) {
			var response=JSON.parse(data);                                    
			var asTypeDropdown=$("#arrivalPort");  
			for(var i=0; i<response.length; i++){
					var data2='<option value="'+response[i].value+'">'+response[i].interp+'</option>';
					asTypeDropdown.append(data2);
			}    
		},      
		error: function (xhr, ajaxOptions, thrownError) {
		}
	});
}
function verifyOtp(){
	$("#otpVerifyBtn").prop('disabled', true);
	var obj="";
	$("#verifyOtpForm").each(function(key, val){
		val = $(this);
		if(val.html() !== "") {
			obj =  
			{ 
					phoneOtp:val.find('#phoneOtp').val(),
					emailOtp:val.find('#emailOtp').val(),
					userid: val.find('#userid').val()
			} 
		}
	});
	$.ajax({     
		type : 'POST',
		url : contextpath + '/verifyOtp',
		contentType : "application/json",
		dataType : 'html',  
		data : JSON.stringify(obj),
		success : function(data) {
			console.log(data);	
			var resp=JSON.parse(data);
			if(resp.statusCode=="200"){
				//window.location.href='#otpMessage';
				$("#otpVerification").closeModal();
				$('#otpMessage').openModal();   
				$("#otpResponse").text(resp.response);
				// $('#otpMessage').modal('open');
			}
			else{
				$("#otpVerification #verifyOtpResp").text(resp.response);
				
			}
			$("#otpVerifyBtn").prop('disabled', false);
		},
		error: function (xhr, ajaxOptions, thrownError) {
			$("#otpVerifyBtn").prop('disabled', false);
		}

	});
	return false;
} 
function resendOtp(){
	var id=document.getElementById("userid").value;
	$.ajax({
		type : 'POST',
		url : contextpath + '/resendOtp/'+id,
		contentType : "application/json",
		dataType : 'html',
		success : function(data) {
			var response=JSON.parse(data);
			$("#verifyOtpResp").text(response.response); 
		},    
		error: function (xhr, ajaxOptions, thrownError) {
		}
	});
}

function refreshCaptcha(imageId){
	path = contextpath+'/captcha?cache='; //for example
	imageObject = document.getElementById(imageId);
	imageObject.src = path + (new Date()).getTime();
} 

function reg(){
	return false;
}


function asTypeData(){ 
	$.ajax({
		type : 'GET',
		url : contextpath + '/asTypeData/AS_TYPE',
		contentType : "application/json",
		dataType : 'html',
		async:false,
		success : function(data) {
			var response=JSON.parse(data);                                    
			var asTypeDropdown=$("#type");  
			for(var i=0; i<response.length; i++){
					var data2='<option value="'+response[i].value+'">'+response[i].interp+'</option>';
					asTypeDropdown.append(data2);
			}    
		},      
		error: function (xhr, ajaxOptions, thrownError) {
		}
	});
}
function usertypeData(){ 
	$.ajax({
		type : 'GET',
		url : contextpath + '/usertypeList/',
		contentType : "application/json",
		dataType : 'html',
		async:false,
		success : function(data) {
			var response=JSON.parse(data);                                    
			var usertypeDropdown=$("#usertypes");  
			for(var i=0; i<response.length; i++){
					var data2='<option value="'+response[i].id+'">'+response[i].usertypeName+'</option>';
					usertypeDropdown.append(data2);
							}    
			setTimeout(function(){ 
				$('.dropdown-trigger').dropdown();
				$('select').formSelect();
			}, 1000);      
		},      
		error: function (xhr, ajaxOptions, thrownError) {
		}
	});
}


function usertypeData2(id){ 
	$.ajax({
		type : 'GET',
		url : contextpath + '/getTypeDropdownList/ROLE_TYPE/'+id,
		contentType : "application/json",
		dataType : 'html',
		async:false,
		success : function(data) {
			var response=JSON.parse(data);                                    
			var usertypeDropdown=$("#usertypes");  
			for(var i=0; i<response.length; i++){
					var data2='<option value="'+response[i].value+'">'+response[i].interp+'</option>';
					usertypeDropdown.append(data2);
				
			}    
			setTimeout(function(){ 
				$('.dropdown-trigger').dropdown();
				$('select').formSelect();
			}, 1000);      
		},      
		error: function (xhr, ajaxOptions, thrownError) {
		}
	});
}

function usertypeDropDownData(){ 
	$.ajax({
		type : 'GET',
		url : contextpath + '/usertypeList/',
		contentType : "application/json",
		dataType : 'html', 
		success : function(data) {
			var response=JSON.parse(data);                                    
			var usertypeDropdown=$("#usertypes");  
			for(var i=0; i<response.length; i++){
					var data2='<option value="'+response[i].id+'">'+response[i].usertypeName+'</option>';
					usertypeDropdown.append(data2);
			}    
		},    
		error: function (xhr, ajaxOptions, thrownError) {
		}
	});
}

function operatorList(){ 
	$.ajax({
		type : 'GET',
		url : contextpath + '/operatorList/OPERATORS',
		contentType : "application/json",
		dataType : 'html', 
		success : function(data) {  
			var response=JSON.parse(data);                                    
			var operatorType=$("#operatorType");  
			for(var i=0; i<response.length; i++){
				var data2='<option value="'+response[i].value+'">'+response[i].interp+'</option>';
				operatorType.append(data2); 
			}     
		},    
		error: function (xhr, ajaxOptions, thrownError) {
		}
	});
}




function questionData(){ 
	$.ajax({
		type : 'GET',
		url : contextpath + '/securityQuestionList/',
		contentType : "application/json",
		dataType : 'html', 
		success : function(data) {
			var response=JSON.parse(data);
			var usertypeDropdown=$("#questionId");
			usertypeDropdown.empty();
			var data1='<option value="" disabled selected>Security Question</option>';
			usertypeDropdown.append(data1); 
			for(var i=0; i<response.length; i++){
				var data2='<option value="'+response[i].id+'">'+response[i].question+'</option>';
				usertypeDropdown.append(data2);
			}   

		},    
		error: function (xhr, ajaxOptions, thrownError) {
		}
	});
}






function saveRegistration(){ 
	$("#btnSave").prop('disabled', true);
	var obj="";
	var oj2=""; 
	var questionData=[];
	$("#registrationForm .securityQuestionDiv").each(function(key, val){
		val = $(this);
		if(val.html() !== "") {
			obj2=  
			{  
					questionId:parseInt(val.find('.questionId').val()),
					answer:val.find('.answer').val(),    
			}  
			questionData.push(obj2);
		}
	});


	$("#registrationForm").each(function(key, val){
		val = $(this);
		if(val.html() !== "") {
			obj =   
			{       
					firstName:val.find('#firstName').val(),
					middleName:val.find('#middleName').val(),  
					lastName: val.find('#lastName').val(),
					type:val.find('#type').val(), 
					passportNo:val.find('#passportNo').val(),
					companyName:val.find('#companyName').val(),
					email:val.find('#email').val(),
					phoneNo:val.find('#phoneNo').val(),
					propertyLocation:val.find('#propertyLocation').val(),
					street:val.find('#street').val(),
					village:val.find("#village").val(),
					locality:val.find('#locality').val(),
					district:val.find('#district').val(),
					commune:val.find('#commune').val(),
					postalCode:val.find('#postalCode').val(),
					province:val.find('#state').val(),
					country:val.find('#country').val(),
					vatStatus:val.find("input[name='vatStatus']:checked").val(),
					vatNo:val.find('#vatNo').val(),
					roles:val.find('#usertypes').val(),  
					password:val.find('#password').val(),  
					rePassword:val.find('#confirm_password').val(),
					captcha:val.find('#captcha').val(),
					usertypeId:val.find('#usertypeId').val(),
					usertypeName:val.find('#usertypeName').val(),
					questionList:questionData   
			}    
		} 
	});
	console.log("question data:  "+JSON.stringify(obj));
	var formData;

	formData = new FormData();
	formData.append( 'file', $( '#file' )[0].files[0] );
	formData.append('data',JSON.stringify(obj));
	formData.append('vatFile',$('#vatFile')[0].files[0]);
	
	console.log("data=  "+formData);
	registrationAjax(formData);
	//$("#btnSave").prop('disabled', true);
  
	return false;
}

function saveCustomRegistration(){ 
	$("#btnSave").prop('disabled', true);
	var obj="";
	var oj2=""; 
	var questionData=[];
	$("#registrationForm .securityQuestionDiv").each(function(key, val){
		val = $(this);
		if(val.html() !== "") {
			obj2=  
			{  
					questionId:parseInt(val.find('.questionId').val()),
					answer:val.find('.answer').val(),    
			}  
			questionData.push(obj2);
		}
	});


	$("#registrationForm").each(function(key, val){
		val = $(this);
		if(val.html() !== "") {
			obj =   
			{       
					firstName:val.find('#firstName').val(),
					middleName:val.find('#middleName').val(),  
					lastName: val.find('#lastName').val(),
					propertyLocation:val.find('#propertyLocation').val(),
					street:val.find('#street').val(),
					village:val.find("#village").val(),
					locality:val.find('#locality').val(),
					district:val.find('#district').val(),
					commune:val.find('#commune').val(),
					postalCode:val.find('#postalCode').val(),
					country:val.find('#country').val(),
					province:val.find('#state').val(),
					passportNo:val.find('#passportNo').val(),
					employeeId:val.find("#employeeId").val(),
					natureOfEmployment:val.find("#natureOfEmployment").val(),
					designation:val.find("#designation").val(),
					authorityName:val.find("#authorityName").val(),
					authorityEmail:val.find("#authorityEmail").val(),
					authorityPhoneNo:val.find("#authorityPhoneNo").val(),
					email:val.find('#email').val(),
					phoneNo:val.find('#phoneNo').val(),
					password:val.find('#password').val(),  
					rePassword:val.find('#confirm_password').val(),
					roles:val.find('#usertypes').val(),  
					captcha:val.find('#captcha').val(),
					usertypeId:val.find('#usertypeId').val(),
					usertypeName:val.find('#usertypeName').val(),
					arrivalPort:val.find('#arrivalPort').val(),
					questionList:questionData,
					type:val.find('#type').val()

			}    
		} 
	});
	console.log("question data:  "+JSON.stringify(obj));
	var formData;
	formData = new FormData();
	formData.append( 'NationalIdImage', $( '#NationalIdImage' )[0].files[0] );
	formData.append( 'photo', $( '#photo' )[0].files[0] );
	formData.append( 'idCard', $( '#idCard' )[0].files[0] );
	formData.append('data',JSON.stringify(obj));  
	console.log("data=  "+JSON.stringify(formData));
	registrationAjax(formData);
	return false;
}   


function saveOperatorRegistration(){ 
	$("#btnSave").prop('disabled', true);
	var obj="";
	var oj2=""; 
	var questionData=[];
	$("#registrationForm .securityQuestionDiv").each(function(key, val){
		val = $(this);
		if(val.html() !== "") {
			obj2=  
			{  
					questionId:parseInt(val.find('.questionId').val()),
					answer:val.find('.answer').val(),    
			}  
			questionData.push(obj2);
		}
	});


	$("#registrationForm").each(function(key, val){
		val = $(this);
		if(val.html() !== "") {
			obj =   
			{       
					firstName:val.find('#firstName').val(),
					middleName:val.find('#middleName').val(),  
					lastName: val.find('#lastName').val(),
					propertyLocation:val.find('#propertyLocation').val(),
					street:val.find('#street').val(),
					village:val.find("#village").val(),
					locality:val.find('#locality').val(),
					district:val.find('#district').val(),
					commune:val.find('#commune').val(),
					postalCode:val.find('#postalCode').val(),
					country:val.find('#country').val(),
					province:val.find('#state').val(),
					passportNo:val.find('#passportNo').val(),
					employeeId:val.find("#employeeId").val(),
					natureOfEmployment:val.find("#natureOfEmployment").val(),
					designation:val.find("#designation").val(),
					authorityName:val.find("#authorityName").val(),
					authorityEmail:val.find("#authorityEmail").val(),
					authorityPhoneNo:val.find("#authorityPhoneNo").val(),
					email:val.find('#email').val(),
					phoneNo:val.find('#phoneNo').val(),
					type:val.find('#type').val(), 
					password:val.find('#password').val(),  
					rePassword:val.find('#confirm_password').val(),
					roles:val.find('#usertypes').val(),  
					captcha:val.find('#captcha').val(),
					usertypeId:val.find('#usertypeId').val(),
					usertypeName:val.find('#usertypeName').val(),
					operatorTypeId:val.find('#operatorType').val(),  
					operatorTypeName:val.find('#operatorType option:selected').text(),
					type:val.find('#type').val(),
					questionList:questionData 
			}    
		} 
	});
	console.log("question data:  "+JSON.stringify(obj));
	var formData;

	formData = new FormData();
	formData.append( 'NationalIdImage', $( '#NationalIdImage' )[0].files[0] );
	formData.append( 'photo', $( '#photo' )[0].files[0] );
	formData.append( 'idCard', $( '#idCard' )[0].files[0] );
	formData.append('data',JSON.stringify(obj));  
	console.log("data=  "+formData);
	registrationAjax(formData);
	return false;
}
function registrationAjax(obj){
	$.ajax({   
		type : 'POST',
		url : contextpath + '/saveRegistration',
		data :obj,   
		processData : false,
		contentType : false,
		success : function(response) {
			var respData=JSON.parse(JSON.stringify(response));
			console.log("response from server:  "+JSON.stringify(respData));
			if(respData.statusCode==200){
				//window.location.href='./verifyOtpPage/?userid='+respData.userId;
				$("#userid").val(response.userId);
				//window.location.href='#otpMsgModal';
				$("#otpMsgModal").openModal();
				$("#otpMsg").text(response.response);
			}
			else{
				$("#registrationForm #msg").text(respData.response);
			}
			$("#btnSave").prop('disabled', false);
		}, 
		error: function (xhr, ajaxOptions, thrownError) {
			$("#btnSave").prop('disabled', false);
		}
	});
}



function openEndUserGrievancePage(reportType){
	window.location.href="./raiseAgrievance?reportType="+reportType.value;

}

function  openEndUserStockPage(reportType)
{
	console.log("reportType=="+reportType.value);
	window.location.href="./uploadAstock?reportType="+reportType.value;
	console.log("details."+window.location.href);
/*	$.ajax({   
		type : 'POST',
		url : contextpath + '/openEndUserStockPage?reportType='+reportType.value,
		processData : false,
		contentType : false,
		success : function(response) {
			console.log("sucess function...%%%%")
			var respData=JSON.parse(JSON.stringify(response));
			console.log("response from server:  "+JSON.stringify(respData));
			if(respData.statusCode==200){
				//window.location.href='./verifyOtpPage/?userid='+respData.userId;
				$("#userid").val(response.userId);
				//window.location.href='#otpMsgModal';
				$("#otpMsgModal").openModal();
				$("#otpMsg").text(response.response);
			}
			else{
				$("#registrationForm #msg").text(respData.response);
			}
			$("#btnSave").prop('disabled', false);
		}, 
		error: function (xhr, ajaxOptions, thrownError) {
			$("#btnSave").prop('disabled', false);
		}
	});*/
}