var cierRoletype = sessionStorage.getItem("cierRoletype");
var featureId = 8;
var roleType = $("body").attr("data-roleType");
var userId = $("body").attr("data-userID");
var currentRoleType = $("body").attr("data-selected-roleType"); 
var startdate=$('#startDate').val(); 
var endDate=$('#endDate').val();

$(document).ready(function(){
	registrationDatatable();
	pageRendering();
	
});

var role = currentRoleType == null ? roleType : currentRoleType;


//**************************************************Registration table**********************************************

function registrationDatatable(){
	var asType = $('#asType').val();
	var userRoleTypeId = $("#role").val();
	var status =  $('#recentStatus').val();
	
	var filterRequest={
			"endDate":$('#endDate').val(),
			"startDate":$('#startDate').val(),
			"asType": parseInt(asType),
			"userRoleTypeId" : parseInt(userRoleTypeId),
			"status" : parseInt(status),
			"userId":parseInt(userId),
			"featureId":parseInt(featureId),
			"userTypeId": parseInt($("body").attr("data-userTypeID")),
			"userType":$("body").attr("data-roleType"),
			
	}
	
	$.ajax({
		url: 'headers?type=adminRegistration',
		type: 'POST',
		dataType: "json",
		success: function(result){
			/*console.log("Url-------" +url+"--------"+ "dataUrl-------" +dataUrl);*/
			var table=	$("#registrationLibraryTable").DataTable({
				destroy:true,
				"serverSide": true,
				orderCellsTop : true,
				"ordering" : false,
				"bPaginate" : true,
				"bFilter" : true,
				"bInfo" : true,
				"bSearchable" : true,
				ajax: {
					url : 'registrationData',
					type: 'POST',
					dataType: "json",
					data : function(d) {
						d.filter = JSON.stringify(filterRequest); 
						console.log(JSON.stringify(filterRequest));
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



//**************************************************Registration page buttons**********************************************

function pageRendering(){
	$.ajax({
		url: 'registration/pageRendering',
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
				$("#registrationTableDiv").append("<div class='col s6 m2 l2 responsiveDiv'>"+
						"<div id='enddatepicker' class='input-group date'>"+
						"<label for='TotalPrice'>"+date[i].title
						+"</label>"+"<input class='form-control datepicker' type='text' id="+date[i].id+" autocomplete='off'>"+
						"<span	class='input-group-addon' style='color: #ff4081'>"+
						"<i	class='fa fa-calendar' aria-hidden='true' style='float: right; margin-top: -37px;'>"+"</i>"+"</span>");
				}
				else if(date[i].type === "select"){
					$("#registrationTableDiv").append("<div class='input-field col s6 m2' style='margin-top: 22px;'><input type="+date[i].type+" id="+date[i].id+" maxlength='15' /><label for='TransactionID' class='center-align'>"+date[i].title+"</label></div>");
					
				}
				
			} 

			// dynamic dropdown portion
			var dropdown=data.dropdownList;
			for(i=0; i<dropdown.length; i++){
				var dropdownDiv=
					$("#registrationTableDiv").append("<div class='col s6 m2 l2 selectDropdwn'>"+
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
			
			$("#registrationTableDiv").append("<div class='col s12 m2 l2'><button class='btn primary botton' type='button' id='submitFilter'></button></div>");
			$("#registrationTableDiv").append("<div class='col s12 m2'><a onclick='exportButton()' type='button' class='export-to-excel right'>Export <i class='fa fa-file-excel-o' aria-hidden='true'></i></a></div>");
			for(i=0; i<button.length; i++){
				$('#'+button[i].id).text(button[i].buttonTitle);
				$('#'+button[i].id).attr("onclick", button[i].buttonURL);
			
			}
			
			$('.datepicker').datepicker({
				dateFormat: "yy-mm-dd"
				});
		
			cierRoletype=="CEIRAdmin"? $("#btnLink").css({display: "none"}) : $("#btnLink").css({display: "block"});
			/*sourceType=="viaStolen" ? $("#btnLink").css({display: "none"}) : $("#btnLink").css({display: "none"});*/
		
			
		}

	}); 
	
	$.getJSON('./getDropdownList/'+featureId+'/'+$("body").attr("data-userTypeID"), function(data) {
		for (i = 0; i < data.length; i++) {
			$('<option>').val(data[i].state).text(data[i].interp)
			.appendTo('#recentStatus');
		}
	});
	

	$.getJSON('./registrationUserType', function(data) {
		for (i = 0; i < data.length; i++) {
			$('<option>').val(data[i].id).text(data[i].usertypeName)
			.appendTo('#role');
		}
	});
	
	
	$.getJSON('./getTypeDropdownList/AS_TYPE/'+$("body").attr("data-userTypeID"), function(data) {
		for (i = 0; i < data.length; i++) {
			$('<option>').val(data[i].value).text(data[i].interp)
			.appendTo('#asType');
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



function userApprovalPopup(userId,date){
	$('#approveInformation').openModal();
	$("#userId").text(userId);
	window.userID=userId;
	window.date=date.replace("="," ");
}




function aprroveUser(){
	var userid= $("#userId").text();
	var approveRequest={
			"userId": parseInt(userid),
			"status" : "Approved",
			"remark": $("#Reason").val()	
	}
	
	$.ajax({
		url : './adminApproval',
		data : JSON.stringify(approveRequest),
		dataType : 'json',
		'async' : false,
		contentType : 'application/json; charset=utf-8',
		type : 'POST',
		success : function(data) {
			console.log("approveRequest----->"+JSON.stringify(approveRequest));
			confirmApproveInformation(window.userID,window.date);
		},
		error : function() {
			alert("Failed");
		}
	});
}

function confirmApproveInformation(userID,date){
	$('#approveInformation').closeModal(); 
	setTimeout(function(){ $('#confirmApproveInformation').openModal();}, 200);
	$("#registrationDate").text(date);
	$("#RegistrationId").text(userID);
}

function userRejectPopup(userId){
	$('#rejectInformation').openModal();
	console.log("Reject userId is---->"+userId);
	$("#userId").text(userId)
}


function rejectUser(userId){
	var userid= $("#userId").text();
	var rejectRequest={
			"userId": parseInt(userid),
			"status" : "Rejected",
			"remark": $("#Reason").val()	
	}
	
	$.ajax({
		url : './adminApproval',
		data : JSON.stringify(rejectRequest),
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		type : 'POST',
		success : function(data) {
			console.log("rejectRequest----->"+JSON.stringify(rejectRequest));
			confirmRejectInformation();
		},
		error : function() {
			alert("Failed");
		}
	});
	
	
}

function confirmRejectInformation(){
	$('#rejectInformation').closeModal();
	$('#confirmRejectInformation').openModal();
}

function exportButton(){
	var startdate=$('#startDate').val(); 
	var endDate=$('#endDate').val();
	var asType =  $('#asType').val();
	var userRoleTypeId =  $("#role").val();
	var status =  $('#recentStatus').val();
	
	var table = $('#registrationLibraryTable').DataTable();
	var info = table.page.info(); 
    var pageNo=info.page;
    var pageSize =info.length;
	window.location.href="./exportAdminRegistration?RegistrationStartDate="+startdate+"&RegistrationEndDate="+endDate+"&asType="+asType+"&userRoleTypeId="+userRoleTypeId+"&status="+status+"&pageSize="+pageSize+"&pageNo="+pageNo;
}


function previewFile(srcFilePath,srcFileName){
	window.filePath = srcFilePath;
	window.fileName = srcFileName;
	window.fileExtension = fileName.replace(/^.*\./, '');
	window.FinalLink = filePath.concat(fileName);
	
	if(filePath == null || filePath == "" || filePath == undefined && fileName == null || fileName == "" || fileName == undefined ){
		console.log("File is not Avialable")
	}else if(fileExtension=="jpg" || fileExtension=="jpeg" || fileExtension=="png" || fileExtension=="gif" ){
		$("#viewuplodedModel").openModal();
		$("#fileSource").attr("src",FinalLink);
	}else{
		 window.open(FinalLink);
	}
}


