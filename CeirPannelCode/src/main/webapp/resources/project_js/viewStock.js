$(document).ready(function(){
$('.datepicker').datepicker();
});

$('.datepicker').on('mousedown',function(event){
event.preventDefault();
});



var roleType = $("body").attr("data-roleType");
var userId = $("body").attr("data-userID");
var currentRoleType = $("body").attr("data-selected-roleType"); 


//*******************************************View Pop up data *************************************************************************************************
function viewUploadedStockDetails(txnId){
 
	 $("#viewStockModal").openModal();
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
				url: 'updateUploadedStock',
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
    		 $("#stockdeleteTxnId").text(txnId);
     }
     
     
     function confirmantiondelete(){
         var role = currentRoleType == null ? roleType : currentRoleType;
         var txnId= $("#stockdeleteTxnId").text();
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
	url: "headers",
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
           		        url: 'stockData',           		        
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
		url: "stock/pageRendering",
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
