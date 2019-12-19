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

<body data-roleType="${usertype}" data-userTypeID="${usertypeId}"
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
								<p class="PageHeading">Report Type-Approved Devices</p>
							</div>

							
								<div class="row" style="margin-top: 10px;">
									<div class="input-field col s12 m6 l6">
										<input type="text" id="manufacturerId" name="manufacturerId"  pattern="[A-Za-z0-9]{0,15}" title="Please enter alphabets and numbers upto 15 characters only"  maxlength="10"/>
										<label for="manufacturerId" >Manufacturer ID</label>
									</div>

									<div class="input-field col s12 m6 l6">
										<input type="text" id="manufacturerName" pattern="[A-Za-z0-9 \s]{0,160}" title="Please enter alphabets and numbers upto 15 characters only"  maxlength="160"
											name="manufacturerName"  required="required" /> <label for="manufacturerName">Manufacturer
											Name <span class="star">*</span>
										</label>
									</div>

									<div class="col s12 m6 l6">
										<label for="country">Country <span class="star">*</span></label>
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
											for="bdate2">Request Date <span class="star">*</span></label>
									</div>


								</div>

								<div class="row" style="margin-top: 5px;">

									<div class="input-field col s12 m6 l6">
										<input type="text" id="tac" pattern="[0-9]{7,7}" title="Please enter 7 digits tac number"  maxlength="7" required="required"
										 name="tac" /> <label for="tac">TAC
											<span class="star">*</span>
										</label>
									</div>

									<div class="col s12 m6 l6">
										<label for="status">Status <span class="star">*</span></label>
										<select class="browser-default" required="required" id="status">
											<option>Status</option>
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
											for="bdate2">Approve/Rejection Date <span
											class="star">*</span></label>
									</div>

                                            
                                            
                                            
									<div class="input-field col s12 m6 l6" style="margin-top: 9px;">
										<textarea id="remark" class="materialize-textarea"></textarea>
										<label for="Remark">Remark </label>
									</div>
								</div>

								<div class="row">
									<h6 style="color: #000; margin-left: 10px;">
										Upload Supporting Document <span class="star">*</span>
									</h6>
									<div class="file-field col s12 m6">
										<div class="btn">
											<span>Select File</span> <input id="file" type="file" required="required"
												multiple>
										</div>
										<div class="file-path-wrapper">
											<input class="file-path validate" type="text" multiple>
											<div>
												<p id="myFiles"></p>
											</div>
										</div>
									</div>
								</div>
								<span style="margin-left: 5px;"> Required Field are
									marked with <span class="star">*</span>
								</span>
								<div class="center" style="margin-top: 50px;">
									<button  class=" btn"
                                                 type="submit">Submit</button>
									<a href="./manageTypeDevices" class="btn" id="Cancel"
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
		  <div id="RegisterManageTypeDevice" class="modal">
     <h6 class="modal-header" style="margin:0px;">Update</h6>
        <div class="modal-content">
            
            <div class="row">
                <h6 id="updateTacMessage"> Your request has been successfully saved.</h6>
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
			var formData = new FormData();
			formData.append('file', $('#file')[0].files[0]);
			formData.append('manufacturerId', manufacturerId);
			formData.append('manufacturerName', manufacturerName);
			formData.append('country', country);
			formData.append('tac', tac);
			formData.append('approveStatus', approveStatus);
			formData.append('approveDisapproveDate', approveDisapproveDate);
			formData.append('requestDate',requestDate);
			formData.append('remark', remark);
			formData.append('userId',userId);
			
			$.ajax({
				url : './register-approved-device',
				type : 'POST',
				data : formData,
				 processData : false,
				contentType : false, 
				success : function(data, textStatus, jqXHR) {
						console.log("-----success"+data);
					  $('#RegisterManageTypeDevice').openModal();
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