
<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setDateHeader("Expires", 0);
	response.setHeader("Pragma", "no-cache");
	/*  session.setMaxInactiveInterval(200); //200 secs
	 session.setAttribute("usertype", null); */
	if (session.getAttribute("usertype") != null) {
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
<title>Add</title>

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
	href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>

<script src="http://malsup.github.io/jquery.blockUI.js"></script>


<style>
.row {
	margin-bottom: 0;
	margin-top: 0;
}

/* input[type=text] {
      height: 35px; 
      margin: 0 0 5px 0;
    } */
input




[
type
=
text
]




:not


 


(
.browser-default


 


)
{
font-size




:


 


13
px




;
/* height: 30px; */
}
input[type=date] {
	font-size: 0.8rem;
}

textarea.materialize-textarea {
	padding: 0;
	padding-left: 10px;
}

.btn-flat {
	height: auto;
}

#starColor {
	color: red;
}

section {
	margin-top: 10px;
}
</style>

</head>
<body data-id="3" 
data-roleType="${usertype}"
	data-userTypeID="${usertypeId}" data-userID="${userid}"
	data-selected-roleType="${selectedUserTypeId}"
	data-stolenselected-roleType="${stolenselectedUserTypeId}"
	data-selected-consignmentTxnId="${consignmentTxnId}"
	data-selected-consignmentStatus="${consignmentStatus}"
	session-value="en"
	session-valueTxnID="${not empty param.txnID ? param.txnID : 'null'}"
	data-username="${username}">
	<!-- START MAIN -->

	<!-- START WRAPPER -->
	<div class="wrapper">

		<!-- START CONTENT -->
		<section id="content">
			<!--start container-->
			<div class="container">
				<div class="section">
					<div class="row">
						<div class="col s12 m12 l12">
							<div class="row card-panel">
								<form action="" onsubmit="return save()" method="POST"
									enctype="multipart/form-data" id="register">
									<div class="container-fluid pageHeader">
										<p class="PageHeading">
											<spring:message code="button.add" />
										</p>

									</div>




									<div class="row myRow">
										<div class=" col s12 m6">
											<p style="margin: 0; font-size: 12px;">
												<spring:message code="table.ruleName" />
												<span class="star">*</span>
											</p>
											<select id="Rule" name="rule"
												class="browser-default" class="mySelect"
												onchange="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
												oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
												style="padding-left: 0;" required></select>
										</div>


										<div class=" col s12 m6">
											<p style="margin: 0; font-size: 12px;">
												<spring:message code="table.featureName" />
												<span class="star">*</span>
											</p>
											<select id="Feature" name="organisationcountry"
												class="browser-default" class="mySelect"
												onchange="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
												oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
												style="padding-left: 0;" required></select>
										</div>
									</div>

									<div class="row myRow">
										<div class=" col s12 m6">
											<p style="margin: 0; font-size: 12px;">
												<spring:message code="table.userType" />
												<span class="star">*</span>
											</p>
											<select id="User" name="organisationcountry"
												class="browser-default" class="mySelect"
												onchange="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
												oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
												style="padding-left: 0;" required></select>
										</div>

										<div class="input-field col s12 m6">
											<input type="text" name="order" id="order"
												pattern="[0-9]{0,7}" maxlength="7"
												oninput="InvalidMsg(this,'input','<spring:message code="validation.7character" />');"
												oninvalid="InvalidMsg(this,'input','<spring:message code="validation.7character" />');"
												required /> <label for="quantity" class="center-align"><spring:message
													code="table.order" /> <span class="star">*</span></label>
										</div>

									</div>



									<div class="row myRow">
										<div class=" col s12 m6">
											<p style="margin: 0; font-size: 12px;">
												<spring:message code="table.gracePeriod" />
												<span class="star">*</span>
											</p>
											<select id="GracePeriod" name="organisationcountry"
												class="browser-default" class="mySelect"
												onchange="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
												oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
												style="padding-left: 0;" required></select>
										</div>


										<div class=" col s12 m6">
											<p style="margin: 0; font-size: 12px;">
												<spring:message code="table.postGracePeriod" />
												<span class="star">*</span>
											</p>
											<select id="PostGracePeriod" name="organisationcountry"
												class="browser-default" class="mySelect"
												onchange="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
												oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
												style="padding-left: 0;" required></select>
										</div>
									</div>



									<div class="row myRow">
										<div class=" col s12 m6">
											<p style="margin: 0; font-size: 12px;">
												<spring:message code="table.moveToGracePeriod" />
												<span class="star">*</span>
											</p>
											<select id="MoveToGracePeriod" name="organisationcountry"
												class="browser-default" class="mySelect"
												onchange="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
												oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
												style="padding-left: 0;" required></select>
										</div>


										<div class=" col s12 m6">
											<p style="margin: 0; font-size: 12px;">
												<spring:message code="table.moveToPostGracePeriod" />
												<span class="star">*</span>
											</p>
											<select id="MoveToPostGracePeriod" name="organisationcountry"
												class="browser-default" class="mySelect"
												onchange="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
												oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
												style="padding-left: 0;" required></select>
										</div>
									</div>


<div class="row myRow">
						<div class=" col s12 m6">
							<p style="margin: 0; font-size: 12px;">
								<spring:message code="table.expectedOutput" />
								<span class="star">*</span>
							</p>
							<select id="output" name="output"
								class="browser-default" class="mySelect"
								onchange="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
								oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
								style="padding-left: 0;" required>
								<option value="Y"> Yes</option>
									<option value="N"> No</option>
								</select>
						</div>
	</div>



									<div class="row">
										<div class="input-field col s12 center">

											<button class=" btn" id="consignmentSubbmitButton"
												type="submit">
												<spring:message code="button.submit" />
											</button>

											<a href="#cancelMessage" class="btn modal-trigger"
												type="cancel" style="margin-left: 10px;"><spring:message
													code="button.cancel" /></a>
													
										</div>

									</div>
								</form>
							</div>

						</div>
					</div>

				</div>
			</div>
		</section>
	</div>


														<div id="cancelMessage" class="modal">
		<h6 class="modal-header"> <spring:message code="modal.cancelrequest" /></h6>
		<div class="modal-content">
			<div class="row">
				<h6><spring:message code="modal.message" /></h6>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<div class="input-field col s12 center">
						<a href="${context}/ruleFeatureMav?FeatureId=30" class="btn"><spring:message code="modal.yes" /></a>
						<button class="modal-close waves-effect waves-light btn"
							style="margin-left: 10px;"><spring:message code="modal.no" /></button>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div id="successModal" class="modal">
     <h6 class="modal-header" style="margin:0px;"><spring:message code="button.add" /></h6>
        <div class="modal-content">
            
            <div class="row">
                <h6 id="updateFieldMessage">Successfully Added New Rule FeatureMapping.</h6>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <a class="modal-close btn" href="${context}/ruleFeatureMav?FeatureId=30"><spring:message code="modal.ok" /></a>
                </div>
            </div>
        </div>
    </div>
    
	<div id="cancelMessage" class="modal">
		<h6 class="modal-header">
			<spring:message code="modal.cancelrequest" />
		</h6>
		<div class="modal-content">
			<div class="row">
				<h6>
					<spring:message code="modal.message" />
				</h6>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<div class="input-field col s12 center">
						<a href="${context}/Consignment/viewConsignment" class="btn"><spring:message
								code="modal.yes" /></a>
						<button class="modal-close waves-effect waves-light btn"
							style="margin-left: 10px;">
							<spring:message code="modal.no" />
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>



	<!--end container-->



	<!-- END MAIN -->


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
	<script type="text/javascript"
		src="${context}/resources/project_js/htmlValidationi18n.js"></script>
	<script type="text/javascript"
		src="${context}/resources/project_js/newRule.js"></script>
	<script type="text/javascript"
		src="${context}/resources/project_js/validationMsg.js"></script>
	<script type="text/javascript"
		src="${context}/resources/project_js/_dateFunction.js" async></script>
	<script type="text/javascript"
		src="${context}/resources/project_js/globalVariables.js"></script>
	<script type="text/javascript"
		src="${context}/resources/project_js/profileInfoTab.js" async></script>
</body>
</html>

<%
	} else {
		/*  request.setAttribute("msg", "  *Please login first");
		request.getRequestDispatcher("./index.jsp").forward(request, response); */
%>
<script language="JavaScript">
	sessionStorage.setItem("loginMsg", "*Session has been expired");
	window.top.location.href = "./login";
</script>
<%
	}
%>