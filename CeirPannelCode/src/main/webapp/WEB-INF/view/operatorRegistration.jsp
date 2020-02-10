<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="msapplication-tap-highlight" content="no">
<meta name="description"
	content="Materialize is a Material Design Admin Template,It's modern, responsive and based on Material Design by Google. ">
<meta name="keywords"
	content="materialize, admin template, dashboard template, flat admin template, responsive admin template,">
<title>CEIR | Operator Portal</title>
<link
	href="${context}/resources/js/plugins/data-tables/css/jquery.dataTables.min.css"
	type="text/css" rel="stylesheet" media="screen,projection">
<link rel="apple-touch-icon-precomposed"
	href="${context}/resources/images/favicon/apple-touch-icon-152x152.png">
<!-- For iPhone -->
<meta name="msapplication-TileColor" content="#00bcd4">
<meta name="msapplication-TileImage"
	content="images/favicon/mstile-144x144.png">
<!-- For Windows Phone -->
<link rel="stylesheet"
	href="${context}/resources/font/font-awesome/css/font-awesome.min.css">

<!-- CORE CSS-->
<link href="${context}/resources/css/materialize.css" type="text/css"
	rel="stylesheet" media="screen,projection">
<link href="${context}/resources/css/style.css" type="text/css"
	rel="stylesheet" media="screen,projection">
<!-- Custome CSS-->
<link href="${context}/resources/css/custom/custom.css" type="text/css"
	rel="stylesheet" media="screen,projection">

<!-- INCLUDED PLUGIN CSS ON THIS PAGE -->
<link href="${context}/resources/js/plugins/prism/prism.css"
	type="text/css" rel="stylesheet" media="screen,projection">
<link
	href="${context}/resources/js/plugins/perfect-scrollbar/perfect-scrollbar.css"
	type="text/css" rel="stylesheet" media="screen,projection">
<link
	href="${context}/resources/js/plugins/chartist-js/chartist.min.css"
	type="text/css" rel="stylesheet" media="screen,projection">

<!-- Country -->
<script type="text/javascript" src="${context}/resources/js/country.js"></script>

<style>
input[type="checkbox"] {
	background-color: initial;
	cursor: default;
	-webkit-appearance: checkbox;
	box-sizing: border-box;
	margin: 3px 3px 3px 4px;
	padding: initial;
	border: initial;
}

footer {
	padding-left: 0;
}

.btn-flat {
	height: auto;
}

.dropdown-content li>a, .dropdown-content li>span {
	color: #444;
}

h6 {
	font-size: 1rem;
	line-height: 110%;
	margin: 0rem 0 0.4rem 0;
	/* margin-top: -20px !important; */
}

.file-upload-heading {
	margin-left: 0;
}

select {
	height: 2.2rem !important;
}

label {
	/*           font-size: 0.8rem; */
	
}

.select-lang-drpdwn {
	width: 75px;
	margin-top: -17px;
	border-bottom: none;
}
.fa-eye-slash, .fa-eye {
	position: absolute;
	right: 10px;
	top: 10px;
}
.section .registration-form {
padding-top: 1rem;
padding-bottom: 1rem;
width: 90%;
margin: auto;
border: solid 2px #444;
}
</style>
<script>
var contextpath = "${context}";
</script>
</head>

<body>
	<%String name=request.getParameter("type");%>

	<!--  Scripts
    ================================================ -->
	<!-- jQuery Library -->
	<!-- <script type="text/javascript" src="js/plugins/jquery-1.11.2.min.js"></script>-->
	<script type="text/javascript"
		src="${context}/resources/js/plugins/jquery-1.11.2.min.js"></script>
	<!-- ajax js -->
	<script type="text/javascript"
		src="${context}/resources/ajax/Registration.js"></script>
	<script type="text/javascript"
		src="${context}/resources/ajax/Profile.js"></script>
	<!--materialize js-->
	<!--<script type="text/javascript" src="js/materialize.js"></script>-->
	<!-- Compiled and minified JavaScript -->

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
	<script type="text/javascript"
		src="${context}/resources/js/materialize.js"></script>
	<script type="text/javascript" src="${context}/resources/js/country.js"></script>
	<!--prism
    <script type="text/javascript" src="js/prism/prism.js"></script>-->
	<!--scrollbar-->
	<script type="text/javascript"
		src="${context}/resources/js/plugins/perfect-scrollbar/perfect-scrollbar.min.js"></script>
	<!-- chartist -->
	<!--<script type="text/javascript" src="js/plugins/chartist-js/chartist.min.js"></script>-->

	<!-- data-tables -->
	<script type="text/javascript"
		src="${context}/resources/js/plugins/data-tables/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript"
		src="${context}/resources/js/plugins/data-tables/data-tables-script.js"></script>
<script type="text/javascript" src="${context}/resources/ajax/Password.js"></script>
	<!--plugins.js - Some Specific JS codes for Plugin Settings-->
	<!--<script type="text/javascript" src="js/plugins.js"></script>-->
	<!--custom-script.js - Add your own theme custom JS-->
	<script type="text/javascript"
		src="${context}/resources/js/custom-script.js"></script>

	<!-- //////////////////////////////////////////////////////////////////////////// -->

	<!-- START CONTENT -->
	<section id="content" id="mainPage">
		<!--start container-->
		<div class="container">
			<div class="section .section .registration-form {
padding-top: 1rem;
padding-bottom: 1rem;
width: 90%;
margin: auto;
border: solid 2px #444;
}">
				<form id="registrationForm" autocomplete="off"
					onsubmit="return saveOperatorRegistration()">
					<div class="card-panel registration-form">
						<div class="row">
							<div class="col s10 m10 select-lang-lable">
<label for="" style="font-size: 1rem;">Language :</label><i class="fa fa-globe fa-6" aria-hidden="true"></i>
							</div>
							<div class="col s2 m2 right" style="padding: 0;">
								<select class="browser-default select-lang-drpdwn" id="langlist">
									<option value="en">English</option>
									<option value="km">Khmer</option>
								</select>
							</div>
							<div class="col s12 m12">
								<h5>
									<spring:message code="operator.reg" />
								</h5>
								<span id="msg" style="color: red;">${msg}</span>
								<hr>
							</div>

							<input type="hidden" id="usertypeId" value="${usertypeId}">
							<input type="hidden" id="usertypeName" value="<%=name%>">
							<input type="hidden" id="type" value="2">
							<div class="row">
								<div class="input-field col s12 m4 l4">
									<input type="text" name="firstName" id="firstName"
										required="required" pattern="[A-Za-z]{0,20}" maxlength="20"
										title="Please enter alphabets upto 20 characters only">
									<label for="firstName" class="center-align"><spring:message
											code="input.firstName" /> <span class="star">*</span> </label>
								</div>

								<div class="input-field col s12 m4 l4">
									<input type="text" name="middleName"
										class="form-control boxBorder boxHeight" id="middleName"
										pattern="[A-Za-z]{0,20}" maxlength="20"
										title="Please enter alphabets upto 20 characters only">
									<label for="middleName"><spring:message
											code="input.middleName" /></label>
								</div>


								<div class="input-field col s12 m4 l4">
									<input type="text" name="lastName"
										class="form-control boxBorder boxHeight" id="lastName"
										pattern="[A-Za-z]{0,20}" maxlength="20"
										title="Please enter alphabets upto 20 characters only"
										required="required"
										title="Please enter alphabets upto 20 characters only">
									<label for="lastName"><spring:message
											code="input.lastName" /> <span class="star">*</span> </label>
								</div>
								<div class="col s12 m6 l6">
									<label><spring:message code="registration.type" /> <span
										class="star">*</span></label> <select class="browser-default"
										id="operatorType">
										<option value="" disabled selected><spring:message
												code="select.select" /></option>
										<!-- <option value="Smart">Smart</option>
                                        <option value="Metfone">Metfone</option>
                                        <option value="Seatle">Seatle</option>
                                        <option value="Cellcard">Cellcard</option> -->
									</select>
								</div>
							</div>

							<div class="row">
								<div class="input-field col s12 m12 l12">
									<input type="text" maxlength="200"
										pattern="[A-Za-z0-9\s]{0,200}" name="propertyLocation"
										class="form-control boxBorder boxHeight"
										title="Please enter alphanumeric with special character upto 200 characters only"
										id="propertyLocation" required="required"> <label
										for="propertyLocation"><spring:message
											code="input.address" /> <span class="star">*</span></label>
								</div>

								<div class="input-field col s12 m6 l6">
									<input type="text" name="street" maxlength="20"
										class="form-control boxBorder boxHeight" id="street"
										pattern="[A-Za-z0-9\s]{0,20}" required="required"
										title="Please enter alphanumeric with special character upto 20 characters only">
									<label for="street"><spring:message
											code="input.streetNumber" /> <span class="star">*</span> </label>
								</div>
								<div class="input-field col s12 m6 l6">
									<input type="text" name="village" maxlength="30"
										class="form-control boxBorder boxHeight" id="village"
										pattern="[A-Za-z0-9\s]{0,30}" required="required"
										title="Please enter alphanumeric with special character upto 30 characters only">
									<label for="village"><spring:message
											code="input.village" /> <span class="star">*</span> </label>
								</div>
								<div class="input-field col s12 m6 l6">
									<input type="text" name="locality" maxlength="30"
										class="form-control boxBorder boxHeight" id="locality"
										pattern="[A-Za-z0-9\s]{0,30}" required="required"
										title="Please enter alphanumeric with special character upto 30 characters only">
									<label for="locality"><spring:message
											code="input.locality" /> <span class="star">*</span> </label>
								</div>

								<div class="input-field col s12 m6 l6">
									<input type="text" name="district" maxlength="30"
										class="form-control boxBorder boxHeight" id="district"
										pattern="[A-Za-z0-9\s]{0,30}" required="required"
										title="Please enter alphanumeric with special character upto 30 characters only">
									<label for="district"><spring:message
											code="input.district" /> <span class="star">*</span> </label>
								</div>
								<div class="input-field col s12 m6 l6">
									<input type="text" name="commune" maxlength="30"
										class="form-control boxBorder boxHeight" id="commune"
										pattern="[A-Za-z0-9\s]{0,30}" required="required"
										title="Please enter alphanumeric with special character upto 30 characters only">
									<label for="commune"><spring:message
											code="input.commune" /> <span class="star">*</span> </label>
								</div>
								<div class="input-field col s12 m6 l6">
									<input type="text" name="postalCode" maxlength="30"
										class="form-control boxBorder boxHeight" id="postalCode"
										pattern="[A-Za-z0-9\s]{0,30}"
										title="Please enter alphanumeric with special character upto 30 characters only">
									<label for="postalCode"><spring:message
											code="input.postalCode" /></label>
								</div>



								<div class="input-field col s12 m6 l6">
									<p
										style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">
										<spring:message code="table.country" />
										<span class="star">*</span>
									</p>
									<select id="country" class="browser-default" class="mySelect"
										style="padding-left: 0;" required></select>
								</div>

								<div class="input-field col s12 m6 l6">
									<p
										style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">
										<spring:message code="input.province" />
										<span class="star">*</span>
									</p>
									<select id="state" class="browser-default" class="mySelect"
										style="padding-left: 0;" required></select>
								</div>


							</div>

							<div class="row">

								<div class="input-field col s12 m6 l6">
									<input type="text" name="passportNo" required="required"
										class="form-control boxBorder boxHeight"
										title="Please enter alphanumeric with special character upto 12 characters only"
										id="passportNo" maxlength="12" pattern="[A-Za-z0-9\s]{0,12}" />
									<label for="passportNo"><spring:message
											code="registration.nationalid" /> <span class="star">*</span></label>
								</div>

								<div class="file-field input-field col s12 m6 l6">
									<div class="btn">
										<span><spring:message
												code="registration.uploadnationalid" /></span><input type="file" id="NationalIdImage" placeholder="Upload National ID Image">
									</div>
									<div class="file-path-wrapper">
										<input class="file-path validate" type="text" placeholder="">
									</div>
								</div>

								<div class="file-field input-field col s12 m6 l6">
									<div class="btn">
										<span><spring:message code="registration.uploadphoto" /></span>
										<input id="photo" type="file" placeholder="">
									</div>
									<div class="file-path-wrapper">
										<input class="file-path validate" type="text" placeholder="">
									</div>
								</div>

								<div class="input-field col s12 m6 l6">
									<input type="text" name="employeeID" required="required"
										id="employeeId" maxlength="30"> <label
										for="employeeId"><spring:message
											code="registration.employeeid" /> <span class="star">*</span></label>
								</div>

								<div class="file-field input-field col s12 m6 l6">
									<div class="btn">
										<span><spring:message code="registration.uploadidcard" /></span>
										<input id="idCard" type="file" placeholder="">
									</div>
									<div class="file-path-wrapper">
										<input class="file-path validate" type="text" placeholder="">
									</div>
								</div>

								<div class="col s12 m6 l6">
									<label><spring:message code="registration.natureofemployment" />
										<span class="star">*</span></label> <select id="natureOfEmployment"
										class="browser-default" required>
										<option value="" disabled selected><spring:message
												code="registration.natureofemployment" /></option>
										<option value="Permanent">Permanent</option>
										<option value="Temporary">Temporary</option>
										<option value="Contract">Contract</option>
									</select>
								</div>
							</div>

							<div class="row">

								<div class="input-field col s12 m6 l6">
									<input type="text" name="designation" required="required"
										id="designation" maxlength="30"> <label
										for="designation"><spring:message
											code="registration.designationandtitle" /> <span
										class="star">*</span></label>
								</div>

								<div class="input-field col s12 m6 l6">
									<input type="text" name="reportingAuthorityName"
										id="authorityName" maxlength="30"> <label
										for="authorityName"><spring:message
											code="registration.reportingauthorityname" /></label>
								</div>

								<div class="input-field col s12 m6 l6">
									<input type="text" name="authorityEmail" maxlength="320"
										class="form-control boxBorder boxHeight" id="authorityEmail"
									title="Enter a valid email id" pattern="[^@]+@[^@]+\.[a-zA-Z]{2,320}">
									<label for="authorityEmail"><spring:message
											code="registration.ReportingAuthorityEmailid" /></label>
								</div>

								<div class="input-field col s12 m6 l6">
									<input type="text" name="authorityPhoneNo"
										id="authorityPhoneNo" maxlength="20" pattern="[0-9]{8,20}"
										title="Please enter phone number between 8 to 20 characters only">
									<label for="authorityPhoneNo"><spring:message
											code="registration.reportingauthoritycontactnumber" /></label>
								</div>

								<div class="input-field col s12 m6 l6">
									<input type="text" name="email" required="required" id="email"
										maxlength="320" title="Enter a valid email id" pattern="[^@]+@[^@]+\.[a-zA-Z]{2,320}">
										 <label for="email"><spring:message
											code="input.email" /><span class="star">*</span></label>
								</div>

								<div class="input-field col s12 m6 l6">
									<input type="text" name="phoneNo" maxlength="20"
										class="form-control boxBorder boxHeight" id="phoneNo"
										pattern="[0-9]{8,20}"
										title="Please enter phone number between 8 to 20 characters only"
										required="required"> <label for="phoneNo"><spring:message
											code="input.contactNum" /> <span class="star">*</span></label>
								</div>


							</div>





							<div class="row">
								<div class="input-field col s12 m6 l6">
									<input type="password" name="password"
										class="form-control boxBorder boxHeight password" id="password"
										pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,10}$"
										min="8"
										title="Please enter atleast one numeric char, one alphabet, one special character and must be of minumum 8 and maximum of 10 length"
										required="required"> <label for="password"><spring:message
											code="registration.password" /> <span class="star">*</span>
									</label>
									<div class="input-field-addon">
										<a href="javascript:void(0)"><i class="fa fa-eye-slash toggle-password"
											aria-hidden="true"></i></a>
									</div>
								</div>

								<div class="input-field col s12 m6 l6">
									<input type="password" name="rePassword"
										title="Please enter atleast one numeric char, one alphabet, one special character and must be of minumum 8 and maximum of 10 length"
										class="form-control boxBorder boxHeight password2" id="confirm_password"
										pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,10}$"
										min="8" required="required"> <label
										for="confirm_password"><spring:message
											code="registration.retypepassword" /> <span class="star">*</span>
									</label>
									<div class="input-field-addon">
										<a href="javascript:void(0)"><i class="fa fa-eye-slash toggle-password2"
											aria-hidden="true"></i></a>
									</div>
								</div>
							</div>


							<div class="row securityQuestionDiv">
								<div class="input-field col s12 m6 l6">
									<p
										style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">
										<spring:message code="registration.securityQuestion1" />
										<span class="star">*</span>
									</p>
									<input type="hidden" class="id" id="id0"> <select
										class="browser-default questionId" id="questionId0"
										name="questionId" required>
										<option value="" disabled selected><spring:message
												code="registration.securityQuestion1" /></option>
										<%--<c:forEach items="${questions}" var="question"> 
									<c:if test="${question.category=='1'}">
									<option value="${question.id}">${question.question}</option>
									</c:if>
									</c:forEach>  --%>
									</select>
								</div>

								<div class="input-field col s12 m6 l6">
									<input type="text" placeholder="" name="answer"
										class="form-control boxBorder boxHeight answer" id="answer0"
										pattern="[A-Za-z0-9\s]{0,50}" required="required"
										maxlength="50"
										title="Please enter alphanumeric upto 50 characters only">
									<label><spring:message code="registration.answer" /> <span
										class="star">*</span> </label>
								</div>
							</div>
							<div class="row securityQuestionDiv">
								<div class="input-field col s12 m6 l6">
									<p
										style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">
										<spring:message code="registration.securityQuestion2" />
										<span class="star">*</span>
									</p>
									<input type="hidden" class="id" id="id1" /> <select
										name="questionId" id="questionId1"
										class="browser-default questionId" required>
										<option value="" disabled selected><spring:message
												code="registration.securityQuestion2" /></option>
										<%--<c:forEach items="${questions}" var="question"> 		
										<c:if test="${question.category=='2'}">
									<option value="${question.id}">${question.question}</option>
									</c:if>
									</c:forEach> --%>
									</select>
								</div>

								<div class="input-field col s12 m6 l6">
									<input type="text" placeholder="" name="answer"
										class="form-control boxBorder boxHeight answer" id="answer1"
										pattern="[A-Za-z0-9\s]{0,50}" maxlength="50"
										title="Please enter alphanumeric upto 50 characters only"
										required="required"> <label><spring:message
											code="registration.answer" /> <span class="star">*</span> </label>
								</div>
							</div>

							<div class="row securityQuestionDiv">
								<div class="input-field col s12 m6 l6">

									<p
										style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">
										<spring:message code="registration.securityQuestion3" />
										<span class="star">*</span>
									</p>
									<input type="hidden" class="id" id="id2" /> <select
										name="questionId" id="questionId2"
										class="browser-default questionId" required>
										<option value="" disabled selected><spring:message
												code="registration.securityQuestion3" /></option>
									</select>
								</div>

								<div class="input-field col s12 m6 l6">
									<input type="text" name="answer" placeholder=""
										class="form-control boxBorder boxHeight answer" id="answer2"
										title="Please enter alphanumeric upto 50 characters only"
										maxlength="50" pattern="[A-Za-z0-9\s]{0,50}"
										required="required"> <label><spring:message
											code="registration.answer" /> <span class="star">*</span> </label>
								</div>
							</div>

							<div class="form-group form-actions">
								<span class="input-icon"> <img id="captchaImage"
									src="${context}/captcha">
									<button style="background: none; border: none; outline: none;"
										type="button" onclick="refreshCaptcha('captchaImage')">
										<i class="fa fa-refresh"></i>
									</button> <%-- <img src="${context}/captcha"" id="captchaImage">
						 <br>
                           <input type="button" onclick="refreshCaptcha('captchaImage')"> --%>
									<div class="input-field col s12 m6 l12">
										<input autocomplete="off" type="text" name="captcha"
											class="form-control boxBorder boxHeight" id="captcha"
											required="required"> <label for="captcha"> <spring:message
												code="registration.enteryourcaptcha" /> <span class="star">*</span>
										</label>
									</div>

								</span>
							</div>

							<p>
								<label style="color: black !important;"> <input
									name="disclamer" type="checkbox" required="required" /> <span>
										<span class="star">*</span>
									<spring:message code="registration.certifyMsg" />
								</span>
								</label>
							</p>
						</div>

						<div class="row">
							<span><spring:message code="input.requiredfields" /> <span
								class="star">*</span></span>
							<div class="input-field col s12 center">
								<button class="btn" id="btnSave" type="submit"
									style="margin-left: 10px;">
									<spring:message code="button.submit" />
								</button>
								<a href="${context}/" class="btn" style="margin-left: 10px;"><spring:message
										code="button.cancel" /></a>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
		<!--end container-->
	</section>
	<!-- END CONTENT -->



	<!-- //////////////////////////////////////////////////////////////////////////// -->



	<!-- Modal 1 start   -->


	<!-- Modal End -->

	<!-- Modal 2 start   -->

	<div id="submitActivateDeactivate" class="modal">
		<button type="button"
			class=" modal-action modal-close waves-effect waves-green btn-flat right"
			data-dismiss="modal">&times;</button>
		<div class="modal-content">

			<div class="row">
				<h6>
					<spring:message code="operator.request" />
				</h6>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<div class="input-field col s12 center">
						<button class="modal-close waves-effect waves-light btn"
							style="margin-left: 10px;" type="submit" name="add_user"
							id="add_user">
							<spring:message code="button.cancel" />
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal End -->

	<!-- Modal 2 start   -->

	<div id="cancelActivateDeactivate" class="modal">
		<button type="button"
			class=" modal-action modal-close waves-effect waves-green btn-flat right"
			data-dismiss="modal">&times;</button>
		<div class="modal-content">

			<div class="row">
				<h6>
					<spring:message code="registration.cancelRequest" />
				</h6>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<div class="input-field col s12 center">
						<a href="userRegistration.html" class="btn" type="submit"
							name="add_user" id="add_user"><spring:message
								code="modal.yes" /></a> <a href="#activateDeactivate"
							class="modal-close modal-trigger btn" style="margin-left: 10px;"><spring:message
								code="modal.no" /></a>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- START MAIN -->

	<div id="otpMessage" class="modal">
		<button type="button"
			class="modal-action modal-close waves-effect waves-green btn-flat right"
			data-dismiss="modal">&times;</button>
		<h6 class="modal-header">
			<spring:message code="registration.verifyotp" />
		</h6>
		<div class="modal-content">


			<div class="row">
				<div class="input-field col s12 center">
					<h6 id="otpResponse"></h6>
					<a href="${context}/login" class="btn"><spring:message
							code="modal.yes" /></a>
				</div>
			</div>
		</div>
	</div>


	<div id="otpMsgModal" class="modal"
		style="width: 40%; margin-left: 30%; margin-top: 10vh;">
		<h6 class="modal-header">
			<spring:message code="registration.verifyotp" />
		</h6>
		<div class="modal-content">
			<p style="padding: 10px;" class="center" id="otpMsg"></p>

			<a href="#otpVerification" class="btn modal-trigger modal-close"
				style="width: 100%; margin-top: 20px; margin-bottom: 20px;"><spring:message
					code="registration.verifyotp" /></a>
		</div>
	</div>





	<!-- modal start -->

<div id="otpVerification" class="modal" style="width: 40%;">
        <!-- <button type="button" class=" modal-action modal-close waves-effect waves-green btn-flat right"
            data-dismiss="modal">&times;</button> -->
               <h6 class="modal-header">Enter OTP</h6>
        <div class="modal-content">  
                <form id="verifyOtpForm" onsubmit="return verifyOtp()">
             <p class="center" id="verifyOtpResp"></p>
                        <input type="hidden" id="userid"  name="userid" value="${userId}">
                        <div class="row">          
                            <div class="input-field col s12 m12">
                                <input type="text" placeholder="Enter OTP of Email" name="emailOtp" maxlength="6"
                                 required="required" id="emailOtp" pattern="[0-9]{0,6}"
										title="Please enter 6 digit number" placeholder=""/>
                            </div> 
                            <div class="input-field col s12 m12">
                                <input placeholder="Enter OTP of Phone" type="text" name="phoneOtp" maxlength="6" 
										pattern="[0-9]{0,6}"
										title="Please enter 6 digit number" 
                                required="required" id="phoneOtp" placeholder=""/>
                            </div>
                        </div>
                        <a href="javascript:void(0)" onclick="resendOtp(); document.getElementById('resendOtp').style.display ='block';" class="right"><spring:message code="registration.resendotp" /></a>
                        <button type="submit" id="otpVerifyBtn"  class="btn" style="width: 100%; margin-top: 20px; margin-bottom: 20px;"><spring:message code="registration.done" /></button>
                    </form>
        </div>
    </div>

	<!-- Modal End -->
	<!-- ================================================
    Scripts
    ================================================ -->
	<!-- Modal End -->
	<script type="text/javascript">
    $('#langlist').on('change', function() {
    	window.lang=$('#langlist').val() == 'km' ? 'km' : 'en';
    	var url_string = window.location.href;
    	var url = new URL(url_string);
    	var type = url.searchParams.get("type");
    	window.location.assign("registration?type="+type+"&lang="+window.lang);			
    	}); 
    	
	
        $(document).ready(function () {
        	var url = new URL( window.location.href);
    		var langParameter = url.searchParams.get("lang");
            	$('#langlist').val(langParameter == 'km' ? 'km' : 'en');
      
            $('.modal').modal();
            questionDataByCategory();
            operatorList();
        });   

        $('.dropdown-trigger').dropdown();

       /*  $(document).ready(function () {
            $('select').material_select();
        }); */
        var password = document.getElementById("password")
        , confirm_password = document.getElementById("confirm_password");

      function validatePassword(){
        if(password.value != confirm_password.value) {
          confirm_password.setCustomValidity("Passwords Don't Match");
        } else {
          confirm_password.setCustomValidity('');
        }
      }

      password.onchange = validatePassword;
      confirm_password.onkeyup = validatePassword;
   
        populateCountries("country","state");
        populateStates("country","state");
   
        function myFunction() {
            var x = document.getElementById("mySelect").value;
            if (x == 'Individual') {
                document.getElementById("uploadFile").style.display = "block";
                document.getElementById("passportNumberDiv").style.display = "block";
                //document.getElementById("companyName").style.display = "none";
                $('#companyName').style.display = "none";
            } else {

                document.getElementById("uploadFile").style.display = "none";
                document.getElementById("passportNumberDiv").style.display = "none";
            }

            if (x == 'Company', 'Organization', 'Government') {
                document.getElementById("companyName").style.display = "block";
            } else {

                document.getElementById("companyName").style.display = "none";
            }
        }
      
    </script>




</body>

</html>