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


  <!-- CORE CSS-->
  <link href="${context}/resources/css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection">
  <link href="${context}/resources/css/style.css" type="text/css" rel="stylesheet" media="screen,projection">
  <!-- Custome CSS-->    
  <link href="${context}/resources/css/custom/custom.css" type="text/css" rel="stylesheet" media="screen,projection">

<link
	href="${context}/resources/js/plugins/data-tables/css/jquery.dataTables.min.css"
	type="text/css" rel="stylesheet" media="screen,projection">
<link
	href="${context}/resources/js/plugins/data-tables/css/jquery.dataTables.css"
	type="text/css" rel="stylesheet" media="screen,projection">
	<link
	href="${context}/resources/js/plugins/perfect-scrollbar/perfect-scrollbar.css"
	type="text/css" rel="stylesheet" media="screen,projection">
  <!-- INCLUDED PLUGIN CSS ON THIS PAGE -->
  <link href="${context}/resources/js/plugins/prism/prism.css" type="text/css" rel="stylesheet" media="screen,projection">
  <link href="${context}/resources/js/plugins/perfect-scrollbar/perfect-scrollbar.css" type="text/css" rel="stylesheet" media="screen,projection">
  <link href="${context}/resources/css/dashboard.css" type="text/css" rel="stylesheet" media="screen,projection">
  <link href="${context}/resources/font/font-awesome/css/font-awesome.css"
	type="text/css" rel="stylesheet" media="screen,projection">
<link
	href="${context}/resources/font/font-awesome/css/font-awesome.min.css"
	type="text/css" rel="stylesheet" media="screen,projection">
<style type="text/css">
  .dataTables_info {
        display: none;
    }
    .paging_simple_numbers {
        display: none;
    }
    .dataTables_filter{
        display: none;
    }
</style>
</head>

<body>

<!-- 
<section id="content">
		<div class="center">
			<h1 style="font-size: 35px; margin-top: 30px;">Welcome To Central Equipment Identity Register</h1>
		</div>
	</section> -->
	
	 <section id="content">
                <!--start container-->
                <div class="container">
                    <div class="section">
                        <div class="row card-panel">
                            <div class="container-fluid pageHeader">
                                <p class="PageHeading">Dashboard</p>
                            </div>
                            <div class="col s12 m12 l12">
                                <div class="row">

                                </div>
                                <div class="col s12 m12">

                                    <div class="col s12 m12 info-div center" style="margin-top: 30px;">
                                       <c:choose>
                                       <c:when test="${roletype=='Importer'}">
                                        <div class="round-circle-center-responsive">
                                            <div class="round-circle">
                                                <h6 class="right">Consignment</h6>
                                                <p class="circle-para right"><b>${consignmentNoticationDetails.count}</b> </p>
                                                <p class="center view-div-info"><a href="./Consignment/viewConsignment" class=""><i
                                                            class="fa fa-eye teal-text" title="view"></i></a></p>
                                            </div>
                                            <div class="icon-div center" style="background-color: #fc950c;">
                                                <i class="fa fa-list-alt test-icon" aria-hidden="true"></i>
                                            </div>
                                        </div>
                                        <div class="round-circle-center-responsive">
                                            <div class="round-circle">
                                                <h6 class="right">Total Device in <br> Transit</h6>
                                                <p class="circle-para right"><b>${consignmentNoticationDetails.quantity}</b> </p>
                                                <p class="center view-div-info"><a href="./Consignment/viewConsignment" class=""><i
                                                            class="fa fa-eye teal-text" title="view"></i></a></p>
                                            </div>
                                            <div class="icon-div center" style="background-color: #57a85c;">
                                                <i class="fa fa-tablet test-icon" aria-hidden="true"></i>
                                            </div>
                                        </div>
                                        <div class="round-circle-center-responsive">
                                            <div class="round-circle">
                                                <h6 class="right">Stock</h6>
                                                <p class="circle-para right"><b>${stockDetails.count}</b> </p>
                                                <p class="center view-div-info"><a href="./assignDistributor"
                                                        class=""><i class="fa fa-eye teal-text" title="view"></i></a>
                                                </p>
                                            </div>
                                            <div class="icon-div center" style="background-color: #57a85c;">
                                                <i class="fa fa-th-list test-icon" aria-hidden="true"></i>
                                            </div>
                                        </div>
                                        
                                        <div class="round-circle-center-responsive">
                                            <div class="round-circle">
                                                <h6 class="right">Total Device <br> Waiting For Upload</h6>
                                                <p class="circle-para right"><b>${stockDetails.quantity}</b> </p>
                                                <p class="center view-div-info"><a href="./assignDistributor" class=""><i
                                                            class="fa fa-eye teal-text" title="view"></i></a></p>
                                            </div>
                                            <div class="icon-div center" style="background-color: #e83c38;">
                                                <i class="fa fa-tablet test-icon" aria-hidden="true"></i>
                                            </div>
                                        </div>
                                        <div class="round-circle-center-responsive">
                                            <div class="round-circle">
                                                <h6 class="right"> Stolen/Recovery</h6>
                                                <p class="circle-para right"><b>${stolenRecoveryDetails.count}</b> </p>
                                                <p class="center view-div-info"><a href="./stolenRecovery"
                                                        class=""><i class="fa fa-eye teal-text" title="view"></i></a>
                                                </p>
                                            </div>
                                            <div class="icon-div center" style="background-color: #e83c38;">
                                                <i class="fa fa-hand-o-right test-icon" aria-hidden="true"></i>
                                            </div>
                                        </div>

                                        <div class="round-circle-center-responsive">
                                            <div class="round-circle">
                                                <h6 class="right">Grievance</h6>
                                                <p class="circle-para right"><b>${grievanceDetails.count}</b> </p>
                                                <p class="center view-div-info"><a href="./grievanceManagement" class=""><i
                                                            class="fa fa-eye teal-text" title="view"></i></a></p>
                                            </div>
                                            <div class="icon-div center" style="background-color: #fc950c;">
                                                <i class="fa fa-file-text-o test-icon" aria-hidden="true"></i>
                                            </div>
                                        </div>
                                        </c:when>
                                        <c:otherwise>
                                        <div class="round-circle-center-responsive">
                                            <div class="round-circle">
                                                <h6 class="right">Stock</h6>
                                                <p class="circle-para right"><b>${stockDetails.count}</b> </p>
                                                <p class="center view-div-info"><a href="./assignDistributor"
                                                        class=""><i class="fa fa-eye teal-text" title="view"></i></a>
                                                </p>
                                            </div>
                                            <div class="icon-div center" style="background-color: #57a85c;">
                                                <i class="fa fa-th-list test-icon" aria-hidden="true"></i>
                                            </div>
                                        </div>
                                        
                                        <div class="round-circle-center-responsive">
                                            <div class="round-circle">
                                                <h6 class="right">Total Device <br> Waiting For Upload</h6>
                                                <p class="circle-para right"><b>${stockDetails.quantity}</b> </p>
                                                <p class="center view-div-info"><a href="./assignDistributor" class=""><i
                                                            class="fa fa-eye teal-text" title="view"></i></a></p>
                                            </div>
                                            <div class="icon-div center" style="background-color: #e83c38;">
                                                <i class="fa fa-tablet test-icon" aria-hidden="true"></i>
                                            </div>
                                        </div>
                                        <div class="round-circle-center-responsive">
                                            <div class="round-circle">
                                                <h6 class="right"> Stolen/Recovery</h6>
                                                <p class="circle-para right"><b>${stolenRecoveryDetails.count}</b> </p>
                                                <p class="center view-div-info"><a href="./stolenRecovery"
                                                        class=""><i class="fa fa-eye teal-text" title="view"></i></a>
                                                </p>
                                            </div>
                                            <div class="icon-div center" style="background-color: #e83c38;">
                                                <i class="fa fa-hand-o-right test-icon" aria-hidden="true"></i>
                                            </div>
                                        </div>

                                        <div class="round-circle-center-responsive">
                                            <div class="round-circle">
                                                <h6 class="right">Grievance</h6>
                                                <p class="circle-para right"><b>${grievanceDetails.count}</b> </p>
                                                <p class="center view-div-info"><a href="./grievanceManagement" class=""><i
                                                            class="fa fa-eye teal-text" title="view"></i></a></p>
                                            </div>
                                            <div class="icon-div center" style="background-color: #fc950c;">
                                                <i class="fa fa-file-text-o test-icon" aria-hidden="true"></i>
                                            </div>
                                        </div>
                                                                                
                                        </c:otherwise>
                                       </c:choose> 
                                    </div>
                                    <div class="col s12 m12" style="padding-bottom: 40px;">
                                        <h4 class="header2" style="font-weight: bold; margin-top: 50px;">
                                            Notification Info</h4>
                                        
                                        <table class="responsive-table striped display" id="notificationLibraryTable"
                                            cellspacing="0">
                                           

                                        </table>
                                    </div>

                                </div>

                            </div>
                        </div>
                    </div>
                </div>
                <!--end container-->
            </section>
            <!-- END CONTENT -->
	<script type="text/javascript"
	src="${context}/resources/js/plugins/jquery-1.11.2.min.js"></script>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
	<script type="text/javascript"
		src="${context}/resources/js/materialize.js"></script>
	
		<script type="text/javascript"
		src="${context}/resources/js/plugins/data-tables/js/jquery.dataTables.js"></script>
	<script type="text/javascript"
		src="${context}/resources/js/plugins/data-tables/js/jquery.dataTables.min.js"></script>
			<script type="text/javascript"
		src="${context}/resources/project_js/dashboard.js"></script>
	
</body>
</html>