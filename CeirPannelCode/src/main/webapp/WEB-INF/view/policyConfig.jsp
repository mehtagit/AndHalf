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
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Security Tags -->
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:csrfMetaTags />
<!-- Security Tags -->

<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<title>Policy Config</title>

<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta content="" name="description" />
<meta content="" name="author" />
<!-- Security Tags -->
<meta name="_csrf" content="${_csrf.token}"/>
<!-- default header name is X-CSRF-TOKEN -->
<meta name="_csrf_header" content="${_csrf.headerName}"/>
<!-- Security Tags -->

<script type="text/javascript"
	src="${context}/resources/js/plugins/jquery-1.11.2.min.js"></script>
<!--   <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css"></script>  
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
<%--  <link href="${context}/resources/js/plugins/chartist-js/chartist.min.css" type="text/css" rel="stylesheet" media="screen,projection"> --%>
<link rel="stylesheet"
	href="${context}/resources/project_css/viewConsignment.css">
<link rel="stylesheet"
	href="${context}/resources/project_css/iconStates.css">
	
 <link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
	



</head>
<%-- <body data-roleType="${usertype}" data-userID="${userid}" data-selected-roleType="${selectedUserTypeId}"> --%>
<body data-roleType="${usertype}" data-userTypeID="${usertypeId}" data-userID="${userid}" 
data-selected-roleType="${selectedUserTypeId}"
data-selected-username="${username}"
 data-stolenselected-roleType="${stolenselectedUserTypeId}">


	<!-- START CONTENT -->
	<!-- START CONTENT -->
	<section id="content">
		<!--start container-->
		<div class="container">
			<div class="section">
				<div class="row">
					<div class="col s12 m12 l12">
						<div class="row card-panel">
							<div class="container-fluid pageHeader" id="pageHeader">

								<a href="" class="boton right" id="btnLink" hidden></a>
							</div>
							<form action="${context}/policyManagement"
								method="post">
								<div class="col s12 m12 l12" id="configTableDiv"
									style="padding-bottom: 5px; background-color: #e2edef52;">
									<div id="filterBtnDiv">
										<!-- 							<div class='col s12 m2 l2'><button type='submit' class='btn primary botton' id='submitFilter'></button></div>
		 -->
									</div>
								</div>
							</form>
							<table id="configLibraryTable"
								class="responsive-table striped display"></table>

						</div>

					</div>
				</div>
				<div id="footerBtn"></div>
			</div>
		</div>
		<!--end container-->
	</section>

   <!-- Modal 2 start   -->

	<div id="viewPolicyConfigModel" class="modal">
		<h6 class="modal-header"><spring:message code="registration.viewpolicyconfiguration" /></h6>
		<div class="modal-content">

			<div class="row">
				<div class="row" style="margin-top: 10px;">
					
						<input type="text" name="tag" id="viewTag" placeholder="tag" disabled hidden="hidden" > 
				
					<div class="input-field col s12 m6 l6">
						<input type="text" name="period" id="viewPeriod"
							placeholder="Period" disabled>
						<label for="period"><spring:message code="table.Period" /> </label>
					</div>

					<div class="input-field col s12 m6">
						<input type="text" id="viewstatus" name="status"
							placeholder="" maxlength="20" placeholder="Status" disabled >
						<label for="status"><spring:message code="table.status" /> </label>
					</div>
				
					
					
					<div class="input-field col s12 m6">
					<textarea id="viewValue" class="materialize-textarea" placeholder="value" readonly="readonly" style="min-height:8rem"></textarea>
					<label for="viewValue" class=""><spring:message code="table.Value" /> </label>

					</div>
					
					
					
					<div class="input-field col s12 m6">
					<textarea id="description" class="materialize-textarea" placeholder="Description" readonly="readonly" style="min-height:8rem"></textarea>
					<label for="description" class=""><spring:message code="registration.description" /></label>

					</div>
				
					
				</div>

				
					

			

				<div class="row input_fields_wrap">
					<div class="col s12 m12 center" style="margin-top: 10px;">
					<button class="btn modal-close" style="margin-left: 10px;"><spring:message code="modal.close" /></button>
				</div>

				</div>
			</div>
		</div>
	</div>
	<!-- Modal End -->

	   <!-- Modal 2 start   -->

	<div id="editPolicyConfigModel" class="modal">
		<h6 class="modal-header"><spring:message code="registration.editpolicyconfiguration" /></h6>
		<div class="modal-content">
		<form action="" onsubmit="return updatePolicy()">
		<div class="row">
			<div class="row">
					<div class="input-field col s12 m6 l6">
						<input type="text" name="tag" id="editTag"
							placeholder="tag" hidden="hidden"> 
					</div>
					
					<div class="input-field col s12 m6 l6">
						<input type="text" name="tag" id="EditId"
							placeholder="ID" hidden="hidden"> 
						<input type="text" name="status" id="EditStatusValue"
							placeholder="" hidden="hidden"> 	
					</div>
					
				

						<div class="col s12 m6">
							<label for="port" class="active"><spring:message code="table.Period" /><span class="star">
									*</span></label> <select class="browser-default" id="editPeriod"
								required="required">
								<option value="" selected > Select Period</option>
							</select> <input type="text" id="editId" hidden>
						</div>

						<div class="col s12 m6">
							<label for="port" class="active"><spring:message code="table.status" /><span class="star">
									*</span></label> <select class="browser-default" id="editstatus"
								required="required">
								<option value="" selected>Select status</option>
							</select>
						</div>

					<div class="input-field col s12 m6"  style="margin-top: 30px;">
					<textarea id="editValue" class="materialize-textarea" placeholder="value" title="Please enter alphabets and numbers upto 100 characters only" maxlength="100" required="required" style="min-height:8rem"></textarea>
					<label for="editValue" class=""><spring:message code="table.Value" /> <span class="star">*</span></label>

					</div>
					
					
					
					<div class="input-field col s12 m6" style="margin-top: 30px;">
					<textarea id="editdescription" class="materialize-textarea" placeholder="Description" title="Please enter alphabets and numbers upto 200 characters only" maxlength="200" style="min-height:8rem"></textarea>
					<label for="description" class=""><spring:message code="registration.description" /></label>

					</div>
				
					
				</div>

				

				<div class="row input_fields_wrap">
					<div class="col s12 m12 center" style="margin-top: 10px;">
					<button class="btn " type="submit"><spring:message code="button.update" /></button>
					<button class="modal-close btn" type="button" style="margin-left: 10px;"><spring:message code="button.cancel" /></button>
				</div>

				</div>
			</div>
			</form>
		</div>
	</div>
	<!-- Modal End -->
	
	
		<!-- Modal 3 start   -->

	<div id="confirmedUpdatedPolicy" class="modal">
		<h6 class="modal-header"><spring:message code="registration.updatepolicymanagement" /></h6>
		<div class="modal-content">



			<div class="row">
				<h6 id="sucessMessage"> <spring:message code="registration.policyupdatedsuccessfully" /></h6>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<a href="${context}/policyManagement" class="btn"> <spring:message code="modal.ok" /></a>
				</div>
			</div>
		</div>
	</div>
	
	
	
	<!--materialize js-->
	<script type="text/javascript"
		src="${context}/resources/js/materialize.js?version=<%= (int) (Math.random() * 10) %>"></script>
	<script type="text/javascript"
		src="${context}/resources/js/plugins/data-tables/js/jquery.dataTables.js?version=<%= (int) (Math.random() * 10) %>"></script>
	<script type="text/javascript"
		src="${context}/resources/js/plugins/data-tables/js/jquery.dataTables.min.js?version=<%= (int) (Math.random() * 10) %>"></script>

	

	<!--plugins.js - Some Specific JS codes for Plugin Settings-->
	<script
		src="${context}/resources/custom_js/bootstrap.min.js?version=<%= (int) (Math.random() * 10) %>"></script>

	<%--   <script type="text/javascript" src="${context}/resources/js/materialize-plugins/date_picker/picker.date.js"></script>
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
	<script type="text/javascript"
		src="${context}/resources/project_js/policyConfig.js?version=<%= (int) (Math.random() * 10) %>"></script>
	<script type="text/javascript"
		src="${context}/resources/project_js/dragableModal.js?version=<%= (int) (Math.random() * 10) %>"></script>
		
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