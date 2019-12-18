<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<title>TRC</title>

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

<style type="text/css">
textarea {
	padding: 0px;
}

</style>
</head>
<%-- <body data-roleType="${usertype}" data-userID="${userid}" data-selected-roleType="${selectedUserTypeId}"> --%>
<body data-roleType="${usertype}" data-userTypeID="${usertypeId}" data-userID="${userid}" data-selected-roleType="${selectedUserTypeId}" data-stolenselected-roleType="${stolenselectedUserTypeId}">


	<!-- START CONTENT -->
	<!-- START CONTENT -->
	<section id="content">
	<div id="initialloader"></div>
		<!--start container-->
		<div class="container">
			<div class="section">
				<div class="row">
					<div class="col s12 m12 l12">
						<div class="row card-panel">
							<div class="container-fluid pageHeader" id="pageHeader">

								<a href="" class="boton right" id="btnLink"></a>
							</div>
							<form action="${context}/manageTypeDevices" method="post">
								<div class="col s12 m12 l12" id="typeAprroveTableDiv"
									style="padding-bottom: 5px; background-color: #e2edef52;">
									<div id="filterBtnDiv">
										<!-- 							<div class='col s12 m2 l2'><button type='submit' class='btn primary botton' id='submitFilter'></button></div>
		 -->
									</div>
								</div>
							</form>
							<table id="typeAprroveTable"
								class="responsive-table striped display"></table>

						</div>

					</div>
				</div>
				<div id="footerBtn"></div>
			</div>
		</div>


		<div id="viewModal" class="modal-form" style="overflow-y: hidden;">
			<div class="modal-content">

				<h6>View Type-Approve Devices</h6>
				<hr>

				<div class="row" style="margin-top: 10px;">
					<div class="input-field col s12 m6 l6">
						<input type="text" id="viewmanufacturerId" name="manufacturerId"
							placeholder="GHJ6487328" disabled=""> <label
							for="manufacturerId" class="active">Manufacturer ID</label>
					</div>

					<div class="input-field col s12 m6 l6">
						<input type="text" id="viewmanufacturerName" name="manufacturerName"
							placeholder="ABC Name" disabled=""> <label
							for="manufacturerName" class="active">Manufacturer Name <span
							class="star">*</span></label>
					</div>

					<div class="input-field col s12 m6 l6">
						<input type="text" id="viewcountry" name="Country"
							placeholder="ABC Country" disabled=""> <label
							for="Country" class="active">Country </label>
					</div>

					<div class="input-field col s12 m6 l6">
						<input type="text" id="viewDeviceType" name="deviceType"
							placeholder="Handheld" disabled=""> <label
							for="deviceType" class="active">Device Type </label>
					</div>


				</div>

				<div class="row" style="margin-top: 5px;">
					<div class="input-field col s12 m6 l6">
						<input type="text" id="viewdeviceTypeID" name="deviceType"
							placeholder="IMEI" disabled=""> <label for="deviceType"
							class="active">Device ID Type</label>
					</div>


					<div class="input-field col s12 m6">
						<input type="text" id="viewrequestDate"
							class="datepicker picker__input" name="requestDate" pattern="[]"
							title="" maxlength="20" placeholder="" disabled="" readonly=""
							tabindex="-1" aria-haspopup="true" aria-expanded="false"
							aria-readonly="false" aria-owns="bdate2_root"> <span
							class="input-group-addon" style="color: #ff4081"><i
							class="fa fa-calendar" aria-hidden="true"
							style="float: right; margin-top: -37px;"></i></span> <label
							for="dateRequested" class="active">Request Date </label>
					</div>

					<div class="input-field col s12 m6 l6">
						<input type="text" id="viewtac" name="tac" placeholder="67458392"
							disabled=""> <label for="tac" class="active">TAC
						</label>
					</div>

					<div class="input-field col s12 m6 l6">
						<input type="text" id="status" name="Status"
							placeholder="Approved" disabled=""> <label for="Status"
							class="active">Status </label>
					</div>
					
			
									
									

				</div>

				<div class="row">
					<div class="input-field col s12 m6">
						<input type="text" id="viewapproveDisapproveDate" class="datepicker picker__input"
							name="approveRejectionDate" pattern="[]" title="" maxlength="20"
							placeholder="5/12/2019" disabled="" readonly="" tabindex="-1"
							aria-haspopup="true" aria-expanded="false" aria-readonly="false"
							aria-owns="bdate2_root"> <span class="input-group-addon"
							style="color: #ff4081"><i class="fa fa-calendar"
							aria-hidden="true" style="float: right; margin-top: -37px;"></i></span>
						<label for="bdate2" class="active">Approve/Rejection Date
							<span class="star">*</span>
						</label>
					</div>

					<div class="input-field col s12 m6 l6" style="margin-top: 9px;">
						<textarea id="viewremark" class="materialize-textarea" placeholder=""
							disabled=""></textarea>
						<label for="Remark" class="active">Remark </label>
					</div>
				</div>
				<div class="center" style="margin-top: 30px;">
					<a href="./manageTypeDevices" class="btn" id="Cancel"
						style="margin-left: 10px;">Cancel</a>
				</div>



			</div>
		</div>


		<div id="editModal" class="modal-form" style="overflow-y: hidden;">
		
                                    <div class="container-fluid pageHeader">
                                        <p class="PageHeading"> Update Report Type-Approve Devices</p>
                                    </div>

                                   
                                        <div class="row" style="margin-top: 10px;">
                                            <div class="input-field col s12 m6 l6">
                                                <input type="text" id="editmanufacturerId" name="manufacturerId"
                                                    placeholder="GHJ6487328" />
                                                <label for="manufacturerId">Manufacturer ID</label>
                                                <input type="text" id="transactionid" style="display: none">
                                                <input type="text" id="columnid" style="display: none">
                                            </div>

                                            <div class="input-field col s12 m6 l6">
                                                <input type="text" id="editmanufacturerName" name="manufacturerName"
                                                    placeholder="ABC Name" />
                                                <label for="manufacturerName">Manufacturer Name <span
                                                        class="star">*</span></label>
                                            </div>

                                            <div class="col s12 m6 l6">
                                                <label for="country">Country <span class="star">*</span></label>
                                                <select id="editcountry" class="browser-default" class="mySelect"
                                                    required></select>
                                            </div>

                                            <div class="input-field col s12 m6">
                                                <input type="text" id="editRequestDate" class="datepicker" name="requestDate"
                                                    pattern="[]" title="" maxlength="20" placeholder="12/10/2019" />
                                                <label for="bdate2">Request Date <span class="star">*</span></label>
                                            </div>

                                          
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
                                                <input type="text" id="edittac" name="tac" placeholder="FGJB263572" />
                                                <label for="tac">TAC <span class="star">*</span></label>
                                            </div>

                                            <div class="col s12 m6 l6">
                                                <label for="deviceType">Status <span class="star">*</span></label>
                                                <select class="browser-default" id="editdeviceType">
                                                    <option value="" disabled selected>Select Status</option>
                                                    <option value="0">Approved</option>
                                                    <option value="1">Rejected</option>
                                                </select>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="input-field col s12 m6">
                                                <input type="text" id="editApproveRejectionDate" class="datepicker"
                                                    name="approveRejectionDate" pattern="[]" title="" maxlength="20"
                                                    placeholder="5/12/2019" />
                                                <label for="bdate2">Approve Rejection Date <span
                                                        class="star">*</span></label>
                                            </div>

                                            <div class="input-field col s12 m6 l6" style="margin-top: 9px;">
                                                <textarea id="editRemark" class="materialize-textarea"
                                                    placeholder="kjshdksahdkjsahdksajhdksad"></textarea>
                                                <label for="Remark" style="margin-top: 7px">Remark </label>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <h6 style="color: #000; margin-left: 10px;">Upload Supporting Document <span
                                                    class="star">*</span></h6>
                                            <div class="file-field col s12 m6">
                                                <div class="btn">
                                                    <span>Select File</span>
                                                    <input id="editUploadFile" type="file" multiple>
                                                </div>
                                                <div class="file-path-wrapper">
                                                    <input class="file-path validate" type="text" id="editFileName" placeholder="file.csv"
                                                        multiple>
                                                    <div>
                                                        <p id="myFiles"></p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <span style="margin-left: 5px;"> Required Field are marked with <span
                                                class="star">*</span></span>
                                            <div class="center" style="margin-top: 50px;">
                                                <button class="btn " onclick="updateReportTypeDevice()"
                                                    type="submit">Update</button>
                                                <a href="manageTypeDevices.html" class="btn" id="Cancel"
                                                    style="margin-left: 10px;">Cancel</a>
                                            </div>
                                    
                                </div>	
                                
     <div id="updateManageTypeDevice" class="modal">
        <div class="modal-content">
            <h6 class="modal-header">Update</h6>
            <div class="row">
                <h6 id="updateTacMessage"> Your request has been successfully updated.</h6>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <a href="./manageTypeDevices" class="btn">ok</a>
                </div>
            </div>
        </div>
    </div>
	
		<!--end container-->
	</section>

	<!--materialize js-->
	<script type="text/javascript"
		src="${context}/resources/js/materialize.js"></script>
	<script type="text/javascript"
		src="${context}/resources/js/plugins/data-tables/js/jquery.dataTables.js"></script>
	<script type="text/javascript"
		src="${context}/resources/js/plugins/data-tables/js/jquery.dataTables.min.js"></script>



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
		src="${context}/resources/project_js/ViewManageTypeApproved.js"></script>

</body>
</html>