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
<body data-id="3"
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

								<a href="" class="boton right" id="btnLink"></a>
							</div>
							<form action="${context}/Consignment/viewConsignment"
								method="post">
								<div class="col s12 m12 l12" id="consignmentTableDIv"
									style="padding-bottom: 5px; background-color: #e2edef52;">
									<div id="filterBtnDiv"></div>
								</div>
							</form>
							<table id="consignmentLibraryTable"
								class="responsive-table striped display"></table>

						</div>

					</div>
				</div>
				<div id="footerBtn"></div>
			</div>
		</div>
		<!--end container-->
	</section>

	<!--viewModal Modal start   -->

	<div id="viewModal" class="modal-form" style="overflow-y: hidden;">
		<h6 class="modal-header">
			<spring:message code="modal.header.viewConsignment" />
		</h6>
		<div class="modal-content" style="margin-top: 5px;">
			<div class="row myRow">
				<div class="input-field col s12 m6">
					<input type="text" name="name" id="supplierId" placeholder=""
						readonly="readonly" /> <label for="Name" class="center-align"><spring:message
							code="input.supplier" /></label>
				</div>

				<div class="input-field col s12 m6">
					<input type="text" name="name" id="supplierName" placeholder=""
						readonly="readonly" /> <label for="Name" class="center-align"><spring:message
							code="input.suppliername" /></label>
				</div>
				<div class="input-field col s12 m6">
					<input type="text" name="name" id="consignmentNumber"
						placeholder="" readonly="readonly" /> <label for="Name"
						class="center-align"><spring:message
							code="input.consignmentnumber" /></label>
				</div>
				<div class="input-field col s12 m6">
					<!-- <p class="input-text-date" style="color: #c4c4c4;">Expected
						Dispatch Date</p> -->
					<input type="text" id="expectedDispatcheDate" placeholder=""
						readonly="readonly" placeholder=""> <label
						for="expectedDispatcheDate"><spring:message
							code="input.dispatchdate" /></label> <span class="input-group-addon"
						style="color: #ff4081"><i class="fa fa-calendar"
						aria-hidden="true"></i></span>
				</div>

				<div class="input-field col s12 m6">
					<input type="text" id="countryview" class="browser-default"
						readonly="readonly" class="mySelect" placeholder=""> <label
						for="Name" class="center-align"><spring:message
							code="input.country" /></label> <label for="countryview"
						class="center-align"></label>
				</div>

				<div class="input-field col s12 m6" style="color: #c4c4c4;">
					<!-- <p style="margin-top: -5px; margin-bottom: -13px; font-size: 12px;">Expected
						Arival Date</p> -->
					<input type="text" id="expectedArrivaldate" placeholder=""
						readonly="readonly" placeholder=""> <label
						for="expectedArrivaldate" class="center-align"><spring:message
							code="input.arrivaldate" /></label> <span class="input-group-addon"
						style="color: #ff4081"><i class="fa fa-calendar"
						aria-hidden="true"></i></span>
				</div>

				<div class="input-field col s12 m6">
					<!-- <label for="Name" class="center-align">Expected arrival port</label> -->
					<input type="text" id="expectedArrivalPort" readonly="readonly"
						placeholder=""> <label for="expectedArrivalPort"
						class="center-align"><spring:message
							code="input.arrivalport" /></label>
				</div>

				<div class="input-field col s12 m6">
					<input type="text" name="Quantity" placeholder="" id="Quantity"
						readonly="readonly" /> <label for="Quantity" class="center-align"><spring:message
							code="input.quantity" /></label>
				</div>

				<div class="input-field col s12 m6">
					<input type="text" name="TransactionId" placeholder=""
						id="TransactionId" readonly="readonly" maxlength="15" /> <label
						for="TransactionId" class="center-align"><spring:message
							code="input.transactionID" /></label>
				</div>


				<div class="input-field col s12 m6">
					<input type="text" name="totalPrice" placeholder=""
						disabled="disabled" id="viewtotalPrice" maxlength="7" />
					<label for="totalPrice" class="center-align"><spring:message
							code="input.totalprice" /></label>
				</div>

			
				<div class="input-field col s12 m6">
					<textarea id="remark" class="materialize-textarea"
						style="height: 0px;" readonly="readonly" placeholder=""></textarea>
					<label for="remark" class=""><spring:message
							code="input.remarks" /></label>

					<!--   <input type="textarea" name="Remark" placeholder="Remark" id="remark" readonly="readonly" maxlength="15" />
                                               <label for="TransactionId" class="center-align">Remark</label> -->
				</div>
					<div class="col s12 m6" id="viewCurrencyDiv">
					<label for="Currency"><spring:message code="input.currency" /></label>
					<select id="viewcurrency" class="browser-default"
						disabled="disabled">
						<option value="" disabled selected><spring:message
								code="input.currency" /></option>

					</select>
					<!-- <input type="text" id="viewcurrency" placeholder="" disabled="disabled"> -->
					<input type="text" id="viewhideCurrency" style="display: none;">
				</div>
			</div>

			<div class="row button-div">
				<div class="input-field col s12 center">
					<button class="btn" onclick="closeViewModal()"
						class="modal-close btn" id="add_user">
						<spring:message code="modal.close" />
					</button>
				</div>
			</div>


		</div>
	</div>
	<!-- Modal End -->


<!--Delete Modal start   -->

	<div id="DeleteConsignment" class="modal">
		<h6 class="modal-header">
			<spring:message code="modal.header.deleteConsignment" />
		</h6>
		<form action="" onsubmit=" return confirmantiondelete()" method="POST">
		<div class="modal-content">
		
				<h6>
					<spring:message code="modal.withdraw.message" />
					(<span id="transID"></span>)
				</h6>
			</div>

			<div class="row">
				<div class="input-field col s12 m12">
					<textarea id="textarea1" required="required" maxlength="200"  class="materialize-textarea"></textarea>
					<label for="textarea1"><spring:message code="input.remarks" /><span class="star">*</span></label>
				</div>
			</div>
			<input type="text" id="popupTransactionId" maxlength="15" hidden />
			<div class="row button-div">
				<div class="input-field col s12 center">
					<div class="input-field col s12 center">
						<button class="btn" type="submit"><spring:message
								code="modal.yes" /></button>
						<button class="modal-close btn" type="button"
							onclick="closeUpdateModal()" style="margin-left: 10px;">
							<spring:message code="modal.no" />
						</button>
					</div>
				</div>
		
			</div>
		</form>
		</div>
		
	</div>
	<!-- Modal End -->
	<!-- END CONTENT -->




	<!-- Modal 1 start   -->

	<div id="updateConsignment" class="modal">
		<h6 class="modal-header">
			<spring:message code="modal.header.updateConsignment" />
		</h6>
		<div class="modal-content">



			<div class="row">
				<h6 id="sucessMessage">
					<spring:message code="modal.message.update" />
				</h6>
			</div>
			<div class="row button-div">
				<div class="input-field col s12 center">
					<a href="${context}/Consignment/viewConsignment" class="btn"><spring:message
							code="modal.ok" /></a>
				</div>
			</div>
		</div>
	</div>





	<!-- Delete confirmation Modal start   -->

	<div id="confirmDeleteConsignment" class="modal">
		<h6 class="modal-header">
			<spring:message code="modal.header.deleteConsignment" />
		</h6>
		<div class="modal-content">



			<!-- <h4 class="header2 pb-2">User Info</h4> -->

			<div class="row">
				<h6 id=consignmentText>
					<spring:message code="modal.message.consignmentDelete" />
				</h6>
			</div>

			<div class="row button-div">
				<div class="input-field col s12 center">
					<div class="input-field col s12 center">
						<a href="${context}/Consignment/viewConsignment" class="btn"><spring:message
								code="modal.ok" /></a>
					</div>
				</div>
			</div>
		</div>
	</div>


	<!-- -----------------------------------------------------------------Approved Model------------------------------------------------------------------------------ -->


	<div id="ApproveConsignment" class="modal">
		<h6 class="modal-header">
			<spring:message code="modal.message.approveConsignment" />
		</h6>
		<div class="modal-content">


						<div class="row">
				<h6 id="approveConsignmnetHeading">
					<spring:message code="modal.message.clearConsignment" />
					<span id="displayname"></span>
				<%-- 	<spring:message code="modal.message.havingTxn" /> --%>
					( <span id="ApproveConsignmentTxnid"></span> )
					<spring:message code="modal.message.hasBeenpaid" />
				</h6>
				<div class="row">
					<h6>
						<spring:message code="modal.message.doApprove" />
					</h6>
					<form action=""  >
          <p id="tacSatusForCustom" style="display: none">
            <label>
              <input type="checkbox" id="tacStatusChecKbox" >
              <span id="tacStatucMessage"></span>
            </label>
          </p>
        </form>
				</div>
</div>
			<div class="row">
				<%-- <h6 id="confirmationMessage">
					<spring:message code="modal.message.clearConsignment" />
				</h6> --%>
				<input type="text" id="setApproveConsignmentTxnId"
					style="display: none">
			</div>
						<div class="row button-div">
				<div class="input-field col s12 center">
					<div class="input-field col s12 center">
						<button class="modal-close modal-trigger btn" id="approveButton" disabled="disabled"
							onclick="approveSubmit(0)">
							<spring:message code="modal.yes" />
						</button>
						<button class="modal-close btn"   style="margin-left: 10px;">
							<spring:message code="modal.no" />
						</button>
					</div>
				</div>
			</div>

		</div>
	</div>

	<div id="confirmApproveConsignment" class="modal">
		<h6 class="modal-header">
			<spring:message code="modal.message.approveConsignment" />
		</h6>
		<div class="modal-content">


			<div class="row">
				<h6 id="approveSuccessMessage">
					<spring:message code="modal.message.approved" />
				</h6>
			</div>
			<div class="row button-div">
				<div class="input-field col s12 center">
					<div class="input-field col s12 center">
						<a href="${context}/Consignment/viewConsignment"
							class="modal-close btn"><spring:message code="modal.close" /></a>
					</div>
				</div>
			</div>
		</div>
	</div>


	<div id="RejectConsignment" class="modal">
		<h6 class="modal-header">
			<spring:message code="modal.header.rejectConsignment" />
		</h6>
		<div class="modal-content">


			<div class="row">
				<h6>
					<spring:message code="modal.message.markConsignment" />
					(<span id="disapprovedDisplayname"></span>
					<spring:message code="modal.message.havingTxn" />
					<span id="disaproveTxnId"></span>
					<spring:message code="modal.message.asRejected" />
				</h6>
				<input type="text" id="setDisapproveConsignmentTxnId"
					style="display: none">
			</div>
			<div class="row">
				<div class="input-field col s12 m12" style="margin-left: -10px;">
					<textarea id="dispproveRemarks" class="materialize-textarea"
						style="padding-left: 0;"></textarea>
					<label for="textarea1"><spring:message code="input.remarks" />
						<span class="star">*</span></label>
				</div>
				<p>
					<spring:message code="input.requiredfields" />
					<span class="star">*</span>
				</p>
			</div>
			<div class="row button-div">
				<div class="input-field col s12 center">
					<div class="input-field col s12 center">
						<button class="modal-close modal-trigger btn"
							onclick="disapproveSubmit(1)">
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





	<div id="ApproveConsignment" class="modal">
		<h6 class="modal-header">
			<spring:message code="modal.message.approveConsignment" />
		</h6>
		<div class="modal-content">


			<div class="row">
				<h6>
					<spring:message code="modal.message.taxAgainst" />
					( <span id="ApproveConsignmentTxnid"></span> )
					<spring:message code="modal.message.hasBeenpaid" />
				</h6>
			</div>
			<div class="row">
				<h6>
					<spring:message code="modal.message.doApprove" />
				</h6>
				<input type="text" id="setApproveConsignmentTxnId"
					style="display: none">
			</div>
			<div class="row button-div">
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

	<div id="confirmApproveConsignment" class="modal">
		<h6 class="modal-header">
			<spring:message code="modal.message.approveConsignment" />
		</h6>
		<div class="modal-content">


			<div class="row">
				<h6 id="approveSuccessMessage">
					<spring:message code="modal.message.approved" />
				</h6>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<div class="input-field col s12 center">
						<a href="${context}/Consignment/viewConsignment"
							class="modal-close btn"><spring:message code="modal.close" /></a>
					</div>
				</div>
			</div>
		</div>
	</div>


	<div id="RejectConsignment" class="modal">
		<h6 class="modal-header">
			<spring:message code="modal.header.rejectConsignment" />
		</h6>
		<div class="modal-content">


			<div class="row">
				<h6>
					<spring:message code="modal.message.haveTxn" />
					<span id="disaproveTxnId"></span>
					<spring:message code="modal.message.asRejected" />
					.
				</h6>
				<input type="text" id="setDisapproveConsignmentTxnId"
					style="display: none">
			</div>
			<div class="row">
				<div class="input-field col s12 m12" style="margin-left: -10px;">
					<textarea id="dispproveRemarks" class="materialize-textarea"
						style="padding-left: 0;"></textarea>
					<label for="textarea1"><spring:message code="input.remarks" />
						<span class="star">*</span></label>
				</div>
				<p>
					<spring:message code="input.requiredfields" />
					<span class="star">*</span>
				</p>
			</div>
			<div class="row button-div">
				<div class="input-field col s12 center">
					<div class="input-field col s12 center">
						<button class="modal-close modal-trigger btn"
							onclick="disapproveSubmit(1)">
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


	<div id="confirmRejectConsignment" class="modal">
		<h6 class="modal-header">
			<spring:message code="modal.header.rejectConsignment" />
		</h6>
		<div class="modal-content">


			<div class="row">
				<h6 id="disapproveSuccessMessage">
					<spring:message code="modal.message.rejected" />
				</h6>
			</div>
			<div class="row button-div">
				<div class="input-field col s12 center">
					<div class="input-field col s12 center">
						<a href="${context}/Consignment/viewConsignment"
							class="modal-close btn"><spring:message code="modal.ok" /></a>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Update Modal Start -->
		<div id="updateModal" class="modal-form" style="overflow-y: hidden;">
		<h6 class="modal-header">
			<spring:message code="modal.header.editConsignment" />
		</h6>
		<div class="modal-content">
			<form action="" onsubmit="return editRegisterConsignment()"
				method="POST" enctype="multipart/form-data">
				<div class="row myRow" style="margin-top: 5px;" >
					<div class="input-field col s12 m6">
						<input type="text" name="supplierId" id="supplierIdEdit"
							pattern="[A-Za-z0-9]{0,15}"
							placeholder="" maxlength="15" /> <label for="Name"
							class="center-align"><spring:message
								code="input.supplier" /></label>
					</div>

					<div class="input-field col s12 m6">
						<input type="text" name="supplierName" id="supplierNameEdit"
							placeholder="" pattern="[A-Za-z  ]{0,50}"
							oninput="InvalidMsg(this,'input','<spring:message code="validation.50character" />');" oninvalid="InvalidMsg(this,'input','<spring:message code="validation.50character" />');"

							maxlength="50" required /> <label for="Name"
							class="center-align"><spring:message
								code="input.suppliername" /><span class="star">*</span> </label>
					</div>
					<div class="input-field col s12 m6">
						<input type="text" name="consignmentNumber"
							id="consignmentNumberEdit" pattern="[A-Za-z0-9]{0,15}"
							placeholder="" maxlength="15" /> <label for="Name"
							class="center-align"><spring:message
								code="input.consignmentnumber" /></label>
					</div>

					<div class="input-field col s12 m6">

						<input name="expectedDispatcheDateEdit" id="expectedDispatcheDateEdit"
							placeholder="" type="text" class='form-control datepick'
							autocomplete='off'
							onchange="InvalidMsg(this,'date','<spring:message code="validation.requiredMsg" />');checkDateOnModal(expectedDispatcheDateEdit,expectedArrivaldateEdit);" oninvalid="InvalidMsg(this,'date','<spring:message code="validation.requiredMsg" />');"
							 required />
						<label for="dispatchDate" class="center-align"><spring:message
								code="input.dispatchdate" /><span class="star">*</span> </label>
								<p id="errorMsgOnModal" class="onEditModalTitle"></p>
								 <span
							class="input-group-addon" style="color: #ff4081"><i
							class="fa fa-calendar" aria-hidden="true"></i></span>
					</div>
					<div class="input-field col s12 m6">
						<!-- <p style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">Device Origination Country <span class="star">*</span></p> -->
						<select id="country" name="organisationcountry"
							class="browser-default" class="mySelect"
							onchange="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');" oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
							required></select> <label for="country" class="center-align"></label>
					</div>


					<div class="input-field col s12 m6">
						<!-- <p class="input-text-date">Expected Dispatch Date <span class="star">*</span></p> -->
						<!-- <label for="Name">Expected arrival Date</label> -->
						<input name="expectedArrivaldateEdit" id="expectedArrivaldateEdit"
							placeholder="" type="text" class='form-control datepick'
							autocomplete='off' onchange="InvalidMsg(this,'date','<spring:message code="validation.requiredMsg" />');checkDateOnModal(expectedDispatcheDateEdit,expectedArrivaldateEdit);" oninvalid="InvalidMsg(this,'date','<spring:message code="validation.requiredMsg" />');"
							 required />
						<label for="dispatchDate" class="center-align"><spring:message
								code="input.arrivaldate" /><span class="star">*</span> </label> <span
							class="input-group-addon" style="color: #ff4081"><i
							class="fa fa-calendar" aria-hidden="true"></i></span>
					</div>
					<div class="input-field col s12 m6">
						<select name="expectedArrivalPort" id="expectedArrivalPortEdit"
							class="browser-default"
						>
							<option value="" disabled selected><spring:message
									code="input.arrivalport" />*
							</option>

						</select>
					</div>

					<div class="input-field col s12 m6">
						<input type="text" name="quantity" id="QuantityEdit"
							pattern="[0-9]{0,7}" placeholder=""
							
							maxlength="7" oninput="InvalidMsg(this,'input','<spring:message code="validation.7character" />');" oninvalid="InvalidMsg(this,'input','<spring:message code="validation.7character" />');" 
							required /> <label for="Quantity" class="center-align"><spring:message
								code="input.quantity" /><span class="star">*</span></label>
					</div>

					<div class="input-field col s12 m6">
						<input type="text" name="txnId" id="TransactionIdEdit"
							placeholder="Transaction ID" value="" readonly maxlength="15" />
						<label for="TransactionId" class="center-align"><spring:message
								code="input.transactionID" /></label>
					</div>


					<div class="input-field col s12 m6">
						<input type="text" name="totalPrice" id="totalPrice" maxlength="7"
							pattern="[0-9]{0,7}"
							placeholder="" /> <label for="totalPrice" class="center-align"><spring:message
								code="input.totalprice" /></label>
					</div>

                     <div class="file-field input-field col s12 m6"
						style="margin-top: 5px;">
						<h6 class="file-upload-heading" style="margin-top: -5px;">
							<spring:message code="input.bulkdevice" />
							<span class="star">*</span>
						</h6>
						<div class="btn">
							<span><spring:message code="input.selectfile" /></span> <input
								type="file" name="file" id="csvUploadFile" accept=".csv" onchange="isFileValid('csvUploadFile')"
								>
						</div>
						<div class="file-path-wrapper">
							<input class="file-path validate responsive-file-div"
								id="fileNameEdit" type="text">
						</div>
					</div>
					<input type="text" id="fileNameToBeSame" style="display: none;">
					<div class="col s12 m6" id="currencyDiv">
						<label for="Currency"><spring:message
								code="input.currency" /></label> <select id="currency"
							class="browser-default">
							<option value="" disabled selected><spring:message
									code="input.currency" /></option>

						</select> <input type="text" id="hideCurrency" style="display: none;">
					</div>

					
				</div>




				<div class="row" style="padding-bottom: 15px">
					<div class="col s12 m12">
						<a href="./sampleFileDownload/3"><spring:message
								code="input.downlaod.sample" /></a></br> <span> <spring:message
								code="input.requiredfields" /> <span class="star">*</span>
						</span>
					</div>
					<div class="input-field col s12 center">
						<button class=" btn" type="submit">
							<spring:message code="button.update" />
						</button>
						<button class="modal-close btn" type="button"
							onclick="closeUpdateModal()" style="margin-left: 10px;">
							<spring:message code="button.cancel" />
						</button>
					</div>
				</div>
			</form>
		</div>
	</div>

	<div id="markAsMultipleStolen" class="modal">
		<h6 class="modal-header">
			<spring:message code="button.markAsStolen" />
		</h6>
		<div class="modal-content">




			<div class="row">
				<h6>
					<spring:message code="modal.message.txnmarked" />
				</h6>
			</div>
			<div class="row button-div">
				<div class="input-field col s12 center">
					<a onclick="openMulipleStolenPopUp()"
						class="modal-close modal-trigger btn"><spring:message
							code="modal.yes" /></a>
					<button class="modal-close btn" style="margin-left: 10px;">
						<spring:message code="modal.no" />
					</button>
				</div>
			</div>
		</div>
	</div>
	<div id="markAsStolenDone" class="modal">
		<h6 class="modal-header">
			<spring:message code="button.markAsStolen" />
		</h6>
		<div class="modal-content">
			<div class="row">
				<h6>
					<spring:message code="modal.message.markedasstolen" />
				</h6>
			</div>
			<div class="row button-div">
				<div class="input-field col s12 center">
					<!-- <button class="modal-close btn" style="margin-left: 10px;">ok</button> -->
					<a onclick="redirectToViewPage()" class="btn"><spring:message
							code="modal.close" /></a>
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
		src="${context}/resources/project_js/viewConsignment.js"></script>
			<script type="text/javascript"
		src="${context}/resources/project_js/validationMsg.js"></script>
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

