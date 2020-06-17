
<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setDateHeader("Expires", 0);
	response.setHeader("Pragma", "no-cache");
	/*  session.setMaxInactiveInterval(200); //200 secs
	 session.setAttribute("usertype", null); */
	if (session.getAttribute("usertype") != null) {
%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html class="no-js" lang="en" dir="ltr">
<head>
<title>Manage Type Approval</title>
<meta http-equiv='cache-control' content='no-cache'>
<meta http-equiv='expires' content='-1'>
<meta http-equiv='pragma' content='no-cache'>
<meta name="fragment" content="!">
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
<!-- Favicons-->
<link rel="icon" href="${context}/resources/images/DMC-Logo.png" sizes="32x32">
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
	href="${context}/resources/project_css/viewConsignment.css">
<link rel="stylesheet"
	href="${context}/resources/project_css/iconStates.css">


<link rel="stylesheet"
	href="${context}/resources/custom_js/jquery-ui.css?version=<%= (int) (Math.random() * 10) %>">
<script src="${context}/resources/custom_js/1.11.2_jquery-ui.js?version=<%= (int) (Math.random() * 10) %>"></script>

<script src="${context}/resources/custom_js/jquery.blockUI.js?version=<%= (int) (Math.random() * 10) %>"></script>

<!------------------------------------------- Dragable Model---------------------------------->
<script src="${context}/resources/custom_js/1.12.1_jquery-ui.js?version=<%= (int) (Math.random() * 10) %>"></script>
<script
	src="${context}/resources/custom_js/1.12.1_jquery-ui.min.js?version=<%= (int) (Math.random() * 10) %>"></script>
	 <script type="text/javascript">
var path="${context}";
</script>

<style type="text/css">
.dataTables_scrollBody {
    height: 100px !important;
}
</style>
</head>
<body data-id="11" data-roleType="${usertype}" data-userTypeID="${usertypeId}"
	data-userID="${userid}" data-selected-roleType="${selectedUserTypeId}"
	data-stolenselected-roleType="${stolenselectedUserTypeId}"
	data-selected-consignmentTxnId="${consignmentTxnId}"
	data-selected-consignmentStatus="${consignmentStatus}"
	session-value="en"
	session-valueTxnID="${not empty param.txnID ? param.txnID : 'null'}">


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


		<div id="viewModal" class="modal" style="overflow-y: hidden;">
			<h6 class="modal-header"><spring:message code="input.ViewType" /></h6>
			<div class="modal-content">

				


				<div class="row" style="margin-top: 10px;">
					<div class="input-field col s12 m6 l6">
						<input type="text" id="viewmanufacturerId" name="manufacturerId"
							placeholder="" disabled=""> <label
							for="manufacturerId" class="active"><spring:message code="input.ManufacturerID" /></label>
					</div>

					<div class="input-field col s12 m6 l6">
						<input type="text" id="viewmanufacturerName" name="manufacturerName"
							placeholder="" disabled="disabled"> <label
							for="manufacturerName" class="active"><spring:message code="input.ManufacturerName" /> <span
							class="star"></span></label>
					</div>

					<div class="input-field col s12 m6 l6">
						<input type="text" id="viewcountry" name="Country"
							placeholder="" disabled=""> <label
							for="Country" class="active"><spring:message code="input.Country" /></label>
					</div>
			<div class="input-field col s12 m6 l6">
			 			<input type="text" id="viewrequestDate"
							class="datepicker picker__input" name="requestDate" pattern="[]"
							title="" maxlength="20" placeholder="" disabled="" readonly=""
							tabindex="-1" aria-haspopup="true" aria-expanded="false"
							aria-readonly="false" aria-owns="bdate2_root"> <span
							class="input-group-addon" style="color: #ff4081"><i
							class="fa fa-calendar" aria-hidden="true"
							style="float: right; margin-top: -37px;"></i></span> <label
							for="dateRequested" class="active"><spring:message code="input.RequestDate" /> </label>
					</div>
					<!-- <div class="input-field col s12 m6 l6">
						<input type="text" id="viewDeviceType" name="deviceType"
							placeholder="" disabled=""> <label
							for="deviceType" class="active">Device Type </label>
					</div> -->


				</div>

				<!-- <div class="row" style="margin-top: 5px;">
					<div class="input-field col s12 m6 l6">
						<input type="text" id="viewdeviceTypeID" name="deviceType"
							placeholder="" disabled=""> <label for="deviceType"
							class="active">Device ID Type</label>
					</div> -->


					<div class="row">

					<div class="input-field col s12 m6 l6">
						<input type="text" id="viewtac" name="tac" placeholder=""
							disabled=""> <label for="tac" class="active"><spring:message code="input.TAC" /></label>
					</div>

					<div class="input-field col s12 m6 l6">
						<input type="text" id="status" name="Status"
							placeholder="" disabled=""> <label for="Status"
							class="active"><spring:message code="input.Status" /> </label>
					</div>
					
					<div class="input-field  col s12 m6">
						<input type="text" id="viewapproveDisapproveDate" class="datepicker picker__input"
							name="approveRejectionDate" pattern="[]" title="" maxlength="20"
							placeholder="" disabled="" readonly="" tabindex="-1"
							aria-haspopup="true" aria-expanded="false" aria-readonly="false"
							aria-owns="bdate2_root"> <span class="input-group-addon"
							style="color: #ff4081"><i class="fa fa-calendar"
							aria-hidden="true" style="float: right; margin-top: -37px;"></i></span>
						<label for="bdate2" class="active"><spring:message code="input.Approve/RejectionDate" /><span class="star"></span>
						</label>
					</div>

					<div class="input-field col s12 m6 l6" style="margin-top: 9px;">
						<textarea id="viewremark" class="materialize-textarea" placeholder=""
							disabled=""></textarea>
						<label for="Remark" class="active"><spring:message code="input.Remark" /></label>
					</div>
					</div>
					<div class="modal-content">
<div id="live-chat">
<div class="chat">
<div class="chat-history">
<div class="chat-message clearfix" id="chatMsg">

</div> <!-- end chat-message -->


</div>
</div>
</div>
</div>
									
									

				</div>
				<div class="row ">
				<div class="center  popup-btn-div" style="margin-top: 10px;">
					<button class="modal-close btn" type="button" id="Cancel"
						style="margin-left: 10px;"><spring:message code="modal.close" /></button>
				</div>



			</div>
		</div>


		<div id="editModal" class="modal" style="overflow-y: hidden;">
		<h6 class="modal-header" > <spring:message code="modal.UpdateDevices" /></h6>
		<div class="modal-content">
                                    
<form action="" onsubmit="return updateReportTypeDevice()" method="post" style="margin-top: 30px;">
                                   
                                        <div class="row" style="margin-top: 10px;">
                                            <div class="input-field col s12 m6 l6">
                                                <input type="text" id="editmanufacturerId" pattern="[A-Za-z0-9]{0,15}" title="Please enter alphabets and numbers upto 15 characters only"  maxlength="10" name="manufacturerId"
                                                    placeholder="" />
                                                <label for="manufacturerId"><spring:message code="input.ManufacturerID" /></label>
                                                <input type="text" id="transactionid" style="display: none">
                                                <input type="text" id="columnid" style="display: none">
                                            </div>

                                            <div class="input-field col s12 m6 l6">
                                                <input type="text" id="editmanufacturerName" name="manufacturerName" pattern="[A-Za-z0-9 \s]{0,160}" title="Please enter alphabets and numbers upto 15 characters only"  maxlength="160"
                                                    required="required" />
                                                <label for="editmanufacturerName"><spring:message code="input.ManufacturerName" /> <span
                                                        class="star">*</span></label>
                                            </div>

                                            <div class="col s12 m6 l6">
                                                <label for="country"><spring:message code="input.Country" /> <span class="star">*</span></label>
                                                <select id="editcountry"  required="required" class="browser-default" class="mySelect"
                                                    required></select>
                                            </div>

                                            <div class="input-field col s12 m6">
                                              <input type="text" id="editRequestDate" required="required"
											class="form-control dateClass" name="requestDate" 
											title="" placeholder="" autocomplete="off"> <label for="bdate2"><spring:message code="input.RequestDate" /> <span class="star">*</span></label>
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
                                                <input type="text" id="edittac" name="tac" placeholder="" pattern="[0-9]{8,8}" title="Please enter 7 digits tac number"  maxlength="8" required="required" />
                                                <label for="tac"><spring:message code="input.TAC" /> <span class="star">*</span></label>
                                            </div>

                                            <div class="col s12 m6 l6">
                                                <label for="deviceType"><spring:message code="input.Status" /> <span class="star">*</span></label>
                                                <select class="browser-default" required="required" id="editdeviceType">
                                                    <option value="" disabled selected><spring:message code="input.SelectStatus" /></option>
                                                    <option value="0"><spring:message code="input.Approved" /></option>
                                                    <option value="1"><spring:message code="input.Rejected" /></option>
                                                </select>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="input-field col s12 m6">
                                              
                                                    
                                                               <input type="text" id="editApproveRejectionDate" required="required"
											class="form-control dateClass" name="requestDate" 
											title="" autocomplete="off" placeholder=""> 
											
                                                <label for="bdate2"><spring:message code="input.ApproveRejectionDate" /><span
                                                        class="star">*</span></label>
                                            </div>

                                            <div class="input-field col s12 m6 l6" style="margin-top: 9px;">
                                                <textarea id="editRemark" class="materialize-textarea" style="padding: 0"
                                                    placeholder="" ></textarea>
                                                <label for="Remark" style="margin-top: 7px"><spring:message code="input.Remark" /> </label>
                                            </div>
                                        </div>

                        		 	<%-- <div class="row">
                                            <h6 style="color: #000; margin-left: 10px;"><spring:message code="input.supportingdocument" /> <span
                                                    class="star">*</span></h6>
                                            <div class="file-field col s12 m6">
                                                <div class="btn">
                                                    <span><spring:message code="input.selectfile" /></span>
                                                    <input id="editUploadFile" type="file"  multiple>
                                                </div>
                                                <div class="file-path-wrapper">
                                                    <input class="file-path validate" required="required" type="text" id="editFileName" placeholder=""
                                                        multiple>
                                                    <div>
                                                        <p id="myFiles"></p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div> --%>
                                        
                                       <div id="mainDiv" class="mainDiv">
											<div id="filediv" class="fileDiv">
												<div class="row">
													<div class="col s12 m6 l6" style="margin-top: 8px;">
														<label for="Category"><spring:message code="input.documenttype"/> <span class="star"> * </span></label>  <select
															class="browser-default" id="docTypetag1" required>
															<option value="" disabled selected><spring:message code="select.documenttype" /></select> 
														
														<select class="browser-default" id="docTypetagValue1"
															style="display: none;">
															<option value="" disabled selected><spring:message
																	code="select.documenttype" /></option>

														</select>
													</div>

													<div class="file-field col s12 m6">
														<h6 style="color: #000;">
															<spring:message code="input.supportingdocument" />
															<span class="star">*</span></h6>
														</h6>
														<div class="btn">
															<span><spring:message code="input.selectfile" /></span>
															<input type="file" name="files[]" id="docTypeFile1" required>
														</div>
														<div class="file-path-wrapper">
															<input class="file-path validate" type="text"
																placeholder="Upload one or more files">
															<div>
																<p id="myFiles"></p>
															</div>
														</div>
													</div>
												</div>


											</div>
									</div> 
                               
                                        <span style="margin-left: 5px;"><spring:message code="input.requiredfields" /><span
                                                class="star">*</span></span>
                                            <div class="center" >
                                                <button class="btn " type="submit"><spring:message code="button.update" /></button>
                                                <!-- <a href="manageTypeDevices.html" class="btn" id="Cancel"
                                                    style="margin-left: 10px;">Cancel</a> -->
                                                    <button class="modal-close btn" type="button" style="margin-left: 10px;"><spring:message code="button.cancel" /></button>
                                            </div>
                                            </form>
                                    </div>
                                    
                                </div>	
                                
     <div id="updateManageTypeDevice" class="modal">
     <h6 class="modal-header" style="margin:0px;"><spring:message code="button.update" /></h6>
        <div class="modal-content">
            
            <div class="row">
                <h6 id="updateTacMessage"><spring:message code="input.requestupdated" /></h6>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <a href="./manageTypeDevices" class="btn"><spring:message code="modal.ok" /></a>
                </div>
            </div>
        </div>
    </div>
	
		<!--end container-->
	</section>
	
		<!-- -----------------------------------------------------------------Approved Model------------------------------------------------------------------------------ -->


	<div id="ApproveTAC" class="modal">
		<h6 class="modal-header"><spring:message code="input.Approve" /></h6>
		<div class="modal-content">


			<div class="row">
				<!-- <h6>
					The tax against the consignment with <span id="ManufacturerName"></span>
					having Transaction ID : ( <span id="TACnumber"></span>
					) has been successfully paid.
				</h6> -->
			</div>
			<div class="row">
				<h6><spring:message code="input.approveTACStatus" /><span id="ApproveTacTxnId"></span> ?</h6>
				<input type="text" id="setApproveTacTxnId"
					style="display: none">
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<div class="input-field col s12 center">
						<button class="modal-close modal-trigger btn"
							onclick="approveSubmit(0)"><spring:message code="modal.yes" /></button>
						<button class="modal-close btn" style="margin-left: 10px;"><spring:message code="modal.no" /></button>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	     <div id="confirmApproveTAC" class="modal">
                <h6 class="modal-header"><spring:message code="input.ApproveTAC" /></h6>
                <div class="modal-content">
    
            <div class="row">
                <h6 id="approveSuccessMessage"></h6>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <div class="input-field col s12 center">
                        <a href="./manageTypeDevices" class="modal-close btn"><spring:message code="modal.ok" /></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <div id="RejectTAC" class="modal">
		<h6 class="modal-header"><spring:message code="input.TACstatus" /></h6>
		<div class="modal-content">


			<div class="row">
				<!-- <h6>
					The tax against the consignment with <span id="ManufacturerName"></span>
					having Transaction ID : ( <span id="TACnumber"></span>
					) has been successfully paid.
				</h6> -->
			</div>
			<div class="row">
				<h6><spring:message code="input.rejectTAC" /> <span id="RejectTacTxnId"></span> ?</h6>
				<input type="text" id="setRejectTacTxnId"
					style="display: none">
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<div class="input-field col s12 center">
						<button class="modal-close modal-trigger btn"
							onclick="rejectSubmit(0)"><spring:message code="modal.yes" /></button>
						<button class="modal-close btn" style="margin-left: 10px;"><spring:message code="modal.no" /></button>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	     <div id="confirmRejectTAC" class="modal">
                <h6 class="modal-header"><spring:message code="input.ApproveTAC" /></h6>
                <div class="modal-content">
    
            <div class="row">
                <h6 id="rejectSuccessMessage"></h6>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <div class="input-field col s12 center">
                        <a href="./manageTypeDevices" class="modal-close btn"><spring:message code="modal.ok" /></a>
                    </div>
                </div>
            </div>
        </div>
    </div>

<div id="viewImporterModal" class="modal" style="overflow-y: hidden;">
			<h6 class="modal-header"><spring:message code="input.ViewType" /></h6>
			<div class="modal-content">

				


				<div class="row" style="margin-top: 10px;">
					<div class="input-field col s12 m6 l6">
						<input type="text" id="viewtradmark" name="tradmark"
							placeholder="" disabled=""> <label
							for="viewtradmark" class="active"><spring:message code="input.Trademark" /></label>
					</div>

					<div class="input-field col s12 m6 l6">
						<input type="text" id="viewmodelName" name="modelName"
							placeholder="" disabled="disabled"> <label
							for="viewmodelName" class="active"><spring:message code="input.modelName" /> <span
							class="star"></span></label>
					</div>
					
					<div class="input-field col s12 m6 l6">
						<input type="text" id="viewModelnumber" name="modelNumber"
							placeholder="" disabled="disabled"> <label
							for="viewModelnumber" class="active"><spring:message code="input.modelNumber" /> <span
							class="star"></span></label>
					</div>

					<div class="input-field col s12 m6 l6">
						<input type="text" id="viewManufacturercountry" name="Country"
							placeholder="" disabled=""> <label
							for="viewManufacturercountry" class="active"><spring:message code="input.manufacturerCountry" /></label>
					</div>
					
			
					<!-- <div class="input-field col s12 m6 l6">
						<input type="text" id="viewDeviceType" name="deviceType"
							placeholder="" disabled=""> <label
							for="deviceType" class="active">Device Type </label>
					</div> -->


				</div>

				<!-- <div class="row" style="margin-top: 5px;">
					<div class="input-field col s12 m6 l6">
						<input type="text" id="viewdeviceTypeID" name="deviceType"
							placeholder="" disabled=""> <label for="deviceType"
							class="active">Device ID Type</label>
					</div> -->


					<div class="row">
					
					<div class="input-field col s12 m6 l6">
						<input type="text" id="viewFrequency" name="tac" placeholder=""
							disabled=""> <label for="viewFrequency" class="active"><spring:message code="input.frequency" /></label>
					</div>

					<div class="input-field col s12 m6 l6">
						<input type="text" id="viewImportertac" name="tac" placeholder=""
							disabled=""> <label for="tac" class="active"><spring:message code="input.TAC" /></label>
					</div>
			</div>
		<div class="modal-content">
<div id="live-chat">
<div class="chat">
<div class="chat-history">
<div class="chat-message clearfix" id="chatMsg">

</div> <!-- end chat-message -->


</div>
</div>
</div>
</div>
		</div>
				<div class="row ">
				<div class="center  popup-btn-div" style="margin-top: 10px;">
					<button class="modal-close btn" type="button" id="Cancel"
						style="margin-left: 10px;"><spring:message code="modal.close" /></button>
				</div>



			</div>
		</div>
		
		





	<!--materialize js-->
	<script type="text/javascript"
		src="${context}/resources/js/materialize.js?version=<%= (int) (Math.random() * 10) %>"></script>
	<script type="text/javascript"
		src="${context}/resources/js/plugins/data-tables/js/jquery.dataTables.js?version=<%= (int) (Math.random() * 10) %>"></script>
	<script type="text/javascript"
		src="${context}/resources/js/plugins/data-tables/js/jquery.dataTables.min.js?version=<%= (int) (Math.random() * 10) %>"></script>



	<!--plugins.js - Some Specific JS codes for Plugin Settings-->
<script
		src="${context}/resources/custom_js/bootstrap.min.js?version=<%= (int) (Math.random() * 10) %>"></script>

	<%--   <script type="text/javascript" src="${context}/resources/js/materialize-plugins/date_picker/picker.date.js"></script>
    <script type="text/javascript" src="${context}/resources/js/materialize-plugins/date_picker/picker.js"></script> --%>
	<!--custom-script.js - Add your own theme custom JS-->
	<script type="text/javascript" src="${context}/resources/js/plugins.js?version=<%= (int) (Math.random() * 10) %>"></script>
	<script type="text/javascript"
		src="${context}/resources/js/Validator.js?version=<%= (int) (Math.random() * 10) %>"></script>
	<!--prism
    <script type="text/javascript" src="${context}/resources/resources/js/prism/prism.js?version=<%= (int) (Math.random() * 10) %>"></script>-->
	<!--scrollbar-->
	<script type="text/javascript"
		src="${context}/resources/js/plugins/perfect-scrollbar/perfect-scrollbar.min.js?version=<%= (int) (Math.random() * 10) %>"></script>
	<!-- chartist -->
	<%-- <script type="text/javascript" src="${context}/resources/js/plugins/chartist-js/chartist.min.js"></script> --%>
	<script type="text/javascript"
		src="${context}/resources/js/countries.js?version=<%= (int) (Math.random() * 10) %>"></script>
			<!-- i18n library -->
	<script type="text/javascript"
		src="${context}/resources/project_js/CLDRPluralRuleParser.js?version=<%= (int) (Math.random() * 10) %>"></script>
	<script type="text/javascript"
		src="${context}/resources/i18n_library/i18n.js?version=<%= (int) (Math.random() * 10) %>"></script>
	<script type="text/javascript"
		src="${context}/resources/i18n_library/messagestore.js?version=<%= (int) (Math.random() * 10) %>"></script>

	<script type="text/javascript"
		src="${context}/resources/i18n_library/fallbacks.js?version=<%= (int) (Math.random() * 10) %>"></script>

	<script type="text/javascript"
		src="${context}/resources/i18n_library/language.js?version=<%= (int) (Math.random() * 10) %>"></script>

	<script type="text/javascript"
		src="${context}/resources/i18n_library/parser.js?version=<%= (int) (Math.random() * 10) %>"></script>


	<script type="text/javascript"
		src="${context}/resources/i18n_library/emitter.js?version=<%= (int) (Math.random() * 10) %>"></script>


	<script type="text/javascript"
		src="${context}/resources/i18n_library/bidi.js?version=<%= (int) (Math.random() * 10) %>"></script>

	<script type="text/javascript"
		src="${context}/resources/i18n_library/history.js?version=<%= (int) (Math.random() * 10) %>"></script>

	<script type="text/javascript"
		src="${context}/resources/i18n_library/min.js?version=<%= (int) (Math.random() * 10) %>"></script>
	
<script type="text/javascript"
		src="${context}/resources/project_js/backbutton.js?version=<%= (int) (Math.random() * 10) %>"></script>
	<script type="text/javascript"
		src="${context}/resources/project_js/dragableModal.js?version=<%= (int) (Math.random() * 10) %>"></script>	
			<script type="text/javascript"
		src="${context}/resources/project_js/enterKey.js?version=<%= (int) (Math.random() * 10) %>"></script>
	<script type="text/javascript" src="${context}/resources/project_js/globalVariables.js?version=<%= (int) (Math.random() * 10) %>"></script>
	<script type="text/javascript"
		src="${context}/resources/project_js/ViewManageTypeApproved.js?version=<%= (int) (Math.random() * 10) %>"></script>	

		<script type="text/javascript"
		src="${context}/resources/project_js/profileInfoTab.js?version=<%= (int) (Math.random() * 10) %>" async></script>
</body>
</html>
<%
	} else {
		/*  request.setAttribute("msg", "  *Please login first");
		request.getRequestDispatcher("./index.jsp").forward(request, response); */
%>
<script language="JavaScript">
	sessionStorage.setItem("loginMsg",
			"*Session has been expired");
	window.top.location.href = "./login";
</script>
<%
	}
%>

