<%@ page import="java.util.Date" %>
<%
   response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setDateHeader("Expires", 0);
	response.setHeader("Pragma", "no-cache");
	
    /*   //200 secs
	 session.setAttribute("usertype", null);  */
/* 	 session.setMaxInactiveInterval(10); */
	 int timeout = session.getMaxInactiveInterval();
	
	 long accessTime = session.getLastAccessedTime();
	 long currentTime= new Date().getTime(); 
	 System.out.println("accessTime========"+(accessTime));
	 System.out.println("timeout========"+timeout);
	 long dfd= accessTime +timeout;
	 System.out.println("currentTime========"+currentTime);
	 if( currentTime< dfd){
	/*  response.setHeader("Refresh", timeout + "; URL = ../login");
	 System.out.println("timeout========"+timeout); 
	if (session.getAttribute("usertype") != null) { */
%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<title>Upload Stock</title>

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
<!--   <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css"></script>  
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
<link href="${context}/resources/css/jquery-datepicker2.css"
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
<%--  <link href="${context}/resources/js/plugins/chartist-js/chartist.min.css" type="text/css" rel="stylesheet" media="screen,projection"> --%>

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>

<script src="http://malsup.github.io/jquery.blockUI.js"></script>
<script src="//cdn.datatables.net/plug-ins/1.10.20/i18n/Khmer.json"></script>

			<script type="text/javascript"
		src="${context}/resources/project_js/enterKey.js"></script>
<style>
#snackbar {
	visibility: hidden;
	min-width: 250px;
	margin-left: -125px;
	background-color: #333;
	color: #fff;
	text-align: center;
	border-radius: 2px;
	padding: 10px;
	position: fixed;
	z-index: 1;
	left: 47%;
	top: 15px;
	font-size: 17px;
	height: 50px;
	line-height: 0px;
}

#snackbar.show {
	visibility: visible;
	-webkit-animation: fadein 0.5s, fadeout 0.5s 2.5s;
	animation: fadein 0.5s, fadeout 0.5s 2.5s;
}

@
-webkit-keyframes fadein {
	from {bottom: 0;
	opacity: 0;
}

to {
	bottom: 30px;
	opacity: 1;
}

}
@
keyframes fadein {
	from {bottom: 0;
	opacity: 0;
}

to {
	bottom: 30px;
	opacity: 1;
}

}
@
-webkit-keyframes fadeout {
	from {bottom: 30px;
	opacity: 1;
}

to {
	bottom: 0;
	opacity: 0;
}

}
@
keyframes fadeout {
	from {bottom: 30px;
	opacity: 1;
}

to {
	bottom: 0;
	opacity: 0;
}

}
.container-fluid {
	background-color: #529dba;
	height: 50px;
	/* margin:0 -20px; */
	padding: 10px;
	/* border-radius:5px 0;  */
}

/* #deletemodal.modal-backdrop {
  z-index: 0;
}  */
.boton {
	color: #2979a5;
	float: right;
	border: solid 1px rgba(33, 167, 201, 0.774);
	padding: 4px 10px;
	border-radius: 7px;
	font-size: 14px;
	background-color: #fff;
}

.boton:hover {
	color: #fff;
	float: right;
	border: solid 1px #ff5a92;
	padding: 4px 10px;
	border-radius: 7px;
	font-size: 14px;
	background-color: #ff5a92;
}

.row {
	margin-bottom: 0;
}

.file-field .btn, .file-field .btn-large {
	float: left;
	height: 2.5rem;
	line-height: 2.5rem;
}

[type="date"]::-webkit-inner-spin-button {
	display: none;
}

[type="date"]::-webkit-calendar-picker-indicator {
	opacity: 0;
}

.selectDropdwn {
	margin-top: 10px;
}
</style>

</head>
<body data-id="4" data-roleType="${usertype}" data-userID="${userid}"
	data-userTypeID="${usertypeId}"
	data-selected-roleType="${selectedUserTypeId}" data-selectedRoleTypeId="${selectedRoleTypeId}">


	<section id="content">
		<!--start container-->
		<div id="initialloader"></div>
		<div class="container">
			<div class="section">
				<div class="row">
					<div class="col s12 m12 l12">
						<div class="row card-panel">
							<div class="container-fluid pageHeader">
								<p class="PageHeading" id="uploaDStockHeader">
									<spring:message code="button.uploadStock" />
								</p>
							</div>
							<form action="" onsubmit="return uploadStock()" method="POST"
								enctype="multipart/form-data" id="uploadStock">

								<div class="row myRow" id="supplierNameOrIdDiv"
									style="display: none;">
									<div class="input-field col s12 m6">
										<input type="text" name="supplierId"
											pattern="[A-Za-z0-9]{0,15}"
											oninput="InvalidMsg(this,'input','<spring:message code="validation.15character" />');"
											oninvalid="InvalidMsg(this,'input','<spring:message code="validation.15character" />');"
											maxlength="15" id="supplierId" maxlength="15" /> <label
											for="supplierId" id="SupplierIdAssignie" class="center-align"><spring:message
												code="input.supplierID" /> </label>
									</div>

									<div class="input-field col s12 m6">
										<input type="text" name="supplierName" id="supplierName"
											pattern="[A-Za-z ]{0,50}"
											oninput="InvalidMsg(this,'input','<spring:message code="validation.50character" />');"
											oninvalid="InvalidMsg(this,'input','<spring:message code="validation.50character" />');"
											maxlength="50" required /> <label for="supplierName"
											id="SupplierIdAssignieName" class="center-align"><spring:message
												code="input.supllierName" /> <span class="star">*</span></label>
									</div>
								</div>
								<div class="col s12 m12">
									<a onclick="openModalForAssigneId()" id="assigneDetailslink"
										class="supplierInformation" style="display: none;"> <spring:message
											code="AssigneSearchLink" /></a>
								</div>
								<div class="row myRow">
									<div class="input-field col s12 m6">
										<input type="text" name="quantity" id="Quantity"
											pattern="[0-9]{0,7}"
											oninput="InvalidMsg(this,'input','<spring:message code="validation.7digits" />');"
											oninvalid="InvalidMsg(this,'input','<spring:message code="validation.7digits" />');"
											maxlength="7" required /> <label for="Quantity"
											class="center-align"><spring:message
												code="input.quantity" /> <span class="star">*</span></label>
									</div>
									<div class="input-field col s12 m6">
											<input type="text" name="devicequantity" id="devicequantity"
												pattern="[0-9]{0,7}"
												
												maxlength="7"  oninput="InvalidMsg(this,'input','<spring:message code="validation.7character" />');" oninvalid="InvalidMsg(this,'input','<spring:message code="validation.7character" />');"  required/> <label for="devicequantity"
												class="center-align"><spring:message code="input.devicequantity" /> <span class="star">*</span></label>
										</div>
									<div class="input-field col s12 m6" id="invoiceNumberDiv"
										style="display: none">
										<input type="text" name="invoiceNumber" id="invoiceNumber"
											pattern="[A-Za-z0-9]{0,15}"
											oninput="InvalidMsg(this,'input','<spring:message code="validation.15numbers" />');"
											oninvalid="InvalidMsg(this,'input','<spring:message code="validation.15numbers" />');"
											maxlength="15" /> <label for="InvoiceNumber"
											class="center-align"><spring:message
												code="input.invoiceNumber" /></label>
									</div>
								</div>



								<div class="row myRow">
									<h6 style="color: #000; margin-left: 10px;">
										<spring:message code="input.uploadBulkStock" />
										<span class="star">*</span>
									</h6>
									<div class="file-field input-field col s12 m6"
										style="margin-top: 5px;">
										<div class="btn">
											<span><spring:message code="input.selectfile" /></span> <input
												onchange="fileTypeValueChanges()" type="file" id="file"
												oninput="InvalidMsg(this,'fileType','<spring:message code="validation.NoChosen" />');"
												oninvalid="InvalidMsg(this,'fileType','<spring:message code="validation.NoChosen" />');"
												required size=50 maxlength=50 accept=".csv">
										</div>
										<div class="file-path-wrapper">
											<input class="file-path validate responsive-file-div"
												id="fileName" type="text">
										</div>
									</div>
									<br> <br>
									<p style="margin-left: 10px;">
										<a href="./Consignment/sampleFileDownload/4"><spring:message
												code="input.downlaod.sample" /></a>
									</p>
								</div>

								<span><spring:message code="input.requiredfields" /></span> <span
									class="star">*</span>

								<div class="row" style="padding-bottom: 100px;">
									<div class="input-field col s12 center">
										<button class=" btn" id="stockSubmitButton" type="submit">
											<spring:message code="button.submit" />
										</button>
										<a href="#cancelMessage" class="modal-trigger btn"
											type="cancel" style="margin-left: 10px;"><spring:message
												code="button.cancel" /></a>


									</div>
								</div>
							</form>
						</div>
					</div>

				</div>

			</div>
		</div>

		<!--end container-->
	</section>

	<div id="cancelMessage" class="modal">
		<h6 class="modal-header">
			<spring:message code="modal.cancel" />
		</h6>
		<div class="modal-content">
			<div class="row">
				<h6>
					<spring:message code="modal.message" />
				</h6>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<div class="input-field col s12 center">
						<a onclick="redirectToViewPage()" class="btn"><spring:message
								code="modal.yes" /></a>
						<button class="modal-close btn" style="margin-left: 10px;">
							<spring:message code="modal.no" />
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal 4 start   -->
	<div id="submitStock" class="modal">
		<h6 class="modal-header">
			<spring:message code="modal.header.submitStock" />
		</h6>
		<div class="modal-content">


			<div class="row">
				<h6 id="stockSuccessMessage"></h6>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<a onclick="redirectToViewPage()" class="btn"><spring:message
							code="modal.close" /></a>
				</div>
			</div>
		</div>
	</div>


	<div id="fileFormateModal" class="modal">
		<h6 class="modal-header">
			<spring:message code="fileValidationModalHeader" />
		</h6>
		<div class="modal-content">
			<div class="row">
				<h6 id="fileErrormessage">
					<spring:message code="fileValidationName" />
					<br> <br>
					<spring:message code="fileValidationFormate" />
					<br> <br>
					<spring:message code="fileValidationSize" />
				</h6>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<div class="input-field col s12 center">
						<button class="modal-close waves-effect waves-light btn"
							onclick="clearFileName()" style="margin-left: 10px;">
							<spring:message code="modal.ok" />
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>


	<div id="searchSupplierInformation" class="modal">
		<!-- <button class="modal-close btn-flat right" data-dismiss="modal">&times;</button> -->
		<a class="btn-flat right" onclick="closeAssigneTable()">&times;</a>
		<h6 class="modal-header">
			<spring:message code="searchAssigneMessage" />
		</h6>
		<div class="modal-content">

				<div class="row">

					<h5 class="center">
						<label> <input name="group1" type="radio" value="1"
							onclick="document.getElementById('submitbtn').style.display ='block';" />
							<span class="checkboxFont"> <spring:message
									code="AssigneeName" /></span></label> <label> <input name="group1"
							type="radio" value="2"
							onclick="document.getElementById('submitbtn').style.display ='block';" />
							<span class="checkboxFont"> <spring:message
									code="AssigneContactNumber" /></span>
						</label> <label> <input name="group1" type="radio" value="3"
							onclick="document.getElementById('submitbtn').style.display ='block';    " />
							<span class="checkboxFont"><spring:message
									code="AssigneEmailID" /> </span>
						</label>
					</h5>

				</div>

				<div class="row" id="submitbtn" style="display: none;">
					<div class="input-field col s12 m2">
						<label for="Search" class="center-align ml-10"> <spring:message
								code="searchField" /></label>
					</div>
					<div class="input-field col s12 m5">
						<input type="text" id="assigneDetails" name="assigneDetails"
							placeholder="<spring:message code="AssigneSearch" />" />
					</div>
					<div class="input-field col s12 m2">
						<button type="button" onclick="viewAssigneeHistory()" id="viewAssigneeHistoryBtn" class="btn"><spring:message
								code="button.submit" /></button>
					</div>
				</div>
		
			<!-- 
            <div class="row myRow" style="margin-top: 10px; display: none;" id="user123">
                
                <p class="center" style="color: red;">No data found</p>

            </div> -->
			<div class="row" id="assigneDetailsDiv" style="display: none">
				<table class="responsive-table striped display"
					id="assignee-data-table" cellspacing="0">
				</table>
			</div>
		</div>
	</div>

	<!-- END CONTENT -->

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
	<script type="text/javascript"
		src="${context}/resources/js/jquery-datepicker2.js"></script>


	<!--plugins.js - Some Specific JS codes for Plugin Settings-->
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

	<%--   <script type="text/javascript" src="${context}/resources/js/materialize-plugins/date_picker/picker.date.js"></script>
    <script type="text/javascript" src="${context}/resources/js/materialize-plugins/date_picker/picker.js"></script> --%>
	<!--custom-script.js - Add your own theme custom JS-->
	<script type="text/javascript" src="${context}/resources/js/plugins.js"></script>
	<script type="text/javascript"
		src="${context}/resources/js/Validator.js"></script>
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
		src="${context}/resources/project_js/profileInfoTab.js" async></script>
	<script type="text/javascript"
		src="${context}/resources/project_js/validationMsg.js"></script>

	<script>

var lang=window.parent.$('#langlist').val() == 'km' ? 'km' : 'en';
	window.parent.$('#langlist').on('change', function() {
		var language=window.parent.$('#langlist').val() == 'km' ? 'km' : 'en';
		window.location.assign("./openUploadStock?reqType=formPage&lang="+language);
	}); 
	$.i18n().locale = lang;
	var successMsg,invoiceNumberExist,assigneIdLabel,assigneNameLabel;
	$.i18n().load( {
		'en': './resources/i18n/en.json',
		'km': './resources/i18n/km.json'
	} ).done( function() { 
		successMsg=$.i18n('successMsg');
		invoiceNumberExist=$.i18n('invoiceNumberExist');
		   assigneIdLabel=$.i18n('assigneIdLabel');
		  assigneNameLabel=$.i18n('assigneNameLabel');
		 
		 var currentRoleTypeAssignei = $("body").attr("data-selected-roleType"); 
		 
			if(currentRoleTypeAssignei=='Importer'){
				$("#assigneDetailslink").css("display", "none"); 
				$("#supplierNameOrIdDiv").css("display", "block"); 
				$("#invoiceNumberDiv").css("display", "block"); 
			
			}
			
			
			else if(currentRoleTypeAssignei == 'Manufacturer')
				{
				
				$("#supplierNameOrIdDiv").css("display", "none"); 
				$("#invoiceNumberDiv").css("display", "none");
				$("#assigneDetailslink").css("display", "none");
				
				 $("#supplierId").attr("required", false);
				 $("#supplierName").attr("required", false);
				 $("#invoiceNumber").attr("required", false);
					$('#assigneDetailslink').text($.i18n('AssigneSearchLink'))
				}
			else if(currentRoleTypeAssignei == 'Custom'){
				
				$("#supplierNameOrIdDiv").css("display", "block"); 
				$("#invoiceNumberDiv").css("display", "block")
				$("#assigneDetailslink").css("display", "block"); 
				$('#SupplierIdAssignie').text('');
				$('#SupplierIdAssignie').text(assigneIdLabel);
			
				$('#SupplierIdAssignieName').text('');
				$('#SupplierIdAssignieName').text(assigneNameLabel);

				$("#SupplierIdAssignieName").append('<span class="star">*</span>');
				$('#uploaDStockHeader').text($.i18n('assignStock'));
				
				
			}
			else{
				
				
				
				$("#supplierNameOrIdDiv").css("display", "block"); 
				$("#invoiceNumberDiv").css("display", "block")
				$("#assigneDetailslink").css("display", "block"); 
				
				$('#assigneDetailslink').text($.i18n('AssigneSearchLink'));
				/* $('#SupplierIdAssignie').text('');
				$('#SupplierIdAssignie').text(assigneIdLabel);
			
				$('#SupplierIdAssignieName').text('');
				$('#SupplierIdAssignieName').text(assigneNameLabel);

				$("#SupplierIdAssignieName").append('<span class="star">*</span>'); */
			}
			
			$('div#initialloader').delay(300).fadeOut('slow');
	});


function uploadStock(){
	
	 var supplierId=$('#supplierId').val();
	 var supplierName=$('#supplierName').val();
	 var invoiceNumber=$('#invoiceNumber').val();
	var quantity=$('#Quantity').val();
	var deviceQuantity=$('#devicequantity').val();
	 
	 console.log("supplierId="+supplierId+" supplierName="+supplierName+" InvoiceNumber="+invoiceNumber+" quantity="+quantity)
	 var formData= new FormData();
		formData.append('file', $('#file')[0].files[0]);
	 	 formData.append('supplierId',supplierId);
	 	formData.append('supplierName',supplierName);
	 	formData.append('invoiceNumber',invoiceNumber);
	 	formData.append('quantity',quantity);
		formData.append('deviceQuantity',deviceQuantity);
	 	
	 $.ajax({
		url: './uploadStock',
		type: 'POST',
		data: formData,
		processData: false,
		contentType: false,
		success: function (data, textStatus, jqXHR) {
			
			 console.log(data);
			 $("#stockSubmitButton").prop('disabled', true);
			   $('#submitStock').openModal({
		    	   dismissible:false
		       });
			 if(data.errorCode=="0")
				 {
				 console.log("status code = 0");
				$('#stockSuccessMessage').text(successMsg);
			 $('#stockSuccessMessage').append(data.txnId);
			 //$('#errorCode').val(data.errorCode);
				 }
			 else if(data.errorCode=="3")
				 {
				console.log("status code = 3"); 
				$('#sucessMessage').text('');
				$('#sucessMessage').text(invoiceNumberExist);
				 $('#errorCode').val(data.errorCode);
				 }
			
			 else if(data.errorCode=="1"){
				    $('#stockSuccessMessage').text('');
					$('#stockSuccessMessage').text(data.message);
			 }
			 else if(data.errorCode=="5")
				 {
				 $('#stockSuccessMessage').text('');
					$('#stockSuccessMessage').text($.i18n('stockResponseMessage'));
				 }
			 
			 else{
				 $('#stockSuccessMessage').text('');
					$('#stockSuccessMessage').text(data.message);
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

function redirectToViewPage()
{

	 var roleType = $("body").attr("data-roleType");
	 var userId = $("body").attr("data-userID");
	 var currentRoleType = $("body").attr("data-selected-roleType"); 
	 var role = currentRoleType == null ? roleType : currentRoleType;
	 console.log(" userId="+userId+" role="+role);
	console.log("./assignDistributor?userTypeId="+role);
	var selectedRoleTypeId = $("body").attr("data-selectedRoleTypeId");
	
	
	 window.location.href = "./assignDistributor?userTypeId="+role+"&selectedRoleTypeId="+selectedRoleTypeId;
	

}

function fileTypeValueChanges(dd, ddd) {
	var uploadedFileName = $("#file").val();
	uploadedFileName = uploadedFileName.replace(/^.*[\\\/]/, '');
	var ext = uploadedFileName.split('.').pop();
	
	var fileSize = ($("#file")[0].files[0].size);
	//fileSize = Math.floor(fileSize/1000) + 'KB';
   	fileSize = (Math.round((fileSize / 1024) * 100) / 100);
	if (uploadedFileName.length > 30) {
	   
	   $('#fileFormateModal').openModal({
    	   dismissible:false
       });
      
       
   } 
	else if(ext!='csv')
		{
		
		 $('#fileFormateModal').openModal({
	    	   dismissible:false
	       });
		}
	else if(fileSize>='10000'){
		 $('#fileFormateModal').openModal({
	    	   dismissible:false
	       });
	}
	
	

}

function clearFileName() {
	$('#fileName').val('');
	$("#file").val('');
	$('#fileFormateModal').closeModal();
}

function openModalForAssigneId(){
	 $('#searchSupplierInformation').openModal({
  	   dismissible:false
     });
	
	//$('#searchSupplierInformation').openModal();
}

function viewAssigneeHistory() {
	assigneeTable("./headers?type=AssigneeStock","./AssigneeDetailsData");
};


function assigneeTable(URL,dataUrl){
	var formData= new FormData()
	//var requestType =	$('input[name="group1"]:checked').val();
   	var assigneDetails=$('#assigneDetails').val();
   $("#assigneDetailsDiv").css("display", "block"); 
   var selectedRoleTypeIdsss = $("body").attr("data-selectedRoleTypeId");
   var request={
		   "field":assigneDetails,
		   "type": parseInt($('input[name="group1"]:checked').val()),
		   "userTypeId" : $("body").attr("data-userTypeID"),
		   "roleTypeId":selectedRoleTypeIdsss
	}
	
	if(lang=='km'){
		var langFile="//cdn.datatables.net/plug-ins/1.10.20/i18n/Khmer.json";
	}
	$.ajax({
		url: URL,
		type: 'POST',
		dataType: "json",
		success: function(result){
			var table=	$("#assignee-data-table").DataTable({
				destroy:true,
				"serverSide": true,
				orderCellsTop : true,
				"ordering" : false,
				"bPaginate" : true,
				"bFilter" : false,
				"bInfo" : true,
				
				"oLanguage": {  
					"sUrl": langFile  
				},
				ajax: {
					url : dataUrl,
					type: 'POST',
					dataType: "json",
					data : function(d) {
						d.filter = JSON.stringify(request); 
					   console.log(JSON.stringify(request));
					}

				},
				"columns": result
			});
			$('div#initialloader').delay(300).fadeOut('slow');
		},
		error: function (jqXHR, textStatus, errorThrown) {
			console.log("error in ajax");
		}
	});


}

$('input:radio[name="group1"]').change(
	    function(){
	        if ($(this).is(':checked')) {
	        	$('#assigneDetails').val('')
	        	//$("input[name='group1']").attr("disabled","disabled");
	        }
	    });


function saveAssigneDetails(assigneId,assigneName)
{
	 //assigneName=assigneName.replace(/%20/g," " );
	 
	 assigneName=assigneName.split("+20").join(" ");
	// alert(assigneName);
	
	$('#searchSupplierInformation').closeModal();
	$("#supplierId").attr("placeholder","");
	$("#supplierName").attr("placeholder","");
	$('#supplierId').val(assigneId).prop('readonly', true);
	$('#supplierName').val(assigneName).prop('readonly', true);
	$("label[for='supplierId']").addClass('active');
	$("label[for='supplierName']").addClass('active');
	
}

$("input[type=file]").keypress(function(ev) {
    return false;
    //ev.preventDefault(); //works as well

});


function closeAssigneTable()
{
	$('#searchSupplierInformation').closeModal();
	 $("#assigneDetailsDiv").css("display", "none");
	
}

$(document).keyup(function(event) { 
	
	if (event.keyCode === 13) {
		
    	$("#viewAssigneeHistoryBtn").click();
  }
}); 
</script>

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