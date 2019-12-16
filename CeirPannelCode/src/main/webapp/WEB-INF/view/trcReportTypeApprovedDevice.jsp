<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<title>Grievance</title>

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
	href="${context}/resources/css/grievance.css">



</head>

<body data-roleType="${usertype}" data-userTypeID="${usertypeId}" data-userID="${userid}" data-selected-roleType="${selectedUserTypeId}" data-stolenselected-roleType="${stolenselectedUserTypeId}">

 <!-- START CONTENT -->
            <section id="content">
                <!--start container-->
                <div class="container">
                    <div class="section">
                        <div class="row">
                            <div class="col s12 m12 l12">
                                <div class="row card-panel">
                                    <div class="container-fluid pageHeader">
                                        <p class="PageHeading">Report Type-Approved Devices</p>
                                    </div>

                                    <form id="">
                                        <div class="row" style="margin-top: 10px;">
                                            <div class="input-field col s12 m6 l6">
                                                <input type="text" id="manufacturerId" name="manufacturerId" />
                                                <label for="manufacturerId">Manufacturer ID</label>
                                            </div>

                                            <div class="input-field col s12 m6 l6">
                                                <input type="text" id="manufacturerName" name="manufacturerName" />
                                                <label for="manufacturerName">Manufacturer Name <span
                                                        class="star">*</span></label>
                                            </div>

                                            <div class="col s12 m6 l6">
                                                <label for="country">Country <span class="star">*</span></label>
                                                <select id="country" class="browser-default" class="mySelect"
                                                    required></select>
                                            </div>

                                            <div class="input-field col s12 m6">
                                                <input type="text" id="bdate2" class="datepicker" name="requestDate"
                                                    pattern="[]" title="" maxlength="20" format="dd/mm/yyyy"/>
                                                    <span class="input-group-addon" style="color:#ff4081"><i
                                                        class="fa fa-calendar" aria-hidden="true"
                                                        style="float: right; margin-top: -37px;"></i></span>
                                                <label for="bdate2">Request Date <span class="star">*</span></label>
                                            </div>

                                            <!-- <div class="col s12 m6 l6">
                                                <label for="deviceType">Device Type <span class="star">*</span></label>
                                                <select class="browser-default" id="deviceType">
                                                    <option value="" disabled selected>Select Device Type</option>
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
                                            </div> -->
                                        </div>

                                        <div class="row" style="margin-top: 5px;">
                                            <!-- <div class="col s12 m6 l6">
                                                <label for="deviceType">Device ID Type </label>
                                                <select class="browser-default" id="deviceType">
                                                    <option value="" disabled selected>Select Device ID Type</option>
                                                    <option value="IMEI">IMEI</option>
                                                    <option value="ESN">ESN</option>
                                                    <option value="MEID">MEID</option>
                                                </select>
                                            </div> -->

                                            

                                            <div class="input-field col s12 m6 l6">
                                                <input type="text" id="tac" name="tac" />
                                                <label for="tac">TAC <span class="star">*</span></label>
                                            </div>

                                            <div class="col s12 m6 l6">
                                                <label for="deviceType">Status <span class="star">*</span></label>
                                                <select class="browser-default" id="deviceType">
                                                    <option value="" disabled selected>Select Status</option>
                                                    <option value="Approved">Approved</option>
                                                    <option value="Rejected">Rejected</option>
                                                </select>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="input-field col s12 m6">
                                                <input type="text" id="bdate2" class="datepicker" name="approveRejectionDate"
                                                    pattern="[]" title="" maxlength="20" />
                                                    <span class="input-group-addon" style="color:#ff4081"><i
                                                        class="fa fa-calendar" aria-hidden="true"
                                                        style="float: right; margin-top: -37px;"></i></span>
                                                <label for="bdate2">Approve/Rejection Date <span
                                                        class="star">*</span></label>
                                            </div>

                                            <div class="input-field col s12 m6 l6" style="margin-top: 9px;">
                                                <textarea id="Remark" class="materialize-textarea"></textarea>
                                                <label for="Remark">Remark </label>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <h6 style="color: #000; margin-left: 10px;">Upload Supporting Document <span
                                                    class="star">*</span></h6>
                                            <div class="file-field col s12 m6">
                                                <div class="btn">
                                                    <span>Select File</span>
                                                    <input id="myInput" type="file" multiple>
                                                </div>
                                                <div class="file-path-wrapper">
                                                    <input class="file-path validate" type="text" multiple>
                                                    <div>
                                                        <p id="myFiles"></p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <span style="margin-left: 5px;"> Required Field are marked with <span
                                                class="star">*</span>
                                            <div class="center" style="margin-top: 50px;">
                                                <button class="btn modal-trigger" data-target="submitManageTypeDevice" type="submit">Submit</button>
                                                <a href="manageTypeDevices.html" class="btn" id="Cancel"
                                                    style="margin-left: 10px;">Cancel</a>
                                            </div>
                                    </form>
                                </div>
                            </div>

                        </div>

                    </div>
                </div>
        </div>
    </div>
    <!--end container-->
    </section>
<!--materialize js-->
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
	<script type="text/javascript"
		src="${context}/resources/js/materialize.js"></script>


	<script type="text/javascript"
		src="${context}/resources/js/plugins/data-tables/js/jquery.dataTables.js"></script>
	<script type="text/javascript"
		src="${context}/resources/js/plugins/data-tables/js/jquery.dataTables.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.3/moment.min.js"></script>
	<script type="text/javascript"
		src="${context}/resources/js/jquery-datepicker2.js"></script>


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
		src="${context}/resources/project_js/AdminRegistrationRequest.js"></script>
	
</body>
</html>