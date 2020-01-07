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
                                            <p class="PageHeading">Report Block</p>
                                        </div>

                                        <div class="row">
                                            <div class="col s12">
                                                <ul class="tabs">
                                                    <li class="tab col s3"><a class="active" onclick="showSingleImeiBlock()">Single</a>
                                                    </li>
                                                    <li class="tab col s3"><a onclick="showMultipleImeiBlock()">Bulk</a></li>
                                                </ul>
                                            </div>
                                            <div id="SingleImeiBlock" class="col s12" style="display:block; margin-top: 30px;">
                                                 <form action="" id="SingleImeiBlockform" onsubmit="return submitSingleBlockDevicesRequest()" method="POST" enctype="multipart/form-data">
                                                    <div class="row">
                                                        <div class="row">
                                        
                                                            <div class="col s12 m6">
                                                                <label for="blockdeviceType">Device Type <span class="star">*</span></label>
                                                                <select class="browser-default" id="blockdeviceType" required="required">
                                                                    <option value="" disabled selected>Device Type</option> 
                                                                    
                                                                </select>
                                                            </div>
                                                            <div class="col s12 m6"><label for="blockdeviceIdType">Device ID
                                                                    Type <span class="star">*</span></label>
                                                                <select class="browser-default" id="blockdeviceIdType" required="required">
                                                                    <option value="" disabled selected>Select Device ID
                                                                        Type
                                                                    </option>
                                                                   
                                                                </select>
                                                            </div>
                                        
                                                            <div class="col s12 m6">
                                                                <label for="blockmultipleSimStatus">Multiple Sim Status <span class="star">*</span></label>
                                                                <select class="browser-default" id="blockmultipleSimStatus" required="required">
                                                                    <option value="" disabled selected>Multiple Sim Status</option>
                                                                    
                                                                </select>
                                                            </div>
                                        
                                                            <div class="input-field col s12 m6" style="margin-top: 21px;">
                                                                <input type="text" id="singleblockserialNumber" name="serialNumber" pattern="[A-Za-z0-9]{1,15}" required="required"
                                                                    title="Please enter your device serial number first" maxlength="15">
                                                                <label for="serialNumber">Device Serial Number <span class="star">*</span></label>
                                                            </div>
                                        
                                                            <div class="input-field col s12 m6">
                                                                <textarea id="singleblockremark" class="materialize-textarea" required="required"></textarea>
                                                                <label for="blockRemark">Remark <span class="star">*</span></label>
                                                            </div>
                                                              <div class="col s12 m6"><label for="singleDeviceCategory">Category
                                                            <span class="star">*</span></label>
                                                        <select class="browser-default" id="singleDeviceCategory" required="required" >
                                                            <option value="" disabled selected>Select Category
                                                            </option>
                                                            
                                                        </select>
                                                    </div>
                                                    
                                                    
               <div class="" style="margin-left: 36%; margin-top: -25px;">
				BlockingType
				<label style="margin-right: 2%;"> <input type="radio" class="blocktypeRadio" id=""
					value="Immediate"
					onclick="document.getElementById('calender').style.display = 'none';"
					name="stolenBlockPeriod" checked> Immediate
				</label> <label style="margin-right: 2%;"> <input type="radio" class="blocktypeRadio"
					value="Default"
					onclick="document.getElementById('calender').style.display = 'none';"
					name="stolenBlockPeriod"> Default
				</label> <label> <input type="radio" required="required" value="tilldate" class="blocktypeRadio"
					onclick="document.getElementById('calender').style.display = 'block';"
					name="stolenBlockPeriod"> Later
				</label>
				<div class="col s6 m2 responsiveDiv"
					style="display: none; width: 30%;" id="calender">
					<div id="startdatepicker" class="input-group date">
						<input  type="text" id="stolenDatePeriod"
							style="margin-top: -9px" /> <span class="input-group-addon"
							style="color: #ff4081"><i class="fa fa-calendar"
							aria-hidden="true" style="float: right; margin-top: -30px;"></i></span>
					</div>

				</div>


				<div class="col s12 m2 l2" style="width: 40%; display: none"
					id="stolenDate">

					<label for="TotalPrice" class="center-align">Till date</label>
					<div id="startdatepicker" class="input-group" style="margin-top: 10px;">

						<input class="form-control" name="inputsaves" type="text"
							id="startDateFilter" readonly /> <span class="input-group-addon"
							style="color: #ff4081"><i
							class="glyphicon glyphicon-calendar"
							onclick="_Services._selectstartDate()"></i></span>
					</div>
				</div>
			</div>
                                                        </div>
                                                        <div class="row input_fields_wrap">
                                                            <div class="col s12 m12">
                                                                <p style="margin-bottom: 0;">IMEI/MEID/ESN</p>
                                                            </div>
                                                            <div class="input-field col s12 m6">
                                                                <input type="text" id="singleblockIMEI1" name="IMEI1" pattern="[0-9]{15,16}" required="required"
                                                                    title="Please enter minimum 15 and maximum 16 digit only" maxlength="16">
                                                                <label for="singleblockIMEI1">1 <span class="star">*</span></label>
                                                            </div>
                                        
                                                            <div class="input-field col s12 m6">
                                                                <input type="text" id="singleblockIMEI2" name="IMEI2" pattern="[0-9]{15,16}"
                                                                    title="Please enter minimum 15 and maximum 16 digit only" maxlength="16">
                                                                <label for="singleblockIMEI2">2</label>
                                                            </div>  
                                                            
                                                            <div class="input-field col s12 m6">
                                                                <input type="text" id="singleblockIMEI3" name="IMEI3" pattern="[0-9]{15,16}"
                                                                    title="Please enter minimum 15 and maximum 16 digit only"
                                                                    maxlength="16">
                                                                <label for="singleblockIMEI3">3</label>
                                                            </div>
            
                                                            <div class="input-field col s12 m6">
                                                                <input type="text" id="singleblockIMEI4" name="IMEI4[]" pattern="[0-9]{15,16}"
                                                                    title="Please enter minimum 15 and maximum 16 digit only"
                                                                    maxlength="16">
                                                                <label for="singleblockIMEI4">4</label>
                                                            </div>
                                                        </div>
                                                        <span> Required Field are marked with <span class="star">*</span></span>
                                                    </div>

                                                    

                                                    <div class="input-field col s12 center">
                                                        <button class="btn" type="submit">Submit</button>
                                                        <a href="./stolenRecovery" class="btn" style="margin-left: 10px;">Cancel</a>
                                                    </div>
                                                </form>
                                            </div>
                                            <div id="multipleImeiBlock" style="display: none" class="col s12">
                                                <form action="" id="multipleImeiBlockform" onsubmit="return submitBlockImei()" method="POST" enctype="multipart/form-data">
                                                
                                                <div class="col s12 m6"><label for="bulkBlockdeviceCategory">Category
                                                            <span class="star">*</span></label>
                                                        <select class="browser-default" id="bulkBlockdeviceCategory" required="required" >
                                                            <option value="" disabled selected>Select Category
                                                            </option>
                                                            
                                                        </select>
                                                    </div>

                                                    <div class="input-field col s12 m6" style="margin-top: 22px;">
                                                        <input type="text" id="blockbulkquantity" name="quantity" pattern="[0-9]{1-9}" title="Please enter  numbers upto 9 characters only"
                                                         maxlength="16" required="required">
                                                        <label for="blockbulkquantity">Quantity <span class="star">*</span></label>
                                                    </div>
                                                    
                                                    <div class="file-field input-field col s12 m6" style="margin-top: 21px;">
                                                        <p style="color: #000;">Upload Bulk Devices <span class="star">*</span></p>
                                                        <div class="btn">
                                                            <span>File</span>
                                                            <input type="file" id="blockBulkFile" accept=".csv" required="required">
                                                        </div>
                                                        <div class="file-path-wrapper">
                                                            <input class="file-path validate" type="text" placeholder="Please select the file">
                                                        </div>
                                                    </div>

                                                    <div class="input-field col s12 m6" style="margin-top: 62px;">
                                                        <textarea id="blockbulkRemark" class="materialize-textarea" required="required"></textarea>
                                                        <label for="blockbulkRemark">Remark <span class="star">*</span></label>
                                                    </div>

                                                    <p style="margin-left: 10px;"><a href="./Consignment/sampleFileDownload/filetype=sample">Download Sample Format</a></p>
                                                    <span style="margin-left: 5px;"> Required Field are marked with <span class="star">*</span></span>

                                                    <div class="input-field col s12 center">
                                                        <button class="btn" type="submit" >Submit</button>
                                                        <a href="./stolenRecovery" class="btn" style="margin-left: 10px;">Cancel</a>
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

<div id="markBulkAsBlock" class="modal">
<h6 class="modal-header">Mark As Block</h6>
        <div class="modal-content">
            
            

            <div class="row">
                <h6>This file has been marked as block.The Transaction ID for future reference is : <span id="txnIdsingleImei"></span></h6>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <a href="./stolenRecovery" class="btn">ok</a>
                </div>
            </div>
        </div>
    </div>
      <div id="markAsBlock" class="modal">
         <h6  class="modal-header">Mark As Block</h6>
        <div class="modal-content">
          <div class="row">
                <h6 id="singleDeviceBlockMessage"> This device has been marked as block.The Transaction ID for future reference is : <span id="txnIdblockBulkDevice"></span></h6>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <a href="./stolenRecovery" class="btn">ok</a>
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
		
		<script type="text/javascript">

		$(document).ready(function () {
			$.getJSON('./getDropdownList/DEVICE_TYPE', function(data) {
				
				for (i = 0; i < data.length; i++) {
					$('<option>').val(data[i].value).text(data[i].interp)
					.appendTo('#blockdeviceType');
					console.log('#blockdeviceType')
				}
			});

			$.getJSON('./getDropdownList/MULTI_SIM_STATUS', function(data) {
				
				for (i = 0; i < data.length; i++) {
					$('<option>').val(data[i].value).text(data[i].interp)
					.appendTo('#blockmultipleSimStatus');
					console.log('#blockmultipleSimStatus');
				}
			});
		});
		
		$.getJSON('./getDropdownList/DEVICE_ID_TYPE', function(data) {
			
			for (i = 0; i < data.length; i++) {
				$('<option>').val(data[i].value).text(data[i].interp)
				.appendTo('#blockdeviceIdType');
				console.log('#blockdeviceIdType');
			}
		});
		$.getJSON('./getDropdownList/BLOCK_CATEGORY', function(data) {
			
			for (i = 0; i < data.length; i++) {
				$('<option>').val(data[i].value).text(data[i].interp)
				.appendTo('#bulkBlockdeviceCategory');
				console.log('#bulkBlockdeviceCategory');
				$('<option>').val(data[i].value).text(data[i].interp)
				.appendTo('#singleDeviceCategory');
				console.log('#singleDeviceCategory');
			}
		});
		
		
		
		
		
		
		</script>

</body>
</html>