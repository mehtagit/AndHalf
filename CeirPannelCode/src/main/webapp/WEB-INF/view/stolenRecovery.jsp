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

</head>

<body data-roleType="${usertype}" data-userID="${userid}" data-selected-roleType="${selectedUserTypeId}">


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

								<a  class="boton right" id="btnLink"></a>
							</div>
							<form action="${context}/stakeholder/record"
								method="post">
								<div class="col s12 m12 l12" id="consignmentTableDIv"
									style="padding-bottom: 5px; background-color: #e2edef52;">
									<div id="filterBtnDiv">
										<!-- 							<div class='col s12 m2 l2'><button type='submit' class='btn primary botton' id='submitFilter'></button></div>
		 -->
									</div>
								</div>
							</form>
							<table id="stolenLibraryTable"
								class="responsive-table striped display"></table>

						</div>

					</div>
				</div>
			</div>
		</div>
		<!--end container-->
	</section>

	<!--viewModal Modal start   -->

	<div id="viewModal" class="modal-form" style="overflow-y: hidden;">
		<div class="modal-content">

			<h6>View Consignment</h6>
			<hr>


			<div class="row myRow">
				<div class="input-field col s12 m6">
					<input type="text" name="name" id="supplierId"
						placeholder="Supplier/Manufacturer ID" readonly="readonly" /> <label
						for="Name" class="center-align">Supplier/Manufacturer ID</label>
				</div>

				<div class="input-field col s12 m6">
					<input type="text" name="name" id="supplierName"
						placeholder="Supplier/Manufacturer Name" readonly="readonly" />
					<label for="Name" class="center-align">Supplier/Manufacturer
						Name</label>
				</div>
				<div class="input-field col s12 m6">
					<input type="text" name="name" id="consignmentNumber"
						placeholder="Consignment Number" readonly="readonly" /> <label
						for="Name" class="center-align">Consignment Number</label>
				</div>

				<div class="input-field col s12 m6" style="color: #c4c4c4;">
					<p style="margin-top: -5px; margin-bottom: -13px; font-size: 12px;">Expected
						Arival Date</p>
					<!-- <label for="Name" class="center-align">Expected Dispatch Date</label> -->
					<input type="date" id="expectedArrivaldate"
						placeholder="Expected Arival Date" readonly="readonly"> <span
						class="input-group-addon" style="color: #ff4081"><i
						class="fa fa-calendar" aria-hidden="true"></i></span>
				</div>
				<div class="input-field col s12 m6">
					<input type="text" id="countryview" class="browser-default"
						readonly="readonly" class="mySelect"
						placeholder="Device Origination Counrty*"> <label
						for="Name" class="center-align"> Origination Country</label> <label
						for="countryview" class="center-align"></label>
				</div>


				<div class="input-field col s12 m6">
					<p class="input-text-date" style="color: #c4c4c4;">Expected
						Dispatch Date</p>
					<!-- <label for="Name">Expected arrival Date</label> -->
					<input type="date" id="expectedDispatcheDate"
						placeholder="Expected Dispatch Date" readonly="readonly">
					<span class="input-group-addon" style="color: #ff4081"><i
						class="fa fa-calendar" aria-hidden="true"></i></span>
				</div>
				<div class="input-field col s12 m6">
					<!-- <label for="Name" class="center-align">Expected arrival port</label> -->
					<input type="text" id="expectedArrivalPort" readonly="readonly"
						placeholder="Arrival port"> <label for="Name"
						class="center-align">Expected Arrival Port</label>
				</div>

				<div class="input-field col s12 m6">
					<input type="text" name="Quantity" placeholder="Quantity"
						id="Quantity" readonly="readonly" /> <label for="Quantity"
						class="center-align">Quantity</label>
				</div>

				<div class="input-field col s12 m6">
					<input type="text" name="TransactionId"
						placeholder="Transaction ID" id="TransactionId"
						readonly="readonly" maxlength="15" /> <label for="TransactionId"
						class="center-align">Transaction ID</label>
				</div>

				<div class="input-field col s12 m6">
					<textarea id="remark" class="materialize-textarea"
						style="height: 0px;" readonly="readonly"></textarea>
					<label for="remark" class="">Remarks</label>

					<!--   <input type="textarea" name="Remark" placeholder="Remark" id="remark" readonly="readonly" maxlength="15" />
                                               <label for="TransactionId" class="center-align">Remark</label> -->
				</div>
			</div>

			<div class="row" style="padding: 20px 0 100px 0;">
				<div class="input-field col s12 center">
					<button class="btn" onclick="closeViewModal()"
						class="modal-close btn" id="add_user">Cancel</button>
				</div>
			</div>


		</div>
	</div>
	<!-- Modal End -->


	<!--Delete Modal start   -->

	<div id="DeleteConsignment" class="modal">
		<div class="modal-content">

			<h6>Delete</h6>
			<hr>

			<div class="row">
				<h6>
					Are you sure you want to withdraw the consignment details for (<span
						id="transID"></span>)
				</h6>
			</div>
		<input type="text" id="popupTransactionId" maxlength="15" hidden />
			<div class="row">
				<div class="input-field col s12 center">
					<div class="input-field col s12 center">
						<a class="btn" onclick="confirmantiondelete()">ok</a>
						<button class="modal-close btn" onclick="closeUpdateModal()"
							style="margin-left: 10px;">No</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal End -->
	<!-- END CONTENT -->




	<!-- Modal 1 start   -->

	<div id="updateConsignment" class="modal">
		<div class="modal-content">
			<h6>Update Consignment</h6>
			<hr>

			<div class="row">
				<h6 id="sucessMessage"></h6>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<a href="${context}/Consignment/viewConsignment" class="btn">ok</a>
				</div>
			</div>
		</div>
	</div>





	<!-- Delete confirmation Modal start   -->

	<div id="confirmDeleteConsignment" class="modal">
		<div class="modal-content">

			<h6>Delete Consignment</h6>
			<hr>
			<!-- <h4 class="header2 pb-2">User Info</h4> -->

			<div class="row">
				<h6 id=consignmentText></h6>
			</div>

			<div class="row">
				<div class="input-field col s12 cent\er">
					<div class="input-field col s12 center">
						<a href="" class="btn">ok</a>
					</div>
				</div>
			</div>
		</div>
	</div>



	<!-- Update Modal Start -->
	<div id="updateModal" class="modal-form" style="overflow-y: hidden;">
		<div class="modal-content">

			<h6>Edit Consignment</h6>
			<hr>


			<div class="row myRow">
				<div class="input-field col s12 m6">
					<input type="text" name="supplierId" id="supplierIdEdit"
						pattern="[A-Za-z0-9]{0,15}"
						title="Please enter alphabets and numbers upto 15 characters only"
						placeholder="Supplier/Manufacturer ID" maxlength="15" /> <label
						for="Name" class="center-align">Supplier/Manufacturer ID</label>
				</div>

				<div class="input-field col s12 m6">
					<input type="text" name="supplierName" id="supplierNameEdit"
						pattern="[A-Za-z]{0,50}"
						title="Please enter alphabets  upto 50 characters only"
						maxlength="50" placeholder="Supplier/Manufacturer Name" required />
					<label for="Name" class="center-align">Supplier/Manufacturer
						Name <span class="star">*</span>
					</label>
				</div>
				<div class="input-field col s12 m6">
					<input type="text" name="consignmentNumber"
						id="consignmentNumberEdit" pattern="[A-Za-z0-9]{0,15}"
						placeholder="Consignment Number" maxlength="15" /> <label
						for="Name" class="center-align">Consignment Number</label>
				</div>

				<div class="input-field col s12 m6">
					<!-- <p style="margin-top: -5px; margin-bottom: -13px; font-size: 12px;">Expected
                                                Arrival Date <span class="star">*</span></p> -->
					<!-- <label for="Name" class="center-align">Expected Dispatch Date</label> -->
					<input name="expectedDispatcheDate" id="expectedDispatcheDateEdit"
						required="required" placeholder="Expected Dispatch Date "
						type="text" onfocus="(this.type='date')"
						onfocusout="(this.type='text')"> <label for="dispatchDate"
						class="center-align">Expected Dispatch Date <span
						class="star">*</span></label> <span class="input-group-addon"
						style="color: #ff4081"><i class="fa fa-calendar"
						aria-hidden="true"></i></span>
				</div>
				<div class="input-field col s12 m6">
					<!-- <p style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">Device Origination Country <span class="star">*</span></p> -->
					<select id="country" name="organisationcountry" required="required"
						class="browser-default" class="mySelect" required></select> <label
						for="country" class="center-align"></label>
				</div>


				<div class="input-field col s12 m6">
					<!-- <p class="input-text-date">Expected Dispatch Date <span class="star">*</span></p> -->
					<!-- <label for="Name">Expected arrival Date</label> -->
					<input name="expectedArrivalDate" id="expectedArrivaldateEdit"
						required="required" placeholder="Expected Arrival  Date"
						type="text" onfocus="(this.type='date')"
						onfocusout="(this.type='text')"> <label for="dispatchDate"
						class="center-align">Expected Arrival Date <span
						class="star">*</span></label> <span class="input-group-addon"
						style="color: #ff4081"><i class="fa fa-calendar"
						aria-hidden="true"></i></span>
				</div>
				<div class="input-field col s12 m6">
					<!-- <label for="Name" class="center-align">Expected arrival port</label> -->
					<!-- <p style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">Expected arrival port <span class="star">*</span></p> -->
					<select name="expectedArrivalPort" id="expectedArrivalPortEdit"
						class="browser-default" required>
						<option value="" disabled selected>Expected arrival port
							*</option>
						<option value="Air">Air</option>
						<option value="Land">Land</option>
						<option value="Water">Water</option>
					</select>
				</div>

				<div class="input-field col s12 m6">
					<input type="text" name="quantity" id="QuantityEdit"
						pattern="[0-9]{0,7}"
						title="Please enter numbers upto 7 characters only" maxlength="7"
						placeholder="Quantity" required /> <label for="Quantity"
						class="center-align">Quantity <span class="star">*</span></label>
				</div>

				<div class="input-field col s12 m6">
					<input type="text" name="txnId" id="TransactionIdEdit"
						placeholder="Transaction ID" value="" readonly maxlength="15" />
					<label for="TransactionId" class="center-align">Transaction
						ID</label>
				</div>
			</div>


			<div class="row myRow">
				<div class="file-field input-field col s12 m6"
					style="margin-top: 5px;">
					<h6 class="file-upload-heading" style="margin-left: 0;">
						Upload Bulk Devices Information <span class="star">*</span>
					</h6>
					<div class="btn">
						<span>Select File</span> <input type="file" name="file"
							id="csvUploadFile" accept=".csv">
					</div>
					<div class="file-path-wrapper">
						<input class="file-path validate responsive-file-div"
							id="fileNameEdit" type="text">
					</div>
				</div>
			</div>
			<p>
				<a href="#">Download Sample Format</a>
			</p>

			<span> Required Field are marked with <span class="star">*</span>
			</span>


			<div class="row">
				<div class="input-field col s12 center">
					<button class="waves-effect waves-light modal-trigger btn"
						type="button" onclick="editRegisterConsignment()">Update</button>
					<button class="modal-close btn" onclick="closeUpdateModal()"
						style="margin-left: 10px;">Cancel</button>
				</div>
			</div>

		</div>
	</div>
	
	<div id="markAsStolen" class="modal">
        <div class="modal-content">

            <h6>Mark As Stolen</h6>
            <hr>

            <div class="row">
                <h6>The following devices marked as stolen has been recieved successfully.</h6>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <a href="./stolenRecovery" class="modal-close btn" style="margin-left: 10px;">ok</a>
                </div>
            </div>
        </div>
    </div>
    
    <div id="updateMarkAsStolen" class="modal">
        <div class="modal-content">

            <h6>Mark As Stolen</h6>
            <hr>

            <div class="row">
                <h6 id="editMessageTextStoleRecovery"></h6>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <a href="" class="modal-close btn" style="margin-left: 10px;">ok</a>
                </div>
            </div>
        </div>
    </div>
	

    <div id="stoleRecoveryModal" class="modal">
        <div class="row" style="padding-bottom: 20px;" id="stolenRecoveryDivPage">
        <button type="button" class=" modal-action modal-close  btn-flat right" data-dismiss="modal">×</button>
            <div class="col s12 m12 modal-content">
                <h6>Report Stolen/Recovery</h6>
                <hr>
                <div class="row">
                    <form action="#">
                        <h5 class="center">
                            <label>
                                <input name="group1" type="radio"
                                    onclick="document.getElementById('stolendiv').style.display ='block'; document.getElementById('recoverydiv').style.display ='none';" />
                                <span class="checkboxFont"> Stolen</span>
                            </label>

                            <label>
                                <input name="group1" type="radio"
                                    onclick="document.getElementById('recoverydiv').style.display ='block'; document.getElementById('stolendiv').style.display ='none';" />
                                <span class="checkboxFont"> Recovery</span>
                            </label>
                        </h5>
                    </form>
                </div>

                <div class="row" style="padding-bottom: 20px; display: none;" id="stolendiv">
                    <div class="col s12 m12 l12">
                        <form action="#">
                            <h5 class="center">
                                <label>
                                    <input name="group1" type="radio" onclick="location.href = 'markAsStolen.html';" />
                                    <span class="checkboxFont"> Choose from consignment</span>
                                </label>

                                <label>
                                    <input name="group1" type="radio"
                                        onclick="location.href = 'chooseFromStockDetail.html';" />
                                    <span class="checkboxFont"> Choose from the stock</span>
                                </label>

                                <label>
                                    <input name="group1" type="radio" onclick="openFileStolenModal()"
                                        class="modal-trigger modal-close" />
                                    <span class="checkboxFont"> Upload Bulk Devices</span>
                                </label>
                            </h5>
                        </form>
                    </div>
                </div>

                <div class="row" style="padding-bottom: 20px; display: none;" id="recoverydiv">
                    <div class="col s12 m12 l12">
                        <form action="#">
                            <h5 class="center">
                                <label>
                                    <input name="group1" type="radio"
                                        onclick="location.href = 'markAsRecovery.html';" />
                                    <span class="checkboxFont"> Choose from existing</span>
                                </label>

                                <label>
                                    <input name="group1" type="radio" onclick="openRecoveryModal()" data-target="recoveryDiv1"
                                        class="modal-trigger modal-close" />
                                    <span class="checkboxFont"> Upload Bulk Devices</span>
                                </label>
                            </h5>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    
     <div id="fileStolenModal" class="modal">
        <div class="modal-content">
            <div class="row">
                <div class="col s12 m12">
                    <h6>Mark As Stolen</h6>
                    <hr>
                    <div class="row">
                        <h6 style="color: #000;">Upload Bulk Devices Information <span class="star">*</span>
                        </h6>

                        <div class="file-field input-field col s12 m8">
                            <div class="btn" style="height: 35px; line-height: 2.5rem;">
                                <span>Select File</span>
                                <input type="file" id="stolenCsvUploadFile" accept=".csv">
                            </div>
                            <div class="file-path-wrapper">
                                <input class="file-path validate" type="text">
                            </div>
                        </div>
                    </div>
                </div>


                <div class="col s12 m6 l6" id="SavedFileNameDiv" style="display: none">
                    <label for="TotalPrice" class="center-align">Uploaded File</label>
                    <input type="text" name="" class="form-control boxBorder boxHeight" readonly id="SavedFileName" />
                </div>
            </div>
            <a href="#" style="margin-left: 10px;">Download Sample Format</a><br><br>

            <div class="row" id="samplefileDiv3" style="display: none;margin-left:05px;">
                <div style="display: inline-flex">
                    <a href="#" id="simDevice3">IMEI dual SIM device entry</a><br><br>
                    <a href="#" style="margin-left: 75px;" id="rangeDevice3">IMEI Range Device entry</a><br><br>
                </div>
            </div>
            <div style="margin-left:36%; margin-top: -25px;">
                <label style="margin-right: 2%;">
                 <input type="radio" id="" value="Immediate" onclick="document.getElementById('calender').style.display = 'none';" name="stolenBlockPeriod" checked>
                    Immediate</label>
                <label style="margin-right: 2%;"> 
                <input type="radio"  value="Default"
                        onclick="document.getElementById('calender').style.display = 'none';" name="stolenBlockPeriod">
                    Default</label>
                <label> <input type="radio"  value="tilldate"
                        onclick="document.getElementById('calender').style.display = 'block';" name="stolenBlockPeriod">
                    Later
                </label>
                <div class="col s6 m2 responsiveDiv" style="display: none; width: 30%;" id="calender">
                    <div id="startdatepicker" class="input-group date" data-date-format="yyyy-mm-dd">
                        <input class="form-control" type="date" id="stolenDatePeriod" style="margin-top: -9px" />
                        <span class="input-group-addon" style="color:#ff4081"><i class="fa fa-calendar"
                                aria-hidden="true" style="float: right; margin-top: -30px;"></i></span>
                    </div>

                </div>


                <div class="col s12 m2 l2" style=" width:40%;display:none" id="stolenDate">

                    <label for="TotalPrice" class="center-align">Till date</label>
                    <div id="startdatepicker" class="input-group date" data-date-format="yyyy-mm-dd"
                        style="   margin-top: 10px;">

                        <input class="form-control" name="inputsaves" type="text" id="startDateFilter" readonly />
                        <span class="input-group-addon" style="color:#ff4081"><i class="glyphicon glyphicon-calendar"
                                onclick="_Services._selectstartDate()"></i></span>
                    </div>
                </div>
            </div>
            <div class="col s12 m12">
                <p style="margin-left: 10px;"> Required Field are marked with <span class="star">*</span></p>
            </div>

            <div class="row" style="margin-bottom: 30px;">
                <div class="input-field col s12 center">
                    <a  onclick="fileStolenReport()" class="modal-close modal-trigger btn" style="margin-right: 10px;">Submit</a>

                    <button class="btn" onclick="closeStolenModalModal()">Cancel</button>
                </div>
            </div>
        </div>
    </div>
    
         <div id="editFileStolenModal" class="modal">
        <div class="modal-content">
            <div class="row">
                <div class="col s12 m12">
                    <h6>Update Stolen request for this transaction id ( <span id="editFileStolenTxnId"></span> ).</h6>
                    <input type="text" id="editFileStolenRequestType" style="display: none;">
                    <hr>
                    <div class="row">
                        <h6 style="color: #000;">Upload Bulk Devices Information <span class="star">*</span>
                        </h6>

                        <div class="file-field input-field col s12 m8">
                            <div class="btn" style="height: 35px; line-height: 2.5rem;">
                                <span>Select File</span>
                                <input type="text" id="editFileStolenId" style="display:none; ">
                                <input type="file" id="editStolenCsvUploadFile" accept=".csv">
                            </div>
                            <div class="file-path-wrapper">
                                <input class="file-path validate" type="text">
                            </div>
                        </div>
                    </div>
                </div>


                <div class="col s12 m6 l6" id="SavedFileNameDiv" style="display: none">
                    <label for="TotalPrice" class="center-align">Uploaded File</label>
                    <input type="text" name="" class="form-control boxBorder boxHeight" readonly id="SavedFileName" />
                </div>
            </div>
            <a href="#" style="margin-left: 10px;">Download Sample Format</a><br><br>

            <div class="row" id="samplefileDiv3" style="display: none;margin-left:05px;">
                <div style="display: inline-flex">
                    <a href="#" id="simDevice3">IMEI dual SIM device entry</a><br><br>
                    <a href="#" style="margin-left: 75px;" id="rangeDevice3">IMEI Range Device entry</a><br><br>
                </div>
            </div>
            <div style="margin-left:36%; margin-top: -25px;">
                <label style="margin-right: 2%;">
                 <input type="radio" id="" value="Immediate" onclick="document.getElementById('calender').style.display = 'none';" name="editStolenBlockPeriod" checked>
                    Immediate</label>
                <label style="margin-right: 2%;"> 
                <input type="radio"  value="Default"
                        onclick="document.getElementById('calender').style.display = 'none';" name="editStolenBlockPeriod">
                    Default</label>
                <label> <input type="radio"  value="tilldate"
                        onclick="document.getElementById('editFilecalender').style.display = 'block';" name="editStolenBlockPeriod">
                    Later
                </label>
                <div class="col s6 m2 responsiveDiv" style="display: none; width: 30%;" id="editFilecalender">
                    <div id="startdatepicker" class="input-group date" data-date-format="yyyy-mm-dd">
                        <input class="form-control" type="date" id="editStolenDatePeriod" style="margin-top: -9px" />
                        <span class="input-group-addon" style="color:#ff4081"><i class="fa fa-calendar"
                                aria-hidden="true" style="float: right; margin-top: -30px;"></i></span>
                    </div>

                </div>


                <div class="col s12 m2 l2" style=" width:40%;display:none" id="stolenDate">

                    <label for="TotalPrice" class="center-align">Till date</label>
                    <div id="startdatepicker" class="input-group date" data-date-format="yyyy-mm-dd"
                        style="   margin-top: 10px;">

                        <input class="form-control" name="inputsaves" type="text" id="startDateFilter" readonly />
                        <span class="input-group-addon" style="color:#ff4081"><i class="glyphicon glyphicon-calendar"
                                onclick="_Services._selectstartDate()"></i></span>
                    </div>
                </div>
            </div>
            <div class="col s12 m12">
                <p style="margin-left: 10px;"> Required Field are marked with <span class="star">*</span></p>
            </div>

            <div class="row" style="margin-bottom: 30px;">
                <div class="input-field col s12 center">
                    <a  onclick="updatefileStolenReport()" class="modal-close modal-trigger btn" style="margin-right: 10px;">Submit</a>

                    <button class="btn" onclick="closeEditStolenRecoveryModal()">Cancel</button>
                </div>
            </div>
        </div>
    </div>
	
	
    <div id="recoveryFileModal" class="modal">
        <div class="modal-content">
            <div class="row">
                <div class="col s12 m12">
                    <h6>Mark As Recovered</h6>
                    <hr>
                    <div class="row">
                        <h6 style="color: #000;">Upload Bulk Devices Information <span class="star">*</span>
                        </h6>

                        <div class="file-field input-field col s12 m8">
                            <div class="btn" style="height: 35px; line-height: 2.5rem;">
                                <span>Select File</span>
                                <input type="file" id="recoveryCsvUploadFile" accept=".csv">
                            </div>
                            <div class="file-path-wrapper">
                                <input class="file-path validate" type="text">
                            </div>
                        </div>

                        <div class="col s12 m6 l6" id="SavedFileNameDiv" style="display: none">
                            <label for="TotalPrice" class="center-align">Uploaded File</label>
                            <input type="text" name="" class="form-control boxBorder boxHeight" readonly
                                id="SavedFileName" />
                        </div>
                    </div>
                    <a href="#" style="margin-left: 10px;">Download Sample Format</a><br><br>

                    <div class="row" id="samplefileDiv12" style="display: none;margin-left:05px;">
                        <div style="display: inline-flex">
                            <a href="#" id="simDevice12">IMEI dual SIM device entry</a><br><br>
                            <a href="#" style="margin-left: 75px;" id="rangeDevice12">IMEI Range Device
                                entry</a><br><br>
                        </div>
                    </div>
                    <span style="margin-left: 10px;"> Required Field are marked with <span class="star">*</span></span>

                        <div class="row" style="margin-bottom: 30px;">
                            <div class="input-field col s12 center">
                                <button class="modal-close modal-trigger btn"  onclick="fileRecoveryReport()"  data-target="markAsRecoverDone"
                                    style="margin-right: 10px;">Submit</button>

                                <button class="btn " onclick="closeRecoveryModalModal()">Cancel</button>
                            </div>
                        </div>
                </div>
            </div>
        </div>
    </div>
        <div id="editRecoveryFileModal" class="modal">
        <div class="modal-content">
            <div class="row">
                <div class="col s12 m12">
                    <h6>Update Recovery request for this transaction id ( <span id="editFileRecoveryTxnId"></span> ).</h6>
                    <hr>
                    <div class="row">
                        <h6 style="color: #000;">Upload Bulk Devices Information <span class="star">*</span>
                        </h6>

                        <div class="file-field input-field col s12 m8">
                            <div class="btn" style="height: 35px; line-height: 2.5rem;">
                                <span>Select File</span>
                                <input type="text" id="editFileRecoveryId" style="display: none;">
                                <input type="file" id="editRecoveryCsvUploadFile" accept=".csv">
                            </div>
                            <div class="file-path-wrapper">
                                <input class="file-path validate" type="text">
                            </div>
                        </div>

                        <div class="col s12 m6 l6" id="SavedFileNameDiv" style="display: none">
                            <label for="TotalPrice" class="center-align">Uploaded File</label>
                            <input type="text" name="" class="form-control boxBorder boxHeight" readonly
                                id="SavedFileName" />
                        </div>
                    </div>
                    <a href="#" style="margin-left: 10px;">Download Sample Format</a><br><br>

                    <div class="row" id="samplefileDiv12" style="display: none;margin-left:05px;">
                        <div style="display: inline-flex">
                            <a href="#" id="simDevice12">IMEI dual SIM device entry</a><br><br>
                            <a href="#" style="margin-left: 75px;" id="rangeDevice12">IMEI Range Device
                                entry</a><br><br>
                        </div>
                    </div>
                    <span style="margin-left: 10px;"> Required Field are marked with <span class="star">*</span></span>

                        <div class="row" style="margin-bottom: 30px;">
                            <div class="input-field col s12 center">
                                <button class="modal-close modal-trigger btn"  onclick="updatefileStolenReport()"  
                                    style="margin-right: 10px;">Submit</button>

                                <button class="btn " onclick="closeEditRecoveryModal()">Cancel</button>
                            </div>
                        </div>
                </div>
            </div>
        </div>
    </div>
	
	 <div id="markAsRecoverDone" class="modal">
        <div class="modal-content">
            <h6>Mark As Recovered</h6>
            <hr>
            <div class="row">
                <h6>The following devices marked as recover has been recieved successfully.</h6>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <a  href="./stolenRecovery" class="modal-close btn" style="margin-left: 10px;">ok</a>
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
		src="${context}/resources/project_js/stolenRecovery.js"></script>

</body>
</html>