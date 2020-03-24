<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setDateHeader("Expires", 0);
	response.setHeader("Pragma", "no-cache");
	/*  session.setMaxInactiveInterval(200); //200 secs
	 session.setAttribute("usertype", null);  */
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
<title>Stolen</title>

<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta content="" name="description" />
<meta content="" name="author" />

		
<script type="text/javascript"
	src="${context}/resources/js/plugins/jquery-1.11.2.min.js"></script>
		<script type="text/javascript" src="${context}/resources/js/intlTelInput.js"></script>
		<script type="text/javascript" src="${context}/resources/js/utils.js"></script>
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
	href="${context}/resources/project_css/stolenRecovery.css">
<link rel="stylesheet"
	href="${context}/resources/project_css/iconStates.css">
<link rel="stylesheet"
	href="${context}/resources/project_css/intlTelInput.css">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>

<style>
.checkboxFont {
	color: #444;
	font-size: 16px;
	margin-right: 10px;
}

.section {
	padding-top: 0.5rem;
}

.welcomeMsg {
	padding-bottom: 50px !important;
	line-height: 1.5 !important;
	text-align: center;
}

.file-label {
	font-size: 0.9rem;
}

.contact-label {
	margin-top: -17px;
	margin-bottom: 0;
	font-size: 0.8rem;
}

select {
			margin-bottom: 5px;
			height: 2.2rem;
		}
		
		.iti--allow-dropdown input, .iti--allow-dropdown input[type=text] {
			margin-bottom: 5px !important;
		}
</style>


</head>

<body data-id="5" data-roleType="${usertype}" data-userTypeID="${usertypeId}"
	data-userID="${userid}" data-operatorTypeId="${operatorTypeId}"
	data-selected-roleType="${stolenselectedUserTypeId}"
	data-stolenselected-roleType="${stolenselectedUserTypeId}">





	<section id="content">
		<div id="initialloader"></div>
		<!--start container-->
		<div class="container">
			<div class="section">
				<div class="row">
					<div class="col s12 m12 l12">
						<div class="row card-panel">
							<div id="reportBlockUnblock">
								<div class="container-fluid pageHeader">
									<p id="headingType" class="PageHeading"><spring:message code="registration.updatereportstolen" /></p>
								</div>

								<div class="row">
								<!-- 	<div class="col s12">
										<ul class="tabs">
											<li class="tab col s3"><a class="active"
												onclick="showSingleFormDiv()">Individual</a></li>
											<li class="tab col s3"><a onclick="showBulkFormDiv()">Company/Organisation/Government</a></li>
										</ul>
									</div> -->
									<div id="SingleForm" class="col s12"
										style="margin-top: 30px; display: block">
										<form action="" id="SingleImeiBlockform" onsubmit="return updateIndivisualStolen()" method="POST" enctype="multipart/form-data">
											<div class="row">

												<div class="col s12 m12">
													<h5><spring:message code="input.personalInformation" /></h5>
													<input type="text" id="existingStolenTxnId" style="display:none" value="${stolenTxnId}" >
													<hr>
												</div>

												<div class="col-s12 m12">
													<div class="input-field col s12 m4">
														<input type="text" name="singleStolenfirstName" placeholder=""
														required="required" pattern="[a-zA-Z ]{1,30}"
														oninput="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');"
													oninvalid="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');"
														 title="" maxlength="30"
															id="singleStolenfirstName"> <label
															for="singleStolenfirstName"><spring:message code="input.firstName" /> <span
															class="star">*</span></label>
															
													</div>
<input type="text" id="pageViewType" value="${viewType}" style="display: none;">
<input type="text" id="pageName" value="${pageName}" style="display: none;">
													<div class="input-field col s12 m4">
														<input type="text" name="middleName" placeholder=""
														pattern="[a-zA-Z]{1,30}" title=""
														oninput="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');"
													oninvalid="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');"
														 maxlength="30"	id="singleStolenmiddleName"> <label
															for="middleName"><spring:message code="input.middleName" /></label>
													</div>

													<div class="input-field col s12 m4">
														<input type="text" name="lastName" placeholder=""
														required="required" pattern="[a-zA-Z]{1,30}"
														 title=""
														 oninput="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');"
													oninvalid="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');"
													 maxlength="30"
															id="singleStolenlastName"> <label for="lastName">
															<spring:message code="input.lastName" /> <span class="star">*</span>
														</label>
													</div>

													<!-- <div class="input-field col s12 m6">
                                                                <input type="text" name="nationality" id="nationality">
                                                                <label for="nationality">Nationality <span class="star">*</span></label>
                                                            </div> -->

													<!-- <div class="col s12 m6 l6" style="margin-top: -7px;">
                                                                <label for="country">Nationality <span class="star">*</span></label>
                                                                <select id="country" class="browser-default" class="mySelect"
                                                                    style="padding-left: 0;" required></select>
                                                            </div> -->

													<div class="file-field col s12 m6 l6"
														style="margin-top: -8px;">
														<h6 class="form-label">
															<spring:message code="registration.uploadnid/passportimage" /> <span class="star"></span>
														</h6>
														<div class="btn" id="passportImageDiv">
															<span id="passportImageText"><spring:message code="input.selectfile" /></span> <input type="file" accept="*image"
															oninput="InvalidMsg(this,'fileType','<spring:message code="validation.NoChosen" />');"
																oninvalid="InvalidMsg(this,'fileType','<spring:message code="validation.NoChosen" />');"
																
																 onchange="isImageValid('singleStolenFile')" id="singleStolenFile">
														</div>
														<div class="file-path-wrapper">
															<input class="file-path validate" type="text" 
																placeholder="<spring:message code="registration.uploadnid/passportimage" />"
																id="singleStolenFileName" 
																title="">
																<a href="#" id="PassportNidLink" class="imgPreviewLink" style="display: none;">Preview</a>
														</div>
													</div>

													<div class="input-field col s12 m6"
														style="margin-top: 22px;">
														<input type="text" name="nIDPassportNumber" placeholder=""
														required="required" pattern="[A-Za-z0-9]{1,15}"
														 title="" 
														 oninput="InvalidMsg(this,'input','<spring:message code="validation.15alphanumeric" />');"
															oninvalid="InvalidMsg(this,'input','<spring:message code="validation.15alphanumeric" />');"
														 maxlength="15"
															id="singleStolennIDPassportNumber"> <label
															for="nIDPassportNumber"> <spring:message code="registration.nid/passportnumber" /> <span
															class="star">*</span></label>
													</div>

													<div class="input-field col s12 m6 l6">
														<input type="email" name="email" id="singleStolenemail" placeholder=""
															pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" title=""
															oninput="InvalidMsg(this,'email','<spring:message code="validation.emial" />');"
															oninvalid="InvalidMsg(this,'email','<spring:message code="validation.emial" />');"
												         maxlength="30"> <label for="email"><spring:message code="input.email" />
														</label>
													</div>

													<div class="input-field col s12 m6 l6">
														<p class="contact-label">
															<spring:message code="registration.altcontactnumber" /> <span class="star">*</span>
														</p>
														<input type="text" name="phone" id="singleStolenphone1" required="required" pattern="[0-9 + ]{1,15}" placeholder=""
															oninput="InvalidMsg(this,'input','<spring:message code="validation.contact" />');"
															oninvalid="InvalidMsg(this,'input','<spring:message code="validation.contact" />');"
															maxlength="15">
														<!-- <label for="phone">Alternate Contact Number <span class="star">*</span></label> -->
													</div>
													<!-- </div>

                                                        <div class="col s12 m12"> -->
													<div class="input-field col s12 m12">
														<input type="text" name="address" placeholder=""
															class="form-control boxBorder boxHeight" id="singleStolenaddress" 
															pattern="[^[a-zA-Z0-9\s,'-]*$]{0,200}" title=""
															oninput="InvalidMsg(this,'input','<spring:message code="validation.200characters" />');"
															oninvalid="InvalidMsg(this,'input','<spring:message code="validation.200characters" />');" 
														maxlength="200" required="required"> <label
															for="address"><spring:message code="input.address" /> <span
															class="star">*</span></label>
													</div>

													<div class="input-field col s12 m6 l6">
														<input type="text" name="streetNumber" placeholder=""
															class="form-control boxBorder boxHeight"
															id="singleStolenstreetNumber"  pattern="[^[a-zA-Z0-9\s,'-]*$]{0,20}" required="required" 
															title=""
															oninput="InvalidMsg(this,'input','<spring:message code="validation.20Character" />');"
															oninvalid="InvalidMsg(this,'input','<spring:message code="validation.20Character" />');"
															 maxlength="20">
														<label for="streetNumber"><spring:message code="input.streetNumber" /> <span
															class="star">*</span></label>
													</div>

													<div class="input-field col s12 m6 l6">
														<input type="text" name="village" id="singleStolenvillage" placeholder=""
															 pattern="[^[a-zA-Z0-9\s,'-]*$]{0,30}" required="required" 
															 title="" 
															 oninput="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');"
															oninvalid="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');"
															 maxlength="30"> 
															 <label for="village"><spring:message code="input.village" />
															<span class="star">*</span>
														</label>
													</div>

													<div class="input-field col s12 m6 l6">
														<input type="text" name="locality" placeholder=""
															class="form-control boxBorder boxHeight"
															id="singleStolenlocality" pattern="[^[a-zA-Z0-9\s,'-]*$]{0,30}"
															 required="required" title=""
															 oninput="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');"
															oninvalid="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');"
															  maxlength="30">
														<label for="locality"> <spring:message code="input.locality" /><span class="star">*</span></label>
													</div>

													<div class="input-field col s12 m6 l6">
														<input type="text" name="district" placeholder=""
															id="singleStolendistrict"  pattern="[^[a-zA-Z0-9\s,'-]*$]{0,30}" required="required" 
															title=""
															oninput="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');"
															oninvalid="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');"
															 maxlength="30"> <label
															for="district"> <spring:message code="input.district" /><span class="star">*</span></label>
													</div>

													<div class="input-field col s12 m6 l6">
														<input type="text" name="commune" id="singleStolencommune" placeholder=""
															 pattern="[^[a-zA-Z0-9\s,'-]*$]{0,30}" required="required" 
															 title="" 
															 oninput="InvalidMsg(this,'input','<spring:message code="validation.20Character" />');"
															oninvalid="InvalidMsg(this,'input','<spring:message code="validation.20Character" />');"
															maxlength="30"> 
															 <label for="commune"><spring:message code="input.commune" />
															<span class="star">*</span>
														</label>
													</div>

													<div class="input-field col s12 m6 l6">
														<input type="text" name="pin" placeholder=""
															class="form-control boxBorder boxHeight"
															id="singleStolenpin" pattern="[0-9]{1,6}"
															oninput="InvalidMsg(this,'input','<spring:message code="validation.postalcode" />');"
															oninvalid="InvalidMsg(this,'input','<spring:message code="validation.postalcode" />');"
															 title=""
															 maxlength="20" required="required"> <label
															for="pin"><spring:message code="registration.postalcode" /> <span class="star">*</span></label>
													</div>

													<div class="col s12 m6 l6">
														<label> <spring:message code="table.country" /><span class="star">*</span></label> <select
															id="country" class="browser-default" class="mySelect" 
															oninput="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
															oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
															style="padding-left: 0;" required></select>
													</div>

													<div class="col s12 m6 l6">
														<label><spring:message code="input.province" /> <span class="star">*</span></label> <select
															id="state" class="browser-default" class="mySelect"
															oninput="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
															oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
															style="padding-left: 0;" required></select>
													</div>

													<div class="col s12 m12" style="margin-top: 30px;">
														<h5><spring:message code="modal.deviceInfo" /></h5>
														<hr>
													</div>

													<div>
														<div class="input-field col s12 m6"">
															<input type="text" name="deviceBrandName" placeholder=""
																id="singleStolendeviceBrandName" pattern="[a-zA-Z]{0,20}"  title=""
																oninput="InvalidMsg(this,'input','<spring:message code="validation.20Character" />');"
																oninvalid="InvalidMsg(this,'input','<spring:message code="validation.20Character" />');" 
																maxlength="20">
															<label for="deviceBrandName"><spring:message code="registration.devicebrandname" /></label>
														</div>

														<%-- <div class="input-field col s12 m6"
															style="margin-top: 22px;">
															<input type="text" name="imeiNumber" placeholder=""
																id="singleStolenimeiNumber" pattern="[0-9]{15,16}" title="Please enter minimum 15 and maximum 16 digit only" 
															maxlength="16"> <label
																for="imeiNumber"><spring:message code="registration.imei/meid/esnnumber" /></label>
														</div> --%>

														<div class="col s6 m6 ">
															<label for="deviceIDType"><spring:message code="select.deviceIDType" /></label> <select
																oninput="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
																oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
																id="singleStolendeviceIDType" class="browser-default">
																<option value="" disabled selected>
																	<spring:message code="select.deviceIDType" /></option>
															</select>
														</div>

														<div class="col s6 m6 ">
															<label for="deviceType"><spring:message code="select.deviceType" /></label> <select
															oninput="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
																oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
																class="browser-default" id="singleStolendeviceType">
																<option value="" disabled selected><spring:message code="select.deviceType" /></option>
															</select>
														</div>

														<!-- <div class="col s6 m6 selectDropdwn">
                                                                <label for="deviceStatus">Device Status <span class="star">*</span></label>
                                                                <select id="deviceStatus" class="browser-default">
                                                                  <option value="" disabled selected>Device Status</option>
                                                                  <option value="New">New</option>
                                                                  <option value="used">used</option>
                                                                  <option value="Refurbished">Refurbished</option>
                                                                </select>
                                                              </div> -->

														<div class="input-field col s12 m6">
															<input type="text" name="modalNumber" placeholder=""
																id="singleStolenmodalNumber" pattern="[a-zA-Z0-9 ]{0,30}" 
																title=""
																oninput="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');"
																oninvalid="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');"
																 maxlength="30"> <label
																for="modalNumber"><spring:message code="table.ModelNumber" /></label>
														</div>

														<div class="col s12 m6">
															<p class="contact-label">
																<spring:message code="input.contactNum" /> <span class="star">*</span>
															</p>
															<input type="text" name="phone" id="singleStolenphone2" placeholder=""
																required 
																pattern="[0-9\s,+]{7,15}"
																oninput="InvalidMsg(this,'input','<spring:message code="validation.contact" />');"
																oninvalid="InvalidMsg(this,'input','<spring:message code="validation.contact" />');"
																title="" maxlength="14">
															<!--  <label for="phone2">Contact Number <span class="star">*</span></label> -->
														</div>

														<!-- <div class="input-field col s12 m6">
                                                                <input type="text" name="operator" id="operator" maxlength="10">
                                                                <label for="operator">Operator <span class="star">*</span></label>
                                                            </div> -->

														<div class="col s12 m6 l6">
															<label><spring:message code="table.Operator" /> <span class="star">*</span></label> <select
																class="browser-default" required="required"
																oninput="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
																oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
																 id="singleStolenOperator">
																<option value="" disabled selected>
																	<spring:message code="registration.selectoperator" /></option>

															</select>
														</div>
														
														
														
														
														<div class="col s12 m6 l6">
															<label> <spring:message code="select.multiSimStatus" /> <span class="star"> *</span></label> <select
																class="browser-default" required="required"
																oninput="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
																oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
																 id="singleStolenSimStatus">
																<option value="" disabled selected> <spring:message code="select.multiSimStatus" /> 
																	</option>

															</select>
														</div>														

														<div class="col s12 m6 l6">
															<label><spring:message code="registration.complainttype" /> <span class="star">*</span></label>
															<select class="browser-default"
															oninput="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
																oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
																 required="required"
																id="singleStolenComplaintType">
																<option value="" disabled selected>
																	<spring:message code="registration.selectcomplainttype" /></option>

															</select>
														</div>
														
														<div class="col s12 m12" style="margin-top: 30px; font-weight: bold;">
															<h6><spring:message code="registration.imei/meid/esnnumber" /></h6>
														</div>
														
														<div class="input-field col s12 m6">
															<input type="text" name="imeiNumber" pattern="[0-9]{15,16}"
																oninput="InvalidMsg(this,'input','<spring:message code="validation.1516digit" />');"
																oninvalid="InvalidMsg(this,'input','<spring:message code="validation.1516digit" />');"
																 required="required"
																title="<spring:message code=" validation.1516digit" />"
															maxlength="16" id="updatesingleStolenimei1"> <label
																for="updatesingleStolenimei1">
																<spring:message code="registration.one" /> <span class="star"> *</span></label>
														</div>

														<div class="input-field col s12 m6">
															<input type="text" name="imeiNumberTwo"
																pattern="[0-9]{15,16}"
																oninput="InvalidMsg(this,'input','<spring:message code="validation.1516digit" />');"
																oninvalid="InvalidMsg(this,'input','<spring:message code="validation.1516digit" />');"
																title="<spring:message code=" validation.1516digit" />"
															maxlength="16" id="updatesingleStolenimei2" > <label
																for="updatesingleStolenimei2">
																<spring:message code="registration.two" /></label>
														</div>

														<div class="input-field col s12 m6">
															<input type="text" name="imeiNumberThree"
																pattern="[0-9]{15,16}"
																oninput="InvalidMsg(this,'input','<spring:message code="validation.1516digit" />');"
																oninvalid="InvalidMsg(this,'input','<spring:message code="validation.1516digit" />');"
																title="<spring:message code=" validation.1516digit" />"
															maxlength="16" id="updatesingleStolenimei3" > <label
																for="updatesingleStolenimei3">
																<spring:message code="registration.three" /></label>
														</div>

														<div class="input-field col s12 m6">
															<input type="text" name="imeiNumberFour"
																pattern="[0-9]{15,16}"
																oninput="InvalidMsg(this,'input','<spring:message code="validation.1516digit" />');"
																oninvalid="InvalidMsg(this,'input','<spring:message code="validation.1516digit" />');"
																title="<spring:message code=" validation.1516digit" />"
															maxlength="16" id="updatesingleStolenimei4" > <label
																for="updatesingleStolenimei4">
																<spring:message code="registration.four" /></label>
														</div>

														<div class="col s12 m12" style="margin-top: 30px;">
															<h5><spring:message code="registration.plstolen" /></h5>
															<hr>
														</div>
														<!-- <div class="col s12 m12">
                                                                <p><b>Place Of Device Stolen</b></p>
                                                            </div> -->
														<div class="input-field col s12 m12">
															<input type="text" name="address" placeholder=""
																class="form-control boxBorder boxHeight"
																id="singleDeviceAddress"  pattern="[^[a-zA-Z0-9\s,'-]*$]{0,200}" title=""
																oninput="InvalidMsg(this,'input','<spring:message code="validation.200characters" />');"
																oninvalid="InvalidMsg(this,'input','<spring:message code="validation.200characters" />');"
																 maxlength="200" required="required" > <label
																for="address"><spring:message code="input.address" /> <span
																class="star">*</span></label>
														</div>

														<div class="input-field col s12 m6 l6">
															<input type="text" name="streetNumber" placeholder=""
																class="form-control boxBorder boxHeight"
																id="singleDevicestreetNumber"pattern="[^[a-zA-Z0-9\s,'-]*$]{0,20}" required="required"
																 title=""
																 oninput="InvalidMsg(this,'input','<spring:message code="validation.20Character" />');"
																oninvalid="InvalidMsg(this,'input','<spring:message code="validation.20Character" />');"
																 maxlength="20">
															<label for="streetNumber"><spring:message code="input.streetNumber" /> <span
																class="star">*</span></label>
														</div>

														<div class="input-field col s12 m6 l6">
															<input type="text" name="village" placeholder=""
																id="singleDevicevillage" pattern="[^[a-zA-Z0-9\s,'-]*$]{0,30}" required="required"
																 title="" 
																 oninput="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');"
																oninvalid="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');"
																 maxlength="30"> <label
																for="village"><spring:message code="input.village" /> <span class="star">*</span></label>
														</div>

														<div class="input-field col s12 m6 l6">
															<input type="text" name="locality" placeholder=""
																class="form-control boxBorder boxHeight"
																id="singleDevicelocality" pattern="[^[a-zA-Z0-9\s,'-]*$]{0,30}" required="required"
																 title=""
																 oninput="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');"
																oninvalid="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');"
																  maxlength="30">
															<label for="locality"><spring:message code="input.locality" /> <span class="star">*</span></label>
														</div>

														<div class="input-field col s12 m6 l6">
															<input type="text" name="district" placeholder=""
																id="singleDevicedistrict" pattern="[^[a-zA-Z0-9\s,'-]*$]{0,30}" required="required"
																 title=""
																 oninput="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');"
																oninvalid="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');"
																 maxlength="30"> <label
																for="district"><spring:message code="input.district" /> <span class="star">*</span></label>
														</div>

														<div class="input-field col s12 m6 l6" >
															<input type="text" name="commune" placeholder=""
																id="singleDevicecommune" pattern="[a-zA-Z0-9]{0,30}" required="required"
																 title=""
																 oninput="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');"
																oninvalid="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');"
																  maxlength="30"> <label
																for="commune"><spring:message code="input.commune" /> <span class="star">*</span></label>
														</div>

														<div class="input-field col s12 m6 l6">
															<input type="text" name="pin" placeholder=""
																class="form-control boxBorder boxHeight"
																id="singleDevicepin"pattern="[0-9]{0,6}" required="required"
																oninput="InvalidMsg(this,'input','<spring:message code="validation.postalcode" />');"
																oninvalid="InvalidMsg(this,'input','<spring:message code="validation.postalcode" />');"
																 title="" maxlength="20"> <label
																for="pin"><spring:message code="registration.postalcode" /> <span class="star">*</span></label>
														</div>

														<div class="col s12 m6 l6">
															<label><spring:message code="table.country" /> <span class="star">*</span></label> <select
																id="singleDevicecountry" class="browser-default"
																onchange="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
																 oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
																class="mySelect" style="padding-left: 0;" required></select>
														</div>

														<div class="col s12 m6 l6">
															<label><spring:message code="input.province" /> <span class="star">*</span></label> <select
																id="singleDevicestate" class="browser-default"
																oninput="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
																oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
																class="mySelect" style="padding-left: 0;" required></select>
														</div>
														<div class="input-field col s12 m6" data-original-title="" title="">
															<input type="text" name="IndivisualStolenDate" id="IndivisualStolenDate"
															 onchange="InvalidMsg(this,'date','<spring:message code="validation.requiredMsg" />');"
														     oninvalid="InvalidMsg(this,'date','<spring:message code="validation.requiredMsg" />');"
															 class="form-control datepick" autocomplete="off" title="" required="" data-original-title="
																validation.requiredMsg">
															<label for="IndivisualStolenDate" class="center-align" data-original-title="" title="">
																<spring:message code="operator.stolenDate" /> <span class="star" data-original-title="" title="">*</span>
															</label> <span class="input-group-addon" style="color: #ff4081" data-original-title="" title=""><i class="fa fa-calendar" aria-hidden="true" data-original-title="" title=""></i></span>
														</div>
														
														<div class="file-field col s12 m6" data-original-title="" title="">
															<h6 class="form-label" style="margin:0; font-size: 0.9rem;" data-original-title="" title="">
																<spring:message code="input.UploadFIR" />
															</h6>
															<div class="btn" id="firImageDiv" data-original-title="" title="">
																<span id="firDivText" data-original-title="" title="">
																	<spring:message code="input.selectfile" /></span>
																<input type="file" 
																oninput="InvalidMsg(this,'fileType','<spring:message code="validation.NoChosen" />');"
																oninvalid="InvalidMsg(this,'fileType','<spring:message code="validation.NoChosen" />');"
																onchange="isImageValid('uploadFirSingle')" accept="*Image" placeholder="Upload FIR" id="uploadFirSingle" data-original-title="" title="">
															</div>
															<div class="file-path-wrapper" data-original-title="" title="">
																<input class="file-path validate" type="text" placeholder="
																	input.UploadFIR" id="uploadFirSingleName" title="" data-original-title="">
																	<a id="firImageLink" class="imgPreviewLink" style="display: none;">Preview</a>
															</div>
														</div>

														<div class="input-field col s12 m12">
															<textarea id="singleDeviceRemark" maxlength="10000" placeholder=""
																oninput="InvalidMsg(this,'input','<spring:message code="validation.10000characters" />');"
																oninvalid="InvalidMsg(this,'input','<spring:message code="validation.10000characters" />');"
																class="materialize-textarea"></textarea>
															<label for="textarea1"><spring:message code="input.remarks" /> </label>
														</div>
													</div>
												</div>
											</div>
											<span> <spring:message code="input.requiredfields" /> <span
												class="star">*</span></span>


												<div class="input-field col s12 center">
													<button class="btn" id="IndivisualUpdateStolen" type="submit" ><spring:message code="button.submit" /></button>
													<a href="./stolenRecovery?FeatureId=5" class="btn modal-trigger"
														style="margin-left: 10px;"><spring:message code="button.cancel" /></a>
												</div>
												
										</form>
									</div>


<!-- ____________________________________________________Bulk stolen form________________________________________________________________________ -->


									<div id="Bulkform" class="col s12" style="display: none">
										<form action="#" style="margin-top: 30px;" id="bulkFormSubmit">
											<div class="input-field col s12 m6">
												<input type="text" name="companyName"
													id="bulkStolencompanyName"> <label
													for="companyName"><spring:message code="registration.companyName" /> <span class="star">*</span></label>
											</div>

											<div class="input-field col s12 m6">
												<input type="text" name="address"
													class="form-control boxBorder boxHeight"
													id="bulkStolenaddress" pattern=[A-Za-z]
													title="Please enter your address"> <label
													for="address"><spring:message code="input.address" /> <span
													class="star">*</span></label>
											</div>

											<div class="input-field col s12 m6 l6">
												<input type="text" name="streetNumber"
													class="form-control boxBorder boxHeight"
													id="bulkStolenstreetNumber" maxlength="30"
													pattern=[A-Za-z0-9] title="Please enter street number">
												<label for="streetNumber"><spring:message code="input.streetNumber" /> <span
													class="star">*</span></label>
											</div>

											<div class="input-field col s12 m6 l6">
												<input type="text" name="village" id="bulkStolenvillage"
													maxlength="20"> <label for="village"><spring:message code="input.village" />
													<span class="star">*</span>
												</label>
											</div>

											<div class="input-field col s12 m6 l6">
												<input type="text" name="locality"
													class="form-control boxBorder boxHeight"
													id="bulkStolenlocality" maxlength="20" pattern=[A-Za-z0-9]
													title="Please enter your locality"> <label
													for="locality"><spring:message code="input.locality" /> <span class="star">*</span></label>
											</div>

											<div class="input-field col s12 m6 l6">
												<input type="text" name="district" id="bulkStolendistrict"
													maxlength="20"> <label for="district"><spring:message code="input.district" />
													<span class="star">*</span>
												</label>
											</div>

											<div class="input-field col s12 m6 l6">
												<input type="text" name="commune" id="bulkStolencommune"
													maxlength="20"> <label for="commune"><spring:message code="input.commune" />
													<span class="star">*</span>
												</label>
											</div>

											<div class="input-field col s12 m6 l6">
												<input type="text" name="pin"
													class="form-control boxBorder boxHeight" id="bulkStolenpin"
													maxlength="20"> <label for="pin"><spring:message code="registration.postalcode" />
													<span class="star">*</span>
												</label>
											</div>

											<div class="col s12 m6 l6">
												<label><spring:message code="table.country" /> <span class="star">*</span></label> <select
													id="country2" class="browser-default" class="mySelect"
													style="padding-left: 0;" required></select>
											</div>

											<div class="col s12 m6 l6">
												<label><spring:message code="input.province" /> <span class="star">*</span></label> <select
													id="state2" class="browser-default" class="mySelect"
													style="padding-left: 0;" required></select>
											</div>

											<div class="col s12 m12" style="margin-top: 30px;">
												<h5><spring:message code="registration.authorizedpersonnel" /></h5>
												<hr>
											</div>
											<!-- <div class="col s12 m12">
                                                        <p><b>Authorized personnel</b> </p>
                                                    </div> -->

											<div class="input-field col s12 m4">
												<input type="text" name="bulkStolenfirstName" id="firstName">
												<label for="firstName"><spring:message code="input.firstName" /> <span class="star">*</span></label>
											</div>

											<div class="input-field col s12 m4">
												<input type="text" name="middleName"
													id="bulkStolenmiddleName"> <label for="middleName">
													<spring:message code="input.middleName" /></label>
											</div>

											<div class="input-field col s12 m4">
												<input type="text" name="lastName" id="bulkStolenlastName">
												<label for="lastName"><spring:message code="input.lastName" /> <span class="star">*</span></label>
											</div>

											<div class="input-field col s12 m6">
												<input type="text" name="officeEmail"
													id="bulkStolenofficeEmail"> <label
													for="officeEmail"><spring:message code="registration.officialemailid" /></label>
											</div>

											<div class="input-field col s12 m6">
												<!-- <p class="contact-label">Alternate Contact Number <span class="star">*</span></p> -->
												<input type="text" name="phone" id="bulkStolenContact"
													maxlength="15"> <label for="phone2">
													<spring:message code="input.contactNum" /></label>
											</div>

											<!-- <div class="input-field col s12 m6">
                                                        <input type="text" name="totalQuantity" id="totalQuantity">
                                                        <label for="totalQuantity">Total Device Quantity </label>
                                                    </div> -->

											<div class="col s12 m12" style="margin-top: 30px;">
												<h5><spring:message code="registration.plstolen" /></h5>
												<hr>
											</div>
											<!-- <div class="col s12 m12">
                                                        <p><b>Place Of Device Stolen</b></p>
                                                    </div> -->
											<div class="input-field col s12 m12">
												<input type="text" name="address"
													class="form-control boxBorder boxHeight"
													id="deviceBulkStolenaddress" pattern=[A-Za-z]
													title="Please enter your address"> <label
													for="address"><spring:message code="input.address" /> <span
													class="star">*</span></label>
											</div>

											<div class="input-field col s12 m6 l6">
												<input type="text" name="streetNumber"
													class="form-control boxBorder boxHeight"
													id="deviceBulkStolenstreetNumber" maxlength="30"
													pattern=[A-Za-z0-9] title="Please enter street number">
												<label for="streetNumber"><spring:message code="input.streetNumber" /> <span
													class="star">*</span></label>
											</div>

											<div class="input-field col s12 m6 l6">
												<input type="text" name="village"
													id="deviceBulkStolenvillage" maxlength="20"> <label
													for="village"><spring:message code="input.village" /> <span class="star">*</span></label>
											</div>

											<div class="input-field col s12 m6 l6">
												<input type="text" name="locality"
													class="form-control boxBorder boxHeight"
													id="deviceBulkStolenlocality" maxlength="20"
													pattern=[A-Za-z0-9] title="Please enter your locality">
												<label for="locality"> <spring:message code="input.locality" /><span class="star">*</span></label>
											</div>

											<div class="input-field col s12 m6 l6">
												<input type="text" name="district"
													id="deviceBulkStolendistrict" maxlength="20"> <label
													for="district"><spring:message code="input.district" /> <span class="star">*</span></label>
											</div>

											<div class="input-field col s12 m6 l6">
												<input type="text" name="commune"
													id="deviceBulkStolencommune" maxlength="20"> <label
													for="commune"><spring:message code="input.commune" /> <span class="star">*</span></label>
											</div>

											<div class="input-field col s12 m6 l6">
												<input type="text" name="pin"
													class="form-control boxBorder boxHeight"
													id="deviceBulkStolenpin" maxlength="20"> <label
													for="pin"> <spring:message code="registration.postalcode" /><span class="star">*</span></label>
											</div>

											<div class="col s12 m6 l6">
												<label><spring:message code="table.country" /> <span class="star">*</span></label> <select
													id="country3" class="browser-default" class="mySelect"
													style="padding-left: 0;" required></select>
											</div>

											<div class="col s12 m6 l6">
												<label><spring:message code="input.province" /> <span class="star">*</span></label> <select
													id="state3" class="browser-default" class="mySelect"
													style="padding-left: 0;" required></select>
											</div>

											<div class="col s12 m6 l6">
												<label><spring:message code="registration.complainttype" /> <span class="star">*</span></label> <select
													class="browser-default" id="deviceBulkStolenComplaint">
													<option value="" disabled selected>
														<spring:message code="registration.selectcomplainttype" /></option>

												</select>
											</div>

											<div class="input-field col s12 m6 l6"
												style="margin-top: 22px;">
												<input type="text" name="quantity"
													class="form-control boxBorder boxHeight"
													id="deviceBulkStolenquantity" maxlength="10" pattern=[0-9]
													title="Please enter your locality"> <label
													for="quantity"> <spring:message code="input.quantity" /><span class="star">*</span></label>
											</div>

											<div class="file-field col s12 m6">
												<h6 class="file-label">
													 <spring:message code="registration.uploaddevicelist" /><span class="star">*</span>
												</h6>
												<div class="btn">
													<span><spring:message code="input.selectfile" /></span> <input type="file"
														id="deviceBulkStolenFile" placeholder="Upload Photo">
												</div>
												<div class="file-path-wrapper">
													<input class="file-path validate" type="text"
														placeholder="Upload Device List"
														title="Please upload your photo">
												</div>
											</div>

											<div class="input-field col s12 m6" style="margin-top: 22px;">
												<textarea id="deviceBulkStolenRemark"
													class="materialize-textarea"></textarea>
												<label for="textarea1"><spring:message code="input.remarks" /></label>
											</div>

											<div class="col s12 m12">
												<a href=""><spring:message code="input.downlaod.sample" /></a>
											</div>

											<div class="input-field col s12 center">
												<button class="btn modal-trigger" data-target="submitStolen"><spring:message code="button.submit" /></button>
												<a href="./stolenRecovery?FeatureId=5" class="btn modal-trigger"
													style="margin-left: 10px;"><spring:message code="button.cancel" /></a>
											</div>
										</form>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<!--end container-->
	</section>
	 <div id="stolenSucessPopUp" class="modal">
  <h6 class="modal-header"><spring:message code="registration.updatereportstolen" /></h6>
        <div class="modal-content">
           
            <div class="row">
                <h6 id="dynamicMessage"><spring:message code="input.Theupdated" /></h6>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <div class="input-field col s12 center">
                        <a  href ="./stolenRecovery?FeatureId=5" class=" btn"><spring:message code="modal.ok" /></a>
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
						<button class="modal-close  btn" onclick="clearFileName()"
							style="margin-left: 10px;"><spring:message code="modal.ok" /></button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div id="viewuplodedModel" class="modal" style="overflow: hidden">
	<a href="#!" class="modal-close waves-effect waves-green btn-flat">&times;</a>
		<div class="modal-content">
			<div class="row">
					<img src="" id="fileSource" width="400" height="400">
			</div>
		</div>
	</div>
	
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
		src="${context}/resources/project_js/globalVariables.js"></script>
	<script type="text/javascript"
		src="${context}/resources/project_js/stolenRecovery.js"></script>
	<script type="text/javascript"
		src="${context}/resources/project_js/lawfulStolenRecovery.js"></script>
		
		<script type="text/javascript"
		src="${context}/resources/project_js/editLawfulStolen.js"></script>
	<script type="text/javascript"
		src="${context}/resources/project_js/validationMsg.js"></script>
		<script type="text/javascript" src="${context}/resources/project_js/globalVariables.js"></script>



	<script>
	var lang=window.parent.$('#langlist').val() == 'km' ? 'km' : 'en';


	$.i18n().locale = lang;	
	//alert(lang)
	var successMsg,stolenIndivisual;
	
	
	$.i18n().load( {
		'en': './resources/i18n/en.json',
		'km': './resources/i18n/km.json'
	}).done( function() { 
		stolenIndivisual=$.i18n('stolenIndivisual');
		editstolenIndivisual=$.i18n('editstolenIndivisual');
		
		
		viewPageType();
	});
	
	
	   function viewPageType() {
           if($('#pageViewType').val()=='view')
           	{
           	$('#headingType').text('');
           	$('#headingType').text(stolenIndivisual);
           	   $("#passportImageDiv").removeClass("btn");
           	   $('#passportImageText').text('');
           	   $('#singleStolenFile').attr('type','text');
           	   $("#PassportNidLink").css("display", "block");
           	   $("#singleStolenFile").css("display", "none");
           	   	
           	   $("#firImageDiv").removeClass("btn");
           	   $('#firDivText').text('');
           	   $('#uploadFirSingle').attr('type','text');
           	   $("#firImageLink").css("display", "block");
           	   $("#uploadFirSingle").css("display", "none");  
           	  // alert(stolenIndivisual);
           	  $("#SingleForm").find("input,select,textarea,button").prop("disabled",true);
           	}
           else{
           	$('#headingType').text('');
           	$('#headingType').text(editstolenIndivisual);
           	  $("#SingleForm").find("input,select,textarea,button").prop("disabled",false);
           }
         
     }
	
	populateCountries(
	        "singleDevicecountry",
	        "singleDevicestate"
	    );
	    populateStates(
	        "singleDevicecountry",
	        "singleDevicestate"
	    );
	    
	    
	    populateCountries(
	            "country",
	            "state"
	        );
	        populateStates(
	            "country",
	            "state"
	        );
	        
	        populateCountries(
		            "country2",
		            "state2"
		        );
		        populateStates(
		            "country2",
		            "state2"
		        );
		        
		        populateCountries(
			            "country3",
			            "state3"
			        );
			        populateStates(
			            "country3",
			            "state3"
			        );
		 
        var input2 = document.querySelector("#singleStolenphone2");
        window.intlTelInput(input2, {
            utilsScript: "${context}/resources/js/utils.js",
        });
        var input = document.querySelector("#singleStolenphone1");
        window.intlTelInput(input, {
            utilsScript: "${context}/resources/js/utils.js",
        });
        
     
        $('.datepick').datepicker({
			dateFormat: "yy-mm-dd"
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