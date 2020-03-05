<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="msapplication-tap-highlight" content="no">
<meta name="description"
	content="Materialize is a Material Design Admin Template,It's modern, responsive and based on Material Design by Google. ">
<meta name="keywords"
	content="materialize, admin template, dashboard template, flat admin template, responsive admin template,">
<title>CEIR | Importer Portal</title>


<link
	href="${context}/resources/js/plugins/data-tables/css/jquery.dataTables.min.css"
	type="text/css" rel="stylesheet" media="screen,projection">

<jsp:include page="/WEB-INF/view/endUserHeader.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/endUserFooter.jsp"></jsp:include>

<!-- Favicons-->
<!--<link rel="icon" href="images/favicon/favicon-32x32.png" sizes="32x32">-->
<!-- Favicons-->
<link rel="apple-touch-icon-precomposed"
	href="images/favicon/apple-touch-icon-152x152.png">
<!-- For iPhone -->
<meta name="msapplication-TileColor" content="#00bcd4">
<meta name="msapplication-TileImage"
	content="images/favicon/mstile-144x144.png">
<!-- For Windows Phone -->
<link rel="stylesheet"
	href="${context}/resources/font/font-awesome/css/font-awesome.min.css">

<!-- CORE CSS-->
<link href="${context}/resources/css/materialize.css" type="text/css"
	rel="stylesheet" media="screen,projection">
<link href="${context}/resources/css/style.css" type="text/css"
	rel="stylesheet" media="screen,projection">
<!-- Custome CSS-->
<link href="${context}/resources/css/custom/custom.css" type="text/css"
	rel="stylesheet" media="screen,projection">

<!-- INCLUDED PLUGIN CSS ON THIS PAGE -->
<link href="${context}/resources/js/plugins/prism/prism.css"
	type="text/css" rel="stylesheet" media="screen,projection">
<link
	href="${context}/resources/js/plugins/perfect-scrollbar/perfect-scrollbar.css"
	type="text/css" rel="stylesheet" media="screen,projection">
<link
	href="${context}/resources/js/plugins/chartist-js/chartist.min.css"
	type="text/css" rel="stylesheet" media="screen,projection">

<style>
.boton {
	color: #2979a5;
	float: right;
	border: solid 1px rgba(33, 167, 201, 0.774);
	padding: 4px 10px;
	border-radius: 7px;
	font-size: 14px;
	background-color: #fff;
}

.row {
	margin-bottom: 0;
	margin-top: 0;
}

input::placeholder {
	color: #444;
}

label {
	margin-top: 5px;
}

footer {
	padding-left: 0;
}

.login-card-panel {
	width: 40%;
	margin: auto !important;
	margin-top: 10vh;
}

@media only screen and (max-width: 992px) {
	.login-card-panel {
		width: 100%;
		margin: auto;
		margin-top: 10vh;
	}
}

@media only screen and (max-width: 627px) {
	.login-card-panel {
		width: 100%;
		margin: auto;
		margin-top: 0vh;
	}
}

@media only screen and (max-width: 425px) {
	.selectDropdwn {
		margin-top: 0;
	}
}
</style>

</head>

<body data-lang-param="${pageContext.response.locale}">



	<!-- START MAIN -->
	<div id="">
		<!-- START WRAPPER -->
		<div class="wrapper">
			<!-- START CONTENT -->
			<section id="content">
				<!--start container-->
				<div class="container" style="margin-top: 10vh;">
					<div class="section">
						<div class="row card-panel login-card-panel">

							<form action="" onsubmit="return DeviceDetails()" method="POST"
								enctype="multipart/form-data" id="">
								<div class="row" id="singleInput">
									<h6 class="fixPage-modal-header ">
										<spring:message code="registration.checkdevice" />
									</h6>
									<div class="col s12 m12 l12">


										<div class="row">
											<div class="input-field col s6 m5">
												<label for="Category"> <spring:message
														code="select.deviceIDType" /><span class="star">*</span></label>
											</div>
											<div class="col s6 m7 selectDropdwn">
												<select class="browser-default" id="deviceIdType"
													oninput="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
													oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
													required>
													<option value="" disabled selected><spring:message
															code="select.selectDeviceIDType" /></option>
												</select>
											</div>
											<div class="input-field col s6 m5">
												<label for="DeviceID"><spring:message
														code="registration.pleaseenterdeviceid" /> <span
													class="star">*</span> :</label>
												<p id="errorMsgOnModal" class="deviceErrorTitle"></p>
											</div>
											<div class="input-field col s6 m7">
												<input type="text" id="DeviceID"required / >

											</div>
										</div>

										<div class="row" style="margin-top: 20px;">
											<div class="input-field col s12 center">
												<!-- <a href="#submitIMEI" class="btn modal-trigger">Submit</a>  -->
												<button class=" btn" type="submit">
													<spring:message code="button.submit" />
												</button>
												<a href="./homePage" class="btn" style="margin-left: 10px;"><spring:message
														code="button.cancel" /></a>
											</div>

										</div>


									</div>
								</div>
							</form>

							<div class="row" id="validDetails" style="display: none;">
								<h6 class="fixPage-modal-header ">
									<spring:message code="registration.checkimeistatus" />
								</h6>
								<div class="col s12 m12 l12">


									<p style="margin-left: 10px;">
										<spring:message code="registration.theimeinumber" />
										<span id="validImeiNumber"> </span> is
										<spring:message code="registration.valid" />
									</p>
									<div class="input-field col s6 m4">
										<label for="validTac"><spring:message
												code="registration.tacnumberis" /></label>
									</div>
									<div class="input-field col s6 m8">
										<input type="text" id="validTac" name="Tac" placeholder=""
											disabled>
									</div>

									<div class="input-field col s6 m4">
										<label for="validbrandName"><spring:message
												code="registration.brandname" /></label>
									</div>
									<div class="input-field col s6 m8">
										<input type="text" id="validbrandName" name="validbrandName"
											placeholder="" disabled>
									</div>

									<div class="input-field col s6 m4">
										<label for="validModelName"><spring:message
												code="registration.modelname" /></label>
									</div>
									<div class="input-field col s6 m8">
										<input type="text" id="validModelName" name="ModelName"
											placeholder="" disabled>
									</div>

									<div class="row">
										<div class="input-field col s12 center">
											<div class="input-field col s12 center">
												<a href="./homePage" class="btn" style="width: 100%;"><spring:message
														code="modal.ok" /></a>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row" id="invalidDetails" style="display: none;">
								<h6 class="fixPage-modal-header ">
									<spring:message code="registration.checkimeistatus" />
								</h6>
								<div class="col s12 m12 l12">


									<p style="margin-left: 10px;">
										<spring:message code="registration.theimeinumber" />
										<span id="InvalidImeiNumber"> </span> is
										<spring:message code="registration.invalid" />
									</p>
									<div class="input-field col s6 m4">
										<label for="invalidTac"><spring:message
												code="registration.tacnumberis" /></label>
									</div>
									<div class="input-field col s6 m8">
										<input type="text" id="invalidTac" name="TAC" placeholder=""
											disabled>
									</div>

									<div class="input-field col s6 m4">
										<label for="invalidRemark"> :<spring:message
												code="input.remarks" /></label>
									</div>
									<div class="input-field col s6 m8">
										<textarea id="invalidRemark" class="materialize-textarea"
											style="height: 22px;" placeholder="" disabled></textarea>
									</div>

									<div class="row">
										<div class="input-field col s12 center">
											<div class="input-field col s12 center">
												<a href="./homePage" class="btn" style="width: 100%;"><spring:message
														code="modal.ok" /></a>
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
			<!-- END CONTENT -->

		</div>
		<!-- END WRAPPER -->
	</div>
	<!-- END MAIN -->


	<div id="errorModal" class="modal">
		<h6 class="modal-header">
			<spring:message code="input.CheckDevice" />
		</h6>
		<div class="modal-content">

			<div class="row">
				<h6 id="">
					<spring:message code="input.notDeviceId" />
				</h6>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<div class="input-field col s12 center">
						<!-- <a href="homePage" class="btn">Yes</a> -->
						<button class="modal-close btn" style="margin-left: 10px;">
							<spring:message code="modal.ok" />
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>







	<!-- Submit Modal start   -->

	<div id="submitIMEI" class="modal">
		<div class="modal-content">
			<h6>
				<spring:message code="registration.submitimei" />
			</h6>
			<hr>
			<div class="row">
				<h6>
					<spring:message code="registration.Theimeivalid" />
				</h6>
				<form action="">
					<div class="input-field col s6 m4">
						<label for="transactionID"><spring:message
								code="registration.tacnumberis" /></label>
					</div>
					<div class="input-field col s6 m8">
						<input type="text" id="transactionID" name="transactionID"
							placeholder="TAC12345678" disabled>
					</div>

					<div class="input-field col s6 m4">
						<label for="transactionID"><spring:message
								code="registration.modaltype" /></label>
					</div>
					<div class="input-field col s6 m8">
						<input type="text" id="transactionID" name="transactionID"
							placeholder="ABC Modal" disabled>
					</div>
				</form>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<div class="input-field col s12 center">
						<a href="index.html" class="btn"><spring:message
								code="modal.ok" /></a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Submit Modal End -->




	<!-- ================================================
    Scripts
    ================================================ -->



	<script>
       /*  function hide() {
            var In = $('#DeviceID').val()
            if (In == "black") {

                $("#inputDetails").css("display", "block");
                $("#singleInput").css("display", "none");

            } else if (In == "blue") {
                $("#inputDetails").css("display", "none");
                $("#singleInput").css("display", "block");
            }
        } */
    </script>

	<!-- jQuery Library -->
	<%-- <script type="text/javascript" src="${context}/resources/js/plugins/jquery-1.11.2.min.js"></script> --%>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.1/jquery.min.js"></script>
	<!-- ajax js -->
	<script type="text/javascript"
		src="${context}/resources/ajax/Registration.js"></script>
	<!--materialize js-->
	<script type="text/javascript"
		src="${context}/resources/js/materialize.js"></script>
	<!--prism
    <script type="text/javascript" src="${context}/resources/js/prism/prism.js"></script>-->
	<!--scrollbar-->
	<script type="text/javascript"
		src="${context}/resources/js/plugins/perfect-scrollbar/perfect-scrollbar.min.js"></script>
	<!-- chartist -->
	<script type="text/javascript"
		src="${context}/resources/js/plugins/chartist-js/chartist.min.js"></script>


	<!-- data-tables -->
	<script type="text/javascript"
		src="${context}/resources/js/plugins/data-tables/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript"
		src="${context}/resources/js/plugins/data-tables/data-tables-script.js"></script>

	<!--plugins.js - Some Specific JS codes for Plugin Settings-->
	<script type="text/javascript" src="${context}/resources/js/plugins.js"></script>
	<!--custom-script.js - Add your own theme custom JS-->
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
		src="${context}/resources/project_js/globalVariables.js"></script>
	<script type="text/javascript"
		src="${context}/resources/project_js/backbutton.js"></script>
	<script type="text/javascript"
		src="${context}/resources/project_js/dragableModal.js"></script>
	<script type="text/javascript"
		src="${context}/resources/project_js/enterKey.js"></script>
	<script type="text/javascript"
		src="${context}/resources/project_js/ValidationFileOutsidePortal.js"></script>
	<script type="text/javascript"
		src="${context}/resources/js/custom-script.js"></script>
	<script type="text/javascript"
		src="${context}/resources/project_js/checkDevice.js"></script>

</body>

</html>