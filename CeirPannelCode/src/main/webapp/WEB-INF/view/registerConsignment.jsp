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
	href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>

<script src="http://malsup.github.io/jquery.blockUI.js"></script>


<style>
.row {
	margin-bottom: 0;
	margin-top: 0;
}

/* input[type=text] {
      height: 35px; 
      margin: 0 0 5px 0;
    } */
input
[
type
=
text
]
:not
 
(
.browser-default
 
)
{
font-size
:
 
13
px
;
/* height: 30px; */


}
input[type=date] {
	font-size: 0.8rem;
}

textarea.materialize-textarea {
	padding: 0;
	padding-left: 10px;
}

.btn-flat {
	height: auto;
}

#starColor {
	color: red;
}



section {
	margin-top: 10px;
}
</style>

</head>
<body>
	<!-- START MAIN -->

	<!-- START WRAPPER -->
	<div class="wrapper">

		<!-- START CONTENT -->
		<section id="content">
			<!--start container-->
			<div class="container">
				<div class="section">
					<div class="row">
						<div class="col s12 m12 l12">
							<div class="row card-panel">
								<form action="" onsubmit="return registerConsignment()"
									method="POST" enctype="multipart/form-data"
									id="registerConsignment">
									<div class="container-fluid pageHeader">
										<p class="PageHeading"><spring:message code="registerconsignment.view.header.title" /></p>
										<!-- <button type="button" class="waves-effect waves-light modal-trigger boton right"
                      data-target="modal1">Register Consignment</button> -->
									</div>

									<div class="row myRow">
										<div class="input-field col s12 m6">
											<input type="text" name="supplierId" id="supplierId"
												pattern="[A-Za-z0-9]{0,15}"
												title="<spring:message code="validation.15character" />"
												maxlength="15" /> <label for="supplierId" class="center-align"><spring:message code="input.supplier" /></label>
										</div>

										<div class="input-field col s12 m6">
											<input type="text" name="supplierName" id="supplierName"
												pattern="[A-Za-z  ]{0,50}"
												title="<spring:message code="validation.50character" />"
												maxlength="50" required /> <label for="supplierName"
												class="center-align"><spring:message code="input.suppliername"/> <span
												class="star">*</span></label>
										</div>
									</div>
									<div class="row myRow">
										<div class="input-field col s12 m6">
											<input type="text" name="consignmentNumber"
												id="consignmentNumber" pattern="[A-Za-z0-9]{0,15}"
												title="<spring:message code="validation.15character" />"
												maxlength="15" /> <label for="consignmentNumber" class="center-align"><spring:message code="input.consignmentnumber" /></label>
										</div>

										<div class="input-field col s12 m6">
											<input type="text" name="expectedDispatcheDate"
												id='expectedDispatcheDate' class='form-control datepick'
												autocomplete='off' required="required"> <label
												for="expectedDispatcheDate" class="center-align"><spring:message code="input.dispatchdate" /> <span class="star">*</span>
											</label> <span class="input-group-addon" style="color: #ff4081"><i
												class="fa fa-calendar" aria-hidden="true"></i></span>
										</div>
									</div>

									<div class="row myRow">
										<div class="input-field col s12 m6">
											<select id="country" name="organisationcountry"
												required="required" class="browser-default" class="mySelect"
												style="padding-left: 0;" required></select>
										</div>


										<div class="input-field col s12 m6">
											<input name="expectedArrivaldate" id="expectedArrivaldate"
												type="text" class='form-control datepick' autocomplete='off'
												required="required"> <label for="expectedArrivaldate"
												class="center-align"><spring:message code="input.arrivaldate" /> <span
												class="star">*</span></label> <span class="input-group-addon"
												style="color: #ff4081"><i class="fa fa-calendar"
												aria-hidden="true"></i></span>
										</div>
									</div>

									<div class="row myRow">
										<div class="input-field col s12 m6">
										<input type="text" name="totalPrice" id="totalPrice"  pattern="[0-9]{0,7}" title="Please enter price in numbers"
												maxlength="7" /> <label for="totalPrice"
												class="center-align"><spring:message code="input.totalprice" /></label>
										</div>

										<div class="input-field col s12 m6">
											<input type="text" name="quantity" id="quantity"
												pattern="[0-9]{0,7}"
												title="<spring:message code="validation.7character" />"
												maxlength="7" required /> <label for="quantity"
												class="center-align"><spring:message code="input.quantity" /><span class="star">*</span></label>
										</div>


										<div class=" col s12 m6">
											
												<label for="expectedArrivalPort"><spring:message code="input.arrivalport" /><span class="star">*</span></label>
											<select class="browser-default" id="expectedArrivalPort"
												required="required" name="expectedArrivalPort">
												<%-- <spring:message code="input.arrivalport" /> --%>
												<option value="" disabled selected> <spring:message code="input.arrivalport" /></option>
											</select>
										</div>

										<div class="col s12 m6">
											<label for="currency"><spring:message code="input.currency" /></label>
											<select id="currency" class="browser-default">
												<option value="" disabled selected><spring:message code="input.currency" /></option>

											</select>
										</div>
									</div>


									<div class="row myRow">
										<h6 class="file-upload-heading" style="margin-left: 15px;">
											<spring:message code="input.bulkdevice" /><span class="star">*</span>
										</h6>
										<div class="file-field input-field col s12 m6"
											style="margin-top: 5px;">
											<div class="btn">
												<span><spring:message code="input.selectfile" /></span> <input type="file"
													required="required" name="file" id="file" accept=".csv">
											</div>
											<div class="file-path-wrapper">
												<input class="file-path validate responsive-file-div"
													type="text">
											</div>
										</div>
										<br> <br>
										<p style="margin-left: 15px;">
											<a href="./sampleFileDownload/3"><spring:message code="input.downlaod.sample" /></a>
										</p>
										<span><spring:message code="input.requiredfields" /> <span
											class="star">*</span></span>
									</div>


									<div class="row">
										<div class="input-field col s12 center">
						
											<button class=" btn" id="consignmentSubbmitButton" type="submit"><spring:message code="button.submit" /></button>
												
											<a href="#cancelMessage" class="btn modal-trigger"
												type="cancel" style="margin-left: 10px;"><spring:message code="button.cancel" /></a>


										</div>

									</div>
								</form>
							</div>

						</div>
					</div>

				</div>
			</div>
		</section>
	</div>


	<div id="submitConsignment" class="modal">
		<h6 class="modal-header"><spring:message code="modal.header.submitConsignment" /></h6>
		<div class="modal-content">
			<div class="row">
				<h6 id="sucessMessage"><spring:message code="modal.message.futureRef" /></h6>
				<input type="text" style="display: none" id="errorCode">
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<form action="${context}/Consignment/viewConsignment"
						id="closeOkPop" method="POST">
						<a onclick="closeConfirmation()" class="btn"><spring:message code="modal.ok" /></a>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div id="cancelMessage" class="modal">
		<h6 class="modal-header"> <spring:message code="modal.cancelrequest" /></h6>
		<div class="modal-content">
			<div class="row">
				<h6><spring:message code="modal.message" /></h6>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<div class="input-field col s12 center">
						<a href="${context}/Consignment/viewConsignment" class="btn"><spring:message code="modal.yes" /></a>
						<button class="modal-close waves-effect waves-light btn"
							style="margin-left: 10px;"><spring:message code="modal.no" /></button>
					</div>
				</div>
			</div>
		</div>
	</div>


	<!--end container-->



	<!-- END MAIN -->


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
	
	window.parent.$('#langlist').on('change', function() {
		var lang=window.parent.$('#langlist').val() == 'km' ? 'km' : 'en';
		window.location.assign("Consignment/openRegisterConsignmentForm?reqType=formPage&lang="+lang);
	}); 
	

	$.i18n().locale = lang;
	var successMsg;
	$.i18n().load( {
		'en': '../resources/i18n/en.json',
		'km': '../resources/i18n/km.json'
	} ).done( function() { 
		successMsg=$.i18n('successMsg');
	});
	
	
	$(document).ready(function() {
		ConsignmentCurrency();
	});
	
	
	
		function registerConsignment() {
			var supplierId = $('#supplierId').val();
			var supplierName = $('#supplierName').val();
			var consignmentNumber = $('#consignmentNumber').val();
			var expectedArrivalDate = $('#expectedArrivaldate').val();
			var expectedDispatcheDate = $('#expectedDispatcheDate').val();
			var expectedArrivalPort = $('#expectedArrivalPort').val();
			var organisationcountry = $('#country').val();
			var currency = $('#currency').val();
			var totalPrice = $('#totalPrice').val();
			var quantity = $('#quantity').val();
			var formData = new FormData();
			formData.append('file', $('#file')[0].files[0]);
			formData.append('supplierId', supplierId);
			formData.append('supplierName', supplierName);
			formData.append('consignmentNumber', consignmentNumber);
			formData.append('expectedArrivaldate', expectedArrivalDate);
			formData.append('expectedDispatcheDate', expectedDispatcheDate);
			formData.append('expectedArrivalPort', expectedArrivalPort);
			formData.append('organisationcountry', organisationcountry);
			formData.append('quantity', quantity);
			formData.append('currency', currency);
			formData.append('totalPrice', totalPrice);

			$
					.ajax({
						url : '${context}/Consignment/registerConsignment',
						type : 'POST',
						data : formData,
						processData : false,
						contentType : false,
						success : function(data, textStatus, jqXHR) {

							console.log(data);
							$("#consignmentSubbmitButton").prop('disabled', true);
							$('#submitConsignment').openModal();
							if (data.errorCode == "0") {
								console.log("status code = 0");
								$('#sucessMessage')
										.text(successMsg);
								$('#sucessMessage').append(data.txnId);
								$('#errorCode').val(data.errorCode);
							} else if (data.errorCode == "3") {
								console.log("status code = 3");
								$('#sucessMessage').text('');
								$('#sucessMessage').text(
										"consignment number already exist");
								$('#errorCode').val(data.errorCode);
							}
							// $('#updateConsignment').modal('open'); 
							//alert("success");

						},
						error : function(jqXHR, textStatus, errorThrown) {
							console.log("error in ajax")
						}
					});

			return false;

		}
	</script>

	<script type="text/javascript">
		function openDeleteModal(transactionId) {
			/*   $('#deletemodal').modal('open');
			  backdrop: 'static' */
			$('#deletemodal').openModal();
			console.log("transactionId value=" + transactionId);
			$('#deleteTransactionId').val(transactionId);
		}
	</script>
	<script type="text/javascript">
		function myFunction(message) {
			var x = document.getElementById("snackbar");
			x.className = "show";
			$('#errorMessage').html(message);
			setTimeout(function() {
				x.className = x.className.replace("show", "");
			}, 3000);
		}

		function dispatchDateValidation() {
			var currentDate;
			var dispatcDate = $('#expectedDispatcheDate').val();
			var now = new Date();
			if (now.getDate().toString().charAt(0) != '0') {
				currentDate = '0' + now.getDate();

				/* alert("only date="+currentDate); */
			} else {
				currentDate = now.getDate();
			}
			var today = now.getFullYear() + '-' + (now.getMonth() + 1) + '-'
					+ currentDate;
			//alert("today"+today);
			console.log("dispatche=" + dispatcDate);
			console.log("todays parse date" + Date.parse(today));
			console.log("dispatche parse date" + Date.parse(dispatcDate));

			if (Date.parse(today) > Date.parse(dispatcDate)) {
				myFunction("dispatche date should be greater then or equals to today");
				$('#expectedDispatcheDate').val("");
			}

			//alert("current date="+today+" dispatche date="+dispatcDate)
		}

		function arrivalDateValidation() {
			var currentDate;
			var dispatcDate = $('#expectedArrivalDate').val();
			var now = new Date();
			if (now.getDate().toString().charAt(0) != '0') {
				currentDate = '0' + now.getDate();

				/* alert("only date="+currentDate); */
			} else {
				currentDate = now.getDate();
			}
			var today = now.getFullYear() + '-' + (now.getMonth() + 1) + '-'
					+ currentDate;
			//alert("today"+today);
			console.log("dispatche=" + dispatcDate);
			console.log("todays parse date" + Date.parse(today));
			console.log("dispatche parse date" + Date.parse(dispatcDate));

			if (Date.parse(today) > Date.parse(dispatcDate)) {
				myFunction("Arrival date should be greater then or equals to today");
				$('#expectedArrivalDate').val("");
			}

			//alert("current date="+today+" dispatche date="+dispatcDate)
		}

		function closeConfirmation() {

			var errorCode = $('#errorCode').val();
			if (errorCode == 0) {
				console.log("status code = 0");
				$("#closeOkPop").submit();
			} else if (errorCode == 3) {
				console.log("status code = 3");
				$('#sucessMessage').text('');
				$('#submitConsignment').closeModal();
				/// $('#submitConsignment').modal('hide');
			}
		}

		function ConsignmentCurrency() {
			var currency = "CURRENCY";
			$.ajax({
				url : '${context}/Consignment/consignmentCurency?CURRENCY='
						+ currency,
				type : 'GET',
				processData : false,
				contentType : false,
				success : function(data, textStatus, jqXHR) {
					console.log(data);

					$('#currency').empty();
					$('#currency').append(
							'<option value="">Select Currency</option>');
					for (i = 0; i < data.length; i++) {

						var html = '<option value="'+data[i].value+'">'
								+ data[i].interp + '</option>';
						//$('<option>').val(data[i]).channnelName.text(data[i]).channnelName.appendTo('#channelId');
						$('#currency').append(html);
					}
					/* $('#currency').val($("#langid").val()); */

				},
				error : function(jqXHR, textStatus, errorThrown) {
					console.log("error in ajax")
				}
			});
		}
		
		$(document).on("keydown", "#totalPrice", function(e) {
			
		
			  var totalPrice=$('#totalPrice').val();
			  if(totalPrice.length<'1' )
				  {
				  $("#currency").attr("required", true);
				  console.log("required true"+totalPrice.length);
				
				  }
			  else
			    {
				  $("#currency").attr("required", false);
				 
				  console.log("required false"+totalPrice.length);
			    }
			  
			 
		    });
		  
		  $('#totalPrice').keypress(function(event){
			  console.log(" key press required false"+totalPrice.length);
		        $('#currency').attr("required", true);
		    });
	
	</script>


	<script>
		populateCountries("country");

		$(document).ready(function() {

			ConsignmentCurrency();
			
			$.getJSON('${context}/getDropdownList/CUSTOMS_PORT', function(data) {
				/* $("#expectedArrivalPort").empty(); */
				for (i = 0; i < data.length; i++) {
					$('<option>').val(data[i].value).text(data[i].interp)
					.appendTo('#expectedArrivalPort');
					
				}
			});
		});

		$('.datepick').datepicker({
			dateFormat : "yy-mm-dd"
		});
	</script>

</body>
</html>