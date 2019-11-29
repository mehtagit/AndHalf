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



$(document).ready(function(){
	$('.datepicker').datepicker();
	tableHeader();
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


var roleType = $("body").attr("data-roleType");
var userId = $("body").attr("data-userID");
var currentRoleType = $("body").attr("data-stolenselected-roleType");  

console.log("roleType=======" +roleType+"---------userId------"+userId+"-----------currentRoleType-----"+currentRoleType) 
var role = currentRoleType == null ? roleType : currentRoleType;
var featureId="5";
var jsonObj = {
		"consignmentStatus":parseInt($('#filterConsignmentStatus').val()),
		"endDate":$('#endDate').val(),
		"startDate":$('#startDate').val(),
		"roleType": role,
		"taxPaidStatus":parseInt($('#taxPaidStatus').val()),
		"userId": userId,
		"featureId":featureId
};

var sourceType = localStorage.getItem("sourceType");	 
function tableHeader(){
	alert(sourceType)
	if(sourceType !="viaExistingRecovery" ){
		Datatable('./headers?type=stolen','stolenData')
	}else if(sourceType =="viaExistingRecovery" ){
		Datatable('./headers?type=stolenCheckHeaders', 'stolenData?sourceType=viaExistingRecovery')
	}
	localStorage.removeItem('sourceType');
}  


function Datatable(url,dataUrl){
	$.ajax({
		url: url,
		type: 'POST',
		dataType: "json",
		success: function(result){
			var table=	$("#stolenLibraryTable").DataTable({
				destroy:true,
				"serverSide": true,
				orderCellsTop : true,
				"ordering": false,
				"bPaginate" : true,
				"bFilter" : true,
				"bInfo" : true,
				"bSearchable" : true,
				ajax: {
					url: dataUrl,
					type: 'POST',
					data : function(d) {
						d.filter =JSON.stringify(jsonObj); 
						console.log(JSON.stringify(jsonObj));
					}
				},
				"columns": result
			});
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
							"<div id='enddatepicker' class='input-group date' data-date-format='yyyy-mm-dd'>"+
							"<label for='TotalPrice'>"+date[i].title
							+"</label>"+"<input class='form-control' type="+date[i].type+" id="+date[i].id+"/>"+
							"<span	class='input-group-addon' style='color: #ff4081'>"+
							"<i	class='fa fa-calendar' aria-hidden='true' style='float: right; margin-top: -37px;'>"+"</i>"+"</span>");
				}else if(date[i].type === "text"){
					$("#consignmentTableDIv").append("<div class='input-field col s6 m2' style='margin-top: 22px;'><input type="+date[i].type+" id="+date[i].id+" maxlength='15' /><label for='TransactionID' class='center-align'>"+date[i].title+"</label></div>");
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
				for(i=0; i<button.length; i++){
					$('#'+button[i].id).text(button[i].buttonTitle);
					if(button[i].type === "HeaderButton"){
						$('#'+button[i].id).attr("onclick", "openStolenRecoveryModal()");
					}
					else{
						$('#'+button[i].id).attr("onclick", button[i].buttonURL);
					}
				}
			}

		}

	//$("#filterBtnDiv").append();
	}); 

	setAllDropdowns();
}	 

function fileStolenReport(){

	var roleType = $("body").attr("data-roleType");
	var userId = $("body").attr("data-userID");
	var currentRoleType = $("body").attr("data-stolenselected-roleType"); 
	var sourceType='file';
	var requestType='stolen';
	var role = currentRoleType == null ? roleType : currentRoleType;
	var blockType=$('input[name=stolenBlockPeriod]:checked').val();
	var blockingTimePeriod=$('#stolenDatePeriod').val();
	console.log("roleType=="+roleType+" userId="+userId+" currentRoleType =="+currentRoleType+"  blockType=="+blockType);
	var formData= new FormData();

	formData.append('file', $('#stolenCsvUploadFile')[0].files[0]);
	formData.append('blockingType',blockType);
	formData.append('blockingTimePeriod',blockingTimePeriod);
	formData.append('requestType',requestType);
	formData.append('roleType',role);
	formData.append('sourceType',sourceType);
	formData.append('userId',userId);


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


	var sourceType='file';
	var requestType='recovery';
	var role = currentRoleType == null ? roleType : currentRoleType;

	var blockType=$('input[name=stolenBlockPeriod]:checked').val();
	console.log("roleType=="+roleType+" userId="+userId+" currentRoleType =="+currentRoleType);

	var formData= new FormData();

	formData.append('file', $('#recoveryCsvUploadFile')[0].files[0]);
	formData.append('requestType',requestType);
	formData.append('roleType',role);
	formData.append('sourceType',sourceType);
	formData.append('userId',userId);

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

function openFileStolenUpdate(txnId,requestType,id)
{
	console.log("requestType="+requestType+" txnId="+txnId+" id= "+id);
	if(requestType=='recovery'){
		$('#editRecoveryFileModal').openModal(); 
		$('#editFileRecoveryTxnId').text(txnId)
		$('#editFileRecoveryId').val(id);
	}
	else{
		$('#editFileStolenModal').openModal(); 
		$('#editFileStolenTxnId').text(txnId)
		$('#editFileStolenId').val(id);
	}


	$('#editFileStolenRequestType').val(requestType);


}


function updatefileStolenReport(){

	var roleType = $("body").attr("data-roleType");
	var userId = $("body").attr("data-userID");
	var currentRoleType = $("body").attr("data-stolenselected-roleType"); 
	var sourceType='file';
	var requestType=$('#editFileStolenRequestType').val();
	var role = currentRoleType == null ? roleType : currentRoleType;
	var blockType=$('input[name=editStolenBlockPeriod]:checked').val();
	var blockingTimePeriod=$('#editStolenDatePeriod').val();
	var txnId=$('#editFileStolenTxnId').text();
	console.log("roleType=="+roleType+" userId="+userId+" currentRoleType =="+currentRoleType+"  blockType=="+blockType+" txnId ="+txnId);
	var formData= new FormData();
	if(requestType=='recovery'){
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
			if(requestType=='recovery'){
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
	var requestType="recovery";
	console.log("role++++"+role+"requestType++"+requestType+"currentRoleType="+currentRoleType);
	$('#stolenLibraryTable tr td input:checkbox:checked').each(function() {

		var json={"txnId":$(this).closest('tr').find('td:eq(2)').text(),
				"userId":userId,
				"sourceType":$(this).closest('tr').find('td:eq(5)').text(),
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
	$.getJSON('./getDropdownList/STOLEN_REQ_TYPE', function(data) {
		for (i = 0; i < data.length; i++) {
			$('<option>').val(data[i].state).text(data[i].interp)
			.appendTo('#requestType');
		}
	});

	//Source Type-----------dropdown
	$.getJSON('./getDropdownList/STOLEN_SOURCE_TYPE', function(data) {
		for (i = 0; i < data.length; i++) {
			$('<option>').val(data[i].state).text(data[i].interp)
			.appendTo('#taxPaidStatus');
		}
	});


	//Stolen Status-----------dropdown
	$.getJSON('./getDropdownList/STOLEN_STATUS_TYPE', function(data) {
		for (i = 0; i < data.length; i++) {
			$('<option>').val(data[i].state).text(data[i].interp)
			.appendTo('#filterConsignmentStatus');
		}
	});
}


