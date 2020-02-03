    $('#langlist').on('change', function() {
        lang=$('#langlist').val() == 'km' ? 'km' : 'en';  
      $('#mainArea').attr('src', function () {
      currentPageLocation=$(this).contents().get(0).location.pathname;
      sessionStorage.setItem("a", currentPageLocation);
    }); 
    // sessionStorage.getItem("a") == 'null' ? "./Home" : sessionStorage.getItem("a");
          window.location.assign("importerDashboard?lang="+lang);
          $("#section").append(" <iframe name='mainArea' class='embed-responsive-item' id='mainArea' scrolling='yes' frameBorder='0' src="+sessionStorage.getItem("a")+" width='100%' height='700px'></iframe>");
    
      }); 
       var intialController=sessionStorage.getItem("a") == null ? "./Home" : sessionStorage.getItem("a");
      $("#section").append(" <iframe name='mainArea' class='embed-responsive-item' id='mainArea' scrolling='yes' frameBorder='0' src="+intialController+" width='100%' height='700px'></iframe>");
    
      $(document).ready(function () {
      //  sessionStorage.removeItem("a");
        var url = new URL(window.location.href);
        var langParameter = url.searchParams.get("lang");
        $('#langlist').val(langParameter == 'km' ? 'km' : 'en');
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
