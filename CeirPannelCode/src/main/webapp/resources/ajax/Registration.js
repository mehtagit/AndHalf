function openRegistrationPage(){
	var usertypeDropdown=$("#usertypes option:selected"); 
	var usertypeDropdownText=usertypeDropdown.text();
	var usertypeDropdownVal=usertypeDropdown.val(); 
	if(usertypeDropdownText=="Importer"){   
		window.location.href=contextpath+"/registration?usertypeId="+usertypeDropdownVal;
	}
	else if(usertypeDropdownText=="Distributor"){   
		window.location.href=contextpath+"/registration?usertypeId="+usertypeDropdownVal;
	}
	else if(usertypeDropdownText=="Retailer"){   
		window.location.href=contextpath+"/registration?usertypeId="+usertypeDropdownVal;
	}
	else if(usertypeDropdownText=="Custom"){
		window.location.href=contextpath+"/customRegistration?usertypeId="+usertypeDropdownVal;
	}     

	else if(usertypeDropdownText=="Operator"){
		window.location.href=contextpath+"/operatorRegistration?usertypeId="+usertypeDropdownVal;
	} 
	else{
	}
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
				$('#otpVerification').closeModal();   
				$('#otpMessage').openModal();   
				$("#otpResponse").text(resp.response);
				// $('#otpMessage').modal('open');
			}
			else{

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
			$("#resendOtp").text(response.response); 
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
				if(response[i].usertypeName=='Importer' || response[i].usertypeName=='Distributor' || response[i].usertypeName=='Retailer' ){
					var data2='<option value="'+response[i].id+'">'+response[i].usertypeName+'</option>';
					usertypeDropdown.append(data2);
				}  
				else{

				}
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
				if(response[i].usertypeName!='admin' ){
					var data2='<option value="'+response[i].id+'">'+response[i].usertypeName+'</option>';
					usertypeDropdown.append(data2);
				}  
				else{

				}
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
					locality:val.find('#locality').val(),
					province:val.find('#state').val(),
					country:val.find('#country').val(),
					vatStatus:val.find("input[name='vatStatus']:checked").val(),
					vatNo:val.find('#vatNo').val(),
					roles:val.find('#usertypes').val(),  
					password:val.find('#password').val(),  
					rePassword:val.find('#confirm_password').val(),
					captcha:val.find('#captcha').val(),
					usertypeId:val.find('#usertypeId').val(),
					questionList:questionData   
			}    
		} 
	});
	console.log("question data:  "+JSON.stringify(obj));
	var formData;

	formData = new FormData();
	formData.append( 'file', $( '#file' )[0].files[0] );
	formData.append('data',JSON.stringify(obj));
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
					locality:val.find('#locality').val(),
					country:val.find('#country').val(),
					state:val.find('#state').val(),
					passportNo:val.find('#passportNo').val(),
					employeeId:val.find("#employeeId").val(),
					natureOfEmployment:val.find("#natureOfEmployment").val(),
					designation:val.find("#designation").val(),
					authorityName:val.find("#authorityName").val(),
					authorityEmail:val.find("#authorityEmail").val(),
					authorityPhoneNo:val.find("#authorityPhoneNo").val(),
					email:val.find('#email').val(),
					province:val.find('#state').val(),
					phoneNo:val.find('#phoneNo').val(),
					password:val.find('#password').val(),  
					rePassword:val.find('#confirm_password').val(),
					roles:val.find('#usertypes').val(),  
					captcha:val.find('#captcha').val(),
					usertypeId:val.find('#usertypeId').val(),
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
	console.log("data=  "+formData);
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
					locality:val.find('#locality').val(),
					country:val.find('#country').val(),
					province:val.find('#state').val(),
					state:val.find('#state').val(),
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


