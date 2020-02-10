$('#langlist').on('change', function() {
	var lang=$('#langlist').val(); 
	sessionStorage.setItem("sessionLang", lang);
	$('#mainArea').attr('src', function () {
		currentPageLocation=$(this).contents().get(0).location;
		changeLanguage(lang);
		sessionStorage.setItem("a", currentPageLocation);
	});    
	window.location.replace("importerDashboard?lang="+lang);
}); 




var intialController=sessionStorage.getItem("a") == null ? "./Home" : sessionStorage.getItem("a");
$(document).ready(function () {
	//var DB_LANG_VALUE= sessionStorage.getItem("sessionLang") == null ? window.parent.$("body").attr("data-lang") :  sessionStorage.getItem("sessionLang");
	$("#section").append(" <iframe name='mainArea' class='embed-responsive-item' id='mainArea' scrolling='yes' frameBorder='0' src="+intialController+" width='100%' height='700px'></iframe>");
	//window.parent.$("body").attr("data-lang", DB_LANG_VALUE);
	var url = new URL(window.location.href);
    var langParameter = url.searchParams.get("lang")== null ? sessionStorage.getItem("sessionLang") : url.searchParams.get("lang");
    window.parent.$('#langlist').val(langParameter); 
	//dataByTag("copyright_footer","copyrightText",2);
	sessionStorage.removeItem("a");
	$('div#initialloader').delay(300).fadeOut('slow');
});   






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


function changeLanguage(lang){
	$.ajax({
		type : 'POST',
		url :'./changeLanguage/'+lang,
		contentType :"application/json",
		dataType : 'html',
		success : function(data) {
		},      
		error: function (xhr, ajaxOptions, thrownError) {
		}
	});
}



