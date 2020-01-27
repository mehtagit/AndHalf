var featureId = 12;
var userId = $("body").attr("data-userID");
var cierRoletype = sessionStorage.getItem("cierRoletype");

$(document).ready(function(){
	$('div#initialloader').fadeIn('fast');
	manageUserDataTable()
	pageRendering();
});




var userType = $("body").attr("data-roleType");





//**************************************************Type Approved table**********************************************

function manageUserDataTable(){
var userId = parseInt($("body").attr("data-userID"))
			var filterRequest={
				"endDate":$('#endDate').val(),
				"startDate":$('#startDate').val(),
			  	"tac" : $('#tac').val(),
			  	"txnId" : $('#transactionID').val(),
			  	"userId":userId,
				"featureId":parseInt(featureId),
				"userTypeId": parseInt($("body").attr("data-userTypeID")),
				"userType":$("body").attr("data-roleType"),
				"status" : parseInt($('#Status').val()),
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
					url : './user-paid-status-data',
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
		            { width: 100, targets: result.length - 1 },
		           
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
		url: 'upload-paid-status/pageRendering',
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
					$("#userManageTableDiv").append("<div class='col s6 m2 l2 responsiveDiv'>"+
							"<div id='enddatepicker' class='input-group date'>"+
							"<label for='TotalPrice'>"+date[i].title
							+"</label>"+"<input class='form-control datepicker' type='text' id="+date[i].id+" autocomplete='off'>"+
							"<span	class='input-group-addon' style='color: #ff4081'>"+
							"<i	class='fa fa-calendar' aria-hidden='true' style='float: right; margin-top: -37px;'>"+"</i>"+"</span>");
				}else if(date[i].type === "text"){
					$("#userManageTableDiv").append("<div class='input-field col s6 m2 filterfield' style='margin-top: 22px;'><input type="+date[i].type+" id="+date[i].id+" maxlength='19' /><label for='tac' class='center-align'>"+date[i].title+"</label></div>");
				}
			} 

			// dynamic drop down portion
			var dropdown=data.dropdownList;
			for(i=0; i<dropdown.length; i++){
				var dropdownDiv=
					$("#userManageTableDiv").append("<div class='col s6 m2 l2 selectDropdwn'>"+
							"<br>"+
							"<div class='select-wrapper select2 form-control boxBorder boxHeight initialized'>"+
							"<span class='caret'>"+"</span>"+
							"<input type='text' class='select-dropdown' readonly='true' data-activates='select-options-1023d34c-eac1-aa22-06a1-e420fcc55868' value='Consignment Status'>"+

							"<select id="+dropdown[i].id+" class='select2 form-control boxBorder boxHeight initialized'>"+
							"<option value = '-1'>"+dropdown[i].title+
							"</option>"+
							"</select>"+
							"</div>"+
					"</div>");
			}

			$("#userManageTableDiv").append("<div class='col s12 m2 l2'><input type='button' class='btn primary botton' id='submitFilter' value='filter'></div>");
			$("#userManageTableDiv").append("<div class='col s12 m2'><a href='JavaScript:void(0)' onclick='exportTacData()' type='button' class='export-to-excel right'>Export <i class='fa fa-file-excel-o' aria-hidden='true'></i></a></div>");
			for(i=0; i<button.length; i++){
				$('#'+button[i].id).text(button[i].buttonTitle);
				if(button[i].type === "HeaderButton"){
					$('#'+button[i].id).attr("href", button[i].buttonURL);
				}
				else{
					$('#'+button[i].id).attr("onclick", button[i].buttonURL);
				}
			}
	
			$('.dateClass').datepicker({
			    dateFormat: "yy-mm-dd"
			    });
			

			$.getJSON('./getDropdownList/'+featureId+'/'+$("body").attr("data-userTypeID"), function(data) {

				for (i = 0; i < data.length; i++) {
					$('<option>').val(data[i].state).text(data[i].interp)
					.appendTo('#Status');
				}
			});
		}
		
	}); 
}




