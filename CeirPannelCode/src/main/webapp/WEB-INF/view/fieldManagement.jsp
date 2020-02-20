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
<html class="no-js" lang="en" dir="ltr">
<head>
<title>Consignment</title>
<meta http-equiv='cache-control' content='no-cache'>
<meta http-equiv='expires' content='-1'>
<meta http-equiv='pragma' content='no-cache'>
<meta name="fragment" content="!">
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
<script src="//cdn.datatables.net/plug-ins/1.10.20/i18n/Khmer.json"></script>

<!------------------------------------------- Dragable Model---------------------------------->
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>

</head>
<body data-id="23"
	data-roleType="${usertype}" data-userTypeID="${usertypeId}"
	data-userID="${userid}" data-selected-roleType="${selectedUserTypeId}"
	data-stolenselected-roleType="${stolenselectedUserTypeId}"
	data-selected-consignmentTxnId="${consignmentTxnId}"
	data-selected-consignmentStatus="${consignmentStatus}"
	session-value="en"
	session-valueTxnID="${not empty param.txnID ? param.txnID : 'null'}">

	<%-- session-value="${not empty param.NID ? param.NID : 'null'}" --%>

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
							<form action="${context}/fieldManagement"
								method="post">
								<div class="col s12 m12 l12" id="FieldTableDiv"
									style="padding-bottom: 5px; background-color: #e2edef52;">
									<div id="filterBtnDiv"></div>
								</div>
							</form>
							<table id="fieldManagementLibraryTable"
								class="responsive-table striped display"></table>

						</div>

					</div>
				</div>
				<div id="footerBtn"></div>
			</div>
		</div>
		
	
		<!--end container-->
	</section>
	
 	<div id="addTags" class="modal" style="z-index: 1003; display: none; opacity: 1; transform: scaleX(1); top: 10%;">
        <h6 class="modal-header">Add Fields</h6>
        <div class="modal-content">
          	<form action="" onsubmit="return submitTag()" method="post" >
                <div class="row" style="margin-top: 10px;">
					
					<div class="input-field col s12 m6" style="margin-top: 22px;">
                        <!-- <input type="text" id="tag" name="tag" pattern="[A-Za-z]{0,30}" Placeholder=""  maxlength="15" value="" required="required" disabled> -->
                        <select class="browser-default" id="tag" disabled>
                                <option value="" disabled="" selected="">Select field</option>
                          </select>
                        <label for="tag" class="active">Field <span class="star">*</span></label>
                         <input type="text" id="displayName" hidden>
                    </div>
					
					<div class="input-field col s12 m6" style="margin-top: 22px;">
                        <input type="text" id="addValue" name="value" pattern="[0-9]" title="Please enter digits"  maxlength="8" required="required">
                        <label for="addValue" class="">Value <span class="star">*</span></label>
                    </div>

                    <div class="input-field col s12 m6" style="margin-top: 22px;">
                        <input type="text" id="addInterp" name="interp" pattern="[A-Za-z0-9]{0,200}" title="Please enter alphabets and numbers upto 15 characters only" maxlength="15" required="required">
                        <label for="addInterp" class="">Interp <span class="star">*</span></label>
                    </div>

                    <div class="input-field col s12 m6" style="margin-top: 22px;">
                        <input type="text" id="tagId" name="addFieldId" pattern="[A-Za-z0-9]{0,200}" title="Please enter alphabets and numbers upto 15 characters only" maxlength="15" required="required">
                        <label for="addFieldId" class="">Field ID <span class="star">*</span></label>
                    </div>

                    <div class="col s12 m12 center" style="margin-top: 20px;">
                        <button class="btn" type="submit">Submit</button>
                        <a href="#" class="btn modal-close" id="Cancel" style="margin-left: 10px;">Cancel</a>
                    </div>
                </div>
            </form>
        </div>
    </div>
		
		
		<div id="editTags" class="modal" style="z-index: 1003; display: none; opacity: 1; transform: scaleX(1); top: 10%;">
        <h6 class="modal-header">Edit Fields</h6>
        <div class="modal-content">
          	<form action="" onsubmit="return updatedTag()" method="post" >
                <div class="row" style="margin-top: 10px;">
					
					<div class="input-field col s12 m6" style="margin-top: 22px;">
                        <!-- <input type="text" id="tag" name="tag" pattern="[A-Za-z]{0,30}" Placeholder=""  maxlength="15" value="" required="required" disabled> -->
                        <select class="browser-default" id="Edittag" disabled>
                                <option value="" disabled="" selected="">Select field</option>
                          </select>
                        <label for="Edittag" class="active">Field <span class="star">*</span></label>
                    <input type="text" id="id" Disabled> 
                    </div>
					
					<div class="input-field col s12 m6" style="margin-top: 22px;">
                        <input type="text" id="editValue" name="value" pattern="[0-9]" title="Please enter digits"  maxlength="8" required="required">
                        <label for="editValue" class="">Value <span class="star">*</span></label>
                    </div>

                    <div class="input-field col s12 m6" style="margin-top: 22px;">
                        <input type="text" id="editInterp" name="interp" pattern="[A-Za-z0-9]{0,200}" title="Please enter alphabets and numbers upto 15 characters only" maxlength="15" required="required">
                        <label for="editInterp" class="">Interp <span class="star">*</span></label>
                    </div>

                    <div class="input-field col s12 m6" style="margin-top: 22px;">
                        <input type="text" id="tagId" name="editFieldId" pattern="[A-Za-z0-9]{0,200}" title="Please enter alphabets and numbers upto 15 characters only" maxlength="15" required="required">
                        <label for="editFieldId" class="">Field ID <span class="star">*</span></label>
                    </div>

                    <div class="col s12 m12 center" style="margin-top: 20px;">
                        <button class="btn" type="submit">Update</button>
                        <a href="#" class="btn modal-close" id="Cancel" style="margin-left: 10px;">Cancel</a>
                    </div>
                </div>
            </form>
        </div>
    </div>
		
	<div id="confirmField" class="modal">
		<h6 class="modal-header"><spring:message code="modal.header.submitTypeApprove" /></h6>
		<div class="modal-content">
			<div class="row">
				<h6 id="sucessMessage"></h6>
				<input type="text" style="display: none" id="errorCode">
			</div>
			 <div class="row">
				<div class="input-field col s12 center">
                    <a href="./fieldManagement" class="btn">ok</a>
                </div>
			</div> 
		</div>
	</div>
		
	   <!-- --------------------------------------------------------------Delete Field Modal Start --------------------------------------------------------------->


	<div id="DeleteFieldModal" class="modal">
		<h6 class="modal-header"><spring:message code="modal.header.deleteTac" /></h6>
		<div class="modal-content">
		<div class="row">
				<h6><spring:message code="modal.message.tac.widthdraw" /></h6>
			</div> 
			<input type="text" id="deleteFieldId">
			<div class="row">
				<div class="input-field col s12 center">
					<a onclick="confirmantiondelete()"
						class="modal-close modal-trigger btn" type="submit"><spring:message code="modal.yes" /></a>
					<button class="modal-close btn" style="margin-left: 10px;"><spring:message code="modal.no" /></button>
				</div>
			</div>
		</div>
	</div>	
	
	<div id="closeDeleteModal" class="modal">
			<h6 class="modal-header"><spring:message code="modal.header.deleteTac" /></h6>
			<div class="modal-content">
		
			
			<div class="row">

				<h6 id="tacModalText"><spring:message code="modal.message.tacDeleted" /> </h6>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<a href="" class="modal-close btn"
						style="margin-left: 10px;"><spring:message code="modal.close" /></a>
				</div>
			</div>
		</div>
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
		src="${context}/resources/project_js/globalVariables.js"></script>
	<script type="text/javascript"
		src="${context}/resources/project_js/backbutton.js"></script>
	<script type="text/javascript"
		src="${context}/resources/project_js/dragableModal.js"></script>
	<script type="text/javascript"
		src="${context}/resources/project_js/enterKey.js"></script>
	<%-- 		<script type="text/javascript"
		src="${context}/resources/project_js/disable_inspectElement.js"></script> --%>
	<script type="text/javascript"
		src="${context}/resources/project_js/fieldManagement.js"></script>
	<script type="text/javascript"
		src="${context}/resources/project_js/_dateFunction.js" async></script>
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
	sessionStorage.setItem("loginMsg",
			"*Session has been expired");
	window.top.location.href = "../login";
</script>
<%
	}
%>


