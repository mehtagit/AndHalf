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
.container-fluid{
background-color: #529dba;
height:50px;
/* margin:0 -20px; */
padding:10px ;
/* border-radius:5px 0;  */
}

/* #deletemodal.modal-backdrop {
  z-index: 0;
}  */

.boton{
color: #2979a5;
float: right;
border: solid 1px rgba(33, 167, 201, 0.774);
padding: 4px 10px;
border-radius: 7px;
font-size: 14px;
background-color: #fff;
}
.boton:hover{
color: #fff;
float: right;
border: solid 1px #ff5a92;
padding: 4px 10px;
border-radius: 7px;
font-size: 14px;
background-color: #ff5a92;
}

.row{
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


      <!-- START CONTENT -->
      <section id="content">
        <!--start container-->
        <div class="container">
          <div class="section">
            <div class="row">
              <div class="col s12 m12 l12">
                <div class="row card-panel">
                  <div class="container-fluid pageHeader">
                    <p class="PageHeading">Consignment</p>
                    <a href="${context}/Consignment/openRegisterConsignmentForm/formPage" class="boton right">Register Consignment</a>
                  </div>
					<form action="${context}/Consignment/viewConsignment" method="post">
                  <div class="col s12 m12 l12" id="consignmentTableDIv"
                    style="padding-bottom: 5px;background-color: #e2edef52;">

                    <div class="col s6 m2 l2 responsiveDiv">

                      <div id="startdatepicker" class="input-group date" data-date-format="yyyy-mm-dd">
                        <label for="TotalPrice">Start date</label>
                        <input type="date">
                        <!-- <input class="form-control" type="date" id="datepicker" /> -->
                        <span class="input-group-addon" style="color:#ff4081"><i class="fa fa-calendar"
                            aria-hidden="true" style="float: right; margin-top: -37px;"></i></span>
                      </div>
                    </div>
                    <div class="col s6 m2 l2 responsiveDiv">

                      <div id="enddatepicker" class="input-group date" data-date-format="yyyy-mm-dd">
                        <!-- <input type='text' class='datepicker-here' data-language='en' /> -->
                        <label for="TotalPrice">End date</label>
                        <!-- <input type="text" class="form-control" name="date" id="date" data-select="datepicker"> -->
                        <input class="form-control" type="date" id="datepicker" />
                        <span class="input-group-addon" style="color:#ff4081"><i class="fa fa-calendar"
                            aria-hidden="true" style="float: right; margin-top: -37px;"></i></span>
                      </div>
                    </div>

                    <div class="col s6 m2 l2 selectDropdwn">
                        <br />
                        <!-- <label for="TotalPrice" class="center-align">File Status</label> -->
                        <select id="filterFileStatus" class="select2 form-control boxBorder boxHeight">
                          <option value="" disabled selected>Consignment Status</option>
                          <option value="Success">Uploading</option>
                          <option value="Processing">Processing</option>
                          <option value="Error">Rejected by system</option>
                          <option value="Error">Pending approval from CEIR</option>
                          <option value="Error">Rejected by CEIR authority</option>
                          <option value="Error">Pending approval from Custom (CEIR authority has approved)</option>
                          <option value="Error">Approved by custom</option>
                          <option value="Error">Rejected by custom</option>
                          <option value="Error">Withdrawn by Importer</option>
                          <option value="Error">Withdrawn by CEIR</option>
                        </select>
  
                      </div>

                    <div class="col s6 m2 l2 selectDropdwn">
                      <br />
                      <!-- <label for="TotalPrice" class="center-align">File Status</label> -->
                      <select id="filterFileStatus" class="select2 form-control boxBorder boxHeight">
                        <option value="" disabled selected>File Status</option>
                        <option value="Success">Success</option>
                        <option value="Error">Error</option>
                        <option value="Processing">Processing</option>

                      </select>

                    </div>
                    <div class="col s6 m2 l2 selectDropdwn">
                      <br />
                      <!-- <label for="TotalPrice" class="center-align">Tax Paid Status</label> -->
                      <select id="taxPaidStatus" class="select2 form-control boxBorder boxHeight">
                        <option value="" disabled selected>Tax Paid Status</option>
                        <option value="Paid">Paid</option>
                        <option value="NotPaid">Not Paid</option>
                      </select>

                    </div>

                    <div class="col s12 m2 l2">
                      <button type="submit" class="btn primary botton" id="submitFilter">Filter</button>
                    </div>

                  </div>
                  </form>
                  <table class="responsive-table striped display" id="consignmentLibraryTable" cellspacing="0">
                    <thead>
                      <tr>
                        <th>Creation Date</th>
                        <th>Transaction ID</th>
                        <th>Supplier Name</th>
                        <th>Consignment Status</th>
                        <th>Tax Paid Status</th>
                        <th>Action</th>
                      </tr>
                    </thead>
                    <tbody id="consignmentTableLibraryTbody">
                      <c:forEach items="${consignmentdetails}" var="consignmentdetails">
                       	<tr>
                        <td>${consignmentdetails.createdOn}</td>
                        <td>${consignmentdetails.txnId}</td>
                        <td>${consignmentdetails.supplierName}</td>
                         
                        <c:choose>
                        <c:when test="${consignmentdetails.consignmentStatus==0}">
                        <td>uploading</td>
                        </c:when>
                         <c:when test="${consignmentdetails.consignmentStatus==1}">
                        <td>processing</td>
                        </c:when>
                        
                        <c:otherwise>
                     
                        
                        </c:otherwise>
                        </c:choose>
                           <td>${consignmentdetails.taxPaidStatus}</td>
                        <td style="width:180px !important;">
                          <a href=""><i class="fa fa-exclamation-circle" aria-hidden="true" title="ErrorFile"
                              style="pointer-events:auto;color: red; font-size:20px; margin-right:15px;"></i></a>
                          <a href="#" download="download"><i class="fa fa-download " aria-hidden="true"
                              style="font-size: 20px; color:#2e8b57" title="download" download="download"></i></a>
                          
                          <a class="waves-effect waves-light modal-trigger" onclick = "viewConsignmentDetails()"><i 
                          class="fa fa-eye teal-text" aria-hidden="true" title="view"
                              style="font-size: 20px; margin:0 0 0 15px;"></i></a>
                         
                         
                          <a class="waves-effect waves-light modal-trigger" onclick = "EditConsignmentDetails()"><i 
                          class="fa fa-pencil" aria-hidden="true" 
                              style="font-size: 20px; margin:0 15px 0 15px; color: #006994" title="edit"></i></a>
                         
                          <a href="#DeleteConsignment" class="waves-effect waves-light modal-trigger"><i
                              class="fa fa-trash" aria-hidden="true" style="font-size: 20px; color: red;"
                              title="delete"></i></a>
                        </td>
                      </tr>
                      </c:forEach>
                      

                                        </tbody>
                  </table>
                </div>

              </div>
            </div>
          </div>
        </div>
        <!--end container-->
      </section>
 
 
<!-- Edit confirmation Modal start   -->



 
 
 <!--viewModal Modal start   -->

  <div id="viewModal" class="modal-form" style="overflow-y: hidden;">
    <div class="modal-content">

      <h6>View Consignment</h6>
      <hr>
	                        <form action="">

                                    <div class="row myRow"	>
                                        <div class="input-field col s12 m6">
                                            <input type="text" name="name" id="name" disabled />
                                            <label for="Name" class="center-align">Supplier/Manufacturer ID</label>
                                        </div>

                                        <div class="input-field col s12 m6">
                                            <input type="text" name="name" id="name" disabled />
                                            <label for="Name" class="center-align">Supplier/Manufacturer Name</label>
                                        </div>
                                        <div class="input-field col s12 m6">
                                            <input type="text" name="name" id="name" disabled />
                                            <label for="Name" class="center-align">Consignment Number</label>
                                        </div>

                                        <div class="input-field col s12 m6" style="color: #c4c4c4;">
                                            <p style="margin-top: -5px; margin-bottom: -13px; font-size: 12px;">Expected
                                                Arival Date</p>
                                            <!-- <label for="Name" class="center-align">Expected Dispatch Date</label> -->
                                            <input type="date" disabled>
                                            <span class="input-group-addon" style="color:#ff4081"><i
                                                    class="fa fa-calendar" aria-hidden="true"></i></span>
                                        </div>
                                        <div class="input-field col s12 m6">
                                            <select id="countryview" class="browser-default" class="mySelect" Placholder="Device Origination Counrty*"
                                                disabled></select>
                                            <label for="countryview" class="center-align"></label>
                                        </div>


                                        <div class="input-field col s12 m6">
                                            <p class="input-text-date" style="color: #c4c4c4;">Expected Dispatch Date
                                            </p>
                                            <!-- <label for="Name">Expected arrival Date</label> -->
                                            <input type="date" disabled>
                                            <span class="input-group-addon" style="color:#ff4081"><i
                                                    class="fa fa-calendar" aria-hidden="true"></i></span>
                                        </div>
                                        <div class="input-field col s12 m6">
                                            <!-- <label for="Name" class="center-align">Expected arrival port</label> -->
                                            <select class="browser-default" disabled>
                                                <option value="" disabled selected>Expected Arrival Port</option>
                                                <option value="Air">Air</option>
                                                <option value="Land">Land</option>
                                                <option value="Water">Water</option>
                                            </select>
                                        </div>

                                        <div class="input-field col s12 m6">
                                            <input type="text" name="Quantity" id="Quantity" disabled />
                                            <label for="Quantity" class="center-align">Quantity</label>
                                        </div>

                                        <div class="input-field col s12 m6">
                                                <input type="text" name="TransactionId" id="TransactionId" disabled maxlength="15" />
                                                <label for="TransactionId" class="center-align">Transaction ID</label>
                                            </div>
                                    </div>

                                    <div class="row" style="padding: 20px 0 100px 0;">
                                        <div class="input-field col s12 center">
                                            <a href="${context}/Consignment/viewConsignment" class="btn" type="cancel" name="add_user"
                                                id="add_user">Cancel</a>
                                        </div>
                                    </div>

                                </form>	
			</div>
  </div>
  <!-- Modal End -->

 
  <!--Delete Modal start   -->

  <div id="DeleteConsignment" class="modal">
    <div class="modal-content">

      <h6>Delete Consignment</h6>
      <hr>

      <div class="row">
        <h6>Are you sure you want to withdraw the consignment details for (Transaction ID)</h6>
      </div>

      <div class="row">
          <div class="input-field col s12 m12">
              <textarea id="textarea1" class="materialize-textarea"></textarea>
              <label for="textarea1">Remarks</label>
            </div>
      </div>
      <div class="row">
        <div class="input-field col s12 center">
          <div class="input-field col s12 center">
            <a href="${context}/Consignment/deleteConsignment/20191023" class="btn">ok</a>
            <button class="modal-close btn" style="margin-left: 10px;">No</button>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- Modal End -->


 



 <!-- Delete confirmation Modal start   -->

  <div id="confirmDeleteConsignment" class="modal">
      <div class="modal-content">
  
        <h6>Delete Consignment</h6>
        <hr>
        <!-- <h4 class="header2 pb-2">User Info</h4> -->
  
        <div class="row">
          <h6>The consignment has been successfully withdrawn</h6>
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
    <!--materialize js-->
   <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    <script type="text/javascript" src="${context}/resources/js/materialize.js"></script>
     
    
     <script type="text/javascript" src="${context}/resources/js/plugins/data-tables/js/jquery.dataTables.js"></script>
      <script type="text/javascript" src="${context}/resources/js/plugins/data-tables/js/jquery.dataTables.min.js"></script>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.3/moment.min.js"></script>
      <script type="text/javascript" src="${context}/resources/js/jquery-datepicker2.js"></script>
      

    <!--plugins.js - Some Specific JS codes for Plugin Settings-->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  
     <%--   <script type="text/javascript" src="${context}/resources/js/materialize-plugins/date_picker/picker.date.js"></script>
    <script type="text/javascript" src="${context}/resources/js/materialize-plugins/date_picker/picker.js"></script> --%>
    <!--custom-script.js - Add your own theme custom JS-->
 <script type="text/javascript" src="${context}/resources/js/plugins.js"></script>
    <script type="text/javascript" src="${context}/resources/js/Validator.js"></script>
   <!--prism
    <script type="text/javascript" src="${context}/resources/resources/js/prism/prism.js"></script>-->
    <!--scrollbar-->
    <script type="text/javascript" src="${context}/resources/js/plugins/perfect-scrollbar/perfect-scrollbar.min.js"></script>
    <!-- chartist -->
    <%-- <script type="text/javascript" src="${context}/resources/js/plugins/chartist-js/chartist.min.js"></script> --%>
       <script type="text/javascript" src="${context}/resources/js/countries.js"></script>
     
       
	    <script type="text/javascript">
	    	
            $('#consignmentLibraryTable').DataTable(
            		{
            			"destroy":true,
            			"bLengthChange": true,
            			"sorting":[]
            		}); 
           </script>
          
     <!--      <script>
    $(document).ready(function () {
      $('.modal').modal();
    });
  </script> -->
          
         
         <script type="text/javascript">
         
         function viewConsignmentDetails(){
        	 $("#viewModal").modal('show');
        	 
        		/* $.ajax({
				url : "./Consignment/openRegisterConsignmentForm/",
				data : JSON.stringify(),
				dataType : 'json',
				contentType : 'application/json; charset=utf-8',
				type : 'GET',
				success : function(data) {
				
				},
				error : function() {
					alert("Failed");
				}
			}); */
        	 
         }
         
         
         function EditConsignmentDetails(){
        	 
        	 $("#updateModal").modal('show');	
        	 
        	/*  $.ajax({
    				url : "./Consignment/openRegisterConsignmentForm/",
    				data : JSON.stringify(),
    				dataType : 'json',
    				contentType : 'application/json; charset=utf-8',
    				type : 'GET',
    				success : function(data) {
    				
    				},
    				error : function() {
    					alert("Failed");
    				}
    			});
        	
        	
        	
        		$("#supplierId").val(supplierId);
        		$("#supplierName").val(supplierName);
        		$("#consignmentNumber").val(consignmentNumber);
        		$("#expectedDispatcheDate").val(expectedDispatcheDate);
        		$("#country").val(organisationcountry);
        		$("#expectedArrivaldate").val(expectedArrivaldate);
        		$("#expectedArrivalPort").val(expectedArrivalPort);
        		$("#quantity").val(quantity);
        		$("#TransactionId").val(txnId);
        		$("#TotalPrice").val(totalPrice);
        		$("#file").val(file); */
        		
        		
        		
        }
         
       
         function registerConsignment(){
        	
        	 var dispatcDate=  $('#expectedDispatcheDate').val();
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
        	 
        	 else if($('#consignmentFile').val()=="")
    		 {
        		 myFunction("please select file to be uploaded ");
    		 return false;
    		 }
        	 else if (csvtext!='csv')
        	 {
        		 myFunction("please select csv file formate ");
					return false;
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
    		 }
        	
        	 $("#registerConsignment").submit();
        	
         }
         </script>
         
         
         <script>
         function filterData(){
        	 var startDate=$('#filterStartDate').val()
        	 var endDate=$('#endDateFilter').val()
        	 
        	 var result=dateValidation(startDate,endDate);
        	  if(result=="smaller")
        		 {
        		 myFunction("end date should be greater then start date")
        		 $('#endDateFilter').val("")
        		 }
         }
         
         function submitFilterData(){
        
        	 var startDate=$('#filterStartDate').val()
        	 var endDate=$('#endDateFilter').val()
        	 if($('#filterStartDate').val()=="")
        		 {
        		 myFunction("Please select start date")
        		 return false;
        		 }
        	 else if($('#endDateFilter').val()=="")
        		 {
        		 myFunction("Please select end date");
        		 return false;
        		 }
        	 else if(Date.parse(startDate)>Date.parse(endDate))
        		 
        	 {
        		 myFunction("start date should be smaller than end date");
        		 return false;
        	 }
        	 
        	 $("#submitFilterConsignment").submit();
         }
         </script>
         
          <script type="text/javascript">
          function openDeleteModal(transactionId)
          {
        	/*   $('#deletemodal').modal('open');
        	  backdrop: 'static' */
        	$('#deletemodal').openModal();
        	console.log("transactionId value="+transactionId);
        	$('#deleteTransactionId').val(transactionId);
        	 }
          </script>
          <script type="text/javascript">
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
          </script> 
         
       
  
  
  <script>
$(document).ready(function(){
$('.datepicker').datepicker();
});

$('.datepicker').on('mousedown',function(event){
event.preventDefault();
})
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
          
      
      <!-- Update Modal Start -->     
      <div id="updateModal" class="modal-form" style = "overflow-y: hidden;">
      <div class="modal-content">
  
         <h6>Edit Consignment</h6>
        <hr>
   			 <form action="" enctype="multipart/form-data" method="POST" id="editRegisterConsignment">

                                    <div class="row myRow">
                                        <div class="input-field col s12 m6">
                                            <input type="text" name="supplierId" id="name" maxlength="15" />
                                            <label for="Name" class="center-align">Supplier/Manufacturer ID</label>
                                        </div>

                                        <div class="input-field col s12 m6">
                                            <input type="text" name="supplierName" id="name" maxlength="15" required />
                                            <label for="Name" class="center-align">Supplier/Manufacturer Name <span class="star">*</span></label>
                                        </div>
                                        <div class="input-field col s12 m6">
                                            <input type="text" name="consignmentNumber" id="name" maxlength="15" />
                                            <label for="Name" class="center-align">Consignment Number</label>
                                        </div>

                                        <div class="input-field col s12 m6">
                                            <!-- <p style="margin-top: -5px; margin-bottom: -13px; font-size: 12px;">Expected
                                                Arrival Date <span class="star">*</span></p> -->
                                            <!-- <label for="Name" class="center-align">Expected Dispatch Date</label> -->
                                            <input name="expectedDispatcheDate" type="text" onfocus="(this.type='date')" onfocusout="(this.type='text')">
                                            <label for="dispatchDate" class="center-align">Expected Dispatch Date <span class="star">*</span></label>
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
                                            <input name="expectedArrivalDate" type="text" onfocus="(this.type='date')" onfocusout="(this.type='text')">
                                            <label for="dispatchDate" class="center-align">Expected Arrival  Date <span class="star">*</span></label>
                                            <span class="input-group-addon" style="color:#ff4081"><i
                                                    class="fa fa-calendar" aria-hidden="true"></i></span>
                                        </div>
                                        <div class="input-field col s12 m6">
                                            <!-- <label for="Name" class="center-align">Expected arrival port</label> -->
                                            <!-- <p style="margin-top: -15px; margin-bottom: -3px; font-size: 12px;">Expected arrival port <span class="star">*</span></p> -->
                                            <select name="expectedArrivalPort" class="browser-default" required>
                                                <option value="" disabled selected>Expected arrival port *</option>
                                                <option value="Air">Air</option>
                                                <option value="Land">Land</option>
                                                <option value="Water">Water</option>
                                            </select>
                                        </div>

                                        <div class="input-field col s12 m6">
                                            <input type="text" name="quantity" id="Quantity" maxlength="7" required />
                                            <label for="Quantity" class="center-align">Quantity <span class="star">*</span></label>
                                        </div>

                                        <div class="input-field col s12 m6">
                                                <input type="text" name="txnId" id="TransactionId"  value="" readonly maxlength="15" />
                                                <label for="TransactionId" class="center-align">Transaction ID</label>
                                            </div>
                                    </div>


                                    <div class="row myRow">
                                        <div class="file-field input-field col s12 m6" style="margin-top: 5px;">
                                                <h6 class="file-upload-heading" style="margin-left: 0;">Upload Bulk Devices
                                                        Information <span class="star">*</span></h6>
                                            <div class="btn">
                                                <span>Select File</span>
                                                <input type="file" name="file" id="csvUploadFile" accept=".csv">
                                            </div>
                                            <div class="file-path-wrapper">
                                                <input class="file-path validate responsive-file-div" type="text">
                                            </div>
                                        </div>
                                    </div>
                                    <p><a href="#">Download Sample Format</a></p>

                                    <span> Required Field are marked with <span class="star">*</span> </span>


                                    <div class="row">
                                        <div class="input-field col s12 center">
                                            <button class="waves-effect waves-light modal-trigger btn"
                                                data-target="updateConsignment" type="button" onclick="editRegisterConsignment()">Update</button>
                                            <a href="${context}/Consignment/viewConsignment" class="btn" type="cancel"
                                                style="margin-left: 10px;">Cancel</a>
									 </div>
                                    </div>
                                </form>
  			</div>
    </div>
       
         <script>
   			 populateCountries
   			 (   
     			 "country"
   			 );
  </script>    
</body>
</html>