<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="context" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
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
<link rel="stylesheet" href="${context}/resources/font/font-awesome/css/font-awesome.min.css">

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
<script>
var contextpath = "${context}";
</script>
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
						<div class="row card-panel"
							style="width: 40%;  margin: auto; margin-top: 10vh;">
							<form action="${context}/login" method="post">
								<div class="col s12 m12 l12">
									<div class="row">
										<h5 style="text-align: -webkit-center;">Login</h5>
										<span style="color: red;">${msg}</span>
										<hr style="margin-bottom: 30px;">

										<div class="input-field col s12">

											<label for="newPassword"
												style="color: #000; font-size: 12px;">Username</label> <input
												type="text" required="required" name="username"
												id="newPassword" class="" maxlength="10" />
										</div>

										<div class="input-field col s12">

											<label for="confirmPassword"
												style="color: #000; font-size: 12px;">Password</label> <input
												type="password" required="required" class="" name="password"
												id="confirmPassword" maxlength="10" />
										</div>

										<div class="form-group form-actions">
											<span class="input-icon"> <img id="captchaImage"
												src="${context}/captcha">
											<button
													style="background: none; border: none; outline: none;
													color: black;"
													type="button" onclick="refreshCaptcha('captchaImage')">
													<i class="fa fa-refresh"></i>
												</button>: <%-- <img src="${context}/captcha"" id="captchaImage">
						 <br>
                           <input type="button" onclick="refreshCaptcha('captchaImage')"> --%>
												<div class="input-field col s12 m6 l12">
													<input type="text" name="captcha"
														class="form-control boxBorder boxHeight" id="captcha"
														required="required"> <label for="captcha">Enter
														your captcha <span class="star">*</span>
													</label>
												</div>

											</span>
										</div>

										<a href="${context}/forgotPassword" class="right"> Forgot
											Password?</a>


										<div class="row" style="margin-top: 30px;">
											<div class="input-field col s12 m12 l12 center">
												<%--     <a href="${context}/importerDashboard" class="btn" type="button" id="save" style="width: 100%;">Login</a> --%>
												<input type="submit" class="btn" id="save"
													style="width: 100%;" value="Login">
											</div>
										</div>

									</div>




								</div>
							</form>
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





	<!-- ================================================
    Scripts
    ================================================ -->
<!-- jQuery Library -->
	<%-- <script type="text/javascript"
		src="${context}/resources/js/plugins/jquery-1.11.2.min.js"></script> --%>
		
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.1/jquery.min.js"></script>
		 <script type="text/javascript" src="${context}/resources/project_js/Registration.js"></script>
	<!--materialize js-->
	<script type="text/javascript"
		src="${context}/resources/js/materialize.js"></script>
	<!--prism
    <script type="text/javascript" src="js/prism/prism.js"></script>-->
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
	<script type="text/javascript"
		src="${context}/resources/js/custom-script.js"></script>
	<script>
        $(document).ready(function () {
            $('.modal').modal();
        });

        // $('.dropdown-trigger').dropdown();
    </script>

	

</body>

</html>

