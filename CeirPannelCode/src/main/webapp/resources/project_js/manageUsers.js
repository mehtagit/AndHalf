var featureId = 19;
var userId = $("body").attr("data-userID");
var cierRoletype = sessionStorage.getItem("cierRoletype");

$(document).ready(function(){
	$('div#initialloader').fadeIn('fast');
	filter();
	pageRendering();
});




var userType = $("body").attr("data-roleType");





//**************************************************Type Approved table**********************************************

function filter(){
var userId = parseInt($("body").attr("data-userID"))
			var filterRequest={
				"endDate":$('#endDate').val(),
				"startDate":$('#startDate').val(),
				"txnId" : $('#transactionID').val(),
				"nid": $('#nId').val(),
			  	"userId":userId,
				"featureId":parseInt(featureId),
				"userTypeId": parseInt($("body").attr("data-userTypeID")),
				"userType":$("body").attr("data-roleType"),
				}
	
	
	$.ajax({
		url: './headers?type=ManageUserType',
		type: 'POST',
		dataType: "json",
		success: function(result){
			var table=	$("#userManageLibraryTable").removeAttr('width').DataTable({
				destroy:true,
				"serverSide": true,
				orderCellsTop : true,
				"ordering" : false,
				"bPaginate" : true,
				"bFilter" : true,
				"bInfo" : true,
				"bSearchable" : true,
				ajax: {
					url : './manageUserData',
					type: 'POST',
					dataType: "json",
					data : function(d) {
						d.filter = JSON.stringify(filterRequest); 
						console.log(JSON.stringify(filterRequest));
					}

				},
				"columns": result,
				fixedColumns: true,
				columnDefs: [
		            { width: 155, targets: result.length - 1 },
		           
			]
			});
			
			$('#typeAprroveTable input').unbind();
		    $('#typeAprroveTable input').bind('keyup', function (e) {
		        if (e.keyCode == 13) {
		            table.search(this.value).draw();
		        }
		    });
		    $('div#initialloader').delay(300).fadeOut('slow');
		},
		error: function (jqXHR, textStatus, errorThrown) {
			console.log("error in ajax");
		}
	});
}


//**************************************************Type Approved page buttons**********************************************

function pageRendering(){
	$.ajax({
		url: 'manageUser/pageRendering',
		type: 'POST',
		dataType: "json",
		success: function(data){
			data.userStatus == "Disable" ? $('#btnLink').addClass( "eventNone" ) : $('#btnLink').removeClass( "eventNone" );

			var elem='<p class="PageHeading">'+data.pageTitle+'</p>';		
			$("#pageHeader").append(elem);
			var button=data.buttonList;



			var date=data.inputTypeDateList;
			for(i=0; i<date.length; i++){
				if(date[i].type === "date"){
					$("#userManageTableDiv").append("<div class='input-field col s6 m2'>"+
							"<div id='enddatepicker' class='input-group'>"+
							"<input class='form-control datepicker' type='text' id="+date[i].id+" autocomplete='off'>"+
							"<label for="+date[i].id+">"+date[i].title
							+"</label>"+
							"<span	class='input-group-addon' style='color: #ff4081'>"+
							"<i	class='fa fa-calendar' aria-hidden='true' style='float: right; margin-top: -37px;'>"+"</i>"+"</span>");

				}else if(date[i].type === "text"){
					$("#userManageTableDiv").append("<div class='input-field col s6 m2'><input type="+date[i].type+" id="+date[i].id+" maxlength='19' /><label for="+date[i].id+" class='center-align'>"+date[i].title+"</label></div>");
				}
			} 

			// dynamic dropdown portion
		/*	var dropdown=data.dropdownList;
			for(i=0; i<dropdown.length; i++){
				var dropdownDiv=
					$("#userManageTableDiv").append("<div class='col s6 m2 selectDropdwn'>"+
							
							"<div class='select-wrapper select2  initialized'>"+
							"<span class='caret'>"+"</span>"+
							"<input type='text' class='select-dropdown' readonly='true' data-activates='select-options-1023d34c-eac1-aa22-06a1-e420fcc55868' value='Consignment Status'>"+

							"<select id="+dropdown[i].id+" class='select-wrapper select2  initialized'>"+
							"<option value=''>"+dropdown[i].title+
							"</option>"+
							"</select>"+
							"</div>"+
					"</div>");
			}*/



			$("#userManageTableDiv").append("<div class='col s3 m2 l1'><button type='button' class='btn primary botton'  id='submitFilter' /></div></div></div>");
			$("#userManageTableDiv").append("<div class='col s3 m2 l3'><a href='JavaScript:void(0)' onclick='exportpaidStatus()' type='button' class='export-to-excel right'>Export<i class='fa fa-file-excel-o' aria-hidden='true'></i></a></div>");

			for(i=0; i<button.length; i++){
				$('#'+button[i].id).text(button[i].buttonTitle);
				if(button[i].type === "HeaderButton"){
					$('#'+button[i].id).attr("href", button[i].buttonURL);
				}
				else{
					$('#'+button[i].id).attr("onclick", button[i].buttonURL);
				}
			}

		}
	}); 	

}



