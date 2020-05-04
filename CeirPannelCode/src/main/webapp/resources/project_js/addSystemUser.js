	var featureId = 41;
	$('#langlist').on('change', function() {
		lang=$('#langlist').val() == 'km' ? 'km' : 'en';
		var url_string = window.location.href;
		var url = new URL(url_string);
		var type = url.searchParams.get("reportType");

		window.location.assign("raiseAgrievance?reportType="+type+"&lang="+lang);			
		}); 


	
	
	
	$(document).ready(function () {
		 $('#langlist').val(data_lang_param);
		 $.i18n().locale = data_lang_param;
			var successMsg;
			$.i18n().load( {
				'en': './resources/i18n/en.json',
				'km': './resources/i18n/km.json'
			} ).done( function() { 
			});

	});

	
function SaveSystemUser(){
	var name=$('#editName').val();
		var description=$('#editDescription').val();
		var state=$('#editState').val();
		var newUser={
				"firstName": $('#firstName').val(),
				"middleName":$('#middleName').val(),
				"lastName": $('#lastName').val(),
				"phoneNo": $('#contactNumber').val(),
				"email": $('#emailID').val(),
				"password": $('#password').val(),
				"password":$('#confirmPassword').val(),
				"userTypeId":parseInt($('#userType').val()),
				"remarks": $('#userRemark').val()
		}
		$.ajax({

			url : "./saveNewSystemUser",
			data : JSON.stringify(newUser),
			dataType : 'json',
			contentType : 'application/json; charset=utf-8',
			type : 'POST',
			success: function (data, textStatus, jqXHR) {
				$("#successModal").openModal({
			        dismissible:false
			    });

				
			},
			error: function (jqXHR, textStatus, errorThrown) {

			}
		});

		return false;
	}


$.getJSON('./registrationUserType?type=1', function(data) {
			for (i = 0; i < data.length; i++) {
				$('<option>').val(data[i].id).text(data[i].usertypeName)
				.appendTo('#userType');
			}
	});



if($('#pageTypeValue').val()==0)
{
console.log("if condition ++++++++");
$('#endUserRaiseGrievance').css("display", "block");
$('#trackGrievanceDiv').css("display", "none");
$('#trackGrievanceHeader').css("display", "none");
$('#trackGrievanctableDiv').css("display", "none");
}
else
{
console.log("else condition ++++++++");
$('#endUserRaiseGrievance').css("display", "none");
$('#trackGrievanceDiv').css("display", "block");
$('#trackGrievanctableDiv').css("display", "none");

}



function  openCancelPopUp()
{
 $('#cancelMessage').openModal(); 
}

function  closeCancelPopUp()
{
 $('#cancelMessage').closeModal();
}

function enableEndUserAddMore(){
$(".endUser_add_field_button").attr("disabled", false);
}

function enableEndUserReplyAddMore(){
$(".add_field_button").attr("disabled", false);
}


function enableReplySelectFile(){
	$("#docTypeFile1").attr("disabled", false);
	$("#docTypeFile1").attr("required", true);
	$("#docTypeFile1Label").append('<span class="star">*</span>');
}



	
			
			
			
		
			

	
	
	
	
	