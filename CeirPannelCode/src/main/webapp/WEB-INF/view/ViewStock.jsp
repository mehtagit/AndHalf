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

#id7 {
	width: 150px;
}

/* icon color */

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
.eventNone {
    user-select: none;
    pointer-events: none;
}
.disable {
    color: grey;
}
</style>

</head>
<body data-roleType="${usertype}" data-userID="${userid}" data-selected-roleType="${selectedUserTypeId}">


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

							<div class="col s12 m12 l12" id="consignmentTableDIv"
								style="padding-bottom: 5px; background-color: #e2edef52;">
								<div id="filterBtnDiv">
									<!-- 							<div class='col s12 m2 l2'><button type='submit' class='btn primary botton' id='submitFilter'></button></div>
		 -->
								</div>
							</div>

							<table id="stockTable"
								class="responsive-table striped display"></table>

						</div>

					</div>
				</div>
			</div>
		</div>
		<!--end container-->
	</section>







<!-- Modal 1 start   -->

   

    <div id="successUpdateStockModal" class="modal">
        <div class="modal-content">
            <h6 style="font-size: 16px;">Update Stock</h6><hr>

            <div class="row">
                <p id="stockSucessMessage">Your updated on the form for transaction ID (Tr12345678) has been successfull.</p>
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
        <div class="modal-content">
            <h6>Edit Stock</h6>
            <hr>
            <form action="" style="margin-top: 10px;">
                    <div class="row myRow">
                        <div class="input-field col s12 m6">
                            <input type="text" name="SupplierId" id="editSupplierId" placeholder="ABCD12345" maxlength="15" />
                            <label for="SupplierId" class="center-align">Supplier ID</label>
                        </div>

                        <div class="input-field col s12 m6">
                            <input type="text" name="SupplierName" id="editSupplierName" placeholder="Abcd"
                                maxlength="15" />
                            <label for="SupplierName" class="center-align">Supplier Name </label>
                        </div>

                        <div class="input-field col s12 m6">
                            <input type="text" name="Quantity" id="editQuantity" placeholder="120" maxlength="7"
                                required />
                            <label for="Quantity" class="center-align">Quantity </label>
                        </div>

                        <div class="input-field col s12 m6">
                            <input type="text" name="InvoiceNumber" id="editInvoiceNumber" placeholder="12345678"
                                maxlength="15" />
                            <label for="InvoiceNumber" class="center-align">Invoice Number</label>
                        </div>

                        <div class="input-field col s12 m6">
                            <input type="text" name="TransactionId" id="editTransactionId" placeholder="TR12345678" disabled
                                maxlength="15" />
                            <label for="TransactionId" class="center-align">Transaction ID</label>
                        </div>
                    </div>


                    <div class="row myRow">
                        <h6 style="color: #000; margin-left: 10px; font-size: 16px;">Upload Bulk
                            Stock <span class="star">*</span></h6>
                        <div class="file-field input-field col s12 m6" style="margin-top: 5px;">
                            <div class="btn">
                                <span>Select File</span>
                                <input type="file" id="editcsvUploadFile" accept=".csv">
                            </div>
                            <div class="file-path-wrapper">
							<input class="file-path validate responsive-file-div" id="editcsvUploadFileName" type="text">
                            </div>
                        </div><br><br>
                        <p style="margin-left: 10px;"><a href="#">Download Sample Format</a></p>
                    </div>

                    <span> Required Field are marked with <span class="star">*</span></span>


                        <div class="row" >
                            <div class="input-field col s12 center">
                                <button class="waves-effect waves-light modal-trigger btn"
                                    data-target="submitStock" onclick="editUploadStock()" type="button">Update</button>
                                <a onclick="closeEditModal();" class="btn" type="cancel"
                                    style="margin-left: 10px;">Cancel</a>


                            </div>
                        </div>
                </form>
        </div>
    </div>


<!-- View Stock Modal start   -->

    <div id="viewStockModal" class="modal">
        <div class="modal-content">
            <h6>View Stock</h6>
            <hr>
            <form action="" style="margin-top: 10px;">

                    <div class="row myRow">
                        <div class="input-field col s12 m6">
                            <input type="text" name="SupplierId" id="SupplierId"
                                placeholder="ABCD12345" disabled />
                            <label for="SupplierId" class="center-align">Supplier ID</label>
                        </div>

                        <div class="input-field col s12 m6">
                            <input type="text" name="SupplierName" id="SupplierName"
                                placeholder="Abcd" disabled />
                            <label for="SupplierName" class="center-align">Supplier Name</label>
                        </div>

                        <div class="input-field col s12 m6">
                            <input type="text" name="Quantity" id="Quantity" placeholder="120"
                                disabled />
                            <label for="Quantity" class="center-align">Quantity</label>
                        </div>

                        <div class="input-field col s12 m6">
                            <input type="text" name="InvoiceNumber" id="InvoiceNumber"
                                placeholder="12345678" disabled />
                            <label for="InvoiceNumber" class="center-align">Invoice Number</label>
                        </div>

                        <div class="input-field col s12 m6">
                            <input type="text" name="TransactionId" id="TransactionId" disabled
                                placeholder="TR12345678" maxlength="15" />
                            <label for="TransactionId" class="center-align">Transaction ID</label>
                        </div>
                    </div>
					
					<div class="row myRow">
                        <h6 style="color: #000; margin-left: 10px; font-size: 16px;">Upload Bulk
                            Stock <span class="star">*</span></h6>
                        <div class="file-field input-field col s12 m6" style="margin-top: 5px;">
                            <div class="btn">
                                <span>Select File</span>
      
      
                                <input type="file" id="csvUploadFile" accept=".csv" disabled>
                            </div>
                            <div class="file-path-wrapper">
                                <input class="file-path validate responsive-file-div" placeholder="fileName.csv" id="csvUploadFileName" type="text" disabled>
                            </div>
                        </div><br><br>
                        <p style="margin-left: 10px;"><a href="#">Download Sample Format</a></p>
                    </div>

                    <div class="row center">
                        <a onclick="closeViewModal()" class="btn" type="cancel">Cancel</a>
                    </div>
                </form>
        </div>
    </div>

<!-- --------------------------------------------------------------View Stock Modal End --------------------------------------------------------------->
 
 
 <!-- --------------------------------------------------------------Delete Stock Modal End --------------------------------------------------------------->
 	
 	
    <div id="DeleteStockconfirmationModal" class="modal">
        <div class="modal-content">

            <h6>Delete Stock</h6>
            <hr>

            <div class="row">
                <h6>Are you sure you want to withdraw the stock details for transaction ID </h6>
            </div>
             <input type="text" id="popupTransactionId" maxlength="15" hidden/>
            <div class="row">
                <div class="input-field col s12 center">
                    <a onclick="confirmantiondelete()"  class="modal-close modal-trigger btn" type="submit">Yes</a>
                    <button class="modal-close btn"  style="margin-left: 10px;">no</button>
                </div>
            </div>
        </div>
    </div>
    
 	<div id="closeDeleteModal" class="modal">
        <div class="modal-content">
            <h6>Delete Stock</h6>
            <hr>
            <div class="row">
           
                <h6 id="stockModalText"></h6>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <a href="./assignDistributor" class="modal-close btn" style="margin-left: 10px;" >ok</a>
                </div>
            </div>
        </div>
     </div>

    <!-- Modal End -->
    <!-- END MAIN -->


</body>
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
		
		
			<script>
$(document).ready(function(){
$('.datepicker').datepicker();
});

$('.datepicker').on('mousedown',function(event){
event.preventDefault();
});






//*******************************************View Pop up data *************************************************************************************************
function viewUploadedStockDetails(txnId){
 
	 $("#viewStockModal").openModal();
	 var roleType = $("body").attr("data-roleType");
	    var userId = $("body").attr("data-userID");
	    var currentRoleType = $("body").attr("data-selected-roleType"); 
	    var role = currentRoleType == null ? roleType : currentRoleType;
	    
	     	$.ajax({
	 				url : "./openStockPopup?reqType=editPage&txnId="+txnId+'&role='+role,
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
	console.log("_________________++++++++++"+data)
	
	$("#SupplierId").val(data.supplierId);
	$("#SupplierName").val(data.suplierName);
	$("#InvoiceNumber").val(data.invoiceNumber);
	$("#Quantity").val(data.quantity);
	$("#TransactionId").val(data.txnId);
	$("#csvUploadFileName").val(data.fileName);
	
	
}


//******************************************* end View Pop up data *************************************************************************************************

//******************************************* start edit Pop up data *************************************************************************************************

function EditUploadedStockDetails(txnId){ 
	

	 var roleType = $("body").attr("data-roleType");
    var userId = $("body").attr("data-userID");
    var currentRoleType = $("body").attr("data-selected-roleType"); 
    var role = currentRoleType == null ? roleType : currentRoleType;
    
     	$.ajax({
 				url : "./openStockPopup?reqType=editPage&txnId="+txnId+'&role='+role,
 				dataType : 'json',
 				contentType : 'application/json; charset=utf-8',
 				type : 'GET',
 				success : function(data) {
 					console.log(data)
 					setEditPopupData(data) 
 				},
 				error : function() {
 					alert("Failed");
 				}
 			});
     	 
     	 $("#editStockModal").openModal();
      }


function setEditPopupData(data){
	console.log()
	$("#editSupplierId").val(data.supplierId);
	$("#editSupplierName").val(data.suplierName);
	$("#editInvoiceNumber").val(data.invoiceNumber);
	$("#editQuantity").val(data.quantity);
	$("#editTransactionId").val(data.txnId);
	$("#editcsvUploadFileName").val(data.fileName);
	
} 


//********************************************************************************************************************************************************
//********************************************************* update Stock function ****************************************************************************

function editUploadStock(){
  
      	 var supplierId=$('#editSupplierId').val();
      	 var supplierName=$('#editSupplierName').val();
      	 var filename=$('#editcsvUploadFileName').val();
      	 var txnId=$('#editTransactionId').val();
      	 var quantity=$('#editQuantity').val();
      	 var InvoiceNumber=$('#editInvoiceNumber').val();
      	  
      	 console.log(supplierName,supplierName,filename,txnId,quantity,InvoiceNumber);
      	 
      	 var formData= new FormData();
      		formData.append('file', $('#editcsvUploadFile')[0].files[0]);
      	 	formData.append('supplierId',supplierId);
      	 	formData.append('supplierName',supplierName);
      	 	formData.append('quantity',quantity);
      		formData.append('txnId',txnId);
      		formData.append('filename',filename);
      		formData.append('invoiceNumber',InvoiceNumber);
      		
      		console.log(JSON.stringify(formData));
      		console.log("*********");
      	 	
      	 $.ajax({
				url: '${context}/updateUploadedStock',
				type: 'POST',
				data: formData,
				processData: false,
				contentType: false,
				success: function (data, textStatus, jqXHR) {
					
					 console.log(data);
					 $('#editStockModal').closeModal();
					 $('#successUpdateStockModal').modal();
					  if(data.errorCode==200){
					
					$('#stockSucessMessage').text('');
					 $('#stockSucessMessage').text('Operation is not allowed');
						 }
					 else{
						 $('#stockSucessMessage').text('');
		 				 $('#stockSucessMessage').text('Your update on the form for transaction ID ('+data.txnId+') has been successfully updated.');
					 }
				   // $('#updateConsignment').modal('open'); 
					//alert("success");
					
				},
				error: function (jqXHR, textStatus, errorThrown) {
				console.log("error in ajax")
				}
			});
      
      }
      
    
      
//*************************************************************** End update Stock function *********************************************************


//****************************************************** Delete Stock Modal ***************************************************************************

		function DeleteStockRecord(txnId){
    		 $("#DeleteStockconfirmationModal").openModal();
     	 $("#popupTransactionId").text(txnId);
     }
     
     
     function confirmantiondelete(){
     	 var txnId = $("#popupTransactionId").text();
     	 var roleType = $("body").attr("data-roleType");
         var userId = $("body").attr("data-userID");
         var currentRoleType = $("body").attr("data-selected-roleType"); 
         var role = currentRoleType == null ? roleType : currentRoleType;
         
     	 var obj ={
     			 "txnId" : txnId,
     			 "roleType":role
     	 }
     	 
     	 $.ajax({
     			url : "./stockDelete",
     			data : JSON.stringify(obj),
     			dataType : 'json',
     			contentType : 'application/json; charset=utf-8',
     			type : 'POST',
     			success : function(data, textStatus, xhr) {
     				console.log(data);
     				if(data.errorCode == 200){
     					$("#stockModalText").text(data.message);
     				}else if(data.errorCode == 0){
     					$("#stockModalText").text(data.message);
     				}
     		  	     $("#materialize-lean-overlay-3").css("display","none");
     			},
     			error : function() {
     					console.log("Error");
     			}
     		});
  		 $("#DeleteStockconfirmationModal").closeModal();
  		 
  		 $("#closeDeleteModal").openModal();
  		/*  
  		 $(".lean-overlay").remove(); */ 

     }






    /*  function closeDeleteModal(){
    	 $("#DeleteStockconfirmationModal").closeModal();
    	// $('#updateModal').closeModal();
    	 $(".lean-overlay").remove();
     } */
     
     function closeViewModal()
     {
    	 $('#viewStockModal').closeModal();
    	 $(".lean-overlay").remove();
    	 
     }
     
     function closeEditModal()
     {
    	 $('#editStockModal').closeModal();
    	 $(".lean-overlay").remove();
    	 
     }

</script>
	<script type="text/javascript">
	
	
	 var roleType = $("body").attr("data-roleType");
     var userId = $("body").attr("data-userID");
     var currentRoleType = $("body").attr("data-selected-roleType"); 
     var role = currentRoleType == null ? roleType : currentRoleType;
   var jsonObj = {
    	 "consignmentStatus": null,
    	 "endDate": "2019-11-11T10:53:37.289Z",
    	 "roleType": role,
    	 "startDate": "2019-11-11T10:53:37.290Z",
    	 "taxPaidStatus": null,
    	 "userId": userId
    	 };
  console.log("REQUEST JSON:::::::::::::::::::::"+jsonObj)
    $(document).ready(function () {
    	 $.ajax({
	url: "${context}/headers",
	type: 'POST',
	dataType: "json",
	success: function(result){
			var table=	$("#stockTable").DataTable({
                "serverSide": true,
    			orderCellsTop : true,
    			"aaSorting" : [],
    			"bPaginate" : true,
    			"bFilter" : true,
    			"bInfo" : true,
    			"bSearchable" : true,
				ajax: {
					type: 'POST',
           		        url: '${context}/stockData',           		        
           		    	data : function(d) {
          		    		d.filter = JSON.stringify(jsonObj);       		    		
           				}
         		},
                "columns": result
            });
	}
					}); 
    });				
		
 $.ajax({
		url: "${context}/stock/pageRendering",
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

$("#consignmentTableDIv").append("<div class='col s12 m2 l2'><button type='submit' class='btn primary botton' id='submitFilter'></button></div>");
for(i=0; i<button.length; i++){
	$('#'+button[i].id).text(button[i].buttonTitle);
	$('#'+button[i].id).attr("href", button[i].buttonURL);
	}
		}

//$("#filterBtnDiv").append();
}); 
</script>
</html>

