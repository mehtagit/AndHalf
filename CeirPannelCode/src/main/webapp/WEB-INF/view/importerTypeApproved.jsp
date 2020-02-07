<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
<!-- <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css"></script>
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
<%-- <link href="${context}/resources/js/plugins/chartist-js/chartist.min.css" type="text/css" rel="stylesheet" media="screen,projection"> --%>
<link rel="stylesheet"
	href="${context}/resources/project_css/viewStock.css">
<link rel="stylesheet"
	href="${context}/resources/project_css/iconStates.css">

</head>
<body data-roleType="${usertype}" data-userID="${userid}"
	 data-userTypeID="${usertypeId}"
	data-selected-roleType="${selectedUserTypeId}"
	data-stolenselected-roleType="${stolenselectedUserTypeId}">




	<section id="content">
		<!--start container-->
		<div class="container">
			<div class="section">
				<div class="row">
					<div class="col s12 m12 l12"> 
						<div class="row card-panel">
							<div class="container-fluid pageHeader">
								<p class="PageHeading">
									<spring:message code="modal.header.typeApproved" />
								</p>
							</div>

							<form onsubmit="return registerTAC()" method="POST"
								enctype="multipart/form-data" id="registerTAC">
								<div class="row" style="margin-top: 10px;">

									<div class="row">


										<div class="input-field col s12 m6 l6"
											style="margin-top: 22px">
											<input type="text" id="trademark" name="trademark"
												maxlength="50"> <label for="trademark"><spring:message
													code="registration.trademark" /> <span class="star">*</span></label>
										</div>


										<div class="col s12 m6 l6" style="margin-bottom: 5px;">
											<label for="productName"><spring:message
													code="registration.productname" /> <span class="star">*</span></label>
											<select id="productname" class="browser-default">
												<option value="" disabled selected><spring:message
														code="registration.selectproduct" />
												</option>
											</select>
										</div>


										<div class="row">
											<div class="col s12 m6 l6">
												<label for="modalNumber"><spring:message
														code="registration.modelnumber" /> <span class="star">*</span></label>
												<select id="modelNumber" class="browser-default">
													<option value="" disabled selected>
														<spring:message code="registration.selectmodelnumber" /></option>

												</select>
											</div>

										<div class="col s12 m6 l6" hidden>
										<select class="browser-default" required="required" id="status" >
										</select>
										</div>
											
											<div class="col s12 m6 l6">
												<label><spring:message
														code="registration.countrymanufacture" /> <span
													class="star">*</span></label> <select id="country"
													class="browser-default" class="mySelect"
													style="padding-left: 0;" required></select>
											</div>


										</div>

										<div class="row" style="margin-top: 10px;">
											<div class="input-field col s12 m6 l6">
												<input type="text" id="frequencyrange" 
													maxlength="50"> <label for="frequencyrange"><spring:message
														code="registration.frequencyrange" /> <span class="star">*</span></label>
											</div>
											<div class="input-field col s12 m6 l6">
												<input type="text" id="tac" name="tac" maxlength="8">
												<label for="tac"><spring:message
														code="registration.tac" /> <span class="star">*</span></label>
											</div>
										</div>

										<div id="mainDiv" class="mainDiv">
											<div id="filediv" class="fileDiv">
												<div class="row">
													<div class="col s12 m6 l6" style="margin-top: 8px;">
														<label for="Category"><spring:message
																code="input.documenttype" /></label> <select
															class="browser-default" id="docTypetag1">
															<option value="" disabled selected><spring:message
																	code="select.documenttype" />
															</option>

														</select> <select class="browser-default" id="docTypetagValue1"
															style="display: none;">
															<option value="" disabled selected><spring:message
																	code="select.documenttype" /></option>

														</select>
													</div>

													<div class="file-field col s12 m6">
														<h6 style="color: #000;">
															<spring:message code="input.supportingdocument" />
														</h6>
														<div class="btn">
															<span><spring:message code="input.selectfile" /></span>
															<input type="file" name="files[]" id="docTypeFile1">
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
										<div class="col s12 m6 right">
											<button class="btn right add_field_button">
												<span style="font-size: 20px;">+</span>
												<spring:message code="input.addmorefile" />
											</button>
										</div>
									</div>

									<span><spring:message code="input.requiredfields" /><span
										class="star">*</span></span>

									<div class="center" style="margin-top: 50px;">
										<button class="btn" id="trcSubmitButton"
											type="submit">
											<spring:message code="button.submit" />
										</button>
										<a href="./manageTypeDevices2" class="btn" id="Cancel"
											style="margin-left: 10px;"><spring:message
												code="button.cancel" /></a>
									</div>
							</form>
						</div>
					</div>

				</div>

			</div>
		</div>

		<!--end container-->
	</section>



  <div id="RegisterManageTypeDevice" class="modal">
     <h6 class="modal-header" style="margin:0px;">Update</h6>
        <div class="modal-content">
            
            <div class="row">
                <h6 id="updateTacMessage"> Your request has been successfully saved.</h6>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <a href="./manageTypeDevices2" class="btn">ok</a>
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

	<%-- <script type="text/javascript" src="${context}/resources/js/materialize-plugins/date_picker/picker.date.js"></script>
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
	<!-- i18n library -->
	<script type="text/javascript"
		src="${context}/resources/project_js/CLDRPluralRuleParser.js"></script>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.i18n/1.0.7/jquery.i18n.js"></script>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.i18n/1.0.7/jquery.i18n.messagestore.js"></script>

	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.i18n/1.0.7/jquery.i18n.fallbacks.js"></script>

	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.i18n/1.0.7/jquery.i18n.language.js"></script>

	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.i18n/1.0.7/jquery.i18n.parser.js"></script>


	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.i18n/1.0.7/jquery.i18n.emitter.js"></script>


	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.i18n/1.0.7/jquery.i18n.emitter.bidi.js"></script>

	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/history.js/1.8/bundled/html4+html5/jquery.history.js"></script>

	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/js-url/2.5.3/url.min.js"></script>
	<script type="text/javascript"
		src="${context}/resources/project_js/viewStock.js"></script>

	<script type="text/javascript">
		window.parent
				.$('#langlist')
				.on(
						'change',
						function() {
							var lang = window.parent.$('#langlist').val() == 'km' ? 'km'
									: 'en';
							window.location
									.assign("./openGrievanceForm?reqType=formPage&lang="
											+ lang);
						});
		$.i18n().locale = lang;
		var documenttype, selectfile, selectDocumentType;
		$.i18n().load({
			'en' : './resources/i18n/en.json',
			'km' : './resources/i18n/km.json'
		}).done(function() {
			console.log("done")
		});
		
		var featureId = 21;
		populateCountries("country");
		
		$.getJSON('./getDropdownList/'+featureId+'/'+$("body").attr("data-userTypeID"), function(data) {
			for (i = 0; i < data.length; i++) {
				$('<option>').val(data[i].state).text(data[i].interp)
				.appendTo('#status');
			}
		});
		
	
		
		function registerTAC() {
			var trademark = $('#trademark').val();
			var productName = $('#productname').val();
			var modelNumber = $('#modelNumber').val();
			var manufacturerCountry = $('#country').val();
			var frequencyRange = $('#frequencyrange').val();
			var tac = $('#tac').val();
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
					"trademark" : $('#trademark').val(),
					"productName" : parseInt($('#productname').val()),
		 			"modelNumber" : parseInt($('#modelNumber').val()),
					"manufacturerCountry" : $('#country').val(),
		 			"frequencyRange" : $('#frequencyrange').val(),
					"tac" : $('#tac').val(),
			 		"userId" : $("body").attr("data-userID"),
			 		"featureId" : featureId,
			 		"approveStatus" : parseInt($("#status option:eq(2)").val())
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
						console.log("-----success"+data);
						$("#trcSubmitButton").prop('disabled', true);
						$('#RegisterManageTypeDevice').openModal();
				},
				error : function(jqXHR, textStatus, errorThrown) {
					console.log("error in ajax")
				}
			});

			return false;

		}

		$.getJSON('./getSourceTypeDropdown/DOC_TYPE/11', function(data) {
			console.log("@@@@@" + JSON.stringify(data));
			for (i = 0; i < data.length; i++) {
				console.log(data[i].interp);
				$('<option>').val(data[i].tagId).text(data[i].interp).appendTo(
						'#docTypetag1');
				$('<option>').val(data[i].value).text(data[i].tagId).appendTo(
						'#docTypetagValue1');
				$('#docTypetagValue1').val(data[i].value);
			}
		});

		function cleanReplyPopUp() {
			console.log("reset form function");
			$('#replymessageForm').trigger("reset");
		}

		var max_fields = 15; //maximum input boxes allowed
		var wrapper = $(".mainDiv"); //Fields wrapper
		var add_button = $(".add_field_button"); //Add button ID
		var x = 1; //initlal text box count
		var id = 2;
		$(".add_field_button")
				.click(
						function(e) { //on add input button click
							e.preventDefault();
							if (x < max_fields) { //max input box allowed
								x++; //text box increment
								$(wrapper)
										.append(
												'<div id="filediv'+id+'" class="fileDiv"><div class="row"><div class="file-field col s12 m6"><label for="Category">'
														+ $
																.i18n('documenttype')
														+ ' <span class="star">*</span></label><select id="docTypetag'+id+'" required class="browser-default"> <option value="" disabled selected>'
														+ $
																.i18n('selectDocumentType')
														+ ' </option></select><select id="docTypetagValue'+id+'" style="display:none" class="browser-default"> <option value="" disabled selected>'
														+ $
																.i18n('selectDocumentType')
														+ ' </option></select></div> <div class="file-field col s12 m6" style="margin-top: 23px;"><div class="btn"><span>'
														+ $.i18n('selectfile')
														+ '</span><input id="docTypeFile'+id+'" type="file" required name="files[]" id="filer_input" /></div><div class="file-path-wrapper"><input class="file-path validate" type="text"></div></div><div style="cursor:pointer;background-color:red;margin-right: 1.7%;" class="remove_field btn right btn-info">-</div></div></div>'); //add input box
							}

							$.getJSON('./getSourceTypeDropdown/DOC_TYPE/11', function(
									data) {

								for (i = 0; i < data.length; i++) {
									console.log(data[i].interp);
									var optionId = id - 1;
									$('<option>').val(data[i].tagId).text(
											data[i].interp).appendTo(
											'#docTypetag' + optionId);
									$('<option>').val(data[i].value).text(
											data[i].tagId).appendTo(
											'#docTypetagValue' + optionId);
									console.log('#docTypetag' + optionId);

								}
							});
							id++;

						});
		/* $(wrapper).on("click", ".remove_field", function (e) { //user click on remove text
			e.preventDefault();
			$(this).parent('div').remove();
			x--;
			id--;
		})
		 */
		$(wrapper).on("click", ".remove_field", function(e) { //user click on remove text
			e.preventDefault();
			var Iid = id - 1;
			/*alert("@@@"+Iid)*/
			$('#filediv' + Iid).remove();
			$(this).parent('div').remove();
			x--;
			id--;

		})
		function saveDocTypeValue() {
			$('#docTypetagValue').val(data[i].value).change();
			$('#docTypetagValue').val(data[i].value).change();
		}

		$.getJSON('./productList', function(data) {
			for (i = 0; i < data.length; i++) {
				$('<option>').val(data[i].id).text(data[i].brand_name)
						.appendTo('#productname');
			}
		});

		$('#productname').on(
				'change',
				function() {
					var brand_id = $('#productname').val();
					$.getJSON('./productModelList?brand_id=' + brand_id,
							function(data) {
								$("#modelNumber").empty();
								for (i = 0; i < data.length; i++) {
									$('<option>').val(data[i].id).text(
											data[i].modelName).appendTo(
											'#modelNumber');
								}
							});
				});
	</script>
</body>
</html>

