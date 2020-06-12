<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>  
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="msapplication-tap-highlight" content="no">
    <meta name="description"
        content="Materialize is a Material Design Admin Template,It's modern, responsive and based on Material Design by Google. ">
    <meta name="keywords"
        content="materialize, admin template, dashboard template, flat admin template, responsive admin template,">
    <title>CEIR | Importer Portal</title>

    <link href="${context}/resources/js/plugins/data-tables/css/jquery.dataTables.min.css" type="text/css" rel="stylesheet"
        media="screen,projection">
<jsp:include page="/WEB-INF/view/endUserHeader.jsp" ></jsp:include>
<jsp:include page="/WEB-INF/view/endUserFooter.jsp" ></jsp:include>
    <!-- Favicons-->
    <!--<link rel="icon" href="images/favicon/favicon-32x32.png" sizes="32x32">-->
    <!-- Favicons-->
    <link rel="apple-touch-icon-precomposed" href="images/favicon/apple-touch-icon-152x152.png">
    <!-- For iPhone -->
    <meta name="msapplication-TileColor" content="#00bcd4">
    <meta name="msapplication-TileImage" content="images/favicon/mstile-144x144.png">
    <!-- For Windows Phone -->
    <link rel="stylesheet" href="${context}/resources/font/font-awesome/css/font-awesome.min.css">

    <!-- CORE CSS-->
    <link href="${context}/resources/css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection">
    <link href="${context}/resources/css/style.css" type="text/css" rel="stylesheet" media="screen,projection">
    <!-- Custome CSS-->
    <link href="${context}/resources/css/custom/custom.css" type="text/css" rel="stylesheet" media="screen,projection">

    <!-- INCLUDED PLUGIN CSS ON THIS PAGE -->
    <link href="${context}/resources/js/plugins/prism/prism.css" type="text/css" rel="stylesheet" media="screen,projection">
    <link href="${context}/resources/js/plugins/perfect-scrollbar/perfect-scrollbar.css" type="text/css" rel="stylesheet"
        media="screen,projection">
    <link href="${context}/resources/js/plugins/chartist-js/chartist.min.css" type="text/css" rel="stylesheet" media="screen,projection">
   <link rel="stylesheet"
	href="${context}/resources/project_css/iconStates.css">
    <style>
 /*        ul li {
            display: inline-flex;
        }

        li {
            padding: 7px 15px;
            border: solid 1px #c9c9c9;
            border-radius: 5px;
            margin-right: 10px;
        }
        select{
            height: 1.5rem;
           /*  border: none; */
        }
        footer {
            padding-left: 0;
        }
        
         /* body {
            background-color: #00bcd4;
        } */

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
            margin-top: 0;
        }

        textarea.materialize-textarea {
            padding: 0;
        }

        input[type=text] {
           /*  height: 35px; */
            margin: 0 0 5px 0;
        }

        footer {
            padding-left: 0;
        }

        .card-panel {
            width: 50%;
            margin-top: 5vh;
        }

        @media only screen  and (max-width: 992px){
            .card-panel {
            width: 100%;
        } 
        }

        #content .container .row {
            margin: auto;
        }

        @media only screen and (max-width: 425px){
            .selectDropdwn {
                margin-top: 0px;
            }

            .selectDropdwn label {
                margin-left: 2%;
            }
        }

        @media only screen and (max-width: 425px) {
            select {
                width: 96%;
                margin-left: 2%;
            }
        }

        .small-modal {
            width: 40%;
        }

        @media only screen and (max-width: 992px) {
            .small-modal {
            width: 70%;
        }
        }

        @media only screen and (max-width: 600px) {
            .small-modal {
            width: 95%;
        }
        }
        .selectDropdwn {
   margin-top: 0;
}
 div#trackGrievanctableDiv {
    width: 70%;
} 
.backdrop {
	display: none !important;
}



    </style>
<script>
var contextpath = "${context}";
</script>

</head>
<body data-lang-param="${pageContext.response.locale}">
         <!-- START CONTENT -->
            <section id="content">
            <div id="initialloader"></div>
                <!--start container-->
                <div class="container" style="padding-bottom: 70px; margin-top:5.5vh;" >
                    <div class="section">
                        <div class="row card-panel responsive-page" id="endUserRaiseGrievance" style="display:block !important">
                            <h6 class="fixPage-modal-header "><spring:message code="modal.Grievance" /></h6>
                            <div style="display:flex">
                            <div class="col-xl-8 col-lg-7">
              <div class="card shadow mb-4">
                <!-- Card Header - Dropdown -->
                <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                  <h6 class="m-0 font-weight-bold text-primary">Graph Title Name 1</h6>
                  <div class="dropdown no-arrow">
                    <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                      <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
                    </a>
                   
                  </div>
                </div>
                <!-- Card Body -->
                <div class="card-body">
                  <div class="chart-area">
                  
                  </div>
                </div>
              </div>
            </div>
             
             
             
             
             <div class="col-xl-8 col-lg-7">
              <div class="card shadow mb-4">
                <!-- Card Header - Dropdown -->
                <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                  <h6 class="m-0 font-weight-bold text-primary">Graph Title Name 2</h6>
                  <div class="dropdown no-arrow">
                    <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                      <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
                    </a>
                   
                  </div>
                </div>
                <!-- Card Body -->
                <div class="card-body">
                  <div class="chart-area">
                  
                  </div>
                </div>
              </div>
            </div>               
             </div>               
                            
                        </div>
            </div></div></section>            
                        
                          
    <!-- ================================================
    Scripts
    ================================================ -->
 <!-- jQuery Library -->
    <%-- <script type="text/javascript" src="${context}/resources/js/plugins/jquery-1.11.2.min.js"></script> --%>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.1/jquery.min.js"></script>
       <!-- ajax js -->
    <script type="text/javascript" src="${context}/resources/ajax/Registration.js"></script>
    <!--materialize js-->
    <script type="text/javascript" src="${context}/resources/js/materialize.js"></script>
    <!--prism
    <script type="text/javascript" src="${context}/resources/js/prism/prism.js"></script>-->
    <!--scrollbar-->
    <script type="text/javascript" src="${context}/resources/js/plugins/perfect-scrollbar/perfect-scrollbar.min.js"></script>
    <!-- chartist -->
    <script type="text/javascript" src="${context}/resources/js/plugins/chartist-js/chartist.min.js"></script>
<!-- i18n library -->
	<script type="text/javascript">
var path="${context}";
</script>

	<script type="text/javascript"
		src="${context}/resources/project_js/CLDRPluralRuleParser.js"></script>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.i18n/1.0.7/jquery.i18n.js"></script>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.i18n/1.0.7/jquery.i18n.messagestore.js"></script>

	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.i18n/1.0.7/jquery.i18n.fallbacks.js"></script>

	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.i18n/1.0.7/jquery.i18n.language.js"></script>

	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.i18n/1.0.7/jquery.i18n.parser.js"></script>


	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.i18n/1.0.7/jquery.i18n.emitter.js"></script>


	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.i18n/1.0.7/jquery.i18n.emitter.bidi.js"></script>

	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/history.js/1.8/bundled/html4+html5/jquery.history.js"></script>

	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/js-url/2.5.3/url.min.js"></script>

    <!-- data-tables -->
    <script type="text/javascript" src="${context}/resources/js/plugins/data-tables/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="${context}/resources/js/plugins/data-tables/data-tables-script.js"></script>

    <!--plugins.js - Some Specific JS codes for Plugin Settings-->
    <script type="text/javascript" src="${context}/resources/js/plugins.js"></script>
    <!--custom-script.js - Add your own theme custom JS-->
    <script type="text/javascript" src="${context}/resources/js/custom-script.js"></script>
    
    <script type="text/javascript"
		src="${context}/resources/project_js/grievanceManagement.js"></script>
    <script type="text/javascript"
		src="${context}/resources/project_js/endUserGrievance.js"></script>
	
			<script type="text/javascript"
		src="${context}/resources/project_js/_dateFunction.js" async></script>
		<script type="text/javascript"
		src="${context}/resources/project_js/profileInfoTab.js" async></script>
	<script type="text/javascript" src="${context}/resources/project_js/globalVariables.js"></script>
<script type="text/javascript"
		src="${context}/resources/project_js/ValidationFileOutsidePortal.js"></script>
    

  <script type="text/javascript">
  $('div#initialloader').delay(300).fadeOut('slow');
  </script> 
</body>
</html>