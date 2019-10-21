<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>CEIR</title> 
<link rel="stylesheet" href="./resources/css/register/custom.css">
<link rel="stylesheet" href="./resources/css/register/materialize.css">
<link rel="stylesheet" href="./resources/css/register/select2-materialize.css">
<link rel="stylesheet" href="./resources/css/register/style.css">

</head>
<style>
    .form-control {
        width: 100% !important;
        background-color: #fbfeff;
    }

    label{
        font-weight: normal;
        margin-bottom: 0;
    }

    input[type=text],
    input[type=password],
    input[type=email],
    input[type=url],
    input[type=time],
    input[type=date],
    input[type=datetime-local],
    input[type=tel],
    input[type=number],
    input[type=search],
    textarea.materialize-textarea {
        border: 1px solid #e0e0e0;
        background-color: #fbfeff;
        padding-left: 5px;
        border-radius: 3px;
        height: 28px;
        margin: 0 0 8px 0;
        font-size: 13px;
    }

    .card-panel {
        box-shadow: none;
        width: 80%;
        margin-left: 10%;
    }
</style>
<body>

<div class="conatainer section">
    <form id="registrationForm" method="post" action="./saveUserRegister">
        <div class="card-panel">
            <div class="row">
                <div class="row">
                    <div class="col s12 m4 l4">
                        <label for="firstName">First Name*</label>
                        <input type="text" name="firstName" class="form-control boxBorder boxHeight" id="firstName"
                            maxlength="14">
                    </div>

                    <div class="col s12 m4 l4">
                        <label for="middleName">Middle Name</label>
                        <input type="text" name="middleName" class="form-control boxBorder boxHeight" id="middleName"
                            maxlength="14">
                    </div>

                    <div class="col s12 m4 l4">
                        <label for="lastName">Last Name*</label>
                        <input type="text" name="lastName" class="form-control boxBorder boxHeight" id="lastName"
                            maxlength="14">
                    </div>

                    <div class="col s12 m6 l6">
                        <label for="passportNumber">Passport Number*</label>
                        <input type="text" name="passportNo" class="form-control boxBorder boxHeight"
                            id="passportNumber" maxlength="14">
                    </div>

                    <div class="col s12 m6 l6">
                        <label for="email">Email*</label>
                        <input type="text" name="email" class="form-control boxBorder boxHeight" id="email"
                            maxlength="30">
                    </div>

                    <div class="col s12 m6 l6">
                        <label for="phone">Phone Number*</label>
                        <input type="text" name="phoneNo" class="form-control boxBorder boxHeight" id="phone"
                            maxlength="10">
                    </div>

                    <div class="col s12 m6 l6">
                        <label for="company">Company Name*</label>
                        <input type="text" name="companyName" class="form-control boxBorder boxHeight" id="company"
                            maxlength="30">
                    </div>
                </div>

                <div class="row">
                    <div class="col s12 m12 l12">
                        <label for="address">Address(Property Location)*</label>
                        <input type="text" name="propertyLocation" class="form-control boxBorder boxHeight" id="address">
                    </div>

                    <div class="col s12 m6 l6">
                        <label for="streetNumber">Street Number*</label>
                        <input type="text" name="street" class="form-control boxBorder boxHeight"
                            id="streetNumber" maxlength="30">
                    </div>

                    <div class="col s12 m6 l6">
                        <label for="locality">Locality*</label>
                        <input type="text" name="locality" class="form-control boxBorder boxHeight" id="locality"
                            maxlength="20">
                    </div>

                    <div class="col s12 m6 l6">
                        <label for="province">Province*</label>
                        <input type="text" name="province" class="form-control boxBorder boxHeight" id="province"
                            maxlength="20">
                    </div>

                    <div class="col s12 m6 l6">
                        <label for="country">Country*</label>
                        <input type="text" name="country" class="form-control boxBorder boxHeight" id="country"
                            maxlength="20">
                    </div>
                </div>

                <div class="row">
                    <div class="col s12 m6 l6" style="margin-bottom: 20px;">
                        <label for="vatNumber">VAT Registration*</label>
                        <div class=" boxHeight">  
                            <input class="with-gap" name="vatStatus" type="radio"
                                onclick="document.getElementById('vatNumberField').style.display = 'block';"> Yes
                            <input class="with-gap" name="vatStatus" type="radio" style="margin-left: 20px;"
                                onclick="document.getElementById('vatNumberField').style.display = 'none';" checked />
                            No
                        </div>
                    </div>

                    <div class="col s12 m6 l6" style="display: none;" id="vatNumberField">
                        <label for="roleType">VAT Number*</label>
                        <input type="text" name="vatNo" class="form-control boxBorder boxHeight" id="vatNumber"
                            maxlength="16">
                    </div>
                </div>

                <div class="row">
                    <div class="col s12 m6 l6">
                        <label for="roleType">Role Type*</label>
                       <!--  <input type="text" name="roles" class="form-control boxBorder boxHeight" id="roleType"
                            maxlength="14"> -->
                      <select id="roleType" name="roles" class="select2 form-control boxBorder boxHeight" style="margin-top: 0;">
                      <option value="">select Role </option>
                      <c:forEach items="${usertypes}" var="usertype">
                      <option value="${usertype.id}">${usertype.usertype}</option>
                      </c:forEach>
                       
                     
                      </select>      
                    </div>

                    <div class="col s12 m6 l6">
                        <label for="type">Type*</label>
                        <select id="typeId" class="select2 form-control boxBorder boxHeight" style="margin-top: 0;">
                            <option value="" disabled selected>type</option>
                            <option value="Paid">Individual</option>
                            <option value="NotPaid">Company</option>
                            <option value="NotPaid">Organization</option>
                            <option value="NotPaid">Government</option>
                        </select>
                    </div>
                </div>

                <div class="row">
                    <div class="col s12 m6 l6">
                        <label for="password">Password*</label>
                        <input type="password" name="password" class="form-control boxBorder boxHeight" id="password"
                            maxlength="14">
                    </div>

                    <div class="col s12 m6 l6">
                        <label for="rePassword">Retype Password*</label>
                        <input type="password" name="rePassword" class="form-control boxBorder boxHeight"
                            id="rePassword" maxlength="14">
                    </div>
                </div>
            </div>

            <div class="row">
                <span> Required Field are marked with *</span>
                <div class="input-field col s12 center">
                    <button  class="btn" type="submit" name="btnSave" id="btnSave">
                        Submit</button> 
                   <!--  <button class="btn" type="button" id="btnUpdate" onclick="_Services._saveDistributar()"
                        style="display:none;">Update</button> -->
                    <button class="btn" type="reset" name="cancel_user" id="add_user">Reset</button>
                </div>
            </div>
</div>
    </form>

</div>
</body>
