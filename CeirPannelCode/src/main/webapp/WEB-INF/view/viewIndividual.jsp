<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<title>Grievance</title>

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
	href="${context}/resources/css/grievance.css">



</head>

<body data-roleType="${usertype}" data-userTypeID="${usertypeId}" data-userID="${userid}" data-selected-roleType="${selectedUserTypeId}" data-stolenselected-roleType="${stolenselectedUserTypeId}">

		<section id="content" style="margin:0 0.5rem">
        <!--start container-->
        <div class="container">
            <div class="section">
                <form id="registrationForm">
                    <div class="card-panel registration-form">
                        <div class="row">
                            <h5>View Individual Information</h5>
                            <hr>
                            <div class="row" style="margin-top: 10px;">
                                <div class="input-field col s12 m4 l4">
                                    <input type="text" name="firstName" id="firstName" value="${registration.firstName}" maxlength="20" placeholder="" disabled="">
                                    <label for="firstName" class="center-align active">First Name</label>
                                </div>

                                <div class="input-field col s12 m4 l4">
                                    <input type="text" name="middleName" id="middleName" value="${registration.middleName}"  maxlength="20" placeholder="" disabled="">
                                    <label for="middleName" class="active">Middle Name</label>
                                </div>

                                <div class="input-field col s12 m4 l4">
                                    <input type="text" name="lastName" id="lastName" value="${registration.lastName}" maxlength="20" placeholder="" disabled="">
                                    <label for="lastName" class="active">Last Name </label>
                                </div>

                                <div class="input-field col s12 m6">
                                    <input type="text" name="asType" id="asType" value="${registration.asTypeName}" maxlength="20" placeholder="" disabled="">
                                    <label for="asType" class="active">As Type </label>
                                </div>

                                <div class="input-field col s12 m6 l6" id="passportNumberDiv">
                                    <input type="text" name="passportNumber" id="passportNumber" value="${registration.passportNo}" maxlength="20" placeholder="" disabled="">
                                    <label for="passportNumber" class="active">National ID</label>
                                </div>

                                <div class="input-field col s12 m6 l6">
                                    <input type="text" name="nationalityInformation" id="nationalityInformation" value="${registration.nidFilename}" maxlength="20" value="file.csv" disabled="">
                                    <label for="nationalityInformation" class="active">Upload Nationality Information </label>
                                   <span> <a href="#" onclick="previewFile('${registration.nidFilePath}','${registration.nidFilename}')">Preview </a></span> 
                                    
                                </div>

                                

                                <div class="input-field col s12 m6 l6">
                                    <input type="text" name="email" id="email" maxlength="30" value="${registration.email}" placeholder="" disabled="">
                                    <label for="email" class="active">Email </label>
                                </div>

                                <div class="input-field col s12 m6 l6">
                                    <input type="text" name="phone" id="phone" value="${registration.phoneNo}" maxlength="10" placeholder="" disabled="">
                                    <label for="phone" class="active">Contact Number </label>
                                </div>


                            </div>

                            <div class="row">
                                <div class="input-field col s12 m12 l12">
                                    <input type="text" name="address" id="address" value="${registration.propertyLocation}" placeholder="" disabled="">
                                    <label for="address" class="active">Address(Property Location)</label>
                                </div>

                                <div class="input-field col s12 m6 l6">
                                    <input type="text" name="streetNumber" id="streetNumber" value="${registration.street}" maxlength="30" placeholder="" disabled="">
                                    <label for="streetNumber" class="active">Street Number</label>
                                </div>
                                
                                <div class="input-field col s12 m6 l6">
									<input type="text" name="village" id="village" maxlength="20" value="${registration.village}" disabled="" placeholder="">
									<label for="village">Village</label>
								</div>

                                <div class="input-field col s12 m6 l6">
                                    <input type="text" name="locality" id="locality" value="${registration.locality}" maxlength="20" placeholder="" disabled="">
                                    <label for="locality" class="active">Locality </label>
                                </div>
                                
                                <div class="input-field col s12 m6 l6">
									<input type="text" name="district" id="district" maxlength="20" value="${registration.district	}" disabled="" placeholder="">
									<label for="district">District</label>
								</div>
                                
                                
                                 <div class="input-field col s12 m6 l6">
                                       <input type="text" name="commune" id="commune" maxlength="20" value="${registration.commune}" disabled="" placeholder="">
                                       <label for="commune" class="active">Commune </label>
                                  </div>
								
								<div class="input-field col s12 m6 l6">
									  <input type="text" name="pin" id="pin" maxlength="20" value="${registration.postalCode}" disabled="" placeholder="">
									  <label for="pin">Postal code </label>
								</div> 	
									
                                
                                <div class="input-field col s12 m6 l6">
                                    <input type="text" name="country" id="country" value="${registration.country}" maxlength="20" placeholder="" disabled="">
                                    <label for="country" class="active">Country</label>
                                </div>
                                
                                
                                <div class="input-field col s12 m6 l6">
                                    <input type="text" name="province" id="province" value="${registration.province}" maxlength="20" placeholder="" disabled="">
                                    <label for="province" class="active">Province</label>
                                </div>
						</div>

                            <div class="row">
                                    <div class="col s12 m6 l6">
                                            <label for="vatNumber">VAT Registration </label>
                                            <div class=" boxHeight">
                                                <input class="with-gap" name="group3" type="radio" disabled="">
                                                Yes
                                                <input class="with-gap" name="group3" type="radio" style="margin-left: 20px;" disabled="">
                                                No
                                            </div>
                               </div>

                                <div class="input-field col s12 m6" id="vatNumberField">
                                    <input type="text" name="roleType" id="roleType" value="${registration.type}" maxlength="16" placeholder="" disabled="">
                                    <label for="roleType" class="active">Role Type </label>
                                </div>

                                <div class="input-field col s12 m6" id="vatNumberField">
                                    <input type="text" name="vatNumber" id="vatNumber" value="${registration.vatStatus}" maxlength="16" placeholder="" disabled="">
                                    <label for="vatNumber" class="active">VAT Number </label>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                          	<div class="input-field col s12 center">
                                  <a class="btn modal-close" href="./registrationRequest">cancel</a>
                            </div>
                        </div>

                
            </div></form>
        </div>
        <!--end container-->
    </div></section>




	<!-- Preview Modal start   -->

	<div id="viewuplodedModel" class="modal">
		<div class="modal-content">
			<div class="row">
					<img src="" id="fileSource" width="400" height="400">
			</div>
		</div>
	</div>
	<!-- Modal End -->
    









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
		src="${context}/resources/project_js/AdminRegistrationRequest.js"></script>
		
</body>
</html>