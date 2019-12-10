<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<title>Dashboard</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta content="" name="description" />
<meta content="" name="author" />
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
<link href="${context}/resources/font/font-awesome/css/font-awesome.css"
	type="text/css" rel="stylesheet" media="screen,projection">
<link
	href="${context}/resources/font/font-awesome/css/font-awesome.min.css"
	type="text/css" rel="stylesheet" media="screen,projection">
	<script>
var contextpath = "${context}";
<%String usertype=(String)session.getAttribute("usertype");%>
</script>
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
							<h1 class="logo-wrapper">
							
								<a href="#" class="brand-logo darken-1">CEIR -
									<span id="cierRoletype"><%=usertype%></span> Portal 
									 
									<%if("Operator".equalsIgnoreCase(usertype)){%>
									-	<%=session.getAttribute("operatorTypeName") %>
									<%}else{}%>   
										
									</a> <span class="logo-text">Materialize</span>
							</h1> 
						</li>
					</ul>
				<ul id="chat-out" class="right hide-on-med-and-down"
style="overflow: inherit !important;">
<li><a
class="btn-flat dropdown-button waves-effect waves-light white-text profile-btn"
href="#" data-activates="profile-dropdown" style="height: 65px;"><i
class="mdi-action-account-circle"
style="color: #fff; font-size: 40px;"></i></a>
<ul id="profile-dropdown" class="dropdown-content">
<li><a href="${context}/editProfile" target="mainArea"><i
class="fa fa-pencil dropdownColor" style="float: left"></i><span
style="float: left" class="dropdownColor">Edit Info</span></a></li>
<li class="divider"></li>
<li><a data-target="changePassword" class="modal-trigger"><i
class="mdi-action-settings dropdownColor" style="float: left"></i><span
style="float: left" class="dropdownColor">Change
Password</span></a></li>

<li class="divider"></li>
<li><a href="#manageAccount" class="modal-trigger"><i class="fa fa-sign-out dropdownColor"></i><span
class="dropdownColor"> Activate/Deactivate Account</span></a></li>	
<li class="divider"></li>

<li><a href="${context}/logout" id=""><i style="float: left;"
class="mdi-hardware-keyboard-tab dropdownColor"></i> <span
class="dropdownColor"> Logout</span></a></li>

</ul></li>
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
							<div class="col col s4 m4 l4">
								<!--  <img src="images/avatar.jpg" alt="" class="circle responsive-img valign profile-image"> -->
								<p
									style="width: 180px; text-align: center; color: #fff; font-size: 16px; margin-top: 2px;">
									Welcome <%=(String)session.getAttribute("name") %>
									(<%=(String)session.getAttribute("username")%>) 
									</p>
							</div>

						</div>
					</li>
					<%-- <li>
						<ul>
							<li class="bold"><a href="${context}/Home" target="mainArea"
								class="waves-effect waves-cyan"><i class="fa fa-tachometer"></i>
									Dashboard</a></li>
							<li><a href="./Consignment/viewConsignment"
								target="mainArea"><i class="fa fa-list-alt"></i>Consignment
							</a></li> 
							<li><a href="./assignDistributor" target="mainArea"><i
									class="fa fa-th-list"></i>Upload Stock</a></li>
							<li><a href="./stolenRecovery" target="mainArea"><i
									class="fa fa-hand-o-right"></i>Stolen/Stock </a></li>
						</ul>
					</li> --%>
					
					
					<li>
					<ul class="navData">
					<c:forEach items="${features}"  var="feature">
							<li class="bold"><a href="${feature.link}" target="mainArea"
								class="waves-effect waves-cyan" data-featureID="${feature.id}"><i class="${feature.logo}"></i>
									 ${feature.name}</a></li>
					</c:forEach>
					</ul>
					</li>



					<!--         <li class="no-padding">
                <ul class="collapsible collapsible-accordion">
                    <li class="bold"><a class="collapsible-header waves-effect waves-cyan"><i class="fa fa-file-text-o"></i> View</a>
                        <div class="collapsible-body">
                            <ul>
                                <li><a  href="./importerConsignment/1" target="mainArea"><i class="fa fa-file-text-o"></i>Consignment </a>
                                </li>
                                <li><a href="#"><i class="fa fa-file-text-o"></i>Stolen/Recovery</a>
                                </li>
                            </ul>
                        </div>
                    </li>
                </ul>
            </li> -->



				</ul>
				<a href="#" data-activates="slide-out"
					class="sidebar-collapse btn-floating btn-medium waves-effect waves-light hide-on-large-only cyan"><i
					class="mdi-navigation-menu"></i></a>
			</aside>
			<!-- END LEFT SIDEBAR NAV-->

			<!-- //////////////////////////////////////////////////////////////////////////// -->

			<!-- START CONTENT -->
			<section id="content">

				<!--breadcrumbs start-->

				<!--breadcrumbs end-->


				<!--start container-->
				<div class="container">
					<div class="section">

						<iframe name="mainArea" class="embed-responsive-item"
							scrolling="yes" frameBorder="0" src="./Home" width="100%"
							height="700px"></iframe>
					</div>
					<!-- Floating Action Button -->

					<!-- Floating Action Button -->
				</div>
				<!--end container-->
			</section>
			<!-- END CONTENT -->

			<!-- //////////////////////////////////////////////////////////////////////////// -->
			<!-- START RIGHT SIDEBAR NAV-->
			<aside id="right-sidebar-nav"></aside>
			<!-- LEFT RIGHT SIDEBAR NAV-->

		</div>
		<!-- END WRAPPER -->

	</div>
	<!-- END MAIN -->



	<!-- //////////////////////////////////////////////////////////////////////////// -->

	<!-- START FOOTER -->
	<footer class="page-footer">
		<div class="footer-copyright">
			<div class="container">
				<span>Copyright © 2018 Sterlite Technologies Ltd, All rights
					reserved.</span>

			</div>
		</div>
	</footer> 	

	<!-- END FOOTER -->
	<div id="manageAccount" class="modal">
		<div class="modal-content">
		<form id="userStatusForm"  onsubmit="return updateUSerStatus()">
			<h6>Manage Account</h6>
			 <span style="text-align: center;color: red;" id="errorMsg"></span> 
			<hr> 
			 
			<p>Request CEIR ADMIN to</p>
			<div class="row" style="height: 30px;">
			
				<p>
					<label style="margin-right: 50px"> <input  type="radio"
						name="status" value="Deactivate" required="required" ><span> Deactivate</span></label>Permanently delete
					the account, you will not login into the portal.
				</p>                        
			</div>
			<%String status=(String)session.getAttribute("userStatus");%>
			<%if(status.equalsIgnoreCase("Approved")){ %>
			<div class="row" style="height: 30px;">
				<p>                
					<label style="margin-right: 67px"> <input type="radio" value="Disable"
						name="status" required="required"><span> Disable</span></label>All the action will be
					disabled, only view option will be available
				</p> 
			</div>
			<%} else if(status.equalsIgnoreCase("Disable")){ %>
                    <div class="row" style="height: 30px;">
				<p>            
					<label style="margin-right: 67px"> <input type="radio" value="Approved"
						name="status" required="required"><span> Enable</span></label>All the action will be
					Enable 
				</p>
			</div>

<%} else {} %>
			<div class="input-field col s12 center">
				<button class="btn" id="updateStatusBtn">Submit</button>
				<a href="" class="btn" style="margin-left: 10px;">Cancel</a>
			</div>
			</form>
		</div>
	</div>
	<!-- Modal End -->

	<!-- Modal 4 start   -->

	<div id="manageAccountSubmit" class="modal">
		<button type="button"
			class=" modal-action modal-close waves-effect waves-green btn-flat right"
			data-dismiss="modal">&times;</button>
		<div class="modal-content">
			<h6><!-- The request has been successfully registered with CEIR
				Admin. Please find confirmation over registered mail in 2 to 3
				working days. --></h6>

 
			<div class="input-field col s12 center">
				<a href="${context}/logout" class="btn modal-close">ok</a>
			</div>
		</div>
	</div>
	<!-- Modal End -->

	<!-- Modal 4 start   -->

<div id="changePassword" class="modal" style="width: 40%;">
<div class="modal-content">
<form onsubmit="return changePassword()">
<div class="row">

<h5 style="text-align: -webkit-center;">Change Password</h5>
<span style="text-align: center;color: red;" id="errorMsg"></span> 
<div class="col s1">
<i class="fa fa-lock" aria-hidden="true"
style="font-size: 30px; margin-top: 12px; color: #ff4081;"></i>
</div>
<div class="input-field col s11">
<input type="password" id="oldPassword" class="validate" 
pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$" maxlength="10" min="8"
title="Please enter atleast one numeric char, one alphabet, one special character and must be of minumum 8 length"
required="required"
/>
<label for="oldPassword" class="center-align"
style="color: #000; font-size: 12px;"> Old Password </label>
<div class="password"></div>
</div>

<div class="col s1">
<span class="fa-passwd-reset fa-stack"
style="margin-top: 12px; color: #ff4081;"> <i
class="fa fa-undo fa-stack-2x"></i> <i
class="fa fa-lock fa-stack-1x"></i>
</span>
</div>
<div class="input-field col s11">

<label for="newPassword" style="color: #000; font-size: 12px;">New
Password</label> <input type="password"
pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$" maxlength="10" min="8"
title="Please enter atleast one numeric char, one alphabet, one special character and must be of minumum 8 length"
required="required"
id="password" class=""
/>
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
required="required"
/>
</div>
</div>
<div class="row" style="margin-top: 30px;">
<div class="input-field col s12 center">
<button class="btn" id="updateStatusBtn">Submit</button>
<a href="" class="btn" style="margin-left: 10px;">Cancel</a>
</div>

</div>
</form>
</div>
</div>
	<!-- Modal End -->


	<!-- Modal 2 start   -->

	
<!-- Modal 2 start -->

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

<!-- Modal 2 start -->

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
<a href="index.html" class="btn" type="submit" name="add_user"
id="add_user">yes</a> <a href="#activateDeactivate"
class="modal-close modal-trigger btn" style="margin-left: 10px;">no</a>
</div>
</div>
</div>
</div>
</div>
<!-- Modal End -->

<!-- modal start -->
<div id="changePasswordMessage" class="modal" style="width: 40%;">
<div class="modal-content">
<p>Change Password</p>
<hr>
<div class="row">
<h6></h6>
</div>
<div class="row">
<div class="center">
<a href="" class="btn">ok</a>
</div>
</div>
</div>
</div>
<!-- Modal End -->
	<!-- Modal End -->


	<!-- ================================================
    Scripts
    ================================================ -->


	<!-- jQuery Library -->
<%-- 	<script type="text/javascript"
		src="${context}/resources/js/plugins/jquery-1.11.2.min.js"></script> --%>
		 <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.1/jquery.min.js"></script>
       <!-- ajax js -->
    <script type="text/javascript" src="${context}/resources/ajax/Profile.js"></script>
	<!--materialize js-->
	<script type="text/javascript"
		src="${context}/resources/js/materialize.js"></script>
	<!--prism
    <script type="text/javascript" src="${context}/resources/js/prism/prism.js"></script>-->
	<!--scrollbar-->
	<script type="text/javascript"
		src="${context}/resources/js/plugins/perfect-scrollbar/perfect-scrollbar.min.js"></script>
	<!-- chartist -->
	<!--   <script type="text/javascript" src="${context}/resources/js/plugins/chartist-js/chartist.min.js"></script>   
     -->
	<!--plugins.js - Some Specific JS codes for Plugin Settings-->
	<script type="text/javascript" src="${context}/resources/js/plugins.js"></script>
	<!--custom-script.js - Add your own theme custom JS-->
	<script type="text/javascript"
		src="${context}/resources/js/custom-script.js"></script>
<script type="text/javascript">
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
console.log($("#cierRoletype").text());
var cierRoletype = $("#cierRoletype").text()
sessionStorage.setItem("cierRoletype", cierRoletype);
$('.navData li').on('click', function() {
$('.navData li:not(.inactive)').addClass("inactive");
$('.navData li').removeClass("active");
$(this).removeClass("inactive");
$(this).addClass("active"); 
});
</script>

</body>

</html> 	