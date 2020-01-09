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
			"tag":$('#parametername').val()
			
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




function myFunction(message) {
	var x = document.getElementById("snackbar");
	x.className = "show";
	$('#errorMessage').html(message);
	setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
}

function dispatchDateValidation(){
	var currentDate;
	var dispatcDate=  $('#expectedDispatcheDate').val();
	var now=new Date();
	if(now.getDate().toString().charAt(0) != '0'){
		currentDate='0'+now.getDate();

		/* alert("only date="+currentDate); */
	}
	else{
		currentDate=now.getDate();
	}
	var today = now.getFullYear()+ '-' + (now.getMonth()+1)+ '-' +currentDate ;
	//alert("today"+today);
	console.log("dispatche="+dispatcDate);
	console.log("todays parse date"+Date.parse(today));
	console.log("dispatche parse date"+Date.parse(dispatcDate));


	if(Date.parse(today)>Date.parse(dispatcDate))
	{
		myFunction("dispatche date should be greater then or equals to today");
		$('#expectedDispatcheDate').val("");
	}

	//alert("current date="+today+" dispatche date="+dispatcDate)
}

function arrivalDateValidation(){
	var currentDate;
	var dispatcDate=  $('#expectedArrivalDate').val();
	var now=new Date();
	if(now.getDate().toString().charAt(0) != '0'){
		currentDate='0'+now.getDate();

		/* alert("only date="+currentDate); */
	}
	else{
		currentDate=now.getDate();
	}
	var today = now.getFullYear()+ '-' + (now.getMonth()+1)+ '-' +currentDate ;
	//alert("today"+today);
	console.log("dispatche="+dispatcDate);
	console.log("todays parse date"+Date.parse(today));
	console.log("dispatche parse date"+Date.parse(dispatcDate));


	if(Date.parse(today)>Date.parse(dispatcDate))
	{
		myFunction("Arrival date should be greater then or equals to today");
		$('#expectedArrivalDate').val("");
	}
	//alert("current date="+today+" dispatche date="+dispatcDate)
}

$('.datepicker').on('mousedown',function(event){
	event.preventDefault();
});










function exportButton(){
	var startdate=$('#startDate').val(); 
	var endDate=$('#endDate').val();
	var asType =  $('#asType').val();
	var userRoleTypeId =  $("#role").val();
	var status =  $('#recentStatus').val();
	
	var table = $('#messageLibraryTable').DataTable();
	var info = table.page.info(); 
    var pageNo=info.page;
    var pageSize =info.length;
	console.log("--------"+pageSize+"---------"+pageNo);
	console.log("RegistrationS----------------------tartDate  ="+startdate+"  RegistrationEndDate=="+endDate+"  asType="+asType+" userRoleTypeId ="+userRoleTypeId+"status  "+status)
	window.location.href="./exportAdminRegistration?RegistrationStartDate="+startdate+"&RegistrationEndDate="+endDate+"&asType="+asType+"&userRoleTypeId="+userRoleTypeId+"&status="+status+"&pageSize="+pageSize+"&pageNo="+pageNo;
}

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

}

function updateDetails(){
	$("#editMessageModel").openModal();
}