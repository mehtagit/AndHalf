var featureId = 8;
var cierRoletype = sessionStorage.getItem("cierRoletype");
$(document).ready(function(){
	$('.datepicker').datepicker();
	grievanceDataTable();
	pageRendering();
});


var startdate=$('#startDate').val(); 
var endDate=$('#endDate').val();
var taxStatus=$('#taxPaidStatus').val();
var consignmentStatus=$('#filterConsignmentStatus').val();
var userId = $("body").attr("data-userID");





//**************************************************Grievance table**********************************************

function grievanceDataTable(){
	var filterRequest={
			"grievanceStatus": -1,
			"endDate":$('#endDate').val(),
			"startDate":$('#startDate').val(),
			"recentStatus":parseInt($('#recentStatus').val()),
			"userId":parseInt(userId),
			"featureId":parseInt(featureId),
			"userTypeId": parseInt($("body").attr("data-userTypeID")),
			"txnId":$('#transactionID').val(),
			"grievanceID":$('#grievanceID').val(),
			"userType":$("body").attr("data-roleType")
	}
	$.ajax({
		url: 'headers?type=grievanceHeaders',
		type: 'POST',
		dataType: "json",
		success: function(result){
			/*console.log("Url-------" +url+"--------"+ "dataUrl-------" +dataUrl);*/
			var table=	$("#grivanceLibraryTable").DataTable({
				destroy:true,
				"serverSide": true,
				orderCellsTop : true,
				"ordering" : false,
				"bPaginate" : true,
				"bFilter" : true,
				"bInfo" : true,
				"bSearchable" : true,
				ajax: {
					url : 'grievanceData',
					type: 'POST',
					dataType: "json",
					data : function(d) {
						d.filter = JSON.stringify(filterRequest); 
						console.log(JSON.stringify(filterRequest));
					}

				},
				"columns": result
			});
			
			$('#grivanceLibraryTable input').unbind();
		    $('#grivanceLibraryTable input').bind('keyup', function (e) {
		        if (e.keyCode == 13) {
		            table.search(this.value).draw();
		        }
		    });
		},
		error: function (jqXHR, textStatus, errorThrown) {
			console.log("error in ajax");
		}
	});
}



//**************************************************Grievance page buttons**********************************************

function pageRendering(){
	$.ajax({
		url: 'grievance/pageRendering',
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
					$("#greivanceTableDiv").append("<div class='col s6 m2 l2 responsiveDiv'>"+
							"<div id='enddatepicker' class='input-group date' data-date-format='yyyy-mm-dd'>"+
							"<label for='TotalPrice'>"+date[i].title
							+"</label>"+"<input class='form-control' type="+date[i].type+" id="+date[i].id+">"+
							"<span	class='input-group-addon' style='color: #ff4081'>"+
							"<i	class='fa fa-calendar' aria-hidden='true' style='float: right; margin-top: -37px;'>"+"</i>"+"</span>");
				}
				else if(date[i].type === "text"){
					$("#greivanceTableDiv").append("<div class='input-field col s6 m2 filterfield' style='margin-top: 22px;'><input type="+date[i].type+" id="+date[i].id+" maxlength='19' /><label for='TransactionID' class='center-align'>"+date[i].title+"</label></div>");

				}



			} 

			// dynamic dropdown portion
			var dropdown=data.dropdownList;
			for(i=0; i<dropdown.length; i++){
				var dropdownDiv=
					$("#greivanceTableDiv").append("<div class='col s6 m2 l2 selectDropdwn'>"+
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

			$("#greivanceTableDiv").append("<div class='col s12 m2 l2'><input type='button' class='btn primary botton' id='submitFilter' value='filter'></div>");
			for(i=0; i<button.length; i++){
				$('#'+button[i].id).text(button[i].buttonTitle);
				if(button[i].type === "HeaderButton"){
					$('#'+button[i].id).attr("href", button[i].buttonURL);
				}
				else{
					$('#'+button[i].id).attr("onclick", button[i].buttonURL);
				}
			}
			
			if(cierRoletype=="CEIRAdmin"){
				$("#btnLink").css({display: "none"});
				}
			//cierRoletype=="Importer"? $("#btnLink").css({display: "block"}) : $("#btnLink").css({display: "none"});
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





function saveGrievance(){


	var category=$('#category').val();
	var txnId=$('#TransactionId').val();
	var remark=$('#Remark').val();
	var file=$('#myInput').val();

	console.log("category="+category+" txnId="+txnId+" remark="+remark+" file="+file)
	var formData= new FormData();
	formData.append('file', $('#myInput')[0].files[0]);
	formData.append('txnId',txnId);
	formData.append('categoryId',category);
	formData.append('remarks',remark);

	$.ajax({
		url: './saveGrievance',
		type: 'POST',
		data: formData,
		processData: false,
		contentType: false,
		success: function (data, textStatus, jqXHR) {

			console.log(data);
			$('#submitGrievance').openModal();
			if(data.errorCode=="0")
			{
				$('#greivanceId').text(data.txnId);

			}
			else if(data.errorCode=="3")
			{
				console.log("status code = 3"); 
				$('#sucessMessage').text('');
				$('#sucessMessage').text("Grievnace number already exist");
				$('#errorCode').val(data.errorCode);
			}
			// $('#updateConsignment').modal('open'); 
			//alert("success");

		},
		error: function (jqXHR, textStatus, errorThrown) {
			console.log("error in ajax")
		}
	});

	return false;

}

function grievanceReply(userId,grievanceId,txnId)
{


	$.ajax({
		url: './viewGrievance?recordLimit=2&grievanceId='+grievanceId,
		type: 'GET',
		processData: false,
		contentType: false,
		success: function (data, textStatus, jqXHR) {

			console.log(JSON.stringify(data));
			$('#replyModal').openModal();
			$('#grievanceIdToSave').text(grievanceId);
			$('#grievanceTxnId').text(txnId);
			var usertype = $("body").attr("data-roleType");
			console.log("usertype=="+usertype);
			$("#viewPreviousMessage").empty();
			for(var i=0; i<data.length; ++i)
			{
				
				$("#viewPreviousMessage").append("<div class='chat-message-content clearfix'><h6 style='float: left; font-weight: bold;' id='mesageUserType'>" +data[i].userDisplayName+" : </h6><span style='float:right;'>" + data[i].modifiedOn + "</span><h6>" + data[i].reply + "</h6></div>");
				
				
			}
			if(usertype=='CEIRAdmin')
			{
				$("#closeTicketCheckbox").css("display","block");
				console.log("block");
			}
			else{
				$("#closeTicketCheckbox").css("display","none");	
				console.log("none");
			}


		},
		error: function (jqXHR, textStatus, errorThrown) {
			console.log("error in ajax")
		}
	});
}

function saveGrievanceReply()
{
	var grievanceTicketStatus;
	if ($('#closeTicketCheck').is(":checked"))
	{
		grievanceTicketStatus=3;
		
	}
	else{
		grievanceTicketStatus=0;
	}
	var remark=$('#replyRemark').val();
	var replyFile=$('#replyFile').val();
	var  grievanceIdToSave= $('#grievanceIdToSave').text();
	var  grievanceTxnId=  $('#grievanceTxnId').text();

	console.log("remark "+remark+"  replyFile="+replyFile+" grievanceTxnId="+grievanceTxnId+"grievanceIdToSave="+grievanceIdToSave+"grievanceTicketStatus=="
			+grievanceTicketStatus);
	var formData= new FormData();
	formData.append('file', $('#replyFile')[0].files[0]);
	formData.append('remark',remark);
	formData.append('grievanceId',grievanceIdToSave);
	formData.append('txnId',grievanceTxnId);
	formData.append('grievanceStatus',grievanceTicketStatus);

	$.ajax({
		url: './saveGrievanceMessage',
		type: 'POST',
		data: formData,
		processData: false,
		contentType: false,
		success: function (data, textStatus, jqXHR) {

			console.log(data);
			$('#replyMsg').openModal();
			if(data.errorCode=="0")
			{
				$('#showReplyResponse').text('');
				$('#showReplyResponse').text(data.message);

			}
			else if(data.errorCode=="3")
			{
				console.log("status code = 3"); 
				$('#showReplyResponse').text('');
				$('#showReplyResponse').text(data.message);
			}
			// $('#updateConsignment').modal('open'); 
			//alert("success");

		},
		error: function (jqXHR, textStatus, errorThrown) {
			console.log("error in ajax")
		}
	});
}

function viewGrievanceHistory(grievanceId)
{

	$.ajax({
		url: './viewGrievance?recordLimit=-1&grievanceId='+grievanceId,
		type: 'GET',
		processData: false,
		contentType: false,
		success: function (data, textStatus, jqXHR) {

			console.log(JSON.stringify(data));
			$('#chatMsg').empty();
			$('#manageAccount').openModal();
			for(var i=0; i<data.length; ++i)
			{

				$("#chatMsg").append("<div class='chat-message-content clearfix'><span class='chat-time' id='timeHistory'>"+data[i].modifiedOn+"</span><h5 id='userTypehistory'>"+data[i].userDisplayName+"</h5><p id='messageHistory'>"+data[i].reply+"</p><hr></div>");


			}
		},
		error: function (jqXHR, textStatus, errorThrown) {
			console.log("error in ajax")
		}
	});
}






//**********************************************************Export Excel file************************************************************************
function exportData()
{
	var grievanceStartDate=$('#startDate').val();
	var grievanceEndDate=$('#endDate').val();
	var grievancetxnId=$('#transactionID').val();
	var grievanceId=$('#grievanceID').val();
	var grievanceStatus=$('#recentStatus').val();

	var table = $('#grivanceLibraryTable').DataTable();
	var info = table.page.info(); 
   var pageNo=info.page;
    var pageSize =info.length;
	console.log("--------"+pageSize+"---------"+pageNo);
	console.log(" grievanceStartDate  ="+grievanceStartDate+"  grievanceEndDate=="+grievanceEndDate+"  grievancetxnId="+grievancetxnId+" grievanceId ="+grievanceId+"grievanceStatus  "+grievanceStatus)
	window.location.href="./exportGrievance?grievanceStartDate="+grievanceStartDate+"&grievanceEndDate="+grievanceEndDate+"&grievancetxnId="+grievancetxnId+"&grievanceId="+grievanceId+"&grievanceStatus="+grievanceStatus+"&pageSize="+pageSize+"&pageNo="+pageNo;
}



