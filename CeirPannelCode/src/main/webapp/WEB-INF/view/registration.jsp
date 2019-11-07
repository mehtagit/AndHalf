<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="msapplication-tap-highlight" content="no">
    <meta name="description"
        content="Materialize is a Material Design Admin Template,It's modern, responsive and based on Material Design by Google. ">
    <meta name="keywords"
        content="materialize, admin template, dashboard template, flat admin template, responsive admin template,">
    <title>CEIR | Importer Portal</title>

    <link href="${context}/resources/js/plugins/data-tables/css/jquery.dataTables.min.css" type="text/css" rel="stylesheet"
        media="screen,projection">

    <!-- Favicons-->
    <!--<link rel="icon" href="images/favicon/favicon-32x32.png" sizes="32x32">-->
    <!-- Favicons-->
    <link rel="apple-touch-icon-precomposed" href="${context}/resources/images/favicon/apple-touch-icon-152x152.png">
    <!-- For iPhone -->
    <meta name="msapplication-TileColor" content="#00bcd4">
    <meta name="msapplication-TileImage" content="${context}/resources/images/favicon/mstile-144x144.png">
    <!-- For Windows Phone -->
    <link rel="stylesheet" href="${context}/resources/font/font-awesome/css/font-awesome.min.css">

    <!-- CORE CSS-->
    <!--<link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection">-->
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <link href="${context}/resources/css/style.css" type="text/css" rel="stylesheet" media="screen,projection">
    <!-- Custome CSS-->
    <link href="${context}/resources/css/custom/custom.css" type="text/css" rel="stylesheet" media="screen,projection">

    <!-- INCLUDED PLUGIN CSS ON THIS PAGE -->
    <link href="${context}/resources/js/plugins/prism/prism.css" type="text/css" rel="stylesheet" media="screen,projection">
    <link href="${context}/resources/js/plugins/perfect-scrollbar/perfect-scrollbar.css" type="text/css" rel="stylesheet"
        media="screen,projection">
    <link href="${context}/resources/js/plugins/chartist-js/chartist.min.css" type="text/css" rel="stylesheet" media="screen,projection">

    <!-- Country -->
    <!-- <script type="text/javascript" src="js/country.js"></script> -->

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

        .dropdown-content li>a,
        .dropdown-content li>span {
            color: #444;
        }

        .input-field>label {
            color: #444 !important;
        }

        select {
            background-color: transparent;
            border: none;
            border-bottom: 1px solid #9e9e9e;
            padding: 0;
            margin-top: 7px;
            ;
        }

        [type="radio"]:not(:checked),
        [type="radio"]:checked {
            opacity: 0;
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
				<form id="registrationForm"  method="post"
					action="${context}/registration" enctype="multipart/form-data" >
					<div class="card-panel"
						style="width: 90%; margin: auto; padding: 20px 5% 20px 5%;">
						<%-- <a href="${context}/"
							style="float: right; margin: -10px; margin-right: -20px;"><i
							class="fa fa-times boton" aria-hidden="true"></i></a> --%>
						<div class="row">
							<h5>Registration</h5>
							<hr> 
					<span style="color: red;">${msg}</span>		
							
							<div class="row">
								<div class="input-field col s12 m4 l4">
									<input type="text" name="firstName" id="firstName"
										 required="required" pattern="[A-Za-z]{0,20}" maxlength="20" title="Please enter alphabets upto 20 characters only"> <label for="firstName"
										class="center-align" >First Name <span class="star">*</span></label>
								</div>

								<div class="input-field col s12 m4 l4">
									<input type="text" name="middleName"
										class="form-control boxBorder boxHeight" id="middleName"
										 pattern="[A-Za-z]{0,20}" maxlength="20" title="Please enter alphabets upto 20 characters only"> <label for="middleName">Middle
										Name</label>
								</div>

								<div class="input-field col s12 m4 l4">
									<input type="text" name="lastName"
										class="form-control boxBorder boxHeight" id="lastName"
										pattern="[A-Za-z]{0,20}" maxlength="20" title="Please enter alphabets upto 20 characters only" required="required" title="Please enter alphabets upto 20 characters only"> <label for="lastName">Last
										Name <span class="star">*</span>
									</label>
								</div>

								<div class="input-field col s12 m6 l6">
									<input  type="text" required="required" name="passportNo"
										class="form-control boxBorder boxHeight"
										title="Please enter alphanumeric with special character upto 12 characters only"
										 id="passportNumber" maxlength="12"
										 pattern="[A-Za-z0-9\s]{0,12}"> <label for="passportNumber">National
										ID/Passport Number <span class="star">*</span>
									</label>
								</div>

								<div class="input-field col s12 m6 l6">
									<input type="email" name="email"
										class="form-control boxBorder boxHeight" id="email"
										 required="required"> <label for="email">Email <span
										class="star">*</span></label>
								</div>

								<div class="input-field col s12 m6 l6">
									<input type="text" name="phoneNo" maxlength="20"
										class="form-control boxBorder boxHeight" id="phone"
										pattern="[0-9]{8,20}" title="Please enter phone number between 8 to 20 characters only"  required="required"> <label for="phone">Phone
										Number <span class="star">*</span> 
									</label>
								</div>

								<div class="input-field col s12 m6 l6">
									<input type="text" name="companyName" required="required"
										class="form-control boxBorder boxHeight" id="company"
										 pattern="[A-Za-z\s]{0,50}"  maxlength="50"
										 title="Please enter alphanumeric upto 50 characters only"
										 > <label for="company">Company
										Name <span class="star">*</span>
									</label>
								</div>
							</div>

							<div class="row">
								<div class="input-field col s12 m12 l12">
									<input type="text" maxlength="200" pattern="[A-Za-z0-9\s]{0,200}" name="propertyLocation"
										class="form-control boxBorder boxHeight"
									title="Please enter alphanumeric with special character upto 200 characters only"	
										 id="address" required="required">
									<label for="address" >Address(Property Location) <span
										class="star">*</span></label>
								</div>

								<div class="input-field col s12 m6 l6">
									<input type="text" name="street" maxlength="20"
										class="form-control boxBorder boxHeight" id="streetNumber"
										 pattern="[A-Za-z0-9\s]{0,20}" required="required" 
									title="Please enter alphanumeric with special character upto 20 characters only"	 
										 > <label for="streetNumber">Street
										Number <span class="star">*</span>
									</label>
								</div>

								<div class="input-field col s12 m6 l6">
									<input type="text" name="locality" maxlength="30"
										class="form-control boxBorder boxHeight" id="locality"
										pattern="[A-Za-z0-9\s]{0,30}" required="required"
										title="Please enter alphanumeric with special character upto 30 characters only"
										> <label for="locality">Locality
										<span class="star">*</span>
									</label>
								</div>

								<div class="input-field col s12 m6 l6">
									<input type="text" name="province" maxlength="20"
										class="form-control  boxBorder boxHeight" id="province"
									pattern="[A-Za-z\s]{0,20}" required="required"  
									title="Please enter alphanumeric with special character upto 20 characters only"
									> <label for="province">Province<span
										class="star">*</span></label>
								</div>

								<div class="input-field col s12 m6 l6">
									<p
										style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">
										Country <span class="star">*</span>
									</p>
									<select id="country" name="country" class="browser-default" class="mySelect"
										style="padding-left: 0;" required>
									
										</select>
									<!-- <label for="country">Country <span class="star">*</span></label> -->
								</div>
							</div>

							<div class="row">
								<div class="col s12 m6 l6" style="margin-bottom: 20px;">
									<label for="vatNumber">VAT Registration <span
										class="star">*</span></label>
									<!-- <div class=" boxHeight">
										<input  value="1" class="with-gap" name="vatStatus" type="radio"
											onclick="document.getElementById('vatNumberField').style.display = 'block';">
										Yes <input value="0" class="with-gap" name="vatStatus" type="radio"
											style="margin-left: 20px;"
											onclick="document.getElementById('vatNumberField').style.display = 'none';"
											checked /> No
									</div> -->
									<div class=" boxHeight">
                                        <label><input value="1" class="with-gap" name="vatStatus" type="radio"
                                                onclick="document.getElementById('vatNumberField').style.display = 'block';">
                                            <span>Yes</span>
                                        </label>
                                        <label>
                                            <input value="0" class="with-gap" name="vatStatus" type="radio"
                                                style="margin-left: 20px;"
                                                onclick="document.getElementById('vatNumberField').style.display = 'none';"
                                                checked />
                                            <span>No</span>
                                        </label>
                                    </div>
								</div>

								<div class="input-field col s12 m6 l6" style="display: none;"
									id="vatNumberField">
									<input type="text" name="vatNo" maxlength="15"
										class="form-control boxBorder boxHeight" id="vatNumber"
										pattern="[A-Za-z0-9]{0,15}"
								title="Please enter alphanumeric upto 15 characters only"		
										> <label for="roleType">VAT
										Number <span class="star">*</span>
									</label>
								</div>
							</div>

							
                                
								
								<div class="input-field col s12 m6 l6">
									<p
										style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">
										Role Type <span class="star">*</span>
									</p> 
									<select multiple required name="roles"  >
										<option value="" disabled>Role Type <span
												class="star"></span></option>
								<c:forEach items="${usertypes}" var="usertype" >
								<c:if test="${usertype.usertypeName!='admin'}">
								<option  value="${usertype.id}">${usertype.usertypeName}</option>
								</c:if> 
								</c:forEach>	  
									<!-- 	<option value="Paid">Importer</option>
										<option value="NotPaid">Distributor</option>
										<option value="NotPaid">Retailer</option>
 -->									</select>
								</div>



								<div class="input-field col s12 m6 l6">
									<p
										style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">
										Type <span class="star">*</span>
									</p>
									<select class="browser-default" name="type" id="mySelect"
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
										<span>Select File</span> <input name="file" type="file" id="csvUploadFile"
											accept=".pfg">     
									</div>
									<div class="file-path-wrapper">
										<input class="file-path validate responsive-file-div"
											type="text">
									</div>
								</div>
								<br> <br>
								<!-- <p style="margin-left: 15px;"><a href="#">Download Sample Format</a></p> -->
							</div>

							<div class="row">
								<div class="input-field col s12 m6 l6">
									<input type="password" name="password"
										class="form-control boxBorder boxHeight" id="password"
									pattern="[A-Za-z0-9\s]{0,10}" maxlength="10"
									title="Please enter alphanumeric with special character upto 10 characters only"
									 required="required"> <label for="password">Password
										<span class="star">*</span>
									</label>
								</div>

								<div class="input-field col s12 m6 l6">
									<input type="password" name="rePassword"
									title="Please enter alphanumeric with special character upto 10 characters only"
										class="form-control boxBorder boxHeight" id="confirm_password"
										pattern="[A-Za-z0-9\s]{0,10}" maxlength="10"
                                    required="required"> <label for="rePassword">Retype
										Password <span class="star">*</span>
									</label>
								</div>
							</div>


							<div class="row">
								<div class="input-field col s12 m6 l6">
									<select class="browser-default" name="questionList[0].question" required>
									<option value="" disabled selected>Security Question
											1</option> 
									<c:forEach items="${questions}" var="question"> 
									<c:if test="${question.category=='1'}">
									<option value="${question.id}">${question.question}</option>
									</c:if>
									</c:forEach>

									</select>
								</div>

								<div class="input-field col s12 m6 l6">
									<input type="text" name="questionList[0].answer"
										class="form-control boxBorder boxHeight" id="SecurityAnswer"
										pattern="[A-Za-z]{0,50}" required="required"
										maxlength="50"
										title="Please enter alphabets upto 50 characters only"
										> <label for="SecurityAnswer">Answer
										<span class="star">*</span>
									</label>
								</div>
							</div>

							<div class="row">
								<div class="input-field col s12 m6 l6">
									<select name="questionList[1].question" class="browser-default" required>
									<option value="" disabled selected>Security Question
											2</option>
									<c:forEach items="${questions}" var="question"> 		
										<c:if test="${question.category=='2'}">
									<option value="${question.id}">${question.question}</option>
									</c:if>
									</c:forEach>
									</select>
								</div>

								<div class="input-field col s12 m6 l6">
									<input type="text" name="questionList[1].answer"
										class="form-control boxBorder boxHeight" id="SecurityAnswer"
										pattern="[A-Za-z]{0,50}"
										maxlength="50"
										title="Please enter alphabets upto 50 characters only"
										 required="required"> <label for="SecurityAnswer">Answer
										<span class="star">*</span>
									</label>
								</div>
							</div>

							<div class="row">
								<div class="input-field col s12 m6 l6">
									<select name="questionList[2].question" class="browser-default" required>
									<option value="" disabled selected>Security Question
											3</option>
									<c:forEach items="${questions}" var="question"> 		
										<c:if test="${question.category=='3'}">
									<option value="${question.id}">${question.question}</option>
									</c:if>     	
									</c:forEach>
											
									</select>
								</div>

								<div class="input-field col s12 m6 l6">
									<input type="text" name="questionList[2].answer"
										class="form-control boxBorder boxHeight" id="SecurityAnswer"
										title="Please enter alphabets upto 50 characters only"
										maxlength="50"
										pattern="[A-Za-z]{0,50}" required="required"> <label for="SecurityAnswer">Answer
										<span class="star">*</span>
									</label>
								</div>
								
													<div class="form-group form-actions">
						<span class="input-icon"> 
						<img id="captchaImage" src="${pageContext.request.contextPath }/captcha"><a href="" onclick="refreshCaptcha()">
						<i class="fa fa-refresh"></i></a>:
						<input type="text" name="captcha" required="required" style="margin-left: 5px;" placeholder="Enter Captcha">
						</span> 
					</div>
					
							</div>
						</div>

						<div class="row">
							<span> Required Field are marked with <span class="star">*</span></span>
							<div class="input-field col s12 center">
								<%-- <a href="${context}/verifyOtp" class="btn" id="btnSave"> Submit</a> --%>
								<button class="btn" id="btnSave" type="submit"
									style="margin-left: 10px;">submit</button>
								<button class="btn" style="margin-left: 10px;">cancel</button>
							</div>
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

	<!-- <div id="submitActivateDeactivate" class="modal">
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
	</div> -->
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
	 <!-- jQuery Library -->
    <!-- <script type="text/javascript" src="js/plugins/jquery-1.11.2.min.js"></script>-->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.1/jquery.min.js"></script>
    <!--materialize js-->
    <!--<script type="text/javascript" src="js/materialize.js"></script>-->
    <!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    <script type="text/javascript" src="${context}/resources/js/country.js"></script>
    <!--prism
    <script type="text/javascript" src="js/prism/prism.js"></script>-->
    <!--scrollbar-->
    <script type="text/javascript" src="${context}/resources/js/plugins/perfect-scrollbar/perfect-scrollbar.min.js"></script>
    <!-- chartist -->
    <!--<script type="text/javascript" src="js/plugins/chartist-js/chartist.min.js"></script>-->

    <!-- data-tables -->
    <script type="text/javascript" src="${context}/resources/js/plugins/data-tables/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="${context}/resources/js/plugins/data-tables/data-tables-script.js"></script>

    <!--plugins.js - Some Specific JS codes for Plugin Settings-->
    <!--<script type="text/javascript" src="js/plugins.js"></script>-->
    <!--custom-script.js - Add your own theme custom JS-->
    <script type="text/javascript" src="${context}/resources/js/custom-script.js"></script>
    <script> 
        $(document).ready(function () {
            $('.modal').modal();
            $('.dropdown-trigger').dropdown();
            $('select').formSelect();
        });
        populateCountries("country", "");

        function myFunction() {
            var x = document.getElementById("mySelect").value;
            if (x == 'Individual') {
                document.getElementById("uploadFile").style.display = "block";
                document.getElementById("passportNumberDiv").style.display = "block";
                //document.getElementById("companyName").style.display = "none";
                $('#companyName').style.display = "none";
            } else {

                document.getElementById("uploadFile").style.display = "none";
                document.getElementById("passportNumberDiv").style.display = "none";
            }

            if (x == 'Company', 'Organization', 'Government') {
                document.getElementById("companyName").style.display = "block";
            } else {

                document.getElementById("companyName").style.display = "none";
            }
        }
    </script>


	
</body>
</html>  	