
<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setDateHeader("Expires", 0);
	response.setHeader("Pragma", "no-cache");
	 // session.setMaxInactiveInterval(200); //200 secs
	 //session.setAttribute("usertype", null); 
	if (session.getAttribute("usertype") != null) {
%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<title>Dashboard</title>
<meta http-equiv='cache-control' content='no-cache'>
<meta http-equiv='expires' content='-1'>
<meta http-equiv='pragma' content='no-cache'>

<meta charset="utf-8" />
<meta name="viewport"
content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta content="" name="description" />
<meta content="" name="author" />

<script type="text/javascript"
src="${context}/resources/js/plugins/jquery-1.11.2.min.js"></script>
<!-- <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css"></script>
-->

<!-- CORE CSS-->
<link href="${context}/resources/css/materialize.css" type="text/css"
rel="stylesheet" media="screen,projection">
<link href="${context}/resources/css/style.css" type="text/css"
rel="stylesheet" media="screen,projection">
<script type="text/javascript"
src="${context}/resources/js/plugins/data-tables/js/jquery.dataTables.min.js"></script>
<link
href="${context}/resources/js/plugins/data-tables/css/jquery.dataTables.min.css"
type="text/css" rel="stylesheet" media="screen,projection">
<link
href="${context}/resources/js/plugins/data-tables/css/jquery.dataTables.css"
type="text/css" rel="stylesheet" media="screen,projection">
<!-- Custome CSS-->
<link href="${context}/resources/css/custom/custom.css" type="text/css"
rel="stylesheet" media="screen,projection">
<link
href="${context}/resources/font/font-awesome/css/font-awesome.min.css"
type="text/css" rel="stylesheet" media="screen,projection">

<!-- INCLUDED PLUGIN CSS ON THIS PAGE -->
<link href="${context}/resources/js/plugins/prism/prism.css"
type="text/css" rel="stylesheet" media="screen,projection">
<link
href="${context}/resources/js/plugins/perfect-scrollbar/perfect-scrollbar.css"
type="text/css" rel="stylesheet" media="screen,projection">
<%-- <link href="${context}/resources/js/plugins/chartist-js/chartist.min.css" type="text/css" rel="stylesheet" media="screen,projection"> --%>
<link rel="stylesheet"
href="${context}/resources/project_css/viewStock.css">
<link rel="stylesheet"
href="${context}/resources/project_css/iconStates.css">

<style>
h6 {
margin: 0 0 0.4rem 0 !important;
}

select {
height: 32px !important;
}
</style>

</head>
<%-- <body data-roleType="${usertype}" data-userID="${userid}"
data-selected-roleType="${selectedUserTypeId}" data-stolenselected-roleType="${stolenselectedUserTypeId}"> --%>

<body data-id="6" data-roleType="${usertype}" data-userTypeID="${usertypeId}" data-userID="${userid}" data-selected-roleType="${selectedUserTypeId}" data-stolenselected-roleType="${stolenselectedUserTypeId}" 
data-grievanceTxnId="${grievanceTxnId}" data-grievanceId="${grievanceId}"
 data-grievanceStatus="${grievanceStatus}" session-valueTxnID="${not empty param.txnID ? param.txnID : 'null'}">




<section id="content">
<!--start container-->
<div class="container">
<div class="section">
<div class="row">
<div class="col s12 m12 l12">
<div class="row card-panel">
<div class="container-fluid pageHeader">
<p class="PageHeading"><spring:message code="modal.header.reportGrievance" /></p>
</div>

<form onsubmit="return saveGrievance()" method="POST" enctype="multipart/form-data" id="saveGrievance">
<div class="row" style="margin-top: 10px;">

<div class="row" >
<div class="input-field col s12 m6 l6">
<input type="text" id="TransactionId" pattern="[A-Z0-9]{18,18}" maxlength="18" 
oninput="InvalidMsg(this,'input','<spring:message code="validation.T18characters" />');" 
oninvalid="InvalidMsg(this,'input','<spring:message code="validation.T18characters" />');" 
class="form-control boxBorder boxHeight"/>
<label for="TransactionId"><spring:message code="input.transactionID" /></label>
</div>

<div class=" col s12 m6 l6">
 <label for="category"><spring:message code="operator.category" /> <span class="star">*</span></label> 
<select class="browser-default" id="category" 
oninput="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');" 
oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
 required>
<option value="" selected disabled ><spring:message code="operator.category" /></option>
</select>
</div>
</div>


<div id="mainDiv" class="mainDiv">
<div id="filediv" class="fileDiv">
<div class="row">

<div class="col s12 m6 l6" style="margin-top: 8px;">
<label for="Category"><spring:message code="input.documenttype" /></label>
<select class="browser-default" id="docTypetag1" onchange="enableSelectFile()" >
<option value="" disabled selected><spring:message code="select.documenttype" /> </option>

</select>
<select class="browser-default" id="docTypetagValue1" 
oninput="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');" 
oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
style="display: none;">
<option value="" disabled selected><spring:message code="select.documenttype" /></option>

</select>
</div>
<div class="file-field col s12 m6">
<h6 id="supportingdocumentFile" style="color: #000;"> <spring:message code="input.supportingdocument" /></h6>
<div class="btn">
<span><spring:message code="input.selectfile" /></span>
<input type="file" name="files[]" id="docTypeFile1"  disabled="disabled" onchange="enableAddMore()"
oninput="InvalidMsg(this,'fileType','<spring:message code="validation.NoChosen" />');" 
oninvalid="InvalidMsg(this,'fileType','<spring:message code="validation.NoChosen" />');" >
</div>
<div class="file-path-wrapper">
<input class="file-path validate" type="text"  
placeholder="<spring:message code="grievanceFileMessage" />">
<div>
<p id="myFiles"></p>
</div>
</div>
</div>
</div>


</div>

</div>
<div class="col s12 m6 right">
<button class="btn right add_field_button" type="button" disabled="disabled"><span
style="font-size: 20px;">+</span><spring:message code="input.addmorefile" /></button>
</div>

<div class="row" style="margin-top: 10px;">
<div class="input-field col s12 m12">
<textarea id="Remark" class="materialize-textarea" maxlength="200" 
oninput="InvalidMsg(this,'input','<spring:message code="validation.200characters" />');" 
oninvalid="InvalidMsg(this,'input','<spring:message code="validation.200characters" />');"
 required></textarea>
<label for="Remark"><spring:message code="input.remarks" /> <span class="star">*</span></label>
</div>
</div>
</div>

<span><spring:message code="input.requiredfields" /><span class="star">*</span></span>

<div class="center" style="margin-top: 50px;">
<button class="btn" id="saveGrievancesubmitButton"
type="submit" ><spring:message code="button.submit" /></button>
<a onclick="openCancelPopUp()" class="btn" id="Cancel"
style="margin-left: 10px;"><spring:message code="button.cancel" /></a>
</div>
</form>
</div>
</div>

</div>

</div>
</div>

<!--end container-->
</section>




<div id="submitGrievance" class="modal">
<h6 class="modal-header"><spring:message code="modal.header.submitGReport" /></h6>
<div class="modal-content">


<div class="row">
<h6 id="grievanceSuccessId"><spring:message code="modal.message.grievance" />( <span id="greivanceId"></span> )</h6>

<p><spring:message code="modal.note" /></p>
</div>
<div class="row">
<div class="input-field col s12 center">
<a href="./grievanceManagement"
class="btn"
><spring:message code="modal.ok" /></a>
</div>
</div>
</div>
</div>


	<div id="fileFormateModal" class="modal">
		<h6 class="modal-header"><spring:message code="fileValidationModalHeader" /></h6>
		<div class="modal-content">
			<div class="row">
				<h6 id="fileErrormessage"><spring:message code="fileValidationName" /><br> <br> <spring:message code="fileValidationFormate" /> <br><br> <spring:message code="fileValidationSize" /> </h6>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<div class="input-field col s12 center">
						<button class=" btn" onclick="clearFileName()"
							style="margin-left: 10px;"><spring:message code="modal.ok" /></button>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	 <!-- cancel Modal start   -->

    <div id="cancelMessage" class= " full-screen-modal modal" >
         <h6 class="modal-header"><spring:message code="button.cancel" /></h6>
        <div class="modal-content">
           <div class="row">
                <h6><spring:message code="modal.message" /></h6>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <div class="input-field col s12 center">
                        <a href="./grievanceManagement" class="btn"><spring:message code="modal.yes" /></a>
                        <button class="btn" onclick="closeCancelPopUp()" style="margin-left: 10px;"><spring:message code="modal.no" /></button>
                    </div>
                </div>
            </div>
        </div>
    </div>

<!--materialize js-->
<script type="text/javascript"
src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
<script type="text/javascript"
src="${context}/resources/js/materialize.js"></script>


<script type="text/javascript"
src="${context}/resources/js/plugins/data-tables/js/jquery.dataTables.js"></script>
<script type="text/javascript"
src="${context}/resources/js/plugins/data-tables/js/jquery.dataTables.min.js"></script>
<script
src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.3/moment.min.js"></script>

<!--plugins.js - Some Specific JS codes for Plugin Settings-->
<script
src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<%-- <script type="text/javascript" src="${context}/resources/js/materialize-plugins/date_picker/picker.date.js"></script>
<script type="text/javascript" src="${context}/resources/js/materialize-plugins/date_picker/picker.js"></script> --%>
<!--custom-script.js - Add your own theme custom JS-->
<script type="text/javascript" src="${context}/resources/js/plugins.js"></script>
<!--prism
<script type="text/javascript" src="${context}/resources/resources/js/prism/prism.js"></script>-->
<!--scrollbar-->
<script type="text/javascript"
src="${context}/resources/js/plugins/perfect-scrollbar/perfect-scrollbar.min.js"></script>
<!-- chartist -->
<%-- <script type="text/javascript" src="${context}/resources/js/plugins/chartist-js/chartist.min.js"></script> --%>
<script type="text/javascript"
src="${context}/resources/js/countries.js"></script>
	<!-- i18n library -->
	<script type="text/javascript"
		src="${context}/resources/project_js/CLDRPluralRuleParser.js"></script>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.i18n/1.0.7/jquery.i18n.js"></script>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.i18n/1.0.7/jquery.i18n.messagestore.js"></script>

	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.i18n/1.0.7/jquery.i18n.fallbacks.js"></script>

	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.i18n/1.0.7/jquery.i18n.language.js"></script>

	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.i18n/1.0.7/jquery.i18n.parser.js"></script>


	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.i18n/1.0.7/jquery.i18n.emitter.js"></script>


	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.i18n/1.0.7/jquery.i18n.emitter.bidi.js"></script>

	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/history.js/1.8/bundled/html4+html5/jquery.history.js"></script>

	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/js-url/2.5.3/url.min.js"></script>
<script type="text/javascript"
src="${context}/resources/project_js/viewStock.js"></script>

<script type="text/javascript"
		src="${context}/resources/project_js/profileInfoTab.js" async></script>
<script type="text/javascript" src="${context}/resources/project_js/globalVariables.js"></script>

			<script type="text/javascript"
		src="${context}/resources/project_js/_dateFunction.js" async></script>
		<script type="text/javascript"
		src="${context}/resources/project_js/profileInfoTab.js" async></script>
		<script type="text/javascript">
window.parent.$('#langlist').on('change', function() {
	var lang=window.parent.$('#langlist').val() == 'km' ? 'km' : 'en';
	window.location.assign("./openGrievanceForm?reqType=formPage&lang="+lang);
}); 
$.i18n().locale = data_lang_param;
var documenttype,selectfile,selectDocumentType;
$.i18n().load( {
	'en': './resources/i18n/en.json',
	'km': './resources/i18n/km.json'
} ).done( function() { console.log("done")});


if(sessionStorage.getItem("cierRoletype")=="Customer Care"){
	window.raisedBy = "customer care"; 
	window.name = sessionStorage.getItem("userName");
	window.userId = sessionStorage.getItem("userId");
}else{
	window.raisedBy = "self";
	window.name = $("body").attr("data-roleType");
	window.userId = $("body").attr("data-userID");
}

function saveGrievance(){
	var category=$('#category').val();
	var txnId=$('#TransactionId').val();
	var remark=$('#Remark').val();
	var file=$('#myInput').val();
	var fieldId=1;
	var fileInfo =[];
	var formData= new FormData();
	var fileData = [];
	var documentFileNameArray=[];
	var x;
		var filename='';
	var filediv;
	var i=0;
	var formData= new FormData();
	var docTypeTagIdValue='';
	var filename='';
	var filesameStatus=false;
	var documenttype=false;
	var docTypeTag='';
	
	$('.fileDiv').each(function() {	

		
		var x={
		"docType":$('#docTypetag'+fieldId).val(),
		"fileName":$('#docTypeFile'+fieldId).val().replace('C:\\fakepath\\','')
		}
		formData.append('files[]',$('#docTypeFile'+fieldId)[0].files[0]);
		
		documentFileName=$('#docTypeFile'+fieldId).val().replace('C:\\fakepath\\','')
		docTypeTag=$('#docTypetag'+fieldId).val();
		
		var fileIsSame=	documentFileNameArray.includes(documentFileName);
		
		var documentTypeTag=documentFileNameArray.includes(docTypeTag);
	
		if(filesameStatus!=true){
			filesameStatus=	fileIsSame;
		}
		
		 if(documenttype!=true)
			{
			documenttype=documentTypeTag;
	
			}
		documentFileNameArray.push(documentFileName);
		documentFileNameArray.push(docTypeTag);
		
		fileInfo.push(x);
		fieldId++;
		i++;
	});
	
	if(filesameStatus==true)
	{	
	
	
	$('#fileFormateModal').openModal({
 	   dismissible:false
    });
		$('#fileErrormessage').text('')
		$('#fileErrormessage').text($.i18n('duplicateFileName'));
	return false;
	
	}
	
	if(documenttype==true)
	{	
		
		$('#fileFormateModal').openModal({
		 	   dismissible:false
		    });
		$('#fileErrormessage').text('')
		$('#fileErrormessage').text($.i18n('documentTypeName'));
	return false;
	
	}
	
	
	var multirequest={
			"attachedFiles":fileInfo,
			"txnId":txnId,
			"categoryId":category,
			"remarks":remark,
			"featureId":6,
			"raisedBy" : window.raisedBy,
			"userId" : window.userId,
			"userType" :  window.name
		}
	
	formData.append('fileInfo[]',JSON.stringify(fileInfo));
	formData.append('multirequest',JSON.stringify(multirequest));
	/*formData.append('categoryId',category);
	formData.append('remarks',remark);
*/
	$.ajax({
		url: './saveGrievance',
		type: 'POST',
		data: formData,
		mimeType: 'multipart/form-data',
		processData: false,
		contentType: false,
		async:false,
	/*	method: 'POST',*/
		success: function (data, textStatus, jqXHR) {
			$("#saveGrievancesubmitButton").prop('disabled', true);
			var x=data;
			var y= JSON.parse(x);
			
			//$('#submitGrievance').openModal();
			$('#submitGrievance').openModal({
			 	   dismissible:false
			    });
			$('#greivanceId').text(y.txnId);
			/*alert(data.errorCode);
			if(data.errorCode=="0")
			{
				
				

			}
			else if(data.errorCode=="3")
			{
				console.log("status code = 3"); 
				$('#sucessMessage').text('');
				$('#sucessMessage').text("Grievnace number already exist");
				$('#errorCode').val(data.errorCode);
			}*/
			// $('#updateConsignment').modal('open'); 
			//alert("success");

		},
		error: function (jqXHR, textStatus, errorThrown) {
			console.log("error in ajax")
		}
	});
return false;

}


$.ajax({
	url: './getTypeDropdownList/GRIEVANCE_CATEGORY/'+$("body").attr("data-userTypeID"),
	type: 'GET',
	processData: false,
	contentType: false,
	success: function (data, textStatus, jqXHR) {
		console.log(data);

		//$('#category').empty();
		//$('#category').append('<option value="">'+$.i18n('selectCategory')+' *</option>');

		for (i = 0; i < data.length; i++){

			var html='<option value="'+data[i].value+'">'+data[i].interp+'</option>';
			//$('<option>').val(data[i]).channnelName.text(data[i]).channnelName.appendTo('#channelId');
			$('#category').append(html);	
		}
		/* $('#currency').val($("#langid").val()); */

	},
	error: function (jqXHR, textStatus, errorThrown) {
		console.log("error in ajax")
	}
});
/* $.getJSON('./getDropdownList/DOC_TYPE', function(data) {
	console.log("@@@@@"+JSON.stringify(data));
	for (i = 0; i < data.length; i++) {
		console.log(data[i].interp);
		$('<option>').val(data[i].tagId).text(data[i].interp).appendTo('#docTypetag1');
		$('<option>').val(data[i].value).text(data[i].tagId).appendTo('#docTypetagValue1');
		$('#docTypetagValue1').val(data[i].value);
	}
});
 */

		function cleanReplyPopUp()
		{
			console.log("reset form function");
			$('#replymessageForm').trigger("reset");
		}



// Integreation with add more field api

 $.getJSON('./addMoreFile/more_files_count', function(data) {
		console.log(data);
		
		localStorage.setItem("maxCount", data.value);
		
	});
 
		//var max_fields = 2; //maximum input boxes allowed
		var max_fields =localStorage.getItem("maxCount");
		
	    var wrapper = $(".mainDiv"); //Fields wrapper
		var add_button = $(".add_field_button"); //Add button ID
		var x = 1; //initlal text box count
		var id=2;
		
		
		$(".add_field_button").click(function (e) { //on add input button click
			e.preventDefault();
			var placeholderValue= $.i18n('selectFilePlaceHolder');
		if (x < max_fields) { //max input box allowed
				x++; //text box increment
				$(wrapper).append(
						'<div id="filediv'+id+'" class="fileDiv"><div class="row"><div class="file-field col s12 m6"><label for="Category">'+$.i18n('documenttype')+'</label><select id="docTypetag'+id+'" oninput="InvalidMsg(this,\'select\',\''+$.i18n('selectDocumentType')+'\');"  oninvalid="InvalidMsg(this,\'select\',\''+$.i18n('selectDocumentType')+'\');"  class="browser-default"> <option value="" disabled selected>'+$.i18n('selectDocumentType')+' </option></select><select id="docTypetagValue'+id+'" style="display:none" class="browser-default"> <option value="" disabled selected>'+$.i18n('selectDocumentType')+' </option></select></div><div class="file-field col s12 m6" style="margin-top: 23px;"><div class="btn"><span>'+$.i18n('selectfile')+'</span><input id="docTypeFile'+id+'" type="file" oninput="InvalidMsg(this,\'file\',\''+$.i18n('selectfile')+'\');"  oninvalid="InvalidMsg(this,\'file\',\''+$.i18n('selectfile')+'\');" name="files[]" id="filer_input" /></div><div class="file-path-wrapper"><input class="file-path validate" placeholder="'+placeholderValue+'" type="text"></div></div><div style="cursor:pointer;background-color:red;margin-right: 1.7%;" class="remove_field btn right btn-info">-</div></div></div>'
								); //add input box
			}
			
			
		/* 	$.getJSON('./getDropdownList/DOC_TYPE', function(data) {


				for (i = 0; i < data.length; i++) {
					console.log(data[i].interp);
					var optionId=id-1;
					$('<option>').val(data[i].tagId).text(data[i].interp).appendTo('#docTypetag'+optionId);
					$('<option>').val(data[i].value).text(data[i].tagId).appendTo('#docTypetagValue'+optionId);
					console.log('#docTypetag'+optionId);

				}
			}); */
			

				var request ={
							 "childTag": "DOC_TYPE",
							  "featureId": 6,
							  "parentValue":  parseInt($('#category').val()),	
							  "tag": "GRIEVANCE_CATEGORY",
							  "userTypeId": parseInt($("body").attr("data-userTypeID")),
						}
				
				console.log("request --->" +JSON.stringify(request));	
				 $.ajax({
						url: './get/tags-mapping',
						type: 'POST',
						data : JSON.stringify(request),
						dataType : 'json',
						contentType : 'application/json; charset=utf-8',
						success: function (data, textStatus, jqXHR) {
							
							console.log(data);
							
							for (i = 0; i < data.length; i++){
								var optionId=id-1;
									//var html='<option value="'+data[i].value+'">'+data[i].interp+'</option>';
									//$('#docTypetag1').append(html);	
								
								$('<option>').val(data[i].tagId).text(data[i].interp).appendTo('#docTypetag'+optionId);
								$('<option>').val(data[i].value).text(data[i].interp).appendTo('#docTypetagValue'+optionId);
							}
							
						},
						error: function (jqXHR, textStatus, errorThrown) {
							console.log("error in ajax")
						}
					});
				 
			
			
			id++;

		});
		/* $(wrapper).on("click", ".remove_field", function (e) { //user click on remove text
			e.preventDefault();
			$(this).parent('div').remove();
			x--;
			id--;
		})
 */
 $(wrapper).on("click", ".remove_field", function (e) { //user click on remove text
	 e.preventDefault();
	 var Iid=id-1;
	 /*alert("@@@"+Iid)*/
	 $('#filediv'+Iid).remove();
	 $(this).parent('div').remove();
	 x--;
	 id--;

	 })		
 function saveDocTypeValue(){
			$('#docTypetagValue').val(data[i].value).change();
			$('#docTypetagValue').val(data[i].value).change();
		}
		


$('#category').on(
					'change',
				function() {
	
	
	var request ={
				 "childTag": "DOC_TYPE",
				  "featureId": 6,
				  "parentValue":  parseInt($('#category').val()),	
				  "tag": "GRIEVANCE_CATEGORY",
				  "userTypeId": parseInt($("body").attr("data-userTypeID")),
			}
	
	console.log("request --->" +JSON.stringify(request));	
	 $.ajax({
			url: './get/tags-mapping',
			type: 'POST',
			data : JSON.stringify(request),
			dataType : 'json',
			contentType : 'application/json; charset=utf-8',
			success: function (data, textStatus, jqXHR) {
				$("#docTypetag1").empty();
				$('#docTypetag1').append('<option value="">'+$.i18n('selectDocumentType')+'</option>');
			
				for (i = 0; i < data.length; i++){
						//var html='<option value="'+data[i].value+'">'+data[i].interp+'</option>';
						//$('#docTypetag1').append(html);	
					$('<option>').val(data[i].tagId).text(data[i].interp).appendTo('#docTypetag1');
				
				}
				
			},
			error: function (jqXHR, textStatus, errorThrown) {
				console.log("error in ajax")
			}
		});
	 
	}); 
	 
function enableAddMore(){
	$(".add_field_button").attr("disabled", false);
}
function enableSelectFile(){
	$("#docTypeFile1").attr("disabled", false);
	$("#docTypeFile1").attr("required", true);

	$("#supportingdocumentFile").append('<span class="star">*</span>');
}

$("input[type=file]").keypress(function(ev) {
    return false;
    //ev.preventDefault(); //works as well

});

function  openCancelPopUp()
{
 $('#cancelMessage').openModal(); 
}

/* $( document ).ready(function() {
	var ccc=addMoreFileCount();
	alert(ccc);
}); */

</script>
<script type="text/javascript"
		src="${context}/resources/project_js/validationMsg.js"></script>
</body>
</html>

<%
} else {

%>
<script language="JavaScript">
sessionStorage.setItem("loginMsg",
"*Session has been expired");
window.top.location.href = "./login";
</script>
<%
}
%>