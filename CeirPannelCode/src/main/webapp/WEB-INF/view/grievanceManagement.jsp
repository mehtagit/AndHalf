<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<title>Grievance</title>

<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta content="" name="description" />
<meta content="" name="author" />

<script type="text/javascript"
	src="${context}/resources/js/plugins/jquery-1.11.2.min.js"></script>
<!--   <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css"></script>  
 -->

		<script type="text/javascript">
var path="${context}";
</script>

<!-- CORE CSS-->
<link href="${context}/resources/css/materialize.css" type="text/css"
	rel="stylesheet" media="screen,projection">
<link href="${context}/resources/css/style.css" type="text/css"
	rel="stylesheet" media="screen,projection">
<script type="text/javascript"
	src="${context}/resources/js/plugins/data-tables/js/jquery.dataTables.min.js"></script>
<link
	href="${context}/resources/js/plugins/data-tables/css/jquery.dataTables.min.css"
	type="text/css" rel="stylesheet" media="screen,projection">
<link
	href="${context}/resources/js/plugins/data-tables/css/jquery.dataTables.css"
	type="text/css" rel="stylesheet" media="screen,projection">
<link href="${context}/resources/css/jquery-datepicker2.css"
	type="text/css" rel="stylesheet" media="screen,projection">
<!-- Custome CSS-->
<link href="${context}/resources/css/custom/custom.css" type="text/css"
	rel="stylesheet" media="screen,projection">
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
<link rel="stylesheet"
	href="${context}/resources/project_css/viewConsignment.css">
<link rel="stylesheet"
	href="${context}/resources/project_css/iconStates.css">
	<link rel="stylesheet"
	href="${context}/resources/css/grievance.css">

 <link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
 

</head>

<body data-roleType="${usertype}" data-userTypeID="${usertypeId}" data-userID="${userid}" data-selected-roleType="${selectedUserTypeId}" data-stolenselected-roleType="${stolenselectedUserTypeId}" data-grievanceTxnId="${grievanceTxnId}" data-grievanceId="${grievanceId}" data-grievanceStatus="${grievanceStatus}" >


	<!-- START CONTENT -->
	<!-- START CONTENT -->
	<section id="content">
		<div id="initialloader"></div>
		<!--start container-->
		<div class="container">
			<div class="section">
				<div class="row">
					<div class="col s12 m12 l12">
						<div class="row card-panel">
							<div class="container-fluid pageHeader" id="pageHeader">

								<a href="" class="boton right" id="btnLink"></a>
							</div>
							<form action="${context}/Grievance/grievanceManagement"
								method="post">
								<div class="col s12 m12 l12" id="greivanceTableDiv"
									style="padding-bottom: 5px; background-color: #e2edef52;">
									<div id="filterBtnDiv">
									</div>
										<div class="right" style="margin-top: 25px;"><a onclick="exportData()" type="button" class="boton right">Export to excel <i class="fa fa-file-excel-o" aria-hidden="true"></i></a></div>
							
								</div>
							</form>
							<table id="grivanceLibraryTable"
								class="responsive-table striped display"></table>

						</div>

					</div>
				</div>
				<div id="footerBtn"></div>
			</div>
		</div>
		<!--end container-->
	</section>
	
	<div id="replyModal" class="modal">
        <button class="modal-close btn-flat right" onclick="cleanReplyPopUp()">&times;</button>
             <h6 class="modal-header">Reply</h6>
             <div class="modal-content">
             <form id="replymessageForm" onsubmit="return saveGrievanceReply()" method="POST" enctype="multipart/form-data" >
            <div class="row">
                <div class="col s12 m12">
                    <h6 style="font-weight: bold;">Grievance ID: <span id="grievanceIdToSave"></span></h6>
                    <span id="grievanceTxnId" style="display: none;"></span>
                    <hr>
                </div>

                <div class="col s12 m12" id="viewPreviousMessage">
                   <!--  <h6 style="float: left; font-weight: bold;" id="mesageUserType"> </h6>
                    <h6 style="float: left;"></h6>
                        <span style="float:right;"></span> -->
                </div>
               
 
               <div class="col s12 m12">
                  <label for="replyRemark" style="margin-top: 7px">Remark <span class="star">*</span></label>
                    <textarea id="replyRemark" class="materialize-textarea" placeholder="" required="required"></textarea>
                    <!-- <h6 style="color: #000;">Upload Supporting Document </h6> -->
                </div>
               <!--   <div class="file-field col s12 m12">
                    <div class="btn"><span>Select File</span><input id="replyFile" type="file" accept=".csv" ></div>
                    <div class="file-path-wrapper"><input class="file-path validate" type="text"
                            placeholder="Upload one or more files">
                        <div>
                            <p id="myFiles"></p>
                        </div>
                    </div>
                </div> -->
                
 <div id="mainDiv" class="mainDiv">
<div id="filediv" class="fileDiv">
<div class="row">
<div class="file-field col s12 m6">
<h6 style="color: #000;">Upload Supporting Document</h6>
<div class="btn">
<span>Select File</span>
<input type="file" name="files[]" id="docTypeFile1"  multiple>
</div>
<div class="file-path-wrapper">
<input class="file-path validate" type="text" multiple
placeholder="Upload one or more files">
<div>
<p id="myFiles"></p>
</div>
</div>
</div>
<div class="col s12 m6 l6" style="margin-top: 8px;">
<label for="Category">Document Type <span class="star">*</span></label>
<select class="browser-default" id="docTypetag1" >
<option value="" disabled selected>Select Document Type </option>

</select>

</div>
</div>


</div>

</div>
<div class="col s12 m6 right">
<button class="btn right add_field_button"><span
style="font-size: 20px;">+</span> Add More files</button>
</div>
              <div class="col s12 m12">  <p>
              <p id="closeTicketCheckbox" style="float: left; display: none;">
                        <label>
                            <span>Do you want to close this ticket?</span>
                            <input type="checkbox" id="closeTicketCheck" />
                        </label>
                    </p> <br>
				<!-- <a href="./Consignment/sampleFileDownload/filetype=sample">Download Sample Format</a><br> -->
			

			<span> Required Field are marked with <span class="star">*</span></span>
			
                </div>
                <div class="col s12 m12 center">
                 <p id="closeTicketCheckbox" style="float: left; display: none;">
                        <label>
                            <span>Do you want to close this ticket?</span>
                            <input type="checkbox" id="closeTicketCheck" />
                        </label>
                    </p>
                    <button class="right btn" type="submit">Reply</button>
                </div>
            </div>
            </form>
        </div>
    </div>
    
    <div id="replyMsg" class="modal">
    <h6 class="modal-header">Grievance Reply</h6>
    <div class="modal-content">
        
        <div class="row">
            <h6 id="showReplyResponse">Your reply having grievance ID: <span id="replyGrievanceId"> </span> is successfully sent.</h6>
        </div>
        <div class="row">
            <div class="input-field col s12 center">
                <div class="input-field col s12 center">
                    <a href="./grievanceManagement" class="modal-close btn">ok</a>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="manageAccount" class="modal">
<button class="modal-close btn-flat right" data-dismiss="modal">&times;</button>
<h6 class="modal-header">Grievance history</h6>
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
	

	<!--materialize js-->
	<script type="text/javascript"
		src="${context}/resources/js/materialize.js"></script>


	<script type="text/javascript"
		src="${context}/resources/js/plugins/data-tables/js/jquery.dataTables.js"></script>
	<script type="text/javascript"
		src="${context}/resources/js/plugins/data-tables/js/jquery.dataTables.min.js"></script>

	

	<!--plugins.js - Some Specific JS codes for Plugin Settings-->
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

	<%--   <script type="text/javascript" src="${context}/resources/js/materialize-plugins/date_picker/picker.date.js"></script>
    <script type="text/javascript" src="${context}/resources/js/materialize-plugins/date_picker/picker.js"></script> --%>
	<!--custom-script.js - Add your own theme custom JS-->
	<script type="text/javascript" src="${context}/resources/js/plugins.js"></script>
	<script type="text/javascript"
		src="${context}/resources/js/Validator.js"></script>
	<!--prism
    <script type="text/javascript" src="${context}/resources/resources/js/prism/prism.js"></script>-->
	<!--scrollbar-->
	<script type="text/javascript"
		src="${context}/resources/js/plugins/perfect-scrollbar/perfect-scrollbar.min.js"></script>
	<!-- chartist -->
	<%-- <script type="text/javascript" src="${context}/resources/js/plugins/chartist-js/chartist.min.js"></script> --%>
	<script type="text/javascript"
		src="${context}/resources/js/countries.js"></script>
	<script type="text/javascript"
		src="${context}/resources/project_js/grievanceManagement.js"></script>
		
	
</body>
</html>