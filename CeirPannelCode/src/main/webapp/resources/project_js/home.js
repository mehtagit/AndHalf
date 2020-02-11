	var userTypeId = parseInt($("body").attr("data-userTypeID"));
	var userType = $("body").attr("data-roleType");
	var userId = $("body").attr("data-userID");
	var featureId="3";
	var requestType="0";

	var sessionLang=window.parent.$('#langlist').val() == 'en' ? 'en' : 'km';
	$.i18n().locale = sessionLang;	
	
	$.i18n().load( {
		'en': './resources/i18n/en.json',
		'km': './resources/i18n/km.json'
	} ).done( function() { 
	});
	

	$(document).ready(function(){
		$('div#initialloader').fadeIn('fast');
		var url;
		$.ajax({
			url: './dashboard/box?userTypeId='+userTypeId,
			type: 'GET',
			success: function(data){
			
				for (i = 0; i < data.length; i++) {
					var id=data[i].name;
					/*var finalID=id.replace (/\//g, "");*/
					url= data[i].url.split("?"); 
					$("#infoBox").append("<div class='round-circle-center-responsive'><div class='round-circle'><h6 class='right' style='width: 100px;'>"+$.i18n(data[i].name)+"</h6><p class='circle-para right'><b id='"+data[i].id+"count'></b> </p><p class='center view-div-info'><a href='"+data[i].viewName+"' class=''><i class='fa fa-eye teal-text' title='view'></i></a></p><div class='icon-div center'><i class='"+data[i].icon+"' aria-hidden='true'></i></div></div>");
					var finalID = data[i].id;
					var outParam = data[i].outParam;
					if(userTypeId == 8){
						userId = -1;
					}
					$.ajax({
						url: './'+url[0]+'?featureId='+data[i].featureId+'&userId='+userId+'&userTypeId='+userTypeId+'&requestType='+requestType+'&userType='+userType,
						'async': false,
						type: 'GET',
						success: function(data){
							outParam == 'count' ? $('#'+finalID+'count').text(data.count) : $('#'+finalID+'count').text(data.quantity);	
						}
					});
				}
				
			}
		});
		notificationDatatable(sessionLang);
	});



	localStorage.setItem("sourceType", "viaDashBoard");
	localStorage.setItem("grievancePageSource", "viaDashBoard");



	//**************************************************Notification Data table**********************************************

	function notificationDatatable(){

		var filterRequest = {
				"userTypeId" : parseInt($("body").attr("data-userTypeID")),
				"userType" : $("body").attr("data-roleType"),
				"userId" : $("body").attr("data-userID"),
				"featureId" : 3
		}

		
		if(sessionLang=='km'){
			var langFile="//cdn.datatables.net/plug-ins/1.10.20/i18n/Khmer.json";
		}
		
		$.ajax({
			url: 'headers?type=dashboardNotification&lang='+sessionLang,
			type: 'POST',
			dataType: "json",
			success: function(result){
				var table=	$("#notificationLibraryTable").DataTable({
					destroy:true,
					"serverSide": true,
					orderCellsTop : true,
					"ordering" : false,
					"bPaginate" : true,
					"bFilter" : true,
					"bInfo" : true,
					"bSearchable" : true,
					ajax: {
						url : './NotificationData',
						type: 'POST',
						dataType: "json",
						data : function(d) {
							d.filter = JSON.stringify(filterRequest); 
						}

					},
					"columns": result
				});
				$('div#initialloader').delay(300).fadeOut('slow');
			},
			error: function (jqXHR, textStatus, errorThrown) {
				console.log("error in ajax");
			}
		});
	}


/*	function changeLanguage(lang){
		$.ajax({
			type : 'POST',
			url :'./changeLanguage/'+lang,
			contentType :"application/json",
			dataType : 'html',
			success : function(data) {
			},      
			error: function (xhr, ajaxOptions, thrownError) {
			}
		});
	}
*/