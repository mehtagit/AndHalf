
var cierRoletype = sessionStorage.getItem("cierRoletype");
$(document).ready(function(){
	$('.datepicker').datepicker();
	registrationDatatable();
	pageRendering();
});


var roleType = $("body").attr("data-roleType");
var userId = $("body").attr("data-userID");
var currentRoleType = $("body").attr("data-selected-roleType"); 


var role = currentRoleType == null ? roleType : currentRoleType;

var startdate=$('#startDate').val(); 
var endDate=$('#endDate').val();
var taxStatus=$('#taxPaidStatus').val();
var consignmentStatus=$('#filterConsignmentStatus').val();
var userId = $("body").attr("data-userID");
var featureId = 8;

var filterRequest={
		"featureId" : featureId,
		"endDate":endDate,
		"startDate":startdate,
		"taxPaidStatus":taxStatus,
		"userId":userId,
		"roleType": role
};



//**************************************************Registration table**********************************************

function registrationDatatable(){

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
						"<div id='enddatepicker' class='input-group date' data-date-format='yyyy-mm-dd'>"+
						"<label for='TotalPrice'>"+date[i].title
						+"</label>"+"<input class='form-control' type="+date[i].type+" id="+date[i].id+"/>"+
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
							"<option>"+dropdown[i].title+
							"</option>"+
							"</select>"+
							"</div>"+
					"</div>");
			}
			
			$("#registrationTableDiv").append("<div class='col s12 m2 l2'><button class='btn primary botton' id='submitFilter'></button></div>");
			for(i=0; i<button.length; i++){
				$('#'+button[i].id).text(button[i].buttonTitle);
				$('#'+button[i].id).attr("onclick", button[i].buttonURL);
			
			}
		
			cierRoletype=="CEIRAdmin"? $("#btnLink").css({display: "none"}) : $("#btnLink").css({display: "block"});
			/*sourceType=="viaStolen" ? $("#btnLink").css({display: "none"}) : $("#btnLink").css({display: "none"});*/
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



function userApprovalPopup(userId,registrationDate){
	$('#approveInformation').openModal();
	$("#userId").text(userId)
}

function confirmApproveInformation(){
	$('#approveInformation').closeModal();
	$('#confirmApproveInformation').openModal();
	$("#registrationDate").text(registrationDate);
}


function aprroveUser(userId){
	var userid= $("#userId").text();
	var approveRequest={
			"userid": parseInt(userid),
			"status" : "Approved",
			"remark": $("#Reason").val()	
	}
	
	$.ajax({
		url : './adminApproval',
		data : JSON.stringify(approveRequest),
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		type : 'POST',
		success : function(data) {
			console.log("approveRequest----->"+JSON.stringify(approveRequest));
			confirmApproveInformation();
			
		},
		error : function() {
			alert("Failed");
		}
	});
	
	
}

function userRejectPopup(userId,registrationDate){
	$('#rejectInformation').openModal();
	console.log("Reject userId is---->"+userId+"------registrationDate----------->"+registrationDate);
	$("#userId").text(userId)
}

function confirmRejectInformation(){
	$('#rejectInformation').closeModal();
	$('#confirmRejectInformation').openModal();
	$("#registrationDate").text(registrationDate);

}

function rejectUser(userId){
	var userid= $("#userId").text();
	var rejectRequest={
			"userid": parseInt(userid),
			"status" : "Approved",
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


