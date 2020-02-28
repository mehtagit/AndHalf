var featureId = 15;
var roleType = $("body").attr("data-roleType");
var userId = $("body").attr("data-userID");
var currentRoleType = $("body").attr("data-selected-roleType"); 
var startdate=$('#startDate').val(); 
var endDate=$('#endDate').val();

$(document).ready(function(){
	messageManagementDatatable();
	pageRendering();
	
});

var role = currentRoleType == null ? roleType : currentRoleType;


//**************************************************Message Detail table**********************************************

function messageManagementDatatable(){
	
	var filterRequest={
			"endDate":$('#endDate').val(),
			"startDate":$('#startDate').val(),
			"userId":parseInt(userId),
			"featureId":parseInt(featureId),
			"userTypeId": parseInt($("body").attr("data-userTypeID")),
			"userType":$("body").attr("data-roleType"),
			"tag":$('#parametername').val(),
			"channel" : parseInt($('#channel').val())
	}
	
	$.ajax({
		url: 'headers?type=adminSystemMessage',
		type: 'POST',
		dataType: "json",
		success: function(result){
			/*console.log("Url-------" +url+"--------"+ "dataUrl-------" +dataUrl);*/
			var table=	$("#messageLibraryTable").removeAttr('width').DataTable({
				destroy:true,
				"serverSide": true,
				orderCellsTop : true,
				"ordering" : false,
				"bPaginate" : true,
				"bFilter" : true,
				"bInfo" : true,
				"bSearchable" : true,
				ajax: {
					url : 'adminMessageData',
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
		            { width: 125, targets: 0 },
		            { width: 125, targets: 1 }

		        ]
			});
		},
		error: function (jqXHR, textStatus, errorThrown) {
			console.log("error in ajax");
		}
	});
}



//**************************************************MessageManagement page buttons**********************************************

function pageRendering(){
	$.ajax({
		url: 'messageManagement/pageRendering',
		type: 'POST',
		dataType: "json",
		success: function(data){
			var elem='<p class="PageHeading">'+data.pageTitle+'</p>';		
			$("#pageHeader").append(elem);
			var button=data.buttonList;

			var date=data.inputTypeDateList;
			for(i=0; i<date.length; i++){
				if(date[i].type === "text"){
					$("#messageTableDiv").append("<div class='input-field col s6 m2' style='margin-top: 22px;'><input type="+date[i].type+" id="+date[i].id+"><label for='parametername' class='center-align'>"+date[i].title+"</label></div>");
				}
				
			} 
			
			// dynamic dropdown portion
			var dropdown=data.dropdownList;
			for(i=0; i<dropdown.length; i++){
				var dropdownDiv=
					$("#messageTableDiv").append("<div class='col s6 m2 l2 selectDropdwn'>"+
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
			
			
			$("#messageTableDiv").append("<div class='col s12 m2 l2'><button class='btn primary botton' type='button' id='submitFilter'></button></div>");
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
	$.getJSON('./getDropdownList/CHANNEL', function(data) {
		for (i = 0; i < data.length; i++) {
			$('<option>').val(data[i].value).text(data[i].interp)
			.appendTo('#channel');
		}
	});
};


$('.datepicker').on('mousedown',function(event){
	event.preventDefault();
});



function viewDetails(tag){
	$("#viewMessageModel").openModal();
	var RequestData = {
			"tag" : tag
	} 
	$.ajax({
		url : "./message/viewTag",
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
	$("#description").val(data.description);
	$("viewChannel").val(data.channelInterp);
	
	$("label[for='viewTag']").addClass('active');
	$("label[for='viewValue']").addClass('active');
	$("label[for='description']").addClass('active');
	$("label[for='viewChannel']").addClass('active');
	

}

function updateDetails(tag){
	$("#editMessageModel").openModal();
	
	var RequestData = {
			"tag" : tag
	} 
	$.ajax({
		url : "./message/viewTag",
		data :	JSON.stringify(RequestData),
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		type : 'POST',
		success : function(data) {
			console.log(data);
			setEditData(data);
		},
		error : function() {
			alert("Failed");
		}
	});
}

function setEditData(data){
	$("#Edittag").val(data.tag);
	$("#EditId").val(data.id);
	$("#editValue").val(data.value);
	$("#editdescription").val(data.description);
	$("#editChannel").val(data.channelInterp);
}


function updateMessage(){
	 var updateRequest = {
			 "id" :  parseInt($("#EditId").val()),
		 	 "tag" : $("#Edittag").val(),
			 "value" : $("#editValue").val(),
			 "description" : $("#editdescription").val(),
			 "channel" : parseInt($("#editChannel").val())
	}
	 
	 $.ajax({
			url : "./message/update",
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
	$("#editMessageModel").closeModal();
	setTimeout(function(){$('#confirmedUpdatedMessage').openModal();},200);
}
