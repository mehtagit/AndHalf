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
	
	console.log(JSON.stringify(obj));
	$.ajax({
		type : 'POST',
		url : contextpath + '/changePassword',
		data : JSON.stringify(obj),
		contentType : "application/json",
		dataType : 'html', 
		success : function(data) {
			var resp=JSON.parse(data);
			if(resp.statusCode=='200'){
			$("#changePasswordMessage h6").text(resp.response);
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
			$("#manageAccountSubmit h6").text(resp.response);
			$("#manageAccountSubmit").openModal();   
			
			}
			else{  
				$("#userStatusForm #errorMsg").text(resp.response);
			}
			$("#updateStatusBtn").prop('disabled', true);
		}, 
		error: function (xhr, ajaxOptions, thrownError) {
			$("#updateStatusBtn").prop('disabled', true);
		} 

	});
	return false;
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
			// usertypeDropdown2.empty();
			var usertypeDropdown3=$("#registrationForm #questionId2");
			// usertypeDropdown3.empty(); 
			var data1='<option value="" disabled selected>Security Question 1</option>';
			//usertypeDropdown1.append(data1);
			var data2='<option value="" disabled selected>Security Question 2</option>';
			//usertypeDropdown2.append(data2);
			var data3='<option value="" disabled selected>Security Question 3</option>';
			// usertypeDropdown3.append(data3);

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
			$("#registrationForm #email").val(resp.email); 
			$("#registrationForm #phoneNo").val(resp.phoneNo);
			$("#registrationForm #propertyLocation").val(resp.propertyLocation);
			$("#registrationForm #street").val(resp.street);
			$("#registrationForm #locality").val(resp.locality);
			$("#registrationForm #province").val(resp.province);
			$("#registrationForm #companyName").val(resp.companyName);
			$("#registrationForm #passportNo").val(resp.passportNo);
			$("#registrationForm #country").val(resp.country);  
			$("#questionId1 #country").val(resp.country);
			$("#registrationForm #usertypes").val(resp.roles); 
            
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
			questionDataByCategory();

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
					locality:val.find('#locality').val(),
					province:val.find('#province').val(),
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
			if(response.statusCode=='200'){
			if(response.userstatus=='Approved'){
				$("#passwordModal").closeModal();
				$("#profileResponse h6").text(response.response); 
				$('#profileResponse').openModal();    
			} 
			else if(response.userstatus=='OTP Verification Pending'){
				$("#userid").val(response.userId);
				$("#passwordModal").closeModal();
				$("#otpMsgModal").openModal();     
				$("#otpMsg").text(response.response);
			}
			else{
			}
			}
			else{
				$("#registrationForm #errorMsg").text(response.response);
				$("#passwordModal").closeModal();
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
	$("#passwordModal").openModal();
	return false;
}

