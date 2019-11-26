function verifyOtp(){
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
				window.location.href='#otpMessage';
				
				$('#otpMessage').openModal();   
				$("#otpResponse").text(resp.response);
				// $('#otpMessage').modal('open');
			}
			else{

			}
		},
		error: function (xhr, ajaxOptions, thrownError) {
		}

	});
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
		success : function(data) {
			var response=JSON.parse(data);                                    
			var usertypeDropdown=$("#usertypes");  
			for(var i=0; i<response.length; i++){
				if(response[i].usertypeName!='admin'){
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
					province:val.find('#province').val(),
					country:val.find('#country').val(),
					vatStatus:val.find("input[name='vatStatus']:checked").val(),
					vatNo:val.find('#vatNo').val(),
					roles:val.find('#usertypes').val(),  
					password:val.find('#password').val(),  
					rePassword:val.find('#confirm_password').val(),
					captcha:val.find('#captcha').val(),
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
    $.ajax({   
		type : 'POST',
		url : contextpath + '/saveRegistration',
	   //	contentType : "application/json",
		//dataType : 'html',            
		data :formData, 
		  processData : false,
          contentType : false,
		success : function(response) {
			var respData=JSON.parse(JSON.stringify(response));
			console.log("response from server:  "+JSON.stringify(respData));
			if(respData.statusCode==200){
				window.location.href='./verifyOtpPage/?userid='+respData.userId;
			}
			else{
				$("#registrationForm #msg").text(respData.response);
			}
		}, 
		error: function (xhr, ajaxOptions, thrownError) {
		}

	});

return false;
}



	