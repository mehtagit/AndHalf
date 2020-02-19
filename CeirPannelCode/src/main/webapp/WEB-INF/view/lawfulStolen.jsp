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

.blockingType {
height: 4.6rem;
margin-bottom: 5px;
}

textarea.materialize-textarea {
	height: unset !important;
	max-height: 300px !important;
}
</style>


</head>

<body data-id="5" data-roleType="${usertype}" data-userTypeID="${usertypeId}"
	data-userID="${userid}" data-operatorTypeId="${operatorTypeId}"
	data-selected-roleType="${stolenselectedUserTypeId}"
	data-stolenselected-roleType="${stolenselectedUserTypeId}">





	<section id="content">
		<!--start container-->
		<div class="container">
			<div class="section">
				<div class="row">
					<div class="col s12 m12 l12">
						<div class="row card-panel">
							<div id="reportBlockUnblock">
								<div class="container-fluid pageHeader">
									<p class="PageHeading"><spring:message code="input.ReportStolen" /></p>
								</div>

								<div class="row">
									<div class="col s12">
										<ul class="tabs">
											<li class="tab col s3"><a class="active"
												onclick="showSingleFormDiv()"><spring:message code="input.Individual" /></a></li>
											<li class="tab col s3"><a onclick="showBulkFormDiv()"><spring:message code="registration.company/organisation/government" /></a></li>
										</ul>
									</div>
									<div id="SingleForm" class="col s12"
										style="margin-top: 30px; display: block">
										<form action="" id="SingleImeiBlockform" onsubmit="return saveIndivisualStolenRequest()" method="POST" enctype="multipart/form-data">
											<div class="row">

												<div class="col s12 m12">
													<h5><spring:message code="input.personalInformation" /></h5>
													<hr>
												</div>

												<div class="col-s12 m12">
													<div class="input-field col s12 m4">
														<input type="text" name="singleStolenfirstName"  
							oninput="InvalidMsg(this,'input');" oninvalid="InvalidMsg(this,'input');"
							title= "<spring:message code="validation.20Character" />" required  / 	pattern="[a-zA-Z ]{1,20}" maxlength="20"
															id="singleStolenfirstName"> <label
															for="singleStolenfirstName"><spring:message code="input.firstName" /> <span
															class="star"> *</span></label>
													</div>

													<div class="input-field col s12 m4">
														<input type="text" name="middleName" pattern="[a-zA-Z]{1,20}" 
													oninput="InvalidMsg(this,'input');" oninvalid="InvalidMsg(this,'input');"
													title= "<spring:message code="validation.20Character" />"	
													maxlength="20" id="singleStolenmiddleName"> <label
															for="middleName"><spring:message code="input.middleName" /></label>
													</div>

													<div class="input-field col s12 m4">
														<input type="text" name="lastName" required="required" pattern="[a-zA-Z]{1,20}"
														oninput="InvalidMsg(this,'input');" oninvalid="InvalidMsg(this,'input');"
							title= "<spring:message code="validation.20Character" />" required  /  maxlength="20"
															id="singleStolenlastName"> <label for="lastName"><spring:message code="input.lastName" /><span class="star"> *</span>
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
															<spring:message code="registration.uploadnid/passportimage" /> <span class="star"> *</span>
														</h6>
														<div class="btn">
															<span><spring:message code="input.selectfile" /></span> <input type="file" 
															oninput="InvalidMsg(this,'fileType');" oninvalid="InvalidMsg(this,'fileType');"
															title= "<spring:message code="validation.NoChosen" />" required  / 
															
																placeholder="Upload Photo" id="singleStolenFile">
														</div>
														<div class="file-path-wrapper">
															<input class="file-path validate" type="text"
																placeholder="Upload NID/Passport image"
																id="singleStolenFileName"
																title="Please upload national ID image">
														</div>
													</div>

													<div class="input-field col s12 m6"
														style="margin-top: 22px;">
														<input type="text" name="nIDPassportNumber"  pattern="[A-Za-z0-9]{1,15}"
														oninput="InvalidMsg(this,'input');" oninvalid="InvalidMsg(this,'input');"
														title= "<spring:message code="validation.15alphanumeric" />" required  / maxlength="15"
															id="singleStolennIDPassportNumber"> <label
															for="nIDPassportNumber"><spring:message code="registration.nid/passportnumber" /> <span
															class="star"> *</span></label>
													</div>

													<div class="input-field col s12 m6 l6">
														<input type="email" name="email" id="singleStolenemail"
														pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" 
														oninput="InvalidMsg(this,'email');" oninvalid="InvalidMsg(this,'email');" 
														title= "<spring:message code="validation.emial" />" 
												        maxlength="30"> <label for="email"><spring:message code="input.email" />
														</label>
													</div>

													<div class="input-field col s12 m6 l6">
														<p class="contact-label">
															 <spring:message code="registration.altcontactnumber" /><span class="star"> *</span>
														</p>
														<input type="tel" name="phone" id="singleStolenphone1"
															required pattern="[0-9 + ]{1,14}" 
															oninput="InvalidMsg(this,'input');" oninvalid="InvalidMsg(this,'input');"
															title= "<spring:message code="validation.10digits" />" required  / maxlength="12">
														<!-- <label for="phone">Alternate Contact Number <span class="star">*</span></label> -->
													</div>
													<!-- </div>

                                                        <div class="col s12 m12"> -->
													<div class="input-field col s12 m12">
														<input type="text" name="address"
															class="form-control boxBorder boxHeight" id="singleStolenaddress" 
														pattern="[^[a-zA-Z0-9\s,'-]*$]{0,200}" 
														oninput="InvalidMsg(this,'input');" oninvalid="InvalidMsg(this,'input');"
															title= "<spring:message code="validation.200characters" />" required  / maxlength="200"> <label
															for="address"><spring:message code="input.address" /> <span
															class="star"> *</span></label>
													</div>

													<div class="input-field col s12 m6 l6">
														<input type="text" name="streetNumber" 	class="form-control boxBorder boxHeight" id="singleStolenstreetNumber" 
														 pattern="[^[a-zA-Z0-9\s,'-]*$]{0,20}" 
														oninput="InvalidMsg(this,'input');" oninvalid="InvalidMsg(this,'input');"
															title= "<spring:message code="validation.20Character" />" required  /  maxlength="20">
														<label for="streetNumber"><spring:message code="input.streetNumber" /><span
															class="star"> *</span></label>
													</div>

													<div class="input-field col s12 m6 l6">
														<input type="text" name="village" id="singleStolenvillage"
															pattern="[^[a-zA-Z0-9\s,'-]*$]{0,30}" 
														oninput="InvalidMsg(this,'input');" oninvalid="InvalidMsg(this,'input');"
															title= "<spring:message code="validation.address30characters" />" required  / maxlength="30">
															 <label for="village"><spring:message code="input.village" />
															<span class="star"> *</span>
														</label>
													</div>

													<div class="input-field col s12 m6 l6">
														<input type="text" name="locality"
															class="form-control boxBorder boxHeight"
															id="singleStolenlocality" pattern="[^[a-zA-Z0-9\s,'-]*$]{0,30}" 
															oninput="InvalidMsg(this,'input');" oninvalid="InvalidMsg(this,'input');"
															title= "<spring:message code="validation.address30characters" />" required  / maxlength="30">
														<label for="locality"> <spring:message code="input.locality" /><span class="star"> *</span></label>
													</div>

													<div class="input-field col s12 m6 l6">
														<input type="text" name="district"
															id="singleStolendistrict" pattern="[^[a-zA-Z0-9\s,'-]*$]{0,30}" 
															oninput="InvalidMsg(this,'input');" oninvalid="InvalidMsg(this,'input');"
															title= "<spring:message code="validation.address30characters" />" required  / maxlength="30"> <label
															for="district"><spring:message code="input.district" /> <span class="star"> *</span></label>
													</div>

													<div class="input-field col s12 m6 l6">
														<input type="text" name="commune" id="singleStolencommune"
															pattern="[A-Za-z0-9._%+-$@,/]+\.{0,30}" 
															oninput="InvalidMsg(this,'input');" oninvalid="InvalidMsg(this,'input');"
															title= "<spring:message code="validation.address30characters" />" required  / maxlength="30"
															> <label for="commune"><spring:message code="input.commune" />
															<span class="star"> *</span>
														</label>
													</div>

													<div class="input-field col s12 m6 l6">
														<input type="text" name="pin"
															class="form-control boxBorder boxHeight"
															id="singleStolenpin" pattern="[0-9]{1,6}" 
															oninput="InvalidMsg(this,'input');" oninvalid="InvalidMsg(this,'input');"
															title= "<spring:message code="validation.postalcode" />" required  / maxlength="6" > 
															<label
															for="pin"><spring:message code="input.postalCode" /><span class="star"> *</span></label>
													</div>

													<div class="col s12 m6 l6">
														<label><spring:message code="input.Country" /><span class="star"> *</span></label> <select
															id="country" class="browser-default" class="mySelect"
															oninput="InvalidMsg(this,'select');" oninvalid="InvalidMsg(this,'select');"
															title= "<spring:message code="validation.selectFieldMsg" />" required  / style="padding-left: 0;" ></select>
													</div>

													<div class="col s12 m6 l6">
														<label><spring:message code="input.province" /> <span class="star"> *</span></label> <select
															id="state" class="browser-default" class="mySelect"
															style="padding-left: 0;" 
															oninput="InvalidMsg(this,'select');" oninvalid="InvalidMsg(this,'select');"
															title= "<spring:message code="validation.selectFieldMsg" />" required  /></select>
													</div>

													<div class="col s12 m12" style="margin-top: 30px;">
														<h5><spring:message code="modal.deviceInfo" /></h5>
														<hr>
													</div>

													<div>
														<div class="input-field col s12 m6"
															style="margin-top: 22px;">
															<input type="text" name="deviceBrandName"
																id="singleStolendeviceBrandName" pattern="[a-zA-Z]{0,20}" 
																oninput="InvalidMsg(this,'input');" oninvalid="InvalidMsg(this,'input');"
															title= "<spring:message code="validation.20Character" />" maxlength="20">
															<label for="deviceBrandName"><spring:message code="registration.devicebrandname" /></label>
														</div>

														<div class="input-field col s12 m6"
															style="margin-top: 22px;">
															<input type="text" name="imeiNumber" pattern="[0-9]{15,16}" 
															oninput="InvalidMsg(this,'input');" oninvalid="InvalidMsg(this,'input');"
															title= "<spring:message code="validation.1516digit" />"  maxlength="16" 	id="singleStolenimeiNumber" > <label
																for="imeiNumber"><spring:message code="registration.imei/meid/esnnumber" /></label>
														</div>

														<div class="col s6 m6 selectDropdwn">
															<label for="deviceIDType"><spring:message code="select.deviceIDType" /></label> <select
																oninput="InvalidMsg(this,'select');" oninvalid="InvalidMsg(this,'select');"
																title= "<spring:message code="validation.selectFieldMsg" />" id="singleStolendeviceIDType" class="browser-default">
																<option value="" disabled selected><spring:message code="select.deviceIDType" /></option>
															</select>
														</div>

														<div class="col s6 m6 selectDropdwn">
															<label for="deviceType"><spring:message code="select.deviceType" /></label> <select
																class="browser-default" id="singleStolendeviceType"
																oninput="InvalidMsg(this,'select');" oninvalid="InvalidMsg(this,'select');"
																title= "<spring:message code="validation.selectFieldMsg" />" >
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
															<input type="text" name="modalNumber"
																id="singleStolenmodalNumber"  pattern="[a-zA-Z0-9]{0,30}" 
																oninput="InvalidMsg(this,'input');" oninvalid="InvalidMsg(this,'input');"
															title= "<spring:message code="validation.30characters" />" maxlength="30">
																 <label
																for="modalNumber"><spring:message code="input.modelNumber" /></label>
														</div>

														<div class="input-field col s12 m6">
															<p class="contact-label">
																<spring:message code="input.contactNum" /> <span class="star"> *</span>
															</p>
															<input type="tel" name="phone" id="singleStolenphone2" pattern="[0-9 + ]{1,12}" 
															oninput="InvalidMsg(this,'input');" oninvalid="InvalidMsg(this,'input');"
															title= "<spring:message code="validation.postalcode" />" required  / maxlength="12">
															<!--  <label for="phone2">Contact Number <span class="star">*</span></label> -->
														</div>

														<!-- <div class="input-field col s12 m6">
                                                                <input type="text" name="operator" id="operator" maxlength="10">
                                                                <label for="operator">Operator <span class="star">*</span></label>
                                                            </div> -->

														<div class="col s12 m6 l6">
															<label><spring:message code="operator.Operator" /> <span class="star"> *</span></label> <select
																class="browser-default" id="singleStolenOperator"
																oninput="InvalidMsg(this,'select');" oninvalid="InvalidMsg(this,'select');"
																title= "<spring:message code="validation.selectFieldMsg" />" required  / >
																<option value="" disabled selected><spring:message code="registration.selectoperator" /></option>
																</select>
														</div>
														
														
														<div class="col s12 m6 l6">
															<label><spring:message code="select.multiSimStatus" />  </label> <select
																class="browser-default" id="singleStolenSimStatus"
																oninput="InvalidMsg(this,'select');" oninvalid="InvalidMsg(this,'select');"
																title= "<spring:message code="validation.selectFieldMsg" />" >
																<option value="" disabled selected><spring:message code="registration.selectMultiplest" /> 
																	</option>

															</select>
														</div>														

														<div class="col s12 m6 l6">
															<label><spring:message code="registration.complainttype" /> <span class="star"> *</span></label>
															<select class="browser-default" 
															oninput="InvalidMsg(this,'select');" oninvalid="InvalidMsg(this,'select');"
																title= "<spring:message code="validation.selectFieldMsg" />" required / id="singleStolenComplaintType">
																<option value="" disabled selected><spring:message code="registration.selectcomplainttype" /></option>

															</select>
														</div>
														 <div class="col s12 m6">
<p style="margin-top: 3px; margin-bottom: 5px"><spring:message code="operator.blocking" /></p>
<label style="margin-right: 2%;"> <input type="radio" class="blocktypeRadio" id=""
value="Immediate"
onclick="document.getElementById('calender').style.display = 'none';"
name="stolenBlockPeriod" checked><spring:message code="operator.immediate" />
</label> <label style="margin-right: 2%;"> <input type="radio" class="blocktypeRadio"
value="Default"
onclick="document.getElementById('calender').style.display = 'none';"
name="stolenBlockPeriod"><spring:message code="operator.default" />
</label> <label> <input type="radio" required="required" value="tilldate" class="blocktypeRadio"
onclick="document.getElementById('calender').style.display = 'block';"
name="stolenBlockPeriod"><spring:message code="operator.later" />
</label>
<div class="col s6 m2 responsiveDiv"
style="display: none; width: 30%; margin-right: 30%; float: right; margin-top: -15px" id="calender">
<div id="startdatepicker" class="input-group date">
<input type="text" id="stolenDatePeriod"
style="margin-top: -9px" /> <span class="input-group-addon"
style="color: #ff4081"><i class="fa fa-calendar"
aria-hidden="true" style="float: right; margin-top: -30px;"></i></span>
</div>

</div>


<div class="col s12 m2 l2" style="width: 40%; display: none; float: right; margin-right:30%;"
id="stolenDate">

<label for="TotalPrice" class="center-align"><spring:message code="operator.tilldate" /></label>
<div id="startdatepicker" class="input-group" style="margin-top: 10px;">

<input class="form-control" name="inputsaves" type="text"
id="startDateFilter" readonly /> <span class="input-group-addon"
style="color: #ff4081"><i
class="glyphicon glyphicon-calendar"
onclick="_Services._selectstartDate()"></i></span>
</div>
</div>
</div>

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
																id="singleDeviceAddress" pattern="[^[a-zA-Z0-9\s,'-]*$]{0,200}" 
																oninput="InvalidMsg(this,'input');" oninvalid="InvalidMsg(this,'input');"
																title= "<spring:message code="validation.200characters" />" required  maxlength="200" > <label
																for="address"><spring:message code="input.address" /><span
																class="star"> *</span></label>
														</div>

														<div class="input-field col s12 m6 l6">
															<input type="text" name="streetNumber"
																class="form-control boxBorder boxHeight"
																id="singleDevicestreetNumber" pattern="[^[a-zA-Z0-9\s,'-]*$]{0,20}" 
															oninput="InvalidMsg(this,'input');" oninvalid="InvalidMsg(this,'input');"
																title= "<spring:message code="validation.20Character" />" required   maxlength="20">
															<label for="streetNumber"><spring:message code="input.streetNumber" /> <span
																class="star"> *</span></label>
														</div>

														<div class="input-field col s12 m6 l6">
															<input type="text" name="village"
																id="singleDevicevillage" maxlength="20"
																pattern="[^[a-zA-Z0-9\s,'-]*$]{0,20}" 
																oninput="InvalidMsg(this,'input');" oninvalid="InvalidMsg(this,'input');"
																title= "<spring:message code="validation.address30characters" />" required   maxlength="30">
																 <label
																for="village"><spring:message code="input.village" /> <span class="star">*</span></label>
														</div>

														<div class="input-field col s12 m6 l6">
															<input type="text" name="locality"
																class="form-control boxBorder boxHeight"
																id="singleDevicelocality" maxlength="20"
																pattern="[^[a-zA-Z0-9\s,'-]*$]{0,30}" 
																oninput="InvalidMsg(this,'input');" oninvalid="InvalidMsg(this,'input');"
																title= "<spring:message code="validation.address30characters" />" required  maxlength="30">
															<label for="locality"><spring:message code="input.locality" /> <span class="star"> *</span></label>
														</div>

														<div class="input-field col s12 m6 l6">
															<input type="text" name="district"
																id="singleDevicedistrict" pattern="[A-Za-z0-9._%+-$@,/]+\.{0,30}" 
																oninput="InvalidMsg(this,'input');" oninvalid="InvalidMsg(this,'input');"
																title= "<spring:message code="validation.address30characters" />" required  maxlength="30"> <label
																for="district"><spring:message code="input.district" /><span class="star"> * </span></label>
														</div>

														<div class="input-field col s12 m6 l6">
															<input type="text" name="commune"
																id="singleDevicecommune" pattern="[A-Za-z0-9._%+-$@,/]+\.{0,30}"
																oninput="InvalidMsg(this,'input');" oninvalid="InvalidMsg(this,'input');"
																title= "<spring:message code="validation.address30characters" />" required  /  maxlength="30">
															 	<label
																for="commune"><spring:message code="input.commune" /> <span class="star"> *</span></label>
														</div>

														<div class="input-field col s12 m6 l6">
															<input type="text" name="pin"
																class="form-control boxBorder boxHeight"
																id="singleDevicepin"  pattern="[0-9]{1,6}" 
																oninput="InvalidMsg(this,'input');" oninvalid="InvalidMsg(this,'input');"
																title= "<spring:message code="validation.postalcode" />" required   maxlength="6">
																	<label
																for="pin"><spring:message code="registration.postalcode" /><span class="star"> *</span></label>
														</div>

														<div class="col s12 m6 l6">
															<label><spring:message code="input.Country" /><span class="star"> *</span></label> <select
																id="singleDevicecountry" class="browser-default"
																class="mySelect" style="padding-left: 0;" required></select>
														</div>

														<div class="col s12 m6 l6">
															<label><spring:message code="input.province" /><span class="star">*</span></label> <select
																id="singleDevicestate" class="browser-default"
																class="mySelect" style="padding-left: 0;" 
																oninput="InvalidMsg(this,'select');" oninvalid="InvalidMsg(this,'select');"
																title= "<spring:message code="validation.selectFieldMsg" />" required> </select>
														</div>
														
														  <div class="input-field col s12 m6">
															<input type="text" name="IndivisualStolenDate"
																id='IndivisualStolenDate' class='form-control datepick'
																autocomplete='off' 
																title="<spring:message code="validation.requiredMsg" />"  required /> 
																<label
																for="IndivisualStolenDate" class="center-align"><spring:message code="operator.stolenDate" /> <span class="star">*</span>
															</label> <span class="input-group-addon" style="color: #ff4081"><i
																class="fa fa-calendar" aria-hidden="true"></i></span>
														</div>
														
														<div class="file-field col s12 m6 l6">
														<h6 class="form-label" style="margin:0; font-size: 0.9rem;">
														<spring:message code="input.UploadFIR" />
														</h6>
														<div class="btn">
															<span><spring:message code="input.selectfile" /></span> <input type="file" 
															oninput="InvalidMsg(this,'fileType');" oninvalid="InvalidMsg(this,'fileType');"
															title= "<spring:message code="validation.NoChosen" />" required  
															placeholder="Upload FIR" id="uploadFirSingle">
														</div>
														<div class="file-path-wrapper">
															<input class="file-path validate" type="text" placeholder="Upload FIR"
																id="uploadFirSingleName" title="Please upload national ID image">
														</div>
													</div>

														<div class="input-field col s12 m12">
															<textarea id="singleDeviceRemark"
															oninput="InvalidMsg(this,'input');" oninvalid="InvalidMsg(this,'input');"
																title= "<spring:message code="validation.10000characters" />" maxlength="10000"
																class="materialize-textarea"></textarea>
															<label for="textarea1"><spring:message code="input.Remark" /></label>
														</div>
													</div>
												</div>
											</div>
											<span><spring:message code="input.requiredfields" /> <span
												class="star">*</span></span>


												<div class="input-field col s12 center">
													<button class="btn" type="submit" id="indivisualStolenButton"><spring:message code="button.submit" /></button>
													<a href="./stolenRecovery" class="btn modal-trigger"
														style="margin-left: 10px;"><spring:message code="button.cancel" /></a>
												</div>
										</form>
									</div>


<!-- ____________________________________________________Bulk stolen form________________________________________________________________________ -->


									<div id="Bulkform" class="col s12" style="display: none">
										<form action="" id="SingleImeiBlockform" onsubmit="return saveCompanyStolenRequest()" method="POST" enctype="multipart/form-data">
											<div class="input-field col s12 m6">
												<input type="text" name="companyName" pattern=[A-Za-z]{1,50} maxlength="30" 
																oninput="InvalidMsg(this,'input');" oninvalid="InvalidMsg(this,'input');"
																title= "<spring:message code="validation.30characters" />" required  
													id="bulkStolencompanyName"> <label
													for="companyName"><spring:message code="registration.companyName" /> <span class="star"> *</span></label>
											</div>

											<div class="input-field col s12 m6">
												<input type="text" name="address"
													class="form-control boxBorder boxHeight"
													id="bulkStolenaddress" pattern="[^[a-zA-Z0-9\s,'-]*$]{0,200}" 
											oninput="InvalidMsg(this,'input');" oninvalid="InvalidMsg(this,'input');"
																title= "<spring:message code="validation.200characters" />" required maxlength="200" /> <label
													for="address"><spring:message code="input.address" /> <span
													class="star">*</span></label>
											</div>

											<div class="input-field col s12 m6 l6">
												<input type="text" name="streetNumber"
													class="form-control boxBorder boxHeight"
													id="bulkStolenstreetNumber" pattern="[^[a-zA-Z0-9\s,'50" 
																oninput="InvalidMsg(this,'input');" oninvalid="InvalidMsg(this,'input');"
																title= "<spring:message code="validation.20Character" />" required 
																 maxlength="20" title="Please enter street number">
												<label for="streetNumber"><spring:message code="input.streetNumber" /><span
													class="star"> *</span></label>
											</div>

											<div class="input-field col s12 m6 l6">
												<input type="text" name="village" id="bulkStolenvillage" pattern="[^[a-zA-Z0-9\s,'-]*$]{0,30}" 
																oninput="InvalidMsg(this,'input');" oninvalid="InvalidMsg(this,'input');"
																title= "<spring:message code="validation.30characters" />" required   maxlength="30">
												
												 <label for="village"><spring:message code="input.village" />
													<span class="star"> *</span>
												</label>
											</div>

											<div class="input-field col s12 m6 l6">
												<input type="text" name="locality"
													class="form-control boxBorder boxHeight"
													id="bulkStolenlocality" pattern="[^[a-zA-Z0-9\s,'-]*$]{0,30}" 
													oninput="InvalidMsg(this,'input');" oninvalid="InvalidMsg(this,'input');"
																title= "<spring:message code="validation.30characters" />" required  maxlength="30"
													title="Please enter your locality"> <label
													for="locality"><spring:message code="input.locality" /> <span class="star"> *</span></label>
											</div>

											<div class="input-field col s12 m6 l6">
												<input type="text" name="district" id="bulkStolendistrict" pattern="[A-Za-z0-9._%+-$@,/]+\.{0,30}" 
												oninput="InvalidMsg(this,'input');" oninvalid="InvalidMsg(this,'input');"
																title= "<spring:message code="validation.30characters" />" required 
														maxlength="30" > <label for="district"><spring:message code="input.district" />
													<span class="star"> *</span>
												</label>
											</div>

											<div class="input-field col s12 m6 l6">
												<input type="text" name="commune" id="bulkStolencommune"
												pattern="[A-Za-z0-9._%+-$@,/]+\.{0,30}" 
																oninput="InvalidMsg(this,'input');" oninvalid="InvalidMsg(this,'input');"
																title= "<spring:message code="validation.30characters" />" required 
												 maxlength="30"> <label for="commune"><spring:message code="input.commune" />
													<span class="star"> *</span>
												</label>
											</div>

											<div class="input-field col s12 m6 l6">
												<input type="text" name="pin"
													class="form-control boxBorder boxHeight" id="bulkStolenpin" pattern="[0-9]{0,6}" 
													oninput="InvalidMsg(this,'input');" oninvalid="InvalidMsg(this,'input');"
																title= "<spring:message code="validation.postalcode" />" required 
															maxlength="6"> <label for="pin"><spring:message code="registration.postalcode" />
													<span class="star"> *</span>
												</label>
											</div>

											<div class="col s12 m6 l6">
												<label><spring:message code="input.Country" /><span class="star"> *</span></label> <select
													id="country2" class="browser-default" class="mySelect"
													oninput="InvalidMsg(this,'select');" oninvalid="InvalidMsg(this,'select');"
													title= "<spring:message code="validation.selectFieldMsg" />" required  / 
														style="padding-left: 0;"></select>
											</div>

											<div class="col s12 m6 l6">
												<label><spring:message code="input.province" /> <span class="star"> *</span></label> <select
													id="state2" class="browser-default" class="mySelect"
													oninput="InvalidMsg(this,'select');" oninvalid="InvalidMsg(this,'select');"
													title= "<spring:message code="validation.selectFieldMsg" />" required  /
													style="padding-left: 0;"></select>
											</div>

											<div class="col s12 m12" style="margin-top: 30px;">
												<h5><spring:message code="registration.authorizedpersonnel" /></h5>
												<hr>
											</div>
											<!-- <div class="col s12 m12">
                                                        <p><b>Authorized personnel</b> </p>
                                                    </div> -->

											<div class="input-field col s12 m4">
												<input type="text" name="bulkStolenfirstName" id="firstName" pattern="[a-zA-Z ]{1,20}" 
												oninput="InvalidMsg(this,'input');" oninvalid="InvalidMsg(this,'input');"
												title= "<spring:message code="validation.20Character" />" required  / maxlength="20">
												<label for="firstName"><spring:message code="input.firstName" /> <span class="star"> *</span></label>
											</div>

											<div class="input-field col s12 m4">
												<input type="text" name="middleName" pattern="[a-zA-Z ]{1,20}" 
												oninput="InvalidMsg(this,'input');" oninvalid="InvalidMsg(this,'input');"
												title= "<spring:message code="validation.20Character" />" maxlength="20"
													id="bulkStolenmiddleName"> <label for="middleName"><spring:message code="input.middleName" /></label>
											</div>

											<div class="input-field col s12 m4">
												<input type="text" name="lastName" id="bulkStolenlastName" pattern="[a-zA-Z ]{1,20}" 
												oninput="InvalidMsg(this,'input');" oninvalid="InvalidMsg(this,'input');"
												title= "<spring:message code="validation.20Character" />" required  /  maxlength="20">
												<label for="lastName"><spring:message code="input.lastName" /> <span class="star"> *</span></label>
											</div>

											<div class="input-field col s12 m6">
												<input type="email" name="officeEmail" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$"
												oninput="InvalidMsg(this,'email');" oninvalid="InvalidMsg(this,'email');" 
												title= "<spring:message code="validation.emial" />"  maxlength="30" id="bulkStolenofficeEmail">
													 <label
													for="officeEmail"><spring:message code="registration.officialemailid" /></label>
											</div>

											<div class="input-field col s12 m6">
												<!-- <p class="contact-label">Alternate Contact Number <span class="star">*</span></p> -->
												<input type="tel" name="phone" id="bulkStolenContact" required pattern="[0-9]{1,12}" 
												oninput="InvalidMsg(this,'input');" oninvalid="InvalidMsg(this,'input');"
												title= "<spring:message code="validation.10digits" />" maxlength="12"> <label for="phone2"><spring:message code="input.contactNum" /></label>
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
													id="deviceBulkStolenaddress" pattern="[^[a-zA-Z0-9\s,'-]*$]{0,200}" 
													oninput="InvalidMsg(this,'input');" oninvalid="InvalidMsg(this,'input');"
													title= "<spring:message code="validation.200characters" />" required  / maxlength="200"> <label
													for="address"><spring:message code="input.address" /> <span
													class="star"> *</span></label>
											</div>

											<div class="input-field col s12 m6 l6">
												<input type="text" name="streetNumber"
													class="form-control boxBorder boxHeight"
													id="deviceBulkStolenstreetNumber" pattern="[^[a-zA-Z0-9\s,'-]*$]{0,20}" 
													oninput="InvalidMsg(this,'input');" oninvalid="InvalidMsg(this,'input');"
													title= "<spring:message code="validation.20Character" />" required  / maxlength="20">
												<label for="streetNumber"><spring:message code="input.streetNumber" /> <span
													class="star"> *</span></label>
											</div>

											<div class="input-field col s12 m6 l6">
												<input type="text" name="village"
													id="deviceBulkStolenvillage" pattern="[^[a-zA-Z0-9\s,'-]*$]{0,30}" 
													oninput="InvalidMsg(this,'input');" oninvalid="InvalidMsg(this,'input');"
													title= "<spring:message code="validation.30characters" />" required  /  maxlength="30"> <label
													for="village"><spring:message code="input.village" /> <span class="star"> *</span></label>
											</div>

											<div class="input-field col s12 m6 l6">
												<input type="text" name="locality"
													class="form-control boxBorder boxHeight"
													id="deviceBulkStolenlocality" pattern="[^[a-zA-Z0-9\s,'-]*$]{0,30}" 
													oninput="InvalidMsg(this,'input');" oninvalid="InvalidMsg(this,'input');"
													title= "<spring:message code="validation.30characters" />" required  / maxlength="30">
												<label for="locality"><spring:message code="input.locality" /> <span class="star"> *</span></label>
											</div>

											<div class="input-field col s12 m6 l6">
												<input type="text" name="district"
													id="deviceBulkStolendistrict" pattern="[^[a-zA-Z0-9\s,'-]*$]{0,30}" 
													oninput="InvalidMsg(this,'input');" oninvalid="InvalidMsg(this,'input');"
													title= "<spring:message code="validation.30characters" />" required  /  maxlength="30"> <label
													for="district"><spring:message code="input.district" /> <span class="star"> *</span></label>
											</div>

											<div class="input-field col s12 m6 l6">
												<input type="text" name="commune"
													id="deviceBulkStolencommune" pattern="[A-Za-z0-9._%+-$@,/]+\.{0,30}" 
													oninput="InvalidMsg(this,'input');" oninvalid="InvalidMsg(this,'input');"
													title= "<spring:message code="validation.30characters" />" required  / maxlength="30"> <label
													for="commune"><spring:message code="input.commune" /><span class="star"> *</span></label>
											</div>

											<div class="input-field col s12 m6 l6">
												<input type="text" name="pin"
													class="form-control boxBorder boxHeight"
													id="deviceBulkStolenpin" pattern="[0-9]{0,6}" 
													oninput="InvalidMsg(this,'input');" oninvalid="InvalidMsg(this,'input');"
													title= "<spring:message code="validation.postalcode" />" required  / maxlength="6" > <label
													for="pin"><spring:message code="registration.postalcode" /><span class="star"> *</span></label>
											</div>

											<div class="col s12 m6 l6">
												<label><spring:message code="input.Country" /><span class="star"> *</span></label> <select
													id="country3" class="browser-default" class="mySelect"
													oninput="InvalidMsg(this,'select');" oninvalid="InvalidMsg(this,'select');"
													title= "<spring:message code="validation.selectFieldMsg" />" required  / 
													style="padding-left: 0;"></select>
											</div>

											<div class="col s12 m6 l6">
												<label><spring:message code="input.province" /> <span class="star"> *</span></label> <select
													id="state3" class="browser-default" class="mySelect"
													oninput="InvalidMsg(this,'select');" oninvalid="InvalidMsg(this,'select');"
													title= "<spring:message code="validation.selectFieldMsg" />" required  /
													style="padding-left: 0;" required></select>
											</div>

											<div class="col s12 m6 l6">
												<label><spring:message code="registration.complainttype" /><span class="star"> *</span></label> <select
													class="browser-default" id="deviceBulkStolenComplaint" 
													oninput="InvalidMsg(this,'select');" oninvalid="InvalidMsg(this,'select');"
													title= "<spring:message code="validation.selectFieldMsg" />" required  / >
													<option value="" disabled selected><spring:message code="registration.selectcomplainttype" /></option>

												</select>
											</div>

											<div class="input-field col s12 m6 l6"
												style="margin-top: 22px;">
												<input type="text" name="quantity"
													class="form-control boxBorder boxHeight" required
													id="deviceBulkStolenquantity" maxlength="7" pattern=[0-9]{1,7}
													oninput="InvalidMsg(this,'input');" oninvalid="InvalidMsg(this,'input');"
													title= "<spring:message code="validation.7digits" />" required  / > <label
													for="quantity"><spring:message code="input.quantity" /><span class="star"> *</span></label>
											</div>
											
											
											<div class="file-field col s12 m6">
												<h6 class="file-label">
													 <spring:message code="registration.uploaddevicelist" /><span class="star"> *</span>
												</h6>
												<div class="btn">
													<span><spring:message code="input.selectfile" /></span> <input type="file"
													oninput="InvalidMsg(this,'fileType');" oninvalid="InvalidMsg(this,'fileType');"
													title= "<spring:message code="validation.NoChosen" />" required  / id="deviceBulkStolenFile" accept=".csv">
												</div>
												<div class="file-path-wrapper">
													<input class="file-path validate" type="text"
														placeholder="Upload Device List"
													>
												</div>
											</div>


										 <div class="col s12 m6 blockingType" >
<p style="margin-top: 3px; margin-bottom: 5px"><spring:message code="operator.blocking" /></p>
<label style="margin-right: 2%;"> <input type="radio" class="blocktypeRadio" id=""
value="Immediate"
onclick="document.getElementById('stolenCalender').style.display = 'none';"
name="stolenBulkBlockPeriod" checked><spring:message code="operator.immediate" />
</label> <label style="margin-right: 2%;"> <input type="radio" class="blocktypeRadio"
value="Default"
onclick="document.getElementById('stolenCalender').style.display = 'none';"
name="stolenBulkBlockPeriod"><spring:message code="operator.default" />
</label> <label> <input type="radio" required="required" value="tilldate" class="blocktypeRadio"
onclick="document.getElementById('stolenCalender').style.display = 'block';"
name="stolenBulkBlockPeriod"><spring:message code="operator.later" />
</label>
<div class="col s6 m2 responsiveDiv"
style="display: none; width: 30%; margin-right: 30%; float: right; margin-top: -15px" id="stolenCalender">
<div id="Stolenstartdatepicker" class="input-group date">
<input type="text" id="stolenBulkDatePeriod"
style="margin-top: -9px" /> <span class="input-group-addon"
style="color: #ff4081"><i class="fa fa-calendar"
aria-hidden="true" style="float: right; margin-top: -30px;"></i></span>
</div>

</div>


<div class="col s12 m2 l2" style="width: 40%; display: none; float: right; margin-right:30%;"
id="stolenDate">

<label for="TotalPrice" class="center-align"><spring:message code="operator.tilldate" /></label>
<div id="Stolenstartdatepicker" class="input-group" style="margin-top: 10px;">

<input class="form-control" name="inputsaves" type="text"
id="startDateFilter" readonly /> <span class="input-group-addon"
style="color: #ff4081"><i
class="glyphicon glyphicon-calendar"
onclick="_Services._selectstartDate()"></i></span>
</div>
</div>
</div>
								<div class="input-field col s12 m6">
											<input type="text" name="bulkStolenDate"
												id='bulkStolenDate' class='form-control datepick'
												autocomplete='off' 
												title="<spring:message code="validation.requiredMsg" />"  required /> 
												<label
												for="bulkStolenDate" class="center-align"><spring:message code="operator.stolenDate" /> <span class="star"> *</span>
											</label> <span class="input-group-addon" style="color: #ff4081"><i
												class="fa fa-calendar" aria-hidden="true"></i></span>
										</div>
										
										<div class="file-field col s12 m6 l6">
														<h6 class="form-label" style="margin: 0; font-size: 0.9rem;">
															<spring:message code="input.UploadFIR" />   
														</h6>
														<div class="btn">
															<span><spring:message code="input.selectfile" /></span> <input type="file" 
															oninput="InvalidMsg(this,'fileType');" oninvalid="InvalidMsg(this,'fileType');"
															title= "<spring:message code="validation.NoChosen" />" required   
															placeholder="Upload FIR" id="uploadFirBulk">
														</div>
														<div class="file-path-wrapper">
															<input class="file-path validate" type="text" placeholder="Upload FIR"
																id="uploadFirSingleBulkName" title="Please upload national ID image">
														</div>
													</div>

											<div class="input-field col s12 m12" style="margin-top: 22px;">
												<textarea id="deviceBulkStolenRemark" 
												oninput="InvalidMsg(this,'input');" oninvalid="InvalidMsg(this,'input');"
												title= "<spring:message code="validation.10000characters" />"
												maxlength="10000" class="materialize-textarea"></textarea>
												<label for="textarea1"><spring:message code="input.Remark" /></label>
											</div>

											<div class="col s12 m12">
												<a href="./Consignment/sampleFileDownload/7"><spring:message code="input.downlaod.sample" /></a>
											</div>

											<div class="input-field col s12 center">
												<button class="btn" type="submit" id="bulkStolenButton" ><spring:message code="button.submit" /></button>
												<a href="./stolenRecovery" class="btn modal-trigger"
													style="margin-left: 10px;"><spring:message code="button.cancel" /></a>
											</div>
										</form>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
					<div class="modal" id="IndivisualStolenSucessPopup">
            <h6 class="modal-header"> <spring:message code="sidebar.Stolen/Recovery"/></h6>
            <div class="row" style="margin-top: 30px; padding: 0 20px;">
               <div class=" col s12 m12">
               	 <h6 id="sucessMessage"> <spring:message code="input.StolenSucessMessage1"/> <span id="IndivisualStolenTxnId"></span> <spring:message code="input.StolenSucessMessage2"/></h6>
                <div class="input-field col s12 center" style="margin:20px 0;">
                    <a href="./stolenRecovery" class="btn" style="margin:20px 0;"><spring:message code="modal.ok"/></a>
                </div>
               </div>
            </div>
        </div>
			</div>
		</div>
		<!--end container-->
	
	</section>




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
		src="${context}/resources/project_js/stolenRecovery.js"></script>
	<script type="text/javascript"
		src="${context}/resources/project_js/lawfulStolenRecovery.js"></script>
	<script type="text/javascript"
		src="${context}/resources/project_js/validationMsg.js"></script>
			<script type="text/javascript"
		src="${context}/resources/project_js/_dateFunction.js" async></script>	
		<script type="text/javascript"
		src="${context}/resources/project_js/profileInfoTab.js" async></script>

	<script type="text/javascript"
		src="${context}/resources/js/intlTelInput.js">
		</script>
	<script>
	$('.datepick').datepicker({
		dateFormat : "yy-mm-dd"
	});
	
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
		 
			        populateCountries
			        (   
			        		"country"
			        );


        var input2 = document.querySelector("#singleStolenphone2");
        window.intlTelInput(input2, {
            utilsScript: "${context}/resources/js/utils.js",
        });
        var input = document.querySelector("#singleStolenphone1");
        window.intlTelInput(input, {
            utilsScript: "${context}/resources/js/utils.js",
        });
        
        $('#stolenDatePeriod').datepicker({
        	dateFormat: "yy-mm-dd"
        	});

        $('#stolenDatePeriodedit').datepicker({
        	dateFormat: "yy-mm-dd"
        	});

        $('#stolenDatePeriodUnblock').datepicker({
        	dateFormat: "yy-mm-dd"
        	});
        
        $('#stolenBulkDatePeriod').datepicker({
        	dateFormat: "yy-mm-dd"
        	});

        $('#stolenDatePeriodedit').datepicker({
        	dateFormat: "yy-mm-dd"
        	});

        $('#stolenDatePeriodUnblock').datepicker({
        	dateFormat: "yy-mm-dd"
        	});

    </script>

</body>
</html>