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
		var reportname = $('#tableId').val();
		var reportInterp = $("#tableId option:selected").text();
		
		if(reportname.length == 0){
			//console.log("please field input");
		}else{
			//sessionStorage.setItem("roleType",roleType);
		sessionStorage.setItem("reportname", reportname);
		sessionStorage.setItem("reportInterp", reportInterp);
		window.location.replace("./report?via=other&tableName="+reportname);
		}
	}
	
	
	;
	
	$.ajax({
		url: './getallreports',
		type: 'POST',
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		success: function (data, textStatus, jqXHR) {
			var result= data;
			//console.log("result-->"+JSON. stringify(result))
			for (i = 0; i < result.length; i++){
				$('<option>').val(result[i].reportnameId).text(result[i].reportName).appendTo('#tableId');
			}
			
		},
		error: function (jqXHR, textStatus, errorThrown) {
			//console.log("error in ajax")
		}
	});