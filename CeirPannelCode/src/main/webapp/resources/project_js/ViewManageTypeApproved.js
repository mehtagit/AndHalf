var featureId = 11;
var userId = $("body").attr("data-userID");
var cierRoletype = sessionStorage.getItem("cierRoletype");
var lang=window.parent.$('#langlist').val() == 'km' ? 'km' : 'en';

window.parent.$('#langlist').on('change', function() {
	var lang=window.parent.$('#langlist').val() == 'km' ? 'km' : 'en';
	window.location.assign("./manageTypeDevices?lang="+lang);				
}); 

$.i18n().locale = lang;	
$.i18n().load( {
	'en': './resources/i18n/en.json',
	'km': './resources/i18n/km.json'
} ).done( function() { 
});


$(document).ready(function(){
	$('div#initialloader').fadeIn('fast');
	typeApprovedDataTable(lang)
	pageRendering();
});




var userType = $("body").attr("data-roleType");

function typeApprovedDataTable(lang){
	if(userType=="CEIRAdmin"){
		Datatable('headers?type=AdmintrcManageType&lang='+lang,'./trc');
	}else if(userType=="Importer"){
		Datatable('headers?type=ImporterTrcManageType&lang='+lang,'./trc');
	}else{
		Datatable('headers?type=trcManageType&lang='+lang,'./trc');
	}
	
}





//**************************************************Type Approved table**********************************************

function Datatable(Url,dataUrl){
	var txn= (txnIdValue == 'null' && transactionIDValue == undefined)? $('#transactionID').val() : transactionIDValue;
if(userType=="CEIRAdmin"){
var userId = 0;
		var filterRequest={
				"endDate":$('#endDate').val(),
				"startDate":$('#startDate').val(),
			  	"tac" : $('#tac').val(),
			  	"txnId" : txn,
			  	"userId":userId,
				"featureId":parseInt(featureId),
				"userTypeId": parseInt($("body").attr("data-userTypeID")),
				"userType":$("body").attr("data-roleType"),
				"adminStatus" : parseInt($('#Status').val()),
				}
	}else{
		var userId = parseInt($("body").attr("data-userID"))
			var filterRequest={
				"endDate":$('#endDate').val(),
				"startDate":$('#startDate').val(),
			  	"tac" : $('#tac').val(),
			  	"txnId" : txn,
			  	"userId":userId,
				"featureId":parseInt(featureId),
				"userTypeId": parseInt($("body").attr("data-userTypeID")),
				"userType":$("body").attr("data-roleType"),
				"status" : parseInt($('#Status').val()),
				}
	}
	


if(lang=='km'){
	var langFile="//cdn.datatables.net/plug-ins/1.10.20/i18n/Khmer.json";
}
	$.ajax({
		url: Url,
		type: 'POST',
		dataType: "json",
		success: function(result){
			var table=	$("#typeAprroveTable").removeAttr('width').DataTable({
				destroy:true,
				"serverSide": true,
				orderCellsTop : true,
				"ordering" : false,
				"bPaginate" : true,
				"bFilter" : true,
				"bInfo" : true,
				"bSearchable" : true,
				"oLanguage": {  
					"sUrl": langFile  
				},
				ajax: {
					url : dataUrl,
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
		            { width: 142, targets: result.length - 1 },
		            { width: 143, targets: 0 },
		            { width: 75, targets: 2 }
			]
			});
			
			$('#typeAprroveTable input').unbind();
		    $('#typeAprroveTable input').bind('keyup', function (e) {
		        if (e.keyCode == 13) {
		            table.search(this.value).draw();
		        }
		    });
		    $('div#initialloader').delay(300).fadeOut('slow');
		},
		error: function (jqXHR, textStatus, errorThrown) {
			console.log("error in ajax");
		}
	});
}


//**************************************************Type Approved page buttons**********************************************

function pageRendering(){
	$.ajax({
		url: 'TRC/pageRendering',
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
					$("#typeAprroveTableDiv")
					.append("<div class='input-field col s6 m2'>"+
							"<div id='enddatepicker' class='input-group'>"+
							"<input class='form-control datepicker' type='text' id="+date[i].id+" autocomplete='off'>"+
							"<label for="+date[i].id+">"+date[i].title
							+"</label>"+
							"<span	class='input-group-addon' style='color: #ff4081'>"+
							"<i	class='fa fa-calendar' aria-hidden='true' style='float: right; margin-top: -37px;'>"+"</i>"+"</span>");
	
				}else if(date[i].type === "text"){
					$("#typeAprroveTableDiv").append("<div class='input-field col s6 m2' ><input type="+date[i].type+" id="+date[i].id+" maxlength='19' /><label for="+date[i].id+" class='center-align'>"+date[i].title+"</label></div>");
				}
			} 
			
			if(userType=="Importer"){
				console.log("userType is----->"+userType)
			}else{
				// dynamic drop down portion
				var dropdown=data.dropdownList;
				for(i=0; i<dropdown.length; i++){
					var dropdownDiv=
						$("#typeAprroveTableDiv").append("<div class='col s6 m2 selectDropdwn'>"+
								
								"<div class='select-wrapper select2  initialized'>"+
								"<span class='caret'>"+"</span>"+
								"<input type='text' class='select-dropdown' readonly='true' data-activates='select-options-1023d34c-eac1-aa22-06a1-e420fcc55868' value='Consignment Status'>"+

								"<select id="+dropdown[i].id+" class='select2 initialized'>"+
								"<option>"+dropdown[i].title+
								"</option>"+
								"</select>"+
								"</div>"+
						"</div>");
				
				}
			}
	
			$("#typeAprroveTableDiv").append("<div class=' col s3 m2 l1'><button type='button' class='btn primary botton' id='submitFilter'/></div>");
			$("#typeAprroveTableDiv").append("<div class='col s3 m2 l1'><a href='JavaScript:void(0)' onclick='exportTacData()' type='button' class='export-to-excel right'>"+$.i18n('Export')+" <i class='fa fa-file-excel-o' aria-hidden='true'></i></a></div>");
			for(i=0; i<button.length; i++){
				$('#'+button[i].id).text(button[i].buttonTitle);
				if(button[i].type === "HeaderButton"){
					$('#'+button[i].id).attr("href", button[i].buttonURL);
				}
				else{
					$('#'+button[i].id).attr("onclick", button[i].buttonURL);
				}
			}
	
			$('.dateClass').datepicker({
			    dateFormat: "yy-mm-dd"
			    });
			

			$.getJSON('./getDropdownList/'+featureId+'/'+$("body").attr("data-userTypeID"), function(data) {

				for (i = 0; i < data.length; i++) {
					$('<option>').val(data[i].state).text(data[i].interp)
					.appendTo('#Status');
				}
			});
		}
		
	}); 
}


if(userType=="CEIRAdmin"){
	$("#btnLink").css({display: "none"});
	}

if(userType=="CEIRAdmin"){
	$("#btnLink").css({display: "none"});
	}

function viewByID(id,actionType){
	
	
	
	$.ajax({
		url : "./viewByID/"+id, //controller haven'nt made yet for this url. this is dummy url.
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		type : 'POST',
		success : function(data) {
			console.log(+data);
			if(actionType=='view')
				{
				$("#viewModal").openModal();
				console.log("222222222");
				setViewPopupData(data);
			
				}
			else if(actionType=='edit')
				{
				console.log("3333333333");
				$("#editModal").openModal();
				setEditPopupData(data)
				
				}
			else if(actionType=='edit')
				{
				console.log("3333333333");
				$("#importereditModal").openModal();
				setImporterEditPopupData(data)
				
				}
			
		},
		error : function() {
			console.log("failed");
		}
	});
	
}


function ImporterviewByID(id,actionType){
	
	
	
	$.ajax({
		url : "./viewByID/"+id, //controller haven'nt made yet for this url. this is dummy url.
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		type : 'POST',
		success : function(data) {
			console.log(+data);
			if(actionType=='view')
				{
				$("#viewImporterModal").openModal();
				console.log("222222222");
				setImporterViewPopupData(data);
			
				}
			else if(actionType=='edit')
				{
				console.log("3333333333");
				$("#importereditModal").openModal();
				setImporterEditPopupData(data)
				
				}
			
		},
		error : function() {
			console.log("failed");
		}
	});
	
}

function ImporterviewByID(id,actionType){
	
	
	
	$.ajax({
		url : "./viewByID/"+id, //controller haven'nt made yet for this url. this is dummy url.
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		type : 'POST',
		success : function(data) {
			console.log(+data);
			if(actionType=='view')
				{
				$("#viewImporterModal").openModal();
				console.log("222222222");
				setImporterViewPopupData(data);
			
				}
			else if(actionType=='edit')
				{
				console.log("3333333333");
				$("#importereditModal").openModal();
				setImporterEditPopupData(data)
				
				}
			
		},
		error : function() {
			console.log("failed");
		}
	});
	
}


function setViewPopupData(data){
	$("#viewmanufacturerId").val(data.manufacturerId);
	$("#viewmanufacturerName").val(data.manufacturerName);
	$("#viewcountry").val(data.country);
	$("#viewtac").val(data.tac);
	$("#status").val(data.stateInterp);
	$('#viewrequestDate').val(data.requestDate)
	$("#viewapproveDisapproveDate").val(data.approveDisapproveDate);
	$("#viewremark").val(data.remark);
	
}

function setImporterViewPopupData(data){
	$("#viewtradmark").val(data.trademark);
	$("#viewmodelName").val(data.productName);
	$("#viewModelnumber").val(data.modelNumber);
	$("#viewManufacturercountry").val(data.manufacturerCountry);
	$('#viewrequestDate').val(data.requestDate)
	$('#viewFrequency').val(data.frequencyRange)
	$("#viewtac").val(data.tac);
}

function setEditPopupData(data){
	$("#editmanufacturerId").val(data.manufacturerId);
	$("#editmanufacturerName").val(data.manufacturerName);
	$("#editcountry").val(data.country);
	$("#edittac").val(data.tac);
	$("#editdeviceType").val(data.approveStatus).change();
	$('#editRequestDate').val(data.requestDate)
	$("#editApproveRejectionDate").val(data.approveDisapproveDate);
	$("#editRemark").val(data.remark);
	$("#editFileName").val(data.fileName);
	$("#transactionid").val(data.txnId);
	$("#columnid").val(data.id);
	
	
}

function setImporterEditPopupData(data){
		$("#editTradmark").val(data.trademark);
		$("#editmodelName").val(data.productName);
		$("#editmodelNumber").val(data.modelNumber);
		$("#editcountry").val(data.manufacturerCountry);
		$('#editRequestDate').val(data.requestDate)
		$('#editfrequency').val(data.frequencyRange)
		$("#edittac").val(data.tac);
	
}

populateCountries
(   
		"editcountry"
);


function updateReportTypeDevice()
{
	var manufacturerId=$("#editmanufacturerId").val();
	var manufacturerName=$("#editmanufacturerName").val();
	 var country=$("#editcountry").val();
	 var tac=$("#edittac").val();
	 var approveStatus=$("#editdeviceType").val();
    var requestDate=$('#editRequestDate').val()
	 var approveDisapproveDate=$("#editApproveRejectionDate").val();
	 var remark =$("#editRemark").val();
	 var file=$("#editFileName").val();
	 var txnid=$("#transactionid").val();
	 var id=$("#columnid").val();
	 console.log("approveStatus=="+approveStatus);
	 
	 var formData = new FormData();
		formData.append('file', $('#editUploadFile')[0].files[0]);
		formData.append('manufacturerId', manufacturerId);
		formData.append('manufacturerName', manufacturerName);
		formData.append('country', country);
		formData.append('tac', tac);
		formData.append('status', approveStatus);
		formData.append('approveDisapproveDate', approveDisapproveDate);
		formData.append('remark', remark);
		formData.append('txnId', txnid);
		formData.append('id', id);
		formData.append('fileName', file);
		formData.append('requestDate', requestDate);
		
		$.ajax({
			url : './update-register-approved-device',
			type : 'POST',
			data : formData,
			processData : false,
			contentType : false,
			success : function(data, textStatus, jqXHR) {
			
				console.log(data);
				
			
					
					 if (data.errorCode==0){
						$('#updateManageTypeDevice').openModal();
					}
					 else {

							$('#updateManageTypeDevice').openModal();
							$('#updateTacMessage').text('');
							$('#updateTacMessage').text(data.message);
						}
					 
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log("error in ajax")
			}
		});
		return false;
}










//**********************************************************Export Excel file************************************************************************
function exportTacData()
{
	var tacStartDate=$('#startDate').val();
	var tacEndDate=$('#endDate').val();
	var tacStatus=parseInt($('#Status').val());
	var tacNumber=$('#tac').val();
	console.log("tacStatus=="+tacStatus);
     if(isNaN(tacStatus))
	   {
    	 tacStatus='';
  	   console.log(" tacStatus=="+tacStatus);
	   }
 
	var table = $('#typeAprroveTable').DataTable();
	var info = table.page.info(); 
 var pageNo=info.page;
  var pageSize =info.length;
	console.log("--------"+pageSize+"---------"+pageNo+" tacStartDate="+tacStartDate+" tacEndDate="+tacEndDate+" tacStatus= "+tacStatus);
	
	window.location.href="./exportTac?tacNumber="+tacNumber+"&tacStartDate="+tacStartDate+"&tacEndDate="+tacEndDate+"&tacStatus="+tacStatus+"&pageSize="+pageSize+"&pageNo="+pageNo;

}


function openApproveTACPopUp(txnId,	manufacturerName)
{
	manufacturerName=manufacturerName.replace("+20"," " );
	$('#ApproveTAC').openModal();
	$('#ApproveTacTxnId').text(txnId);
	$('#setApproveTacTxnId').val(txnId);

}
function approveSubmit(actiontype){
	var txnId=$('#setApproveTacTxnId').val();
	var userId = $("body").attr("data-userID");
	var userType=$("body").attr("data-roleType");
	var adminApproveStatus=0;
	var approveRequest={
			"adminApproveStatus":adminApproveStatus,
			"txnId":txnId,
			"featureId":11,
			"adminUserId":userId,
			"adminUserType":userType
			
	}
	$.ajax({
		url : "./TACAprroveDisapprove",
		data : JSON.stringify(approveRequest),
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		type : 'POST',
		success : function(data) {
			$('#confirmApproveTAC').openModal();
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




function openDisapproveTACPopUp(txnId,	manufacturerName)
{
	manufacturerName=manufacturerName.replace("+20"," " );
	$('#RejectTAC').openModal();
	
	$('#RejectTacTxnId').text(txnId);
	$('#setRejectTacTxnId').val(txnId);

}
function rejectSubmit(actiontype){
	var txnId=$('#setRejectTacTxnId').val();
	var userId = $("body").attr("data-userID");
	var userType=$("body").attr("data-roleType");
	var adminApproveStatus=1;
	var approveRequest={
			"adminApproveStatus":adminApproveStatus,
			"txnId":txnId,
			"featureId":11,
			"adminUserId":userId,
			"adminUserType":userType
			
	}
	$.ajax({
		url : "./TACAprroveDisapprove",
		data : JSON.stringify(approveRequest),
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		type : 'POST',
		success : function(data) {
			$('#confirmRejectTAC').openModal();
			if(data.errorCode==0){

				$('#rejectSuccessMessage').text('');
				$('#rejectSuccessMessage').text(data.message);
			}
			else{
				$('#rejectSuccessMessage').text('');
				$('#rejectSuccessMessage').text(data.message);
			}
		},
		error : function() {
			alert("Failed");
		}
	});
}


$(document).on('keypress' , '#tac', validateNumber);
function validateNumber(event) {
var key = window.event ? event.keyCode : event.which;
if (event.keyCode === 8 || event.keyCode === 46) {
return true;
} else if ( key < 48 || key > 57 ) {
return false;
} else {
return true;
}
}
