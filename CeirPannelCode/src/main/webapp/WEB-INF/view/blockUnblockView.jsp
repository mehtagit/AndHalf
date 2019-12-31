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

</head>
<body data-roleType="${usertype}" data-userID="${userid}"
	data-selected-roleType="${selectedUserTypeId}">



            <!-- START CONTENT -->
            <section id="content">
                <!--start container-->
                <div class="container">
                    <div class="section">
                        <div class="row">
                            <div class="col s12 m12 l12">
                                <div class="row card-panel">
                                    <div class="container-fluid pageHeader">
                                        <p class="PageHeading">Block/Unblock</p>
                                        <a href="./selectblockUnblockPage" class="boton right">Report Block/Unblock</a>
                                    </div>

                                    <div class="col s12 m12 l12" id="consignmentTableDIv"
                                        style="padding-bottom: 5px;background-color: #e2edef52;">

                                        <div class="col s6 m2 l2 responsiveDiv">

                                            <div id="startdatepicker" class="input-group date"
                                                data-date-format="yyyy-mm-dd">
                                                <label for="TotalPrice">Start date</label>
                                                <input type="date">
                                                <span class="input-group-addon" style="color:#ff4081"><i
                                                        class="fa fa-calendar" aria-hidden="true"
                                                        style="float: right; margin-top: -37px;"></i></span>
                                            </div>
                                        </div>
                                        <div class="col s6 m2 l2 responsiveDiv">

                                            <div id="enddatepicker" class="input-group date"
                                                data-date-format="yyyy-mm-dd">
                                                <label for="TotalPrice">End date</label>
                                                <input class="form-control" type="date" id="datepicker" />
                                                <span class="input-group-addon" style="color:#ff4081"><i
                                                        class="fa fa-calendar" aria-hidden="true"
                                                        style="float: right; margin-top: -37px;"></i></span>
                                            </div>
                                        </div>

                                        <div class="col s6 m2 l2 selectDropdwn">
                                            <br />
                                            <!-- <label for="TotalPrice">Select Request type</label> -->
                                            <select id="taxPaidStatus" class="browser-default">
                                                <option value="" disabled selected>Request type</option>
                                                <option value="Stln">Block</option>
                                                <option value="recovery">Unblock</option>
                                            </select>

                                        </div>

                                        <div class="col s6 m2 l2 selectDropdwn">
                                            <br />
                                            <!-- <label for="TotalPrice">Select File Status</label> -->
                                            <select id="filterFileStatus" class="browser-default">
                                                <option value="" disabled selected>Status</option>
                                                <option value="Success">Success</option>
                                                <option value="Error">Error</option>
                                                <option value="Processing">Processing</option>
                                                <option value="INIT">INIT</option>
                                            </select>

                                        </div>

                                        <div class="col s12 m2 l2">
                                            <button type="button" class="btn primary botton"
                                                id="submitFilter">Filter</button>
                                        </div>

                                    </div>
                                    <table class="responsive-table striped display" id="blockUnblockDataTable"
                                        cellspacing="0">
                                        <thead>
                                            <tr>
                                                <th>Date</th>
                                                <th>Transaction ID</th>
                                                <th>Request Type</th>
                                                <th>Mode</th>
                                                <th>File Name</th>
                                                <th>Status</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody id="consignmentTableLibraryTbody">
                                            <tr>
                                                <td>10/05/2019</td>
                                                <td>Tr12345678</td>
                                                <td>Block</td>
                                                <td>Single</td>
                                                <td></td>
                                                <td>Success</td>
                                              <td style="width: 150px;">
                                                    <a href="#"><i class="fa fa-exclamation-circle " title="ErrorFile"></i></a>
                                                    <a href="#" download="download"><i class="fa fa-download download" aria-hidden="true" title="download"></i></a>
                                                    <a onclick="viewDeviceDetails(B3112201912345)"> <i class="fa fa-eye " aria-hidden="true" title="view"></i></a>
                                                    <a href="#editDevice" class="modal-trigger"><i class="fa fa-pencil" aria-hidden="true" title="edit"></i></a>
                                                </td>
                                            </tr>
 </tbody>
                                    </table>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
                <!--end container-->
            </section>
            
              
           <div id="viewBulkBlockDeviceModal" class="modal-form" style="overflow-y: hidden;">
		<h6 class="modal-header">View Block Devices</h6>
		<div class="modal-content" style="margin-top: 5px;">
			
                                            <form action="#" style="margin-top: 30px;">
                                                    

                                                    <div class="input-field col s12 m6" style="margin-top: 25px;">
                                                        <input type="text" id="viewBulkBlockCategory" name="Category" pattern="[0-9]"
                                                            title="" maxlength="16" value="Contract Violation" disabled>
                                                        <label for="viewBulkBlockCategory">Category</label>
                                                    </div>

                                                    <div class="input-field col s12 m6">
                                                        <textarea id="viewBulkBlockRemark" class="materialize-textarea" placeholder="kjdhdskjfhdskhfkdsjhf" disabled></textarea>
                                                        <label for="viewBulkBlockRemark">Remark </label>
                                                    </div>

                                                    

                                                    <div class="input-field col s12 m6" style="margin-top: 25px;">
                                                        <input type="text" id="viewBulkBlockuploadFile" name="uploadFile" pattern="[0-9]"
                                                            title="" maxlength="16" value="file.csv" disabled>
                                                        <label for="viewBulkBlockuploadFile">Upload Bulk Devices</label>
                                                    </div>

                                                    <div class="input-field col s12 m6" style="margin-top: 25px;">
                                                        <input type="text" id="viewBulkBlockquantity" name="quantity" pattern="[0-9]"
                                                            title="" maxlength="16" value="1500" disabled>
                                                        <label for="viewBulkBlockquantity">Quantity</label>
                                                    </div>
                                                     <div class="input-field col s12 m6" style="margin-top: 25px;">
                                                        <input type="text" id="viewBulkBlockTxnId" name="viewBulkBlockTxnId" pattern="[0-9]"
                                                            title="" maxlength="16" value="1500" disabled>
                                                        <label for="viewBulkBulkTxnId">Transaction Id</label>
                                                    </div>

                                                   


                                                    <div class="input-field col s12 center">
                                                       
                                                        <a href="blockUnblockList.html" class="btn">Cancel</a>
                                                    </div>
                                                </form>
            </div></div>
            
            <div id="editBulkBlockDeviceModal" class="modal-form" style="overflow-y: hidden;">
		<h6 class="modal-header">View Block Devices</h6>
		<div class="modal-content" style="margin-top: 5px;">
			
                                            <form action="#" style="margin-top: 30px;">
                                                    

                                                    <div class="input-field col s12 m6" style="margin-top: 25px;">
                                                        <input type="text" id="editBulkBlockCategory" name="editBulkBlockCategory" pattern="[0-9]"
                                                            title="" maxlength="16" value="Contract Violation" disabled>
                                                        <label for="editBulkBlockCategory">Category</label>
                                                    </div>

                                                    <div class="input-field col s12 m6">
                                                        <textarea id="editBulkBlockRemark" class="materialize-textarea" placeholder="kjdhdskjfhdskhfkdsjhf" disabled></textarea>
                                                        <label for="editBulkBlockRemark">Remark </label>
                                                    </div>

                                                    

                                                    <div class="input-field col s12 m6" style="margin-top: 25px;">
                                                        <input type="text" id="editBulkBlockuploadFile" name="editBulkBlockuploadFile" pattern="[0-9]"
                                                            title="" maxlength="16" value="file.csv" disabled>
                                                        <label for="editBulkBlockuploadFile">Upload Bulk Devices</label>
                                                    </div>

                                                    <div class="input-field col s12 m6" style="margin-top: 25px;">
                                                        <input type="text" id="editBulkBlockquantity" name="editBulkBlockquantity" pattern="[0-9]"
                                                            title="" maxlength="16" value="1500" disabled>
                                                        <label for="editBulkBlockquantity">Quantity</label>
                                                    </div>
                                                     <div class="input-field col s12 m6" style="margin-top: 25px;">
                                                        <input type="text" id="editBulkBlockTxnId" name="editBulkBlockTxnId" pattern="[0-9]"
                                                            title="" maxlength="16" value="1500" disabled>
                                                        <label for="editBulkBlockTxnId">Transaction Id</label>
                                                        <input type="text" style="display:none" id="editBulkBlockrequestType">
                                                    </div>

                                                   


                                                    <div class="input-field col s12 center">
                                                        	<button class="waves-effect waves-light modal-trigger btn"
						type="button" onclick="updateBulkDevice()">Update</button>
                                                        <a href="./" class="btn">Cancel</a>
                                                    </div>
                                                </form>
            </div></div>
            
           <div id="viewSingleDeviceModal" class="modal-form" style="overflow-y: hidden;">
		<h6 class="modal-header">View Consignment</h6>
		<div class="modal-content" style="margin-top: 5px;">
			
			
			
			  <form action="#">
                                                    <div class="row">
                                                        <div class="row">


                                                            <div class="input-field col s12 m6"
                                                                style="margin-top: 21px;">
                                                                <input type="text" id="viewSingledeviceType" name="deviceType"
                                                                    pattern="[0-9]" value="Handheld" disabled
                                                                    title="Please enter your device serial number first"
                                                                    maxlength="20">
                                                                <label for="deviceType">Device Type </label>
                                                            </div>

                                                          

                                                            <div class="input-field col s12 m6"
                                                                style="margin-top: 21px;">
                                                                <input type="text" id="simStatus" name="simStatus"
                                                                    pattern="[0-9]" value="Yes" disabled
                                                                    title="Please enter your device serial number first"
                                                                    maxlength="20">
                                                                <label for="simStatus">Multiple Sim Status </label>
                                                            </div>

                                                            <div class="input-field col s12 m6"
                                                                style="margin-top: 21px;">
                                                                <input type="text" id="serialNumber" name="serialNumber"
                                                                    pattern="[0-9]" value="GHJ1425376565" disabled
                                                                    title="Please enter your device serial number first"
                                                                    maxlength="20">
                                                                <label for="serialNumber">Device Serial Number </label>
                                                            </div>

                                                            <div class="input-field col s12 m6">
                                                                <textarea id="Remark" placeholder="kjdshfkjdshfkdsjhfkdsj" disabled
                                                                    class="materialize-textarea"></textarea>
                                                                <label for="Remark">Remark </label>
                                                            </div>


                                                            <div class="input-field col s12 m6"
                                                                style="margin-top: 21px;">
                                                                <input type="text" id="deviceIdType" name="deviceIdType"
                                                                    pattern="[0-9]" value="IMEI" disabled
                                                                    title="Please enter your device serial number first"
                                                                    maxlength="20">
                                                                <label for="deviceIdType">Device ID Type </label>
                                                            </div>

                                                        

                                                            <div class="input-field col s12 m6"
                                                                style="margin-top: 21px;">
                                                                <input type="text" id="Category" name="Category"
                                                                    pattern="[0-9]" value="Contract Violation" disabled
                                                                    title="Please enter your device serial number first"
                                                                    maxlength="20">
                                                                <label for="Category">Category </label>
                                                            </div>
                                                        </div>
                                                        <div class="row input_fields_wrap">
                                                            <div class="col s12 m12">
                                                                <p style="margin-bottom: 0;">IMEI/MEID/ESN</p>
                                                            </div>
                                                            <div class="input-field col s12 m6">
                                                                <input type="text" id="IMEI1" name="IMEI1"
                                                                    pattern="[0-9]" value="867564534243556" disabled
                                                                    title="Please enter minimum 15 and maximum 16 digit only"
                                                                    maxlength="16">
                                                                <label for="IMEI1">1 </label>
                                                            </div>

                                                            <div class="input-field col s12 m6">
                                                                <input type="text" id="IMEI2" name="IMEI2"
                                                                    pattern="[0-9]" value="968764537586978" disabled
                                                                    title="Please enter minimum 15 and maximum 16 digit only"
                                                                    maxlength="16">
                                                                <label for="IMEI2">2</label>
                                                            </div>

                                                            <div class="input-field col s12 m6">
                                                                <input type="text" id="IMEI3" name="IMEI3"
                                                                    pattern="[0-9]" disabled
                                                                    title="Please enter minimum 15 and maximum 16 digit only"
                                                                    maxlength="16">
                                                                <label for="IMEI3">3</label>
                                                            </div>

                                                            <div class="input-field col s12 m6">
                                                                <input type="text" id="IMEI4" name="IMEI4[]"
                                                                    pattern="[0-9]" disabled
                                                                    title="Please enter minimum 15 and maximum 16 digit only"
                                                                    maxlength="16">
                                                                <label for="IMEI4">4</label>
                                                            </div>
                                                        </div>
                                                    </div>



                                                    <div class="input-field col s12 center">
                                                        <!-- <button class="btn modal-trigger"
                                                            data-target="markAsBlock">Submit</button> -->
                                                        <a href="blockUnblockList.html" class="btn">Cancel</a>
                                                    </div>
                                                </form>
			



		</div>
	</div>
	
	
    <div id="confirmEditBlockUnblock" class="modal">
        <div class="modal-content">
            <h6 class="modal-header">Update Device Information</h6>
            <div class="row">
                <h6>The following details has been updated successfully</h6>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <div class="input-field col s12 center">
                        <button class="modal-close btn">ok</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
            
            
            
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

	<!--plugins.js - Some Specific JS codes for Plugin Settings-->
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

	<%--   <script type="text/javascript" src="${context}/resources/js/materialize-plugins/date_picker/picker.date.js"></script>
    <script type="text/javascript" src="${context}/resources/js/materialize-plugins/date_picker/picker.js"></script> --%>
	<!--custom-script.js - Add your own theme custom JS-->
	<script type="text/javascript" src="${context}/resources/js/plugins.js"></script>
	<!--prism
    <script type="text/javascript" src="${context}/resources/resources/js/prism/prism.js"></script>-->
	<!--scrollbar-->
	<script type="text/javascript"
		src="${context}/resources/js/plugins/perfect-scrollbar/perfect-scrollbar.min.js"></script>
	<!-- chartist -->
	<%-- <script type="text/javascript" src="${context}/resources/js/plugins/chartist-js/chartist.min.js"></script> --%>
	<script type="text/javascript"
		src="${context}/resources/js/countries.js"></script>

  <script type="text/javascript">
            $('#blockUnblockDataTable').DataTable(
            		{
            			"bLengthChange": false
            		});
            
          </script>

</body>
</html>