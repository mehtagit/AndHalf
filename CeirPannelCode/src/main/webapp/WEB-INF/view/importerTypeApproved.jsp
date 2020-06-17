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
	 long dfd= accessTime +timeout;
	 if( currentTime< dfd){
	/*  response.setHeader("Refresh", timeout + "; URL = ../login");
	 System.out.println("timeout========"+timeout); 
	if (session.getAttribute("usertype") != null) { */
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
<title>Importer</title>
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
<!-- <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css"></script>
-->
<!-- Favicons-->
<link rel="icon" href="${context}/resources/images/DMC-Logo.png" sizes="32x32">
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
<%-- <link href="${context}/resources/js/plugins/chartist-js/chartist.min.css" type="text/css" rel="stylesheet" media="screen,projection"> --%>
<link rel="stylesheet"
	href="${context}/resources/project_css/viewStock.css">
<link rel="stylesheet"
	href="${context}/resources/project_css/iconStates.css">

</head>
<body data-id="21" data-roleType="${usertype}" data-userID="${userid}"
	data-userTypeID="${usertypeId}"
	data-selected-roleType="${selectedUserTypeId}"
	data-stolenselected-roleType="${stolenselectedUserTypeId}">




	<section id="content">
		<!--start container-->
		
		<div class="container">
			<div class="section">
				<div class="row">
					<div class="col s12 m12 l12">
						<div class="row card-panel">
							<div class="container-fluid pageHeader">
								<p class="PageHeading">
									<spring:message code="modal.header.typeApproved" />
								</p>
							</div>

							<form onsubmit="return registerTAC()" method="POST"
								enctype="multipart/form-data" id="registerTAC">

								<div class="row myRow">
									<div class="input-field col s12 m6 l6" style="margin-top: 22px">
										<input type="text" id="trademark" name="trademark"
											pattern="[A-Za-z0-9 \s]{0,160}"
											oninput="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');"
											oninvalid="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');"
											title="" maxlength="30" required> <label
											for="trademark"><spring:message
												code="registration.trademark" /> <span class="star">*</span></label>
									</div>


									<div class="col s12 m6 l6" style="margin-bottom: 5px;">
										<label for="productName"><spring:message
												code="table.ProductName" /> <span class="star">*</span></label> <select
											id="productname" class="browser-default"
											onchange="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
											oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
											required>
											<option value="" disabled selected><spring:message
													code="registration.selectproduct" />
											</option>
										</select>
									</div>
								</div>

								<div class="row myRow">
									<div class="col s12 m6 l6">
										<label for="modalNumber"><spring:message
												code="registration.modelnumber" /> <span class="star">*</span></label>
										<select id="modelNumber" class="browser-default"
											onchange="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
											oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
											required>
											<option value="" disabled selected>
												<spring:message code="registration.selectmodelnumber" /></option>

										</select>
									</div>

									<div class="col s12 m6 l6" hidden="hidden">
										<select class="browser-default" id="status">
										</select>
									</div>

									<div class="col s12 m6 l6">
										<label><spring:message code="input.Country" /> <span
											class="star">*</span></label> <select id="country"
											class="browser-default" class="mySelect"
											style="padding-left: 0;"
											onchange="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
											oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
											required></select>
									</div>

								</div>

								<div class="row myRow">
									<div class="input-field col s12 m6 l6">
										<input type="text" id="frequencyrange"
											oninput="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');"
											oninvalid="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');"
											maxlength="30" required> <label for="frequencyrange"><spring:message
												code="registration.frequencyrange" /> <span class="star">*</span></label>
									</div>
									<div class="input-field col s12 m6 l6">
										<input type="text" id="tac" pattern="[A-Za-z0-9 \s]{8,8}"
											oninput="InvalidMsg(this,'input','<spring:message code="validation.tac8" />');"
											oninvalid="InvalidMsg(this,'input','<spring:message code="validation.tac8" />');"
											name="tac" maxlength="8" required="required"> <label
											for="tac"><spring:message code="registration.tac" />
											<span class="star">*</span></label>
									</div>
								</div>



								<div id="mainDiv" class="mainDiv">
									<div id="filediv" class="fileDiv">
										<div class="row myRow">
											<div class="col s12 m6 l6">
												<label for="Category"><spring:message
														code="input.documenttype" /></label> <select
													class="browser-default" id="docTypetag1"
													onchange="enableSelectFile()">
													<option value="" disabled selected><spring:message
															code="select.documenttype" />
													</option>

												</select> <select class="browser-default" id="docTypetagValue1"
													style="display: none;">
													<option value="" disabled selected><spring:message
															code="select.documenttype" /></option>

												</select>
											</div>

											<div class="file-field col s12 m6">
												<h6 id="supportingdocumentFile" class="file-upload-heading">
													<spring:message code="input.supportingdocument" />
												</h6>
												<div class="btn">
													<span><spring:message code="input.selectfile" /></span> <input
														type="file" name="files[]" id="docTypeFile1"
														disabled="disabled" onchange="enableAddMore()"
														onchange="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
														oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');">
												</div>
												<div class="file-path-wrapper">
													<input class="file-path validate" type="text"
														placeholder="<spring:message code="grievanceFileMessage" />">
													<div>
														<p id="myFiles"></p>
													</div>
												</div>
											</div>
										</div>


									</div>

								</div>
								<div class="col s12 m6 right">
									<button class="btn right add_field_button" disabled="disabled">
										<span style="font-size: 20px;">+</span>
										<spring:message code="input.addmorefile" />
									</button>
								</div>

								<div class="row myRow">
									<span><spring:message code="input.requiredfields" /><span
										class="star">*</span></span>
								</div>
								<div class="row">
									<div class="center">
										<button class="btn" id="trcSubmitButton" type="submit">
											<spring:message code="button.submit" />
										</button>
										<a href="./manageTypeDevices2" class="btn" id="Cancel"
											style="margin-left: 10px;"><spring:message
												code="button.cancel" /></a>
									</div>
								</div>

							</form>
						</div>
					</div>

				</div>

			</div>
		</div>

		<!--end container-->
	</section>


	<div id="RegisterManageTypeDevice" class="modal">
		<h6 class="modal-header">
			<spring:message code="modal.header.submitTypeApprove" />
		</h6>
		<div class="modal-content">
			<div class="row">
				<h6 id="sucessMessage">
				</h6><span id="transactionId"> </span>
				<input type="text" style="display: none" id="errorCode">
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<a href="./manageTypeDevices2" class="btn"><spring:message
							code="modal.ok" /></a>
				</div>
			</div>
		</div>
	</div>

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
						<button class=" btn" onclick="clearFileName()"
							style="margin-left: 10px;">
							<spring:message code="modal.ok" />
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>




	<!--materialize js-->
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js?version=<%= (int) (Math.random() * 10) %>"></script>
	<script type="text/javascript"
		src="${context}/resources/js/materialize.js"></script>


	<script type="text/javascript"
		src="${context}/resources/js/plugins/data-tables/js/jquery.dataTables.js?version=<%= (int) (Math.random() * 10) %>"></script>
	<script type="text/javascript"
		src="${context}/resources/js/plugins/data-tables/js/jquery.dataTables.min.js?version=<%= (int) (Math.random() * 10) %>"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.3/moment.min.js?version=<%= (int) (Math.random() * 10) %>"></script>

	<!--plugins.js - Some Specific JS codes for Plugin Settings-->
	<script
		src="${context}/resources/custom_js/bootstrap.min.js?version=<%= (int) (Math.random() * 10) %>"></script>

	<%-- <script type="text/javascript" src="${context}/resources/js/materialize-plugins/date_picker/picker.date.js"></script>
<script type="text/javascript" src="${context}/resources/js/materialize-plugins/date_picker/picker.js"></script> --%>
	<!--custom-script.js - Add your own theme custom JS-->
	<script type="text/javascript" src="${context}/resources/js/plugins.js?version=<%= (int) (Math.random() * 10) %>"></script>
	<!--prism
<script type="text/javascript" src="${context}/resources/resources/js/prism/prism.js"></script>-->
	<!--scrollbar-->
	<script type="text/javascript"
		src="${context}/resources/js/plugins/perfect-scrollbar/perfect-scrollbar.min.js?version=<%= (int) (Math.random() * 10) %>"></script>
	<!-- chartist -->
	<%-- <script type="text/javascript" src="${context}/resources/js/plugins/chartist-js/chartist.min.js"></script> --%>
	<script type="text/javascript"
		src="${context}/resources/js/countries.js?version=<%= (int) (Math.random() * 10) %>"></script>
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
		src="${context}/resources/project_js/viewStock.js?version=<%= (int) (Math.random() * 10) %>"></script>
	<script type="text/javascript"
		src="${context}/resources/js/countries.js?version=<%= (int) (Math.random() * 10) %>"></script>
	<script type="text/javascript"
		src="${context}/resources/project_js/importerTypeApproved.js?version=<%= (int) (Math.random() * 10) %>"></script>
	<script type="text/javascript"
		src="${context}/resources/project_js/validationMsg.js?version=<%= (int) (Math.random() * 10) %>"></script>
	<script type="text/javascript"
		src="${context}/resources/project_js/_dateFunction.js?version=<%= (int) (Math.random() * 10) %>" async></script>
	<script type="text/javascript"
		src="${context}/resources/project_js/globalVariables.js?version=<%= (int) (Math.random() * 10) %>"></script>
	<script type="text/javascript">
		populateCountries("country");
	</script>
</body>
</html>
<%
	} else {
		/*  request.setAttribute("msg", "  Please login first");
		request.getRequestDispatcher("./index.jsp").forward(request, response);  */
%>
<script language="JavaScript">
	sessionStorage.setItem("loginMsg", "*Session has been expired");
	window.top.location.href = "./login";
</script>
<%
	}
%>
