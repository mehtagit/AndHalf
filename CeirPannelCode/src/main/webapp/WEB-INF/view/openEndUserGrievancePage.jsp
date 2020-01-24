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



            <!-- START CONTENT -->
            <section id="content">
                <!--start container-->
                <div class="container" style="padding-bottom: 70px; margin-top:10vh;">
                    <div class="section">
                        <div class="row card-panel responsive-page">
                            <h6 class="fixPage-modal-header ">Grievance</h6>
                            <form onsubmit="return saveaAonymousGrievance()" method="POST" enctype="multipart/form-data" id="saveGrievance">
                             <input type="text" id="pageTypeValue" value="${reportType}" style="display: none;">
                                <div class="col s12 m12 l12">
                                    <div class="row">
                                        <div class="input-field col s12 m4">
                                            <input type="text" id="firstName" name="firstName" pattern="[a-zA-Z]{0,20}"
                                                title="Please enter alphabets upto 20 characters only" maxlength="20" />
                                            <label for="firstName">First Name <span class="star">*</span></label>
                                        </div>

                                        <div class="input-field col s12 m4">
                                            <input type="text" id="middleName" name="middleName" pattern="[a-zA-Z]{0,20}"
                                                title="Please enter alphabets upto 20 characters only" maxlength="20" />
                                            <label for="middleName">Middle Name</label>
                                        </div>

                                        <div class="input-field col s12 m4">
                                            <input type="text" id="lastName" name="lastName" pattern="[a-zA-Z]{0,20}" title="Please enter alphabets upto 20 characters only"
                                                maxlength="20" />
                                            <label for="lastName">Last Name <span class="star">*</span></label>
                                        </div>

                                        <div class="input-field col s12 m6">
                                            <input type="text" id="contactNumber" name="contactNumber" pattern="[0-9]{10,12}"
                                                title=numbers maxlength="10" />
                                            <label for="contactNumber">Contact Number <span
                                                    class="star">*</span></label>
                                        </div>

                                        <div class="input-field col s12 m6">
                                            <input type="email" id="emailID" name="emailID"
                                                maxlength="30" />
                                            <label for="emailID">Email ID</label>
                                        </div>

                                        <div class="col s12 m6 selectDropdwn">
                                            <label for="Category">Category <span class="star">*</span></label>
                                            <select class="browser-default" id="category">
                                                <option value="" disabled selected>Category</option>
                                            </select>
                                        </div>

                                        <div class="input-field col s12 m6">
                                            <textarea id="Remark" class="materialize-textarea"></textarea>
                                            <label for="Remark">Remark <span
                                                    class="star">*</span></label>
                                        </div>

                                        <div class="input-field col s12 m6">
                                            <input type="text" id="transactionId" name="transactionId"
                                                pattern="[A-Z0-9]{18,18}" title="transaction id must be in 18 digit"
                                                maxlength="18" />
                                            <label for="transactionId">Transaction ID</label>
                                        </div>

                                        
                                    </div>
									<div id="mainDiv" class="mainDiv">
									<div id="filediv" class="fileDiv">	
                                    <div class="row">
                                        <div class="col s12 m6">
                                            <label for="Category">Document Type </label>
                                            <select class="browser-default" id="docTypetag1">
                                                <option value="" disabled selected>Select Document Type </option>
                                            </select>
                                        </div>

                                        
                                        <div class="file-field col s12 m6">
                                            <h6 style="color: #000;">Upload Supporting Document (if any)
                                            </h6>
                                            <div class="btn">
                                                <span>Select File</span>
                                                <input id="docTypeFile1" type="file" name="files[]" id="filer_input"
                                                    multiple="multiple" />
                                            </div>
                                            <div class="file-path-wrapper">
                                                <input class="file-path validate" type="text"
                                                    placeholder="Upload one or more files">
                                            </div>
                                        </div>

                                        <div class="input_fields_wrap"></div>

                                      
                                       
                                    </div>
									</div>
									</div>	

                                      <div class="col s12 m6 right">
                                            <button class="btn right add_field_button"><span
                                                    style="font-size: 20px;">+</span> Add More files</button>
                                        </div>
                                         <p>Required Field are marked with <span class="star">*</span></p>
                                    <div class="row" style="margin-top: 30px;">
                                        <div class="input-field col s12 m12 l12 center">
                                            <button class="btn" type="submit">Submit</button>
                                            <a href="#cancelMessage" class="btn modal-trigger"
                                                style="margin-left: 10px;">Cancel</a>
                                        </div>
                                    </div>

                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <!--end container-->
            </section>
            <!-- END CONTENT -->



 <!-- START FOOTER -->
    <footer class="page-footer" style="position: fixed; bottom: 0; width: 100%;">
        <div class="footer-copyright">
            <div class="container">
                <span class="right">Copyright © 2018 Sterlite Technologies Ltd, All rights reserved.</span>
            </div>
        </div>
    </footer>
    <!-- END FOOTER -->

    <!-- Grievance Modal start   -->

    <div id="GrievanceMsg" class="modal" style="width: 40%;">
        <div class="modal-content">
            <h6 class="modal-header">Submit Grievance Report</h6>
            <div class="row">
                <h6>Your grievance report has been successfully submitted. Your Grievance Id is GB12345</h6>

                <p>(Note: Please remember your grievance Id. This is used for future reference)</p>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <a href="index.html" class="btn">ok</a>
                </div>
            </div>
        </div>
    </div>

    <!-- Grievance Modal End -->

    <!-- Otp Modal start   -->

    <div id="submitGrievance" class="modal small-modal">
        <div class="modal-content">
            <form action="">
                <h6 class="modal-header">Enter OTP</h6>
                <p class="center" id="resendOtp" style="display: none;">A text message and e-mail with OTP has been sent
                </p>
                <div class="row">
                    <div class="input-field col s12 m12">
                        <input type="text" name="SupplierID" id="SupplierID" placeholder="Enter OTP of Email" />
                    </div>

                    <div class="input-field col s12 m12">
                        <input type="text" name="SupplierID" id="SupplierID" placeholder="Enter OTP of Phone" />
                    </div>
                </div>

                <a href="#" onclick="document.getElementById('resendOtp').style.display ='block';" class="right">Resend
                    OTP</a>

                <a href="#GrievanceMsg" class="btn modal-trigger modal-close"
                    style="width: 100%; margin-top: 20px; margin-bottom: 20px;">Done</a>
            </form>
        </div>
    </div>

    <!-- Otp Modal End -->

    <!-- cancel Modal start   -->

    <div id="cancelMessage" class="modal" style="width: 40%;">
        <div class="modal-content">
            <h6 class="modal-header">Cancel</h6>
            <div class="row">
                <h6>Are you sure you want to close this window. The form data will be lost</h6>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <div class="input-field col s12 center">
                        <a href="index.html" class="btn">yes</a>
                        <button class="modal-close btn" style="margin-left: 10px;">no</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
     <div id="exceptionMessage" class="modal" style="width: 40%;">
       <h6 class="modal-header">Save Grievance</h6>
        <div class="modal-content">
            <div class="row">
                <h6>Something wrong happened ,please resubmit the form</h6>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <div class="input-field col s12 center">
                        
                        <button class="modal-close btn" style="margin-left: 10px;">Close</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
   <!--  <div id="GrievanceMsg" class="modal" style="width: 40%; z-index: 1005; display: block; opacity: 1; transform: scaleX(1); top: 10%;">
        <div class="modal-content">
            <h6 class="modal-header">Submit Grievance Report</h6>
            <div class="row">
                <h6>Your grievance report has been successfully submitted. Your Grievance Id is GB12345</h6>

                <p>(Note: Please remember your grievance Id. This is used for future reference)</p>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <a href="./redirectToHomePage" class="btn">ok</a>
                </div>
            </div>
        </div>
    </div> -->
    
    <div id="GrievanceMsg" class="modal" style="width: 40%; z-index: 1005;; opacity: 1; transform: scaleX(1); top: 10%;">
        <div class="modal-content">
            <h6 class="modal-header">Submit Grievance Report</h6>
            <div class="row">
                <h6 >Your grievance report has been successfully submitted. Your Grievance Id is <span id="sucessMessageGrievance"></span></h6>

                <p >(Note: Please remember your grievance Id. This is used for future reference)</p>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <a href="./redirectToHomePage" class="btn">ok</a>
                </div>
            </div>
        </div>
    </div>
    <!-- cancel Modal End -->



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
  <!--   <script>
        $(document).ready(function () {
        	  usertypeDropDownData();
            $('.modal').modal();
        });
      
        
        
        
    </script> -->
    

    <script>
        $(document).ready(function () {
            $('.modal').modal();
            if($('#pageTypeValue').val()==0)
        	{
        	$('#').css("display", "block");
        	$('#checkUploadStatusDiv').css("display", "none");
        	}
        else
        {
        	$('#uploadPaidStatusDiv').css("display", "none");
        	$('#checkUploadStatusDiv').css("display", "block");
        }
        });
        
          
    </script>


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script>
    function saveaAonymousGrievance(){
    
    	var firstName=$('#firstName').val();
    	var middleName=$('#middleName').val();
    	var lastName=$('#lastName').val();
    	var contactNumber=$('#contactNumber').val();
    	var emailID=$('#emailID').val();
    	var category=$('#category').val();
    	
    	var txnId=$('#transactionId').val();
    	var remark=$('#Remark').val();
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
    	$('.fileDiv').each(function() {	

    		
    		var x={
    		"docType":$('#docTypetag'+fieldId).val(),
    		"fileName":$('#docTypeFile'+fieldId).val().replace('C:\\fakepath\\','')
    		}
    		formData.append('files[]',$('#docTypeFile'+fieldId)[0].files[0]);
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
    			 $("#saveGrievancesubmitButton").prop('disabled', true);
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
	var wrapper = $(".mainDiv"); //Fields wrapper
	var add_button = $(".add_field_button"); //Add button ID
	var x = 1; //initlal text box count
	var id=2;
	$(".add_field_button").click(function (e) { //on add input button click
		e.preventDefault();
		if (x < max_fields) { //max input box allowed
			x++; //text box increment
			$(wrapper).append(
					'<div id="filediv'+id+'" class="fileDiv"><div class="row"><div class="file-field col s12 m6"><label for="Category">'+$.i18n('documenttype')+' <span class="star">*</span></label><select id="docTypetag'+id+'" required class="browser-default"> <option value="" disabled selected>'+$.i18n('selectDocumentType')+' </option></select></div> <div class="file-field col s12 m6" style="margin-top: 23px;"><div class="btn"><span>'+$.i18n('selectfile')+'</span><input id="docTypeFile'+id+'" type="file" required name="files[]" id="filer_input" /></div><div class="file-path-wrapper"><input class="file-path validate" type="text"></div></div><div style="cursor:pointer;background-color:red;margin-right: 1.7%;" class="remove_field btn right btn-info">-Remove</div></div></div>'
					/* '<div id="filediv'+id+'" class="fileDiv"><div class="row"><div class="file-field col s12 m6" style="margin-top: 23px;"><div class="btn"><span>'+$.i18n('selectfile')+'</span><input id="docTypeFile'+id+'" type="file" required name="files[]" id="filer_input" /></div><div class="file-path-wrapper"><input class="file-path validate" type="text"></div></div><div class="file-field col s12 m6"><label for="Category">'+$.i18n('documenttype')+' <span class="star">*</span></label><select id="docTypetag'+id+'" required class="browser-default"> <option value="" disabled selected>'+$.i18n('selectDocumentType')+' </option></select><select id="docTypetagValue'+id+'" style="display:none" class="browser-default"> <option value="" disabled selected>'+$.i18n('selectDocumentType')+' </option></select></div><div style="cursor:pointer;background-color:red;margin-right: 1.7%;" class="remove_field btn right btn-info">-</div></div></div>' */
			); //add input box
		}
		
		
		$.getJSON('./getDropdownList/DOC_TYPE', function(data) {


			for (i = 0; i < data.length; i++) {
				console.log(data[i].interp);
				var optionId=id-1;
				$('<option>').val(data[i].tagId).text(data[i].interp).appendTo('#docTypetag'+optionId);
				console.log('#docTypetag'+optionId);

			}
		});
		id++;

	});

$(wrapper).on("click", ".remove_field", function (e) { //user click on remove text
 e.preventDefault();
 var Iid=id-1;
 /*alert("@@@"+Iid)*/
 $('#filediv'+Iid).remove();
 $(this).parent('div').remove();
 x--;
 id--;

 })
 
 
$.getJSON('./getDropdownList/DOC_TYPE', function(data) {
	for (i = 0; i < data.length; i++) {
		console.log(data[i].interp);
		$('<option>').val(data[i].tagId).text(data[i].interp).appendTo('#docTypetag1');
		
	}
});
$.getJSON('./getDropdownList/GRIEVANCE_CATEGORY', function(data) {
	for (i = 0; i < data.length; i++) {
		console.log(data[i].interp);
		$('<option>').val(data[i].value).text(data[i].interp).appendTo('#category');
		
	}
});
    </script>

    

   
</body>
</html>