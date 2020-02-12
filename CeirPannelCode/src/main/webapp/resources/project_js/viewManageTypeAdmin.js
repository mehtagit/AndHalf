var featureId = 21;
var userId = $("body").attr("data-userID");
var cierRoletype = sessionStorage.getItem("cierRoletype");
var lang=window.parent.$('#langlist').val() == 'km' ? 'km' : 'en';

/*window.parent.$('#langlist').on('change', function() {
	var lang=window.parent.$('#langlist').val() == 'km' ? 'km' : 'en';
	window.location.assign("./manageTypeDevices?lang="+lang);				
}); */


$(document).ready(function(){
	$('div#initialloader').fadeIn('fast');
	typeApprovedDataTable(lang)
	pageRendering();
	setAllDropdown();
});




var userType = $("body").attr("data-roleType");

function typeApprovedDataTable(lang){
	if(userType=="CEIRAdmin"){
		Datatable('headers?type=AdminImportertrcManageType&lang='+lang,'./importerAdmintrc');
	}else if(userType=="Importer"){
		Datatable('headers?type=ImporterTrcManageType&lang='+lang,'./importerAdmintrc');
	}else{
		Datatable('headers?type=trcManageType&lang='+lang,'./trc');
	}
	
}



//**************************************************Importer Type Approved table**********************************************

function Datatable(Url,dataUrl){
	var txn= (txnIdValue == 'null' && transactionIDValue == undefined)? $('#transactionID').val() : transactionIDValue;

			
	if (userType == "CEIRAdmin") {
		var userId = 0;
		var filterRequest = {
			"endDate" : $('#endDate').val(),
			"startDate" : $('#startDate').val(),
			"tac" : $('#tac').val(),
			"txnId" : txn,
			"userId" : userId,
			"featureId" : parseInt(featureId),
			"userTypeId" : parseInt($("body").attr("data-userTypeID")),
			"userType" : $("body").attr("data-roleType"),
			"adminStatus" : parseInt($('#Status').val()),
		}
	} else {
		var userId = parseInt($("body").attr("data-userID"))
		var filterRequest = {
			"endDate" : $('#endDate').val(),
			"startDate" : $('#startDate').val(),
			"tac" : $('#tac').val(),
			"txnId" : txn,
			"userId" : userId,
			"featureId" : parseInt(featureId),
			"userTypeId" : parseInt($("body").attr("data-userTypeID")),
			"userType" : $("body").attr("data-roleType"),
			"status" : parseInt($('#Status').val()),
		}
	}
					
	


$.ajax({
		url: Url,
		type: 'POST',
		dataType: "json",
		success: function(result){
			var table=	$("#ImporterAdmintypeAprroveTable").removeAttr('width').DataTable({
				destroy:true,
				"serverSide": true,
				orderCellsTop : true,
				"ordering" : false,
				"bPaginate" : true,
				"bFilter" : true,
				"bInfo" : true,
				"bSearchable" : true,
				/*"oLanguage": {  
					"sUrl": langFile  
				},*/
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
			
			$('#ImporterAdmintypeAprroveTable input').unbind();
		    $('#ImporterAdmintypeAprroveTable input').bind('keyup', function (e) {
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
		url: 'adminImprterTrc/pageRendering',
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
	
			
			
			$('.datepicker').datepicker({
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


if (userType == "CEIRAdmin") {
	$("#btnLink").css({
		display : "none"
	});
}

if (userType == "CEIRAdmin") {
	$("#btnLink").css({
		display : "none"
	});
}


//**********************************************************Export Excel file************************************************************************
function exportTacData()
{
	var txn= (txnIdValue == 'null' && transactionIDValue == undefined)? $('#transactionID').val() : transactionIDValue;
	var tacStartDate=$('#startDate').val();
	var tacEndDate=$('#endDate').val();
	var tacStatus=parseInt($('#Status').val());
	var tacNumber=$('#tac').val();
	var txnId = txn;
	
	console.log("tacStatus=="+tacStatus);
     if(isNaN(tacStatus))
	   {
    	 tacStatus='';
  	   }
 
	var table = $('#ImporterAdmintypeAprroveTable').DataTable();
	var info = table.page.info(); 
 var pageNo=info.page;
  var pageSize =info.length;
	console.log("pageSize=="+pageSize+" tacNumber=="+tacNumber+" tacStartDate=="+tacStartDate+" tacEndDate=="+tacEndDate+" tacStatus=="+tacStatus+" txnId=="+txnId+" pageSize=="+pageSize+" pageNo=="+pageNo);
	
	window.location.href="./exportTac?tacNumber="+tacNumber+"&tacStartDate="+tacStartDate+"&tacEndDate="+tacEndDate+"&tacStatus="+tacStatus+"&txnId="+txnId+"&pageSize="+pageSize+"&pageNo="+pageNo;

}




function ImporterviewByID(id,actionType,projectPath){
	
	window.projectPath = projectPath;
	
	
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
				setImporterViewPopupData(data,projectPath);
			
				}
			else if(actionType=='edit')
				{
				$("#importereditModal").openModal();
				setImporterEditPopupData(data)
				
				}
			
		},
		error : function() {
			console.log("failed");
		}
	});
	
}


function setImporterViewPopupData(data,projectPath){

	$("#viewtradmark").val(data.trademark);
	$("#viewmodelName").val(data.productNameInterp);
	$("#viewModelnumber").val(data.modelNumberInterp);
	$("#viewManufacturercountry").val(data.manufacturerCountry);
	$('#viewrequestDate').val(data.requestDate)
	$('#viewFrequency').val(data.frequencyRange)
	$("#viewImportertac").val(data.tac);
	
	var result= data;
	var importerViewResponse = [];
	importerViewResponse.push(result);
	
	$('#chatMsg').empty();
	var projectpath=path+"/Consignment/dowloadFiles/actual";
	for(var i=0; i< importerViewResponse.length; i++)
	{
		for (var j=0 ; j < importerViewResponse[i]["attachedFiles"].length; j++)
			{
			if(importerViewResponse[i].attachedFiles[j].docType == null || importerViewResponse[i].attachedFiles[j].docType == undefined ){
				importerViewResponse[i].attachedFiles[j].docType == "";
			}else{
				$("#chatMsg").append("<div class='chat-message-content clearfix'><span class='document-Type' ><b>Document Type : </b>"+importerViewResponse[i].attachedFiles[j].docType+"</span>  <a href='"+projectpath+"/"+importerViewResponse[i].attachedFiles[j].fileName+"/"+importerViewResponse[i].txnId+"/"+importerViewResponse[i].attachedFiles[j].docType+"'>"+importerViewResponse[i].attachedFiles[j].fileName+"</a></div>");
			}
		}
	}
	
	
}


function setImporterEditPopupData(data){
	var model  = data.modelNumber;
	$("#editImportertransactionid").val(data.txnId);
	$("#editTradmark").val(data.trademark);
	$("#productname").val(data.productName);
	
	var brand_id = $('#productname').val();
	$.getJSON('./productModelList?brand_id=' + brand_id,
			function(data) {
				$("#modelNumber").empty();
				for (i = 0; i < data.length; i++) {
					$('<option>').val(data[i].id).text(
							data[i].modelName).appendTo(
							'#modelNumber');
				}
				$('#modelNumber').val(model);
			});
	
	//setTimeout(function(){ $('#modelNumber').val(data.modelNumber); },200);
	$("#editmanufacturercountry").val(data.manufacturerCountry);
	$('#editfrequency').val(data.frequencyRange)
	$("#editImportertac").val(data.tac);
	$("#importerColumnid").val(data.id);
	$("#approveStatus").val(data.approveStatus);
	
	$.getJSON('./getSourceTypeDropdown/DOC_TYPE/21', function( //same values to be configure for featureId 21
			data) {
		$("#docTypetag1").empty();
		for (i = 0; i < data.length; i++) {
			console.log(data[i].interp);
			$('<option>').val(data[i].tagId).text(data[i].interp).appendTo(
					'#docTypetag1');
		}
	});

	//$("#editImporterFileName").val(data.attachedFiles[0].fileName);
	//$("#docTypetag1").val(data.attachedFiles[0].docType);
}

populateCountries
(   
		"editmanufacturercountry"
);

function updateImporterTypeDevice()
{
	var userId = $("body").attr("data-userID");
	var id=$("#importerColumnid").val();
	 
		var fieldId=1;
		var fileInfo =[];
		var formData= new FormData();
		var fileData = [];

		var x;
		var filename='';
		var filediv;
		var i=0;
		var formData= new FormData();
		var docTypeTagIdValue='';
		var filename='';
		
		
		$('.fileDiv').each(function() {	
		var x={
			"docType":$('#docTypetag'+fieldId).val(),
			"fileName":$('#docTypeFile'+fieldId).val().replace('C:\\fakepath\\','')
			}
			formData.append('files[]',$('#docTypeFile'+fieldId)[0].files[0]);
			fileInfo.push(x);
			fieldId++;
			i++;
		});
		
		var multirequest={
					"attachedFiles":fileInfo,
					"trademark" : $('#editTradmark').val(),
					"productName" : $('#productname').val(),
		 			"modelNumber" : $('#modelNumber').val(),
					"manufacturerCountry" : $('#editmanufacturercountry').val(),
		 			"frequencyRange" : $('#editfrequency').val(),
					"tac" : $('#editImportertac').val(),
					"txnId": $("#editImportertransactionid").val(),
					"userId" : $("body").attr("data-userID"),
					"featureId" : parseInt(featureId),
					"approveStatus" :  $("#approveStatus").val(),
					"id": parseInt($("#importerColumnid").val())
				}
			
		console.log("multirequest------------->" +JSON.stringify(multirequest))
		formData.append('fileInfo[]',JSON.stringify(fileInfo));
		formData.append('multirequest',JSON.stringify(multirequest));
	 
		
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

function setAllDropdown() {
	$.getJSON('./productList', function(data) {
		for (i = 0; i < data.length; i++) {
			$('<option>').val(data[i].id).text(data[i].brand_name).appendTo(
					'#productname');
		}
	});

	$('#productname').on(
			'change',
			function() {
				var brand_id = $('#productname').val();
				$.getJSON('./productModelList?brand_id=' + brand_id, function(
						data) {
					$("#modelNumber").empty();
					for (i = 0; i < data.length; i++) {
						$('<option>').val(data[i].id).text(data[i].modelName)
								.appendTo('#modelNumber');
					}
				});
			});

	$.getJSON('./getSourceTypeDropdown/DOC_TYPE/' + featureId + '', function(
			data) {
		for (i = 0; i < data.length; i++) {
			console.log(data[i].interp);
			$('<option>').val(data[i].tagId).text(data[i].interp).appendTo(
					'#docTypetag1');
		}
	});

}

var max_fields = 15; //maximum input boxes allowed
var wrapper = $(".mainDiv"); //Fields wrapper
var add_button = $(".add_field_button"); //Add button ID
var x = 1; //initlal text box count
var id = 2;
$(".add_field_button")
		.click(
				function(e) { //on add input button click
					e.preventDefault();
					if (x < max_fields) { //max input box allowed
						x++; //text box increment
						$(wrapper)
								.append(
										'<div id="filediv'+id+'" class="fileDiv"><div class="row"><div class="file-field col s12 m6"><label for="Category">'
												+ $
														.i18n('documenttype')
												+ ' </label><select id="docTypetag'+id+'" required class="browser-default"> <option value="" disabled selected>'
												+ $
														.i18n('selectDocumentType')
												+ ' </option></select><select id="docTypetagValue'+id+'" style="display:none" class="browser-default"> <option value="" disabled selected>'
												+ $
														.i18n('selectDocumentType')
												+ ' </option></select></div> <div class="file-field col s12 m6" style="margin-top: 23px;"><div class="btn"><span>'
												+ $.i18n('selectfile')
												+ '</span><input id="docTypeFile'+id+'" type="file" required name="files[]" id="filer_input" /></div><div class="file-path-wrapper"><input class="file-path validate" type="text"></div></div><div style="cursor:pointer;background-color:red;margin-right: 1.7%;" class="remove_field btn right btn-info">-</div></div></div>'); //add input box
					}

					$.getJSON('./getSourceTypeDropdown/DOC_TYPE/21', function(
							data) {

						for (i = 0; i < data.length; i++) {
							console.log(data[i].interp);
							var optionId = id - 1;
							$('<option>').val(data[i].tagId).text(
									data[i].interp).appendTo(
									'#docTypetag' + optionId);
							$('<option>').val(data[i].value).text(
									data[i].tagId).appendTo(
									'#docTypetagValue' + optionId);
							console.log('#docTypetag' + optionId);

						}
					});
					id++;

				});

$(wrapper).on("click", ".remove_field", function(e) { // user click on remove  text
	e.preventDefault();
	var Iid = id - 1;
	/* alert("@@@"+Iid) */
	$('#filediv' + Iid).remove();
	$(this).parent('div').remove();
	x--;
	id--;

})

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
			"featureId":featureId,
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
			"featureId": featureId,
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



function DeleteTacRecord(txnId, id){
	$("#DeleteTacConfirmationModal").openModal();
	$("#tacdeleteTxnId").text(txnId);
	$("#deleteTacId").val(id);
}

function confirmantiondelete(){
	var txnId= $("#tacdeleteTxnId").text();
	var tacRemark= $("#deleteTacRemark").val();
	var id =  $("#deleteTacId").val();
	
	console.log("userType=="+userType+" ==remarks=="+tacRemark+"===id===" +id);
	
	/*var obj ={
			"txnId" : txnId,
			"userType": $("body").attr("data-roleType"),
			"remark" : tacRemark
	}*/

	$.ajax({
		url : './importerTacDelete?id='+id,
		//data : JSON.stringify(obj),
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		type : 'POST',
		success : function(data, textStatus, xhr) {
			console.log(data);

			//$("#stockModalText").text(data.message);
			$("#DeleteTacConfirmationModal").closeModal();

			$("#closeDeleteModal").openModal();
			if(data.errorCode == 0){
				$("#tacModalText").text(stockDeleted);
			}
			else{	$("#TacModalText").text(data.message);}
			$("#materialize-lean-overlay-3").css("display","none");
		},
		error : function() {
			console.log("Error");
		}
	});
	
	/* 
$(".lean-overlay").remove(); */ 

}