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
	href="${context}/resources/project_css/stolenRecovery.css">
<link rel="stylesheet"
	href="${context}/resources/project_css/iconStates.css">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
<link rel="stylesheet"
	href="${context}/resources/project_css/intlTelInput.css">

<style>
        .checkboxFont {
            color: #444;
            font-size: 16px;
            margin-right: 10px;
        }

        .section {
            padding-top: 0.5rem;
        }

        .welcomeMsg {
            padding-bottom: 50px !important;
            line-height: 1.5 !important;
            text-align: center;
        }

        .file-label {
           font-size: 0.9rem;
       }

       .contact-label {
            margin-top: -17px;
            margin-bottom: 0;
            font-size: 0.8rem;
        }
    </style>



</head>

<body data-roleType="${usertype}" data-userTypeID="${usertypeId}"
	data-userID="${userid}" data-operatorTypeId="${operatorTypeId}"
	data-selected-roleType="${stolenselectedUserTypeId}"
	data-stolenselected-roleType="${stolenselectedUserTypeId}"	>





        <!-- START CONTENT -->
            <section id="content">
                <!--start container-->
                <div class="container">
                    <div class="section">
                        <div class="row">
                            <div class="col s12 m12 l12">
                                <div class="row card-panel">
                                    <div id="reportBlockUnblock">
                                        <div class="container-fluid pageHeader">
                                            <p class="PageHeading">Report Recovery</p>
                                        </div>

                                        <div class="row">
                                            <div class="col s12">
                                                <ul class="tabs">
                                                    <li class="tab col s3"><a class="active" onclick="singleRecoverydiv()">Single</a>
                                                    </li>
                                                    <li class="tab col s3"><a onclick="showBulkRecovery()">Company/Organisation/Government</a></li>
                                                </ul>
                                            </div>
                                            <div id="singleRecoveryDiv" class="col s12" style="margin-top: 30px; display: block">
                                                <form action="#" id="singleRecoveryForm">
                                                    <div class="row">
                                                        <div class="col-s12 m12">
                                                             <div class="input-field col s12 m6" style="margin-top: 22px;">
                                                                <input type="text" name="sigleRecoverydeviceBrandName" id="sigleRecoverydeviceBrandName" maxlength="30">
                                                                <label for="sigleRecoverydeviceBrandName">Device Brand Name</label>
                                                            </div>

                                                            <div class="input-field col s12 m6" style="margin-top: 22px;">
                                                                <input type="text" name="sigleRecoveryimeiNumber" id="sigleRecoveryimeiNumber" maxlength="30">
                                                                <label for="sigleRecoveryimeiNumber">IMEI/MEID/ESN Number <span class="star">*</span></label>
                                                            </div>

                                                            <div class="col s6 m6 selectDropdwn">
                                                                <label for="sigleRecoverydeviceIDType">Device ID Type <span class="star">*</span></label>
                                                                <select id="sigleRecoverydeviceIDType" class="browser-default">
                                                                  <option value="" disabled selected>Device ID Type</option>
                                                                </select>
                                                              </div>

                                                              <div class="col s6 m6 selectDropdwn">
                                                                <label for="sigleRecoverydeviceType">Device Type </label>
                                                                <select class="browser-default" id="sigleRecoverydeviceType">
                                                                    <option value="" disabled selected>Device Type</option>
                                                                </select>
                                                              </div> 
                                                              
                                                              <div class="col s6 m6 selectDropdwn">
                                                                <label for="sigleRecoverydeviceSimStatus">Multiple Sim Status </label>
                                                                <select id="sigleRecoverydeviceSimStatus" class="browser-default">
                                                                  <option value="" disabled selected>Multiple Sim Status</option>
                                                                </select>
                                                              </div>

                                                              <div class="input-field col s12 m6" style="margin-top: 22px;">
                                                                <input type="text" name="sigleRecoveryserialNumber" id="sigleRecoveryserialNumber" maxlength="30">
                                                                <label for="sigleRecoveryserialNumber">Device Serial Number</label>
                                                            </div>

                                                            <div class="col s12 m12" style="margin-top: 30px;">
                                                                <h5>Place Of Device Recovery</h5>
                                                                <hr>
                                                            </div>
                                                            <!-- <div class="col s12 m12">
                                                                <p><b>Place Of Device Recovery</b></p>
                                                            </div> -->
                                                            <div class="input-field col s12 m12">
                                                                <input type="text" name="sigleRecoveryaddress" class="form-control boxBorder boxHeight"
                                                                     pattern=[A-Za-z] title="Please enter your address">
                                                                <label for="sigleRecoveryaddress">Address(Property Location) <span class="star">*</span></label>
                                                            </div>
                            
                                                            <div class="input-field col s12 m6 l6">
                                                                <input type="text" name="sigleRecoverystreetNumber" class="form-control boxBorder boxHeight"
                                                                    id="sigleRecoverystreetNumber" maxlength="30" pattern=[A-Za-z0-9] title="Please enter street number">
                                                                <label for="sigleRecoverystreetNumber">Street Number <span class="star">*</span></label>
                                                            </div>
        
                                                            <div class="input-field col s12 m6 l6">
                                                                <input type="text" name="sigleRecoveryvillage" id="sigleRecoveryvillage" maxlength="20">
                                                                <label for="sigleRecoveryvillage">Village <span class="star">*</span></label>
                                                            </div>
                            
                                                            <div class="input-field col s12 m6 l6">
                                                                <input type="text" name="sigleRecoverylocality" class="form-control boxBorder boxHeight"
                                                                    id="sigleRecoverylocality" maxlength="20" pattern=[A-Za-z0-9] title="Please enter your locality">
                                                                <label for="sigleRecoverylocality">Locality <span class="star">*</span></label>
                                                            </div>
                                
                                                            <div class="input-field col s12 m6 l6">
                                                                <input type="text" name="sigleRecoverydistrict" id="sigleRecoverydistrict" maxlength="20">
                                                                <label for="sigleRecoverydistrict">District <span class="star">*</span></label>
                                                            </div>
                                
                                                            <div class="input-field col s12 m6 l6">
                                                                <input type="text" name="sigleRecoverycommune" id="sigleRecoverycommune" maxlength="20">
                                                                <label for="sigleRecoverycommune">Commune <span class="star">*</span></label>
                                                            </div>
                                
                                                            <div class="input-field col s12 m6 l6">
                                                                <input type="text" name="sigleRecoverypin" class="form-control boxBorder boxHeight"
                                                                    id="sigleRecoverypin" maxlength="20">
                                                                <label for="sigleRecoverypin">Postalcode <span class="star">*</span></label>
                                                            </div>
                            
                                                            <div class="col s12 m6 l6">
                                                                <label>Country <span class="star">*</span></label>
                                                                <select id="country1" class="browser-default" class="mySelect"
                                                                    style="padding-left: 0;" required></select>
                                                            </div>
                            
                                                            <div class="col s12 m6 l6">
                                                                <label>Province <span class="star">*</span></label>
                                                                <select id="state1" class="browser-default" class="mySelect" style="padding-left: 0;"
                                                                    required></select>
                                                            </div>

                                                            <div class="col s6 m6 selectDropdwn">
                                                                <label for="sigleRecoverydeviceStatus">Device Status <span class="star">*</span></label>
                                                                <select id="sigleRecoverydeviceStatus" class="browser-default">
                                                                  <option value="" disabled selected>Device Status</option>
                                                                </select>
                                                              </div>

                                                            <div class="input-field col s12 m6">
                                                                <textarea id="sigleRecovery" class="materialize-textarea"></textarea>
                                                                <label for="sigleRecovery">Remark </label>
                                                            </div>
                                                            </div>
                                                        </div>
                                                    <p> Required Field are marked with <span class="star">*</span></p>


                                                    <div class="input-field col s12 center">
                                                        <button class="btn modal-trigger" data-target="submitStolen">Submit</button>
                                                        <a href="./stolenRecovery" class="btn modal-trigger"
                                                            style="margin-left: 10px;">Cancel</a>
                                                    </div>
                                                </form>
                                            </div>
                                    </div>
                                            <div id="bulkRecoveryDiv" class="col s12 m12" style="display: none">
                                                <form action="#" style="margin-top: 30px;" id="bulkRecoveryForm">
                                                    <div class="input-field col s12 m6 l6" style="margin-top: 20px;">
                                                        <input type="text" name="bulkRecoveryquantity" class="form-control boxBorder boxHeight"
                                                            id="bulkRecoveryquantity" maxlength="10" pattern=[0-9] title="Please enter your locality">
                                                        <label for="bulkRecoveryquantity">Quantity <span class="star">*</span></label>
                                                    </div>

                                                    <div class="input-field col s12 m6">
                                                        <textarea id="bulkRecoveryRemark" class="materialize-textarea"></textarea>
                                                        <label for="bulkRecoveryRemark">Remark</label>
                                                    </div>

                                                    <div class="file-field col s12 m6">
                                                        <h6 style="margin: 2px;">Upload File <span class="star">*</span></h6>
                                                        <div class="btn">
                                                            <span>select file</span>
                                                            <input type="file" id="bulkRecoveryFile" placeholder="Upload Photo">
                                                        </div>
                                                        <div class="file-path-wrapper">
                                                            <input class="file-path validate" type="text" id="bulkRecoveryFileName"
                                                                placeholder="Upload file" title="Please upload your photo">
                                                        </div>
                                                    </div>

                                                   <div class="col s12 m12">
                                                       <a href="">Download Sample Format</a>
                                                   </div>

                                                   <div class="col s12 m12" style="margin-top: 30px;">
                                                    <h5>Place Of Device Recovery</h5>
                                                    <hr>
                                                </div>
                                                    <!-- <div class="col s12 m12">
                                                        <p><b>Place Of Device Recovery</b></p>
                                                    </div> -->
                                                    <div class="input-field col s12 m12">
                                                        <input type="text" name="bulkRecoveryaddress" class="form-control boxBorder boxHeight"
                                                            id="bulkRecoveryaddress" pattern=[A-Za-z] title="Please enter your address">
                                                        <label for="bulkRecoveryaddress">Address(Property Location) <span class="star">*</span></label>
                                                    </div>
                    
                                                    <div class="input-field col s12 m6 l6">
                                                        <input type="text" name="bulkRecoverystreetNumber" class="form-control boxBorder boxHeight"
                                                            id="bulkRecoverystreetNumber" maxlength="30" pattern=[A-Za-z0-9] title="Please enter street number">
                                                        <label for="bulkRecoverystreetNumber">Street Number <span class="star">*</span></label>
                                                    </div>

                                                    <div class="input-field col s12 m6 l6">
                                                        <input type="text" name="bulkRecoveryvillage" id="bulkRecoveryvillage" maxlength="20">
                                                        <label for="bulkRecoveryvillage">Village <span class="star">*</span></label>
                                                    </div>
                    
                                                    <div class="input-field col s12 m6 l6">
                                                        <input type="text" name="bulkRecoverylocality" class="form-control boxBorder boxHeight"
                                                            id="bulkRecoverylocality" maxlength="20" pattern=[A-Za-z0-9] title="Please enter your locality">
                                                        <label for="bulkRecoverylocality">Locality <span class="star">*</span></label>
                                                    </div>
                        
                                                    <div class="input-field col s12 m6 l6">
                                                        <input type="text" name="bulkRecoverydistrict" id="bulkRecoverydistrict" maxlength="20">
                                                        <label for="bulkRecoverydistrict">District <span class="star">*</span></label>
                                                    </div>
                        
                                                    <div class="input-field col s12 m6 l6">
                                                        <input type="text" name="bulkRecoverycommune" id="bulkRecoverycommune" maxlength="20">
                                                        <label for="bulkRecoverycommune">Commune <span class="star">*</span></label>
                                                    </div>
                        
                                                    <div class="input-field col s12 m6 l6">
                                                        <input type="text" name="bulkRecoverypin" class="form-control boxBorder boxHeight"
                                                            id="bulkRecoverypin" maxlength="20">
                                                        <label for="bulkRecoverypin">Postalcode <span class="star">*</span></label>
                                                    </div>
                    
                                                    <div class="col s12 m6 l6">
                                                        <label>Country <span class="star">*</span></label>
                                                        <select id="bulkRecoverycountry" class="browser-default" class="mySelect"
                                                            style="padding-left: 0;" required></select>
                                                    </div>
                    
                                                    <div class="col s12 m6 l6">
                                                        <label>Province <span class="star">*</span></label>
                                                        <select id="bulkRecoverystate" class="browser-default" class="mySelect" style="padding-left: 0;"
                                                            required></select>
                                                    </div>

                                                    <div class="col s12 m12">
                                                        <p> Required Field are marked with <span class="star">*</p>
                                                    </div>

                                                    <div class="input-field col s12 center">
                                                        <button class="btn modal-trigger" data-target="submitStolen">Submit</button>
                                                        <a href="./stolenRecovery" class="btn modal-trigger"
                                                            style="margin-left: 10px;">Cancel</a>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--end container-->
            </section>


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
		src="${context}/resources/project_js/lawfulStolenRecovery.js"></script>
		
		<script>
    populateCountries(
        "country1",
        "state1"
    );
    populateStates(
        "country1",
        "state1"
    );
    
    populateCountries(
            "bulkRecoverycountry",
            "bulkRecoverystate"
        );
        populateStates(
            "bulkRecoverycountry",
            "bulkRecoverystate"
        );
</script>
		

</body>
</html>