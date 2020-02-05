$('#langlist').on('change', function() {
			window.lang = $('#langlist').val() == 'km' ? 'km' : 'en';
			var url_string = window.location.href;
			var url = new URL(url_string);
			var type = url.searchParams.get("type");
			window.location.assign("login?&lang=" + window.lang);
		});

		$(document).ready(function() {
			var url = new URL(window.location.href);
			var langParameter = url.searchParams.get("lang");
			$('#langlist').val(langParameter == 'km' ? 'km' : 'en');
			dataByTag("link_dmc_portal", "newUserLink", 1);
		});

		
		$(document).on('click', '.toggle-password', function() {

		    $(this).toggleClass("fa-eye fa-eye-slash");
		    
		    var input = $(".password");
		    input.attr('type') === 'password' ? input.attr('type','text') : input.attr('type','password')
		});
		
		$(document).on('click', '.toggle-password2', function() {

		    $(this).toggleClass("fa-eye fa-eye-slash");
		    
		    var input = $(".oldPassword");
		    input.attr('type') === 'password' ? input.attr('type','text') : input.attr('type','password')
		});
		$(document).on('click', '.toggle-password3', function() {

		    $(this).toggleClass("fa-eye fa-eye-slash");
		    
		    var input = $(".newPassword");
		    input.attr('type') === 'password' ? input.attr('type','text') : input.attr('type','password')
		});
		
		
		
		
		
					
		