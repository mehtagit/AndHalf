<%@ page import="java.util.Date" %>
<%
   response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setDateHeader("Expires", 0);
	response.setHeader("Pragma", "no-cache");
	
    /*   //200 secs
	 session.setAttribute("usertype", null);  */
/* 	 session.setMaxInactiveInterval(10); */
	 int timeout = session.getMaxInactiveInterval();
	
	 long accessTime = session.getLastAccessedTime();
	 long currentTime= new Date().getTime(); 
	 long dfd= accessTime +timeout;
	 if( currentTime< dfd){
	/*  response.setHeader("Refresh", timeout + "; URL = ../login");
	 System.out.println("timeout========"+timeout); 
	if (session.getAttribute("usertype") != null) { */
%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Security Tags -->
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<sec:csrfMetaTags />
<!-- Security Tags -->
<c:set var="context" value="${pageContext.request.contextPath}" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<title>Graph</title>

<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta content="" name="description" />
<meta content="" name="author" />
<!-- Security Tags -->
<meta name="_csrf" content="${_csrf.token}" />
<!-- default header name is X-CSRF-TOKEN -->
<meta name="_csrf_header" content="${_csrf.headerName}" />
<!-- Security Tags -->

<script type="text/javascript"
	src="${context}/resources/js/plugins/jquery-1.11.2.min.js"></script>

<!-- CORE CSS-->
<link href="${context}/resources/css/materialize.css" type="text/css"
	rel="stylesheet" media="screen,projection">
<link href="${context}/resources/css/style.css" type="text/css"
	rel="stylesheet" media="screen,projection">
<link
	href="${context}/resources/js/plugins/perfect-scrollbar/perfect-scrollbar.css"
	type="text/css" rel="stylesheet" media="screen,projection">


<style>
.row {
	margin-bottom: 0;
	margin-top: 0;
}

/* input[type=text] {
      height: 35px; 
      margin: 0 0 5px 0;
    } */
input
[
type
=
text
]
:not
 
(
.browser-default
 
)
{
font-size
:
 
13
px
;
/* height: 30px; */


}
input[type=date] {
	font-size: 0.8rem;
}

textarea.materialize-textarea {
	padding: 0;
	padding-left: 10px;
}

.btn-flat {
	height: auto;
}

#starColor {
	color: red;
}



section {
	margin-top: 10px;
}

input#expectedArrivaldate {
    height: 6vh;
}
input#quantity {
    height: 7vh;
}



@media (min-width: 1200px)
.col-xl-8 {
    flex: 0 0 66.66667%;
    max-width: 66.66667%;
}
.col, .col-1, .col-10, .col-11, .col-12, .col-2, .col-3, .col-4, .col-5, .col-6, .col-7, .col-8, .col-9, .col-auto, .col-lg, .col-lg-1, .col-lg-10, .col-lg-11, .col-lg-12, .col-lg-2, .col-lg-3, .col-lg-4, .col-lg-5, .col-lg-6, .col-lg-7, .col-lg-8, .col-lg-9, .col-lg-auto, .col-md, .col-md-1, .col-md-10, .col-md-11, .col-md-12, .col-md-2, .col-md-3, .col-md-4, .col-md-5, .col-md-6, .col-md-7, .col-md-8, .col-md-9, .col-md-auto, .col-sm, .col-sm-1, .col-sm-10, .col-sm-11, .col-sm-12, .col-sm-2, .col-sm-3, .col-sm-4, .col-sm-5, .col-sm-6, .col-sm-7, .col-sm-8, .col-sm-9, .col-sm-auto, .col-xl, .col-xl-1, .col-xl-10, .col-xl-11, .col-xl-12, .col-xl-2, .col-xl-3, .col-xl-4, .col-xl-5, .col-xl-6, .col-xl-7, .col-xl-8, .col-xl-9, .col-xl-auto {
    position: relative;
    width: 100%;
    padding-right: .75rem;
}
.card {
    position: relative;
    display: flex;
    flex-direction: column;
    min-width: 0;
    word-wrap: break-word;
    background-color: #fff;
    background-clip: border-box;
    border: 1px solid #e3e6f0;
    border-radius: .35rem;
}

.shadow {
    box-shadow: 0 .15rem 1.75rem 0 rgba(58,59,69,.15)!important;
}
.mb-4, .my-4 {
    margin-bottom: 1.5rem!important;
}

.card-header:first-child {
    border-radius: calc(.35rem - 1px) calc(.35rem - 1px) 0 0;
}
.pb-3, .py-3 {
    padding-bottom: 1rem!important;
}
.pt-3, .py-3 {
    padding-top: 1rem!important;
}
.align-items-center {
    align-items: center!important;
}
.justify-content-between {
    justify-content: space-between!important;
}
.flex-row {
    flex-direction: row!important;
}
.d-flex {
    display: flex!important;
}
.card-header {
    padding: .75rem 1.25rem;
    margin-bottom: 0;
    background-color: #f8f9fc;
    border-bottom: 1px solid #e3e6f0;
}

.text-primary {
    color: #4e73df!important;
}

.font-weight-bold {
    font-weight: 700!important;
}
.m-0 {
    margin: 0!important;
}
.h6, h6 {
    font-size: 1rem;
}

.card-body {
    flex: 1 1 auto;
    padding: 1.25rem;
}


.highcharts-credits {
display: none !important;
}
</style>

  <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.min.js"></script>
</head>
<body>
	<!-- START MAIN -->

	<!-- START WRAPPER -->
	<div class="wrapper">

		<!-- START CONTENT -->
		<section id="content">
			<!--start container-->
			<div class="container">
				<div class="section">
					<div class="row">
						<div class="col s12 m12 l12">
							<div class="row card-panel">
								<div class="row card-panel responsive-page" id="endUserRaiseGrievance" style="display:block !important">
                            <h6 class="fixPage-modal-header ">
                            <spring:message code="sidebar.User_Dashboard" />
                            				</h6>
                            <div style="display:flex">
                            <div class="col-xl-8 col-lg-7">
              <div class="card shadow mb-4">
                <!-- Card Header - Dropdown -->
                <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                  <h6 class="m-0 font-weight-bold text-primary">User Login Graph</h6>
                 
                </div>
                <!-- Card Body -->
                <div class="card-body">
                   <canvas class="chart-area" id="lineGraph" style = "width: 550px; height: 400px; margin: 0 auto">
                  </canvas>
                </div>
              </div>
            </div>
             
             
             
             
             <div class="col-xl-8 col-lg-7">
              <div class="card shadow mb-4">
                <!-- Card Header - Dropdown -->
                <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                  <h6 class="m-0 font-weight-bold text-primary">User Login Graph</h6>
              
                </div>
                <!-- Card Body -->
                <div class="card-body">
                 
                    <canvas class="chart-area" id="barGraph" style = "width: 550px; height: 400px; margin: 0 auto">
                  </canvas>
                </div>
              </div>
            </div>               
             </div>               
                    
                    
                    <div style="display:flex">
                            <div class="col-xl-8 col-lg-7">
              <div class="card shadow mb-4">
                <!-- Card Header - Dropdown -->
                <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                  <h6 class="m-0 font-weight-bold text-primary">User Login Graph</h6>
                 
                </div>
                <!-- Card Body -->
                <div class="card-body">
                   <canvas class="chart-area" id="pieGraph" style = "width: 550px; height: 400px; margin: 0 auto">
                  </canvas>
                </div>
              </div>
            </div>
             
             
             
             
             <div class="col-xl-8 col-lg-7">
              <div class="card shadow mb-4">
                <!-- Card Header - Dropdown -->
                <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                  <h6 class="m-0 font-weight-bold text-primary">User Login Graph</h6>
              
                </div>
                <!-- Card Body -->
                <div class="card-body">
                 
                    <canvas class="chart-area" id="donutGraph" style = "width: 550px; height: 400px; margin: 0 auto">
                  </canvas>
    </div></div></div></div>
    
    
    
    <div style="display:flex">
                            <div class="col-xl-8 col-lg-7">
              <div class="card shadow mb-4">
                <!-- Card Header - Dropdown -->
                <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                  <h6 class="m-0 font-weight-bold text-primary">User Login Graph</h6>
                 
                </div>
                <!-- Card Body -->
                <div class="card-body">
                   <canvas class="chart-area" id="gaugeGraph" style = "width: 550px; height: 400px; margin: 0 auto">
                  </canvas>
                </div>
              </div>
            </div>
             
             
             
             
             <div class="col-xl-8 col-lg-7">
              <div class="card shadow mb-4">
                <!-- Card Header - Dropdown -->
                <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                  <h6 class="m-0 font-weight-bold text-primary">User Login Graph</h6>
              
                </div>
                <!-- Card Body -->
                <div class="card-body">
                 
                    <canvas class="chart-area" id="horizontalBarGraph" style = "width: 550px; height: 400px; margin: 0 auto">
                  </canvas>
    </div></div></div></div>
    </div></div></div></div></div></div></section></div>



	<!--end container-->



	<!-- END MAIN -->


	<!--materialize js-->
	<script type="text/javascript"
		src="${context}/resources/js/materialize.js"></script>
	<script
		src="${context}/resources/custom_js/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="${context}/resources/js/plugins/perfect-scrollbar/perfect-scrollbar.min.js"></script>
	<script type="text/javascript"
		src="${context}/resources/graph_js/UserLoginGraph.js?version=<%= (int) (Math.random() * 10) %>"></script>		
	<!-- chartist -->
<script type="text/javascript">
  
  $(document).ready(function () {
	  userloginGraph() 
	  var timeoutTime = <%=session.getLastAccessedTime()%>;
	  var timeout = <%=session.getMaxInactiveInterval()%>;
	  timeoutTime += timeout;
	  var currentTime;
	  $("body").click(function(e) {
	  $.ajaxSetup({
	  headers:
	  { 'X-CSRF-TOKEN': $("meta[name='_csrf']").attr("content") }
	  });
	  $.ajax({
	  url: './serverTime',
	  type: 'GET',
	  async: false,
	  success: function (data, textStatus, jqXHR) {
	  currentTime = data;
	  },
	  error: function (jqXHR, textStatus, errorThrown) {}
	  });
	  if( currentTime > timeoutTime ){
	  window.top.location.href = "./login";
	  }else{
	  timeoutTime += timeout;
	  }
	  });
  });
  </script>          
<script type="text/javascript">$( document ).ready(function() {var timeoutTime = <%=session.getLastAccessedTime()%>;var timeout = <%=session.getMaxInactiveInterval()%>;timeoutTime += timeout;var currentTime;$("body").click(function(e) {$.ajaxSetup({headers:{ 'X-CSRF-TOKEN': $("meta[name='_csrf']").attr("content") }});$.ajax({url: './serverTime',type: 'GET',async: false,success: function (data, textStatus, jqXHR) {currentTime = data;},error: function (jqXHR, textStatus, errorThrown) {}});if( currentTime > timeoutTime ){window.top.location.href = "./login";}else{timeoutTime = currentTime + timeout;}});});</script>
</body></html>

<%
	} else {
		/*  request.setAttribute("msg", "  *Please login first");
		request.getRequestDispatcher("./index.jsp").forward(request, response); */
%>
<script language="JavaScript">
	sessionStorage.setItem("loginMsg",
			"*Session has been expired");
	window.top.location.href = "./login";
</script>
<%
	}
%>