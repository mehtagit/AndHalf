<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setDateHeader("Expires", 0);
	response.setHeader("Pragma", "no-cache");
	/*  session.setMaxInactiveInterval(200); //200 secs
	 session.setAttribute("usertype", null);  */
	if (session.getAttribute("usertype") != null) {
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
<title>Immigration</title>

<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta content="" name="description" />
<meta content="" name="author" />
<!-- Favicons-->
<link rel="icon" href="${context}/resources/images/DMC-Logo.png" sizes="32x32">

<script type="text/javascript"
	src="${context}/resources/js/plugins/jquery-1.11.2.min.js"></script>
<script type="text/javascript"
	src="${context}/resources/js/intlTelInput.js"></script>
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

<link rel="stylesheet"
	href="${context}/resources/project_css/iconStates.css">
<link rel="stylesheet"
	href="${context}/resources/project_css/intlTelInput.css">
<link rel="stylesheet"
	href="${context}/resources/custom_js/jquery-ui.css">
<script src="${context}/resources/custom_js/1.11.2_jquery-ui.js"></script>
<style>
</style>
</head>

<body data-id="19" data-roleType="${usertype}" data-userID="${userid}"
	data-userTypeID="${usertypeId}"
	data-selectedRoleTypeId="${selectedRoleTypeId}"
	data-selected-roleType="${selectedUserTypeId}"
	data-stolenselected-roleType="${stolenselectedUserTypeId}"
	session-valuepassportNo="${not empty param.passportNo ? param.passportNo : 'null'}"
	data-period="${period}"
	data-lang-param="${pageContext.response.locale}">
	<section id="content">
		<!--start container-->
		<div class="container">
			<div class="section">
				<div class="row card-panel">
					<h6 class="fixPage-modal-header ">
						<spring:message code="modal.header.updateimmigrationform" />
					</h6>
					<form action=""  onsubmit="return updateImmigrationForm()"
						 enctype="multipart/form-data" >
						<div class="col s12 m12 l12">
							<div class="row">
								<div class="row">

									<div>
										<h5>
											<spring:message code="input.personalInformation" />
										</h5>
										<hr>
									</div>
									<div id="nationalID">
										<div class="input-field col s12 m4">
											<input type="text" id="endUserfirstName"
												pattern="[a-zA-Z]{1,20}" placeholder=""
												oninput="InvalidMsg(this,'input','<spring:message code="validation.20Character" />');"
												oninvalid="InvalidMsg(this,'input','<spring:message code="validation.20Character" />');"
												required maxlength="20" /> <label for="firstName">
												<spring:message code="input.firstName" /> <span
												class="star">*</span>
											</label>
										</div>

										<div class="input-field col s12 m4">
											<input type="text" id="endUsermiddleName"
												pattern="[a-zA-Z]{1,20}" placeholder=""
												oninput="InvalidMsg(this,'input','<spring:message code="validation.20Character" />');"
												oninvalid="InvalidMsg(this,'input','<spring:message code="validation.20Character" />');"
												maxlength="20" /> <label for="middleName"> <spring:message
													code="input.middleName" />
											</label>
										</div>

										<div class="input-field col s12 m4">
											<input type="text" id="endUserlastName"
												pattern="[a-zA-Z]{1,20}" placeholder=""
												oninput="InvalidMsg(this,'input','<spring:message code="validation.20Character" />');"
												oninvalid="InvalidMsg(this,'input','<spring:message code="validation.20Character" />');"
												required maxlength="20" /> <label for="lastName"> <spring:message
													code="input.lastName" /> <span class="star">*</span></label>
										</div>

										<div class="input-field col s12 m6" id="nationalityDiv">
											<input type="text" id="nationality" name="nationality"
												pattern="[a-zA-Z]{1,25}" placeholder=""
												oninput="InvalidMsg(this,'input','<spring:message code="validation.15character" />');"
												oninvalid="InvalidMsg(this,'input','<spring:message code="validation.15character" />');"
												maxlength="25"> <label for="nationality" class="">
												<spring:message code="input.Nationality" /> <span
												class="star">*</span>
											</label>
										</div>
										<div class="input-field col s12 m12 l12">
											<input type="text" pattern="[^[a-zA-Z0-9\s,'-]*$]{0,200}" placeholder=""
												oninput="InvalidMsg(this,'input','<spring:message code="validation.200characters" />');"
												oninvalid="InvalidMsg(this,'input','<spring:message code="validation.200characters" />');"
												required maxlength="200"
												class="form-control boxBorder boxHeight" id="address">
											<label for="address"> <spring:message
													code="input.address" /> <span class="star">*</span>
											</label>
										</div>

										<div class="input-field col s12 m6 l6">
											<input type="text" class="form-control boxBorder boxHeight" placeholder=""
												id="streetNumber" pattern="[^[a-zA-Z0-9\s,'-]*$]{0,20}"
												oninput="InvalidMsg(this,'input','<spring:message code="validation.20Character" />');"
												oninvalid="InvalidMsg(this,'input','<spring:message code="validation.20Character" />');"
												required maxlength="20"> <label for="streetNumber">
												<spring:message code="input.streetNumber" /> <span
												class="star">*</span>
											</label>
										</div>

										<div class="input-field col s12 m6 l6">
											<input type="text" class="form-control boxBorder boxHeight"
												pattern="[^[a-zA-Z0-9\s,'-]*$]{0,30}" placeholder=""
												oninput="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');"
												oninvalid="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');"
												required class="form-control boxBorder boxHeight"
												id="locality" id="endUserlocality" maxlength="30"> <label
												for="locality"> <spring:message
													code="input.locality" /> <span class="star">*</span></label>
										</div>

										<div class="input-field col s12 m6 l6">
											<input type="text" id="village" placeholder=""
												pattern="[^[a-zA-Z0-9\s,'-]*$]{0,30}"
												oninput="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');"
												oninvalid="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');"
												required maxlength="30"> <label for="village">
												<spring:message code="input.village" /> <span class="star">*</span>
											</label>
										</div>

										<div class="input-field col s12 m6 l6">
											<input type="text" id="commune" pattern="[a-zA-Z]{1,30}" placeholder=""
												oninput="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');"
												oninvalid="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');"
												required maxlength="30"> <label for="commune">
												<spring:message code="input.commune" /> <span class="star">*</span>
											</label>
										</div>

										<div class="input-field col s12 m6 l6">
											<input type="text" id="endUserdistrict" placeholder=""
												pattern="[^[a-zA-Z0-9\s,'-]*$]{0,30}"
												oninput="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');"
												oninvalid="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');"
												required maxlength="30"> <label for="district">
												<spring:message code="input.district" /> <span class="star">*</span>
											</label>
										</div>

										<div class="input-field col s12 m6 l6">
											<input type="text" class="form-control boxBorder boxHeight"
												id="pin" pattern="[0-9]{1,6}" placeholder=""
												oninput="InvalidMsg(this,'input','<spring:message code="validation.6digit" />');"
												oninvalid="InvalidMsg(this,'input','<spring:message code="validation.6digit" />');"
												required maxlength="6"> <label for="pin"> <spring:message
													code="registration.postalcode" /><span class="star">*</span></label>
										</div>

										<div class="input-field col s12 m6 l6">
											<p
												style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">
												<spring:message code="input.Country" />
												<span class="star">*</span>
											</p>
											<select id="country" class="browser-default" class="mySelect"
												oninput="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
												oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
												style="padding-left: 0;" required></select>
										</div>

										<div class="input-field col s12 m6 l6"
											style="margin-bottom: 5px;">
											<p
												style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">
												<spring:message code="input.province" />
												<span class="star">*</span>
											</p>
											<select id="state" class="browser-default" class="mySelect"
												oninput="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
												oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
												style="padding-left: 0;" required></select>
										</div>

										<div class="input-field col s12 m6" style="margin: 0;">
											<p class="contact-label">
												<spring:message code="input.contactNum" />
												<span class="star">*</span>
											</p>
											<input type="text" id="phone" pattern="[0-9 + ]{4,15}" placeholder="" maxlength="15"
												oninput="InvalidMsg(this,'input','<spring:message code="validation.10digits" />');"
												oninvalid="InvalidMsg(this,'input','<spring:message code="validation.10digits" />');"
												required >
										</div>

										<div class="input-field col s12 m6">
											<input type="email" id="endUseremailID" placeholder=""
												pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$"
												oninput="InvalidMsg(this,'email');"
												oninvalid="InvalidMsg(this,'email');"
												title="<spring:message code="
                                                validation.emailformate" />"
												maxlength="30" /> <label for="emailID"> <spring:message
													code="input.EmailID" />
											</label>
										</div>


										<div class="input-field col s12 m6">
											<input type="text" id="endUserNID"
												pattern="[A-Za-z0-9]{1,15}" placeholder=""
												oninput="InvalidMsg(this,'input','<spring:message code="validation.15character" />');"
												oninvalid="InvalidMsg(this,'input','<spring:message code="validation.15character" />');"
												required maxlength="15" /> <label id="endUserLabelNID"
												for="NID"> <spring:message
													code="registration.nationalid" /> <span class="star">*</span></label>
										</div>
										<div class="file-field col s12 m6" style="margin-top: -8px;">
											<h6 style="font-size: 12px;" id="nidType">
												<spring:message code="input.IDImage" />
												<span class="star">*</span>
											</h6>
											<div class="btn">
												<span> <spring:message code="input.selectfile" />
												</span> <input type="file" onchange="fileTypeValueChanges('uploadnationalID')"
													accept="image/*" id="uploadnationalID" >
											</div>
											<div class="file-path-wrapper">
												<input class="file-path validate" type="text"
													id="nidPlaceHolder" placeholder="Upload NID Image" value="">
											</div>
										</div>

										<div class="col s12 m12" style="height: 4rem;">
											<label for="nationality"> <spring:message
													code="input.VIP" />
											</label>
											<div class=" boxHeight">
												<label><input class="with-gap" type="radio"
													name="selectvip" value="Y" onclick="selectVip()"> <span>
														<spring:message code="modal.yes" />
												</span> </label> <label> <input class="with-gap" value="N"
													type="radio"  name="selectvip"
													style="margin-left: 20px;" onclick="removeSelectVip()" />
													<span> <spring:message code="modal.no" /></span>
												</label>
											</div>
										</div>

										<div class="row" style="display: none;" id="vipUserDiv">
											<div class="input-field col s12 m6">
												<input type="text" id="departmentName" placeholder=""
													pattern="[a-zA-Z ]{1,50}"
													oninput="InvalidMsg(this,'input','<spring:message code="validation.50character" />');"
													oninvalid="InvalidMsg(this,'input','<spring:message code="validation.50character" />');"
													required maxlength="50" /> <label for="departmentName">
													<spring:message code="input.DepartmentName" /> <span
													class="star">*</span>
												</label>
											</div>

											<div class="input-field col s12 m6">
												<input type="text" id="endUserdepartmentID"
													pattern="[a-zA-Z0-9]{1,15}" placeholder=""
													oninput="InvalidMsg(this,'input','<spring:message code="validation.15character" />');"
													oninvalid="InvalidMsg(this,'input','<spring:message code="validation.15character" />');"
													required maxlength="15" /> <label for="departmentID">
													<spring:message code="input.DepartmentID" /><span
													class="star">*</span>
												</label>
											</div>

											<div class="file-field input-field col s12 m6 l6">
												<h6 style="color: #000;">
													<spring:message code="input.UploadIDImage" />
													<span class="star">*</span>
												</h6>
												<div class="btn">
													<span> <spring:message code="operator.file" /></span> <input
														type="file" accept="image/*"
														onchange="deptImageValidation()"
														oninput="InvalidMsg(this,'fileType','<spring:message code="validation.NoChosen" />');"
														oninvalid="InvalidMsg(this,'fileType','<spring:message code="validation.NoChosen" />');"
														required id="endUserDepartmentId"
														placeholder="Upload Department ID Image">
												</div>
												<div class="file-path-wrapper">
													<input class="file-path validate" type="text"
														id="endUSerNidaPlaceholder" 
														placeholder="Upload Department ID Image">
												</div>
											</div>
										</div>

										<div class="col s12 m12" style="height: 4rem;"
											id="askVisaDetails">
											<label for="nationality"> <spring:message
													code="input.AddVisa" /> <span class="star">*</span></label>
											<div class=" boxHeight">
												<label><input class="with-gap" type="radio"
													name="onVisa" value="Y" onclick="showVisaDetails()">
													<span> <spring:message code="modal.yes" /></span> </label> <label>
													<input class="with-gap" type="radio" id="onVisaNo"
													 name="onVisa" value="N"
													style="margin-left: 20px;" onclick="hideVisaDetails()" />
													<span> <spring:message code="modal.no" /></span>
												</label>
											</div>
										</div>

										<div class="row" id="visaDetails" style="display: none;">
											<div class="col s12 m6">
												<label for="visaType"> <spring:message
														code="input.VisaType" /> <span class="star">*</span></label> <select
													class="browser-default" id="visaType"
													oninput="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
													oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
													required style="height: 33px">
													<option value="" disabled selected>
														<spring:message code="input.SelectVisaType" />
													</option>

												</select>
											</div>

										
											<div class="input-field col s12 m6">
												<input type="text" id="datepicker" placeholder=""
													oninput="InvalidMsg(this,'input','<spring:message code="validation.date" />');"
													oninvalid="InvalidMsg(this,'input','<spring:message code="validation.date" />');"
													required maxlength="15" /> <label for="datepicker">
													<spring:message code="input.EntryCountry" /> <span
													class="star">*</span>
												</label>
											</div>

											<div class="input-field col s12 m6">
												<input type="text" id="visaNumber" placeholder=""
													pattern="[a-zA-Z0-9]{1,15}"
													oninput="InvalidMsg(this,'input','<spring:message code="validation.15character" />');"
													oninvalid="InvalidMsg(this,'input','<spring:message code="validation.15character" />');"
													required maxlength="15" /> <label for="visaNumber">
													<spring:message code="input.VisaNumber" />
												</label>
											</div>

									

											<div class="input-field col s12 m6">
												<input type="text" id="datepicker1" placeholder=""
													oninput="InvalidMsg(this,'input','<spring:message code="validation.date" />');"
													oninvalid="InvalidMsg(this,'input','<spring:message code="validation.date" />');"
													required maxlength="15" /> <label for="datepicker1">
													<spring:message code="input.VisaExpiry" /> <span
													class="star">*</span>
												</label>
											</div>

											<div class="file-field input-field col s12 m6">
												<h6 style="color: #000;">
													<spring:message code="input.UploadVisa" />
													<span class="star">*</span>
												</h6>
												<div class="btn">
													<span> <spring:message code="operator.file" /></span> <input
														type="file" id="visaImage" accept="image/*"
														oninput="InvalidMsg(this,'fileType','<spring:message code="validation.NoChosen" />');"
														onchange="visaImageValidation()"
														oninvalid="InvalidMsg(this,'fileType','<spring:message code="validation.NoChosen" />');"
														required placeholder="Upload Visa Image">
												</div>
												<div class="file-path-wrapper">
													<input class="file-path validate" type="text"
														id="ensUserVisaPlaceHolder"
														placeholder="Upload Visa Image">
												</div>
											</div>
										</div>
					

										<div class="col s12 m12">
											<p>
												<spring:message code="input.requiredfields" />
												<span class="star">*</span>
											</p>
										</div>
										<div class="row" style="padding-bottom: 50px;">
										
											<div class="input-field col s12 m12 center">
												<button id="endUserRegisterButton" type="submit" class="btn">
													<spring:message code="button.update" />
												</button>
												<a href="./manageDevicesStock?FeatureId=19" class="btn"
													style="margin-left: 10px;"> <spring:message
														code="button.cancel" /></a>
											</div>
										</div>
										
										
									</div>
								</div>

							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		<!--end container-->
		<div id="endUserRegisterDeviceModal" class="modal">
			<h6 class="modal-header">
				<spring:message code="modal.header.registerdevice" />
			</h6>
			<div class="modal-content">

				<div class="row">
					
					<h6 id="sucessMessageId">
						<spring:message code="modal.message.futureRef" />
						<span id="endUsertXnId"></span>
					</h6>
					<!--    </h6> -->
				</div>
				<div class="row">
					<div class="input-field col s12 center">
						<div class="input-field col s12 center">
							<a href="./manageDevicesStock?FeatureId=19" class="btn"> <spring:message
									code="modal.ok" /></a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

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
						<button class="modal-close  btn" onclick="clearFileName()"
							style="margin-left: 10px;">
							<spring:message code="modal.ok" />
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="visafileFormateModal" class="modal">
		<h6 class="modal-header">
			<spring:message code="fileValidationModalHeader" />
		</h6>
		<div class="modal-content">
			<div class="row">
				<h6 id="visafileErrormessage">
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
						<button class="modal-close  btn" onclick="clearVisaName()"
							style="margin-left: 10px;">
							<spring:message code="modal.ok" />
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="DeptfileFormateModal" class="modal">
		<h6 class="modal-header">
			<spring:message code="fileValidationModalHeader" />
		</h6>
		<div class="modal-content">
			<div class="row">
				<h6 id="DeptfileErrormessage">
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
						<button class="modal-close  btn" onclick="clearDeptName()"
							style="margin-left: 10px;">
							<spring:message code="modal.ok" />
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript"
		src="${context}/resources/js/materialize.js"></script>
	<script type="text/javascript"
		src="${context}/resources/js/plugins/data-tables/js/jquery.dataTables.js"></script>
	<script type="text/javascript"
		src="${context}/resources/js/plugins/data-tables/js/jquery.dataTables.min.js">
    </script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${context}/resources/js/plugins.js"></script>
	<%-- <script type="text/javascript"
		src="${context}/resources/js/Validator.js"></script> --%>
	<script type="text/javascript"
		src="${context}/resources/js/plugins/perfect-scrollbar/perfect-scrollbar.min.js">
    </script>
	<script type="text/javascript"
		src="${context}/resources/js/countries.js"></script>
	<!-- i18n library -->
	<script type="text/javascript"
		src="${context}/resources/project_js/CLDRPluralRuleParser.js?version=<%= (int) (Math.random() * 10) %>"></script>
	<script type="text/javascript"
		src="${context}/resources/i18n_library/i18n.js?version=<%= (int) (Math.random() * 10) %>"></script>
	<script type="text/javascript"
		src="${context}/resources/i18n_library/messagestore.js?version=<%= (int) (Math.random() * 10) %>"></script>

	<script type="text/javascript"
		src="${context}/resources/i18n_library/fallbacks.js?version=<%= (int) (Math.random() * 10) %>"></script>

	<script type="text/javascript"
		src="${context}/resources/i18n_library/language.js?version=<%= (int) (Math.random() * 10) %>"></script>

	<script type="text/javascript"
		src="${context}/resources/i18n_library/parser.js?version=<%= (int) (Math.random() * 10) %>"></script>


	<script type="text/javascript"
		src="${context}/resources/i18n_library/emitter.js?version=<%= (int) (Math.random() * 10) %>"></script>


	<script type="text/javascript"
		src="${context}/resources/i18n_library/bidi.js?version=<%= (int) (Math.random() * 10) %>"></script>

	<script type="text/javascript"
		src="${context}/resources/i18n_library/history.js?version=<%= (int) (Math.random() * 10) %>"></script>

	<script type="text/javascript"
		src="${context}/resources/i18n_library/min.js?version=<%= (int) (Math.random() * 10) %>"></script>
	
	<script type="text/javascript"
		src="${context}/resources/project_js/globalVariables.js"></script>
	<script type="text/javascript"
		src="${context}/resources/project_js/dragableModal.js"></script>
	<script type="text/javascript"
		src="${context}/resources/project_js/enterKey.js"></script>
	<script type="text/javascript"
		src="${context}/resources/project_js/update_immigrationForm.js"></script>
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