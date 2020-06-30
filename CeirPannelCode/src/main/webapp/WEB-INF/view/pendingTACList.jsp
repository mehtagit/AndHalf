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
<title>Pending TAC</title>

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
<!--   <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css"></script>  
 -->
<!-- Favicons-->
<link rel="icon" href="${context}/resources/images/DMC-Logo.png" sizes="32x32">
		<script type="text/javascript">
var path="${context}";
</script>

<!-- CORE CSS-->
<link href="${context}/resources/css/materialize.css" type="text/css"
	rel="stylesheet" media="screen,projection">
<link href="${context}/resources/css/style.css" type="text/css"
	rel="stylesheet" media="screen,projection">
<link
	href="${context}/resources/js/plugins/data-tables/css/jquery.dataTables.min.css"
	type="text/css" rel="stylesheet" media="screen,projection">

<link
	href="${context}/resources/font/font-awesome/css/font-awesome.min.css"
	type="text/css" rel="stylesheet" media="screen,projection">

<!-- INCLUDED PLUGIN CSS ON THIS PAGE -->
<link href="${context}/resources/js/plugins/prism/prism.css"
	type="text/css" rel="stylesheet" media="screen,projection">
<link
	href="${context}/resources/js/plugins/perfect-scrollbar/perfect-scrollbar.css"
	type="text/css" rel="stylesheet" media="screen,projection">
<link rel="stylesheet"
	href="${context}/resources/project_css/viewConsignment.css">
<link rel="stylesheet"
	href="${context}/resources/project_css/iconStates.css">
	<link rel="stylesheet"
	href="${context}/resources/css/grievance.css">
<link rel="stylesheet"
	href="${context}/resources/custom_js/jquery-ui.css">
<!------------------------------------------- Dragable Model---------------------------------->
<script
	src="${context}/resources/custom_js/1.12.1_jquery-ui.min.js"></script>
	 
 

</head>

<body data-id="38" data-roleType="${usertype}" data-userTypeID="${usertypeId}" data-userID="${userid}" data-selected-roleType="${selectedUserTypeId}" data-stolenselected-roleType="${stolenselectedUserTypeId}" 
data-grievanceTxnId="${grievanceTxnId}" data-grievanceId="${grievanceId}"
 data-grievanceStatus="${grievanceStatus}" session-valueTxnID="${not empty param.txnID ? param.txnID : 'null'}">


	<!-- START CONTENT -->
	<!-- START CONTENT -->
	<section id="content">
		<div id="initialloader"></div>
		<!--start container-->
		<div class="container">
			<div class="section">
				<div class="row">
					<div class="col s12 m12 l12">
						<div class="row card-panel">
							<div class="container-fluid pageHeader" id="pageHeader">

								<a href="" class="boton right" id="btnLink" hidden="hidden"></a>
							</div>
							<form action="${context}/pendingTacList"
								method="post">
								<div class="col s12 m12 l12" id="pendingTacTableDiv"
									style="padding-bottom: 5px; background-color: #e2edef52;">
									<div id="filterBtnDiv">
									</div>
								</div>
							</form>
							<table id="pendingTACLibraryTable"
								class="responsive-table striped display"></table>

						</div>

					</div>
				</div>
				<div id="footerBtn"></div>
			</div>
		</div>
		<!--end container-->
	</section>
	
	
	<div id="DeleteTacConfirmationModal" class="modal">
			<h6 class="modal-header">
				<spring:message code="modal.header.deletePendingTac" />
			</h6>
			<div class="modal-content">



				<form action="" onsubmit="return confirmantiondelete()">
					<div class="row">
						<h6>
							<spring:message code="modal.message.pendingtac.delete" />
							<span id="tacdeleteTxnId"></span>
						</h6>
					</div>
					<div class="row">
						<div class="input-field col s12 m12">
							<textarea id="deleteTacRemark"  
								class="materialize-textarea" 
							oninput="InvalidMsg(this,'input','<spring:message code="validation.200character" />');"
							oninvalid="InvalidMsg(this,'input','<spring:message code="validation.200character" />');" style="min-height: 8rem;" required></textarea>
							<label for="textarea1" class=""><spring:message
									code="input.remarks" /> <span class="star">*</span> </label>
						</div>
					</div>
					<input type="text" id="deleteTacId" hidden>
					<div class="row">
						<div class="input-field col s12 center">
							<button type="submit" class="btn" type="submit">
								<spring:message code="modal.yes" />
							</button>
							<button class="modal-close btn" type="button"
								style="margin-left: 10px;">
								<spring:message code="modal.no" />
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		
		
		<div id="closeDeleteModal" class="modal">
			<h6 class="modal-header">
				<spring:message code="modal.header.deletePendingTac"/>
			</h6>
			<div class="modal-content">


				<div class="row">

					<h6 id="tacModalText">
						<spring:message code="modal.message.PendingtacDeleted" />
					</h6>
				</div>
				<div class="row">
					<div class="input-field col s12 center">
						<a href="" class="modal-close btn" style="margin-left: 10px;"><spring:message
								code="modal.ok" /></a>
					</div>
				</div>
			</div>
		</div>	
	

	<!--materialize js-->
	<script type="text/javascript"
		src="${context}/resources/js/materialize.js"></script>
<script
		src="${context}/resources/custom_js/bootstrap.min.js"></script>
	
<script type="text/javascript"
	src="${context}/resources/js/plugins/data-tables/js/jquery.dataTables.min.js"></script>



<script type="text/javascript" src="${context}/resources/js/plugins.js"></script>

	<script type="text/javascript"
		src="${context}/resources/js/plugins/perfect-scrollbar/perfect-scrollbar.min.js"></script>
	<script type="text/javascript"
		src="${context}/resources/js/countries.js"></script>
		<!-- i18n library -->
	<script type="text/javascript"
		src="${context}/resources/project_js/CLDRPluralRuleParser.js"></script>
	<script type="text/javascript"
		src="${context}/resources/i18n_library/i18n.js"></script>
	<script type="text/javascript"
		src="${context}/resources/i18n_library/messagestore.js"></script>

	<script type="text/javascript"
		src="${context}/resources/i18n_library/fallbacks.js"></script>

	<script type="text/javascript"
		src="${context}/resources/i18n_library/language.js"></script>

	<script type="text/javascript"
		src="${context}/resources/i18n_library/parser.js"></script>


	<script type="text/javascript"
		src="${context}/resources/i18n_library/emitter.js"></script>


	<script type="text/javascript"
		src="${context}/resources/i18n_library/bidi.js"></script>

	<script type="text/javascript"
		src="${context}/resources/i18n_library/history.js"></script>

	<script type="text/javascript"
		src="${context}/resources/i18n_library/min.js"></script>
		<script type="text/javascript" src="${context}/resources/project_js/globalVariables.js?version=<%= (int) (Math.random() * 10) %>"></script>
	<script type="text/javascript" src="${context}/resources/project_js/pendingTACList.js?version=<%= (int) (Math.random() * 10) %>"></script>
		<script type="text/javascript"
		src="${context}/resources/project_js/dragableModal.js?version=<%= (int) (Math.random() * 10) %>"></script>	
			<script type="text/javascript"
		src="${context}/resources/project_js/enterKey.js?version=<%= (int) (Math.random() * 10) %>"></script>
<script type="text/javascript"
		src="${context}/resources/project_js/profileInfoTab.js?version=<%= (int) (Math.random() * 10) %>" async></script>
		<script type="text/javascript"
		src="${context}/resources/project_js/_dateFunction.js?version=<%= (int) (Math.random() * 10) %>" async></script>
		
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