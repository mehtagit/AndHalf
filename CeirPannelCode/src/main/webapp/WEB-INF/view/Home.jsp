<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!-- Security Tags -->
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<sec:csrfMetaTags />
<!-- Security Tags -->
<c:set var="context" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en" class="no-js">
<head><title>CEIR Portal</title>
<!--<title>Home</title>-->
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:400,600,700,800&display=swap"
	rel="stylesheet">
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

<!-- Favicons-->
<link rel="icon" href="${context}/resources/images/DMC-Logo.png"
	sizes="32x32">
<script type="text/javascript"
	src="${context}/resources/js/plugins/jquery-1.11.2.min.js"></script>
<link rel="shortcut icon" href="">
<!-- CORE CSS-->
<link href="${context}/resources/css/materialize.css" type="text/css"
	rel="stylesheet" media="screen,projection">
<link href="${context}/resources/css/style.css" type="text/css"
	rel="stylesheet" media="screen,projection">
<!-- Custome CSS-->

<link
	href="${context}/resources/js/plugins/data-tables/css/jquery.dataTables.min.css"
	type="text/css" rel="stylesheet" media="screen,projection">
<link
	href="${context}/resources/js/plugins/perfect-scrollbar/perfect-scrollbar.css"
	type="text/css" rel="stylesheet" media="screen,projection">
<!-- INCLUDED PLUGIN CSS ON THIS PAGE -->
<link href="${context}/resources/js/plugins/prism/prism.css"
	type="text/css" rel="stylesheet" media="screen,projection">
<link
	href="${context}/resources/js/plugins/perfect-scrollbar/perfect-scrollbar.css"
	type="text/css" rel="stylesheet" media="screen,projection">
<link href="${context}/resources/css/dashboard.css" type="text/css"
	rel="stylesheet" media="screen,projection">
<link
	href="${context}/resources/font/font-awesome/css/font-awesome.min.css"
	type="text/css" rel="stylesheet" media="screen,projection">

<link rel="stylesheet"
	href="${context}/resources/custom_js/jquery-ui.css">

<script src="${context}/resources/custom_js/jquery.blockUI.js"></script>
<!------------------------------------------- Dragable Model---------------------------------->
<script src="${context}/resources/custom_js/1.12.1_jquery-ui.min.js"></script>

    <script src="https://files.codepedia.info/files/uploads/iScripts/html2canvas.js"></script>


<link rel="stylesheet"
	href="${context}/resources/project_css/iconStates.css">
<style type="text/css">
.dataTables_info {
	display: none;
}

.paging_simple_numbers {
	display: none;
}

.dataTables_filter {
	display: none;
}

.section {
	padding-bottom: 1rem;
	width: 99%;
	margin: auto;
	margin-top: 10px;
}
              .grid-container {
                display: grid;
				grid-gap:2px;
                grid-template-columns: repeat(auto-fill, minmax(100px, 1fr));
                background-color: #333;
                padding: 10px;
              }
              .grid-item {
                background-color: rgba(255, 255, 255, 0.8);
                border: 1px solid rgba(0, 0, 0, 0.8);
                padding: 20px;
                font-size: 30px;
                text-align: center;
		color: cadetblue;
              }
              .statusColor{
              background: #008000 !important;}
              .NotstatusColor{
              background: #ADD8E6 !important;}
              .statusNotAvailableColor{
              background: #898989 !important;
              }
</style>
</head>

<body data-id="1" data-roleType="${usertype}"
	data-userTypeID="${usertypeId}" data-userID="${userid}"
	data-selected-roleType="${selectedUserTypeId}"
	data-stolenselected-roleType="${stolenselectedUserTypeId}">

	<!-- 
<section id="content">
		<div class="center">
			<h1 style="font-size: 35px; margin-top: 30px;">Welcome To Central Equipment Identity Register</h1>
		</div>
	</section> -->

	<section id="content">
		<!--start container-->
	<!-- 	<div id="initialloader"></div> -->
		<div class="container">
			<div class="section">
				<div class="row card-panel">
					<div class="pageHeader">
						<p class="PageHeading">
							<spring:message code="view.dashboard" />
						</p>

					</div>
					<div class="col s12 m12 l12">
						<div class="row"></div>
						<div class="col s12 m12">

			  <a id="btn-Convert-Html2Image" href="#" style="margin-left: 92%;font-size: large;"><i class="fa fa-download" aria-hidden="true"> Download</i></a>
    		  <div id="previewImage" style="display: none;"></div>
              <div id="html-content-holder" class="grid-container">
              
                  <table class='responsive-table striped datatable' id='activeDeviceTable'>
                            </table>
                            
             <%--  <c:forEach items="${list}" var="item">
                <a href="#"><div class="grid-item">${item.id}</div></a>
               </c:forEach> --%>
              </div>

						</div>

					</div>
				</div>
			</div>
		</div>
		<!--end container-->
	</section>
	<!-- END CONTENT -->
	<script src="${context}/resources/custom_js/bootstrap.min.js"></script>

	<script type="text/javascript"
		src="${context}/resources/js/materialize.js"></script>

	<script type="text/javascript"
		src="${context}/resources/project_js/globalVariables.js?version=<%= (int) (Math.random() * 10) %>"></script>
	<script type="text/javascript"
		src="${context}/resources/project_js/backbutton.js?version=<%= (int) (Math.random() * 10) %>"></script>
	<script type="text/javascript"
		src="${context}/resources/project_js/enterKey.js?version=<%= (int) (Math.random() * 10) %>"></script>
<%-- 	<script type="text/javascript"
		src="${context}/resources/project_js/home.js?version=<%= (int) (Math.random() * 10) %>"></script>
	 --%><script type="text/javascript">

        $(document).ready(function () {
        	var currentTime = new Date()
        	var month = ("0" + (currentTime.getMonth() + 1)).slice(-2)
        	var day = ("0" + (currentTime.getDate())).slice(-2)
        	var hours=("0"+ (currentTime.getHours() - 6)).slice(-2)
        	var year = currentTime.getFullYear();
        	var finalVal=year+"-"+month+"-"+day+" "+hours+":"+currentTime.getMinutes()+":"+currentTime.getSeconds();
        //("0" + currentHours).slice(-2)
        	var token = $("meta[name='_csrf']").attr("content");
        	var header = $("meta[name='_csrf_header']").attr("content");
        	$.ajaxSetup({
        	headers:
        	{ 'X-CSRF-TOKEN': token }
        	});
        	$.ajax({
        		type : 'GET',
        		url : 'http://13.234.49.133:9004/substation/station/get',
        		contentType : "application/json",
        		success: function(data){
			//	console.log(data);
        			for(var i=0 ;i<data.length;i++){
        				if(finalVal <= data[i].lastIntervalPacketDate){
        					
        					var classN="statusColor";
        					//$('#activeDeviceTable').append("<tr class="+classN+"><td>"+data[i].substation+"</td><tr>");
        					 $("#html-content-holder").append("<a href='javascript:void(0)'><div class='grid-item "+classN+"'>"+data[i].substation+"</div></a>");
        				     
        				}
        				else if( data[i].lastIntervalPacketDate == null){
        					var classN="statusNotAvailableColor";
        			//	$('#activeDeviceTable').append("<tr class="+classN+"><td>"+data[i].substation+"</td><tr>");
        					
          			$("#html-content-holder").append("<a href='javascript:void(0)'><div class='grid-item "+classN+"'>"+data[i].substation+"</div></a>");
          				     
        				}
        				else{
        				var classN="NotstatusColor";
        				//$('#activeDeviceTable').append("<tr class="+classN+"><td>"+data[i].substation+"</td><tr>");
    					
       					$("#html-content-holder").append("<a href='javascript:void(0)'><div class='grid-item "+classN+"'>"+data[i].substation+"</div></a>");
       				     
        				}
     // $('div#initialloader').delay(300).fadeOut('slow');
        			}
        		}
        	
        	});
        	
        	
        	
            var element = $("#html-content-holder"); // global variable
            var getCanvas; // global variable

            html2canvas(element, {
                onrendered: function (canvas) {
                    $("#previewImage").append(canvas);
                    getCanvas = canvas;
                }
            });

            $("#btn-Convert-Html2Image").on('click', function () {
                var imgageData = getCanvas.toDataURL("image/png");
                // Now browser starts downloading it instead of just showing it
                var newData = imgageData.replace(/^data:image\/png/, "data:application/octet-stream");
                $("#btn-Convert-Html2Image").attr("download", "image.png").attr("href", newData);
            });
        });
	</script>
<script type="text/javascript">$( document ).ready(function() {var timeoutTime = <%=session.getLastAccessedTime()%>;var timeout = <%=session.getMaxInactiveInterval()%>;timeoutTime += timeout;var currentTime;$("body").click(function(e) {$.ajaxSetup({headers:{ 'X-CSRF-TOKEN': $("meta[name='_csrf']").attr("content") }});$.ajax({url: './serverTime',type: 'GET',async: false,success: function (data, textStatus, jqXHR) {currentTime = data;},error: function (jqXHR, textStatus, errorThrown) {}});if( currentTime > timeoutTime ){window.top.location.href = "./login";}else{timeoutTime = currentTime + timeout;}});});</script>

</body></html>