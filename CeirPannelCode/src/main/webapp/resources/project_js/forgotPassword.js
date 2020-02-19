	$('#langlist').on('change', function() {
		lang=$('#langlist').val() == 'km' ? 'km' : 'en';
		var url_string = window.location.href;
		var url = new URL(url_string);
		var type = url.searchParams.get("reportType");

		window.location.assign("forgotPassword?lang="+lang);			
		});	
$(document).ready(function () {
	 $('#langlist').val(data_lang_param);
				//$('.modal').openModal();
				questionData();
			});
			
			var password = document.getElementById("password");
			var confirm_password = document.getElementById("confirm_password");

			function validatePassword(){
			if(password.value != confirm_password.value) {
			confirm_password.setCustomValidity("Passwords Don't Match");
			} else {
			confirm_password.setCustomValidity('');
			}
			}
			password.onchange = validatePassword;
			confirm_password.onkeyup = validatePassword;

		