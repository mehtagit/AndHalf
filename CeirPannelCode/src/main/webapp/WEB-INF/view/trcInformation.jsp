<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<title>Grievance</title>

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
	href="${context}/resources/css/grievance.css">



</head>

<body data-roleType="${usertype}" data-userTypeID="${usertypeId}" 
data-userID="${userid}" data-selected-roleType="${selectedUserTypeId}" 
data-stolenselected-roleType="${stolenselectedUserTypeId}"
data-session-id="${not empty param.id ? param.id : 'null'}"
data-session-roles="${not empty param.roles ? param.roles : 'null'}"
data-session-type="${not empty param.type ? param.type : 'null'}"
>


	<!-- START CONTENT -->
	<!-- START CONTENT -->
		<section id="content" style="margin:0 0.5rem">
                <!--start container-->
                <div class="container">
                    <div class="section">
                        <form action="${context}/trcInformation" method="post" >
							<div class="card-panel">
                                <div class="row">
                                    <div class="col s12 m12">
                                        <h5><spring:message code="input.ViewTRC" /></h5>
                                        <hr>
                                        <div class="row" style="margin-top: 10px;">
                                            <div class="input-field col s12 m4 l4">
                                                <input type="text" name="firstName" id="firstName" maxlength="20" value="${registration.firstName}" disabled="" placeholder="">
                                                <label for="firstName" class="center-align active"><spring:message code="input.firstName" /> </label>
                                            </div>

                                            <div class="input-field col s12 m4 l4">
                                                <input type="text" name="middleName" id="middleName" maxlength="20" value="${registration.middleName}"  disabled="" placeholder="">	
                                                <label for="middleName" class="active"><spring:message code="input.middleName" /></label>
                                            </div>

                                            <div class="input-field col s12 m4 l4">
                                                <input type="text" name="lastName" id="lastName" maxlength="20" value="${registration.lastName}" disabled="" placeholder="">
                                                <label for="lastName" class="active"><spring:message code="input.lastName" /></label>
                                            </div>

                                        </div>

                                        <div class="row">
                                            <div class="input-field col s12 m12 l12">
                                                <input type="text" name="address" id="address" value="${registration.propertyLocation}" disabled="" placeholder="">
                                                <label for="address" class="active"><spring:message code="input.address" /> </label>
                                            </div>

                                            <div class="input-field col s12 m6 l6">
                                                <input type="text" name="streetNumber" id="streetNumber" maxlength="30" value="${registration.street}" disabled="" placeholder="">
                                                <label for="streetNumber" class="active"><spring:message code="input.streetNumber" /> </label>
                                            </div>
                                            
                                            <div class="input-field col s12 m6 l6">
												<input type="text" name="village" id="village" maxlength="20" value="${registration.village}" disabled="" placeholder="">
												<label for="village"><spring:message code="input.village" /></label>
											</div>
											

                                            <div class="input-field col s12 m6 l6">
                                                <input type="text" name="locality" id="locality" maxlength="20" value="${registration.locality}" disabled="" placeholder="">
                                                <label for="locality" class="active"><spring:message code="input.locality" /> </label>
                                            </div>
											
											<div class="input-field col s12 m6 l6">
												<input type="text" name="district" id="district" maxlength="20" value="${registration.district	}" disabled="" placeholder="">
												<label for="district"><spring:message code="input.district" /></label>
											</div>
											
											<div class="input-field col s12 m6 l6">
                                      			 <input type="text" name="commune" id="commune" maxlength="20" value="${registration.commune}" disabled="" placeholder="">
                                      			 <label for="commune" class="active"><spring:message code="input.commune" /></label>
                                  			</div>
                                  			
                                  			<div class="input-field col s12 m6 l6">
									 			 <input type="text" name="pin" id="pin" maxlength="20" value="${registration.postalCode}" disabled="" placeholder="">
									  			<label for="pin"><spring:message code="registration.postalcode" /> </label>
											</div> 	
											
                                            <div class="input-field col s12 m6 l6">
                                                <input type="text" name="country" id="country" maxlength="20" value="${registration.country}" disabled="" placeholder="">
                                                <label for="country" class="active"><spring:message code="input.Country" /> </label>
                                            </div>

                                            <div class="input-field col s12 m6 l6">
                                                <input type="text" name="Province" id="Province" maxlength="20" value="${registration.province}" disabled="" placeholder="">
                                                <label for="Province" class="active"><spring:message code="input.province" /></label>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="input-field col s12 m6 l6">
                                                <input type="text" name="NationalID" id="NationalID" maxlength="30" value="${registration.passportNo}" disabled="" placeholder="">
                                                <label for="NationalID" class="active"><spring:message code="registration.nationalid" /> </label>
                                            </div>

                                            <div class="input-field col s12 m6 l6">
                                                <input type="text" name="uploadNationalId" id="uploadNationalId" maxlength="30" value="${registration.nidFilename}" disabled="">
                                                <label for="uploadNationalId" class="active"><spring:message code="registration.uploadednationalid" /> </label>
                                                 <span><a href="#" onclick="previewFile('${registration.nidFilePath}','${registration.nidFilename}')">Preview </a></span>  
                                            </div>

                                            <div class="input-field col s12 m6 l6">
                                                <input type="text" name="uploadPhoto" id="uploadPhoto" maxlength="30" value="${registration.photoFilename}" disabled="">
                                                <label for="uploadPhoto" class="active"><spring:message code="registration.uploadedphoto" /> </label>
                                                <span> <a href="#" onclick="previewFile('${registration.photoFilePath}','${registration.photoFilename}')">Preview </a> </span> 
                                            </div>

                                            <div class="input-field col s12 m6 l6">
                                                <input type="text" name="employeeID" id="employeeID" maxlength="30" value="${registration.employeeId}" disabled="" placeholder="">
                                                <label for="employeeID" class="active"><spring:message code="registration.employeeid" /> </label>
                                            </div>

                                            <div class="input-field col s12 m6 l6">
                                                <input type="text" name="uploadIdCard" id="uploadIdCard" maxlength="30" value="${registration.idCardFilename}" disabled="">
                                                <label for="uploadIdCard" class="active"><spring:message code="registration.uploadedidcard" /> </label>
                                              <span>  <a href="#" onclick="previewFile('${registration.idCardFilePath}','${registration.idCardFilename}')">Preview </a>  </span>
                                            </div>

                                            <div class="input-field col s12 m6 l6">
                                                <input type="text" name="natureOfEmployment" id="natureOfEmployment" maxlength="30" value="${registration.natureOfEmployment}" disabled="" placeholder="">
                                                <label for="natureOfEmployment" class="active"><spring:message code="registration.natureofemployment" /></label>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="input-field col s12 m6 l6">
                                                <input type="text" name="designation" id="designation" maxlength="30" value="${registration.designation}" disabled="" placeholder="">
                                                <label for="designation" class="active"><spring:message code="registration.designationandtitle" /> </label>
                                            </div>

                                            <div class="input-field col s12 m6 l6">
                                                <input type="text" name="reportingAuthorityName" id="reportingAuthorityName" maxlength="30" value="${registration.authorityName}" disabled="" placeholder="">
                                                <label for="reportingAuthorityName" class="active"><spring:message code="registration.reportingauthorityname" /></label>
                                            </div>

                                            <div class="input-field col s12 m6 l6">
                                                <input type="text" name="reportingAuthorityEmail" id="reportingAuthorityEmail" maxlength="30" value="${registration.authorityEmail}" disabled="" placeholder="">
                                                <label for="reportingAuthorityEmail" class="active"><spring:message code="registration.ReportingAuthorityEmailid" /></label>
                                            </div>

                                            <div class="input-field col s12 m6 l6">
                                                <input type="text" name="reportingAuthorityContact" id="reportingAuthorityContact" maxlength="30" value="${registration.authorityPhoneNo}" disabled="" placeholder="">
                                                <label for="reportingAuthorityContact" class="active"><spring:message code="registration.reportingauthoritycontactnumber" /></label>
                                            </div>

                                            <div class="input-field col s12 m6 l6">
                                                <input type="text" name="email" id="email" maxlength="30" value="${registration.email}" disabled="" placeholder="">
                                                <label for="email" class="active"><spring:message code="input.email" /> </label>
                                            </div>

                                            <div class="input-field col s12 m6 l6">
                                                <input type="text" name="phone" id="phone" maxlength="10" value="${registration.phoneNo}" disabled="" placeholder="">
                                                <label for="phone" class="active"><spring:message code="input.contactNum" /> </label>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col s12 m12">
                                        <div class="input-field col s12 center">
                                            <!-- <a href="index.html" class="btn" id="btnSave"> Submit</a> -->
                                             <a class="btn modal-close" href="./registrationRequest"><spring:message code="modal.cancel" /></a>
                                        </div>
                                    </div>
                                </div>

                        
                    </div></form>
                </div>
                <!--end container-->
            </div></section>
            
           <!-- Preview Modal start   -->

	<div id="viewuplodedModel" class="modal">
		<div class="modal-content">
			<div class="row">
					<img src="" id="fileSource" width="400" height="400">
			</div>
		</div>
	</div>
	<!-- Modal End -->
     

	
	<!--materialize js-->
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
	<script type="text/javascript"
		src="${context}/resources/js/materialize.js"></script>


	<script type="text/javascript"
		src="${context}/resources/js/plugins/data-tables/js/jquery.dataTables.js"></script>
	<script type="text/javascript"
		src="${context}/resources/js/plugins/data-tables/js/jquery.dataTables.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.3/moment.min.js"></script>
	<script type="text/javascript"
		src="${context}/resources/js/jquery-datepicker2.js"></script>


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
	<script type="text/javascript"
		src="${context}/resources/project_js/AdminRegistrationRequest.js"></script>
		
</body>
</html>