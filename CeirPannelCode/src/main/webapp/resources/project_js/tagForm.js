/*
		window.parent.$('#langlist').on('change', function() {
			var lang=window.parent.$('#langlist').val() == 'km' ? 'km' : 'en';
			window.location.assign("./uploadPaidStatus?lang="+lang);
		}); */
		
		
	$('#btnLink').css({"display":"none"});	
	var roleType = $("body").attr("data-roleType");
	var userId = $("body").attr("data-userID");
	var roleType = $("body").attr("data-roleType"); 
	var featureId =23;

	
	
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