<%
Integer statusCode=(Integer)session.getAttribute("statusCode");
%>
<%
if(statusCode==200){
%>
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
	<meta http-equiv='cache-control' content='no-cache'>
<meta http-equiv='expires' content='-1'>
<meta http-equiv='pragma' content='no-cache'>
<title>CEIR | Importer Portal</title>
<link
	href="${context}/resources/js/plugins/data-tables/css/jquery.dataTables.min.css"
	type="text/css" rel="stylesheet" media="screen,projection">
<link rel="apple-touch-icon-precomposed"
	href="${context}/resources/images/favicon/apple-touch-icon-152x152.png">
<!-- For iPhone -->
<meta name="msapplication-TileColor" content="#00bcd4">
<meta name="msapplication-TileImage"
	content="${context}/resources/images/favicon/mstile-144x144.png">
<!-- For Windows Phone -->
<link rel="stylesheet"
	href="${context}/resources/font/font-awesome/css/font-awesome.min.css">
<!-- CORE CSS-->


<!--<link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection">-->
<!-- Compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
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
	<link href="${context}/resources/project_css/leanOverlay.css" type="text/css"
	rel="stylesheet" media="screen,projection">
<!-- Country -->
<!-- <script type="text/javascript" src="js/country.js"></script> -->
<style>
input[type="checkbox"] {
	display: none;
}

footer {
	padding-left: 0;
}

.btn-flat {
	height: auto;
}

.star {
	color: red;
}

.dropdown-content li>a, .dropdown-content li>span {
	color: #444;
}

.input-field>label {
	color: #444 !important;
}

[type="radio"]:not (:checked ), [type="radio"]:checked {
	opacity: 0;
}

input[type=text], input[type=password], input[type=email], input[type=url],
	input[type=time], input[type=date], input[type=datetime-local], input[type=tel],
	input[type=number], input[type=search], textarea.materialize-textarea {
	background-color: transparent !important;
	border: none !important;
	border-bottom: 1px solid #9e9e9e !important;
	border-radius: 0 !important;
	outline: none !important;
	height: 2.6rem !important;
	width: 100% !important;
	font-size: 1rem !important;
	margin: 0 0 5px 0 !important;
	padding: 0 !important;
	box-shadow: none !important;
	-webkit-box-sizing: content-box !important;
	-moz-box-sizing: content-box !important;
	box-sizing: content-box !important;
	transition: all .3s !important;
}

input
[
type
=
text
]
:focus
:not
 
(
[
readonly
]
 
)
{
border-bottom:1px
 
1
px
 
solid
 
#ff4081
 
!
important
;

	
box-shadow
:
 
0
1
px
 
0
0
#ff4081
 
!
important
;


}
input[type=text]:focus:not ([readonly] )+label {
	color: #ff4081 !important;
	background-color: transparent !important;
}

.input-field {
	position: relative;
	margin-top: 1rem;
	margin-bottom: 0;
}

.row {
	margin-left: auto;
	margin-right: auto;
	margin-bottom: 0;
}

.btn {
	background-color: #ff4081 !important;
}

select {
	background-color: transparent;
	border: none;
	border-bottom: 1px solid #9e9e9e;
	padding: 0;
	margin-top: 0;
	height: 2.6rem;
}

[type="checkbox"]:not (:checked ), [type="checkbox"]:checked {
	position: inherit;
	opacity: 1;
	pointer-events: none;
}

[type="checkbox"]+span:not (.lever ):before, [type="checkbox"]:not (.filled-in
	 )+span:not (.lever ):after {
	display: none;
}

input[type="checkbox"] {
	display: block;
}

[type="checkbox"]:not (:checked ), [type="checkbox"]:checked {
	float: left;
	margin-top: 5px;
}
.fa-eye-slash, .fa-eye {
	position: absolute;
	right: 10px;
	top: 10px;
}

.upload-file-label {
	margin: 0;
	
}

.file-field .btn {
	line-height: 2.4rem;
	height: 2.4rem;
}
.section .registration-form {
padding-top: 1rem;
padding-bottom: 1rem;
width: 90%;
margin: auto;
/* border: solid 2px #444; */
}


</style>
<script>
var contextpath = "${context}";
</script>
</head>

<body data-lang-param="${pageContext.response.locale}">
	<%String userType=request.getParameter("type");
String usertypeId="${usertypeId}";
%>

	<!-- Modal End -->
	<!-- ================================================
    Scripts
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
	<script type="text/javascript"
		src="${context}/resources/ajax/Password.js"></script>
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

	<!--plugins.js - Some Specific JS codes for Plugin Settings-->
	<!--<script type="text/javascript" src="js/plugins.js"></script>-->
	<!--custom-script.js - Add your own theme custom JS-->
	<script type="text/javascript"
		src="${context}/resources/js/custom-script.js"></script>

	<!-- //////////////////////////////////////////////////////////////////////////// -->
	<!-- //////////////////////////////////////////////////////////////////////////// -->
	<!-- START CONTENT -->
	<section id="content" id="mainPage">
		<!--start container-->
		<div class="container">
			<div class="section registration-form">
				<form id="registrationForm" autocomplete="off" onsubmit="return saveRegistration()">
					<div class="card-panel registration-form">
						<%-- <a href="${context}/"
							style="float: right; margin: -10px; margin-right: -20px;"><i
							class="fa fa-times boton" aria-hidden="true"></i></a> --%>
						<div class="row">
							<div class="col s10 m11 select-lang-lable">
								
								<i class="fa fa-globe fa-6" aria-hidden="true"></i>
							</div>
							<div class="col s2 m1 right" style="padding: 0;">
								<select class="browser-default select-lang-drpdwn" id="langlist">
									<option value="en">English</option>
									<option value="km"><spring:message code="lang.khmer" /></option>
								</select>
							</div>
							<div class="col s12 m12">
								<h5>
								<spring:message code="roletype.${param.type}" />
										<spring:message code="select.registration" /> 
								</h5>

								<hr>
								<span id="msg" style="color: red;">${msg}</span>
							</div>

							<input type="hidden" id="usertypeId" value="${usertypeId}">
							<input type="hidden" id="usertypeName" value="<%=userType%>">
							<div class="row">
								<div class="input-field col s12 m4 l4">
									<input type="text" name="firstName" id="firstName"
										pattern="[A-Za-z]{3,20}" maxlength="20"  oninput="InvalidMsg(this,'input','<spring:message code="validation.20Character" />');" oninvalid="InvalidMsg(this,'input','<spring:message code="validation.20Character" />');" 
										 required>
									<label for="firstName" class="center-align"> <spring:message code="input.firstName" /> <span class="star">*</span></label>
								</div>

								<div class="input-field col s12 m4 l4">
									<input type="text" name="middleName" id="middleName" pattern="[A-Za-z]{3,20}" maxlength="20" >
									<label for="middleName"><spring:message code="input.middleName" /></label>
								</div>

								<div class="input-field col s12 m4 l4">
									<input type="text" name="lastName" id="lastName"
										pattern="[A-Za-z]{3,20}" maxlength="20" oninput="InvalidMsg(this,'input','<spring:message code="validation.20Character" />');" oninvalid="InvalidMsg(this,'input','<spring:message code="validation.20Character" />');" 
						 required/>
									<label for="lastName"> <spring:message code="input.lastName" /> <span class="star">*</span>
									</label>
								</div>


								<div class="col s12 m6 l6">
									<label><spring:message code="registration.type" /> <span class="star">*</span></label>
									<select name="type" class="browser-default" id="type"  
										onchange="myFunction()" oninput="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');" oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');" required>
										<option value="" disabled selected><spring:message code="registration.type" /></option>
									</select>
								</div>

								<div class="input-field col s12 m6 l6" id="passportNumberDiv" style="display: none;">
									<input type="text" name="passportNo" id="passportNo" maxlength="12" pattern="[A-Za-z0-9\s]{8,12}"
									oninput="InvalidMsg(this,'input','<spring:message code="validation.12Character" />');" oninvalid="InvalidMsg(this,'input','<spring:message code="validation.12Character" />');"
									 required/>
									<label for="passportNo"><spring:message code="registration.nationalid/passporartnumber" /> <span class="star">*</span></label>
								</div>

								<div class="input-field col s12 m6 l6" id="companyNames" style="display: none; margin-top: 22px;">
									<input type="text" name="companyName" id="companyName" pattern="[A-Za-z\s]+{0,50}" maxlength="50"
									oninput="InvalidMsg(this,'input','<spring:message code="validation.50character" />');" oninvalid="InvalidMsg(this,'input','<spring:message code="validation.50character" />');"
									/>
									<label for="companyName"><spring:message code="registration.companyName" /> <span class="star">*</span></label>
								</div>

								<div class="row myRow" style="display: none;" id="uploadFile">
									<div class="col s12 m12">
										<h6 class="file-upload-heading"><spring:message code="registration.UploadNationalityInformation" /> <span class="star">*</span></h6>
										<div class="file-field input-field col s12 m6" style="margin-top: 5px; padding-left: 0;">
											<div class="btn">
												<span><spring:message code="input.selectfile" /></span> <input name="file" type="file" id="file" accept=".pdf"
												oninput="InvalidMsg(this,'fileType','<spring:message code="validation.file" />');" oninvalid="InvalidMsg(this,'fileType','<spring:message code="validation.file" />');" pattern="{0,30}"
												 >
											</div>
											<div class="file-path-wrapper">
												<input class="file-path validate responsive-file-div" id="fileText"  />
											</div>
										</div>
										<br> <br>
									</div>
									<!-- <p style="margin-left: 15px;"><a javascript:void(0)>Download Sample Format</a></p> -->
								</div>


								<div class="input-field col s12 m6 l6" style="margin-top:22px;">
									<input type="text" name="email" maxlength="320" id="email" pattern="[^@]+@[^@]+\.[a-zA-Z]{2,320}"
									oninput="InvalidMsg(this,'email','<spring:message code="validation.email" />');" oninvalid="InvalidMsg(this,'email','<spring:message code="validation.email" />');"  
									required />
										 <label for="email"><spring:message                        
											code="input.email" /> <span class="star">*</span> </label>
								</div>

								<div class="input-field col s12 m6 l6" style="margin-top: 22px;">
									<input type="text" name="phoneNo" maxlength="15" id="phoneNo" pattern="[0-9]{7,15}"
									oninput="InvalidMsg(this,'input','<spring:message code="validation.contact" />');" oninvalid="InvalidMsg(this,'input','<spring:message code="validation.contact" />');" 
										required/> <label for="phoneNo"><spring:message
											code="registration.phone" /> <span class="star">*</span> </label>
								</div>
							</div>

							<div class="row">
								<div class="input-field col s12 m12 l12">
									<input type="text" maxlength="200" pattern="[A-Za-z0-9._%+-$@,/]+\.+{0,200}" name="propertyLocation" id="propertyLocation" 
									oninput="InvalidMsg(this,'input','<spring:message code="validation.200characters" />');" oninvalid="InvalidMsg(this,'input','<spring:message code="validation.200characters" />');" 
									required> 
									<label for="propertyLocation"> <spring:message code="input.address" /> <span class="star">*</span></label>
								</div>

								<div class="input-field col s12 m6 l6">
									<input type="text" name="street" maxlength="20" id="street" pattern="[A-Za-z0-9._%+-$@,/]+\.+{0,20}"
									 oninput="InvalidMsg(this,'input','<spring:message code="validation.address20characters" />');" oninvalid="InvalidMsg(this,'input','<spring:message code="validation.address20characters" />');" 
									 required/>
									<label for="street"><spring:message code="input.streetNumber" /> <span class="star">*</span> </label>
								</div>
								<div class="input-field col s12 m6 l6">
									<input type="text" name="village" maxlength="30" id="village" pattern="[A-Za-z0-9._%+-$@,/]+\.{0,30}"
									oninput="InvalidMsg(this,'input','<spring:message code="validation.address30characters" />');" oninvalid="InvalidMsg(this,'input','<spring:message code="validation.address30characters" />');" 
									required/>
									<label for="village"><spring:message code="input.village" /> <span class="star">*</span> </label>
								</div>
								<div class="input-field col s12 m6 l6">
									<input type="text" name="locality" maxlength="30" id="locality" pattern="[A-Za-z0-9._%+-$@,/]+\.{0,30}"
									oninput="InvalidMsg(this,'input','<spring:message code="validation.address30characters" />');" oninvalid="InvalidMsg(this,'input','<spring:message code="validation.address30characters" />');" 
									 required/>
									<label for="locality"><spring:message code="input.locality" /> <span class="star">*</span> </label>
								</div>

								<div class="input-field col s12 m6 l6">
									<input type="text" name="district" maxlength="30" id="district" pattern="[A-Za-z0-9._%+-$@,/]+\.{0,30}"
									oninput="InvalidMsg(this,'input','<spring:message code="validation.address30characters" />');" oninvalid="InvalidMsg(this,'input','<spring:message code="validation.address30characters" />');" 
									 required/>
									<label for="district"><spring:message code="input.district" /> <span class="star">*</span> </label>
								</div>
								<div class="input-field col s12 m6 l6">
									<input type="text" name="commune" maxlength="30" id="commune" pattern="[A-Za-z0-9._%+-$@,/]+\.{0,30}"
									oninput="InvalidMsg(this,'input','<spring:message code="validation.address30characters" />');" oninvalid="InvalidMsg(this,'input','<spring:message code="validation.address30characters" />');" 
									 required/>
									<label for="commune"><spring:message code="input.commune" /> <span class="star">*</span> </label>
								</div>
								<div class="input-field col s12 m6 l6">
									<input type="text" name="postalCode" maxlength="6" id="postalCode" pattern="[0-9]{0,6}" 
									> 
									<label for="postalCode"><spring:message code="registration.postalcode" /></label>
								</div>



								<div class="col s12 m6 l6">
									<label><spring:message code="table.country" /> <span class="star">*</span></label>
									<select id="country" class="browser-default" class="mySelect"  
									onchange="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');" oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');" style="padding-left: 0;" required></select>
								</div>

								<div class="col s12 m6 l6">
									<label><spring:message code="input.province" /> <span class="star">*</span></label>
									<select id="state" class="browser-default" class="mySelect"
									onchange="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');" oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');" style="padding-left: 0;" required></select>
								</div>
							</div>


							<div class="row">
								<div class="col s12 m6 l6" style="margin-bottom: 10px;">
									<label for="vatNumber"><spring:message code="registration.vatnumber" /> <span class="star">*</span></label>
									<div class=" boxHeight">
										<label><input class="with-gap vatStatus" id="vatYes" value="1" name="vatStatus" type="radio"
											onclick="document.getElementById('vatNumberField').style.display = 'block';document.getElementById('vatFileDiv').style.display = 'block';vatChecked()">
											<span><spring:message code="registration.radioyes" /></span> </label> <label> <input class="with-gap vatStatus" name="vatStatus" type="radio" style="margin-left: 20px;" value="0"
											onclick="document.getElementById('vatNumberField').style.display = 'none';document.getElementById('vatFileDiv').style.display = 'none';vatChecked()"
											checked /> <span><spring:message code="registration.radiono" /></span>
										</label>
									</div>
								</div>
								<div class="col s12 m6 l6">
									<label data-error="Please select at least one option" for="usertypes"><spring:message code="registration.roletype" /> <span class="star">*</span></label>
									<select name="roles" class="validate" id="usertypes" 
									oninput="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"  oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');" multiple required>
										<option value="" disabled><spring:message code="table.roleType" /></option>
									</select> 
								</div>
								<div class="row">
									<div class="input-field col s12 m6 l6" style="display: none;" id="vatNumberField">
										<input type="text" name="vatNo" maxlength="15" id="vatNo" pattern="[A-Za-z0-9]{0,15}"
										oninput="InvalidMsg(this,'input','<spring:message code="validation.15alphanumeric" />');" oninvalid="InvalidMsg(this,'input','<spring:message code="validation.15alphanumeric" />');" 
										>
										<label for="vatNo"><spring:message code="registration.vatnumber" /> <span class="star">*</span></label>
									</div>

									<div id="vatFileDiv" style="display: none;">
										<div class="file-field col s12 m6">
											<p class="upload-file-label"><spring:message code="registration.vatfile" /> <span class="star">*</span></p>
											<div class="btn">
												<span><spring:message code="input.selectfile" /></span> <input name="file" type="file" id="vatFile" accept=".pdf"
												oninput="InvalidMsg(this,'fileType','<spring:message code="validation.file" />');" oninvalid="InvalidMsg(this,'fileType','<spring:message code="validation.file" />');"   />
											</div>
											<div class="file-path-wrapper">
												<input name="vatFile" class="file-path validate responsive-file-div" type="text"  >
											</div>
										</div>
										<br> <br>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="input-field col s12 m6 l6">
									<input type="password" name="password" class="password" id="password" pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,10}$" min="8" maxlength="10"
										oninput="InvalidMsg(this,'input','<spring:message code="validation.password" />');" oninvalid="InvalidMsg(this,'input','<spring:message code="validation.password" />');" 
											required /> 
										<label for="password"><spring:message code="registration.password" /> <span class="star">*</span></label>
									<div class="input-field-addon">
							<i class="fa fa-eye-slash teal-text toggle-password" aria-hidden="true"></i>
									</div>
								</div>

								<div class="input-field col s12 m6 l6">
									<input type="password" name="rePassword" class="password2" id="confirm_password" pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,10}$" maxlength="10"
										min="8" oninput="InvalidMsg(this,'input','<spring:message code="validation.password" />');" oninvalid="InvalidMsg(this,'input','<spring:message code="validation.password" />');" 
										 required /> 
										<label for="confirm_password"> <spring:message code="registration.retypepassword" /> <span class="star">*</span></label>
									<div class="input-field-addon">
					<i class="fa fa-eye-slash teal-text toggle-password2" aria-hidden="true"></i>
									</div>
								</div>
							</div>
							<div class="row securityQuestionDiv">
								<div class="col s12 m6 l6">
									<label><spring:message code="registration.securityQuestion1" /> <span class="star">*</span></label>
									<input type="hidden" class="id" id="id0"> <select class="browser-default questionId" id="questionId0" name="questionId"  
												onchange="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');" oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');" required>
										<option value="" disabled selected><spring:message code="registration.securityQuestion1" />
										</option>
									</select>
								</div>

								<div class="input-field col s12 m6 l6" style="margin-top: 22px;">
									<input type="text" name="answer" id="answer0" pattern="[A-Za-z0-9\s]{0,50}" required="required" maxlength="50"
									oninput="InvalidMsg(this,'input','<spring:message code="validation.50alphanumeric" />');" oninvalid="InvalidMsg(this,'input','<spring:message code="validation.50alphanumeric" />');" 
										 required />
									<label for="answer0"><spring:message code="registration.answer" /> <span class="star">*</span> </label>
								</div>
							</div>
							<div class="row securityQuestionDiv">
								<div class="col s12 m6 l6">
									<label><spring:message code="registration.securityQuestion2" /> <span class="star">*</span></label>
									<input type="hidden" class="id" id="id1" /> <select name="questionId" id="questionId1" class="browser-default questionId"  
												onchange="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');" oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');" required>
										<option value="" disabled selected><spring:message code="registration.securityQuestion2" /></option>
									</select>
								</div>

								<div class="input-field col s12 m6 l6" style="margin-top: 22px;">
									<input type="text" name="answer" class="answer" id="answer1" pattern="[A-Za-z0-9\s]{0,50}" maxlength="50"
									oninput="InvalidMsg(this,'input','<spring:message code="validation.50alphanumeric" />');" oninvalid="InvalidMsg(this,'input','<spring:message code="validation.50alphanumeric" />');" 
										 required /> 
										<label for="answer1"><spring:message code="registration.answer" /> <span class="star">*</span> </label>
								</div>
							</div>

							<div class="row securityQuestionDiv">
								<div class="col s12 m6 l6">
									<label><spring:message code="registration.securityQuestion3" /> <span class="star">*</span></label>
									<input type="hidden" class="id" id="id2" /> <select name="questionId" id="questionId2" class="browser-default questionId" 
									 onchange="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');" oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');" required>
										<option value="" disabled selected><spring:message code="registration.securityQuestion3" /></option>
									</select>
								</div>

								<div class="input-field col s12 m6 l6" style="margin-top: 22px;">
									<input type="text" name="answer" class="answer" id="answer2" maxlength="50" pattern="[A-Za-z0-9\s]{0,50}"
									oninput="InvalidMsg(this,'input','<spring:message code="validation.50alphanumeric" />');" oninvalid="InvalidMsg(this,'input','<spring:message code="validation.50alphanumeric" />');" 
											required /> 
										<label for="answer2"><spring:message code="registration.answer" /> <span class="star">*</span> </label>
								</div>
							</div>

							<div class="form-group form-actions">
								<span class="input-icon"> <img id="captchaImage" src="${context}/captcha">
									<button style="background: none; border: none; outline: none;" type="button" onclick="refreshCaptcha('captchaImage')">
										<i class="fa fa-refresh"></i>
									</button> <%-- <img src="${context}/captcha"" id="captchaImage">
						 			<br>
                           			<input type="button" onclick="refreshCaptcha('captchaImage')"> --%>
									<div class="input-field col s12 m6 l12">
										<input type="text" autocomplete="off" name="captcha" class="boxHeight" id="captcha"
										oninput="InvalidMsg(this,'input','<spring:message code="validation.captcha" />');" oninvalid="InvalidMsg(this,'input','<spring:message code="validation.captcha" />');"
										 required /> 
											<label for="captcha"> <spring:message code="registration.enteryourcaptcha" /> <span class="star">*</span></label>
									</div>
								</span>
							</div>
							<p>
								<label style="color: black !important; padding-left: 16px;">
									<input name="disclamer" id="disclamer" type="checkbox" value="" style="margin-left: 12px;" 
									oninput="InvalidMsg(this,'checkbox','<spring:message code="validation.checkbox" />');" oninvalid="InvalidMsg(this,'checkbox','<spring:message code="validation.checkbox" />');"
									required> <span> <span
										class="star">*</span> <spring:message code="registration.certifyMsg" /></span>
								</label>
							</p>
						</div>

						<div class="row">
							<span> <spring:message code="input.requiredfields" /><span class="star">*</span></span>
							<div class="input-field col s12 center">
								<%-- <a href="${context}/verifyOtp" class="btn" id="btnSave"> Submit</a> --%>
								<button disabled="disabled" class="btn" id="btnSave" type="submit" style="margin-left: 10px;">
									<spring:message code="button.submit" />
								</button>
								<a href="${context}/" class="btn" style="margin-left: 10px;"><spring:message code="registration.cancel" /></a>
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

	<div id="otpMsgModal" class="modal"
		style="width: 40%; margin-left: 30%; margin-top: 10vh;">
		<!-- <button type="button"
			class=" modal-action modal-close waves-effect waves-green btn-flat right"
			data-dismiss="modal">&times;</button> -->
		<h6 class="modal-header">
			<spring:message code="registration.verifyotp" />
		</h6>
		<div class="modal-content">
			<!-- <h4 class="header2 pb-2">User Info</h4> -->

			<p style="padding: 10px;" class="center" id="otpMsg"></p>

			<a href="#otpVerification" class="btn modal-trigger"
				style="width: 100%; margin-top: 20px; margin-bottom: 20px;"><spring:message
					code="registration.verifyotp" /></a>
		</div>
	</div>
	<!-- Modal End -->

	<!-- Modal 2 start   -->

	<!-- <div id="submitActivateDeactivate" class="modal">
		<button type="button"
			class=" modal-action modal-close waves-effect waves-green btn-flat right"
			data-dismiss="modal">&times;</button>
		<div class="modal-content">

			<div class="row">
				<h6>The request has been successfully registered with CEIR
					Admin. Please find the confirmation over registered mail <
					mail@mail.com> in 2 to 3 working days.</h6>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<div class="input-field col s12 center">
						<button class="modal-close waves-effect waves-light btn"
							style="margin-left: 10px;" type="submit" name="add_user"
							id="add_user">Cancel</button>
					</div>
				</div>
			</div>
		</div>
	</div> -->
	<!-- Modal End -->

	<!-- Modal 2 start   -->

	<div id="cancelActivateDeactivate" class="modal">
		<button type="button"
			class=" modal-action modal-close waves-effect waves-green btn-flat right"
			data-dismiss="modal">&times;</button>
		<div class="modal-content">

			<div class="row">
				<h6>
					<spring:message code="registration.doyouwanttocanceltherequest?" />
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
	<!-- <div id="">
        START WRAPPER
        <div class="wrapper">
            START CONTENT
            <section id="content">
                start container
                <div class="container">
                    <div class="section">
                        <div id="otpMsgModal" class="modal" style="width: 40%; margin-left: 30%;">
                            <h6 class="modal-header">Verify OTP</h6>
                            <p style="padding:10px;" class="center" id="otpMsg"></p>

                            <a href="#otpVerification" class="btn modal-trigger"
                               style="margin-left: 3%;width: 94%;background-color: #ff4081;margin-bottom:30px;" >verify otp</a>
 
                        </div>
                    </div>
                </div>
                end container
            </section>
            END CONTENT
        </div>
    </div> -->

	<!-- START WRAPPER -->
	<!-- //////////////////////////////////////////////////////////////////////////// -->

	<!-- START CONTENT -->
	<!--start container-->
	<!-- <div id="" class="card-panel modal" style="width: 40%; margin-left: 30%; margin-top: 10vh;">
                            <h6 class="modal-header">Verify OTP</h6>
                            <p class="center">The text and and an e-mail with OTP details has been sent to your
                                registered Phone Number and E-Mail ID</p>
                            <a href="#otpVerification" class="btn modal-trigger"
                                style="width: 100%; margin-top: 20px; margin-bottom: 20px;">verify otp</a>
                </div> -->
	<!-- END CONTENT -->

	<!-- END WRAPPER -->


	<div id="otpMessage" class="modal">
		<button type="button"
			class="modal-action modal-close waves-effect waves-green btn-flat right"
			data-dismiss="modal">&times;</button>
		<h6 class="modal-header">
			<spring:message code="registration.verifyotp" />
		</h6>
		<div class="modal-content">
			<h6 id="otpResponse"></h6>
			<div class="row">
				<div class="input-field col s12 center">
					<a href="${context}/login" class="btn"><spring:message
							code="modal.ok" /></a>
				</div>
			</div>
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
				<input type="hidden" id="userid" name="userid" value="${userId}">
				<div class="row">
					<div class="input-field col s12 m12">
						<input type="text" placeholder="Enter OTP of Email" name="emailOtp" maxlength="6" id="emailOtp"
							pattern="[0-9]{0,6}" oninput="InvalidMsg(this,'input','<spring:message code="validation.6Character" />');" oninvalid="InvalidMsg(this,'input','<spring:message code="validation.6Character" />');" 
							 required  />
					</div>
					<div class="input-field col s12 m12">
						<input placeholder="Enter OTP of Phone" type="text" name="phoneOtp" maxlength="6" pattern="[0-9]{0,6}"
							id="phoneOtp" oninput="InvalidMsg(this,'input','<spring:message code="validation.6Character" />');" oninvalid="InvalidMsg(this,'input','<spring:message code="validation.6Character" />');" 
							required  />
					</div>
				</div>
				<a href="javascript:void(0)"
					onclick="resendOtp(); document.getElementById('resendOtp').style.display ='block';"
					class="right"><spring:message code="registration.resendotp" /></a>
				<button type="submit" id="otpVerifyBtn" class="btn"
					style="width: 100%; margin-top: 20px; margin-bottom: 20px;">
					<spring:message code="registration.done" />
				</button>
			</form>
		</div>
	</div>

	<!-- Modal End -->

	
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
	<script type="text/javascript" src="${context}/resources/project_js/globalVariables.js"></script>
<script type="text/javascript"
		src="${context}/resources/project_js/backbutton.js"></script>
	<script type="text/javascript"
		src="${context}/resources/project_js/dragableModal.js"></script>	
			<script type="text/javascript"
		src="${context}/resources/project_js/enterKey.js"></script>
	    	<script type="text/javascript"
		src="${context}/resources/project_js/ValidationFileOutsidePortal.js"></script>
 <script type="text/javascript">

 $('#langlist').on('change', function() {
 	window.lang=$('#langlist').val() == 'km' ? 'km' : 'en';
 	var url_string = window.location.href;
 	var url = new URL(url_string);
 	var type = url.searchParams.get("type");
 	window.location.assign("registration?type="+type+"&lang="+window.lang);			
 	}); 
 	
         $(document).ready(function () {
        	 checkBoxClick();
         	$('#langlist').val(data_lang_param);
             	$.i18n().locale = data_lang_param;	
             			
             			$.i18n().load( {
             				'en': './resources/i18n/en.json',
             				'km': './resources/i18n/km.json'
             			} ).done( function() { 
             			});
             			
         	$('.modal-trigger').leanModal({
         		dismissible: false
         	});
         	
         	asTypeData();       	
             questionDataByCategory();
             usertypeData2("${usertypeId}");
         }); 
         populateCountries( "country",    "state");
         
        $("#country").val("Cambodia");
        
        populateStates( "country",
                "state" );
        
        
        function validatePassword(){
            if(password.value != confirm_password.value) {
              confirm_password.setCustomValidity($.i18n('password_mismatch'));
            } else {
              confirm_password.setCustomValidity('');
            }
          }

      password.onchange = validatePassword;
      confirm_password.onkeyup = validatePassword;

       
      function myFunction() {
          var x = document.getElementById("type").value;
          if (x == '0') {
          	$("input[name='vatStatus']").prop('checked',true);
          	vatShowHide();
          	$("input[name='vatStatus']").attr('disabled', false);
              document.getElementById("uploadFile").style.display = "block";
              document.getElementById("passportNumberDiv").style.display = "block";
              document.getElementById("companyNames").style.display = "none";
              $("#passportNo").prop('required',true);
              $("#companyName").prop('required',false);
              $("#companyName").val("");
              $("#file").prop('required',true);
          } else {
          	$("#vatYes").prop('checked',true);
          	vatShowHide();
          	$("input[name='vatStatus']").attr('disabled', true);
              document.getElementById("uploadFile").style.display = "none";
              document.getElementById("passportNumberDiv").style.display = "none";
              document.getElementById("companyNames").style.display = "block";
              $("#companyName").prop('required',true);
              $("#passportNo").prop('required',false);
              $("#passportNo").val("");
              $("#fileText").val("");
              $("#file").val("");
              $("#file").prop('required',false);
          }
      }
        
         
         function vatChecked(){
         	var radioValue = $("input[name='vatStatus']:checked").val();
         	if(radioValue==1){
         		$("#vatNo").prop('required',true);
         		$("#vatFile").prop('required',true);
         	}
         	else{
         		$("#vatNo").prop('required',false);
         		$("#vatFile").prop('required',false);
         		$("#vatNo").val("");
         		$("#vatFile").val("");
         		$("input[name='vatFile']").val("");
         	}
         }
         
         function vatShowHide(){
         	var radioValue = $("input[name='vatStatus']:checked").val();
         	if(radioValue==1){
            		$("#vatNo").prop('required',true);
         		$("#vatFile").prop('required',true);
         		document.getElementById("vatNumberField").style.display = "block";
         		document.getElementById("vatFileDiv").style.display = "block";
         	}
         	else{
          		$("#vatNo").val("");
         		$("#vatFile").val("");
         		$("input[name='vatFile']").val("");
         		$("#vatNo").prop('required',false);
         		$("#vatFile").prop('required',false);
         		document.getElementById("vatNumberField").style.display = "none";
         		document.getElementById("vatFileDiv").style.display = "none";
         	}
         }
         </script>
</body>
</html>
<%}
else{
%>
<%@include file="registrationPopup.jsp" %>
<%}%>