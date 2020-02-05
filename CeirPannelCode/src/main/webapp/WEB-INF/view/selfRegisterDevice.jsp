<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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

<script type="text/javascript"
	src="${context}/resources/js/plugins/jquery-1.11.2.min.js"></script>
<!--   <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css"></script>  
 -->
<jsp:include page="/WEB-INF/view/endUserHeader.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/endUserFooter.jsp"></jsp:include>

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
<!------------------------------------------- Dragable Model---------------------------------->
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>

<link rel="stylesheet"
	href="${context}/resources/project_css/intlTelInput.css">

<style>
</style>
</head>
<body>





	<section id="content">
		<!--start container-->
		<div class="container">
			<div class="section">
				<div class="row card-panel register-device-responsive-page"
					style="margin: auto">
					<form action="" onsubmit="return submitEndUserDeviceInfo()"
						method="POST" enctype="multipart/form-data">
						<div class="col s12 m12 l12">
							<div class="row">
								<h5>Register Device</h5>
								<hr style="margin-bottom: 0px;">
								<div class="row">
									<div class="col s12 m12">
										<label for="nationality">Nationality <span
											class="star">*</span></label>
										<div class=" boxHeight">
											<label><input class="with-gap"
												name="selectUSerViseForm" type="radio"
												onclick="showCambodianUserForm()" checked> <span>Cambodian</span>
											</label> <label> <input class="with-gap" type="radio"
												name="selectUSerViseForm" style="margin-left: 20px;"
												onclick="showOtherUserForm()" /> <span>Other</span>
											</label>
										</div>
									</div>
									<div id="nationalID">
										<div class="input-field col s12 m6">
											<input type="text" id="endUserNID" required="required"
												pattern="[A-Za-z0-9]{1,15}" title="Please enter alphabets and numbers upto 15 characters only" maxlength="15" /> <label
												id="endUserLabelNID" for="NID">National ID <span
												class="star">*</span></label>
										</div>
										<div class="file-field col s12 m6" style="margin-top: -8px;">
											<h6 style="font-size: 12px;" id="nidType">
												Upload ID Image <span class="star">*</span>
											</h6>
											<div class="btn">
												<span>select file</span> <input type="file" required="required" accept="image/*"
													id="uploadnationalID">
											</div>
											<div class="file-path-wrapper">
												<input class="file-path validate" type="text"
													id="nidPlaceHolder" placeholder="Upload Nid Image" value="">
											</div>
										</div>

										<div class="input-field col s12 m4">
											<input type="text" id="endUserfirstName" required="required"
												pattern="[a-zA-Z]{1,20}" title="Please enter alphabets  upto 20 characters only" maxlength="20" /> <label
												for="firstName">First Name <span class="star">*</span></label>
										</div>

										<div class="input-field col s12 m4">
											<input type="text" id="endUsermiddleName"
												pattern="[a-zA-Z]{1,20}" title="Please enter alphabets  upto 20 characters only" maxlength="20" /> <label
												for="middleName">Middle Name</label>
										</div>

										<div class="input-field col s12 m4">
											<input type="text" id="endUserlastName" required="required"
												pattern="[a-zA-Z]{1,20}" title="Please enter alphabets  upto 20 characters only" maxlength="20" /> <label
												for="lastName">Last Name <span class="star">*</span></label>
										</div>

										<div class="input-field col s12 m6" id="nationalityDiv" style="display: none">
											<input type="text" id="nationality" name="nationality"
												pattern="[a-zA-Z]{1,25}" title="Please enter alphabets and numbers upto 15 characters only" maxlength="25"> <label
												for="nationality" class="">Nationality <span
												class="star">*</span></label>
										</div>
										<div class="input-field col s12 m12 l12">
											<input type="text" pattern="[^[a-zA-Z0-9\s,'-]*$]{0,200}"
														title="Please enter alphabets and numbers upto 200 characters only"
														maxlength="200" required="required" class="form-control boxBorder boxHeight"
												id="address"> <label for="address">Address(Property
												Location) <span class="star">*</span>
											</label>
										</div>

										<div class="input-field col s12 m6 l6">
											<input type="text" class="form-control boxBorder boxHeight"
												id="streetNumber" pattern="[^[a-zA-Z0-9\s,'-]*$]{0,20}"
													required="required"	title="Please enter alphabets and numbers upto 20 characters only"
												maxlength="20"> <label
												for="streetNumber">Street Number <span class="star">*</span>
											</label>
										</div>

										<div class="input-field col s12 m6 l6">
											<input type="text" class="form-control boxBorder boxHeight"
												pattern="[^[a-zA-Z0-9\s,'-]*$]{0,50}" required="required"
														title="Please enter alphabets and numbers upto 50 characters only"
														class="form-control boxBorder boxHeight" id="locality"
												id="endUserlocality" maxlength="50"> <label
												for="locality">Locality <span class="star">*</span></label>
										</div>

										<div class="input-field col s12 m6 l6">
											<input type="text" id="village" pattern="[^[a-zA-Z0-9\s,'-]*$]{0,50}" required="required"
														title="Please enter alphabets and numbers upto 50 characters only" maxlength="50"> <label
												for="village">Village <span class="star">*</span>
											</label>
										</div>

										<div class="input-field col s12 m6 l6">
											<input type="text" id="commune" pattern="[a-zA-Z]{1,20}"
												title="" maxlength="20" required="required"> <label
												for="commune">Commune <span class="star">*</span></label>
										</div>

										<div class="input-field col s12 m6 l6">
											<input type="text" id="endUserdistrict"
												pattern="[^[a-zA-Z0-9\s,'-]*$]{0,50}" required="required"
														title="Please enter alphabets and numbers upto 50 characters only"maxlength="50"> <label for="district">District
												<span class="star">*</span>
											</label>
										</div>

										<div class="input-field col s12 m6 l6">
											<input type="text" class="form-control boxBorder boxHeight"
												id="pin" pattern="[0-9]{1,20}" title="Please enter Postel code upto 10 Numbers only" 
												maxlength="20" required="required"> <label for="pin">Pincode <span
												class="star">*</span></label>
										</div>

										<div class="input-field col s12 m6 l6">
											<p
												style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">
												Country <span class="star">*</span>
											</p>
											<select id="country" required class="browser-default" class="mySelect"
												style="padding-left: 0;" required></select>
										</div>

										<div class="input-field col s12 m6 l6">
											<p
												style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">
												Province <span class="star">*</span>
											</p>
											<select id="state" required class="browser-default" class="mySelect"
												style="padding-left: 0;" required></select>
										</div>

										<div class="input-field col s12 m6" style="margin: 0;">
											<p class="contact-label">
												Contact Number <span class="star">*</span>
											</p>
											<input type="tel" id="phone" required pattern="[0-9]{1,10}" title="Please enter 10 digits contact number"
												maxlength="10">
										</div>

										<div class="input-field col s12 m6">
											<input type="email" id="endUseremailID" required="required"
												pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" title=""
												maxlength="30" /> <label for="emailID">Email ID </label>
										</div>

										<div class="col s12 m12" style="height: 4rem;">
											<label for="nationality">VIP </label>
											<div class=" boxHeight">
												<label><input class="with-gap" type="radio"
													name="selectvip" value="Y" onclick="selectVip()"> <span>yes</span>
												</label> <label> <input class="with-gap" value="N"
													type="radio" checked="checked" name="selectvip" style="margin-left: 20px;"
													onclick="removeSelectVip()" /> <span>No</span>
												</label>
											</div>
										</div>

										<div class="row" style="display: none;" id="vipUserDiv">
											<div class="input-field col s12 m6">
												<input type="text" id="departmentName" 
												pattern="[a-zA-Z ]{1,50}" title="Please enter alphabets  upto 50 characters only"  maxlength="50" /> <label
													for="departmentName">Department Name <span
													class="star">*</span></label>
											</div>

											<div class="input-field col s12 m6">
												<input type="text" id="endUserdepartmentID"
													pattern="[a-zA-Z0-9]{1,15}" title="Please enter alphabets  upto 15 characters only" maxlength="15" /> <label
													for="departmentID">Department ID <span class="star">*</span>
												</label>
											</div>

											<div class="file-field input-field col s12 m6 l6"
												style="margin-top: 0;">
												<h6 style="color: #000; margin: 0;">
													Upload Department ID Image <span class="star">*</span>
												</h6>
												<div class="btn">
													<span>File</span> <input type="file" accept="image/*"
														id="endUserDepartmentId"
														placeholder="Upload Department ID Image">
												</div>
												<div class="file-path-wrapper">
													<input class="file-path validate" type="text"
														placeholder="Upload Department ID Image">
												</div>
											</div>
										</div>

										<div class="col s12 m12" style="height: 4rem; display: none"
											id="askVisaDetails">
											<label for="nationality">Add Visa <span class="star">*</span></label>
											<div class=" boxHeight">
												<label><input class="with-gap" type="radio"
													name="onVisa" value="Y" onclick="showVisaDetails()">
													<span>yes</span> </label> <label> <input class="with-gap"
													type="radio" id="onVisaNo" checked="checked" name="onVisa" value="N"
													style="margin-left: 20px;" onclick="hideVisaDetails()" />
													<span>No</span>
												</label>
											</div>
										</div>

										<div class="row" id="visaDetails" style="display: none;">
											<div class="col s12 m6">
												<label for="visaType">Visa Type <span class="star">*</span></label>
												<select class="browser-default" id="visaType">
													<option value="" disabled selected>Select Visa
														Type</option>

												</select>
											</div>

											<!-- <div class="input-field col s12 m6">
                                                    <input type="text" id="bdate2" class="datepicker" name="entryDate"
                                                        pattern="[]" title="" maxlength="20" />
                                                    <label for="bdate2">Entry Date In Country <span
                                                            class="star">*</span></label>
                                                </div> -->

											<div class="input-field col s12 m6">
												<input type="text" id="datepicker" title="" maxlength="15" />
												<label for="datepicker">Entry Date In Country <span
													class="star">*</span></label>
											</div>

											<div class="input-field col s12 m6">
												<input type="text" id="visaNumber"
													pattern="[a-zA-Z0-9]{1,15}" title="" maxlength="15" /> <label
													for="visaNumber">Visa Number</label>
											</div>

											<!-- <div class="input-field col s12 m6">
                                                    <input type="text" id="bdate" class="datepicker" name="expiryDate"
                                                        pattern="[]" title="" maxlength="15" />
                                                    <label for="bdate">Visa Expiry Date <span
                                                            class="star">*</span></label>
                                                </div> -->

											<div class="input-field col s12 m6">
												<input type="text" id="datepicker1" title="" maxlength="15" />
												<label for="datepicker1">Visa Expiry Date <span
													class="star">*</span></label>
											</div>

											<div class="file-field input-field col s12 m6"
												style="margin-top: 0;">
												<h6 style="color: #000; margin: 0;">
													Upload Visa Image <span class="star">*</span>
												</h6>
												<div class="btn">
													<span>File</span> <input type="file" id="visaImage" accept="image/*"
														placeholder="Upload Visa Image">
												</div>
												<div class="file-path-wrapper">
													<input class="file-path validate" type="text"
														placeholder="Upload Visa Image">
												</div>
											</div>
										</div>
										<div class="row">
											<div id="mainDeviceInformation" class="mainDeviceInformation">
												<div id="deviceInformation" class="deviceInformation">
													<div class="col s12 m6">
														<label for="deviceIdType">Device ID Type <span
															class="star">*</span></label> <select class="browser-default"
															id="deviceIdType1" required="required">
															<option value="" disabled selected>Select Device
																ID Type</option>

														</select>
													</div>


													<div class="col s12 m6">
														<label for="deviceType">Multiple Sim Status <span
															class="star">*</span></label> <select class="browser-default" required="required"
															id="multipleSimStatus1">
															<option value="" disabled selected>Multiple Sim
																Status</option>

														</select>
													</div>

													<div class="col s12 m6">
														<label for="deviceType">Device Type <span
															class="star">*</span></label> <select class="browser-default"
															style="height: 34px" id="deviceType1" required="required">
															<option value="" disabled selected>Device Type</option>

														</select>
													</div>
													<div class="input-field col s12 m6 l6">
														<p
															style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">
															Country <span class="star">*</span>
														</p>
														<select id="country1" class="browser-default" 
															style="margin-bottom: 5px;" class="mySelect"
															style="padding-left: 0;" required></select>
													</div>
													<div class="input-field col s12 m6"
														style="margin-top: 22px;">
														<input type="text" id="serialNumber1" required="required"
															pattern="[A-Za-z0-9]{0,15}"
																title="Please enter alphabets and numbers upto 15 characters only"
															maxlength="15"> <label for="serialNumber">Device
															Serial Number <span class="star">*</span>
														</label>
													</div>

													<div class="col s12 m6">
														<label for="taxStatus">Device Status <span
															class="star">*</span></label> <select class="browser-default"
															id="deviceStatus1"  required="required">
															<option value="" disabled="disabled" selected>Select
																Device Status</option>

														</select>
													</div>

													<div class="col s12 m12">
														<p>IMEI/MEID/ESN</p>
													</div>

													<div class="input-field col s12 m6">
														<input type="text" id="IMEIA1" pattern="[0-9]{16,16}"
															title="Please enter minimum 15 and maximum 16 digit only"
															maxlength="16" required="required"> <label for="IMEI1">1 <span
															class="star">*</span></label>
													</div>

													<div class="input-field col s12 m6">
														<input type="text" id="IMEIB1" pattern="[0-9]{16,16}"
															title="Please enter minimum 15 and maximum 16 digit only"
															maxlength="16"> <label for="IMEI2">2</label>
													</div>
													<div class="input-field col s12 m6">
														<input type="text" id="IMEIC1" pattern="[0-9]{16,16}"
															title="Please enter minimum 15 and maximum 16 digit only"
															maxlength="16"> <label for="IMEI1">3</label>
													</div>

													<div class="input-field col s12 m6">
														<input type="text" id="IMEID1" pattern="[0-9]{16,16}"
															title="Please enter minimum 15 and maximum 16 digit only"
															maxlength="16"> <label for="IMEI2">4</label>
													</div>


												</div>
											</div>
											<!-- <div class="input_fields_wrap_1"></div> -->

											<div class="row">
												<div class="col s12 m12">
													<button class="btn right add_field_button"
														style="margin-top: 5px;">
														<span style="font-size: 20px;">+</span> Add More Device
													</button>
												</div>
											</div>
										</div>


										<p>
											Required Field are marked with <span class="star">*</span>
										</p>
										<div class="row" style="padding-bottom: 50px;">
											<div class="input-field col s12 m12 center">
												<button id="endUserRegisterButton" type="submit" class="btn">Submit</button>
												<a href="./redirectToHomePage" class="btn"
													style="margin-left: 10px;">cancel</a>
											</div>
										</div>
									</div>
								</div>

							</div>
					</form>
				</div>

			</div>
		</div>
		</div>
		<!--end container-->
		<div id="endUserRegisterDeviceModal" class="modal">
     <h6 class="modal-header">Register Device</h6>
        <div class="modal-content">
           
            <div class="row">
                <!-- <h6>Your request to upload device details has been accepted. The Transaction ID is ___________. Please
                    save this for future reference.
                    Kindly check the status of file upload by clicking on the check upload status button on the previous
                    page and providing the Transaction ID. -->
                   <h6 id="sucessMessageId"> Your form has been successfully submitted. The Transaction ID for future reference is <span id="endUsertXnId"></span></h6>
             <!--    </h6> -->
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <div class="input-field col s12 center">
                        <a href="./redirectToHomePage" class="btn">ok</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
	</section>

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
		src="${context}/resources/project_js/selfRegisterDevice.js"></script>
	<script type="text/javascript"
		src="${context}/resources/project_js/dragableModal.js"></script>

	<script type="text/javascript"
		src="${context}/resources/project_js/enterKey.js"></script>

	<script type="text/javascript"
		src="${context}/resources/js/intlTelInput.js"></script>
	<script type="text/javascript">

	 var input = document.querySelector("#phone1");
     window.intlTelInput(input, {
         utilsScript: "js/utils.js",
     });
     
     var input = document.querySelector("#phone");
     window.intlTelInput(input, {
         utilsScript: "js/utils.js",
     });
	</script>



</body>
</html>