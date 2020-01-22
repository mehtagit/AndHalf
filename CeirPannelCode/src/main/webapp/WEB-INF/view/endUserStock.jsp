<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>  
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="msapplication-tap-highlight" content="no">
    <meta name="description"
        content="Materialize is a Material Design Admin Template,It's modern, responsive and based on Material Design by Google. ">
    <meta name="keywords"
        content="materialize, admin template, dashboard template, flat admin template, responsive admin template,">
    <title>CEIR | Importer Portal</title>

    <link href="${context}/resources/js/plugins/data-tables/css/jquery.dataTables.min.css" type="text/css" rel="stylesheet"
        media="screen,projection">

    <!-- Favicons-->
    <!--<link rel="icon" href="images/favicon/favicon-32x32.png" sizes="32x32">-->
    <!-- Favicons-->
    <link rel="apple-touch-icon-precomposed" href="images/favicon/apple-touch-icon-152x152.png">
    <!-- For iPhone -->
    <meta name="msapplication-TileColor" content="#00bcd4">
    <meta name="msapplication-TileImage" content="images/favicon/mstile-144x144.png">
    <!-- For Windows Phone -->
    <link rel="stylesheet" href="${context}/resources/font/font-awesome/css/font-awesome.min.css">

    <!-- CORE CSS-->
    <link href="${context}/resources/css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection">
    <link href="${context}/resources/css/style.css" type="text/css" rel="stylesheet" media="screen,projection">
    <!-- Custome CSS-->
    <link href="${context}/resources/css/custom/custom.css" type="text/css" rel="stylesheet" media="screen,projection">

    <!-- INCLUDED PLUGIN CSS ON THIS PAGE -->
    <link href="${context}/resources/js/plugins/prism/prism.css" type="text/css" rel="stylesheet" media="screen,projection">
    <link href="${context}/resources/js/plugins/perfect-scrollbar/perfect-scrollbar.css" type="text/css" rel="stylesheet"
        media="screen,projection">
    <link href="${context}/resources/js/plugins/chartist-js/chartist.min.css" type="text/css" rel="stylesheet" media="screen,projection">

    <style>
        ul li {
            display: inline-flex;
        }

        li {
            padding: 7px 15px;
            border: solid 1px #c9c9c9;
            border-radius: 5px;
            margin-right: 10px;
        }
        select{
            height: 1.5rem;
            border: none;
        }
        footer {
            padding-left: 0;
        }
        
         /* body {
            background-color: #00bcd4;
        } */

        .boton {
            color: #2979a5;
            float: right;
            border: solid 1px rgba(33, 167, 201, 0.774);
            padding: 4px 10px;
            border-radius: 7px;
            font-size: 14px;
            background-color: #fff;
        }

        .row {
            margin-bottom: 0;
            margin-top: 0;
        }

        textarea.materialize-textarea {
            padding: 0;
        }

        input[type=text] {
            height: 35px;
            margin: 0 0 5px 0;
        }

        footer {
            padding-left: 0;
        }

        .card-panel {
            width: 50%;
            margin-top: 5vh;
        }

        @media only screen  and (max-width: 992px){
            .card-panel {
            width: 100%;
        } 
        }

        #content .container .row {
            margin: auto;
        }

        @media only screen and (max-width: 425px){
            .selectDropdwn {
                margin-top: 0px;
            }

            .selectDropdwn label {
                margin-left: 2%;
            }
        }

        @media only screen and (max-width: 425px) {
            select {
                width: 96%;
                margin-left: 2%;
            }
        }

        .small-modal {
            width: 40%;
        }

        @media only screen and (max-width: 992px) {
            .small-modal {
            width: 70%;
        }
        }

        @media only screen and (max-width: 600px) {
            .small-modal {
            width: 95%;
        }
        }
        
        .modal {
        	width: 50%;
        }
    </style>
<script>
var contextpath = "${context}";
</script>

</head>
<body>

    <header id="header" class="page-topbar">
        <!-- start header nav-->
        <div class="navbar-fixed">
            <nav class="navbar-color">
                <div class="nav-wrapper">
                    <ul class="left">
                        <li>
                            <h1 class="logo-wrapper"><a href="index.html" class="brand-logo darken-1">CEIR </a> <span
                                    class="logo-text">Materialize</span></h1>
                        </li>
                    </ul>
                    
                    <ul id="chat-out" class="right hide-on-med-and-down"
style="overflow: inherit !important;">

<li><div id="divLang" style="display:flex;margin: 8px 6px;" class="darken-1">
			<div id="iconLable" class="darken-1"><i class="fa fa-globe fa-6" aria-hidden="true"></i></div>	
			<div><select class="darken-1" id="langlist" style="border-bottom: none;height: 42px;background: #00bcd4;border: 1px solid #00bcd4 !important;">
					<option value="en">English</option>
					<option value="km">Khmer</option>
					</select></div>
			</div>
			</li>
		<li><a href="./homePage" id="newUserLink" style="color:white;">Home</a>	</li>			
</ul>
                    
                </div>
            </nav>
        </div>
        <!-- end header nav-->
    </header>


           <section id="content">
                <!--start container-->
                <input type="text" id="pageTypeValue" value="${showPagetype}" style="display: none;">
                <div class="container" style="margin-top:10vh;" id="uploadPaidStatusDiv" style="dispay:none">
                    <div class="section">
                        <div class="row card-panel upload-stock-responsive-page">
                            <h6 class="fixPage-modal-header ">Upload Stock</h6>
                           	<form action="" onsubmit="return uploadEndUserStock()" method="POST"
								enctype="multipart/form-data" id="uploadStock">
                                <div class="col s12 m12 l12">
                                    <div class="row">
                                        <!-- <div class="row myRow">
                                            <div class="input-field col s6 m6">
                                                <label for="supplierId" style="color: #000;">Supplier
                                                    Id </label>
                                                <input type="text" id="supplierId" name="supplierId"
                                                    pattern=[A-Za-z0-9]{3,12}
                                                    title="Please enter minimum 4 and maximum 12 characters only"
                                                    maxlength="10" />
                                            </div>
                                            <div class="input-field col s6 m6">
                                                <label for="supplierName" style="color: #000;">Supplier
                                                    Name</label>
                                                <input type="text" id="supplierName" name="supplierName"
                                                    pattern=[A-Za-z]{3,15}
                                                    title="Please enter minimum 4 and maximum 15 alphabates only"
                                                    maxlength="15" />
                                            </div>
                                        </div> -->

                                        <div class="row myRow">
                                            <div class="input-field col s12 m6">
                                                <label for="endUser" style="color: #000;">Email ID </label>
                                                <input type="email" id="endUseremail" maxlength="30" name="email"/>
                                            </div>

                                            <div class="input-field col s12 m6 quantity" style="margin-top: 19px;">
                                                <label for="endUserquantity" style="color: #000;">Quantity <span class="star">*</span></label>
                                                <input type="text" id="endUserquantity" name="endUserquantity" pattern=[0-9]{0.7}
                                                    title="Please enter maximum 10 characters only" maxlength="10" />
                                            </div>

                                            <div class="file-field col s12 m6">
                                                <h6 style="margin-top: 15px;">Upload File <span
                                                        class="star">*</span></h6>
                                                <div class="btn">
                                                    <span>Select File</span>
                                                    <input type="file" id="endUsercsvUploadFile" accept=".csv" required="required">
                                                </div>
                                                <div class="file-path-wrapper">
                                                    <input class="file-path validate responsive-file-div" id="endUsersaveFileName" type="text">
                                                </div>
                                            </div>

                                            <!-- <div class="col s12 m6 l6" style="margin-top: 15px;">
                                                <label for="Category">Document Type <span class="star">*</span></label>
                                                <select class="browser-default">
                                                    <option value="" disabled selected>Select Document Type </option>
                                                    <option value="1">Passport Number</option>
                                                    <option value="2">Visa Number</option>
                                                    <option value="3">Pan Number</option>
                                                </select>
                                            </div> -->
                                        </div>
                                        
                                        <p style="margin-left: 10px;"><a href="#">Download Sample
                                            Format</a></p>
                                        <p style="margin-left: 10px;"> Required Field are marked with <span class="star">*</span></p>
                                    </div>
                                    <div class="row" style="margin: 30px 0 30px 0;">
                                        <div class="input-field col s12 m12 l12 center">
                                            <!-- <a href="#submitStock" class="btn modal-trigger">Submit</a> -->
                                            <button class=" btn" type="submit">Submit</button>
                                            <a href="#cancelStock" class="btn modal-trigger"
                                                style="margin-left: 10px;">Cancel</a>
                                        </div>
                                    </div>

                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                
                                       <div class="container" style="margin-top:10vh;display: none" id="checkUploadStatusDiv" >
                    <div class="section">
                        <div class="row card-panel upload-stock-responsive-page" >
                            <a href="index.html" class="modal-close btn-flat modal-btn right"
                                data-dismiss="modal">&times;</a>
                                <h6 class="fixPage-modal-header ">Check Upload Status</h6>
                                 	
                                <div class="col s12 m12 l12">
                                <form action="" onsubmit="return validateTxnId()" method="POST"
								enctype="multipart/form-data" id="uploadStock">
                                    <div class="row" id="singleInput">
                                        <div class="row">
                                            <div class="input-field col s6 m5">
                                                <label for="transactionID">Please Enter The Transaction ID <span class="star">*</span> :</label>
                                            </div>
                                            <div class="input-field col s6 m7">
                                                <input type="text" id="checktransactionID" name="checktransactionID"
                                                    pattern=[A-Z0-9]{18,18} required="required"
                                                    title="Please enter maximum 18 Numbers only" maxlength="18">
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="input-field col s12 center">
                                               <!--  <a href="#" class="btn"
                                                    onclick="document.getElementById('singleInput').style.display ='none'; 
                                                    document.getElementById('inputDetails').style.display ='block';">Submit</a> -->
                                                    <button class=" btn" type="submit">Submit</button>
                                                <a href="#cancelStock" class="btn modal-trigger modal-close"
                                                    style="margin-left: 10px;">Cancel</a>
                                            </div>
                                        </div>
                                    </div></form>
                                    <div class="row" id="inputDetails" style="display: none;">
                                    <form action="" onsubmit="return updateFile()" method="POST"
								enctype="multipart/form-data" id="uploadStock">
                                        <div class="row">
                                            <div class="input-field col s6 m5">
                                                <label for="transactionID">Transaction ID :</label>
                                            </div>
                                            <div class="input-field col s6 m7">
                                                <input type="text" id="transactionID" name="transactionID"
                                                    placeholder="" readonly="readonly">
                                            </div>

                                            <div class="input-field col s6 m5">
                                                <label for="uploadDate">Upload Date :</label>
                                            </div>
                                            <div class="input-field col s6 m7">
                                                <input type="text" id="uploadDate" name="uploadDate"
                                                    placeholder="" readonly="readonly">
                                            </div>
                                            <div class="input-field col s6 m5">
                                                <label for="viewUploadFile">View Upload File :</label>
                                            </div>
                                            <div class="input-field col s6 m7">
                                               <a href="JavaScript:void();" onclick="endUserStockFileDownload()" > <i class="fa fa-download download-icon" aria-hidden="true"
                                                    style="position: absolute; right: 0; margin: 10px 15px 0 0;"
                                                    title="download"></i></a>
                                                <input type="text" id="viewUploadFile" name="viewUploadFile"
                                                     placeholder="" readonly="readonly">
                                            </div>
											<div id="errorFileStatusDiv" style="display: none;">
                                            <div class="input-field col s6 m5">
                                                <label for="errorFileStatus">File Status :</label>
                                            </div>
                                            <div class="input-field col s6 m7">
                                                <input type="text" id="errorFileStatus" name="errorFileStatus"
                                                    placeholder="Error" readonly="readonly">
                                            </div>

                                            <div class="input-field col s6 m5">
                                                <label for="errorFileName">View Error Report :</label>
                                            </div>
                                            <div class="input-field col s6 m7">
                                                <a href="JavaScript:void();"><i class="fa fa-download download-icon" aria-hidden="true"
                                                    style="position: absolute; right: 0; margin: 10px 15px 0 0;"
                                                    title="download"></i></a>
                                                <input type="text" id="errorFileName" name="errorFileName"
                                                    placeholder="" readonly="readonly">
                                            </div>

                                            <div class="input-field col s6 m5">
                                                <label for="viewUploadFile">Do you want to upload a fresh file?</label>
                                            </div>
                                            <div class="input-field col s6 m7">
                                                <div class=" boxHeight" style="margin-top: 15px;">
                                                    <input class="with-gap" name="group3" type="radio"
                                                        onclick="document.getElementById('uploadFile').style.display = 'block';">
                                                    Yes
                                                </div>
                                            </div>

													
                                            <div id="uploadFile" style="display: none;">
                                            
                                                <!-- <h6 class="file-upload-heading" style="margin-left: 15px;">Upload File
                                                </h6> -->
                                                <div class="file-field input-field col s12 m12"
                                                    style="margin-top: 5px;">
                                                    <div class="btn">
                                                        <span>Select File</span>
                                                        <input type="file" id="csvUploadFile" accept=".csv">
                                                    </div>
                                                    <div class="file-path-wrapper">
                                                        <input class="file-path validate responsive-file-div"
                                                            type="text">
                                                    </div>
                                                </div><br><br>
                                               
                                            </div>
                                        </div></div>
                                        <div class="row">
                                            <div class="input-field col s12 center">
                                                <!-- <a href="homePage" class="btn" style="width: 100%;">ok</a> -->
                                                 <button class=" btn" id="updateEndUserStockOK" type="submit">OK</button>
                                                <button class=" btn" id="updateEndUserStock" type="submit">Submit</button>
                                            </div>
                                        </div>
                                         </form>
                                    </div>

                                    <div class="row" id="cancelInputDetails" style="display: none;">
                                        <h6>Cancel Stock</h6>
                                        <div class="row">
                                            <h6 style="margin-left: 15px;">Do you really want to Exit page?
                                            </h6>
                                        </div>
                                        <div class="row">
                                            <div class="input-field col s12 center">
                                                <div class="input-field col s12 center">
                                                    <a href="index.html" class="btn">Yes</a>
                                                    <button class="btn" style="margin-left: 10px;"
                                                        onclick="document.getElementById('singleInput').style.display = 'none';
                                                        document.getElementById('cancelInputDetails').style.display = 'none';
                                                        document.getElementById('inputDetails').style.display = 'block';">No</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                           
                        </div>
                    </div>
                </div>
                <!--end container-->
            </section>
            <!-- END CONTENT -->




    <!-- Submit Modal start   -->

    <div id="endUserStockModal" class="modal">
     <h6 class="modal-header">Submit Stock</h6>
        <div class="modal-content">
           
            <div class="row">
                <!-- <h6>Your request to upload device details has been accepted. The Transaction ID is ___________. Please
                    save this for future reference.
                    Kindly check the status of file upload by clicking on the check upload status button on the previous
                    page and providing the Transaction ID. -->
                   <h6 id="sucessMessageId"> Your form has been successfully submitted. The Transaction ID for future reference is <span id="endUsertXnId"></span></h6>
             <!--    </h6> -->
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <div class="input-field col s12 center">
                        <a href="homePage" class="btn">ok</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
        <div id="cancelStock" class="modal">
         <h6 class="modal-header">Cancel Stock</h6>
        <div class="modal-content">
           
            <div class="row">
                <h6>Do you really want to Exit page?
                </h6>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <div class="input-field col s12 center">
                        <a href="homePage" class="btn">Yes</a>
                        <button class="modal-close btn" style="margin-left: 10px;">No</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
     <div id="errorModal" class="modal">
         <h6 class="modal-header">Check Stock Status</h6>
        <div class="modal-content">
           
            <div class="row">
                <h6 >stock not found please enter valid Transaction Id.
                </h6>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <div class="input-field col s12 center">
                        <!-- <a href="homePage" class="btn">Yes</a> -->
                        <button class="modal-close btn" style="margin-left: 10px;">OK</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- ================================================
    Scripts
    ================================================ -->
 <!-- jQuery Library -->
    <%-- <script type="text/javascript" src="${context}/resources/js/plugins/jquery-1.11.2.min.js"></script> --%>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.1/jquery.min.js"></script>
       <!-- ajax js -->
    <script type="text/javascript" src="${context}/resources/ajax/Registration.js"></script>
    <!--materialize js-->
    <script type="text/javascript" src="${context}/resources/js/materialize.js"></script>
    <!--prism
    <script type="text/javascript" src="${context}/resources/js/prism/prism.js"></script>-->
    <!--scrollbar-->
    <script type="text/javascript" src="${context}/resources/js/plugins/perfect-scrollbar/perfect-scrollbar.min.js"></script>
    <!-- chartist -->
    <script type="text/javascript" src="${context}/resources/js/plugins/chartist-js/chartist.min.js"></script>

    <!-- data-tables -->
    <script type="text/javascript" src="${context}/resources/js/plugins/data-tables/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="${context}/resources/js/plugins/data-tables/data-tables-script.js"></script>

    <!--plugins.js - Some Specific JS codes for Plugin Settings-->
    <script type="text/javascript" src="${context}/resources/js/plugins.js"></script>
    <!--custom-script.js - Add your own theme custom JS-->
    <script type="text/javascript" src="${context}/resources/js/custom-script.js"></script>
        <script type="text/javascript" src="${context}/resources/project_js/endUserStock.js"></script>
    <script>
        $(document).ready(function () {
      
        if($('#pageTypeValue').val()==0)
        	{
        	$('#uploadPaidStatusDiv').css("display", "block");
        	$('#checkUploadStatusDiv').css("display", "none");
        	}
        else
        {
        	$('#uploadPaidStatusDiv').css("display", "none");
        	$('#checkUploadStatusDiv').css("display", "block");
        }
            $('.modal').modal();
        });
      
    </script>

   
</body>
</html>