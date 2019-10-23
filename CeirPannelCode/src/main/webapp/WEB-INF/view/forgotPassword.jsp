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
                        <div class="row card-panel" style="width: 40%; height: 50vh; margin: auto; margin-top: 10vh;">
                            <div class="col s12 m12 l12">
                                <form action="">
                                <div class="row">
                                    <h5 style="text-align: -webkit-center;">Forgot Password</h5> <hr style="margin-bottom: 30px;">

                                    <div class="row myRow">
                                        <div class="input-field col s12 m6">
                                            
                                            <label for="userId" class="right">Please enter your User ID</label>
                                        </div>

                                        <div class="input-field col s12 m6 l6">
                                            <input type="text" name="userId" id="userId" maxlength="15" />
                                        </div>
                                    </div>

                                    <div class="row myRow">
                                        <div class="input-field col s12 m6">
                                            
                                            <label for="Name">Please select your security question, provide at the time of registration</label>
                                        </div>

                                        <div class="input-field col s12 m6 l6">
                                            <select class="browser-default" required>
                                                <option value="" disabled selected>Security Question</option>
                                                <option value="NotPaid">What is your childhood name?</option>
                                                <option value="NotPaid">Where is your birth place?</option>
                                                <option value="NotPaid">What is your favourite movie?</option>
                                                <option value="NotPaid">What is your favourite sports team?</option>
                                                <option value="NotPaid">What is your favourite pet’s name?</option>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="row myRow" style="margin-top: 20px;">
                                        <div class="input-field col s12 m6">
                                            
                                            <label for="securityQueistion" class="center-align">Provide answer to the question</label>
                                        </div>

                                        <div class="input-field col s12 m6 l6">
                                            <input type="text" name="securityQueistion" id="securityQueistion" maxlength="50" />
                                        </div>
                                    </div>
                                    
                                </div>
                                <div class="row" style="margin-top: 30px;">
                                    <div class="input-field col s12 m12 l12 center">
                                        <a href="${context}/login" class="btn">submit</a>
                                        <a href="${context}/login" class="btn" style="margin-left: 10px;">cancel</a>
                                    </div>
                                </div>
                            </form>
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



    <!-- //////////////////////////////////////////////////////////////////////////// -->

    


    <div id="submitBtnAction" class="modal">
        <button type="button" class=" modal-action modal-close waves-effect waves-green btn-flat right"
          data-dismiss="modal">&times;</button>
        <div class="modal-content">
    
          <div class="row">
            <h6>The has been sent to your registered email ID</h6>
          </div>
          <div class="row">
            <div class="input-field col s12 center">
              <div class="input-field col s12 center">
                  <a href="otpVerification.html" class="btn" style="margin-left: 10px;">ok</a>
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