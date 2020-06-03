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
		var tableName = $('#tableId').val();
		
		if(tableName.length == 0){
			console.log("please field input");
		}else{
			//sessionStorage.setItem("roleType",roleType);
		sessionStorage.setItem("tableName", tableName);
		window.location.replace("./dbTables?via=other&tableName="+tableName);
		}
	}
	
	
	var dbName = "ceirconfig" ;
	
	$.ajax({
		url: './getallTables?dbName='+dbName,
		type: 'POST',
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		success: function (data, textStatus, jqXHR) {
			var result = data.tableNames;
			for (i = 0; i < result.length; i++){
				$('<option>').val(result[i]).text(result[i]).appendTo('#tableId');
			}
			
		},
		error: function (jqXHR, textStatus, errorThrown) {
			console.log("error in ajax")
		}
	});