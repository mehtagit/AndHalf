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

    <style>
        ul li {
            display: inline-flex;
        }

        li {
            padding: 7px 15px;
            border: solid 1px #c9c9c9;
            border-radius: 5px;
            margin-right: 10px;
        }
        select{
            height: 1.5rem;
            border: none;
        }
        footer {
            padding-left: 0;
        }
    </style>
<script>
var contextpath = "${context}";
</script>

</head>

<body>

    <!-- //////////////////////////////////////////////////////////////////////////// -->

    <!-- START CONTENT -->
    <section id="content" id="mainPage">
        <!--start container-->
        <div class="container">
            <div class="section">
                <div class="row">
                    <div class="col s12 m12 l12">
                        <h1 style="text-align: center;">Welcome To Central Equipment Identity Register</h1>
                        <img src="${context}/resources/images/TELECOMMUNICATIONS-4.jpg" alt="" class="responsive-img">
                    </div>
                </div>

                <div class="row">
                    <div class="col s12 m12 l12">

                        <ul class="haed-btn">
                            <li class="haed-btn-style"><a href="${context}/login" style="color: #000;">Login</a></li>
                            <li class="haed-btn-style" style="padding: 0;">
 
                                                               
<select id="usertypes" class="browser-default" onchange="openRegistrationPage();" style="height: 35px; width: 150px;">
<option value="" disabled selected>Registration</option>
</select>
</li>

                            <li class="haed-btn-style"><a href="#" style="color: #000;">Check IMEI</a></li>
                            <li class="haed-btn-style"><a href="#" style="color: #000;">Upload
                                    Document</a></li>
                            <li class="haed-btn-style"><a href="#" style="color: #000;">Register
                                    Complaint</a></li>
                            <!-- <li class="haed-btn-style"><a href="#" style="color: #000;">Report
                                    Grievance</a></li> -->
                            <!-- <li class="haed-btn-style">
                                    <a class='dropdown-trigger btn' href='#' data-target='dropdown1'>Select user</a>
                                <ul id='dropdown1' class='dropdown-content'>
                                    <li><a href="#!">one</a></li>
                                    <li><a href="#!">two</a></li>
                                    <li class="divider" tabindex="-1"></li>
                                  </ul>
                            </li> -->
                        </ul>

                    </div>
                </div>
            </div>
        </div>
        <!--end container-->
    </section>
    <!-- END CONTENT -->



    <!-- //////////////////////////////////////////////////////////////////////////// -->

    <!-- START FOOTER -->
    <footer class="page-footer" style="position: fixed; bottom: 0; width: 100%;">
        <div class="footer-copyright">
            <div class="container">
                <span class="right">Copyright © 2018 Sterlite Technologies Ltd, All rights reserved.</span>
            </div>
        </div>
    </footer>
    <!-- END FOOTER -->





    <!-- START CONTENT -->
    <section id="content" id="loginForm" style="display: none;">
        <!--start container-->
        <div class="container">
            <div class="section">
                <div class="row card-panel" style="width: 50%; height: 65vh; margin: auto; margin-top: 10vh;">
                    <div class="col s12 m12 l12">
                        <div class="row">
                            <h5 style="text-align: -webkit-center;">Login</h5> <hr style="margin-bottom: 30px;">
                            
                            <!-- <div class="col s1"><i class="fa fa-lock" aria-hidden="true" style="font-size: 30px; margin-top: 12px; color: #ff4081;"></i></div> -->
                            <div class="input-field col s12">
                                <select class="browser-default">
                                    <option value="" disabled selected>User type</option>
                                    <option value="Air">Importer</option>
                                    <option value="Land">Distributor</option>
                                    <option value="Water">Retailer</option>
                                </select>
                            </div>
        
                            <!-- <div class="col s1">
                                <span class="fa-passwd-reset fa-stack" style="margin-top: 12px; color: #ff4081;">
                                    <i class="fa fa-undo fa-stack-2x"></i>
                                    <i class="fa fa-lock fa-stack-1x"></i>
                                </span></div> -->
                            <div class="input-field col s12">
                                
                                <label for="newPassword" style="color: #000; font-size: 12px;">Username</label>
                                <input type="text" id="newPassword" class="" maxlength="10" />
                            </div>
        
                            <!-- <div class="col s1"><i class="fa fa-check-square-o" aria-hidden="true" style="font-size: 28px; margin-top: 12px; color: #ff4081;;"></i></div> -->
                            <div class="input-field col s12">
                                
                                <label for="confirmPassword" style="color: #000; font-size: 12px;">Password</label>
                                <input type="text" class="" id="confirmPassword" maxlength="10" />
                            </div>
                        </div>
                        <div class="row" style="margin-top: 30px;">
                            <div class="input-field col s12 m12 l12 center">
                                <a href="index.html" class="btn" type="button" id="save" style="width: 108px;">Login</a>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
        <!--end container-->
    </section>
    <!-- END CONTENT -->





    <!-- ================================================
    Scripts
    ================================================ -->
 <!-- jQuery Library -->
    <%-- <script type="text/javascript" src="${context}/resources/js/plugins/jquery-1.11.2.min.js"></script> --%>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.1/jquery.min.js"></script>
       <!-- ajax js -->
    <script type="text/javascript" src="${context}/resources/ajax/Registration.js"></script>
    <!--materialize js-->
    <script type="text/javascript" src="${context}/resources/js/materialize.js"></script>
    <!--prism
    <script type="text/javascript" src="${context}/resources/js/prism/prism.js"></script>-->
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
    <script>
        $(document).ready(function () {
        	  usertypeDropDownData();
            $('.modal').modal();
        });
      
    </script>

   

</body>

</html>