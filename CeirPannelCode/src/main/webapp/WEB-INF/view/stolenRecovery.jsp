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

</head>

<body data-roleType="${usertype}" data-userTypeID="${usertypeId}"
	data-userID="${userid}"
	data-selected-roleType="${stolenselectedUserTypeId}"
	data-stolenselected-roleType="${stolenselectedUserTypeId}">


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
			<h6 class="modal-header">View Consignment</h6>
			
			<div class="modal-content">

		


			<div class="row myRow">
				<div class="input-field col s12 m6">
					<input type="text" name="name" id="supplierId"
						placeholder="Supplier/Manufacturer ID" readonly="readonly" /> <label
						for="Name" class="center-align">Supplier/Manufacturer ID</label>
				</div>

				<div class="input-field col s12 m6">
					<input type="text" name="name" id="supplierName"
						placeholder="Supplier/Manufacturer Name" readonly="readonly" /> <label
						for="Name" class="center-align">Supplier/Manufacturer Name</label>
				</div>
				<div class="input-field col s12 m6">
					<input type="text" name="name" id="consignmentNumber"
						placeholder="Consignment Number" readonly="readonly" /> <label
						for="Name" class="center-align">Consignment Number</label>
				</div>

				<div class="input-field col s12 m6" style="color: #c4c4c4;">
					<p style="margin-top: -5px; margin-bottom: -13px; font-size: 12px;">Expected
						Arival Date</p>
					<!-- <label for="Name" class="center-align">Expected Dispatch Date</label> -->
					<input type="date" id="expectedArrivaldate"
						placeholder="Expected Arival Date" readonly="readonly"> <span
						class="input-group-addon" style="color: #ff4081"><i
						class="fa fa-calendar" aria-hidden="true"></i></span>
				</div>
				<div class="input-field col s12 m6">
					<input type="text" id="countryview" class="browser-default"
						readonly="readonly" class="mySelect"
						placeholder="Device Origination Counrty*"> <label
						for="Name" class="center-align"> Origination Country</label> <label
						for="countryview" class="center-align"></label>
				</div>


				<div class="input-field col s12 m6">
					<p class="input-text-date" style="color: #c4c4c4;">Expected
						Dispatch Date</p>
					<!-- <label for="Name">Expected arrival Date</label> -->
					<input type="date" id="expectedDispatcheDate"
						placeholder="Expected Dispatch Date" readonly="readonly">
					<span class="input-group-addon" style="color: #ff4081"><i
						class="fa fa-calendar" aria-hidden="true"></i></span>
				</div>
				<div class="input-field col s12 m6">
					<!-- <label for="Name" class="center-align">Expected arrival port</label> -->
					<input type="text" id="expectedArrivalPort" readonly="readonly"
						placeholder="Arrival port"> <label for="Name"
						class="center-align">Expected Arrival Port</label>
				</div>

				<div class="input-field col s12 m6">
					<input type="text" name="Quantity" placeholder="Quantity"
						id="Quantity" readonly="readonly" /> <label for="Quantity"
						class="center-align">Quantity</label>
				</div>

				<div class="input-field col s12 m6">
					<input type="text" name="TransactionId"
						placeholder="Transaction ID" id="TransactionId"
						readonly="readonly" maxlength="15" /> <label for="TransactionId"
						class="center-align">Transaction ID</label>
				</div>

				<div class="input-field col s12 m6">
					<textarea id="remark" class="materialize-textarea"
						style="height: 0px;" readonly="readonly"></textarea>
					<label for="remark" class="">Remarks</label>

					<!--   <input type="textarea" name="Remark" placeholder="Remark" id="remark" readonly="readonly" maxlength="15" />
                                               <label for="TransactionId" class="center-align">Remark</label> -->
				</div>
			</div>

			<div class="row" style="padding: 20px 0 100px 0;">
				<div class="input-field col s12 center">
					<button class="btn" onclick="closeViewModal()"
						class="modal-close btn" id="add_user">Cancel</button>
				</div>
			</div>


		</div>
	</div>
	<!-- Modal End -->


	<!--Delete Modal start   -->

	<div id="DeleteConsignment" class="modal">
		<h6 class="modal-header">Delete</h6>
		<div class="modal-content">
	<div class="row">
				<h6>
					Are you sure you want to withdraw the consignment details for (<span
						id="transID"></span>)
				</h6>
				<span id="setStolenRecoveyRowId" style="display: none;"></span>
			</div>
			<input type="text" id="popupTransactionId" maxlength="15" hidden />
			<div class="row">
				<div class="input-field col s12 center">
					<div class="input-field col s12 center">
						<a class="btn" onclick="confirmantiondelete()">ok</a>
						<button class="modal-close btn" onclick="closeUpdateModal()"
							style="margin-left: 10px;">No</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal End -->
	<!-- END CONTENT -->




	<!-- Modal 1 start   -->

	<div id="updateConsignment" class="modal">
			<h6>Update Consignment</h6>
			<div class="modal-content">
		
			

			<div class="row">
				<h6 id="sucessMessage"></h6>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<a href="${context}/Consignment/viewConsignment" class="btn">ok</a>
				</div>
			</div>
		</div>
	</div>





	<!-- Delete confirmation Modal start   -->

	<div id="confirmDeleteConsignment" class="modal">
		<h6 class="modal-header">Delete Consignment</h6>
		<div class="modal-content">

			
			
			<!-- <h4 class="header2 pb-2">User Info</h4> -->

			<div class="row">
				<h6 id=consignmentText></h6>
			</div>

			<div class="row">
				<div class="input-field col s12 cent\er">
					<div class="input-field col s12 center">
						<a href="" class="btn">ok</a>
					</div>
				</div>
			</div>
		</div>
	</div>



	<!-- Update Modal Start -->
	<div id="updateModal" class="modal-form" style="overflow-y: hidden;">
			<h6 class="modal-header">Edit Consignment</h6>
			<div class="modal-content">

		
			


			<div class="row myRow">
				<div class="input-field col s12 m6">
					<input type="text" name="supplierId" id="supplierIdEdit"
						pattern="[A-Za-z0-9]{0,15}"
						title="Please enter alphabets and numbers upto 15 characters only"
						placeholder="Supplier/Manufacturer ID" maxlength="15" /> <label
						for="Name" class="center-align">Supplier/Manufacturer ID</label>
				</div>

				<div class="input-field col s12 m6">
					<input type="text" name="supplierName" id="supplierNameEdit"
						pattern="[A-Za-z]{0,50}"
						title="Please enter alphabets  upto 50 characters only"
						maxlength="50" placeholder="Supplier/Manufacturer Name" required />
					<label for="Name" class="center-align">Supplier/Manufacturer
						Name <span class="star">*</span>
					</label>
				</div>
				<div class="input-field col s12 m6">
					<input type="text" name="consignmentNumber"
						id="consignmentNumberEdit" pattern="[A-Za-z0-9]{0,15}"
						placeholder="Consignment Number" maxlength="15" /> <label
						for="Name" class="center-align">Consignment Number</label>
				</div>

				<div class="input-field col s12 m6">
					<!-- <p style="margin-top: -5px; margin-bottom: -13px; font-size: 12px;">Expected
                                                Arrival Date <span class="star">*</span></p> -->
					<!-- <label for="Name" class="center-align">Expected Dispatch Date</label> -->
					<input name="expectedDispatcheDate" id="expectedDispatcheDateEdit"
						required="required" placeholder="Expected Dispatch Date "
						type="text" onfocus="(this.type='date')"
						onfocusout="(this.type='text')"> <label for="dispatchDate"
						class="center-align">Expected Dispatch Date <span
						class="star">*</span></label> <span class="input-group-addon"
						style="color: #ff4081"><i class="fa fa-calendar"
						aria-hidden="true"></i></span>
				</div>
				<div class="input-field col s12 m6">
					<!-- <p style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">Device Origination Country <span class="star">*</span></p> -->
					<select id="country" name="organisationcountry" required="required"
						class="browser-default" class="mySelect" required></select> <label
						for="country" class="center-align"></label>
				</div>


				<div class="input-field col s12 m6">
					<!-- <p class="input-text-date">Expected Dispatch Date <span class="star">*</span></p> -->
					<!-- <label for="Name">Expected arrival Date</label> -->
					<input name="expectedArrivalDate" id="expectedArrivaldateEdit"
						required="required" placeholder="Expected Arrival  Date"
						type="text" onfocus="(this.type='date')"
						onfocusout="(this.type='text')"> <label for="dispatchDate"
						class="center-align">Expected Arrival Date <span
						class="star">*</span></label> <span class="input-group-addon"
						style="color: #ff4081"><i class="fa fa-calendar"
						aria-hidden="true"></i></span>
				</div>
				<div class="input-field col s12 m6">
					<!-- <label for="Name" class="center-align">Expected arrival port</label> -->
					<!-- <p style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">Expected arrival port <span class="star">*</span></p> -->
					<select name="expectedArrivalPort" id="expectedArrivalPortEdit"
						class="browser-default" required>
						<option value="" disabled selected>Expected arrival port
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
						placeholder="Quantity" required /> <label for="Quantity"
						class="center-align">Quantity <span class="star">*</span></label>
				</div>

				<div class="input-field col s12 m6">
					<input type="text" name="txnId" id="TransactionIdEdit"
						placeholder="Transaction ID" value="" readonly maxlength="15" />
					<label for="TransactionId" class="center-align">Transaction
						ID</label>
				</div>
			</div>


			<div class="row myRow">
				<div class="file-field input-field col s12 m6"
					style="margin-top: 5px;">
					<h6 class="file-upload-heading" style="margin-left: 0;">
						Upload Bulk Devices Information <span class="star">*</span>
					</h6>
					<div class="btn">
						<span>Select File</span> <input type="file" name="file"
							id="csvUploadFile" accept=".csv">
					</div>
					<div class="file-path-wrapper">
						<input class="file-path validate responsive-file-div"
							id="fileNameEdit" type="text">
					</div>
				</div>


			</div>
			<p>
				<a href="#">Download Sample Format</a>
			</p>

			<span> Required Field are marked with <span class="star">*</span>
			</span>


			<div class="row">
				<div class="input-field col s12 center">
					<button class="waves-effect waves-light modal-trigger btn"
						type="button" onclick="editRegisterConsignment()">Update</button>
					<button class="modal-close btn" onclick="closeUpdateModal()"
						style="margin-left: 10px;">Cancel</button>
				</div>
			</div>

		</div>
	</div>

	<div id="markAsStolen" class="modal">
		<h6 class="modal-header">Mark As Stolen</h6>
		<div class="modal-content">

			
			

			<div class="row">
				<h6>The following devices marked as stolen has been received
					successfully.</h6>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<a onclick="redirectToViewStolenPage()" class="modal-close btn"
						style="margin-left: 10px;">ok</a>
				</div>
			</div>
		</div>
	</div>

	<div id="updateMarkAsStolen" class="modal">
		<h6 class="modal-header">Mark As Stolen</h6>
		<div class="modal-content">

			
			

			<div class="row">
				<h6 id="editMessageTextStoleRecovery"></h6>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<a href="" class="modal-close btn" style="margin-left: 10px;">ok</a>
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
<h6 class="modal-header">Report Stolen/Recovery</h6>
			<div class="col s12 m12 modal-content">
				
				<div class="row">
					<form action="#">
						<h5 class="center">
							<label> <input name="group1" type="radio"
								onclick="document.getElementById('stolendiv').style.display ='block'; document.getElementById('recoverydiv').style.display ='none';" />
								<span class="checkboxFont"> Stolen</span>
							</label> <label> <input name="group1" type="radio"
								onclick="document.getElementById('recoverydiv').style.display ='block'; document.getElementById('stolendiv').style.display ='none';" />
								<span class="checkboxFont"> Recovery</span>
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
											class="checkboxFont"> Choose from consignment</span>
										</label>

										<label> <input name="group1" type="radio"
											class="chooseStock" onclick="pickstock()" /> <span
											class="checkboxFont"> Choose from the stock</span>
										</label>

										<label> <input name="group1" type="radio"
											onclick="openFileStolenModal()"
											class="modal-trigger modal-close" /> <span
											class="checkboxFont"> Upload Bulk Devices</span>
										</label>
									</c:when>
									<c:otherwise>
										<label> <input name="group1" type="radio"
											class="chooseStock" onclick="pickstock()" /> <span
											class="checkboxFont"> Choose from the stock</span>
										</label>

										<label> <input name="group1" type="radio"
											onclick="openFileStolenModal()"
											class="modal-trigger modal-close" /> <span
											class="checkboxFont"> Upload Bulk Devices</span>
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
									class="checkboxFont"> Choose from existing</span>
								</label> <label> <input name="group1" type="radio"
									onclick="openRecoveryModal()" data-target="recoveryDiv1"
									class="modal-trigger modal-close" /> <span
									class="checkboxFont"> Upload Bulk Devices</span>
								</label>
							</h5>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>


	<div id="fileStolenModal" class="modal">
		<h6 class="modal-header">Mark As Stolen</h6>
		<div class="modal-content">
			<div class="row">
				<div class="col s12 m12">
					
					
					<div class="row">
						<h6 style="color: #000;">
							Upload Bulk Devices Information <span class="star">*</span>
						</h6>

						<div class="file-field input-field col s12 m6">
							<div class="btn" style="height: 35px; line-height: 2.5rem;">
								<span>Select File</span> <input type="file"
									id="stolenCsvUploadFile" accept=".csv">
							</div>
							<div class="file-path-wrapper">
								<input class="file-path validate" type="text">
							</div>
						</div>

						<div class="input-field col s12 m6">
							<input type="text" name="stolenQuantity" id="stolenQuantity">
							<label for="stolenQuantity" class="center-align">Quantity</label>
						</div>
					</div>
				</div>


				<div class="col s12 m6 l6" id="SavedFileNameDiv"
					style="display: none">
					<label for="TotalPrice" class="center-align">Uploaded File</label>
					<input type="text" name="" class="form-control boxBorder boxHeight"
						readonly id="SavedFileName" />
				</div>
			</div>
			<a href="./Consignment/sampleFileDownload/filetype=sample"
				style="margin-left: 10px;">Download Sample Format</a><br> <br>

			<div class="row" id="samplefileDiv3"
				style="display: none; margin-left: 05px;">
				<div style="display: inline-flex">
					<a href="#" id="simDevice3">IMEI dual SIM device entry</a><br>
					<br> <a href="#" style="margin-left: 75px;" id="rangeDevice3">IMEI
						Range Device entry</a><br> <br>
				</div>
			</div>
			<div style="margin-left: 36%; margin-top: -25px;">
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
			</div>
			<div class="col s12 m12">
				<p style="margin-left: 10px;">
					Required Field are marked with <span class="star">*</span>
				</p>
			</div>

			<div class="row" style="margin-bottom: 30px;">
				<div class="input-field col s12 center">
					<a onclick="fileStolenReport()"
						class="modal-close modal-trigger btn" style="margin-right: 10px;">Submit</a>

					<button class="btn" onclick="closeStolenModalModal()">Cancel</button>
				</div>
			</div>
		</div>
	</div>

	<div id="editFileStolenModal" class="modal">
		<h6 class="modal-header">
						Update Stolen request for this transaction id ( <span
							id="editFileStolenTxnId"></span> ).
					</h6>
					<div class="modal-content">
			<div class="row">
				<div class="col s12 m12">
					
					<input type="text" id="editFileStolenRequestType"
						style="display: none;">
					
					<div class="row">
						<h6 style="color: #000;">
							Upload Bulk Devices Information <span class="star">*</span>
						</h6>

						<div class="file-field input-field col s12 m6">
							<div class="btn" style="height: 35px; line-height: 2.5rem;">
								<span>Select File</span> <input type="text"
									id="editFileStolenId" style="display: none;"> <input
									type="file" id="editStolenCsvUploadFile" accept=".csv">
							</div>
							<div class="file-path-wrapper">
								<input class="file-path validate" type="text">
							</div>
						</div>
						<div class="input-field col s12 m6">
					<input type="text" name="quantity" id="editStolenQuantity" /> <label
						for="quantity" class="center-align">Quantity</label>
				</div>
					</div>
					
				</div>

				

				<div class="col s12 m6 l6" id="SavedFileNameDiv"
					style="display: none">
					<label for="TotalPrice" class="center-align">Uploaded File</label>
					<input type="text" name="" class="form-control boxBorder boxHeight"
						readonly id="SavedFileName" />
				</div>


			</div>
			<a href="./Consignment/sampleFileDownload/filetype=sample"
				style="margin-left: 10px;">Download Sample Format</a><br> <br>

			<div class="row" id="samplefileDiv3"
				style="display: none; margin-left: 05px;">
				<div style="display: inline-flex">
					<a href="#" id="simDevice3">IMEI dual SIM device entry</a><br>
					<br> <a href="#" style="margin-left: 75px;" id="rangeDevice3">IMEI
						Range Device entry</a><br> <br>
				</div>
			</div>
			<div style="margin-left: 36%; margin-top: -25px;">
				<label style="margin-right: 2%;"> <input type="radio" id=""
					value="Immediate"
					onclick="document.getElementById('calender').style.display = 'none';"
					name="editStolenBlockPeriod" checked> Immediate
				</label> <label style="margin-right: 2%;"> <input type="radio"
					value="Default"
					onclick="document.getElementById('calender').style.display = 'none';"
					name="editStolenBlockPeriod"> Default
				</label> <label> <input type="radio" value="tilldate"
					onclick="document.getElementById('editFilecalender').style.display = 'block';"
					name="editStolenBlockPeriod"> Later
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
			</div>
			<div class="col s12 m12">
				<p style="margin-left: 10px;">
					Required Field are marked with <span class="star">*</span>
				</p>
			</div>

			<div class="row" style="margin-bottom: 30px;">
				<div class="input-field col s12 center">
					<a onclick="updatefileStolenReport()"
						class="modal-close modal-trigger btn" style="margin-right: 10px;">Submit</a>

					<button class="btn" onclick="closeEditStolenRecoveryModal()">Cancel</button>
				</div>
			</div>
		</div>
	</div>


	<div id="recoveryFileModal" class="modal">
		<div class="modal-content">
			<div class="row">
				<div class="col s12 m12">
					<h6 class="modal-header">Mark As Recovered</h6>
					
					<div class="row">
						<h6 style="color: #000;">
							Upload Bulk Devices Information <span class="star">*</span>
						</h6>

						<div class="file-field input-field col s12 m6">
							<div class="btn" style="height: 35px; line-height: 2.5rem;">
								<span>Select File</span> <input type="file"
									id="recoveryCsvUploadFile" accept=".csv">
							</div>
							<div class="file-path-wrapper">
								<input class="file-path validate" type="text">
							</div>
						</div>

						<div class="input-field col s12 m6">
							<input type="text" name="quantity" id="recoverQuantity">
							<label for="quantity" class="center-align">Quantity</label>
						</div>


						<div class="col s12 m6 l6" id="SavedFileNameDiv"
							style="display: none">
							<label for="TotalPrice" class="center-align">Uploaded
								File</label> <input type="text" name=""
								class="form-control boxBorder boxHeight" readonly
								id="SavedFileName" />
						</div>
					</div>
					<a href="./Consignment/sampleFileDownload/filetype=sample"
						style="margin-left: 10px;">Download Sample Format</a><br> <br>

					<div class="row" id="samplefileDiv12"
						style="display: none; margin-left: 05px;">
						<div style="display: inline-flex">
							<a href="#" id="simDevice12">IMEI dual SIM device entry</a><br>
							<br> <a href="#" style="margin-left: 75px;"
								id="rangeDevice12">IMEI Range Device entry</a><br> <br>
						</div>
					</div>
					<span style="margin-left: 10px;"> Required Field are marked
						with <span class="star">*</span>
					</span>

					<div class="row" style="margin-bottom: 30px;">
						<div class="input-field col s12 center">
							<button class="modal-close modal-trigger btn"
								onclick="fileRecoveryReport()" data-target="markAsRecoverDone"
								style="margin-right: 10px;">Submit</button>

							<button class="btn " onclick="closeRecoveryModalModal()">Cancel</button>
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
						Update Recovery request for this transaction id ( <span
							id="editFileRecoveryTxnId"></span> ).
					</h6>
					
					<div class="row">
						<h6 style="color: #000;">
							Upload Bulk Devices Information <span class="star">*</span>
						</h6>

						<div class="file-field input-field col s12 m6">
							<div class="btn" style="height: 35px; line-height: 2.5rem;">
								<span>Select File</span> <input type="text"
									id="editFileRecoveryId" style="display: none;"> <input
									type="file" id="editRecoveryCsvUploadFile" accept=".csv">
							</div>
							<div class="file-path-wrapper">
								<input class="file-path validate" type="text">
							</div>
						</div>

	<div class="input-field col s12 m6">
					<input type="text" name="quantity" id="editRecoveryQuantity" /> <label
						for="quantity" class="center-align">Quantity</label>
				</div>
				
						<div class="col s12 m6 l6" id="SavedFileNameDiv"
							style="display: none">
							<label for="TotalPrice" class="center-align">Uploaded
								File</label> <input type="text" name=""
								class="form-control boxBorder boxHeight" readonly
								id="SavedFileName" />
						</div>
					</div>
					<a href="./Consignment/sampleFileDownload/filetype=sample"
						style="margin-left: 10px;">Download Sample Format</a><br> <br>

					<div class="row" id="samplefileDiv12"
						style="display: none; margin-left: 05px;">
						<div style="display: inline-flex">
							<a href="#" id="simDevice12">IMEI dual SIM device entry</a><br>
							<br> <a href="#" style="margin-left: 75px;"
								id="rangeDevice12">IMEI Range Device entry</a><br> <br>
						</div>
					</div>
					<span style="margin-left: 10px;"> Required Field are marked
						with <span class="star">*</span>
					</span>

					<div class="row" style="margin-bottom: 30px;">
						<div class="input-field col s12 center">
							<button class="modal-close modal-trigger btn"
								onclick="updatefileStolenReport()" style="margin-right: 10px;">Submit</button>

							<button class="btn " onclick="closeEditRecoveryModal()">Cancel</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div id="markAsRecoverDone" class="modal">
		<div class="modal-content">
			<h6 class="modal-header">Mark As Recovered</h6>
			
			<div class="row">
				<h6>The following devices marked as recover has been recieved
					successfully.</h6>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<a onclick="redirectToViewStolenPage()" class="modal-close btn"
						style="margin-left: 10px;">ok</a>
				</div>
			</div>
		</div>
	</div>
	<div id="markAsMultipleRecovery" class="modal">
		<div class="modal-content">

			<h6 class="modal-header">Mark As Recover</h6>
			

			<div class="row">
				<h6>Do you want to recover the devices for the following
					transaction ID ?</h6>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<a onclick="openMulipleStolenPopUp()"
						class="modal-close modal-trigger btn">Yes</a>
					<button class="modal-close btn" style="margin-left: 10px;">no</button>
				</div>
			</div>
		</div>
	</div>
	<div id="markAsRecoveryDone" class="modal">
		<div class="modal-content">
			<h6 class="modal-header">Mark As Recover</h6>
			
			<div class="row">
				<h6>The following Transaction ID's devices marked as
					recovered..</h6>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<!-- <button class="modal-close btn" style="margin-left: 10px;">ok</button> -->
					<a onclick="redirectToViewStolenPage()" class="btn">ok</a>
				</div>
			</div>
		</div>
	</div>
	            <div id="editBulkBlockDeviceModal" class="modal" style="overflow-y: hidden;">
		<h6 class="modal-header">Edit Device Information</h6>
		<div class="modal-content" style="margin-top: 5px;">
			
                                            <form action="#" style="margin-top: 30px;">
                        
                        <div class="row">
                                          <div class="input-field col s12 m6" style="margin-top: 25px;">
                                                       <!--  <input type="text" id="editBulkBlockCategory" name="editBulkBlockCategory" pattern="[0-9]"
                                                            title="" maxlength="16" value="Contract Violation" >
                                                        <label for="editBulkBlockCategory">Category</label> -->
                                                         <select class="browser-default" id="editBulkBlockCategory" required="required" >
                                                            <option value="" disabled selected>Select Category
                                                            </option>
                                                            
                                                        </select>
                                                    </div>

                                                    <div class="input-field col s12 m6" style="margin-top: 25px;">
                                                        <input type="text" id="editBulkBlockquantity" name="editBulkBlockquantity" pattern="[0-9]"
                                                            title="" maxlength="16" value="" placeholder="" >
                                                        <label for="editBulkBlockquantity">Quantity</label>
                                                    </div>
                                                    

                                                   </div> 
                             <div class="row">
                                                   <div class="file-field input-field col s12 m6" style="margin-top: 21px;">
                                                        <p style="color: #000;">Upload Bulk Devices <span class="star">*</span></p>
                                                        <div class="btn">
                                                            <span>File</span>
                                                            <input type="file" id="editselectBulkBlockuploadFile" required="required">
                                                        </div>
                                                        <div class="file-path-wrapper">
                                                            <input class="file-path validate" type="text" id="editBulkBlockuploadFile" placeholder="">
                                                        </div>
                                                    </div>
                                                    
                                                    <div class="input-field col s12 m6">
                                                        <textarea id="editBulkBlockRemark" class="materialize-textarea" placeholder="" ></textarea>
                                                        <label for="editBulkBlockRemark">Remark </label>
                                                   <!--      <input type="text" id="editBulkBlockTxnId" name="editBulkBlockTxnId" pattern="[0-9]"
                                                            title="" maxlength="16" value="1500" disabled> -->
                                                            <input type="text" style="display:none" id="editBulkBlockrequestType">
                                                            <input type="text" style="display:none" id="editBulkBlockTxnId">
                                                    </div>

                                                  <!--    <div class="input-field col s12 m6" style="margin-top: 25px;">
                                                        <input type="text" id="editBulkBlockTxnId" name="editBulkBlockTxnId" pattern="[0-9]"
                                                            title="" maxlength="16" value="1500" disabled>
                                                        <label for="editBulkBlockTxnId">Transaction Id</label>
                                                        <input type="text" style="display:none" id="editBulkBlockrequestType">
                                                    </div> -->
                                                    </div>
                <div class="row">
                    <div class="input-field col s12 center">
                                                        	<button class=" btn"
						type="button" onclick="updateBulkDevice()">Update</button>
                                                        <a href="./stolenRecovery" class="btn">Cancel</a>
                                                    </div>
                </div>
                                                </form>
            </div></div>




           <div id="viewBulkBlockDeviceModal" class="modal-form" style="overflow-y: hidden;">
		<h6 class="modal-header">View Block Devices</h6>
		<div class="modal-content" style="margin-top: 5px;">
			
                                            <form action="#" style="margin-top: 30px;">
                                                    
                                                   <div class="row">
                                                    <div class="input-field col s12 m6" style="margin-top: 25px;">
                                                        <input type="text" id="viewBulkBlockCategory" name="Category" pattern="[0-9]"
                                                            title="" maxlength="16" value="Contract Violation" disabled>
                                                        <label for="viewBulkBlockCategory">Category</label>
                                                    </div>

                                                    <div class="input-field col s12 m6">
                                                        <textarea id="viewBulkBlockRemark" class="materialize-textarea" placeholder="kjdhdskjfhdskhfkdsjhf" disabled></textarea>
                                                        <label for="viewBulkBlockRemark">Remark </label>
                                                    </div>
														</div>	
                                                    <div class="row">

                                                    <div class="input-field col s12 m6" style="margin-top: 25px;">
                                                        <input type="text" id="viewBulkBlockuploadFile" name="uploadFile" pattern="[0-9]"
                                                            title="" maxlength="16" value="file.csv" disabled>
                                                        <label for="viewBulkBlockuploadFile">Upload Bulk Devices</label>
                                                    </div>

                                                    <div class="input-field col s12 m6" style="margin-top: 25px;">
                                                        <input type="text" id="viewBulkBlockquantity" name="quantity" pattern="[0-9]"
                                                            title="" maxlength="16" value="1500" disabled>
                                                        <label for="viewBulkBlockquantity">Quantity</label>
                                                    </div>
                                                    </div>
                                                    <div class="row">
                                                     <div class="input-field col s12 m6" style="margin-top: 25px;">
                                                        <input type="text" id="viewBulkBlockTxnId" name="viewBulkBlockTxnId" pattern="[0-9]"
                                                            title="" maxlength="16" value="1500" disabled>
                                                        <label for="viewBulkBulkTxnId">Transaction Id</label>
                                                    </div>
                                                    </div>
													
                                                   


                                                    <div class="input-field col s12 center">
                                                        <button class="modal-close btn">Close</button>
                                                        
                                                    </div>
                                                </form>
            </div></div>




           <div id="viewblockImeiDevice" class="modal-form" style="overflow-y: hidden;">
		<h6 class="modal-header">View Block Devices</h6>
		<div class="modal-content" style="margin-top: 5px;">
			   <form action=""  method="POST" enctype="multipart/form-data">
                                                    <div class="row">
                                                        <div class="row">
                                                       		<div class="row">
                                        					<div class="col s12 m6">
                                                                <label for="viewblockdeviceType">Device Type <span class="star">*</span></label>
                                                                <select class="browser-default" id="viewblockdeviceType" required="required" disabled="disabled">
                                                                    <option value="" disabled selected>Device Type</option> 
                                                                    
                                                                </select>
                                                            </div>
                                                            <div class="col s12 m6"><label for="viewblockdeviceIdType">Device ID
                                                                    Type <span class="star">*</span></label>
                                                                <select class="browser-default" id="viewblockdeviceIdType" disabled="disabled" required="required">
                                                                    <option value="" disabled selected>Select Device ID
                                                                        Type
                                                                    </option>
                                                                   
                                                                </select>
                                                            </div>
                                                            </div>
                                        					<div class="row">
                                                            <div class="col s12 m6">
                                                                <label for="viewblockmultipleSimStatus">Multiple Sim Status <span class="star">*</span></label>
                                                                <select class="browser-default" id="viewblockmultipleSimStatus" disabled="disabled" required="required">
                                                                    <option value="" disabled selected>Multiple Sim Status</option>
                                                                    
                                                                </select>
                                                            </div>
                                        
                                                            <div class="input-field col s12 m6" style="margin-top: 21px;">
                                                                <input type="text" id="viewsingleblockserialNumber" name="serialNumber" placeholder="" pattern="[0-9]{1,15}" required="required"
                                                                    title="Please enter your device serial number first" disabled="disabled" maxlength="15">
                                                                <label for="viewsingleblockserialNumber">Device Serial Number <span class="star">*</span></label>
                                                            </div>
                                                            </div>
                                                             <div class="row">
                                                            <div class="input-field col s12 m6">
                                                                <textarea id="viewsingleblockremark" disabled="disabled" placeholder="" class="materialize-textarea" required="required"></textarea>
                                                                <label for="viewsingleblockremark">Remark <span class="star">*</span></label>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                        <div class="row input_fields_wrap">
                                                            <div class="col s12 m12">
                                                                <p style="margin-bottom: 0;">IMEI/MEID/ESN</p>
                                                            </div>
                                                            <div class="input-field col s12 m6">
                                                                <input type="text" id="viewsingleblockIMEI1" name="IMEI1" placeholder="" disabled="disabled" pattern="[0-9]{15,16}" required="required"
                                                                    title="Please enter minimum 15 and maximum 16 digit only" maxlength="16">
                                                                <label for="viewsingleblockIMEI1">1 <span class="star">*</span></label>
                                                            </div>
                                        
                                                            <div class="input-field col s12 m6">
                                                                <input type="text" id="viewsingleblockIMEI2" name="IMEI2" placeholder="" disabled="disabled" pattern="[0-9]{15,16}"
                                                                    title="Please enter minimum 15 and maximum 16 digit only" maxlength="16">
                                                                <label for="viewsingleblockIMEI2">2</label>
                                                            </div>  
                                                            
                                                            <div class="input-field col s12 m6">
                                                                <input type="text" id="viewsingleblockIMEI3" name="IMEI3" placeholder="" disabled="disabled" pattern="[0-9]{15,16}"
                                                                    title="Please enter minimum 15 and maximum 16 digit only"
                                                                    maxlength="16">
                                                                <label for="viewsingleblockIMEI3">3</label>
                                                            </div>
            
                                                            <div class="input-field col s12 m6">
                                                                <input type="text" id="viewsingleblockIMEI4" name="IMEI4[]" placeholder="" disabled="disabled" pattern="[0-9]{15,16}"
                                                                    title="Please enter minimum 15 and maximum 16 digit only"
                                                                    maxlength="16">
                                                                <label for="viewsingleblockIMEI4">4</label>
                                                            </div>
                                                        </div>
                                                        </div>
                                                        <span> Required Field are marked with <span class="star">*</span></span>
                                                    </div>

                                                    </div>

                                                    <div class="input-field col s12 center">
                                                       <!--  <button class="btn" type="submit">Submit</button> -->
                                                        <a href="./stolenRecovery" class="btn" style="margin-left: 10px;">Cancel</a>
                                                    </div>
                                                </form>
                                           
            </div></div>
            
                     <div id="editblockImeiDevice" class="modal-form" style="overflow-y: hidden;">
		<h6 class="modal-header">edit Block Devices</h6>
		<div class="modal-content" style="margin-top: 5px;">
			   <form action=""  method="POST" enctype="multipart/form-data">
                                                    <div class="row">
                                                        <div class="row">
                                                       		<div class="row">
                                        					<div class="col s12 m6">
                                                                <label for="editblockdeviceType">Device Type <span class="star">*</span></label>
                                                                <select class="browser-default" id="editblockdeviceType" required="required" >
                                                                    <option value="" disabled selected>Device Type</option> 
                                                                    
                                                                </select>
                                                            </div>
                                                            <div class="col s12 m6"><label for="editblockdeviceIdType">Device ID
                                                                    Type <span class="star">*</span></label>
                                                                <select class="browser-default" id="editblockdeviceIdType"  required="required">
                                                                    <option value="" disabled selected>Select Device ID
                                                                        Type
                                                                    </option>
                                                                   
                                                                </select>
                                                            </div>
                                                            </div>
                                        					<div class="row">
                                                            <div class="col s12 m6">
                                                                <label for="editblockmultipleSimStatus">Multiple Sim Status <span class="star">*</span></label>
                                                                <select class="browser-default" id="editblockmultipleSimStatus"  required="required">
                                                                    <option value="" disabled selected>Multiple Sim Status</option>
                                                                    
                                                                </select>
                                                            </div>
                                        
                                                            <div class="input-field col s12 m6" style="margin-top: 21px;">
                                                                <input type="text" id="editsingleblockserialNumber" name="serialNumber" placeholder="" pattern="[0-9]{1,15}" required="required"
                                                                    title="Please enter your device serial number first"  maxlength="15">
                                                                <label for="editsingleblockserialNumber">Device Serial Number <span class="star">*</span></label>
                                                            </div>
                                                            </div>
                                                             <div class="row">
                                                            <div class="input-field col s12 m6">
                                                                <textarea id="editsingleblockremark"  placeholder="" class="materialize-textarea" required="required"></textarea>
                                                                <label for="editsingleblockremark">Remark <span class="star">*</span></label>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                        <div class="row input_fields_wrap">
                                                            <div class="col s12 m12">
                                                                <p style="margin-bottom: 0;">IMEI/MEID/ESN</p>
                                                            </div>
                                                            <div class="input-field col s12 m6">
                                                                <input type="text" id="editsingleblockIMEI1" name="IMEI1" placeholder=""  pattern="[0-9]{15,16}" required="required"
                                                                    title="Please enter minimum 15 and maximum 16 digit only" maxlength="16">
                                                                <label for="editsingleblockIMEI1">1 <span class="star">*</span></label>
                                                            </div>
                                        
                                                            <div class="input-field col s12 m6">
                                                                <input type="text" id="editsingleblockIMEI2" name="IMEI2" placeholder=""  pattern="[0-9]{15,16}"
                                                                    title="Please enter minimum 15 and maximum 16 digit only" maxlength="16">
                                                                <label for="editsingleblockIMEI2">2</label>
                                                            </div>  
                                                            
                                                            <div class="input-field col s12 m6">
                                                                <input type="text" id="editsingleblockIMEI3" name="IMEI3" placeholder=""  pattern="[0-9]{15,16}"
                                                                    title="Please enter minimum 15 and maximum 16 digit only"
                                                                    maxlength="16">
                                                                <label for="editsingleblockIMEI3">3</label>
                                                            </div>
            
                                                            <div class="input-field col s12 m6">
                                                                <input type="text" id="editsingleblockIMEI4" name="IMEI4[]" placeholder=""  pattern="[0-9]{15,16}"
                                                                    title="Please enter minimum 15 and maximum 16 digit only"
                                                                    maxlength="16">
                                                                <label for="editsingleblockIMEI4">4</label>
                                                                <input type="text" id="editsingleblockTxnid" style="display: none">
                                                                <input type="text" id="editsingleblocRequestType" style="display: none">
                                                                
                                                            </div>
                                                        </div>
                                                        </div>
                                                        <span> Required Field are marked with <span class="star">*</span></span>
                                                    </div>

                                                    </div>

                                                    <div class="input-field col s12 center">
                                                        <button class="btn" type="button" onclick="updateSingleBlockDevicesRequest()">Submit</button>
                                                        <a href="./stolenRecovery" class="btn" style="margin-left: 10px;">Cancel</a>
                                                    </div>
                                                </form>
                                           
            </div></div>
 <div id="confirmEditBlockUnblock" class="modal">
  <h6 class="modal-header">Update Device Information</h6>
        <div class="modal-content">
           
            <div class="row">
                <h6>The following details has been updated successfully</h6>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <div class="input-field col s12 center">
                        <a  href ="./stolenRecovery" class=" btn">ok</a>
                    </div>
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

	<script type="text/javascript"
		src="${context}/resources/project_js/stolenRecovery.js"></script>
	<script type="text/javascript"
		src="${context}/resources/project_js/reportBlock.js"></script>

</body>
</html>