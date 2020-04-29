<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setDateHeader("Expires", 0);
	response.setHeader("Pragma", "no-cache");
	/*  session.setMaxInactiveInterval(200); //200 secs
	 session.setAttribute("usertype", null); */
	if (session.getAttribute("usertype") != null) {
%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<title>Dashboard</title>
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
	
 <link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
   <!------------------------------------------- Dragable Model---------------------------------->
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script> 
	



</head>
<%-- <body data-roleType="${usertype}" data-userID="${userid}" data-selected-roleType="${selectedUserTypeId}"> --%>
<body data-id="8" data-roleType="${usertype}" data-userTypeID="${usertypeId}" data-userID="${userid}" 
data-selected-roleType="${selectedUserTypeId}" 
data-stolenselected-roleType="${stolenselectedUserTypeId}" 
session-valueTxnID="${not empty param.txnID ? param.txnID : 'null'}"
data-session-id="${not empty param.id ? param.id : 'null'}"
data-session-roles="${not empty param.roles ? param.roles : 'null'}"
data-session-type="${not empty param.type ? param.type : 'null'}">


	<!-- START CONTENT -->
	<!-- START CONTENT -->
	<section id="content">
		<!--start container-->
			<div id="initialloader"></div>
		<div class="container">
			<div class="section">
				<div class="row">
					<div class="col s12 m12 l12">
						<div class="row card-panel">
							<div class="container-fluid pageHeader" id="pageHeader">

								<a href="" class="boton right" id="btnLink"></a>
							</div>
							<form action="${context}/registrationRequest"
								method="post">
								<div class="col s12 m12 l12" id="registrationTableDiv"
									style="padding-bottom: 5px; background-color: #e2edef52;">
									<div id="filterBtnDiv">
										<!-- 							<div class='col s12 m2 l2'><button type='submit' class='btn primary botton' id='submitFilter'></button></div>
		 -->
									</div>
								</div>
							</form>
							<table id="registrationLibraryTable"
								class="responsive-table striped display"></table>

						</div>

					</div>
				</div>
				<div id="footerBtn"></div>
			</div>
		</div>
		<!--end container-->
	</section>

   
	<div id="approveInformation" class="modal">
           <h6 class="modal-header"><spring:message code="modal.header.approve" /></h6>
           <div class="modal-content">
            <div class="row">
                <form action="">
                 	<h6> <spring:message code="registration.thetransactionid" /><span id="registrationTxnId"> </span> <spring:message code="registration.pendingforapproval" /></h6>
                    <p><spring:message code="registration.dorequest" /></p>
                    <input type ="text" id="sessionUserName" hidden="hidden">
                    	
                </form>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <a onclick="aprroveUser()" class="btn modal-trigger"><spring:message code="modal.yes" /></a>
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
            
                    <h6><spring:message code="registration.approveEmailregister" />.</h6>
                   
                </form>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                     <a class="btn modal-close" href="./registrationRequest"><spring:message code="modal.ok" /></a>
                   
                </div>
            </div>
        </div>
    </div>
	<div id="rejectInformation" class="modal">
           <h6 class="modal-header"><spring:message code="modal.header.reject" /></h6>
            <div class="modal-content">
            <div class="row">
                <form action="">
                
                    <div class="input-field" style="margin-top: 30px;">
                        <textarea id="Reason" class="materialize-textarea"></textarea>
                        <label for="textarea1" style="margin-left: -10px;"><spring:message code="lable.reason" /></label>
                    </div>
                    <h6><spring:message code="registration.doreject" /></h6>
                    <input type ="text" id="rejectUserName" hidden="hidden">
                </form>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <a onclick="rejectUser()" class="btn modal-close modal-trigger"><spring:message code="modal.yes" /></a>
                    <button class="btn modal-close" style="margin-left: 10px;"><spring:message code="modal.no" /></button>
                </div>
            </div>
        </div>
    </div>
	<div id="confirmRejectInformation" class="modal">
         <h6 class="modal-header"><spring:message code="registration.reject" /></h6>
          <div class="modal-content">
            <div class="row">
                <form action="">
                  
                    <h6><spring:message code="registration.approveEmailregister" /></h6>
                </form>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <a class="btn modal-close" href="./registrationRequest"><spring:message code="modal.ok" /></a>
                </div>
            </div>
        </div>
    </div>
    
    		<div id="statusChangemodal" class="modal">
               <form action="" onsubmit="return chanegeUserStatus()" method="POST"
								enctype="multipart/form-data" id="">
								  <div class="row" id="singleInput">
								  <h6 class="modal-header "> <spring:message code="registration.changeUserStatus" /></h6>
                                <div class="col s12 m12 l12">
                   	   
                                   <div class="row"  style="margin-top: 10px">
                                        	<div class="col s12 m6 l6" style="margin-bottom: 5px;">
											<label for="userStatus"><spring:message
													code="select.changeUserStatus" /> <span class="star">*</span></label>
											<select id="userStatus" class="browser-default"
												onchange="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
												oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
												required>
												<option value="" disabled selected><spring:message
														code="select.selectUserStatus" />
												</option>
											</select>
										</div> 
                                       		
                                       
										
									<div class="input-field col s12 m6" style="margin-top: 22px;">
										<input type="text" name="refererence" id="refererenceId"
							placeholder="" oninput="InvalidMsg(this,'input','<spring:message code="validation.50character" />');" oninvalid="InvalidMsg(this,'input','<spring:message code="validation.50character" />');"
							maxlength="50"/> <label for="refererenceId"
							class="center-align"><spring:message
								code="input.refId" /> </label>
								</div>		
                                            
                   		  
								<div class="input-field col s12 m12">
							<textarea id="changeStatusRemark" style="min-height: 8rem;" 
								class="materialize-textarea" 
							oninput="InvalidMsg(this,'input','<spring:message code="validation.200character" />');"
							oninvalid="InvalidMsg(this,'input','<spring:message code="validation.200character" />');" required></textarea>
							<label for="textarea1" class=""><spring:message
									code="input.remarks" /> <span class="star">*</span> </label>
								</div>
						
                                   
                                        </div>
                                 		 <input type ="text" id="statusUserName" hidden="hidden" >
                                        <div class="row">
                                            <div class="input-field col s12 center" style="padding: 20px 0;">
                                                <!-- <a href="#submitIMEI" class="btn modal-trigger">Submit</a>  -->
                                                 <button class=" btn" type="submit"><spring:message code="button.submit" /></button>
                                               	<button type="button" onclick = "resetButtons()" class="btn modal-close" style="margin-left: 10px;" title=" "><spring:message code="button.cancel" /></button>
                                            </div>

                                        </div>
                                      
									
                                    </div>
                                   </div></form>
                    </div>           
    
    <div id="confirmUserStatus" class="modal">
         <h6 class="modal-header"><spring:message code="registration.changeUserStatus" /></h6>
          <div class="modal-content">
            <div class="row">
                <form action="">
                  
                    <h6><spring:message code="registration.changedStatus" /></h6>
                </form>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <a class="btn modal-close" href="./registrationRequest"><spring:message code="modal.ok" /></a>
                </div>
            </div>
        </div>
    </div>
    
    	<div id="statusRoleChange" class="modal">
           <h6 class="modal-header"><spring:message code="table.RegistrationRequest" /></h6>
           <div class="modal-content">
            <div class="row">
              
                   <h5 class="center">
						<label> <input name="group1" type="radio" value="0"
							onclick="userChangeStatus('status');"/>
							<span class="checkboxFont"> <spring:message code="registration.changeUserStatus" /></span></label>
									
						 <label> <input name="group2" type="radio" value="1"
							onclick="userChangeStatus('roleType')"/>
							<span class="checkboxFont"> <spring:message code="changeRoleType" /></span>
						</label> 
					</h5>
							  
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <button class="btn modal-close" onclick = "resetButtons()"style="margin-left: 10px;"><spring:message code="button.cancel" /></button>
                </div>
            </div>
        </div>
    </div>
    
    
    <div id="roleTypeChangemodal" class="modal">
               <form action="" onsubmit="return chanegeUserStatus()" method="POST"
								enctype="multipart/form-data" id="">
								  <div class="row" id="singleInput">
								  <h6 class="modal-header "> <spring:message code="changeRoleType" /></h6>
                                <div class="col s12 m12 l12">
                   	   
                                   <div class="row"  style="margin-top: 10px">
                                        	<div class="col s12 m6 l6" style="margin-bottom: 5px;">
											<label for="userStatus"><spring:message
													code="changeRoleType" /> <span class="star">*</span></label>
											<select id="userRoleType" class="browser-default"
												onchange="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
												oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
												required>
												<option value="" disabled selected><spring:message
														code="select.changeUserRole" />
												</option>
											</select>
										</div> 
                                       		
                                       
										
									<div class="input-field col s12 m6" style="margin-top: 22px;">
										<input type="text" name="refererence" id="refererenceId"
							placeholder="" oninput="InvalidMsg(this,'input','<spring:message code="validation.50character" />');" oninvalid="InvalidMsg(this,'input','<spring:message code="validation.50character" />');"
							maxlength="50"/> <label for="refererenceId"
							class="center-align"><spring:message
								code="input.refId" /> </label>
								</div>		
                                            
                   		  
								<div class="input-field col s12 m12">
							<textarea id="changeStatusRemark" style="min-height: 8rem;" 
								class="materialize-textarea" 
							oninput="InvalidMsg(this,'input','<spring:message code="validation.200character" />');"
							oninvalid="InvalidMsg(this,'input','<spring:message code="validation.200character" />');" required></textarea>
							<label for="textarea1" class=""><spring:message
									code="input.remarks" /> <span class="star">*</span> </label>
								</div>
						
                                   
                                        </div>
                                 		 <input type ="text" id="statusUserName" hidden="hidden" >
                                        <div class="row">
                                            <div class="input-field col s12 center" style="padding: 20px 0;">
                                                <!-- <a href="#submitIMEI" class="btn modal-trigger">Submit</a>  -->
                                                 <button class=" btn" type="submit"><spring:message code="button.submit" /></button>
                                               	<button type="button" onclick = "resetButtons()" class="btn modal-close" style="margin-left: 10px;" title=" "><spring:message code="button.cancel" /></button>
                                            </div>

                                        </div>
                                      
									
                                    </div>
                                   </div></form>
                    </div>           

    
	
	<!--materialize js-->
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
		
	<script type="text/javascript" src="${context}/resources/project_js/globalVariables.js"></script>
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

<script type="text/javascript"
		src="${context}/resources/project_js/backbutton.js"></script>
	<script type="text/javascript"
		src="${context}/resources/project_js/dragableModal.js"></script>	
			<script type="text/javascript"
		src="${context}/resources/project_js/enterKey.js"></script>
	<script type="text/javascript"
		src="${context}/resources/project_js/AdminRegistrationRequest.js"></script>
	<script type="text/javascript"
		src="${context}/resources/project_js/_dateFunction.js" async></script>	
		<script type="text/javascript"
		src="${context}/resources/project_js/profileInfoTab.js" async></script>
		<script type="text/javascript"
		src="${context}/resources/project_js/validationMsg.js"></script>
			
		
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