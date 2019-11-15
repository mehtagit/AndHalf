 
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
   				url: "./headers?type=stolen",
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
   					url: "./stolen/pageRendering",
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
 				
   				$('#'+button[i].id).attr("href", button[i].buttonURL);
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
	    
	    var sourceType;
	    var requestType;
	    var role = currentRoleType == null ? roleType : currentRoleType;
	    var blockPeriod=$('#blockPeriod').val();
   	    var blockType=$('#blockType').val();
   	      	
   	      	  
   	      	
   	      	 
   	      	 	var formData= new FormData();
   	      		formData.append('blockingType',blockType);
   	      	 	formData.append('blockingTimePeriod',blockPeriod);
   	      	 	formData.append('requestType',requestType);
   	      	 	formData.append('roleType',role);
   	      		formData.append('sourceType',sourceType);
   	      		formData.append('userId',userId);
   	      		
   	      		
   	      		console.log(JSON.stringify(formData));
   	      		console.log("*********");
   	      	 	
   	      	 $.ajax({
   					url: './fileTypeStolen',
   					type: 'POST',
   					data: formData,
   					processData: false,
   					contentType: false,
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
   	      
  

   function fileRecoveryReport(){
	    var roleType = $("body").attr("data-roleType");
	    var userId = $("body").attr("data-userID");
	    var currentRoleType = $("body").attr("data-selected-roleType"); 
	    var sourceType;
	    var requestType;
	    var role = currentRoleType == null ? roleType : currentRoleType;
	   	var formData= new FormData();
   	      	 
	   	     formData.append('requestType',requestType);
   	      	 formData.append('roleType',role);
   	         formData.append('sourceType',sourceType);
   	      	 formData.append('userId',userId);
   	      	 
   	      	 console.log(JSON.stringify(formData));
   	      	 console.log("*********");
   	      	 $.ajax({
   					url: './fileTypeStolen',
   					type: 'POST',
   					data: formData,
   					processData: false,
   					contentType: false,
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
	      					url: './multipleStolenRecovery',
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
	      	      
	     
   			 