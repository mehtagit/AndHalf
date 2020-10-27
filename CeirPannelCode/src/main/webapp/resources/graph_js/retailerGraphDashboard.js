
//boxes
$(document).ready(function(){
	$('div#initialloader').fadeIn('fast');
	var url;

	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	[40,50,31,36,47].forEach(function(reportnameId) {
	$.ajaxSetup({
	headers:
	{ 'X-CSRF-TOKEN': token }
	});
	var graphRequest={

			"reportnameId": reportnameId,
			"file" : 0,
			"pageSize" :1,
			"pageNo" :0
	}
	
	$.ajax({
		type : 'POST',
		url : './importer/count/retailer',
		contentType : "application/json",
		async: false,
		data : JSON.stringify(graphRequest),
		success: function(data){
var i=0;
				Object.keys(data['rowData'][0]).map(function(key){ 
				if(key == 'Date'){
					$('#dateVal').text('Last Update Date: '+data['rowData'][0][key]);
				}
				else{if(data['rowData'][0][key]!=null)
				{
					$("#infoBox").append("<div class='round-circle-center-responsive'><div class='round-circle'><h6 class='right' style='width: 105px;'>"+key+"</h6><p class='circle-para right' style='position:absolute;margin-top:62px;width: 180px;margin-left: 5px;padding-right: 0px !important;'><b id="+i+++">"+data['rowData'][0][key]+"</b> </p><div class='icon-div center'><i class='fa fa-puzzle-piece test-icon' aria-hidden='true'></i></div></div>");	
					}
				else{
					//$("#infoBox").append("<div class='round-circle-center-responsive'><div class='round-circle'><h6 class='right' style='width: 105px;'>"+key+"</h6><p class='circle-para right' style='position:absolute;margin-top:62px;width: 180px;margin-left: 5px;padding-right: 0px !important;'><b id="+i+++">0</b> </p><div class='icon-div center'><i class='fa fa-puzzle-piece test-icon' aria-hidden='true'></i></div></div>");
				}

				}
				$('div#initialloader').delay(300).fadeOut('slow');

				
		});
		}
	});
	});
});