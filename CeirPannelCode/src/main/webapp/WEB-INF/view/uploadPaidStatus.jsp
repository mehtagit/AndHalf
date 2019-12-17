<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
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

 <link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
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

        .dropdown-content li>a,
        .dropdown-content li>span {
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
<body data-roleType="${usertype}" data-userTypeID="${usertypeId}" data-userID="${userid}" data-selected-roleType="${selectedUserTypeId}" data-stolenselected-roleType="${stolenselectedUserTypeId}" data-selected-consignmentTxnId="${consignmentTxnId}" data-selected-consignmentStatus="${consignmentStatus}">


<!-- //////////////////////////////////////////////////////////////////////////// -->

            <!-- START CONTENT -->
            <section id="content">
                <!--start container-->
                <div class="container">
                    <div class="section">
                        <div class="row">
                            <div class="col s12 m12 l12">
                                <div class="row card-panel">
                                    <div class="container-fluid pageHeader">
                                        <p class="PageHeading">Upload Paid Status</p>
                                        <a href="addDeviceInformation.html" class="boton right" id="addbutton"
                                            style="display: none;">Add Device</a>
                                    </div>
                                    <div class="row">
                                        <div class="col s12 m12" style="margin-top: 20px;">
                                            <div id="submitbtn">
                                                <div class="input-field col s12 m1 l1">
                                                    <label for="Search" class="center-align ml-10">NID :</label>
                                                </div>
                                                <div class="input-field col s12 m3 l3">
                                                    <input type="text" id="Search" name="Search" placeholder="NID" />
                                                </div>
                                                <div class="input-field col s12 m2 l2">
                                                    <button class="btn" type="submit" onclick="hide();">Submit</button>
                                                </div>
                                            </div>
                                        </div>


                                        <div id="user123" class="section" style="display: none;">
                                            <form action="">
                                                <div class="row">
                                                    <div class="col s12 m12">
                                                        <div class="col s12 m12">
                                                            <h5>Personal Information</h5>
                                                            <hr>
                                                        </div>
                                                        </div>
                                                        <div class="col s12 m12" style="margin-top: 20px;">
                                                        <div class="input-field col s12 m6">
                                                            <input type="text" id="nationalID" name="nationalID"
                                                                value="black" />
                                                            <label for="nationalID" class="center-align ml-10">NID
                                                            </label>
                                                        </div>

                                                        <div class="file-field input-field col s12 m6"
                                                            style="margin-top: -15px;">
                                                            <h6 style="color: #000;">Upload National ID Proof <span
                                                                    class="star">*</span>
                                                            </h6>
                                                            <div class="btn">
                                                                <span>Select File</span>
                                                                <input type="file" id="csvUploadFile" accept=".csv">
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
                                                                maxlength="20">
                                                            <label for="firstName" class="center-align">First Name <span
                                                                    class="star">*</span></label>
                                                        </div>

                                                        <div class="input-field col s12 m4 l4">
                                                            <input type="text" name="middleName" id="middleName"
                                                                maxlength="20">
                                                            <label for="middleName">Middle Name</label>
                                                        </div>

                                                        <div class="input-field col s12 m4 l4">
                                                            <input type="text" name="lastName" id="lastName"
                                                                maxlength="20">
                                                            <label for="lastName">Last Name <span
                                                                    class="star">*</span></label>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="row">
                                                    <div class="col s12 m12">
                                                        <div class="input-field col s12 m12 l12">
                                                            <input type="text" name="address"
                                                                class="form-control boxBorder boxHeight" id="address">
                                                            <label for="address">Address(Property Location) <span
                                                                    class="star">*</span></label>
                                                        </div>

                                                        <div class="input-field col s12 m6 l6">
                                                            <input type="text" name="streetNumber"
                                                                class="form-control boxBorder boxHeight"
                                                                id="streetNumber" maxlength="30">
                                                            <label for="streetNumber">Street Number <span
                                                                    class="star">*</span></label>
                                                        </div>

                                                        <div class="input-field col s12 m6 l6">
                                                            <input type="text" name="locality"
                                                                class="form-control boxBorder boxHeight" id="locality"
                                                                maxlength="20">
                                                            <label for="locality">Locality <span
                                                                    class="star">*</span></label>
                                                        </div>

                                                        <div class="input-field col s12 m6 l6">
                                                            <p
                                                                style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">
                                                                Country <span class="star">*</span></p>
                                                            <select id="country" class="browser-default"
                                                                class="mySelect" style="padding-left: 0;"
                                                                required></select>
                                                        </div>

                                                        <div class="input-field col s12 m6 l6">
                                                            <p
                                                                style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">
                                                                Province <span class="star">*</span></p>
                                                            <select id="state" class="browser-default" class="mySelect"
                                                                style="padding-left: 0;" required></select>
                                                        </div>
                                                    </div>

                                                    <div class="col s12 m12" style="margin-top: 10px;">
                                                        <div class="input-field col s12 m6 l6">
                                                            <input type="text" name="email" id="email" maxlength="30">
                                                            <label for="email">Email <span class="star">*</span></label>
                                                        </div>

                                                        <div class="input-field col s12 m6 l6">
                                                            <input type="text" name="phone"
                                                                class="form-control boxBorder boxHeight" id="phone"
                                                                maxlength="10">
                                                            <label for="phone">Contact Number <span
                                                                    class="star">*</span></label>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="row">
                                                    <div class="col s12 m12">
                                                        <div class="col s12 m12" style="margin-top: 30px;">
                                                            <h5>Device Information</h5>
                                                            <hr>
                                                        </div>

                                                        <div class="col s12 m6">
                                                            <label for="deviceType">Device Type <span
                                                                    class="star">*</span></label>
                                                            <select class="browser-default" id="deviceType">
                                                                <option value="" disabled selected>Select Device Type
                                                                </option>
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

                                                        <div class="col s12 m6"><label for="deviceIdType">Device ID Type
                                                                <span class="star">*</span></label>
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
                                                                <option value="" disabled selected>Select Multiple Sim
                                                                    Status
                                                                </option>
                                                                <option value="Yes">Yes</option>
                                                                <option value="No">No</option>
                                                            </select>
                                                        </div>

                                                        <div class="col s12 m6">
                                                            <label for="country">Country bought From <span
                                                                    class="star">*</span></label>
                                                            <select id="country" class="browser-default"
                                                                class="mySelect" style="padding-left: 0;"
                                                                required></select>
                                                        </div>

                                                        <div class="input-field col s12 m6" style="margin-top: 28px;">
                                                            <input type="text" id="serialNumber" name="serialNumber"
                                                                pattern="[0-9]"
                                                                title="Please enter your device serial number first"
                                                                maxlength="20">
                                                            <label for="serialNumber">Device Serial Number <span
                                                                    class="star">*</span></label>
                                                        </div>

                                                        <div class="col s12 m6">
                                                            <label for="taxStatus">Tax paid Status <span
                                                                    class="star">*</span></label>
                                                            <select class="browser-default" id="taxStatus">
                                                                <option value="" disabled selected>Select Tax paid
                                                                    Status
                                                                </option>
                                                                <option value="Regularized">Regularized</option>
                                                                <option value="Paid">Paid</option>
                                                                <option value="NotPaid">Not Paid</option>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col s12 m12">
                                                        <div class="col s12 m6" style="margin-top: -10px;">
                                                            <label for="taxStatus">Device Status <span
                                                                    class="star">*</span></label>
                                                            <select class="browser-default" id="taxStatus">
                                                                <option value="" disabled selected>Select Device Status
                                                                </option>
                                                                <option value="New">New</option>
                                                                <option value="Used">Used</option>
                                                                <option value="Refurbished">Refurbished</option>
                                                            </select>
                                                        </div>

                                                        <div class="input-field col s12 m6 l6">
                                                            <input type="text" name="Price" id="Price" maxlength="30">
                                                            <label for="Price">Price <span class="star">*</span></label>
                                                        </div>

                                                        <div class="col s12 m6">
                                                            <label for="Currency">Currency <span
                                                                    class="star">*</span></label>
                                                            <select class="browser-default" id="Currency">
                                                                <option value="" disabled selected>Select Currency
                                                                </option>
                                                                <option value="Regularized">$</option>
                                                                <option value="Paid">$</option>
                                                                <option value="NotPaid">$</option>
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
                                                                    <input type="text" id="IMEI1" name="IMEI1"
                                                                        pattern="[0-9]"
                                                                        title="Please enter minimum 15 and maximum 16 digit only"
                                                                        maxlength="16">
                                                                    <label for="IMEI1">1 <span
                                                                            class="star">*</span></label>
                                                                </div>

                                                                <div class="input-field col s12 m6">
                                                                    <input type="text" id="IMEI2" name="IMEI2"
                                                                        pattern="[0-9]"
                                                                        title="Please enter minimum 15 and maximum 16 digit only"
                                                                        maxlength="16">
                                                                    <label for="IMEI2">2</label>
                                                                </div>

                                                                <div class="input-field col s12 m6">
                                                                    <input type="text" id="IMEI3" name="IMEI3"
                                                                        pattern="[0-9]"
                                                                        title="Please enter minimum 15 and maximum 16 digit only"
                                                                        maxlength="16">
                                                                    <label for="IMEI3">3</label>
                                                                </div>

                                                                <div class="input-field col s12 m6" id="field">
                                                                    <input type="text" id="IMEI4" name="IMEI4[]"
                                                                        pattern="[0-9]"
                                                                        title="Please enter minimum 15 and maximum 16 digit only"
                                                                        maxlength="16" id="field0">
                                                                    <label for="IMEI4">4</label>
                                                                </div>
                                                            </div>
                                                        </div>


                                                        <div class="col s12 m12">
                                                            <button class="btn right add_field_button"
                                                                style="margin-top: 5px;"><span
                                                                    style="font-size: 20px;">+</span>
                                                                Add More
                                                                Device</button>
                                                            <p>Required Field are marked with <span
                                                                    class="star">*</span>
                                                            </p>
                                                        </div>

                                                        <div class="col s12 m12 center" style="margin-top: 30px;">
                                                            <button class="btn modal-trigger"
                                                                data-target="otpVerification">Submit</button>
                                                            <button class="btn modal-trigger" data-target="cancelMsg"
                                                                style="margin-left: 10px;">Cancel</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>

                                        <div id="profile-page" class="section">
                                            <div class="container" id="user456" style="display: none;">
                                                <div class="col s12 m12 l12" id="consignmentTableDIv"
                                                    style="padding-bottom: 5px;background-color: #e2edef52;">

                                                    <div class="col s6 m2 l2 responsiveDiv">
                                                        <div id="startdatepicker" class="input-group date"
                                                            data-date-format="yyyy-mm-dd">
                                                            <label for="TotalPrice">Start date</label>
                                                            <input type="date" id="startDate" name="startDate"
                                                                class="pickerDate" value="20/10/2018">
                                                            <!-- <input class="form-control" type="date" id="datepicker" />  -->
                                                            <span class="input-group-addon" style="color:#ff4081"><i
                                                                    class="fa fa-calendar" aria-hidden="true"
                                                                    style="float: right; margin-top: -37px;"></i></span>
                                                        </div>
                                                    </div>

                                                    <div class="col s6 m2 l2 responsiveDiv">
                                                        <div id="enddatepicker" class="input-group date"
                                                            data-date-format="yyyy-mm-dd">
                                                            <label for="TotalPrice">End date</label>
                                                            <input type="date" id="datepicker-3" />
                                                            <span class="input-group-addon" style="color:#ff4081"><i
                                                                    class="fa fa-calendar" aria-hidden="true"
                                                                    style="float: right; margin-top: -37px;"></i></span>
                                                        </div>
                                                    </div>

                                                    <div class="col s6 m2 l2 selectDropdwn">
                                                        <br />
                                                        <!-- <label for="TotalPrice" class="center-align">File Status</label> -->
                                                        <select id="filterFileStatus" class="browser-default">
                                                            <option value="" disabled selected>Device ID Type</option>
                                                            <option value="IMEI">IMEI </option>
                                                            <option value="MEID">MEID</option>
                                                            <option value="ESN">ESN</option>
                                                        </select>

                                                    </div>


                                                    <div class="col s6 m2 l2 selectDropdwn">
                                                        <br />
                                                        <select id="deviceType" class="browser-default">
                                                            <option value="" disabled selected>Device Type
                                                            </option>
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

                                                    <div class="col s6 m2 l2 selectDropdwn">
                                                        <br />
                                                        <select id="taxPaidStatus" class="browser-default">
                                                            <option value="" disabled selected>Tax Paid Status</option>
                                                            <option value="Paid">Paid</option>
                                                            <option value="regularized">Regularized</option>
                                                            <option value="NotPaid">Not Paid</option>
                                                        </select>
                                                    </div>

                                                    <div class="col s12 m2">
                                                        <button type="button" class="btn primary botton"
                                                            id="submitFilter">Filter</button>
                                                        <div class="right"><a href="JavaScript:void(0);" type="button"
                                                                class="export-to-excel right">Export <i
                                                                    class="fa fa-file-excel-o"
                                                                    aria-hidden="true"></i></a>
                                                        </div>
                                                    </div>


                                                </div>
                                                <div class="row">
                                                    <div class="col s12 m12">
                                                        <table class="responsive-table striped display"
                                                            id="data-table-simple" cellspacing="0">
                                                            <thead>
                                                                <tr>
                                                                    <th>S No.</th>
                                                                    <th>Date</th>
                                                                    <th>Device ID Type</th>
                                                                    <th>Device Type</th>
                                                                    <th>Price</th>
                                                                    <th>Country</th>
                                                                    <th>Status</th>
                                                                    <th>Action</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody id="consignmentTableLibraryTbody">

                                                                <tr>
                                                                    <td>1</td>
                                                                    <td>25/05/2019</td>
                                                                    <td>IMEI</td>
                                                                    <td>Handheld</td>
                                                                    <td>125$</td>
                                                                    <td>ABC Country</td>
                                                                    <td>Tax Paid</td>
                                                                    <td style="width: 120px;">
                                                                        <a href="#payTaxModal" class="modal-trigger"><i
                                                                                class="fa fa-money pay-tax-icon"
                                                                                aria-hidden="true"
                                                                                title="Pay Tax"></i></a>
                                                                        <a href="#viewDeviceInformation"
                                                                            class="modal-trigger"><i
                                                                                class="fa fa-eye view-icon"
                                                                                aria-hidden="true" title="View"></i></a>
                                                                        <a href="#deleteMsg" class="modal-trigger"><i
                                                                                class="fa fa-trash delete-icon"
                                                                                aria-hidden="true"
                                                                                title="Delete"></i></a>
                                                                    </td>
                                                                </tr>

                                                                <tr>
                                                                    <td>2</td>
                                                                    <td>25/05/2019</td>
                                                                    <td>MEID</td>
                                                                    <td>Mobile Phone/Featured Phone</td>
                                                                    <td>125$</td>
                                                                    <td>ABC Country</td>
                                                                    <td>Regularized</td>
                                                                    <td>
                                                                        <a href="#payTaxModal" class="modal-trigger"><i
                                                                                class="fa fa-money pay-tax-icon"
                                                                                aria-hidden="true"
                                                                                title="Pay Tax"></i></a>
                                                                        <a href="#viewDeviceInformation"
                                                                            class="modal-trigger"><i
                                                                                class="fa fa-eye view-icon"
                                                                                aria-hidden="true" title="View"></i></a>
                                                                        <a href="#deleteMsg" class="modal-trigger"><i
                                                                                class="fa fa-trash delete-icon"
                                                                                aria-hidden="true"
                                                                                title="Delete"></i></a>
                                                                    </td>
                                                                </tr>

                                                                <tr>
                                                                    <td>3</td>
                                                                    <td>25/05/2019</td>
                                                                    <td>ESN</td>
                                                                    <td>Vehicle</td>
                                                                    <td>125$</td>
                                                                    <td>ABC Country</td>
                                                                    <td>Non Tax Paid</td>
                                                                    <td>
                                                                        <a href="#payTaxModal" class="modal-trigger"><i
                                                                                class="fa fa-money pay-tax-icon"
                                                                                aria-hidden="true"
                                                                                title="Pay Tax"></i></a>
                                                                        <a href="#viewDeviceInformation"
                                                                            class="modal-trigger"><i
                                                                                class="fa fa-eye view-icon"
                                                                                aria-hidden="true" title="View"></i></a>
                                                                        <a href="#deleteMsg" class="modal-trigger"><i
                                                                                class="fa fa-trash delete-icon"
                                                                                aria-hidden="true"
                                                                                title="Delete"></i></a>
                                                                    </td>
                                                                </tr>

                                                                <tr>
                                                                    <td>4</td>
                                                                    <td>25/05/2019</td>
                                                                    <td>EMEI</td>
                                                                    <td>Portable</td>
                                                                    <td>125$</td>
                                                                    <td>ABC Country</td>
                                                                    <td>Tax Paid</td>
                                                                    <td>
                                                                        <a href="#payTaxModal" class="modal-trigger"><i
                                                                                class="fa fa-money pay-tax-icon"
                                                                                aria-hidden="true"
                                                                                title="Pay Tax"></i></a>
                                                                        <a href="#viewDeviceInformation"
                                                                            class="modal-trigger"><i
                                                                                class="fa fa-eye view-icon"
                                                                                aria-hidden="true" title="View"></i></a>
                                                                        <a href="#deleteMsg" class="modal-trigger"><i
                                                                                class="fa fa-trash delete-icon"
                                                                                aria-hidden="true"
                                                                                title="Delete"></i></a>
                                                                    </td>
                                                                </tr>

                                                                <tr>
                                                                    <td>5</td>
                                                                    <td>25/05/2019</td>
                                                                    <td>MEID</td>
                                                                    <td>Module</td>
                                                                    <td>125$</td>
                                                                    <td>ABC Country</td>
                                                                    <td>Regularized</td>
                                                                    <td>
                                                                        <a href="#payTaxModal" class="modal-trigger"><i
                                                                                class="fa fa-money pay-tax-icon"
                                                                                aria-hidden="true"
                                                                                title="Pay Tax"></i></a>
                                                                        <a href="#viewDeviceInformation"
                                                                            class="modal-trigger"><i
                                                                                class="fa fa-eye view-icon"
                                                                                aria-hidden="true" title="View"></i></a>
                                                                        <a href="#deleteMsg" class="modal-trigger"><i
                                                                                class="fa fa-trash delete-icon"
                                                                                aria-hidden="true"
                                                                                title="Delete"></i></a>
                                                                    </td>
                                                                </tr>

                                                            </tbody>
                                                        </table>

                                                        <a href="#viewBlockDevices" class="modal-trigger">View block
                                                            devices</a>
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




</body>
</html>