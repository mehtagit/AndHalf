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

        /* input[type=text] {
      height: 35px; 
      margin: 0 0 5px 0;
    } */
        input[type=text]:not(.browser-default) {
            font-size: 13px;
            /* height: 30px; */
        }

        input[type=date] {
            font-size: 0.8rem;
        }

        textarea.materialize-textarea {
            padding: 0;
            padding-left: 10px;
        }

        .btn-flat {
            height: auto;
        }
        #starColor {
            color: red;
        }

        select option:first-child{
  color: red;}
  
  		section{
  			margin-top:10px;
  		}
</style>

</head>
<body>
	 <!-- START MAIN -->
    <div id="">
        <!-- START WRAPPER -->
        <div class="wrapper">
	
<!-- START CONTENT -->
            <section id="content">
                <!--start container-->
                <div class="container">
                    <div class="section">
                        <div class="row">
                            <div class="col s12 m12 l12">
                                <div class="row card-panel">
                                    <form action="${context}/Consignment/registerConsignment/default" method="POST" enctype="multipart/form-data"  id="registerConsignment">
                                    <div class="container-fluid pageHeader">
                                        <p class="PageHeading">Register Consignment</p>
                                        <!-- <button type="button" class="waves-effect waves-light modal-trigger boton right"
                      data-target="modal1">Register Consignment</button> -->
                                    </div>

                                    <div class="row myRow">
                                        <div class="input-field col s12 m6">
                                            <input type="text" name="supplierId" id="name" maxlength="15" />
                                            <label for="Name" class="center-align">Supplier/Manufacturer ID</label>
                                        </div>

                                        <div class="input-field col s12 m6">
                                            <input type="text" name="supplierName" id="name" maxlength="15" required />
                                            <label for="Name" class="center-align">Supplier/Manufacturer Name <span class="star">*</span></label>
                                        </div>
                                    </div>
                                    <div class="row myRow">
                                        <div class="input-field col s12 m6">
                                            <input type="text" name="consignmentNumber" id="name" maxlength="15" />
                                            <label for="Name" class="center-align">Consignment Number</label>
                                        </div>

                                        <div class="input-field col s12 m6">
                                            <input name="expectedDispatcheDate" type="text" onfocus="(this.type='date')" onfocusout="(this.type='text')">
                                            <label for="dispatchDate" class="center-align">Expected Dispatch Date <span class="star">*</span></label>
                                            <span class="input-group-addon" style="color:#ff4081"><i
                                                    class="fa fa-calendar" aria-hidden="true"></i></span>
                                        </div>
                                    </div>

                                    <div class="row myRow">
                                        <div class="input-field col s12 m6">
                                            <select id="country" name="organisationcountry" class="browser-default" class="mySelect"
                                                style="padding-left: 0;" required></select>
                                        </div>


                                        <div class="input-field col s12 m6">
                                            <input name="expectedArrivalDate" type="text" onfocus="(this.type='date')" onfocusout="(this.type='text')">
                                            <label for="dispatchDate" class="center-align">Expected Arival Date <span class="star">*</span></label>
                                            <span class="input-group-addon" style="color:#ff4081"><i
                                                    class="fa fa-calendar" aria-hidden="true"></i></span>
                                        </div>
                                    </div>

                                    <div class="row myRow">
                                        <div class="input-field col s12 m6">
                                            <select class="browser-default" name="expectedArrivalPort">
                                                <option value="" disabled selected>Expected Arrival Port <span id="starColor">*</span></option>
                                                <option value="Air">Air</option>
                                                <option value="Land">Land</option>
                                                <option value="Water">Water</option>
                                            </select>
                                            
                                        </div>

                                        <div class="input-field col s12 m6">
                                            <input type="text" name="quantity" id="Quantity" maxlength="7" required />
                                            <label for="Quantity" class="center-align">Quantity <span class="star">*</span></label>
                                        </div>
                                    </div>


                                    <div class="row myRow">
                                        <h6 class="file-upload-heading" style="margin-left: 15px;">Upload Bulk Devices
                                            Information<span class="star">*</span></h6>
                                        <div class="file-field input-field col s12 m6" style="margin-top: 5px;">
                                            <div class="btn">
                                                <span>Select File</span>
                                                <input type="file" name="file" id="csvUploadFile" accept=".csv">
                                            </div>
                                            <div class="file-path-wrapper">
                                                <input class="file-path validate responsive-file-div" type="text">
                                            </div>
                                        </div><br><br>
                                        <p style="margin-left: 15px;"><a href="#">Download Sample Format</a></p>
                                    </div>
                                   

                                    <div class="row">
                                        <div class="input-field col s12 center">
                                            <button class="waves-effect waves-light modal-trigger btn"
                                                data-target="submitConsignment" onclick="registerConsignment()" type="button">Submit</button>
                                            <a href="#cancelMessage" class="btn modal-trigger" type="cancel"
                                                style="margin-left: 10px;">Cancel</a>


                                        </div>
                                        
                                    </div></form>
                                </div>
                         
                            </div>
                        </div>

                    </div>
                </div>
</section>        </div>
    </div>
    
        <div id="submitConsignment" class="modal">
        <div class="modal-content">
            <h6>Submit Consignment</h6><hr>

            <div class="row">
                <h6>Your form has been successfully submitted. The Transaction ID for future reference is (Tr12345678)</h6>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                <form action="${context}/Consignment/viewConsignment" id="closeOkPop" method="POST">
                    <a  onclick="closeConfirmation()" class="btn">ok</a>
                </form>
                </div>
            </div>
        </div>
    </div>
    <div id="cancelMessage" class="modal">
            <div class="modal-content">
                <h6>Cancel Request</h6><hr>
              <div class="row">
                <h6>Are you sure you want to close this window. The form data will be lost</h6>
              </div>
              <div class="row">
                <div class="input-field col s12 center">
                  <div class="input-field col s12 center">
                    <a href="${context}/Consignment/viewConsignment" class="btn">yes</a>
                    <button class="modal-close waves-effect waves-light btn" style="margin-left: 10px;" >no</button>
                  </div>
                </div>
              </div>
            </div>
   </div>
          
         
    <!--end container-->
    
    
   
    <!-- END MAIN -->
    

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
         function registerConsignment(){
        	
     			
        	 $("#registerConsignment").submit();
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
         */	
        	 
        	
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
           
           function closeConfirmation()
           {
        	   $("#closeOkPop").submit();
           }
          </script> 
          
    
  <script>
   			 populateCountries
   			 (   
     			 "country"
   			 );
  </script>

</body>
</html>