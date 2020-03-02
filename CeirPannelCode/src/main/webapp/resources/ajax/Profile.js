//var lang=$('#langlist').val() == 'km' ? 'km' : 'en';
var tag;
function changePassword(){
	$("#changePassBtn").prop('disabled', true);
	var obj="";
	$("#changePassword").each(function(key, val){
		val = $(this);
		if(val.html() !== "") {
			obj =  
			{  
					oldPassword:val.find('#oldPassword').val(),
					password:val.find('#password').val(),
					confirmPassword: val.find('#confirm_password').val()
			}    
		}
	});


	$.ajax({
		type : 'POST',
		url : contextpath + '/changePassword',
		data : JSON.stringify(obj),
		contentType : "application/json",
		dataType : 'html', 
		success : function(data) {
			var resp=JSON.parse(data);
			if(resp.statusCode=='200'){
				$.i18n().locale = $('#langlist').val();
				$.i18n().load( {
					'en': './resources/i18n/en.json',
					'km': './resources/i18n/km.json'
				}).done( function() {
					$("#changePasswordMessage #cPassSucessMsg").text($.i18n(resp.tag));
					$("#changePasswordMessage").openModal({
				        dismissible:false
				    });
					$("#changePassword").closeModal();
					
				});
				
			}
			else{
				
				
				$.i18n().locale = $('#langlist').val();
				$.i18n().load( {
					'en': './resources/i18n/en.json',
					'km': './resources/i18n/km.json'
				}).done( function() {
					$("#changePassword #errorMsg").text($.i18n(resp.tag));
				});
				
			}
			$("#changePassBtn").prop('disabled', false);
		},  
		error: function (xhr, ajaxOptions, thrownError) {
			$("#changePassBtn").prop('disabled', false);
		} 

	});
	return false;
}

function updateUSerStatus(){

	$("#updateStatusBtn").prop('disabled', true);
	var obj="";  
	obj={
			status:$("input[name='status']:checked").val()

	};
	console.log("user status: "+JSON.stringify(obj));
	$.ajax({ 
		type : 'POST',
		url : contextpath+'/updateUserStatus',
		contentType : "application/json",
		dataType : 'html', 
		data : JSON.stringify(obj),
		success : function(data) { 

			var resp=JSON.parse(data);
			if(resp.statusCode=='200'){
				$.i18n().locale = $('#langlist').val();
				$.i18n().load( {
					'en': './resources/i18n/en.json',
					'km': './resources/i18n/km.json'
				}).done( function() {
					$("#manageAccountSubmit #mgAccount").text($.i18n(resp.tag));
					$("#manageAccountSubmit").openModal({
				        dismissible:false
				    });
 
				});
			}
			else{  
				$.i18n().locale = $('#langlist').val();
				$.i18n().load( {
					'en': './resources/i18n/en.json',
					'km': './resources/i18n/km.json'
				}).done( function() {
					$("#userStatusForm #errorMsg").text($.i18n(resp.tag));
				});
			}
			$("#updateStatusBtn").prop('disabled', true);
		}, 
		error: function (xhr, ajaxOptions, thrownError) {
			$("#updateStatusBtn").prop('disabled', true);
		} 

	});
	return false;
}

function questionDataByCategory2(){ 
	$.ajax({
		type : 'GET',
		url : contextpath + '/securityQuestionList/',
		contentType : "application/json",
		dataType : 'html', 
		success : function(data) {
			var response=JSON.parse(data);
			var usertypeDropdown1=$("#registrationForm #questionId0");
			var usertypeDropdown2=$("#registrationForm #questionId1");
			var usertypeDropdown3=$("#registrationForm #questionId2");
			var data1='<option value="" disabled selected>Security Question 1</option>';
			var data2='<option value="" disabled selected>Security Question 2</option>';
			var data3='<option value="" disabled selected>Security Question 3</option>';
			var checkVal=usertypeDropdown1.val();
			var checkVal2=usertypeDropdown2.val();
			var checkVal3=usertypeDropdown3.val();
			for(var i=0; i<response.length; i++){
				if(response[i].category==1){
					if(checkVal==response[i].id){

					}
					else{
						var text='<option value="'+response[i].id+'">'+response[i].question+'</option>';
						usertypeDropdown1.append(text);	
					}
				}
				else if(response[i].category==2){
					if(checkVal2==response[i].id){

					}
					else{
						var text='<option value="'+response[i].id+'">'+response[i].question+'</option>';
						usertypeDropdown2.append(text);	  
					}
				}
				else if(response[i].category==3){
					if(checkVal3==response[i].id){

					}
					else{
						var text='<option value="'+response[i].id+'">'+response[i].question+'</option>';
						usertypeDropdown3.append(text);	
					}
				}
				else{}
			}

		},    
		error: function (xhr, ajaxOptions, thrownError) {
		}
	});
}

function questionDataByCategory(){ 
	$.ajax({
		type : 'GET',
		url : contextpath + '/securityQuestionList/',
		contentType : "application/json",
		dataType : 'html', 
		success : function(data) {
			var response=JSON.parse(data);
			var usertypeDropdown1=$("#registrationForm #questionId0");
			var usertypeDropdown2=$("#registrationForm #questionId1");
			var usertypeDropdown3=$("#registrationForm #questionId2");
			var data1='<option value="" disabled selected>Security Question 1</option>';
			var data2='<option value="" disabled selected>Security Question 2</option>';
			var data3='<option value="" disabled selected>Security Question 3</option>';

			for(var i=0; i<response.length; i++){
				if(response[i].category==1){
					var text='<option value="'+response[i].id+'">'+response[i].question+'</option>';
					usertypeDropdown1.append(text);	

				}
				else if(response[i].category==2){
					var text='<option value="'+response[i].id+'">'+response[i].question+'</option>';
					usertypeDropdown2.append(text);	  

				}
				else if(response[i].category==3){
					var text='<option value="'+response[i].id+'">'+response[i].question+'</option>';
					usertypeDropdown3.append(text);	

				}
				else{}
			}

		},    
		error: function (xhr, ajaxOptions, thrownError) {
		}
	});
}



function editProfile(){
	$.ajax({ 
		type : 'POST',
		url : contextpath+'/editProfile',
		contentType : "application/json",
		dataType : 'html', 
		success : function(data) { 
			console.log(data);
			var resp=JSON.parse(data);
			//$("#registrationForm #firstName").val("heello");
			$("#registrationForm #firstName").val(resp.firstName);
			$("#registrationForm #id").val(resp.id);
			$("#registrationForm #middleName").val(resp.middleName);
			$("#registrationForm #lastName").val(resp.lastName);
			$("#registrationForm #type").val(resp.type);        
			$("#registrationForm #asTypeName").val(resp.asTypeName);
			$("#registrationForm #email").val(resp.email); 
			$("#registrationForm #phoneNo").val(resp.phoneNo);
			$("#registrationForm #propertyLocation").val(resp.propertyLocation);
			$("#registrationForm #street").val(resp.street);
			$("#registrationForm #village").val(resp.village);
			$("#registrationForm #district").val(resp.district);
			$("#registrationForm #commune").val(resp.commune);
			$("#registrationForm #country").val(resp.country); 
			$("#registrationForm #postalCode").val(resp.postalCode);
			$("#registrationForm #locality").val(resp.locality);

			//$("#registrationForm #state").text(resp.province);
			$("#registrationForm #companyName").val(resp.companyName);
			$("#registrationForm #passportNo").val(resp.passportNo);
			$("#questionId1 #country").val(resp.country);
			$("#registrationForm #usertypes").val(resp.roles); 
			populateStates( "country","state" );
			$("#registrationForm #state").val(resp.province);
			var arr=[];    
			arr=resp.roles;
			for (var i = 0; i < arr.length; i++) {
				$('#registrationForm #usertypes option[value="'+arr[i]+'"]').attr('disabled', true);
			}




			//$("#").val(resp[i].); 
			var questionData=resp.questionList;
			for(var i=0;i<questionData.length;i++){
				$("#registrationForm #questionId"+i).empty();
				var selectData='<option value="'+questionData[i].questionId+'">'+questionData[i].question+'</option>';
				$("#registrationForm #questionId"+i).append(selectData);
				$("#registrationForm #answer"+i).val(questionData[i].answer);
				$("#registrationForm #id"+i).val(questionData[i].id);
			}  
			questionDataByCategory2();

		}, 
		error: function (xhr, ajaxOptions, thrownError) {
		}

	});
}	

function updateProfile(){
	$("#passwordBtn").prop('disabled', true);
	$('#registrationForm #usertypes option').attr('disabled', false);
	console.log($('select#usertypes').val());
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
					id:val.find('.id').val()
			}   
			questionData.push(obj2);
		}
	});

	var password=document.getElementById("confirmPassword").value;
	$("#registrationForm").each(function(key, val){
		val = $(this);  
		if(val.html() !== "") {
			obj =  
			{       id:val.find('#id').val(),
					firstName:val.find('#firstName').val(),
					middleName:val.find('#middleName').val(),  
					lastName: val.find('#lastName').val(),
					type:val.find('#type').val(), 
					email:val.find('#email').val(),
					phoneNo:val.find('#phoneNo').val(),
					propertyLocation:val.find('#propertyLocation').val(),
					street:val.find('#street').val(),
					village:val.find('#village').val(),
					locality:val.find('#locality').val(),
					district:val.find('#district').val(),
					commune:val.find('#commune').val(),
					postalCode:val.find('#postalCode').val(),
					country:val.find('#country').val(),
					province:val.find('#state').val(),
					companyName:val.find('#companyName').val(),
					passportNo:val.find('#passportNo').val(),
					country:val.find('#country').val(),
					roles:val.find('#usertypes').val(),
					questionList:questionData,
					password:password
			}    
		}  
	});
	console.log("question data:  "+JSON.stringify(obj));

	$.ajax({
		type : 'POST',
		url : contextpath + '/updateProfile',
		contentType : "application/json",
		dataType : 'html',
		data : JSON.stringify(obj),

		success : function(data) {
			var response=JSON.parse(data);
			var lang=window.parent.$('#langlist').val() == 'km' ? 'km' : 'en';

			if(response.statusCode=='200'){
				if(response.userstatus=='Approved'){
					    
				$.i18n().locale = lang;	
					$.i18n().load( {
						'en': './resources/i18n/en.json',
						'km': './resources/i18n/km.json'
					}).done( function() {
						$("#profileResponse #updateInfoMsg").text($.i18n(response.tag)); 
						$('#profileResponse').openModal({
					        dismissible:false
					    });

					});
					
				} 
				else if(response.userstatus=='OTP Verification Pending'){
					$.i18n().locale = $('#langlist').val();
					$.i18n().locale = lang;	
					$.i18n().load( {
						'en': './resources/i18n/en.json',
						'km': './resources/i18n/km.json'
					}).done( function() {
						$("#userid").val(response.userId);
						$("#passwordModal").closeModal();
						$("#otpMsg").text($.i18n(response.tag));
						$("#otpMsgModal").openModal({
					        dismissible:false
					    });
					});
					
					
					//$("#otpMsg").text(response.response);

					
				}
				else{
				}
			}
			else{
				
				
				$.i18n().locale = window.parent.$('#langlist').val();
				$.i18n().load( {
					'en': './resources/i18n/en.json',
					'km': './resources/i18n/km.json'
				}).done( function() {
					$("#registrationForm #errorMsg").text($.i18n(response.tag));
					$("#passwordModal").closeModal();
				});
			}
			$("#passwordBtn").prop('disabled', false);
			$("#btnSave").prop('disabled', false);
		}, 
		error: function (xhr, ajaxOptions, thrownError) {
			$("#passwordBtn").prop('disabled', false);
			$("#btnSave").prop('disabled', false);
		}
	});
	return false;
} 

function passwordPopup(){
	$("#btnSave").prop('disabled', true);
	$("#passwordModal").openModal({
        dismissible:false
    });

	return false;
}





function verifyOtp2(){
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
			$.i18n().locale =window.parent.$('#langlist').val();
			if(resp.statusCode=="200"){
	
				$.i18n().load( {
					'en': './resources/i18n/en.json',
					'km': './resources/i18n/km.json'
				}).done( function() {
				
					$("#otpVerification").closeModal();
					$("#otpMessage #otpResponse").text($.i18n(resp.tag));
					$('#otpMessage').openModal({
				        dismissible:false
				    });
				});
				
			}
			else{
			
//				$.i18n().locale =window.parent.$('#langlist').val();
				$.i18n().load( {
					'en': './resources/i18n/en.json',
					'km': './resources/i18n/km.json'
				}).done( function() {
					$("#otpVerification #verifyOtpResp").text($.i18n(resp.tag));
				});
			}
			$("#otpVerifyBtn").prop('disabled', false);
		},
		error: function (xhr, ajaxOptions, thrownError) {
			$("#otpVerifyBtn").prop('disabled', false);
		}

	});
	return false;
}


function redirectToDashboard(){
	window.location.href="Home?lang="+window.parent.$('#langlist').val();
}