<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setDateHeader("Expires", 0);
	response.setHeader("Pragma", "no-cache");
	/* session.setMaxInactiveInterval(200); //200 secs
	 session.setAttribute("usertype", null);   */
	if (session.getAttribute("usertype") != null) {
%>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<title>Lawful</title>

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
	href="${context}/resources/project_css/stolenRecovery.css">
<link rel="stylesheet"
	href="${context}/resources/project_css/iconStates.css">
<link rel="stylesheet"
	href="${context}/resources/project_css/intlTelInput.css">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>

<style>
.checkboxFont {
	color: #444;
	font-size: 16px;
	margin-right: 10px;
}

.section {
	padding-top: 0.5rem;
}

.welcomeMsg {
	padding-bottom: 50px !important;
	line-height: 1.5 !important;
	text-align: center;
}

.file-label {
	font-size: 0.9rem;
}

.contact-label {
	margin-top: -17px;
	margin-bottom: 0;
	font-size: 0.8rem;
}
</style>


</head>
<body data-id="12" data-roleType="${usertype}" data-userTypeID="${usertypeId}"
	data-userID="${userid}" data-selected-roleType="${selectedUserTypeId}"
	data-stolenselected-roleType="${stolenselectedUserTypeId}"
	data-selected-consignmentTxnId="${consignmentTxnIdv}"
	data-selected-consignmentStatus="${consignmentStatus}" 
	session-value="${not empty param.NID ? param.NID : 'null'}"
	session-valueTxnID="${not empty param.txnID ? param.txnID : 'null'}">
	
	
	
<div id="approveInformation" class="modal" style="width: 40%; z-index: 1003; opacity: 1; transform: scaleX(1); top: 10%;">
        <h6 class="modal-header"><spring:message code="modal.header.approve" /></h6>
        <div class="modal-content">
            <div class="row">
                <form action="">
                   
                    <h6> <spring:message code="modal.approveRequest" /><span id="approveTxnId"></span> ?</h6>
                </form>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <a onclick="aprroveDevice()" class="btn modal-trigger"><spring:message code="modal.yes" /></a>
                    <button class="btn modal-close" style="margin-left: 10px;"><spring:message code="modal.no" /></button>
                      <span id="userId" hidden></span>
                       <span id="adminCurrentStatus" hidden></span>
                </div>
            </div>
        </div>
    </div>	
    
	<div id="confirmApproveInformation" class="modal" style="width: 40%; z-index: 1005; opacity: 1; transform: scaleX(1); top: 10%;">
        <h6 class="modal-header"><spring:message code="modal.header.approve" /></h6>
        <div class="modal-content">
            <div class="row">
                <form action="">
                    
                    <h6><spring:message code="modal.deviceApproved" /></h6>
                   
                </form>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                     <a class="btn modal-close" href="./uploadPaidStatus"><spring:message code="modal.ok" /></a>
                   
                </div>
            </div>
        </div>
    </div>
    
    <div id="rejectInformation" class="modal">
           <h6 class="modal-header"><spring:message code="modal.header.reject" /></h6>
            <div class="modal-content">
             <form action="" onsubmit=" return rejectUser()" method="POST">
            <div class="row">
             <h6><spring:message code="modal.rejectRequest" /><span id="disapproveTxnId"></span> ?</h6>
               
                
                    <div class="input-field" style="margin-top: 30px;">
                        <textarea id="Reason" class="materialize-textarea" name="Reason"
                        oninput="InvalidMsg(this,'input','<spring:message code="validation.10000characters" />');"
						oninvalid="InvalidMsg(this,'input','<spring:message code="validation.10000characters" />');"
                         required="required"></textarea>
                        <label for="Reason" style="margin-left: -10px;"><spring:message code="lable.reason" /><span
												class="star">*</span></label>
                    </div>
                   
                    
                
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <button type="submit" class="btn"><spring:message code="modal.yes" /></button>
                    <button class="btn modal-close" type="button"  style="margin-left: 10px;"><spring:message code="modal.no" /></button>
                </div>
            </div>
            </form>
        </div>
    </div>
  
    	<div id="confirmRejectInformation" class="modal">
         <h6 class="modal-header"><spring:message code="modal.header.reject" /></h6>
          <div class="modal-content">
            <div class="row">
                <form action="">
                  
                    <h6><spring:message code="modal.deviceRejected" /></h6>
                </form>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <a class="btn modal-close" href="./uploadPaidStatus"><spring:message code="modal.ok" /></a>
                </div>
            </div>
        </div>
    </div>
	
	</body>
</html>
<%
	}else{
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