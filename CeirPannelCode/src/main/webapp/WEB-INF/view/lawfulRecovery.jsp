<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setDateHeader("Expires", 0);
	response.setHeader("Pragma", "no-cache");
	/* session.setMaxInactiveInterval(200); //200 secs
	 session.setAttribute("usertype", null);   */
	if (session.getAttribute("usertype") != null) {
%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<title>Dashboard</title>
<meta http-equiv='cache-control' content='no-cache'>
<meta http-equiv='expires' content='-1'>
<meta http-equiv='pragma' content='no-cache'>
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
        
        .blockingType {
height: 4.6rem;
margin-bottom: 5px;
}

textarea.materialize-textarea {
	height: auto;
	max-height: 300px;s
}

select.browser-default {
    height: 34px;
    margin-bottom: 4px;
}

    </style>



</head>

<body data-id="5" data-roleType="${usertype}" data-userTypeID="${usertypeId}"
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
                                            <p class="PageHeading"><spring:message code="registration.reportrecovery" /></p>
                                        </div>

                                        <div class="row">
                                            <div class="col s12">
                                                <ul class="tabs">
                                                    <li class="tab col s3"><a class="active" onclick="singleRecoverydiv()"><spring:message code="operator.single" /></a>
                                                    </li>
                                                    <li class="tab col s3"><a onclick="showBulkRecovery()"><spring:message code="registration.company/organisation/government" /></a></li>
                                                </ul>
                                            </div>
                                            <div id="singleRecoveryDiv" class="col s12" style="margin-top: 30px; display: block">
                                                <form action="" id="" onsubmit="return saveIndivisualRecoveryRequest()" method="POST" enctype="multipart/form-data">
                                                    <div class="row">
                                                        <div class="col-s12 m12">
                                                             <div class="input-field col s12 m6">
                                                                <input type="text" name="sigleRecoverydeviceBrandName" id="sigleRecoverydeviceBrandName" pattern="[a-zA-Z]{0,30}" 
                                                     oninput="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');" 
                                                     oninvalid="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');"
                                                                 maxlength="30">
                                                                <label for="sigleRecoverydeviceBrandName"><spring:message code="registration.devicebrandname" /></label>
                                                            </div>
<%-- 
                                                            <div class="input-field col s12 m6" style="margin-top: 22px;">
                                                                <input type="text" name="sigleRecoveryimeiNumber" id="sigleRecoveryimeiNumber" 
                                                                 pattern="[0-9]{15,16}" 
                                                               oninput="InvalidMsg(this,'input');" oninvalid="InvalidMsg(this,'input');"
                                                                title= "<spring:message code="validation.1516digit" />" required  maxlength="16" />
                                                                <label for="sigleRecoveryimeiNumber"> <spring:message code="registration.imei/meid/esnnumber" /><span class="star"> *</span></label>
                                                            </div> --%>

                                                            <div class="col s6 m6 ">
                                                                <label for="sigleRecoverydeviceIDType"><spring:message code="select.deviceIDType" /> <span class="star"> *</span></label>
                                                                <select id="sigleRecoverydeviceIDType" class="browser-default"
                                                                oninput="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');" 
                                                                oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
                                                       				 required  >
                                                                  <option value="" disabled selected><spring:message code="select.deviceIDType" /></option>
                                                                </select>
                                                              </div>

                                                              <div class="col s6 m6 ">
                                                                <label for="sigleRecoverydeviceType"><spring:message code="select.deviceType" /> </label>
                                                                <select class="browser-default" id="sigleRecoverydeviceType"
                                                                 oninput="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');" 
                                                                 oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');">
                                                               <option value="" disabled selected><spring:message code="select.deviceType" /></option>
                                                                </select>
                                                              </div> 
                                                              
                                                              <div class="col s6 m6 ">
                                                                <label for="sigleRecoverydeviceSimStatus"> <spring:message code="select.multiSimStatus" /></label>
                                                                <select id="sigleRecoverydeviceSimStatus" class="browser-default"
                                                                oninput="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');" 
                                                                oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');">
                                                                <option value="" disabled selected><spring:message code="select.multiSimStatus" /></option>
                                                                </select>
                                                              </div>

                                                              <div class="input-field col s12 m6" style="margin-top: 22px;">
                                                                <input type="text" name="sigleRecoveryserialNumber" id="sigleRecoveryserialNumber" pattern="[a-zA-Z0-9]{0,15}" 
                                                          oninput="InvalidMsg(this,'input','<spring:message code="validation.15serialNo" />');" 
                                                          oninvalid="InvalidMsg(this,'input','<spring:message code="validation.15serialNo" />');"
                                                                 maxlength="15">
                                                                <label for="sigleRecoveryserialNumber"><spring:message code="input.deviceSerialNumber" /></label>
                                                            </div>
                                                            
                                                            <div class="col s12 m12" style="margin-top: 10px; font-weight: bold;">
															<h6><spring:message code="registration.imei/meid/esnnumber" /></h6>
														</div>

														<div class="input-field col s12 m6">
															<input type="text" name="sigleRecoveryimeiNumber1" pattern="[0-9]{15,16}" 
												oninput="InvalidMsg(this,'input','<spring:message code="validation.1516digit" />');" 
												oninvalid="InvalidMsg(this,'input','<spring:message code="validation.1516digit" />');"
														  maxlength="16" id="sigleRecoveryimeiNumber" required/> 
															<label for="sigleRecoveryimeiNumber1"><spring:message code="registration.one" /> <span class="star"> *</span></label>
														</div>
														
														<div class="input-field col s12 m6">
															<input type="text" name="imeiNumberTwo" pattern="[0-9]{15,16}" 
															oninput="InvalidMsg(this,'input','<spring:message code="validation.1516digit" />');" 
															oninvalid="InvalidMsg(this,'input','<spring:message code="validation.1516digit" />');"
													  maxlength="16" 	id="sigleRecoveryimeiNumber2" > <label
																for="sigleRecoveryimeiNumber2"><spring:message code="registration.two" /></label>
														</div>
														
														<div class="input-field col s12 m6">
															<input type="text" name="imeiNumberThree" pattern="[0-9]{15,16}" 
															oninput="InvalidMsg(this,'input','<spring:message code="validation.1516digit" />');" 
															oninvalid="InvalidMsg(this,'input','<spring:message code="validation.1516digit" />');"
														  maxlength="16" 	id="sigleRecoveryimeiNumber3" > <label
																for="sigleRecoveryimeiNumber3"><spring:message code="registration.three" /></label>
														</div>
														
														<div class="input-field col s12 m6">
															<input type="text" name="imeiNumberFour" pattern="[0-9]{15,16}" 
															oninput="InvalidMsg(this,'input','<spring:message code="validation.1516digit" />');" 
															oninvalid="InvalidMsg(this,'input','<spring:message code="validation.1516digit" />');"
															  maxlength="16" 	id="sigleRecoveryimeiNumber4" > <label
																for="sigleRecoveryimeiNumber4"><spring:message code="registration.four" /></label>
														</div>

                                                            <div class="col s12 m12" style="margin-top: 30px;">
                                                                <h5><spring:message code="registration.placeofdevicerecovery" /></h5>
                                                                <hr>
                                                            </div>
                                                            <!-- <div class="col s12 m12">
                                                                <p><b>Place Of Device Recovery</b></p>
                                                            </div> -->
                                                            <div class="input-field col s12 m12">
                                                                <input type="text" id="sigleRecoveryaddress" name="sigleRecoveryaddress" class="form-control boxBorder boxHeight"
                                                                     pattern="[^[a-zA-Z0-9\s,'-]*$]{0,200}" 
                                                       oninput="InvalidMsg(this,'input','<spring:message code="validation.200characters" />');" 
                                                       oninvalid="InvalidMsg(this,'input','<spring:message code="validation.200characters" />');"
                                                        		  maxlength="200" required />
                                                                <label for="sigleRecoveryaddress"> <spring:message code="input.address" /><span class="star"> *</span></label>
                                                            </div>
                            
                                                            <div class="input-field col s12 m6 l6">
                                                                <input type="text" name="sigleRecoverystreetNumber" class="form-control boxBorder boxHeight"
                                                                    id="sigleRecoverystreetNumber" maxlength="20" pattern="[^[a-zA-Z0-9\s,'-]*$]{0,20}" 
                                                                oninput="InvalidMsg(this,'input','<spring:message code="validation.20Character" />');" 
                                                                oninvalid="InvalidMsg(this,'input','<spring:message code="validation.20Character" />');"
                                                                required />
                                                                <label for="sigleRecoverystreetNumber"> <spring:message code="input.streetNumber" /><span class="star"> *</span></label>
                                                            </div>
        
                                                            <div class="input-field col s12 m6 l6">
                                                                <input type="text" name="sigleRecoveryvillage" id="sigleRecoveryvillage" maxlength="30" pattern="[^[a-zA-Z0-9\s,'-]*$]{0,30}" 
                                                                   oninput="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');"
                                                                   oninvalid="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');"
                                                               required />
                                                        
                                                                <label for="sigleRecoveryvillage"> <spring:message code="input.village" /><span class="star"> *</span></label>
                                                            </div>
                            
                                                            <div class="input-field col s12 m6 l6">
                                                                <input type="text" name="sigleRecoverylocality" class="form-control boxBorder boxHeight"
                                                                    id="sigleRecoverylocality" maxlength="30" pattern="[^[a-zA-Z0-9\s,'-]*$]{0,30}" 
                                                                    oninput="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');" 
                                                                    oninvalid="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');"
                                                              	 required />
                                                                <label for="sigleRecoverylocality"><spring:message code="input.locality" /> <span class="star"> *</span></label>
                                                            </div>
                                
                                                            <div class="input-field col s12 m6 l6">
                                                                <input type="text" name="sigleRecoverydistrict" id="sigleRecoverydistrict" pattern="[^[a-zA-Z0-9\s,'-]*$]{0,30}" 
                                                                    oninput="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');"
                                                                    oninvalid="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');"
                                                              required />
                                                               <label for="sigleRecoverydistrict"><spring:message code="input.district" /> <span class="star"> *</span></label>
                                                            </div>
                                
                                                            <div class="input-field col s12 m6 l6">
                                                                <input type="text" name="sigleRecoverycommune" id="sigleRecoverycommune" maxlength="30" pattern="[^[a-zA-Z0-9\s,'-]*$]{0,30}" 
                                                                    oninput="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');" 
                                                                    oninvalid="InvalidMsg(this,'input','<spring:message code="validation.30characters" />');"
                                                                 required />
																<label for="sigleRecoverycommune"> <spring:message code="input.commune" /><span class="star"> *</span></label>
                                                            </div>
                                
                                                            <div class="input-field col s12 m6 l6">
                                                                <input type="text" name="sigleRecoverypin" class="form-control boxBorder boxHeight"
                                                                    id="sigleRecoverypin" maxlength="6" pattern="[0-9]{0,6}"
                                                                    oninput="InvalidMsg(this,'input','<spring:message code="validation.postalcode" />');" 
                                                                    oninvalid="InvalidMsg(this,'input','<spring:message code="validation.postalcode" />');"
                                                              required />
                                      					<label for="sigleRecoverypin"> <spring:message code="input.postalCode" /><span class="star"> *</span></label>
                                                            </div>
                            
                                                            <div class="col s12 m6 l6">
                                                                <label><spring:message code="table.country" /> <span class="star">*</span></label>
                                                                <select id="country1" class="browser-default" class="mySelect"
                                                                oninput="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');" 
                                                                oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
                                                                required  style="padding-left: 0;" ></select>
                                                            </div>
                            
                                                            <div class="col s12 m6 l6"> 
                                                                <label> <spring:message code="input.province" /><span class="star"> *</span></label>
                                                                <select id="state1" class="browser-default" class="mySelect" style="padding-left: 0;"
                                                       oninput="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');" 
                                                       oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
                                                                required></select>
                                                            </div>

                                                            <div class="col s6 m6 ">
                                                                <label for="sigleRecoverydeviceStatus"><spring:message code="select.deviceStatus" /> <span class="star"> *</span></label>
                                                                <select id="sigleRecoverydeviceStatus" class="browser-default" 
                                                                oninput="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');" 
                                                                oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
                                                               required >
                                                        	 <option value="" disabled selected><spring:message code="select.deviceStatus" /></option>
                                                                </select>
                                                              </div>





														 <div class="col s12 m6 blockingType">
<p style="margin-top: 3px; margin-bottom: 5px"><spring:message code="operator.blocking" /></p>
<label style="margin-right: 2%;"> <input type="radio" class="blocktypeRadio" id=""
value="Immediate"
onclick="document.getElementById('SingleRecoverycalender').style.display = 'none';"
name="singleRecoveryPeriod" checked><spring:message code="operator.immediate" />
</label> <label style="margin-right: 2%;"> <input type="radio" class="blocktypeRadio"
value="Default"
onclick="document.getElementById('SingleRecoverycalender').style.display = 'none';"
name="singleRecoveryPeriod"><spring:message code="operator.default" />
</label> <label> <input type="radio" required="required" value="tilldate" class="blocktypeRadio"
onclick="document.getElementById('SingleRecoverycalender').style.display = 'block';"
name="singleRecoveryPeriod"><spring:message code="operator.later" />
</label>
<div class="col s6 m2 responsiveDiv"
style="display: none; width: 30%; margin-right: 30%; float: right; margin-top: -15px" id="SingleRecoverycalender">
<div id="startdatepicker" class="input-group date">
<input type="text" id="singleRecoveryDatePeriod"
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

<div class="input-field col s12 m6">
											<input type="text" name="deviceRecoveryDevice"
												id='IndivisualRecoveryDevice' class='form-control datepick'
												autocomplete='off' 
												oninput="InvalidMsg(this,'input','<spring:message code="validation.requiredMsg" />');" 
												oninvalid="InvalidMsg(this,'input','<spring:message code="validation.requiredMsg" />');"
												 required /> 
												<label
												for="deviceRecoveryDevice" class="center-align"><spring:message code="operator.recoveryDate" /> <span class="star">*</span>
											</label> <span class="input-group-addon" style="color: #ff4081"><i
												class="fa fa-calendar" aria-hidden="true"></i></span>
										</div>
										

										
                                                            <div class="input-field col s12 m12">
                                                                <textarea id="sigleRecovery" 
                                                                oninput="InvalidMsg(this,'input','<spring:message code="validation.10000characters" />');" 
                                                                oninvalid="InvalidMsg(this,'input','<spring:message code="validation.10000characters" />');"
                                                         	maxlength="10000" class="materialize-textarea"></textarea>
                                                                <label for="sigleRecovery"><spring:message code="input.remarks" /> </label>
                                                            </div>
                                                            </div>
                                                        </div>
                                                    <p> <spring:message code="input.requiredfields" /> <span class="star"> *</span></p>


                                                    <div class="input-field col s12 center">
                                                        <button class="btn" type="submit"><spring:message code="button.submit" /></button>
                                                        <a href="./stolenRecovery" class="btn modal-trigger"
                                                            style="margin-left: 10px;"><spring:message code="modal.cancel" /></a>
                                                    </div>
                                                </form>
                                            </div>
                                    </div>
                                            <div id="bulkRecoveryDiv" class="col s12 m12" style="display: none">
                                                <form action="#" style="margin-top: 30px;" onsubmit="return saveCompanyRecoveryRequest()" method="POST" enctype="multipart/form-data"  id="bulkRecoveryForm">
                                                    <div class="input-field col s12 m6 l6" style="margin-top: 20px;">
                                                        <input type="text" name="bulkRecoveryquantity" class="form-control boxBorder boxHeight"
                                                            id="bulkRecoveryquantity" required id="deviceBulkStolenquantity" maxlength="7" pattern="[0-9]{1,7}" 
                                                            oninput="InvalidMsg(this,'input','<spring:message code="validation.7digits" />');" 
                                                            oninvalid="InvalidMsg(this,'input','<spring:message code="validation.7digits" />');"
                                                             required>
														 <label for="bulkRecoveryquantity"><spring:message code="input.quantity" /> <span class="star"> *</span></label>
                                                    </div>

                                                    <div class="file-field col s12 m6">
                                                        <h6 style="margin: 2px;"><spring:message code="registration.uploadfile" /> <span class="star"> *</span></h6>
                                                        <div class="btn">
                                                            <span><spring:message code="input.selectfile" /></span>
                                                            <input type="file" id="bulkRecoveryFile" 
                                                            oninput="InvalidMsg(this,'fileType','<spring:message code="validation.NoChosen" />');" 
                                                            oninvalid="InvalidMsg(this,'fileType','<spring:message code="validation.NoChosen" />');"
                                                             required  accept=".csv" placeholder="Upload Photo">
                                                        </div>
                                                        <div class="file-path-wrapper">
                                                            <input class="file-path validate" type="text" id="bulkRecoveryFileName"
                                                                placeholder="<spring:message code="registration.uploadfile" />" title="Please upload your photo">
                                                        </div>
                                                    </div>
                            
                                                    <div class="input-field col s12 m12">
                                                        <textarea id="bulkRecoveryRemark"  
                                                         oninput="InvalidMsg(this,'input','<spring:message code="validation.10000characters" />');"
                                                          oninvalid="InvalidMsg(this,'input','<spring:message code="validation.10000characters" />');"
                                                          maxlength="10000" class="materialize-textarea" style="height: auto; max-height:300px;"></textarea>
                                                        <label for="bulkRecoveryRemark"><spring:message code="input.remarks" /></label>
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
                                                        <input type="text" name="bulkRecoveryaddress" class="form-control boxBorder boxHeight"
                                                            id="bulkRecoveryaddress" pattern="[^[a-zA-Z0-9\s,'-]*$]{0,200}" 
                                                           oninput="InvalidMsg(this,'input','<spring:message code="validation.200character" />');"
                                                           oninvalid="InvalidMsg(this,'input','<spring:message code="validation.200character" />');"
                                                            required  maxlength="200" >
                                                        <label for="bulkRecoveryaddress"><spring:message code="input.address" /> <span class="star"> *</span></label>
                                                    </div>
                    
                                                    <div class="input-field col s12 m6 l6">
                                                        <input type="text" name="bulkRecoverystreetNumber" class="form-control boxBorder boxHeight"
                                                            id="bulkRecoverystreetNumber" pattern="[^[a-zA-Z0-9\s,'-]*$]{0,30}" 
                                                      oninput="InvalidMsg(this,'input','<spring:message code="validation.address30characters" />');"
                                                       oninvalid="InvalidMsg(this,'input','<spring:message code="validation.address30characters" />');"
                                                       required  maxlength="30">
                                                        <label for="bulkRecoverystreetNumber"><spring:message code="input.streetNumber" /> <span class="star"> *</span></label>
                                                    </div>

                                                    <div class="input-field col s12 m6 l6">
                                                        <input type="text" name="bulkRecoveryvillage" id="bulkRecoveryvillage" pattern="[^[a-zA-Z0-9\s,'-]*$]{0,30}" 
                                                      oninput="InvalidMsg(this,'input','<spring:message code="validation.address30characters" />');" 
                                                      oninvalid="InvalidMsg(this,'input','<spring:message code="validation.address30characters" />');"
                                                        required  maxlength="30">   
                                                        <label for="bulkRecoveryvillage"><spring:message code="input.village" /> <span class="star"> *</span></label>
                                                    </div>
                    
                                                    <div class="input-field col s12 m6 l6">
                                                        <input type="text" name="bulkRecoverylocality" class="form-control boxBorder boxHeight"
                                                            id="bulkRecoverylocality" maxlength="50" pattern="[^[a-zA-Z0-9\s,'-]*$]{0,50}" 
                                                         oninput="InvalidMsg(this,'input','<spring:message code="validation.address30characters" />');" 
                                                         oninvalid="InvalidMsg(this,'input','<spring:message code="validation.address30characters" />');"
                                                       required  maxlength="30">
                                                        <label for="bulkRecoverylocality"><spring:message code="input.locality" /><span class="star"> *</span></label>
                                                    </div>
                        
                                                    <div class="input-field col s12 m6 l6">
                                                        <input type="text" name="bulkRecoverydistrict" id="bulkRecoverydistrict" pattern="[^[a-zA-Z0-9\s,'-]*$]{0,30}" 
                                                         oninput="InvalidMsg(this,'input','<spring:message code="validation.address30characters" />');"
                                                          oninvalid="InvalidMsg(this,'input','<spring:message code="validation.address30characters" />');"
                                                        required maxlength="30">
                                                       <label for="bulkRecoverydistrict"> <spring:message code="input.district" /><span class="star"> *</span></label>
                                                    </div>
                        
                                                    <div class="input-field col s12 m6 l6">
                                                        <input type="text" name="bulkRecoverycommune" id="bulkRecoverycommune" pattern="[^[a-zA-Z0-9\s,'-]*$]{0,30}" 
                                                         oninput="InvalidMsg(this,'input','<spring:message code="validation.address30characters" />');" 
                                                         oninvalid="InvalidMsg(this,'input','<spring:message code="validation.address30characters" />');"
                                                         required  maxlength="30">
                                                        <label for="bulkRecoverycommune"><spring:message code="input.commune" /><span class="star"> *</span></label>
                                                    </div>
                        
                                                    <div class="input-field col s12 m6 l6">
                                                        <input type="text" name="bulkRecoverypin" class="form-control boxBorder boxHeight"
                                                            id="bulkRecoverypin" pattern="[0-9]{0,20}" 
                                                             oninput="InvalidMsg(this,'input','<spring:message code="validation.postalcode" />');" 
                                                             oninvalid="InvalidMsg(this,'input','<spring:message code="validation.postalcode" />');"
                                                        required  maxlength="6">
                                                        <label for="bulkRecoverypin"> <spring:message code="input.postalCode" /><span class="star"> *</span></label>
                                                    </div>
                    
                                                    <div class="col s12 m6 l6">
                                                        <label> <spring:message code="table.country" /><span class="star"> *</span></label>
                                                        <select id="bulkRecoverycountry" class="browser-default" class="mySelect"
                                                        oninput="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');" 
                                                        oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
                                                 required  style="padding-left: 0;"></select>
                                                    </div>
                    
                                                    <div class="col s12 m6 l6">
                                                        <label><spring:message code="input.province" /> <span class="star"> *</span></label>
                                                        <select id="bulkRecoverystate" required class="browser-default" class="mySelect" style="padding-left: 0;"
                                                      oninput="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');" 
                                                      oninvalid="InvalidMsg(this,'select','<spring:message code="validation.selectFieldMsg" />');"
                                                        required></select>
                                                    </div>
                                                    
                                                    <div class="input-field col s12 m6">
											<input type="text" name="deviceRecoveryDevice"
												id='bulkRecoveryDate' class='form-control datepick'
												autocomplete='off' 
												title="<spring:message code="validation.requiredMsg" />"  required /> 
												<label
												for="deviceRecoveryDevice" class="center-align"><spring:message code="operator.recoveryDate" /> <span class="star">*</span>
											</label> <span class="input-group-addon" style="color: #ff4081"><i
												class="fa fa-calendar" aria-hidden="true"></i></span>
										</div>

                                                    <div class="col s12 m12">
                                                        <p> <spring:message code="input.requiredfields" /><span class="star"> *</span></p>
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
                
             <div class="modal" id="IndivisualStolenSucessPopup">
            <h6 class="modal-header"> <spring:message code="sidebar.Stolen/Recovery"/></h6>
            <div class="row" style="margin-top: 30px; padding: 0 20px;">
               <div class=" col s12 m12">
               	 <h6 id="sucessMessage"> <spring:message code="input.StolenSucessMessage1"/> <span id="IndivisualStolenTxnId"></span> <spring:message code="input.StolenSucessMessage2"/></h6>
                <div class="input-field col s12 center" style="margin:20px 0;">
                    <a href="./stolenRecovery" class="btn" style="margin:20px 0;"><spring:message code="modal.ok"/></a>
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
		src="${context}/resources/project_js/lawfulStolenRecovery.js"></script>
		<script type="text/javascript"
		src="${context}/resources/project_js/lawfulReportUnblock.js"></script>
<script type="text/javascript"
		src="${context}/resources/project_js/validationMsg.js"></script>
			<script type="text/javascript"
		src="${context}/resources/project_js/_dateFunction.js" async></script>
		<script type="text/javascript"
		src="${context}/resources/project_js/profileInfoTab.js" async></script>		
		<script type="text/javascript" src="${context}/resources/project_js/globalVariables.js"></script>
		<script>
		$('.datepick').datepicker({
			dateFormat : "yy-mm-dd"
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
        
        $('#singleRecoveryDatePeriod').datepicker({
        	dateFormat: "yy-mm-dd"
        	});

        
        $('#stolenDatePeriod').datepicker({
        	dateFormat: "yy-mm-dd"
        	});


</script>
		

</body>
</html>
<%
	}else{
		/*  request.setAttribute("msg", "  *Please login first");
		request.getRequestDispatcher("./index.jsp").forward(request, response); */
%>
<script language="JavaScript">
	sessionStorage.setItem("loginMsg",
			"*Session has been expired");
	window.top.location.href = "./login";
</script>
<%
	}
%>