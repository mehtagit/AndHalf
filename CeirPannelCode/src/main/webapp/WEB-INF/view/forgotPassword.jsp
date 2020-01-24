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
    <link rel="stylesheet" href="${context}/resources/font/font-awesome/css/font-awesome.min.css" >

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

        /* .row {
            margin-bottom: 0;
            margin-top: 0;
        } */

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
<script type="text/javascript">
var contextpath='${context}';
</script>
</head>

<body>

    <!-- //////////////////////////////////////////////////////////////////////////// -->

     <header id="header" class="page-topbar">
        <!-- start header nav-->
        <div class="navbar-fixed">
            <nav class="navbar-color">
                <div class="nav-wrapper">
                    <ul class="left">
                        <li>
                            <h1 class="logo-wrapper"><a href="index.html" class="brand-logo darken-1">CEIR </a> <span
                                    class="logo-text">Materialize</span></h1>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
        <!-- end header nav-->
    </header>

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
                        <div class="row card-panel login-card-panel" style="margin: auto; margin-top: 10vh;">
                        <h6 class="fixPage-modal-header">Forgot Password</h6>
                            <div class="col s12 m12 l12">
                                <form  id="forgotPassword" onsubmit="return forgotPassword()" >
                                <div class="row">
                       
                        <span style="text-align: center;color: red;" id="errorMsg"></span>                          
                                 <!--     <hr style="margin-bottom: 30px;"> -->

                                    <div class="row myRow">
                                        <div class="input-field col s12 m6">
                                            
                                            <label for="username" class="right">Please enter your User ID <span class="star">*</span></label>
                                        </div>

                                        <div class="input-field col s12 m6 l6">
                                            <input type="text" name="username" required="required" id="username" maxlength="10" />
                                        </div>
                                    </div>

                                    <div class="row myRow">
                                        <div class="input-field col s12 m6">
                                            
                                            <label for="questionId">Please select your security question, provide at the time of registration <span class="star">*</span></label>
                                        </div>

                                        <div class="input-field col s12 m6 l6">
                                            <select class="browser-default" id="questionId" required>
                                                <option value="" disabled selected>Security Question</option>
                                             </select>
                                        </div>
                                    </div>

                                    <div class="row myRow" style="margin-top: 20px;">
                                        <div class="input-field col s12 m6">
                                            
                                            <label for="answer" class="center-align">Provide answer to the question <span class="star">*</span></label>
                                        </div>

                                        <div class="input-field col s12 m6 l6">
                                            <input type="text" name="answer" required="required" id="answer" maxlength="50" />
                                        </div>
                                    </div>
                                    
                                </div>
                                <div class="row" style="margin-top: 30px;">
                                    <div class="input-field col s12 m12 l12 center">
                                        <button id="forgotPasswordBtn"   type="submit" class="btn">submit</button>
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

    

<div id="changePassword" class="modal" style="width: 40%;">
		<h6 class="modal-header" >Set New Password</h6>  
		<div class="modal-content">
		<form onsubmit="return udapteNewPassword()">
			<div class="row">   
				<h5 style="text-align: -webkit-center;">New Password</h5>
  <span style="text-align: center;color: red;" id="errorMsg"></span>
				<div class="col s1">
					<i class="fa fa-lock" aria-hidden="true"
						style="font-size: 30px; margin-top: 12px; color: #ff4081;"></i>
				</div>
				<div class="col s1">
					<span class="fa-passwd-reset fa-stack"
						style="margin-top: 12px; color: #ff4081;"> <i
						class="fa fa-undo fa-stack-2x"></i> <i
						class="fa fa-lock fa-stack-1x"></i>
					</span>
				</div>
				<div class="input-field col s11">
                    <input type="hidden" id="usernamedata">
					<label for="password" style="color: #000; font-size: 12px;">New
						Password</label> <input type="password" id="password" class=""
						pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$" maxlength="10" min="8"
									title="Please enter atleast one numeric char, one alphabet, one special character and must be of minumum 8 length"
									required="required"/>
				</div>           

				<div class="col s1">
					<i class="fa fa-check-square-o" aria-hidden="true"
						style="font-size: 28px; margin-top: 12px; color: #ff4081;"></i>
				</div>   
				<div class="input-field col s11">
					<label for="confirm_password" style="color: #000; font-size: 12px;">Confirm
						Password</label> <input type="password" class="" id="confirm_password"
						pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$" maxlength="10" min="8"
									title="Please enter atleast one numeric char, one alphabet, one special character and must be of minumum 8 length"
									required="required"/>
				</div>
			</div>  
			<div class="row" style="margin-top: 30px;">
				<div class="input-field col s12 m12 l12 center">
					<button  
						class="btn" type="submit" id="save"
						style="width: 100%;">Save</button>
				</div>
			</div>
			</form>
		</div>
	</div>
	
	
	<div id="submitBtnAction" class="modal">
        <button type="button" class="modal-action modal-close waves-effect waves-green btn-flat right"
            data-dismiss="modal">&times;</button>
        <h6 class="modal-header">Forgot Password</h6>
        <div class="modal-content">
                <h6 id="responseMsg"></h6>
            <div class="row">
                <div class="input-field col s12 center">
                    <a href="${context}/login" class="btn">ok</a>
                </div>
            </div>
        </div>
    </div>

    <%-- <div id="submitBtnAction" class="modal">
        <button type="button" class=" modal-action modal-close waves-effect waves-green btn-flat right"
          data-dismiss="modal">&times;</button>
        <div class="modal-content">
        <h6 class="modal-header">Forgot Password</h6>
          <div class="row">
            <h6 id="responseMsg"></h6>
          </div>
          <div class="row">
            <div class="input-field col s12 center">
              <div class="input-field col s12 center">
                  <a href="${context}/login" class="btn" style="margin-left: 10px;">ok</a>
              </div>
            </div>
          </div>
        </div>
      </div> --%>
     <!--  Modal End -->
    


    <!-- ================================================
    Scripts
    ================================================ -->

  <!-- jQuery Library -->
   <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.1/jquery.min.js"></script>
       <!-- ajax js -->
    <script type="text/javascript" src="${context}/resources/ajax/Registration.js"></script>
    <script type="text/javascript" src="${context}/resources/ajax/Login.js"></script>
 
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


    <script>
        $(document).ready(function () {
            //$('.modal').openModal();
            questionData();
        });
        
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

        // $('.dropdown-trigger').dropdown();
    </script>

  
</body>

</html>