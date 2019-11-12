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

   <script type="text/javascript" src="${context}/resources/js/plugins/jquery-1.11.2.min.js"></script> 
  <!--   <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css"></script>  
 -->

  <!-- CORE CSS-->
  <link href="${context}/resources/css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection">
  <link href="${context}/resources/css/style.css" type="text/css" rel="stylesheet" media="screen,projection">
   <script type="text/javascript" src="${context}/resources/js/plugins/data-tables/js/jquery.dataTables.min.js"></script>
    <link href="${context}/resources/js/plugins/data-tables/css/jquery.dataTables.min.css" type="text/css" rel="stylesheet" media="screen,projection">
    <link href="${context}/resources/js/plugins/data-tables/css/jquery.dataTables.css" type="text/css" rel="stylesheet" media="screen,projection">
  <link href="${context}/resources/css/jquery-datepicker2.css" type="text/css" rel="stylesheet" media="screen,projection">
  <!-- Custome CSS-->    
  <link href="${context}/resources/css/custom/custom.css" type="text/css" rel="stylesheet" media="screen,projection">
   <link href="${context}/resources/font/font-awesome/css/font-awesome.min.css" type="text/css" rel="stylesheet" media="screen,projection">

  <!-- INCLUDED PLUGIN CSS ON THIS PAGE -->
  <link href="${context}/resources/js/plugins/prism/prism.css" type="text/css" rel="stylesheet" media="screen,projection">
  <link href="${context}/resources/js/plugins/perfect-scrollbar/perfect-scrollbar.css" type="text/css" rel="stylesheet" media="screen,projection">
 <%--  <link href="${context}/resources/js/plugins/chartist-js/chartist.min.css" type="text/css" rel="stylesheet" media="screen,projection"> --%>
<style>
        .row {
            margin-bottom: 0;
            margin-top: 0;
        }

        input[type=text] {
            height: 35px;
            margin: 0 0 5px 0;
        }

        .checkboxFont {
            font-size: 16px;
            margin-right: 10px;
        }

        textarea.materialize-textarea {
            padding: 0;
            padding-left: 10px;
        }

        .btn-flat {
            height: auto;
        }

        .dropdown-content li>a, .dropdown-content li>span {
            color: #444;
        }
        .welcomeMsg {
          padding-bottom: 50px !important;
          line-height: 1.5 !important;
          text-align: center;
        }
    </style>
</head>
<body>	
  <!-- START MAIN -->
 

 <section id="content">
                <!--start container-->
                <div class="container">
                    <div class="section">
                        <div class="row">
                            <div class="col s12 m12 l12">
                                <div class="row card-panel">
                                        <div class="container-fluid pageHeader">
                                            <p class="PageHeading">View Stock</p>
                                            <a href="${context}/openUploadStock?reqType=formPage" type="button" class="boton right">Upload
                                                Stock</a>
                                        </div>

                                        <div class="col s12 m12 l12" id="distributorTableDIv"
                                            style="padding-bottom: 5px;background-color: #e2edef52;">

                                            <div class="col s6 m3 l3 responsiveDiv">
                                                <label for="TotalPrice">Start date</label>
                                                <div id="startdatepicker" class="input-group date"
                                                    data-date-format="yyyy-mm-dd" style="    margin-top: 10px;">
                                                    <input class="form-control" type="date" id="datepicker"
                                                        style="margin-top: -9px" />
                                                    <span class="input-group-addon" style="color:#ff4081"><i
                                                            class="fa fa-calendar" aria-hidden="true"
                                                            style="float: right; margin-top: -30px;"></i></span>
                                                </div>

                                            </div>
                                            <div class="col s6 m3 l3 responsiveDiv">
                                                <label for="TotalPrice">End date</label>
                                                <div id="enddatepicker" class="input-group date"
                                                    data-date-format="yyyy-mm-dd" style="    margin-top: 10px;">

                                                    <input class="form-control" id="endDateFilter" type="date"
                                                        style="margin-top: -9px" />
                                                    <span class="input-group-addon" style="color:#ff4081"><i
                                                            class="fa fa-calendar" aria-hidden="true"
                                                            style="float: right; margin-top: -30px;"></i></span>
                                                </div>
                                            </div>



                                            <div class="col s6 m3 l3 selectDropdwn" style="margin-top: 3px;">
                                                <br />
                                                <!-- <label for="TotalPrice" class="center-align">Stock Status</label> -->
                                                <select id="filterFileStatus"
                                                    class="browser-default">
                                                    <option value="" disabled selected>Stock Status</option>
                                                    <option value="Success">Success</option>
                                                    <option value="Error">Error</option>
                                                    <option value="Processing">Processing</option>
                                                    <option value="INIT">INIT</option>
                                                </select>

                                            </div>
                                            <div class="col s12 m3 l3">
                                                <button type="button" class="btn primary botton" id="submitFilter"
                                                    onclick="_Services._submitToDistributorFilterData()">Filter</button>
                                            </div>
                                        </div>
                                        <table class="responsive-table striped display" id="data-table-simple"
                                            cellspacing="0">
                                            <thead>
                                                <tr>
                                                    <th>Date</th>
                                                    <th>Transaction ID</th>
                                                    <th>File Name</th>
                                                    <th>Stock Status</th>
                                                    <th style="width: 180px !important">Action</th>
                                                </tr>
                                            </thead>
                                            <tbody id="consignmentTableLibraryTbody">
                                                <tr>
                                                    <td>20/10/2018</td>
                                                    <td>GFrt4581</td>
                                                    <td>name.csv</td>
                                                    <td>Success</td>
                                                    <td style="width: 180px !important">
                                                        <a href="#ErrorFile"><i class="fa fa-exclamation-circle"
                                                                title="ErrorFile"
                                                                style=" color:red; font-size:20px; margin-right:15px;"></i></a>
                                                        <a href="#" ><i class="fa fa-download"
                                                                aria-hidden="true"
                                                                style="font-size: 20px;  color:#2e8b57;"
                                                                title="download"></i></a>
                                                        <a onclick="viewUploadedStockDetails('C20191111104643073')"><i
                                                                class="fa fa-eye teal-text" aria-hidden="true"
                                                                title="view"
                                                                style="font-size: 20px; margin:0 0 0 15px;"></i></a>
                                                        <a href="editStockManagement.html"><i class="fa fa-pencil"
                                                                aria-hidden="true"
                                                                style="font-size: 20px; margin:0 15px 0 15px; color: #006994"
                                                                title="edit"></i></a>
                                                        <a href="#DeleteStock"
                                                            class="waves-effect waves-light modal-trigger mr-3"><i
                                                                class="fa fa-trash" aria-hidden="true"
                                                                style="font-size: 20px; color: red;"
                                                                title="delete"></i></a>
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
            <!-- END CONTENT -->

            <!-- //////////////////////////////////////////////////////////////////////////// -->
            <!-- START RIGHT SIDEBAR NAV-->

            <!-- LEFT RIGHT SIDEBAR NAV-->

      
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
                                <input class="file-path validate responsive-file-div" type="text">
                            </div>
                        </div><br><br>
                        <p style="margin-left: 10px;"><a href="#">Download Sample Format</a></p>
                    </div>

                    <span> Required Field are marked with <span class="star">*</span></span>


                        <div class="row" style="padding-bottom: 100px;">
                            <div class="input-field col s12 center">
                                <button class="waves-effect waves-light modal-trigger btn"
                                    data-target="submitStock" type="submit">Update</button>
                                <a href="stockManagement.html" class="btn" type="cancel"
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
                                <input class="file-path validate responsive-file-div" placeholder="fileName.csv" type="text" disabled>
                            </div>
                        </div><br><br>
                        <p style="margin-left: 10px;"><a href="#">Download Sample Format</a></p>
                    </div>

                    <div class="row center" style="padding:20px 0 100px 0;">
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
                    <button class="modal-close btn" onclick="closeDeleteModal()" style="margin-left: 10px;">no</button>
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
                    <button class="modal-close btn" style="margin-left: 10px;" onclick="confirmantiondelete()">ok</button>
                </div>
            </div>
        </div>
     </div>

    <!-- Modal End -->
    <!-- END MAIN -->



 <!------------------------------------------------------------- materialize js --------------------------------------------------------------------------- -->
 <!------------------------------------------------------------- materialize js --------------------------------------------------------------------------- -->


      <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
   	  <script type="text/javascript" src="${context}/resources/js/materialize.js"></script>
      <script type="text/javascript" src="${context}/resources/js/plugins/data-tables/js/jquery.dataTables.js"></script>
      <script type="text/javascript" src="${context}/resources/js/plugins/data-tables/js/jquery.dataTables.min.js"></script>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.3/moment.min.js"></script>
      <script type="text/javascript" src="${context}/resources/js/jquery-datepicker2.js"></script>
      

      <!--plugins.js - Some Specific JS codes for Plugin Settings-->
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  
      <!--custom-script.js - Add your own theme custom JS-->
 	
 	  <script type="text/javascript" src="${context}/resources/js/plugins.js"></script>
      <script type="text/javascript" src="${context}/resources/js/Validator.js"></script>
   
  	   <!--scrollbar-->
      <script type="text/javascript" src="${context}/resources/js/plugins/perfect-scrollbar/perfect-scrollbar.min.js"></script>
  
      <!-- chartist -->
      <%-- <script type="text/javascript" src="${context}/resources/js/plugins/chartist-js/chartist.min.js"></script> --%>
       <script type="text/javascript" src="${context}/resources/js/countries.js"></script>
     
     
     
 <!------------------------------------------------------------- End  materialize js --------------------------------------------------------------------------- -->
 <!------------------------------------------------------------- End materialize js --------------------------------------------------------------------------- -->  
     
     

<script type="text/javascript">

// *******************************************View Pop up data *************************************************************************************************
function viewUploadedStockDetails(txnId){
    
	 $("#viewStockModal").openModal();
	 $.ajax({
			url : "./openStockPopup?reqType=editPage&txnId="+txnId,
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
	$("#csvUploadFile").val(data.fileName); 
	
	
}


//******************************************* end View Pop up data *************************************************************************************************

//******************************************* start edit Pop up data *************************************************************************************************

 function EditUploadedStockDetails(txnId){ 	
        	$.ajax({
    				url : "./openStockPopup?reqType=editPage&txnId="+txnId,
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
	$("#editcsvUploadFile").val(data.fileName);
 	
 } 


//********************************************************************************************************************************************************
//********************************************************* update Stock function ****************************************************************************

function editRegisterConsignment(){
     
         	 var supplierId=$('#editSupplierId').val();
         	 var supplierName=$('#editSupplierName').val();
         	 var filename=$('#editcsvUploadFile').val();
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
         		formData.append('invoiceNumber',filename);
         		
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
        	 var obj ={
        			 "txnId" : txnId,
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
        			},
        			error : function() {
        					console.log("Error");
        			}
        		});
     		 $("#DeleteStockconfirmationModal").closeModal();
     		 $("#closeDeleteModal").openModal();
        }






        function closeDeleteModal(){
       	 $("#DeleteStockconfirmationModal").closeModal();
       	// $('#updateModal').closeModal();
       	 $(".lean-overlay").remove();
        }
        
        function closeViewModal()
        {
       	 $('#viewStockModal').closeModal();
       	 $(".lean-overlay").remove();
       	 
        }








</script>

</body>
</html>