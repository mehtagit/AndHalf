 /*  $('#langlist').on('change', function() {
      lang=$('#langlist').val() == 'km' ? 'km' : 'en';  
      window.location.assign("importerDashboard?lang="+lang); 
    }); 
 */
    $(document).ready(function () {
      /* var url = new URL(window.location.href);
      var langParameter = url.searchParams.get("lang");
      $('#langlist').val(langParameter == 'km' ? 'km' : 'en'); */
      dataByTag("copyright_footer","copyrightText",2);
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
