function forgotPassword(){
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
			if(resp.statusCode==200){
				$("#usernamedata").val(username);
				$('#changePassword').openModal();
			}      
			else{
				$("#forgotPassword #errorMsg").text(resp.response);
			}
		},
		error: function (xhr, ajaxOptions, thrownError) {
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
				$("#responseMsg").text(resp.response);
				$("#submitBtnAction").openModal();	
			}
			else{
				$("#changePassword #errorMsg").text(resp.response);
			}
			
		}, 
		error: function (xhr, ajaxOptions, thrownError) {
		}

	});
	return false;
}