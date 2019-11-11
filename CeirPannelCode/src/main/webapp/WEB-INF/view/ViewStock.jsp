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

        input[type=text] {
            height: 35px;
            margin: 0 0 5px 0;
        }

        .checkboxFont {
            font-size: 16px;
            margin-right: 10px;
        }

        textarea.materialize-textarea {
            padding: 0;
            padding-left: 10px;
        }

        .btn-flat {
            height: auto;
        }

        .dropdown-content li>a, .dropdown-content li>span {
            color: #444;
        }
        .welcomeMsg {
          padding-bottom: 50px !important;
          line-height: 1.5 !important;
          text-align: center;
        }
    </style>
</head>
<body>	
  <!-- START MAIN -->
 

 <section id="content">
                <!--start container-->
                <div class="container">
                    <div class="section">
                        <div class="row">
                            <div class="col s12 m12 l12">
                                <div class="row card-panel">
                                        <div class="container-fluid pageHeader">
                                            <p class="PageHeading">View Stock</p>
                                            <a href="${context}/openUploadStock?reqType=formPage" type="button" class="boton right">Upload
                                                Stock</a>
                                        </div>

                                        <div class="col s12 m12 l12" id="distributorTableDIv"
                                            style="padding-bottom: 5px;background-color: #e2edef52;">

                                            <div class="col s6 m3 l3 responsiveDiv">
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
                                            <div class="col s6 m3 l3 responsiveDiv">
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



                                            <div class="col s6 m3 l3 selectDropdwn" style="margin-top: 3px;">
                                                <br />
                                                <!-- <label for="TotalPrice" class="center-align">Stock Status</label> -->
                                                <select id="filterFileStatus"
                                                    class="browser-default">
                                                    <option value="" disabled selected>Stock Status</option>
                                                    <option value="Success">Success</option>
                                                    <option value="Error">Error</option>
                                                    <option value="Processing">Processing</option>
                                                    <option value="INIT">INIT</option>
                                                </select>

                                            </div>
                                            <div class="col s12 m3 l3">
                                                <button type="button" class="btn primary botton" id="submitFilter"
                                                    onclick="_Services._submitToDistributorFilterData()">Filter</button>
                                            </div>
                                        </div>
                                        <table class="responsive-table striped display" id="data-table-simple"
                                            cellspacing="0">
                                            <thead>
                                                <tr>
                                                    <th>Date</th>
                                                    <th>Transaction ID</th>
                                                    <th>File Name</th>
                                                    <th>Stock Status</th>
                                                    <th style="width: 180px !important">Action</th>
                                                </tr>
                                            </thead>
                                            <tbody id="consignmentTableLibraryTbody">
                                                <tr>
                                                    <td>20/10/2018</td>
                                                    <td>GFrt4581</td>
                                                    <td>name.csv</td>
                                                    <td>Success</td>
                                                    <td style="width: 180px !important">
                                                        <a href="#ErrorFile"><i class="fa fa-exclamation-circle"
                                                                title="ErrorFile"
                                                                style=" color:red; font-size:20px; margin-right:15px;"></i></a>
                                                        <a href="#" download="download"><i class="fa fa-download"
                                                                aria-hidden="true"
                                                                style="font-size: 20px;  color:#2e8b57;"
                                                                title="download"></i></a>
                                                        <a href="viewStockManagement.html"><i
                                                                class="fa fa-eye teal-text" aria-hidden="true"
                                                                title="view"
                                                                style="font-size: 20px; margin:0 0 0 15px;"></i></a>
                                                        <a href="editStockManagement.html"><i class="fa fa-pencil"
                                                                aria-hidden="true"
                                                                style="font-size: 20px; margin:0 15px 0 15px; color: #006994"
                                                                title="edit"></i></a>
                                                        <a href="#DeleteStock"
                                                            class="waves-effect waves-light modal-trigger mr-3"><i
                                                                class="fa fa-trash" aria-hidden="true"
                                                                style="font-size: 20px; color: red;"
                                                                title="delete"></i></a>
                                                    </td>
                                                </tr>

                                                <tr>
                                                    <td>20/10/2018</td>
                                                    <td>GFrt4581</td>
                                                    <td>name.csv</td>
                                                    <td>Error</td>
                                                    <td>
                                                        <a href="#ErrorFile"><i class="fa fa-exclamation-circle"
                                                                title="ErrorFile"
                                                                style=" color:red; font-size:20px; margin-right:15px;"></i></a>
                                                        <a href="#" download="download"><i class="fa fa-download"
                                                                aria-hidden="true"
                                                                style="font-size: 20px;  color:#2e8b57;"
                                                                title="download"></i></a>
                                                        <a href="viewStockManagement.html"><i
                                                                class="fa fa-eye teal-text" aria-hidden="true"
                                                                title="view"
                                                                style="font-size: 20px; margin:0 0 0 15px;"></i></a>
                                                        <a href="editStockManagement.html"><i class="fa fa-pencil"
                                                                aria-hidden="true"
                                                                style="font-size: 20px; margin:0 15px 0 15px; color: #006994"
                                                                title="edit"></i></a>
                                                        <a href="#DeleteStock"
                                                            class="waves-effect waves-light modal-trigger mr-3"><i
                                                                class="fa fa-trash" aria-hidden="true"
                                                                style="font-size: 20px; color: red;"
                                                                title="delete"></i></a>
                                                    </td>
                                                </tr>

                                                <tr>
                                                    <td>20/10/2018</td>
                                                    <td>GFrt4581</td>
                                                    <td>name.csv</td>
                                                    <td>Processing</td>
                                                    <td>
                                                        <a href="#ErrorFile"><i class="fa fa-exclamation-circle"
                                                                title="ErrorFile"
                                                                style=" color:red; font-size:20px; margin-right:15px;"></i></a>
                                                        <a href="#" download="download"><i class="fa fa-download"
                                                                aria-hidden="true"
                                                                style="font-size: 20px;  color:#2e8b57;"
                                                                title="download"></i></a>
                                                        <a href="viewStockManagement.html"><i
                                                                class="fa fa-eye teal-text" aria-hidden="true"
                                                                title="view"
                                                                style="font-size: 20px; margin:0 0 0 15px;"></i></a>
                                                        <a href="editStockManagement.html"><i class="fa fa-pencil"
                                                                aria-hidden="true"
                                                                style="font-size: 20px; margin:0 15px 0 15px; color: #006994"
                                                                title="edit"></i></a>
                                                        <a href="#DeleteStock"
                                                            class="waves-effect waves-light modal-trigger mr-3"><i
                                                                class="fa fa-trash" aria-hidden="true"
                                                                style="font-size: 20px; color: red;"
                                                                title="delete"></i></a>
                                                    </td>
                                                </tr>

                                                <tr>
                                                    <td>20/10/2018</td>
                                                    <td>GFrt4581</td>
                                                    <td>name.csv</td>
                                                    <td>INIT</td>
                                                    <td>
                                                        <a href="#ErrorFile"><i class="fa fa-exclamation-circle"
                                                                title="ErrorFile"
                                                                style=" color:red; font-size:20px; margin-right:15px;"></i></a>
                                                        <a href="#" download="download"><i class="fa fa-download"
                                                                aria-hidden="true"
                                                                style="font-size: 20px;  color:#2e8b57;"
                                                                title="download"></i></a>
                                                        <a href="viewStockManagement.html"><i
                                                                class="fa fa-eye teal-text" aria-hidden="true"
                                                                title="view"
                                                                style="font-size: 20px; margin:0 0 0 15px;"></i></a>
                                                        <a href="editStockManagement.html"><i class="fa fa-pencil"
                                                                aria-hidden="true"
                                                                style="font-size: 20px; margin:0 15px 0 15px; color: #006994"
                                                                title="edit"></i></a>
                                                        <a href="#DeleteStock"
                                                            class="waves-effect waves-light modal-trigger mr-3"><i
                                                                class="fa fa-trash" aria-hidden="true"
                                                                style="font-size: 20px; color: red;"
                                                                title="delete"></i></a>
                                                    </td>
                                                </tr>

                                                <tr>
                                                    <td>20/10/2018</td>
                                                    <td>GFrt4581</td>
                                                    <td>name.csv</td>
                                                    <td>Success</td>
                                                    <td>
                                                        <a href="#ErrorFile"><i class="fa fa-exclamation-circle"
                                                                title="ErrorFile"
                                                                style=" color:red; font-size:20px; margin-right:15px;"></i></a>
                                                        <a href="#" download="download"><i class="fa fa-download"
                                                                aria-hidden="true"
                                                                style="font-size: 20px;  color:#2e8b57;"
                                                                title="download"></i></a>
                                                        <a href="viewStockManagement.html"><i
                                                                class="fa fa-eye teal-text" aria-hidden="true"
                                                                title="view"
                                                                style="font-size: 20px; margin:0 0 0 15px;"></i></a>
                                                        <a href="editStockManagement.html"><i class="fa fa-pencil"
                                                                aria-hidden="true"
                                                                style="font-size: 20px; margin:0 15px 0 15px; color: #006994"
                                                                title="edit"></i></a>
                                                        <a href="#DeleteStock"
                                                            class="waves-effect waves-light modal-trigger mr-3"><i
                                                                class="fa fa-trash" aria-hidden="true"
                                                                style="font-size: 20px; color: red;"
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
            <!-- END CONTENT -->

            <!-- //////////////////////////////////////////////////////////////////////////// -->
            <!-- START RIGHT SIDEBAR NAV-->

            <!-- LEFT RIGHT SIDEBAR NAV-->

      
    <!-- END MAIN -->


</body>
</html>