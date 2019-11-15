function changePassword(){
	var obj="";
	$("#changePassword").each(function(key, val){
		val = $(this);
		if(val.html() !== "") {
			obj =  
			{  
					oldPassword:val.find('#oldPassword').val(),
					password:val.find('#newPassword').val(),
					confirmPassword: val.find('#confirmPassword').val()
			}    
		}
	});
	$.ajax({
		type : 'POST',
		url : contextpath + '/changePassword',
		contentType : "application/json",
		dataType : 'html',
		data : JSON.stringify(obj),
		success : function(data) {
			var resp=JSON.parse(data);
			$("#changePasswordMessage h6").text(resp.response);
			$("#changePasswordMessage").openModal();
		}, 
		error: function (xhr, ajaxOptions, thrownError) {
		}

	});
	return false;
}

function updateUSerStatus(){
	var obj="";
	obj={
			userStatus:$("input[name='status']:checked").val()

	};
	$.ajax({ 
		type : 'POST',
		url : contextpath+'/updateUserStatus',
		contentType : "application/json",
		dataType : 'html', 
		data : JSON.stringify(obj),
		success : function(data) { 
			var resp=JSON.parse(data);
			$("#manageAccountSubmit h6").text(resp.response);
			$("#manageAccountSubmit").openModal();
		}, 
		error: function (xhr, ajaxOptions, thrownError) {
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
			// usertypeDropdown1.empty();
			var usertypeDropdown2=$("#registrationForm #questionId1");
			// usertypeDropdown2.empty();
			var usertypeDropdown3=$("#registrationForm #questionId2");
			// usertypeDropdown3.empty(); 
			var data1='<option value="" disabled selected>Security Question</option>';
			//usertypeDropdown1.append(data1);
			var data2='<option value="" disabled selected>Security Question</option>';
			//usertypeDropdown2.append(data2);
			var data3='<option value="" disabled selected>Security Question</option>';
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
	$('#registrationForm #usertypes option').attr('disabled', false);
	console.log($('select#usertypes').val());
alert($('select#usertypes').val());
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
					questionList:questionData
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
			//var resp=JSON.parse(data);
			//$("#changePasswordMessage h6").text(resp.response);
			//$("#changePasswordMessage").openModal();
		}, 
		error: function (xhr, ajaxOptions, thrownError) {
		}

	});


}

