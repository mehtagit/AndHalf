/*
		window.parent.$('#langlist').on('change', function() {
			var lang=window.parent.$('#langlist').val() == 'km' ? 'km' : 'en';
			window.location.assign("./uploadPaidStatus?lang="+lang);
		}); */
	
		var lang=window.parent.$('#langlist').val() == 'km' ? 'km' : 'en';


		$.i18n().locale = lang;	

		$.i18n().load( {
			'en': './resources/i18n/en.json',
			'km': './resources/i18n/km.json'
		} ).done( function() { 
			
		});

	$('#btnLink').css({"display":"none"});	
	var roleType = $("body").attr("data-roleType");
	var userId = $("body").attr("data-userID");
	var roleType = $("body").attr("data-roleType"); 
	
	$.getJSON('./getDropdownList/REPORT_CATEGORY', function(data) {
		for (i = 0; i < data.length; i++) {
			$('<option>').val(data[i].value).text(data[i].interp)
			.appendTo('#reportCatagory');
		}
	});

	$('#reportCatagory').on(
			'change',
			function() {
				var token = $("meta[name='_csrf']").attr("content");
				var header = $("meta[name='_csrf_header']").attr("content");
				$.ajaxSetup({
					headers:
					{ 'X-CSRF-TOKEN': token }
				});
				
				var reportCategory = parseInt($('#reportCatagory').val());
				
				$.ajax({
					url: './getallreports?reportCategory='+reportCategory,
					type: 'POST',
					dataType : 'json',
					contentType : 'application/json; charset=utf-8',
					success: function (data, textStatus, jqXHR) {
						var result= data;
						$("#tableId").empty();
						for (i = 0; i < result.length; i++){
							$('<option>').val(result[i].reportnameId).text(result[i].reportName).appendTo('#tableId');
						}

					},
					error: function (jqXHR, textStatus, errorThrown) {
						//////console.log("error in ajax")
					}
				});
			});
	
	
	
	function hide() {
		var reportname = $('#tableId').val();
		var reportInterp = $("#tableId option:selected").text();
		
		if(reportname.length == 0){
			//////console.log("please field input");
		}else{
			//sessionStorage.setItem("roleType",roleType);
		sessionStorage.setItem("reportname", reportname);
		sessionStorage.setItem("reportInterp", reportInterp);
		window.location.replace("./report?via=other&tableName="+reportname);
		return false;
		}
		
	};
	
	
	
