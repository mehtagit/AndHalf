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
   
   
  

	function hide() {
		var In = $('#Search').val();
		//sessionStorage.setItem("roleType",roleType);
		sessionStorage.setItem("userName", In);
		
		if(In.length == 0){
				console.log("please field input");
		}else{
				
				/*$.ajax({
					url: './selfRegisterDevicePage?NID='+In,
					type: 'POST',
					processData: false,
					contentType: false,
					success: function (data, textStatus, jqXHR) {
						console.log("111111111");
					},
					error: function (jqXHR, textStatus, errorThrown) {
						console.log("error in ajax")

					}
				});*/
				//window.location.replace("./selfRegisterDevicePage?NID="+In);
				window.location.href = "./raiseCCgrievance";
				
				$("#submitbtn").css("display", "none");
				$("#endUserRaiseGrievance").css("display", "block");
			}
	    	
	}