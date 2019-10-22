<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en" class="no-js">
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

        h6{
            font-size: 18px;
        }

        input[type=text] {
            height: 35px;
            margin: 0 0 5px 0;
        }

        textarea.materialize-textarea {
            padding: 0;
            padding-left: 10px;
        }

        .btn-flat {
            height: auto;
        }
    </style>

</head>

<body>
    <!-- Start Page Loading -->
    <div id="loader-wrapper">
        <div id="loader"></div>
        <div class="loader-section section-left"></div>
        <div class="loader-section section-right"></div>
    </div>
    <!-- End Page Loading -->

    <!-- //////////////////////////////////////////////////////////////////////////// -->

    <!-- START HEADER -->
    <header id="header" class="page-topbar">
        <!-- start header nav-->
        <div class="navbar-fixed">
            <nav class="navbar-color">
                <div class="nav-wrapper">
                    <ul class="left">
                        <li>
                            <h1 class="logo-wrapper"><a href="index.html" class="brand-logo darken-1">CEIR - Importer
                                    Portal</a> <span class="logo-text">Materialize</span></h1>
                        </li>
                    </ul>
                    <ul id="chat-out" class="right hide-on-med-and-down" style="overflow: inherit !important;">
                        <li>
                            <a class="btn-flat dropdown-button waves-effect waves-light white-text profile-btn" href="#"
                                data-activates="profile-dropdown" style="height:65px;"><i
                                    class="mdi-action-account-circle" style="color: #fff; font-size: 40px;"></i></a>
                            <ul id="profile-dropdown" class="dropdown-content">
                                <li><a href="${context}/editProfile"><i class="fa fa-pencil dropdownColor" style="float: left"></i><span
                                            style="float: left"  class="dropdownColor">Edit Info</span></a>
                                </li>
                                <li class="divider"></li>
                                <li><a href="#changePassword" class="modal-trigger"><i class="mdi-action-settings dropdownColor"
                                            style="float: left"></i><span style="float: left" class="dropdownColor">Change Password</span></a>
                                </li>
                                <li class="divider"></li>
                                <li><a href="${context}/login" id=""><i class="mdi-hardware-keyboard-tab dropdownColor"></i>
                                        <span class="dropdownColor"> Logout</span></a></li>
                                <li class="divider"></li>
                                <li><a href="#manageAccount" class="modal-trigger">
                                        <span class="dropdownColor"> Activate/Deactivate Account</span></a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
        <!-- end header nav-->
    </header>
    <!-- END HEADER -->

    <!-- //////////////////////////////////////////////////////////////////////////// -->

    <!-- START MAIN -->
    <div id="main">
        <!-- START WRAPPER -->
        <div class="wrapper">

            <!-- START LEFT SIDEBAR NAV-->
            <aside id="left-sidebar-nav">
                <ul id="slide-out" class="side-nav fixed leftside-navigation">
                    <li class="user-details cyan darken-2">
                        <div class="row">
                            <div class="col col s8 m8 l8" style="margin-left:32px;">
                                <a class="btn-flat dropdown-button waves-effect waves-light white-text profile-btn"
                                    href="#" data-activates="profile-dropdown" style="padding-bottom: 50px;">Welcome
                                    Importer</a>
                            </div>
                        </div>
                    </li>
                    <li class="active"><a href="dashboard.html"><i class="fa fa-tachometer"></i> Dashboard</a></li>
                    <li><a href="consignment.html" class="waves-effect waves-cyan"><i class="fa fa-list-alt"
                                aria-hidden="true"></i>
                            Consignment</a></li>
                    <li><a href="distributor.html" class="waves-effect waves-cyan"><i class="fa fa-th-list"
                                aria-hidden="true"></i>
                            Stock Management</a></li>
                    <li><a href="stolenRecovery.html" class="waves-effect waves-cyan"><i class="fa fa-hand-o-right"
                                aria-hidden="true"></i> Stolen/Recovery</a></li>
                    <li><a href="grievance.html" class="waves-effect waves-cyan"><i class="fa fa-file-text-o"
                                aria-hidden="true"></i>
                            Grievance Management</a></li>
                </ul>
                <a href="#" data-activates="slide-out"
                    class="sidebar-collapse btn-floating btn-medium waves-effect waves-light hide-on-large-only cyan"><i
                        class="mdi-navigation-menu"></i></a>
            </aside>
            <!-- END LEFT SIDEBAR NAV-->

            <!-- //////////////////////////////////////////////////////////////////////////// -->

            <!-- START CONTENT -->
            <section id="content">
                <!--start container-->
                <div class="container">
                    <div class="section">
                        <div class="row">
                            <div class="col s12 m12 l12">
                                <div class="row">

                                    <h1 class="center">Welcome To Central Equipment Identity
                                        Register</h1>
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

    <!-- Modal 4 start   -->

  <div id="manageAccount" class="modal">
    <div class="modal-content">
      <h6>Manage Account</h6>
      <hr>
      <p>Request CEIR ADMIN to </p>
      <div class="row" style="height: 30px;">
        <p><label style="margin-right: 50px "> <input type="radio"
          onclick="document.getElementById('calender').style.display = 'none';" name="stolen"><span>
          Deactivate</span></label>Permanently delete the account, you will not login into the portal.</p></div>
          <div class="row" style="height: 30px;">
        <p><label style="margin-right: 67px "> <input type="radio" onclick="document.getElementById('calender').style.display = 'block';"
          name="stolen"><span> Disable</span></label>All the action will be disabled, only view option will be available</p>
      </div>

      <div class="input-field col s12 center">
        <button class="btn modal-trigger modal-close" data-target="manageAccountSubmit">Submit</button>
        <a href="${context}/importerDashboard" class="btn" style="margin-left: 10px;">Cancel</a>
      </div>
    </div>
  </div>
  <!-- Modal End -->

  <!-- Modal 4 start   -->

  <div id="manageAccountSubmit" class="modal">
    <button type="button" class=" modal-action modal-close waves-effect waves-green btn-flat right"
      data-dismiss="modal">&times;</button>
    <div class="modal-content">
      <h6>The request has been successfully registered with CEIR Admin. Please find confirmation over registered mail in 2 to 3 working days.</h6>
      

      <div class="input-field col s12 center">
        <button class="btn modal-close">ok</button>
      </div>
    </div>
  </div>
  <!-- Modal End -->

    <!-- Modal 4 start   -->

    <div id="changePassword" class="modal" style="width: 40%;">
            <div class="modal-content">
                <div class="row">
                        <h5 style="text-align: -webkit-center;">Change Password</h5>
                        
                        <div class="col s1"><i class="fa fa-lock" aria-hidden="true" style="font-size: 30px; margin-top: 12px; color: #ff4081;"></i></div>
                        <div class="input-field col s11">
                           <input type="text" id="oldPassword" class="validate" maxlength="10" />
                            <label for="oldPassword" class="center-align" style="color: #000; font-size: 12px;"> Old Password </label>
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
    
                        <div class="col s1"><i class="fa fa-check-square-o" aria-hidden="true" style="font-size: 28px; margin-top: 12px; color: #ff4081;;"></i></div>
                        <div class="input-field col s11">
                            
                            <label for="confirmPassword" style="color: #000; font-size: 12px;">Confirm Password</label>
                            <input type="text" class="" id="confirmPassword" maxlength="10" />
                        </div>
                    </div>
                    <div class="row" style="margin-top: 30px;">
                        <div class="input-field col s12 m12 l12 center">
                        <a href="#changePasswordMessage" class="btn modal-trigger modal-close" type="button" id="save" style="width: 100%;">Save</a>
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
            <h6>The request has been successfully registered with CEIR Admin. Please find the confirmation over registered
              mail < mail@mail.com> in 2 to 3 working days.</h6>
          </div>
          <div class="row">
            <div class="input-field col s12 center">
              <div class="input-field col s12 center">
                <button class="modal-close waves-effect waves-light btn" style="margin-left: 10px;" type="submit"
                  name="add_user" id="add_user">Cancel</button>
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
                  <a href="index.html" class="btn" type="submit"
                    name="add_user" id="add_user">yes</a>
                    <a href="#activateDeactivate" class="modal-close modal-trigger btn" style="margin-left: 10px;">no</a>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!-- Modal End -->

        <!-- modal start -->
    <div id="changePasswordMessage" class="modal">
            <button type="button" class=" modal-action modal-close waves-effect waves-green btn-flat right"
              data-dismiss="modal">&times;</button>
            <div class="modal-content">
        
              <div class="row">
                <h6>Your Password has been changed</h6>
              </div>
              <div class="row">
                <div class="input-field col s12 center">
                  <div class="input-field col s12 center">
                    <a href="#" class="btn modal-close">ok</a>
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