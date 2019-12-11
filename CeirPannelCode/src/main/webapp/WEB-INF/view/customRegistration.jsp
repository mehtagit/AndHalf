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
    <title>CEIR | Custom Portal</title>
    <link href="${context}/resources/js/plugins/data-tables/css/jquery.dataTables.min.css" type="text/css" rel="stylesheet"
        media="screen,projection">
    <link rel="apple-touch-icon-precomposed" href="${context}/resources/images/favicon/apple-touch-icon-152x152.png">
    <!-- For iPhone -->
    <meta name="msapplication-TileColor" content="#00bcd4">
    <meta name="msapplication-TileImage" content="images/favicon/mstile-144x144.png">
    <!-- For Windows Phone -->
    <link rel="stylesheet" href="${context}/resources/font/font-awesome/css/font-awesome.min.css">

    <!-- CORE CSS-->
    <link href="${context}/resources/css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection">
    <link href="${context}/resources/css/style.css" type="text/css" rel="stylesheet" media="screen,projection">
    <!-- Custome CSS-->
    <link href="${context}/resources/css/custom/custom.css" type="text/css" rel="stylesheet" media="screen,projection">

    <!-- INCLUDED PLUGIN CSS ON THIS PAGE -->
    <link href="${context}/resources/js/plugins/prism/prism.css" type="text/css" rel="stylesheet" media="screen,projection">
    <link href="${context}/resources/js/plugins/perfect-scrollbar/perfect-scrollbar.css" type="text/css" rel="stylesheet"
        media="screen,projection">
    <link href="${context}/resources/js/plugins/chartist-js/chartist.min.css" type="text/css" rel="stylesheet" media="screen,projection">

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

        .dropdown-content li>a,
        .dropdown-content li>span {
            color: #444;
        }

        h6 {
            font-size: 1rem;
            line-height: 110%;
            margin: 0rem 0 0.4rem 0;
            margin-top: -20px !important;
        }

        .file-upload-heading {
            margin-left: 0;
        }

        select {
            height: 2.2rem !important;
        }

        label {
            font-size: 0.8rem;
        }
    </style>
<script>
var contextpath = "${context}";
</script>
</head>

<body>
    
    <!--  Scripts
    ================================================ --> 
	 <!-- jQuery Library -->
    <!-- <script type="text/javascript" src="js/plugins/jquery-1.11.2.min.js"></script>-->
  <script type="text/javascript" src="${context}/resources/js/plugins/jquery-1.11.2.min.js"></script>
       <!-- ajax js -->
    <script type="text/javascript" src="${context}/resources/ajax/Registration.js"></script>
      <script type="text/javascript" src="${context}/resources/ajax/Profile.js"></script>
    <!--materialize js-->
    <!--<script type="text/javascript" src="js/materialize.js"></script>-->
    <!-- Compiled and minified JavaScript -->
    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    <script type="text/javascript" src="${context}/resources/js/materialize.js"></script>
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
                                                                                            
    <!-- //////////////////////////////////////////////////////////////////////////// -->
                      
    <!-- START CONTENT -->
    <section id="content" id="mainPage">
        <!--start container--> 
        <div class="container">
            <div class="section">
                <form id="registrationForm" onsubmit="return saveOperatorRegistration()">
                    <div class="card-panel registration-form" >
                        <div class="row">
                            <h5>Registration</h5>
                            <hr>  
                              <input type="hidden" id="usertypeId" value="${usertypeId}">
                               <input type="hidden" id="type" value="Organization">
                            <div class="row">
                              	<div class="input-field col s12 m4 l4">
									<input type="text" name="firstName" id="firstName"
										required="required" pattern="[A-Za-z]{0,20}" maxlength="20"
										title="Please enter alphabets upto 20 characters only">
									<label for="firstName" class="center-align">First Name
										<span class="star">*</span>
									</label>
								</div>

								<div class="input-field col s12 m4 l4">
									<input type="text" name="middleName"
										class="form-control boxBorder boxHeight" id="middleName"
										pattern="[A-Za-z]{0,20}" maxlength="20"
										title="Please enter alphabets upto 20 characters only">
									<label for="middleName">Middle Name</label>
								</div>
								

								<div class="input-field col s12 m4 l4">
									<input type="text" name="lastName"
										class="form-control boxBorder boxHeight" id="lastName"
										pattern="[A-Za-z]{0,20}" maxlength="20"
										title="Please enter alphabets upto 20 characters only"
										required="required"
										title="Please enter alphabets upto 20 characters only">
									<label for="lastName">Last Name <span class="star">*</span>
									</label>
								</div>
								
								</div>

                            <div class="row">
                               <div class="input-field col s12 m12 l12">
									<input type="text" maxlength="200"
										pattern="[A-Za-z0-9\s]{0,200}" name="propertyLocation"
										class="form-control boxBorder boxHeight"
										title="Please enter alphanumeric with special character upto 200 characters only"
										id="propertyLocation" required="required"> <label
										for="propertyLocation">Address(Property Location) <span
										class="star">*</span></label>
								</div>
								
                                <div class="input-field col s12 m6 l6">
									<input type="text" name="street" maxlength="20"
										class="form-control boxBorder boxHeight" id="street"
										pattern="[A-Za-z0-9\s]{0,20}" required="required"
										title="Please enter alphanumeric with special character upto 20 characters only">
									<label for="street">Street Number <span class="star">*</span>
									</label>
								</div>

								<div class="input-field col s12 m6 l6">
									<input type="text" name="locality" maxlength="30"
										class="form-control boxBorder boxHeight" id="locality"
										pattern="[A-Za-z0-9\s]{0,30}" required="required"
										title="Please enter alphanumeric with special character upto 30 characters only">
									<label for="locality">Locality <span class="star">*</span>
									</label>
								</div>

                                <div class="input-field col s12 m6 l6">
                                    <p style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">Country <span
                                            class="star">*</span></p>
                                    <select id="country" class="browser-default" class="mySelect"
                                        style="padding-left: 0;" required></select>
                                </div>

                                <div class="input-field col s12 m6 l6">
                                    <p style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">Province <span
                                            class="star">*</span></p>
                                    <select id="state" class="browser-default" class="mySelect" style="padding-left: 0;"
                                        required></select>
                                </div>
                            </div>

                            <div class="row">

                                <div class="input-field col s12 m6 l6">
                                  <input type="text" name="passportNo" required="required"
										class="form-control boxBorder boxHeight" 
										title="Please enter alphanumeric with special character upto 12 characters only"
										id="passportNo" maxlength="12" pattern="[A-Za-z0-9\s]{0,12}" />
                                    <label for="passportNo">National ID <span class="star">*</span></label>
                                </div>

                                <div class="file-field input-field col s12 m6 l6">
                                    <div class="btn">
                                        <span>Upload National ID *</span>
                                        <input id="NationalIdImage" type="file" placeholder="Upload National ID Image">
                                    </div>
                                    <div class="file-path-wrapper">
                                        <input class="file-path validate" type="text"
                                            placeholder="Upload National ID Image">
                                    </div>
                                </div>

                                <div class="file-field input-field col s12 m6 l6">
                                    <div class="btn">
                                        <span>Upload Photo *</span>
                                        <input id="photo" type="file" placeholder="Upload Photo">
                                    </div>
                                    <div class="file-path-wrapper">
                                        <input class="file-path validate" type="text" placeholder="Upload Photo">
                                    </div>
                                </div>

                                <div class="input-field col s12 m6 l6">
                                    <input type="text" name="employeeID" required="required" id="employeeId" maxlength="30">
                                    <label for="employeeId">Employee ID <span class="star">*</span></label>
                                </div>

                                <div class="file-field input-field col s12 m6 l6">
                                    <div class="btn">
                                        <span>Upload ID Card *</span>
                                        <input id="idCard" type="file" placeholder="Upload Photo">
                                    </div>  
                                    <div class="file-path-wrapper">
                                        <input class="file-path validate" type="text"
                                            placeholder="Upload ID Card Image">
                                    </div>
                                </div>

                                <div class="col s12 m6 l6">
                                    <label>Nature Of Employment <span class="star">*</span></label>
                                    <select id="natureOfEmployment" class="browser-default" required>
                                        <option value="" disabled selected>Nature Of Employment</option>
                                        <option value="Permanent">Permanent</option>
                                        <option value="Temporary">Temporary</option>
                                        <option value="Contract">Contract</option>
                                    </select>
                                </div>
                            </div>

                            <div class="row">

                                <div class="input-field col s12 m6 l6">
                                    <input type="text"  name="designation" required="required" id="designation" maxlength="30">
                                    <label for="designation">Designation and Title <span class="star">*</span></label>
                                </div>

                                <div class="input-field col s12 m6 l6">
                                    <input type="text" name="reportingAuthorityName" id="authorityName"
                                        maxlength="30">
                                    <label for="authorityName">Reporting Authority Name</label>
                                </div>

                                <div class="input-field col s12 m6 l6">
                                    <input type="email" name="authorityEmail"
										class="form-control boxBorder boxHeight" id="authorityEmail"
										>                                     
                                    <label for="authorityEmail">Reporting Authority Email ID</label>
                                </div>

                                <div class="input-field col s12 m6 l6">
                                    <input type="text" name="authorityPhoneNo" id="authorityPhoneNo"
                                        maxlength="20"
                                        pattern="[0-9]{8,20}"
										title="Please enter phone number between 8 to 20 characters only"
										 >
                                    <label for="authorityPhoneNo">Reporting Authority Contact Number</label>
                                </div>

                                <div class="input-field col s12 m6 l6">
                                    <input type="email" name="email" required="required" id="email" maxlength="30">
                                    <label for="email">Email <span class="star">*</span></label>
                                </div>

                                <div class="input-field col s12 m6 l6">
                                   <input type="text" name="phoneNo" maxlength="20"
										class="form-control boxBorder boxHeight" id="phoneNo"
										pattern="[0-9]{8,20}"
										title="Please enter phone number between 8 to 20 characters only"
										required="required">
                                    <label for="phoneNo">Contact Number <span class="star">*</span></label>
                                </div>


                            </div>





                            <div class="row">
                                <div class="input-field col s12 m6 l6">
                                    <input type="password" name="password"
									class="form-control boxBorder boxHeight" id="password"
									pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$" maxlength="10" min="8"
									title="Please enter atleast one numeric char, one alphabet, one special character and must be of minumum 8 length"
									required="required">
                                    <label for="password">Password <span class="star">*</span></label>
                                </div>

                                <div class="input-field col s12 m6 l6">
                                    <input type="password" name="rePassword" 
										title="Please enter atleast one numeric char, one alphabet, one special character and must be of minumum 8 length"
									class="form-control boxBorder boxHeight" id="confirm_password"
									pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$"  maxlength="10" min="8"
									required="required">
									
                                    <label for="confirm_password">Retype Password <span class="star">*</span></label>
                                </div>
                            </div>


                   <div class="row securityQuestionDiv">
							<div class="input-field col s12 m6 l6">
								<p
									style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">
									Security Question 1 <span class="star">*</span>
								</p>
								<input type="hidden" class="id" id="id0"> <select
									class="browser-default questionId" id="questionId0"
									name="questionId" required>
									<option value="" disabled selected>Security Question 1</option>
									<%--<c:forEach items="${questions}" var="question"> 
									<c:if test="${question.category=='1'}">
									<option value="${question.id}">${question.question}</option>
									</c:if>
									</c:forEach>  --%>
								</select>
							</div>

							<div class="input-field col s12 m6 l6">
								<input type="text" placeholder="" name="answer"
									class="form-control boxBorder boxHeight answer" id="answer0"
									pattern="[A-Za-z]{0,50}" required="required" maxlength="50"
									title="Please enter alphabets upto 50 characters only">
								<label>Answer <span class="star">*</span>
								</label>
							</div>
						</div>
						<div class="row securityQuestionDiv">
							<div class="input-field col s12 m6 l6">
								<p
									style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">
									Security Question 2 <span class="star">*</span>
								</p>
								<input type="hidden" class="id" id="id1" /> <select
									name="questionId" id="questionId1"
									class="browser-default questionId" required>
									<option value="" disabled selected>Security Question 2</option>
									<%--<c:forEach items="${questions}" var="question"> 		
										<c:if test="${question.category=='2'}">
									<option value="${question.id}">${question.question}</option>
									</c:if>
									</c:forEach> --%>
								</select>
							</div>

							<div class="input-field col s12 m6 l6">
								<input type="text" placeholder="" name="answer"
									class="form-control boxBorder boxHeight answer" id="answer1"
									pattern="[A-Za-z]{0,50}" maxlength="50"
									title="Please enter alphabets upto 50 characters only"
									required="required"> <label>Answer <span
									class="star">*</span>
								</label>
							</div>
						</div>

						<div class="row securityQuestionDiv">
							<div class="input-field col s12 m6 l6">

								<p
									style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">
									Security Question 3<span class="star">*</span>
								</p>
								<input type="hidden" class="id" id="id2" /> <select
									name="questionId" id="questionId2"
									class="browser-default questionId" required>
									<option value="" disabled selected>Security Question 3</option>
								</select>
							</div>

							<div class="input-field col s12 m6 l6">
								<input type="text" name="answer" placeholder=""
									class="form-control boxBorder boxHeight answer" id="answer2"
									title="Please enter alphabets upto 50 characters only"
									maxlength="50" pattern="[A-Za-z]{0,50}" required="required">
								<label>Answer <span class="star">*</span>
								</label>
							</div>
						</div>
						
						<div class="form-group form-actions">
							<span class="input-icon"> <img id="captchaImage"
								src="${context}/captcha">
							<button style="background: none; border: none; outline: none;"
									type="button" onclick="refreshCaptcha('captchaImage')">
									<i class="fa fa-refresh"></i>
								</button> <%-- <img src="${context}/captcha"" id="captchaImage">
						 <br>
                           <input type="button" onclick="refreshCaptcha('captchaImage')"> --%>
								<div class="input-field col s12 m6 l12">
									<input autocomplete="off" type="text" name="captcha"
										class="form-control boxBorder boxHeight" id="captcha"
										required="required"> <label for="address">Enter
										your captcha <span class="star">*</span>
									</label>
								</div>

							</span>
						</div>
						
						
                        </div>

                        <div class="row">
                            <span> Required Field are marked with <span class="star">*</span></span>
                            <div class="input-field col s12 center">
                            	<button class="btn" id="btnSave" 
									type="submit" style="margin-left: 10px;">submit</button>
										<a href="${context}/" class="btn" style="margin-left: 10px;">cancel</a>
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
        <button type="button" class=" modal-action modal-close waves-effect waves-green btn-flat right"
            data-dismiss="modal">&times;</button>
        <div class="modal-content">
            <!-- <h4 class="header2 pb-2">User Info</h4> -->

            <div class="row">
                <h6>Verify OTP</h6>
                <hr>
                <p>A text message and an E-Mail has been sent to your registered Phone number and E-mail. Please Verify
                    !</p>
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
        <button type="button" class=" modal-action modal-close waves-effect waves-green btn-flat right"
            data-dismiss="modal">&times;</button>
        <div class="modal-content">

            <div class="row">
                <h6>The request has been successfully registered with CEIR Admin. Please find the confirmation over
                    registered
                    mail < mail@mail.com> in 2 to 3 working days.</h6>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <div class="input-field col s12 center">
                        <button class="modal-close waves-effect waves-light btn" style="margin-left: 10px;"
                            type="submit" name="add_user" id="add_user">Cancel</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Modal End -->

    <!-- Modal 2 start   -->

    <div id="cancelActivateDeactivate" class="modal">
        <button type="button" class=" modal-action modal-close waves-effect waves-green btn-flat right"
            data-dismiss="modal">&times;</button>
        <div class="modal-content">

            <div class="row">
                <h6>Do you want to cancel the request?</h6>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <div class="input-field col s12 center">
                        <a href="userRegistration.html" class="btn" type="submit" name="add_user" id="add_user">yes</a>
                        <a href="#activateDeactivate" class="modal-close modal-trigger btn"
                            style="margin-left: 10px;">no</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
     <!-- START MAIN -->
    <div id="">
        <!-- START WRAPPER -->
        <div class="wrapper">
            <!-- START CONTENT -->
            <section id="content">
                <!--start container-->
                <div class="container">
                    <div class="section">
                        <div id="otpMsgModal" class="modal" style="width: 40%; margin-left: 30%;">
                            <h5 class="center">Verify OTP</h5>
                            <p style="padding:10px;" class="center" id="otpMsg"></p>

                            <a href="#otpVerification" class="btn modal-trigger"
                               style="margin-left: 3%;width: 94%;background-color: #ff4081;margin-bottom:30px;" >verify otp</a>
 
                        </div>
                    </div>
                </div>
                <!--end container-->
            </section>
            <!-- END CONTENT -->
        </div>
    </div>
    
    <div id="otpMessage" class="modal">
        <button type="button" class="modal-action modal-close waves-effect waves-green btn-flat right"
            data-dismiss="modal">&times;</button>
        <div class="modal-content">
            <!-- <h4 class="header2 pb-2">User Info</h4> -->

            <div class="row">  
                <h6 id="otpResponse"></h6>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <a href="${context}/login" class="btn">ok</a>
                </div>
            </div>
        </div>
    </div>
    
    
    
    <!-- modal start -->

    <div id="otpVerification" class="modal" style="width: 40%;">
        <!-- <button type="button" class=" modal-action modal-close waves-effect waves-green btn-flat right"
            data-dismiss="modal">&times;</button> -->
        <div class="modal-content">  
                <form id="verifyOtpForm" onsubmit="return verifyOtp()">
                        <h5 class="center">Enter OTP</h5>  
                        <p class="center" id="resendOtp" style="display: none;"></p>
                        <input type="hidden" id="userid"  name="userid" value="${userId}">
                        <div class="row">          
                            <div class="input-field col s12 m12">
                                <input type="text" name="emailOtp" maxlength="6"
                               
										title="Please enter number characters only"
                                  required="required" id="emailOtp" placeholder="Enter OTP of Email"/>
                            </div> 
                   
                            <div class="input-field col s12 m12">
                                <input type="text" name="phoneOtp" maxlength="6" 
                                
										title="Please enter number characters only" 
                                required="required" id="phoneOtp" placeholder="Enter OTP of Phone"/>
                            </div>
                        </div>

                        <a href="#" onclick="resendOtp(); document.getElementById('resendOtp').style.display ='block';" class="right">Resend OTP</a>

                        <button id="otpVerifyBtn" type="submit"  class="btn" style="width: 100%; margin-top: 20px; margin-bottom: 20px;">Done</button>
                    </form>
        </div>
    </div>
    
    <!-- Modal End -->
    <!-- ================================================
    Scripts
    ================================================ -->
<!-- Modal End -->
    <script>
        $(document).ready(function () {
            $('.modal').modal();
            questionDataByCategory();
            
        });   

        $('.dropdown-trigger').dropdown();

       /*  $(document).ready(function () {
            $('select').material_select();
        }); */
        var password = document.getElementById("password")
        , confirm_password = document.getElementById("confirm_password");

      function validatePassword(){
        if(password.value != confirm_password.value) {
          confirm_password.setCustomValidity("Passwords Don't Match");
        } else {
          confirm_password.setCustomValidity('');
        }
      }

      password.onchange = validatePassword;
      confirm_password.onkeyup = validatePassword;
    </script>

    <script>
        populateCountries(
            "country",
            "state",
        );
        $("#country").val("Cambodia");
        populateStates(
            "country",
            "state",
        );
    </script>

    <script>
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