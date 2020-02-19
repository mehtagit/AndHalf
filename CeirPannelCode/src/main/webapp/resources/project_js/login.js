var data_lang_param =$("body").attr("data-lang-param") == ('en_US' || 'en') ? 'en' : 'km';	
$('#langlist').on('change', function() {
			lang = $('#langlist').val() == 'km' ? 'km' : 'en';
			var url_string = window.location.href;
			var url = new URL(url_string);
			var type = url.searchParams.get("type");
			window.location.assign("login?&lang=" + lang);
		});

		$(document).ready(function() {
			//var url = new URL( window.location.href);
    		//var langParameter = url.searchParams.get("lang") == 'km' ? 'km' : 'en';
            $('#langlist').val($("body").attr("data-lang-param")); 
			//dataByTag("link_dmc_portal", "newUserLink", 1);
		});

		
	//Login Msg from javascript
		
		var msg=$("body").attr("data-msg");
		sessionStorage.getItem("loginMsg") == null ? $('#errorMsg').text(msg) : $('#errorMsg').text(sessionStorage.getItem("loginMsg"));
		sessionStorage.removeItem("loginMsg");
		
		
					
		 