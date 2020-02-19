function forgotPassword(){
	$("#forgotPasswordBtn").prop('disabled', true);
	var obj="";
	var username=document.getElementById("username").value;
	$("#forgotPassword").each(function(key, val){
		val = $(this);
		if(val.html() !== "") {
			obj =  
			{ 
					username:val.find('#username').val(),
					questionId:val.find('#questionId').val(),
					answer: val.find('#answer').val()
			}    
		}    
	});
	console.log("obj data:"+JSON.stringify(obj));
	$.ajax({
		type : 'POST',
		url : contextpath + '/forgotPasswordRequest',
		contentType : "application/json",
		dataType : 'html',
		data : JSON.stringify(obj),
		success : function(data) {
			var resp=JSON.parse(data);
			if(resp.statusCode=='200'){
				$("#usernamedata").val(username);
				$('#changePassword').openModal();
			}      
			else{
				
				$.i18n().locale = $('#langlist').val();
				$("#forgotPassword #errorMsg").text(resp.response);
/*				$.i18n().load( {
					'en': './resources/i18n/en.json',
					'km': './resources/i18n/km.json'
				}).done( function() {
					$("#forgotPassword #errorMsg").text($.i18n(resp.tag));
				});
*/
			}
			$("#forgotPasswordBtn").prop('disabled', false);
		},
		error: function (xhr, ajaxOptions, thrownError) {
			$("#forgotPasswordBtn").prop('disabled', false);
		} 
	});
	return false;
} 
function udapteNewPassword(){
	var obj="";
	$("#changePassword").each(function(key, val){
		val = $(this);
		if(val.html() !== "") {
			obj =  
			{  
					username:val.find('#usernamedata').val(),
					password:val.find('#password').val(),
					confirmPassword: val.find('#confirm_password').val()
			} 
		}
	});
	$.ajax({  
		type : 'POST',
		url : contextpath + '/updateNewPassword',
		contentType : "application/json",
		dataType :'html',      
		data : JSON.stringify(obj), 
		success : function(data) {
			var resp=JSON.parse(data);
			if(resp.statusCode=='200'){
				$.i18n().locale = $('#langlist').val();
				$.i18n().load( {
					'en': './resources/i18n/en.json',
					'km': './resources/i18n/km.json'
				}).done( function() {
					$("#responseMsg").text($.i18n(resp.tag));
				});
				
				
				$("#submitBtnAction").openModal();	
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

		}, 
		error: function (xhr, ajaxOptions, thrownError) {
		}

	});
	return false;
}

function login(){
	var obj="";
	$("#loginForm").each(function(key, val){
		val = $(this);
		if(val.html()!=="") {
			obj =  
			{ 
					username:val.find('#username').val(),
					password:val.find('#password').val(),
					captcha:val.find("#captcha").val()
			}    
		}    
	});
	console.log("obj data:"+JSON.stringify(obj));
	$.ajax({
		type : 'POST',
		url : contextpath + '/saveLogin',
		contentType : "application/json",
		dataType : 'html',
		data : JSON.stringify(obj),
		success : function(data) {
			var resp=JSON.parse(data);
			if(resp.statusCode=='200'){
				window.location.href="./importerDashboard?lang="+resp.userLanguage;
			}
			else if(resp.statusCode=='401'){
				$("#userId").val(resp.userId);
				$('#changePassword').openModal();
			}
			else{
				
				$.i18n().locale = $('#langlist').val();
				$.i18n().load( {
					'en': './resources/i18n/en.json',
					'km': './resources/i18n/km.json'
				}).done( function() {
					$("#errorMsg").text($.i18n(resp.tag));
				});
				
			}

		},
		error: function (xhr, ajaxOptions, thrownError) {

		} 
	});
	return false;
}
function dataByTag(tag,divId,input){ 
	$.ajax({
		type : 'GET',
		url :contextpath+'/dataByTag/'+tag+"/",
		contentType : "application/json",
		dataType : 'html',
		success : function(data) {
			var response=JSON.parse(data);
			if(input==1){
				var asTypeDropdown=$("#"+divId);  
				asTypeDropdown.attr('href',response.value);			
			}
			else if(input==2){
				var copyRightSpan=$("#"+divId);
				copyRightSpan.empty();
				copyRightSpan.append(response.value);	
			}
			else{}
		},      
		error: function (xhr, ajaxOptions, thrownError) {
		}
	});
}




function changeExpiryPassword(){
	$("#changePassBtn").prop('disabled', true);
	var obj="";
	$("#changePassword").each(function(key, val){
		val = $(this);
		if(val.html() !== "") {
			obj =  
			{  
					oldPassword:val.find('#oldPassword').val(),
					password:val.find('#password').val(),
					confirmPassword: val.find('#confirm_password').val(),
					userid:val.find("#userId").val()
			}    
		}
	});
	$.ajax({
		type : 'POST',
		url : contextpath + '/changeExpirePassword',
		data : JSON.stringify(obj),
		contentType : "application/json",
		dataType : 'html', 
		success : function(data) {
			var resp=JSON.parse(data);
			if(resp.statusCode=='200'){
				$("#changePasswordMessage #cPassSucessMsg").text(resp.response);
				$("#changePasswordMessage").openModal();   
			}
			else{
				$("#changePassword #errorMsg").text(resp.response);
			}
			$("#changePassBtn").prop('disabled', false);
		},  
		error: function (xhr, ajaxOptions, thrownError) {
			$("#changePassBtn").prop('disabled', false);
		} 

	});
	return false;
}



