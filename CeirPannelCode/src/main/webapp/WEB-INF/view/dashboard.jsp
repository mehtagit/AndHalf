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
  <link href="./resourcesCss/css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection">
  <link href="./resourcesCss/css/style.css" type="text/css" rel="stylesheet" media="screen,projection">
  <!-- Custome CSS-->    
  <link href="./resourcesCss/css/custom/custom.css" type="text/css" rel="stylesheet" media="screen,projection">

  <!-- INCLUDED PLUGIN CSS ON THIS PAGE -->
  <link href="./resourcesCss/js/plugins/prism/prism.css" type="text/css" rel="stylesheet" media="screen,projection">
  <link href="./resourcesCss/js/plugins/perfect-scrollbar/perfect-scrollbar.css" type="text/css" rel="stylesheet" media="screen,projection">
  <link href="./resourcesCss/js/plugins/chartist-js/chartist.min.css" type="text/css" rel="stylesheet" media="screen,projection">
  <link href="./resourcesCss/font/font-awesome/css/font-awesome.css" type="text/css" rel="stylesheet" media="screen,projection">
  <link href="./resourcesCss/font/font-awesome/css/font-awesome.min.css" type="text/css" rel="stylesheet" media="screen,projection">
  
  
</head>

<body>
  <!-- Start Page Loading -->
  <div id="loader-wrapper">
      <div id="loader"></div>        
      <div class="loader-section section-left"></div>
      <div class="loader-section section-right"></div>
  </div>
  <!-- End Page Loading -->

  <!-- //////////////////////////////////////////////////////////////////////////// -->

  <!-- START HEADER -->
  <header id="header" class="page-topbar">
        <!-- start header nav-->
        <div class="navbar-fixed">
            <nav class="navbar-color">
                <h5 style="margin-left:20px;">CEIR Importer Portal</h5>
            
            	<ul class="right hide-on-med-and-down" style="overflow: inherit !important; margin-top:-50px;">
													  
									<li style="height: 64px;">
									<a  class="btn-flat dropdown-button waves-effect waves-light white-text profile-btn" href="#" data-activates="profile-dropdown" style=""><i class="mdi-action-account-circle" style="color: #fff; font-size:2.5rem"></i></a>
									<ul id="profile-dropdown" class="dropdown-content" style="    top: 50px; margin-top: 12px; width: 165px !important; position: absolute; top: 3.375px; opacity: 1; right: 0; left: none;">
                            		<li><a href="javascript:void(0)" id="" ><i class="mdi-action-settings"></i>Change Password</a></li>
                            		<li class="divider"></li>
                            		<li><a href="${context}/Logout" id="" ><i class="mdi-hardware-keyboard-tab"></i> Logout</a></li>
                        			</ul>
									</li>
								</ul>
            </nav>
            
            
}
        </div>
        
        <!-- end header nav-->
  </header>
  <!-- END HEADER -->

  <!-- //////////////////////////////////////////////////////////////////////////// -->

  <!-- START MAIN -->
  <div id="main">
    <!-- START WRAPPER -->
    <div class="wrapper">

      <!-- START LEFT SIDEBAR NAV-->
      <aside id="left-sidebar-nav">
        <ul id="slide-out" class="side-nav fixed leftside-navigation">
            <li class="user-details cyan darken-2">
            <div class="row">
                <div class="col col s4 m4 l4">
                   <!--  <img src="images/avatar.jpg" alt="" class="circle responsive-img valign profile-image"> -->
                    <p style="width: 180px;text-align: center;color: #fff;font-size: 20px;   margin-top: 2px;">
                    welcome sharad</p>
                </div>
             
            </div>
            </li>
            <li>
           <ul >
            <li class="bold"><a href="./Home" target="mainArea" class="waves-effect waves-cyan"><i class="fa fa-tachometer"></i> Dashboard</a>
            </li>
           <li><a  href="./Consignment/viewConsignment" target="mainArea"><i class="fa fa-file-text-o"></i>Consignment </a>
            </li>
            <li><a  href="./assignDistributor" target="mainArea"><i class="fa fa-file-text-o"></i>Upload Stock</a>
            </li>
            <li><a  href="./stolenRecovery" target="mainArea"><i class="fa fa-file-text-o"></i>Stolen/Stock </a>
            </li>
           </ul>
            </li>
       	
           
      
    <!--         <li class="no-padding">
                <ul class="collapsible collapsible-accordion">
                    <li class="bold"><a class="collapsible-header waves-effect waves-cyan"><i class="fa fa-file-text-o"></i> View</a>
                        <div class="collapsible-body">
                            <ul>
                                <li><a  href="./importerConsignment/1" target="mainArea"><i class="fa fa-file-text-o"></i>Consignment </a>
                                </li>
                                <li><a href="#"><i class="fa fa-file-text-o"></i>Stolen/Recovery</a>
                                </li>
                            </ul>
                        </div>
                    </li>
                </ul>
            </li> -->
         
          
         
        </ul>
        <a href="#" data-activates="slide-out" class="sidebar-collapse btn-floating btn-medium waves-effect waves-light hide-on-large-only cyan"><i class="mdi-navigation-menu"></i></a>
        </aside>
      <!-- END LEFT SIDEBAR NAV-->

      <!-- //////////////////////////////////////////////////////////////////////////// -->

      <!-- START CONTENT -->
      <section id="content">
        
        <!--breadcrumbs start-->
     
        <!--breadcrumbs end-->
        

        <!--start container-->
        <div class="container">
          <div class="section">
		
         <iframe name="mainArea" class="embed-responsive-item"scrolling="yes" frameBorder="0" src="./Home" width="100%"height="700px"></iframe>
		 </div>
          <!-- Floating Action Button -->
         
            <!-- Floating Action Button -->
        </div>
        <!--end container-->
      </section>
      <!-- END CONTENT -->

      <!-- //////////////////////////////////////////////////////////////////////////// -->
      <!-- START RIGHT SIDEBAR NAV-->
      <aside id="right-sidebar-nav">

      </aside>
      <!-- LEFT RIGHT SIDEBAR NAV-->

    </div>
    <!-- END WRAPPER -->

  </div>
  <!-- END MAIN -->



  <!-- //////////////////////////////////////////////////////////////////////////// -->

  <!-- START FOOTER -->
  <footer class="page-footer">
    <div class="footer-copyright">
      <div class="container">
        <span>Copyright © 2018 Sterlite Technologies Ltd, All rights reserved.</span>
        
        </div>
    </div>
  </footer>
  <!-- END FOOTER -->



    <!-- ================================================
    Scripts
    ================================================ -->
    
    <!-- jQuery Library -->
    <script type="text/javascript" src="./resourcesCss/js/plugins/jquery-1.11.2.min.js"></script>    
    <!--materialize js-->
    <script type="text/javascript" src="./resourcesCss/js/materialize.js"></script>
    <!--prism
    <script type="text/javascript" src="./resourcesCss/js/prism/prism.js"></script>-->
    <!--scrollbar-->
    <script type="text/javascript" src="./resourcesCss/js/plugins/perfect-scrollbar/perfect-scrollbar.min.js"></script>
    <!-- chartist -->
  <!--   <script type="text/javascript" src="./resourcesCss/js/plugins/chartist-js/chartist.min.js"></script>   
     -->
    <!--plugins.js - Some Specific JS codes for Plugin Settings-->
    <script type="text/javascript" src="./resourcesCss/js/plugins.js"></script>
    <!--custom-script.js - Add your own theme custom JS-->
    <script type="text/javascript" src="./resourcesCss/js/custom-script.js"></script>
    
  
</body>

</html>