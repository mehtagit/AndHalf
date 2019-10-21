<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script type="text/javascript" src="${context}/resourcesCss/js/plugins/jquery-1.11.2.min.js"></script> 
<link rel="stylesheet" href="./resourcesCss/css/custom/custom.css">
<link rel="stylesheet" href="./resourcesCss/css/select2-materialize.css">
<link rel="stylesheet" href="./resourcesCss/css/style.css">
<link rel="stylesheet" href="./resourcesCss/css/materialize.css">
<title>Login Page</title>

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
  height:50px;
 line-height:0px;
 
 }

#snackbar.show {
  visibility: visible;
  -webkit-animation: fadein 0.5s, fadeout 0.5s 2.5s;
  animation: fadein 0.5s, fadeout 0.5s 2.5s;
}

	ul li{
            display: inline;
        }
        li{
            padding: 7px 15px;
            border: solid 1px #c9c9c9;
            border-radius: 5px;
            margin-right: 10px;
        }
</style>

</head>
<body>
     
    

<section id="content" >
        <!--start container-->
        <div class="container" >
            <div class="section" id="mainPage" style="display: block;">
                <div class="row">
                    <div class="col s12 m12 l12">
                        <h1 style="text-align: center;">Welcome To Central Equipment Identity Register</h1>
                        <img src="resourcesCss/images/TELECOMMUNICATIONS-4.jpg" alt="" class="responsive-img">
                    </div>
                </div>

                <div class="row">
                    <div class="col s12 m12 l12">

                        <ul class="haed-btn">
                            <li class="haed-btn-style"><a href="login.html" style="color: #000;">Login</a></li>
                            <li class="haed-btn-style"><a onclick="displayRegistrationPage()"
                                    style="color: #000;">Registration</a></li>
                            <li class="haed-btn-style"><a href="#" style="color: #000;">Check IMEI</a></li>
                            <li class="haed-btn-style"><a href="#" style="color: #000;">Upload
                                    Document</a></li>
                            <li class="haed-btn-style"><a href="#" style="color: #000;">Register
                                    Complaint</a></li>
                            <!-- <li class="haed-btn-style"><a href="#" style="color: #000;">Report
                                    Grievance</a></li> -->
                            <!-- <li class="haed-btn-style">
                                    <a class='dropdown-trigger btn' href='#' data-target='dropdown1'>Select user</a>
                                <ul id='dropdown1' class='dropdown-content'>
                                    <li><a href="#!">one</a></li>
                                    <li><a href="#!">two</a></li>
                                    <li class="divider" tabindex="-1"></li>
                                  </ul>
                            </li> -->
                        </ul>

                    </div>
                </div>
            </div>
            
            
              <div class="section" id="registrationPageId" style="display: none;">
                <form id="registrationForm">
                    <div class="card-panel" style="width: 90%; margin: auto; padding: 20px 5% 20px 5%;">
                        <div class="row">
                            <h5>Registration</h5>
                            <hr>
                            <div class="row">
                                <div class="input-field col s12 m4 l4">
                                    <input type="text" name="firstName" id="firstName" maxlength="14">
                                    <label for="firstName" class="center-align">First Name*</label>
                                </div>

                                <div class="input-field col s12 m4 l4">
                                    <input type="text" name="middleName" class="form-control boxBorder boxHeight"
                                        id="middleName" maxlength="14">
                                        <label for="middleName">Middle Name</label>
                                </div>

                                <div class="input-field col s12 m4 l4">
                                    <input type="text" name="lastName" class="form-control boxBorder boxHeight"
                                        id="lastName" maxlength="14">
                                        <label for="lastName">Last Name*</label>
                                </div>

                                <div class="input-field col s12 m6 l6">
                                    <input type="text" name="passportNo" class="form-control boxBorder boxHeight"
                                        id="passportNo" maxlength="14">
                                        <label for="passportNumber">Passport Number*</label>
                                </div>

                                <div class="input-field col s12 m6 l6">
                                    <input type="text" name="email" class="form-control boxBorder boxHeight" id="emailId"
                                        maxlength="30">
                                        <label for="email">Email*</label>
                                </div>

                                <div class="input-field col s12 m6 l6">
                                    <input type="text" name="phoneNo" class="form-control boxBorder boxHeight" id="phoneNo"
                                        maxlength="10">
                                        <label for="phone">Phone Number*</label>
                                </div>

                                <div class="input-field col s12 m6 l6">
                                    <input type="text" name="companyName" class="form-control boxBorder boxHeight"
                                        id="companyName" maxlength="30">
                                        <label for="company">Company Name*</label>
                                </div>
                            </div>

                            <div class="row">
                                <div class="input-field col s12 m12 l12">
                                    <input type="text" name="propertyLocation" class="form-control boxBorder boxHeight"
                                        id="propertyLocation">
                                        <label for="address">Address(Property Location)*</label>
                                </div>

                                <div class="input-field col s12 m6 l6">
                                    <input type="text" name="street" class="form-control boxBorder boxHeight"
                                        id="street" maxlength="30">
                                        <label for="streetNumber">Street Number*</label>
                                </div>

                                <div class="input-field col s12 m6 l6">
                                    <input type="text" name="locality" class="form-control boxBorder boxHeight"
                                        id="locality" maxlength="20">
                                        <label for="locality">Locality*</label>
                                </div>

                                <div class="input-field col s12 m6 l6">
                                    <input type="text" name="province" class="form-control boxBorder boxHeight"
                                        id="province" maxlength="20">
                                        <label for="province">Province*</label>
                                </div>

                                <div class="input-field col s12 m6 l6">
                                    <input type="text" name="country" class="form-control boxBorder boxHeight"
                                        id="country" maxlength="20">
                                        <label for="country">Country*</label>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col s12 m6 l6" style="margin-bottom: 20px;">
                                    <label for="vatNumber">VAT Registration*</label>
                                    <div class=" boxHeight">
                                        <input class="with-gap" name="group3" type="radio"
                                            onclick="document.getElementById('vatNumberField').style.display = 'block';">
                                        Yes
                                        <input class="with-gap" name="group3" type="radio" style="margin-left: 20px;"
                                            onclick="document.getElementById('vatNumberField').style.display = 'none';"
                                            checked />
                                        No
                                    </div>
                                </div>

                                <div class="input-field col s12 m6 l6" style="display: none;" id="vatNumberField">
                                    <input type="text" name="vatNo" class="form-control boxBorder boxHeight"
                                        id="vatNo" maxlength="16">
                                        <label for="roleType">VAT Number*</label>
                                </div>
                            </div>

                            <div class="row">
                                <div class="input-field col s12 m6 l6">
                                    <input type="text" name="roleType" class="form-control boxBorder boxHeight"
                                        id="roleType" maxlength="14">
                                        <label for="roleType">Role Type*</label>
                                </div>

                                <div class="input-field col s12 m6 l6">
                                    <!-- <label for="type">Type*</label> -->
                                    <!-- <select id="typeId">
                                        <option value="" disabled selected>type</option>
                                        <option value="Paid" disabled>Individual</option>
                                        <option value="NotPaid">Company</option>
                                        <option value="NotPaid">Organization</option>
                                        <option value="NotPaid">Government</option>
                                    </select> -->

                                    <select class="browser-default" name="type"id="type" required>
                                        <option value="" disabled selected>type</option>
                                        <option value="Paid" disabled>Individual</option>
                                        <option value="NotPaid">Company</option>
                                        <option value="NotPaid">Organization</option>
                                        <option value="NotPaid">Government</option>
                                    </select>
                                </div>
                            </div>

                            <div class="row">
                                <div class="input-field col s12 m6 l6">
                                    <input type="password" name="password" class="form-control boxBorder boxHeight"
                                        id="password" maxlength="14">
                                        <label for="password">Password*</label>
                                </div>

                                <div class="input-field col s12 m6 l6">
                                    <input type="password" name="rePassword" class="form-control boxBorder boxHeight"
                                        id="rePassword" maxlength="14">
                                        <label for="rePassword">Retype Password*</label>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <span> Required Field are marked with *</span>
                            <div class="input-field col s12 center">
                                <button type="button" onclick="registerConsignment()" class="btn" id="btnSave"> Submit</button>
                                <!-- <button class="btn" type="button" id="btnUpdate" style="display:none;">Update</button> -->
                                <button class="btn" type="reset" name="cancel_user" id="add_user" style="margin-left: 10px;">Reset</button>
                            </div>
                        </div>
			</div>
                </form>
            </div>
        </div>  
            
         <div id="snackbar"><p id="errorMessage"></p></div>   
            
            
            
             <div class="row card-panel" id="loginformId" style="width: 45%; display:none; height: 55vh; margin: auto; margin-top: 10vh;">
                            <div class="col s12 m12 l12">
                                <div class="row">
                                    <h5 style="text-align: -webkit-center;">Login</h5> <hr style="margin-bottom: 30px;">
                                    
                                    <!-- <div class="col s1"><i class="fa fa-lock" aria-hidden="true" style="font-size: 30px; margin-top: 12px; color: #ff4081;"></i></div> -->
                                    <!-- <div class="input-field col s12">
                                        <select class="browser-default">
                                            <option value="" disabled selected>User type</option>
                                            <option value="Air">Importer</option>
                                            <option value="Land">Distributor</option>
                                            <option value="Water">Retailer</option>
                                        </select>
                                    </div> -->
                
                                    <!-- <div class="col s1">
                                        <span class="fa-passwd-reset fa-stack" style="margin-top: 12px; color: #ff4081;">
                                            <i class="fa fa-undo fa-stack-2x"></i>
                                            <i class="fa fa-lock fa-stack-1x"></i>
                                        </span></div> -->
                                    <div class="input-field col s12">
                                        
                                        <label for="newPassword" style="color: #000; font-size: 12px;">Username</label>
                                        <input type="text" id="newPassword" class="" maxlength="10" />
                                    </div>
                
                                    <!-- <div class="col s1"><i class="fa fa-check-square-o" aria-hidden="true" style="font-size: 28px; margin-top: 12px; color: #ff4081;;"></i></div> -->
                                    <div class="input-field col s12">
                                        
                                        <label for="confirmPassword" style="color: #000; font-size: 12px;">Password</label>
                                        <input type="text" class="" id="confirmPassword" maxlength="10" />
                                    </div>
                                    <a href="" class="right"> Forget Password?</a>
                                </div>
                                <div class="row" style="margin-top: 30px;">
                                    <div class="input-field col s12 m12 l12 center">
                                        <a href="index.html" class="btn" type="button" id="save" style="width: 108px;">Login</a>
                                    </div>
                                </div>

                            </div>
                        </div>
       
        <!--end container-->
    </section>
    

 <script type="text/javascript" src="${context}/resourcesCss/js/materialize.js"></script>
    <!--prism
    <script type="text/javascript" src="js/prism/prism.js"></script>-->
    <!--scrollbar-->
    <script type="text/javascript" src="${context}/resourcesCss/js/plugins/perfect-scrollbar/perfect-scrollbar.min.js"></script>
	<script type="text/javascript" src="${context}/resourcesCss/js/Validator.js"></script>
    <!--plugins.js - Some Specific JS codes for Plugin Settings-->
    <script type="text/javascript" src="${context}/resourcesCss/js/plugins.js"></script>
    <!--custom-script.js - Add your own theme custom JS-->
    <script type="text/javascript" src="${context}/resourcesCss/js/custom-script.js"></script>

        
    <script type="text/javascript">
    function displayRegistrationPage(){
    	document.getElementById("mainPage").style.display = "none";
    	document.getElementById("registrationPageId").style.display = "block";
    	
    }
    
    function registerConsignment()
    {
    	 if(!isAlphabateic($('#firstName').val()))
		 {
		 
		 myFunction("First name must be in alphabetic");
		 return false;
		 }
    	 else if($('#firstName').val()=='')
		 {
		 
		 myFunction("name can not be blank");
		 return false;
		 }
    	 else if(!isAlphabateic($('#middleName').val()))
		 {
		 
		 myFunction("middle name must be in alphabetic");
		 return false;
		 } 
    	 else if(!isAlphabateic($('#lastName').val()))
		 {
		 
		 myFunction("last name must be in alphabetic");
		 return false;
		 } 
    	 
    	 else if(($('#lastName').val()==''))
		 {
		 
		 myFunction("last name can not be blank");
		 return false;
		 }
    	 
    	 
    	 else if(($('#passportNo').val()==''))
		 {
		 
		 myFunction("passport number can not be blank");
		 return false;
		 }
    	 
    	 else if(!isAlphanumericValue($('#passportNo').val()))
		 {
		 
		 myFunction("passport number must be in alpanumeric");
		 return false;
		 }
    	 
    	 else if($('#phoneNo').val()=='')
		 {
		 
		 myFunction("phone number  can not be blank");
		 return false;
		 }
    	 else if(!isNumericValue($('#phoneNo').val()))
		 {
		 
		 myFunction("phone number must be in numeric");
		 return false;
		 }
    	 else if(!isEmail($('#emailId').val()))
		 {
		 
		 myFunction("email must be in 'abc@gmail.com' fomrate");
		 return false;
		 }
    	 else if($('#emailId').val()=='')
		 {
		 
		 myFunction("email can not be blank");
		 return false;
		 }
    	 
    	 else if($('#companyName').val()=='')
		 {
		 
		 myFunction("company name  can not be blank");
		 return false;
		 }
    	 
    	 else if(!isAlphanumericValue($('#companyName').val()))
		 {
		 
		 myFunction("company name  can not be blank");
		 return false;
		 }
    	 
    	 else if(!isAlphanumericValue($('#companyName').val()))
		 {
		 
		 myFunction("company name  must be  alphanumeric");
		 return false;
		 }
    	 else if(!isAlphanumericValue($('#propertyLocation').val()))
		 {
		 
		 myFunction("property location must be in alphanumeric");
		 return false;
		 }
    	 
    	 else if($('#propertyLocation').val()=='')
		 {
		 
		 myFunction("property location can not be blank");
		 return false;
		 }
    	 
    	 else if($('#street').val()=='')
		 {
		 
		 myFunction("street can not be blank");
		 return false;
		 }
    	 
    	 else if(!isAlphanumericValue($('#street').val()))
		 {
		 
		 myFunction("Street must be in alphanumeric");
		 return false;
		 }
    	 
    	 else if(!isAlphanumericValue($('#locality').val()))
		 {
		 
		 myFunction("locality must be in alphanumeric.");
		 return false;
		 }
    	 
    	 else if($('#locality').val()=='')
		 {
		 
		 myFunction("locality can not be blank");
		 return false;
		 }
    	 
    	 else if(!isAlphanumericValue($('#locality').val()))
		 {
		 
		 myFunction("locality must be in alphanumeric  ");
		 return false;
		 }
    	 
    	 else if($('#province').val()=='')
		 {
		 
		 myFunction("province can not be blank");
		 return false;
		 }
    	 else if(!isAlphanumericValue($('#province').val()))
		 {
		 
		 myFunction("province must be in alphanumeric  ");
		 return false;
		 }
    	 
    	
    	 else if($('#country').val()=='')
		 {
		 
		 myFunction("country can not be blank");
		 return false;
		 }
    	 
    	 else if(!isNumericValue($('#vatNo').val()))
		 {
		 
		 myFunction("vat number must be in numeric  ");
		 return false;
		 }
    	 else if($('#type').val()=='')
		 {
		 
		 myFunction("type can not be blank.");
		 return false;
		 } 
    	 
    	 else if($('#password').val()=='')
		 {
		 
		 myFunction("password can not be blank.");
		 return false;
		 } 
    	 
    	 else if(!isAlphanumericValue($('#password').val()))
		 {
		 
		 myFunction("password must be in alphanumeric  ");
		 return false;
		 }
    	 
    	 else if($('#password').val()!=$('#rePassword').val())
		 {
		 
		 myFunction("password and rePassword not matched.");
		 return false;
		 }
    	 
    	 alert("validation succes");
    	
		
	}
    
    
    function myFunction(message) {
  	  var x = document.getElementById("snackbar");
  	  x.className = "show";
  	  $('#errorMessage').html(message);
  	  setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
  	}
    </script>
</body>

</html>