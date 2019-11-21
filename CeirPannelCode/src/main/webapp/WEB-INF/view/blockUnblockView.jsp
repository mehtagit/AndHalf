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

<script type="text/javascript"
	src="${context}/resources/js/plugins/jquery-1.11.2.min.js"></script>
<!--   <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css"></script>  
 -->

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
	href="${context}/resources/project_css/viewStock.css">
<%-- <link rel="stylesheet"
	href="${context}/resources/project_css/iconStates.css"> --%>

</head>
<body data-roleType="${usertype}" data-userID="${userid}"
	data-selected-roleType="${selectedUserTypeId}">



            <!-- START CONTENT -->
            <section id="content">
                <!--start container-->
                <div class="container">
                    <div class="section">
                        <div class="row">
                            <div class="col s12 m12 l12">
                                <div class="row card-panel">
                                    <div class="container-fluid pageHeader">
                                        <p class="PageHeading">Block/Unblock</p>
                                        <a href="./selectblockUnblockPage" class="boton right">Report Block/Unblock</a>
                                    </div>

                                    <div class="col s12 m12 l12" id="consignmentTableDIv"
                                        style="padding-bottom: 5px;background-color: #e2edef52;">

                                        <div class="col s6 m2 l2 responsiveDiv">

                                            <div id="startdatepicker" class="input-group date"
                                                data-date-format="yyyy-mm-dd">
                                                <label for="TotalPrice">Start date</label>
                                                <input type="date">
                                                <span class="input-group-addon" style="color:#ff4081"><i
                                                        class="fa fa-calendar" aria-hidden="true"
                                                        style="float: right; margin-top: -37px;"></i></span>
                                            </div>
                                        </div>
                                        <div class="col s6 m2 l2 responsiveDiv">

                                            <div id="enddatepicker" class="input-group date"
                                                data-date-format="yyyy-mm-dd">
                                                <label for="TotalPrice">End date</label>
                                                <input class="form-control" type="date" id="datepicker" />
                                                <span class="input-group-addon" style="color:#ff4081"><i
                                                        class="fa fa-calendar" aria-hidden="true"
                                                        style="float: right; margin-top: -37px;"></i></span>
                                            </div>
                                        </div>

                                        <div class="col s6 m2 l2 selectDropdwn">
                                            <br />
                                            <!-- <label for="TotalPrice">Select Request type</label> -->
                                            <select id="taxPaidStatus" class="browser-default">
                                                <option value="" disabled selected>Request type</option>
                                                <option value="Stln">Block</option>
                                                <option value="recovery">Unblock</option>
                                            </select>

                                        </div>

                                        <div class="col s6 m2 l2 selectDropdwn">
                                            <br />
                                            <!-- <label for="TotalPrice">Select File Status</label> -->
                                            <select id="filterFileStatus" class="browser-default">
                                                <option value="" disabled selected>Status</option>
                                                <option value="Success">Success</option>
                                                <option value="Error">Error</option>
                                                <option value="Processing">Processing</option>
                                                <option value="INIT">INIT</option>
                                            </select>

                                        </div>

                                        <div class="col s12 m2 l2">
                                            <button type="button" class="btn primary botton"
                                                id="submitFilter">Filter</button>
                                        </div>

                                    </div>
                                    <table class="responsive-table striped display" id="blockUnblockDataTable"
                                        cellspacing="0">
                                        <thead>
                                            <tr>
                                                <th>Date</th>
                                                <th>Transaction ID</th>
                                                <th>Request Type</th>
                                                <th>Mode</th>
                                                <th>File Name</th>
                                                <th>Status</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody id="consignmentTableLibraryTbody">
                                            <tr>
                                                <td>10/05/2019</td>
                                                <td>Tr12345678</td>
                                                <td>Block</td>
                                                <td>Single</td>
                                                <td></td>
                                                <td>Success</td>
                                                <td>
                                                    <a href="#"><i class="fa fa-exclamation-circle error-disable-icon"
                                                            title="ErrorFile"></i></a>
                                                    <a href="#" download="download"><i
                                                            class="fa fa-download download-disable-icon"
                                                            aria-hidden="true" title="download"></i></a>
                                                    <a href="#editDevice" class="modal-trigger"><i
                                                            class="fa fa-pencil edit-icon" aria-hidden="true"
                                                            title="edit"></i></a>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>10/05/2019</td>
                                                <td>Tr12345678</td>
                                                <td>Unblock</td>
                                                <td>Single</td>
                                                <td></td>
                                                <td>Success</td>
                                                <td>
                                                    <a href="#"><i class="fa fa-exclamation-circle error-disable-icon"
                                                            title="ErrorFile"></i></a>
                                                    <a href="#" download="download"><i
                                                            class="fa fa-download download-disable-icon"
                                                            aria-hidden="true" title="download"
                                                            download="download"></i></a>
                                                    <a href="#editDevice" class="modal-trigger"><i
                                                            class="fa fa-pencil edit-icon" aria-hidden="true"
                                                            title="edit"></i></a>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>10/05/2019</td>
                                                <td>Tr12345678</td>
                                                <td>Block</td>
                                                <td>Bulk</td>
                                                <td>fileName.csv</td>
                                                <td>INIT</td>
                                                <td>
                                                    <a href="#"><i class="fa fa-exclamation-circle error-disable-icon"
                                                            title="ErrorFile"></i></a>
                                                    <a href="#" download="download"><i
                                                            class="fa fa-download download-icon" aria-hidden="true"
                                                            title="download" download="download"></i></a>
                                                    <a href="#editBlockUnblock" class="modal-trigger"><i
                                                            class="fa fa-pencil edit-icon" aria-hidden="true"
                                                            title="edit"></i></a>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>10/05/2019</td>
                                                <td>Tr12345678</td>
                                                <td>Unblock</td>
                                                <td>Bulk</td>
                                                <td>fileName.csv</td>
                                                <td>Error</td>
                                                <td>
                                                    <a href="#"><i class="fa fa-exclamation-circle error-icon"
                                                            title="ErrorFile"></i></a>
                                                    <a href="#" download="download"><i
                                                            class="fa fa-download download-icon" aria-hidden="true"
                                                            title="download" download="download"></i></a>
                                                    <a href="#"><i class="fa fa-pencil edit-disable-icon"
                                                            aria-hidden="true" title="edit"></i></a>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>10/05/2019</td>
                                                <td>Tr12345678</td>
                                                <td>Unblock</td>
                                                <td>Bulk</td>
                                                <td>fileName.csv</td>
                                                <td>Processing</td>
                                                <td>
                                                    <a href="#"><i class="fa fa-exclamation-circle error-disable-icon"
                                                            title="ErrorFile"></i></a>
                                                    <a href="#" download="download"><i
                                                            class="fa fa-download download-icon" aria-hidden="true"
                                                            title="download" download="download"></i></a>
                                                    <a href="#editBlockUnblock" class="modal-trigger"><i
                                                            class="fa fa-pencil edit-icon" aria-hidden="true"
                                                            title="edit"></i></a>
                                                </td>
                                            </tr>

                                        </tbody>
                                    </table>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
                <!--end container-->
            </section>
            
            
<!--materialize js-->
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
	<script type="text/javascript"
		src="${context}/resources/js/materialize.js"></script>


	<script type="text/javascript"
		src="${context}/resources/js/plugins/data-tables/js/jquery.dataTables.js"></script>
	<script type="text/javascript"
		src="${context}/resources/js/plugins/data-tables/js/jquery.dataTables.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.3/moment.min.js"></script>

	<!--plugins.js - Some Specific JS codes for Plugin Settings-->
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

	<%--   <script type="text/javascript" src="${context}/resources/js/materialize-plugins/date_picker/picker.date.js"></script>
    <script type="text/javascript" src="${context}/resources/js/materialize-plugins/date_picker/picker.js"></script> --%>
	<!--custom-script.js - Add your own theme custom JS-->
	<script type="text/javascript" src="${context}/resources/js/plugins.js"></script>
	<!--prism
    <script type="text/javascript" src="${context}/resources/resources/js/prism/prism.js"></script>-->
	<!--scrollbar-->
	<script type="text/javascript"
		src="${context}/resources/js/plugins/perfect-scrollbar/perfect-scrollbar.min.js"></script>
	<!-- chartist -->
	<%-- <script type="text/javascript" src="${context}/resources/js/plugins/chartist-js/chartist.min.js"></script> --%>
	<script type="text/javascript"
		src="${context}/resources/js/countries.js"></script>

  <script type="text/javascript">
            $('#blockUnblockDataTable').DataTable(
            		{
            			"bLengthChange": false
            		});
            
          </script>

</body>
</html>