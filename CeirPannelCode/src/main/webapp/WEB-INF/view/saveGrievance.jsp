<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<title>Dashboard</title>

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

</head>
<body data-roleType="${usertype}" data-userID="${userid}"
data-selected-roleType="${selectedUserTypeId}" data-stolenselected-roleType="${stolenselectedUserTypeId}">




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
<input type="text" id="TransactionId" pattern="[A-Za-z0-9]{0,18}" maxlength="18" title="Please enter alphabets and numbers upto 18 characters only"
class="form-control boxBorder boxHeight"/>
<label for="TransactionId"><spring:message code="input.transactionID" /></label>
</div>

<div class="input-field col s12 m6 l6">
<!-- <label for="Category">Category</label> -->
<select class="browser-default" id="category" required="required">
</select>
</div>
</div>

<div class="row" style="margin-top: 10px;">
<div class="input-field col s12 m6 l6">
<textarea id="Remark" class="materialize-textarea" required="required"></textarea>
<label for="Remark"><spring:message code="input.remarks" /><span class="star">*</span></label>
</div>
</div>


<div id="mainDiv" class="mainDiv">
<div id="filediv" class="fileDiv">
<div class="row">
<div class="file-field col s12 m6">
<h6 style="color: #000;"> <spring:message code="input.supportingdocument" /></h6>
<div class="btn">
<span><spring:message code="input.selectfile" /></span>
<input type="file" name="files[]" id="docTypeFile1" required >
</div>
<div class="file-path-wrapper">
<input class="file-path validate" type="text" 
placeholder="Upload one or more files">
<div>
<p id="myFiles"></p>
</div>
</div>
</div>
<div class="col s12 m6 l6" style="margin-top: 8px;">
<label for="Category"><spring:message code="input.documenttype" /> <span class="star">*</span></label>
<select class="browser-default" id="docTypetag1" required>
<option value="" disabled selected><spring:message code="select.documenttype" /> </option>

</select>
<select class="browser-default" id="docTypetagValue1" style="display: none;">
<option value="" disabled selected><spring:message code="select.documenttype" /></option>

</select>
</div>
</div>


</div>
</div>

<div class="col s12 m6 right">
<button class="btn right add_field_button"><span
style="font-size: 20px;">+</span><spring:message code="input.addmorefile" /></button>
</div>

</div>

<span><spring:message code="input.requiredfields" /><span class="star">*</span></span>

<div class="center" style="margin-top: 50px;">
<button class="btn"
type="submit" ><spring:message code="button.submit" /></button>
<a href="./grievanceManagement" class="btn" id="Cancel"
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
src="${context}/resources/project_js/grievanceManagement.js"></script>

<script type="text/javascript">
window.parent.$('#langlist').on('change', function() {
	var lang=window.parent.$('#langlist').val() == 'km' ? 'km' : 'en';
	window.location.assign("./openGrievanceForm?reqType=formPage&lang="+lang);
}); 


function saveGrievance(){
	var category=$('#category').val();
	var txnId=$('#TransactionId').val();
	var remark=$('#Remark').val();
	var file=$('#myInput').val();
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
			"txnId":txnId,
			"categoryId":category,
			"remarks":remark
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
			var x=data;
			var y= JSON.parse(x);
			
			$('#submitGrievance').openModal();
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


var grievanceCategory="GRIEVANCE_CATEGORY";
$.ajax({
	url: './Consignment/consignmentCurency?CURRENCY='+grievanceCategory,
	type: 'GET',
	processData: false,
	contentType: false,
	success: function (data, textStatus, jqXHR) {
		console.log(data);

		$('#category').empty();
		$('#category').append('<option value="">'+$.i18n('selectCategory')+' *</option>');

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
$.getJSON('./getDropdownList/DOC_TYPE', function(data) {
	console.log("@@@@@"+JSON.stringify(data));
	for (i = 0; i < data.length; i++) {
		console.log(data[i].interp);
		$('<option>').val(data[i].tagId).text(data[i].interp).appendTo('#docTypetag1');
		$('<option>').val(data[i].value).text(data[i].tagId).appendTo('#docTypetagValue1');
		$('#docTypetagValue1').val(data[i].value);
	}
});
</script>
</body>
</html>

