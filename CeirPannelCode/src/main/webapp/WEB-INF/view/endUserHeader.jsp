<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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

<script type="text/javascript"
	src="${context}/resources/js/plugins/jquery-1.11.2.min.js"></script>
<!--   <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css"></script>  
 -->

<!-- CORE CSS-->
<link href="${context}/resources/css/materialize.css" type="text/css"
	rel="stylesheet" media="screen,projection">
<link href="${context}/resources/css/style.css" type="text/css"
	rel="stylesheet" media="screen,projection">
<script type="text/javascript"
	src="${context}/resources/js/plugins/data-tables/js/jquery.dataTables.min.js"></script>
<link
	href="${context}/resources/js/plugins/data-tables/css/jquery.dataTables.min.css"
	type="text/css" rel="stylesheet" media="screen,projection">
<link
	href="${context}/resources/js/plugins/data-tables/css/jquery.dataTables.css"
	type="text/css" rel="stylesheet" media="screen,projection">
<link href="${context}/resources/css/jquery-datepicker2.css"
	type="text/css" rel="stylesheet" media="screen,projection">
<!-- Custome CSS-->
<link href="${context}/resources/css/custom/custom.css" type="text/css"
	rel="stylesheet" media="screen,projection">
<link
	href="${context}/resources/font/font-awesome/css/font-awesome.min.css"
	type="text/css" rel="stylesheet" media="screen,projection">

<!-- INCLUDED PLUGIN CSS ON THIS PAGE -->
<link href="${context}/resources/js/plugins/prism/prism.css"
	type="text/css" rel="stylesheet" media="screen,projection">
<link
	href="${context}/resources/js/plugins/perfect-scrollbar/perfect-scrollbar.css"
	type="text/css" rel="stylesheet" media="screen,projection">
<%--  <link href="${context}/resources/js/plugins/chartist-js/chartist.min.css" type="text/css" rel="stylesheet" media="screen,projection"> --%>
<link rel="stylesheet"
	href="${context}/resources/project_css/viewConsignment.css">
<link rel="stylesheet"
	href="${context}/resources/project_css/iconStates.css">

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>

<script src="http://malsup.github.io/jquery.blockUI.js"></script>
  <!------------------------------------------- Dragable Model---------------------------------->
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>

<style>

</style>
</head>
<body>

    <header id="header" class="page-topbar">
        <!-- start header nav-->
        <div class="navbar-fixed">
            <nav class="navbar-color">
                <div class="nav-wrapper">
                    <ul class="left">
                    	<li>
					<div class="col-1 col-xs-1 offset-md-1 text-right px-0 ml-3 my-auto">
            					<a href="http://dmc-cci.edu.kh/" rel="noopener noreferrer" target="_blank" title="DMC, external link that open in a new window">
                				<img src="./resources/images/DMC-Logo.png" class="darken-1 my-2" style="height:56px;"></a>
        					</div>
        				</li>
                        <li>
                            <h1 class="logo-wrapper"><a href="index.html" class="brand-logo darken-1">CEIR</a> <span class="logo-text">Materialize</span></h1>
                        </li>
                    </ul>
                    <ul id="chat-out" class="right hide-on-med-and-down" style="overflow: inherit !important;">
                        <li class="dropdown-button">
                       <div id="divLang" style="display: flex; margin: 8px 6px;"
								class="darken-1">
								<div id="iconLable" class="darken-1">
									<i class="fa fa-globe fa-6" aria-hidden="true" style="margin-top: 22px;"></i>
								</div>
								<div>
									<select class="darken-1" id="langlist"
										style="border-bottom: none; height: 42px; width: 75px; line-height:1; background: #00bcd4; border: 1px solid #00bcd4 !important;">
										<option value="en" style="color:black">English</option>
										<option value="km" style="color:black"><spring:message code="lang.khmer" /></option>
									</select>
								</div>
							</div>
                        </li>
<%--                         <li><a href="#goToHome" id="newUserLink" class="modal-trigger" style="color:white;"><spring:message code="registration.home" /></a>	</li>
 --%>                     <!--    <li>
                            <a class="btn-flat dropdown-button waves-effect waves-light white-text profile-btn" href="#"
                                data-activates="profile-dropdown" style="height: 64px;"><i
                                    class="mdi-action-account-circle" style="color: #fff; font-size: 40px;"></i></a>
                            <ul id="profile-dropdown" class="dropdown-content">
                                <li><a href="editInformation.html" id=""><i class="fa fa-pencil dropdownColor"
                                            style="float: left"></i><span style="float: left" class="dropdownColor">Edit
                                            Info</span></a>
                                </li>
                                <li class="divider"></li>
                                <li><a href="#changePassword" class="modal-trigger" id=""><i
                                            class="fa fa-key dropdownColor" style="float: left"></i><span
                                            style="float: left" class="dropdownColor">Change Password</span></a>
                                </li>
                                <li class="divider"></li>
                                <li><a href="#logoutMsg" class="modal-trigger"><i
                                            class="mdi-hardware-keyboard-tab dropdownColor"></i>
                                        <span class="dropdownColor"> Logout</span></a></li>
                                <li class="divider"></li>
                                <li><a href="#manageAccount" class="modal-trigger dropdownColor"><i
                                            class="mdi-action-settings dropdownColor"></i>
                                        <span class="dropdownColor"> Activate/Deactivate Account</span></a></li>
                            </ul>
                        </li> -->
                    </ul>
                </div>
            </nav>
        </div>
 
        <!-- end header nav-->
    </header>

       <div id="goToHome" class="modal modal-small" style="width: 40%;">
<!-- <button type="button" class=" modal-action modal-close waves-effect waves-green btn-flat right"
data-dismiss="modal">&times;</button> -->
<h6 class="modal-header"><spring:message code="registration.homepage" /></h6>
<div class="modal-content">
<div class="row">
<h6><spring:message code="registration.pagewillredirectpanel" /></h6>
</div>
<div class="input-field col s12 center">
<div class="input-field col s12 center">
<a href="./homePage" class="btn" type="submit" name="add_user" id="add_user"><spring:message code="modal.yes" /></a>
<a href="#" class="modal-close btn"
style="margin-left: 10px;"><spring:message code="modal.no" /></a>
</div>
</div>
</div>
</div>


	<script type="text/javascript"
		src="${context}/resources/js/materialize.js"></script>
	<script type="text/javascript"
		src="${context}/resources/js/plugins/data-tables/js/jquery.dataTables.js"></script>
	<script type="text/javascript"
		src="${context}/resources/js/plugins/data-tables/js/jquery.dataTables.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${context}/resources/js/plugins.js"></script>
	<script type="text/javascript"
		src="${context}/resources/js/Validator.js"></script>
	<script type="text/javascript"
		src="${context}/resources/js/plugins/perfect-scrollbar/perfect-scrollbar.min.js"></script>
	<script type="text/javascript"
		src="${context}/resources/js/countries.js"></script>
			<!-- i18n library -->
	<script type="text/javascript"
		src="${context}/resources/project_js/CLDRPluralRuleParser.js"></script>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.i18n/1.0.7/jquery.i18n.js"></script>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.i18n/1.0.7/jquery.i18n.messagestore.js"></script>

	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.i18n/1.0.7/jquery.i18n.fallbacks.js"></script>

	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.i18n/1.0.7/jquery.i18n.language.js"></script>

	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.i18n/1.0.7/jquery.i18n.parser.js"></script>


	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.i18n/1.0.7/jquery.i18n.emitter.js"></script>


	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.i18n/1.0.7/jquery.i18n.emitter.bidi.js"></script>

	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/history.js/1.8/bundled/html4+html5/jquery.history.js"></script>

	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/js-url/2.5.3/url.min.js"></script>
		
	