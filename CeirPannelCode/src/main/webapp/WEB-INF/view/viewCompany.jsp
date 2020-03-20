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

<body data-roleType="${usertype}" data-userTypeID="${usertypeId}" data-userID="${userid}" data-selected-roleType="${selectedUserTypeId}" data-stolenselected-roleType="${stolenselectedUserTypeId}">


		<section id="content" style="margin:0 0.5rem">
                <!--start container-->
                <div class="container">
                    <div class="section">
                        <form action="" id="registrationForm">
                            <div class="card-panel">
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
																class="star">*</span></label> <select class="browser-default"
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
													<div class="btn">
														<span><spring:message code="input.selectfile" /></span> <input type="file"
														oninput="InvalidMsg(this,'fileType','<spring:message code="validation.NoChosen" />');"
											oninvalid="InvalidMsg(this,'fileType','<spring:message code="validation.NoChosen" />');"
														 onchange="isImageValid('csvUploadFile')"
														title= "<spring:message code="validation.NoChosen" />" required id="csvUploadFile" accept="*image">
													</div>
													<div class="file-path-wrapper">
														<input class="file-path validate responsive-file-div" id="csvUploadFileName"
															type="text">
													</div>
												</div>
											</div>
											<div class="col s12 m12">
												<div class="input-field col s12 m4 l4">
													<input type="text" name="firstName" id="firstName"
														maxlength="20" required="required"
														pattern="[A-Za-z]{0,20}"
														oninput="InvalidMsg(this,'input','<spring:message code="validation.20Character" />');"
											            oninvalid="InvalidMsg(this,'input','<spring:message code="validation.20Character" />');"
														 required>
													<label for="firstName" class="center-align"><spring:message code="input.firstName" /> <span class="star">*</span>
													</label>
												</div>

												<div class="input-field col s12 m4 l4">
													<input type="text" name="middleName" id="middleName"
														maxlength="20" pattern="[A-Za-z]{0,20}"
														oninput="InvalidMsg(this,'input','<spring:message code="validation.20Character" />');"
											            oninvalid="InvalidMsg(this,'input','<spring:message code="validation.20Character" />');"
														>
													<label for="middleName"><spring:message code="input.middleName" /></label>
												</div>

												<div class="input-field col s12 m4 l4">
													<input type="text" name="lastName" id="lastName"
														 pattern="[A-Za-z]{0,20}"
														oninput="InvalidMsg(this,'input','<spring:message code="validation.20Character" />');"
											            oninvalid="InvalidMsg(this,'input','<spring:message code="validation.20Character" />');"
														title= "<spring:message code="validation.20Character" />" required
														maxlength="20"> <label for="lastName"><spring:message code="input.lastName" /> <span class="star">*</span>
													</label>
												</div>
											</div>
										</div>

										<div class="row">
											<div class="col s12 m12">
												<div class="input-field col s12 m12 l12">
													<input type="text" name="address"
														pattern="[^[a-zA-Z0-9\s,'-]*$]{0,200}"
														oninput="InvalidMsg(this,'input','<spring:message code="validation.200characters" />');"
											            oninvalid="InvalidMsg(this,'input','<spring:message code="validation.200characters" />');"
														 maxlength="200" required
														class="form-control boxBorder boxHeight" id="address">
													<label for="address"><spring:message code="input.address" /> <span
														class="star">*</span></label>
												</div>

												<div class="input-field col s12 m6 l6">
													<input type="text" pattern="[^[a-zA-Z0-9\s,'-]*$]{0,20}"
														oninput="InvalidMsg(this,'input');" oninvalid="InvalidMsg(this,'input');"
														title= "<spring:message code="validation.20Character" />"
														name="streetNumber"
														class="form-control boxBorder boxHeight" id="streetNumber"
														maxlength="20" required/> <label
														for="streetNumber"> <spring:message code="input.streetNumber" /> <span
														class="star">*</span>
													</label>
												</div>
													<div class="input-field col s12 m6 l6">
													<input type="text" pattern="[^[a-zA-Z0-9\s,'-]*$]{0,30}"
														oninput="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');"
											            oninvalid="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');"
														name="streetNumber"
														class="form-control boxBorder boxHeight" id="village"
														maxlength="30" required/> <label
														for="village"> <spring:message code="input.village" /> <span
														class="star">*</span>
													</label>
												</div>
												

												<div class="input-field col s12 m6 l6">
													<input type="text" name="locality"
														pattern="[^[a-zA-Z0-9\s,'-]*$]{0,30}"
														oninput="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');"
											            oninvalid="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');"
														
														class="form-control boxBorder boxHeight" id="locality"
														maxlength="30" required/> <label
														for="locality"> <spring:message code="input.locality" /> <span class="star">*</span>
													</label>
												</div>

													<div class="input-field col s12 m6 l6">
													<input type="text" pattern="[^[a-zA-Z0-9\s,'-]*$]{0,30}"
														oninput="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');"
											            oninvalid="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');"
														 name="streetNumber"
														class="form-control boxBorder boxHeight" id="district"
														maxlength="30" required> <label
														for="district"> <spring:message code="input.district" /> <span
														class="star">*</span>
													</label>
												</div>
												<div class="input-field col s12 m6 l6">
													<input type="text" pattern="[^[a-zA-Z0-9\s,'-]*$]{0,30}"
														oninput="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');"
											            oninvalid="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');"
														 name="streetNumber"
														class="form-control boxBorder boxHeight" id="commune"
														maxlength="30" required/> <label
														for="commune"> <spring:message code="input.commune" /> <span
														class="star">*</span>
													</label>
												</div>
												<div class="input-field col s12 m6 l6">
													
													<input type="text" pattern="[0-9]{1,6}"
														oninput="InvalidMsg(this,'input','<spring:message code="validation.postalcode" />');"
											            oninvalid="InvalidMsg(this,'input','<spring:message code="validation.postalcode" />');"
														title= "<spring:message code="validation.postalcode" />" name="streetNumber"
														class="form-control boxBorder boxHeight" id="postalcode"
														maxlength="6" required/> <label
														for="postalcode"> <spring:message code="input.postalCode" /> <span
														class="star">*</span>
													</label>
												</div>
												<div class="input-field col s12 m6 l6">
													<p
														style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">
														<spring:message code="table.country" /> <span class="star">*</span>
													</p>
													<select id="country" class="browser-default"
														class="mySelect"
														oninput="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
											            oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
														title= "<spring:message code="validation.selectFieldMsg" />"
														 style="padding-left: 0;" required></select>
												</div>

												<div class="input-field col s12 m6 l6">
													<p
														style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">
													<spring:message code="input.province" /> <span class="star">*</span>
													</p>
													<select id="state" class="browser-default" class="mySelect"
													oninput="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
											        oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
													title= "<spring:message code="validation.selectFieldMsg" />"
														style="padding-left: 0;" required></select>
												</div>
											</div>

											<div class="col s12 m12" style="margin-top: 10px;">
												<div class="input-field col s12 m6 l6">
														<input type="email" name="email" id="email" 
														oninput="InvalidMsg(this,'input','<spring:message code="validation.emial" />');"
											            oninvalid="InvalidMsg(this,'input','<spring:message code="validation.emial" />');"
														title= "<spring:message code="validation.emial" />" maxlength="30"> <label for="email"><spring:message code="input.email" /><span
														class="star"></span></label>
												</div>

												<div class="input-field col s12 m6 l6" style="margin-top: 18px;">
													<input type="text" name="phone" 
														pattern="[0-9]{10,10}"
														oninput="InvalidMsg(this,'input','<spring:message code="validation.10digits" />');"
											            oninvalid="InvalidMsg(this,'input','<spring:message code="validation.10digits" />');"
														title= "<spring:message code="validation.10digits" />" required  class="form-control boxBorder boxHeight" id="phone"
														maxlength="10"> <label for="phone"><spring:message code="input.contactNum" /><span class="star">*</span>
													</label>
												</div>
											</div>
										</div>
                                <div class="row">
                                    <div class="col s12 m12">
                                    <h5><spring:message code="company.ViewCompanyInformation" /></h5>
                                    <hr>
                                    <div class="row" style="margin-top: 10px;">
                                        <div class="input-field col s12 m4 l4">
                                            <input type="text" name="firstName" id="firstName" value="${registration.firstName}" maxlength="20" placeholder="" disabled="">
                                            <label for="firstName" class="center-align active"><spring:message code="input.firstName" /></label>
                                        </div>

                                        <div class="input-field col s12 m4 l4">
                                            <input type="text" name="middleName" id="middleName" value="${registration.middleName}" maxlength="20" placeholder="" disabled="">
                                            <label for="middleName" class="active"><spring:message code="input.middleName" /></label>
                                        </div>

                                        <div class="input-field col s12 m4 l4">
                                            <input type="text" name="lastName" placeholder="" disabled="" id="lastName" value="${registration.lastName}" maxlength="20">
                                            <label for="lastName" class="active"><spring:message code="input.lastName" /></label>
                                        </div>

                                        <div class="input-field col s12 m6 l6">
                                            <input type="text" name="asType" disabled="" id="asType" value="${registration.asTypeName}" maxlength="20" placeholder="">
                                            <label for="asType" class="active"><spring:message code="input.AsType"/></label>
                                        </div>

                                        <div class="input-field col s12 m6 l6" id="passportNumberDiv" style="display: none;">
                                            <input type="text" name="passportNumber" id="passportNumber" value="${registration.passportNo}" disabled="" maxlength="20">
                                            <label for="passportNumber">National IDs</label>
                                        </div>

                                        <div class="input-field col s12 m6 l6" id="companyName">
                                            <input type="text" name="company" placeholder="" disabled="" id="company" value="${registration.companyName}" maxlength="30">
                                            <label for="company" class="active"><spring:message code="input.companyName"/> </label>
                                        </div>

                                       <!--  <div class="row myRow" style="display: none;" id="uploadFile">
                                            <div class="col s12 m12">
                                                <h6 class="file-upload-heading" style="margin-left: 15px;">Uploaded
                                                    Nationality
                                                    Information</h6>
                                                <div class="file-field input-field col s12 m6" style="margin-top: 5px;">
                                                    <div class="btn">
                                                        <span>Select File</span>
                                                        <input type="file" id="csvUploadFile" accept=".csv" disabled="">
                                                    </div>
                                                    <div class="file-path-wrapper">
                                                        <input class="file-path validate responsive-file-div" disabled="" type="text" placeholder="">
                                                    </div>
                                                </div><br><br>
                                            </div>
                                        </div> -->

                                        <div class="input-field col s12 m6 l6">
                                            <input type="text" name="email" placeholder="" disabled="" id="email"  value="${registration.email}" maxlength="30">
                                            <label for="email" class="active"><spring:message code="input.EmailID"/></label>
                                        </div>

                                        <div class="input-field col s12 m6 l6">
                                            <input type="text" name="phone" placeholder="" disabled="" id="phone" value="${registration.phoneNo}" maxlength="10">
                                            <label for="phone" class="active"><spring:message code="input.contactNum"/> </label>
                                        </div>


                                    </div>

                                    <div class="row">
                                        <div class="input-field col s12 m12 l12">
                                            <input type="text" name="address" placeholder="" disabled="" value="${registration.propertyLocation}" id="address">
                                            <label for="address" class="active"><spring:message code="input.address"/> </label>
                                        </div>

                                        <div class="input-field col s12 m6 l6">
                                            <input type="text" name="streetNumber" id="streetNumber" value="${registration.street}" disabled="" maxlength="30" placeholder="">
                                            <label for="streetNumber" class="active"><spring:message code="input.streetNumber"/> </label>
                                        </div>
										
										<div class="input-field col s12 m6 l6">
												<input type="text" name="village" id="village" maxlength="20" value="${registration.village}" disabled="" placeholder="">
												<label for="village"><spring:message code="input.village"/></label>
										</div>
											
                                        <div class="input-field col s12 m6 l6">
                                            <input type="text" name="locality" placeholder="" disabled="" id="locality" value="${registration.locality}" maxlength="20">
                                            <label for="locality" class="active"><spring:message code="input.locality"/></label> 
                                        </div>
                                        
                                         <div class="input-field col s12 m6 l6">
												<input type="text" name="district" id="district" maxlength="20" value="${registration.district	}" disabled="" placeholder="">
												 <label for="district"><spring:message code="input.district"/></label>
										</div>
                                        
                                        
                                         <div class="input-field col s12 m6 l6">
                                                <input type="text" name="commune" id="commune" maxlength="20" value="${registration.commune}" disabled="" placeholder="">
                                                <label for="commune" class="active"><spring:message code="input.commune"/> </label>
                                            </div>
                                        
										<div class="input-field col s12 m6 l6">
												<input type="text" name="pin" id="pin" maxlength="20" value="${registration.postalCode}" disabled="" placeholder="">
												<label for="pin"><spring:message code="input.postalCode"/> </label>
										</div> 	
										
                                      	<div class="input-field col s12 m6 l6">
                                            <input type="text" name="Country" placeholder="" disabled="" id="Country"  value="${registration.country}" maxlength="20">
                                            <label for="Country" class="active"><spring:message code="input.Country"/></label>
                                        </div>
                                      
                                        <div class="input-field col s12 m6 l6">
                                            <input type="text" name="province" placeholder="" disabled="" id="province" value="${registration.province}" maxlength="20">
                                            <label for="province" class="active"><spring:message code="input.province"/></label>
                                        </div>

                                    </div>

                                    <div class="row">
                                         <div class="col s12 m6 l6">
                                            <label for="vatNumber"><spring:message code="registration.vatregistration" /> </label>
                                            <div class=" boxHeight">
                                                <input type="text" id="vat" value="${registration.vatStatus}" hidden="hidden">
                                                <input class="with-gap" name="group3" id="yes" type="radio"  disabled=""><spring:message code="modal.yes" />
                                                <input class="with-gap" name="group3" id="no" type="radio" style="margin-left: 20px;" disabled=""><spring:message code="modal.no" />
                                            </div>
                              			 </div>

                                       <div class="input-field col s12 m6 l6">
                                            <input type="text" name="roleType" disabled="" id="roleType"  value="${registration['user'].usertype.usertypeName}" maxlength="16" placeholder="">
                                            <label for="roleType" class="active"> <spring:message code="registration.roletype"/></label>
                                        </div>

                                        <div class="input-field col s12 m6 l6" id="vatNumberField">
                                            <input type="text" name="vatNumber" disabled="" id="vatNumber"  value="${registration.vatNo}"  maxlength="16">
                                            <label for="vatNumber"><spring:message code="registration.vatnumber"/></label>
                                        </div>
                                    </div>
                                </div>
                                </div>

                                <div class="row">
                                    <div class="col s12 m12">
                                  	<div class="input-field col s12 center">
                                         <a class="btn modal-close" href="./registrationRequest"><spring:message code="modal.cancel" /></a>
                                    </div>
                                </div>
                                </div>

                        
                    </div></form>
                </div>
                <!--end container-->
            </div></section>













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
		
	<script type="text/javascript">
		var vatStatus = $('#vat').val();
		if(vatStatus== 1){
			$("#yes").prop("checked", true);
		}else if(vatStatus == 0){
			$("#no").prop("checked", true);
		}
		
	</script>
		
</body>
</html>