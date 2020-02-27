$('#langlist').on('change', function() {
	var lang=$('#langlist').val(); 
	sessionStorage.setItem("sessionLang", lang);
	$('#mainArea').attr('src', function () {
		currentPageLocation=$(this).contents().get(0).location;
		var feature=$(this).contents().find("body").attr('data-id');
		sessionStorage.setItem("data-feature", feature);
		changeLanguage(lang);
		sessionStorage.setItem("a", currentPageLocation);
	});    
	window.location.replace("?lang="+lang);
}); 



var featurID=sessionStorage.getItem("data-feature") == null ? '1' : sessionStorage.getItem("data-feature");
var intialController=sessionStorage.getItem("a") == null ? "./Home" : sessionStorage.getItem("a");
$(document).ready(function () {
	//var DB_LANG_VALUE= sessionStorage.getItem("sessionLang") == null ? window.parent.$("body").attr("data-lang") :  sessionStorage.getItem("sessionLang");
	$("#section").append(" <iframe name='mainArea' class='embed-responsive-item' id='mainArea' frameBorder='0' src="+intialController+" width='100%' onLoad='self.scrollTo(0,0)'></iframe>");
	//window.parent.$("body").attr("data-lang", DB_LANG_VALUE);
	var url = new URL(window.location.href);
	/*sessionStorage.getItem("sessionLang")*/
	var langParameter = url.searchParams.get("lang")== (null || 'null') ? 'en' : url.searchParams.get("lang");

	window.parent.$('#langlist').val(langParameter); 
	//dataByTag("copyright_footer","copyrightText",2);
	sessionStorage.removeItem("a");
	$('div#initialloader').delay(300).fadeOut('slow'); 
	isActive(featurID);
	sessionStorage.removeItem("data-feature");
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
var cierRoletype = $("body").attr("data-usertype");
sessionStorage.setItem("cierRoletype", cierRoletype);
//$(".navData li:first").addClass("active");
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




function isActive(feature){
	$('.navData li:nth-child(1)').removeClass("active");
	$('.navData li a').each(function(){
		if($(this).attr('data-featureid') == feature){    	 
			$(this).closest('li').addClass("inactive");
			$(this).closest('li').removeClass("active");
			$(this).closest('li').removeClass("inactive");
			$(this).closest('li').addClass("active"); 
		}
	})
}
