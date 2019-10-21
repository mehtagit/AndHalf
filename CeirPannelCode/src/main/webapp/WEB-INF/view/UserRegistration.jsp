<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="./resourcesCss/css/custom/custom.css">
<link rel="stylesheet" href="./resourcesCss/css/select2-materialize.css">
<link rel="stylesheet" href="./resourcesCss/css/style.css">
<link rel="stylesheet" href="./resourcesCss/css/materialize.css">
<title>Login Page</title>

<style>
	ul li {
		display: inline;
		padding: 10px;
	}
</style>

</head>
<body>
     
     <div id="login-page" class="row"  style="margin-top: 100px; ">
      <div class="col s12 card-panel" style="width: 39%; height: 450px; margin: auto;     margin-left: 29%;">
        <form class="login-form" action="./Dashboard"  modelAttribute="userForm" method="POST" style="width: 500px; height: 350px; margin: auto;">
        
          <div class="row">
            <div class="input-field col s12 center">            
              <h5  id="welcomeMsg">Welcome to CEIR</h5>
            </div>
          </div>
          
             <div class="row" style="margin-top: 30px;">
            <div class="input-field col s12">
              <i class="mdi-action-lock-outline prefix" style="font-size: 30px;"></i>
          <!--     <label for="password" class="center-align" style="font-size: 12px; color: #444;">USertype</label> -->
               <select id="CEIRuserList"  name="usertype" class="select2 form-control boxBorder boxHeight" style="width: 455px !important; margin-top: -9px;     margin-left: 44px;    border-color: #0059bc;">
              <option value="" >Select User Type</option>
                <option value="admin">Admin</option>
                <option value="Importer">importer</option>
                <option value="custom">custom</option>
				
            </select>
              <div class="password"></div>
            </div>
          </div>  
          
          <div class="row margin">
            <div class="input-field col s12">
              <i class="mdi-social-person-outline prefix" style="font-size: 30px;"></i>
           <!--    <label for="username" class="center-align" style="font-size: 12px; color: #444;">Username</label> -->
              <input  type="text"  class="validate" name="username" id="username" autofocus />
              <div class="username"></div>
            </div>
          </div>
          <div class="row" style="margin-top: 30px;">
            <div class="input-field col s12">
              <i class="mdi-action-lock-outline prefix" style="font-size: 30px;"></i>
        <!--       <label for="password" class="center-align" style="font-size: 12px; color: #444;">Password</label> -->
              <input  name="password" type="password" class="validate" name="password" id="password"  />
              <div class="password"></div>
            </div>
          </div>   
               
          <div class="row">
            <div class="input-field col s12 center">
              <button type="submit" class="btn waves-effect waves-light" style="width: 150px;">Login</button>
            </div>
          </div>        
        </form>
      </div>
  </div>
<!--   <div id="ceirStartPage" >
    <div class="row">
      <div class="col s12 m12 l12">
        <h1 style="text-align: center;">Welcome To Central Equipment Identity Register</h1>
        <img src="./resources/images/TELECOMMUNICATIONS-4.jpg" alt="" class="responsive-img">
      </div>
    </div>
  
    <div class="row">
      <div class="col s12 m12 l12">
  
        <ul class="haed-btn">
          <li class="haed-btn-style"><a href="javascript:void(0)" data-activates="chat-out"  style="color: #000;">Login</a></li>
          <li class="haed-btn-style"><a  onclick="_Services._displayRegistrationPage();" style="color: #000;">Registration</a></li>
          <li class="haed-btn-style"><a href="checkIMEI.html" style="color: #000;">Check IMEI</a></li>
          <li class="haed-btn-style"><a href="uploadDocument.html" style="color: #000;">Upload Document</a></li>
          <li class="haed-btn-style"><a href="RegisterComplaint.html" style="color: #000;">Register Complaint</a></li>
          <li class="haed-btn-style"><a href="GreibanceHandling.html" style="color: #000;">Report Grievance</a></li>
        
          <li>
             <div class="col s12 m6 l6" >
         												
            
            <select id="CEIRuserList"   class="select2 form-control boxBorder boxHeight" style="width: 200px !important; margin-top: -9px;    border-color: #0059bc;">
              <option value="" disabled selected>Select User</option>
              <option value="importer">importer</option>
                <option value="importer">custom</option>
            </select>
          </div>
          </li>
           
          
          
        </ul>
        
        
        
       
      </div>
    </div>
  </div> -->
</body>

</html>