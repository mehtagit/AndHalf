<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<title>Edit Consignment</title>

<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta content="" name="description" />
<meta content="" name="author" />

<script type="text/javascript"
	src="${context}/resources/js/plugins/jquery-1.11.2.min.js"></script>

<!-- CORE CSS-->
<link href="${context}/resources/css/materialize.css" type="text/css"
	rel="stylesheet" media="screen,projection">
<link href="${context}/resources/css/style.css" type="text/css"
	rel="stylesheet" media="screen,projection">
<!-- Custome CSS-->
<link href="${context}/resources/css/custom/custom.css"
	type="text/css" rel="stylesheet" media="screen,projection">
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

<script type="text/javascript"
	src="${context}/resources/js/plugins/data-tables/js/jquery.dataTables.min.js"></script>
<link
	href="${context}/resources/js/plugins/data-tables/css/jquery.dataTables.min.css"
	type="text/css" rel="stylesheet" media="screen,projection">
<link
	href="${context}/resources/js/plugins/data-tables/css/jquery.dataTables.css"
	type="text/css" rel="stylesheet" media="screen,projection">

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
  left: 45%;
  top: 0px;
  font-size: 17px;
  height:50px;
 line-height:0px;
 }

#snackbar.show {
  visibility: visible;
  -webkit-animation: fadein 0.5s, fadeout 0.5s 2.5s;
  animation: fadein 0.5s, fadeout 0.5s 2.5s;
}

@-webkit-keyframes fadein {
  from {bottom: 0; opacity: 0;} 
  to {bottom: 30px; opacity: 1;}
}

@keyframes fadein {
  from {bottom: 0; opacity: 0;}
  to {bottom: 30px; opacity: 1;}
}

@-webkit-keyframes fadeout {
  from {bottom: 30px; opacity: 1;} 
  to {bottom: 0; opacity: 0;}
}

@keyframes fadeout {
  from {bottom: 30px; opacity: 1;}
  to {bottom: 0; opacity: 0;}
}

.container-fluid {
	background-color: #529dba;
	height: 50px;
	/* margin: 0 -20px; */
	padding: 10px;
	/* border-radius: 5px 0; */
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
</style>

</head>
<body>


 <!-- START MAIN -->
    <div id="">
        <!-- START WRAPPER -->
        <div class="wrapper">
 <section id="content">
                <!--start container-->
                <div class="container">
                    <div class="section">
                        <div class="row">
                            <div class="col s12 m12 l12">
                                <div class="row card-panel">
                                    <div class="container-fluid pageHeader">
                                        <p class="PageHeading">Edit Consignment</p>
                                        <!-- <button type="button" class="waves-effect waves-light modal-trigger boton right"
                      data-target="modal1">Register Consignment</button> -->
                                    </div>
                                    <form action="${context}/Consignment/updateRegisterConsignment/20192210/default" enctype="multipart/form-data" method="POST" id="editRegisterConsignment">

                                    <div class="row myRow">
                                        <div class="input-field col s12 m6">
                                            <input type="text" name="supplierId" id="supplierId" value="${consignmentdetails.supplierId}" maxlength="15" />
                                            <label for="supplierId" class="center-align">Supplier/Manufacturer ID</label>
                                        </div>

                                        <div class="input-field col s12 m6">
                                            <input type="text" name="supplierName" id="supplierName" value="${consignmentdetails.supplierName}" maxlength="15" required />
                                            <label for="supplierName" class="center-align">Supplier/Manufacturer Name <span class="star">*</span></label>
                                        </div>
                                        <div class="input-field col s12 m6">
                                            <input type="text" name="consignmentNumber" value="${consignmentdetails.consignmentNumber}" id="consignmentNumber" maxlength="15" />
                                            <label for="consignmentNumber" class="center-align">Consignment Number</label>
                                        </div>

                                        <div class="input-field col s12 m6">
                                            <!-- <p style="margin-top: -5px; margin-bottom: -13px; font-size: 12px;">Expected
                                                Arrival Date <span class="star">*</span></p> -->
                                            <!-- <label for="Name" class="center-align">Expected Dispatch Date</label> -->
                                            <input name="expectedDispatcheDate" id="expectedDispatcheDate" type="text"  value="${consignmentdetails.expectedDispatcheDate}" onfocus="(this.type='date')" onfocusout="(this.type='text')">
                                            <label for="expectedDispatcheDate" class="center-align">Expected Dispatch Date <span class="star">*</span></label>
                                            <span class="input-group-addon" style="color:#ff4081"><i
                                                    class="fa fa-calendar" aria-hidden="true"></i></span>
                                        </div>
                                        <div class="input-field col s12 m6">
                                                <!-- <p style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">Device Origination Country <span class="star">*</span></p> -->
                                            <select id="country"  name="organisationcountry" class="browser-default" class="mySelect"
                                                required></select>
                                            <label for="country" class="center-align"></label>
                                        </div>


                                        <div class="input-field col s12 m6">
                                            <!-- <p class="input-text-date">Expected Dispatch Date <span class="star">*</span></p> -->
                                            <!-- <label for="Name">Expected arrival Date</label> -->
                                            <input name="expectedArrivalDate" id="expectedArrivaldate" type="text" value="${consignmentdetails.expectedArrivaldate}" onfocus="(this.type='date')" onfocusout="(this.type='text')">
                                            <label for="expectedArrivaldate" class="center-align">Expected Arrival  Date <span class="star">*</span></label>
                                            <span class="input-group-addon" style="color:#ff4081"><i
                                                    class="fa fa-calendar" aria-hidden="true"></i></span>
                                        </div>
                                        <div class="input-field col s12 m6">
                                            <!-- <label for="Name" class="center-align">Expected arrival port</label> -->
                                            <!-- <p style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">Expected arrival port <span class="star">*</span></p> -->
                                            <select name="expectedArrivalPort" id="expectedArrivalPort" class="browser-default" required>
                                                <option value="${consignmentdetails.expectedArrivalPort}" disabled selected>${consignmentdetails.expectedArrivalPort} *</option>
                                                <option value="Air">Air</option>
                                                <option value="Land">Land</option>
                                                <option value="Water">Water</option>
                                            </select>
                                        </div>

                                        <div class="input-field col s12 m6">
                                            <input type="text" name="quantity" id="quantity" value="${consignmentdetails.quantity}" maxlength="7" required />
                                            <label for="quantity" class="center-align">Quantity <span class="star">*</span></label>
                                        </div>

                                        <div class="input-field col s12 m6">
                                                <input type="text" name="txnId" id="txnId"  value="${consignmentdetails.txnId}" readonly maxlength="15" />
                                                <label for="txnId" class="center-align">Transaction ID</label>
                                            </div>
                                    </div>


                                    <div class="row myRow">
                                        <div class="file-field input-field col s12 m6" style="margin-top: 5px;">
                                                <h6 class="file-upload-heading" style="margin-left: 0;">Upload Bulk Devices
                                                        Information <span class="star">*</span></h6>
                                            <div class="btn">
                                                <span>Select File</span>
                                                <input type="file" name="file" id="file" accept=".csv">
                                            </div>
                                            <div class="file-path-wrapper">
                                                <input class="file-path validate responsive-file-div" id="filename" value="${consignmentdetails.fileName}" type="text">
                                            </div>
                                        </div>
                                    </div>
                                    <p><a href="#">Download Sample Format</a></p>

                                    <span> Required Field are marked with <span class="star">*</span> </span>


                                    <div class="row">
                                        <div class="input-field col s12 center">
                                            <button class="waves-effect waves-light modal-trigger btn"
                                                 type="button" onclick="editRegisterConsignment()">Update</button>
                                            <a href="${context}/Consignment/viewConsignment" class="btn" type="cancel"
                                                style="margin-left: 10px;">Cancel</a>
									 </div>
                                    </div>
                                    
                                   
                                </form>
                                </div>
                                
                                </div>
                            </div>
</div>
                        </div>

                   
      </section>          </div></div>
      
    <!--end container-->

    <!-- END CONTENT -->

<!-- Modal 1 start   -->

    <div id="updateConsignment" class="modal">
        <div class="modal-content">
            <h6>Update Consignment</h6> <hr>

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


	<!--materialize js-->
	<script type="text/javascript"
		src="${context}/resources/js/materialize.js"></script>
	<!--prism
    <script type="text/javascript" src="${context}/resources/resources/js/prism/prism.js"></script>-->
	<!--scrollbar-->
	<script type="text/javascript"
		src="${context}/resources/js/plugins/perfect-scrollbar/perfect-scrollbar.min.js"></script>
	<!-- chartist -->
	<%-- <script type="text/javascript" src="${context}/resources/js/plugins/chartist-js/chartist.min.js"></script> --%>

	<!--plugins.js - Some Specific JS codes for Plugin Settings-->
	<script type="text/javascript"
		src="${context}/resources/js/plugins.js"></script>
	<!--custom-script.js - Add your own theme custom JS-->

	<script type="text/javascript"
		src="${context}/resources/js/countries.js"></script>
	<script type="text/javascript"
		src="${context}/resources/js/plugins/data-tables/js/jquery.dataTables.js"></script>
		 <script type="text/javascript" src="${context}/resources/js/Validator.js"></script>
	<script type="text/javascript"
		src="${context}/resources/js/plugins/data-tables/js/jquery.dataTables.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


	<script type="text/javascript">
		$('#distributorLibraryTable').DataTable({
			"bLengthChange" : false
		});
	</script>


    <script type="text/javascript">
         function editRegisterConsignment(){
        	/*  $("#editRegisterConsignment").submit(); */
        	 
        	 var supplierId=$('#supplierId').val();
        	 var supplierName=$('#supplierName').val();
        	 var consignmentNumber=$('#consignmentNumber').val();
        	 var expectedArrivalDate=$('#expectedArrivaldate').val();
        	 var expectedDispatcheDate=$('#expectedDispatcheDate').val();
        	 var expectedArrivalPort=$('#expectedArrivalPort').val();
        	 var organisationcountry=$('#country').val();
        	 var filename=$('#filename').val();
        	 var txnId=$('#txnId').val();
        	 var quantity=$('#quantity').val();
        	  console.log(supplierName,consignmentNumber,expectedArrivalDate,txnId,filename)
        	 
        	 
        	 var formData= new FormData();
        		formData.append('file', $('#file')[0].files[0]);
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
        	
        	
        	/*  var dispatcDate=  $('#expectedDispatcheDate').val();
        	 var arrivalDate=  $('#expectedArrivalDate').val();
     		
        	 var csvFile=$('#consignmentFile').val();
        	 var csvtext=csvFile.split('.').pop();
        	 console.log("file formate"+csvtext);
        	 var ss=$('#supplierId').val();
        	 if(!isNumericValue(ss))
        		 {
        		 
        		 myFunction("supplierId must be numeric value");
        		 return false;
        		 }
        	 else if($('#SupplierName').val()==""){
        		 myFunction("supplier Name Can not be blank");
        		 return false;
        	 }
        	 else if(!isAlphabateic($('#SupplierName').val()))
        		 {
        		 myFunction("supplier Name  must be alphabetic value");
        		 return false;
        		 }
        	 else if(!isAlphanumericValue($('#consignmentNumber').val()))
    		 {
        		 myFunction("consignmentNumber must be alphanumeric value");
    		 return false;
    		 }
        	 else if($('#expectedDispatcheDate').val()=="")
    		 {
        		 myFunction("please select expected dispatche Date ");
    		 return false;
    		 }
        	 
        	 else if($('#country option:selected').val()=="")
    		 {
        		 myFunction("please select origination Country ");
    		 return false;
    		 }
        	 
        	 else if($('#expectedArrivalDate').val()=="")
    		 {
        		 myFunction("please select expected arrival Date ");
    		 return false;
    		 }
        	 
        	 else if($('#expectedArrivalPort option:selected').val()=="")
    		 {
        		 myFunction("please select expected arrival port ");
    		
    		 return false;
    		 }
        	 
        	 else if($('#quantity').val()=="")
    		 {
        		 myFunction("please enter quantity");
    		 return false;
    		 }
        	 else if(!isNumericValue($('#quantity').val()))
    		 {
        		 myFunction("please enter quantity in numbers");
    		 return false;
    		 } */
        	 
        	/*  else if($('#consignmentFile').val()=="")
    		 {
        		 myFunction("please select file to be uploaded ");
    		 return false;
    		 } */
    		 
    		/*  else  if(csvFile!=""){
        	  if (csvtext!='csv')
        	 {
        		 myFunction("please select csv file formate ");
					return false;
        	 }
    		 }
    		 else if(Date.parse(dispatcDate)==Date.parse(arrivalDate))
    		 {
    		 myFunction("can not be equal ");
				return false;
    		 }
    	 else if(Date.parse(dispatcDate)>Date.parse(arrivalDate))
		 {
		 myFunction("dispatche date should be smaler then arrival date ");
			return false;
		 } */
    	
        	
        	
         }
         </script>
  		  <script type="text/javascript">
          function myFunction(message) {
        	  var x = document.getElementById("snackbar");
        	  x.className = "show";
        	  $('#errorMessage').html(message);
        	  setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
        	}
          </script>
          <script type="text/javascript">
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

          </script>
	<script type="text/javascript">
		function openDeleteModal(transactionId) {
			/*   $('#deletemodal').modal('open');
			  backdrop: 'static' */
			$('#deletemodal').openModal();
			console.log("transactionId value=" + transactionId);
			$('#deleteTransactionId').val(transactionId);
		}
		
		
		function closeEditpage()
		{
			console.log("edit page");
			var location='${context}/closeEditPage';
			console.log(location);
			
		}
	</script>
	<script>
   			 populateCountries
   			 (   
     			 "country"
   			 );
  </script>

	<script type="text/javascript">
          /*  $(document ).ready(function() {
        	   setTimeout(function(){ alert("Hello"); }, 3000);
           }); */
            
        /*     $(document).ready(function(){
            	
        	   $('#registerConsignmentModal').modal('show');
        	   backdrop: 'static   
            });  */
          </script>
</body>
</html>