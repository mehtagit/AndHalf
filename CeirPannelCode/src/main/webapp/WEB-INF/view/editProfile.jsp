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
	 System.out.println("accessTime========"+(accessTime + timeout));
	 System.out.println("timeout========"+timeout);
	 System.out.println("currentTime========"+currentTime);
	 if( currentTime< accessTime + timeout){
	/*  response.setHeader("Refresh", timeout + "; URL = ../login");
	 System.out.println("timeout========"+timeout); 
	if (session.getAttribute("usertype") != null) { */
%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>          
<head>  
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="msapplication-tap-highlight" content="no">
    <meta name="description"
        content="Materialize is a Material Design Admin Template,It's modern, responsive and based on Material Design by Google. ">
    <meta name="keywords"
        content="materialize, admin template, dashboard template, flat admin template, responsive admin template,">
    <title>CEIR | Importer Portal</title>

    <link href="${context}/resources/js/plugins/data-tables/css/jquery.dataTables.min.css" type="text/css" rel="stylesheet"
        media="screen,projection">

    <!-- Favicons-->
    <!--<link rel="icon" href="images/favicon/favicon-32x32.png" sizes="32x32">-->
    <!-- Favicons-->
    <link rel="apple-touch-icon-precomposed" href="${context}/resources/images/favicon/apple-touch-icon-152x152.png">
    <!-- For iPhone -->
    <meta name="msapplication-TileColor" content="#00bcd4">
    <meta name="msapplication-TileImage" content="${context}/resources/images/favicon/mstile-144x144.png">
    <!-- For Windows Phone -->
    <link rel="stylesheet" href="${context}/resources/font/font-awesome/css/font-awesome.min.css">

    <!-- CORE CSS-->
    <!--<link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection">-->
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <link href="${context}/resources/css/style.css" type="text/css" rel="stylesheet" media="screen,projection">
    <!-- Custome CSS-->
    <link href="${context}/resources/css/custom/custom.css" type="text/css" rel="stylesheet" media="screen,projection">

    <!-- INCLUDED PLUGIN CSS ON THIS PAGE -->
    <link href="${context}/resources/js/plugins/prism/prism.css" type="text/css" rel="stylesheet" media="screen,projection">
    <link href="${context}/resources/js/plugins/perfect-scrollbar/perfect-scrollbar.css" type="text/css" rel="stylesheet"
        media="screen,projection">
    <link href="${context}/resources/js/plugins/chartist-js/chartist.min.css" type="text/css" rel="stylesheet" media="screen,projection">
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

		.row
		{
			margin-bottom: 0;
		}
		
        .dropdown-content li>a,
        .dropdown-content li>span {
            color: #444;
        }

        .input-field>label {
            color: #444 !important;
        }
        
        .btn{background-color: #ff4081;}
        .btn:hover{background-color: #ff4081;}
        select {
            background-color: transparent;
            border: none;
            border-bottom: 1px solid #9e9e9e;
            padding: 0;
            margin-top: 7px;
            ;
        }

        [type="radio"]:not(:checked),
        [type="radio"]:checked {
            opacity: 0;
        }
        .fa-eye-slash, .fa-eye {
	position: absolute;
	right: 10px;
	top: 10px;
}
.card-panel {
margin: 0.5rem;
}

.section {
padding-top: 0;
}
    </style>


<script>
var contextpath = "${context}";
</script>


</head>


<body>
<!-- Modal End -->
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
							
							<div class="row">
								<div class="input-field col s12 m4 l4">
								<input type="hidden" id="id" name="id">
								
									<input type="text" name="firstName" id="firstName" placeholder=""
										  pattern="[A-Za-z]{3,20}" maxlength="20"   
					oninput="InvalidMsg(this,'input','<spring:message code="validation.20Character" />');" oninvalid="InvalidMsg(this,'input','<spring:message code="validation.20Character" />');"
					  required > <label 
										class="center-align" ><spring:message code="input.firstName" /> <span class="star">*</span></label>
								</div>

								<div class="input-field col s12 m4 l4">
									<input type="text" name="middleName" placeholder=""
										class="form-control boxBorder boxHeight" id="middleName"
										 pattern="[A-Za-z]{3,20}" maxlength="20"  oninput="InvalidMsg(this,'input','<spring:message code="validation.20Character" />');" 
										 oninvalid="InvalidMsg(this,'input','<spring:message code="validation.20Character" />');"> <label >
										<spring:message code="input.middleName" /> </label>
								</div>

								<div class="input-field col s12 m4 l4">
									<input type="text" name="lastName" placeholder=""
										class="form-control boxBorder boxHeight" id="lastName"
										pattern="[A-Za-z]{3,20}" maxlength="20" 
								oninput="InvalidMsg(this,'input','<spring:message code="validation.20Character" />');" 
										 oninvalid="InvalidMsg(this,'input','<spring:message code="validation.20Character" />');" required> <label>
										<spring:message code="input.lastName" />  <span class="star">*</span>
									</label>
								</div>


<div class="input-field col s12 m6 l6" style="display: none;" id="AsTypeDiv">
                                    <p style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;"><spring:message code="registration.astype" /> <span
                                            class="star">*</span></p>
                                        <input type="text" readonly="readonly" id="asTypeName" name="type"  />
                                        <input type="hidden"  id="type" name="type"  />                         
                                </div>
                                
                                <div class="input-field col s12 m6 l6" id="passportNumberDiv" style="display: none;">
									<input placeholder="" type="text" name="passportNo" id="passportNo" readonly="readonly"
									/>
									<label for="passportNo"><spring:message code="registration.nationalid/passporartnumber" /> <span class="star">*</span></label>
								</div>

								<div class="input-field col s12 m6 l6" id="companyNames" style="display: none; margin-top: 22px;">
									<input placeholder="" type="text" readonly="readonly" name="companyName" id="companyName"
									/>
									<label for="companyName"><spring:message code="registration.companyName" /> <span class="star">*</span></label>
								</div>

								<div class="row myRow" style="display: none;" id="uploadFile">
									<div class="col s12 m12">
										<h6 class="file-upload-heading"><spring:message code="registration.UploadNationalityInformation" /> <span class="star">*</span></h6>
									<div class="input-field col s12 m6 l6">
                                            <input type="text" name="uploadNationalId" id="file"  maxlength="30" disabled>
                                            <input type="hidden"  id="filePath">
                                            
                                <%--             <label for="uploadNationalId" class="active"><spring:message code="registration.uploadednationalid" /></label> --%>
                                           <span> <a  href="#" onclick="previewFile2(document.getElementById('filePath').value,document.getElementById('file').value)">  <spring:message code="registration.preview" /></a> </span>   
                                        </div>
									</div>
									<!-- <p style="margin-left: 15px;"><a javascript:void(0)>Download Sample Format</a></p> -->
								</div>
								
								<div class="input-field col s12 m6 l6">
									<input type="text"   placeholder="" name="email" maxlength="280"
										class="form-control boxBorder boxHeight" id="email"
										oninput="InvalidMsg(this,'email','<spring:message code="validation.email" />');"
										 oninvalid="InvalidMsg(this,'email','<spring:message code="validation.email" />');"
								 pattern="[^@]+@[^@]+\.[a-zA-Z]{3,280}"  required >
										  <label for="email"> <spring:message code="input.email" /> <span
										class="star">*</span></label>
								</div>

								<div class="input-field col s12 m6 l6">
									<input   placeholder="" type="text"  maxlength="20"
										class="form-control boxBorder boxHeight" id="phoneNo"
										pattern="[0-9]{8,20}" 
								oninput="InvalidMsg(this,'input','<spring:message code="validation.20Character" />');" 
								oninvalid="InvalidMsg(this,'input','<spring:message code="validation.20Character" />');" required  > <label>
										<spring:message code="registration.phonenumber" /> <span class="star">*</span> 
									</label>
								</div>
							</div>
															<div class="row">
							<div class="input-field col s12 m12 l12">
									<input type="text" maxlength="200"
										pattern="[A-Za-z0-9._%+-$@,/]+\.{0,200}" placeholder="" name="propertyLocation"
										class="form-control boxBorder boxHeight"
										
								oninput="InvalidMsg(this,'input','<spring:message code="validation.200characters" />');" 
								oninvalid="InvalidMsg(this,'input','<spring:message code="validation.200characters" />');"
										
										id="propertyLocation" required  > <label for="propertyLocation"><spring:message code="input.address" />  <span
										class="star">*</span></label> 
								</div>

								<div class="input-field col s12 m6 l6">
									<input type="text" name="street" maxlength="20"
										class="form-control boxBorder boxHeight" id="street"
										pattern="[A-Za-z0-9._%+-$@,/]+\.{1,20}" placeholder="" 
								oninput="InvalidMsg(this,'input','<spring:message code="validation.20Character" />');" 
								oninvalid="InvalidMsg(this,'input','<spring:message code="validation.20Character" />');"
								required >
									<label for="street"><spring:message code="input.streetNumber" /> <span class="star">*</span>
									</label>
								</div>
									<div class="input-field col s12 m6 l6">
									<input type="text" name="village" maxlength="30"
										class="form-control boxBorder boxHeight" id="village"
										pattern="[A-Za-z0-9._%+-$@,/]+\.{3,30}" placeholder="" 
										oninput="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');" 
										oninvalid="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');"
									required >
									<label for="village"><spring:message code="input.village" /> <span class="star">*</span>
									</label>
								</div>
								<div class="input-field col s12 m6 l6">
									<input type="text" name="locality" maxlength="30"
										class="form-control boxBorder boxHeight" id="locality"
										pattern="[A-Za-z0-9._%+-$@,/]+\.{3,30}" placeholder="" 
									
										oninput="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');" 
										oninvalid="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');" required  >
									<label for="locality"><spring:message code="input.locality" /> <span class="star">*</span>
									</label>
								</div>

								<div class="input-field col s12 m6 l6">
									<input type="text" name="district" placeholder="" maxlength="30"
										class="form-control boxBorder boxHeight" id="district"
										pattern="[A-Za-z0-9._%+-$@,/]+\.{3,30}" 
										
										oninput="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');" 
										oninvalid="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');"
										 required  >
									<label for="district"><spring:message code="input.district" /> <span class="star">*</span>
									</label>
								</div>
								<div class="input-field col s12 m6 l6">
									<input type="text" placeholder="" name="commune" maxlength="30"
										class="form-control boxBorder boxHeight" id="commune"
										pattern="[A-Za-z0-9._%+-$@,/]+\.{0,30}" 
										oninput="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');" 
										oninvalid="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');" required  >
									<label for="commune"><spring:message code="input.commune" /> <span class="star">*</span>
									</label>
								</div>
								<div class="input-field col s12 m6 l6">
									<input required="required" type="text" placeholder="" name="postalCode" maxlength="6"
										class="form-control boxBorder boxHeight" id="postalCode"
										pattern="[A-Za-z0-9\s]{0,6}"
										oninput="InvalidMsg(this,'input','<spring:message code="validation.postalcode" />');" 
										oninvalid="InvalidMsg(this,'input','<spring:message code="validation.postalcode" />');">
									<label for="postalCode"><spring:message code="input.postalCode" /><span class="star">*</span></label>
								</div>
								
								
								
								<div class="input-field col s12 m6 l6">
									<p
										style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">
										<spring:message code="table.country" /> <span class="star">*</span>
									</p>
									<select id="country" class="browser-default" class="mySelect"
onchange="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');" oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"								
										style="padding-left: 0;" required></select>
								</div>

								<div class="input-field col s12 m6 l6">
									<p
										style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">
										 <spring:message code="input.province" /> <span class="star">*</span>
									</p>
									<select id="state" class="browser-default" class="mySelect"
onchange="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');" oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"									
										style="padding-left: 0;" required></select>
								</div>
							</div>

							
                               <div class="row">
                            <div class="col s12 m6 l6" style="margin-bottom: 10px;">
									<label for="vatNumber"><spring:message code="registration.vatnumber" /> <span class="star">*</span></label>
									<div class=" boxHeight" draggable="true">
										<label><input placeholder="" disabled="disabled"  class="with-gap vatStatus" id="vatYes" value="1" name="vatStatus" type="radio">
											<span><spring:message code="registration.radioyes" /></span> </label> <label> 
											<input id="vatNos" disabled="disabled" class="with-gap vatStatus" name="vatStatus" type="radio" style="margin-left: 20px;" value="0"/> <span><spring:message code="registration.radiono" /></span>
										</label>
									</div>
								</div>
								
							<%-- 	<div class="input-field col s12 m6 l6" id="rolesDiv" style="display: none;">
									<p
										style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">
										<spring:message code="table.roleType" /> <span class="star">*</span>
									</p> 
									<select multiple  name="roles" id="usertypes"  >
										<option value="" disabled><spring:message code="table.roleType" /></option>
								</select>
								</div> --%>
								
								
								<div class="row">
									<div class="input-field col s12 m6 l6" style="display: none;" id="vatNumberField">
										<input type="text" placeholder="" name="vatNo" disabled="disabled"  maxlength="12" id="vatNo" pattern="[A-Za-z0-9]{9,12}"
										oninput="InvalidMsg(this,'input','<spring:message code="validation.12Character" />');" oninvalid="InvalidMsg(this,'input','<spring:message code="validation.12Character" />');" 
										>
										<label for="vatNo"><spring:message code="registration.vatnumber" /> <span class="star">*</span></label>
									</div>

	
							
							
									<div id="vatFileDiv" style="display: none;">
										 <%-- <div class="file-field col s12 m6">
											<p class="upload-file-label"><spring:message code="registration.vatfile" /> <span class="star">*</span></p>
											<div class="input-field col s12 m6 l6">
                                            <input type="text" name="uploadVatFile" id="vatFile"  maxlength="30" disabled="">
                                            <input type="hidden"  id="vatFilePath">
                                            
                                            <label for="uploadNationalId" class="active"><spring:message code="registration.uploadednationalid" /></label>
                                           <span> <a  href="#" onclick="previewFile2(document.getElementById('vatFilePath').value,document.getElementById('vatFile').value)">  <spring:message code="registration.preview" /></a> </span>   
                                        </div>
										</div> --%>
										<div class="input-field col s12 m6 l6">
							<input type="text" name="uploadVatFile" id="vatFile"  maxlength="30" disabled="">
                                            <input type="hidden"  id="vatFilePath">
<%-- 										<label for="vatNo"><spring:message code="registration.vatnumber" /> <span class="star">*</span></label> --%>
									 <span> <a  href="#" onclick="previewFile2(document.getElementById('vatFilePath').value,document.getElementById('vatFile').value)">  <spring:message code="registration.preview" /></a> </span>
									</div>
											</div>
								</div>
                            </div>    
								
							
								
							</div>

						
							
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
									<input  type="text" placeholder="" name="answer" 
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
									<input  type="text" placeholder="" name="answer"
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
									<input type="text" name="answer" placeholder=""
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
								<%if(userLatestLang!=null){%>
								
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

	<!-- Modal End -->

	<!-- Modal 2 start   -->

	<!-- <div id="submitActivateDeactivate" class="modal">
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
	
		<!-- ================================================
    Scripts
    ================================================ -->
	 <!-- jQuery Library -->
    <!-- <script type="text/javascript" src="js/plugins/jquery-1.11.2.min.js"></script>-->
  <script type="text/javascript" src="${context}/resources/js/plugins/jquery-1.11.2.min.js"></script>
  <script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.1/jquery.min.js"></script>
       <!-- ajax js -->
  
       	
	




<script type="text/javascript" src="${context}/resources/ajax/Registration.js"></script>
      <script type="text/javascript" src="${context}/resources/ajax/Profile.js"></script>
       	<script type="text/javascript" src="${context}/resources/ajax/Password.js"></script>   
    		
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
<script type="text/javascript"
		src="${context}/resources/project_js/profileInfoTab.js" async></script>
	<!-- //////////////////////////////////////////////////////////////////////////// -->

	
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
		src="${context}/resources/project_js/validationMsg.js"></script>
		    <script type="text/javascript"
		src="${context}/resources/project_js/messageWindow.js"></script>  
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
     
       editProfile();
   	
      
   $('.modal').modal();
            /* $('.dropdown-trigger').dropdown();
            $('select').formSelect(); */
        }); 
       
      
       	
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