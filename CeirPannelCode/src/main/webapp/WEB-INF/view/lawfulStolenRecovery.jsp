<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<title>Lawful</title>

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
	href="${context}/resources/project_css/stolenRecovery.css">
<link rel="stylesheet"
	href="${context}/resources/project_css/iconStates.css">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
<style type="text/css">
 <style>
        .row {
            margin-bottom: 0;
            margin-top: 0;
        }

        input[type=text] {
            height: 35px;
            margin: 0 0 5px 0;
        }

        .checkboxFont {
            font-size: 16px;
            margin-right: 10px;
            color: #444;
        }

        textarea.materialize-textarea {
            padding: 0;
            padding-left: 10px;
        }

        .btn-flat {
            height: auto;
        }

        .dropdown-content li>a,
        .dropdown-content li>span {
            color: #444;
        }

        .welcomeMsg {
            padding-bottom: 50px !important;
            line-height: 1.5 !important;
            text-align: center;
        }

        input[type='search'] {
            background-image: url(images/search-512.jpg);
            background-position: 97% 7px;
            background-repeat: no-repeat;
            color: #444;
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
                                        <p class="PageHeading">Stolen/Recovery</p>
                                        <button type="button" class="waves-effect waves-light modal-trigger boton right"
                                            data-target="modal1">Report Stolen/Recovery</button>
                                    </div>

                                    <div class="col s12 m12" id="distributorTableDIv"
                                        style="padding-bottom: 5px;background-color: #e2edef52;">


                                        <div class="col s6 m2 responsiveDiv">
                                            <label for="TotalPrice">Start date</label>
                                            <div id="startdatepicker" class="input-group date"
                                                data-date-format="yyyy-mm-dd" style="    margin-top: 10px;">
                                                <input class="form-control" type="date" id="datepicker"
                                                    style="margin-top: -9px" />
                                                <span class="input-group-addon" style="color:#ff4081"><i
                                                        class="fa fa-calendar" aria-hidden="true"
                                                        style="float: right; margin-top: -30px;"></i></span>
                                            </div>

                                        </div>
                                        <div class="col s6 m2 responsiveDiv">
                                            <label for="TotalPrice">End date</label>
                                            <div id="enddatepicker" class="input-group date"
                                                data-date-format="yyyy-mm-dd" style="    margin-top: 10px;">

                                                <input class="form-control" id="endDateFilter" type="date"
                                                    style="margin-top: -9px" />
                                                <span class="input-group-addon" style="color:#ff4081"><i
                                                        class="fa fa-calendar" aria-hidden="true"
                                                        style="float: right; margin-top: -30px;"></i></span>
                                            </div>
                                        </div>

                                        <div class="input-field col s6 m2" style="margin-top: 29px;">
                                            <input type="text" name="transactionID" id="transactionID" maxlength="20">
                                            <label for="transactionID">Transaction ID </label>
                                        </div>

                                        <div class="col s6 m2 selectDropdwn" style="margin-top: 3px;">
                                            <br />
                                            <!-- <label for="TotalPrice">Select File Status</label> -->
                                            <select id="filterFileStatus" class=" browser-default">
                                                <option value="" disabled selected>Status</option>
                                                <option value="Success">Success</option>
                                                <option value="Error">Error</option>
                                                <option value="Processing">Processing</option>
                                                <option value="INIT">INIT</option>
                                            </select>
                                        </div>

                                        <!-- <div class="col s6 m2 selectDropdwn" style="margin-top: 3px;">
                                            <br />
                                            <!-- <label for="TotalPrice">Select File Status</label> 
                                            <select id="filterFileStatus" class=" browser-default">
                                                <option value="" disabled selected>Source</option>
                                                <option value="Success">Consignment</option>
                                                <option value="Error">Stock</option>
                                                <option value="Processing">File upload</option>
                                            </select>
                                        </div> -->


                                        <div class="col s6 m2 selectDropdwn" style="margin-top: 3px;">
                                            <br />
                                            <!-- <label for="TotalPrice">Select Request type</label> -->
                                            <select id="taxPaidStatus" class="browser-default">
                                                <option value="" disabled selected>Request type</option>
                                                <option value="Stln">Stolen</option>
                                                <option value="recovery">Recovery</option>
                                            </select>

                                        </div>

                                        <div class="col s12 m2">
                                            <button type="button" class="btn primary botton"
                                                id="submitFilter">Filter</button>
                                            <a href="JavaScript:void(0);" type="button"
                                                class="export-to-excel right">Export <i class="fa fa-file-excel-o"
                                                    aria-hidden="true"></i></a>
                                        </div>

                                    </div>
                                    <table class="responsive-table striped display" id="data-table-simple"
                                        cellspacing="0">
                                        <thead>
                                            <tr>
                                                <th>Request Date</th>
                                                <th>Transaction ID</th>
                                                <th>Contact Number</th>
                                                <th>Block Type</th>
                                                <th>Request type</th>
                                                <th>Mode</th>
                                                <th>Status</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody id="consignmentTableLibraryTbody">
                                            <tr>
                                                <td>20/10/2018</td>
                                                <td>Tr12345678</td>
                                                <td>8745639285</td>
                                                <td>Immediate</td>
                                                <td>Stolen</td>
                                                <td>Individual</td>
                                                <td>Processing</td>
                                                <td style="width: 180px;">
                                                    <a href="#ErrorFile" download="download"><i
                                                            class="fa fa-exclamation-circle error-icon"
                                                            title="ErrorFile"></i></a>
                                                    <a href="#" download="download"><i
                                                            class="fa fa-download download-icon" aria-hidden="true"
                                                            title="download"></i></a>
                                                    <a href="viewStolen.html"><i class="fa fa-eye teal-text view-icon"
                                                            aria-hidden="true" title="view"></i></a>
                                                    <a href="editStolen.html"><i class="fa fa-pencil edit-icon"
                                                            aria-hidden="true" title="edit"></i></a>
                                                    <a href="" class="waves-effect waves-light "><i
                                                            class="fa fa-trash delete-icon" aria-hidden="true"
                                                            title="delete"></i></a>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>20/10/2018</td>
                                                <td>Tr12345678</td>
                                                <td>8745639285</td>
                                                <td>Default</td>
                                                <td>Recovery</td>
                                                <td>Single</td>
                                                <td>INIT</td>
                                                <td>
                                                    <a href="#ErrorFile" download="download"><i
                                                            class="fa fa-exclamation-circle error-icon"
                                                            title="ErrorFile"></i></a>
                                                    <a href="#" download="download"><i
                                                            class="fa fa-download download-icon" aria-hidden="true"
                                                            title="download"></i></a>
                                                    <a href="viewRecoerySingle.html"><i
                                                            class="fa fa-eye teal-text view-icon" aria-hidden="true"
                                                            title="view"></i></a>
                                                    <a href="EditRecoverySingle.html"><i class="fa fa-pencil edit-icon"
                                                            aria-hidden="true" title="edit"></i></a>
                                                    <a href="" class="waves-effect waves-light"><i
                                                            class="fa fa-trash delete-icon" aria-hidden="true"
                                                            title="delete"></i></a>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>20/10/2018</td>
                                                <td>Tr12345678</td>
                                                <td>8745639285</td>
                                                <td>Later</td>
                                                <td>Stolen</td>
                                                <td>Company</td>
                                                <td>Error</td>
                                                <td>
                                                    <a href="#ErrorFile" download="download"><i
                                                            class="fa fa-exclamation-circle error-icon"
                                                            title="ErrorFile"></i></a>
                                                    <a href="#" download="download"><i
                                                            class="fa fa-download download-icon" aria-hidden="true"
                                                            title="download"></i></a>
                                                    <a href="viewStolenCompany.html"><i
                                                            class="fa fa-eye teal-text view-icon" aria-hidden="true"
                                                            title="view"></i></a>
                                                    <a href="editStolenCompany.html"><i class="fa fa-pencil edit-icon"
                                                            aria-hidden="true" title="edit"></i></a>
                                                    <a href="" class="waves-effect waves-light"><i
                                                            class="fa fa-trash delete-icon" aria-hidden="true"
                                                            title="delete"></i></a>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>20/10/2018</td>
                                                <td>Tr12345678</td>
                                                <td>8745639285</td>
                                                <td>Immediate</td>
                                                <td>Stolen</td>
                                                <td>Individual</td>
                                                <td>Success</td>
                                                <td>
                                                    <a href="#ErrorFile" download="download"><i
                                                            class="fa fa-exclamation-circle error-icon"
                                                            title="ErrorFile"></i></a>
                                                    <a href="#" download="download"><i
                                                            class="fa fa-download download-icon" aria-hidden="true"
                                                            title="download"></i></a>
                                                    <a href="viewStolen.html"><i class="fa fa-eye teal-text view-icon"
                                                            aria-hidden="true" title="view"></i></a>
                                                    <a href="editStolen.html"><i class="fa fa-pencil edit-icon"
                                                            aria-hidden="true" title="edit"></i></a>
                                                    <a href="" class="waves-effect waves-light"><i
                                                            class="fa fa-trash delete-icon" aria-hidden="true"
                                                            title="delete"></i></a>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>20/10/2018</td>
                                                <td>Tr12345678</td>
                                                <td>8745639285</td>
                                                <td>Default</td>
                                                <td>Recovery</td>
                                                <td>Company</td>
                                                <td>Success</td>
                                                <td>
                                                    <a href="#ErrorFile" download="download"><i
                                                            class="fa fa-exclamation-circle error-icon"
                                                            title="ErrorFile"></i></a>
                                                    <a href="#" download="download" 
                                                        i class="fa fa-download download-icon" aria-hidden="true"
                                                        title="download"></i></a>
                                                    <a href="viewRecoveryBulk.html"><i
                                                            class="fa fa-eye teal-text view-icon" aria-hidden="true"
                                                            title="view"></i></a>
                                                    <a href="editRecoveryBulk.html"><i class="fa fa-pencil edit-icon"
                                                            aria-hidden="true" title="edit"></i></a>
                                                    <a href="" class="waves-effect waves-light"><i
                                                            class="fa fa-trash delete-icon" aria-hidden="true"
                                                            title="delete"></i></a>
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

</body>
</html>