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
    <link rel="apple-touch-icon-precomposed" href="images/favicon/apple-touch-icon-152x152.png">
    <!-- For iPhone -->
    <meta name="msapplication-TileColor" content="#00bcd4">
    <meta name="msapplication-TileImage" content="images/favicon/mstile-144x144.png">
    <!-- For Windows Phone -->
    <link rel="stylesheet" href="font/font-awesome/css/font-awesome.min.css">

    <!-- CORE CSS-->
    <link href="${context}/resources/css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection">
    <link href="${context}/resources/css/style.css" type="text/css" rel="stylesheet" media="screen,projection">
    <!-- Custome CSS-->
    <link href="css/custom/custom.css" type="text/css" rel="stylesheet" media="screen,projection">

    <!-- INCLUDED PLUGIN CSS ON THIS PAGE -->
    <link href="${context}/resources/js/plugins/prism/prism.css" type="text/css" rel="stylesheet" media="screen,projection">
    <link href="${context}/resources/js/plugins/perfect-scrollbar/perfect-scrollbar.css" type="text/css" rel="stylesheet"
        media="screen,projection">
    <link href="${context}/resources/js/plugins/chartist-js/chartist.min.css" type="text/css" rel="stylesheet" media="screen,projection">

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

        /* @media only screen and (min-width: 601px) .row .col.m6 {
            margin-top: 0;
            margin-bottom: 0;
            height: 40px;
        } */

        input[type=text] {
            height: 35px;
            margin: 0 0 5px 0;
        }
    </style>

</head>

<body>

    <!-- //////////////////////////////////////////////////////////////////////////// -->



    <!-- //////////////////////////////////////////////////////////////////////////// -->

    <!-- START MAIN -->
    <div id="">
        <!-- START WRAPPER -->
        <div class="wrapper">



            <!-- //////////////////////////////////////////////////////////////////////////// -->

            <!-- START CONTENT -->
            <section id="content">
                <!--start container-->
                <div class="container">
                    <div class="section">
                        <div class="card-panel" style="width: 40%; margin-left: 30%; margin-top: 10vh;">
                            <h5 class="center">Verify OTP</h5>
                            <!-- <img src="images/otpImage.png" class=""
                                style="width: 80px; display: block; margin:auto;"> -->
                            <!-- <p class="center" style="margin-top: 20px;">Enter One Time Password (OTP)</p> -->
                            <p class="center">${msg}<!-- The text and and an e-mail with OTP details has been sent to your registered Phone Number and E-Mail ID --></p>
                            

                            <a href="#otpVerification" class="btn modal-trigger"
                                style="width: 100%; margin-top: 20px; margin-bottom: 20px;">verify otp</a>

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


    <!-- modal start -->

    <div id="otpVerification" class="modal" style="width: 40%;">
        <!-- <button type="button" class=" modal-action modal-close waves-effect waves-green btn-flat right"
            data-dismiss="modal">&times;</button> -->
        <div class="modal-content">
                <form action="">
                        <h5 class="center">Enter OTP</h5>
                        <p class="center" id="resendOtp" style="display: none;">A text message and e-mail with OTP has been sent</p>
                        <div class="row">
                            <div class="input-field col s12 m12">
                                <input type="text" name="SupplierID" id="SupplierID" placeholder="Enter OTP of Email"/>
                            </div>

                            <div class="input-field col s12 m12">
                                <input type="text" name="SupplierID" id="SupplierID" placeholder="Enter OTP of Phone"/>
                            </div>
                        </div>

                        <a href="#" onclick="document.getElementById('resendOtp').style.display ='block';" class="right">Resend OTP</a>

                        <a href="#otpMessage" class="btn modal-trigger modal-close" style="width: 100%; margin-top: 20px; margin-bottom: 20px;">Done</a>
                    </form>
        </div>
    </div>
    <!-- Modal End -->

    <!-- modal start -->

    <div id="" class="modal" style="width: 40%;">
        <!-- <button type="button" class=" modal-action modal-close waves-effect waves-green btn-flat right"
            data-dismiss="modal">&times;</button> -->
        <div class="modal-content">
                <form action="">
                        <h5 class="center">Enter OTP</h5>
                        <p class="center" id="resendOtp" style="display: none;">A text message and e-mail with OTP has been sent</p>
                        <div class="row">
                            <div class="input-field col s12 m12">
                                <input type="text" name="SupplierID" id="SupplierID" placeholder="Enter OTP of Phone"/>
                            </div>
                        </div>

                        <a href="#" onclick="document.getElementById('resendOtp').style.display ='block';" class="right">Resend OTP</a>

                        <a href="#otpMessage" class="btn modal-trigger modal-close" style="width: 100%; margin-top: 20px; margin-bottom: 20px;">Done</a>
                    </form>
        </div>
    </div>
    <!-- Modal End -->

    <!-- modal start -->

    <div id="" class="modal" style="width: 40%;">
        <!-- <button type="button" class=" modal-action modal-close waves-effect waves-green btn-flat right"
            data-dismiss="modal">&times;</button> -->
        <div class="modal-content">
                <form action="">
                        <h5 class="center">Enter OTP</h5>
                        <p class="center" id="resendOtp" style="display: none;">A text message and e-mail with OTP has been sent</p>
                        <div class="row">
                            <div class="input-field col s12 m12">
                                <input type="text" name="SupplierID" id="SupplierID" placeholder="Enter OTP of Email"/>
                            </div>
                        </div>

                        <a href="#" onclick="document.getElementById('resendOtp').style.display ='block';" class="right">Resend OTP</a>

                        <a href="#otpMessage" class="btn modal-trigger modal-close" style="width: 100%; margin-top: 20px; margin-bottom: 20px;">Done</a>
                    </form>
        </div>
    </div>
    <!-- Modal End -->

    <!-- //////////////////////////////////////////////////////////////////////////// -->




<!-- Modal 2 start   -->

<div id="otpMessage" class="modal">
        <button type="button" class=" modal-action modal-close waves-effect waves-green btn-flat right"
            data-dismiss="modal">&times;</button>
        <div class="modal-content">
            <!-- <h4 class="header2 pb-2">User Info</h4> -->

            <div class="row">
                <h6>Your OTP is verified! The form has been submitted for approval. You will receive an intimation on your registered e-mail with the approval status within 2 to 3 working days </h6>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <a href="${context}/login" class="btn">ok</a>
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

        // $('.dropdown-trigger').dropdown();
    </script>

    <!-- jQuery Library -->
    <script type="text/javascript" src="${context}/resources/js/plugins/jquery-1.11.2.min.js"></script>
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

    <!--plugins.js - Some Specific JS codes for Plugin Settings-->
    <script type="text/javascript" src="${context}/resources/js/plugins.js"></script>
    <!--custom-script.js - Add your own theme custom JS-->
    <script type="text/javascript" src="${context}/resources/js/custom-script.js"></script>

</body>

</html>

<li>
    <a class="btn-flat dropdown-button waves-effect waves-light white-text profile-btn" href="#"
        data-activates="profile-dropdown" style="margin-top:-5px; height:55px;"></a>
    <ul id="profile-dropdown" class="dropdown-content" style="margin-top: 51px;">
        <li><a href="javascript:void(0)" id=""><i class="mdi-action-settings"></i>Change Password</a></li>
        <li class="divider"></li>
        <li><a href="javascript:void(0)" id=""><i class="mdi-hardware-keyboard-tab"></i>
                Logout</a></li>
    </ul>
</li>