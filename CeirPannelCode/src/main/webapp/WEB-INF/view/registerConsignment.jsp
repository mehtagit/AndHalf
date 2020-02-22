<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<title>Registration</title>

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
	href="${context}/resources/project_css/viewConsignment.css">
<link rel="stylesheet"
	href="${context}/resources/project_css/iconStates.css">

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>

<script src="http://malsup.github.io/jquery.blockUI.js"></script>


<style>
.row {
	margin-bottom: 0;
	margin-top: 0;
}

/* input[type=text] {
      height: 35px; 
      margin: 0 0 5px 0;
    } */
input
[
type
=
text
]
:not
 
(
.browser-default
 
)
{
font-size
:
 
13
px
;
/* height: 30px; */


}
input[type=date] {
	font-size: 0.8rem;
}

textarea.materialize-textarea {
	padding: 0;
	padding-left: 10px;
}

.btn-flat {
	height: auto;
}

#starColor {
	color: red;
}



section {
	margin-top: 10px;
}
</style>

</head>
<body>
	<!-- START MAIN -->

	<!-- START WRAPPER -->
	<div class="wrapper">

		<!-- START CONTENT -->
		<section id="content">
			<!--start container-->
			<div class="container">
				<div class="section">
					<div class="row">
						<div class="col s12 m12 l12">
							<div class="row card-panel">
								<form action="" onsubmit="return registerConsignment()"
									method="POST" enctype="multipart/form-data"
									id="registerConsignment">
									<div class="container-fluid pageHeader">
										<p class="PageHeading"><spring:message code="registerconsignment.view.header.title" /></p>
										<!-- <button type="button" class="waves-effect waves-light modal-trigger boton right"
                      data-target="modal1">Register Consignment</button> -->
									</div>

									<div class="row myRow">
										<div class="input-field col s12 m6">
											<input type="text" name="supplierId" id="supplierId"
												pattern="[A-Za-z0-9]{0,15}"
												title="<spring:message code="validation.15character" />"
												maxlength="15" /> <label for="supplierId" class="center-align"><spring:message code="input.supplier" /></label>
										</div>

										<div class="input-field col s12 m6">
											<input type="text" name="supplierName" id="supplierName"
												pattern="[A-Za-z  ]{0,50}" oninput="InvalidMsg(this,'input','<spring:message code="validation.50character" />');" oninvalid="InvalidMsg(this,'input','<spring:message code="validation.50character" />');"
												
												maxlength="50" required /> <label for="supplierName"
												class="center-align"><spring:message code="input.suppliername"/> <span
												class="star">*</span></label>
										</div>
									</div>
									<div class="row myRow">
										<div class="input-field col s12 m6">
											<input type="text" name="consignmentNumber"
												id="consignmentNumber" pattern="[A-Za-z0-9]{0,15}"
												title="<spring:message code="validation.15character" />"
												maxlength="15" /> <label for="consignmentNumber" class="center-align"><spring:message code="input.consignmentnumber" /></label>
										</div>

										<div class="input-field col s12 m6">
											<input type="text" name="expectedDispatcheDate"
												id='expectedDispatcheDate' class='form-control datepick'
												autocomplete='off'  onchange="InvalidMsg(this,'date','<spring:message code="validation.requiredMsg" />');checkDate(expectedDispatcheDate,expectedArrivaldate);" oninvalid="InvalidMsg(this,'date','<spring:message code="validation.requiredMsg" />');" 
												 required /> 
												<label
												for="expectedDispatcheDate" class="center-align"><spring:message code="input.dispatchdate" /> <span class="star">*</span>
											</label> <span class="input-group-addon" style="color: #ff4081"><i
												class="fa fa-calendar" aria-hidden="true"></i></span>
					<p id="errorMsg" style="color: red;font-size: 12px;position: absolute;margin: 0;top: 40px;right: 0;" class="left"></p>
										</div>
									</div>

									<div class="row myRow">
										<div class=" col s12 m6">
										<p style="margin: 0;font-size: 12px;"><spring:message code="input.country" /> <span class="star">*</span></p>
											<select id="country" name="organisationcountry" 
												 class="browser-default" class="mySelect" onchange="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');" oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
												style="padding-left: 0;"   required ></select>
										</div>


										<div class="input-field col s12 m6">
											<input name="expectedArrivaldate" id="expectedArrivaldate"
												type="text" class='form-control datepick' autocomplete='off' onchange="InvalidMsg(this,'date','<spring:message code="validation.requiredMsg" />');checkDate(expectedDispatcheDate,expectedArrivaldate);" oninvalid="InvalidMsg(this,'date','<spring:message code="validation.requiredMsg" />');"
												
												required /> <label for="expectedArrivaldate"
												class="center-align"><spring:message code="input.arrivaldate" /> <span
												class="star">*</span></label> <span class="input-group-addon"
												style="color: #ff4081"><i class="fa fa-calendar"
												aria-hidden="true"></i></span>
										</div>
									</div>

									<div class="row myRow">
										<div class="input-field col s12 m6">
										<input type="text" name="totalPrice" id="totalPrice" pattern="[0-9]{0,7}"
												maxlength="7" title="<spring:message code="validation.7character" />" /> <label for="totalPrice"
												class="center-align"><spring:message code="input.totalprice" /></label>
										</div>

										<div class="input-field col s12 m6">
											<input type="text" name="quantity" id="quantity"
												pattern="[0-9]{0,7}"
												
												maxlength="7"  oninput="InvalidMsg(this,'input','<spring:message code="validation.7character" />');" oninvalid="InvalidMsg(this,'input','<spring:message code="validation.7character" />');"  required/> <label for="quantity"
												class="center-align"><spring:message code="input.quantity" /> <span class="star">*</span></label>
										</div>


										<div class=" col s12 m6">
											
												<label for="expectedArrivalPort"><spring:message code="input.arrivalport" /> <span class="star">*</span></label>
											<select class="browser-default" id="expectedArrivalPort"  oninput="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');" oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');" required
												 name="expectedArrivalPort" >
												<%-- <spring:message code="input.arrivalport" /> --%>
												<option value="" disabled selected> <spring:message code="input.arrivalport" /></option>
											</select>
										</div>

										<div class="col s12 m6" id="currencyDiv">
											<label for="currency"><spring:message code="input.currency" /> <span class="star">*</span></label>
											<select class="browser-default" id="currency"  oninput="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"  oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');" name="currency" >
												<option value=""  selected><spring:message code="input.currency" /></option>
									
											</select>
										</div>
									</div>


									<div class="row myRow">
										<h6 class="file-upload-heading" style="margin-left: 15px;">
											<spring:message code="input.bulkdevice" /> <span class="star">*</span>
										</h6>
										<div class="file-field input-field col s12 m6"
											style="margin-top: 5px;">
											<div class="btn">
												<span><spring:message code="input.selectfile" /></span> <input type="file" onchange="fileTypeValueChanges(this,'fileType')"
													 name="file" id="file" accept=".csv"  
												oninput="InvalidMsg(this,'fileType','<spring:message code="validation.file" />');" oninvalid="InvalidMsg(this,'fileType','<spring:message code="validation.file" />');"  required />
											</div>
											<div class="file-path-wrapper">
												<input class="file-path validate responsive-file-div" id="fileName"
													type="text">
											</div>
										</div>
										<br> <br>
										<p style="margin-left: 15px;">
											<a href="./sampleFileDownload/3"><spring:message code="input.downlaod.sample" /></a>
										</p>
										<span><spring:message code="input.requiredfields" /> <span
											class="star">*</span></span>
									</div>


									<div class="row">
										<div class="input-field col s12 center">
						
											<button class=" btn" id="consignmentSubbmitButton" type="submit"><spring:message code="button.submit" /></button>
												
											<a href="#cancelMessage" class="btn modal-trigger"
												type="cancel" style="margin-left: 10px;"><spring:message code="button.cancel" /></a>


										</div>

									</div>
								</form>
							</div>

						</div>
					</div>

				</div>
			</div>
		</section>
	</div>


	<div id="submitConsignment" class="modal">
		<h6 class="modal-header"><spring:message code="modal.header.submitConsignment" /></h6>
		<div class="modal-content">
			<div class="row">
				<h6 id="sucessMessage"><spring:message code="modal.message.futureRef" /></h6>
				<input type="text" style="display: none" id="errorCode">
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<form action="${context}/Consignment/viewConsignment"
						id="closeOkPop" method="POST">
						<a onclick="closeConfirmation()" class="btn"><spring:message code="modal.ok" /></a>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div id="cancelMessage" class="modal">
		<h6 class="modal-header"> <spring:message code="modal.cancelrequest" /></h6>
		<div class="modal-content">
			<div class="row">
				<h6><spring:message code="modal.message" /></h6>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<div class="input-field col s12 center">
						<a href="${context}/Consignment/viewConsignment" class="btn"><spring:message code="modal.yes" /></a>
						<button class="modal-close waves-effect waves-light btn"
							style="margin-left: 10px;"><spring:message code="modal.no" /></button>
					</div>
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
						<button class="modal-close waves-effect waves-light btn" onclick="clearFileName()"
							style="margin-left: 10px;"><spring:message code="modal.ok" /></button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!--end container-->



	<!-- END MAIN -->


	<!--materialize js-->
	<script type="text/javascript"
		src="${context}/resources/js/materialize.js"></script>
	<script type="text/javascript"
		src="${context}/resources/js/plugins/data-tables/js/jquery.dataTables.js"></script>
	<script type="text/javascript"
		src="${context}/resources/js/plugins/data-tables/js/jquery.dataTables.min.js"></script>



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
		src="${context}/resources/project_js/htmlValidationi18n.js"></script>
	<script type="text/javascript"
		src="${context}/resources/project_js/registerConsignment.js"></script>
	<script type="text/javascript"
		src="${context}/resources/project_js/validationMsg.js"></script>
			<script type="text/javascript"
		src="${context}/resources/project_js/_dateFunction.js" async></script>
	
</body>
</html>