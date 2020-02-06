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
<jsp:include page="/WEB-INF/view/endUserHeader.jsp" ></jsp:include>
<jsp:include page="/WEB-INF/view/endUserFooter.jsp" ></jsp:include>
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
   <link rel="stylesheet"
	href="${context}/resources/project_css/iconStates.css">
    <style>
 /*        ul li {
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
           /*  border: none; */
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
           /*  height: 35px; */
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
        .selectDropdwn {
   margin-top: 0;
}
 div#trackGrievanctableDiv {
    width: 70%;
} 
    </style>
<script>
var contextpath = "${context}";
</script>

</head>
<body>
         <!-- START CONTENT -->
            <section id="content">
                <!--start container-->
                <div class="container" style="padding-bottom: 70px; margin-top:10vh;" >
                    <div class="section">
                        <div class="row card-panel responsive-page" id="endUserRaiseGrievance" style="display: none">
                            <h6 class="fixPage-modal-header "><spring:message code="modal.Grievance" /></h6>
                            <form onsubmit="return saveaAonymousGrievance()" method="POST" enctype="multipart/form-data" id="saveGrievance">
                             <input type="text" id="pageTypeValue" value="${reportType}" style="display: none;">
                                <div class="col s12 m12 l12">
                                    <div class="row">
                                        <div class="input-field col s12 m4">
                                            <input type="text" id="firstName" required="required" name="firstName" pattern="[a-zA-Z]{0,20}"
                                                title="Please enter alphabets upto 20 characters only" maxlength="20" />
                                            <label for="firstName"><spring:message code="input.firstName" /> <span class="star">*</span></label>
                                        </div>

                                        <div class="input-field col s12 m4">
                                            <input type="text" id="middleName" name="middleName" pattern="[a-zA-Z]{0,20}"
                                                title="Please enter alphabets upto 20 characters only" maxlength="20" />
                                            <label for="middleName"><spring:message code="input.middleName" /></label>
                                        </div>

                                        <div class="input-field col s12 m4">
                                            <input type="text" id="lastName" required="required" name="lastName" pattern="[a-zA-Z]{0,20}" title="Please enter alphabets upto 20 characters only"
                                                maxlength="20" />
                                            <label for="lastName"><spring:message code="input.lastName" /> <span class="star">*</span></label>
                                        </div>

                                        <div class="input-field col s12 m6">
                                            <input type="text" id="contactNumber" required="required" name="contactNumber" pattern="[0-9]{10,12}"
                                                title=numbers maxlength="10" />
                                            <label for="contactNumber"><spring:message code="input.contactNum" /><span
                                                    class="star">*</span></label>
                                        </div>

                                        <div class="input-field col s12 m6">
                                            <input type="email" id="emailID" name="emailID" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" title="please enter valid email formate "
                                                maxlength="30" />
                                            <label for="emailID"><spring:message code="input.EmailID" /></label>
                                        </div>

                                        <div class="col s12 m6 selectDropdwn">
                                            <label for="endUsercategory"><spring:message code="input.Category" /> <span class="star">*</span></label>
                                            <select class="browser-default" required="required" id="endUsercategory">
                                                <option value="" disabled selected><spring:message code="input.Category" /></option>
                                            </select>
                                        </div>

                                        <div class="input-field col s12 m6">
                                            <textarea id="endUserRemark" required="required" maxlength="200" class="materialize-textarea"></textarea>
                                            <label for="endUserRemark"><spring:message code="input.Remark" /><span
                                                    class="star">*</span></label>
                                        </div>

                                        <div class="input-field col s12 m6">
                                            <input type="text" id="endUsertransactionId" name="transactionId"
                                                pattern="[A-Z0-9]{18,18}" title="transaction id must be in 18 digit"
                                                maxlength="18" />
                                            <label for="endUsertransactionId"><spring:message code="input.TransactionID1" /></label>
                                        </div>

                                        
                                    </div>
									<div id="endUsermainDiv" class="endUsermainDiv">
									<div id="endUserfilediv" class="endUserfileDiv">	
                                    <div class="row">
                                        <div class="col s12 m6">
                                            <label for="endUserdocTypetag1"><spring:message code="input.documenttype" /></label>
                                            <select class="browser-default" id="endUserdocTypetag1">
                                                <option value="" disabled selected><spring:message code="select.documenttype" /></option>
                                            </select>
                                        </div>

                                        
                                        <div class="file-field col s12 m6">
                                            <h6 class="upload-file-label"><spring:message code="modal.UploadSupporting" />
                                            </h6>
                                            <div class="btn">
                                                <span><spring:message code="input.selectfile" /></span>
                                                <input id="endUserdocTypeFile1" type="file" name="files[]" id="filer_input"
                                                    multiple="multiple" />
                                            </div>
                                            <div class="file-path-wrapper">
                                                <input class="file-path validate" type="text"
                                                    placeholder="Upload  file">
                                            </div>
                                        </div>

                                        <div class="input_fields_wrap"></div>

                                      
                                       
                                    </div>
									</div>
									</div>	

                                      <div class="col s12 m6 right">
                                            <button class="btn right endUser_add_field_button"><span
                                                    style="font-size: 20px;">+</span><spring:message code="input.addmorefile" /></button>
                                        </div>
                                         <p><spring:message code="input.requiredfields" /> <span class="star">*</span></p>
                                    <div class="row" style="margin-top: 30px;">
                                        <div class="input-field col s12 m12 l12 center">
                                            <button class="btn" id="saveAnonymousGrieavance" type="submit"><spring:message code="button.submit" /></button>
                                            <a href="#cancelMessage" class="btn modal-trigger"
                                                style="margin-left: 10px;"><spring:message code="button.cancel" /></a>
                                        </div>
                                    </div>

                                </div>
                            </form>
                        </div>
                        
                        
                           <div class="row card-panel track-grievance-responsive-page" id="trackGrievanceHeader" >
                              
                     <!--        <a href="./redirectToHomePage" class="modal-close btn-flat modal-btn right" data-dismiss="modal">&times;</a> -->
                            <h6 class="fixPage-modal-header "><spring:message code="modal.TrackGrievance" /></h6>
                           <div id="trackGrievanceDiv" style="display: none;">
                            <div class="col s12 m12 l12">
                                <form action="" onsubmit="return endUsergrivanceLibraryTable()" method="POST" enctype="multipart/form-data" id="saveGrievance">
                                    <div class="row" id="singleInput">
                                        <!-- <h6>Track Grievance</h6>
                                        <hr> -->
                                        <div class="row">
                                            <div class="input-field col s6 m6 l5">
                                                <label for="trackGrievanceId"><spring:message code="modal.PleaseGrievanceID" /><span class="star">*</span> :</label>
                                            </div>
                                            <div class="input-field col s6 m6 l6">
                                                <input type="text" id="trackGrievanceId" required="required" name="trackGrievanceId" pattern="[A-Za-z0-9]{18,18}" title="Please enter maximum 18 characters only" maxlength="18">
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="input-field col s12 center">
                                                <button class="btn" type="submit" ><spring:message code="button.submit" /></button>
                                                <a href="./redirectToHomePage" class="btn modal-trigger" style="margin-left: 10px;"><spring:message code="button.cancel" /></a>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                                </div>
                               
                            </div>
                            
                               
                        </div>
                            <div class="row card-panel track-grievance-responsive-page" id="trackGrievanctableDiv" style="display: none" >
                              
                           <!--  <a href="./redirectToHomePage" class="modal-close btn-flat modal-btn right" data-dismiss="modal">&times;</a> -->
                            <h6 class="fixPage-modal-header "><spring:message code="modal.TrackGrievance" /></h6>
                          
                        <table id="endUsergrivanceLibraryTable" style="display: none"
								class="responsive-table striped display"></table>
								  <div class="input-field col s12 m12 l12 center">
                                           
                                            <a href="./redirectToHomePage" class="btn modal-trigger"
                                                style="margin-left: 10px;"><spring:message code="button.cancel" /></a>
                                        </div>
                    
                </div>
                
								</div></div>
                <!--end container-->
            </section>
            <!-- END CONTENT -->



 
    <!-- END FOOTER -->

    <!-- Grievance Modal start   -->

    <!-- Grievance Modal End -->

    <!-- Otp Modal start   -->



    <!-- Otp Modal End -->

    <!-- cancel Modal start   -->

    <div id="cancelMessage" class= " full-screen-modal modal" >
         <h6 class="modal-header"><spring:message code="button.cancel" /></h6>
        <div class="modal-content">
           <div class="row">
                <h6><spring:message code="modal.message" /></h6>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <div class="input-field col s12 center">
                        <a href="./redirectToHomePage" class="btn"><spring:message code="modal.yes" /></a>
                        <button class="modal-close btn" style="margin-left: 10px;"><spring:message code="modal.no" /></button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
     <div id="exceptionMessage" class="full-screen-modal modal">
       <h6 class="modal-header"><spring:message code="modal.SaveGrievance" /></h6>
        <div class="modal-content">
            <div class="row">
                <h6><spring:message code="modal.Somethingwrong" /></h6>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <div class="input-field col s12 center">
                        
                        <button class="modal-close btn" style="margin-left: 10px;"><spring:message code="modal.close" /></button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    

    <div id="GrievanceMsg" class="full-screen-modal modal">
    <h6 class="modal-header"><spring:message code="modal.header.submitGReport" /></h6>
        <div class="modal-content">
            
            <div class="row">
                <h6 ><spring:message code="modal.message.grievance" /> <span id="sucessMessageGrievance"></span></h6>

                <p ><spring:message code="modal.note" /></p>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <a href="./redirectToHomePage" class="btn"><spring:message code="modal.ok" /></a>
                </div>
            </div>
        </div>
    </div>
    <!-- cancel Modal End -->

	<div id="replyModal" class="full-screen-modal modal" >
        <button class="modal-close btn-flat right" onclick="cleanReplyPopUp()">&times;</button>
             <h6 class="modal-header"><spring:message code="input.reply" /></h6>
             <div class="modal-content">
             <form id="replymessageForm" onsubmit="return saveGrievanceReply()" method="POST" enctype="multipart/form-data" >
            <div class="row">
                <div class="col s12 m12">
                    <h6 style="font-weight: bold;"><spring:message code="input.grievID" /><span id="grievanceIdToSave"></span></h6>
                    <span id="grievanceTxnId" style="display: none;"></span>
                    <hr>
                </div>

                <div class="col s12 m12" id="viewPreviousMessage">
                   <!--  <h6 style="float: left; font-weight: bold;" id="mesageUserType"> </h6>
                    <h6 style="float: left;"></h6>
                        <span style="float:right;"></span> -->
                </div>
               
 
               <div class="col s12 m12">
                  <label for="replyRemark" style="margin-top: 7px"><spring:message code="input.remarks" /><span class="star">*</span></label>
                    <textarea id="replyRemark" class="materialize-textarea" maxlength="200" placeholder="" required="required"></textarea>
                    <input type="text" style="display: none" id="grievanceUserid">
                    <!-- <h6 style="color: #000;">Upload Supporting Document </h6> -->
                </div>
               <!--   <div class="file-field col s12 m12">
                    <div class="btn"><span>Select File</span><input id="replyFile" type="file" accept=".csv" ></div>
                    <div class="file-path-wrapper"><input class="file-path validate" type="text"
                            placeholder="">
                        <div>
                            <p id="myFiles"></p>
                        </div>
                    </div>
                </div> -->
                
 <div id="mainDiv" class="mainDiv">
<div id="filediv" class="fileDiv">
<div class="row">

<div class="col s12 m6 l6" style="margin-top: 8px;">
<label for="Category"><spring:message code="input.documenttype" /></label>
<select class="browser-default" id="docTypetag1" >
<option value="" disabled selected><spring:message code="select.documenttype" /> </option>

</select>

</div>

<div class="file-field col s12 m6">
<h6 style="color: #000;"><spring:message code="input.supportingdocument" /></h6>
<div class="btn">
<span><spring:message code="input.selectfile" /></span>
<input type="file" name="files[]" id="docTypeFile1"  multiple>
</div>
<div class="file-path-wrapper">
<input class="file-path validate" type="text" multiple
placeholder="Upload file">
<div>
<p id="myFiles"></p>
</div>
</div>
</div>

</div>


</div>

</div>
<div class="col s12 m6 right">
<button class="btn right add_field_button"><span
style="font-size: 20px;">+</span> <spring:message code="input.addmorefile" /></button>
</div>
              <div class="col s12 m12">  <p>
              <p id="closeTicketCheckbox" style="float: left; display: none;">
                        <label>
                            <span><spring:message code="modal.message.griev.closeticket" /></span>
                            <input type="checkbox" id="closeTicketCheck" />
                        </label>
                    </p> <br>
				<!-- <a href="./Consignment/sampleFileDownload/filetype=sample">Download Sample Format</a><br> -->
			

			<span> <spring:message code="input.requiredfields" /> <span class="star">*</span></span>
			
                </div>
                <div class="col s12 m12 center">
                 <p id="closeTicketCheckbox" style="float: left; display: none;">
                        <label>
                            <span><spring:message code="modal.message.griev.closeticket" /></span>
                            <input type="checkbox" id="closeTicketCheck" />
                        </label>
                    </p>
                    <button class="right btn" type="submit"><spring:message code="input.reply" /></button>
                </div>
            </div>
            </form>
        </div>
    </div>

 <div id="replyMsg" class="full-screen-modal modal">
    <h6 class="modal-header"><spring:message code="modal.header.grievancereply" /></h6>
    <div class="modal-content">
        
        <div class="row">
            <h6 id="showReplyResponse"><spring:message code="modal.message.grievance.reply" /><span id="replyGrievanceId"> </span> <spring:message code="modal.issuccessful" /></h6>
        </div>
        <div class="row">
            <div class="input-field col s12 center">
                <div class="input-field col s12 center">
                    <a href="./redirectToHomePage" class="modal-close btn"><spring:message code="modal.ok" /></a>
                </div>
            </div>
        </div>
    </div>
</div>
  <div id="errorModal" class=" full-screen-modal modal">
         <h6 class="modal-header"><spring:message code="input.CheckStock" /></h6>
        <div class="modal-content">
           
            <div class="row">
                <h6 id=""><spring:message code="input.notTransactionId" />
                </h6>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <div class="input-field col s12 center">
                        <!-- <a href="homePage" class="btn">Yes</a> -->
                        <button class="modal-close btn" style="margin-left: 10px;"><spring:message code="modal.ok" /></button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <div id="manageAccount" class=" full-screen-modal modal">
<button class="modal-close btn-flat right" data-dismiss="modal">&times;</button>
<h6 class="modal-header"><spring:message code="modal.header.grievancehistory" /></h6>
<div class="modal-content">
<div id="live-chat">
<div class="chat">
<div class="chat-history">
<div class="chat-message clearfix" id="chatMsg">

</div> <!-- end chat-message -->


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
<!-- i18n library -->
	<script type="text/javascript">
var path="${context}";
</script>

	<script type="text/javascript"
		src="${context}/resources/project_js/CLDRPluralRuleParser.js"></script>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.i18n/1.0.7/jquery.i18n.js"></script>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.i18n/1.0.7/jquery.i18n.messagestore.js"></script>

	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.i18n/1.0.7/jquery.i18n.fallbacks.js"></script>

	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.i18n/1.0.7/jquery.i18n.language.js"></script>

	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.i18n/1.0.7/jquery.i18n.parser.js"></script>


	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.i18n/1.0.7/jquery.i18n.emitter.js"></script>


	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.i18n/1.0.7/jquery.i18n.emitter.bidi.js"></script>

	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/history.js/1.8/bundled/html4+html5/jquery.history.js"></script>

	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/js-url/2.5.3/url.min.js"></script>

    <!-- data-tables -->
    <script type="text/javascript" src="${context}/resources/js/plugins/data-tables/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="${context}/resources/js/plugins/data-tables/data-tables-script.js"></script>

    <!--plugins.js - Some Specific JS codes for Plugin Settings-->
    <script type="text/javascript" src="${context}/resources/js/plugins.js"></script>
    <!--custom-script.js - Add your own theme custom JS-->
    <script type="text/javascript" src="${context}/resources/js/custom-script.js"></script>
    
    <script type="text/javascript"
		src="${context}/resources/project_js/grievanceManagement.js"></script>
    <script type="text/javascript"
		src="${context}/resources/project_js/endUserGrievance.js"></script>
  <!--   <script>
        $(document).ready(function () {
        	  usertypeDropDownData();
            $('.modal').modal();
        });
      
        
        
        
    </script> -->
    

    <script>
        $(document).ready(function () {
            $('.modal').modal();
         
        });
  
    function saveaAonymousGrievance(){

    	var firstName=$('#firstName').val();
    	var middleName=$('#middleName').val();
    	var lastName=$('#lastName').val();
    	var contactNumber=$('#contactNumber').val();
    	var emailID=$('#emailID').val();
    	var category=$('#endUsercategory').val();
    	
    	var txnId=$('#endUsertransactionId').val();
    	var remark=$('#endUserRemark').val();
    	var file=$('#myInput').val();
    	var fieldId=1;
    	var fileInfo =[];
    	var formData= new FormData();
    	var fileData = [];
    	
    	var x;
    	var filename='';
    	var filediv;
    	var i=0;
    	var formData= new FormData();
    	var docTypeTagIdValue='';
    	var filename='';
    	$('.endUserfileDiv').each(function() {	

    		
    		var x={
    		"docType":$('#endUserdocTypetag'+fieldId).val(),
    		"fileName":$('#endUserdocTypeFile'+fieldId).val().replace('C:\\fakepath\\','')
    		}
    		formData.append('files[]',$('#endUserdocTypeFile'+fieldId)[0].files[0]);
    		fileInfo.push(x);
    		fieldId++;
    		i++;
    	});
    	
    	var multirequest={
    			"attachedFiles":fileInfo,
    			"txnId":txnId,
    			"categoryId":category,
    			"remarks":remark,
    			"email":emailID,
    			"firstName":firstName,
    			"lastName":lastName,
    			"middleName":middleName,
    			"phoneNo":contactNumber,
    			"featureId":6
    	}
    	
    	formData.append('fileInfo[]',JSON.stringify(fileInfo));
    	formData.append('multirequest',JSON.stringify(multirequest));
    	/*formData.append('categoryId',category);
    	formData.append('remarks',remark);
    */
    	$.ajax({
    		url: './saveEndUserGrievance',
    		type: 'POST',
    		data: formData,
    		mimeType: 'multipart/form-data',
    		processData: false,
    		contentType: false,
    		async:false,
    		success: function (data, textStatus, jqXHR) {
    			console.log(data);
    			 $("#saveAnonymousGrieavance").prop('disabled', true);
    			var x=data;
    			var y= JSON.parse(x);
    			 $('#GrievanceMsg').openModal(); 
    			 $('#sucessMessageGrievance').text(y.txnId); 
    			
    		},
    		error: function (jqXHR, textStatus, errorThrown) {
    			/* console.log("error in ajax") */
    			 $('#exceptionMessage').openModal(); 
    		}
    	});
    return false;

    }
    
    
    
	var max_fields = 15; //maximum input boxes allowed
	var endUserwrapper = $(".endUsermainDiv"); //Fields wrapper
	var add_button = $(".endUser_add_field_button"); //Add button ID
	var x = 1; //initlal text box count
	var id=2;
	$(".endUser_add_field_button").click(function (e) { //on add input button click
		e.preventDefault();
		if (x < max_fields) { //max input box allowed
			x++; //text box increment
			$(endUserwrapper).append(
					'<div id="endUserfilediv'+id+'" class="endUserfileDiv"><div class="row"><div class="file-field col s12 m6"><label for="">'+$.i18n('documenttype')+' <span class="star">*</span></label><select id="endUserdocTypetag'+id+'" required class="browser-default"> <option value="" disabled selected>'+$.i18n('selectDocumentType')+' </option></select></div> <div class="file-field col s12 m6" style="margin-top: 23px;"><div class="btn"><span>'+$.i18n('selectfile')+'</span><input id="endUserdocTypeFile'+id+'" type="file" required name="files[]" id="filer_input" /></div><div class="file-path-wrapper"><input class="file-path validate" placeholder="Upload file" type="text"></div></div><div  class="endUser_remove_field btn right btn-info">-Remove</div></div></div>'
					/* '<div id="filediv'+id+'" class="fileDiv"><div class="row"><div class="file-field col s12 m6" style="margin-top: 23px;"><div class="btn"><span>'+$.i18n('selectfile')+'</span><input id="docTypeFile'+id+'" type="file" required name="files[]" id="filer_input" /></div><div class="file-path-wrapper"><input class="file-path validate" type="text"></div></div><div class="file-field col s12 m6"><label for="Category">'+$.i18n('documenttype')+' <span class="star">*</span></label><select id="docTypetag'+id+'" required class="browser-default"> <option value="" disabled selected>'+$.i18n('selectDocumentType')+' </option></select><select id="docTypetagValue'+id+'" style="display:none" class="browser-default"> <option value="" disabled selected>'+$.i18n('selectDocumentType')+' </option></select></div><div style="cursor:pointer;background-color:red;margin-right: 1.7%;" class="remove_field btn right btn-info">-</div></div></div>' */
			); //add input box
		}
		
		
		$.getJSON('./getDropdownList/DOC_TYPE', function(data) {


			for (i = 0; i < data.length; i++) {
				console.log(data[i].interp);
				var optionId=id-1;
				$('<option>').val(data[i].tagId).text(data[i].interp).appendTo('#endUserdocTypetag'+optionId);
				

			}
		});
		id++;

	});

$(endUserwrapper).on("click", ".endUser_remove_field", function (e) { //user click on remove text
 e.preventDefault();
 var Iid=id-1;
 /*alert("@@@"+Iid)*/
 $('#endUserfilediv'+Iid).remove();
 $(this).parent('div').remove();
 x--;
 id--;

 })
 
 
$.getJSON('./getDropdownList/DOC_TYPE', function(data) {
	for (i = 0; i < data.length; i++) {
		console.log(data[i].interp);
		$('<option>').val(data[i].tagId).text(data[i].interp).appendTo('#endUserdocTypetag1');
		
	}
});
$.getJSON('./getDropdownList/GRIEVANCE_CATEGORY', function(data) {
	for (i = 0; i < data.length; i++) {
		console.log(data[i].interp);
		$('<option>').val(data[i].value).text(data[i].interp).appendTo('#endUsercategory');
		
	}
});
    

if($('#pageTypeValue').val()==0)
{
	console.log("if condition ++++++++");
$('#endUserRaiseGrievance').css("display", "block");
$('#trackGrievanceDiv').css("display", "none");
$('#trackGrievanceHeader').css("display", "none");
$('#trackGrievanctableDiv').css("display", "none");
}
else
{
console.log("else condition ++++++++");
$('#endUserRaiseGrievance').css("display", "none");
$('#trackGrievanceDiv').css("display", "block");
$('#trackGrievanctableDiv').css("display", "none");

}
    
  
  
  
   </script>

    

   
</body>
</html>