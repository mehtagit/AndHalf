var featureId = 16;
var roleType = $("body").attr("data-roleType");
var userId = $("body").attr("data-userID");
var currentRoleType = $("body").attr("data-selected-roleType"); 
var startdate=$('#startDate').val(); 
var endDate=$('#endDate').val();

$(document).ready(function(){
	configManagementDatatable();
	pageRendering();
	
});

var role = currentRoleType == null ? roleType : currentRoleType;


//**************************************************Config Detail table**********************************************

function configManagementDatatable(){
	
	var filterRequest={
			"endDate":$('#endDate').val(),
			"startDate":$('#startDate').val(),
			"userId":parseInt(userId),
			"featureId":parseInt(featureId),
			"userTypeId": parseInt($("body").attr("data-userTypeID")),
			"userType":$("body").attr("data-roleType"),
			"tag":$('#parametername').val()
	}
	
	$.ajax({
		url: 'headers?type=adminConfigMessage',
		type: 'POST',
		dataType: "json",
		success: function(result){
			/*console.log("Url-------" +url+"--------"+ "dataUrl-------" +dataUrl);*/
			var table=	$("#configLibraryTable").removeAttr('width').DataTable({
				destroy:true,
				"serverSide": true,
				orderCellsTop : true,
				"ordering" : false,
				"bPaginate" : true,
				"bFilter" : true,
				"bInfo" : true,
				"bSearchable" : true,
				ajax: {
					url : 'adminConfigData',
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
		            { width: 100, targets: result.length - 1 }
		        ]
			});
		},
		error: function (jqXHR, textStatus, errorThrown) {
			console.log("error in ajax");
		}
	});
}



//**************************************************viewConfig page buttons**********************************************

function pageRendering(){
	$.ajax({
		url: 'configManagement/pageRendering',
		type: 'POST',
		dataType: "json",
		success: function(data){
			var elem='<p class="PageHeading">'+data.pageTitle+'</p>';		
			$("#pageHeader").append(elem);
			var button=data.buttonList;

			var date=data.inputTypeDateList;
			
			for(i=0; i<date.length; i++){
				if(date[i].type === "date"){
					$("#configTableDiv").append("<div class='col s6 m2 l2 responsiveDiv'>"+
							"<div id='enddatepicker' class='input-group date'>"+
							"<label for='TotalPrice'>"+date[i].title
							+"</label>"+"<input class='form-control datepicker' type='text' id="+date[i].id+" autocomplete='off'>"+
							"<span	class='input-group-addon' style='color: #ff4081'>"+
							"<i	class='fa fa-calendar' aria-hidden='true' style='float: right; margin-top: -37px;'>"+"</i>"+"</span>");
					}
					else if(date[i].type === "text"){
						$("#configTableDiv").append("<div class='input-field col s6 m2' style='margin-top: 22px;'><input type="+date[i].type+" id="+date[i].id+"><label for='parametername' class='center-align'>"+date[i].title+"</label></div>");
						
					}
					
				} 
			
			// dynamic dropdown portion
			var dropdown=data.dropdownList;
			for(i=0; i<dropdown.length; i++){
				var dropdownDiv=
					$("#configTableDiv").append("<div class='col s6 m2 l2 selectDropdwn'>"+
							"<br>"+
							"<div class='select-wrapper select2 form-control boxBorder boxHeight initialized'>"+
							"<span class='caret'>"+"</span>"+
							"<input type='text' class='select-dropdown' readonly='true' data-activates='select-options-1023d34c-eac1-aa22-06a1-e420fcc55868' value='Consignment Status'>"+

							"<select id="+dropdown[i].id+" class='select2 form-control boxBorder boxHeight initialized'>"+
							"<option value='-1'>"+dropdown[i].title+
							"</option>"+
							"</select>"+
							"</div>"+
					"</div>");
			}

			
			
			$("#configTableDiv").append("<div class='col s12 m2 l2'><button class='btn primary botton' type='button' id='submitFilter'></button></div>");
			for(i=0; i<button.length; i++){
				$('#'+button[i].id).text(button[i].buttonTitle);
				$('#'+button[i].id).attr("onclick", button[i].buttonURL);
			
			}
			
			$('.datepicker').datepicker({
				dateFormat: "yy-mm-dd"
				});
		}

	}); 
	
	//status-----------dropdown
	$.getJSON('./getDropdownList/CONFIG_TYPE', function(data) {
		for (i = 0; i < data.length; i++) {
			$('<option>').val(data[i].value).text(data[i].interp)
			.appendTo('#type');
		}
	});
};


$('.datepicker').on('mousedown',function(event){
	event.preventDefault();
});


function viewDetails(tag){
	$("#viewAdminSystemModel").openModal();
	var RequestData = {
			"tag" : tag
	} 
	$.ajax({
		url : "./system/viewTag",
		data :	JSON.stringify(RequestData),
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		type : 'POST',
		success : function(data) {
			console.log(data);
			setViewPopupData(data);
		},
		error : function() {
			alert("Failed");
		}
	});
}

function setViewPopupData(data){
	$("#viewTag").val(data.tag);
	$("#viewValue").val(data.value);
	$("#viewtype").val(data.type);
	$("#description").val(data.description);
	$("#remarks").val(data.remark);
}


function updateDetails(tag){
	$("#editAdminSystemModel").openModal();
	var RequestData = {
			"tag" : tag
	} 
	$.ajax({
		url : "./system/viewTag",
		data :	JSON.stringify(RequestData),
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		type : 'POST',
		success : function(data) {
			console.log(data);
			setEditPopupData(data);
		},
		error : function() {
			alert("Failed");
		}
	});
}


function setEditPopupData(data){
	$("#EditId").val(data.id);
	$("#editTag").val(data.tag);
	$("#editValue").val(data.value);
	$("#edittype").val(data.type);
	$("#editdescription").val(data.description);
	$("#editremarks").val(data.remark);
}

function updateSystem(){
var updateRequest = {
		"id" :  parseInt($("#EditId").val()),
 		"tag" : $("#editTag").val(),
		"description": $("#editdescription").val(),
		"remark": $("#editremarks").val(),
		"value": $("#editValue").val(),
		"type" : parseInt($("#edittype").val())
}


$.ajax({
	url : "./system/update",
	data :	JSON.stringify(updateRequest),
	dataType : 'json',
	contentType : 'application/json; charset=utf-8',
	type : 'PUT',
	success : function(data) {
		console.log("updateRequest---------->" +JSON.stringify(updateRequest));
		confirmModel()
	},
	error : function() {
		alert("Failed");
	}
});

	
}

function confirmModel(){
$("#editAdminSystemModel").closeModal();
setTimeout(function(){$('#confirmedUpdatedSystem').openModal();},200);
}
