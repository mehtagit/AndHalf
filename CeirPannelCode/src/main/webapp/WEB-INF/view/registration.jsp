<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
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



input[type=text],
input[type=password],
input[type=email],
input[type=url],
input[type=time],
input[type=date],
input[type=datetime-local],
input[type=tel],
input[type=number],
input[type=search],
textarea.materialize-textarea {
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

input[type=text]:focus:not([readonly]) {
border-bottom: 1px solid #ff4081 !important;
box-shadow: 0 1px 0 0 #ff4081 !important;
}

input[type=text]:focus:not([readonly])+label {
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
height: 2.6rem ;
}
[type="checkbox"]:not(:checked), [type="checkbox"]:checked {
position: inherit;
opacity: 1;
pointer-events: none;
}
[type="checkbox"]+span:not(.lever):before, [type="checkbox"]:not(.filled-in)+span:not(.lever):after {
display: none;
}
input[type="checkbox"] {
display: block;
}

[type="checkbox"]:not(:checked), [type="checkbox"]:checked {
float: left;
margin-top: 5px;
}
</style>
<script>
var contextpath = "${context}";
</script>
</head>

<body>
<%String userType=request.getParameter("type");
String usertypeId="${usertypeId}";
%>
<!-- Modal End --> 
	<!-- ================================================
    Scripts
    ================================================ -->
	 <!-- jQuery Library -->
    <!-- <script type="text/javascript" src="js/plugins/jquery-1.11.2.min.js"></script>-->
  <script type="text/javascript" src="${context}/resources/js/plugins/jquery-1.11.2.min.js"></script>
       <!-- ajax js -->
    <script type="text/javascript" src="${context}/resources/ajax/Registration.js"></script>
      <script type="text/javascript" src="${context}/resources/ajax/Profile.js"></script>
    <!--materialize js-->
    <!--<script type="text/javascript" src="js/materialize.js"></script>-->
    <!-- Compiled and minified JavaScript -->
    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    <script type="text/javascript" src="${context}/resources/js/materialize.js"></script>
    <script type="text/javascript" src="${context}/resources/js/country.js"></script>
    <!--prism
    <script type="text/javascript" src="js/prism/prism.js"></script>-->
    <!--scrollbar-->
    <script type="text/javascript" src="${context}/resources/js/plugins/perfect-scrollbar/perfect-scrollbar.min.js"></script>
    <!-- chartist -->
    <!--<script type="text/javascript" src="js/plugins/chartist-js/chartist.min.js"></script>-->

    <!-- data-tables -->
    <script type="text/javascript" src="${context}/resources/js/plugins/data-tables/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="${context}/resources/js/plugins/data-tables/data-tables-script.js"></script>

    <!--plugins.js - Some Specific JS codes for Plugin Settings-->
    <!--<script type="text/javascript" src="js/plugins.js"></script>-->
    <!--custom-script.js - Add your own theme custom JS-->
    <script type="text/javascript" src="${context}/resources/js/custom-script.js"></script>

	<!-- //////////////////////////////////////////////////////////////////////////// -->
	<!-- //////////////////////////////////////////////////////////////////////////// -->
	<!-- START CONTENT -->
	<section id="content" id="mainPage">
		<!--start container-->
		<div class="container">
			<div class="section">
				<form id="registrationForm" onsubmit="return saveRegistration()">
					<div class="card-panel registration-form" >
						<%-- <a href="${context}/"
							style="float: right; margin: -10px; margin-right: -20px;"><i
							class="fa fa-times boton" aria-hidden="true"></i></a> --%>
						<div class="row">
						<div class="col s10 m11 select-lang-lable">
<label for="" style="font-size:1rem;">Language :</label>
</div>
<div class="col s2 m1 right" style="padding: 0;">
<select class="browser-default select-lang-drpdwn">
<option value="1">English</option>
<option value="2">Khmer</option>
</select>
</div>
<div class="col s12 m11"><h5><%=request.getParameter("type") %> Registration</h5>
							<hr>
							<span id="msg" style="color: red;">${msg}</span></div>
							
                     <input type="hidden" id="usertypeId" value="">
               <input type="hidden" id="usertypeName" value="<%=userType%>">
               			<div class="row">
								<div class="input-field col s12 m4 l4">
									<input type="text" name="firstName" id="firstName"
										required="required" pattern="[A-Za-z]{0,20}" maxlength="20"
										title="Please enter alphabets upto 20 characters only">
									<label for="firstName" class="center-align">First Name
										<span class="star">*</span>
									</label>
								</div>

								<div class="input-field col s12 m4 l4">
									<input type="text" name="middleName"
										class="form-control boxBorder boxHeight" id="middleName"
										pattern="[A-Za-z]{0,20}" maxlength="20"
										title="Please enter alphabets upto 20 characters only">
									<label for="middleName">Middle Name</label>
								</div>

								<div class="input-field col s12 m4 l4">
									<input type="text" name="lastName"
										class="form-control boxBorder boxHeight" id="lastName"
										pattern="[A-Za-z]{0,20}" maxlength="20"
										title="Please enter alphabets upto 20 characters only"
										required="required"
										title="Please enter alphabets upto 20 characters only">
									<label for="lastName">Last Name <span class="star">*</span>
									</label>
								</div>


								<div class="input-field col s12 m6 l6">
									<p
										style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">
										AsType <span class="star">*</span>
									</p>
									<select name="type" class="browser-default" id="type"
										onchange="myFunction()" required>
										<option value="" disabled selected>Type</option>
									</select>
								</div>

								<div class="input-field col s12 m6 l6" id="passportNumberDiv"
									style="display: none;">
									<input type="text" name="passportNo"
										class="form-control boxBorder boxHeight"
										title="Please enter alphanumeric with special character upto 12 characters only"
										id="passportNo" maxlength="12" pattern="[A-Za-z0-9\s]{0,12}" />
									<label for="passportNo">National ID/Passport Number <span
										class="star">*</span></label>
								</div>

								<div class="input-field col s12 m6 l6" id="companyNames"
									style="display: none;">
									<input type="text" name="companyName"
										class="form-control boxBorder boxHeight" id="companyName"
										pattern="[A-Za-z\s]{0,50}" maxlength="50"
										title="Please enter alphanumeric upto 50 characters only">
									<label for="companyName">Company Name <span class="star">*</span></label>
								</div>

								<div class="row myRow" style="display: none;" id="uploadFile">
									<div class="col s12 m12">
										<h6 class="file-upload-heading">
											Upload Nationality Information<span class="star">*</span>
										</h6>
										<div class="file-field input-field col s12 m6"
											style="margin-top: 5px; padding-left: 0;">
											<div class="btn">
												<span>Select File</span> <input name="file" type="file"
													id="file" accept=".pdf">
											</div>  
											<div class="file-path-wrapper">
												<input class="file-path validate responsive-file-div"
													type="text">
											</div>
										</div>
										<br>
										<br>
									</div>
									<!-- <p style="margin-left: 15px;"><a href="#">Download Sample Format</a></p> -->
								</div>


								<!-- <div class="input-field col s12 m6 l6">
									<input  type="text" required="required" name="passportNo"
										class="form-control boxBorder boxHeight"
										title="Please enter alphanumeric with special character upto 12 characters only"
										 id="passportNumber" maxlength="12"
										 pattern="[A-Za-z0-9\s]{0,12}"> <label for="passportNumber">National
										ID/Passport Number <span class="star">*</span>
									</label>
								</div> -->

								<div class="input-field col s12 m6 l6">
									<input type="email" name="email"
										class="form-control boxBorder boxHeight" id="email"
										required="required"> <label for="email">Email
										<span class="star">*</span>
									</label>
								</div>

								<div class="input-field col s12 m6 l6">
									<input type="text" name="phoneNo" maxlength="20"
										class="form-control boxBorder boxHeight" id="phoneNo"
										pattern="[0-9]{8,20}"
										title="Please enter phone number between 8 to 20 characters only"
										required="required"> <label for="phoneNo">Phone
										Number <span class="star">*</span>
									</label>
								</div>

								<!-- <div class="input-field col s12 m6 l6">
									<input type="text" name="companyName" required="required"
										class="form-control boxBorder boxHeight" id="company"
										 pattern="[A-Za-z\s]{0,50}"  maxlength="50"
										 title="Please enter alphanumeric upto 50 characters only"
										 > <label for="company">Company
										Name <span class="star">*</span>
									</label>
								</div> -->
							</div>

								<div class="row">
								<div class="input-field col s12 m12 l12">
									<input type="text" maxlength="200"
										pattern="[A-Za-z0-9\s]{0,200}" name="propertyLocation"
										class="form-control boxBorder boxHeight"
										title="Please enter alphanumeric with special character upto 200 characters only"
										id="propertyLocation" required="required"> <label
										for="propertyLocation">Address(Property Location) <span
										class="star">*</span></label>
								</div>

								<div class="input-field col s12 m6 l6">
									<input type="text" name="street" maxlength="20"
										class="form-control boxBorder boxHeight" id="street"
										pattern="[A-Za-z0-9\s]{0,20}" required="required"
										title="Please enter alphanumeric with special character upto 20 characters only">
									<label for="street">Street Number <span class="star">*</span>
									</label>
								</div>
									<div class="input-field col s12 m6 l6">
									<input type="text" name="village" maxlength="30"
										class="form-control boxBorder boxHeight" id="village"
										pattern="[A-Za-z0-9\s]{0,30}" required="required"
										title="Please enter alphanumeric with special character upto 30 characters only">
									<label for="village">Village <span class="star">*</span>
									</label>
								</div>
								<div class="input-field col s12 m6 l6">
									<input type="text" name="locality" maxlength="30"
										class="form-control boxBorder boxHeight" id="locality"
										pattern="[A-Za-z0-9\s]{0,30}" required="required"
										title="Please enter alphanumeric with special character upto 30 characters only">
									<label for="locality">Locality <span class="star">*</span>
									</label>
								</div>

								<div class="input-field col s12 m6 l6">
									<input type="text" name="district" maxlength="30"
										class="form-control boxBorder boxHeight" id="district"
										pattern="[A-Za-z0-9\s]{0,30}" required="required"
										title="Please enter alphanumeric with special character upto 30 characters only">
									<label for="district">District <span class="star">*</span>
									</label>
								</div>
								<div class="input-field col s12 m6 l6">
									<input type="text" name="commune" maxlength="30"
										class="form-control boxBorder boxHeight" id="commune"
										pattern="[A-Za-z0-9\s]{0,30}" required="required"
										title="Please enter alphanumeric with special character upto 30 characters only">
									<label for="commune">Commune <span class="star">*</span>
									</label>
								</div>
								<div class="input-field col s12 m6 l6">
									<input type="text" name="postalCode" maxlength="30"
										class="form-control boxBorder boxHeight" id="postalCode"
										pattern="[A-Za-z0-9\s]{0,30}"
										title="Please enter alphanumeric with special character upto 30 characters only">
									<label for="postalCode">Postal Code</label>
								</div>
								
								
								
								<div class="input-field col s12 m6 l6">
									<p
										style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">
										Country <span class="star">*</span>
									</p>
									<select id="country" class="browser-default" class="mySelect"
										style="padding-left: 0;" required></select>
								</div>

								<div class="input-field col s12 m6 l6">
									<p
										style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">
										Province <span class="star">*</span>
									</p>
									<select id="state" class="browser-default" class="mySelect"
										style="padding-left: 0;" required></select>
								</div>
							</div>
							
							
							<!-- <div class="row">
								<div class="col s12 m6 l6" style="margin-bottom: 20px;">
									<label for="vatNumber">VAT Registration <span
										class="star">*</span></label>
									<div class=" boxHeight">
                                        <label><input value="1" class="with-gap" name="vatStatus" type="radio"
                                                onclick="document.getElementById('vatNumberField').style.display = 'block';">
                                            <span>Yes</span>
                                        </label>
                                        <label>
                                            <input value="0" class="with-gap" name="vatStatus" type="radio"
                                                style="margin-left: 20px;"
                                                onclick="document.getElementById('vatNumberField').style.display = 'none';"
                                                checked />
                                            <span>No</span>
                                        </label>
                                    </div>
								</div>

								<div class="input-field col s12 m6 l6" style="display: none;"
									id="vatNumberField">
									<input type="text" name="vatNo" maxlength="15"
										class="form-control boxBorder boxHeight" id="vatNumber"
										pattern="[A-Za-z0-9]{0,15}"
								title="Please enter alphanumeric upto 15 characters only"		
										> <label for="roleType">VAT
										Number <span class="star">*</span>
									</label>
								</div>
							</div> -->


							<div class="row">
								<div class="col s12 m6 l6" style="margin-bottom: 20px;">
									<label for="vatNumber">VAT Registration <span
										class="star">*</span></label>
									<div class=" boxHeight">
										<label><input class="with-gap vatStatus" value="1"
											name="vatStatus" type="radio"
											onclick="document.getElementById('vatNumberField').style.display = 'block';document.getElementById('vatFileDiv').style.display = 'block';vatChecked()">
											<span>Yes</span> </label> <label> <input
											class="with-gap vatStatus" name="vatStatus" type="radio"
											style="margin-left: 20px;" value="0"
											onclick="document.getElementById('vatNumberField').style.display = 'none';document.getElementById('vatFileDiv').style.display = 'none';vatChecked()"
											checked /> <span>No</span>
										</label>
									</div>
								</div>
								<div class="input-field col s12 m6 l6">
									<!-- <p
										style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">
										Role Type <span class="star">*</span>
									</p> -->

									<select  name="roles" class="validate" id="usertypes" multiple required>
										<option value="" disabled>Role Type <span
												class="star"></span></option>

									</select>
									<label  data-error="Please select at least one option"  for="usertypes">Role Type <span class="star">*</span></label>
								</div>
								<div class="row">
								<div class="input-field col s12 m6 l6" style="display: none;"
									id="vatNumberField">
									<input type="text" name="vatNo" maxlength="15"
										class="form-control boxBorder boxHeight" id="vatNo"
										pattern="[A-Za-z0-9]{0,15}"
										title="Please enter alphanumeric upto 15 characters only">
									<label for="vatNo">VAT Number <span class="star">*</span></label>
								</div>
								
								<div  id="vatFileDiv" class="col s12 m12" style="display: none;">
										<h6 class="file-upload-heading">
										VAT File<span class="star">*</span>
										</h6>
										<div class="file-field input-field col s12 m6"
											style="margin-top: 5px; padding-left: 0;">
											<div class="btn">
												<span>Select File</span> <input name="file" type="file"
													id="vatFile" accept=".pdf">
											</div>  
											<div class="file-path-wrapper">
												<input  name="vatFile" class="file-path validate responsive-file-div"
													type="text">
											</div>
										</div>
										<br>
										<br>
									</div>
								</div>
							</div>
						<div class="row">
							<div class="input-field col s12 m6 l6">
								<input type="password" name="password"
									class="form-control boxBorder boxHeight" id="password"
									pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,10}$"  min="8"
									title="Please enter atleast one numeric char, one alphabet, one special character and must be of minumum 8 and maximum of 10 length"
									required="required"> <label for="password">Password
									<span class="star">*</span>
								</label>  
							</div>

							<div class="input-field col s12 m6 l6">
								<input type="password" name="rePassword" 
	title="Please enter atleast one numeric char, one alphabet, one special character and must be of minumum 8 and maximum of 10 length" class="form-control boxBorder boxHeight" id="confirm_password"
									pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,10}$"  min="8"
									required="required"> <label for="confirm_password">Retype
									Password <span class="star">*</span>
								</label>
							</div>
						</div>
						<div class="row securityQuestionDiv">
							<div class="input-field col s12 m6 l6">
								<p
									style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">
									Security Question 1 <span class="star">*</span>
								</p>
								<input type="hidden" class="id" id="id0"> <select
									class="browser-default questionId" id="questionId0"
									name="questionId" required>
									<option value="" disabled selected>Security Question 1</option>
								</select>
							</div>

							<div class="input-field col s12 m6 l6">
								<input type="text" placeholder="" name="answer"
									class="form-control boxBorder boxHeight answer" id="answer0"
									pattern="[A-Za-z0-9\s]{0,50}" required="required" maxlength="50"
									title="Please enter alphanumeric upto 50 characters only">
								<label>Answer <span class="star">*</span>
								</label>
							</div>
						</div>
						<div class="row securityQuestionDiv">
							<div class="input-field col s12 m6 l6">
								<p
									style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">
									Security Question 2 <span class="star">*</span>
								</p>
								<input type="hidden" class="id" id="id1" /> <select
									name="questionId" id="questionId1"
									class="browser-default questionId" required>
									<option value="" disabled selected>Security Question 2</option>
								</select>
							</div>

							<div class="input-field col s12 m6 l6">
								<input type="text" placeholder="" name="answer"
									class="form-control boxBorder boxHeight answer" id="answer1"
									pattern="[A-Za-z0-9\s]{0,50}" maxlength="50"
									title="Please enter alphanumeric upto 50 characters only"
									required="required"> <label>Answer <span
									class="star">*</span>
								</label>
							</div>
						</div>

						<div class="row securityQuestionDiv">
							<div class="input-field col s12 m6 l6">

								<p
									style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">
									Security Question 3<span class="star">*</span>
								</p>
								<input type="hidden" class="id" id="id2" /> <select
									name="questionId" id="questionId2"
									class="browser-default questionId" required>
									<option value="" disabled selected>Security Question 3</option>
								
								</select>
							</div>

							<div class="input-field col s12 m6 l6">
								<input type="text" name="answer" placeholder=""
									class="form-control boxBorder boxHeight answer" id="answer2"
									title="Please enter alphanumeric upto 50 characters only"
									maxlength="50" pattern="[A-Za-z0-9\s]{0,50}" required="required">
								<label>Answer <span class="star">*</span>
								</label>
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
									<input type="text"  autocomplete="off" name="captcha"
										class="form-control boxBorder boxHeight" id="captcha"
										required="required"> <label for="address">Enter
										your captcha <span class="star">*</span>
									</label>
								</div>

							</span>
						</div>
						   <p>
   <label style="color: black!important;padding-left: 16px;">
        <input name="disclamer" type="checkbox" required="required" style="margin-left: 12px;">
        <span style="margin-top:-18px !important;"> <span class="star">*</span> I certify that all the above information provided by me is true to the best of my knowledge. I am aware that if any of the above information is found to be incorrect/incomplete , CEIR Admin may take disciplinary action as applicable.  There would be a checkbox. User must click checkbox during registration</span>
      </label>
    </p>
    
    <!-- <div class="input-field no-margin col s12 m9">
    <input type="checkbox" name="accept" id="regTOS" required class="validate">
    <label for="regTOS" data-error="You must accept the Terms of Service">I accept the Terms of Service</label>
</div> -->

							                         </div>
                         
						<div class="row">
							<span> Required Field are marked with <span class="star">*</span></span>
							<div class="input-field col s12 center">
								<%-- <a href="${context}/verifyOtp" class="btn" id="btnSave"> Submit</a> --%>
								<button class="btn" id="btnSave"  
									type="submit" style="margin-left: 10px;">submit</button>
								<a href="${context}/" class="btn" style="margin-left: 10px;">cancel</a>
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

	<div id="otpMsgModal" class="modal" style="width:40%;margin-left: 30%;margin-top: 10vh;">
		<!-- <button type="button"
			class=" modal-action modal-close waves-effect waves-green btn-flat right"
			data-dismiss="modal">&times;</button> -->
				<h6 class="modal-header">Verify OTP</h6>
		<div class="modal-content">
			<!-- <h4 class="header2 pb-2">User Info</h4> -->

				<p style="padding:10px;" class="center" id="otpMsg"></p>
			
					<a href="#otpVerification" class="btn modal-trigger"
                                style="width: 100%; margin-top: 20px; margin-bottom: 20px;">verify otp</a>
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
				<h6>Do you want to cancel the request?</h6>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<div class="input-field col s12 center">
						<a href="userRegistration.html" class="btn" type="submit"
							name="add_user" id="add_user">yes</a> <a
							href="#activateDeactivate" class="modal-close modal-trigger btn"
							style="margin-left: 10px;">no</a>
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
    
    
    <div id="otpMessage" class="modal" style="display: block;">
        <button type="button" class="modal-action modal-close waves-effect waves-green btn-flat right"
            data-dismiss="modal">&times;</button>
        <h6 class="modal-header">Verify OTP</h6>
        <div class="modal-content">
                <h6 id="otpResponse"></h6>
            <div class="row">
                <div class="input-field col s12 center">
                    <a href="${context}/login" class="btn">ok</a>
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
                        <p class="center" id="resendOtp" style="display: none;"></p>
                        <input type="hidden" id="userid"  name="userid" value="${userId}">
                        <div class="row">          
                            <div class="input-field col s12 m12">
                                <input type="text" name="emailOtp" maxlength="6"
                               
										title="Please enter number characters only"
                                  required="required" id="emailOtp" placeholder="Enter OTP of Email"/>
                            </div> 
                            <div class="input-field col s12 m12">
                                <input type="text" name="phoneOtp" maxlength="6" 
                                
										title="Please enter number characters only" 
                                required="required" id="phoneOtp" placeholder="Enter OTP of Phone"/>
                            </div>
                        </div>
                        <a href="#" onclick="resendOtp(); document.getElementById('resendOtp').style.display ='block';" class="right">Resend OTP</a>
                        <button type="submit" id="otpVerifyBtn"  class="btn" style="width: 100%; margin-top: 20px; margin-bottom: 20px;">Done</button>
                    </form>
        </div>
    </div>
    
	
	<script> 
        $(document).ready(function () {
        	$('.modal-trigger').leanModal({
        		dismissible: false
        	});
        	
        	asTypeData();       	
            questionDataByCategory();
            usertypeData2("${usertypeId}");
        }); 
        populateCountries(
                "country",    "state",
            );
        
       $("#country").val("Cambodia");
       
       populateStates( "country",
               "state" );
       
       
       function validatePassword(){
           if(password.value != confirm_password.value) {
             confirm_password.setCustomValidity("Passwords Don't Match");
           } else {
             confirm_password.setCustomValidity('');
           }
         }

     password.onchange = validatePassword;
     confirm_password.onkeyup = validatePassword;

      
        function myFunction() {
            var x = document.getElementById("type").value;
            if (x == '0') {
                document.getElementById("uploadFile").style.display = "block";
                document.getElementById("passportNumberDiv").style.display = "block";
                document.getElementById("companyNames").style.display = "none";
                $("#passportNo").prop('required',true);
                $("#companyName").prop('required',false);
                $("#companyName").val("");
                $("#file").prop('required',true);
            } else {
                document.getElementById("uploadFile").style.display = "none";
                document.getElementById("passportNumberDiv").style.display = "none";
                document.getElementById("companyNames").style.display = "block";
                $("#companyName").prop('required',true);
                $("#passportNo").prop('required',false);
                $("#passportNo").val("");
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
        	}
        }
    </script>
</body>
</html>