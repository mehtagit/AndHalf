
<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setDateHeader("Expires", 0);
	response.setHeader("Pragma", "no-cache");
	/*  session.setMaxInactiveInterval(200); //200 secs
	 session.setAttribute("usertype", null); */
	if (session.getAttribute("usertype") != null) {
%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>


<title>Customer care</title>
<meta http-equiv='cache-control' content='no-cache'>
<meta http-equiv='expires' content='-1'>
<meta http-equiv='pragma' content='no-cache'>
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
<link rel="stylesheet"
	href="${context}/resources/project_css/iconStates.css">

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>

<script src="http://malsup.github.io/jquery.blockUI.js"></script>
<script src="//cdn.datatables.net/plug-ins/1.10.20/i18n/Khmer.json"></script>
<!------------------------------------------- Dragable Model---------------------------------->
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>


<style type="text/css">
 #starColor {
            color: red;
        }
</style>

</head>
<body data-id="26"
data-roleType="${usertype}" data-userID="${userid}" data-userTypeID="${usertypeId}" data-selectedRoleTypeId="${selectedRoleTypeId}"
	data-selected-roleType="${selectedUserTypeId}"
	 data-stolenselected-roleType="${stolenselectedUserTypeId}"
	 session-valueTxnID="${not empty param.txnID ? param.txnID : 'null'}" 
	 data-period="${period}" data-msisdn="${msisdn}" data-imei="${imei}" data-deviceIdType="${deviceIdType}">


	<!-- START CONTENT -->
	<section id="content">
<!-- 	<div id="initialloader"></div> -->
		<!--start container-->
		<div class="container">
			<div class="section">
				<div class="row">
					<div class="col s12 m12 l12">
						<div class="row card-panel">
							<div class="container" id="CustomerDetail">
                  <div id="profile-page" class="section">

                    <div class="row">
                      <form action="">
                        <div class="col s12 m12 l12">
                          <h4 class="header2 device-info">
                            Device Information</h4>
                          <div class="row">
                            <div class="input-field col s12 m4 l4">
                              <input type="text" id="MSISDN" name="MSISDN" value="" disabled>
                              <label for="MSISDN" >MSISDN</label>
                            </div>

                            <div class="input-field col s12 m4 l4">
                              <input type="text" id="IMEI" name="IMEI" value="" disabled>
                              <label for="IMEI" >IMEI</label>
                            </div>

                            <div class="input-field col s12 m4 l4">
                              <input type="text" id="IMSI" name="IMSI" value="" disabled>
                              <label for="IMSI" >IMSI</label>
                            </div>
                          </div>
                        </div>

                        <div class="col s12 m12 l12">
                          <h4 class="header2 tac-info">
                            TAC Information</h4>
                          <div class="row">
                            <div class="input-field col s12 m3 l3">
                              <input type="text" id="handsetType" name="handsetType" value="" disabled>
                              <label for="handsetType" >Handset Type</label>
                            </div>

                            <div class="input-field col s12 m3 l3">
                              <input type="text" id="osType" name="osType" value="" disabled>
                              <label for="osType" >OS Type</label>
                            </div>

                            <div class="input-field col s12 m3 l3">
                              <input type="text" id="brand" name="brand" value="" disabled>
                              <label for="brand" >Brand</label>
                            </div>

                            <div class="input-field col s12 m3 l3">
                              <input type="text" id="modal" name="modal" value="" disabled>
                              <label for="modal" >Model</label>
                            </div>
                          </div>
                        </div>

                        <div class="col s12 m12" >
                          <h4 class="header2 device-state">
                            DEVICE STATE</h4>
                          <div class="col s12 m6">
                            <table class="responsive-table striped datatable" id="DeviceStateTable">
                              <thead>
                                <tr>
                                  <th>State</th>
                                  <th>Date</th>
                                  <th>Status</th>
                                  <th>View</th>
                                </tr>
                              </thead>
  							  <tbody style="background-color: #fff;">
                             
                              </tbody>
                            </table>
                          </div>

                          <div class="col s12 m6">
                            <table class="responsive-table striped datatable" id="DeviceTable">
                              <thead>
                                <tr>
                                  <th>Device Found In</th>
                                  <th>Date</th>
                                  <th>Status</th>
                                  <th>View</th>
                                </tr>
                              </thead>
  
                              <tbody style="background-color: #fff;">
                                
                              </tbody>
                            </table>
                          </div>
                        </div>
                        

                        <div class="col s12 m12">
                          <h4 class="header2" style="font-weight: bold; margin-top: 50px;">
                            Notification Information</h4>
                            <table class="responsive-table striped display" id="Notification-data-table" cellspacing="0">
                          
                          </table>
                        </div>
                      </form>
                    </div>
                  </div>
                </div>
						</div>

					</div>
				</div>
				<div id="footerBtn"></div>
			</div>
		</div>
		<!--end container-->
	</section>







	

 



	<!-- END MAIN -->

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
	<!--prism
    <script type="text/javascript" src="${context}/resources/resources/js/prism/prism.js"></script>-->
	<!--scrollbar-->
	<script type="text/javascript"
		src="${context}/resources/js/plugins/perfect-scrollbar/perfect-scrollbar.min.js"></script>
	<!-- chartist -->
	<%-- <script type="text/javascript" src="${context}/resources/js/plugins/chartist-js/chartist.min.js"></script> --%>

	<!-- i18n library -->
	<script type="text/javascript"
		src="${context}/resources/project_js/CLDRPluralRuleParser.js"></script>
	<script type="text/javascript"
		src="${context}/resources/i18n_library/i18n.js"></script>
	<script type="text/javascript"
		src="${context}/resources/i18n_library/messagestore.js"></script>

	<script type="text/javascript"
		src="${context}/resources/i18n_library/fallbacks.js"></script>

	<script type="text/javascript"
		src="${context}/resources/i18n_library/language.js"></script>

	<script type="text/javascript"
		src="${context}/resources/i18n_library/parser.js"></script>


	<script type="text/javascript"
		src="${context}/resources/i18n_library/emitter.js"></script>


	<script type="text/javascript"
		src="${context}/resources/i18n_library/bidi.js"></script>

	<script type="text/javascript"
		src="${context}/resources/i18n_library/history.js"></script>

	<script type="text/javascript"
		src="${context}/resources/i18n_library/min.js"></script>


	<script type="text/javascript" src="${context}/resources/project_js/globalVariables.js"></script>
	
	<script type="text/javascript"
		src="${context}/resources/project_js/customerCare.js"></script>  
			<script type="text/javascript"
		src="${context}/resources/project_js/enterKey.js"></script>
	<script type="text/javascript"
		src="${context}/resources/project_js/dragableModal.js"></script>
	<script type="text/javascript"
		src="${context}/resources/project_js/profileInfoTab.js" async></script>	
	

</body>
</html>

<%
} else {

%>
<script language="JavaScript">
sessionStorage.setItem("loginMsg",
"*Session has been expired");
window.top.location.href = "./login";
</script>
<%
}
%>