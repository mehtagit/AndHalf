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


<style>
#snackbar {
	visibility: hidden;
	min-width: 250px;
	margin-left: -125px;
	background-color: #333;
	color: #fff;
	text-align: center;
	border-radius: 2px;
	padding: 10px;
	position: fixed;
	z-index: 1;
	left: 47%;
	top: 15px;
	font-size: 17px;
	height: 50px;
	line-height: 0px;
}

#snackbar.show {
	visibility: visible;
	-webkit-animation: fadein 0.5s, fadeout 0.5s 2.5s;
	animation: fadein 0.5s, fadeout 0.5s 2.5s;
}

@
-webkit-keyframes fadein {
	from {bottom: 0;
	opacity: 0;
}

to {
	bottom: 30px;
	opacity: 1;
}

}
@
keyframes fadein {
	from {bottom: 0;
	opacity: 0;
}

to {
	bottom: 30px;
	opacity: 1;
}

}
@
-webkit-keyframes fadeout {
	from {bottom: 30px;
	opacity: 1;
}

to {
	bottom: 0;
	opacity: 0;
}

}
@
keyframes fadeout {
	from {bottom: 30px;
	opacity: 1;
}

to {
	bottom: 0;
	opacity: 0;
}

}
.container-fluid {
	background-color: #529dba;
	height: 50px;
	/* margin:0 -20px; */
	padding: 10px;
	/* border-radius:5px 0;  */
}

/* #deletemodal.modal-backdrop {
  z-index: 0;
}  */
.boton {
	color: #2979a5;
	float: right;
	border: solid 1px rgba(33, 167, 201, 0.774);
	padding: 4px 10px;
	border-radius: 7px;
	font-size: 14px;
	background-color: #fff;
}

.boton:hover {
	color: #fff;
	float: right;
	border: solid 1px #ff5a92;
	padding: 4px 10px;
	border-radius: 7px;
	font-size: 14px;
	background-color: #ff5a92;
}

.row {
	margin-bottom: 0;
}

.file-field .btn, .file-field .btn-large {
	float: left;
	height: 2.5rem;
	line-height: 2.5rem;
}

[type="date"]::-webkit-inner-spin-button {
	display: none;
}

[type="date"]::-webkit-calendar-picker-indicator {
	opacity: 0;
}

#id7 {
	width: 150px;
}

.eventNone {
	cursor: not-allowed;
	user-select: none;
	pointer-events: none;
}
</style>

</head>
<body data-roleType="${usertype}" data-userID="${userid}" >


	<!-- START CONTENT -->
	<section id="content">
		<!--start container-->
		<div class="container">
			<div class="section">
				<div class="row">
					<div class="col s12 m12 l12">
						<div class="row card-panel">
							<div class="container-fluid pageHeader" id="pageHeader">

								<a href="" class="boton right" id="btnLink"></a>
							</div>

							<div class="col s12 m12 l12" id="consignmentTableDIv"
								style="padding-bottom: 5px; background-color: #e2edef52;">
								<div id="filterBtnDiv">
									<!-- 							<div class='col s12 m2 l2'><button type='submit' class='btn primary botton' id='submitFilter'></button></div>
		 -->
								</div>
							</div>

							<table id="stockTable"
								class="responsive-table striped display"></table>

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


	<script>
$(document).ready(function(){
$('.datepicker').datepicker();
});

$('.datepicker').on('mousedown',function(event){
event.preventDefault();
});
</script>
	<script type="text/javascript">
	
	 var roleType = $("body").attr("data-roleType");
     var userId = $("body").attr("data-userID");
     
   var jsonObj = {
    	 "consignmentStatus": null,
    	 "endDate": "2019-11-11T10:53:37.289Z",
    	 "roleType": roleType,
    	 "startDate": "2019-11-11T10:53:37.290Z",
    	 "taxPaidStatus": null,
    	 "userId": userId
    	 };
  console.log(jsonObj)
    $(document).ready(function () {
    	 $.ajax({
	url: "${context}/headers",
	type: 'POST',
	dataType: "json",
	success: function(result){
			var table=	$("#stockTable").DataTable({
                "serverSide": true,
    			orderCellsTop : true,
    			"aaSorting" : [],
    			"bPaginate" : true,
    			"bFilter" : true,
    			"bInfo" : true,
    			"bSearchable" : true,
				ajax: {
					type: 'POST',
           		        url: '${context}/stockData',           		        
           		    	data : function(d) {
          		    		d.filter = JSON.stringify(jsonObj);       		    		
           				}
         		},
                "columns": result
            });
	}
					}); 
    });				
		
 $.ajax({
		url: "${context}/stock/pageRendering",
		type: 'POST',
		dataType: "json",
		success: function(data){
var elem='<p class="PageHeading">'+data.pageTitle+'</p>';
$("#pageHeader").append(elem);
var button=data.buttonList;

var date=data.inputTypeDateList;
for(i=0; i<date.length; i++){
$("#consignmentTableDIv").append("<div class='col s6 m2 l2 responsiveDiv'>"+
		"<div id='enddatepicker' class='input-group date' data-date-format='yyyy-mm-dd'>"+
		"<label for='TotalPrice'>"+date[i].title
		+"</label>"+"<input class='form-control' type="+date[i].type+" id="+date[i].id+"/>"+
		"<span	class='input-group-addon' style='color: #ff4081'>"+
		"<i	class='fa fa-calendar' aria-hidden='true' style='float: right; margin-top: -37px;'>"+"</i>"+"</span>");
} 

// dynamic dropdown portion
var dropdown=data.dropdownList;
for(i=0; i<dropdown.length; i++){
var dropdownDiv=
$("#consignmentTableDIv").append("<div class='col s6 m2 l2 selectDropdwn'>"+
		"<br>"+
		"<div class='select-wrapper select2 form-control boxBorder boxHeight initialized'>"+
		"<span class='caret'>"+"</span>"+
		"<input type='text' class='select-dropdown' readonly='true' data-activates='select-options-1023d34c-eac1-aa22-06a1-e420fcc55868' value='Consignment Status'>"+
		
		"<select id="+dropdown[i].id+" class='select2 form-control boxBorder boxHeight initialized'>"+
		"<option>"+dropdown[i].title+
		"</option>"+
		"</select>"+
		"</div>"+
		"</div>");
}

$("#consignmentTableDIv").append("<div class='col s12 m2 l2'><button type='submit' class='btn primary botton' id='submitFilter'></button></div>");
for(i=0; i<button.length; i++){
	$('#'+button[i].id).text(button[i].buttonTitle);
	$('#'+button[i].id).attr("href", button[i].buttonURL);
	}
		}

//$("#filterBtnDiv").append();
}); 
</script>
</body>
</html>

