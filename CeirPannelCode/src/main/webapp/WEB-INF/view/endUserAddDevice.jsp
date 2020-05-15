
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<title>Upload Paid Status</title>
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
<jsp:include page="/WEB-INF/view/endUserHeader.jsp" ></jsp:include>
<jsp:include page="/WEB-INF/view/endUserFooter.jsp" ></jsp:include>

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
	href="${context}/resources/project_css/intlTelInput.css">

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>

<script src="http://malsup.github.io/jquery.blockUI.js"></script>
  <!------------------------------------------- Dragable Model---------------------------------->
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>


<style>
.row {
	margin-bottom: 0;
	margin-top: 0;
}

input[type=text] {
	height: 35px;
	margin: 0 0 5px 0;
}

.checkboxFont {
	font-size: 16px;
	margin-right: 10px;
}

textarea.materialize-textarea {
	padding: 0;
	padding-left: 10px;
}

.btn-flat {
	height: auto;
}

select option {
	color: #444;
}

#filterFileStatus {
	color: #444;
}

.dropdown-content li>a, .dropdown-content li>span {
	color: #444;
}

.welcomeMsg {
	padding-bottom: 50px !important;
	line-height: 1.5 !important;
	text-align: center;
}

.pay-tax-icon {
	font-size: 20px;
	color: #2e568b;
	margin: 0 7px;
}

.row {
	margin-top: 5px;
}

.section {
	padding-top: 0 !important;
}

label {
	font-size: 0.8rem;
}

input[type=text]:disabled+label {
	color: #444;
}

input::placeholder {
	color: #444;
}

select:disabled {
	color: #444;
}

/* .btn-info {
            margin-right: 1%;
        } */
input[type='search'] {
	background-image: url(images/search-512.jpg);
	background-position: 97% 7px;
	background-repeat: no-repeat;
	color: #444;
}
</style>
</head>
<body data-id="12" session-value="${not empty param.NID ? param.NID : 'null'}" data-roleType="${usertype}">

	<!-- START CONTENT -->
	<section id="content">
		<!--start container-->
		<div class="container">
			<div class="section">
				<div class="row">
					<div class="col s12 m12 l12">
						<div class="row card-panel register-device-responsive-page" style="margin: 5vh auto auto; display: block;" >
							<div class="container-fluid pageHeader">
								<p class="PageHeading"><spring:message code="registerconsignment.header.addDeviceInformation" /></p>
								<!-- <a href="#addDevice" class="boton right modal-trigger">Add Device</a> -->
							</div>
							<div id="user123" class="section">
								<form action="" onsubmit="return submitDeviceInfo()"
									method="POST" enctype="multipart/form-data">
									<div id="mainDeviceInformation" class="mainDeviceInformation">
										<div id="deviceInformation" class="deviceInformation">
											<div class="row">
												<div class="col s12 m12">
													<div class="col s12 m12" style="margin-top: 30px;">
														<h5><spring:message code="modal.deviceInfo" /></h5>
														<hr>
													</div>

													<div class="col s12 m6">
														<label for="deviceType1"><spring:message code="select.deviceType" /><span
															class="star"></span></label> <select class="browser-default"
															oninput="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
											                oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
															id="deviceType1">
															<option value="" disabled selected><spring:message code="select.selectDeviceType" />
																</option>


														</select>
													</div>

													<div class="col s12 m6">
														<label for="deviceIdType1"><spring:message code="select.deviceIDType" /><span
															class="star">*</span></label> <select required="required"
															oninput="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
											                oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
															class="browser-default" id="deviceIdType1">
															<option value="" disabled selected><spring:message code="select.selectDeviceIDType" /></option>

														</select>
													</div>

													<div class="col s12 m6">
														<label for="multipleSimStatus1"><spring:message code="select.multiSimStatus" /><span class="star"></span>
														</label> <select class="browser-default"
														oninput="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
											        oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
															id="multipleSimStatus1">
															<option value="" disabled selected><spring:message code="select.select" />
														</select>
													</div>

													<div class="col s12 m6">
														<label for="country1"><spring:message code="select.countryBoughtFrom" /><span
															class="star"></span></label> <select id="country1"
															oninput="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
											        oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
															class="browser-default" class="mySelect"
															style="padding-left: 0;" >
															<option value="" disabled selected><spring:message code="select.countryBoughtFrom" />
														</select>
													</div>

													<div class="input-field col s12 m6"
														style="margin-top: 28px;">
														<input type="text" id="serialNumber1" name="serialNumber"
															 pattern="[A-Za-z0-9]{0,15}"
															
															oninput="InvalidMsg(this,'input','<spring:message code="validation.15serialNo" />');"
											        oninvalid="InvalidMsg(this,'input','<spring:message code="validation.15serialNo" />');"
															title=""
															maxlength="15"> <label for="serialNumber1"> <spring:message code="input.deviceSerialNumber" /><span class="star"></span>
														</label>
													</div>

													<div class="col s12 m6">
														<label for="taxStatus1"><spring:message code="select.taxPaidStatus" /><span
															class="star">*</span></label> <select class="browser-default"
															required="required"
															oninput="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
											        oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
															 id="taxStatus1">
															<option value="" disabled selected><spring:message code="select.selectTaxPaidStatus" /></option>

														</select>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col s12 m12">
													<div class="col s12 m6" style="margin-top: -10px;">
														<label for="deviceStatus1"><spring:message code="select.deviceStatus" /><span
															class="star"></span></label> <select class="browser-default"
															oninput="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
											        oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
															id="deviceStatus1">
															<option value="" disabled selected><spring:message code="select.selectDeviceStatus" /></option>

														</select>
													</div>

													<div class="input-field col s12 m6 l6">
														<input type="text" name="Price" id="Price1"
														oninput="InvalidMsg(this,'input','<spring:message code="validation.7digits" />');"
											        oninvalid="InvalidMsg(this,'input','<spring:message code="validation.7digits" />');"
															pattern="[0-9]{0,7}"  maxlength="7">
														<label for="Price1"><spring:message code="select.price" /></label>
													</div>

													<div class="col s12 m6" id="CurrencyDiv" style="display: none;">
														<label for="Currency1"><spring:message code="input.currency" /><span class="star">*</span></label>
														<select class="browser-default" id="Currency1"
														oninput="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
											            oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');">
															<option value="" disabled selected><spring:message code="select.selectCurrency" /></option>
														 </select>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col s12 m12">
													<div class='col s12 m12 input_fields_wrap'>
														<p><spring:message code="title.imeiMeidEsn" /></p>
														<div class='row'>
															<div class="input-field col s12 m6">
																<input type="text" id="IMEIA1" name="IMEI1"
																	pattern="[0-9]{15,16}" required
																	oninput="InvalidMsg(this,'input','<spring:message code="validation.1516digit" />');"
											        oninvalid="InvalidMsg(this,'input','<spring:message code="validation.1516digit" />');"
																
																	maxlength="16"> <label for="IMEIA1"><spring:message code="title.one" /><span
																	class="star">*</span></label>
															</div>
															<div class="input-field col s12 m6">
																<input type="text" id="IMEIB1" name="IMEI2"
																	pattern="[0-9]{15,16}"
																	oninput="InvalidMsg(this,'input','<spring:message code="validation.1516digit" />');"
											        oninvalid="InvalidMsg(this,'input','<spring:message code="validation.1516digit" />');"
															
																	maxlength="16"> <label for="IMEIB1"><spring:message code="title.two" /></label>
															</div>

															<div class="input-field col s12 m6">
																<input type="text" id="IMEIC1" name="IMEIC3"
																	pattern="[0-9]{15,16}"
																	oninput="InvalidMsg(this,'input','<spring:message code="validation.1516digit" />');"
											        oninvalid="InvalidMsg(this,'input','<spring:message code="validation.1516digit" />');"
																
																	maxlength="16"> <label for="IMEIC1"><spring:message code="title.three" /></label>
															<p id="errorMsgOnModal" class="deviceErrorTitle" style="margin-top: -154px; margin-left: 104px;"></p>
															</div>

															<div class="input-field col s12 m6" id="field">
																<input type="text" id="IMEID1" name="IMEID4[]"
																	pattern="[0-9]{15,16}"
																	oninput="InvalidMsg(this,'input','<spring:message code="validation.1516digit" />');"
											        oninvalid="InvalidMsg(this,'input','<spring:message code="validation.1516digit" />');"
																	title="Please enter minimum 15 and maximum 16 digit only"
																	maxlength="16" id="field0"> <label for="IMEID1"><spring:message code="title.four" /></label>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="col s12 m12">
										<button class="btn right add_field_button"
											style="margin-top: 5px;">
											<span style="font-size: 20px;">+</span><spring:message code="button.addMoreDevice" />
										</button>
										<p>
											<spring:message code="input.requiredfields" /> <span class="star">*</span>
										</p>
									</div>

									<div class="col s12 m12 center" style="margin-top: 30px; padding-bottom: 50px;">
										<button class="btn " type="submit"> <spring:message code="button.submit" /></button>
										<button type='button' class="btn" id="redirectToPage" 
												style="margin-left: 10px;"><spring:message code="button.cancel" /></button>
									</div>

								</form>
								
								<form action="selfRegisterDevicePage" method="post" id="cancelAddDeviceForm">
								<input type="text" id="nationalIdForCancel" name="Search">
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--end container-->
	</section>
	
	<div id="regularisedDevice" class="modal">
		<h6 class="modal-header"><spring:message code="modal.header.registerdevice" /></h6>
		<div class="modal-content">
			<div class="row">
				<h6 id="sucessMessage"><spring:message code="modal.message.futureRef" />  <span id="dynamicTxnId"></span>
				</h6>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<div class="input-field col s12 center">
						<button class="btn" id="ok"><spring:message code="modal.ok" /></button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal End -->
	<script type="text/javascript"
		src="${context}/resources/js/materialize.js"></script>
	<script type="text/javascript"
		src="${context}/resources/js/plugins/data-tables/js/jquery.dataTables.js"></script>
	<script type="text/javascript"
		src="${context}/resources/js/plugins/data-tables/js/jquery.dataTables.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${context}/resources/js/plugins.js"></script>
	<script type="text/javascript"
		src="${context}/resources/js/Validator.js"></script>
	<script type="text/javascript"
		src="${context}/resources/js/plugins/perfect-scrollbar/perfect-scrollbar.min.js"></script>
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
		<script type="text/javascript" src="${context}/resources/js/intlTelInput.js"></script>
		<script type="text/javascript" src="${context}/resources/js/utils.js"></script>
	<script type="text/javascript"
		src="${context}/resources/project_js/endUserAddDevice.js"></script>
	<script type="text/javascript"
		src="${context}/resources/project_js/globalVariables.js"></script>
	
	<script type="text/javascript"
		src="${context}/resources/project_js/dragableModal.js"></script>

	<script type="text/javascript"
		src="${context}/resources/project_js/enterKey.js"></script>

	<%-- 	<script type="text/javascript"
		src="${context}/resources/js/intlTelInput.js"></script> --%>

	<script type="text/javascript"
		src="${context}/resources/project_js/ValidationFileOutsidePortal.js"></script>
		


<script type="text/javascript">
var input2 = document.querySelector("#phone");
window.intlTelInput(input2, {
	utilsScript : "${context}/resources/js/utils.js",
});
</script>

</body>
</html>
	
