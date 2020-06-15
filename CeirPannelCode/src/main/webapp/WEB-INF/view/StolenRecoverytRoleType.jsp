<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="context" value="${pageContext.request.contextPath}" />
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

   <!-- jQuery Library -->
    <script type="text/javascript" src="${context}/resources/js/plugins/jquery-1.11.2.min.js"></script>
    <link href="${context}/resources/js/plugins/data-tables/css/jquery.dataTables.min.css" type="text/css" rel="stylesheet"
        media="screen,projection">

    <!-- Favicons-->
    <!--<link rel="icon" href="images/favicon/favicon-32x32.png" sizes="32x32">-->
    <!-- Favicons-->
    <link rel="apple-touch-icon-precomposed" href="${context}/resources/images/favicon/apple-touch-icon-152x152.png">
    <!-- For iPhone -->
    <meta name="msapplication-TileColor" content="#00bcd4">
    <meta name="msapplication-TileImage" content="${context}/resources/images/favicon/mstile-144x144.png">
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
        
        .checkboxFont {
            font-size: 16px;
            margin-right: 10px;
        }
    </style>

</head>
       
       
       <div class="section">
                        <div class="row">
                            <div class="col s12 m12 l12">
                                <div class="row card-panel">
                                <div class="col s12 m12 l12" id="roleTypeDiv">
                                        <h5 class="center">Role Type</h5>
                                        <form action="${context}/selectModuleType" method="POST">
                                            <h5 class="center">
                                            <c:forEach items="${userTypelist}" var="userTypelist">
                                                <label>
                                                    <input type="radio" id="userTypelistId" name="modeuleType" value="${userTypelist.id}" onclick="StockController('${userTypelist.usertypeName}')" />
                                                    <span class="checkboxFont"> ${userTypelist.usertypeName}</span>
                                                </label>
                                              
                                               <%--  <label>
                                                    <input type="radio" name="modeuleType" value="Distributor" />
                                                    <span class="checkboxFont"> ${userTypeId.usertypeName}</span>
                                                </label>

                                                <label>
                                                    <input type="radio" name="modeuleType" value="Retailer"/>
                                                    <span class="checkboxFont"> ${userTypeId.usertypeName}</span>
                                                </label> --%>
                                                </c:forEach>
                                            </h5>

                                        </form>
                                    </div>

 
 
 
                                 </div>

                            </div>
                        </div>
                    </div>
<body>


    <!--materialize js-->
    <script type="text/javascript" src="${context}/resources/js/materialize.js?version=<%= (int) (Math.random() * 10) %>"></script>
    <!--prism
    <script type="text/javascript" src="js/prism/prism.js"></script>-->
    <!--scrollbar-->
    <script type="text/javascript" src="${context}/resources/js/plugins/perfect-scrollbar/perfect-scrollbar.min.js?version=<%= (int) (Math.random() * 10) %>"></script>
    <!-- chartist -->
 <%--    <script type="text/javascript" src="${context}/resources/js/plugins/chartist-js/chartist.min.js"></script>
 --%>
    <!-- data-tables -->
    <script type="text/javascript" src="${context}/resources/js/plugins/data-tables/js/jquery.dataTables.min.js?version=<%= (int) (Math.random() * 10) %>"></script>
    <script type="text/javascript" src="${context}/resources/js/plugins/data-tables/data-tables-script.js?version=<%= (int) (Math.random() * 10) %>"></script>

    <!--plugins.js - Some Specific JS codes for Plugin Settings-->
    <script type="text/javascript" src="${context}/resources/js/plugins.js?version=<%= (int) (Math.random() * 10) %>"></script>
    <!--custom-script.js - Add your own theme custom JS-->
    <script type="text/javascript" src="${context}/resources/js/custom-script.js?version=<%= (int) (Math.random() * 10) %>"></script>
    

  <script>
    function StockController(selectedRoleTyple){
    	var url="${context}/stolenRecovery?userTypeId="+selectedRoleTyple;
    	console.log(url);
    	window.location.href=url;
   
    }   
    
    
    </script>
</body>
</html>