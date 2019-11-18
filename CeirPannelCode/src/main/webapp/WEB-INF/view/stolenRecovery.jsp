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



<style>
#snackbar {
	visibility: hidden;
	min-width: 250px;
	margin-left: -125px;
	background-color: #333;
	color: #fff;
	text-align: center;
	border-radius: 2px;
	padding: 10px;
	position: fixed;
	z-index: 1;
	left: 47%;
	top: 15px;
	font-size: 17px;
	height: 50px;
	line-height: 0px;
}

#snackbar.show {
	visibility: visible;
	-webkit-animation: fadein 0.5s, fadeout 0.5s 2.5s;
	animation: fadein 0.5s, fadeout 0.5s 2.5s;
}

@
-webkit-keyframes fadein {
	from {bottom: 0;
	opacity: 0;
}

to {
	bottom: 30px;
	opacity: 1;
}

}
@
keyframes fadein {
	from {bottom: 0;
	opacity: 0;
}

to {
	bottom: 30px;
	opacity: 1;
}

}
@
-webkit-keyframes fadeout {
	from {bottom: 30px;
	opacity: 1;
}

to {
	bottom: 0;
	opacity: 0;
}

}
@
keyframes fadeout {
	from {bottom: 30px;
	opacity: 1;
}

to {
	bottom: 0;
	opacity: 0;
}

}
.container-fluid {
	background-color: #529dba;
	height: 50px;
	/* margin:0 -20px; */
	padding: 10px;
	/* border-radius:5px 0;  */
}

/* #deletemodal.modal-backdrop {
  z-index: 0;
}  */
.boton {
	color: #2979a5;
	float: right;
	border: solid 1px rgba(33, 167, 201, 0.774);
	padding: 4px 10px;
	border-radius: 7px;
	font-size: 14px;
	background-color: #fff;
}

.boton:hover {
	color: #fff;
	float: right;
	border: solid 1px #ff5a92;
	padding: 4px 10px;
	border-radius: 7px;
	font-size: 14px;
	background-color: #ff5a92;
}

.row {
	margin-bottom: 0;
}

.file-field .btn, .file-field .btn-large {
	float: left;
	height: 2.5rem;
	line-height: 2.5rem;
}

[type="date"]::-webkit-inner-spin-button {
	display: none;
}

[type="date"]::-webkit-calendar-picker-indicator {
	opacity: 0;
}

.eventNone {
    cursor: not-allowed;
    user-select: none;
    pointer-events: none;
}

.error-icon
{
color:red; font-size:20px; margin-right:15px;
}
.download-icon{
font-size: 20px; color:#2e8b57;
}
.view-icon{
font-size: 20px; margin:0 0 0 15px;
}
.edit-icon{
font-size: 20px; margin:0 15px 0 15px; color: #006994
}
.delete-icon{
font-size: 20px; color: red;
}
.disable{
color: grey;
}
</style>

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
						<a href="${context}/stolenRecovery" class="btn">ok</a>
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
	

    <div id="stoleRecoveryModal" class="modal">
        <div class="row" style="padding-bottom: 20px;" id="stolenRecoveryDivPage">
        <button type="button" class=" modal-action modal-close  btn-flat right" data-dismiss="modal">�</button>
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





	<script type="text/javascript">
         
        function DeleteConsignmentRecord(txnId){
       		 $("#DeleteConsignment").openModal();
        	 $("#transID").text(txnId);
        }
        
        
        function confirmantiondelete(){
        	var txnId = $("#transID").text();
        	var roleType = $("body").attr("data-roleType");
       	    var userId = $("body").attr("data-userID");
       	    var currentRoleType = $("body").attr("data-selected-roleType"); 
       	    var role = currentRoleType == null ? roleType : currentRoleType;
        	console.log("txnId===**"+txnId+" userId="+userId+" roleType== "+roleType+ " currentRoleType=="+currentRoleType);
     		 var obj ={
        			 "txnId" : txnId,
        			 "roleType":role,
        			 "userId":userId
        			 
        	 }
        	 $.ajax({
        			url : "./stolenRecoveryDelete",
        			data : JSON.stringify(obj),
        			dataType : 'json',
        			contentType : 'application/json; charset=utf-8',
        			type : 'POST',
        			success : function(data, textStatus, xhr) {
        				console.log(data);
        				if(data.errorCode == 200){
        					$("#consignmentText").text(data.message);
        				}else if(data.errorCode == 0){
        					$("#consignmentText").text(data.message);
        				}
        			},
        			error : function() {
        					console.log("Error");
        			}
        		});
     		 $("#DeleteConsignment").closeModal();
     		 $("#confirmDeleteConsignment").openModal();
        }
         
     
        function EditConsignmentDetails(txnId){ 	
        
        	 
        	 $("#fileStolenModal").openModal();
         }
         
    	
        function viewConsignmentDetails(txnId){
        
        	 $("#viewModal").openModal();
        	 $.ajax({
    				url : "./openRegisterConsignmentPopup?reqType=editPage&txnId="+txnId,
    				dataType : 'json',
    				contentType : 'application/json; charset=utf-8',
    				type : 'GET',
    				success : function(data) {
    					console.log(data)
    					setViewPopupData(data);
    				},
    				error : function() {
    					alert("Failed");
    				}
    			});
        }
         
         
         function setViewPopupData(data){
        	console.log("_________________++++++++++"+data.organisationCountry)
        	
        	$("#supplierId").val(data.supplierId);
     		$("#supplierName").val(data.supplierName);
     		$("#consignmentNumber").val(data.consignmentNumber);
     		$("#expectedDispatcheDate").val(data.expectedDispatcheDate);
     		$("#countryview").val(data.organisationCountry);
     		$("#expectedArrivaldate").val(data.expectedArrivaldate);
     		$("#expectedArrivalPort").val(data.expectedArrivalPort);
     		$("#Quantity").val(data.quantity);
     		$("#TransactionId").val(data.txnId);
     		$("#remark").val(data.remarks);
     		$("#fileName").val(data.fileName); 
     		
     		
     	}
       
        function setEditPopupData(data){
        	console.log()
        	$("#supplierIdEdit").val(data.supplierId);
     		$("#supplierNameEdit").val(data.supplierName);
     		$("#consignmentNumberEdit").val(data.consignmentNumber);
     		$("#expectedDispatcheDateEdit").val(data.expectedDispatcheDate);
     		$('#country').val(data.organisationCountry);
     		$("#expectedArrivaldateEdit").val(data.expectedArrivaldate);
     		$("#expectedArrivalPortEdit").val(data.expectedArrivalPort);
     		$("#QuantityEdit").val(data.quantity);
     		$("#TransactionIdEdit").val(data.txnId);
     		$("#fileNameEdit").val(data.fileName); 
     		
        	
        } 
       
        
        // **************************************************filter table**********************************************
     
        
        function filterConsignment()
        {
       	 	var startdate=$('#startDate').val(); 
        	var endDate=$('#endDate').val();
        	var taxStatus=$('#taxPaidStatus').val();
        	var consignmentStatus=$('#filterConsignmentStatus').val();
        	var userId="1";
        	
        	var filterRequest={
        	"consignmentStatus":consignmentStatus,
        	"endDate":startdate,
        	"startDate":endDate,
        	"taxPaidStatus":taxStatus,
        	"userId":userId
        	};
        	
        	if( startdate !='' || endDate !='' || taxStatus != null || consignmentStatus != null ){
        	console.log("startdate="+startdate+" endDate="+endDate+" taxPaidstatus="+taxStatus+" consignmentStatus="+consignmentStatus)
        	
	$.ajax({
	url: "${context}/headers?type=consignment",
	type: 'POST',
	dataType: "json",
	success: function(result){
			var table=	$("#consignmentLibraryTable").DataTable({
    	  		destroy:true,
                "serverSide": true,
    			orderCellsTop : true,
    			"aaSorting" : [],
    			"bPaginate" : true,
    			"bFilter" : true,
    			"bInfo" : true,
    			"bSearchable" : true,
				ajax: {
           		        url: '${context}/consignmentData',
           		        type: 'POST',
           		    	data : function(d) {
          		    		d.filter = JSON.stringify(filterRequest);       		    		
           				}
           		    	
         		},
                "columns": result
            });
	},
	error: function (jqXHR, textStatus, errorThrown) {
    	console.log("error in ajax");
    	}
    	});
        	}
        	else{
            	console.log("please fill select");
            	}
        	}

        
        //******************************************************************************************************************************************************************888888
 //******************************************      ************************************************************************************************************************888888
 //******************************************************************************************************************************************************************888888   
 
 function editRegisterConsignment(){
         	/*  $("#editRegisterConsignment").submit(); */
         	 
         	 var supplierId=$('#supplierIdEdit').val();
         	 var supplierName=$('#supplierNameEdit').val();
         	 var consignmentNumber=$('#consignmentNumberEdit').val();
         	 var expectedArrivalDate=$('#expectedArrivaldateEdit').val();
         	 var expectedDispatcheDate=$('#expectedDispatcheDateEdit').val();
         	 var expectedArrivalPort=$('#expectedArrivalPortEdit').val();
         	 var organisationcountry=$('#country').val();
         	 var filename=$('#fileNameEdit').val();
         	 var txnId=$('#TransactionIdEdit').val();
         	 var quantity=$('#QuantityEdit').val();
         	  console.log(supplierName,consignmentNumber,expectedArrivalDate,txnId,filename)
         	 
         	 
         	 var formData= new FormData();
         		formData.append('file', $('#csvUploadFile')[0].files[0]);
         	 	formData.append('supplierId',supplierId);
         	 	formData.append('supplierName',supplierName);
         	 	formData.append('consignmentNumber',consignmentNumber);
         	 	formData.append('expectedArrivaldate',expectedArrivalDate);
         	 	formData.append('expectedDispatcheDate',expectedDispatcheDate);
         	 	formData.append('expectedArrivalPort',expectedArrivalPort);
         	 	formData.append('organisationcountry',organisationcountry);
         		formData.append('quantity',quantity);
         		formData.append('txnId',txnId);
         		formData.append('filename',filename);
         		
         		console.log(JSON.stringify(formData));
         		console.log("*********");
         	 	
         	 $.ajax({ 
  				url: '${context}/Consignment/updateRegisterConsignment',
  				type: 'POST',
  				data: formData,
  				processData: false,
  				contentType: false,
  				success: function (data, textStatus, jqXHR) {
  					
  					 console.log(data);
  					 $('#updateModal').closeModal();
  					 $('#updateConsignment').modal();
  					  if(data.errorCode==200){
  					
  					$('#sucessMessage').text('');
 					 $('#sucessMessage').text('Operation is not allowed');
  						 }
  					 else{
  						 $('#sucessMessage').text('');
  		 				 $('#sucessMessage').text('Your update on the form for transaction ID ('+data.txnId+') has been successfully updated.');
  					 }
  				   // $('#updateConsignment').modal('open'); 
  					//alert("success");
  					
  				},
  				error: function (jqXHR, textStatus, errorThrown) {
  				console.log("error in ajax")
  				}
  			});
         
         }
         
       

          function openDeleteModal(transactionId)
          {
        	/*   $('#deletemodal').modal('open');
        	  backdrop: 'static' */
        	$('#deletemodal').openModal();
        	console.log("transactionId value="+transactionId);
        	$('#deleteTransactionId').val(transactionId);
        	 }

          
          
          
          function myFunction(message) {
        	  var x = document.getElementById("snackbar");
        	  x.className = "show";
        	  $('#errorMessage').html(message);
        	  setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
        	}
          
           function dispatchDateValidation(){
        	   var currentDate;
        	var dispatcDate=  $('#expectedDispatcheDate').val();
        	var now=new Date();
        	if(now.getDate().toString().charAt(0) != '0'){
        		currentDate='0'+now.getDate();

        	/* alert("only date="+currentDate); */
        	}
        	else{
        		currentDate=now.getDate();
        	}
        	var today = now.getFullYear()+ '-' + (now.getMonth()+1)+ '-' +currentDate ;
        	//alert("today"+today);
        	console.log("dispatche="+dispatcDate);
        	console.log("todays parse date"+Date.parse(today));
        	console.log("dispatche parse date"+Date.parse(dispatcDate));
        	
        	
        	if(Date.parse(today)>Date.parse(dispatcDate))
        		{
        		myFunction("dispatche date should be greater then or equals to today");
        		$('#expectedDispatcheDate').val("");
        		}
        	
        	//alert("current date="+today+" dispatche date="+dispatcDate)
          }
           
           function arrivalDateValidation(){
        	   var currentDate;
        	var dispatcDate=  $('#expectedArrivalDate').val();
        	var now=new Date();
        	if(now.getDate().toString().charAt(0) != '0'){
        		currentDate='0'+now.getDate();

        	/* alert("only date="+currentDate); */
        	}
        	else{
        		currentDate=now.getDate();
        	}
        	var today = now.getFullYear()+ '-' + (now.getMonth()+1)+ '-' +currentDate ;
        	//alert("today"+today);
        	console.log("dispatche="+dispatcDate);
        	console.log("todays parse date"+Date.parse(today));
        	console.log("dispatche parse date"+Date.parse(dispatcDate));
        	
        	
        	if(Date.parse(today)>Date.parse(dispatcDate))
        		{
        		myFunction("Arrival date should be greater then or equals to today");
        		$('#expectedArrivalDate').val("");
        		}
        	
        	//alert("current date="+today+" dispatche date="+dispatcDate)
          }
 
           
           
$(document).ready(function(){
$('.datepicker').datepicker();
});

$('.datepicker').on('mousedown',function(event){
event.preventDefault();
});



         function closeUpdateModal(){
        	 $("#DeleteConsignment").closeModal();
        	 $('#updateModal').closeModal();
        	 $(".lean-overlay").remove();
         }
         
         function closeViewModal()
         {
        	 $('#viewModal').closeModal();
        	 $(".lean-overlay").remove();
        	 
         }
         
   			 populateCountries
   			 (   
     			 "country"
   			 );
   			 
   			 
   			 
   			 
   			 
   			 $.ajax({
   				url: "${context}/headers?type=stolen",
   				type: 'POST',
   				dataType: "json",
   				success: function(result){
   						var table=	$("#stolenLibraryTable").DataTable({
   			    	  		destroy:true,
   			                "serverSide": true,
   			    			orderCellsTop : true,
   			    			"aaSorting" : [],
   			    			"bPaginate" : true,
   			    			"bFilter" : true,
   			    			"bInfo" : true,
   			    			"bSearchable" : true,
   							ajax: {
   			           		        url: './stolenData',
   			           		        type: 'POST',
   			           		  data : function(d) {
   	          		    		d.filter = null;       		    		
   	           				}
   			         		},
   			                "columns": result
   			            });
   				}
   								}); 
   								
   					
   			 $.ajax({
   					url: "${context}/stolen/pageRendering",
   					type: 'POST',
   					dataType: "json",
   					success: function(data){
   			var elem='<p class="PageHeading">'+data.pageTitle+'</p>';
   			$("#pageHeader").append(elem);
   			var button=data.buttonList;

   			var date=data.inputTypeDateList;
   			for(i=0; i<date.length; i++){
   			$("#consignmentTableDIv").append("<div class='col s6 m2 l2 responsiveDiv'>"+
   					"<div id='enddatepicker' class='input-group date' data-date-format='yyyy-mm-dd'>"+
   					"<label for='TotalPrice'>"+date[i].title
   					+"</label>"+"<input class='form-control' type="+date[i].type+" id="+date[i].id+"/>"+
   					"<span	class='input-group-addon' style='color: #ff4081'>"+
   					"<i	class='fa fa-calendar' aria-hidden='true' style='float: right; margin-top: -37px;'>"+"</i>"+"</span>");
   			} 

   			// dynamic dropdown portion
   			var dropdown=data.dropdownList;
   			for(i=0; i<dropdown.length; i++){
   			var dropdownDiv=
   			$("#consignmentTableDIv").append("<div class='col s6 m2 l2 selectDropdwn'>"+
   					"<br>"+
   					"<div class='select-wrapper select2 form-control boxBorder boxHeight initialized'>"+
   					"<span class='caret'>"+"</span>"+
   					"<input type='text' class='select-dropdown' readonly='true' data-activates='select-options-1023d34c-eac1-aa22-06a1-e420fcc55868' value='Consignment Status'>"+
   					
   					"<select id="+dropdown[i].id+" class='select2 form-control boxBorder boxHeight initialized'>"+
   					"<option>"+dropdown[i].title+
   					"</option>"+
   					"</select>"+
   					"</div>"+
   					"</div>");
   			}

   			$("#consignmentTableDIv").append("<div class='col s12 m2 l2'><button class='btn primary botton' id='submitFilter'></button></div>");
   			for(i=0; i<button.length; i++){
   				$('#'+button[i].id).text(button[i].buttonTitle);
 			if(button[i].type === "HeaderButton"){
 				$('#btnLink').attr("onclick", "openStolenRecoveryModal()");
   				//$('#'+button[i].id).attr("href", button[i].buttonURL);
 }
 else{
   				$('#'+button[i].id).attr("onclick", button[i].buttonURL);
 }
   				}
   					}

   			//$("#filterBtnDiv").append();
   			}); 
   			 
   	
   function fileStolenReport(){
    		
	    var roleType = $("body").attr("data-roleType");
	    var userId = $("body").attr("data-userID");
	    var currentRoleType = $("body").attr("data-selected-roleType"); 
	    var sourceType='file';
	    var requestType='stolen';
	    var role = currentRoleType == null ? roleType : currentRoleType;
		var blockType=$('input[name=stolenBlockPeriod]:checked').val();
		var blockingTimePeriod=$('#stolenDatePeriod').val();
	    console.log("roleType=="+roleType+" userId="+userId+" currentRoleType =="+currentRoleType+"  blockType=="+blockType);
   	  	var formData= new FormData();
   	 			
   	    		formData.append('file', $('#stolenCsvUploadFile')[0].files[0]);
   	      		formData.append('blockingType',blockType);
   	      	 	formData.append('blockingTimePeriod',blockingTimePeriod);
   	      	 	formData.append('requestType',requestType);
   	      	 	formData.append('roleType',role);
   	      		formData.append('sourceType',sourceType);
   	      		formData.append('userId',userId);
   	      		
   	      		
   	      		console.log(JSON.stringify(formData));
   	      		console.log("*********");
   	      	 	
   	      	 $.ajax({
   					url: '${context}/fileTypeStolen',
   					type: 'POST',
   					data: formData,
   					processData: false,
   					contentType: false,
   					success: function (data, textStatus, jqXHR) {
   						
   						 console.log(data);
   						 $('#fileStolenModal').closeModal();
   						 $('#markAsStolen').openModal();
   						  //if(data.errorCode==200){
   						/* 
   						 $('#stockSucessMessage').text('');
   						 $('#stockSucessMessage').text('Operation is not allowed');
   							 }
   						 else{
   							 $('#stockSucessMessage').text('');
   			 				 $('#stockSucessMessage').text('Your update on the form for transaction ID ('+data.txnId+') has been successfully updated.');
   						 } */ 
   					   // $('#updateConsignment').modal('open'); 
   						//alert("success");
   						
   					},
   					error: function (jqXHR, textStatus, errorThrown) {
   					console.log("error in ajax")
   					}
   				});
   	      
   	      }
   	      
  

   function fileRecoveryReport(){
	
	    var roleType = $("body").attr("data-roleType");
	    var userId = $("body").attr("data-userID");
	    var currentRoleType = $("body").attr("data-selected-roleType"); 
	    
	   
	    var sourceType='file';
	    var requestType='recovery';
	    var role = currentRoleType == null ? roleType : currentRoleType;
	
	      var blockType=$('input[name=stolenBlockPeriod]:checked').val();
	      console.log("roleType=="+roleType+" userId="+userId+" currentRoleType =="+currentRoleType);
   	  
   	    		var formData= new FormData();
   	 			
   	    		formData.append('file', $('#recoveryCsvUploadFile')[0].files[0]);
   	      		formData.append('requestType',requestType);
   	      	 	formData.append('roleType',role);
   	      		formData.append('sourceType',sourceType);
   	      		formData.append('userId',userId);
   	
   	      	 	console.log(JSON.stringify(formData));
   	      	 	console.log("*********");
   	      	 	$.ajax({
   					url: '${context}/fileTypeRecovery',
   					type: 'POST',
   					data: formData,
   					processData: false,
   					contentType: false,
   					success: function (data, textStatus, jqXHR) {
   				    console.log(data);
   				 	 $('#recoveryFileModal').closeModal();
					 $('#markAsRecoverDone').openModal();
   						/*  $('#editStockModal').closeModal();
   						 $('#successUpdateStockModal').modal();
   						  if(data.errorCode==200){
   						
   						$('#stockSucessMessage').text('');
   						 $('#stockSucessMessage').text('Operation is not allowed');
   							 }
   						 else{
   							 $('#stockSucessMessage').text('');
   			 				 $('#stockSucessMessage').text('Your update on the form for transaction ID ('+data.txnId+') has been successfully updated.');
   						 } */
   					   // $('#updateConsignment').modal('open'); 
   						//alert("success");
   						
   					},
   					error: function (jqXHR, textStatus, errorThrown) {
   					console.log("error in ajax")
   					}
   				});
   	      
   	      }
   	      
  
   function multipleStolenRecovery(){
	   /*    			  
	      	      	 var supplierId=$('#editSupplierId').val();
	      	      	 var supplierName=$('#editSupplierName').val();
	      	      	 var filename=$('#editcsvUploadFileName').val();
	      	      	 var txnId=$('#editTransactionId').val();
	      	      	 var quantity=$('#editQuantity').val();
	      	      	 var InvoiceNumber=$('#editInvoiceNumber').val(); */
	      	      	  
	      	      	
	      	      	 
	      	      	 var stolenRecoverydata= 
	      	      			[{
	      	      		    
	      	      		    "txnId": "C2019103113182217",
	      	      		    "userId": 265
	      	      		  },
	      	      		  {
	      	      		    
	      	      		    "txnId": "C201910311318229976",
	      	      		    "userId": 266
	      	      		  }]
	      	      	 
	      	      	
	      	      	 $.ajax({
	      					url: '${context}/multipleStolenRecovery',
	      					type: 'POST',
	      					data: JSON.stringify(stolenRecoverydata),
	      					dataType : 'json',
	            			contentType : 'application/json; charset=utf-8',
	            			success: function (data, textStatus, jqXHR) {
	      						
	      						 console.log(data);
	      						/*  $('#editStockModal').closeModal();
	      						 $('#successUpdateStockModal').modal();
	      						  if(data.errorCode==200){
	      						
	      						$('#stockSucessMessage').text('');
	      						 $('#stockSucessMessage').text('Operation is not allowed');
	      							 }
	      						 else{
	      							 $('#stockSucessMessage').text('');
	      			 				 $('#stockSucessMessage').text('Your update on the form for transaction ID ('+data.txnId+') has been successfully updated.');
	      						 } */
	      					   // $('#updateConsignment').modal('open'); 
	      						//alert("success");
	      						
	      					},
	      					error: function (jqXHR, textStatus, errorThrown) {
	      					console.log("error in ajax")
	      					}
	      				});
	      	      
	      	      }
	      	      
	     
   function openStolenRecoveryModal(){
	   console.log("openStolenRecoveryModal===");
	   $('#stoleRecoveryModal').openModal();
   }
   
   function openFileStolenModal(){
	   console.log("openfileStolenModal===");
	 //  $("#materialize-lean-overlay-3").css("display","none");
	
	  $('#stoleRecoveryModal').closeModal();
	  setTimeout(function(){
		 
		  $('#fileStolenModal').openModal();
		}, 200);
	  //$("#materialize-lean-overlay-3").css("display","none");
	 
   }
 
   
   function openRecoveryModal(){
	   console.log("openfileStolenModal===");
	 //  $("#materialize-lean-overlay-3").css("display","none");
	
	  $('#stoleRecoveryModal').closeModal();
	  setTimeout(function(){
		 
		  $('#recoveryFileModal').openModal();
		}, 200);
	  //$("#materialize-lean-overlay-3").css("display","none");
	 
   }
   function closeStolenModalModal()
   {
  	 $('#fileStolenModal').closeModal();
  	 $(".lean-overlay").remove();
  	 }
   	
   function closeRecoveryModalModal()
   {
  	 $('#recoveryFileModal').closeModal();
  	 $(".lean-overlay").remove();
  	}
   	
  </script>
</body>
</html>