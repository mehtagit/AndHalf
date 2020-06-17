<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setDateHeader("Expires", 0);
	if (session.getAttribute("usertype") != null) {
%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<title>TRC</title>
<meta http-equiv='cache-control' content='no-cache'>
<meta http-equiv='expires' content='-1'>
<meta http-equiv='pragma' content='no-cache'>
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
	href="${context}/resources/custom_js/jquery-ui.css?version=<%= (int) (Math.random() * 10) %>">
<script src="${context}/resources/custom_js/1.11.2_jquery-ui.js?version=<%= (int) (Math.random() * 10) %>"></script>

<script src="${context}/resources/custom_js/jquery.blockUI.js?version=<%= (int) (Math.random() * 10) %>"></script>

   <style>
        .row {
            margin-bottom: 0;
            margin-top: 0;
        }

        label {
            color: #444;
            font-size: 13px;
        }

        textarea.materialize-textarea {
            padding: 0;
            /* padding-left: 10px; */
        }

        select {
            height: 2.2rem;
        }

        .welcomeMsg {
            padding-bottom: 50px !important;
            line-height: 1.5 !important;
            text-align: center;
        }
        .picker__date-display {
            display: none !important;
        }
    </style>
</head>

<body data-id="11" data-roleType="${usertype}" data-userTypeID="${usertypeId}"
	data-userID="${userid}" data-selected-roleType="${selectedUserTypeId}"
	data-stolenselected-roleType="${stolenselectedUserTypeId}">

	<!-- START CONTENT -->
	<section id="content">
		
		<!--start container-->
		<div class="container">
			<div class="section">
				<div class="row">
					<div class="col s12 m12 l12">
						<div class="row card-panel">
						<form action="" onsubmit="return registerTAC()"  method="POST" enctype="multipart/form-data"  id="registerTAC">
							<div class="container-fluid pageHeader">
								<p class="PageHeading"><spring:message code="table.ReportTypeApprovedDevices" /></p>
							</div>

							
								<div class="row" style="margin-top: 10px;">
									<div class="input-field col s12 m6 l6">
										<input type="text" id="manufacturerId" name="manufacturerId"  pattern="[A-Za-z0-9]{0,15}" title="Please enter alphabets and numbers upto 15 characters only"  maxlength="10"/>
										<label for="manufacturerId" ><spring:message code="input.ManufacturerID" /></label>
									</div>

									<div class="input-field col s12 m6 l6">
										<input type="text" id="manufacturerName" pattern="[A-Za-z0-9 \s]{0,160}" title="Please enter alphabets and numbers upto 15 characters only"  maxlength="160"
											name="manufacturerName"  required="required" /> <label for="manufacturerName"><spring:message code="input.ManufacturerName" /> <span class="star">*</span>
										</label>
									</div>

									<div class="col s12 m6 l6">
										<label for="country"><spring:message code="input.Country" /> <span class="star">*</span></label>
										<select id="country" class="browser-default" class="mySelect" required="required"
											required></select>
									</div>

									<div class="input-field col s12 m6">
										<input type="text" id="requestDate" required="required"
											class="form-control dateClass" name="requestDate" 
											title="" autocomplete="off"> <span
											class="input-group-addon" style="color: #ff4081"><i
											class="fa fa-calendar" aria-hidden="true"
											style="float: right; margin-top: -37px;"></i></span> <label
											for="requestDate"><spring:message code="input.RequestDate" /> <span class="star">*</span></label>
									</div>


								</div>

								<div class="row" style="margin-top: 5px;">

									<div class="input-field col s12 m6 l6">
										<input type="text" id="tac" pattern="[0-9]{8,8}" title="Please enter 8 digits tac number"  maxlength="8" required="required"
										 name="tac" /> <label for="tac"><spring:message code="input.TAC" /><span class="star">*</span>
										</label>
									</div>

									<div class="col s12 m6 l6">
										<label for="status"><spring:message code="input.Status" /> <span class="star">*</span></label>
										<select class="browser-default" required="required" id="status">
											<option value=""><spring:message code="input.Status" /></option>
										</select>
									</div>
									
									
								</div>

								<div class="row">
									<div class="input-field col s12 m6">
										<input type="text" id="approveDisapproveDate"  required="required"
											class="form-control dateClass" name="approveRejectionDate"
											 title="" autocomplete="off" /> <span
											class="input-group-addon" style="color: #ff4081"><i
											class="fa fa-calendar" aria-hidden="true"
											style="float: right; margin-top: -37px;"></i></span> <label
											for="approveDisapproveDate"><spring:message code="input.Approve/RejectionDate" /><span
											class="star">*</span></label>
									</div>

                                            
                                            
                                            
									<div class="input-field col s12 m6 l6" style="margin-top: 9px;">
										<textarea id="remark" class="materialize-textarea"></textarea>
										<label for="remark"><spring:message code="input.Remark" /> </label>
									</div>
								</div>

								<div id="mainDiv" class="mainDiv">
											<div id="filediv" class="fileDiv">
												<div class="row">
													<div class="col s12 m6 l6" style="margin-top: 8px;">
														<label for="Category"><spring:message code="input.documenttype"/><span class="star">*</span></label> <select
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
															<span class="star">*</span>
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
								<span style="margin-left: 5px;"><spring:message code="input.requiredfields" /><span class="star">*</span>
								</span>
								<div class="center" style="margin-top: 50px;">
									<button  class=" btn" id="trcSubmitButton"
                                                 type="submit"><spring:message code="button.submit" /></button>
									<a href="./manageTypeDevices" class="btn" id="Cancel"
										style="margin-left: 10px;"><spring:message code="button.cancel" /></a>
								</div>
							</form>
						</div>
					</div>

				</div>

			</div>
		</div>
		</div>
		</div>

	<div id="RegisterManageTypeDevice" class="modal">
		<h6 class="modal-header"><spring:message code="modal.header.submitTypeApprove" /></h6>
		<div class="modal-content">
			<div class="row">
				<h6 id="sucessMessage"><spring:message code="modal.message.futureRef"/>  <span id="transactionId"> </span></h6>
				<input type="text" style="display: none" id="errorCode">
			</div>
			 <div class="row">
				<div class="input-field col s12 center">
                    <a href="./manageTypeDevices" class="btn">ok</a>
                </div>
			</div> 
		</div>
	</div>


<%-- <div id="RegisterManageTypeDevice" class="modal">
     <h6 class="modal-header" style="margin:0px;"><spring:message code="button.update" /></h6>
        <div class="modal-content">
            
            <div class="row">
                <h6 id="updateTacMessage"><spring:message code="input.Yoursaved" /></h6>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <a href="./manageTypeDevices" class="btn"><spring:message code="modal.ok" /></a>
                </div>
            </div>
        </div>
    </div>
 --%>		<!--end container-->
	</section>
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
    <script type="text/javascript" src="${context}/resources/resources/js/prism/prism.js"></script>-->
	<!--scrollbar-->
	<script type="text/javascript"
		src="${context}/resources/js/plugins/perfect-scrollbar/perfect-scrollbar.min.js?version=<%= (int) (Math.random() * 10) %>"></script>
	<!-- chartist -->
	<%-- <script type="text/javascript" src="${context}/resources/js/plugins/chartist-js/chartist.min.js"></script> --%>
	<script type="text/javascript"
		src="${context}/resources/js/countries.js?version=<%= (int) (Math.random() * 10) %>"></script>
	
		<script type="text/javascript">
var featureId = 11;
		populateCountries("country");

		function registerTAC() {
			var manufacturerId = $('#manufacturerId').val();
			var manufacturerName = $('#manufacturerName').val();
			var country = $('#country').val();
			var tac = $('#tac').val();
			var approveStatus = parseInt($('#status').val());
			var approveDisapproveDate = $('#approveDisapproveDate').val();
			var requestDate= $('#requestDate').val();
			var remark = $('#remark').val();
			var userId = $("body").attr("data-userID");
			
			var fieldId=1;
			var fileInfo =[];
			var formData= new FormData();
			var fileData = [];
	
			var x;
			var filename='';
			var filediv;
			var i=0;
			var formData= new FormData();
			var docTypeTagIdValue='';
			var filename='';
			
			
	
			$('.fileDiv').each(function() {	
				var x={
					"docType":$('#docTypetag'+fieldId).val(),
					"fileName":$('#docTypeFile'+fieldId).val().replace('C:\\fakepath\\','')
					}
					formData.append('files[]',$('#docTypeFile'+fieldId)[0].files[0]);
					fileInfo.push(x);
					fieldId++;
					i++;
				});
			
			var multirequest={
					"attachedFiles":fileInfo,
					"manufacturerId" : $('#manufacturerId').val(),
					"manufacturerName" : $('#manufacturerName').val(),
					"country" : $('#country').val(),
		 			"tac" : $('#tac').val(),
					"approveStatus" : parseInt($('#status').val()),
		 			"approveDisapproveDate" : $('#approveDisapproveDate').val(),
					"requestDate" : $('#requestDate').val(),
					"remark" : $('#remark').val(),
					"userId" : $("body").attr("data-userID"),
					"featureId" : featureId,
					//"adminApproveStatus" : 2
				}
			
			console.log("multirequest------------->" +JSON.stringify(multirequest))
			
			formData.append('fileInfo[]',JSON.stringify(fileInfo));
			formData.append('multirequest',JSON.stringify(multirequest));
			
			$.ajax({
				url : './register-approved-device',
				type : 'POST',
				data : formData,
				mimeType: 'multipart/form-data',
				processData : false,
				contentType : false, 
				async:false,
				success : function(data, textStatus, jqXHR) {
						var result =  JSON.parse(data)
						console.log("-----success"+result);
						$("#trcSubmitButton").prop('disabled', true);
						  $('#RegisterManageTypeDevice').openModal();
						  $('#transactionId').text(result.txnId);
					  /*if(data.errorCode=="0")
					 {
					 console.log("status code = 0");
					$('#sucessMessage').text('Your form has been successfully submitted. The Transaction ID for future reference is ');
					$('#sucessMessage').append(data.txnId);
					$('#errorCode').val(data.errorCode);
					 }
					else if(data.errorCode=="3")
					 {
					console.log("status code = 3"); 
					$('#sucessMessage').text('');
					$('#sucessMessage').text("consignment number already exist");
					$('#errorCode').val(data.errorCode);
					 } */
					// $('#updateConsignment').modal('open'); 
					//alert("success");
					 
				},
				error : function(jqXHR, textStatus, errorThrown) {
					console.log("error in ajax")
				}
			});

			return false;

		}
		
		/* $.getJSON('./getDropdownList/DOC_TYPE', function(data) {
			console.log("@@@@@" + JSON.stringify(data));
			for (i = 0; i < data.length; i++) {
				console.log(data[i].interp);
				$('<option>').val(data[i].tagId).text(data[i].interp).appendTo(
						'#docTypetag1');
			}
		});
		 */
		 
		 $.getJSON('./getSourceTypeDropdown/DOC_TYPE/'+featureId, function(data) {
				for (i = 0; i < data.length; i++) {
					console.log(data[i].interp);
					$('<option>').val(data[i].tagId).text(data[i].interp).appendTo(
							'#docTypetag1');
				}
			});
		

		$('#approveDisapproveDate,#requestDate').datepicker({
			dateFormat : "yy-mm-dd"
		});
	
		
		$.getJSON('./getDropdownList/'+featureId+'/'+$("body").attr("data-userTypeID"), function(data) {

			for (i = 0; i < data.length; i++) {
				$('<option>').val(data[i].state).text(data[i].interp)
				.appendTo('#status');
			}
		
		});
		
	</script>
			
</body>
</html>
<%
        }
        else{
        
        %>
<script language="JavaScript">
        sessionStorage.setItem("loginMsg", "*Session has been expired.please login again"); 
     	 window.top.location.href ="./login";
   
        </script>
<%
       
        }
%>