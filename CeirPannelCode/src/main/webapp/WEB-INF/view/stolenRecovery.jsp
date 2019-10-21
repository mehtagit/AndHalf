<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="msapplication-tap-highlight" content="no">
<meta name="description"
	content="Materialize is a Material Design Admin Template,It's modern, responsive and based on Material Design by Google. ">
<meta name="keywords"
	content="materialize, admin template, dashboard template, flat admin template, responsive admin template,">
<title>CEIR | Importer Portal</title>

	<script type="text/javascript"
	src="${context}/resourcesCss/js/plugins/jquery-1.11.2.min.js"></script>
	
<link
	href="${context}/resourcesCss/js/plugins/data-tables/css/jquery.dataTables.min.css"
	type="text/css" rel="stylesheet" media="screen,projection">

<!-- Favicons-->
<!--<link rel="icon" href="images/favicon/favicon-32x32.png" sizes="32x32">-->
<!-- Favicons-->
<link rel="apple-touch-icon-precomposed"
	href="${context}/resourcesCss/images/favicon/apple-touch-icon-152x152.png">
<!-- For iPhone -->
<meta name="msapplication-TileColor" content="#00bcd4">
<meta name="msapplication-TileImage"
	content="${context}/resourcesCss/images/favicon/mstile-144x144.png">
<!-- For Windows Phone -->
<link rel="stylesheet"
	href="${context}/resourcesCss/font/font-awesome/css/font-awesome.min.css">

<!-- CORE CSS-->
<link href="${context}/resourcesCss/css/materialize.css" type="text/css"
	rel="stylesheet" media="screen,projection">
<link href="${context}/resourcesCss/css/style.css" type="text/css"
	rel="stylesheet" media="screen,projection">
<!-- Custome CSS-->
<link href="${context}/resourcesCss/css/custom/custom.css"
	type="text/css" rel="stylesheet" media="screen,projection">
<!-- jQuery Library -->

<!-- INCLUDED PLUGIN CSS ON THIS PAGE -->
<link href="${context}/resourcesCss/js/plugins/prism/prism.css"
	type="text/css" rel="stylesheet" media="screen,projection">
<link
	href="${context}/resourcesCss/js/plugins/perfect-scrollbar/perfect-scrollbar.css"
	type="text/css" rel="stylesheet" media="screen,projection">
<link
	href="${context}/resourcesCss/js/plugins/chartist-js/chartist.min.css"
	type="text/css" rel="stylesheet" media="screen,projection">


<style>
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
	margin-top: 10px;
}

/* @media only screen and (min-width: 601px) .row .col.m6 {
            margin-top: 0;
            margin-bottom: 0;
            height: 40px;
        } */
input[type=text] {
	height: 35px;
	margin: 0 0 5px 0;
}

.active {
	background-color: #e9e9e9;
}

[type="date"]::-webkit-inner-spin-button {
	display: none;
}

[type="date"]::-webkit-calendar-picker-indicator {
	opacity: 0;
}

/* label {
            font-size: 18px;
            color: #9e9e9e;
        } */
</style>

</head>

<body>
	<div class="section">
		<div class="row">
			<div class="col s12 m12 l12">
				<div class="row card-panel">
					<div class="container-fluid"
						style="height: 50px; background-color: #529dba; margin: -20px -20px 0 -20px; padding: 10px;">
						<button type="button"
							class="waves-effect waves-light modal-trigger boton right"
							data-target="modal1">Report Stolen/Recovery</button>
					</div>

					<div class="col s12 m12 l12" id="distributorTableDIv"
						style="padding-bottom: 5px; background-color: #e2edef52;">


						<div class="col s12 m2 l2" style="width: 20%;padding-right: 10px">
							<br /> <label for="TotalPrice">Start date</label>
							<div id="startdatepicker" class="input-group date"
								data-date-format="yyyy-mm-dd" style="margin-top: 10px;">
								<input class="form-control" type="date" id="datepicker"
									style="margin-top: -9px" /> <span class="input-group-addon"
									style="color: #ff4081"><i class="fa fa-calendar"
									aria-hidden="true" style="float: right; margin-top: -35px;"></i></span>
							</div>

						</div>
						<div class="col s12 m2 l2" style="width: 20%;padding-right: 10px">
							<br /> <label for="TotalPrice">End date</label>
							<div id="enddatepicker" class="input-group date"
								data-date-format="yyyy-mm-dd" style="margin-top: 10px;">

								<input class="form-control" id="endDateFilter" type="date"
									style="margin-top: -9px" /> <span class="input-group-addon"
									style="color: #ff4081"><i class="fa fa-calendar"
									aria-hidden="true" style="float: right; margin-top: -35px;"></i></span>
							</div>
						</div>


						<div class="col s12 m2 l2" style="width: 20%;margin-top: -7px;padding-right: 10px">
							<br /> <label for="TotalPrice">Select Request type</label> <select
								id="taxPaidStatus"
								class="">
								<option value="" disabled selected></option>
								<option value="Stln">Stolen</option>
								<option value="recovery">Recovery</option>
							</select>

						</div>

						<div class="col s12 m2 l2" style="width: 20%;margin-top: -7px;padding-right: 10px">
							<br /> <label for="TotalPrice">Select File Status</label>
							 <select id="filterFileStatus" >
								<option value="" disabled selected></option>
								<option value="Success">Success</option>
								<option value="Error">Error</option>
								<option value="Processing">Processing</option>

							</select>

						</div>
						<div class="col s12 m2 l2" style="width: 20%">
							<button type="button"
								style="margin-top: 50px; margin-left: 15px; height: 32px; width: 72;"
								class="btn primary" id="submitFilter"
								onclick="_Services._submitStolenRecoveryFilterData()">Filter</button>
						</div>

					</div>
					<table class="responsive-table striped display"
						id="stolenRecoveryTableId" cellspacing="0">
						<thead>
							<tr>
								<th>Date</th>
								<th>File Name</th>
								<th>File Status</th>
								<th>Request type</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody id="consignmentTableLibraryTbody">
							<tr>
								<td>20/10/2018</td>
								<td>kjskjjhck</td>
								<td>kjnckd654654</td>
								<td>dskhcbjds</td>
								<td>
<a href="#ErrorFile"><i class="fa fa-exclamation-circle" aria-hidden="true" title="ErrorFile"
style="pointer-events:auto;color: red; font-size:20px; margin-right:15px;"></i></a>
<a href="javascript:void(0)"><i class="fa fa-download " aria-hidden="true"
style="font-size: 20px; color:#2e8b57" title="download"></i></a>
<a href="#EditConsignment"><i class="fa fa-pencil" aria-hidden="true"
style="font-size: 20px; margin:0 15px 0 15px; color: #006994" title="edit"></i></a>
<a href="#DeleteConsignment"><i class="fa fa-trash" aria-hidden="true"
style="font-size: 20px; color: red;" title="delete"></i></a>
</td>
							</tr>

							<tr>
								<td>20/10/2018</td>
								<td>kjskjjhck</td>
								<td>kjnckd654654</td>
								<td>dskhcbjds</td>
							<td>
<a href="#ErrorFile"><i class="fa fa-exclamation-circle" aria-hidden="true" title="ErrorFile"
style="pointer-events:auto;color: red; font-size:20px; margin-right:15px;"></i></a>
<a href="javascript:void(0)"><i class="fa fa-download " aria-hidden="true"
style="font-size: 20px; color:#2e8b57" title="download"></i></a>
<a href="#EditConsignment"><i class="fa fa-pencil" aria-hidden="true"
style="font-size: 20px; margin:0 15px 0 15px; color: #006994" title="edit"></i></a>
<a href="#DeleteConsignment"><i class="fa fa-trash" aria-hidden="true"
style="font-size: 20px; color: red;" title="delete"></i></a>
</td>
							</tr>

							<tr>
								<td>20/10/2018</td>
								<td>kjskjjhck</td>
								<td>kjnckd654654</td>
								<td>dskhcbjds</td>
								<td>
<a href="#ErrorFile"><i class="fa fa-exclamation-circle" aria-hidden="true" title="ErrorFile"
style="pointer-events:auto;color: red; font-size:20px; margin-right:15px;"></i></a>
<a href="javascript:void(0)"><i class="fa fa-download " aria-hidden="true"
style="font-size: 20px; color:#2e8b57" title="download"></i></a>
<a href="#EditConsignment"><i class="fa fa-pencil" aria-hidden="true"
style="font-size: 20px; margin:0 15px 0 15px; color: #006994" title="edit"></i></a>
<a href="#DeleteConsignment"><i class="fa fa-trash" aria-hidden="true"
style="font-size: 20px; color: red;" title="delete"></i></a>
</td>
							</tr>

							<tr>
								<td>20/10/2018</td>
								<td>kjskjjhck</td>
								<td>kjnckd654654</td>
								<td>dskhcbjds</td>
								<td>
<a href="#ErrorFile"><i class="fa fa-exclamation-circle" aria-hidden="true" title="ErrorFile"
style="pointer-events:auto;color: red; font-size:20px; margin-right:15px;"></i></a>
<a href="javascript:void(0)"><i class="fa fa-download " aria-hidden="true"
style="font-size: 20px; color:#2e8b57" title="download"></i></a>
<a href="#EditConsignment"><i class="fa fa-pencil" aria-hidden="true"
style="font-size: 20px; margin:0 15px 0 15px; color: #006994" title="edit"></i></a>
<a href="#DeleteConsignment"><i class="fa fa-trash" aria-hidden="true"
style="font-size: 20px; color: red;" title="delete"></i></a>
</td>
							</tr>

							<tr>
								<td>20/10/2018</td>
								<td>kjskjjhck</td>
								<td>kjnckd654654</td>
								<td>dskhcbjds</td>
								<td>
<a href="#ErrorFile"><i class="fa fa-exclamation-circle" aria-hidden="true" title="ErrorFile"
style="pointer-events:auto;color: red; font-size:20px; margin-right:15px;"></i></a>
<a href="javascript:void(0)"><i class="fa fa-download " aria-hidden="true"
style="font-size: 20px; color:#2e8b57" title="download"></i></a>
<a href="#EditConsignment"><i class="fa fa-pencil" aria-hidden="true"
style="font-size: 20px; margin:0 15px 0 15px; color: #006994" title="edit"></i></a>
<a href="#DeleteConsignment"><i class="fa fa-trash" aria-hidden="true"
style="font-size: 20px; color: red;" title="delete"></i></a>
</td>
							</tr>

						</tbody>
					</table>
				</div>

			</div>
		</div>


		<!-- END CONTENT -->

		<!-- //////////////////////////////////////////////////////////////////////////// -->


	</div>
	<!-- END WRAPPER -->


	<!-- END MAIN -->



	<!-- //////////////////////////////////////////////////////////////////////////// -->



	<!-- Modal 1 start   -->

	<div id="modal1" class="modal">
		<button type="button"
			class=" modal-action modal-close waves-effect waves-green btn-flat right"
			data-dismiss="modal">&times;</button>
		<div class="modal-content">

			<h6>Report Stolen/Recovery</h6>
			<hr>
			<!-- <h4 class="header2 pb-2">User Info</h4> -->

			<h5 class="center">Report Stolen/Recovery</h5>
			<div class="row"
				style="margin-left: 32%; height: 50px; padding-top: 17px; display: inline-flex;">

				<label style="margin-right: 50px"> <input type="radio"
					onclick="document.getElementById('stolendiv').style.display = 'block';document.getElementById('recoveryDiv').style.display = 'none';"
					name="stolen"><span> Device Stolen</span></label> <label> <input
					type="radio"
					onclick="document.getElementById('recoveryDiv').style.display = 'block'; document.getElementById('stolendiv').style.display = 'none';"
					name="stolen"><span> Device Recovery</span></label>
			</div>

			<div id="stolendiv" style="display: none">

				<h5 id="recoveryHeading" style="margin-left: 0px; font-size: 22px;">
					Device Stolen</h5>
				<div class="row">
					<h6 style="color: #000; margin-left: 10px;">Upload Bulk
						Devices Information*</h6>

					<div class="file-field input-field col s12 m6">
						<div class="btn" style="height: 35px; line-height: 2.5rem;">
							<span>Select File</span> <input type="file" id="csvUploadFile"
								accept=".csv">
						</div>
						<div class="file-path-wrapper">
							<input class="file-path validate" type="text">
						</div>
					</div>



					<div class="col s12 m6 l6" id="SavedFileNameDiv"
						style="display: none">
						<label for="TotalPrice" class="center-align">Uploaded File</label>
						<input type="text" name=""
							class="form-control boxBorder boxHeight" readonly
							id="SavedFileName" />
					</div>
				</div>
				<a href="#" onclick="_Services._downloadStolenSamplefile()">Download
					Sample Format</a><br> <br>

				<div class="row" id="samplefileDiv3"
					style="display: none; margin-left: 05px;">
					<div style="display: inline-flex">
						<a href="#" id="simDevice3">IMEI dual SIM device entry</a><br>
						<br> <a href="#" style="margin-left: 75px;" id="rangeDevice3">IMEI
							Range Device entry</a><br> <br>
					</div>
				</div>
				<div style="margin-left: 36%; margin-top: -25px;">
					<label style="margin-right: 2%;"> <input type="radio"
						id="blockPeriod" value="Immediate" onclick="" name="blockPeriod"
						checked> Immediate
					</label> <label style="margin-right: 2%;"> <input type="radio"
						id="blockPeriod" value="Default" onclick="" name="blockPeriod">
						Default
					</label> <label> <input type="radio" id="blockPeriod"
						value="tilldate" onclick="" name="blockPeriod"> Later
					</label>

					<div class="col s12 m2 l2" style="width: 40%; display: none"
						id="stolenDate">

						<!-- <label for="TotalPrice" class="center-align">Start date</label> -->
						<label for="TotalPrice" class="center-align">Till date</label>
						<div id="startdatepicker" class="input-group date"
							data-date-format="yyyy-mm-dd" style="margin-top: 10px;">

							<input class="form-control" name="inputsaves" type="text"
								id="startDateFilter" readonly /> <span
								class="input-group-addon" style="color: #ff4081"><i
								class="glyphicon glyphicon-calendar"
								onclick="_Services._selectstartDate()"></i></span>
						</div>

					</div>
					<!-- <label> <input type="radio" value="Immediate"  onclick="" name="blockPeriod"> Days</label> -->
				</div>
				<span> Required Field are marked with *</span>

				<div class="row">
					<div class="input-field col s12 center">
						<button class="btn" type="button" id="btnSave" name="save"
							id="save" onclick="_Services._completeConsignmentStolen()">Submit</button>

						<button class="btn modal-action modal-close waves-effect"
							type="reset" name="cancel" id="cancel">Cancel</button>
					</div>
				</div>
			</div>






			<div id="recoveryDiv" style="display: none">
				<h5 id="recoveryHeading" style="font-size: 22px; margin-left: 0px;">
					Device Recovery</h5>
				<div class="row">
					<h6 style="color: #000; margin-left: 10px;">Upload Bulk
						Devices Information*</h6>

					<div class="file-field input-field col s12 m6">
						<div class="btn" style="height: 35px; line-height: 2.5rem;">
							<span>Select File</span> <input type="file" id="csvUploadFile"
								accept=".csv">
						</div>
						<div class="file-path-wrapper">
							<input class="file-path validate" type="text">
						</div>
					</div>

					<div class="col s12 m6 l6" id="SavedFileNameDiv"
						style="display: none">
						<label for="TotalPrice" class="center-align">Uploaded File</label>
						<input type="text" name=""
							class="form-control boxBorder boxHeight" readonly
							id="SavedFileName" />
					</div>
				</div>
				<a href="#" onclick="_Services._downloadStolenSamplefile()">Download
					Sample Format</a><br> <br>

				<div class="row" id="samplefileDiv12"
					style="display: none; margin-left: 05px;">
					<div style="display: inline-flex">
						<a href="#" id="simDevice12">IMEI dual SIM device entry</a><br>
						<br> <a href="#" style="margin-left: 75px;"
							id="rangeDevice12">IMEI Range Device entry</a><br> <br>
					</div>
				</div>
				<span> Required Field are marked with *</span>

				<div class="row">
					<div class="input-field col s12 center">
						<button class="btn" type="button" id="btnSave" name="save"
							id="save" onclick="">Submit</button>

						<button class="btn modal-action modal-close waves-effect"
							type="reset" name="cancel" id="cancel">Cancel</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal End -->


	<!-- ================================================
    Scripts
    ================================================ -->
<!-- 	<script type="text/javascript">
        $(document).ready(function () {
            $('.modal').modal();
        });
        
    
    
        </script> -->



	<!-- <script type="text/javascript">
        $(function () {
            $('#datetimepicker1').datetimepicker();
        });
    </script> -->

	<!--materialize js-->



<%-- <script type="text/javascript"
	src="${context}/resourcesCss/js/plugins/data-tables/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript"
		src="${context}/resourcesCss/js/materialize.js"></script>
	<!--prism
    <script type="text/javascript" src="js/prism/prism.js"></script>-->
	<!--scrollbar-->
	<script type="text/javascript"
		src="${context}/resourcesCss/js/plugins/perfect-scrollbar/perfect-scrollbar.min.js"></script>
	<!-- chartist -->
	

	<!-- data-tables -->
	<script type="text/javascript"
		src="${context}/resourcesCss/js/plugins/data-tables/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript"
		src="${context}/resourcesCss/js/plugins/data-tables/data-tables-script.js"></script>

	<!--plugins.js - Some Specific JS codes for Plugin Settings-->
	<script type="text/javascript"
		src="${context}/resourcesCss/js/plugins.js"></script>
	<!--custom-script.js - Add your own theme custom JS--> --%>
	
	 
    <!--materialize js-->
    <script type="text/javascript" src="${context}/resourcesCss/js/materialize.js"></script>
    <!--prism
    <script type="text/javascript" src="${context}/resourcesCss/resourcesCss/js/prism/prism.js"></script>-->
    <!--scrollbar-->
    <script type="text/javascript" src="${context}/resourcesCss/js/plugins/perfect-scrollbar/perfect-scrollbar.min.js"></script>
    <!-- chartist -->
   <%--  <script type="text/javascript" src="${context}/resourcesCss/js/plugins/chartist-js/chartist.min.js"></script>   
     --%>
    <!--plugins.js - Some Specific JS codes for Plugin Settings-->
    <script type="text/javascript" src="${context}/resourcesCss/js/plugins.js"></script>
    <!--custom-script.js - Add your own theme custom JS-->
    <script type="text/javascript" src="${context}/resourcesCss/js/custom-script.js"></script>
      
      <script type="text/javascript" src="${context}/resourcesCss/js/plugins/data-tables/js/jquery.dataTables.js"></script>
       
    
	
	
	<script type="text/javascript"
		src="${context}/resourcesCss/js/custom-script.js"></script>
		
			<script type="text/javascript">

			$('#stolenRecoveryTableId').DataTable(
		{
			"bLengthChange": false
		});
</script>
<body>

</body>
</html>