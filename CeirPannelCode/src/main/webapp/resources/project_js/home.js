var userTypeId = parseInt($("body").attr("data-userTypeID"));
var userType = $("body").attr("data-roleType");
var userId = $("body").attr("data-userID");
var featureId="1";
var requestType="stolen";
$(document).ready(function(){
	$.ajax({
		url: './dashboard/box?userTypeId='+userTypeId,
		type: 'GET',
		success: function(data){
			for (i = 0; i < data.length; i++) {
				var id=data[i].name;
				var finalID=id.replace (/\//g, "");

				$("#infoBox").append("<div class='round-circle-center-responsive'><div class='round-circle'><h6 class='right'>"+data[i].name+"</h6><p class='circle-para right'><b id='"+finalID+"count'></b> </p><p class='center view-div-info'><a href='"+data[i].url+"' class=''><i class='fa fa-eye teal-text' title='view'></i></a></p><div class='icon-div center' style='background-color: #fc950c;'><i class='"+data[i].icon+"' aria-hidden='true'></i></div></div>");
			}

			$.ajax({
				url: './getConsignmetnCountAndQuantity?featureId='+featureId+'&userId='+userId+'&userTypeId='+userTypeId,
				type: 'GET',
				success: function(data){
					$('#Consignmentcount').text(data.count);	
				}
			});


			$.ajax({
				url: './getStockCountAndQuantity?featureId='+featureId+'&userId='+userId+'&userTypeId='+userTypeId,
				type: 'GET',
				success: function(data){
					$('#Stockcount').text(data.count);	
				}
			});


			$.ajax({
				url: './getStolen_RecoveryCountAndQuantity?requestType='+requestType+'&featureId='+featureId+'&userId='+userId+'&userTypeId='+userTypeId,
				type: 'GET',
				success: function(data){
					$('#StolenRecoverycount').text(data.count);	
				}
			});



			$.ajax({
				url: './getGrievanceNotificationCountAndQuantity?featureId='+featureId+'&userId='+userId+'&userTypeId='+userTypeId,
				type: 'GET',
				success: function(data){
					$('#Grievancecount').text(data.count);	
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


