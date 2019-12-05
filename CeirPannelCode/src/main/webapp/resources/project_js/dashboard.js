$(document).ready(function(){
	$.ajax({
		url: './dashboard/box?userTypeId=4',
		type: 'GET',
		success: function(data){
			for (i = 0; i < data.length; i++) {
				$("#infoBox").append("<div class='round-circle-center-responsive'><div class='round-circle'><h6 class='right'>"+data[i].name+"</h6><p class='circle-para right'><b id='"+data[i].name+"count'></b> </p><p class='center view-div-info'><a href='"+data[i].url+"' class=''><i class='fa fa-eye teal-text' title='view'></i></a></p><div class='icon-div center' style='background-color: #fc950c;'><i class='fa fa-list-alt test-icon' aria-hidden='true'></i></div></div>");
				}
			
			$.ajax({
				url: './getConsignmetnCountAndQuantity?featureId=3&userId=271&userTypeId=8',
				type: 'GET',
				success: function(data){
					$('#Consignmentcount').text(data.count);	
				}
			});
		}
		});
	notificationDatatable();
});



//**************************************************Notification Data table**********************************************

function notificationDatatable(){

	$.ajax({
		url: 'headers?type=dashboardNotification',
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
					url : 'NotificationData',
					type: 'POST',
					dataType: "json",
					data : function(d) {
						console.log("Success");
					}

				},
				"columns": result
			});
		},
		error: function (jqXHR, textStatus, errorThrown) {
			console.log("error in ajax");
		}
	});
}


