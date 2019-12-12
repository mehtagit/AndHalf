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
<link rel="stylesheet"
	href="${context}/resources/project_css/iconStates.css">

</head>
<body data-roleType="${usertype}" data-userID="${userid}"
	data-selected-roleType="${selectedUserTypeId}" data-stolenselected-roleType="${stolenselectedUserTypeId}">




 <section id="content">
                <!--start container-->
                <div class="container">
                    <div class="section">
                        <div class="row">
                            <div class="col s12 m12 l12">
                                <div class="row card-panel">
                                    <div class="container-fluid pageHeader">
                                        <p class="PageHeading">Report Grievance</p>
                                    </div>

                                    <form  onsubmit="return saveGrievance()" method="POST" enctype="multipart/form-data"  id="saveGrievance">
                                        <div class="row" style="margin-top: 10px;">
                                            <div class="input-field col s12 m6 l6">
                                                <input type="text" id="TransactionId" pattern="[A-Za-z0-9]{0,18}" maxlength="18" title="Please enter alphabets and numbers upto 18 characters only"
                                                    class="form-control boxBorder boxHeight"/>
                                                    <label for="TransactionId">Transaction ID</label>
                                            </div>

                                            <div class="input-field col s12 m6 l6">
                                                <!-- <label for="Category">Category</label> -->
                                                <select class="browser-default" id="category" required="required">
                                                <!--<option value="" disabled selected>Category *</option>
                                                    <option value="1">Report Related</option>
                                                    <option value="2">Device Stolen Related</option>
                                                    <option value="3">Device Recovery Related</option>
                                                    <option value="4">Stock Related</option>
                                                    <option value="5">Other</option> -->
                                                </select>
                                            </div>
                                        </div>

                                        <div class="row" style="margin-top: 10px;">
                                            <div class="input-field col s12 m6 l6">
                                                <textarea id="Remark" class="materialize-textarea" required="required"></textarea>
                                                <label for="Remark">Remark <span class="star">*</span></label>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <h6 style="color: #000; margin-left: 10px;">Upload Supporting Document*</h6>
                                            <div class="file-field col s12 m6">
                                                <div class="btn">
                                                    <span>Select File</span>
                                                    <input id="myInput" type="file" accept=".csv" required="required" multiple>
                                                </div>
                                                <div class="file-path-wrapper">
                                                    <input class="file-path validate" type="text" multiple
                                                        placeholder="Upload one or more files">
                                                    <div>
                                                        <p id="myFiles"></p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col s12 m12">  <p>
				<a href="./Consignment/sampleFileDownload/filetype=sample">Download Sample Format</a>
			</p>

			<span> Required Field are marked with <span class="star">*</span></span>
			
                </div>
                                        <div class="center" style="margin-top: 50px;">
                                            <button class="btn"
                                                 type="submit" >Submit</button>
                                            <a href="./grievanceManagement" class="btn" id="Cancel"
                                                style="margin-left: 10px;">Cancel</a>
                                        </div>
                                    </form>
                                </div>
                            </div>

                        </div>

                    </div>
                </div>
       
    <!--end container-->
    </section>




<div id="submitGrievance" class="modal">
        <div class="modal-content">
            <h6>Submit Grievance Report</h6><hr>

            <div class="row">
                <h6 id="grievanceSuccessId">Your grievance report has been successfully submitted. Your Grievance Id is ( <span id="greivanceId"></span> )</h6>

                <p>(Note: Please remember your grievance Id. This is used for future reference)</p>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <a href="./grievanceManagement"
                        class="btn"
                        >ok</a>
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
	<script type="text/javascript"
		src="${context}/resources/project_js/viewStock.js"></script>
		<script type="text/javascript"
		src="${context}/resources/project_js/grievanceManagement.js"></script>
</body>
</html>


