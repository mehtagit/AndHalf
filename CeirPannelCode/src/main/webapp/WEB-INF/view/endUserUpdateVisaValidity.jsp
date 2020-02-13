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
<link rel="stylesheet"
	href="${context}/resources/project_css/intlTelInput.css">
<script src="http://malsup.github.io/jquery.blockUI.js"></script>
<!------------------------------------------- Dragable Model---------------------------------->
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>

<style>
</style>
</head>
<body>


	<section id="content">
		<!--start container-->
		<div class="container">
			<div class="section">
				<div class="row card-panel update-visa-responsive-page"
					style="width: 50%; margin: auto; margin-bottom: 50px; margin-top: 20px;">
					<h6 class="fixPage-modal-header ">
						<spring:message code="input.UpdateVisaValidity" />
					</h6>
					<div class="col s12 m12 l12">
						<div class="row">
							<div class="row">
								<form id="" onsubmit="return findEndUserByNid()" method="POST"
									enctype="multipart/form-data">
									<div id="submitbtn">
										<div class="input-field col s12 m1 l1">
											<label for="Search" class="center-align ml-10"><spring:message
													code="input.NID4" /></label>
										</div>
										<div class="input-field col s12 m3 l3">
											<input type="text" id="nidForEndUser"
												pattern="[A-Za-z0-9]{0,15}"
												oninput="InvalidMsg(this,'input');"
												oninvalid="InvalidMsg(this,'input');"
												title="<spring:message code="validation.maximum18" />"
												required / maxlength="15" name="Search" />

										</div>
										<div class="input-field col s12 m2 l2">
											<button class="btn" type="submit">
												<spring:message code="button.submit" />
											</button>
										</div>
									</div>
								</form>
							</div>
							<div id="EndUserInfoForm" style="display: none;">
								<form id="" onsubmit="return updateVisaDetails()" method="POST"
									enctype="multipart/form-data">
									<div class="row">
										<div class="input-field col s12 m6">
											<input type="text" id="endUserpassportNumber"
												name="endUserpassportNumber" placeholder=""
												pattern="[a-zA-Z0-9]{1,15}"
												oninput="InvalidMsg(this,'input');"
												oninvalid="InvalidMsg(this,'input');"
												title="<spring:message code="validation.15numbers" />"
												required  /  maxlength="15" /> <label
												for="endUserpassportNumber"><spring:message
													code="input.passportNo" /> <span class="star">*</span></label>
										</div>
										<input type="text" id="endUserTxnId" style="display: none">
										<div class="file-field col s12 m6" style="margin-top: -8px;">
											<h6 style="font-size: 12px;">
												<spring:message code="input.UploadImage" />
												<span class="star">*</span>
											</h6>
											<div class="btn" id="passportFileDiv">
												<span><spring:message code="input.selectfile" /></span> <input
													type="file"
													title="<spring:message code="validation.NoChosen" />"
													oninput="InvalidMsg(this,'input');"
													oninvalid="InvalidMsg(this,'input');"
													required / id="uploadPassportID">
											</div>
											<div class="file-path-wrapper" id="passportFileNameDiv">
												<input class="file-path validate" type="text"
													id="passportFileName" placeholder="" value="">
											</div>
										</div>

										<div class="input-field col s12 m4">
											<input type="text" id="endUserfirstName" placeholder=""
												pattern="[a-zA-Z]{1,20}" oninput="InvalidMsg(this,'input');"
												oninvalid="InvalidMsg(this,'input');"
												title="<spring:message code="validation.20Character" />"
												required  /  maxlength="20" /> <label
												for="endUserfirstName"><spring:message
													code="input.firstName" /> <span class="star">*</span></label>
										</div>

										<div class="input-field col s12 m4">
											<input type="text" id="endUsermiddleName" placeholder=""
												pattern="[a-zA-Z]" oninput="InvalidMsg(this,'input');"
												oninvalid="InvalidMsg(this,'input');"
												title="<spring:message code="validation.20Character" />"
												maxlength="20" /> <label for="endUsermiddleName"><spring:message
													code="input.middleName" /></label>
										</div>

										<div class="input-field col s12 m4">
											<input type="text" id="endUserlastName" placeholder=""
												pattern="[a-zA-Z]{1,20}" oninput="InvalidMsg(this,'input');"
												oninvalid="InvalidMsg(this,'input');"
												title="<spring:message code="validation.20Character" />"
												required  /  maxlength="20" /> <label for="endUserlastName"><spring:message
													code="input.lastName" /> <span class="star">*</span></label>
										</div>

										<div class="input-field col s12 m12 l12">
											<input type="text" placeholder=""
												oninput="InvalidMsg(this,'input');"
												oninvalid="InvalidMsg(this,'input');"
												title="<spring:message code="validation.50character" />"
												required  /                                                     
                                                     pattern="[^[a-zA-Z0-9\s,'-]*$]{0,200}"
												class="form-control boxBorder boxHeight" id="endUseraddress"
												maxlength="200"> <label for="address"><spring:message
													code="input.address" /> <span class="star">*</span></label>
										</div>

										<div class="input-field col s12 m6 l6">
											<input type="text" required="required" placeholder=""
												pattern="[^[a-zA-Z0-9\s,'-]*$]{0,50}"
												class="form-control boxBorder boxHeight"
												id="endUserstreetNumber" oninput="InvalidMsg(this,'input');"
												oninvalid="InvalidMsg(this,'input');"
												title="<spring:message code="validation.50character" />"
												required  /  maxlength="50"> <label
												for="streetNumber"><spring:message
													code="input.streetNumber" /> <span class="star">*</span></label>
										</div>
										<div class="input-field col s12 m6 l6">
											<input type="text" placeholder="" placeholder=""
												pattern="[^[a-zA-Z0-9\s,'-]*$]{0,50}"
												oninput="InvalidMsg(this,'input');"
												oninvalid="InvalidMsg(this,'input');"
												title="<spring:message code="validation.50character" />"
												required  /
                                                        class="form-control boxBorder boxHeight"
												id="endUserlocality" maxlength="20"> <label
												for="locality"><spring:message code="input.locality" />
												<span class="star">*</span></label>
										</div>

										<div class="input-field col s12 m6 l6">
											<input type="text" placeholder="" id="endUservillage"
												pattern="[^[a-zA-Z0-9\s,'-]*$]{0,50}"
												oninput="InvalidMsg(this,'input');"
												oninvalid="InvalidMsg(this,'input');"
												title="<spring:message code="validation.50character" />"
												required  / maxlength="20"> <label for="village"><spring:message
													code="input.village" /> <span class="star">*</span></label>
										</div>

										<div class="input-field col s12 m6 l6">
											<input type="text" placeholder=""
												pattern="[^[a-zA-Z0-9\s,'-]*$]{0,50}"
												oninput="InvalidMsg(this,'input');"
												oninvalid="InvalidMsg(this,'input');"
												title="<spring:message code="validation.50character" />"
												required  /   id="endUsercommune" maxlength="50"> <label
												for="commune"><spring:message code="input.commune" /><span
												class="star">*</span></label>
										</div>

										<div class="input-field col s12 m6 l6">
											<input type="text" placeholder="" id="endUserdistrict"
												pattern="[^[a-zA-Z0-9\s,'-]*$]{0,50}"
												oninput="setCustomValidity('')"
												oninvalid="this.setCustomValidity('<spring:message code="validation.requiredMsg" />')"
												title="<spring:message code="validation.50character" />"
												required  / maxlength="50"> <label for="district"><spring:message
													code="input.district" /> <span class="star">*</span></label>
										</div>

										<div class="input-field col s12 m6 l6">
											<input type="text" placeholder="" pattern="[0-9]{6,10}"
												oninput="InvalidMsg(this,'input');"
												oninvalid="InvalidMsg(this,'input');"
												title="<spring:message code="validation.10Numbers" />"
												class="form-control boxBorder boxHeight" id="endUserpin"
												required  / maxlength="20"> <label for="pin"><spring:message
													code="registration.postalcode" /><span class="star">*</span></label>
										</div>

										<div class="input-field col s12 m6 l6">
											<p
												style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">
												<spring:message code="input.Country" />
												<span class="star">*</span>
											</p>
											<select id="country" class="browser-default" required
												class="mySelect"
												title="<spring:message code="validation.selectFieldMsg" />"
												oninput="setCustomValidity('')"
												oninput="InvalidMsg(this,'input');"
												oninvalid="InvalidMsg(this,'input');"
												style="padding-left: 0;" required></select>
										</div>

										<div class="input-field col s12 m6 l6"
											style="margin-bottom: 5px;">
											<p
												style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">
												<spring:message code="input.province" />
												<span class="star">*</span>
											</p>
											<select id="state" class="browser-default" class="mySelect"
												title="<spring:message code="validation.selectFieldMsg" />"
												oninput="setCustomValidity('')"
												oninput="InvalidMsg(this,'select');"
												oninvalid="InvalidMsg(this,'select');"
												style="padding-left: 0;"required / ></select>
										</div>


										<div class="input-field col s12 m6" style="margin-top: 0;">
											<p class="contact-label">
												<spring:message code="input.contactNum" />
												<span class="star">*</span>
											</p>
											<input type="tel" oninput="setCustomValidity('')"
												oninvalid="this.setCustomValidity('<spring:message code="validation.requiredMsg" />')"
												title="<spring:message code="validation.requiredMsg" />"
												required  / placeholder="" id="phone" maxlength="15">
										</div>

										<div class="input-field col s12 m6">
											<input type="email" id="endUseremailID" placeholder=""
												title="<spring:message code="validation.Matchformat" />"
												oninput="InvalidMsg(this,'email');"
												oninvalid="InvalidMsg(this,'email');"
												required  / maxlength="30" /> <label for="emailID"><spring:message
													code="input.EmailID" /></label>
										</div>
									</div>

									<div class="row">
										<div class="col s12 m6">
											<label for="visaType"><spring:message
													code="input.VisaType" /> <span class="star">*</span></label> <select
												class="browser-default" id="endUservisaType" required
												title="<spring:message code="validation.selectFieldMsg" />"
												oninput="setCustomValidity('')"
												oninput="InvalidMsg(this,'select');"
												oninvalid="InvalidMsg(this,'select');"
												style="padding: 0; height: 35px;">
												<option value="" disabled selected><spring:message
														code="input.SelectVisaType" /></option>

											</select>
										</div>

										<div class="input-field col s12 m6" id="endUserdatepickerDiv">
											<input type="text" id="endUserdatepicker1" placeholder=""
												onchange="InvalidMsg(this,'date');"
												oninvalid="InvalidMsg(this,'date');"
												title="<spring:message code="validation.date" />"
												required / maxlength="15" /> <label for="datepicker1"><spring:message
													code="input.EntryCountry" /> <span class="star">*</span></label>
										</div>

										<div class="file-field col s12 m6" style="margin-top: -8px;">
											<h6>
												<spring:message code="input.UploadVisa" />
												<span class="star">*</span>
											</h6>
											<div class="btn">
												<span><spring:message code="input.selectfile" /></span> <input
													type="file"
													title="<spring:message code="validation.NoChosen" />"
													oninput="InvalidMsg(this,'fileType');"
													oninvalid="InvalidMsg(this,'fileType');"
													oninput="setCustomValidity('')"
													required /  id="endUseruploadnationalID"
													placeholder="Upload Visa Image">
											</div>
											<div class="file-path-wrapper">
												<input class="file-path validate" type="text"
													placeholder="Upload Visa Image">
											</div>
										</div>

										<div class="input-field col s12 m6" style="margin-top: 22px;">
											<input type="text" id="endUserdatepicker" placeholder=""
												onchange="InvalidMsg(this,'date');"
												oninvalid="InvalidMsg(this,'date');"
												title="<spring:message code="validation.date" />"
												required / maxlength="15" /> <label for="datepicker"><spring:message
													code="input.VisaExpiry" /> <span class="star">*</span></label>
										</div>
									</div>

									<p>
										<spring:message code="input.requiredfields" />
										<span class="star">*</span>
									</p>
									<div class="row">
										<div class="input-field col s12 m12 l12 center">
											<button id="updateVisaButton" class="btn" type="submit">
												<spring:message code="button.update" />
											</button>
											<a href="./redirectToHomePage" class="btn"
												style="margin-left: 10px;"><spring:message
													code="button.cancel" /></a>
										</div>
									</div>
								</form>
							</div>

							<!--  <div id="match-data" style="display: none;">
                                            <div class="row">
                                                <div class="input-field col s12 m12">
                                                    <input type="text" id="passportNumber" name="passportNumber"
                                                        pattern="[a-zA-Z0-9]" title="" maxlength="15" value="THU5463746"
                                                        disabled />
                                                    <label for="passportNumber">Passport Number <span
                                                            class="star">*</span></label>
                                                </div>

                                                <div class="input-field col s12 m4">
                                                    <input type="text" id="firstName" name="firstName"
                                                        pattern="[a-zA-Z]" title="" maxlength="15" value="Abc"
                                                        disabled />
                                                    <label for="firstName">First Name <span
                                                            class="star">*</span></label>
                                                </div>

                                                <div class="input-field col s12 m4">
                                                    <input type="text" id="middleName" name="middleName"
                                                        pattern="[a-zA-Z]" title="" maxlength="15" disabled />
                                                    <label for="middleName">Middle Name</label>
                                                </div>

                                                <div class="input-field col s12 m4">
                                                    <input type="text" id="lastName" name="lastName" pattern="[a-zA-Z]"
                                                        title="" maxlength="15" value="DEF" disabled />
                                                    <label for="lastName">Last Name <span class="star">*</span></label>
                                                </div>

                                                <div class="input-field col s12 m12 l12">
                                                    <input type="text" name="address" id="address"
                                                        value="12 kjdshkjdshfkdsj" disabled>
                                                    <label for="address">Address(Property Location) <span
                                                            class="star">*</span></label>
                                                </div>

                                                <div class="input-field col s12 m6 l6">
                                                    <input type="text" name="streetNumber" id="streetNumber"
                                                        maxlength="30" value="13 hsk" disabled>
                                                    <label for="streetNumber">Street Number <span
                                                            class="star">*</span></label>
                                                </div>

                                                <div class="input-field col s12 m6 l6">
                                                    <input type="text" name="locality" id="locality" maxlength="20"
                                                        value="dskjhkdjk" disabled>
                                                    <label for="locality">Locality <span class="star">*</span></label>
                                                </div>

                                                <div class="input-field col s12 m6 l6">
                                                    <input type="text" name="village" id="village" maxlength="20"
                                                        value="djhcjdshj" disabled>
                                                    <label for="village">Village <span class="star">*</span></label>
                                                </div>

                                                <div class="input-field col s12 m6 l6">
                                                    <input type="text" name="commune" id="commune" maxlength="20"
                                                        value="jhdscjhdsj" disabled>
                                                    <label for="commune">Commune <span class="star">*</span></label>
                                                </div>

                                                <div class="input-field col s12 m6 l6">
                                                    <input type="text" name="district" id="district" maxlength="20"
                                                        value="kjdsnckjdsnk" disabled>
                                                    <label for="district">District <span class="star">*</span></label>
                                                </div>

                                                <div class="input-field col s12 m6 l6">
                                                    <input type="text" name="pin" id="pin" maxlength="20" value="986753"
                                                        disabled>
                                                    <label for="pin">Pincode <span class="star">*</span></label>
                                                </div>

                                                <div class="input-field col s12 m6">
                                                    <input type="text" name="Country" id="Country" maxlength="20"
                                                        value="Abc Country" disabled>
                                                    <label for="Country">Country <span class="star">*</span></label>
                                                </div>

                                                <div class="input-field col s12 m6 l6">
                                                    <input type="text" name="Province" id="states" maxlength="20"
                                                        value="Abc" disabled>
                                                    <label for="state">Province <span class="star">*</span></label>
                                                </div>

                                                <div class="input-field col s12 m6">
                                                    <p class="contact-label">Contact Number <span class="star">*</span>
                                                    </p>
                                                    <input type="tel" name="phone" id="phone1" maxlength="15"
                                                        value="+91 6968674536" disabled>
                                                </div>

                                                <div class="input-field col s12 m6">
                                                    <input type="text" id="emailID" name="emailID"
                                                        pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" title=""
                                                        maxlength="30" value="mail@mail.com" disabled />
                                                    <label for="emailID">Email ID</label>
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="col s12 m6">
                                                <label for="visaType">Visa Type <span class="star">*</span></label>
                                                <select class="browser-default" id="visaType">
                                                    <option value="" disabled selected>Select Visa Type</option>
                                                    <option value="Yes">Tourist</option>
                                                    <option value="No">Other</option>
                                                </select>
                                            </div>

                                                <div class="input-field col s12 m6">
                                                    <input type="text" id="visaType" name="visaType" pattern="[]"
                                                        title="" maxlength="15" value="Tourist" disabled />
                                                    <label for="visaType">Visa Type <span class="star">*</span></label>
                                                </div>

                                                <div class="input-field col s12 m6">
                                                    <input type="text" id="datepicker1" name="entryDate" pattern="[]"
                                                        title="" maxlength="15" value="16/01/2020" disabled />
                                                    <label for="datepicker1">Entry Date In Country <span
                                                            class="star">*</span></label>
                                                </div>

                                                <div class="file-field col s12 m6" style="margin-top: -8px;">
                                                    <h6>Upload Visa Image <span class="star">*</span></h6>
                                                    <div class="btn">
                                                        <span>select file</span>
                                                        <input type="file" id="uploadnationalID" disabled>
                                                    </div>
                                                    <div class="file-path-wrapper">
                                                        <input class="file-path validate" type="text"
                                                            placeholder="Upload Visa Image" value="image.jpg" disabled>
                                                    </div>
                                                </div>

                                                <div class="input-field col s12 m6" style="margin-top: 22px;">
                                                    <input type="text" id="datepicker2" name="expiryDate" pattern="[]"
                                                        title="" maxlength="15" value="12/07/2020" />
                                                    <label for="datepicker2">Visa Expiry Date <span
                                                            class="star">*</span></label>
                                                </div>
                                            </div>

                                            
                                            <p>Required Field are marked with <span class="star">*</span></p>
                                            <div class="row">
                                                <div class="input-field col s12 m12 l12 center">
                                                    <a href="#successMsg" class="btn modal-trigger">Submit</a>
                                                    <a href="index.html" class="btn"
                                                        style="margin-left: 10px;">cancel</a>
                                                </div>
                                            </div>
                                        </div> -->
						</div>

					</div>
				</div>
			</div>
		</div>
		<div id="successMsg" class="modal open"
			style="width: 45%; z-index: 1003; opacity: 1; transform: scaleX(1); top: 10%;">
			<h6 class="modal-header">
				<spring:message code="button.submit" />
			</h6>
			<div class="modal-content">
				<div class="row">
					<h6 id="messageResponse"></h6>
				</div>
				<div class="row">
					<div class="input-field col s12 center">
						<div class="input-field col s12 center">
							<a href="./redirectToHomePage" class="btn"><spring:message
									code="modal.ok" /></a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--end container-->
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
		src="${context}/resources/project_js/endUserUpdateVisaValidity.js"></script>
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

