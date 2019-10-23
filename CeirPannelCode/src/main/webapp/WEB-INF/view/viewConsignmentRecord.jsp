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


 <!-- START CONTENT -->
            <section id="content">
                <!--start container-->
                <div class="container">
                    <div class="section">
                        <div class="row">
                            <div class="col s12 m12 l12">
                                <div class="row card-panel">
                                    <div class="container-fluid pageHeader">
                                        <p class="PageHeading">View Consignment</p>
                                        <!-- <button type="button" class="waves-effect waves-light modal-trigger boton right"
                                        data-target="modal1">Register Consignment</button> -->
                                    </div>
                                    <form action="">

                                    <div class="row myRow">
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
                                            <select id="country" class="browser-default" class="mySelect"
                                                disabled></select>
                                            <label for="country" class="center-align"></label>
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
                        </div>
                    </div>
                </div>
                <!--end container-->
            </section>



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
        	 
        	/*  else if($('#consignmentFile').val()=="")
    		 {
        		 myFunction("please select file to be uploaded ");
    		 return false;
    		 } */
    		 
    		 else  if(csvFile!=""){
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
		 }
    	
        	 
        	 $("#registerConsignment").submit();
        	
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