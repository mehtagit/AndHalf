var cierRoletype = sessionStorage.getItem("cierRoletype");
var startdate=$('#startDate').val(); 
var endDate=$('#endDate').val();
var taxStatus=$('#taxPaidStatus').val();
var txnId=$('#transactionID').val();
var consignmentStatus=$('#filterConsignmentStatus').val();
var userId = $("body").attr("data-userID");
var userType=$("body").attr("data-roleType");
var featureId="3";

$(document).ready(function(){
	$('div#initialloader').fadeIn('fast');
	filterConsignment();
	pageRendering();
	/*var x = '19 December, 2019'; 
	var formattedDate = moment(x).format('YYYY-MM-DD h:mm:ss');
*/

});

$('.datepick').datepicker({
	dateFormat: "yy-mm-dd"
	});
/*$('body').on('click',".datepicker", function(){
    $(this).datepicker({
            	dateFormat: "yy-mm-dd"});
});*/
/*$('#startDate').datepicker({
showAnim: "fold",
dateFormat: "yy-mm-dd"
});
$('.datepicker').datepicker({
showAnim: "fold",
dateFormat: "yy-mm-dd"
});*/









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
			setEditPopupData(data) ;
			ConsignmentCurrency();
		},
		error : function() {
			alert("Failed");
		}
	});

	$("#updateModal").openModal();
}

function ConsignmentCurrency()
{
	var currency="currency";
	$.ajax({
		url: './consignmentCurency?currency='+currency,
		type: 'GET',
		processData: false,
		contentType: false,
		success: function (data, textStatus, jqXHR) {
			console.log(data);

			$('#currency').empty();
			for (i = 0; i < data.length; i++){
				var html='<option value="'+data[i].value+'">'+data[i].interp+'</option>';
				//$('<option>').val(data[i]).channnelName.text(data[i]).channnelName.appendTo('#channelId');
				$('#currency').append(html);	
			}
			$('#currency').val($("#hideCurrency").val()); 

		},
		error: function (jqXHR, textStatus, errorThrown) {
			console.log("error in ajax")
		}
	});
}

function viewConsignmentCurrency()
{
	var currency="currency";
	$.ajax({
		url: './consignmentCurency?currency='+currency,
		type: 'GET',
		processData: false,
		contentType: false,
		success: function (data, textStatus, jqXHR) {
			console.log(data);

			$('#viewcurrency').empty();
			for (i = 0; i < data.length; i++){
				var html='<option value="'+data[i].value+'">'+data[i].interp+'</option>';
				//$('<option>').val(data[i]).channnelName.text(data[i]).channnelName.appendTo('#channelId');
				$('#viewcurrency').append(html);	
			}
			$('#viewcurrency').val($("#viewhideCurrency").val()); 

		},
		error: function (jqXHR, textStatus, errorThrown) {
			console.log("error in ajax")
		}
	});
}

function viewConsignmentDetails(txnId){
	$("#viewModal").openModal();
	$.ajax({
		url : "./openRegisterConsignmentPopup?reqType=editPage&txnId="+txnId,
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		type : 'GET',
		success : function(data) {
			setViewPopupData(data);
			viewConsignmentCurrency();
		},
		error : function() {
			alert("Failed");
		}
	});
}


function setViewPopupData(data){
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
	$("#viewcurrency").val(data.currency);
	$("#viewtotalPrice").val(data.totalPrice);
	$("#viewhideCurrency").val(data.currency);


}

function setEditPopupData(data){
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
	$("#currency").val(data.currency);
	$("#totalPrice").val(data.totalPrice);
	$("#hideCurrency").val(data.currency);


} 



var sourceType =localStorage.getItem("sourceType");
function filterConsignment()
{       	 
	console.log("source type value=="+sourceType);
	var sessionFlag;
	if(sourceType==null){
		sessionFlag=2;
		console.log("sesion value set to "+sessionFlag);
	}
	else{
		sessionFlag=1;
		console.log("sesion value set to "+sessionFlag);
	}
	
	if(cierRoletype=="Importer" && sourceType !="viaStolen" ){
			table('../headers?type=consignment','../consignmentData?sessionFlag='+sessionFlag);
		}

		else if(cierRoletype=="Custom" && sourceType !="viaStolen"){
			table('../headers?type=customConsignment','../consignmentData?sessionFlag='+sessionFlag);
		}

		else if(cierRoletype=="CEIRAdmin"  && sourceType !="viaStolen"){
			table('../headers?type=adminConsignment','../consignmentData?sessionFlag='+sessionFlag);
		}  

		else if(cierRoletype=="Importer" && sourceType ==="viaStolen" ){
			
			table('../headers?type=stolenconsignment','../consignmentData?sourceType=viaStolen&sessionFlag='+sessionFlag);
		}
		localStorage.removeItem('sourceType');
	
}

//**************************************************filter table**********************************************

function table(url,dataUrl){
	var filterRequest={
			"consignmentStatus":parseInt($('#filterConsignmentStatus').val()),
			"endDate":$('#endDate').val(),
			"startDate":$('#startDate').val(),
			"taxPaidStatus":parseInt($('#taxPaidStatus').val()),
			"userId":parseInt(userId),
			"featureId":parseInt(featureId),
			"userTypeId": parseInt($("body").attr("data-userTypeID")),
			"txnId":$('#transactionID').val(),
			"userType":$("body").attr("data-roleType")
	}
	$.ajax({
		url: url,
		type: 'POST',
		dataType: "json",
		success: function(result){
			var table=	$("#consignmentLibraryTable").DataTable({
				destroy:true,
				"serverSide": true,
				orderCellsTop : true,
				"ordering" : false,
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

					}

				},
				"columns": result
			});

			$('div#initialloader').delay(300).fadeOut('slow');
		/*	$('div#initialloader').fadeOut('slow');*/
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
	var currency=$('#currency').val();
	var totalPrice=$('#totalPrice').val();

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
	formData.append('currency',currency);
	formData.append('totalPrice',totalPrice);
	$.ajax({
		url: './updateRegisterConsignment',
		type: 'POST',
		data: formData,
		processData: false,
		contentType: false,
		success: function (data, textStatus, jqXHR) {
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
		},
		error: function (jqXHR, textStatus, errorThrown) {
			console.log("error in ajax")
		}
	});

}



function openDeleteModal(transactionId)
{
	$('#deletemodal').openModal();
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
	}
	else{
		currentDate=now.getDate();
	}
	var today = now.getFullYear()+ '-' + (now.getMonth()+1)+ '-' +currentDate ;



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
				if(date[i].type === "date"){
					$("#consignmentTableDIv").append("<div class='col s6 m2 l2 responsiveDiv'>"+
							"<div id='enddatepicker' class='input-group'>"+
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


			if(sourceType=="viaStolen"){
				$("#btnLink").css({display: "none"});
			$("#consignmentTableDIv").append("<div class='col s12 m1'><input type='button' class='btn primary botton' value='filter' id='submitFilter'/></div>");
				$("#consignmentTableDIv").append("<div class='col s12 m1'><a href='JavaScript:void(0)' type='button' class='export-to-excel right'>Export <i class='fa fa-file-excel-o' aria-hidden='true'></i></a></div>");
				
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
				$("#consignmentTableDIv").append("<div class='col s12 m1'><input type='button' class='btn primary botton' value='filter' id='submitFilter' /></div>");
				$("#consignmentTableDIv").append("<div class='col s12 m1'><a href='JavaScript:void(0)' type='button' class='export-to-excel right'>Export <i class='fa fa-file-excel-o' aria-hidden='true'></i></a></div>");
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
			sourceType=="viaStolen"? $("#btnLink").css({display: "none"}) : $("#btnLink").css({display: "block"});
			if(cierRoletype=="CEIRAdmin"){ 
				$("#btnLink").css({display: "none"}); 
			}
			//Consignment status-----------dropdown
			$.getJSON('../getDropdownList/'+featureId+'/'+$("body").attr("data-userTypeID"), function(data) {

				for (i = 0; i < data.length; i++) {
					$('<option>').val(data[i].state).text(data[i].interp)
					.appendTo('#filterConsignmentStatus');
				}
				 var consignmentStatus= $("body").attr("data-selected-consignmentStatus");
				 console.log("#################33"+consignmentStatus);
				 if(consignmentStatus=="")
					 {
					 console.log("consignment status is blank ");
					 
					 }
				 else{
					 console.log("consignment status is Not blank ");
				 $("#filterConsignmentStatus").val(consignmentStatus).change();
				 }
			});



			//Tax paid status-----------dropdown
			$.getJSON('../getDropdownList/CUSTOMS_TAX_STATUS', function(data) {
				for (i = 0; i < data.length; i++) {
					$('<option>').val(data[i].value).text(data[i].interp)
					.appendTo('#taxPaidStatus');
				}
			});
			
			var txnid = $("body").attr("data-selected-consignmentTxnId");

			console.log("*******************"+txnid+" consignmentStatus="+consignmentStatus);
			$('#transactionID').val(txnid);$('#transactionID').attr("placeholder","" );
			if(txnid=="")
				{
				console.log("txnid is null");
				}
			else{
				console.log("txnid is not null")
				$('label[for=TransactionID]').remove();
			}
			
			$('.datepicker').datepicker({
				dateFormat: "yy-mm-dd"
				});
		}
	}); 	
}






function openApprovePopUp(txnId,displayName)
{
	displayName=displayName.replace("+20"," " );
	$('#ApproveConsignment').openModal();
	$('#ApproveConsignmentTxnid').text(txnId);
	$('#setApproveConsignmentTxnId').val(txnId);
	$('#displayname').text(displayName);
	console.log(displayName);


}
function approveSubmit(actiontype){
	var txnId=$('#setApproveConsignmentTxnId').val();
	
	var approveRequest={
			"action": actiontype,
			"txnId":txnId
	}
	$.ajax({
		url : "./updateConsignmentStatus",
		data : JSON.stringify(approveRequest),
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		type : 'POST',
		success : function(data) {
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

function openDisapprovePopup(txnId,displayName)
{
	displayName=displayName.replace("+20"," " );
	$('#RejectConsignment').openModal();
	$('#disaproveTxnId').text(txnId);
	$('#setDisapproveConsignmentTxnId').val(txnId);
	$('#disapprovedDisplayname').text(displayName);


}

function disapproveSubmit(actiontype){
	var txnId=$('#setDisapproveConsignmentTxnId').val();
	var Remark=$('#dispproveRemarks').val();

	var approveRequest={
			"action": actiontype,
			"txnId":txnId,
			"remarks":Remark
	}
	$.ajax({
		url : "./updateConsignmentStatus",
		data : JSON.stringify(approveRequest),
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		type : 'POST',
		success : function(data) {
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
	var requestType=0;
	$('#consignmentLibraryTable tr td input:checkbox:checked').each(function() {

		var json={"txnId":$(this).closest('tr').find('td:eq(2)').text(),
				"userId":userId,
				"sourceType":'0',
				"roleType":role,
				"requestType":requestType
		};

		selectedMultipleConsignment.push(json);
	});
	return selectedMultipleConsignment;
}



function markedstolen(){
	$('#markAsMultipleStolen').openModal();

}

function openMulipleStolenPopUp()
{

	var stolenRecoverydata=JSON.stringify(valuesPush());
	$.ajax({
		url: '../multipleStolenRecovery',
		type: 'POST',
		data: stolenRecoverydata,
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		success: function (data, textStatus, jqXHR) {
			$('#markAsStolenDone').openModal();
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
	window.location.href = "../stolenRecovery?userTypeId="+role;

}