
var cierRoletype = sessionStorage.getItem("cierRoletype");
/* 	alert(cierRoletype);	 */

function DeleteConsignmentRecord(txnId){
	$("#DeleteConsignment").openModal();
	$("#transID").text(txnId);
}


function confirmantiondelete(){
	var txnId = $("#transID").text();
	var remarks = $("#textarea1").val();
	var obj ={
			"txnId" : txnId,
			"remarks" : remarks
	}
	$.ajax({
		url : "./deleteConsignment",
		data : JSON.stringify(obj),
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		type : 'POST',
		success : function(data, textStatus, xhr) {
			console.log(data);
			if(data.errorCode == 200){
				$("#consignmentText").text(data.message);
			}else if(data.errorCode == 0){
				$("#consignmentText").text(data.message);
			}
		},
		error : function() {
			console.log("Error");
		}
	});
	$("#DeleteConsignment").closeModal();
	$("#confirmDeleteConsignment").openModal();
}


function EditConsignmentDetails(txnId){ 	
	$.ajax({
		url : "./openRegisterConsignmentPopup?reqType=editPage&txnId="+txnId,
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		type : 'GET',
		success : function(data) {
			console.log(data)
			setEditPopupData(data) 
		},
		error : function() {
			alert("Failed");
		}
	});

	$("#updateModal").openModal();
}


function viewConsignmentDetails(txnId){

	$("#viewModal").openModal();
	$.ajax({
		url : "./openRegisterConsignmentPopup?reqType=editPage&txnId="+txnId,
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		type : 'GET',
		success : function(data) {
			console.log(data)
			setViewPopupData(data);
		},
		error : function() {
			alert("Failed");
		}
	});
}


function setViewPopupData(data){
	console.log("_________________++++++++++"+data.organisationCountry)

	$("#supplierId").val(data.supplierId);
	$("#supplierName").val(data.supplierName);
	$("#consignmentNumber").val(data.consignmentNumber);
	$("#expectedDispatcheDate").val(data.expectedDispatcheDate);
	$("#countryview").val(data.organisationCountry);
	$("#expectedArrivaldate").val(data.expectedArrivaldate);
	$("#expectedArrivalPort").val(data.expectedArrivalPort);
	$("#Quantity").val(data.quantity);
	$("#TransactionId").val(data.txnId);
	$("#remark").val(data.remarks);
	$("#fileName").val(data.fileName); 


}

function setEditPopupData(data){
	console.log()
	$("#supplierIdEdit").val(data.supplierId);
	$("#supplierNameEdit").val(data.supplierName);
	$("#consignmentNumberEdit").val(data.consignmentNumber);
	$("#expectedDispatcheDateEdit").val(data.expectedDispatcheDate);
	$('#country').val(data.organisationCountry);
	$("#expectedArrivaldateEdit").val(data.expectedArrivaldate);
	$("#expectedArrivalPortEdit").val(data.expectedArrivalPort);
	$("#QuantityEdit").val(data.quantity);
	$("#TransactionIdEdit").val(data.txnId);
	$("#fileNameEdit").val(data.fileName); 


} 


var startdate=$('#startDate').val(); 
var endDate=$('#endDate').val();
var taxStatus=$('#taxPaidStatus').val();
var consignmentStatus=$('#filterConsignmentStatus').val();
var userId = $("body").attr("data-userID");

var filterRequest={
		"consignmentStatus":consignmentStatus,
		"endDate":startdate,
		"startDate":endDate,
		"taxPaidStatus":taxStatus,
		"userId":userId,
};

var sourceType =localStorage.getItem("sourceType");
function filterConsignment()
{       	 	
	

	if( startdate !='' || endDate !='' || taxStatus != null || consignmentStatus != null ){
		console.log("startdate="+startdate+" endDate="+endDate+" taxPaidstatus="+taxStatus+" consignmentStatus="+consignmentStatus)
		
	
		if(cierRoletype=="Importer" && sourceType !="viaStolen" ){
			table('../headers?type=consignment','../consignmentData');
		}
		
		else if(cierRoletype=="Custom" && sourceType !="viaStolen"){
			table('../headers?type=customConsignment','../consignmentData');
		}
		
		else if(cierRoletype=="CEIRAdmin"  && sourceType !="viaStolen"){
			table('../headers?type=adminConsignment','../consignmentData');
		}  
		
		else if(cierRoletype=="Importer" && sourceType ==="viaStolen" ){
			table('../headers?type=stolenconsignment','../consignmentData?sourceType=viaStolen');
		}
		localStorage.removeItem('sourceType');
	}
	else{
		console.log("please fill select");
	}
}

//**************************************************filter table**********************************************

function table(url,dataUrl){

	$.ajax({
		url: url,
		type: 'POST',
		dataType: "json",
		success: function(result){
			/*console.log("Url-------" +url+"--------"+ "dataUrl-------" +dataUrl);*/
			var table=	$("#consignmentLibraryTable").DataTable({
				destroy:true,
				"serverSide": true,
				orderCellsTop : true,
				"aaSorting" : [],
				"bPaginate" : true,
				"bFilter" : true,
				"bInfo" : true,
				"bSearchable" : true,
				ajax: {
					url : dataUrl,
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







//******************************************************************************************************************************************************************888888
//******************************************************************************************************************************************************************888888
//******************************************************************************************************************************************************************888888   

function editRegisterConsignment(){
	/*  $("#editRegisterConsignment").submit(); */

	var supplierId=$('#supplierIdEdit').val();
	var supplierName=$('#supplierNameEdit').val();
	var consignmentNumber=$('#consignmentNumberEdit').val();
	var expectedArrivalDate=$('#expectedArrivaldateEdit').val();
	var expectedDispatcheDate=$('#expectedDispatcheDateEdit').val();
	var expectedArrivalPort=$('#expectedArrivalPortEdit').val();
	var organisationcountry=$('#country').val();
	var filename=$('#fileNameEdit').val();
	var txnId=$('#TransactionIdEdit').val();
	var quantity=$('#QuantityEdit').val();
	console.log(supplierName,consignmentNumber,expectedArrivalDate,txnId,filename)


	var formData= new FormData();
	formData.append('file', $('#csvUploadFile')[0].files[0]);
	formData.append('supplierId',supplierId);
	formData.append('supplierName',supplierName);
	formData.append('consignmentNumber',consignmentNumber);
	formData.append('expectedArrivaldate',expectedArrivalDate);
	formData.append('expectedDispatcheDate',expectedDispatcheDate);
	formData.append('expectedArrivalPort',expectedArrivalPort);
	formData.append('organisationcountry',organisationcountry);
	formData.append('quantity',quantity);
	formData.append('txnId',txnId);
	formData.append('filename',filename);

	console.log(JSON.stringify(formData));
	console.log("*********");

	$.ajax({
		url: './updateRegisterConsignment',
		type: 'POST',
		data: formData,
		processData: false,
		contentType: false,
		success: function (data, textStatus, jqXHR) {

			console.log(data);
			$('#updateModal').closeModal();
			$('#updateConsignment').modal();
			if(data.errorCode==200){

				$('#sucessMessage').text('');
				$('#sucessMessage').text('Operation is not allowed');
			}
			else{
				$('#sucessMessage').text('');
				$('#sucessMessage').text('Your update on the form for transaction ID ('+data.txnId+') has been successfully updated.');
			}
			// $('#updateConsignment').modal('open'); 
			//alert("success");

		},
		error: function (jqXHR, textStatus, errorThrown) {
			console.log("error in ajax")
		}
	});

}



function openDeleteModal(transactionId)
{
	/*   $('#deletemodal').modal('open');
        	  backdrop: 'static' */
	$('#deletemodal').openModal();
	console.log("transactionId value="+transactionId);
	$('#deleteTransactionId').val(transactionId);
}




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



$(document).ready(function(){
	$('.datepicker').datepicker();
	filterConsignment();
	pageRendering();

});

$('.datepicker').on('mousedown',function(event){
	event.preventDefault();
});



function closeUpdateModal(){
	$("#DeleteConsignment").closeModal();
	$('#updateModal').closeModal();
	$(".lean-overlay").remove();
}

function closeViewModal()
{
	$('#viewModal').closeModal();
	$(".lean-overlay").remove();

}

populateCountries
(   
		"country"
);

function pageRendering(){
	if(sourceType !="viaStolen" ){
		pageButtons('../consignment/pageRendering');
	
	}else if(sourceType ==="viaStolen" ){
		pageButtons('../consignment/pageRendering?sourceType=viaStolen');
		
	}
	localStorage.removeItem('sourceType');
	
}


function pageButtons(url){
$.ajax({
	url: url,
	type: 'POST',
	dataType: "json",
	success: function(data){
		data.userStatus == "Disable" ? $('#btnLink').addClass( "eventNone" ) : $('#btnLink').removeClass( "eventNone" );


		var elem='<p class="PageHeading">'+data.pageTitle+'</p>';		
		$("#pageHeader").append(elem);
		var button=data.buttonList;

		var date=data.inputTypeDateList;
		for(i=0; i<date.length; i++){
			$("#consignmentTableDIv").append("<div class='col s6 m2 l2 responsiveDiv'>"+
					"<div id='enddatepicker' class='input-group date' data-date-format='yyyy-mm-dd'>"+
					"<label for='TotalPrice'>"+date[i].title
					+"</label>"+"<input class='form-control' type="+date[i].type+" id="+date[i].id+"/>"+
					"<span	class='input-group-addon' style='color: #ff4081'>"+
					"<i	class='fa fa-calendar' aria-hidden='true' style='float: right; margin-top: -37px;'>"+"</i>"+"</span>");
			
		} 

		// dynamic dropdown portion
		var dropdown=data.dropdownList;
		for(i=0; i<dropdown.length; i++){
			var dropdownDiv=
				$("#consignmentTableDIv").append("<div class='col s6 m2 l2 selectDropdwn'>"+
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

		
	if(sourceType=="viaStolen"){
		$("#btnLink").css({display: "none"});
		$("#consignmentTableDIv").append("<div class='col s12 m2 l2'><button class='btn primary botton' id='submitFilter'></button></div>");
		for(i=0; i<button.length; i++){
			$('#'+button[i].id).text(button[i].buttonTitle);
			if(button[i].type === "HeaderButton"){
				$('#'+button[i].id).attr("href", button[i].buttonURL);
			}
			else{
				$('#'+button[i].id).attr("onclick", button[i].buttonURL);
			}
		}
		
		$("#footerBtn").append("<div class='col s12 m2 l2'><button class='btn' id='markedstolen' style='margin-left:38%;margin-top: 8px;'></button><button class='btn' id='cancel' style='margin-left: 22px;margin-top: 8px;'></button></div>");
		for(i=0; i<button.length; i++){
			$('#'+button[i].id).text(button[i].buttonTitle);
			if(button[i].type === "FooterButton"){
				$('#'+button[i].id).attr("onclick", button[i].buttonURL);
			}
			else{
				$('#'+button[i].id).attr("href", button[i].buttonURL);
				
			}
		}		
	
	}else{
		$("#consignmentTableDIv").append("<div class='col s12 m2 l2'><button class='btn primary botton' id='submitFilter'></button></div>");
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
	
		
		cierRoletype=="Importer"? $("#btnLink").css({display: "block"}) : $("#btnLink").css({display: "none"});
		/*sourceType=="viaStolen" ? $("#btnLink").css({display: "none"}) : $("#btnLink").css({display: "none"});*/
		
		

		
		
		$.getJSON('../getDropdownList/3/4', function(data) {
			for (i = 0; i < data.length; i++) {
				$('<option>').val(data[i].state).text(data[i].interp)
				.appendTo('#filterConsignmentStatus');

			}
		});



		//Tax paid status-----------dropdown
		$.getJSON('../getDropdownList/CUSTOMS_TAX_STATUS', function(data) {
			for (i = 0; i < data.length; i++) {
				$('<option>').val(data[i].state).text(data[i].interp)
				.appendTo('#taxPaidStatus');
			}
		});
	}
//$("#filterBtnDiv").append();
}); 
};






function openApprovePopUp(txnId)
{
	console.log("open approve pop  up."+txnId);
	$('#ApproveConsignment').openModal();
	$('#ApproveConsignmentTxnid').text(txnId);
	$('#setApproveConsignmentTxnId').val(txnId);



}
function approveSubmit(actiontype){
	var txnId=$('#setApproveConsignmentTxnId').val();
	console.log("txnId==="+txnId)
	var approveRequest={
		"action": actiontype,
		"txnId":txnId
	}
	console.log(JSON.stringify(approveRequest));
	$.ajax({
		url : "./updateConsignmentStatus",
		data : JSON.stringify(approveRequest),
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		type : 'POST',
		success : function(data) {
			console.log(data)
			$('#confirmApproveConsignment').openModal();
			if(data.errorCode==0){

				$('#approveSuccessMessage').text('');
				$('#approveSuccessMessage').text(data.message);
			}
			else{
				$('#approveSuccessMessage').text('');
				$('#approveSuccessMessage').text(data.message);
			}
		},
		error : function() {
			alert("Failed");
		}
	});
}

function openDisapprovePopup(txnId)
{
	console.log("open approve pop  up."+txnId);
	$('#RejectConsignment').openModal();
	$('#disaproveTxnId').text(txnId);
	$('#setDisapproveConsignmentTxnId').val(txnId);



}

function disapproveSubmit(actiontype){
	var txnId=$('#setDisapproveConsignmentTxnId').val();
	var Remark=$('#dispproveRemarks').val();

	console.log("txnId==="+txnId+"  Remark  "+Remark)
	var approveRequest={
		"action": actiontype,
		"txnId":txnId,
		"remarks":Remark
	}
	console.log(JSON.stringify(approveRequest));
	$.ajax({
		url : "./updateConsignmentStatus",
		data : JSON.stringify(approveRequest),
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		type : 'POST',
		success : function(data) {
			console.log(data)
			setTimeout(function(){ $('#confirmRejectConsignment').openModal()}, 200);
			
			if(data.errorCode==0){

				$('#disapproveSuccessMessage').text('');
				$('#disapproveSuccessMessage').text(data.message);
			}
			else{
				$('#disapproveSuccessMessage').text('');
				$('#disapproveSuccessMessage').text(data.message);
			}
		},
		error : function() {
			alert("Failed");
		}
	});
}	


function valuesPush(){
	   var selectedMultipleConsignment=[];
	   var roleType = $("body").attr("data-roleType");
	   var currentRoleType = $("body").attr("data-stolenselected-roleType"); 
	   var role = currentRoleType == null ? roleType : currentRoleType;
	   var requestType="stolen";
	   console.log("role++++"+role+"requestType++"+requestType);
	   $('#consignmentLibraryTable tr td input:checkbox:checked').each(function() {
	  
		   var json={"txnId":$(this).closest('tr').find('td:eq(2)').text(),
			   	"userId":userId,
			   	"sourceType":'consignment',
			   	"roleType":role,
			   	"requestType":requestType
			   	};
	   
		   selectedMultipleConsignment.push(json);
	   });
	   console.log(selectedMultipleConsignment)
	   return selectedMultipleConsignment;
	   }



function markedstolen(){
	$('#markAsMultipleStolen').openModal();
	 
	}

function openMulipleStolenPopUp()
{
	   
	   var stolenRecoverydata=JSON.stringify(valuesPush());
	   console.log("release-------"+stolenRecoverydata);
	   $.ajax({
				url: '../multipleStolenRecovery',
				type: 'POST',
				data: stolenRecoverydata,
				dataType : 'json',
			contentType : 'application/json; charset=utf-8',
			success: function (data, textStatus, jqXHR) {
					
					 console.log(data);
					 $('#markAsStolenDone').openModal();
					/*  $('#editStockModal').closeModal();
					 $('#successUpdateStockModal').modal();
					  if(data.errorCode==200){
					
					$('#stockSucessMessage').text('');
					 $('#stockSucessMessage').text('Operation is not allowed');
						 }
					 else{
						 $('#stockSucessMessage').text('');
		 				 $('#stockSucessMessage').text('Your update on the form for transaction ID ('+data.txnId+') has been successfully updated.');
					 } */
				   // $('#updateConsignment').modal('open'); 
					//alert("success");
					
				},
				error: function (jqXHR, textStatus, errorThrown) {
				console.log("error in ajax")
				}
			});
	   
}
function redirectToViewPage()
{

	 var roleType = $("body").attr("data-roleType");
	 var userId = $("body").attr("data-userID");
	 var currentRoleType = $("body").attr("data-stolenselected-roleType"); 
	 var role = currentRoleType == null ? roleType : currentRoleType;
	 console.log(" userId="+userId+" role="+role);
	console.log("./stolenRecovery?userTypeId="+role);
	 window.location.href = "../stolenRecovery?userTypeId="+role;
	 /*  $.ajax({
	url : "./assignDistributor?userTypeId="+role,
	dataType : 'json',
	contentType : 'application/json; charset=utf-8',
	type : 'GET',
	success : function(data) {
		console.log(data)
		
	},
	error : function() {
		alert("Failed");
	}
}); */

}