<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
</head>

<body data-id="7" data-roleType="${usertype}" data-userTypeID="${usertypeId}"
	data-userID="${userid}" data-operatorTypeId="${operatorTypeId}"
	data-selected-roleType="${stolenselectedUserTypeId}"
	data-stolenselected-roleType="${stolenselectedUserTypeId}"	
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

	<!--viewModal Modal start   -->

	<div id="viewModal" class="modal-form" style="overflow-y: hidden;">
			<h6 class="modal-header"><spring:message code="modal.header.viewConsignment" /></h6>
			
			<div class="modal-content">

		


			<div class="row myRow">
				<div class="input-field col s12 m6">
					<input type="text" name="name" id="supplierId"
						placeholder="" readonly="readonly" /> <label
						for="supplierId" class="center-align"><spring:message code="input.supplier" /></label>
				</div>

				<div class="input-field col s12 m6">
					<input type="text" name="name" id="supplierName"
						placeholder="" readonly="readonly" /> <label
						for="supplierName" class="center-align"><spring:message code="input.suppliername" /></label>
				</div>
				<div class="input-field col s12 m6">
					<input type="text" name="name" id="consignmentNumber"
						placeholder="" readonly="readonly" /> <label
						for="consignmentNumber" class="center-align"><spring:message code="input.consignmentnumber" /></label>
				</div>

				<div class="input-field col s12 m6" style="color: #c4c4c4;">
					<p style="margin-top: -5px; margin-bottom: -13px; font-size: 12px;"><spring:message code="input.ExpectedArivalDate" /></p>
					<!-- <label for="Name" class="center-align">Expected Dispatch Date</label> -->
					<input type="date" id="expectedArrivaldate"
						placeholder="" readonly="readonly"> <span
						class="input-group-addon" style="color: #ff4081"><i
						class="fa fa-calendar" aria-hidden="true"></i></span>
				</div>
				<div class="input-field col s12 m6">
					<input type="text" id="countryview" class="browser-default"
						readonly="readonly" class="mySelect"
						placeholder=""> <label
						for="countryview" class="center-align"><spring:message code="input.country" /></label> <label
						for="countryview" class="center-align"></label>
				</div>


				<div class="input-field col s12 m6">
					<p class="input-text-date" style="color: #c4c4c4;"><spring:message code="input.dispatchdate" /></p>
					<!-- <label for="expectedDispatcheDate">Expected arrival Date</label> -->
					<input type="date" id="expectedDispatcheDate"
						placeholder="" readonly="readonly">
					<span class="input-group-addon" style="color: #ff4081"><i
						class="fa fa-calendar" aria-hidden="true"></i></span>
				</div>
				<div class="input-field col s12 m6">
					<!-- <label for="expectedArrivalPort" class="center-align">Expected arrival port</label> -->
					<input type="text" id="expectedArrivalPort" readonly="readonly"
						placeholder=""> <label for="expectedArrivalPort"
						class="center-align"><spring:message code="input.arrivalport" /></label>
				</div>

				<div class="input-field col s12 m6">
					<input type="text" name="Quantity" placeholder=""
						id="Quantity" readonly="readonly" /> <label for="Quantity"
						class="center-align"><spring:message code="input.quantity" /></label>
				</div>

				<div class="input-field col s12 m6">
					<input type="text" name="TransactionId"
						placeholder="" id="TransactionId"
						readonly="readonly" maxlength="15" /> <label for="TransactionId"
						class="center-align"><spring:message code="input.TransactionID" /></label>
				</div>

				<div class="input-field col s12 m6">
					<textarea id="remark" class="materialize-textarea"
						style="height: 0px;" readonly="readonly"></textarea>
					<label for="remark" class=""><spring:message code="input.remarks" /></label>

					<!--   <input type="textarea" name="Remark" placeholder="Remark" id="remark" readonly="readonly" maxlength="15" />
                                               <label for="TransactionId" class="center-align">Remark</label> -->
				</div>
			</div>

			<div class="row" style="padding: 20px 0 100px 0;">
				<div class="input-field col s12 center">
					<button class="btn" onclick="closeViewModal()"
						class="modal-close btn" id="add_user"><spring:message code="modal.cancel" /></button>
				</div>
			</div>


		</div>
	</div>
	<!-- Modal End -->


	<!--Delete Modal start   -->

	<div id="DeleteConsignment" class="modal">
		<h6 class="modal-header"><spring:message code="modal.header.delete" /></h6>
		<div class="modal-content">
	<div class="row">
				<h6><spring:message code="modal.withdraw.messageforStolen" /> ( <span id="transID"></span>)
				</h6>
				<span id="setStolenRecoveyRowId" style="display: none;"></span>
			</div>
			<input type="text" id="popupTransactionId" maxlength="15" hidden />
			<div class="row">
				<div class="input-field col s12 center">
					<div class="input-field col s12 center">
						<a class="btn" onclick="confirmantiondelete()"><spring:message code="modal.ok" /></a>
						<button class="modal-close btn" onclick="closeUpdateModal()"
							style="margin-left: 10px;"><spring:message code="modal.no" /></button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal End -->
	<!-- END CONTENT -->




	<!-- Modal 1 start   -->

	<div id="updateConsignment" class="modal">
			<h6><spring:message code="modal.header.updateConsignment" /></h6>
			<div class="modal-content">
		
			

			<div class="row">
				<h6 id="sucessMessage"></h6>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<a href="${context}/Consignment/viewConsignment" class="btn"><spring:message code="modal.ok" /></a>
				</div>
			</div>
		</div>
	</div>





	<!-- Delete confirmation Modal start   -->

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



	<!-- Update Modal Start -->
	<div id="updateModal" class="modal-form" style="overflow-y: hidden;">
			<h6 class="modal-header"><spring:message code="modal.header.editConsignment" /></h6>
			<div class="modal-content">

		
			


			<div class="row myRow">
				<div class="input-field col s12 m6">
					<input type="text" name="supplierId" id="supplierIdEdit"
						pattern="[A-Za-z0-9]{0,15}"
						title="Please enter alphabets and numbers upto 15 characters only"
						placeholder="" maxlength="15" /> <label
						for="supplierIdEdit" class="center-align"><spring:message code="input.supplier" /></label>
				</div>

				<div class="input-field col s12 m6">
					<input type="text" name="supplierName" id="supplierNameEdit"
						pattern="[A-Za-z]{0,50}"
						title="Please enter alphabets  upto 50 characters only"
						maxlength="50" placeholder="" required />
					<label for="supplierNameEdit" class="center-align"><spring:message code="input.suppliername" /> <span class="star">*</span>
					</label>
				</div>
				<div class="input-field col s12 m6">
					<input type="text" name="consignmentNumber"
						id="consignmentNumberEdit" pattern="[A-Za-z0-9]{0,15}"
						placeholder="" maxlength="15" /> <label
						for="consignmentNumberEdit" class="center-align"><spring:message code="input.consignmentnumber" /></label>
				</div>

				<div class="input-field col s12 m6">
					<!-- <p style="margin-top: -5px; margin-bottom: -13px; font-size: 12px;">Expected
                                                Arrival Date <span class="star">*</span></p> -->
					<!-- <label for="Name" class="center-align">Expected Dispatch Date</label> -->
					<input name="expectedDispatcheDate" id="expectedDispatcheDateEdit"
						required="required" placeholder=" "
						type="text" onfocus="(this.type='date')"
						onfocusout="(this.type='text')"> <label for="expectedDispatcheDateEdit"
						class="center-align"><spring:message code="input.dispatchdate" /> <span
						class="star">*</span></label> <span class="input-group-addon"
						style="color: #ff4081"><i class="fa fa-calendar"
						aria-hidden="true"></i></span>
				</div>
				<div class="input-field col s12 m6">
					<!-- <p style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">Device Origination Country <span class="star">*</span></p> -->
					<select id="country" name="organisationcountry" required="required"
						class="browser-default" class="mySelect" required></select> <label
						for="country" class="center-align"></label> </div>


				<div class="input-field col s12 m6">
					<!-- <p class="input-text-date">Expected Dispatch Date <span class="star">*</span></p> -->
					<!-- <label for="Name">Expected arrival Date</label> -->
					<input name="expectedArrivalDate" id="expectedArrivaldateEdit"
						required="required" placeholder=""
						type="text" onfocus="(this.type='date')"
						onfocusout="(this.type='text')"> <label for="expectedArrivaldateEdit"
						class="center-align"><spring:message code="input.arrivaldate" /> <span
						class="star">*</span></label> <span class="input-group-addon"
						style="color: #ff4081"><i class="fa fa-calendar"
						aria-hidden="true"></i></span>
				</div>
				<div class="input-field col s12 m6">
					<!-- <label for="expectedArrivalPortEdit" class="center-align">Expected arrival port</label> -->
					<!-- <p style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">Expected arrival port <span class="star">*</span></p> -->
					<select name="expectedArrivalPort" id="expectedArrivalPortEdit"
						class="browser-default" required>
						<option value="" disabled selected><spring:message code="input.arrivalport" />
							*</option>
						<option value="Air">Air</option>
						<option value="Land">Land</option>
						<option value="Water">Water</option>
					</select>
				</div>

				<div class="input-field col s12 m6">
					<input type="text" name="quantity" id="QuantityEdit"
						pattern="[0-9]{0,7}"
						title="Please enter numbers upto 7 characters only" maxlength="7"
						placeholder="" required /> <label for="QuantityEdit"
						class="center-align"><spring:message code="input.quantity" /> <span class="star">*</span></label>
				</div>

				<div class="input-field col s12 m6">
					<input type="text" name="txnId" id="TransactionIdEdit"
						placeholder="" value="" readonly maxlength="15" />
					<label for="TransactionIdEdit" class="center-align"><spring:message code="input.TransactionID1" /></label>
				</div>
			</div>


			<div class="row myRow">
				<div class="file-field input-field col s12 m6"
					style="margin-top: 5px;">
					<h6 class="file-upload-heading" style="margin-left: 0;">
					<spring:message code="input.bulkdevice" /> <span class="star">*</span>
					</h6>
					<div class="btn">
						<span><spring:message code="input.selectfile" /></span> <input type="file" name="file"
							id="csvUploadFile" accept=".csv">
					</div>
					<div class="file-path-wrapper">
						<input class="file-path validate responsive-file-div"
							id="fileNameEdit" type="text">
					</div>
				</div>


			</div>
			<p>
				<a href="#"><spring:message code="input.downlaod.sample" /></a>
			</p>

			<span><spring:message code="input.requiredfields" /> <span class="star">*</span>
			</span>


			<div class="row">
				<div class="input-field col s12 center">
					<button class="waves-effect waves-light modal-trigger btn"
						type="button" onclick="editRegisterConsignment()"><spring:message code="button.update" /></button>
					<button class="modal-close btn" onclick="closeUpdateModal()"
						style="margin-left: 10px;"><spring:message code="button.cancel" /></button>
				</div>
			</div>

		</div>
	</div>

	<div id="markAsStolen" class="modal">
		<h6 class="modal-header"><spring:message code="button.markAsStolen" /></h6>
		<div class="modal-content">

			
			

			<div class="row">
				<h6><spring:message code="input.stolenreceived" /></h6>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<a onclick="redirectToViewStolenPage()" class="modal-close btn"
						style="margin-left: 10px;"><spring:message code="modal.ok" /></a>
				</div>
			</div>
		</div>
	</div>

	<div id="updateMarkAsStolen" class="modal">
		<h6 class="modal-header"><spring:message code="button.markAsStolen" /></h6>
		<div class="modal-content">

			
			

			<div class="row">
				<h6 id="editMessageTextStoleRecovery"></h6>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<a href="" class="modal-close btn" style="margin-left: 10px;"><spring:message code="modal.ok" /></a>
				</div>
			</div>
		</div>
	</div>


	<div id="stoleRecoveryModal" class="modal">
		<button type="button"
			class=" modal-action modal-close  btn-flat right"
			data-dismiss="modal">&times;</button>
		<div class="row" style="padding-bottom: 20px;"
			id="stolenRecoveryDivPage">
<h6 class="modal-header"><spring:message code="modal.Stolen/Recovery" /></h6>
			<div class="col s12 m12 modal-content">
				
				<div class="row">
					<form action="#">
						<h5 class="center">
							<label> <input name="group1" type="radio"
								onclick="document.getElementById('stolendiv').style.display ='block'; document.getElementById('recoverydiv').style.display ='none';" />
								<span class="checkboxFont"> <spring:message code="input.Stolen" /></span>
							</label> <label> <input name="group1" type="radio"
								onclick="document.getElementById('recoverydiv').style.display ='block'; document.getElementById('stolendiv').style.display ='none';" />
								<span class="checkboxFont"> <spring:message code="input.Recovery" /></span>
							</label>
						</h5>
					</form>
				</div>

				<div class="row" style="padding-bottom: 20px; display: none;"
					id="stolendiv">
					<div class="col s12 m12 l12">
						<form action="#">
							<h5 class="center">
								<c:choose>
									<c:when test="${stolenselectedUserTypeId=='Importer'}">
										<label> <input name="group1" class="chooseconsignment"
											type="radio" onclick="pickConsignment()" /> <span
											class="checkboxFont"><spring:message code="input.Chooseconsignment" /></span>
										</label>

										<label> <input name="group1" type="radio"
											class="chooseStock" onclick="pickstock()" /> <span
											class="checkboxFont"><spring:message code="input.stock" /></span>
										</label>

										<label> <input name="group1" type="radio"
											onclick="openFileStolenModal()"
											class="modal-trigger modal-close" /> <span
											class="checkboxFont"><spring:message code="input.UploadBulk" /></span>
										</label>
									</c:when>
									<c:otherwise>
										<label> <input name="group1" type="radio"
											class="chooseStock" onclick="pickstock()" /> <span
											class="checkboxFont"><spring:message code="input.stock" /></span>
										</label>

										<label> <input name="group1" type="radio"
											onclick="openFileStolenModal()"
											class="modal-trigger modal-close" /> <span
											class="checkboxFont"><spring:message code="input.UploadBulk" /></span>
										</label>
									</c:otherwise>
								</c:choose>
							</h5>
						</form>
					</div>
				</div>

				<div class="row" style="padding-bottom: 20px; display: none;"
					id="recoverydiv">
					<div class="col s12 m12 l12">
						<form action="#">
							<h5 class="center">
								<label> <input name="group1" type="radio"
									onclick="pickExistingRecovery();" /> <span
									class="checkboxFont"> <spring:message code="input.existing" /></span>
								</label> <label> <input name="group1" type="radio"
									onclick="openRecoveryModal()" data-target="recoveryDiv1"
									class="modal-trigger modal-close" /> <span
									class="checkboxFont"><spring:message code="input.UploadBulk" /></span>
								</label>
							</h5>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>


	<div id="fileStolenModal" class="modal">
		<h6 class="modal-header"><spring:message code="button.markAsStolen" /></h6>
		<div class="modal-content">
			<div class="row">
				<div class="col s12 m12">
					
					
					<div class="row">
						<h6 style="color: #000;">
							<spring:message code="input.bulkdevice" /> <span class="star">*</span>
						</h6>

						<div class="file-field input-field col s12 m6">
							<div class="btn" style="height: 35px; line-height: 2.5rem;">
								<span><spring:message code="input.selectfile" /></span> <input type="file"
									id="stolenCsvUploadFile" accept=".csv">
							</div>
							<div class="file-path-wrapper">
								<input class="file-path validate" type="text">
							</div>
						</div>

						<div class="input-field col s12 m6">
							<input type="text" name="stolenQuantity" id="stolenQuantity">
							<label for="stolenQuantity" class="center-align"><spring:message code="input.quantity" /></label>
						</div>
					</div>
				</div>


				<div class="col s12 m6 l6" id="SavedFileNameDiv"
					style="display: none">
					<label for="SavedFileName" class="center-align"><spring:message code="input.UploadedFile" /></label>
					<input type="text" name="" class="form-control boxBorder boxHeight"
						readonly id="SavedFileName" />
				</div>
			</div>
			<a href="./Consignment/sampleFileDownload/filetype=sample"
				style="margin-left: 10px;"><spring:message code="input.downlaod.sample" /></a><br> <br>

			<div class="row" id="samplefileDiv3"
				style="display: none; margin-left: 05px;">
				<div style="display: inline-flex">
					<a href="#" id="simDevice3"><spring:message code="input.IMEIdual" /></a><br>
					<br> <a href="#" style="margin-left: 75px;" id="rangeDevice3"><spring:message code="input.IMEIRange" /></a><br> <br>
				</div>
			</div>
<!-- 			<div style="margin-left: 36%; margin-top: -25px;">
				<label style="margin-right: 2%;"> <input type="radio" id=""
					value="Immediate"
					onclick="document.getElementById('calender').style.display = 'none';"
					name="stolenBlockPeriod" checked> Immediate
				</label> <label style="margin-right: 2%;"> <input type="radio"
					value="Default"
					onclick="document.getElementById('calender').style.display = 'none';"
					name="stolenBlockPeriod"> Default
				</label> <label> <input type="radio" value="tilldate"
					onclick="document.getElementById('calender').style.display = 'block';"
					name="stolenBlockPeriod"> Later
				</label>
				<div class="col s6 m2 responsiveDiv"
					style="display: none; width: 30%;" id="calender">
					<div id="startdatepicker" class="input-group date"
						data-date-format="yyyy-mm-dd">
						<input class="form-control" type="date" id="stolenDatePeriod"
							style="margin-top: -9px" /> <span class="input-group-addon"
							style="color: #ff4081"><i class="fa fa-calendar"
							aria-hidden="true" style="float: right; margin-top: -30px;"></i></span>
					</div>

				</div>


				<div class="col s12 m2 l2" style="width: 40%; display: none"
					id="stolenDate">

					<label for="TotalPrice" class="center-align">Till date</label>
					<div id="startdatepicker" class="input-group date"
						data-date-format="yyyy-mm-dd" style="margin-top: 10px;">

						<input class="form-control" name="inputsaves" type="text"
							id="startDateFilter" readonly /> <span class="input-group-addon"
							style="color: #ff4081"><i
							class="glyphicon glyphicon-calendar"
							onclick="_Services._selectstartDate()"></i></span>
					</div>
				</div>
			</div> -->
			<div class="col s12 m12">
				<p style="margin-left: 10px;">
					<spring:message code="input.requiredfields" /> <span class="star">*</span>
				</p>
			</div>

			<div class="row" style="margin-bottom: 30px;">
				<div class="input-field col s12 center">
					<a onclick="fileStolenReport()"
						class="modal-close modal-trigger btn" style="margin-right: 10px;"><spring:message code="button.submit" /></a>

					<button class="btn" onclick="closeStolenModalModal()"><spring:message code="button.cancel" /></button>
				</div>
			</div>
		</div>
	</div>

	<div id="editFileStolenModal" class="modal">
		<h6 class="modal-header">
						<spring:message code="input.UpdateStolenrequest" /> ( <span
							id="editFileStolenTxnId"></span> ).
					</h6>
					<div class="modal-content">
			<div class="row">
				<div class="col s12 m12">
					
					<input type="text" id="editFileStolenRequestType"
						style="display: none;">
					
					<div class="row">
						<h6 style="color: #000;">
							 <spring:message code="input.bulkdevice" /><span class="star">*</span>
						</h6>

						<div class="file-field input-field col s12 m6">
							<div class="btn" style="height: 35px; line-height: 2.5rem;">
								<span><spring:message code="input.selectfile" /></span> <input type="text"
									id="editFileStolenId" style="display: none;"> <input
									type="file" id="editStolenCsvUploadFile" accept=".csv">
							</div>
							<div class="file-path-wrapper">
								<input class="file-path validate" type="text">
							</div>
						</div>
						<div class="input-field col s12 m6">
					<input type="text" name="quantity" id="editStolenQuantity" /> <label
						for="editStolenQuantity" class="center-align"><spring:message code="input.quantity" /></label>
				</div>
					</div>
					
				</div>

				

				<div class="col s12 m6 l6" id="SavedFileNameDiv"
					style="display: none">
					<label for="SavedFileName" class="center-align"><spring:message code="input.UploadedFile" /></label>
					<input type="text" name="" class="form-control boxBorder boxHeight"
						readonly id="SavedFileName" />
				</div>


			</div>
			<a href="./Consignment/sampleFileDownload/filetype=sample"
				style="margin-left: 10px;"><spring:message code="input.downlaod.sample" /></a><br> <br>

			<div class="row" id="samplefileDiv3"
				style="display: none; margin-left: 05px;">
				<div style="display: inline-flex">
					<a href="#" id="simDevice3"><spring:message code="input.IMEIdual" /></a><br>
					<br> <a href="#" style="margin-left: 75px;" id="rangeDevice3"><spring:message code="input.IMEIRange" /></a><br> <br>
				</div>
			</div>
			<div style="margin-left: 36%; margin-top: -25px;">
				<label style="margin-right: 2%;"> <input type="radio" id=""
					value="Immediate"
					onclick="document.getElementById('calender').style.display = 'none';"
					name="editStolenBlockPeriod" checked> Immediate<spring:message code="input.supplier" />
				</label> <label style="margin-right: 2%;"> <input type="radio"
					value="Default"
					onclick="document.getElementById('calender').style.display = 'none';"
					name="editStolenBlockPeriod"> Default<spring:message code="input.supplier" />
				</label> <label> <input type="radio" value="tilldate"
					onclick="document.getElementById('editFilecalender').style.display = 'block';"
					name="editStolenBlockPeriod"> Later<spring:message code="input.supplier" />
				</label>
				<div class="col s6 m2 responsiveDiv"
					style="display: none; width: 30%;" id="editFilecalender">
					<div id="startdatepicker" class="input-group date"
						data-date-format="yyyy-mm-dd">
						<input class="form-control" type="date" id="editStolenDatePeriod"
							style="margin-top: -9px" /> <span class="input-group-addon"
							style="color: #ff4081"><i class="fa fa-calendar"
							aria-hidden="true" style="float: right; margin-top: -30px;"></i></span>
					</div>

				</div>


				<div class="col s12 m2 l2" style="width: 40%; display: none"
					id="stolenDate">

					<label for="startDateFilter" class="center-align">Till date<spring:message code="input.supplier" /></label>
					<div id="startdatepicker" class="input-group date"
						data-date-format="yyyy-mm-dd" style="margin-top: 10px;">

						<input class="form-control" name="inputsaves" type="text"
							id="startDateFilter" readonly /> <span class="input-group-addon"
							style="color: #ff4081"><i
							class="glyphicon glyphicon-calendar"
							onclick="_Services._selectstartDate()"></i></span>
					</div>
				</div>
			</div>
			<div class="col s12 m12">
				<p style="margin-left: 10px;">
					<spring:message code="input.requiredfields" /> <span class="star">*</span>
				</p>
			</div>

			<div class="row" style="margin-bottom: 30px;">
				<div class="input-field col s12 center">
					<a onclick="updatefileStolenReport()"
						class="modal-close modal-trigger btn" style="margin-right: 10px;"><spring:message code="button.submit" /></a>

					<button class="btn" onclick="closeEditStolenRecoveryModal()"><spring:message code="button.cancel" /></button>
				</div>
			</div>
		</div>
	</div>


	<div id="recoveryFileModal" class="modal">
		<div class="modal-content">
			<div class="row">
				<div class="col s12 m12">
					<h6 class="modal-header"><spring:message code="button.MarkAsRecovered" /></h6>
					
					<div class="row">
						<h6 style="color: #000;">
							<spring:message code="input.bulkdevice" /> <span class="star">*</span>
						</h6>

						<div class="file-field input-field col s12 m6">
							<div class="btn" style="height: 35px; line-height: 2.5rem;">
								<span><spring:message code="input.selectfile" /></span> <input type="file"
									id="recoveryCsvUploadFile" accept=".csv">
							</div>
							<div class="file-path-wrapper">
								<input class="file-path validate" type="text">
							</div>
						</div>

						<div class="input-field col s12 m6">
							<input type="text" name="quantity" id="recoverQuantity">
							<label for="recoverQuantity" class="center-align"><spring:message code="input.quantity" /></label>
						</div>


						<div class="col s12 m6 l6" id="SavedFileNameDiv"
							style="display: none">
							<label for="SavedFileName" class="center-align"><spring:message code="input.UploadedFile" /></label> <input type="text" name=""
								class="form-control boxBorder boxHeight" readonly
								id="SavedFileName" />
						</div>
					</div>
					<a href="./Consignment/sampleFileDownload/filetype=sample"
						style="margin-left: 10px;"><spring:message code="input.downlaod.sample" /></a><br> <br>

					<div class="row" id="samplefileDiv12"
						style="display: none; margin-left: 05px;">
						<div style="display: inline-flex">
							<a href="#" id="simDevice12"><spring:message code="input.IMEIdual" /></a><br>
							<br> <a href="#" style="margin-left: 75px;"
								id="rangeDevice12"><spring:message code="input.IMEIRange" /></a><br> <br>
						</div>
					</div>
					<span style="margin-left: 10px;"> <spring:message code="input.requiredfields" /><span class="star">*</span>
					</span>

					<div class="row" style="margin-bottom: 30px;">
						<div class="input-field col s12 center">
							<button class="modal-close modal-trigger btn"
								onclick="fileRecoveryReport()" data-target="markAsRecoverDone"
								style="margin-right: 10px;"><spring:message code="button.submit" /></button>

							<button class="btn " onclick="closeRecoveryModalModal()"><spring:message code="button.cancel" /></button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="editRecoveryFileModal" class="modal">
		<div class="modal-content">
			<div class="row">
				<div class="col s12 m12">
					<h6>
						<spring:message code="input.Updaterequestid" /> ( <span
							id="editFileRecoveryTxnId"></span> ).
					</h6>
					
					<div class="row">
						<h6 style="color: #000;">
							 <spring:message code="input.bulkdevice" /><span class="star">*</span>
						</h6>

						<div class="file-field input-field col s12 m6">
							<div class="btn" style="height: 35px; line-height: 2.5rem;">
								<span><spring:message code="input.selectfile" /></span> <input type="text"
									id="editFileRecoveryId" style="display: none;"> <input
									type="file" id="editRecoveryCsvUploadFile" accept=".csv">
							</div>
							<div class="file-path-wrapper">
								<input class="file-path validate" type="text">
							</div>
						</div>

	<div class="input-field col s12 m6">
					<input type="text" name="quantity" id="editRecoveryQuantity" /> <label
						for="editRecoveryQuantity" class="center-align"><spring:message code="input.quantity" /></label>
				</div>
				
						<div class="col s12 m6 l6" id="SavedFileNameDiv"
							style="display: none">
							<label for="SavedFileNameDiv" class="center-align"><spring:message code="input.UploadedFile" /></label> <input type="text" name=""
								class="form-control boxBorder boxHeight" readonly
								id="SavedFileName" />
						</div>
					</div>
					<a href="./Consignment/sampleFileDownload/filetype=sample"
						style="margin-left: 10px;"><spring:message code="input.downlaod.sample" /></a><br> <br>

					<div class="row" id="samplefileDiv12"
						style="display: none; margin-left: 05px;">
						<div style="display: inline-flex">
							<a href="#" id="simDevice12"><spring:message code="input.IMEIdual" /></a><br>
							<br> <a href="#" style="margin-left: 75px;"
								id="rangeDevice12"><spring:message code="input.IMEIRange" /></a><br> <br>
						</div>
					</div>
					<span style="margin-left: 10px;"><spring:message code="input.requiredfields" /> <span class="star">*</span>
					</span>

					<div class="row" style="margin-bottom: 30px;">
						<div class="input-field col s12 center">
							<button class="modal-close modal-trigger btn"
								onclick="updatefileStolenReport()" style="margin-right: 10px;"><spring:message code="button.submit" /></button>

							<button class="btn " onclick="closeEditRecoveryModal()"><spring:message code="button.cancel" /></button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div id="markAsRecoverDone" class="modal">
		<div class="modal-content">
			<h6 class="modal-header"><spring:message code="button.MarkAsRecovered" /></h6>
			
			<div class="row">
				<h6><spring:message code="input.recoverrecieved" /></h6>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<a onclick="redirectToViewStolenPage()" class="modal-close btn"
						style="margin-left: 10px;"><spring:message code="modal.ok" /></a>
				</div>
			</div>
		</div>
	</div>
	<div id="markAsMultipleRecovery" class="modal">
		<div class="modal-content">

			<h6 class="modal-header"><spring:message code="modal.Recover" /></h6>
			

			<div class="row">
				<h6><spring:message code="input.recoverID" /></h6>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<a onclick="openMulipleStolenPopUp()"
						class="modal-close modal-trigger btn"><spring:message code="modal.yes" /></a>
					<button class="modal-close btn" style="margin-left: 10px;"><spring:message code="modal.no" /></button>
				</div>
			</div>
		</div>
	</div>
	<div id="markAsRecoveryDone" class="modal">
		<div class="modal-content">
			<h6 class="modal-header"><spring:message code="modal.Recover" /></h6>
			
			<div class="row">
				<h6><spring:message code="input.IDrecovered" /></h6>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<!-- <button class="modal-close btn" style="margin-left: 10px;">ok</button> -->
					<a onclick="redirectToViewStolenPage()" class="btn"><spring:message code="modal.ok" /></a>
				</div>
			</div>
		</div>
	</div>





           <div id="viewBulkBlockDeviceModal" class="modal-form" style="overflow-y: hidden;">
		<h6 id="viewModalHeader" class="modal-header"><spring:message code="modal.header.viewBlockDevices" /></h6>
		<div class="modal-content" style="margin-top: 5px;">
			
                                            <form action="#" style="margin-top: 30px;">
                                                    
                                                   <div class="row">
                                                    <div class="input-field col s12 m6" style="margin-top: 25px;">
                                                        <input type="text" id="viewBulkBlockCategory" name="Category" pattern="[0-9]"
                                                            title="" maxlength="16" value="Contract Violation" disabled>
                                                        <label for="viewBulkBlockCategory"><spring:message code="input.Category" /></label>
                                                    </div>

                                                   <div class="input-field col s12 m6" style="margin-top: 25px;">
                                                        <input type="text" id="viewBulkBlockquantity" name="quantity" pattern="[0-9]"
                                                            title="" maxlength="16" value="1500" disabled>
                                                        <label for="viewBulkBlockquantity"><spring:message code="input.quantity" /></label>
                                                    </div>
														</div>	
                                                    <div class="row">

                                                    <div class="input-field col s12 m6" style="margin-top: 25px;">
                                                        <input type="text" id="viewBulkBlockuploadFile" name="uploadFile" pattern="[0-9]"
                                                            title="" maxlength="16" value="file.csv" disabled>
                                                        <label for="viewBulkBlockuploadFile"><spring:message code="input.UploadBulk" /></label>
                                                    </div>
													
													<div class="input-field col s12 m6" style="margin-top: 25px;">
                                                        <input type="text" id="viewBulkBlockTxnId" name="viewBulkBlockTxnId" pattern="[0-9]"
                                                            title="" maxlength="16" value="1500" disabled>
                                                        <label for="viewBulkBulkTxnId"><spring:message code="input.TransactionID1" /></label>
                                                    </div>		
                                                    </div>
                                                    <div class="row">
                                                     <div class="input-field col s12 m6">
                                                        <textarea id="viewBulkBlockRemark" class="materialize-textarea" placeholder="kjdhdskjfhdskhfkdsjhf" disabled></textarea>
                                                        <label for="viewBulkBlockRemark"><spring:message code="input.Remark" /></label>
                                                    </div>	
                                                    </div>
													
                                                   


                                                    <div class="button-div col s12 center">
                                                        <button type="button" class="modal-close btn"><spring:message code="modal.close" /></button>
                                                        
                                                    </div>
                                                </form>
            </div></div>




           <div id="viewblockImeiDevice" class="modal-form" style="overflow-y: hidden;">
		<h6 id="singleBlockUnblockHeading"  class="modal-header"><spring:message code="modal.header.viewBlockDevices" /></h6>
		<div class="modal-content" style="margin-top: 5px;">
			   <form action=""  method="POST" enctype="multipart/form-data">
                                                    <div class="row">
                                                        <div class="row">
                                                       		<div class="row">
                                        					<div class="col s12 m6">
                                                                <label for="viewblockdeviceType"><spring:message code="table.devicetype" /></label>
                                                                <%-- <select class="browser-default" id="viewblockdeviceType" required="required" disabled="disabled">
                                                                    <option value="" disabled selected><spring:message code="table.devicetype" /></option> 
                                                                    
                                                                </select> --%>
                                                                
                                                                <input type="text" id="viewblockdeviceType" name="viewblockdeviceType" placeholder="" pattern="[0-9]{1,15}" required="required"
                                                                     disabled="disabled" maxlength="15">
                                                            </div>
                                                            <div class="col s12 m6"><label for="viewblockdeviceIdType"><spring:message code="select.deviceIDType" /> </label>
                                                               <%--  <select class="browser-default" id="viewblockdeviceIdType" disabled="disabled" required="required">
                                                                    <option value="" disabled selected><spring:message code="select.deviceIDType" /></option>
                                                                   
                                                                </select> --%>
                                                                <input type="text" id="viewblockdeviceIdType" name="viewblockdeviceIdType" placeholder="" pattern="[0-9]{1,15}" required="required"
                                                                     disabled="disabled" maxlength="15">
                                                            </div>
                                                            </div>
                                        					<div class="row">
                                                            <div class="col s12 m6">
                                                                <label for="viewblockmultipleSimStatus"><spring:message code="select.multiSimStatus" /></label>
                                                               <%--  <select class="browser-default" id="viewblockmultipleSimStatus" disabled="disabled" required="required">
                                                                    <option value="" disabled selected><spring:message code="select.multiSimStatus" /></option>
                                                                    
                                                                </select> --%>
                                                                <input type="text" id="viewblockmultipleSimStatus" name="viewblockmultipleSimStatus" placeholder="" pattern="[0-9]{1,15}" required="required"
                                                                     disabled="disabled" maxlength="15">
                                                            </div>
                                        
                                                            <div class="input-field col s12 m6" style="margin-top: 21px;">
                                                                <input type="text" id="viewsingleblockserialNumber" name="serialNumber" placeholder="" pattern="[0-9]{1,15}" required="required"
                                                                    title="Please enter your device serial number first" disabled="disabled" maxlength="15">
                                                                <label for="viewsingleblockserialNumber"><spring:message code="input.deviceSerialNumber" /></label>
                                                            </div>
                                                            </div>
                                                             <div class="row">
                                                            <div class="input-field col s12 m6">
                                                                <textarea id="viewsingleblockremark" disabled="disabled" placeholder="" class="materialize-textarea" required="required"></textarea>
                                                                <label for="viewsingleblockremark"><spring:message code="input.remarks" /></label>
                                                            </div>
                                                            <div class="input-field col s12 m6">
                                                                <input type="text" id="viewsingleblockCategory" name="" placeholder="" disabled="disabled">
                                                                <label for="viewsingleblockCategory"><spring:message code="operator.category" /></label>
                                                              </div> 
                                                              <div class="input-field col s12 m6">
            													<input type="text" id="viewsingleblockingType" name="" placeholder="" disabled="disabled">
                                                                <label for="viewsingleblockingType"><spring:message code="operator.blocking" /></label>		
                                                              </div>
                                                            
                                                        </div>
                                                        <div class="row">
                                                        <div class="row input_fields_wrap">
                                                            <div class="col s12 m12">
                                                                <p style="margin-bottom: 0;"><spring:message code="title.imeiMeidEsn" /></p>
                                                            </div>
                                                            <div class="input-field col s12 m6">
                                                                <input type="text" id="viewsingleblockIMEI1" name="IMEI1" placeholder="" disabled="disabled" pattern="[0-9]{15,16}" required="required"
                                                                    title="Please enter minimum 15 and maximum 16 digit only" maxlength="16">
                                                                <label for="viewsingleblockIMEI1"><spring:message code="title.one" /></label>
                                                            </div>
                                        
                                                            <div class="input-field col s12 m6">
                                                                <input type="text" id="viewsingleblockIMEI2" name="IMEI2" placeholder="" disabled="disabled" pattern="[0-9]{15,16}"
                                                                    title="Please enter minimum 15 and maximum 16 digit only" maxlength="16">
                                                                <label for="viewsingleblockIMEI2"><spring:message code="title.two" /></label>
                                                            </div>  
                                                            
                                                            <div class="input-field col s12 m6">
                                                                <input type="text" id="viewsingleblockIMEI3" name="IMEI3" placeholder="" disabled="disabled" pattern="[0-9]{15,16}"
                                                                    title="Please enter minimum 15 and maximum 16 digit only"
                                                                    maxlength="16">
                                                                <label for="viewsingleblockIMEI3"><spring:message code="title.three" /></label>
                                                            </div>
            
                                                            <div class="input-field col s12 m6">
                                                                <input type="text" id="viewsingleblockIMEI4" name="IMEI4[]" placeholder="" disabled="disabled" pattern="[0-9]{15,16}"
                                                                    title="Please enter minimum 15 and maximum 16 digit only"
                                                                    maxlength="16">
                                                                <label for="viewsingleblockIMEI4"><spring:message code="title.four" /></label>
                                                            </div>
                                                        </div>
                                                        </div>
                                                  
                                                    </div>

                                                    </div>

                                                    <div class="input-field col s12 center popup-btn-div">
                                                       <!--  <button class="btn" type="submit">Submit</button> -->
                                                         <button type="button" class="modal-close btn"><spring:message code="modal.cancel" /></button>
                                                    </div>
                                                </form>
                                           
            </div></div>
            
         <div id="editblockImeiDevice" class="modal-form" style="overflow-y: hidden;">
<h6 id="singleBlockDeviceHeading" class="modal-header">
<spring:message code="modal.UpdateBlock" />
</h6>
<div class="modal-content" style="margin-top: 5px;">
<form action="" method="POST" onsubmit="return updateSingleBlockDevicesRequest()" id="editSingleImeiform"
enctype="multipart/form-data">
<div class="row">
<div class="row">
<div class="row">
<div class="col s12 m6">
<label for="editblockdeviceType">
<spring:message code="table.devicetype" /> <span class="star">*</span></label>
<select class="browser-default" id="editblockdeviceType" required="required">
<option value="" disabled selected>
<spring:message code="table.devicetype" />
</option>

</select>
</div>
<div class="col s12 m6"><label for="editblockdeviceIdType">
<spring:message code="select.deviceIDType" /> <span class="star">*</span></label>
<select class="browser-default" id="editblockdeviceIdType" required="required">
<option value="" disabled selected>
<spring:message code="select.deviceIDType" />
</option>

</select>
</div>
</div>
<div class="row">
<div class="col s12 m6">
<label for="editblockmultipleSimStatus">
<spring:message code="select.multiSimStatus" /> <span class="star">*</span></label>
<select class="browser-default" id="editblockmultipleSimStatus" required="required">
<option value="" disabled selected>
<spring:message code="select.multiSimStatus" />
</option>

</select>
</div>

<div class="input-field col s12 m6">
<input type="text" id="editsingleblockserialNumber" name="serialNumber" placeholder=""
pattern="[A-Za-z0-9]{1,15}" required="required"
title="Please enter your device serial number first" maxlength="15">
<label for="editsingleblockserialNumber">
<spring:message code="input.deviceSerialNumber" /> <span class="star">*</span></label>
</div>
</div>
<div class="row">
<div class="input-field col s12 m6">
<textarea id="editsingleblockremark" placeholder="" class="materialize-textarea"
required="required"></textarea>
<label for="editsingleblockremark">
<spring:message code="input.remarks" /> <span class="star">*</span></label>
</div>

<div class="col s12 m6"><label for="editbulkBlockdeviceCategory">
<spring:message code="operator.category" /> <span class="star">*</span></label>
<select class="browser-default" id="editbulkBlockdeviceCategory" required="required">
<option value="" disabled selected>
<spring:message code="operator.selectcategory" />
</option>

</select>
</div>
</div>
<div class="row">
<div class="col s12 m6">
<spring:message code="operator.blocking" /> <label style="margin-right: 2%;"> <input
type="radio" name="editbulkBlockdeviceradio" class="blocktypeRadio" id=""
value="Immediate"
onchange="document.getElementById('calender').style.display = 'none';"
name="stolenBlockPeriod">
<spring:message code="operator.immediate" />
</label> <label style="margin-right: 2%;"> <input type="radio"
name="editbulkBlockdeviceradio" class="blocktypeRadio" value="Default"
onchange="document.getElementById('calender').style.display = 'none';"
name="stolenBlockPeriod">
<spring:message code="operator.default" />
</label> <label> <input type="radio" name="editbulkBlockdeviceradio" required="required"
value="tilldate" class="blocktypeRadio"
onchange="document.getElementById('calender').style.display = 'block';"
name="stolenBlockPeriod">
<spring:message code="operator.later" /></label>

</div>
<div class="col s6 m6 responsiveDiv" style="display: none;" id="calender">
<div id="startdatepicker" class="input-group date">
<p> Blocking Time Period </p>
<input type="text" id="stolenDatePeriodedit" style="margin-top: -9px" /> <span
class="input-group-addon" style="color: #ff4081"><i class="fa fa-calendar"
aria-hidden="true" style="float: right; margin-top: -30px;"></i></span>
</div>
</div>

<!-- 
<div class="input-field col s6 m6 responsiveDiv" style="display: block;" id="calender">
<div id="startdatepicker" class="input-group date">
<input type="text" id="stolenDatePeriodedit" placeholder="" style="margin-top: -9px" class="hasDatepicker">
<label for="" class="active">Blocking Time Period</label>
<p> Blocking Time Period </p>
 <span class="input-group-addon" style="color: #ff4081">
    
    <i class="fa fa-calendar" aria-hidden="true" style="float: right; margin-top: -30px;"></i></span>
</div>
</div> -->

<div class="col s12 m6 l6" style="display: none" id="stolenDate">

<label for="TotalPrice" class="center-align">
<spring:message code="operator.tilldate" /></label>
<div id="startdatepicker" class="input-group" style="margin-top: 10px;">

<input class="form-control" placeholder="" name="inputsaves" type="text" id="startDateFilter"
readonly /> <span class="input-group-addon" style="color: #ff4081"><i
class="glyphicon glyphicon-calendar"
onclick="_Services._selectstartDate()"></i></span>
</div>
</div>
</div>
</div>
<div class="row">
<div class="row input_fields_wrap">
<div class="col s12 m12">
<p style="margin-bottom: 0;">
<spring:message code="title.imeiMeidEsn" />
</p>
</div>
<div class="input-field col s12 m6">
<input type="text" id="editsingleblockIMEI1" name="IMEI1" placeholder=""
pattern="[0-9]{15,16}" required="required"
title="Please enter minimum 15 and maximum 16 digit only" maxlength="16">
<label for="editsingleblockIMEI1">
<spring:message code="title.one" /> <span class="star">*</span></label>
</div>

<div class="input-field col s12 m6">
<input type="text" id="editsingleblockIMEI2" name="IMEI2" placeholder=""
pattern="[0-9]{15,16}" title="Please enter minimum 15 and maximum 16 digit only"
maxlength="16">
<label for="editsingleblockIMEI2">
<spring:message code="title.two" /></label>
</div>

<div class="input-field col s12 m6">
<input type="text" id="editsingleblockIMEI3" name="IMEI3" placeholder=""
pattern="[0-9]{15,16}" title="Please enter minimum 15 and maximum 16 digit only"
maxlength="16">
<label for="editsingleblockIMEI3">
<spring:message code="title.three" /></label>
</div>

<div class="input-field col s12 m6">
<input type="text" id="editsingleblockIMEI4" name="IMEI4[]" placeholder=""
pattern="[0-9]{15,16}" title="Please enter minimum 15 and maximum 16 digit only"
maxlength="16">
<label for="editsingleblockIMEI4">
<spring:message code="title.four" /></label>
<input type="text" id="editsingleblockTxnid" style="display: none">
<input type="text" id="editsingleblocRequestType" style="display: none">

</div>
</div>
</div>
<span>
<spring:message code="input.requiredfields" /> <span class="star">*</span></span>
</div>




<div class="input-field col s12 center popup-btn-div">
<button class="btn" type="submit">
<spring:message code="button.update" /></button>

<button type="button" class="modal-close btn popup-btn-div" onclick="singleImeiFormClear()">
<spring:message code="button.cancel" /></button>

</div>
</form>
</div>
</div>
         
         
            <div id="editBulkBlockDeviceModal" class="modal" style="overflow-y: hidden;">
<h6 id="editblockHeading" class="modal-header"><spring:message code="modal.EditDevice" /></h6>
<div class="modal-content" style="margin-top: 5px;">

<form action="" onsubmit="return updateBulkDevice()" method="post" style="margin-top: 30px;">

<div class="row">
<div class="col s12 m6" style="margin-top: -7px;">

<label for="editBulkBlockCategory"><spring:message code="operator.category" /><span class="star">*</span></label>
<select class="browser-default" id="editBulkBlockCategory" required="required" >
<option value="" disabled selected><spring:message code="operator.selectcategory" />
</option>

</select>
</div>

<div class="input-field col s12 m6">
<input type="text" id="editBulkBlockquantity" required name="editBulkBlockquantity" pattern="[0-9]{1,9}"
title="Please enter numbers upto 9 characters only" maxlength="9" value="" placeholder="" >
<label for="editBulkBlockquantity"><spring:message code="input.quantity" /> <span class="star">*</span></label>
</div>


</div>
<div class="row">
<div class="file-field input-field col s12 m6" style="margin-top: -10px;">
<p style="color: #000; margin: 5px 0;"><spring:message code="input.UploadBulk" /> <span class="star">*</span></p>
<div class="btn">
<span><spring:message code="operator.file" /></span>
<input type="file" id="editselectBulkBlockuploadFile">
</div>
<div class="file-path-wrapper">
<input class="file-path validate" required="required" type="text" id="editBulkBlockuploadFile" placeholder="">
</div>
</div>

<div class="input-field col s12 m6">
<textarea id="editBulkBlockRemark" class="materialize-textarea" required="required" placeholder="" ></textarea>
<label for="editBulkBlockRemark"><spring:message code="input.Remark" /> <span class="star">*</span> </label>
<!-- <input type="text" id="editBulkBlockTxnId" name="editBulkBlockTxnId" pattern="[0-9]"
title="" maxlength="16" value="1500" disabled> -->

</div>
<input type="text" style="display:none" id="editBulkBlockrequestType">
<input type="text" style="display:none" id="editBulkBlockTxnId">
<!-- <div class="input-field col s12 m6" style="margin-top: 25px;">
<input type="text" id="editBulkBlockTxnId" name="editBulkBlockTxnId" pattern="[0-9]"
title="" maxlength="16" value="1500" disabled>
<label for="editBulkBlockTxnId">Transaction Id</label>
<input type="text" style="display:none" id="editBulkBlockrequestType">
</div> -->
</div>
<p style="margin-left: 10px;"><a href="./Consignment/sampleFileDownload/7"><spring:message code="input.downlaod.sample" /></a></p>
<div class="row">
<div class="input-field col s12 center">
<button class=" btn"
type="submit" ><spring:message code="button.update" /></button>
<button type="button" class="modal-close btn"><spring:message code="button.cancel" /></button>
</div>
</div>
</form>
</div></div>
 <div id="confirmEditBlockUnblock" class="modal">
  <h6 class="modal-header"><spring:message code="input.UpdateDevice" /></h6>
        <div class="modal-content">
           
            <div class="row">
                <h6><spring:message code="input.Theupdated" /></h6>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <div class="input-field col s12 center">
                        <a  href ="./stolenRecovery" class=" btn"><spring:message code="modal.ok" /></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <!------------------------------------------ Approve Model ----------------------------------->
    
    
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
                     <a class="btn modal-close" href="./stolenRecovery"><spring:message code="modal.ok" /></a>
                   
                </div>
            </div>
        </div>
    </div>
    
    
    <!--------------------------------------------------- Reject Model--------------------------------------------->
    
        <div id="rejectInformation" class="modal">
           <h6 class="modal-header"><spring:message code="modal.header.reject" /></h6>
            <div class="modal-content">
            <div class="row">
             <h6><spring:message code="modal.rejectRequest" /><span id="rejectTxnId"></span> ?</h6>
                <form action="">
                
                    <div class="input-field" style="margin-top: 30px;">
                        <textarea id="Reason" class="materialize-textarea"></textarea>
                        <label for="textarea1" style="margin-left: -10px;"><spring:message code="lable.reason" /></label>
                    </div>
                   
                    
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
                  
                    <h6><spring:message code="modal.deviceRejected" /></h6>
                </form>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <a class="btn modal-close" href="./stolenRecovery"><spring:message code="modal.ok" /></a>
                </div>
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
		<script type="text/javascript" src="${context}/resources/project_js/globalVariables.js"></script>
<script type="text/javascript"
		src="${context}/resources/project_js/backbutton.js"></script>
	<script type="text/javascript"
		src="${context}/resources/project_js/dragableModal.js"></script>	
			<script type="text/javascript"
		src="${context}/resources/project_js/enterKey.js"></script>
	<script type="text/javascript"
		src="${context}/resources/project_js/stolenRecovery.js"></script>
	<script type="text/javascript"
		src="${context}/resources/project_js/reportBlock.js"></script>			

</body>
</html>