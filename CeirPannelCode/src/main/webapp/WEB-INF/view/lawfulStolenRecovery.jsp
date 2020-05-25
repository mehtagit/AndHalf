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
	 System.out.println("accessTime========"+(accessTime));
	 System.out.println("timeout========"+timeout);
	 long dfd= accessTime +timeout;
	 System.out.println("currentTime========"+currentTime);
	 if( currentTime< dfd){
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
	href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
  <!------------------------------------------- Dragable Model---------------------------------->
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script> 

<style type="text/css">
.dataTables_scrollBody {
    width: 100%;
    max-height: 400px !important;

   height: auto !important;


}
.dataTables_scroll {
    margin-top: 2px;
}
button.modal-action.modal-close.waves-effect.waves-green.btn-flat.right {
   height: 36px;
	 font-size: 31px;
}
.header-fixed-style{
width: inherit;
z-index: 1003;
position: fixed;
}

</style>
</head>

<body data-id="5" data-roleType="${usertype}" data-userTypeID="${usertypeId}"
	data-userID="${userid}" data-operatorTypeId="${operatorTypeId}"
	data-selected-roleType="${stolenselectedUserTypeId}"
	data-stolenselected-roleType="${stolenselectedUserTypeId}"	data-requestType="${requestType}" >


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

								<a class="boton right" id="btnLink"></a>
							</div>
							<form action="${context}/stakeholder/record" method="post">
								<div class="col s12 m12 l12" id="consignmentTableDIv"
									style="padding-bottom: 5px; background-color: #e2edef52;">
									<div id="filterBtnDiv">
										<!-- 							<div class='col s12 m2 l2'><button type='submit' class='btn primary botton' id='submitFilter'></button></div>
		 -->
									</div>
								</div>
							</form>
							<table id="stolenLibraryTable"
								class="responsive-table striped display"></table>

						</div>

					</div>
				</div>
				<div id="footerBtn"></div>
			</div>
		</div>
		<!--end container-->
	</section>

	<div id="DeleteConsignment" class="modal">
		<h6 class="modal-header"><spring:message code="modal.header.delete" /></h6>  
		<form action="" onsubmit=" return confirmantiondelete()" method="POST">
		<div class="modal-content">
	<div class="row">
				<h6><spring:message code="modal.withdraw.messageforStolen" /> ( <span id="transID"></span>)
				</h6>
				<span id="setStolenRecoveyRowId" style="display: none;"></span>
				<div class="input-field col s12 m12">
					<textarea id="textarea1" required="required" maxlength="200"  class="materialize-textarea"></textarea>
					<label for="textarea1"><spring:message code="input.remarks" /><span class="star">*</span></label>
				</div>
			</div>
			<input type="text" id="popupTransactionId" maxlength="15" hidden />
			<div class="row">
				<div class="input-field col s12 center">
					<div class="input-field col s12 center">
						<button class="btn" type="submit"><spring:message code="modal.ok" /></button>
						<button type="button" class="modal-close btn" onclick="closeUpdateModal()"
							style="margin-left: 10px;"><spring:message code="modal.no" /></button>
					</div>
				</div>
			</div>
		</div>
		</form>
	</div>


	<div id="confirmDeleteConsignment" class="modal">
		<h6 class="modal-header"><spring:message code="modal.header.deleteStolen" /></h6>
		<div class="modal-content">

			
			
			<!-- <h4 class="header2 pb-2">User Info</h4> -->

			<div class="row">
				<h6 id=consignmentText></h6>
			</div>

			<div class="row">
				<div class="input-field col s12 cent\er">
					<div class="input-field col s12 center">
						<a href="" class="btn"><spring:message code="modal.ok" /></a>
					</div>
				</div>
			</div>
		</div>
	</div>
                         <div id="chooseStolenOption" class="modal">
                         <button type="button"
			class=" modal-action modal-close  btn-flat right"
			data-dismiss="modal">&times;</button>
        <div class="row" style="padding-bottom: 20px;" id="stolenRecoveryDivPage">
        <h6 class="modal-header"><spring:message code="modal.Stolen/Recovery" /></h6>
            <div class="col s12 m12 modal-content">
                
                <div class="row">
                    <form action="#">
                        <h5 class="center">
                            <label>
                                <input name="group1" type="radio" onclick="openStolenRecoveryPage('stolen','default')" />
                                <span class="checkboxFont"><spring:message code="input.Stolen" /></span>
                            </label>

                            <label>
                                <input name="group1" type="radio" onclick="openStolenRecoveryPage('recovery','default')" />
                                <span class="checkboxFont"><spring:message code="input.Recovery" /></span>
                            </label>
                        </h5>
                    </form>
                </div>
                </div></div></div>


<div id="fileFormateModal" class="modal">
		<h6 class="modal-header"><spring:message code="fileValidationModalHeader" /></h6>
		<div class="modal-content">
			<div class="row">
				<h6 id="fileErrormessage"><spring:message code="fileValidationName" /><br> <br> <spring:message code="fileValidationFormate" /> <br><br> <spring:message code="fileValidationSize" /> </h6>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<div class="input-field col s12 center">
						<button class="modal-close waves-effect waves-light btn" onclick="clearFileName()"
							style="margin-left: 10px;"><spring:message code="modal.ok" /></button>
					</div>
				</div>
			</div>
		</div>
	</div>

<div id="approveInformation" class="modal" style="width: 40%; z-index: 1003; opacity: 1; transform: scaleX(1); top: 10%;">
        <h6 class="modal-header"><spring:message code="modal.header.approve" /></h6>
        <div class="modal-content">
            <div class="row">
                <form action="">
                   
                    <h6><spring:message code="modal.approveRequest" /><span id="blockApproveTxnId"></span>?</h6>
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
                     <a class="btn modal-close" href="./stolenRecovery?FeatureId=5"><spring:message code="modal.ok" /></a>
                   
                </div>
            </div>
        </div>
    </div>
    
	    <!--------------------------------------------------- Reject Model--------------------------------------------->
    
        <div id="rejectInformation" class="modal">
           <h6 class="modal-header"><spring:message code="modal.header.reject" /></h6>
            <div class="modal-content">
                <form action="" onsubmit=" return rejectUser()" method="POST">
            <div class="row">
             <h6><spring:message code="modal.rejectRequest" /><span id="rejectTxnId"></span> ?</h6>
            
                
                    <div class="input-field" style="margin-top: 30px;">
                        <textarea id="Reason" class="materialize-textarea" 
                        oninput="InvalidMsg(this,'input','<spring:message code="validation.10000characters" />');"
						oninvalid="InvalidMsg(this,'input','<spring:message code="validation.10000characters" />');"
                        required="required"></textarea>
                        <label for="textarea1" style="margin-left: -10px;"><spring:message code="lable.reason" /><span class="star">*</span></label>
                    </div>
                   
                    
            
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <button  class="btn" type="submit"><spring:message code="modal.yes" /></button>
                    <button class="btn modal-close" type="button" style="margin-left: 10px;"><spring:message code="modal.no" /></button>
                </div>
            </div>
                </form>
        </div>
    </div>
  
    	<div id="confirmRejectInformation" class="modal">
         <h6 class="modal-header"><spring:message code="registration.reject" /></h6>
          <div class="modal-content">
            <div class="row">
                <form action="">
                  
                    <h6><spring:message code="modal.deviceRejected" /></h6>
                </form>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <a class="btn modal-close" href="./stolenRecovery?FeatureId=5"><spring:message code="modal.ok" /></a>
                </div>
            </div>
        </div>
    </div>

<div id="tableOnModal" class="modal">
<div class="header-fixed header-fixed-style">
		<button type="button"
			class=" modal-action modal-close waves-effect waves-green btn-flat right"
			data-dismiss="modal">&times;</button>
		<h6 class="modal-header"><spring:message code="modal.header.viewHistory" /></h6>
		</div>
		<div class="scrollDivHeight"></div>
		<div class="modal-content modal-content-style">

			<div class="row">
				<table class="responsive-table striped display"
					id="data-table-history" cellspacing="0">
				</table>
			</div>
		</div>
	</div>
<script type="text/javascript"
		src="${context}/resources/js/materialize.js"></script>


	<script type="text/javascript"
		src="${context}/resources/js/plugins/data-tables/js/jquery.dataTables.js"></script>
	<script type="text/javascript"
		src="${context}/resources/js/plugins/data-tables/js/jquery.dataTables.min.js"></script>

	

	<!--plugins.js - Some Specific JS codes for Plugin Settings-->
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

	<%--   <script type="text/javascript" src="${context}/resources/js/materialize-plugins/date_picker/picker.date.js"></script>
    <script type="text/javascript" src="${context}/resources/js/materialize-plugins/date_picker/picker.js"></script> --%>
	<!--custom-script.js - Add your own theme custom JS-->
	<script type="text/javascript" src="${context}/resources/js/plugins.js"></script>
	<!--prism
    <script type="text/javascript" src="${context}/resources/resources/js/prism/prism.js"></script>-->
	<!--scrollbar-->
	<script type="text/javascript"
		src="${context}/resources/js/plugins/perfect-scrollbar/perfect-scrollbar.min.js"></script>
	<!-- chartist -->
	<%-- <script type="text/javascript" src="${context}/resources/js/plugins/chartist-js/chartist.min.js"></script> --%>
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
		src="${context}/resources/project_js/_dateFunction.js" async></script>
<script type="text/javascript"
		src="${context}/resources/project_js/lawfulStolenRecovery.js"></script>
<script type="text/javascript"
		src="${context}/resources/project_js/dragableModal.js"></script>
			<script type="text/javascript"
		src="${context}/resources/project_js/validationMsg.js"></script>
					<script type="text/javascript"
		src="${context}/resources/project_js/profileInfoTab.js" async></script>			
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