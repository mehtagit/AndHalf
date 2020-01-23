$(document).ready(function () {
	dataByTag("copyright_footer","copyrightText",2);
     });   


//var lang=$('#langlist').val() == 'km' ? 'km' : 'en';
/* $('#langlist').on('change', function() {
	window.location.assign("CeirPanelCode/importerDashboard?lang="+$('#langlist').val());
	
}); 
 */

var password = document.getElementById("password");
var confirm_password = document.getElementById("confirm_password");

function validatePassword(){
if(password.value != confirm_password.value) {
  confirm_password.setCustomValidity("Passwords Don't Match");
} else {
  confirm_password.setCustomValidity('');
}
}   
password.onchange = validatePassword;
confirm_password.onkeyup = validatePassword;
console.log($("#cierRoletype").text());
var cierRoletype = $("#cierRoletype").text()
sessionStorage.setItem("cierRoletype", cierRoletype);
$(".navData li:first").addClass("active");
$('.navData li').on('click', function() {
$('.navData li:not(.inactive)').addClass("inactive");
$('.navData li').removeClass("active");
$(this).removeClass("inactive");
$(this).addClass("active"); 
});
