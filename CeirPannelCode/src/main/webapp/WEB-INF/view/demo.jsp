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

   <script type="text/javascript" src="${context}/resourcesCss/js/plugins/jquery-1.11.2.min.js"></script> 
  <!--   <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css"></script>  
 -->

  <!-- CORE CSS-->
  <link href="${context}/resourcesCss/css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection">
  <link href="${context}/resourcesCss/css/style.css" type="text/css" rel="stylesheet" media="screen,projection">
   <script type="text/javascript" src="${context}/resourcesCss/js/plugins/data-tables/js/jquery.dataTables.min.js"></script>
    <link href="${context}/resourcesCss/js/plugins/data-tables/css/jquery.dataTables.min.css" type="text/css" rel="stylesheet" media="screen,projection">
    <link href="${context}/resourcesCss/js/plugins/data-tables/css/jquery.dataTables.css" type="text/css" rel="stylesheet" media="screen,projection">
  <link href="${context}/resourcesCss/css/jquery-datepicker2.css" type="text/css" rel="stylesheet" media="screen,projection">
  <!-- Custome CSS-->    
  <link href="${context}/resourcesCss/css/custom/custom.css" type="text/css" rel="stylesheet" media="screen,projection">
   <link href="${context}/resourcesCss/font/font-awesome/css/font-awesome.min.css" type="text/css" rel="stylesheet" media="screen,projection">

  <!-- INCLUDED PLUGIN CSS ON THIS PAGE -->
  <link href="${context}/resourcesCss/js/plugins/prism/prism.css" type="text/css" rel="stylesheet" media="screen,projection">
  <link href="${context}/resourcesCss/js/plugins/perfect-scrollbar/perfect-scrollbar.css" type="text/css" rel="stylesheet" media="screen,projection">
 <%--  <link href="${context}/resourcesCss/js/plugins/chartist-js/chartist.min.css" type="text/css" rel="stylesheet" media="screen,projection"> --%>

 
    
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
margin:0 -20px;
padding:10px ;
border-radius:5px 0; 
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
<div class="section">

<div class="row">
		<div class="col s12 m12 l12">
				       		
				<div class="row card-panel">
				
				<div class="container-fluid" style="margin-top: -44px !important;">
				<p class="PageHeading">Consignment</p>
                <a href="${context}/openReisterConsignmentPage" class="boton right">Register Consignment</a>
				<!-- <button type="button" class="boton  waves-effect waves-light  modal-trigger" data-toggle="modal"  data-backdrop="static" data-keyboard="false"  data-target='registerConsignmentModal' >Register Consignment</button> -->
				</div>
				
				
				
				
				<!-- Modal 1 start -->

<%-- <div id="registerConsignmentModal" class="modal">
<button type="button" class=" modal-action modal-close waves-effect waves-green btn-flat right"
data-dismiss="modal">&times;</button>
<div class="modal-content">

<h6  style="text-align: -webkit-center; padding:5px;font-size: 1.2rem;">Register consignment </h6>
<hr>

<!-- <h4 class="header2 pb-2">User Info</h4> -->
<form class="login-form" action="${context}/addConsignment"   enctype="multipart/form-data" method="POST" id="registerConsignment">
<div class="row">
<div class="input-field col s12 m6">
<input type="text" name="supplierId" id="supplierId" maxlength="10" />
<label for="Name">Supplier/Manufacturer ID</label>
</div>

<div class="input-field col s12 m6" style="margin-left:4%;">
<input type="text" name="supplierName" id="SupplierName" maxlength="100"/>
<label for="Name">Supplier/Manufacturer Name*</label>
</div>
</div>
<div class="row">
<div class="input-field col s12 m6">
<input type="text" name="consignmentNumber" id="consignmentNumber" maxlength="12" />
<label for="Name">Consignment Number</label>
</div>

<!-- <div class="input-field col s12 m6" style="margin-left:4%;">
<input type="text" name="name" id="name" />
<label for="Name">Expected Dispatch Date</label>
</div> -->

 <div class="input-field col s10 m6" style="margin-left: 4%;
margin-top: 17px;">
          <p style="margin-top: -15px; margin-bottom: -13px; font-size: 12px;">Expected Dispatch Date*</p>
            <!-- <label for="Name">Expected arrival Date</label> -->
          <input type="date"  class="form-control" data-select="datepicker" data-date=""  data-date-format="D MMMM YYYY"  onchange="dispatchDateValidation()" name="expectedDispatcheDate" id="expectedDispatcheDate" />
          <span class="input-group-addon" style="color:#ff4081"><i class="fa fa-calendar"
            aria-hidden="true" style="float: right; margin-top: -34px;"></i></span>
        </div>
</div>

<div class="row">
 <div class="input-field col s12 m6">
          <select id="country" class="browser-default" name="organisationcountry"></select>
          <label for="country" class="center-align"></label>
        </div>

<!-- <div class="input-field col s12 m6" style="margin-left:4%;">
<input type="text" name="name" id="name" />
<label for="Name">Expected arrival Date</label>
</div> -->

 <div class="input-field col s12 m6" style="margin-left: 4%;
margin-top: 17px;">
          <p style="margin-top: -15px; margin-bottom: -13px; font-size: 12px;">Expected arrival Date*</p>
          <!-- <label for="Name" class="center-align">Expected Dispatch Date</label> -->
          <input type="date" class="form-control"  onchange="arrivalDateValidation()" name="expectedArrivalDate" id="expectedArrivalDate" />
          <span class="input-group-addon" style="color:#ff4081"><i class="fa fa-calendar"
            aria-hidden="true" style="float: right; margin-top: -34px;"></i></span>
        </div>
</div>

<div class="row">
        <div class="input-field col s12 m6">
          <!-- <label for="Name" class="center-align">Expected arrival port</label> -->
          <select class="browser-default" name="expectedArrivalPort" id="expectedArrivalPort">
            <option value="" disabled selected>Expected arrival port*</option>
            <option value="Air">Air</option>
            <option value="Land">Land</option>
            <option value="Water">Water</option>
          </select>
        </div>
        
        
									<div class="input-field col s12 m6" style="margin-left: 4%;">
										<input type="text" name="quantity" value="" id="quantity" maxlength="7" /> <label
											for="Name">Quantity*</label>
									</div>

</div>


<p style="color: #000;">Upload Bulk Devices Information*</p>
<div class="row" style=" margin-bottom: 0;">

<div class="file-field col s12 m6">
<div class="btn">
<span>Select File</span>
<input type="file" name="file"  id="consignmentFile"  multiple>
</div>
<div class="file-path-wrapper">
<input class="file-path validate" type="text" placeholder="Upload one or more files">
</div>
</div>

</div>

<a href="${context}/sampleFileDownload/stoke" style="margin-left: 15PX;">Download Sample Format</a>
<p style="padding:0px;margin:0;">Required fields are marked with *</p>


<div class="row">
<div class="input-field col s12">
<button
class="waves-effect waves-light btn gradient-45deg-light-blue-cyan box-shadow-none border-round mr-1"
type="button" name="add_user" id="registerConsignmentButton" onclick="registerConsignment()" style="margin-left: 35%;">Submit</button>
<button type="reset"
class="modal-close waves-effect waves-light btn gradient-45deg-light-blue-cyan box-shadow-none border-round mr-1" name="canceluser" id="canceluser">Cancel</button>


</div>
</div>
</form>
</div>
</div> --%>



<!-- Modal End -->


      <div class="col s12 m12 l12" id="consignmentTableDIv"
                    style="padding-bottom: 5px;background-color: #e2edef52;">

					<form action="${context}/FillterConsignmentData" method="POST" id="submitFilterConsignment">                   
                   
                    <c:choose >
                       <c:when test=" ${empty startDate || startDate=='' || startDate==null}">
                    
                   
                    </c:when>
                    <c:otherwise>
                    
                      <div class="col s12 m2 l2" style=" width:17%;   padding-right: 10px">
                      <br />
                      
                      <div id="startdatepicker" class="input-group date" data-date-format="yyyy-mm-dd"
                        style="    margin-top: 10px;">
                      
                      <label for="">Start date </label>
                        <input class="form-control" type="date" id="filterStartDate" value="${startDate}" name="startDate"  style="margin-top: -9px" />
                        <span class="input-group-addon" style="color:#ff4081"><i class="fa fa-calendar"
                            aria-hidden="true" style="float: right; margin-top: -35px;"></i></span>
                      </div>

                    </div>
                    <div class="col s12 m2 l2" style=" width:17%;    padding-right: 10px">
                      <br/>
                      <label for="">End date</label>
                      <div id="enddatepicker" class="input-group date" data-date-format="yyyy-mm-dd" style="margin-top: 10px;">

                        <input class="form-control" onchange="filterData()" id="endDateFilter" value="${endDate}" name="endDate" type="date" style="margin-top: -9px" />
                        <span class="input-group-addon" style="color:#ff4081"><i class="fa fa-calendar"
                            aria-hidden="true" style="float: right; margin-top: -35px;"></i></span>
                      </div>
                    </div>
					 <div class="col s12 m2 l2" style=" width:13%;  margin-top: -7px; padding-right: 10px;">
                      <br />
                      <label for="TotalPrice" class="center-align">File Status</label>
                      <select id="filterFileStatus" name="fileStatus" >
                        <option value="${fileStatus}"  selected>${fileStatus}</option>
                         <option value="">All</option>
                        <option value="Success">Success</option>
                        <option value="Error">Error</option>
                        <option value="Processing">Processing</option>

                      </select>

                    </div>
                    <div class="col s12 m2 l2" style=" width:13%;margin-top: -7px;padding-right: 10px">
                      <br />
                      <label for="TotalPrice" class="center-align">Tax Paid Status</label>
                      <select id="taxPaidStatus" name="taxStatus">
                        <option value="${taxStatus}"  selected>${taxStatus}</option>
                        <option value="">All</option>
                        <option value="Paid">Paid</option>
                        <option value="NotPaid">Not Paid</option>
                      </select>

                    </div>
                     <div class="col s12 m2 l2" style=" width:20%;margin-top: -7px;padding-right: 10px">
                      <br />
                      <label for="TotalPrice" class="center-align">consignment Status</label>
                      <select id="consignmentStatus" name="consignmentStatus">
                        <option value="${consignmentStatus}"  selected>${consignmentStatus}</option>
                        <option value="">All</option>
                        <option value="Uploading">Uploading</option>
                        <option value="Processing">Processing</option>
                        <option value="Rejected by System">Rejected by System</option>
                        <option value="Pending approval from CEIR Authority">Pending approval from CEIR Authority</option>
                        <option value="Rejected by CEIR Authority">Rejected by CEIR Authority</option>
                        <option value="Pending approval from Customs">Pending approval from Customs</option>
                        <option value="Approved">Approved</option>
                        <option value="Rejected by customs">Rejected by customs</option>
                        <option value="Withdrawn by importer">Withdrawn by importer</option>
                        <option value="Withdrawn by CEIR Admin">Withdrawn by CEIR Admin</option>
                        
                      </select>

                    </div>
                   </c:otherwise>
                    </c:choose>

                    <div class="col s12 m2 l2" style=" width:20%; padding-right: 10px">
                      <button type="button" onclick="submitFilterData()" style="margin-top: 50px;margin-left: 15px; height: 32px;width: 72;"
                        class="btn primary" id="submitFilter" >Filter</button>
                    </div>
					</form>
                  </div>		
                  
                  <div id="snackbar"><p id="errorMessage"></p></div>		
					<div class="col s12 m12 l12" id="distributorTableDIv">
						<table class="responsive-table striped datatable" id="distributorLibraryTable">
							<thead>
								<tr>									
									
									<th>ModifiedOn</th>
									<th>Transaction Number</th>
								    <th>Tax Status</th>
								    <th>Consignment Status</th>
									<th>File Status</th>
									<th>Action</th>
								</tr>
							</thead>
								
							<tbody id="distributorTableLibraryTbody">
							<c:forEach items="${consignmentdetails}" var="consignmentdetails" >
							<tr>
							<td>${consignmentdetails.modifiedOn}</td>
							<td>${consignmentdetails.txnId}</td>
							<td>${consignmentdetails.taxPaidStatus}</td>
							<td>${consignmentdetails.consignmentStatus}</td>
							<td>${consignmentdetails.fileStatus}</td>
							<td class="center">
												<div>
													<!-- <a class="btn btn-xs btn-teal tooltips" data-placement="top"
											data-original-title="Edit"
											href=""><i
											data-target="#responsive" class="fa fa-exclamation-circle"></i></a> -->
							 
							 <a ><i class="fa fa-exclamation-circle" aria-hidden="true" title="ErrorFile"
                              style=" cursor:not-allowed; color:grey;pointer-events:auto; font-size:20px; margin-right:15px;"></i></a>
                              
                                <a href="${context}/openViewConsignmentRecord/${consignmentdetails.txnId}"><i class="fa fa-eye teal-text" aria-hidden="true" title="view"
                              style="font-size: 20px; margin:15px;"></i></a>
                              
                              <a href="${context}/dowloadFiles/Actual/${consignmentdetails.fileName}/${consignmentdetails.txnId}"><i class="fa fa-download " aria-hidden="true"
								style="font-size: 20px; color:#2e8b57" title="download"></i></a>
								
								<a href="${context}/openEditPage/${consignmentdetails.txnId}"><i class="fa fa-pencil" aria-hidden="true"
								style="font-size: 20px; margin:0 15px 0 15px; color: #006994" title="edit"></i></a>

								<a  class="waves-effect waves-light modal-trigger"  onclick="openDeleteModal('${consignmentdetails.txnId}')">
								<i class="fa fa-trash" aria-hidden="true" style="font-size: 20px; color: red;" title="delete"></i>
								</a>
								
								<!-- <a href="#DeleteConsignment" class="waves-effect waves-light modal-trigger"><i class="fa fa-trash" aria-hidden="true" style="font-size: 20px; color: red;"   title="delete"></i></a>
								 -->
								 			<%--  <a
														class="btn btn-xs btn-bricky tooltips"
														data-placement="top" data-original-title="Remove"
														onclick="document.getElementById('featureid').value ='${feature.id}'"
														class="btn btn-info btn-lg" data-toggle="modal"
														data-target="#deleteu"><i
														class="fa fa-times fa fa-white"></i></a> --%>
												</div>
											</td>
							
							</tr>
						</c:forEach>
								</tbody>
								
						</table>
					</div>
					
					
				</div>	
		</div>
	</div>
	<div id="deletemodal" class="modal">
	
<button type="button" class=" modal-action modal-close waves-effect waves-green btn-flat right"
data-dismiss="modal">&times;</button>
<div class="modal-content">
<h6>Are you sure want  to delete this Consignment?</h6>
<hr>

<!-- <h4 class="header2 pb-2">User Info</h4> -->
<form  action="${context}/deleteConsignment" method="POST"  enctype="multipart/form-data" >

<input type="text" name="txnId" value="" id="deleteTransactionId" style="display:none;">
<div class="row">
<div class="input-field col s12">

<button class="waves-effect waves-light btn gradient-45deg-light-blue-cyan box-shadow-none border-round mr-1"
    type="submit" name="add_user" id="add_user" style="margin-left: 35%;">YES</button>
<button  type="reset" class="modal-close waves-effect waves-light btn gradient-45deg-light-blue-cyan box-shadow-none border-round mr-1" name="deleteuser" id="deleteuser">NO</button>


</div>
</div>
</form>
</div>
</div>
	</div>
	
	  
    <!--materialize js-->
   <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    <script type="text/javascript" src="${context}/resourcesCss/js/materialize.js"></script>
     
    
     <script type="text/javascript" src="${context}/resourcesCss/js/plugins/data-tables/js/jquery.dataTables.js"></script>
      <script type="text/javascript" src="${context}/resourcesCss/js/plugins/data-tables/js/jquery.dataTables.min.js"></script>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.3/moment.min.js"></script>
      <script type="text/javascript" src="${context}/resourcesCss/js/jquery-datepicker2.js"></script>
      

    <!--plugins.js - Some Specific JS codes for Plugin Settings-->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  
     <%--   <script type="text/javascript" src="${context}/resourcesCss/js/materialize-plugins/date_picker/picker.date.js"></script>
    <script type="text/javascript" src="${context}/resourcesCss/js/materialize-plugins/date_picker/picker.js"></script> --%>
    <!--custom-script.js - Add your own theme custom JS-->
 <script type="text/javascript" src="${context}/resourcesCss/js/plugins.js"></script>
    <script type="text/javascript" src="${context}/resourcesCss/js/Validator.js"></script>
   <!--prism
    <script type="text/javascript" src="${context}/resourcesCss/resourcesCss/js/prism/prism.js"></script>-->
    <!--scrollbar-->
    <script type="text/javascript" src="${context}/resourcesCss/js/plugins/perfect-scrollbar/perfect-scrollbar.min.js"></script>
    <!-- chartist -->
    <%-- <script type="text/javascript" src="${context}/resourcesCss/js/plugins/chartist-js/chartist.min.js"></script> --%>
       <script type="text/javascript" src="${context}/resourcesCss/js/countries.js"></script>
     
       
	    <script type="text/javascript">
	    	
            $('#distributorLibraryTable').DataTable(
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
         
        <!--    <script>
   			 populateCountries
   			 (   
     			 "country"
   			 );
  </script> -->
  
  
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
</body>
</html>