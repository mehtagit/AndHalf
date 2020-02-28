<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
                                            <p class="PageHeading"> <spring:message code="registration.updatereportrecovery" /></p>
                                        </div>

                                      <!--   <div class="row">
                                            <div class="col s12">
                                                <ul class="tabs">
                                                    <li class="tab col s3"><a class="active" onclick="singleRecoverydiv()">Single</a>
                                                    </li>
                                                    <li class="tab col s3"><a onclick="showBulkRecovery()">Company/Organisation/Government</a></li>
                                                </ul>
                                            </div> -->
                                            <div id="singleRecoveryDiv" class="col s12" style="margin-top: 30px; display: none">
                                                <form action="#" id="singleRecoveryForm">
                                                    <div class="row">
                                                        <div class="col-s12 m12">
                                                             <div class="input-field col s12 m6" style="margin-top: 22px;">
                                                                <input type="text" name="sigleRecoverydeviceBrandName" id="sigleRecoverydeviceBrandName" maxlength="30">
                                                                <label for="sigleRecoverydeviceBrandName"> <spring:message code="registration.devicebrandname" /></label>
                                                            </div>

                                                            <div class="input-field col s12 m6" style="margin-top: 22px;">
                                                                <input type="text" name="sigleRecoveryimeiNumber" id="sigleRecoveryimeiNumber" maxlength="30">
                                                                <label for="sigleRecoveryimeiNumber"> <spring:message code="registration.imei/meid/esnnumber" /><span class="star">*</span></label>
                                                            </div>

                                                            <div class="col s6 m6 selectDropdwn">
                                                                <label for="sigleRecoverydeviceIDType"><spring:message code="select.deviceIDType" /> <span class="star">*</span></label>
                                                                <select id="sigleRecoverydeviceIDType" class="browser-default">
                                                                  <option value="" disabled selected><spring:message code="select.deviceIDType" /></option>
                                                                </select>
                                                              </div>

                                                              <div class="col s6 m6 selectDropdwn">
                                                                <label for="sigleRecoverydeviceType"> <spring:message code="select.deviceType" /></label>
                                                                <select class="browser-default" id="sigleRecoverydeviceType">
                                                                    <option value="" disabled selected><spring:message code="select.deviceType" /></option>
                                                                </select>
                                                              </div> 
                                                              
                                                              <div class="col s6 m6 selectDropdwn">
                                                                <label for="sigleRecoverydeviceSimStatus"> <spring:message code="select.multiSimStatus" /></label>
                                                                <select id="sigleRecoverydeviceSimStatus" class="browser-default">
                                                                  <option value="" disabled selected><spring:message code="select.multiSimStatus" /></option>
                                                                </select>
                                                              </div>

                                                              <div class="input-field col s12 m6" style="margin-top: 22px;">
                                                                <input type="text" name="sigleRecoveryserialNumber" id="sigleRecoveryserialNumber" maxlength="30">
                                                                <label for="sigleRecoveryserialNumber"><spring:message code="input.deviceSerialNumber" /></label>
                                                            </div>

                                                            <div class="col s12 m12" style="margin-top: 30px;">
                                                                <h5><spring:message code="registration.placeofdevicerecovery" /></h5>
                                                                <hr>
                                                            </div>
                                                            <!-- <div class="col s12 m12">
                                                                <p><b>Place Of Device Recovery</b></p>
                                                            </div> -->
                                                            <div class="input-field col s12 m12">
                                                                <input type="text" name="sigleRecoveryaddress" class="form-control boxBorder boxHeight"
                                                                     pattern=[A-Za-z] title="Please enter your address">
                                                                <label for="sigleRecoveryaddress"><spring:message code="input.address" /> <span class="star">*</span></label>
                                                            </div>
                            
                                                            <div class="input-field col s12 m6 l6">
                                                                <input type="text" name="sigleRecoverystreetNumber" class="form-control boxBorder boxHeight"
                                                                    id="sigleRecoverystreetNumber" maxlength="30" pattern=[A-Za-z0-9] title="Please enter street number">
                                                                <label for="sigleRecoverystreetNumber"><spring:message code="input.streetNumber" /> <span class="star">*</span></label>
                                                            </div>
        
                                                            <div class="input-field col s12 m6 l6">
                                                                <input type="text" name="sigleRecoveryvillage" id="sigleRecoveryvillage" maxlength="20">
                                                                <label for="sigleRecoveryvillage"><spring:message code="input.village" /> <span class="star">*</span></label>
                                                            </div>
                            
                                                            <div class="input-field col s12 m6 l6">
                                                                <input type="text" name="sigleRecoverylocality" class="form-control boxBorder boxHeight"
                                                                    id="sigleRecoverylocality" maxlength="20" pattern=[A-Za-z0-9] title="Please enter your locality">
                                                                <label for="sigleRecoverylocality"><spring:message code="input.locality" />  <span class="star">*</span></label>
                                                            </div>
                                
                                                            <div class="input-field col s12 m6 l6">
                                                                <input type="text" name="sigleRecoverydistrict" id="sigleRecoverydistrict" maxlength="20">
                                                                <label for="sigleRecoverydistrict"><spring:message code="input.district" /> <span class="star">*</span></label>
                                                            </div>
                                
                                                            <div class="input-field col s12 m6 l6">
                                                                <input type="text" name="sigleRecoverycommune" id="sigleRecoverycommune" maxlength="20">
                                                                <label for="sigleRecoverycommune"><spring:message code="input.commune" /> <span class="star">*</span></label>
                                                            </div>
                                
                                                            <div class="input-field col s12 m6 l6">
                                                                <input type="text" name="sigleRecoverypin" class="form-control boxBorder boxHeight"
                                                                    id="sigleRecoverypin" maxlength="20">
                                                                <label for="sigleRecoverypin"><spring:message code="input.postalCode" /> <span class="star">*</span></label>
                                                            </div>
                            
                                                            <div class="col s12 m6 l6">
                                                                <label><spring:message code="table.country" /> <span class="star">*</span></label>
                                                                <select id="country1" class="browser-default" class="mySelect"
                                                                    style="padding-left: 0;" required></select>
                                                            </div>
                            
                                                            <div class="col s12 m6 l6">
                                                                <label> <spring:message code="input.province" /><span class="star">*</span></label>
                                                                <select id="state1" class="browser-default" class="mySelect" style="padding-left: 0;"
                                                                    required></select>
                                                            </div>

                                                            <div class="col s6 m6 selectDropdwn">
                                                                <label for="sigleRecoverydeviceStatus"><spring:message code="select.deviceStatus" /> <span class="star">*</span></label>
                                                                <select id="sigleRecoverydeviceStatus" class="browser-default">
                                                                  <option value="" disabled selected><spring:message code="select.deviceStatus" /></option>
                                                                </select>
                                                              </div>

                                                            <div class="input-field col s12 m6">
                                                                <textarea id="sigleRecovery" class="materialize-textarea"></textarea>
                                                                <label for="sigleRecovery"><spring:message code="input.remarks" /> </label>
                                                            </div>
                                                            </div>
                                                        </div>
                                                    <p> <spring:message code="input.requiredfields" /> <span class="star">*</span></p>


                                                    <div class="input-field col s12 center">
                                                        <button class="btn modal-trigger" data-target="submitStolen"><spring:message code="button.submit" /></button>
                                                        <a href="./stolenRecovery" class="btn modal-trigger"
                                                            style="margin-left: 10px;"><spring:message code="modal.cancel" /></a>
                                                    </div>
                                                </form>
                                            </div>
                                    </div>
<!-- ---------------------------------------------------------- bulk recovery edit------------------------------------------------------------------------- -->                                    
                                           
                                            <div id="bulkRecoveryDiv" class="col s12 m12" style="display: block">
                                                <form action="#" style="margin-top: 30px;" onsubmit="return updateCompanyRecoveryRequest()" method="POST" enctype="multipart/form-data"  id="">
                                                    <div class="input-field col s12 m6 l6" style="margin-top: 20px;">
                                                        <input type="text" name="bulkRecoveryquantity" class="form-control boxBorder boxHeight" placeholder=""
                                                            id="bulkRecoveryquantity" maxlength="7" pattern="[0-9]{1,7}" 
                                                            title="Please enter number up to 7 digits">
                                                        <label for="bulkRecoveryquantity"><spring:message code="operator.quantity" /> <span class="star">*</span></label>
                                                    </div>
<input type="text" id="pageViewType" value="${viewType}" style="display: none;">
<input type="text" id="existingStolenTxnId" style="display:none" value="${stolenTxnId}" >
                                                    <div class="input-field col s12 m6">
                                                        <textarea id="bulkRecoveryRemark" maxlength="200000" class="materialize-textarea" placeholder=""></textarea>
                                                        <label for="bulkRecoveryRemark"><spring:message code="input.remarks" /></label>
                                                    </div>

                                                    <div class="file-field col s12 m6">
                                                        <h6 style="margin: 2px;"><spring:message code="registration.uploadfile" /></h6>
                                                        <div class="btn">
                                                            <span><spring:message code="input.selectfile" /></span>
                                                            <input type="file" id="bulkRecoveryFile" placeholder="Upload Photo">
                                                        </div>
                                                        <div class="file-path-wrapper">
                                                            <input class="file-path validate" type="text" id="bulkRecoveryFileName" placeholder=""
                                                                placeholder="Upload file" title="Please upload your photo">
                                                        </div>
                                                    </div>
                                                    
                                                    
                                                     <div class="col s12 m6">
<p style="margin-top: 3px; margin-bottom: 5px"><spring:message code="operator.blocking" /></p>
<label style="margin-right: 2%;"> <input type="radio" class="blocktypeRadio" id=""
value="Immediate"
onclick="document.getElementById('calender').style.display = 'none';"
name="stolenBlockPeriod" checked><spring:message code="operator.immediate" />
</label> <label style="margin-right: 2%;"> <input type="radio" class="blocktypeRadio"
value="Default"
onclick="document.getElementById('calender').style.display = 'none';"
name="stolenBlockPeriod"><spring:message code="operator.default" />
</label> <label> <input type="radio" required="required" value="tilldate" class="blocktypeRadio"
onclick="document.getElementById('calender').style.display = 'block';"
name="stolenBlockPeriod"><spring:message code="operator.later" />
</label>
<div class="col s6 m2 responsiveDiv"
style="display: none; width: 30%; margin-right: 30%; float: right; margin-top: -15px" id="calender">
<div id="startdatepicker" class="input-group date">
<input type="text" id="stolenDatePeriod"
style="margin-top: -9px" /> <span class="input-group-addon"
style="color: #ff4081"><i class="fa fa-calendar"
aria-hidden="true" style="float: right; margin-top: -30px;"></i></span>
</div>

</div>


<div class="col s12 m2 l2" style="width: 40%; display: none; float: right; margin-right:30%;"
id="stolenDate">

<label for="TotalPrice" class="center-align"><spring:message code="operator.tilldate" /></label>
<div id="startdatepicker" class="input-group" style="margin-top: 10px;">

<input class="form-control" name="inputsaves" type="text"
id="startDateFilter" readonly /> <span class="input-group-addon"
style="color: #ff4081"><i
class="glyphicon glyphicon-calendar"
onclick="_Services._selectstartDate()"></i></span>
</div>
</div>
</div>

                                                   <div class="col s12 m12">
                                                       <a href="./Consignment/sampleFileDownload/7"><spring:message code="input.downlaod.sample" /></a>
                                                   </div>

                                                   <div class="col s12 m12" style="margin-top: 30px;">
                                                    <h5><spring:message code="registration.placeofdevicerecovery" /></h5>
                                                    <hr>
                                                </div>
                                                    <!-- <div class="col s12 m12">
                                                        <p><b>Place Of Device Recovery</b></p>
                                                    </div> -->
                                                    <div class="input-field col s12 m12">
                                                        <input type="text" name="bulkRecoveryaddress" class="form-control boxBorder boxHeight" placeholder=""
                                                            id="bulkRecoveryaddress" pattern="[^[a-zA-Z0-9\s,'-]*$]{0,200}" 
                                                            title="Please enter alphabets and numbers upto 200 characters only"
                                                             maxlength="200" required="required">
                                                        <label for="bulkRecoveryaddress"><spring:message code="input.address" /> <span class="star">*</span></label>
                                                    </div>
                    
                                                    <div class="input-field col s12 m6 l6">
                                                        <input type="text" name="bulkRecoverystreetNumber" class="form-control boxBorder boxHeight" placeholder=""
                                                            id="bulkRecoverystreetNumber" pattern="[^[a-zA-Z0-9\s,'-]*$]{0,50}" title="Please enter alphabets and numbers upto 50 characters only"
                                                             maxlength="50" required="required">
                                                        <label for="bulkRecoverystreetNumber"><spring:message code="input.streetNumber" /> <span class="star">*</span></label>
                                                    </div>

                                                    <div class="input-field col s12 m6 l6">
                                                        <input type="text" name="bulkRecoveryvillage" id="bulkRecoveryvillage" placeholder="" pattern="[^[a-zA-Z0-9\s,'-]*$]{0,50}" title="Please enter alphabets and numbers upto 50 characters only"
                                                             maxlength="50" required="required">
                                                        <label for="bulkRecoveryvillage"><spring:message code="input.village" /> <span class="star">*</span></label>
                                                    </div>
                    
                                                    <div class="input-field col s12 m6 l6">
                                                        <input type="text" name="bulkRecoverylocality" class="form-control boxBorder boxHeight" placeholder=""
                                                            id="bulkRecoverylocality" pattern="[^[a-zA-Z0-9\s,'-]*$]{0,50}" title="Please enter alphabets and numbers upto 50 characters only"
                                                             maxlength="50" required="required">
                                                        <label for="bulkRecoverylocality"> <spring:message code="input.locality" /><span class="star">*</span></label>
                                                    </div>
                        
                                                    <div class="input-field col s12 m6 l6">
                                                        <input type="text" name="bulkRecoverydistrict" id="bulkRecoverydistrict" placeholder="" pattern="[^[a-zA-Z0-9\s,'-]*$]{0,50}" title="Please enter alphabets and numbers upto 50 characters only"
                                                             maxlength="50" required="required">
                                                        <label for="bulkRecoverydistrict"><spring:message code="input.district" /> <span class="star">*</span></label>
                                                    </div>
                        
                                                    <div class="input-field col s12 m6 l6">
                                                        <input type="text" name="bulkRecoverycommune" id="bulkRecoverycommune" placeholder="" pattern="[^[a-zA-Z0-9\s,'-]*$]{0,50}" title="Please enter alphabets and numbers upto 50 characters only"
                                                             maxlength="50" required="required">
                                                        <label for="bulkRecoverycommune"> <spring:message code="input.commune" /><span class="star">*</span></label>
                                                    </div>
                        
                                                    <div class="input-field col s12 m6 l6">
                                                        <input type="text" name="bulkRecoverypin"  placeholder="" class="form-control boxBorder boxHeight"
                                                            id="bulkRecoverypin" pattern="[0-9]{0,20}" title="Please enter  numbers upto 20 digits only" 
                                                        maxlength="20" required="required">
                                                        <label for="bulkRecoverypin"> <spring:message code="input.postalCode" /><span class="star">*</span></label>
                                                    </div>
                    
                                                    <div class="col s12 m6 l6">
                                                        <label><spring:message code="table.country" /> <span class="star">*</span></label>
                                                        <select id="bulkRecoverycountry" class="browser-default" class="mySelect"
                                                            style="padding-left: 0;" required></select>
                                                    </div>
                    
                                                    <div class="col s12 m6 l6">
                                                        <label> <spring:message code="input.province" /><span class="star">*</span></label>
                                                        <select id="bulkRecoverystate" class="browser-default" class="mySelect" style="padding-left: 0;"
                                                            required></select>
                                                    </div>
                                                    
                                                     <div class="input-field col s12 m6"> 
											<input type="text" name="deviceRecoveryDevice" placeholder=""
												id='bulkRecoveryDate' class='form-control datepick'
												autocomplete='off' 
												title="<spring:message code="validation.requiredMsg" />"  required /> 
												<label
												for="deviceRecoveryDevice" class="center-align"><spring:message code="operator.recoveryDate" /> <span class="star">*</span>
											</label> <span class="input-group-addon" style="color: #ff4081"><i
												class="fa fa-calendar" aria-hidden="true"></i></span>
										</div>

                                                    <div class="col s12 m12">
                                                        <p><spring:message code="input.requiredfields" /> <span class="star">*</span></p>
                                                    </div>

                                                    <div class="input-field col s12 center">
                                                        <button class="btn" type="submit"><spring:message code="button.submit" /></button>
                                                        <a href="./stolenRecovery" class="btn modal-trigger"
                                                            style="margin-left: 10px;"><spring:message code="modal.cancel" /></a>
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
                
                 	 <div id="stolenSucessPopUp" class="modal">
  <h6 class="modal-header"><spring:message code="registration.updatereportstolen" /></h6>
        <div class="modal-content">
           
            <div class="row">
                <h6 id="dynamicMessage"><spring:message code="input.Theupdated" /></h6>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <div class="input-field col s12 center">
                        <a  href ="./stolenRecovery" class=" btn"><spring:message code="modal.ok" /></a>
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
		src="${context}/resources/project_js/editCompanyRecovery.js"></script>
		<script type="text/javascript"
		src="${context}/resources/project_js/lawfulStolenRecovery.js"></script>
		
		<script>
		$('.datepick').datepicker({
			dateFormat : "yy-mm-dd"
		});
		
		$('#stolenDatePeriod').datepicker({
        	dateFormat: "yy-mm-dd"
        	});


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
        
        
        window.onload = function () {
            if($('#pageViewType').val()=='view')
            	{
            	$('#headingType').text('');
            	$('#headingType').text('View Report Recovery');
            	  $("#bulkRecoveryDiv").find("input,select,textarea,button").prop("disabled",true);
            	}
            else{
            	$('#headingType').text('');
            	$('#headingType').text(' Update Report Recovery');
            	  $("#bulkRecoveryDiv").find("input,select,textarea,button").prop("disabled",false);
            }
          
      }
</script>
		

</body>
</html>