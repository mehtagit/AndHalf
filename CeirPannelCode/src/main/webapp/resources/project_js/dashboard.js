
$(document).ready(function(){
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


