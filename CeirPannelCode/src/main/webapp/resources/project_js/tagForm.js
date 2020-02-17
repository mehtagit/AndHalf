/*
		window.parent.$('#langlist').on('change', function() {
			var lang=window.parent.$('#langlist').val() == 'km' ? 'km' : 'en';
			window.location.assign("./uploadPaidStatus?lang="+lang);
		}); */
		
		
	$('#btnLink').css({"display":"none"});	
	var roleType = $("body").attr("data-roleType");
	var userId = $("body").attr("data-userID");
	var roleType = $("body").attr("data-roleType"); 


	
	
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
	
	
	
	var request ={
			  "userId" : parseInt($("body").attr("data-userID"))
		}
	$.ajax({
		url: './getSystemTags',
		type: 'POST',
		data : JSON.stringify(request),
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		success: function (data, textStatus, jqXHR) {
			var result = data.data;
			for (i = 0; i < result.length; i++){
				$('<option>').val(result[i]).text(result[i]).appendTo('#tagId');
			}
			
		},
		error: function (jqXHR, textStatus, errorThrown) {
			console.log("error in ajax")
		}
	});