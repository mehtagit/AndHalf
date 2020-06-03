/*
		window.parent.$('#langlist').on('change', function() {
			var lang=window.parent.$('#langlist').val() == 'km' ? 'km' : 'en';
			window.location.assign("./uploadPaidStatus?lang="+lang);
		}); */
		
		
	$('#btnLink').css({"display":"none"});	
	var roleType = $("body").attr("data-roleType");
	var userId = $("body").attr("data-userID");
	var roleType = $("body").attr("data-roleType"); 
	var featureId =12;

	/* $(function() {
		// Initialize form validation on the registration form.
		// It has the name attribute "registration"
		$("form[name='nidregistration']").validate({
			// Specify validation rules
			rules: {
			// The key name on the left side is the name attribute
			// of an input field. Validation rules are defined
			// on the right side
			Search: "required",
			
			password: {
				required: true,
				minlength: 5
			}
			},
			// Specify validation error messages
			messages: {
			firstname: "Please enter your firstname",
			lastname: "Please enter your lastname",
			password: {
				required: "Please provide a password",
				minlength: "Your password must be at least 5 characters long"
			},
			email: "Please enter a valid email address"
			},
			// Make sure the form is submitted to the destination defined
			// in the "action" attribute of the form when valid
			submitHandler: function(form) {
			form.submit();
			}
		});
		});
		*/
		
	function hide() {
		var In = $('#Search').val();
		
		if(In.length == 0){
			console.log("please field input");
		}else{
			sessionStorage.setItem("roleType",roleType);
			sessionStorage.setItem("nationalId", In);
		window.location.replace("./uploadPaidStatus?via=other&NID="+In);
		}
	}