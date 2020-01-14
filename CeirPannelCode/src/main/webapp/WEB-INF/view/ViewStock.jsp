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
<link rel="stylesheet"
	href="${context}/resources/project_css/iconStates.css">


 <link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
 
 
  <script src="https://cdn.datatables.net/fixedcolumns/3.3.0/js/dataTables.fixedColumns.min.js"></script>

<style type="text/css">
 #starColor {
            color: red;
        }
</style>

</head>
<body data-roleType="${usertype}" data-userID="${userid}" data-userTypeID="${usertypeId}"
	data-selected-roleType="${selectedUserTypeId}" data-stolenselected-roleType="${stolenselectedUserTypeId}">


	<!-- START CONTENT -->
	<section id="content">
	<div id="initialloader"></div>
		<!--start container-->
		<div class="container">
			<div class="section">
				<div class="row">
					<div class="col s12 m12 l12">
						<div class="row card-panel">
							<div class="container-fluid pageHeader" id="pageHeader">

								<a href="" class="boton right" id="btnLink"></a>
							</div>

							<div class="col s12 m12 l12" id="consignmentTableDIv"
								style="padding-bottom: 5px; background-color: #e2edef52;">
								<div id="filterBtnDiv">
								</div>
							</div>

							<table id="stockTable" class="responsive-table striped display"></table>

						</div>

					</div>
				</div>
				<div id="footerBtn"></div>
			</div>
		</div>
		<!--end container-->
	</section>







	<!-- Modal 1 start   -->



	<div id="successUpdateStockModal" class="modal">
		<h6 class="modal-header" style="font-size: 16px;">Update Stock</h6>
		<div class="modal-content">
			
			

			<div class="row">
				<p id="stockSucessMessage">Your updated on the form for
					transaction ID (Tr12345678) has been successfull.</p>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<a href="" class="btn">ok</a>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal End -->

	<div id="editStockModal" class="modal">
				<h6 class="modal-header">Edit Stock</h6>
				<div class="modal-content">
	
			
			<form action="" onsubmit="return editUploadStock()" method="POST" enctype="multipart/form-data"  style="margin-top: 10px;">
				<div class="row myRow">
					<div class="input-field col s12 m6">
						<input type="text" name="SupplierId" id="editSupplierId"

							placeholder="" pattern="[A-Za-z0-9]{0,15}" title="Please enter alphabets and numbers upto 15 characters only" maxlength="15" /> <label
							for="SupplierId" class="center-align">Supplier ID</label>
					</div>

					<div class="input-field col s12 m6">
						<input type="text" name="SupplierName" id="editSupplierName" required="required"
							placeholder="" pattern="[A-Za-z ]{0,50}" title="Please enter alphabets  upto 50 characters only" maxlength="50" /> <label for="SupplierName"
							class="center-align">Supplier Name <span class="star">*</span></label></label>
					</div>

					<div class="input-field col s12 m6">
						<input type="text" name="Quantity" id="editQuantity"
							placeholder="" pattern="[0-9]{0,7}" title="Please enter numbers upto 7 characters only" maxlength="7" required /> <label
							for="Quantity" class="center-align">Quantity <span class="star">*</span></label></label>
					</div>

					<div class="input-field col s12 m6">
						<input type="text" name="InvoiceNumber" id="editInvoiceNumber"
							placeholder="" pattern="[A-Za-z0-9]{0,15}" title="Please enter alphabets and numbers upto 15 characters only"  maxlength="15"/> <label
							for="InvoiceNumber" class="center-align">Invoice Number</label>
					</div>

					<div class="input-field col s12 m6">
						<input type="text" name="TransactionId" id="editTransactionId"
							placeholder="" disabled maxlength="15" /> <label
							for="TransactionId" class="center-align">Transaction ID</label>
					</div>
				</div>


				<div class="row myRow">
					<h6 style="color: #000; margin-left: 10px; font-size: 16px;">
						Upload Bulk Stock <span class="star">*</span>
					</h6>
					<div class="file-field input-field col s12 m6"
						style="margin-top: 5px;">
						<div class="btn">
							<span>Select File</span> <input type="file"
								id="editcsvUploadFile" accept=".csv">
						</div>
						<div class="file-path-wrapper">
							<input class="file-path validate responsive-file-div"
								id="editcsvUploadFileName" type="text">
						</div>
					</div>
					<br>
					<br>
					<p style="margin-left: 10px;">
						<a href="./Consignment/sampleFileDownload/filetype=sample">Download Sample Format</a>
					</p>
				</div>

				<span> Required Field are marked with <span class="star">*</span></span>


				<div class="row">
					<div class="input-field col s12 center">
						<button class="btn"
							type="submit">Update</button>
						<a onclick="closeEditModal();" class="btn" type="cancel"
							style="margin-left: 10px;">Cancel</a>


					</div>
				</div>
			</form>
		</div>
	</div>


	<!-- View Stock Modal start   -->

	<div id="viewStockModal" class="modal">
		<h6 class="modal-header">View Stock</h6>
		<div class="modal-content">
			
			
			<form action="" style="margin-top: 10px;">

				<div class="row myRow">
					<div class="input-field col s12 m6">
						<input type="text" name="SupplierId" id="SupplierId"
							placeholder="" disabled /> <label for="SupplierId"
							class="center-align">Supplier ID</label>
					</div>

					<div class="input-field col s12 m6">
						<input type="text" name="SupplierName" id="SupplierName"
							placeholder="" disabled /> <label for="SupplierName"
							class="center-align">Supplier Name</label>
					</div>

					<div class="input-field col s12 m6">
						<input type="text" name="Quantity" id="Quantity" placeholder=""
							disabled /> <label for="Quantity" class="center-align">Quantity</label>
					</div>

					<div class="input-field col s12 m6">
						<input type="text" name="InvoiceNumber" id="InvoiceNumber"
							placeholder="" disabled /> <label for="InvoiceNumber"
							class="center-align">Invoice Number</label>
					</div>

					<div class="input-field col s12 m6">
						<input type="text" name="TransactionId" id="TransactionId"
							disabled placeholder="" maxlength="15" /> <label
							for="TransactionId" class="center-align">Transaction ID</label>
					</div>
				</div>

				<div class="row myRow">
					<h6 style="color: #000; margin-left: 10px; font-size: 16px;">
						Upload Bulk Stock
					</h6>
					<div class="file-field input-field col s12 m6"
						style="margin-top: 5px;">
						<!-- <div class="btn">
							<span>Select File</span> <input type="file" id="csvUploadFile"
								accept=".csv" disabled>
						</div> -->
						<div class="file-path-wrapper">
							<input class="file-path validate responsive-file-div"
								placeholder="fileName.csv" id="csvUploadFileName" type="text"
								disabled>
						</div>
					</div>
					<br>
					<br>
					
				</div>

				<div class="row center">

					<a onclick="closeViewModal()" class="btn" type="cancel">Close</a>
				</div>
			</form>
		</div>
	</div>

	<!-- --------------------------------------------------------------View Stock Modal End --------------------------------------------------------------->


	<!-- --------------------------------------------------------------Delete Stock Modal End --------------------------------------------------------------->


	<div id="DeleteStockconfirmationModal" class="modal">
		<h6 class="modal-header">Delete Stock</h6>
		<div class="modal-content">

			
			

			<div class="row">
				<h6>Are you sure you want to withdraw the stock details for
					transaction ID: <span id="stockdeleteTxnId"></span></h6>
			</div>
			<div class="row">
				<div class="input-field col s12 m12">
					<textarea id="deleteStockremark" class="materialize-textarea"></textarea>
					<label for="textarea1" class="">Remarks</label>
				</div>
			</div>
			<input type="text" id="popupTransactionId" maxlength="15" hidden />
			<div class="row">
				<div class="input-field col s12 center">
					<a onclick="confirmantiondelete()"
						class="modal-close modal-trigger btn" type="submit">Yes</a>
					<button class="modal-close btn" style="margin-left: 10px;">no</button>
				</div>
			</div>
		</div>
	</div>

	<div id="closeDeleteModal" class="modal">
			<h6 class="modal-header">Delete Stock</h6>
			<div class="modal-content">
		
			
			<div class="row">

				<h6 id="stockModalText">Stock Deleted Successfully </h6>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<a href="" class="modal-close btn"
						style="margin-left: 10px;">ok</a>
				</div>
			</div>
		</div>
	</div>
	
	  <div id="markAsMultipleStolen" class="modal">
       <h6 class="modal-header">Mark As Stolen</h6>
        <div class="modal-content">

            
            

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
                <h6 class="modal-header">Mark As Stolen</h6>
                    <div class="modal-content">

            
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
    <div id="ApproveStock" class="modal">
        <h6 class="modal-header">Approve Stock</h6>
        <div class="modal-content">
            
            <div class="row">
                <h6 id="stockApproveMessage">The stock with Transaction ID : <span id="approveStockTxnId"> </span> has been
                    successfully paid.</h6>
                    <input type="text" id="approveStockTransactionId" style="display: none;">
            </div>
            <div class="row">
                <h6 id="stockAppapprove">Do you approve the stock?</h6>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <div class="input-field col s12 center">
                        <button class=" modal-close modal-trigger btn"
                             onclick="approveStockSubmit(0)">Yes</button>
                        <button class="modal-close btn" style="margin-left: 10px;">No</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <div id="confirmApproveStockModal" class="modal">
           <h6 class="modal-header">Approve Stock</h6>
             <div class="modal-content">
       
            <div class="row">
                <h6 id="stockApproveSucessMessage">Stock Approved SuccessFully</h6>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <div class="input-field col s12 center">
                        <a href="./assignDistributor" class="modal-close btn">ok</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
      <div id="RejectStockModal" class="modal">
                    <h6 class="modal-header">Reject Stock</h6>
                    <div class="modal-content">

            <div class="row">
                <h6>Do you really want to mark the stock with Transaction ID: <span id="disaproveTxnId"> </span> as
                    rejected.</h6>
            </div>
            <div class="row">
                <div class="input-field col s12 m12" style="margin-left: -10px;">
                    <textarea id="stockDispproveRemarks" class="materialize-textarea" style="padding-left: 0;"></textarea>
                    <label for="textarea1">Remarks</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <div class="input-field col s12 center">
                        <button class="modal-close modal-trigger btn"
                            onclick="disApproveStockSubmit(1)">Yes</button>
                        <button class="modal-close btn" style="margin-left: 10px;">No</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
     <div id="confirmRejectStock" class="modal">
                <h6 class="modal-header">Reject Stock</h6>
                <div class="modal-content">
    
            <div class="row">
                <h6 id="stockDisapproveSucessMessage">Stock Rejected SuccessFully </h6>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <div class="input-field col s12 center">
                        <a href="./assignDistributor" class="modal-close btn">ok</a>
                    </div>
                </div>
            </div>
        </div>
    </div>

	<!-- Modal End -->
	<!-- END MAIN -->

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
	<!--prism
    <script type="text/javascript" src="${context}/resources/resources/js/prism/prism.js"></script>-->
	<!--scrollbar-->
	<script type="text/javascript"
		src="${context}/resources/js/plugins/perfect-scrollbar/perfect-scrollbar.min.js"></script>
	<!-- chartist -->
	<%-- <script type="text/javascript" src="${context}/resources/js/plugins/chartist-js/chartist.min.js"></script> --%>
	<script type="text/javascript"
		src="${context}/resources/project_js/viewStock.js"></script>
</body>
</html>

