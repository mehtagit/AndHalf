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
<!-- Security Tags -->
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:csrfMetaTags />
<!-- Security Tags -->
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<title>Tag Form</title>

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

<link rel="stylesheet"
	href="${context}/resources/custom_js/jquery-ui.css?version=<%= (int) (Math.random() * 10) %>">
<script src="${context}/resources/custom_js/1.11.2_jquery-ui.js?version=<%= (int) (Math.random() * 10) %>"></script>

<script src="${context}/resources/custom_js/jquery.blockUI.js?version=<%= (int) (Math.random() * 10) %>"></script>


<style>
.row {
	margin-bottom: 0;
	margin-top: 0;
}

input[type=text] {
	height: 35px;
	margin: 0 0 5px 0;
}

.checkboxFont {
	font-size: 16px;
	margin-right: 10px;
}

textarea.materialize-textarea {
	padding: 0;
	padding-left: 10px;
}

.btn-flat {
	height: auto;
}

select option {
	color: #444;
}

#filterFileStatus {
	color: #444;
}

.dropdown-content li>a, .dropdown-content li>span {
	color: #444;
}

.welcomeMsg {
	padding-bottom: 50px !important;
	line-height: 1.5 !important;
	text-align: center;
}

.pay-tax-icon {
	font-size: 20px;
	color: #2e568b;
	margin: 0 7px;
}

.row {
	margin-top: 5px;
}

.section {
	padding-top: 0 !important;
}

label {
	font-size: 0.8rem;
}

input[type=text]:disabled+label {
	color: #444;
}

input::placeholder {
	color: #444;
}

select:disabled {
	color: #444;
}

/* .btn-info {
            margin-right: 1%;
        } */
input[type='search'] {
	background-image: url(images/search-512.jpg);
	background-position: 97% 7px;
	background-repeat: no-repeat;
	color: #444;
}

section {
	margin: 0 0.5rem;
}
</style>
</head>
<body data-roleType="${usertype}" data-userTypeID="${usertypeId}"
	data-userID="${userid}" data-selected-roleType="${selectedUserTypeId}"
	data-stolenselected-roleType="${stolenselectedUserTypeId}"
	data-selected-consignmentTxnId="${consignmentTxnId}"
	data-selected-consignmentStatus="${consignmentStatus}">


	<!-- //////////////////////////////////////////////////////////////////////////// -->

	<!-- START CONTENT -->
	<section id="content">

		<div class="row card-panel">
			<div class="container-fluid pageHeader">
				<p class="PageHeading"><spring:message code="view.Field" /></p>
			</div>
			<div class="row">
				<div class="col s12 m12" style="margin-top: 20px;">
					<div id="submitbtn">
						<div class="input-field col s12 m1">
							<label for="Search" class="center-align ml-10"><spring:message code="input.field" /></label>
						</div>
						<div class="input-field col s7 m4">
                                <select class="browser-default" id="tagId" required="">
                                <option value="" selected="">Select field</option>
                                 </select>
                         </div>
						<div class="input-field col s12 m2 l2">
							<button type="button" class="btn"  id="submit" onclick="hide();"><spring:message code="button.submit" /></button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<script type="text/javascript"
		src="${context}/resources/js/materialize.js?version=<%= (int) (Math.random() * 10) %>"></script>
	<script type="text/javascript"
		src="${context}/resources/js/plugins/data-tables/js/jquery.dataTables.js?version=<%= (int) (Math.random() * 10) %>"></script>
	<script type="text/javascript"
		src="${context}/resources/js/plugins/data-tables/js/jquery.dataTables.min.js?version=<%= (int) (Math.random() * 10) %>"></script>
	<script
		src="${context}/resources/custom_js/bootstrap.min.js?version=<%= (int) (Math.random() * 10) %>"></script>
<script type="text/javascript" src="${context}/resources/js/plugins.js?version=<%= (int) (Math.random() * 10) %>"></script>
		<script type="text/javascript"
		src="${context}/resources/js/plugins/perfect-scrollbar/perfect-scrollbar.min.js?version=<%= (int) (Math.random() * 10) %>"></script>
	<script type="text/javascript"
		src="${context}/resources/js/countries.js?version=<%= (int) (Math.random() * 10) %>"></script>
	<script type="text/javascript"
		src="${context}/resources/project_js/enterKey.js?version=<%= (int) (Math.random() * 10) %>"></script>
	<script type="text/javascript"
		src="${context}/resources/project_js/tagForm.js?version=<%= (int) (Math.random() * 10) %>"></script>
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