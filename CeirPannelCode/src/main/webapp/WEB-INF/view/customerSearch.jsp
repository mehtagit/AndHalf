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
<title>Dashboard</title>

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
	href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>

<script src="http://malsup.github.io/jquery.blockUI.js"></script>
<script src="http://malsup.github.io/jquery.blockUI.js"></script>
<script src="//cdn.datatables.net/plug-ins/1.10.20/i18n/Khmer.json"></script>

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

 <div class="card-panel" style="    height: 156px;">
                <div class="container-fluid pageHeader">
                  <p class="PageHeading">Search</p>
                  <!-- <a href="createConsignment.html" class="boton right">Register Consignment</a> -->
                </div>

                <div class="container" id="searchDiv">
                  <div id="profile-page" class="section">
                    <div class="row ">
                      <form action="#" onsubmit="return hide()">
                        <div class="col s12 m12 l12 bx-shadow">
                          <div class="row">
                           
                            
                            <div class="col s12 m3 l3">
                              <label for="deviceIdType">Device ID Type <span class="star">*</span></label>
                              <select class="browser-default" id="deviceType" style="height: 30px" 
                              					
												required>
                                  <option value=""  selected>Device ID Type</option>
                              </select>
                          </div>
                            
                            <div class="input-field col s12 m3 l3">
                              <input type="text" name="IMEI" id="imei" maxlength="16" pattern="[0-9\s]{15,16}" value="" required/>
                              <p id="errorMsgOnModal" class="deviceErrorTitle"></p>
                              <label for="IMEI" >IMEI<span class="star">*</span></label>
                            </div>
                            
                             <div class="input-field col s12 m3 l3">
                              <input type="text" id="msisdn" name="MSISDN" maxlength="15" pattern="[0-9\s]{15}" value="">
                              <label for="MSISDN" >MSISDN</label>
                            </div>
                            
                            <div class="input-field col s12 m3 l3">
                              <button class="btn " type="submit"><spring:message code="button.submit" /></button>
                              <a onclick="clearFields()" class="btn" style="margin-left: 10px;">Clear</a>
                            </div>
                          </div>
                        </div>
                      </form>
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
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js?version=<%= (int) (Math.random() * 10) %>"></script>
	<script type="text/javascript" src="${context}/resources/js/plugins.js?version=<%= (int) (Math.random() * 10) %>"></script>

	<script type="text/javascript"
		src="${context}/resources/js/plugins/perfect-scrollbar/perfect-scrollbar.min.js?version=<%= (int) (Math.random() * 10) %>"></script>
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
		src="${context}/resources/project_js/enterKey.js?version=<%= (int) (Math.random() * 10) %>"></script>
	<script type="text/javascript"
		src="${context}/resources/project_js/customerSearch.js?version=<%= (int) (Math.random() * 10) %>"></script>
		<script type="text/javascript"
		src="${context}/resources/project_js/validationMsg.js?version=<%= (int) (Math.random() * 10) %>"></script>
</body>
</html>
<%
} else {

%>
<script language="JavaScript">
sessionStorage.setItem("loginMsg",
"*Session has been expired");
window.top.location.href = "./login";
</script>
<%
}
%>