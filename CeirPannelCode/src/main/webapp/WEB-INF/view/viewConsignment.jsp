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



</head>
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

								<a href="" class="boton right" id="btnLink"></a>
							</div>
							<form action="${context}/Consignment/viewConsignment"
								method="post">
								<div class="col s12 m12 l12" id="consignmentTableDIv"
									style="padding-bottom: 5px; background-color: #e2edef52;">
									<div id="filterBtnDiv">
										<!-- 							<div class='col s12 m2 l2'><button type='submit' class='btn primary botton' id='submitFilter'></button></div>
		 -->
									</div>
								</div>
							</form>
							<table id="consignmentLibraryTable"
								class="responsive-table striped display"></table>

						</div>

					</div>
				</div>
				<div id="footerBtn"></div>
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
                                                <input type="text" name="totalPrice" placeholder="" disabled="disabled"  id="viewtotalPrice" maxlength="7"
                                                    required />
                                                <label for="totalPrice" class="center-align">Total Price</label>
                                            </div>

                                            <div class="col s12 m6">
                                                <label for="Currency">Currency</label>
                                                <select id="viewcurrency" class="browser-default" disabled="disabled">
                                                    <option value="" disabled selected>Currency</option>
                                                 
                                                </select>
                                                <!-- <input type="text" id="viewcurrency" placeholder="" disabled="disabled"> -->
                                                <input type="text" id="viewhideCurrency"  style="display: none;">
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

			<h6>Delete Consignment</h6>
			<hr>

			<div class="row">
				<h6>
					Are you sure you want to withdraw the consignment details for (<span
						id="transID"></span>)
				</h6>
			</div>

			<div class="row">
				<div class="input-field col s12 m12">
					<textarea id="textarea1" class="materialize-textarea"></textarea>
					<label for="textarea1">Remarks</label>
				</div>
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
				<div class="input-field col s12 center">
					<div class="input-field col s12 center">
						<a href="${context}/Consignment/viewConsignment" class="btn">ok</a>
					</div>
				</div>
			</div>
		</div>
	</div>


<!-- -----------------------------------------------------------------Approved Model------------------------------------------------------------------------------ -->


  <div id="ApproveConsignment" class="modal">
    <div class="modal-content">
      <h6>Approve Consignment</h6>
      <hr>
      <div class="row">
        <h6>The tax against the consignment with (Importer/Company name) having Transaction ID : ( <span id="ApproveConsignmentTxnid"></span> ) has been
          successfully paid.</h6>
      </div>
      <div class="row">
        <h6>Do you approve the consignment?</h6>
        <input type="text" id="setApproveConsignmentTxnId" style="display: none">
      </div>
      <div class="row">
        <div class="input-field col s12 center">
          <div class="input-field col s12 center">
            <button class="modal-close modal-trigger btn" onclick="approveSubmit(0)">Yes</button>
            <button class="modal-close btn" style="margin-left: 10px;">No</button>
          </div>
        </div>
      </div>
    </div>
  </div>

<div id="confirmApproveConsignment" class="modal">
    <div class="modal-content">
      <h6>Approve Consignment</h6>
      <hr>
      <div class="row">
        <h6 id="approveSuccessMessage">The consignment has been successfully approved.</h6>
      </div>
      <div class="row">
        <div class="input-field col s12 center">
          <div class="input-field col s12 center">
            <a  href="${context}/Consignment/viewConsignment" class="modal-close btn">ok</a>
          </div>
        </div>
      </div>
    </div>
  </div>
  
  
    <div id="RejectConsignment" class="modal">
    <div class="modal-content">
      <h6>Reject Consignment</h6>
      <hr>
      <div class="row">
        <h6>Do you really want to mark the consignment (Importer/Company name) having Transaction ID: <span id="disaproveTxnId"></span> as
          rejected.</h6>
            <input type="text" id="setDisapproveConsignmentTxnId" style="display: none">
      </div>
      <div class="row">
        <div class="input-field col s12 m12" style="margin-left: -10px;">
          <textarea id="dispproveRemarks" class="materialize-textarea" style="padding-left: 0;"></textarea>
          <label for="textarea1">Remarks <span class="star">*</span></label>
        </div>
        <p>Required Field are marked with <span class="star">*</span></p>
      </div>
      <div class="row">
        <div class="input-field col s12 center">
          <div class="input-field col s12 center">
            <button class="modal-close modal-trigger btn" onclick="disapproveSubmit(1)">Yes</button>
            <button class="modal-close btn" style="margin-left: 10px;">No</button>
          </div>
        </div>
      </div>
    </div>
  </div>
  
  

  

  <div id="ApproveConsignment" class="modal">
    <div class="modal-content">
      <h6>Approve Consignment</h6>
      <hr>
      <div class="row">
        <h6>The tax against the consignment with (Importer/Company name) having Transaction ID : ( <span id="ApproveConsignmentTxnid"></span> ) has been
          successfully paid.</h6>
      </div>
      <div class="row">
        <h6>Do you approve the consignment?</h6>
        <input type="text" id="setApproveConsignmentTxnId" style="display: none">
      </div>
      <div class="row">
        <div class="input-field col s12 center">
          <div class="input-field col s12 center">
            <button class="modal-close modal-trigger btn" onclick="approveSubmit(0)">Yes</button>
            <button class="modal-close btn" style="margin-left: 10px;">No</button>
          </div>
        </div>
      </div>
    </div>
  </div>

<div id="confirmApproveConsignment" class="modal">
    <div class="modal-content">
      <h6>Approve Consignment</h6>
      <hr>
      <div class="row">
        <h6 id="approveSuccessMessage">The consignment has been successfully approved.</h6>
      </div>
      <div class="row">
        <div class="input-field col s12 center">
          <div class="input-field col s12 center">
            <a  href="${context}/Consignment/viewConsignment" class="modal-close btn">ok</a>
          </div>
        </div>
      </div>
    </div>
  </div>
  
  
    <div id="RejectConsignment" class="modal">
    <div class="modal-content">
      <h6>Reject Consignment</h6>
      <hr>
      <div class="row">
        <h6>Do you really want to mark the consignment (Importer/Company name) having Transaction ID: <span id="disaproveTxnId"></span> as
          rejected.</h6>
            <input type="text" id="setDisapproveConsignmentTxnId" style="display: none">
      </div>
      <div class="row">
        <div class="input-field col s12 m12" style="margin-left: -10px;">
          <textarea id="dispproveRemarks" class="materialize-textarea" style="padding-left: 0;"></textarea>
          <label for="textarea1">Remarks <span class="star">*</span></label>
        </div>
        <p>Required Field are marked with <span class="star">*</span></p>
      </div>
      <div class="row">
        <div class="input-field col s12 center">
          <div class="input-field col s12 center">
            <button class="modal-close modal-trigger btn" onclick="disapproveSubmit(1)">Yes</button>
            <button class="modal-close btn" style="margin-left: 10px;">No</button>
          </div>
        </div>
      </div>
    </div>
  </div>
  
  
  <div id="confirmRejectConsignment" class="modal">
    <div class="modal-content">
      <h6>Reject Consignment</h6>
      <hr>
      <div class="row">
        <h6 id="disapproveSuccessMessage">The consignment has been marked as rejected.</h6>
      </div>
      <div class="row">
        <div class="input-field col s12 center">
          <div class="input-field col s12 center">
            <a href="${context}/Consignment/viewConsignment" class="modal-close btn">ok</a>
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
				
				
                                            <div class="input-field col s12 m6">
                                                <input type="text" name="totalPrice" id="totalPrice" maxlength="7"
                                                    required placeholder="" />
                                                <label for="totalPrice" class="center-align">Total Price</label>
                                            </div>

                                            <div class="col s12 m6">
                                                <label for="Currency">Currency</label>
                                                <select id="currency" class="browser-default">
                                                    <option value="" disabled selected>Currency</option>
                                                   
                                                </select>
                                                  <input type="text" id="hideCurrency"  style="display: none;">
                                            </div>
			
				<div class="file-field input-field col s12 m6"
					style="margin-top: 5px;">
					<h6 class="file-upload-heading" style="margin-top: -5px;">
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
				<a href="./sampleFileDownload/filetype=sample">Download Sample Format</a>
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
	
	   <div id="markAsMultipleStolen" class="modal">
        <div class="modal-content">

            <h6>Mark As Stolen</h6>
            <hr>

            <div class="row">
                <h6>Do you want to mark the following transaction  as stolen?</h6>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <a onclick="openMulipleStolenPopUp()" class="modal-close modal-trigger btn">Yes</a>
                    <button class="modal-close btn" style="margin-left: 10px;">no</button>
                </div>
            </div>
        </div>
    </div>
     <div id="markAsStolenDone" class="modal">
        <div class="modal-content">
            <h6>Mark As Stolen</h6>
            <hr>
            <div class="row">
                <h6>To be the following Transaction ID's marked as stolen has been recieved successfully.
                </h6>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <!-- <button class="modal-close btn" style="margin-left: 10px;">ok</button> -->
                    <a onclick="redirectToViewPage()" class="btn">ok</a>
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
		src="${context}/resources/project_js/viewConsignment.js"></script>
		
</body>
</html>