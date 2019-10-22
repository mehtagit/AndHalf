<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
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
	href="font/font-awesome/${context}/resources/css/font-awesome.min.css">

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
	href="${context}/resources/js/plugins/chartist-${context}/resources/js/chartist.min.css"
	type="text/css" rel="stylesheet" media="screen,projection">

<!-- Country -->
<script type="text/javascript" src="${context}/resources/js/country.js"></script>

<style>
input[type="checkbox"] {
	display: none;
}

footer {
	padding-left: 0;
}

.btn-flat {
	height: auto;
}

.star {
	color: red;
}

.dropdown-content li>a, .dropdown-content li>span {
	color: #444;
}
</style>

</head>


<body>


	<!-- //////////////////////////////////////////////////////////////////////////// -->

	<!-- START CONTENT -->
	<section id="content" id="mainPage">
		<!--start container-->
		<div class="container">
			<div class="section">
				<form id="registrationForm">
					<div class="card-panel"
						style="width: 90%; margin: auto; padding: 20px 5% 20px 5%;">
						<a href="index.html"
							style="float: right; margin: -10px; margin-right: -20px;"><i
							class="fa fa-times boton" aria-hidden="true"></i></a>
						<div class="row">
							<h5>Registration</h5>
							<hr>
							<div class="row">
								<div class="input-field col s12 m4 l4">
									<input type="text" name="firstName" id="firstName"
										maxlength="14"> <label for="firstName"
										class="center-align">First Name <span class="star">*</span></label>
								</div>

								<div class="input-field col s12 m4 l4">
									<input type="text" name="middleName"
										class="form-control boxBorder boxHeight" id="middleName"
										maxlength="14"> <label for="middleName">Middle
										Name</label>
								</div>

								<div class="input-field col s12 m4 l4">
									<input type="text" name="lastName"
										class="form-control boxBorder boxHeight" id="lastName"
										maxlength="14"> <label for="lastName">Last
										Name <span class="star">*</span>
									</label>
								</div>

								<div class="input-field col s12 m6 l6">
									<input type="text" name="passportNumber"
										class="form-control boxBorder boxHeight" id="passportNumber"
										maxlength="14"> <label for="passportNumber">National
										ID/Passport Number <span class="star">*</span>
									</label>
								</div>

								<div class="input-field col s12 m6 l6">
									<input type="text" name="email"
										class="form-control boxBorder boxHeight" id="email"
										maxlength="30"> <label for="email">Email <span
										class="star">*</span></label>
								</div>

								<div class="input-field col s12 m6 l6">
									<input type="text" name="phone"
										class="form-control boxBorder boxHeight" id="phone"
										maxlength="10"> <label for="phone">Phone
										Number <span class="star">*</span>
									</label>
								</div>

								<div class="input-field col s12 m6 l6">
									<input type="text" name="company"
										class="form-control boxBorder boxHeight" id="company"
										maxlength="30"> <label for="company">Company
										Name <span class="star">*</span>
									</label>
								</div>
							</div>

							<div class="row">
								<div class="input-field col s12 m12 l12">
									<input type="text" name="address"
										class="form-control boxBorder boxHeight" id="address">
									<label for="address">Address(Property Location) <span
										class="star">*</span></label>
								</div>

								<div class="input-field col s12 m6 l6">
									<input type="text" name="streetNumber"
										class="form-control boxBorder boxHeight" id="streetNumber"
										maxlength="30"> <label for="streetNumber">Street
										Number <span class="star">*</span>
									</label>
								</div>

								<div class="input-field col s12 m6 l6">
									<input type="text" name="locality"
										class="form-control boxBorder boxHeight" id="locality"
										maxlength="20"> <label for="locality">Locality
										<span class="star">*</span>
									</label>
								</div>

								<div class="input-field col s12 m6 l6">
									<input type="text" name="province"
										class="form-control boxBorder boxHeight" id="province"
										maxlength="20"> <label for="province">Province<span
										class="star">*</span></label>
								</div>

								<div class="input-field col s12 m6 l6">
									<p
										style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">
										Country <span class="star">*</span>
									</p>
									<select id="country" class="browser-default" class="mySelect"
										style="padding-left: 0;" required></select>
									<!-- <label for="country">Country <span class="star">*</span></label> -->
								</div>
							</div>

							<div class="row">
								<div class="col s12 m6 l6" style="margin-bottom: 20px;">
									<label for="vatNumber">VAT Registration <span
										class="star">*</span></label>
									<div class=" boxHeight">
										<input class="with-gap" name="group3" type="radio"
											onclick="document.getElementById('vatNumberField').style.display = 'block';">
										Yes <input class="with-gap" name="group3" type="radio"
											style="margin-left: 20px;"
											onclick="document.getElementById('vatNumberField').style.display = 'none';"
											checked /> No
									</div>
								</div>

								<div class="input-field col s12 m6 l6" style="display: none;"
									id="vatNumberField">
									<input type="text" name="vatNumber"
										class="form-control boxBorder boxHeight" id="vatNumber"
										maxlength="16"> <label for="roleType">VAT
										Number <span class="star">*</span>
									</label>
								</div>
							</div>

							<div class="row">
								<div class="input-field col s12 m6 l6">
									<p
										style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">
										Role Type <span class="star">*</span>
									</p>
									<select multiple required>
										<option value="" disabled selected>Role Type <span
												class="star"></span></option>
										<option value="Paid">Importer</option>
										<option value="NotPaid">Distributor</option>
										<option value="NotPaid">Retailer</option>
									</select>
								</div>

								<div class="input-field col s12 m6 l6">
									<p
										style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">
										Type <span class="star">*</span>
									</p>
									<select class="browser-default" id="mySelect"
										onchange="myFunction()" required>
										<option value="" disabled selected>Type</option>
										<option value="Individual">Individual</option>
										<option value="Company">Company</option>
										<option value="Organization">Organization</option>
										<option value="Government">Government</option>
									</select>
								</div>
							</div>

							<div class="row myRow" style="display: none;" id="uploadFile">
								<h6 class="file-upload-heading" style="margin-left: 15px;">
									Upload Nationality Information<span class="star">*</span>
								</h6>
								<div class="file-field input-field col s12 m6"
									style="margin-top: 5px;">
									<div class="btn">
										<span>Select File</span> <input type="file" id="csvUploadFile"
											accept=".csv">
									</div>
									<div class="file-path-wrapper">
										<input class="file-path validate responsive-file-div"
											type="text">
									</div>
								</div>
								<br>
								<br>
								<!-- <p style="margin-left: 15px;"><a href="#">Download Sample Format</a></p> -->
							</div>

							<div class="row">
								<div class="input-field col s12 m6 l6">
									<input type="password" name="password"
										class="form-control boxBorder boxHeight" id="password"
										maxlength="14"> <label for="password">Password
										<span class="star">*</span>
									</label>
								</div>

								<div class="input-field col s12 m6 l6">
									<input type="password" name="rePassword"
										class="form-control boxBorder boxHeight" id="rePassword"
										maxlength="14"> <label for="rePassword">Retype
										Password <span class="star">*</span>
									</label>
								</div>
							</div>


							<div class="row">
								<div class="input-field col s12 m6 l6">
									<select class="browser-default" required>
										<option value="" disabled selected>Security Question
											1</option>
										<option value="NotPaid">What is your childhood name?</option>
										<option value="NotPaid">Where is your birth place?</option>
										<option value="NotPaid">What is your favourite movie?</option>
										<option value="NotPaid">What is your favourite sports
											team?</option>
										<option value="NotPaid">What is your favourite pet’s
											name?</option>
									</select>
								</div>

								<div class="input-field col s12 m6 l6">
									<input type="text" name="SecurityAnswer"
										class="form-control boxBorder boxHeight" id="SecurityAnswer"
										maxlength="50"> <label for="SecurityAnswer">Answer
										<span class="star">*</span>
									</label>
								</div>
							</div>

							<div class="row">
								<div class="input-field col s12 m6 l6">
									<select class="browser-default" required>
										<option value="" disabled selected>Security Question
											2</option>
										<option value="NotPaid">What is your childhood name?</option>
										<option value="NotPaid">Where is your birth place?</option>
										<option value="NotPaid">What is your favourite movie?</option>
										<option value="NotPaid">What is your favourite sports
											team?</option>
										<option value="NotPaid">What is your favourite pet’s
											name?</option>
									</select>
								</div>

								<div class="input-field col s12 m6 l6">
									<input type="text" name="SecurityAnswer"
										class="form-control boxBorder boxHeight" id="SecurityAnswer"
										maxlength="50"> <label for="SecurityAnswer">Answer
										<span class="star">*</span>
									</label>
								</div>
							</div>

							<div class="row">
								<div class="input-field col s12 m6 l6">
									<select class="browser-default" required>
										<option value="" disabled selected>Security Question
											3</option>
										<option value="NotPaid">What is your childhood name?</option>
										<option value="NotPaid">Where is your birth place?</option>
										<option value="NotPaid">What is your favourite movie?</option>
										<option value="NotPaid">What is your favourite sports
											team?</option>
										<option value="NotPaid">What is your favourite pet’s
											name?</option>
									</select>
								</div>

								<div class="input-field col s12 m6 l6">
									<input type="text" name="SecurityAnswer"
										class="form-control boxBorder boxHeight" id="SecurityAnswer"
										maxlength="50"> <label for="SecurityAnswer">Answer
										<span class="star">*</span>
									</label>
								</div>
							</div>
						</div>

						<div class="row">
							<span> Required Field are marked with <span class="star">*</span></span>
							<div class="input-field col s12 center">
								<a href="${context}/verifyOtp" class="btn" id="btnSave"> Submit</a>
								<button class="btn" style="margin-left: 10px;">cancel</button>
							</div>
						</div>
				</form>
			</div>
		</div>
		<!--end container-->
	</section>
	<!-- END CONTENT -->



	<!-- //////////////////////////////////////////////////////////////////////////// -->



	<!-- Modal 1 start   -->

	<div id="submitForm" class="modal">
		<button type="button"
			class=" modal-action modal-close waves-effect waves-green btn-flat right"
			data-dismiss="modal">&times;</button>
		<div class="modal-content">
			<!-- <h4 class="header2 pb-2">User Info</h4> -->

			<div class="row">
				<h6>Verify OTP</h6>
				<hr>
				<p>A text message and an E-Mail has been sent to your registered
					Phone number and E-mail. Please Verify !</p>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<a href="otpVerification.html" class="btn">Verify OTP</a>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal End -->

	<!-- Modal 2 start   -->

	<div id="submitActivateDeactivate" class="modal">
		<button type="button"
			class=" modal-action modal-close waves-effect waves-green btn-flat right"
			data-dismiss="modal">&times;</button>
		<div class="modal-content">

			<div class="row">
				<h6>The request has been successfully registered with CEIR
					Admin. Please find the confirmation over registered mail <
					mail@mail.com> in 2 to 3 working days.</h6>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<div class="input-field col s12 center">
						<button class="modal-close waves-effect waves-light btn"
							style="margin-left: 10px;" type="submit" name="add_user"
							id="add_user">Cancel</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal End -->

	<!-- Modal 2 start   -->

	<div id="cancelActivateDeactivate" class="modal">
		<button type="button"
			class=" modal-action modal-close waves-effect waves-green btn-flat right"
			data-dismiss="modal">&times;</button>
		<div class="modal-content">

			<div class="row">
				<h6>Do you want to cancel the request?</h6>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<div class="input-field col s12 center">
						<a href="userRegistration.html" class="btn" type="submit"
							name="add_user" id="add_user">yes</a> <a
							href="#activateDeactivate" class="modal-close modal-trigger btn"
							style="margin-left: 10px;">no</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal End -->
	<!-- ================================================
    Scripts
    ================================================ -->

	<script>
        $(document).ready(function () {
            $('.modal').modal();
        });

        $('.dropdown-trigger').dropdown();

        $(document).ready(function () {
            $('select').formSelect();
        });
    </script>

	<script>
        populateCountries(
            "country",
        );
    </script>

	<script>
        function myFunction() {
            var x = document.getElementById("mySelect").value;
            if (x == 'Individual') {
                document.getElementById("uploadFile").style.display = "block";
            } else {

                document.getElementById("uploadFile").style.display = "none";
            }
        } 
    </script>


	<!-- jQuery Library -->
	<script type="text/javascript"
		src="${context}/resources/js/plugins/jquery-1.11.2.min.js"></script>
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
		src="${context}/resources/js/plugins/chartist-${context}/resources/js/chartist.min.js"></script>

	<!-- data-tables -->
	<script type="text/javascript"
		src="${context}/resources/js/plugins/data-tables/${context}/resources/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript"
		src="${context}/resources/js/plugins/data-tables/data-tables-script.js"></script>

	<!--plugins.js - Some Specific JS codes for Plugin Settings-->
	<script type="text/javascript" src="${context}/resources/js/plugins.js"></script>
	<!--custom-script.js - Add your own theme custom JS-->
	<script type="text/javascript"
		src="${context}/resources/js/custom-script.js"></script>

</body>
</html>