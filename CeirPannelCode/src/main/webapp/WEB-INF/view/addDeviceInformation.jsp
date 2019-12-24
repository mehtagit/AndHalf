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
<body>

	<!-- START CONTENT -->
	<section id="content">
		<!--start container-->
		<div class="container">
			<div class="section">
				<div class="row">
					<div class="col s12 m12 l12">
						<div class="row card-panel">
							<div class="container-fluid pageHeader">
								<p class="PageHeading">Add Device Information</p>
								<!-- <a href="#addDevice" class="boton right modal-trigger">Add Device</a> -->
							</div>
							<div id="user123" class="section">
								<form action="" onsubmit="return submitDeviceInfo()" method="POST" enctype="multipart/form-data">
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
														<option value="" disabled selected>Select Device
															Type</option>
															
														
													</select>
												</div>

												<div class="col s12 m6">
													<label for="deviceIdType1">Device ID Type <span
														class="star">*</span></label> <select required="required" class="browser-default"
														id="deviceIdType1">
														<option value="" disabled selected>Select Device ID Type</option>
													
													</select>
												</div>

												<div class="col s12 m6">
													<label for="multipleSimStatus1">Multiple Sim Status <span
														class="star">*</span></label> <select class="browser-default" required="required"
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
													<input type="text" id="serialNumber1" name="serialNumber" required="required" 
														pattern="[A-Za-z0-9]{0,15}" title="Please enter alphabets and numbers upto 15 characters only"
														maxlength="15"> <label for="serialNumber">Device
														Serial Number <span class="star">*</span>
													</label>
												</div>

												<div class="col s12 m6">
													<label for="taxStatus1">Tax paid Status <span
														class="star">*</span></label> <select class="browser-default" required="required"
														id="taxStatus1">
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
														class="star">*</span></label> <select class="browser-default" required="required"
														id="deviceStatus1">
														<option value="" disabled selected>Select Device
															Status</option>
														
													</select>
												</div>

												<div class="input-field col s12 m6 l6">
													<input type="text" name="Price" id="Price1" pattern="[0-9]{0,7}" required="required"  maxlength="7">
													<label for="Price1">Price <span class="star">*</span></label>
												</div>

												<div class="col s12 m6">
													<label for="Currency1">Currency <span class="star">*</span></label>
													<select class="browser-default" id="Currency1" required="required">
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
																pattern="[0-9]{15,16}"
																title="Please enter minimum 15 and maximum 16 digit only"
																maxlength="16"> <label for="IMEIA1">1 <span
																class="star">*</span></label>
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
																maxlength="16" id="field0"> <label for="IMEID4">4</label>
														</div>
													</div>
												</div>
                                             </div></div> 
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
													<button class="btn " type="submit" >Submit</button>
													<button class="btn modal-trigger" data-target="cancelMsg"
														style="margin-left: 10px;">Cancel</button>
												</div>
								
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--end container-->
	</section>
	<!-- END CONTENT -->
	</div>
	<!-- END WRAPPER -->

	</div>
	<!-- END MAIN -->



	<!-- //////////////////////////////////////////////////////////////////////////// -->

	<!-- START FOOTER -->
	<!-- <footer class="page-footer"
		style="position: fixed; bottom: 0; width: 100%;">
		<div class="footer-copyright">
			<div class="container">
				<span class="right">Copyright Â© 2018 Sterlite Technologies
					Ltd, All rights reserved.</span>
			</div>
		</div>
	</footer> -->
	<!-- END FOOTER -->

	<!-- Modal 2 start   -->

	<div id="payNowTaxPayment" class="modal">
		<div class="modal-content">
			<h6 class="modal-header">Add Device Information</h6>
			<div class="row">
				<h6>Your device has been successfully regularized</h6>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<div class="input-field col s12 center">
						<button class="modal-close btn">ok</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal End -->

	<!-- Modal 2 start   -->

	<div id="needToPayTax" class="modal">
		<div class="modal-content">
			<h6 class="modal-header">Pay Tax Information</h6>
			<div class="row">
				<h6>You need to pay tax</h6>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<div class="input-field col s12 center">
						<button class="modal-close btn">Pay now</button>
						<button class="modal-close btn" style="margin-left: 10px;">Pay
							Later</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal End -->

	<!-- Pay Tax Modal start   -->

	<div id="payTaxModal" class="modal">
		<div class="modal-content">
			<h6 class="modal-header">Pay Tax Information</h6>
			<div class="row">
				<h6>Do you confirm that tax has been paid?</h6>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<div class="input-field col s12 center">
						<button class="modal-close modal-trigger btn"
							data-target="payNowTaxPayment">Yes</button>
						<button class="modal-close btn" style="margin-left: 10px;">No</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Pay Tax Modal End -->

	<!-- Modal 1 start   -->

	<div id="ApproveConsignment" class="modal">
		<div class="modal-content">
			<h6 class="modal-header">Approve Consignment</h6>
			<div class="row">
				<h6>The tax against the consignment with (Importer/Company
					name) having Transaction ID:___________ has been successfully paid.</h6>
			</div>
			<div class="row">
				<h6>Do you approve the consignment?</h6>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<div class="input-field col s12 center">
						<button class="modal-close modal-trigger btn"
							data-target="confirmApproveConsignment">Yes</button>
						<button class="modal-close btn" style="margin-left: 10px;">No</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal End -->

	<!-- Modal 2 start   -->

	<div id="confirmApproveConsignment" class="modal">
		<div class="modal-content">
			<h6 class="modal-header">Approve Consignment</h6>
			<div class="row">
				<h6>The consignment has been successfully approved.</h6>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<div class="input-field col s12 center">
						<button class="modal-close btn">ok</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal End -->

	<!-- Modal 3 start   -->

	<div id="RejectConsignment" class="modal">
		<div class="modal-content">
			<h6 class="modal-header">Reject Consignment</h6>
			<div class="row">
				<h6>Do you really want to mark the consignment
					(Importer/Company name) having Transaction ID:___________ as
					rejected.</h6>
			</div>
			<div class="row">
				<div class="input-field col s12 m12" style="margin-left: -10px;">
					<textarea id="textarea1" class="materialize-textarea"
						style="padding-left: 0;"></textarea>
					<label for="textarea1">Remarks</label>
				</div>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<div class="input-field col s12 center">
						<button class="modal-close modal-trigger btn"
							data-target="confirmRejectConsignment">Yes</button>
						<button class="modal-close btn" style="margin-left: 10px;">No</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal End -->

	<!-- Modal 4 start   -->

	<div id="confirmRejectConsignment" class="modal">
		<div class="modal-content">
			<h6 class="modal-header">Reject Consignment</h6>
			<div class="row">
				<h6>The consignment has been marked as rejected.</h6>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<div class="input-field col s12 center">
						<button class="modal-close btn">ok</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal End -->


	<!-- Modal 4 start   -->

	<div id="manageAccount" class="modal">
		<div class="modal-content">
			<!-- <h4 class="header2 pb-2">User Info</h4> -->
			<h6 class="modal-header">Manage Account</h6>
			<p>Request CEIR ADMIN to</p>
			<div class="row" style="height: 30px;">
				<p>
					<label style="margin-right: 50px"> <input type="radio"
						onclick="document.getElementById('calender').style.display = 'none';"
						name="stolen"><span> Deactivate</span></label>Permanently delete
					the account, you will not be able to login into the portal.
				</p>
			</div>
			<div class="row" style="height: 30px;">
				<p>
					<label style="margin-right: 67px"> <input type="radio"
						onclick="document.getElementById('calender').style.display = 'block';"
						name="stolen"><span> Disable</span></label>All the action will be
					disabled, only view option will be available
				</p>
			</div>

			<div class="input-field col s12 center">
				<button class="btn modal-trigger modal-close"
					data-target="manageAccountSubmit">Submit</button>
				<a href="consignment.html" class="btn" style="margin-left: 10px;">Cancel</a>
			</div>
		</div>
	</div>
	<!-- Modal End -->

	<!-- Modal 4 start   -->

	<div id="manageAccountSubmit" class="modal">
		<div class="modal-content">
			<h6 class="modal-header">Manage Account</h6>
			<h6>The request has been successfully registered with CEIR
				Admin. Please find confirmation over registered mail in 2 to 3
				working days.</h6>
			<div class="input-field col s12 center">
				<button class="btn modal-close">ok</button>
			</div>
		</div>
	</div>
	<!-- Modal End -->

	<!-- Modal 2 start   -->

	<div id="submitActivateDeactivate" class="modal">
		<div class="modal-content">
			<h6 class="modal-header">Manage Account</h6>
			<div class="row">
				<h6>The request has been successfully registered with CEIR
					Admin. Please find the confirmation over registered mail <
					mail@mail.com> in 2 to 3 working days.</h6>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<div class="input-field col s12 center">
						<button class="modal-close waves-effect waves-light btn"
							style="margin-left: 10px;" type="submit" name="add_user"
							id="add_user">Cancel</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal End -->

	<!-- Modal 2 start   -->

	<div id="cancelActivateDeactivate" class="modal">
		<div class="modal-content">
			<div class="row">
				<h6>Do you want to cancel the request?</h6>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<div class="input-field col s12 center">
						<a href="consignment.html" class="btn" type="submit"
							name="add_user" id="add_user">yes</a> <a
							href="#activateDeactivate" class="modal-close modal-trigger btn"
							style="margin-left: 10px;">no</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal End -->



	<!-- Change Passowrd Modal start   -->

	<div id="changePassword" class="modal" style="width: 40%;">
		<button type="button"
			class=" modal-action modal-close waves-effect waves-green btn-flat right"
			data-dismiss="modal">&times;</button>
		<div class="modal-content">
			<h6 class="modal-header">Change Password</h6>
			<div class="row">
				<div class="col s1">
					<i class="fa fa-lock" aria-hidden="true"
						style="font-size: 30px; margin-top: 20px; color: #ff4081;"></i>
				</div>
				<div class="input-field col s11">
					<input type="text" id="oldPassword" class="validate" maxlength="10" />
					<label for="oldPassword" class="center-align" style="color: #000;">
						Old Password </label>
					<div class="password"></div>
				</div>

				<div class="col s1">
					<span class="fa-passwd-reset fa-stack"
						style="margin-top: 20px; color: #ff4081;"> <i
						class="fa fa-undo fa-stack-2x"></i> <i
						class="fa fa-lock fa-stack-1x"></i>
					</span>
				</div>
				<div class="input-field col s11">

					<label for="newPassword" style="color: #000;">New Password</label>
					<input type="text" id="newPassword" class="" maxlength="10" />
				</div>

				<div class="col s1">
					<i class="fa fa-check-square-o" aria-hidden="true"
						style="font-size: 28px; margin-top: 20px; color: #ff4081;"></i>
				</div>
				<div class="input-field col s11">

					<label for="confirmPassword" style="color: #000;">Confirm
						Password</label> <input type="text" class="" id="confirmPassword"
						maxlength="10" />
				</div>
			</div>
			<div class="row" style="margin-top: 30px;">
				<div class="input-field col s12 m12 l12 center">
					<a href="login.html" class="btn" type="button" id="save"
						style="width: 100%;">Save</a>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal End -->

	<!-- modal start -->
	<div id="changePasswordMessage" class="modal">
		<div class="modal-content">
			<h6 class="modal-header">Change Password</h6>
			<div class="row">
				<h6>Your Password has been changed</h6>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<div class="input-field col s12 center">
						<a href="#" class="btn modal-close">ok</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal End -->

	<!-- Pay Tax Modal start   -->

	<div id="payTaxModal" class="modal">
		<div class="modal-content">
			<h6 class="modal-header">Pay Tax Information</h6>
			<div class="row">
				<h6>Do you confirm that tax has been paid?</h6>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<div class="input-field col s12 center">
						<button class="modal-close modal-trigger btn"
							data-target="payNowTaxPayment">Yes</button>
						<button class="modal-close modal-trigger btn"
							style="margin-left: 10px;" data-target="needToPayTax">No</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Pay Tax Modal End -->

	<!-- Modal start   -->

	<div id="logoutMsg" class="modal">
		<div class="modal-content">
			<h6 class="modal-header">Logout</h6>
			<div class="row">
				<h6>Do you want to exit the CEIR portal?</h6>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<div class="input-field col s12 center">
						<a href="login.html" class=" btn">yes</a>
						<button class="modal-close btn" style="margin-left: 10px;">no</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal End -->

	<!-- Modal 2 start   -->

	<div id="addDevice" class="modal">
		<div class="modal-content">
			<h6 class="modal-header">Add Device</h6>
			<div class="row">
				<div class="row">

					<div class="col s12 m6">
						<label for="deviceType">Device Type <span class="star">*</span></label>
						<select class="browser-default" id="deviceType">
							<option value="" disabled selected>Device Type</option>
							<option value="Handheld">Handheld</option>
							<option value="MobilePhone">Mobile Phone/Feature phone</option>
							<option value="Vehicle">Vehicle</option>
							<option value="Portable">Portable(include PDA)</option>
							<option value="Module">Module</option>
							<option value="Dongle">Dongle</option>
							<option value="WLAN">WLAN Router</option>
							<option value="Modem">Modem</option>
							<option value="Smartphone">Smartphone</option>
							<option value="Computer">Connected Computer</option>
							<option value="Tablet">Tablet</option>
							<option value="e-Book">e-Book</option>
						</select>
					</div>

					<div class="col s12 m6">
						<label for="deviceType">Multiple Sim Status <span
							class="star">*</span></label> <select class="browser-default"
							id="deviceType">
							<option value="" disabled selected>Multiple Sim Status</option>
							<option value="Yes">Yes</option>
							<option value="No">No</option>
						</select>
					</div>

					<div class="col s12 m6">
						<label for="country">Country bought From <span
							class="star">*</span></label> <select id="country"
							class="browser-default" class="mySelect" style="padding-left: 0;"
							required></select>
					</div>

					<div class="input-field col s12 m6" style="margin-top: 25px;">
						<input type="text" id="serialNumber" name="serialNumber"
							pattern="[0-9]"
							title="Please enter your device serial number first"
							maxlength="20"> <label for="serialNumber">Device
							Serial Number <span class="star">*</span>
						</label>
					</div>

					<div class="col s12 m6">
						<label for="deviceType">Tax paid Status <span class="star">*</span></label>
						<select class="browser-default" id="deviceType">
							<option value="" disabled selected>Tax paid Status</option>
							<option value="Regularized">Regularized</option>
							<option value="Paid">Paid</option>
							<option value="NotPaid">Not Paid</option>
						</select>
					</div>
				</div>
				<div class="row">
					<div class="col s12 m12">
						<p style="margin-bottom: 0;">IMEI/MEID/ESN</p>
					</div>
					<div class="input-field col s12 m6">
						<input type="text" id="IMEI1" name="IMEI1" pattern="[0-9]"
							title="Please enter minimum 15 and maximum 16 digit only"
							maxlength="16"> <label for="IMEI1">1 <span
							class="star">*</span></label>
					</div>

					<div class="input-field col s12 m6">
						<input type="text" id="IMEI2" name="IMEI2" pattern="[0-9]"
							title="Please enter minimum 15 and maximum 16 digit only"
							maxlength="16"> <label for="IMEI2">2</label>
					</div>

					<div class="input-field col s12 m6">
						<input type="text" id="IMEI3" name="IMEI3" pattern="[0-9]"
							title="Please enter minimum 15 and maximum 16 digit only"
							maxlength="16"> <label for="IMEI3">3</label>
					</div>

					<div class="input-field col s12 m6">
						<input type="text" id="IMEI4" name="IMEI4[]" pattern="[0-9]"
							title="Please enter minimum 15 and maximum 16 digit only"
							maxlength="16"> <label for="IMEI4">4</label>
					</div>

					<div class="col s12 m12">
						<p>
							Required Field are marked with <span class="star">*</span>
						</p>
					</div>

					<div class="col s12 m12 center" style="margin-top: 10px;">
						<button class="btn modal-trigger modal-close"
							data-target="submitMsg">Submit</button>
						<button class="btn modal-trigger modal-close"
							data-target="cancelMsg" style="margin-left: 10px;">Cancel</button>
					</div>

				</div>
			</div>
		</div>
	</div>
	<!-- Modal End -->

	<!-- Modal 2 start   -->

	<div id="viewDeviceInformation" class="modal">
		<div class="modal-content">
			<h6 class="modal-header">View Device Information</h6>
			<div class="row">
				<div class="row">

					<div class="col s12 m6">
						<label for="deviceType">Device Type <span class="star">*</span></label>
						<select class="browser-default" id="deviceType" disabled>
							<option value="MobilePhone">Mobile Phone/Feature phone</option>
						</select>
					</div>

					<div class="col s12 m6">
						<label for="deviceType">Multiple Sim Status <span
							class="star">*</span></label> <select class="browser-default"
							id="deviceType" disabled>
							<option value="Yes">Yes</option>
						</select>
					</div>

					<div class="col s12 m6" style="margin-top: 3px;">
						<label for="country">Country bought From <span
							class="star">*</span></label> <input type="text" id="country"
							name="country" placeholder="USA" disabled>
					</div>

					<div class="input-field col s12 m6" style="margin-top: 25px;">
						<input type="text" id="serialNumber" name="serialNumber"
							pattern="[0-9]"
							title="Please enter your device serial number first"
							maxlength="20" placeholder="123456789" disabled> <label
							for="serialNumber">Device Serial Number <span
							class="star">*</span></label>
					</div>

					<div class="col s12 m6">
						<label for="deviceType">Tax paid Status <span class="star">*</span></label>
						<select class="browser-default" id="deviceType" disabled>
							<option value="Regularized">Regularized</option>
						</select>
					</div>
				</div>
				<div class="row input_fields_wrap">
					<div class="col s12 m12">
						<p style="margin-bottom: 0;">IMEI/MEID/ESN</p>
					</div>
					<div class="input-field col s12 m6">
						<input type="text" id="IMEI1" name="IMEI1" pattern="[0-9]"
							title="Please enter minimum 15 and maximum 16 digit only"
							maxlength="16" placeholder="1234567891234567" disabled> <label
							for="IMEI1">1 <span class="star">*</span></label>
					</div>

					<div class="input-field col s12 m6">
						<input type="text" id="IMEI2" name="IMEI2" pattern="[0-9]"
							title="Please enter minimum 15 and maximum 16 digit only"
							maxlength="16" placeholder="98765432198765432" disabled>
						<label for="IMEI2">2</label>
					</div>

					<div class="input-field col s12 m6">
						<input type="text" id="IMEI3" name="IMEI3" pattern="[0-9]"
							title="Please enter minimum 15 and maximum 16 digit only"
							maxlength="16"> <label for="IMEI3">3</label>
					</div>

					<div class="input-field col s12 m6">
						<input type="text" id="IMEI4" name="IMEI4[]" pattern="[0-9]"
							title="Please enter minimum 15 and maximum 16 digit only"
							maxlength="16"> <label for="IMEI4">4</label>
					</div>

					<div class="col s12 m12 center" style="margin-top: 10px;">
						<button class="btn modal-close" style="margin-left: 10px;">Cancel</button>
					</div>

				</div>
			</div>
		</div>
	</div>
	<!-- Modal End -->

	<!-- Modal start   -->

	<div id="cancelMsg" class="modal">
		<div class="modal-content">
			<h6 class="modal-header">Cancel Request</h6>
			<div class="row">
				<h6>Are you sure you want to close this window. The form data
					will be lost?</h6>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<div class="input-field col s12 center">
						<!-- <button class="modal-close btn">yes</button> -->
						<a href="./uploadPaidStatus" class="btn">Yes</a>
						<button class="modal-close btn" style="margin-left: 10px;">no</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal End -->

	<!-- Modal start   -->

	<div id="regularisedDevice" class="modal">
        <h6  class="modal-header">Upload Paid Device</h6>
        <div class="modal-content">
            <div class="row">
                <h6 id="sucessMessage"> Your form has been successfully submitted. The Transaction ID for future reference is <span id="dynamicTxnId"></span></h6>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <div class="input-field col s12 center">
                        <a href="./uploadPaidStatus" class="btn">ok</a>
                    </div>
                </div>
            </div>
        </div>
    </div>

	<div id="deleteMsg" class="modal">
		<div class="modal-content">
			<h6 class="modal-header">Delete</h6>
			<div class="row">
				<h6>Are you sure you want to Delete this device information?</h6>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<button class="modal-close modal-trigger btn"
						data-target="confirmDeleteMsg">yes</button>
					<button class="modal-close btn" style="margin-left: 10px;">no</button>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal End -->

	<!-- Modal start   -->

	<div id="confirmDeleteMsg" class="modal">
		<div class="modal-content">
			<h6 class="modal-header">Delete</h6>
			<div class="row">
				<h6>This device information has been successfully deleted.</h6>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<button class="modal-close btn">ok</button>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal End -->

	<!-- Modal start   -->

	<div id="submitMsg" class="modal">
		<div class="modal-content">
			<h6 class="modal-header">Submit Request</h6>
			<div class="row">
				<h6>Your request has been successfully submitted.</h6>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<div class="input-field col s12 center">
						<button class="modal-close btn">ok</button>
						<!-- <a href="uploadPaidStatus1.htm" class="btn">ok</a> -->
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal End -->




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
	<%-- <script type="text/javascript"
		src="${context}/resources/project_js/uploadPaidStatus.js"></script> --%>
	<!-- ================================================
    Scripts
    ================================================ -->



	




	<script>
	
	function submitDeviceInfo(){
		 var formData= new FormData();
		 var nationalID = sessionStorage.getItem("nationalId");
			console.log(" nationalID ="+nationalID)
			 var fieldId=1;	
			 var regularizeDeviceDbs =[];
			 $('.deviceInformation').each(function() {	
				 var deviceType1=$('#deviceType'+fieldId).val();
					var serialNumber1=$('#serialNumber'+fieldId).val();
					var deviceIdType1=$('#deviceIdType'+fieldId).val();
					var taxStatus1=$('#taxStatus'+fieldId).val();
					var deviceStatus1=$('#deviceStatus'+fieldId).val();
					var Price1=$('#Price'+fieldId).val();
					var Currency1=$('#Currency'+fieldId).val();
					var IMEI1=$('#IMEIA'+fieldId).val();
					var IMEI2=$('#IMEIB'+fieldId).val();
					var IMEI3=$('#IMEIC'+fieldId).val();
					var IMEI4=$('#IMEID'+fieldId).val();
					var deviceCountry=$('#country'+fieldId).val();
					var multipleSimStatus1=$('#multipleSimStatus1'+fieldId).val();
				console.log("serialNumber1="+serialNumber1+" deviceIdType1="+deviceIdType1+" taxStatus1="+taxStatus1+" deviceStatus1="+deviceStatus1+" Price1="+Price1+" Currency1="+Currency1)
				var deviceInfo=
				{
					      "country": deviceCountry,
					      "currency": parseInt(Currency1),
					      "deviceIdType": parseInt(deviceType1),
					      "deviceSerialNumber": serialNumber1,
					      "deviceStatus": parseInt(deviceStatus1),
					      "deviceType": parseInt(deviceType1),
					      "firstImei": parseInt(IMEI1),
					      "secondImei": parseInt(IMEI2),
					      "thirdImei": parseInt(IMEI3),
					      "fourthImei": parseInt(IMEI4),
					      "multiSimStatus": deviceStatus1,
					      "price": parseFloat(Price1),
					      "taxPaidStatus": parseInt(taxStatus1),
					      "nid":nationalID,
					      "txnId":""
				}
				regularizeDeviceDbs.push(deviceInfo);  
				fieldId++;
			});
			 var request={
					 "country": null,
					 "email": null,
					  "firstName": null,
					  "lastName": null,
					  "locality": null,
					  "middleName": null,
					  "nid": nationalID,
					  "phoneNo": null,
					  "propertyLocation": null,
					  "province": null,
					  "street": null,
					  "regularizeDeviceDbs":regularizeDeviceDbs,
					  
					}
			 formData.append("request",JSON.stringify(request));
			$.ajax({
				url: './uploadPaidStatusForm',
				type: 'POST',
				data: formData,
				processData: false,
				contentType: false,
				success: function (data, textStatus, jqXHR) {
					console.log("in suucess method");
					console.log(data);
				   
					// $('#updateConsignment').modal();
					if(data.errorCode==200){

					//	$('#sucessMessage').text('');
					$('#regularisedDevice').openModal();
						$('#dynamicTxnId').text(data.txnId);
					}
					else{
						//$('#sucessMessage').text('');
						$('#regularisedDevice').openModal();
						$('#dynamicTxnId').text(data.txnId);
					}
					//sessionStorage.removeItem("nationalId");
				},
				error: function (jqXHR, textStatus, errorThrown) {
					console.log("error in ajax")
				
				}
			});
			return false;

			 } 
/* 	
	populateCountries(
            "country1",
            "state" ); */
   	 populateCountries
 	(   
 			"country1"
 	);
	
	$(document).ready(function () {
		console.log("start,..");
		$.getJSON('./getDropdownList/CUSTOMS_TAX_STATUS', function(data) {
			for (i = 0; i < data.length; i++) {
				$('<option>').val(data[i].value).text(data[i].interp)
				.appendTo('#taxStatus1');
				console.log("...........");
			}
		});



		$.getJSON('./getDropdownList/DEVICE_TYPE', function(data) {
			for (i = 0; i < data.length; i++) {
				$('<option>').val(data[i].value).text(data[i].interp)
				.appendTo('#deviceType1');
				console.log("...........");
			}
		});


		
		$.getJSON('./getDropdownList/DEVICE_ID_TYPE', function(data) {
			for (i = 0; i < data.length; i++) {
				$('<option>').val(data[i].value).text(data[i].interp)
				.appendTo('#deviceIdType1');
				console.log("...........");
			}
		});
		
		$.getJSON('./getDropdownList/currency', function(data) {
			for (i = 0; i < data.length; i++) {
				$('<option>').val(data[i].value).text(data[i].interp)
				.appendTo('#Currency1');
				console.log("...........");
			}
		});
		
		$.getJSON('./getDropdownList/MULTI_SIM_STATUS', function(data) {
			for (i = 0; i < data.length; i++) {
				$('<option>').val(data[i].value).text(data[i].interp)
				.appendTo('#multipleSimStatus1');
				console.log("...........");
			}
		});
		
		$.getJSON('./getDropdownList/DEVICE_STATUS', function(data) {
			for (i = 0; i < data.length; i++) {
				$('<option>').val(data[i].value).text(data[i].interp)
				.appendTo('#deviceStatus1');
				console.log("...........");
			}
		});
		
		
	        });

  /*       $(document).ready(function () {
            var max_fields = 15; //maximum input boxes allowed
            var wrapper = $(".input_fields_wrap"); //Fields wrapper
            var add_button = $(".add_field_button"); //Add button ID
            var x = 1; //initlal text box count
            $(add_button).click(function (e) { //on add input button click
                e.preventDefault();
                if (x < max_fields) { //max input box allowed
                    x++; //text box increment
                    $(wrapper).append(
                        '<div class="row"><div class="col s12 m6"><label for="deviceType">Device Type <spanclass="star">*</span></label><select class="browser-default" id="deviceType"><option value="" disabled selected>Device Type</option><option value="Handheld">Handheld</option><option value="MobilePhone">Mobile Phone/Feature phone</option><option value="Vehicle">Vehicle</option><option value="Portable">Portable(include PDA)</option><option value="Module">Module</option><option value="Dongle">Dongle</option><option value="WLAN">WLAN Router</option><option value="Modem">Modem</option><option value="Smartphone">Smartphone</option><option value="Computer">Connected Computer</option><option value="Tablet">Tablet</option><option value="e-Book">e-Book</option></select></div><div class="col s12 m6"><label for="deviceIdType">Device ID Type <span class="star">*</span></label><select class="browser-default" id="deviceType"><option value="" disabled selected>Select Device ID Type</option><option value="IMEI">IMEI</option><option value="ESN">ESN</option><option value="MEID">MEID</option></select></div><div class="col s12 m6"><label for="deviceType">Multiple Sim Status <span class="star">*</span></label><select class="browser-default" id="deviceType"><option value="" disabled selected>Multiple Sim Status</option><option value="Yes">Yes</option><option value="No">No</option></select></div><div class="col s12 m6"><label for="country">Country bought From <span class="star">*</span></label><select id="country1" class="browser-default" class="mySelect" style="padding-left: 0;" required></select></div><div class="input-field col s12 m6" style="margin-top: 25px;"><input type="text" id="serialNumber1" name="serialNumber1" pattern="[0-9]" title="Please enter your device serial number first" maxlength="20"><label for="serialNumber1">Device Serial Number <span class="star">*</span></label></div><div class="col s12 m6"><label for="deviceType">Tax paid Status <span class="star">*</span></label><select class="browser-default" id="deviceType"><option value="" disabled selected>Tax paid Status</option><option value="Regularized">Regularized</option><option value="Paid">Paid</option><option value="NotPaid">Not Paid</option></select></div></div><div class="row"><div class="col s12 m6" style="margin-top: -10px;"><label for="taxStatus">Device Status <span class="star">*</span></label><select class="browser-default" id="taxStatus"><option value="" disabled selected>Select Device Status</option><option value="New">New</option><option value="Used">Used</option><option value="Refurbished">Refurbished</option></select></div><div class="input-field col s12 m6 l6"><input type="text" name="Price" id="Price" maxlength="30"><label for="Price">Price <span class="star">*</span></label></div><div class="col s12 m6"><label for="Currency">Currency <span class="star">*</span></label><select class="browser-default" id="Currency"><option value="" disabled selected>Select Currency</option><option value="Regularized">$</option><option value="Paid">$</option><option value="NotPaid">$</option></select></div></div><div class="row"><div class="col s12 m12"><p>IMEI/MEID/ESN</p></div><div class="input-field col s12 m6"><input type="text" id="IMEI1" name="IMEI1" pattern="[0-9]"title="Please enter minimum 15 and maximum 16 digit only"maxlength="16"><label for="IMEI1">1 <span class="star">*</span></label></div><div class="input-field col s12 m6"><input type="text" id="IMEI2" name="IMEI2" pattern="[0-9]"title="Please enter minimum 15 and maximum 16 digit only"maxlength="16"><label for="IMEI2">2</label></div><div class="input-field col s12 m6"><input type="text" id="IMEI3" name="IMEI3" pattern="[0-9]"title="Please enter minimum 15 and maximum 16 digit only"maxlength="16"><label for="IMEI3">3</label></div><div class="input-field col s12 m6"><input type="text" id="IMEI4" name="IMEI4[]" pattern="[0-9]"title="Please enter minimum 15 and maximum 16 digit only"maxlength="16"><label for="IMEI4">4</label></div><div style="cursor:pointer;background-color:red;" class="remove_field btn right btn-info">Remove</div></div>'
                    ); //add input box
                }
            });
            $(wrapper).on("click", ".remove_field", function (e) { //user click on remove text
                e.preventDefault();
                $(this).parent('div').remove();
                x--;
            })
        }); */
        


        $(document).ready(function () {
                    var max_fields = 15; //maximum input boxes allowed
                    var wrapper = $(".mainDeviceInformation"); //Fields wrapper
                    var add_button = $(".add_field_button"); //Add button ID
                    var x = 1; //initlal text box count
                    var id=2;
                    $(add_button).click(function (e) { //on add input button click
                        e.preventDefault();
                        if (x < max_fields) { //max input box allowed
                            x++; //text box increment
                           
                            console.log("incremented value="+id)
                            $(wrapper).append(
                              /*  '<div style="margin-top:30px;"><div class="row"><div class="col s12 m6"><label for="deviceType">Device Type <spanclass="star">*</span></label><select class="browser-default" id='"deviceType"+id+"'><option value="" disabled selected>Device Type</option><option value='Handheld'>Handheld</option><option value='MobilePhone'>Mobile Phone/Feature phone</option><option value='Vehicle'>Vehicle</option><option value='Portable'>Portable(include PDA)</option><option value='Module'>Module</option><option value='Dongle'>Dongle</option><option value='WLAN'>WLAN Router</option><option value='Modem'>Modem</option><option value='Smartphone'>Smartphone</option><option value='Computer'>Connected Computer</option><option value='Tablet'>Tablet</option><option value='e-Book'>e-Book</option></select></div><div class='col s12 m6'><label for='deviceIdType'>Device ID Type <span class='star'>*</span></label><select class='browser-default' id='deviceIdType'><option value="" disabled selected>Select Device ID Type</option><option value='IMEI'>IMEI</option><option value='ESN'>ESN</option><option value='MEID'>MEID</option></select></div><div class='col s12 m6'><label for='deviceType'>Multiple Sim Status <span class='star'>*</span></label><select class='browser-default' id='deviceType'><option value="" disabled selected>Multiple Sim Status</option><option value='Yes'>Yes</option><option value='No'>No</option></select></div><div class='col s12 m6'><label for='country'>Country bought From <span class='star'>*</span></label><select id='country1' class='browser-default' class='mySelect' style='padding-left: 0;' required></select></div><div class='input-field col s12 m6' style='margin-top: 28px;'><input type='text' id='serialNumber1' name='serialNumber1' pattern='[0-9]' title='Please enter your device serial number first' maxlength='20'><label for='serialNumber1'>Device Serial Number <span class='star'>*</span></label></div><div class='col s12 m6'><label for='deviceType'>Tax paid Status <span class='star'>*</span></label><select class='browser-default' id='deviceType'><option value="" disabled selected>Tax paid Status</option><option value='Regularized'>Regularized</option><option value='Paid'>Paid</option><option value='NotPaid'>Not Paid</option></select></div></div><div class='row'><div class='col s12 m6' style='margin-top: -10px;'><label for='taxStatus'>Device Status <span class='star'>*</span></label><select class='browser-default' id='taxStatus'><option value='' disabled selected>Select Device Status</option><option value='New'>New</option><option value='Used'>Used</option><option value='Refurbished'>Refurbished</option></select></div><div class='input-field col s12 m6 l6'><input type='text' name='Price' id='Price' maxlength='30'><label for='Price'>Price <span class='star'>*</span></label></div><div class='col s12 m6'><label for='Currency'>Currency <span class='star'>*</span></label><select class='browser-default' id='Currency'><option value='' disabled selected>Select Currency</option><option value='Regularized'>$</option><option value='Paid'>$</option><option value="NotPaid">$</option></select></div></div><div class="row"><div class="col s12 m12"><p>IMEI/MEID/ESN</p></div><div class="input-field col s12 m6"><input type="text" id="IMEI1" name="IMEI1" pattern="[0-9]"title="Please enter minimum 15 and maximum 16 digit only"maxlength="16"><label for="IMEI1">1 <span class="star">*</span></label></div><div class="input-field col s12 m6"><input type="text" id="IMEI2" name="IMEI2" pattern="[0-9]"title="Please enter minimum 15 and maximum 16 digit only"maxlength="16"><label for="IMEI2">2</label></div><div class="input-field col s12 m6"><input type="text" id="IMEI3" name="IMEI3" pattern="[0-9]"title="Please enter minimum 15 and maximum 16 digit only"maxlength="16"><label for="IMEI3">3</label></div><div class="input-field col s12 m6"><input type="text" id="IMEI4" name="IMEI4[]" pattern="[0-9]"title="Please enter minimum 15 and maximum 16 digit only"maxlength="16"><label for="IMEI4">4</label></div></div><div style="cursor:pointer;background-color:red;' class='remove_field btn right btn-info'>Remove</div></div>'*/
                            		'<div id="deviceInformation'+id+'" class="deviceInformation"><div class="row"><div class="col s12 m6"><label for="deviceType">Device Type <spanclass="star">*</span></label><select class="browser-default" required id="deviceType'+id+'"><option value="" disabled selected>Device Type</option></select></div><div class="col s12 m6"><label for="deviceIdType'+id+'">Device ID Type <span class="star">*</span></label><select class="browser-default" required id="deviceIdType'+id+'"><option value="" disabled selected>Select Device ID Type</option></select></div><div class="col s12 m6"><label for="multipleSimStatus'+id+'">Multiple Sim Status <span class="star">*</span></label><select class="browser-default" required id="multipleSimStatus'+id+'"><option value="" disabled selected>Multiple Sim Status</option></select></div><div class="col s12 m6"><label for="deviceCountry">Country bought From <span class="star">*</span></label><select id="country'+id+'" class="browser-default" required class="mySelect" style="padding-left: 0;" required></select></div><div class="input-field col s12 m6" style="margin-top: 28px;"><input type="text" id="serialNumber'+id+'" required name="serialNumber'+id+'" pattern="[A-Za-z0-9]{0,15}" title="Please enter your device serial number first" maxlength="15"><label for="serialNumber'+id+'">Device Serial Number <span class="star">*</span></label></div><div class="col s12 m6"><label for="taxStatus'+id+'">Tax paid Status <span class="star">*</span></label><select class="browser-default" required id="taxStatus'+id+'"><option value="" disabled selected>Tax paid Status</option></select></div></div><div class="row"><div class="col s12 m6" style="margin-top: -10px;"><label for="deviceStatus'+id+'">Device Status <span class="star">*</span></label><select class="browser-default" required id="deviceStatus'+id+'"><option value="" disabled selected>Select Device Status</option></select></div><div class="input-field col s12 m6 l6"><input type="text" name="Price" id="Price'+id+'" required maxlength="30"><label for="Price'+id+'">Price <span class="star">*</span></label></div><div class="col s12 m6"><label for="Currency'+id+'">Currency <span class="star">*</span></label><select class="browser-default" required id="Currency'+id+'"><option value="" disabled selected>Select Currency</option></select></div></div><div class="row"><div class="col s12 m12"><p>IMEI/MEID/ESN</p></div><div class="input-field col s12 m6"><input type="text" id="IMEIA'+id+'" required name="IMEI1" pattern="[0-9]{15,16}"title="Please enter minimum 15 and maximum 16 digit only"maxlength="16"><label for="IMEIA'+id+'">1 <span class="star">*</span></label></div><div class="input-field col s12 m6"><input type="text"  id="IMEIB'+id+'" name="IMEI2" pattern="[0-9]{15,16}"title="Please enter minimum 15 and maximum 16 digit only"maxlength="16"><label for="IMEIB'+id+'">2</label></div><div class="input-field col s12 m6"><input type="text" id="IMEIC'+id+'"  name="IMEI3" pattern="[0-9]{15,16}"title="Please enter minimum 15 and maximum 16 digit only"maxlength="16"><label for="IMEIC'+id+'">3</label></div><div class="input-field col s12 m6"><input type="text" id="IMEID'+id+'"  name="IMEI4[]" pattern="[0-9]{15,16}"title="Please enter minimum 15 and maximum 16 digit only"maxlength="16"><label for="IMEID'+id+'">4</label></div></div><div style="cursor:pointer;background-color:red;" class="remove_field btn right btn-info">Remove</div></div>'
                            
                            );  //add input box
                          
                            populateCountries
                         	(   
                         			"country"+id
                         	);
                            $.getJSON('./getDropdownList/CUSTOMS_TAX_STATUS', function(data) {
                            	var dropdownid=id-1;
                        		for (i = 0; i < data.length; i++) {
                        			$('<option>').val(data[i].value).text(data[i].interp)
                        			.appendTo('#taxStatus'+dropdownid);
                        			
                        			console.log("+++++taxStatus"+dropdownid);
                        		}
                        	});



                        	$.getJSON('./getDropdownList/DEVICE_TYPE', function(data) {
                        		var dropdownid=id-1;
                        		for (i = 0; i < data.length; i++) {
                        			$('<option>').val(data[i].value).text(data[i].interp)
                        			.appendTo('#deviceType'+dropdownid);
                        			console.log('#deviceType'+dropdownid)
                        		}
                        	});


                        	
                        	$.getJSON('./getDropdownList/DEVICE_ID_TYPE', function(data) {
                        		var dropdownid=id-1;
                        		for (i = 0; i < data.length; i++) {
                        			$('<option>').val(data[i].value).text(data[i].interp)
                        			.appendTo('#deviceIdType'+dropdownid);
                        			console.log('#deviceIdType'+dropdownid);
                        		}
                        	});
                        	
                        	$.getJSON('./getDropdownList/currency', function(data) {
                        		var dropdownid=id-1;
                        		for (i = 0; i < data.length; i++) {
                        			$('<option>').val(data[i].value).text(data[i].interp)
                        			.appendTo('#Currency'+dropdownid);
                        			console.log('#Currency'+dropdownid);
                        		}
                        	});
                        	
                        	$.getJSON('./getDropdownList/MULTI_SIM_STATUS', function(data) {
                        		var dropdownid=id-1;
                        		for (i = 0; i < data.length; i++) {
                        			$('<option>').val(data[i].value).text(data[i].interp)
                        			.appendTo('#multipleSimStatus'+dropdownid);
                        			console.log('#multipleSimStatus'+dropdownid);
                        		}
                        	});
                        	
                        	$.getJSON('./getDropdownList/DEVICE_STATUS', function(data) {
                        		var dropdownid=id-1;
                        		for (i = 0; i < data.length; i++) {
                        			$('<option>').val(data[i].value).text(data[i].interp)
                        			.appendTo('#deviceStatus'+dropdownid);
                        			console.log('#deviceStatus'+dropdownid);
                        		}
                        	});
                            
                        	
                        	id++;
                        }
                    });
                    $(wrapper).on("click", ".remove_field", function (e) { //user click on remove text
                        e.preventDefault();
                        $(this).parent('div').remove();
                        x--;
                    })
                });
    </script>

</body>
</html>