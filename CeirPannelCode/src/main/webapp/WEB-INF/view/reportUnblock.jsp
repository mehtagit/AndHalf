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
<style type="text/css">
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
    textarea.materialize-textarea {
padding: 0 !important;
}
    </style>
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
	href="${context}/resources/project_css/viewStock.css">
<%-- <link rel="stylesheet"
	href="${context}/resources/project_css/iconStates.css"> --%>
	

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>

<script src="http://malsup.github.io/jquery.blockUI.js"></script>
</head>
<body data-roleType="${usertype}" data-userID="${userid}"
	data-selected-roleType="${selectedUserTypeId}">



<section id="content">
                <!--start container-->
                <div class="container">
                    <div class="section">
                        <div class="row">
                            <div class="col s12 m12 l12">
                                <div class="row card-panel">
                                    <div id="reportBlockUnblock">
                                        <div class="container-fluid pageHeader">
                                            <p class="PageHeading"><spring:message code="operator.reportUnblock" /></p>
                                        </div>

                                        <div class="row">
                                            <div class="col s12">
                                                <ul class="tabs">
                                                    <li class="tab col s3"><a class="active" onclick="showSingleImeiUnBlock()"><spring:message code="operator.single" /></a>
                                                    </li>
                                                    <li class="tab col s3"><a onclick="showMultipleImeiUnBlock()"><spring:message code="operator.bulk" /></a></li>
                                                </ul>
                                            </div>
                                            <div id="SingleImeiUnBlock" class="col s12" style="margin-top: 30px;display: block">
                                                 <form action="" id="SingleImeiUnBlockform" onsubmit="return submitSingleUnBlockDevicesRequest()" method="POST" enctype="multipart/form-data">
                                                  
                                                        <div class="row">
                                        
                                                            <div class="col s12 m6">
                                                                <label for="deviceType"><spring:message code="operator.devicetype" /></label>
                                                                <select class="browser-default" id="unbockSingledeviceType"
                                                                 oninput="InvalidMsg(this,'select');" oninvalid="InvalidMsg(this,'select');"
                                                                title= "<spring:message code="validation.selectFieldMsg" />" >
                                                                    <option value="" disabled selected><spring:message code="operator.devicetype" /></option> 
                                                                </select>
                                                            </div>
                                                             <div class="col s12 m6"><label for="UnblockdeviceIdType">
                                                                    <spring:message code="operator.deviceidtype" /> <span class="star">*</span></label>
                                                                <select class="browser-default" id="UnblockdeviceIdType" 
                                                                oninput="InvalidMsg(this,'select');" oninvalid="InvalidMsg(this,'select');"
                                                                title= "<spring:message code="validation.selectFieldMsg" />" required / >
                                                                    <option value="" disabled selected>
                                                                        <spring:message code="operator.selectdeviceidtype" />
                                                                    </option>
                                                                   
                                                                </select>
                                                            </div>
                                        
                                                            <div class="col s12 m6">
                                                                <label for="deviceType"><spring:message code="operator.multiplesim" /></label>
                                                                <select class="browser-default" id="unbockSingleMultipleSimStatus"
                                                                 oninput="InvalidMsg(this,'select');" oninvalid="InvalidMsg(this,'select');"
                                                                title= "<spring:message code="validation.selectFieldMsg" />" >
                                                                    <option value="" disabled selected><spring:message code="operator.multiplesim" /></option>
                                                                   
                                                                </select>
                                                            </div>
                                        
                                                            <div class="input-field col s12 m6" style="margin-top: 21px;">
                                                                <input type="text" id="unbockSingleSerialNumber" name="unbockSingleserialNumber" pattern="[A-Za-z0-9]{1,15}"
                                                                   oninput="InvalidMsg(this,'input');" oninvalid="InvalidMsg(this,'input');"
                                                                   title= "<spring:message code="validation.numberfirst" />"  maxlength="15">
                                                                <label for="serialNumber"><spring:message code="operator.deviceserial" /></label>
                                                            </div>
                                                            
                                                              <div class="col s12 m6"><label for="singleDeviceUnblock"><spring:message code="operator.category" />
                                                            <span class="star">*</span></label>
                                                        <select class="browser-default" id="singleDeviceUnblock"  
                                                     		 oninput="InvalidMsg(this,'select');" oninvalid="InvalidMsg(this,'select');"
                                                                title= "<spring:message code="validation.selectFieldMsg" />" required / >
                                                            <option value="" disabled selected><spring:message code="operator.selectcategory" />
                                                            </option>
                                                          
                                                        </select>
                                                    </div>
                                                            <div class="input-field col s12 m6">
                                                                <textarea id="unbockSingleRemark"  class="materialize-textarea" 
                                                                oninput="InvalidMsg(this,'input');" oninvalid="InvalidMsg(this,'input');"
                                                                title= "<spring:message code="validation.200character" />" required / maxlength="200"></textarea>
                                                                <label for="Remark"><spring:message code="input.remarks" /> <span class="star">*</span></label>
                                                            </div>
                                                           
                                                        <div class="row input_fields_wrap">
                                                        <div class="col s12 m12">
                                                        <div class="col s12 m6">
<p style="margin-top: 3px; margin-bottom: 5px;"><spring:message code="operator.blocking" /></p>
<label style="margin-right: 2%;"> <input type="radio" class="blocktypeRadio" id=""
value="Immediate"
onclick="document.getElementById('calender').style.display = 'none';"
name="stolenBlockPeriod" checked><spring:message code="operator.immediate" />
</label> <label style="margin-right: 2%;"> <input type="radio" class="blocktypeRadio"
value="Default"
onclick="document.getElementById('calender').style.display = 'none';"
name="stolenBlockPeriod"><spring:message code="operator.default" />
</label> <label> <input type="radio" value="tilldate" class="blocktypeRadio"
onclick="document.getElementById('calender').style.display = 'block';"
name="stolenBlockPeriod"><spring:message code="operator.later" />
</label>
<div class="col s6 m2 responsiveDiv"
style="display: none; width: 30%; float: right; margin-right: 30%; margin-top: -15px;" id="calender">
<div id="startdatepicker" class="input-group date">
<input type="text" id="stolenDatePeriodUnblock" 
style="margin-top: -9px" /> <span class="input-group-addon"
style="color: #ff4081"><i class="fa fa-calendar"
aria-hidden="true" style="float: right; margin-top: -30px;"></i></span>
</div>
</div>
                                                        </div>
                                                            <div class="col s12 m12">
                                                                <p style="margin-bottom: 0;"><spring:message code="title.imeiMeidEsn" /> </p>
                                                            </div>
                                                            <div class="input-field col s12 m6">
                                                                <input type="text" id="unbockSingleIMEI1"  name="IMEI1" pattern="[0-9]{15,16}"
                                                                   oninput="InvalidMsg(this,'input');" oninvalid="InvalidMsg(this,'input');"
                                                                   title= "<spring:message code="validation.1516digit" />" required  / maxlength="16">
                                                                <label for="IMEI1"><spring:message code="title.one" /> <span class="star">*</span></label>
                                                            </div>
                                        
                                                            <div class="input-field col s12 m6">
                                                                <input type="text" id="unbockSingleIMEI2" name="IMEI2" pattern="[0-9]{15,16}"
                                                                   oninput="InvalidMsg(this,'input');" oninvalid="InvalidMsg(this,'input');"
                                                                   title= "<spring:message code="validation.1516digit" />"  maxlength="16">
                                                                <label for="IMEI2"><spring:message code="title.two" /></label>
                                                            </div>  
                                                            
                                                            <div class="input-field col s12 m6">
                                                                <input type="text" id="unbockSingleIMEI3" name="IMEI3" pattern="[0-9]{15,16}"
                                                                   oninput="InvalidMsg(this,'input');" oninvalid="InvalidMsg(this,'input');"
                                                                   title= "<spring:message code="validation.1516digit" />" maxlength="16">
                                                                <label for="IMEI3"><spring:message code="title.three" /></label>
                                                            </div>
            
                                                            <div class="input-field col s12 m6">
                                                                <input type="text" id="unbockSingleIMEI4" name="IMEI4[]" pattern="[0-9]{15,16}"
                                                                   oninput="InvalidMsg(this,'input');" oninvalid="InvalidMsg(this,'input');"
                                                                   title= "<spring:message code="validation.1516digit" />" maxlength="16">
                                                                <label for="IMEI4"><spring:message code="title.four" /></label>
                                                            </div>
                                                        	<div class="col s12 m12"><span><spring:message code="input.requiredfields" /> <span class="star">*</span></span></div>
                                                         </div>
                                                        </div>
                                                        
                                                       
                                                    </div>

                                                    

                                                    <div class="input-field col s12 center">
                                                      <button class="btn" id="singleUnblockSubmitButton" type="submit"><spring:message code="button.submit" /></button>
                                                        <a href="./blockUnblockDevices" class="btn" style="margin-left: 10px;"><spring:message code="button.cancel" /></a>
                                                 
                                                                 </div>
                                                </form>
                                            </div>
                                            <div id="multipleImeiUnBlock" class="col s12" style="display: none">
                                             <form action="" id="multipleImeiUnBlockform" onsubmit="return submitUnBlockImei()" method="POST" enctype="multipart/form-data">
                                                    
                                                     <div class="col s12 m6"><label for="bulkBlockdeviceCategory"><spring:message code="operator.category" />
                                                            <span class="star"> *</span></label>
                                                        <select class="browser-default" id="bulkunBlockdeviceCategory"  required="required" >
                                                            <option value="" disabled selected><spring:message code="operator.selectcategory" />
                                                            </option>
                                                          
                                                        </select>
                                                    </div>
                                                    <div class="input-field col s12 m6 " style="margin-top: 22px;">
                                                        <input type="text" id="unblockbulkquantity" name="quantity" pattern="[0-9]{1,9}" title="Please enter  numbers upto 9 characters only"
                                                         maxlength="16" required="required">
                                                        <label for="unblockbulkquantity"><spring:message code="input.quantity" /> <span class="star"> *</span></label>
                                                    </div>
                                                    
                                                    
                                                    <div class="file-field input-field col s12 m6" style="margin-top: 21px;">
                                                        <p style="color: #000;"><spring:message code="operator.upload" /> <span class="star"> *</span></p>
                                                        <div class="btn">
                                                            <span>File</span>
                                                            <input type="file" id="unblockBulkFile" required="required">
                                                        </div>
                                                        <div class="file-path-wrapper">
                                                            <input class="file-path validate" type="text" placeholder="Please select the file">
                                                        </div>
                                                    </div>

                                                    <div class="input-field col s12 m6" style="margin-top: 62px;">
                                                        <textarea id="unblockbulkRemark" class="materialize-textarea" maxlength="200" required="required"></textarea>
                                                        <label for="unblockbulkRemark"><spring:message code="input.remarks" /> <span class="star">*</span></label>
                                                    </div>
                                                    
                                                <p style="margin-left: 10px;"><a href="./Consignment/sampleFileDownload/7"><spring:message code="input.downlaod.sample" /></a></p>
                                                    <span style="margin-left: 5px;"><spring:message code="input.requiredfields" /> <span class="star"> *</span></span>
                                                
                                                   <div class="input-field col s12 center">
                                                <button class="btn " id="bulkUnblockSubmitButton" type="submit"><spring:message code="button.submit" /></button>
                                                        <a href="./stolenRecovery" class="btn" style="margin-left: 10px;"><spring:message code="button.cancel" /></a>
                                                    
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


    <div id="markAsUnblock" class="modal">
            <h6 class="modal-header"><spring:message code="operator.markunblock" /></h6>
        <div class="modal-content">
         <div class="row">
                <h6><spring:message code="operator.markedwith" /> <span id="txnIdblocksingleDevice"></span></h6>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <a href="./stolenRecovery" class="btn"><spring:message code="modal.ok" /></a>
                </div>
            </div>
        </div>
    </div>
    
    
    <div id="markBulkAsUnblock" class="modal">
        <h6 class="modal-header"><spring:message code="operator.markunblock" /></h6>
        <div class="modal-content">
         <div class="row">
                <h6><spring:message code="operator.markedwith" /> <span id="txnIdUnblocksingleDevice"></span></h6>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <a href="./stolenRecovery" class="btn"><spring:message code="modal.ok" /></a>
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
		src="${context}/resources/project_js/reportBlock.js"></script>


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
<script type="text/javascript" src="${context}/resources/project_js/globalVariables.js"></script>
<script type="text/javascript"
src="${context}/resources/project_js/backbutton.js"></script>
<script type="text/javascript"
src="${context}/resources/project_js/dragableModal.js"></script>	
<script type="text/javascript"
src="${context}/resources/project_js/enterKey.js"></script>
<script type="text/javascript"
		src="${context}/resources/project_js/validationMsg.js"></script>
			<script type="text/javascript"
		src="${context}/resources/project_js/_dateFunction.js" async></script>
		<script type="text/javascript"
		src="${context}/resources/project_js/profileInfoTab.js" async></script>
		<script>


		<script type="text/javascript">
		window.parent.$('#langlist').on('change', function() {
			var lang=window.parent.$('#langlist').val() == 'km' ? 'km' : 'en';
			
			window.location.assign("openBlockUnblockPage?pageType=block&lang="+lang);
		}); 
		
		var langParam=window.parent.$('#langlist').val() == 'km' ? 'km' : 'en';
		$.i18n().locale = langParam;
		var successMsg;
		$.i18n().load( {
			'en': './resources/i18n/en.json',
			'km': './resources/i18n/km.json'
		} ).done( function() { 
		//	successMsg=$.i18n('successMsg');
		});

		
		
		$(document).ready(function () {
			
			$.getJSON('./getDropdownList/DEVICE_TYPE', function(data) {
					
					for (i = 0; i < data.length; i++) {
						$('<option>').val(data[i].value).text(data[i].interp)
						.appendTo('#unbockSingledeviceType');
						console.log('#unbockSingledeviceType')
					}
				});

				$.getJSON('./getDropdownList/MULTI_SIM_STATUS', function(data) {
					
					for (i = 0; i < data.length; i++) {
						$('<option>').val(data[i].value).text(data[i].interp)
						.appendTo('#unbockSingleMultipleSimStatus');
						console.log('#unbockSingleMultipleSimStatus');
					}
				});
				
				$.getJSON('./getDropdownList/DEVICE_ID_TYPE', function(data) {
					
					for (i = 0; i < data.length; i++) {
						$('<option>').val(data[i].value).text(data[i].interp)
						.appendTo('#UnblockdeviceIdType');
						console.log('#UnblockdeviceIdType');
					}
				});
				
				$.getJSON('./getDropdownList/BLOCK_CATEGORY', function(data) {
					
					for (i = 0; i < data.length; i++) {
						$('<option>').val(data[i].value).text(data[i].interp)
						.appendTo('#bulkunBlockdeviceCategory');
						console.log('#bulkunBlockdeviceCategory');
						$('<option>').val(data[i].value).text(data[i].interp)
						.appendTo('#singleDeviceUnblock');
						console.log('#singleDeviceUnblock');
						
					}
				});
		});
		
		
		 $('#stolenDatePeriodUnblock').datepicker({
	        	dateFormat: "yy-mm-dd"
	        	});
		</script>

</body>
</html>