<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<title>Home</title>
<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,600,700,800&display=swap" rel="stylesheet">
<meta charset="utf-8" />
<meta name="viewport"
content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta content="" name="description" />
<meta content="" name="author" />
	
<!-- Favicons-->
<link rel="icon" href="${context}/resources/images/DMC-Logo.png" sizes="32x32">
<script type="text/javascript"
	src="${context}/resources/js/plugins/jquery-1.11.2.min.js"></script>
	<link rel="shortcut icon" href="">
  <!-- CORE CSS-->
  <link href="${context}/resources/css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection">
  <link href="${context}/resources/css/style.css" type="text/css" rel="stylesheet" media="screen,projection">
  <!-- Custome CSS-->    
  <link href="${context}/resources/css/custom/custom.css" type="text/css" rel="stylesheet" media="screen,projection">

<link
	href="${context}/resources/js/plugins/data-tables/css/jquery.dataTables.min.css"
	type="text/css" rel="stylesheet" media="screen,projection">
<link
	href="${context}/resources/js/plugins/data-tables/css/jquery.dataTables.css"
	type="text/css" rel="stylesheet" media="screen,projection">
	<link
	href="${context}/resources/js/plugins/perfect-scrollbar/perfect-scrollbar.css"
	type="text/css" rel="stylesheet" media="screen,projection">
  <!-- INCLUDED PLUGIN CSS ON THIS PAGE -->
  <link href="${context}/resources/js/plugins/prism/prism.css" type="text/css" rel="stylesheet" media="screen,projection">
  <link href="${context}/resources/js/plugins/perfect-scrollbar/perfect-scrollbar.css" type="text/css" rel="stylesheet" media="screen,projection">
  <link href="${context}/resources/css/dashboard.css" type="text/css" rel="stylesheet" media="screen,projection">
  <link href="${context}/resources/font/font-awesome/css/font-awesome.css"
	type="text/css" rel="stylesheet" media="screen,projection">
<link
	href="${context}/resources/font/font-awesome/css/font-awesome.min.css"
	type="text/css" rel="stylesheet" media="screen,projection">
	

	<link rel="stylesheet"
	href="${context}/resources/custom_js/jquery-ui.css">
<script src="${context}/resources/custom_js/1.11.2_jquery-ui.js"></script>

<script src="${context}/resources/custom_js/jquery.blockUI.js"></script>
<!------------------------------------------- Dragable Model---------------------------------->
<script
	src="${context}/resources/custom_js/1.12.1_jquery-ui.min.js"></script>
	
	
	
<link rel="stylesheet"

	href="${context}/resources/project_css/iconStates.css">
<style type="text/css">
  .dataTables_info {
        display: none;
    }
    .paging_simple_numbers {
        display: none;
    }
    .dataTables_filter{
        display: none;
    }
    
   .section {
    padding-bottom: 1rem;
    width: 99%;
    margin: auto;
    margin-top: 10px;
}
table.dataTable.display tbody tr:first-child td {
    border-top: none;
    min-width: 120px !important;
}
</style>
</head>

<body data-id="1" data-roleType="${usertype}" data-userTypeID="${usertypeId}" data-userID="${userid}" data-selected-roleType="${selectedUserTypeId}" data-stolenselected-roleType="${stolenselectedUserTypeId}">

<!-- 
<section id="content">
		<div class="center">
			<h1 style="font-size: 35px; margin-top: 30px;">Welcome To Central Equipment Identity Register</h1>
		</div>
	</section> -->
	
	 <section id="content">
                <!--start container-->
                <div id="initialloader"></div>
                <div class="container">
                    <div class="section">
                        <div class="row card-panel">
                           <div class="pageHeader">
                                <p class="PageHeading"><spring:message code="view.dashboard" /></p>
                           
                              </div>
                            <div class="col s12 m12 l12">
                                <div class="row">

                                </div>
                                <div class="col s12 m12">

                                    <div class="col s12 m12 info-div center" id="infoBox" style="margin-top: 30px;">
                                                                          
                                          
                                      </div>
                                    <div class="col s12 m12" style="padding-bottom: 40px;">
                                    <div class="wrap-table"> 
                                        <h4 class="header2" style="font-weight: bold; margin-top: 50px;">
                                            <spring:message code="table.header"/></h4>
                                        
                                       <table class="responsive-table striped display" id="notificationLibraryTable"
                                            cellspacing="0">
                                            
                                        </table>
                                        </div>                                        
                                    </div>

                                </div>

                            </div>
                        </div>
                    </div>
                </div>
                <!--end container-->
            </section>
            <!-- END CONTENT -->
	<script type="text/javascript"
	src="${context}/resources/js/plugins/jquery-1.11.2.min.js?version=<%= (int) (Math.random() * 10) %>"></script>
		<script
		src="${context}/resources/custom_js/bootstrap.min.js?version=<%= (int) (Math.random() * 10) %>"></script>
<script type="text/javascript"
		src="${context}/resources/js/materialize.js?version=<%= (int) (Math.random() * 10) %>"></script>
	
		<script type="text/javascript"
		src="${context}/resources/js/plugins/data-tables/js/jquery.dataTables.js?version=<%= (int) (Math.random() * 10) %>"></script>
	
	<script type="text/javascript"
		src="${context}/resources/js/plugins/data-tables/js/jquery.dataTables.min.js?version=<%= (int) (Math.random() * 10) %>"></script>  


		
	<!-- i18n library -->
	<script type="text/javascript"
		src="${context}/resources/project_js/CLDRPluralRuleParser.js?version=<%= (int) (Math.random() * 10) %>"></script>
	<script type="text/javascript"
		src="${context}/resources/i18n_library/i18n.js?version=<%= (int) (Math.random() * 10) %>"></script>
	<script type="text/javascript"
		src="${context}/resources/i18n_library/messagestore.js?version=<%= (int) (Math.random() * 10) %>"></script>

	<script type="text/javascript"
		src="${context}/resources/i18n_library/fallbacks.js?version=<%= (int) (Math.random() * 10) %>"></script>

	<script type="text/javascript"
		src="${context}/resources/i18n_library/language.js?version=<%= (int) (Math.random() * 10) %>"></script>

	<script type="text/javascript"
		src="${context}/resources/i18n_library/parser.js?version=<%= (int) (Math.random() * 10) %>"></script>


	<script type="text/javascript"
		src="${context}/resources/i18n_library/emitter.js?version=<%= (int) (Math.random() * 10) %>"></script>


	<script type="text/javascript"
		src="${context}/resources/i18n_library/bidi.js?version=<%= (int) (Math.random() * 10) %>"></script>

	<script type="text/javascript"
		src="${context}/resources/i18n_library/history.js?version=<%= (int) (Math.random() * 10) %>"></script>

	<script type="text/javascript"
		src="${context}/resources/i18n_library/min.js?version=<%= (int) (Math.random() * 10) %>"></script>
		

	<script type="text/javascript" src="${context}/resources/project_js/globalVariables.js?version=<%= (int) (Math.random() * 10) %>"></script>
<script type="text/javascript"
		src="${context}/resources/project_js/backbutton.js?version=<%= (int) (Math.random() * 10) %>"></script>	
			<script type="text/javascript"
		src="${context}/resources/project_js/enterKey.js?version=<%= (int) (Math.random() * 10) %>"></script>
			<script type="text/javascript"
		src="${context}/resources/project_js/home.js?version=<%= (int) (Math.random() * 10) %>"></script>
<script type="text/javascript"
		src="${context}/resources/project_js/profileInfoTab.js?version=<%= (int) (Math.random() * 10) %>" async></script>
</body>

</html>