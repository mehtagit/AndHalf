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

<script type="text/javascript"
	src="${context}/resources/js/plugins/jquery-1.11.2.min.js"></script>
<!--   <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css"></script>  
 -->
<jsp:include page="/WEB-INF/view/endUserHeader.jsp" ></jsp:include>
<jsp:include page="/WEB-INF/view/endUserFooter.jsp" ></jsp:include>

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
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>

<style>

</style>
</head>
<body>

	
	
	
	
	 <section id="content">
                <!--start container-->
                <div class="container">
                    <div class="section">
                        <div class="row card-panel register-device-responsive-page" style="margin:auto">
                            <form action="">
                                <div class="col s12 m12 l12">
                                    <div class="row">
                                        <h5>Register Device</h5>
                                        <hr style="margin-bottom: 0px;">
                                        <div class="row">
                                            <div class="col s12 m12">
                                                <label for="nationality">Nationality <span class="star">*</span></label>
                                                <div class=" boxHeight">
                                                    <label><input class="with-gap" name="group3" type="radio"
                                                            onclick="document.getElementById('nationalID').style.display = 'block';document.getElementById('passportNmbr').style.display = 'none';"
                                                            checked>
                                                        <span>Cambodian</span>
                                                    </label>
                                                    <label>
                                                        <input class="with-gap" name="group3" type="radio"
                                                            style="margin-left: 20px;"
                                                            onclick="document.getElementById('passportNmbr').style.display = 'block';document.getElementById('nationalID').style.display = 'none';" />
                                                        <span>Other</span>
                                                    </label>
                                                </div>
                                            </div>
                                            <div id="nationalID">
                                                <div class="input-field col s12 m12">
                                                    <input type="text" id="NID" name="NID" pattern="[a-zA-Z0-9]"
                                                        title="" maxlength="15" />
                                                    <label for="NID">National ID <span class="star">*</span></label>
                                                </div>

                                                <div class="input-field col s12 m4">
                                                    <input type="text" id="firstName" name="firstName"
                                                        pattern="[a-zA-Z]" title="" maxlength="15" />
                                                    <label for="firstName">First Name <span
                                                            class="star">*</span></label>
                                                </div>

                                                <div class="input-field col s12 m4">
                                                    <input type="text" id="middleName" name="middleName"
                                                        pattern="[a-zA-Z]" title="" maxlength="15" />
                                                    <label for="middleName">Middle Name</label>
                                                </div>

                                                <div class="input-field col s12 m4">
                                                    <input type="text" id="lastName" name="lastName" pattern="[a-zA-Z]"
                                                        title="" maxlength="15" />
                                                    <label for="lastName">Last Name <span class="star">*</span></label>
                                                </div>

                                                <div class="input-field col s12 m12 l12">
                                                    <input type="text" name="address"
                                                        class="form-control boxBorder boxHeight" id="address">
                                                    <label for="address">Address(Property Location) <span
                                                            class="star">*</span></label>
                                                </div>

                                                <div class="input-field col s12 m6 l6">
                                                    <input type="text" name="streetNumber"
                                                        class="form-control boxBorder boxHeight" id="streetNumber"
                                                        maxlength="30">
                                                    <label for="streetNumber">Street Number <span
                                                            class="star">*</span></label>
                                                </div>

                                                <div class="input-field col s12 m6 l6">
                                                    <input type="text" name="locality"
                                                        class="form-control boxBorder boxHeight" id="locality"
                                                        maxlength="20">
                                                    <label for="locality">Locality <span class="star">*</span></label>
                                                </div>

                                                <div class="input-field col s12 m6 l6">
                                                    <input type="text" name="village" id="village" maxlength="20">
                                                    <label for="village">Village <span class="star">*</span></label>
                                                </div>

                                                <div class="input-field col s12 m6 l6">
                                                    <input type="text" name="commune" id="commune" maxlength="20">
                                                    <label for="commune">Commune <span class="star">*</span></label>
                                                </div>

                                                <div class="input-field col s12 m6 l6">
                                                    <input type="text" name="district" id="district" maxlength="20">
                                                    <label for="district">District <span class="star">*</span></label>
                                                </div>

                                                <div class="input-field col s12 m6 l6">
                                                    <input type="text" name="pin"
                                                        class="form-control boxBorder boxHeight" id="pin"
                                                        maxlength="20">
                                                    <label for="pin">Pincode <span class="star">*</span></label>
                                                </div>

                                                <div class="input-field col s12 m6 l6">
                                                    <p style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">
                                                        Country
                                                        <span class="star">*</span></p>
                                                    <select id="country" class="browser-default" class="mySelect"
                                                        style="padding-left: 0;" required></select>
                                                </div>

                                                <div class="input-field col s12 m6 l6">
                                                    <p style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">
                                                        Province
                                                        <span class="star">*</span></p>
                                                    <select id="state" class="browser-default" class="mySelect"
                                                        style="padding-left: 0;" required></select>
                                                </div>

                                                <div class="input-field col s12 m6">
                                                    <p class="contact-label">Contact Number <span class="star">*</span>
                                                    </p>
                                                    <input type="tel" name="phone" id="phone" maxlength="15">
                                                </div>

                                                <div class="input-field col s12 m6">
                                                    <input type="text" id="emailID" name="emailID"
                                                        pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" title=""
                                                        maxlength="30" />
                                                    <label for="emailID">Email ID </label>
                                                </div>

                                                <div class="col s12 m12" style="height: 4rem;">
                                                    <label for="nationality">VIP </label>
                                                    <div class=" boxHeight">
                                                        <label><input class="with-gap" name="group3" type="radio"
                                                                onclick="document.getElementById('vipDetails').style.display = 'block';document.getElementById('imeiInformation').style.display = 'none';">
                                                            <span>yes</span>
                                                        </label>
                                                        <label>
                                                            <input class="with-gap" name="group3" type="radio"
                                                                style="margin-left: 20px;"
                                                                onclick="document.getElementById('vipDetails').style.display = 'none';document.getElementById('imeiInformation').style.display = 'block';" />
                                                            <span>No</span>
                                                        </label>
                                                    </div>
                                                </div>

                                                <div class="row" id="vipDetails" style="display: none;">
                                                    <div class="input-field col s12 m6">
                                                        <input type="text" name="departmentName" id="departmentName"
                                                            maxlength="20" />
                                                        <label for="departmentName">Department Name <span
                                                                class="star">*</span></label>
                                                    </div>

                                                    <div class="input-field col s12 m6">
                                                        <input type="text" id="departmentID" name="departmentID"
                                                            pattern="[a-zA-Z0-9]" title="" maxlength="15" />
                                                        <label for="departmentID">Department ID <span
                                                                class="star">*</span></label>
                                                    </div>

                                                    <div class="file-field input-field col s12 m6 l6"
                                                        style="margin-top: 0;">
                                                        <h6 style="color: #000; margin: 0;">Upload Department ID
                                                            Image <span class="star">*</span></h6>
                                                        <div class="btn">
                                                            <span>File</span>
                                                            <input type="file" placeholder="Upload Department ID Image">
                                                        </div>
                                                        <div class="file-path-wrapper">
                                                            <input class="file-path validate" type="text"
                                                                placeholder="Upload Department ID Image">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col s12 m6">
                                                        <label for="deviceIdType">Device ID Type <span
                                                                class="star">*</span></label>
                                                        <select class="browser-default" id="deviceType">
                                                            <option value="" disabled selected>Select Device ID Type
                                                            </option>
                                                            <option value="IMEI">IMEI</option>
                                                            <option value="ESN">ESN</option>
                                                            <option value="MEID">MEID</option>
                                                        </select>
                                                    </div>


                                                    <div class="col s12 m6">
                                                        <label for="deviceType">Multiple Sim Status <span
                                                                class="star">*</span></label>
                                                        <select class="browser-default" id="deviceType">
                                                            <option value="" disabled selected>Multiple Sim Status
                                                            </option>
                                                            <option value="Yes">Yes</option>
                                                            <option value="No">No</option>
                                                        </select>
                                                    </div>

                                                    <div class="col s12 m6">
                                                        <label for="deviceType">Device Type <span
                                                                class="star">*</span></label>
                                                        <select class="browser-default" id="deviceType">
                                                            <option value="" disabled selected>Device Type</option>
                                                            <option value="Handheld">Handheld</option>
                                                            <option value="MobilePhone">Mobile Phone/Feature phone
                                                            </option>
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


                                                    <div class="col s12 m12">
                                                        <p>IMEI/MEID/ESN</p>
                                                    </div>

                                                    <div class="input-field col s12 m6">
                                                        <input type="text" id="IMEI1" name="IMEI1" pattern="[0-9]"
                                                            title="Please enter minimum 15 and maximum 16 digit only"
                                                            maxlength="16">
                                                        <label for="IMEI1">1 <span class="star">*</span></label>
                                                    </div>

                                                    <div class="input-field col s12 m6">
                                                        <input type="text" id="IMEI2" name="IMEI2" pattern="[0-9]"
                                                            title="Please enter minimum 15 and maximum 16 digit only"
                                                            maxlength="16">
                                                        <label for="IMEI2">2</label>
                                                    </div>

                                                    <!-- <div class="input-field col s12 m6">
                                                        <input type="text" id="serialNumber" name="serialNumber"
                                                            pattern="[0-9]"
                                                            title="Please enter your device serial number first"
                                                            maxlength="20">
                                                        <label for="serialNumber">Device Serial Number <span
                                                                class="star">*</span></label>
                                                    </div>

                                                    <div class="input-field col s12 m6">
                                                        <input type="text" id="simNumber" name="simNumber"
                                                            pattern="[0-9]" title="Please enter your sim number"
                                                            maxlength="20">
                                                        <label for="simNumber">Sim Number</label>
                                                    </div> -->
                                                </div>


                                                <div class="input_fields_wrap_1"></div>

                                                <div class="row">
                                                    <div class="col s12 m12"><button
                                                            class="btn right add_field_button_1"
                                                            style="margin-top: 5px;"><span
                                                                style="font-size: 20px;">+</span> Add More
                                                            Device</button>
                                                    </div>
                                                </div>
                                            </div>


                                            <div id="passportNmbr" style="display: none;">

                                                <div class="input-field col s12 m12">
                                                    <input type="text" id="passportNumber" name="passportNumber"
                                                        pattern="[a-zA-Z0-9]" title="" maxlength="15" />
                                                    <label for="passportNumber">Passport Number <span
                                                            class="star">*</span></label>
                                                </div>

                                                <div class="input-field col s12 m4">
                                                    <input type="text" id="firstName" name="firstName"
                                                        pattern="[a-zA-Z]" title="" maxlength="15" />
                                                    <label for="firstName">First Name <span
                                                            class="star">*</span></label>
                                                </div>

                                                <div class="input-field col s12 m4">
                                                    <input type="text" id="middleName" name="middleName"
                                                        pattern="[a-zA-Z]" title="" maxlength="15" />
                                                    <label for="middleName">Middle Name</label>
                                                </div>

                                                <div class="input-field col s12 m4">
                                                    <input type="text" id="lastName" name="lastName" pattern="[a-zA-Z]"
                                                        title="" maxlength="15" />
                                                    <label for="lastName">Last Name <span class="star">*</span></label>
                                                </div>

                                                <div class="input-field col s12 m12 l12">
                                                    <input type="text" name="address"
                                                        class="form-control boxBorder boxHeight" id="address">
                                                    <label for="address">Address(Property Location) <span
                                                            class="star">*</span></label>
                                                </div>

                                                <div class="input-field col s12 m6 l6">
                                                    <input type="text" name="streetNumber"
                                                        class="form-control boxBorder boxHeight" id="streetNumber"
                                                        maxlength="30">
                                                    <label for="streetNumber">Street Number <span
                                                            class="star">*</span></label>
                                                </div>

                                                <div class="input-field col s12 m6 l6">
                                                    <input type="text" name="locality"
                                                        class="form-control boxBorder boxHeight" id="locality"
                                                        maxlength="20">
                                                    <label for="locality">Locality <span class="star">*</span></label>
                                                </div>

                                                <div class="input-field col s12 m6 l6">
                                                    <input type="text" name="village" id="village" maxlength="20">
                                                    <label for="village">Village <span class="star">*</span></label>
                                                </div>

                                                <div class="input-field col s12 m6 l6">
                                                    <input type="text" name="commune" id="commune" maxlength="20">
                                                    <label for="commune">Commune <span class="star">*</span></label>
                                                </div>

                                                <div class="input-field col s12 m6 l6">
                                                    <input type="text" name="district" id="district" maxlength="20">
                                                    <label for="district">District <span class="star">*</span></label>
                                                </div>

                                                <div class="input-field col s12 m6 l6">
                                                    <input type="text" name="pin"
                                                        class="form-control boxBorder boxHeight" id="pin"
                                                        maxlength="20">
                                                    <label for="pin">Pincode <span class="star">*</span></label>
                                                </div>

                                                <div class="input-field col s12 m6 l6">
                                                    <p style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">
                                                        Country
                                                        <span class="star">*</span></p>
                                                    <select id="country1" class="browser-default" class="mySelect"
                                                        style="padding-left: 0;" required></select>
                                                </div>

                                                <div class="input-field col s12 m6 l6">
                                                    <p style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">
                                                        Province
                                                        <span class="star">*</span></p>
                                                    <select id="state1" class="browser-default" class="mySelect"
                                                        style="padding-left: 0;" required></select>
                                                </div>

                                                <div class="input-field col s12 m6">
                                                    <p class="contact-label">Contact Number <span class="star">*</span>
                                                    </p>
                                                    <input type="tel" name="phone" id="phone1" maxlength="15">
                                                </div>

                                                <div class="input-field col s12 m6">
                                                    <input type="text" id="emailID" name="emailID"
                                                        pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" title=""
                                                        maxlength="30" />
                                                    <label for="emailID">Email ID </label>
                                                </div>

                                                <div class="col s12 m12" style="height: 4rem;">
                                                    <label for="nationality">Add Visa <span
                                                            class="star">*</span></label>
                                                    <div class=" boxHeight">
                                                        <label><input class="with-gap" name="group3" type="radio"
                                                                onclick="document.getElementById('visaDetails').style.display = 'block';">
                                                            <span>yes</span>
                                                        </label>
                                                        <label>
                                                            <input class="with-gap" name="group3" type="radio"
                                                                style="margin-left: 20px;"
                                                                onclick="document.getElementById('visaDetails').style.display = 'none';" />
                                                            <span>No</span>
                                                        </label>
                                                    </div>
                                                </div>

                                                <div class="row" id="visaDetails" style="display: none;">
                                                    <div class="col s12 m6">
                                                        <label for="visaType">Visa Type <span
                                                                class="star">*</span></label>
                                                        <select class="browser-default" id="visaType">
                                                            <option value="" disabled selected>Select Visa Type</option>
                                                            <option value="Yes">Tourist</option>
                                                            <option value="No">Other</option>
                                                        </select>
                                                    </div>

                                                    <!-- <div class="input-field col s12 m6">
                                                    <input type="text" id="bdate2" class="datepicker" name="entryDate"
                                                        pattern="[]" title="" maxlength="20" />
                                                    <label for="bdate2">Entry Date In Country <span
                                                            class="star">*</span></label>
                                                </div> -->

                                                    <div class="input-field col s12 m6">
                                                        <input type="text" id="datepicker" name="entryDate" pattern="[]"
                                                            title="" maxlength="15" />
                                                        <label for="datepicker">Entry Date In Country <span
                                                                class="star">*</span></label>
                                                    </div>

                                                    <div class="input-field col s12 m6">
                                                        <input type="text" id="visaNumber" name="visaNumber"
                                                            pattern="[a-zA-Z0-9]" title="" maxlength="15" />
                                                        <label for="visaNumber">Visa Number <span
                                                                class="star">*</span></label>
                                                    </div>

                                                    <!-- <div class="input-field col s12 m6">
                                                    <input type="text" id="bdate" class="datepicker" name="expiryDate"
                                                        pattern="[]" title="" maxlength="15" />
                                                    <label for="bdate">Visa Expiry Date <span
                                                            class="star">*</span></label>
                                                </div> -->

                                                    <div class="input-field col s12 m6">
                                                        <input type="text" id="datepicker1" name="entryDate"
                                                            pattern="[]" title="" maxlength="15" />
                                                        <label for="datepicker1">Visa Expiry Date <span
                                                                class="star">*</span></label>
                                                    </div>

                                                    <div class="file-field input-field col s12 m6"
                                                        style="margin-top: 0;">
                                                        <h6 style="color: #000; margin: 0;">Upload Visa
                                                            Image <span class="star">*</span></h6>
                                                        <div class="btn">
                                                            <span>File</span>
                                                            <input type="file" placeholder="Upload Visa Image">
                                                        </div>
                                                        <div class="file-path-wrapper">
                                                            <input class="file-path validate" type="text"
                                                                placeholder="Upload Visa Image">
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="col s12 m12" style="height: 4rem;">
                                                    <label for="nationality">VIP </label>
                                                    <div class=" boxHeight">
                                                        <label><input class="with-gap" name="group3" type="radio"
                                                                onclick="document.getElementById('foreignerVipDetails').style.display = 'block';">
                                                            <span>yes</span>
                                                        </label>
                                                        <label>
                                                            <input class="with-gap" name="group3" type="radio"
                                                                style="margin-left: 20px;"
                                                                onclick="document.getElementById('foreignerVipDetails').style.display = 'none';" />
                                                            <span>No</span>
                                                        </label>
                                                    </div>
                                                </div>

                                                <div class="row" id="foreignerVipDetails" style="display: none;">
                                                    <div class="input-field col s12 m6">
                                                        <input type="text" name="departmentName" id="departmentName"
                                                            maxlength="20" />
                                                        <label for="departmentName">Department Name <span
                                                                class="star">*</span></label>
                                                    </div>

                                                    <div class="input-field col s12 m6">
                                                        <input type="text" id="departmentID" name="departmentID"
                                                            pattern="[a-zA-Z0-9]" title="" maxlength="15" />
                                                        <label for="departmentID">Department ID <span
                                                                class="star">*</span></label>
                                                    </div>

                                                    <div class="col s12 m6">
                                                        <label for="visaType">Visa Type <span
                                                                class="star">*</span></label>
                                                        <select class="browser-default" id="visaType">
                                                            <option value="" disabled selected>Select Visa Type</option>
                                                            <option value="Yes">Tourist</option>
                                                            <option value="No">Other</option>
                                                        </select>
                                                    </div>

                                                    <div class="file-field input-field col s12 m6 l6"
                                                        style="margin-top: 0;">
                                                        <h6 style="color: #000; margin: 0;">Upload Department ID
                                                            Image <span class="star">*</span></h6>
                                                        <div class="btn">
                                                            <span>File</span>
                                                            <input type="file" placeholder="Upload Department ID Image">
                                                        </div>
                                                        <div class="file-path-wrapper">
                                                            <input class="file-path validate" type="text"
                                                                placeholder="Upload Department ID Image">
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="row">
                                                    <div class="col s12 m6">
                                                        <label for="deviceIdType">Device ID Type <span
                                                                class="star">*</span></label>
                                                        <select class="browser-default" id="deviceType">
                                                            <option value="" disabled selected>Select Device ID Type
                                                            </option>
                                                            <option value="IMEI">IMEI</option>
                                                            <option value="ESN">ESN</option>
                                                            <option value="MEID">MEID</option>
                                                        </select>
                                                    </div>


                                                    <div class="col s12 m6">
                                                        <label for="deviceType">Multiple Sim Status <span
                                                                class="star">*</span></label>
                                                        <select class="browser-default" id="deviceType">
                                                            <option value="" disabled selected>Multiple Sim Status
                                                            </option>
                                                            <option value="Yes">Yes</option>
                                                            <option value="No">No</option>
                                                        </select>
                                                    </div>

                                                    <div class="col s12 m6">
                                                        <label for="deviceType">Device Type <span
                                                                class="star">*</span></label>
                                                        <select class="browser-default" id="deviceType">
                                                            <option value="" disabled selected>Device Type</option>
                                                            <option value="Handheld">Handheld</option>
                                                            <option value="MobilePhone">Mobile Phone/Feature phone
                                                            </option>
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

                                                    <div class="col s12 m12">
                                                        <p>IMEI/MEID/ESN</p>
                                                    </div>

                                                    <div class="input-field col s12 m6">
                                                        <input type="text" id="IMEI1" name="IMEI1" pattern="[0-9]"
                                                            title="Please enter minimum 15 and maximum 16 digit only"
                                                            maxlength="16">
                                                        <label for="IMEI1">1 <span class="star">*</span></label>
                                                    </div>

                                                    <div class="input-field col s12 m6">
                                                        <input type="text" id="IMEI2" name="IMEI2" pattern="[0-9]"
                                                            title="Please enter minimum 15 and maximum 16 digit only"
                                                            maxlength="16">
                                                        <label for="IMEI2">2</label>
                                                    </div>

                                                    <!-- <div class="input-field col s12 m6">
                                                    <input type="text" id="IMEI3" name="IMEI3" pattern="[0-9]"
                                                        title="Please enter minimum 15 and maximum 16 digit only"
                                                        maxlength="16">
                                                    <label for="IMEI3">3</label>
                                                </div>

                                                <div class="input-field col s12 m6">
                                                    <input type="text" id="IMEI4" name="IMEI4[]" pattern="[0-9]"
                                                        title="Please enter minimum 15 and maximum 16 digit only"
                                                        maxlength="16">
                                                    <label for="IMEI4">4</label>
                                                </div>

                                                <div class="input-field col s12 m6">
                                                    <input type="text" id="serialNumber" name="serialNumber"
                                                        pattern="[0-9]"
                                                        title="Please enter your device serial number first"
                                                        maxlength="20">
                                                    <label for="serialNumber">Device Serial Number <span
                                                            class="star">*</span></label>
                                                </div>

                                                <div class="input-field col s12 m6">
                                                    <input type="text" id="simNumber" name="simNumber" pattern="[0-9]"
                                                        title="Please enter your sim number" maxlength="20">
                                                    <label for="simNumber">Sim Number</label>
                                                </div> -->
                                                </div>

                                                <div class="input_fields_wrap"></div>
                                                <div class="row">
                                                    <div class="col s12 m12"><button class="btn right add_field_button"
                                                            style="margin-top: 5px;"><span
                                                                style="font-size: 20px;">+</span> Add More
                                                            Device</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <p>Required Field are marked with <span class="star">*</span></p>
                                    <div class="row" style="padding-bottom: 50px;">
                                        <div class="input-field col s12 m12 center">
                                            <a href="#successMsg" class="btn modal-trigger">Submit</a>
                                            <a href="index.html" class="btn" style="margin-left: 10px;">cancel</a>
                                        </div>
                                    </div>
                                </div>
                        </form>
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
		src="${context}/resources/project_js/uploadPaidStatus.js"></script>
	<script type="text/javascript"
		src="${context}/resources/project_js/dragableModal.js"></script>	
			



</body>
</html>