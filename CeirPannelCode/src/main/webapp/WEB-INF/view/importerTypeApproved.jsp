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
                                    <div class="container-fluid pageHeader">
                                        <p class="PageHeading">Type Approve</p>
                                    </div>

                                    <div id="ForeignerFormId">
                                        <div class="row" style="margin-top: 10px;">
                                            <div class="input-field col s12 m6 l6">
                                                <input type="text" id="trademark"  name="trademark"  maxlength="50">
                                                <label for="trademark">Trademark</label>
                                            </div>

                                            <div class="col s12 m6 l6">
                                                <label for="productName">Product Name</label>
                                                <select class="browser-default">
                                                    <option value="" disabled selected>Select Product </option>
                                                    <option value="1">abc</option>
                                                    <option value="2">xyz</option>
                                                </select>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col s12 m6 l6" style="margin-top: 7px;">
                                                <label for="modalNumber">Model Number</label>
                                                <select class="browser-default">
                                                    <option value="" disabled selected>Select Model Number </option>
                                                    <option value="1">abc</option>
                                                    <option value="2">Vxyz</option>
                                                </select>
                                            </div>
                                            
                                            <div class="input-field col s12 m6 l6">
                                                <textarea id="Remark" class="materialize-textarea" maxlength="50"></textarea>
                                                <label for="Remark">Frequency Range</label>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col s12 m6 l6">
                                                <p style="margin-top: 0; margin-bottom: 5px; font-size: 0.9rem;">Country of Manufacture
                                                    </p>
                                                <select id="country" class="browser-default" class="mySelect"
                                                    style="padding-left: 0;" required></select>
                                            </div>

                                            <div class="input-field col s12 m6 l6">
                                                <input type="text" id="tac"  name="tac" maxlength="8">
                                                <label for="tac">TAC</label>
                                            </div>
                                        </div>

                                        <div class="row">                                            
                                            <div class="col s12 m6 l6" style="margin-top: 7px;">
                                                <label for="documenttype">Document Type</label>
                                                <select class="browser-default">
                                                    <option value="" disabled selected>Select Document Type </option>
                                                    <option value="1">Technical Specification</option>
                                                    <option value="2">Professional Report</option>
                                                </select>
                                            </div>

                                            <div class="file-field col s12 m6">
                                                <h6 style="color: #000;">Upload Documnet</h6>
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

                                        <div class="row">
                                            <div class="input_fields_wrap"></div>

                                        <div class="col s12 m6 right">
                                            <button class="btn right add_field_button"><span
                                                    style="font-size: 20px;">+</span> Add More files</button>
                                        </div>
                                        </div>

                                        <span style="margin-left: 5px;"> Required Field are marked with <span
                                                class="star">*</span>

                                            <div class="center" style="margin-top: 50px;">
                                                <button class=" waves-light btn" type="submit">Submit</button>
                                                <a href="./manageTypeDevices" class="btn" id="Cancel" style="margin-left: 10px;">Cancel</a>
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
						$("#trcSubmitButton").prop('disabled', true);
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