var roleType = $("body").attr("data-roleType");
var userId = $("body").attr("data-userID");
var currentRoleType = $("body").attr("data-stolenselected-roleType");  
var role = currentRoleType == null ? roleType : currentRoleType;

var userType = $("body").attr("data-roleType");
if(userType == "Operator" || userType == "CEIRAdmin" ){
	var featureId="7";
}else{
	var featureId="5"; //this check is for stolen & recovery
}


$(document).ready(function(){
	$('div#initialloader').fadeIn('fast');
	filterStolen();
	pageRendering();
});



function DeleteConsignmentRecord(txnId,id){
	$("#DeleteConsignment").openModal();
	$("#transID").text(txnId);
	$("#setStolenRecoveyRowId").text(id);
}


function confirmantiondelete(){
	var txnId = $("#transID").text();
	var id= $("#setStolenRecoveyRowId").text();
	var roleType = $("body").attr("data-roleType");
	var userId = $("body").attr("data-userID");
	var currentRoleType = $("body").attr("data-stolenselected-roleType"); 
	var role = currentRoleType == null ? roleType : currentRoleType;
	console.log("txnId===**"+txnId+" userId="+userId+" roleType== "+roleType+ " currentRoleType=="+currentRoleType);
	var obj ={
			"txnId" : txnId,
			"roleType":role,
			"userId":userId,
			"id":id

	}
	$.ajax({
		url : "./stolenRecoveryDelete",
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


	$("#fileStolenModal").openModal();
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



//******************************************************************************************************************************************************************888888
//******************************************      ************************************************************************************************************************888888
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
		url: './Consignment/updateRegisterConsignment',
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



var userType = $("body").attr("data-roleType");
var sourceType = localStorage.getItem("sourceType");
function filterStolen(){
	if(userType=="Operator"){
		Datatable('./headers?type=blockUnblock','stolenData')
	}else if(userType =="CEIRAdmin"){
		Datatable('./headers?type=BlockUnblockCEIRAdmin','stolenData')
	}else if(sourceType !="viaExistingRecovery"){
		Datatable('./headers?type=stolen','stolenData')
	}else if(sourceType =="viaExistingRecovery" ){
		Datatable('./headers?type=stolenCheckHeaders', 'stolenData?sourceType=viaExistingRecovery')
	}
	localStorage.removeItem('sourceType');
}  


function Datatable(url,dataUrl){
	var filterRequest={
			"endDate":$('#endDate').val(),
			"startDate":$('#startDate').val(),
			"txnId":$('#transactionID').val(),
			//"consignmentStatus":parseInt($('#status').val()),
			"requestType":parseInt($('#requestType').val()),
			"sourceType":parseInt($('#sourceStatus').val()),
			"fileStatus" : parseInt($('#status').val()),
			//"roleType": role,
			"userId": userId,
			"featureId":featureId,
			"userTypeId": parseInt($("body").attr("data-userTypeID")),
			"userType":$("body").attr("data-roleType"),
	}

	$.ajax({
		url: url,
		type: 'POST',
		dataType: "json",
		success: function(result){
			var table=	$("#stolenLibraryTable").DataTable({
				bAutoWidth: false,
				destroy:true,
				"serverSide": true,
				orderCellsTop : true,
				"ordering": false,
				"bPaginate" : true,
				"bFilter" : true,
				"bInfo" : true,
				"bSearchable" : true,
				scrollCollapse: true,	
				ajax: {
					url: dataUrl,
					type: 'POST',
					data : function(d) {
						d.filter =JSON.stringify(filterRequest); 
						console.log(JSON.stringify(filterRequest));
					}
				},
				"columns": result,
				fixedColumns: true,
				columnDefs: [
		            { width: 240, targets: result.length - 1 }
		        ]
			});
			$('div#initialloader').delay(300).fadeOut('slow');
		}
	}); 
}				

function pageRendering(){
	console.log("sourceType in render check" +sourceType);
	if(sourceType !="viaExistingRecovery" ){
		pageElements('./stolen/pageRendering');

	}else if(sourceType ==="viaExistingRecovery" ){
		pageElements('./stolen/pageRendering?sourceType=viaExistingRecovery');
	}
	localStorage.removeItem('sourceType');

}



function pageElements(url){
	$.ajax({
		url: url,
		type: 'POST',
		dataType: "json",
		success: function(data){
			var elem='<p class="PageHeading">'+data.pageTitle+'</p>';
			$("#pageHeader").append(elem);
			var button=data.buttonList;

			var date=data.inputTypeDateList;
			for(i=0; i<date.length; i++){
				if(date[i].type === "date"){	
					$("#consignmentTableDIv").append("<div class='col s6 m2 l2 responsiveDiv'>"+
							"<div id='enddatepicker' class='input-group date'>"+
							"<label for='TotalPrice'>"+date[i].title
							+"</label>"+"<input class='form-control datepicker' type='text' id="+date[i].id+" autocomplete='off'>"+
							"<span	class='input-group-addon' style='color: #ff4081'>"+
							"<i	class='fa fa-calendar' aria-hidden='true' style='float: right; margin-top: -37px;'>"+"</i>"+"</span>");
				}else if(date[i].type === "text"){
					$("#consignmentTableDIv").append("<div class='input-field col s6 m2 filterfield' style='margin-top: 22px;'><input type="+date[i].type+" id="+date[i].id+" maxlength='19' /><label for='TransactionID' class='center-align'>"+date[i].title+"</label></div>");
				}
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
			if(sourceType=="viaExistingRecovery"){
				$("#btnLink").css({display: "none"});
				$("#consignmentTableDIv").append("<div class='col s12 m2 l2'><input type='button' class='btn primary botton' value='filter' id='submitFilter' /></div>");
				$("#consignmentTableDIv").append("<div class='col s12 m4'><a href='JavaScript:void(0);' type='button' class='export-to-excel right'>Export <i class='fa fa-file-excel-o' aria-hidden='true'></i></a></div>");
				for(i=0; i<button.length; i++){
					$('#'+button[i].id).text(button[i].buttonTitle);
					if(button[i].type === "HeaderButton"){
						$('#'+button[i].id).attr("onclick", "openStolenRecoveryModal()");
					}
					else{
						$('#'+button[i].id).attr("onclick", button[i].buttonURL);
					}
				}

				$("#footerBtn").append("<div class='col s12 m2 l2'><button class='btn' id='markedRecovered' style='margin-left:38%;margin-top: 8px;'></button><button class='btn' id='cancel' style='margin-left: 22px;margin-top: 8px;'></button></div>");
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
				
				$("#consignmentTableDIv").append("<div class='col s12 m2 l2'><input type='button' class='btn primary botton' value='filter' id='submitFilter' /></div>");
				$("#consignmentTableDIv").append("<div class='col s12 m4'><a href='JavaScript:void(0);' onclick='exportStolenRecoveryData()'  class='export-to-excel right'>Export <i class='fa fa-file-excel-o' aria-hidden='true'></i></a></div>");
				for(i=0; i<button.length; i++){
					$('#'+button[i].id).text(button[i].buttonTitle);
					if(button[i].type === "HeaderButton" && userType != "Operator" ){
						$('#'+button[i].id).attr("onclick", "openStolenRecoveryModal()");
					}else if(button[i].type === "HeaderButton" && userType == "Operator" ){
						$('#'+button[i].id).attr("href", button[i].buttonURL);
						
					}else{
						$('#'+button[i].id).attr("onclick", button[i].buttonURL);
					}
				}
			}
			$('.datepicker').datepicker({
			    dateFormat: "yy-mm-dd"
			    });
		}

	//$("#filterBtnDiv").append();
	}); 

	setAllDropdowns();
	if(userType=="CEIRAdmin"){
		$("#btnLink").css({display: "none"});
		}

}	 

function fileStolenReport(){

	var roleType = $("body").attr("data-roleType");
	var userId = $("body").attr("data-userID");
	var currentRoleType = $("body").attr("data-stolenselected-roleType"); 
	var sourceType='2';
	var requestType='0';
	var role = currentRoleType == null ? roleType : currentRoleType;
	var blockType=$('input[name=stolenBlockPeriod]:checked').val();
	var blockingTimePeriod=$('#stolenDatePeriod').val();
	var qty = $('#stolenQuantity').val();
	console.log("roleType=="+roleType+" userId="+userId+" currentRoleType =="+currentRoleType+"  blockType=="+blockType);
	var formData= new FormData();

	formData.append('file', $('#stolenCsvUploadFile')[0].files[0]);
	formData.append('blockingType',blockType);
	formData.append('blockingTimePeriod',blockingTimePeriod);
	formData.append('requestType',requestType);
	formData.append('roleType',role);
	formData.append('sourceType',sourceType);
	formData.append('userId',userId);
	formData.append('qty',qty);
	console.log(JSON.stringify(formData));
	console.log("*********");

	$.ajax({
		url: './fileTypeStolen',
		type: 'POST',
		data: formData,
		processData: false,
		contentType: false,
		success: function (data, textStatus, jqXHR) {

			console.log(data);
			$('#fileStolenModal').closeModal();
			$('#markAsStolen').openModal();
			//if(data.errorCode==200){
			/* 
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



function fileRecoveryReport(){

	var roleType = $("body").attr("data-roleType");
	var userId = $("body").attr("data-userID");
	var currentRoleType = $("body").attr("data-stolenselected-roleType"); 


	var sourceType='2';
	var requestType='1';
	var role = currentRoleType == null ? roleType : currentRoleType;
	var qty = $('#recoverQuantity').val();
	var blockType=$('input[name=stolenBlockPeriod]:checked').val();
	console.log("roleType=="+roleType+" userId="+userId+" currentRoleType =="+currentRoleType);

	var formData= new FormData();

	formData.append('file', $('#recoveryCsvUploadFile')[0].files[0]);
	formData.append('requestType',requestType);
	formData.append('roleType',role);
	formData.append('sourceType',sourceType);
	formData.append('userId',userId);
	formData.append('qty',qty);
	console.log(JSON.stringify(formData));
	console.log("*********");
	$.ajax({
		url: './fileTypeRecovery',
		type: 'POST',
		data: formData,
		processData: false,
		contentType: false,
		success: function (data, textStatus, jqXHR) {
			console.log(data);
			$('#recoveryFileModal').closeModal();
			$('#markAsRecoverDone').openModal();
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





function openStolenRecoveryModal(){
	console.log("openStolenRecoveryModal===");
	$('#stoleRecoveryModal').openModal();
}

function openFileStolenModal(){
	console.log("openfileStolenModal===");
	//  $("#materialize-lean-overlay-3").css("display","none");

	$('#stoleRecoveryModal').closeModal();
	setTimeout(function(){

		$('#fileStolenModal').openModal();
	}, 200);
	//$("#materialize-lean-overlay-3").css("display","none");

}


function openRecoveryModal(){
	console.log("openfileStolenModal===");
	//  $("#materialize-lean-overlay-3").css("display","none");

	$('#editRecoveryFileModal').closeModal();
	setTimeout(function(){

		$('#recoveryFileModal').openModal();
	}, 200);
	//$("#materialize-lean-overlay-3").css("display","none");

}
function closeStolenModalModal()
{
	$('#fileStolenModal').closeModal();
	$(".lean-overlay").remove();
}

function closeRecoveryModalModal()
{
	$('#recoveryFileModal').closeModal();
	$(".lean-overlay").remove();
}

function openFileStolenUpdate(txnId,requestType,id,qty)
{
	console.log("requestType="+requestType+" txnId="+txnId+" id= "+id);
	if(requestType=='1'){
		$('#editRecoveryFileModal').openModal(); 
		$('#editFileRecoveryTxnId').text(txnId)
		$('#editFileRecoveryId').val(id);
		$('#editRecoveryQuantity').val(qty);
		
	}
	else{
		$('#editFileStolenModal').openModal(); 
		$('#editFileStolenTxnId').text(txnId)
		$('#editFileStolenId').val(id);
		$('#editStolenQuantity').val(qty);
	}


	$('#editFileStolenRequestType').val(requestType);


}


function updatefileStolenReport(){

	var roleType = $("body").attr("data-roleType");
	var userId = $("body").attr("data-userID");
	var currentRoleType = $("body").attr("data-stolenselected-roleType"); 
	var sourceType=2;
	var requestType=$('#editFileStolenRequestType').val();
	var role = currentRoleType == null ? roleType : currentRoleType;
	var blockType=$('input[name=editStolenBlockPeriod]:checked').val();
	var blockingTimePeriod=$('#editStolenDatePeriod').val();
	var txnId=$('#editFileStolenTxnId').text();
	console.log("roleType=="+roleType+" userId="+userId+" currentRoleType =="+currentRoleType+"  blockType=="+blockType+" txnId ="+txnId);
	var formData= new FormData();
	if(requestType=='1'){
		formData.append('file', $('#editRecoveryCsvUploadFile')[0].files[0]);
		formData.append('blockingType','');
		formData.append('blockingTimePeriod','');
		formData.append('id',$('#editFileRecoveryId').val());
		formData.append('txnId',$('#editFileRecoveryTxnId').text());
	}
	else{
		formData.append('file', $('#editStolenCsvUploadFile')[0].files[0]);
		formData.append('blockingType',blockType);
		formData.append('blockingTimePeriod',blockingTimePeriod);
		formData.append('id',$('#editFileStolenId').val());
		formData.append('txnId',$('#editFileStolenTxnId').text());
	}


	formData.append('requestType',requestType);
	formData.append('roleType',role);
	formData.append('sourceType',sourceType);
	formData.append('userId',userId);
	console.log(JSON.stringify(formData));
	console.log("*********");

	$.ajax({
		url: './updateFileTypeStolenRecovery',
		type: 'POST',
		data: formData,
		processData: false,
		contentType: false,
		success: function (data, textStatus, jqXHR) {

			console.log(data);
			if(requestType=='1'){
				console.log("close recovery model.");
				$('#editFileStolenModal').closeModal();
			}
			else{
				console.log("close stolen model.");
				$('#editRecoveryFileModal').closeModal();
			}
			$('#updateMarkAsStolen').openModal();
			if(data.errorCode==0){

				$('#editMessageTextStoleRecovery').text('');
				$('#editMessageTextStoleRecovery').text(data.message);
			}
			else{
				$('#editMessageTextStoleRecovery').text('');
				$('#editMessageTextStoleRecovery').text(data.message);
			}  
			// $('#updateConsignment').modal('open'); 
			//alert("success");

		},
		error: function (jqXHR, textStatus, errorThrown) {
			console.log("error in ajax")
		}
	});

}

function closeEditRecoveryModal()
{
	$('#editRecoveryFileModal').closeModal();
	$(".lean-overlay").remove();
}
function closeEditStolenRecoveryModal()
{
	$('#editFileStolenModal').closeModal();
	$(".lean-overlay").remove();
}

function pickConsignment(){
	if($("input[name='chooseconsignment']:checked")){
		var url="./Consignment/viewConsignment";
		localStorage.setItem("sourceType", "viaStolen");
		console.log(url);
		window.location.href=url;

	}
}


var roleType = $("body").attr("data-stolenselected-roleType");
function pickstock(){
	localStorage.setItem("sourceType", "viaStock");
	var url="./assignDistributor?userTypeId="+roleType;
	window.location.href = url;
	console.log(url);
}

function pickExistingRecovery(){
	localStorage.setItem("sourceType", "viaExistingRecovery");
	var url =  "./stolenRecovery?userTypeId="+roleType;
	window.location.href = url;
	console.log(url);
}

function valuesPush(){
	var multipleMarkedRequest=[];
	var roleType = $("body").attr("data-roleType");
	var currentRoleType = $("body").attr("data-stolenselected-roleType"); 
	var role = currentRoleType == null ? roleType : currentRoleType;
	var requestType="1";
	console.log("role++++"+role+"requestType++"+requestType+"currentRoleType="+currentRoleType);
	
	 
	console.log("role++++"+role+"requestType++"+requestType+"currentRoleType="+currentRoleType);
	$('#stolenLibraryTable tr td input:checkbox:checked').each(function() {
		var sourceInterp=$(this).closest('tr').find('td:eq(5)').text();
		 var sourceType;
		 if(sourceInterp=='Consignment')
			 {
			 sourceType=0;
			 }
		 else if(sourceInterp=='File')
			 {
			 sourceType=2;
			 }
		 else if(sourceInterp=='Stock')
			 {
			 sourceType=1;
			 }
		 
		var json={"txnId":$(this).closest('tr').find('td:eq(2)').text(),
				"userId":userId,
				"sourceType":sourceType,
				"roleType":role,
				"requestType":requestType
		};

		multipleMarkedRequest.push(json);
	});
	console.log(multipleMarkedRequest)
	return multipleMarkedRequest;
}



function markedRecovered(){
	$('#markAsMultipleRecovery').openModal();

}

function openMulipleStolenPopUp()
{

	var stolenRecoverydata=JSON.stringify(valuesPush());
	console.log("release-------"+stolenRecoverydata);
	$.ajax({
		url: './multipleStolenRecovery',
		type: 'POST',
		data: stolenRecoverydata,
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		success: function (data, textStatus, jqXHR) {

			console.log(data);
			$('#markAsRecoveryDone').openModal();
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


function redirectToViewStolenPage()
{

	var roleType = $("body").attr("data-roleType");
	var userId = $("body").attr("data-userID");
	var currentRoleType = $("body").attr("data-stolenselected-roleType"); 
	var role = currentRoleType == null ? roleType : currentRoleType;
	console.log(" userId="+userId+" role="+role);
	console.log("./stolenRecovery?userTypeId="+role);
	window.location.href = "./stolenRecovery?userTypeId="+role;


}


function setAllDropdowns(){
	//Request Type status-----------dropdown
$.getJSON('./getSourceTypeDropdown/REQ_TYPE/'+featureId+'', function(data) {
		for (i = 0; i < data.length; i++) {
			$('<option>').val(data[i].value).text(data[i].interp)
			.appendTo('#requestType');
		}
	});

	//Source Type-----------dropdown
$.getJSON('./getSourceTypeDropdown/SOURCE_TYPE/'+featureId+'', function(data) {
		for (i = 0; i < data.length; i++) {
			$('<option>').val(data[i].value).text(data[i].interp)
			.appendTo('#sourceStatus');
		}
	});


	//Stolen Status-----------dropdown
	$.getJSON('./getDropdownList/'+featureId+'/'+$("body").attr("data-userTypeID"), function(data) {
		for (i = 0; i < data.length; i++) {
			$('<option>').val(data[i].state).text(data[i].interp)
			.appendTo('#status'); 
		}
	});
	
	
}


//**********************************************************Export Excel file************************************************************************
function exportStolenRecoveryData()
{
	var stolenRecoveryStartDate=$('#startDate').val();
	var stolenRecoveryEndDate=$('#endDate').val();
	var stolenRecoveryTxnId=$('#transactionID').val();
	var stolenRecoveryFileStatus=parseInt($('#status').val());
	var stolenRecoverySourceStatus=parseInt($('#sourceStatus').val());
	var stolenRecoveryRequestType=parseInt($('#requestType').val());

	
	var roleType = $("body").attr("data-roleType");
	var currentRoleType = $("body").attr("data-stolenselected-roleType");
	
	var role = currentRoleType == null ? roleType : currentRoleType;
	console.log("roleType=="+roleType+" currentRoleType="+currentRoleType+" role="+role);
    
	console.log("stolenRecoveryFileStatus=="+stolenRecoveryFileStatus+" stolenRecoverySourceStatus=="+stolenRecoverySourceStatus+" stolenRecoveryRequestType="+stolenRecoveryRequestType)
      if(isNaN(stolenRecoveryFileStatus) && isNaN(stolenRecoverySourceStatus) && isNaN(stolenRecoveryRequestType))
	   {
    	  stolenRecoveryFileStatus='';
    	  stolenRecoverySourceStatus='';
    	  stolenRecoveryRequestType='';
    	  console.log(" 11111111stolenRecoveryFileStatus && stolenRecoverySourceStatus && stolenRecoveryRequestType is empty =="+stolenRecoveryFileStatus+stolenRecoverySourceStatus);
	   }
      else if(isNaN(stolenRecoveryFileStatus) && isNaN(stolenRecoverySourceStatus))
	   {
    	  stolenRecoveryFileStatus='';
    	  stolenRecoverySourceStatus='';
    	  console.log(" 2222stolenRecoveryFileStatus && stolenRecoverySourceStatus is empty=="+stolenRecoveryFileStatus+stolenRecoverySourceStatus);
	   }
      else if(isNaN(stolenRecoverySourceStatus) && isNaN(stolenRecoveryRequestType))
	   {
    	  stolenRecoverySourceStatus='';
    	  stolenRecoveryRequestType='';
    	  console.log(" 333333stolenRecoverySourceStatus && stolenRecoveryRequestType is empty="+stolenRecoverySourceStatus+stolenRecoveryRequestType);
	   }
      else if(isNaN(stolenRecoveryRequestType) && isNaN(stolenRecoveryFileStatus))
    	  {
    	   stolenRecoveryRequestType='';
    	   stolenRecoveryFileStatus='';
    	   console.log(" 44444stolenRecoveryRequestType && stolenRecoveryFileStatus is empty "+stolenRecoveryRequestType+stolenRecoveryFileStatus);
    	  }
      else if(isNaN(stolenRecoveryFileStatus))
    	  {
    	  stolenRecoveryFileStatus='';
    	  console.log("stolenRecoveryFileStatus is blank="+stolenRecoveryFileStatus);
    	  }
      else if(isNaN(stolenRecoverySourceStatus))
	  {
    	  stolenRecoverySourceStatus='';
    	  console.log("stolenRecoverySourceStatus is blank="+stolenRecoverySourceStatus);
	  }
      else if(isNaN(stolenRecoveryRequestType))
	  {
    	  stolenRecoveryRequestType='';
    	  console.log("stolenRecoveryRequestType is blank="+stolenRecoveryRequestType);
	  }

	var table = $('#stolenLibraryTable').DataTable();
	var info = table.page.info(); 
    var pageNo=info.page;
    var pageSize =info.length;
	console.log("--------"+pageSize+"---------"+pageNo);
	console.log("stolenRecoveryStartDate  ="+stolenRecoveryStartDate+"  stolenRecoveryEndDate=="+stolenRecoveryEndDate+"  stolenRecoveryTxnId="+stolenRecoveryTxnId+" stolenRecoveryFileStatus ="+stolenRecoveryFileStatus+"=role="+role+" stolenRecoverySourceStatus="+stolenRecoverySourceStatus+" stolenRecoveryRequestType"+stolenRecoveryRequestType);
	window.location.href="./exportStolenRecovery?stolenRecoveryStartDate="+stolenRecoveryStartDate+"&stolenRecoveryEndDate="+stolenRecoveryEndDate+"&stolenRecoveryTxnId="+stolenRecoveryTxnId+"&stolenRecoveryFileStatus="+stolenRecoveryFileStatus+"&stolenRecoverySourceStatus="+stolenRecoverySourceStatus+"&stolenRecoveryRequestType="+stolenRecoveryRequestType+"&pageSize="+pageSize+"&pageNo="+pageNo+"&roleType="+roleType;
}