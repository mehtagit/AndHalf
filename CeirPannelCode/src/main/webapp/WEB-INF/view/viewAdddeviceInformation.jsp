<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setDateHeader("Expires", 0);
	response.setHeader("Pragma", "no-cache");
	/*  session.setMaxInactiveInterval(200); //200 secs
	 session.setAttribute("usertype", null); */
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
<title>View Add Device Information</title>

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
<body data-id="12" session-value="${not empty param.NID ? param.NID : 'null'}">

	<!-- START CONTENT -->
	<section id="content">
		<!--start container-->
		<div class="container">
			<div class="section">
				<div class="row">
					<div class="col s12 m12 l12">
						<div class="row card-panel">
							<div class="container-fluid pageHeader">
								<p class="PageHeading"><spring:message code="registerconsignment.header.addDeviceInformation" /></p>
								<!-- <a href="#addDevice" class="boton right modal-trigger">Add Device</a> -->
							</div>
							<div id="user123" class="section"> 
								<form action="" onsubmit="return submitDeviceInfo()" id="viewDeviceForm"
									method="POST" enctype="multipart/form-data">
																			<div class="row">
											<div class="col s12 m12">
												<div class="col s12 m12">
													<h5><spring:message code="input.personalInformation" /></h5>
													<hr>
												</div>
											</div>
											<div class="col s12 m12" style="margin-top: 20px;">
												<div class="input-field col s12 m4">
													<input type="text" id="nationalID" pattern="[A-Za-z0-9]{1,12}" 
													oninput="InvalidMsg(this,'input','<spring:message code="validation.12Character" />');"
											        oninvalid="InvalidMsg(this,'input','<spring:message code="validation.12Character" />');"
													 required maxlength="12" name="nationalID" placeholder="" disabled="disabled" value="" /> <label for="nationalID"
														class="center-align ml-10"><spring:message code="input.nidText" /></label>
												</div>

													<div class="col s12 m4" style="margin-top: -10px;">
															<label for="deviceType"><spring:message code="input.documenttype" /> <span
																class="star"></span></label> <select class="browser-default" disabled="disabled"
																id="doc_type" 
																oninput="InvalidMsg(this,'input','<spring:message code="validation.selectFieldMsg" />');"
											                    oninvalid="InvalidMsg(this,'input','<spring:message code="validation.selectFieldMsg" />');" required>
																<option value="" disabled selected><spring:message code="select.documenttype" /> </option>
															</select>
															
															<!-- <input type="text" id="docTypeNymericValue" style="display: none" > -->
														</div>	

												<div class="file-field col s12 m4"
													style="margin-top: -15px;">
													<h6 style="color: #000;"><spring:message code="input.uploadNidProof" /> <span class="star">*</span>
													</h6>
													<div class="">
														 <input type="text" disabled="disabled"
														oninput="InvalidMsg(this,'fileType','<spring:message code="validation.NoChosen" />');"
											oninvalid="InvalidMsg(this,'fileType','<spring:message code="validation.NoChosen" />');"
														 onchange="isImageValid('csvUploadFile')"
														title= "<spring:message code="validation.NoChosen" />" required id="csvUploadFile" accept="*image">
													</div>
													<!-- <div class="file-path-wrapper">
														<input class="file-path validate responsive-file-div" id="csvUploadFileName"
															type="text">
													</div> -->
												</div>
											</div>
											<div class="col s12 m12">
												<div class="input-field col s12 m4 l4">
													<input type="text" name="firstName" id="firstName" disabled="disabled"
														maxlength="20" required="required"
														pattern="[A-Za-z]{0,20}"
														oninput="InvalidMsg(this,'input','<spring:message code="validation.20Character" />');"
											            oninvalid="InvalidMsg(this,'input','<spring:message code="validation.20Character" />');"
														 required>
													<label for="firstName" class="center-align"><spring:message code="input.firstName" /> <span class="star"></span>
													</label>
												</div>

												<div class="input-field col s12 m4 l4">
													<input type="text" name="middleName" id="middleName" disabled="disabled"
														maxlength="20" pattern="[A-Za-z]{0,20}"
														oninput="InvalidMsg(this,'input','<spring:message code="validation.20Character" />');"
											            oninvalid="InvalidMsg(this,'input','<spring:message code="validation.20Character" />');"
														>
													<label for="middleName"><spring:message code="input.middleName" /></label>
												</div>

												<div class="input-field col s12 m4 l4">
													<input type="text" name="lastName" id="lastName" disabled="disabled"
														 pattern="[A-Za-z]{0,20}"
														oninput="InvalidMsg(this,'input','<spring:message code="validation.20Character" />');"
											            oninvalid="InvalidMsg(this,'input','<spring:message code="validation.20Character" />');"
														title= "<spring:message code="validation.20Character" />" required
														maxlength="20"> <label for="lastName"><spring:message code="input.lastName" /> <span class="star"></span>
													</label>
												</div>
											</div>
										</div>

										<div class="row">
											<div class="col s12 m12">
												<div class="input-field col s12 m12 l12">
													<input type="text" name="address" disabled="disabled"
														pattern="[^[a-zA-Z0-9\s,'-]*$]{0,200}"
														oninput="InvalidMsg(this,'input','<spring:message code="validation.200characters" />');"
											            oninvalid="InvalidMsg(this,'input','<spring:message code="validation.200characters" />');"
														 maxlength="200" required
														class="form-control boxBorder boxHeight" id="address">
													<label for="address"><spring:message code="input.address" /> <span
														class="star"></span></label>
												</div>

												<div class="input-field col s12 m6 l6">
													<input type="text" pattern="[^[a-zA-Z0-9\s,'-]*$]{0,20}" disabled="disabled"
														oninput="InvalidMsg(this,'input');" oninvalid="InvalidMsg(this,'input');"
														title= "<spring:message code="validation.20Character" />"
														name="streetNumber"
														class="form-control boxBorder boxHeight" id="streetNumber"
														maxlength="20" required/> <label
														for="streetNumber"> <spring:message code="input.streetNumber" /> <span
														class="star"></span>
													</label>
												</div>
													<div class="input-field col s12 m6 l6">
													<input type="text" pattern="[^[a-zA-Z0-9\s,'-]*$]{0,30}" disabled="disabled"
														oninput="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');"
											            oninvalid="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');"
														name="streetNumber"
														class="form-control boxBorder boxHeight" id="village"
														maxlength="30" required/> <label
														for="village"> <spring:message code="input.village" /> <span
														class="star"></span>
													</label>
												</div>
												

												<div class="input-field col s12 m6 l6">
													<input type="text" name="locality" disabled="disabled"
														pattern="[^[a-zA-Z0-9\s,'-]*$]{0,30}"
														oninput="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');"
											            oninvalid="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');"
														
														class="form-control boxBorder boxHeight" id="locality"
														maxlength="30" required/> <label
														for="locality"> <spring:message code="input.locality" /> <span class="star"></span>
													</label>
												</div>

													<div class="input-field col s12 m6 l6">
													<input type="text" pattern="[^[a-zA-Z0-9\s,'-]*$]{0,30}" disabled="disabled"
														oninput="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');"
											            oninvalid="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');"
														 name="streetNumber"
														class="form-control boxBorder boxHeight" id="district"
														maxlength="30" required> <label
														for="district"> <spring:message code="input.district" /> <span
														class="star"></span>
													</label>
												</div>
												<div class="input-field col s12 m6 l6">
													<input type="text" pattern="[^[a-zA-Z0-9\s,'-]*$]{0,30}" disabled="disabled"
														oninput="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');"
											            oninvalid="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');"
														 name="streetNumber"
														class="form-control boxBorder boxHeight" id="commune"
														maxlength="30" required/> <label
														for="commune"> <spring:message code="input.commune" /> <span
														class="star"></span>
													</label>
												</div>
												<div class="input-field col s12 m6 l6">
													
													<input type="text" pattern="[0-9]{1,6}" disabled="disabled"
														oninput="InvalidMsg(this,'input','<spring:message code="validation.postalcode" />');"
											            oninvalid="InvalidMsg(this,'input','<spring:message code="validation.postalcode" />');"
														title= "<spring:message code="validation.postalcode" />" name="streetNumber"
														class="form-control boxBorder boxHeight" id="postalcode"
														maxlength="6" required/> <label
														for="postalcode"> <spring:message code="input.postalCode" /> <span
														class="star"></span>
													</label>
												</div>
												<div class="input-field col s12 m6 l6">
													<p
														style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">
														<spring:message code="table.country" /> <span class="star"></span>
													</p>
													<select id="country" class="browser-default"
														class="mySelect" disabled="disabled"
														oninput="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
											            oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
														title= "<spring:message code="validation.selectFieldMsg" />"
														 style="padding-left: 0;" required></select>
												</div>

												<div class="input-field col s12 m6 l6">
													<p
														style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">
													<spring:message code="input.province" /> <span class="star"></span>
													</p>
													<select id="state" class="browser-default" class="mySelect" disabled="disabled"
													oninput="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
											        oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
													title= "<spring:message code="validation.selectFieldMsg" />"
														style="padding-left: 0;" required></select>
												</div>
											</div>

											<div class="col s12 m12" style="margin-top: 10px;">
												<div class="input-field col s12 m6 l6">
														<input type="email" name="email" id="email"  disabled="disabled"
														oninput="InvalidMsg(this,'input','<spring:message code="validation.emial" />');"
											            oninvalid="InvalidMsg(this,'input','<spring:message code="validation.emial" />');"
														title= "<spring:message code="validation.emial" />" maxlength="30"> <label for="email"><spring:message code="input.email" /><span
														class="star"></span></label>
												</div>

												<div class="input-field col s12 m6 l6" style="margin-top: 18px;">
													<input type="text" name="phone"  disabled="disabled"
														pattern="[0-9]{10,10}"
														oninput="InvalidMsg(this,'input','<spring:message code="validation.10digits" />');"
											            oninvalid="InvalidMsg(this,'input','<spring:message code="validation.10digits" />');"
														title= "<spring:message code="validation.10digits" />" required  class="form-control boxBorder boxHeight" id="phone"
														maxlength="10"> <label for="phone"><spring:message code="input.contactNum" /><span class="star"></span>
													</label>
												</div>
											</div>
										</div>
									
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
															class="star"></span></label>
															<input type="text" value="${viewInformation.deviceTypeInterp}" disabled="disabled">
													</div>

													<div class="col s12 m6">
														<label for="deviceIdType1"><spring:message code="select.deviceIDType" /><span
															class="star"></span></label> 
															<input type="text" value="${viewInformation.deviceIdTypeInterp}" disabled="disabled">
													</div>

													<div class="col s12 m6">
														<label for="multipleSimStatus1"><spring:message code="select.multiSimStatus" /><span class="star"></span>
														</label> <input type="text" value="${viewInformation.deviceIdTypeInterp}" disabled="disabled">
													</div>

													<div class="col s12 m6">
														<label for="country1"><spring:message code="select.countryBoughtFrom" /><span
															class="star">*</span></label> 
															<input type="text" value="${viewInformation.country}" disabled="disabled">
													</div>

													<div class="input-field col s12 m6"
														style="margin-top: 28px;">
														<input type="text" value="${viewInformation.deviceSerialNumber}" disabled="disabled">
														 <label for="serialNumber1"> <spring:message code="input.deviceSerialNumber" /><span class="star"></span>
														</label>
													</div>

													<div class="col s12 m6">
														<label for="taxStatus1"><spring:message code="select.taxPaidStatus" /><span
															class="star"></span></label> <input type="text" disabled="disabled" value="${viewInformation.taxPaidStatusInterp}">
															
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col s12 m12">
													<div class="col s12 m6" style="margin-top: -10px;">
														<label for="deviceStatus1"><spring:message code="select.deviceStatus" /><span
															class="star"></span></label> 
														<input type="text" disabled="disabled" value="${viewInformation.taxPaidStatusInterp}">	
													</div>

													<div class="input-field col s12 m6 l6">
														<input type="text" value="${viewInformation.price}" disabled="disabled">
														<label for="Price1"><spring:message code="select.price" /></label>
													</div>

													<div class="col s12 m6">
														<label for="Currency1"><spring:message code="input.currency" /><span class="star"></span></label>
														<input type="text" value="${viewInformation.currencyInterp}" disabled="disabled">
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col s12 m12">
													<div class='col s12 m12 input_fields_wrap'>
														<p><spring:message code="title.imeiMeidEsn" /></p>
														<div class='row'>
															<div class="input-field col s12 m6">
																<input type="text" value="${viewInformation.firstImei}" disabled="disabled">
																 <label for="IMEIA1"><spring:message code="title.one" /><span
																	class="star"></span></label>
															</div>
															<div class="input-field col s12 m6">
																<input type="text" value="${viewInformation.secondImei}" disabled="disabled">
																 <label for="IMEIB1"><spring:message code="title.two" /></label>
															</div>

															<div class="input-field col s12 m6">
																<input type="text" value="${viewInformation.thirdImei}" disabled="disabled">
																 <label for="IMEIC1"><spring:message code="title.three" /></label>
															</div>

															<div class="input-field col s12 m6" id="field">
																<input type="text" value="${viewInformation.fourthImei}" disabled="disabled">
																 <label for="IMEID1"><spring:message code="title.four" /></label>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="col s12 m12">
										<%-- <button class="btn right add_field_button"
											style="margin-top: 5px;">
											<span style="font-size: 20px;">+</span><spring:message code="button.addMoreDevice" />
										</button> --%>
										<p>
											<spring:message code="input.requiredfields" /> <span class="star"></span>
										</p>
									</div>

									<div class="col s12 m12 center" style="margin-top: 30px;">
										<%-- <button class="btn " type="submit"> <spring:message code="button.submit" /></button> --%>
										<button type='button' class="btn" id="redirectToPage" 
												style="margin-left: 10px;"><spring:message code="button.cancel" /></button>
									</div>

								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--end container-->
	</section>
	<!-- END CONTENT -->
	</div>
	<!-- END WRAPPER -->

	</div>
	<!-- END MAIN -->



	<!-- //////////////////////////////////////////////////////////////////////////// -->

	<!-- START FOOTER -->
	<!-- <footer class="page-footer"
		style="position: fixed; bottom: 0; width: 100%;">
		<div class="footer-copyright">
			<div class="container">
				<span class="right">Copyright Ã‚Â© 2018 Sterlite Technologies
					Ltd, All rights reserved.</span>
			</div>
		</div>
	</footer> -->
	<!-- END FOOTER -->

	<!-- Modal 2 start   -->

	<div id="payNowTaxPayment" class="modal">
		<div class="modal-content">
			<h6 class="modal-header"><spring:message code="registerconsignment.header.addDeviceInformation" /></h6>
			<div class="row">
				<h6><spring:message code="modal.regularizedDevice" /></h6>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<div class="input-field col s12 center">
						<button class="modal-close btn"><spring:message code="modal.ok" /></button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal End -->

	<!-- Modal 2 start   -->

	<div id="needToPayTax" class="modal">
		<div class="modal-content">
			<h6 class="modal-header"><spring:message code="modal.payTaxInfo" /></h6>
			<div class="row">
				<h6><spring:message code="modal.needTaxPay" /></h6>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<div class="input-field col s12 center">
						<button class="modal-close btn"> <spring:message code="button.payNow" /></button>
						<button class="modal-close btn" style="margin-left: 10px;"><spring:message code="button.payLater" /></button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal End -->

	<!-- Pay Tax Modal start   -->

	<div id="payTaxModal" class="modal">
		<div class="modal-content">
			<h6 class="modal-header"><spring:message code="modal.payTaxInfo" /></h6>
			<div class="row">
				<h6> <spring:message code="modal.taxHasBeenPaid" /></h6>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<div class="input-field col s12 center">
						<button class="modal-close modal-trigger btn"
							data-target="payNowTaxPayment"><spring:message code="modal.yes" /></button>
						<button class="modal-close btn" style="margin-left: 10px;"><spring:message code="modal.no" /></button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Pay Tax Modal End -->

	
	
	<!-- Modal 2 start   -->

	<div id="addDevice" class="modal">
		<div class="modal-content">
			<h6 class="modal-header"><spring:message code="button.adddevice" /></h6>
			<div class="row">
				<div class="row">

					<div class="col s12 m6">
						<label for="deviceType"><spring:message code="select.deviceType" /><span class="star">*</span></label>
						<select class="browser-default" id="deviceType">
							<option value="" disabled selected><spring:message code="select.deviceType" /></option>
							<option value="Handheld">Handheld</option>
							<option value="MobilePhone">Mobile Phone/Feature phone</option>
							<option value="Vehicle">Vehicle</option>
							<option value="Portable">Portable(include PDA)</option>
							<option value="Module">Module</option>
							<option value="Dongle">Dongle</option>
							<option value="WLAN">WLAN Router</option>
							<option value="Modem">Modem</option>
							<option value="Smartphone">Smartphone</option>
							<option value="Computer">Connected Computer</option>
							<option value="Tablet">Tablet</option>
							<option value="e-Book">e-Book</option>
						</select>
					</div>

					<div class="col s12 m6">
						<label for="deviceType"><spring:message code="select.multiSimStatus" /> <span
							class="star">*</span></label> <select class="browser-default"
							id="deviceType">
							<option value="" disabled selected><spring:message code="select.multiSimStatus" /></option>
							<option value="Yes">Yes</option>
							<option value="No">No</option>
						</select>
					</div>

					<div class="col s12 m6">
						<label for="country"><spring:message code="select.countryBoughtFrom" /> <span
							class="star">*</span></label> <select id="country"
							class="browser-default" class="mySelect" style="padding-left: 0;"
							required></select>
					</div>

					<div class="input-field col s12 m6" style="margin-top: 25px;">
						<input type="text" id="serialNumber" name="serialNumber"
							pattern="[0-9]"
							title="Please enter your device serial number first"
							maxlength="20"> <label for="serialNumber"><spring:message code="input.deviceSerialNumber" /><span class="star">*</span>
						</label>
					</div>

					<div class="col s12 m6">
						<label for="deviceType"><spring:message code="select.taxPaidStatus" /><span class="star">*</span></label>
						<select class="browser-default" id="deviceType">
							<option value="" disabled selected><spring:message code="select.taxPaidStatus" /></option>
							<option value="Regularized">Regularized</option>
							<option value="Paid">Paid</option>
							<option value="NotPaid">Not Paid </option>
						</select>
					</div>
				</div>
				<div class="row">
					<div class="col s12 m12">
						<p style="margin-bottom: 0;"><spring:message code="title.imeiMeidEsn" /></p>
					</div>
					<div class="input-field col s12 m6">
						<input type="text" id="IMEI1" name="IMEI1" pattern="[0-9]"
							title="Please enter minimum 15 and maximum 16 digit only"
							maxlength="16"> <label for="IMEI1"><spring:message code="title.one" /><span
							class="star">*</span></label>
					</div>

					<div class="input-field col s12 m6">
						<input type="text" id="IMEI2" name="IMEI2" pattern="[0-9]"
							title="Please enter minimum 15 and maximum 16 digit only"
							maxlength="16"> <label for="IMEI2"><spring:message code="title.two" /></label>
					</div>

					<div class="input-field col s12 m6">
						<input type="text" id="IMEI3" name="IMEI3" pattern="[0-9]"
							title="Please enter minimum 15 and maximum 16 digit only"
							maxlength="16"> <label for="IMEI3"><spring:message code="title.three" /></label>
					</div>

					<div class="input-field col s12 m6">
						<input type="text" id="IMEI4" name="IMEI4[]" pattern="[0-9]"
							title="Please enter minimum 15 and maximum 16 digit only"
							maxlength="16"> <label for="IMEI4"><spring:message code="title.four" /></label>
					</div>

					<div class="col s12 m12">
						<p><spring:message code="input.requiredfields" /><span class="star">*</span>
						</p>
					</div>

					<div class="col s12 m12 center" style="margin-top: 10px;">
						<button class="btn modal-trigger modal-close"
							data-target="submitMsg"><spring:message code="button.submit" /></button>
						<button class="btn modal-trigger modal-close"
							data-target="cancelMsg" style="margin-left: 10px;"><spring:message code="button.cancel" /></button>
					</div>

				</div>
			</div>
		</div>
	</div>
	<!-- Modal End -->

	<!-- Modal 2 start   -->

	<div id="viewDeviceInformation" class="modal">
		<div class="modal-content">
			<h6 class="modal-header"><spring:message code="modal.viewDeviceInfo" /></h6>
			<div class="row">
				<div class="row">

					<div class="col s12 m6">
						<label for="deviceType"><spring:message code="select.deviceType" /><span class="star">*</span></label>
						<select class="browser-default" id="deviceType" disabled>
							<option value="MobilePhone"><spring:message code="select.mobileFeature" /></option>
						</select>
					</div>

					<div class="col s12 m6">
						<label for="deviceType"><spring:message code="select.multiSimStatus" /><span
							class="star">*</span></label> <select class="browser-default"
							id="deviceType" disabled>
							<option value="Yes">Yes</option>
						</select>
					</div>

					<div class="col s12 m6" style="margin-top: 3px;">
						<label for="country"><spring:message code="select.countryBoughtFrom" /><span
							class="star">*</span></label> <input type="text" id="country"
							name="country" placeholder="" disabled>
					</div>

					<div class="input-field col s12 m6" style="margin-top: 25px;">
						<input type="text" id="serialNumber" name="serialNumber"
							pattern="[0-9]"
							title="Please enter your device serial number first"
							maxlength="20" placeholder="" disabled> <label
							for="serialNumber"><spring:message code="input.deviceSerialNumber" /><span
							class="star">*</span></label>
					</div>

					<div class="col s12 m6">
						<label for="deviceType"> <spring:message code="select.taxPaidStatus" /><span class="star">*</span></label>
						<select class="browser-default" id="deviceType" disabled>
							<option value="Regularized"> <spring:message code="select.regularized" /></option>
						</select>
					</div>
				</div>
				<div class="row input_fields_wrap">
					<div class="col s12 m12">
						<p style="margin-bottom: 0;"><spring:message code="title.imeiMeidEsn" /></p>
					</div>
					<div class="input-field col s12 m6">
						<input type="text" id="IMEI1" name="IMEI1" pattern="[0-9]"
							title="Please enter minimum 15 and maximum 16 digit only"
							maxlength="16" placeholder="" disabled> <label
							for="IMEI1"><spring:message code="title.one" /><span class="star">*</span></label>
					</div>

					<div class="input-field col s12 m6">
						<input type="text" id="IMEI2" name="IMEI2" pattern="[0-9]"
							title="Please enter minimum 15 and maximum 16 digit only"
							maxlength="16" placeholder="" disabled>
						<label for="IMEI2"><spring:message code="title.two" /></label>
					</div>

					<div class="input-field col s12 m6">
						<input type="text" id="IMEI3" name="IMEI3" pattern="[0-9]"
							title="Please enter minimum 15 and maximum 16 digit only"
							maxlength="16"> <label for="IMEI3"><spring:message code="title.three" /></label>
					</div>

					<div class="input-field col s12 m6">
						<input type="text" id="IMEI4" name="IMEI4[]" pattern="[0-9]"
							title="Please enter minimum 15 and maximum 16 digit only"
							maxlength="16"> <label for="IMEI4"><spring:message code="title.four" /></label>
					</div>

					<div class="col s12 m12 center" style="margin-top: 10px;">
						<button class="btn modal-close" style="margin-left: 10px;"><spring:message code="modal.close" /></button>
					</div>

				</div>
			</div>
		</div>
	</div>
	<!-- Modal End -->

	<!-- Modal start   -->

	<div id="cancelMsg" class="modal">
		<h6 class="modal-header"><spring:message code="modal.cancelrequest" /></h6>
		<div class="modal-content">

			<div class="row">
				<h6><spring:message code="modal.dataWillBeLost" /></h6>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<div class="input-field col s12 center">
						<!-- <button class="modal-close btn">yes</button> -->
						<a href="./uploadPaidStatus" class="btn"><spring:message code="modal.yes" /></a>
						<button class="modal-close btn" style="margin-left: 10px;"><spring:message code="modal.no" /></button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal End -->

	<!-- Modal start   -->

	<div id="regularisedDevice" class="modal">
		<h6 class="modal-header"><spring:message code="modal.uploadPaidDevice" /></h6>
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

	<div id="deleteMsg" class="modal">
		<h6 class="modal-header"><spring:message code="modal.header.delete" /></h6>
		<div class="modal-content">

			<div class="row">
				<h6><spring:message code="modal.deleteDeviceInfo" /></h6>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<button class="modal-close modal-trigger btn"
						data-target="confirmDeleteMsg"><spring:message code="modal.yes" /></button>
					<button class="modal-close btn" style="margin-left: 10px;"><spring:message code="modal.no" /></button>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal End -->

	<!-- Modal start   -->

	<div id="confirmDeleteMsg" class="modal">
		<h6 class="modal-header"> <spring:message code="modal.header.delete" /></h6>
		<div class="modal-content">

			<div class="row">
				<h6><spring:message code="modal.deviceInfoDeleted" /></h6>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<button class="modal-close btn"><spring:message code="modal.ok" /></button>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal End -->

	<!-- Modal start   -->

	<div id="submitMsg" class="modal">
		<h6 class="modal-header"> <spring:message code="modal.submitRequest" /></h6>
		<div class="modal-content">

			<div class="row">
				<h6><spring:message code="modal.requestSubmit" /></h6>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<div class="input-field col s12 center">
						<button class="modal-close btn"><spring:message code="modal.ok" /></button>
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

	<!-- ================================================
    Scripts
    ================================================ -->

<script type="text/javascript">
$("#viewDeviceForm").find("input,select,textarea,button").prop("disabled",false);
</script>
</body>
</html>
<%
	} else {
		/*  request.setAttribute("msg", "  *Please login first");
		request.getRequestDispatcher("./index.jsp").forward(request, response); */
%>
<script language="JavaScript">
	sessionStorage.setItem("loginMsg",
			"*Session has been expired");
	window.top.location.href = "./login";
	
	
</script>
<%
	}
%>