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
<style type="text/css">
 .checkboxFont {
           color: #444;
            font-size: 16px;
            margin-right: 10px;
        }
        .section {
            padding-top: 0.5rem;
        }

        .welcomeMsg {
      padding-bottom: 50px !important;
      line-height: 1.5 !important;
      text-align: center;
    }
    </style>
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
	href="${context}/resources/project_css/viewStock.css">
<%-- <link rel="stylesheet"
	href="${context}/resources/project_css/iconStates.css"> --%>

</head>
<body data-roleType="${usertype}" data-userID="${userid}"
	data-selected-roleType="${selectedUserTypeId}">



<section id="content">
                <!--start container-->
                <div class="container">
                    <div class="section">
                        <div class="row">
                            <div class="col s12 m12 l12">
                                <div class="row card-panel">
                                    <div id="reportBlockUnblock">
                                        <div class="container-fluid pageHeader">
                                            <p class="PageHeading">Report Unblock</p>
                                        </div>

                                        <div class="row">
                                            <div class="col s12">
                                                <ul class="tabs">
                                                    <li class="tab col s3"><a class="active" href="#Single">Single</a>
                                                    </li>
                                                    <li class="tab col s3"><a href="#Bulk">Bulk</a></li>
                                                </ul>
                                            </div>
                                            <div id="Single" class="col s12" style="margin-top: 30px;">
                                                <form action="#">
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
                                                                <label for="deviceType">Multiple Sim Status <span class="star">*</span></label>
                                                                <select class="browser-default" id="multipleSimStatus">
                                                                    <option value="" disabled selected>Multiple Sim Status</option>
                                                                    <option value="Yes">Yes</option>
                                                                    <option value="No">No</option>
                                                                </select>
                                                            </div>
                                        
                                                            <div class="input-field col s12 m6" style="margin-top: 21px;">
                                                                <input type="text" id="serialNumber" name="serialNumber" pattern="[0-9]"
                                                                    title="Please enter your device serial number first" maxlength="20">
                                                                <label for="serialNumber">Device Serial Number <span class="star">*</span></label>
                                                            </div>
                                        
                                                            <div class="input-field col s12 m6">
                                                                <textarea id="remark" class="materialize-textarea"></textarea>
                                                                <label for="Remark">Remark <span class="star">*</span></label>
                                                            </div>
                                                        </div>
                                                        <div class="row input_fields_wrap">
                                                            <div class="col s12 m12">
                                                                <p style="margin-bottom: 0;">IMEI/MEID/ESN</p>
                                                            </div>
                                                            <div class="input-field col s12 m6">
                                                                <input type="text" id="IMEI1" name="IMEI1" pattern="[0-9]"
                                                                    title="Please enter minimum 15 and maximum 16 digit only" maxlength="16">
                                                                <label for="IMEI1">1 <span class="star">*</span></label>
                                                            </div>
                                        
                                                            <div class="input-field col s12 m6">
                                                                <input type="text" id="IMEI2" name="IMEI2" pattern="[0-9]"
                                                                    title="Please enter minimum 15 and maximum 16 digit only" maxlength="16">
                                                                <label for="IMEI2">2</label>
                                                            </div>  
                                                            
                                                            <div class="input-field col s12 m6">
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
                                                        </div>
                                                        <span> Required Field are marked with <span class="star">*</span></span>
                                                    </div>

                                                    

                                                    <div class="input-field col s12 center">
                                                        <button class="btn modal-trigger" data-target="markAsUnblock">Submit</button>
                                                        <a href="blockUnblockList.html" class="btn" style="margin-left: 10px;">Cancel</a>
                                                    </div>
                                                </form>
                                            </div>
                                            <div id="Bulk" class="col s12">
                                                <form action="#">
                                                    <div class="file-field input-field col s12 m6" style="margin-top: 21px;">
                                                        <p style="color: #000;">Upload Bulk Devices <span class="star">*</span></p>
                                                        <div class="btn">
                                                            <span>File</span>
                                                            <input type="file">
                                                        </div>
                                                        <div class="file-path-wrapper">
                                                            <input class="file-path validate" type="text" placeholder="Please select the file">
                                                        </div>
                                                    </div>

                                                    <div class="input-field col s12 m6" style="margin-top: 62px;">
                                                        <textarea id="Remark1" class="materialize-textarea"></textarea>
                                                        <label for="Remark1">Remark <span class="star">*</span></label>
                                                    </div>
                                                    
                                                    <p style="margin-left: 10px;"><a href="#">Download Sample Format</a></p>
                                                    <span style="margin-left: 5px;"> Required Field are marked with <span class="star">*</span></span>

                                                    <div class="input-field col s12 center">
                                                        <button class="btn modal-trigger" data-target="markBulkAsUnblock">Submit</button>
                                                        <a href="blockUnblockList.html" class="btn" style="margin-left: 10px;">Cancel</a>
                                                    </div>
                                                </form>
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


    <div id="markAsUnblock" class="modal">
        <div class="modal-content">
            <h6>Mark As Unblock</h6>
            <hr>

            <div class="row">
                <h6>This device has been marked as unblock.</h6>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <a href="blockUnblockList.html" class="btn">ok</a>
                </div>
            </div>
        </div>
    </div>
    
    
    <div id="markBulkAsUnblock" class="modal">
        <div class="modal-content">
            <h6>Mark As Unblock</h6>
            <hr>

            <div class="row">
                <h6>This file has been marked as unblock.</h6>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <a href="blockUnblockList.html" class="btn">ok</a>
                </div>
            </div>
        </div>
    </div>

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

	<!--plugins.js - Some Specific JS codes for Plugin Settings-->
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

	<%--   <script type="text/javascript" src="${context}/resources/js/materialize-plugins/date_picker/picker.date.js"></script>
    <script type="text/javascript" src="${context}/resources/js/materialize-plugins/date_picker/picker.js"></script> --%>
	<!--custom-script.js - Add your own theme custom JS-->
	<script type="text/javascript" src="${context}/resources/js/plugins.js"></script>
	<!--prism
    <script type="text/javascript" src="${context}/resources/resources/js/prism/prism.js"></script>-->
	<!--scrollbar-->
	<script type="text/javascript"
		src="${context}/resources/js/plugins/perfect-scrollbar/perfect-scrollbar.min.js"></script>
	<!-- chartist -->
	<%-- <script type="text/javascript" src="${context}/resources/js/plugins/chartist-js/chartist.min.js"></script> --%>
	<script type="text/javascript"
		src="${context}/resources/js/countries.js"></script>

</body>
</html>