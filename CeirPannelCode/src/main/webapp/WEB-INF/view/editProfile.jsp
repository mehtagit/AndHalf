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

    <!-- Datepicker -->
    <link href="${context}/resources/jquery.datepicker2.css" rel="stylesheet">

    <!-- new file -->
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${context}/resources/js/materialize.min.js"></script>


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
    <!-- countries -->
    <script type="text/javascript" src="${context}/resources/js/countries.js">
    </script>

    <style>
        .boton {
            color: #fff;
            font-size: 20px;
            background-color: #ff4081;
            padding: 1px 3px;
            border-radius: 999px;
        }

        textarea.materialize-textarea {
            padding: 0;
            padding-left: 10px;
        }

        .btn-flat {
            height: auto;
        }

        input[type="checkbox"] {
            display: none;
        }

        .dropdown-content li>a,
        .dropdown-content li>span {
            color: #444;
        }
    </style>

</head>

<body>
    

    <!-- //////////////////////////////////////////////////////////////////////////// -->

    <!-- START MAIN -->
    <div>
        <!-- START WRAPPER -->
        <div class="wrapper">

       
            <!-- END LEFT SIDEBAR NAV-->

            <!-- //////////////////////////////////////////////////////////////////////////// -->


            <!-- START CONTENT -->
            <section id="content" id="mainPage">
                <!--start container-->
                <div class="container">
                    <div class="section">
                        <form action="" id="registrationForm">
                            <div class="card-panel">
                                <!-- <a href="dashboard.html" style="float: right; margin: -10px;"><i
                                    class="fa fa-times boton" aria-hidden="true"></i></a> -->
                                <div class="row">
                                    <h5>Edit Information</h5>
                                    <hr>
                                    <div class="row">
                                        <div class="input-field col s12 m4 l4">
                                            <input type="text" name="firstName" id="firstName" maxlength="14">
                                            <label for="firstName" class="center-align">First Name <span
                                                    class="star">*</span></label>
                                        </div>

                                        <div class="input-field col s12 m4 l4">
                                            <input type="text" name="middleName"
                                                class="form-control boxBorder boxHeight" id="middleName" maxlength="14">
                                            <label for="middleName">Middle Name</label>
                                        </div>

                                        <div class="input-field col s12 m4 l4">
                                            <input type="text" name="lastName" class="form-control boxBorder boxHeight"
                                                id="lastName" maxlength="14">
                                            <label for="lastName">Last Name <span class="star">*</span></label>
                                        </div>

                                        <div class="input-field col s12 m6 l6">
                                            <input type="text" name="passportNumber"
                                                class="form-control boxBorder boxHeight" id="passportNumber"
                                                maxlength="14" readonly>
                                            <label for="passportNumber">National ID/Passport Number <span
                                                    class="star">*</span></label>
                                        </div>

                                        <div class="input-field col s12 m6 l6">
                                            <input type="text" name="email" class="form-control boxBorder boxHeight"
                                                id="email" maxlength="30">
                                            <label for="email">Email <span class="star">*</span></label>
                                        </div>

                                        <div class="input-field col s12 m6 l6">
                                            <input type="text" name="phone" class="form-control boxBorder boxHeight"
                                                id="phone" maxlength="10">
                                            <label for="phone">Phone Number <span class="star">*</span></label>
                                        </div>

                                        <div class="input-field col s12 m6 l6">
                                            <input type="text" name="company" class="form-control boxBorder boxHeight"
                                                id="company" maxlength="30">
                                            <label for="company">Company Name <span class="star">*</span></label>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="input-field col s12 m12 l12">
                                            <input type="text" name="address" class="form-control boxBorder boxHeight"
                                                id="address">
                                            <label for="address">Address(Property Location) <span
                                                    class="star">*</span></label>
                                        </div>

                                        <div class="input-field col s12 m6 l6">
                                            <input type="text" name="streetNumber"
                                                class="form-control boxBorder boxHeight" id="streetNumber"
                                                maxlength="30">
                                            <label for="streetNumber">Street Number <span class="star">*</span></label>
                                        </div>

                                        <div class="input-field col s12 m6 l6">
                                            <input type="text" name="locality" class="form-control boxBorder boxHeight"
                                                id="locality" maxlength="20">
                                            <label for="locality">Locality <span class="star">*</span></label>
                                        </div>

                                        <div class="input-field col s12 m6 l6">
                                            <input type="text" name="province" class="form-control boxBorder boxHeight"
                                                id="province" maxlength="20">
                                            <label for="province">Province<span class="star">*</span></label>
                                        </div>

                                        <div class="input-field col s12 m6 l6">
                                            <p style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">Country
                                                <span class="star">*</span></p>
                                            <select id="country" class="browser-default" class="mySelect"
                                                style="padding-left: 0;" required></select>
                                            <!-- <label for="country">Country <span class="star">*</span></label> -->
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col s12 m6 l6" style="margin-bottom: 20px;">
                                            <label for="vatNumber">VAT Registration <span class="star">*</span></label>
                                            <div class=" boxHeight">
                                                <input class="with-gap" name="group3" type="radio"
                                                    onclick="document.getElementById('vatNumberField').style.display = 'block';">
                                                Yes
                                                <input class="with-gap" name="group3" type="radio"
                                                    style="margin-left: 20px;"
                                                    onclick="document.getElementById('vatNumberField').style.display = 'none';"
                                                    checked />
                                                No
                                            </div>
                                        </div>

                                        <div class="input-field col s12 m6 l6" style="display: none;"
                                            id="vatNumberField">
                                            <input type="text" name="vatNumber" class="form-control boxBorder boxHeight"
                                                id="vatNumber" maxlength="16" readonly>
                                            <label for="roleType">VAT Number <span class="star">*</span></label>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="input-field col s12 m6 l6">
                                            <p style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">Role
                                                Type <span class="star">*</span></p>
                                            <select multiple required>
                                                <option value="" disabled selected>Role Type <span class="star"></span>
                                                </option>
                                                <option value="Paid">Importer</option>
                                                <option value="NotPaid">Distributor</option>
                                                <option value="NotPaid">Retailer</option>
                                            </select>
                                        </div>

                                        <div class="input-field col s12 m6 l6">
                                            <p style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">Type
                                                <span class="star">*</span></p>
                                            <select class="browser-default" id="mySelect" onchange="myFunction()"
                                                required>
                                                <option value="" disabled selected>Type</option>
                                                <option value="Individual"> Individual</option>
                                                <option value="Company">Company</option>
                                                <option value="Organization">Organization</option>
                                                <option value="Government">Government</option>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="row myRow" style="display: none;" id="uploadFile">
                                        <h6 class="file-upload-heading" style="margin-left: 15px;">Upload Nationality
                                            Information<span class="star">*</span></h6>
                                        <div class="file-field input-field col s12 m6" style="margin-top: 5px;">
                                            <div class="btn">
                                                <span>Select File</span>
                                                <input type="file" id="csvUploadFile" accept=".csv">
                                            </div>
                                            <div class="file-path-wrapper">
                                                <input class="file-path validate responsive-file-div" type="text">
                                            </div>
                                        </div><br><br>
                                        <!-- <p style="margin-left: 15px;"><a href="#">Download Sample Format</a></p> -->
                                    </div>

                                    <div class="row">
                                        <div class="input-field col s12 m6 l6">
                                            <input type="password" name="password"
                                                class="form-control boxBorder boxHeight" id="password" maxlength="14">
                                            <label for="password">Password <span class="star">*</span></label>
                                        </div>

                                        <div class="input-field col s12 m6 l6">
                                            <input type="password" name="rePassword"
                                                class="form-control boxBorder boxHeight" id="rePassword" maxlength="14">
                                            <label for="rePassword">Retype Password <span class="star">*</span></label>
                                        </div>
                                    </div>


                                    <div class="row">
                                        <div class="input-field col s12 m6 l6">
                                            <select class="browser-default" required>
                                                <option value="" disabled selected>Security Question 1</option>
                                                <option value="NotPaid">What is your childhood name?</option>
                                                <option value="NotPaid">Where is your birth place?</option>
                                                <option value="NotPaid">What is your favourite movie?</option>
                                                <option value="NotPaid">What is your favourite sports team?</option>
                                                <option value="NotPaid">What is your favourite pet’s name?</option>
                                            </select>
                                        </div>

                                        <div class="input-field col s12 m6 l6">
                                            <input type="text" name="SecurityAnswer"
                                                class="form-control boxBorder boxHeight" id="SecurityAnswer"
                                                maxlength="50">
                                            <label for="SecurityAnswer">Answer <span class="star">*</span></label>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="input-field col s12 m6 l6">
                                            <select class="browser-default" required>
                                                <option value="" disabled selected>Security Question 2</option>
                                                <option value="NotPaid">What is your childhood name?</option>
                                                <option value="NotPaid">Where is your birth place?</option>
                                                <option value="NotPaid">What is your favourite movie?</option>
                                                <option value="NotPaid">What is your favourite sports team?</option>
                                                <option value="NotPaid">What is your favourite pet’s name?</option>
                                            </select>
                                        </div>

                                        <div class="input-field col s12 m6 l6">
                                            <input type="text" name="SecurityAnswer"
                                                class="form-control boxBorder boxHeight" id="SecurityAnswer"
                                                maxlength="50">
                                            <label for="SecurityAnswer">Answer <span class="star">*</span></label>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="input-field col s12 m6 l6">
                                            <select class="browser-default" required>
                                                <option value="" disabled selected>Security Question 3</option>
                                                <option value="NotPaid">What is your childhood name?</option>
                                                <option value="NotPaid">Where is your birth place?</option>
                                                <option value="NotPaid">What is your favourite movie?</option>
                                                <option value="NotPaid">What is your favourite sports team?</option>
                                                <option value="NotPaid">What is your favourite pet’s name?</option>
                                            </select>
                                        </div>

                                        <div class="input-field col s12 m6 l6">
                                            <input type="text" name="SecurityAnswer"
                                                class="form-control boxBorder boxHeight" id="SecurityAnswer"
                                                maxlength="50">
                                            <label for="SecurityAnswer">Answer <span class="star">*</span></label>
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <span> Required Field are marked with <span class="star">*</span></span>
                                    <div class="input-field col s12 center">
                                        <!-- <a href="index.html" class="btn" id="btnSave"> Submit</a> -->
                                        <a href="#updateInformation" class="modal-trigger btn" type="button"
                                            id="btnUpdate">Update</a>
                                        <a href="${context}/Home" class="btn">Cancel</a>
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

            <div id="updateInformation" class="modal" style="width: 40%;">
                <div class="modal-content">

                    <div class="row">
                        <form action="">
                            <h5 style="text-align: -webkit-center;">Enter</h5>
                            <div class="input-field col s12">

                                <label for="newPassword" style="color: #000; font-size: 12px;">Username</label>
                                <input type="text" id="newPassword" class="" maxlength="10" />
                            </div>

                            <div class="input-field col s12">

                                <label for="confirmPassword" style="color: #000; font-size: 12px;">Password</label>
                                <input type="text" class="" id="confirmPassword" maxlength="10" />
                            </div>
                        </form>
                    </div>
                    <div class="row">
                        <div class="input-field col s12 center">
                            <a href="#updateInformationMsg" class="btn modal-trigger modal-close">Submit</a>
                            <button class="btn modal-close" style="margin-left: 10px;">Cancel</button>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Modal End -->


            <!-- Modal 1 start   -->

            <div id="updateInformationMsg" class="modal">
                <div class="modal-content">
                    <h6>Update Information</h6><hr>
                    <div class="row">
                        <h6>Your request has been successfully updated</h6>
                    </div>
                    <div class="center">
                        <a href="${context}/Home" class="btn">ok</a>
                    </div>
                </div>
            </div>

            <!-- Modal End -->

            <!-- Modal 2 start   -->

            <div id="errorMessage" class="modal">
                <div class="modal-content">
                    <h6>Error</h6><hr>
                    <div class="row">
                        <h6>Enter a valid username and password</h6>
                    </div>
                    <div class="row">
                        <div class="input-field col s12 center">
                            <a href="#updateInformation" class="btn modal-trigger modal-close">ok</a>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Modal End -->



            <!-- Modal 4 start   -->

            <div id="manageAccount" class="modal">
                <button type="button" class=" modal-action modal-close waves-effect waves-green btn-flat right"
                    data-dismiss="modal">&times;</button>
                <div class="modal-content">
                    <h6>Manage Account</h6>
                    <hr>
                    <p>Request CEIR ADMIN to </p>
                    <div class="row" style="height: 30px;">
                        <p><label style="margin-right: 50px "> <input type="radio"
                                    onclick="document.getElementById('calender').style.display = 'none';"
                                    name="stolen"><span>
                                    Deactivate</span></label>Permanently delete the account, you will not login into the
                            portal.</p>
                    </div>
                    <div class="row" style="height: 30px;">
                        <p><label style="margin-right: 67px "> <input type="radio"
                                    onclick="document.getElementById('calender').style.display = 'block';"
                                    name="stolen"><span> Disable</span></label>All the action will be disabled, only
                            view option will be available</p>
                    </div>
                    <div class="input-field col s12 center">
                        <button class="btn modal-trigger modal-close" data-target="manageAccountSubmit">Submit</button>
                        <a href="consignment.html" class="btn" style="margin-left: 10px;">Cancel</a>
                    </div>
                </div>
            </div>
            <!-- Modal End -->

            <!-- Modal 4 start   -->

            <div id="manageAccountSubmit" class="modal">
                <button type="button" class=" modal-action modal-close waves-effect waves-green btn-flat right"
                    data-dismiss="modal">&times;</button>
                <div class="modal-content">
                    <h6>The request has been successfully registered with CEIR Admin. Please find confirmation over
                        registered mail in 2 to 3 working days.</h6>


                    <div class="input-field col s12 center">
                        <button class="btn modal-close">ok</button>
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
                                <a href="index.html" class="btn" type="submit" name="add_user" id="add_user">yes</a>
                                <a href="#activateDeactivate" class="modal-close modal-trigger btn"
                                    style="margin-left: 10px;">no</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Modal End -->


            <!-- Modal 4 start   -->

            <div id="changePassword" class="modal" style="width: 40%;">
                <div class="modal-content">
                    <div class="row">
                        <h5 style="text-align: -webkit-center;">Change Password</h5>

                        <div class="col s1"><i class="fa fa-lock" aria-hidden="true"
                                style="font-size: 30px; margin-top: 12px; color: #ff4081;"></i></div>
                        <div class="input-field col s11">
                            <input type="text" id="oldPassword" class="validate" maxlength="10" />
                            <label for="oldPassword" class="center-align" style="color: #000; font-size: 12px;"> Old
                                Password </label>
                            <div class="password"></div>
                        </div>

                        <div class="col s1">
                            <span class="fa-passwd-reset fa-stack" style="margin-top: 12px; color: #ff4081;">
                                <i class="fa fa-undo fa-stack-2x"></i>
                                <i class="fa fa-lock fa-stack-1x"></i>
                            </span></div>
                        <div class="input-field col s11">

                            <label for="newPassword" style="color: #000; font-size: 12px;">New Password</label>
                            <input type="text" id="newPassword" class="" maxlength="10" />
                        </div>

                        <div class="col s1"><i class="fa fa-check-square-o" aria-hidden="true"
                                style="font-size: 28px; margin-top: 12px; color: #ff4081;;"></i></div>
                        <div class="input-field col s11">

                            <label for="confirmPassword" style="color: #000; font-size: 12px;">Confirm Password</label>
                            <input type="text" class="" id="confirmPassword" maxlength="10" />
                        </div>
                    </div>
                    <div class="row" style="margin-top: 30px;">
                        <div class="input-field col s12 m12 l12 center">
                            <a href="index.html" class="btn" type="button" id="save" style="width: 100%;">Save</a>
                        </div>
                    </div>
                </div>
            </div>
</div>
</div>
            <!-- Modal End -->

            <!-- ================================================
    Scripts
    ================================================ -->
 <script type="text/javascript" src="${context}/resources/js/plugins/jquery-1.11.2.min.js"></script>
            <script>
                $(document).ready(function () {
                    $('.modal').modal();
                });
            </script>

            <script>
                $('#data-table-simple').dataTable({
                    "lengthChange": false,
                });
                  </script>

            <script>
                populateCountries(
                    "country",
                );
            </script>

            <script>
                $(document).ready(function () {
                    $('.datepicker').datepicker();
                });

                $('.datepicker').on('mousedown', function (event) {
                    event.preventDefault();
                })
            </script>

            <script>
                $('#data-table-simple').DataTable({
                    responsive: true
                });
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

            <script src="https://code.jquery.com/jquery-1.12.4.min.js"
                integrity="sha384-nvAa0+6Qg9clwYCGGPpDQLVpLNn0fRaROjHqs13t4Ggj3Ez50XnGQqc/r8MhnRDZ"
                crossorigin="anonymous">
            </script>
            <script src="${context}/resources/jquery.datepicker2.js"></script>
            <!-- <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script> -->

            <!-- jQuery Library -->
           
            <!--materialize js-->
            <script type="text/javascript" src="${context}/resources/js/materialize.js"></script>
            <!--prism
    <script type="text/javascript" src="js/prism/prism.js"></script>-->
            <!--scrollbar-->
            <script type="text/javascript" src="${context}/resources/js/plugins/perfect-scrollbar/perfect-scrollbar.min.js"></script>
            <!-- chartist -->
            <script type="text/javascript" src="${context}/resources/js/plugins/chartist-js/chartist.min.js"></script>

            <!-- data-tables -->
            <script type="text/javascript" src="${context}/resources/js/plugins/data-tables/js/jquery.dataTables.min.js"></script>
            <script type="text/javascript" src="${context}/resources/js/plugins/data-tables/data-tables-script.js"></script>

            <!-- date picker -->
            <script type="text/javascript" src="${context}/resources/js/materialize-plugins/date_picker/picker.date.js"></script>
            <script type="text/javascript" src="${context}/resources/js/materialize-plugins/date_picker/picker.js"></script>

            <!--plugins.js - Some Specific JS codes for Plugin Settings-->
            <script type="text/javascript" src="${context}/resources/js/plugins.js"></script>
            <!--custom-script.js - Add your own theme custom JS-->
            <script type="text/javascript" src="${context}/resources/js/custom-script.js"></script>



</html>




<a class="btn-flat dropdown-button waves-effect waves-light white-text profile-btn" href="#"
    data-activates="profile-dropdown" style="margin-top:-5px; height:63px;"></a>
<ul id="profile-dropdown" class="dropdown-content" style="margin-top: 51px;">
    <li><a href="javascript:void(0)" id=""><i class="mdi-action-settings"></i>Change Password</a></li>
    <li class="divider"></li>
    <li><a href="javascript:void(0)" id=""><i class="mdi-hardware-keyboard-tab"></i>
            Logout</a></li>