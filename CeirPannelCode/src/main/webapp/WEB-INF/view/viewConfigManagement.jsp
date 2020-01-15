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
	
 <link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
	



</head>
<%-- <body data-roleType="${usertype}" data-userID="${userid}" data-selected-roleType="${selectedUserTypeId}"> --%>
<body data-roleType="${usertype}" data-userTypeID="${usertypeId}" data-userID="${userid}" data-selected-roleType="${selectedUserTypeId}" data-stolenselected-roleType="${stolenselectedUserTypeId}">


	<!-- START CONTENT -->
	<!-- START CONTENT -->
	<section id="content">
		<!--start container-->
		<div class="container">
			<div class="section">
				<div class="row">
					<div class="col s12 m12 l12">
						<div class="row card-panel">
							<div class="container-fluid pageHeader" id="pageHeader">

								<a href="" class="boton right" id="btnLink" hidden></a>
							</div>
							<form action="${context}/systempConfigManagement"
								method="post">
								<div class="col s12 m12 l12" id="configTableDiv"
									style="padding-bottom: 5px; background-color: #e2edef52;">
									<div id="filterBtnDiv">
										<!-- 							<div class='col s12 m2 l2'><button type='submit' class='btn primary botton' id='submitFilter'></button></div>
		 -->
									</div>
								</div>
							</form>
							<table id="configLibraryTable"
								class="responsive-table striped display"></table>

						</div>

					</div>
				</div>
				<div id="footerBtn"></div>
			</div>
		</div>
		<!--end container-->
	</section>


	 <!-- Modal 2 start   -->

	<div id="viewAdminSystemModel" class="modal">
		<h6 class="modal-header">View System Configuration</h6>
		<div class="modal-content">

			<div class="row">
				<div class="row" style="margin-top:10px">
					<div class="input-field col s12 m6 l6">
						<input type="text" name="tag" id="viewTag"
							placeholder="" disabled
							style="height: 28px;"> <label for="tag">Tag</label>
					</div>
					
					<div class="input-field col s12 m6">
						<input type="text" id="viewtype" name="type"
							placeholder="" maxlength="20" disabled style="height: 28px;">
						<label for="type">Type</label>
					</div>	
					
					<div class="input-field col s12 m6" style="margin-top:30px">
					<input type="text" id="viewValue" class="materialize-textarea" style="height: 22px;" placeholder="" readonly="readonly">
					<label for="viewValue" class="">Value</label>

					</div>
					

					
					<div class="row input_fields_wrap">
					
					<div class="input-field col s12 m6" style="margin-top:37px">
					<textarea id="description" class="materialize-textarea" style="height: 22px;" placeholder="" readonly="readonly"></textarea>
					<label for="description" class="">Description</label>

					</div>

					
					</div>
					<div class="row input_fields_wrap">	
					<div class="input-field col s12 m6">
					<textarea id="remarks" class="materialize-textarea" style="height: 22px;" placeholder="" readonly="readonly"></textarea>
					<label for="remarks" class="">Remarks</label>

					</div>
					</div>
				</div>


				<div class="row input_fields_wrap">
					<div class="col s12 m12 center" style="margin-top: 10px;">
					<button class="btn modal-close" style="margin-left: 10px;">Cancel</button>
				</div>

				</div>
			</div>
		</div>
	</div>
	<!-- Modal End -->
	
   	 <!-- Modal 3 start   -->

	<div id="editAdminSystemModel" class="modal">
		<h6 class="modal-header">Edit System Configuration</h6>
		<div class="modal-content">

			<div class="row">
				<div class="row">
					<div class="input-field col s12 m6 l6">
						<input type="text" name="tag" id="viewTag"
							placeholder="" 
							style="height: 28px;"> <label for="tag">Tag</label>
					</div>

					
					
					<div class="input-field col s12 m6">
					<textarea id="viewValue" class="materialize-textarea" style="height: 22px;"></textarea>
					<label for="viewValue" class="">Value</label>

					</div>


					<div class="input-field col s12 m6">
					<textarea id="description" class="materialize-textarea" style="height: 22px;"></textarea>
					<label for="description" class="">Description</label>

					</div>

					<div class="input-field col s12 m6">
						<input type="text" id="viewtype" name="type"
							placeholder="" maxlength="20"  style="height: 28px;">
						<label for="type">type</label>
					</div>

					<div class="input-field col s12 m6">
					<textarea id="remarks" class="materialize-textarea" style="height: 22px;" ></textarea>
					<label for="remarks" class="">Remarks</label>

					</div>
				</div>


				<div class="row input_fields_wrap">
					<div class="col s12 m12 center" style="margin-top: 10px;">
					<button class="btn modal-close" style="margin-left: 10px;">Update</button>
				</div>

				</div>
			</div>
		</div>
	</div>
   
   
   
   

	
	<!--materialize js-->
	<script type="text/javascript"
		src="${context}/resources/js/materialize.js"></script>
	<script type="text/javascript"
		src="${context}/resources/js/plugins/data-tables/js/jquery.dataTables.js"></script>
	<script type="text/javascript"
		src="${context}/resources/js/plugins/data-tables/js/jquery.dataTables.min.js"></script>

	

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
		src="${context}/resources/project_js/viewConfigManagement.js"></script>
		
</body>
</html>