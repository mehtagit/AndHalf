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
					style="margin: auto;margin-top: 5vh;">
					<h6 class="fixPage-modal-header "> <spring:message code="modal.header.registerdevice" /></h6>
					<form action="" onsubmit="return submitEndUserDeviceInfo()"
						method="POST" enctype="multipart/form-data">
						<div class="col s12 m12 l12">
							<div class="row">
								<div class="row">
								
								<div >
													<h5><spring:message code="input.personalInformation" /></h5>
													<hr>
												</div>
									<div class="col s12 m12">
										<label for="nationality"><spring:message code="input.Nationality" /> <span
											class="star">*</span></label>
										<div class=" boxHeight">
											<label><input class="with-gap"
												name="selectUSerViseForm" type="radio"
												onclick="showCambodianUserForm()" checked> <span><spring:message code="input.Cambodian" /></span>
											</label> <label> <input class="with-gap" type="radio"
												name="selectUSerViseForm" style="margin-left: 20px;"
												onclick="showOtherUserForm()" /> <span><spring:message code="input.Other" /></span>
											</label>
										</div>
									</div>
									<div id="nationalID">
										<div class="input-field col s12 m6">
											<input type="text" id="endUserNID" required="required"
												pattern="[A-Za-z0-9]{1,15}" title="Please enter alphabets and numbers upto 15 characters only" maxlength="15" /> <label
												id="endUserLabelNID" for="NID"><spring:message code="registration.nationalid" />  <span
												class="star">*</span></label>
										</div>
										<div class="file-field col s12 m6" style="margin-top: -8px;">
											<h6 style="font-size: 12px;" id="nidType">
											<spring:message code="input.IDImage" />  <span class="star">*</span>
											</h6>
											<div class="btn">
												<span><spring:message code="input.selectfile" /> </span> <input type="file" required="required" accept="image/*"
													id="uploadnationalID">
											</div>
											<div class="file-path-wrapper">
												<input class="file-path validate" type="text"
													id="nidPlaceHolder" placeholder="Upload NID Image" value="">
											</div>
										</div>

										<div class="input-field col s12 m4">
											<input type="text" id="endUserfirstName" required="required"
												pattern="[a-zA-Z]{1,20}" title="Please enter alphabets  upto 20 characters only" maxlength="20" /> <label
												for="firstName"><spring:message code="input.firstName" /><span class="star">*</span></label>
										</div>

										<div class="input-field col s12 m4">
											<input type="text" id="endUsermiddleName"
												pattern="[a-zA-Z]{1,20}" title="Please enter alphabets  upto 20 characters only" maxlength="20" /> <label
												for="middleName"><spring:message code="input.middleName" /> </label>
										</div>

										<div class="input-field col s12 m4">
											<input type="text" id="endUserlastName" required="required"
												pattern="[a-zA-Z]{1,20}" title="Please enter alphabets  upto 20 characters only" maxlength="20" /> <label
												for="lastName"><spring:message code="input.lastName" /> <span class="star">*</span></label>
										</div>

										<div class="input-field col s12 m6" id="nationalityDiv" style="display: none">
											<input type="text" id="nationality" name="nationality"
												pattern="[a-zA-Z]{1,25}" title="Please enter alphabets and numbers upto 15 characters only" maxlength="25"> <label
												for="nationality" class=""><spring:message code="input.Nationality" />  <span
												class="star">*</span></label>
										</div>
										<div class="input-field col s12 m12 l12">
											<input type="text" pattern="[^[a-zA-Z0-9\s,'-]*$]{0,200}"
														title="Please enter alphabets and numbers upto 200 characters only"
														maxlength="200" required="required" class="form-control boxBorder boxHeight"
												id="address"> <label for="address"><spring:message code="input.address" /><span class="star">*</span>
											</label>
										</div>

										<div class="input-field col s12 m6 l6">
											<input type="text" class="form-control boxBorder boxHeight"
												id="streetNumber" pattern="[^[a-zA-Z0-9\s,'-]*$]{0,20}"
													required="required"	title="Please enter alphabets and numbers upto 20 characters only"
												maxlength="20"> <label
												for="streetNumber"><spring:message code="input.streetNumber" /> <span class="star">*</span>
											</label>
										</div>

										<div class="input-field col s12 m6 l6">
											<input type="text" class="form-control boxBorder boxHeight"
												pattern="[^[a-zA-Z0-9\s,'-]*$]{0,50}" required="required"
														title="Please enter alphabets and numbers upto 50 characters only"
														class="form-control boxBorder boxHeight" id="locality"
												id="endUserlocality" maxlength="50"> <label
												for="locality"><spring:message code="input.locality" /> <span class="star">*</span></label>
										</div>

										<div class="input-field col s12 m6 l6">
											<input type="text" id="village" pattern="[^[a-zA-Z0-9\s,'-]*$]{0,50}" required="required"
														title="Please enter alphabets and numbers upto 50 characters only" maxlength="50"> <label
												for="village"><spring:message code="input.village" /> <span class="star">*</span>
											</label>
										</div>

										<div class="input-field col s12 m6 l6">
											<input type="text" id="commune" pattern="[a-zA-Z]{1,20}"
												title="" maxlength="20" required="required"> <label
												for="commune"><spring:message code="input.commune" /> <span class="star">*</span></label>
										</div>

										<div class="input-field col s12 m6 l6">
											<input type="text" id="endUserdistrict"
												pattern="[^[a-zA-Z0-9\s,'-]*$]{0,50}" required="required"
														title="Please enter alphabets and numbers upto 50 characters only"maxlength="50"> <label for="district"><spring:message code="input.district" />
												<span class="star">*</span>
											</label>
										</div>

										<div class="input-field col s12 m6 l6">
											<input type="text" class="form-control boxBorder boxHeight"
												id="pin" pattern="[0-9]{1,20}" title="Please enter Postel code upto 10 Numbers only" 
												maxlength="20" required="required"> <label for="pin"><spring:message code="registration.postalcode" /><span
												class="star">*</span></label>
										</div>

										<div class="input-field col s12 m6 l6">
											<p
												style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">
												<spring:message code="input.Country" /> <span class="star">*</span>
											</p>
											<select id="country" required class="browser-default" class="mySelect"
												style="padding-left: 0;" required></select>
										</div>

										<div class="input-field col s12 m6 l6" style="margin-bottom: 5px;">
											<p
												style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">
												<spring:message code="input.province" /> <span class="star">*</span>
											</p>
											<select id="state" required class="browser-default" class="mySelect"
												style="padding-left: 0;" required></select>
										</div>

										<div class="input-field col s12 m6" style="margin: 0;">
											<p class="contact-label">
												<spring:message code="input.contactNum" /> <span class="star">*</span>
											</p>
											<input type="tel" id="phone" required pattern="[0-9]{1,10}" title="Please enter 10 digits contact number"
												maxlength="10">
										</div>

										<div class="input-field col s12 m6">
											<input type="email" id="endUseremailID" 
												pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" title=""
												maxlength="30" /> <label for="emailID"><spring:message code="input.EmailID" /> </label>
										</div>

										<div class="col s12 m12" style="height: 4rem;">
											<label for="nationality"><spring:message code="input.VIP" /> </label>
											<div class=" boxHeight">
												<label><input class="with-gap" type="radio"
													name="selectvip" value="Y" onclick="selectVip()"> <span><spring:message code="modal.yes" /></span>
												</label> <label> <input class="with-gap" value="N"
													type="radio" checked="checked" name="selectvip" style="margin-left: 20px;"
													onclick="removeSelectVip()" /> <span><spring:message code="modal.no" /></span>
												</label>
											</div>
										</div>

										<div class="row" style="display: none;" id="vipUserDiv">
											<div class="input-field col s12 m6">
												<input type="text" id="departmentName" 
												pattern="[a-zA-Z ]{1,50}" title="Please enter alphabets  upto 50 characters only"  maxlength="50" /> <label
													for="departmentName"><spring:message code="input.DepartmentName" /> <span
													class="star">*</span></label>
											</div>

											<div class="input-field col s12 m6">
												<input type="text" id="endUserdepartmentID"
													pattern="[a-zA-Z0-9]{1,15}" title="Please enter alphabets  upto 15 characters only" maxlength="15" /> <label
													for="departmentID"><spring:message code="input.DepartmentID" /><span class="star">*</span>
												</label>
											</div>

											<div class="file-field input-field col s12 m6 l6"
												>
												<h6 style="color: #000;">
													<spring:message code="input.UploadIDImage" /> <span class="star">*</span>
												</h6>
												<div class="btn">
													<span><spring:message code="operator.file" /></span> <input type="file" accept="image/*"
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
											<label for="nationality"><spring:message code="input.AddVisa" /> <span class="star">*</span></label>
											<div class=" boxHeight">
												<label><input class="with-gap" type="radio"
													name="onVisa" value="Y" onclick="showVisaDetails()">
													<span><spring:message code="modal.yes" /></span> </label> <label> <input class="with-gap"
													type="radio" id="onVisaNo" checked="checked" name="onVisa" value="N"
													style="margin-left: 20px;" onclick="hideVisaDetails()" />
													<span><spring:message code="modal.no" /></span>
												</label>
											</div>
										</div>

										<div class="row" id="visaDetails" style="display: none;">
											<div class="col s12 m6">
												<label for="visaType"><spring:message code="input.VisaType" /> <span class="star">*</span></label>
												<select class="browser-default" id="visaType" style="height: 33px">
													<option value="" disabled selected><spring:message code="input.SelectVisaType" /></option>

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
												<label for="datepicker"><spring:message code="input.EntryCountry" /> <span
													class="star">*</span></label>
											</div>

											<div class="input-field col s12 m6">
												<input type="text" id="visaNumber"
													pattern="[a-zA-Z0-9]{1,15}" title="" maxlength="15" /> <label
													for="visaNumber"><spring:message code="input.VisaNumber" /></label>
											</div>

											<!-- <div class="input-field col s12 m6">
                                                    <input type="text" id="bdate" class="datepicker" name="expiryDate"
                                                        pattern="[]" title="" maxlength="15" />
                                                    <label for="bdate">Visa Expiry Date <span
                                                            class="star">*</span></label>
                                                </div> -->

											<div class="input-field col s12 m6">
												<input type="text" id="datepicker1" title="" maxlength="15" />
												<label for="datepicker1"><spring:message code="input.VisaExpiry" /> <span
													class="star">*</span></label>
											</div>

											<div class="file-field input-field col s12 m6"
												>
												<h6 style="color: #000;">
													 <spring:message code="input.UploadVisa" /><span class="star">*</span>
												</h6>
												<div class="btn">
													<span><spring:message code="operator.file" /></span> <input type="file" id="visaImage" accept="image/*"
														placeholder="Upload Visa Image">
												</div>
												<div class="file-path-wrapper">
													<input class="file-path validate" type="text"
														placeholder="Upload Visa Image">
												</div>
											</div>
										</div>
										<div class="row">
										<div class="col s12 m12" style="margin-top: 30px;">
															<h5><spring:message code="modal.deviceInfo" /></h5>
															<hr>
														</div>
											<div id="mainDeviceInformation" class="mainDeviceInformation">
												<div id="deviceInformation" class="deviceInformation">
													<div class="col s12 m6">
														<label for="deviceIdType"><spring:message code="select.deviceIDType" /><span
															class="star">*</span></label> <select class="browser-default"
															id="deviceIdType1" required="required">
															<option value="" disabled selected><spring:message code="select.selectDeviceIDType" /></option>

														</select>
													</div>


													<div class="col s12 m6">
														<label for="deviceType"><spring:message code="select.multiSimStatus" /> <span
															class="star">*</span></label> <select class="browser-default" required="required"
															id="multipleSimStatus1">
															<option value="" disabled selected><spring:message code="select.multiSimStatus" /></option>

														</select>
													</div>

													<div class="col s12 m6">
														<label for="deviceType"><spring:message code="select.deviceType" /><span
															class="star">*</span></label> <select class="browser-default"
															style="height: 34px" id="deviceType1" required="required">
															<option value="" disabled selected><spring:message code="select.deviceType" /></option>

														</select>
													</div>
													<div class="input-field col s12 m6 l6">
														<p
															style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">
															<spring:message code="input.Country" /><span class="star">*</span>
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
															maxlength="15"> <label for="serialNumber"><spring:message code="input.deviceSerialNumber" /> <span class="star">*</span>
														</label>
													</div>

													<div class="col s12 m6">
														<label for="taxStatus"><spring:message code="select.deviceStatus" /> <span
															class="star">*</span></label> <select class="browser-default"
															id="deviceStatus1"  required="required">
															<option value="" disabled="disabled" selected><spring:message code="select.selectDeviceStatus" /></option>

														</select>
													</div>

													<div class="col s12 m12">
														<p><spring:message code="title.imeiMeidEsn" /></p>
													</div>

													<div class="input-field col s12 m6">
														<input type="text" id="IMEIA1" pattern="[0-9]{16,16}"
															title="Please enter minimum 15 and maximum 16 digit only"
															maxlength="16" required="required"> <label for="IMEI1"><spring:message code="title.one" /> <span
															class="star">*</span></label>
													</div>

													<div class="input-field col s12 m6">
														<input type="text" id="IMEIB1" pattern="[0-9]{16,16}"
															title="Please enter minimum 15 and maximum 16 digit only"
															maxlength="16"> <label for="IMEI2"><spring:message code="title.two" /></label>
													</div>
													<div class="input-field col s12 m6">
														<input type="text" id="IMEIC1" pattern="[0-9]{16,16}"
															title="Please enter minimum 15 and maximum 16 digit only"
															maxlength="16"> <label for="IMEI1"><spring:message code="title.three" /></label>
													</div>

													<div class="input-field col s12 m6">
														<input type="text" id="IMEID1" pattern="[0-9]{16,16}"
															title="Please enter minimum 15 and maximum 16 digit only"
															maxlength="16"> <label for="IMEI2"><spring:message code="title.four" /></label>
													</div>


												</div>
											</div>
											<!-- <div class="input_fields_wrap_1"></div> -->

											<div class="row">
												<div class="col s12 m12">
													<button class="btn right add_field_button"
														style="margin-top: 5px;">
														<span style="font-size: 20px;">+</span><spring:message code="button.addMoreDevice" />
													</button>
												</div>
											</div>
										</div>


										<p>
											<spring:message code="input.requiredfields" /> <span class="star">*</span>
										</p>
										<div class="row" style="padding-bottom: 50px;">
											<div class="input-field col s12 m12 center">
												<button id="endUserRegisterButton" type="submit" class="btn"><spring:message code="button.submit" /></button>
												<a href="./redirectToHomePage" class="btn"
													style="margin-left: 10px;"><spring:message code="button.cancel" /></a>
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
     <h6 class="modal-header"><spring:message code="modal.header.registerdevice" /></h6>
        <div class="modal-content">
           
            <div class="row">
                <!-- <h6>Your request to upload device details has been accepted. The Transaction ID is ___________. Please
                    save this for future reference.
                    Kindly check the status of file upload by clicking on the check upload status button on the previous
                    page and providing the Transaction ID. -->
                   <h6 id="sucessMessageId"><spring:message code="modal.message.futureRef" /> <span id="endUsertXnId"></span></h6>
             <!--    </h6> -->
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <div class="input-field col s12 center">
                        <a href="./redirectToHomePage" class="btn"><spring:message code="modal.ok" /></a>
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