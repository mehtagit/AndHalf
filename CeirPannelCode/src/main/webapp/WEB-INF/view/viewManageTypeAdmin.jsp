
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
<title>Manage Type Admin</title>
<metas http-equiv='cache-control' content='no-cache'>
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
	src="${context}/resources/js/plugins/jquery-1.11.2.min.js"></script> <!--   <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css"></script>  
 --> <!-- CORE CSS-->
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
<script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script> <script
	src="http://malsup.github.io/jquery.blockUI.js"></script> <script
	src="//cdn.datatables.net/plug-ins/1.10.20/i18n/Khmer.json"></script> <!------------------------------------------- Dragable Model---------------------------------->
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script> <script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
<script type="text/javascript">
var path="${context}";
</script>
</head>
<body data-id="21" data-roleType="${usertype}"
	data-userTypeID="${usertypeId}" data-userID="${userid}"
	data-selected-roleType="${selectedUserTypeId}"
	data-stolenselected-roleType="${stolenselectedUserTypeId}"
	data-selected-consignmentTxnId="${consignmentTxnId}"
	data-selected-consignmentStatus="${consignmentStatus}"
	session-value="en"
	session-valueTxnID="${not empty param.txnID ? param.txnID : 'null'}">

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

								<a href="" class="boton right" id="btnLink"></a>
							</div>
							<form action="${context}/manageTypeDevices" method="post">
								<div class="col s12 m12 l12" id="typeAprroveTableDiv"
									style="padding-bottom: 5px; background-color: #e2edef52;">
									<div id="filterBtnDiv">
										<!-- 							<div class='col s12 m2 l2'><button type='submit' class='btn primary botton' id='submitFilter'></button></div>
		 -->
									</div>
								</div>
							</form>
							<table id="ImporterAdmintypeAprroveTable"
								class="responsive-table striped display"></table>

						</div>

					</div>
				</div>
				<div id="footerBtn"></div>
			</div>
		</div>

		<div id="viewImporterModal" class="modal" style="overflow-y: hidden;">
			<h6 class="modal-header">
				<spring:message code="input.ViewType" />
			</h6>
			<div class="modal-content">




				<div class="row" style="margin-top: 10px;">
					<div class="input-field col s12 m6 l6">
						<input type="text" id="viewtradmark" name="tradmark"
							placeholder="" disabled=""> <label for="viewtradmark"
							class="active"><spring:message code="input.Trademark" /></label>
					</div>
					
					<div class="input-field col s12 m6 l6">
						<input type="text" id="viewtxnId" name="transactioId"
							placeholder="" disabled=""> <label for="viewtxnId"
							class="active"><spring:message code="table.transactionID" /></label>
					</div>

					<div class="input-field col s12 m6 l6">
						<input type="text" id="viewmodelName" name="modelName"
							placeholder="" disabled="disabled"> <label
							for="viewmodelName" class="active"><spring:message
								code="input.modelName" /> <span class="star"></span></label>
					</div>

					<div class="input-field col s12 m6 l6">
						<input type="text" id="viewModelnumber" name="modelNumber"
							placeholder="" disabled="disabled"> <label
							for="viewModelnumber" class="active"><spring:message
								code="input.modelNumber" /> <span class="star"></span></label>
					</div>

					


					<!-- <div class="input-field col s12 m6 l6">
						<input type="text" id="viewDeviceType" name="deviceType"
							placeholder="" disabled=""> <label
							for="deviceType" class="active">Device Type </label>
					</div> -->


				</div>

				<!-- <div class="row" style="margin-top: 5px;">
					<div class="input-field col s12 m6 l6">
						<input type="text" id="viewdeviceTypeID" name="deviceType"
							placeholder="" disabled=""> <label for="deviceType"
							class="active">Device ID Type</label>
					</div> -->


				<div class="row">

					<div class="input-field col s12 m6 l6">
						<input type="text" id="viewFrequency" name="tac" placeholder=""
							disabled=""> <label for="viewFrequency" class="active"><spring:message
								code="input.frequency" /></label>
					</div>

					<div class="input-field col s12 m6 l6">
						<input type="text" id="viewImportertac" name="tac" placeholder=""
							disabled=""> <label for="tac" class="active"><spring:message
								code="input.TAC" /></label>
					</div>
				</div>
				
				<div class="input-field col s12 m6 l6" style="width: 402px;margin-left: 1px;">
						<input type="text" id="viewManufacturercountry" name="Country" style="padding-left: 8px;"
							placeholder="" disabled=""> <label
							for="viewManufacturercountry" class="active"><spring:message
								code="input.Country" /></label>
				</div>
				<div class="modal-content">
					<div id="live-chat">
						<div class="chat">
							<div class="chat-history">
								<div class="chat-message clearfix" id="chatMsg" style="cursor: pointer;"></div>
								<!-- end chat-message -->


							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row ">
				<div class="center  popup-btn-div" style="margin-top: 10px;">
					<button class="modal-close btn" type="button" id="Cancel"
						style="margin-left: 10px;">
						<spring:message code="modal.close" />
					</button>
				</div>



			</div>
		</div>



		<div id="importereditModal" class="modal">
			<h6 class="modal-header">
				<spring:message code="modal.UpdateDevices" />
			</h6>
			<div class="modal-content">

				<form action="" onsubmit="return updateImporterTypeDevice()"
					method="post" style="margin-top: 30px;">

					<div class="row" style="margin-top: 10px;">
						<div class="input-field col s12 m6 l6">
							<input type="text" id="editTradmark" name="trademarkName"
								pattern="[A-Za-z0-9 \s]{0,160}"
								oninput="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');"
								oninvalid="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');"
								maxlength="30" placeholder="" required /> <label
								for="editTradmark"><spring:message
									code="input.Trademark" /> <span class="star">*</span></label>
						</div>
						
						<div class="input-field col s12 m6 l6">
						<input type="text" id="editImportertransactionid" name="transactioId"
							placeholder="" disabled=""> <label for="editImportertransactionid"
							class="active"><spring:message code="table.transactionID" /></label>
						</div>


						<div class="col s12 m6 l6" style="margin-bottom: 5px;">
							<label for="productName"><spring:message
									code="registration.productname" /> <span class="star">*</span></label>
							<select id="productname" class="browser-default"
								onchange="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
								oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
								required>
								<option value="" disabled selected><spring:message
										code="registration.selectproduct" />
								</option>
							</select>
						</div>


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

						

					</div>

					<div class="row" style="margin-top: 5px;">
						<!-- <div class="col s12 m6 l6">
                                                <label for="deviceType">Device ID Type </label>
                                                <select class="browser-default" id="deviceType">
                                                    <option value="" disabled selected>Select Device ID Type</option>
                                                    <option value="IMEI">IMEI</option>
                                                    <option value="ESN">ESN</option>
                                                    <option value="MEID">MEID</option>
                                                </select>
                                            </div> -->
                      	<div class="input-field col s12 m6 l6">
							<input type="text" id="editfrequency" name="frequency"
								placeholder="" pattern="[A-Za-z0-9 \s]{0,160}" maxlength="30"
								oninput="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');"
								oninvalid="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');"
								required> <label for="editfrequency"><spring:message
									code="input.frequency" /> <span class="star">*</span></label>
						</div>
						
						 <div class="input-field col s12 m6 l6">
							<input type="text" id="editImportertac" name="tac" placeholder=""
								pattern="[0-9]{8,8}" maxlength="8"
								oninput="InvalidMsg(this,'input','<spring:message code="validation.tac8" />');"
								oninvalid="InvalidMsg(this,'input','<spring:message code="validation.tac8" />');"
								required> <label for="tac"><spring:message
									code="input.TAC" /> <span class="star">*</span></label>
						</div>



						

					</div>
					
						  <div class="col s12 m6 l6" style="width: 409px; margin-left: 7px;">
							<label for="country"><spring:message code="input.Country" />
								<span class="star">*</span></label> <select id="editmanufacturercountry"
								class="browser-default" class="mySelect"
								onchange="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
								oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
								required></select>
						</div>                    
								

					<div id="mainDiv" class="col s12 m12 mainDiv">
						<div id="filediv" class="fileDiv">
							<div class="row">
								<div class="col s12 m6 l6">
									<label for="Category"><spring:message
											code="input.documenttype" /></label> <select class="browser-default"
										id="docTypetag1" onchange="enableSelectFile()">
										<option value="" disabled selected><spring:message
												code="select.documenttype" />
										</option>
									</select> <select class="browser-default" id="docTypetagValue1"
										style="display: none;">
										<option value="" disabled selected><spring:message
												code="select.documenttype" />
										</option>

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
										<input class="file-path validate" type="text" id="fileName"
											placeholder="<spring:message code="grievanceFileMessage" />">
										<div>
											<p id="myFiles"></p>
										</div>
									</div>
								</div>
							</div>


						</div>

					</div>
					<div class="row">
						<div class="col s12 m12 right">
							<span style="margin-left: 5px;"><spring:message
									code="input.requiredfields" /><span class="star">*</span></span>
							<button class="btn right add_field_button" disabled="disabled">
								<span style="font-size: 20px;">+</span>
								<spring:message code="input.addmorefile" />
							</button>
						</div>

						<div class="col s12 m12 center">
							<button class="btn " type="submit">
								<spring:message code="button.update" />
							</button>
							<!-- <a href="manageTypeDevices.html" class="btn" id="Cancel"
                                                    style="margin-left: 10px;">Cancel</a> -->
							<button class="modal-close btn" type="button"
								style="margin-left: 10px;">
								<spring:message code="button.cancel" />
							</button>
						</div>
					</div>
				</form>
			</div>

		</div>

		<div id="updateManageTypeDevice" class="modal">
			<h6 class="modal-header" style="margin: 0px;">
				<spring:message code="modal.UpdateDevices" />
			</h6>
			<div class="modal-content">

				<div class="row">
					<h6 id="updateTacMessage">
						<spring:message code="input.requestupdated" />
					</h6>
				</div>
				<div class="row">
					<div class="input-field col s12 center">
						<a href="./manageTypeDevices2" class="btn"><spring:message
								code="modal.ok" /></a>
					</div>
				</div>
			</div>
		</div>

		<div id="ApproveTAC" class="modal">
			<h6 class="modal-header">
				<spring:message code="input.Approve" />
			</h6>
			<div class="modal-content">


				<div class="row">
					<!-- <h6>
					The tax against the consignment with <span id="ManufacturerName"></span>
					having Transaction ID : ( <span id="TACnumber"></span>
					) has been successfully paid.
				</h6> -->
				</div>
				<div class="row">
					<h6>
						<spring:message code="input.approveTACStatus" />
						<span id="ApproveTacTxnId"></span> ?
					</h6>
					<input type="text" id="setApproveTacTxnId" style="display: none">
				</div>
				<div class="row">
					<div class="input-field col s12 center">
						<div class="input-field col s12 center">
							<button class="modal-close modal-trigger btn"
								onclick="approveSubmit(0)">
								<spring:message code="modal.yes" />
							</button>
							<button class="modal-close btn" style="margin-left: 10px;">
								<spring:message code="modal.no" />
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div id="confirmApproveTAC" class="modal">
			<h6 class="modal-header">
				<spring:message code="input.ApproveTAC" />
			</h6>
			<div class="modal-content">

				<div class="row">
					<h6 id="approveSuccessMessage"></h6>
				</div>
				<div class="row">
					<div class="input-field col s12 center">
						<div class="input-field col s12 center">
							<a href="./manageTypeDevices2" class="modal-close btn"><spring:message
									code="modal.ok" /></a>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div id="RejectTAC" class="modal">
			<h6 class="modal-header">
				<spring:message code="input.TACstatus" />
			</h6>
			<div class="modal-content">


				<div class="row">
					<!-- <h6>
					The tax against the consignment with <span id="ManufacturerName"></span>
					having Transaction ID : ( <span id="TACnumber"></span>
					) has been successfully paid.
				</h6> -->
				</div>
				<div class="row">
					<h6>
						<spring:message code="input.rejectTAC" />
						<span id="RejectTacTxnId"></span> ?
					</h6>
					<input type="text" id="setRejectTacTxnId" style="display: none">
				</div>
				<div class="row">
					<div class="input-field col s12 center">
						<div class="input-field col s12 center">
							<button class="modal-close modal-trigger btn"
								onclick="rejectSubmit(0)">
								<spring:message code="modal.yes" />
							</button>
							<button class="modal-close btn" style="margin-left: 10px;">
								<spring:message code="modal.no" />
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div id="confirmRejectTAC" class="modal">
			<h6 class="modal-header">
				<spring:message code="input.ApproveTAC" />
			</h6>
			<div class="modal-content">

				<div class="row">
					<h6 id="rejectSuccessMessage"></h6>
				</div>
				<div class="row">
					<div class="input-field col s12 center">
						<div class="input-field col s12 center">
							<a href="./manageTypeDevices2" class="modal-close btn"><spring:message
									code="modal.ok" /></a>
						</div>
					</div>
				</div>
			</div>
		</div>


		<!-- --------------------------------------------------------------Delete TAC Modal Start --------------------------------------------------------------->


		<div id="DeleteTacConfirmationModal" class="modal">
			<h6 class="modal-header">
				<spring:message code="modal.header.deleteTac" />
			</h6>
			<div class="modal-content">



				<form action="" onsubmit="return confirmantiondelete()">
					<div class="row">
						<h6>
							<spring:message code="modal.message.tac.widthdraw" />
							<span id="tacdeleteTxnId"></span>
						</h6>
					</div>
					<div class="row">
						<div class="input-field col s12 m12">
							<textarea id="deleteTacRemark"  
								class="materialize-textarea" 
							oninput="InvalidMsg(this,'input','<spring:message code="validation.200character" />');"
							oninvalid="InvalidMsg(this,'input','<spring:message code="validation.200character" />');" required></textarea>
							<label for="textarea1" class=""><spring:message
									code="input.remarks" /> <span class="star">*</span> </label>
						</div>
					</div>
					<input type="text" id="popupTransactionId" maxlength="15" hidden />
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
				<spring:message code="modal.header.deleteTac" />
			</h6>
			<div class="modal-content">


				<div class="row">

					<h6 id="tacModalText">
						<spring:message code="modal.message.tacDeleted" />
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
						<br>
						<br>
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
		<script type="text/javascript"
			src="${context}/resources/js/plugins.js"></script>
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
			src="${context}/resources/project_js/globalVariables.js"></script>
		<script type="text/javascript"
			src="${context}/resources/project_js/viewManageTypeAdmin.js"></script>
			<script type="text/javascript"
		src="${context}/resources/project_js/validationMsg.js"></script>
		<script type="text/javascript"
			src="${context}/resources/project_js/profileInfoTab.js" async></script>
		<script type="text/javascript"
			src="${context}/resources/project_js/_dateFunction.js" async></script>
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
