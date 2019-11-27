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


 <style>
        .welcomeMsg {
          padding-bottom: 50px !important;
          line-height: 1.5 !important;
          text-align: center;
        }
    </style>
</head>
<body data-roleType="${usertype}" data-userID="${userid}" data-selected-roleType="${selectedUserTypeId}">

<section id="content">
 
                <!--start container-->
              	<div id="" class="modal">
        <button class="modal-close btn-flat right" data-dismiss="modal">&times;</button>
        <div class="modal-content">
        <div class="row">
                                    <div>
                                        <h5 style="margin-left: 10%;">Grievance history</h5>

                                    </div>
                                    <hr>
                                    <div id="live-chat">
                                        <div class="chat">
                                            <div class="chat-history">
                                                <div class="chat-message clearfix">
                                                    <div class="chat-message-content clearfix">
                                                        <span class="chat-time">15/07/2018 13:35</span>
                                                        <h5>Admin</h5>
                                                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
                                                            Error, explicabo quasi ratione odio
                                                            dolorum harum.</p>
                                                    </div> <!-- end chat-message-content -->
                                                </div> <!-- end chat-message -->
                                                <hr>
                                                <div class="chat-message-user clearfix">
                                                    <div class="chat-message-content clearfix">
                                                        <span class="chat-time">16/07/2018 13:37</span>
                                                        <h5>You</h5>
                                                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
                                                            Blanditiis, nulla accusamus magni
                                                            vel debitis numquam qui tempora rem voluptatem delectus!</p>
                                                    </div> <!-- end chat-message-content -->
                                                </div> <!-- end chat-message -->
                                                <hr>

                                            </div> <!-- end chat-history -->
                                        </div> <!-- end live-chat -->
                                    </div>
                                </div>
                            </div>
                            </div>
                            
           <!-- Modal 4 start -->

               
    
</section>

	<!--materialize js-->
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
	<script type="text/javascript"
		src="${context}/resources/js/materialize.js"></script>


	<script type="text/javascript"
		src="${context}/resources/js/plugins/data-tables/js/jquery.dataTables.js"></script>
	<script type="text/javascript"
		src="${context}/resources/js/plugins/data-tables/js/jquery.dataTables.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.3/moment.min.js"></script>
	<script type="text/javascript"
		src="${context}/resources/js/jquery-datepicker2.js"></script>


	<!--plugins.js - Some Specific JS codes for Plugin Settings-->
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

	<%--   <script type="text/javascript" src="${context}/resources/js/materialize-plugins/date_picker/picker.date.js"></script>
    <script type="text/javascript" src="${context}/resources/js/materialize-plugins/date_picker/picker.js"></script> --%>
	<!--custom-script.js - Add your own theme custom JS-->
	<script type="text/javascript" src="${context}/resources/js/plugins.js"></script>
	<script type="text/javascript"
		src="${context}/resources/js/Validator.js"></script>
	<!--prism
    <script type="text/javascript" src="${context}/resources/resources/js/prism/prism.js"></script>-->
	<!--scrollbar-->
	<script type="text/javascript"
		src="${context}/resources/js/plugins/perfect-scrollbar/perfect-scrollbar.min.js"></script>
	<!-- chartist -->
	<%-- <script type="text/javascript" src="${context}/resources/js/plugins/chartist-js/chartist.min.js"></script> --%>
	<script type="text/javascript"
		src="${context}/resources/js/countries.js"></script>
	<script type="text/javascript"
		src="${context}/resources/project_js/grievanceManagement.js"></script>
		</body>
</html>