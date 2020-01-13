<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
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

input[type=text] {
	height: 35px;
	margin: 0 0 5px 0;
}

.checkboxFont {
	font-size: 16px;
	margin-right: 10px;
}

textarea.materialize-textarea {
	padding: 0;
	padding-left: 10px;
}

.btn-flat {
	height: auto;
}

select option {
	color: #444;
}

#filterFileStatus {
	color: #444;
}

.dropdown-content li>a, .dropdown-content li>span {
	color: #444;
}

.welcomeMsg {
	padding-bottom: 50px !important;
	line-height: 1.5 !important;
	text-align: center;
}

.pay-tax-icon {
	font-size: 20px;
	color: #2e568b;
	margin: 0 7px;
}

.row {
	margin-top: 5px;
}

.section {
	padding-top: 0 !important;
}

label {
	font-size: 0.8rem;
}

input[type=text]:disabled+label {
	color: #444;
}

input::placeholder {
	color: #444;
}

select:disabled {
	color: #444;
}

/* .btn-info {
            margin-right: 1%;
        } */
input[type='search'] {
	background-image: url(images/search-512.jpg);
	background-position: 97% 7px;
	background-repeat: no-repeat;
	color: #444;
}
</style>
</head>
<body data-roleType="${usertype}" data-userTypeID="${usertypeId}"
	data-userID="${userid}" data-selected-roleType="${selectedUserTypeId}"
	data-stolenselected-roleType="${stolenselectedUserTypeId}"
	data-selected-consignmentTxnId="${consignmentTxnId}"
	data-selected-consignmentStatus="${consignmentStatus}" session-value="${not empty param.NID ? param.NID : 'null'}">


	<!-- //////////////////////////////////////////////////////////////////////////// -->

	<!-- START CONTENT -->
	<section id="content">
		<!--start container-->
		<div class="container">
			<div class="section">
				<div class="row">
					<div class="col s12 m12 l12" id="emptyTable">
						<div class="row card-panel">

							<div class="container-fluid pageHeader" id="pageHeader">

								<a href="" class="boton right" id="btnLink"></a>
							</div>

							<div class="row">
					

								<div id="user123" class="section" style="display: none;">
									<form action="" onsubmit="return submitDeviceInfo()"
										method="POST" enctype="multipart/form-data">
										<div class="row">
											<div class="col s12 m12">
												<div class="col s12 m12">
													<h5>Personal Information</h5>
													<hr>
												</div>
											</div>
											<div class="col s12 m12" style="margin-top: 20px;">
												<div class="input-field col s12 m6">
													<input type="text" id="nationalID" pattern="[A-Za-z0-9]{1,12}" title="Please enter alphabets and numbers upto 15 characters only"
													  required="required" maxlength="12" name="nationalID" placeholder="" disabled="disabled" value="" /> <label for="nationalID"
														class="center-align ml-10">NID </label>
												</div>

												<div class="file-field input-field col s12 m6"
													style="margin-top: -15px;">
													<h6 style="color: #000;">
														Upload National ID Proof <span class="star">*</span>
													</h6>
													<div class="btn">
														<span>Select File</span> <input type="file"
															required="required" id="csvUploadFile" accept=".csv">
													</div>
													<div class="file-path-wrapper">
														<input class="file-path validate responsive-file-div"
															type="text">
													</div>
												</div>
											</div>
											<div class="col s12 m12">
												<div class="input-field col s12 m4 l4">
													<input type="text" name="firstName" id="firstName"
														maxlength="20" required="required"
														pattern="[A-Za-z]{0,20}"
														title="Please enter alphabets  upto 20 characters only">
													<label for="firstName" class="center-align">First
														Name <span class="star">*</span>
													</label>
												</div>

												<div class="input-field col s12 m4 l4">
													<input type="text" name="middleName" id="middleName"
														maxlength="20" pattern="[A-Za-z]{0,20}"
														title="Please enter alphabets upto 20 characters only">
													<label for="middleName">Middle Name</label>
												</div>

												<div class="input-field col s12 m4 l4">
													<input type="text" name="lastName" id="lastName"
														required="required" pattern="[A-Za-z]{0,20}"
														title="Please enter alphabets  upto 20 characters only"
														maxlength="20"> <label for="lastName">Last
														Name <span class="star">*</span>
													</label>
												</div>
											</div>
										</div>

										<div class="row">
											<div class="col s12 m12">
												<div class="input-field col s12 m12 l12">
													<input type="text" name="address"
														pattern="[^[a-zA-Z0-9\s,'-]*$]{0,200}"
														title="Please enter alphabets and numbers upto 200 characters only"
														maxlength="200" required="required"
														class="form-control boxBorder boxHeight" id="address">
													<label for="address">Address(Property Location) <span
														class="star">*</span></label>
												</div>

												<div class="input-field col s12 m6 l6">
													<input type="text" pattern="[^[a-zA-Z0-9\s,'-]*$]{0,20}"
														title="Please enter alphabets and numbers upto 20 characters only"
														name="streetNumber"
														class="form-control boxBorder boxHeight" id="streetNumber"
														maxlength="20" required="required"> <label
														for="streetNumber">Street Number <span
														class="star">*</span>
													</label>
												</div>
													<div class="input-field col s12 m6 l6">
													<input type="text" pattern="[^[a-zA-Z0-9\s,'-]*$]{0,50}"
														title="Please enter alphabets and numbers upto 50 characters only"
														name="streetNumber"
														class="form-control boxBorder boxHeight" id="village"
														maxlength="50" required="required"> <label
														for="village">Vllage <span
														class="star">*</span>
													</label>
												</div>
												

												<div class="input-field col s12 m6 l6">
													<input type="text" name="locality"
														pattern="[^[a-zA-Z0-9\s,'-]*$]{0,50}"
														title="Please enter alphabets and numbers upto 50 characters only"
														class="form-control boxBorder boxHeight" id="locality"
														maxlength="50" required="required"> <label
														for="locality">Locality <span class="star">*</span>
													</label>
												</div>

													<div class="input-field col s12 m6 l6">
													<input type="text" pattern="[^[a-zA-Z0-9\s,'-]*$]{0,50}"
														title="Please enter alphabets and numbers upto 50 characters only"
														name="streetNumber"
														class="form-control boxBorder boxHeight" id="district"
														maxlength="50" required="required"> <label
														for="district">District <span
														class="star">*</span>
													</label>
												</div>
												<div class="input-field col s12 m6 l6">
													<input type="text" pattern="[^[a-zA-Z0-9\s,'-]*$]{0,50}"
														title="Please enter alphabets and numbers upto 50 characters only"
														name="streetNumber"
														class="form-control boxBorder boxHeight" id="commune"
														maxlength="50" required="required"> <label
														for=""commune"">Commune <span
														class="star">*</span>
													</label>
												</div>
												<div class="input-field col s12 m6 l6">
													<input type="text" pattern="[0-9]{6,10}"
														title="Please enter Postel code upto 10 Numbers only"
														name="streetNumber"
														class="form-control boxBorder boxHeight" id="postalcode"
														maxlength="20" required="required"> <label
														for="postalcode">PostalCode <span
														class="star">*</span>
													</label>
												</div>
												<div class="input-field col s12 m6 l6">
													<p
														style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">
														Country <span class="star">*</span>
													</p>
													<select id="country" class="browser-default"
														class="mySelect" style="padding-left: 0;" required></select>
												</div>

												<div class="input-field col s12 m6 l6">
													<p
														style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">
														Province <span class="star">*</span>
													</p>
													<select id="state" class="browser-default" class="mySelect"
														style="padding-left: 0;" required></select>
												</div>
											</div>

											<div class="col s12 m12" style="margin-top: 10px;">
												<div class="input-field col s12 m6 l6">
													<input type="email" name="email" id="email" required
														maxlength="30"> <label for="email">Email <span
														class="star">*</span></label>
												</div>

												<div class="input-field col s12 m6 l6">
													<input type="text" name="phone" required
														pattern="[0-9]{10,10}"
														title="Please enter 10 digits contact number"
														class="form-control boxBorder boxHeight" id="phone"
														maxlength="10"> <label for="phone">Contact
														Number <span class="star">*</span>
													</label>
												</div>
											</div>
										</div>
										<div id="mainDeviceInformation" class="mainDeviceInformation">
											<div id="deviceInformation" class="deviceInformation">
												<div class="row">
													<div class="col s12 m12">
														<div class="col s12 m12" style="margin-top: 30px;">
															<h5>Device Information</h5>
															<hr>
														</div>

														<div class="col s12 m6">
															<label for="deviceType">Device Type <span
																class="star">*</span></label> <select class="browser-default"
																id="deviceType1" required="required">
																<option value="" disabled selected>Select
																	Device Type</option>


															</select>
														</div>

														<div class="col s12 m6">
															<label for="deviceIdType1">Device ID Type <span
																class="star">*</span></label> <select required="required"
																class="browser-default" id="deviceIdType1">
																<option value="" disabled selected>Select
																	Device ID Type</option>

															</select>
														</div>

														<div class="col s12 m6">
															<label for="multipleSimStatus1">Multiple Sim
																Status <span class="star">*</span>
															</label> <select class="browser-default" required="required"
																id="multipleSimStatus1">
																<option value="" disabled selected>Select
															</select>
														</div>

														<div class="col s12 m6">
															<label for="country1">Country bought From <span
																class="star">*</span></label> <select id="country1"
																class="browser-default" class="mySelect"
																style="padding-left: 0;" required></select>
														</div>

														<div class="input-field col s12 m6"
															style="margin-top: 28px;">
															<input type="text" id="serialNumber1" required="required" name="serialNumber"
																pattern="[A-Za-z0-9]{0,15}"
																title="Please enter alphabets and numbers upto 15 characters only"
																maxlength="15"> <label for="serialNumber">Device
																Serial Number <span class="star">*</span>
															</label>
														</div>

														<div class="col s12 m6">
															<label for="taxStatus1">Tax paid Status <span
																class="star">*</span></label> <select class="browser-default"
																required="required" id="taxStatus1">
																<option value="" disabled selected>Select Tax
																	paid Status</option>

															</select>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col s12 m12">
														<div class="col s12 m6" style="margin-top: -10px;">
															<label for="deviceStatus1">Device Status <span
																class="star">*</span></label> <select class="browser-default"
																required="required" id="deviceStatus1">
																<option value="" disabled selected>Select
																	Device Status</option>

															</select>
														</div>

														<div class="input-field col s12 m6 l6">
															<input type="text" name="Price" id="Price1"
																pattern="[0-9]{0,7}" required="required" maxlength="7">
															<label for="Price1">Price <span class="star">*</span></label>
														</div>

														<div class="col s12 m6">
															<label for="Currency1">Currency <span
																class="star">*</span></label> <select class="browser-default"
																id="Currency1" required="required">
																<option value="" disabled selected>Select
																	Currency</option>

															</select>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col s12 m12">
														<div class='col s12 m12 input_fields_wrap'>
															<p>IMEI/MEID/ESN</p>
															<div class='row'>
																<div class="input-field col s12 m6">
																	<input type="text" id="IMEIA1" name="IMEI1"
																		pattern="[0-9]{15,16}" required="required"
																		title="Please enter minimum 15 and maximum 16 digit only"
																		maxlength="16"> <label for="IMEIA1">1
																		<span class="star">*</span>
																	</label>
																</div>

																<div class="input-field col s12 m6">
																	<input type="text" id="IMEIB1" name="IMEI2"
																		pattern="[0-9]{15,16}"
																		title="Please enter minimum 15 and maximum 16 digit only"
																		maxlength="16"> <label for="IMEIB2">2</label>
																</div>

																<div class="input-field col s12 m6">
																	<input type="text" id="IMEIC1" name="IMEIC3"
																		pattern="[0-9]{15,16}"
																		title="Please enter minimum 15 and maximum 16 digit only"
																		maxlength="16"> <label for="IMEIC3">3</label>
																</div>

																<div class="input-field col s12 m6" id="field">
																	<input type="text" id="IMEID1" name="IMEID4[]"
																		pattern="[0-9]{15,16}"
																		title="Please enter minimum 15 and maximum 16 digit only"
																		maxlength="16" id="field0"> <label
																		for="IMEID4">4</label>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="col s12 m12">
											<button class="btn right add_field_button"
												style="margin-top: 5px;">
												<span style="font-size: 20px;">+</span> Add More Device
											</button>
											<p>
												Required Field are marked with <span class="star">*</span>
											</p>
										</div>

										<div class="col s12 m12 center" style="margin-top: 30px;">
											<button class="btn " type="submit">Submit</button>
											<a  href='./uploadPaidStatus?via=other' class="btn"
												style="margin-left: 10px;">Cancel</a>
										</div>


									</form>
								</div>

								<div id="profile-page" class="section">
									<div class="container" id="user456" style="display: none;">
										<div class="col s12 m12 l12" id="tableDiv"
											style="padding-bottom: 5px; background-color: #e2edef52;">
										</div>
										<div class="row">
											<div class="col s12 m12">
												<table class="responsive-table striped display"
													id="data-table-simple" cellspacing="0">

												</table>

												<a href="Javascript:void(0);" onclick="viewDeviceHistory()">View
													block devices</a>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--end container-->
	</section>
	<!-- END CONTENT -->




	<!-- Modal start   -->

	<div id="viewBlockDevices" class="modal" style="width: 75%;">
		<button type="button"
			class=" modal-action modal-close waves-effect waves-green btn-flat right"
			data-dismiss="modal">&times;</button>
		<h6 class="modal-header">View Block Devices</h6>
		
		<div class="modal-content">

			<div class="row">
				<table class="responsive-table striped display"
					id="data-table-history" cellspacing="0">
				</table>
			</div>
		</div>
	</div>
	<!-- Modal End -->



	<!-- Modal start   -->

	<div id="deleteMsg" class="modal">
		<h6 class="modal-header">Delete</h6>
		<div class="modal-content">

			<div class="row">
				<h6>Are you sure you want to Delete this device information?</h6>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<button class="btn" onclick="accept()">yes</button>
					<button class="modal-close btn" style="margin-left: 10px;">no</button>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal End -->



	<!-- Modal 2 start   -->

	<div id="viewDeviceInformation" class="modal">
		<h6 class="modal-header">View Device Information</h6>
		<div class="modal-content">

			<div class="row">
				<div class="row">
					<div class="input-field col s12 m6 l6">
						<input type="text" name="deviceType" id="viewdeviceType"
							placeholder="Mobile Phone/Feature phone" maxlength="30" disabled
							style="height: 28px;"> <label for="deviceType">Device
							Type</label>
					</div>

					<div class="input-field col s12 m6 l6">
						<input type="text" name="deviceIdType" id="viewdeviceIdType"
							placeholder="IMEI" maxlength="30" disabled style="height: 28px;">
						<label for="deviceIdType">Device ID Type</label>
					</div>

					<div class="input-field col s12 m6 l6">
						<input type="text" name="simStatus" id="viewsimStatus"
							placeholder="Yes" maxlength="30" disabled style="height: 28px;">
						<label for="simStatus">Multiple Sim Status</label>
					</div>

					<div class="input-field col s12 m6 l6">
						<input type="text" name="countryBought" id="viewcountryBought"
							placeholder="" maxlength="30" disabled style="height: 28px;">
						<label for="countryBought">Country bought From</label>
					</div>

					<div class="input-field col s12 m6">
						<input type="text" id="viewserialNumber" name="serialNumber"
							placeholder="" maxlength="20" disabled style="height: 28px;">
						<label for="serialNumber">Device Serial Number</label>
					</div>

					<div class="input-field col s12 m6 l6">
						<input type="text" name="taxStatus" id="viewtaxStatus"
							placeholder="" maxlength="30" disabled style="height: 28px;">
						<label for="taxStatus">Tax paid Status</label>
					</div>
				</div>

				<div class="row">
					<div class="input-field col s12 m6 l6">
						<input type="text" name="deviceStatus" id="viewdeviceStatus"
							placeholder="" maxlength="30" disabled style="height: 28px;">
						<label for="deviceStatus">Device Status</label>
					</div>

					<div class="input-field col s12 m6 l6">
						<input type="text" name="Price" id="viewPrice" placeholder=""
							maxlength="30" disabled style="height: 28px;"> <label
							for="Price">Price </label>
					</div>

					<div class="input-field col s12 m6 l6">
						<input type="text" name="currency" id="viewcurrency"
							placeholder="" maxlength="30" disabled style="height: 28px;">
						<label for="currency">Currency</label>
					</div>
				</div>

				<div class="row input_fields_wrap">
					<div class="col s12 m12">
						<p style="margin-bottom: 0; margin-top: -10px;">IMEI/MEID/ESN</p>
					</div>
					<div class="input-field col s12 m6">
						<input type="text" id="viewIMEI1" name="IMEI1"
							pattern="[0-9]{15,16}"
							title="Please enter minimum 15 and maximum 16 digit only"
							maxlength="16" placeholder="" disabled style="height: 28px;">
						<label for="IMEI1">1 </label>
					</div>

					<div class="input-field col s12 m6">
						<input type="text" id="viewIMEI2" name="IMEI2"
							pattern="[0-9]{15,16}"
							title="Please enter minimum 15 and maximum 16 digit only"
							maxlength="16" placeholder="" disabled style="height: 28px;">
						<label for="IMEI2">2</label>
					</div>

					<div class="input-field col s12 m6">
						<input type="text" id="viewIMEI3" name="IMEI3"
							pattern="[0-9]{15,16}"
							title="Please enter minimum 15 and maximum 16 digit only"
							maxlength="16" placeholder="" style="height: 28px;" disabled>
						<label for="IMEI3">3</label>
					</div>

					<div class="input-field col s12 m6">
						<input type="text" id="viewIMEI4" name="IMEI4[]" pattern="[15-9]"
							title="Please enter minimum 15 and maximum 16 digit only"
							maxlength="16" placeholder="" style="height: 28px;" disabled>
						<label for="IMEI4">4</label>
					</div>

					<div class="col s12 m12 center" style="margin-top: 10px;">
						<button class="btn modal-close" style="margin-left: 10px;">Ok</button>
					</div>

				</div>
			</div>
		</div>
	</div>
	<!-- Modal End -->

	

	<div id="regularisedDevice" class="modal">
		<h6 class="modal-header">Upload Paid Device</h6>
		<div class="modal-content">
			<div class="row">
				<h6 id="sucessMessage">
					Your form has been successfully submitted. The Transaction ID for
					future reference is <span id="dynamicTxnId"></span>
				</h6>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<div class="input-field col s12 center">
						<button class="btn" onclick="refreshContent();">ok</button>
					</div>
				</div>
			</div>
		</div>
	</div>


	<div id="payTaxModal" class="modal">
		<h6 class="modal-header">Pay Tax Information</h6>
		<div class="modal-content">

			<div class="row">
				<h6>Do you confirm that tax has been paid?</h6>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<div class="input-field col s12 center">
						<button class="btn" onclick="taxPaidStatus()">Yes</button>
						<button class="modal-close btn" style="margin-left: 10px;">No</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div id="confirmDeleteMsg" class="modal">
		<h6 class="modal-header">Delete</h6>
		<div class="modal-content">

			<div class="row">
				<h6 id="responseMsg"> This device information has been successfully deleted.</h6>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<button class="btn" onclick="refreshContent();">ok</button>
				</div>
			</div>
		</div>
	</div>

	<div id="payNowTaxPayment" class="modal">
		<h6 class="modal-header">Add Device Information</h6>
		<div class="modal-content">

			<div class="row">
				<h6 id="taxPaidMsg"> The device status has been successfully updated</h6>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<div class="input-field col s12 center">
						<button class="btn" onclick="refreshContent()">ok</button>
					</div>
				</div>
			</div>
		</div>
	</div>
<div id="approveInformation" class="modal" style="width: 40%; z-index: 1003; opacity: 1; transform: scaleX(1); top: 10%;">
        <h6 class="modal-header">Approve</h6>
        <div class="modal-content">
            <div class="row">
                <form action="">
                   
                    <h6>Do you want to Approve the request having Transaction ID: <span id="approveTxnId"></span> ?</h6>
                </form>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <a onclick="aprroveDevice()" class="btn modal-trigger">Yes</a>
                    <button class="btn modal-close" style="margin-left: 10px;">No</button>
                      <span id="userId" hidden></span>
                       <span id="adminCurrentStatus" hidden></span>
                </div>
            </div>
        </div>
    </div>
    
    <div id="confirmApproveInformation" class="modal" style="width: 40%; z-index: 1005; opacity: 1; transform: scaleX(1); top: 10%;">
        <h6 class="modal-header">Approve</h6>
        <div class="modal-content">
            <div class="row">
                <form action="">
                    
                    <h6>Device has been Approved Successfully.</h6>
                   
                </form>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                     <a class="btn modal-close" href="./uploadPaidStatus">ok</a>
                   
                </div>
            </div>
        </div>
    </div>
    
    
    <div id="rejectInformation" class="modal">
           <h6 class="modal-header">Reject</h6>
            <div class="modal-content">
            <div class="row">
             <h6>Do you want to reject the request having transaction ID:<span id="disapproveTxnId"></span> ?</h6>
                <form action="">
                
                    <div class="input-field" style="margin-top: 30px;">
                        <textarea id="Reason" class="materialize-textarea"></textarea>
                        <label for="textarea1" style="margin-left: -10px;">Reason</label>
                    </div>
                   
                    
                </form>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <a onclick="rejectUser()" class="btn modal-close modal-trigger">Yes</a>
                    <button class="btn modal-close" style="margin-left: 10px;">No</button>
                </div>
            </div>
        </div>
    </div>
  
    	<div id="confirmRejectInformation" class="modal">
         <h6 class="modal-header">Reject</h6>
          <div class="modal-content">
            <div class="row">
                <form action="">
                  
                    <h6>Device has been Rejected Successfully.</h6>
                </form>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <a class="btn modal-close" href="./uploadPaidStatus">ok</a>
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
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${context}/resources/js/plugins.js"></script>
	<script type="text/javascript"
		src="${context}/resources/js/Validator.js"></script>
	<script type="text/javascript"
		src="${context}/resources/js/plugins/perfect-scrollbar/perfect-scrollbar.min.js"></script>
	<script type="text/javascript"
		src="${context}/resources/js/countries.js"></script>
	<script type="text/javascript"
		src="${context}/resources/project_js/uploadPaidStatus.js"></script>



</body>
</html>