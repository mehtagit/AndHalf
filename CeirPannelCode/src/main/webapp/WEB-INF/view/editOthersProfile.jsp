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
	 if( currentTime< accessTime + timeout){
	/*  response.setHeader("Refresh", timeout + "; URL = ../login");
	 System.out.println("timeout========"+timeout); 
	if (session.getAttribute("usertype") != null) { */
%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
<title>Profile</title>
<link
	href="${context}/resources/js/plugins/data-tables/css/jquery.dataTables.min.css"
	type="text/css" rel="stylesheet" media="screen,projection">
<link rel="apple-touch-icon-precomposed"
	href="${context}/resources/images/favicon/apple-touch-icon-152x152.png">
<!-- For iPhone -->
<meta name="msapplication-TileColor" content="#00bcd4">
<meta name="msapplication-TileImage"
	content="images/favicon/mstile-144x144.png">
		
<!-- Favicons-->
<link rel="icon" href="${context}/resources/images/DMC-Logo.png" sizes="32x32">
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
	<link href="${context}/resources/project_css/leanOverlay.css" type="text/css"
	rel="stylesheet" media="screen,projection">
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
/* input[type="checkbox"]+span:not(.lever) {
    position: relative;
    padding-left: 35px;
    cursor: pointer;
    display: inline-block;
    height: 25px;
    line-height: 25px;
    font-size: 1rem;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
}    

input[type="checkbox"]+span:not(.lever):before, [type="checkbox"]:not(.filled-in)+span:not(.lever):after {
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    width: 18px;
    height: 18px;
    z-index: 0;
    border: 2px solid #5a5a5a;
    border-radius: 1px;
    margin-top: 3px;
    -webkit-transition: .2s;
    transition: .2s;

} */
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
font-size: 1.15rem;
line-height: 110%;
margin: 0rem 0 0.2rem 0;
/* margin-top: 1px !important; */
} 

.file-upload-heading {
	margin-left: 0;
}

select {
	height: 2.2rem !important;
}

label {
/* 	font-size: 0.8rem; */
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
/* border: solid 2px #444; */
}
p label span {
font-size: 1rem;
}
</style>
<script>
var contextpath = "${context}";
</script>
</head>

<body data-lang-param="${pageContext.response.locale}">

<%String userType=request.getParameter("type");
%>

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
	<script type="text/javascript" src="${context}/resources/ajax/Password.js"></script>
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
    <%//String name=request.getParameter("type");
    Integer usertypeId=(Integer)session.getAttribute("usertypeId");
    if(usertypeId==null){
    	usertypeId=0;
    }
    %>
	<!-- START CONTENT -->
		<section id="content" id="mainPage">
		<!--start container-->
		<div class="container">
			<div class="section">
				<form id="registrationForm"  onsubmit="return passwordPopup()" >
				
			
					<div class="card-panel">
						<%-- <a href="${context}/"
							style="float: right; margin: -10px; margin-right: -20px;"><i
							class="fa fa-times boton" aria-hidden="true"></i></a> --%>
						<div class="row">
							<h5><spring:message code="registration.editinformation" /></h5>
								 <span style="text-align: center;color: red;" id="errorMsg"></span>
							<hr> 
					<span style="color: red;">${msg}</span>		
							<input type="hidden" id="id" name="id">
							<div class="row">
								<div class="input-field col s12 m4 l4">
									<input placeholder="" type="text" name="firstName" id="firstName" required="required" pattern="[A-Za-z]{3,20}" maxlength="20"
										oninput="InvalidMsg(this,'input','<spring:message code="validation.20Character" />');" oninvalid="InvalidMsg(this,'input','<spring:message code="validation.20Character" />');" 
										required />
									<label for="firstName" class="center-align"><spring:message code="input.firstName" /> <span class="star">*</span></label>
								</div>

								<div class="input-field col s12 m4 l4">
									<input type="text" placeholder="" name="middleName" id="middleName" pattern="[A-Za-z]{3,20}" maxlength="20"
										title="" />
									<label for="middleName"><spring:message code="input.middleName" /></label>
								</div>


								<div class="input-field col s12 m4 l4">
									<input placeholder="" type="text" name="lastName" id="lastName" pattern="[A-Za-z]{3,20}" maxlength="20"
										oninput="InvalidMsg(this,'input','<spring:message code="validation.20Character" />');" oninvalid="InvalidMsg(this,'input','<spring:message code="validation.20Character" />');"
									required />
									<label for="lastName"> <spring:message code="input.lastName" /> <span class="star">*</span>
									</label>
								</div>
	<%if(usertypeId==9){ %>
								 
									<div class="input-field col s12 m6 l6">
									<input disabled="disabled" placeholder="" type="text" name="operatorTypeName" id="operatorTypeName"/>
									<label for="operatorTypeName"><spring:message code="input.OperatorType" /> <span class="star">*</span></label>
								</div>
<% }%>
							</div>
                                <%if(usertypeId==12){%>
								<div class="input-field col s12 m6 l6" id="companyNames" style="margin-top: 22px;">
									<input disabled="disabled" placeholder="" type="text" name="companyName" id="companyName"
									 
									/>
									<label for="companyName"><spring:message code="registration.companyName" /> <span class="star">*</span></label>
								</div>
								<%}%>
											<div class="row">
								<div class="input-field col s12 m12 l12">
									<input placeholder="" type="text" maxlength="200" pattern="[A-Za-z0-9._%+-$@,/]+\.+{1,200}" name="propertyLocation"
										id="propertyLocation" oninput="InvalidMsg(this,'input','<spring:message code="validation.200character" />');" oninvalid="InvalidMsg(this,'input','<spring:message code="validation.200character" />');"
									 required /> 
										<label for="propertyLocation"><spring:message code="input.address" /> <span class="star">*</span></label>
								</div>

								<div class="input-field col s12 m6 l6">
									<input placeholder="" type="text" name="street" maxlength="20" id="street" pattern="[A-Za-z0-9._%+-$@,/]+\.+{1,20}"
									oninput="InvalidMsg(this,'input','<spring:message code="validation.address20characters" />');" oninvalid="InvalidMsg(this,'input','<spring:message code="validation.address20characters" />');"
										 required />
									<label for="street"><spring:message code="input.streetNumber" /> <span class="star">*</span>
									</label>
								</div>
									<div class="input-field col s12 m6 l6">
									<input placeholder="" type="text" name="village" maxlength="30" id="village" pattern="[A-Za-z0-9._%+-$@,/]+\.+{3,30}"
									oninput="InvalidMsg(this,'input','<spring:message code="validation.address30characters" />');" oninvalid="InvalidMsg(this,'input','<spring:message code="validation.address30characters" />');"
									required />
									<label for="village"> <spring:message code="input.village" /> <span class="star">*</span></label>
								</div>
								<div class="input-field col s12 m6 l6">
									<input placeholder="" type="text" name="locality" maxlength="30" id="locality" pattern="[A-Za-z0-9._%+-$@,/]+\.+{3,30}"
										oninput="InvalidMsg(this,'input','<spring:message code="validation.address30characters" />');" oninvalid="InvalidMsg(this,'input','<spring:message code="validation.address30characters" />');"
										required />
									<label for="locality"><spring:message code="input.locality" /> <span class="star">*</span></label>
								</div>

								<div class="input-field col s12 m6 l6">
									<input placeholder="" type="text" name="district" maxlength="30" id="district" pattern="[A-Za-z0-9._%+-$@,/]+\.+{3,30}"
									oninput="InvalidMsg(this,'input','<spring:message code="validation.address20characters" />');" oninvalid="InvalidMsg(this,'input','<spring:message code="validation.address20characters" />');"
										required />
									<label for="district"><spring:message code="input.district" /> <span class="star">*</span></label>
								</div>
								<div class="input-field col s12 m6 l6">
									<input placeholder="" type="text" name="commune" maxlength="30" id="commune" pattern="[A-Za-z0-9._%+-$@,/]+\.+{0,30}"
									oninput="InvalidMsg(this,'input','<spring:message code="validation.address30characters" />');" oninvalid="InvalidMsg(this,'input','<spring:message code="validation.address30characters" />');"
										 required />
									<label for="commune"><spring:message code="input.commune" /> <span class="star">*</span></label>
								</div>

								<div class="input-field col s12 m6 l6">
									<input placeholder="" required="required" type="text" name="postalCode" maxlength="6" id="postalCode" pattern="[0-9\s]{6}"
									oninput="InvalidMsg(this,'input','<spring:message code="validation.postalcode" />');" oninvalid="InvalidMsg(this,'input','<spring:message code="validation.postalcode" />');" title="">
									<label for="postalCode"><spring:message code="input.postalCode" /><span class="star">*</span></label>
								</div>
								
								
								
								<div class="col s12 m6 l6">
									<label><spring:message code="table.country" /> <span class="star">*</span></label>
									<select id="country" class="browser-default" class="mySelect" 
									oninput="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');" oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');" style="padding-left: 0;" required></select>
								</div>

								<div class="col s12 m6 l6">
									<label><spring:message code="input.province" /> <span class="star">*</span></label>
									<select id="state" class="browser-default" class="mySelect" 
									oninput="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');" oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');" style="padding-left: 0;" required></select>
								</div>
								
								<%if(usertypeId==7){ %>
								
									<div class="input-field col s12 m6 l6">
									<input disabled="disabled" placeholder="" type="text" name="arrivalPort" id="arrivalPort"/>
									<label for="passportNo"><spring:message code="input.arrivalport" /> <span class="star">*</span></label>
								</div>
								
									
									<div class="input-field col s12 m6 l6">
									<input disabled="disabled" placeholder="" type="text" name="portAddress" id="portAddress"/>
									<label for="passportNo"><spring:message code="registration.portAddress" /> <span class="star">*</span></label>
								</div>						
								<%} %>
								
								<%-- <div class="input-field col s12 m6 l6">
									<input type="text" maxlength="200" pattern="[A-Za-z0-9\s]{0,200}" name="arrivalPortLocation" id="arrivalPortLocation"
									oninput="setCustomValidity('')" oninvalid="this.setCustomValidity('<spring:message code="validation.requiredMsg" />')" 
										title="<spring:message code="validation.200character" />" required /> 
									<label for="arrivalPortLocation" class="">Port Address <span class="star">*</span></label>
								</div> --%>
								
							</div>

							<div class="row">
								<div class="input-field col s12 m6 l6">
									<input disabled="disabled" placeholder="" type="text" name="passportNo" id="passportNo"/>
									<label for="passportNo"><spring:message code="registration.nationalid" /> <span class="star">*</span></label>
								</div>
								
								
								<div class="input-field col s12 m6 l6">
									    <input placeholder="" type="text" name="uploadNationalId" id="NationalIdImage"   disabled="disabled">
                                            <input type="hidden"  id="nidFilePath">
                           <span> <a  href="#"  onclick="previewFile2(document.getElementById('nidFilePath').value,document.getElementById('NationalIdImage').value)">  <spring:message code="registration.preview" /></a> </span>                     
									<label for="passportNo"><spring:message code="registration.UploadNationalityInformation" /> <span class="star">*</span></label> 
								</div>
								
								
								<%-- <div class="col s12 m6 l6">
										<h6 class="file-upload-heading"><spring:message code="registration.UploadNationalityInformation" /> <span class="star">*</span></h6>
									<div class="input-field col s12 m6 l6">
                                            <input type="text" name="uploadNationalId" id="file"  maxlength="30" disabled="">
                                            <input type="hidden"  id="filePath">
                                            
                                            <label for="uploadNationalId" class="active"><spring:message code="registration.uploadednationalid" /></label>
                                           <span> <a  href="#" onclick="previewFile2(document.getElementById('filePath').value,document.getElementById('file').value)">  <spring:message code="registration.preview" /></a> </span>   
                                        </div>
									</div> --%>
									

								<%-- <div class="file-field col s12 m6 l6">
								<h6 class="file-upload-heading"><spring:message code="registration.uploadphoto" /> <span class="star">*</span></h6>
									<div class="btn">
										<span><spring:message code="registration.uploadphoto" /></span> <input id="photo" type="file" placeholder=""
										oninput="InvalidMsg(this,'fileType','<spring:message code="validation.selectImgMsg" />');" oninvalid="InvalidMsg(this,'fileType','<spring:message code="validation.selectImgMsg" />');"
								required/>
									</div>
									<div class="file-path-wrapper">
										<input class="file-path validate" type="text" placeholder=""  />
									</div>
								</div> --%>
								<div class="input-field col s12 m6 l6">
									    <input placeholder="" type="text" name="uploadNationalId" id="photo"  disabled="">
                                            <input type="hidden"  id="photoFilePath">
                           <span> <a  href="#"  onclick="previewFile2(document.getElementById('photoFilePath').value,document.getElementById('photo').value)">  <spring:message code="registration.preview" /></a> </span>                     
									<label for="passportNo"><spring:message code="registration.uploadphoto" /> <span class="star">*</span></label> 
								</div>

								<div class="input-field col s12 m6 l6" style="margin-top: 24px;">
									<input disabled="disabled" placeholder="" type="text" name="employeeID" id="employeeId" 
										  /> 
									<label for="employeeId"><spring:message code="registration.employeeid" /> <span class="star">*</span></label>
								</div>

								<%-- <div class="file-field col s12 m6 l6">
								<h6 class="file-upload-heading"><spring:message code="registration.uploadidcard" /> <span class="star">*</span></h6>
									<div class="btn">
										<span>*<spring:message code="registration.uploadidcard" /></span> <input id="idCard" type="file" placeholder=""
										oninput="InvalidMsg(this,'fileType','<spring:message code="validation.selectImgMsg" />');" oninvalid="InvalidMsg(this,'fileType','<spring:message code="validation.selectImgMsg" />');"
										title="" required />
									</div>
									<div class="file-path-wrapper">
										<input  class="file-path validate" type="text" placeholder="" />
									</div>
								</div> --%>
								<div class="input-field col s12 m6 l6">
									    <input placeholder="" type="text" name="idCard" id="idCard"  disabled="">
                                            <input type="hidden"  id="idCardFilePath">
                           <span> <a  href="#"  onclick="previewFile2(document.getElementById('idCardFilePath').value,document.getElementById('idCard').value)">  <spring:message code="registration.preview" /></a> </span>                     
								<%if(usertypeId==9){ %>
									<label for="passportNo"><spring:message code="operator.uploadidcard" /> <span class="star">*</span></label> 
							<%} else{%>
									<label for="passportNo"><spring:message code="registration.uploadidcard" /> <span class="star">*</span></label> 
<%} %>
								</div>
								
	<div class="input-field col s12 m6 l6">
									<input disabled="disabled" placeholder="" type="text" name="natureOfEmployment" id="natureOfEmployment" 
										title=""  /> 
									<label for="natureOfEmployment"><spring:message code="registration.natureofemployment" /> <span class="star">*</span></label>
								</div>


<%-- 								<div class="col s12 m6 l6" style="margin-top: 3px;">
									<input disabled="disabled" placeholder="" type="text" name="natureOfEmployment" id="natureOfEmployment" 
										title=""  /> 
									<label for="natureOfEmployment"><spring:message code="registration.natureofemployment" /> <span class="star">*</span></label>
 --%>

									<%-- <label> <spring:message code="registration.natureofemployment" /> <span class="star">*</span></label>

									<select disabled="disabled" id="natureOfEmployment" class="browser-default" 
									oninput="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');" oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');" >
										<option value="" disabled selected><spring:message code="registration.natureofemployment" /></option>
										<option value="Permanent"><spring:message code="registration.permanent" /></option>
										<option value="Temporary"><spring:message code="registration.temporary" /></option>
										<option value="Contract"><spring:message code="registration.contract" /></option>
									</select>
 --%>
 								</div>
							</div>

							<div class="row">
								<div class="input-field col s12 m6 l6">
									<input disabled="disabled" placeholder="" type="text" name="designation" id="designation" 
										title=""  /> 
									<label for="designation"><spring:message code="registration.designationandtitle" /> <span class="star">*</span></label>
								</div>

								<div class="input-field col s12 m6 l6">
									<input disabled="disabled" placeholder="" type="text" name="reportingAuthorityName" id="authorityName"  
										/> 
									<label for="authorityName"><spring:message code="registration.reportingauthorityname" /></label>
								</div>

								<div class="input-field col s12 m6 l6">
									<input disabled="disabled"  placeholder="" type="text" name="authorityEmail"   id="authorityEmail" >
									<label for="authorityEmail"> <spring:message code="registration.ReportingAuthorityEmailid" /><span class="star">*</span></label>
								</div>

								<div class="input-field col s12 m6 l6">
									<input disabled="disabled"  placeholder="" type="tel" name="authorityPhoneNo" id="authorityPhoneNo" >
									<label for="authorityPhoneNo"> <spring:message code="registration.reportingauthoritycontactnumber" /><span class="star">*</span></label>
								</div>

								<div class="input-field col s12 m6 l6">
									<input maxlength="280" placeholder="" type="text" name="email" required="required" id="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,63}$"
	                                 oninput="InvalidMsg(this,'email','<spring:message code="validation.email" />');" oninvalid="InvalidMsg(this,'email','<spring:message code="validation.email" />');" title=""
                                required /> 
									<label for="email"><spring:message code="input.email" /> <span class="star">*</span></label>
								</div>

								<div class="input-field col s12 m6 l6">
									<input placeholder="" type="tel" name="phoneNo" maxlength="15" id="phoneNo" pattern="[0-9]{7,15}"
									oninput="InvalidMsg(this,'input','<spring:message code="validation.requiredMsg" />');" oninvalid="InvalidMsg(this,'input','<spring:message code="validation.requiredMsg" />');"
									
										title="<spring:message code="validation.contact" />" required /> 
										<label for="phoneNo"><spring:message code="input.contactNum" /> <span class="star">*</span>
									</label>
								</div>
							</div>

<%if(usertypeId==12){%>
<div class="row">
									<div class="input-field col s12 m6 l6"  id="vatNumberField">
									<input type="hidden" id="vatStatus" value="1"/>
										<input id="vatNo" disabled="disabled" placeholder="" type="text"  name="vatNo" 
										>
										<label for="vatNo"><spring:message code="registration.vatnumber" /> <span class="star">*</span></label>
									</div>

									<div id="vatFileDiv">
										<%-- <div class="file-field col s12 m6">
											<p class="upload-file-label"><spring:message code="registration.vatfile" /> <span class="star">*</span></p>
											<div class="btn">
												<span><spring:message code="input.selectfile" /></span> <input required="required" name="file" type="file" id="vatFile" accept=".pdf"
												oninput="InvalidMsg(this,'fileType','<spring:message code="validation.file" />');" oninvalid="InvalidMsg(this,'fileType','<spring:message code="validation.file" />');"   />
											</div>
											<div class="file-path-wrapper">
												<input name="vatFile" class="file-path validate responsive-file-div" type="text"  >
											</div>
										</div> --%>
										
										<div class="input-field col s12 m6 l6">
									    <input placeholder="" type="text" name="vatFile" id="vatFile" disabled="disabled">
                                            <input type="hidden"  id="vatFilePath">
                           <span> <a  href="#"  onclick="previewFile2(document.getElementById('vatFilePath').value,document.getElementById('vatFile').value)">  <spring:message code="registration.preview" /></a> </span>                     
									<label for="passportNo"><spring:message code="registration.vatfile" /> <span class="star">*</span></label> 
								</div>
										<br> <br>
									</div>
								</div>
								<%} %>
								
							


													<div class="row securityQuestionDiv">
								<div class="input-field col s12 m6 l6">
										<p
										style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">
										<spring:message code="registration.securityQuestion1" /> <span class="star">*</span>
									</p>
									<input type="hidden"  class="id" id="id0">
									<select class="browser-default questionId" id="questionId0" name="questionId"
									oninput="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');" 
									oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
									 required>
									<option value="" disabled selected>
											<spring:message code="registration.securityQuestion1" /></option> 
									<c:forEach items="${questions}" var="question"> 
									<c:if test="${question.category=='1'}">
									<option value="${question.id}">${question.question}</option>
									</c:if>
									</c:forEach> 
									</select>
								</div>

								<div class="input-field col s12 m6 l6">
									<input placeholder=""  type="text" placeholder="" name="answer" 
										class="form-control boxBorder boxHeight answer" id="answer0"
										pattern="[A-Za-z0-9\s]{3,50}"
										maxlength="50" oninput="InvalidMsg(this,'input','<spring:message code="validation.50character" />');" 
										oninvalid="InvalidMsg(this,'input','<spring:message code="validation.50character" />');"
									required  > <label><spring:message code="registration.answer" />
										<span class="star">*</span>
									</label>
								</div>
							</div>
							<div class="row securityQuestionDiv">
								<div class="input-field col s12 m6 l6">
								<p
										style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">
										<spring:message code="registration.securityQuestion2" /> <span class="star">*</span>
									</p>
									<input type="hidden"  class="id"  id="id1"/>
									<select name="questionId" id="questionId1" class="browser-default questionId" 
									oninput="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');" 
									oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
									required>
									<option value="" disabled selected>
											<spring:message code="registration.securityQuestion2" /></option>
									<c:forEach items="${questions}" var="question"> 		
										<c:if test="${question.category=='2'}">
									<option value="${question.id}">${question.question}</option>
									</c:if>
									</c:forEach>
									</select>
								</div>

								<div class="input-field col s12 m6 l6">
									<input  placeholder="" type="text" placeholder="" name="answer"
										class="form-control boxBorder boxHeight answer" id="answer1"
										pattern="[A-Za-z0-9\s]{3,50}"
										maxlength="50"
										oninput="InvalidMsg(this,'input','<spring:message code="validation.50character" />');" 
										oninvalid="InvalidMsg(this,'input','<spring:message code="validation.50character" />');"
										 required> <label ><spring:message code="registration.answer" />
										<span class="star">*</span>
									</label>
								</div>
							</div>

							<div class="row securityQuestionDiv">
								<div class="input-field col s12 m6 l6">
								
								<p
										style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">
										<spring:message code="registration.securityQuestion2" /> <span class="star">*</span>
									</p>
								     <input type="hidden"  class="id" id="id2" />
									<select name="questionId" id="questionId2" class="browser-default questionId" 
									oninput="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');" 
									oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');" required>
									<option value="" disabled selected>
											<spring:message code="registration.securityQuestion2" /></option>
									<c:forEach items="${questions}" var="question"> 		
										<c:if test="${question.category=='3'}">
									<option value="${question.id}">${question.question}</option>
									</c:if>     	
									</c:forEach>
											
									</select>
								</div>

								<div class="input-field col s12 m6 l6">
									<input placeholder="" type="text" name="answer" placeholder=""
										class="form-control boxBorder boxHeight answer" id="answer2"
										title= "<spring:message code="validation.50character" />"
										maxlength="50" 
										pattern="[A-Za-z0-9\s]{3,50}" 
										oninput="InvalidMsg(this,'input','<spring:message code="validation.50character" />');" 
										oninvalid="InvalidMsg(this,'input','<spring:message code="validation.50character" />');"
										required > <label ><spring:message code="registration.answer" />
										<span class="star">*</span>
									</label>
								</div>
							</div>

							
						
   											<div class="row">
							<span> <spring:message code="input.requiredfields" /> <span class="star">*</span></span>
							<div class="input-field col s12 center">
								<%-- <a href="${context}/verifyOtp" class="btn" id="btnSave"> Submit</a> --%>
								<button class="btn"  id="btnSave" type="submit" 
									style="margin-left: 10px;"><spring:message code="button.update" /></button>
								<%String userLatestLang=(String)session.getAttribute("updatedLanguage"); 
								%>
								<%if(userLatestLang!=null){%>
								
								<a target="_parent" href="./?lang=<%=userLatestLang%>" class="btn" style="margin-left: 10px;"><spring:message code="modal.cancel" /></a>
                                <%}else{ %>
								<a target="_parent" href="./?lang=<%=session.getAttribute("language")%>" class="btn" style="margin-left: 10px;"><spring:message code="modal.cancel" /></a>
                                <%} %>
							</div>
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


	<div id="submitForm" class="modal">
		<div class="modal-content">
			<!-- <h4 class="header2 pb-2">User Info</h4> -->

			<div class="row">
				<h6><spring:message code="registration.verifyotp" /></h6>
				<p>
					<spring:message code="registration.textmsg" /></p>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<a href="otpVerification.html" class="btn"><spring:message code="registration.verifyotp" /></a>
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
	
	
	  <!-- //////////////////////////////////////////////////////////////////////////// -->

    <!-- START MAIN -->
    <div id="">
        <!-- START WRAPPER -->
        <div class="wrapper">
            <!-- START CONTENT -->
            <section id="content">
                <!--start container-->
                <div class="container">
                    <div class="section">
                        <div id="otpMsgModal" class="modal" style="width: 40%; margin-left: 30%; margin-top: 10vh;">
                            <h6 class="modal-header"><spring:message code="registration.verifyotp" /></h6>
                            <p class="center" id="otpMsg"><!-- The text and and an e-mail with OTP details has been sent to your registered Phone Number and E-Mail ID --></p>
           			<a href="javascript:void(0)" onclick="openOtpPopup()" class="btn"
				style="width: 100%; margin-top: 20px; margin-bottom: 20px;"><spring:message
					code="registration.verifyotp" /></a>
                        </div>
                    </div>
                </div>
                <!--end container-->
            </section>
            <!-- END CONTENT -->
        </div>
    </div>
    <!-- END MAIN -->
<div id="otpMessage" class="modal">
       <h6 class="modal-header"><spring:message code="registration.updateinformation" /></h6>
        <div class="modal-content">
            <!-- <h4 class="header2 pb-2">User Info</h4> -->

            <div class="row">  
                <h6 id="otpResponse"></h6>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
								<%if(userLatestLang!=null){%>
								
								<a target="_parent" href="./?lang=<%=userLatestLang%>" class="btn" style="margin-left: 10px;"><spring:message code="modal.ok" /></a>
                                <%}else{ %>
								<a target="_parent" href="./?lang=<%=session.getAttribute("language")%>" class="btn" style="margin-left: 10px;"><spring:message code="modal.ok" /></a>
                                <%} %>                </div>
                </div>
            </div>
        </div>
    </div>
    
    <div id="profileResponse" class="modal" style="width: 40%">
                            <h6 class="modal-header"><spring:message code="registration.updateinformation" /></h6>
           
        <div class="modal-content">
            <!-- <h4 class="header2 pb-2">User Info</h4> -->

                    <div class="row">
                        <h6 id="updateInfoMsg"></h6>
                    </div>
            <div class="row">
            
                <div class="input-field col s12 center">
               
				<%				if(userLatestLang!=null){%>
								
								<a target="_parent" href="./?lang=<%=userLatestLang%>" class="btn" style="margin-left: 10px;"><spring:message code="modal.ok" /></a>
                                <%}else{ %>
								<a target="_parent" href="./?lang=<%=session.getAttribute("language")%>" class="btn" style="margin-left: 10px;"><spring:message code="modal.ok" /></a>
                                <%} %>                </div>
            </div>
        </div>
    </div>
    
    

    <!-- modal start -->

       <div id="otpVerification" class="modal" style="width: 40%;">
               <h6 class="modal-header"><spring:message code="registration.otp" /></h6>
        <div class="modal-content">
                 <form id="verifyOtpForm" onsubmit="return verifyOtp2()">
             <p class="center" id="verifyOtpResp"></p>
                                       <input type="hidden" id="userid"  name="userid" value="${userId}">
                        <div class="row">          
                            <div class="input-field col s12 m12">
                                <input type="text" placeholder="<spring:message code="placeholder.emailotp" />" name="emailOtp" maxlength="6"
                                 required="required" id="emailOtp" pattern="[0-9]{0,6}" 
                                  oninput="setCustomValidity('')" oninvalid="this.setCustomValidity('<spring:message code="validation.requiredMsg" />')"
										title= "<spring:message code="validation.6digit" />" placeholder="" required />
                            </div> 
                            <div class="input-field col s12 m12">
                                <input placeholder="<spring:message code="placeholder.optphone" />" type="text" name="phoneOtp" maxlength="6" 
										pattern="[0-9]{0,6}"
										 oninput="setCustomValidity('')" oninvalid="this.setCustomValidity('<spring:message code="validation.requiredMsg" />')"
										title= "<spring:message code="validation.6digit" />" 
                                   id="phoneOtp" placeholder="" required >
                            </div>
                        </div>
                        <a href="javascript:void(0)" onclick="resendOtp2(); document.getElementById('resendOtp').style.display ='block';" class="right"><spring:message code="registration.resendotp" /></a>
                        <button type="submit" id="otpVerifyBtn"  class="btn" style="width: 100%; margin-top: 20px; margin-bottom: 20px;"><spring:message code="registration.done" /></button>
                    </form>
        </div>
    </div>
    
    
         <div id="passwordModal"  class="modal" style="width: 40%; z-index: 1003;  opacity: 1; transform: scaleX(1); top: 10%;">
             <h6 class="modal-header"><spring:message code="registration.pleaseenteryourpassword" /></h6>
            <div class="modal-content" >
<form  onsubmit="return updateProfile()">
                    <div class="row">
                        
                           <div class="input-field col s12">

                                <label for="confirmPassword" style="color: #000; font-size: 12px;"><spring:message code="registration.password" /></label>
                                <input required="required"  type="password" class="password" id="confirmPassword" maxlength="10">
                                	<div class="input-field-addon">
							<i class="fa fa-eye-slash teal-text toggle-password"
								aria-hidden="true"></i>
						</div>
                            </div>
                        
                    </div>
                    <div class="row">
                        <div class="input-field col s12 center">
                            <button type="submit" id="passwordBtn" class="btn"><spring:message code="button.submit" /></button>
                            <button type="button" class="btn modal-close" style="margin-left: 10px;"><spring:message code="button.cancel" /></button>
                        </div>
                    </div>
                    </form>
                </div>
                            </div>

	<!-- Modal 2 start   -->
<div id="cancelActivateDeactivate" class="modal">
		<div class="modal-content">

			<div class="row">
				<h6><spring:message code="registration.cancelRequest" /></h6>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<div class="input-field col s12 center">
						<!-- <a  href="userRegistration.html" class="btn" type="submit"
							name="add_user" id="add_user">yes</a> -->
		<button type="button" onclick="" class="btn" ><spring:message code="button.submit" /></button>				
							 <a
							href="#activateDeactivate" class="modal-close modal-trigger btn"
							style="margin-left: 10px;"><spring:message code="modal.no" /></a>
					</div>
				</div>
			</div>
		</div>
	</div>


<!-- 	Error Modal -->
	<div class="modal" id="modal212" role="dialog">
		<div class="modal-dialog">
			<div class="row" id="modalMessageBody"
					style="text-align: center; color: black;"></div>
			
		</div>
	</div>
	<!-- Modal End -->
	
	<!-- ================================================
    Scripts
    ================================================ -->
    
    
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
	
	
		<script type="text/javascript" src="${context}/resources/project_js/globalVariables.js?version=<%= (int) (Math.random() * 10) %>"></script>
<script type="text/javascript"
		src="${context}/resources/project_js/backbutton.js?version=<%= (int) (Math.random() * 10) %>"></script>
	<script type="text/javascript"
		src="${context}/resources/project_js/dragableModal.js?version=<%= (int) (Math.random() * 10) %>"></script>	
			<script type="text/javascript"
		src="${context}/resources/project_js/enterKey.js?version=<%= (int) (Math.random() * 10) %>"></script>
	 	<script type="text/javascript"
		src="${context}/resources/project_js/validationMsg.js?version=<%= (int) (Math.random() * 10) %>"></script>
					    <script type="text/javascript"
		src="${context}/resources/project_js/messageWindow.js?version=<%= (int) (Math.random() * 10) %>"></script>  
<script> 
    var lang=window.parent.$('#langlist').val() == 'km' ? 'km' : 'en';
	window.parent.$('#langlist').on('change', function() {
		var lang=window.parent.$('#langlist').val() == 'km' ? 'km' : 'en';
	window.location.assign("./editProfile?lang="+lang);

	}); 
	
        $(document).ready(function () {
        	questionDataByCategory();
        //	 $("select[required]").css({position: "absolute", display: "inline", height: 0, padding: 0, width: 0});
       populateCountries("country", "state");
     
       editOtherProfile();
   	
      
   $('.modal').modal();
            /* $('.dropdown-trigger').dropdown();
            $('select').formSelect(); */
        }); 
       
      
       	
    </script>


	<script>
        populateCountries(
            "country",
            "state"
        );
       
        populateStates(
            "country",
            "state"
        );
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