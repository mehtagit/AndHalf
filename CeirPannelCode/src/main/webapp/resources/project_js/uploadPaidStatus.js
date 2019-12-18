/* $(document).ready(function () {
            $('.modal').modal();
            $('#DeleteConsignment').openModal({
                dismissible: false
            });
        });*/

     

   
        populateCountries("country","state");
        populateStates("country","state");
 

   
        function hide() {
            var In = $('#Search').val();
            
            $.ajax({
        		url : "./paid-status/"+In,
        		dataType : 'json',
        		contentType : 'application/json; charset=utf-8',
        		type : 'GET',
        		success : function(data) {
        			//console.log(data.txnId)
        			if (data.errorCode == 1) {
        					$("#user123").css("display", "none");
          	                $("#user456").css("display", "block");
          	                $("#addbutton").css("display", "block");
          	                $("#submitbtn").css("display", "none");
          	              } else
        	            {
        	            	$("#user123").css("display", "block");
        	                $("#user456").css("display", "none");
        	                $("#addbutton").css("display", "none");
        	                $("#submitbtn").css("display", "none");
        	            }
        		},
        		error : function() {
        			console.log("Failed");
        		}
        	}); 
           
        }
 


    $(document).ready(function () {
            var max_fields = 15; //maximum input boxes allowed
            var wrapper = $(".input_fields_wrap"); //Fields wrapper
            var add_button = $(".add_field_button"); //Add button ID
            var x = 1; //initlal text box count
            $(add_button).click(function (e) { //on add input button click
                e.preventDefault();
                if (x < max_fields) { //max input box allowed
                    x++; //text box increment
                    $(wrapper).append(
                        '<div style="margin-top:30px;"><div class="row"><div class="col s12 m6"><label for="deviceType">Device Type <spanclass="star">*</span></label><select class="browser-default" id="deviceType"><option value="" disabled selected>Device Type</option><option value="Handheld">Handheld</option><option value="MobilePhone">Mobile Phone/Feature phone</option><option value="Vehicle">Vehicle</option><option value="Portable">Portable(include PDA)</option><option value="Module">Module</option><option value="Dongle">Dongle</option><option value="WLAN">WLAN Router</option><option value="Modem">Modem</option><option value="Smartphone">Smartphone</option><option value="Computer">Connected Computer</option><option value="Tablet">Tablet</option><option value="e-Book">e-Book</option></select></div><div class="col s12 m6"><label for="deviceIdType">Device ID Type <span class="star">*</span></label><select class="browser-default" id="deviceType"><option value="" disabled selected>Select Device ID Type</option><option value="IMEI">IMEI</option><option value="ESN">ESN</option><option value="MEID">MEID</option></select></div><div class="col s12 m6"><label for="deviceType">Multiple Sim Status <span class="star">*</span></label><select class="browser-default" id="deviceType"><option value="" disabled selected>Multiple Sim Status</option><option value="Yes">Yes</option><option value="No">No</option></select></div><div class="col s12 m6"><label for="country">Country bought From <span class="star">*</span></label><select id="country1" class="browser-default" class="mySelect" style="padding-left: 0;" required></select></div><div class="input-field col s12 m6" style="margin-top: 28px;"><input type="text" id="serialNumber1" name="serialNumber1" pattern="[0-9]" title="Please enter your device serial number first" maxlength="20"><label for="serialNumber1">Device Serial Number <span class="star">*</span></label></div><div class="col s12 m6"><label for="deviceType">Tax paid Status <span class="star">*</span></label><select class="browser-default" id="deviceType"><option value="" disabled selected>Tax paid Status</option><option value="Regularized">Regularized</option><option value="Paid">Paid</option><option value="NotPaid">Not Paid</option></select></div></div><div class="row"><div class="col s12 m6" style="margin-top: -10px;"><label for="taxStatus">Device Status <span class="star">*</span></label><select class="browser-default" id="taxStatus"><option value="" disabled selected>Select Device Status</option><option value="New">New</option><option value="Used">Used</option><option value="Refurbished">Refurbished</option></select></div><div class="input-field col s12 m6 l6"><input type="text" name="Price" id="Price" maxlength="30"><label for="Price">Price <span class="star">*</span></label></div><div class="col s12 m6"><label for="Currency">Currency <span class="star">*</span></label><select class="browser-default" id="Currency"><option value="" disabled selected>Select Currency</option><option value="Regularized">$</option><option value="Paid">$</option><option value="NotPaid">$</option></select></div></div><div class="row"><div class="col s12 m12"><p>IMEI/MEID/ESN</p></div><div class="input-field col s12 m6"><input type="text" id="IMEI1" name="IMEI1" pattern="[0-9]"title="Please enter minimum 15 and maximum 16 digit only"maxlength="16"><label for="IMEI1">1 <span class="star">*</span></label></div><div class="input-field col s12 m6"><input type="text" id="IMEI2" name="IMEI2" pattern="[0-9]"title="Please enter minimum 15 and maximum 16 digit only"maxlength="16"><label for="IMEI2">2</label></div><div class="input-field col s12 m6"><input type="text" id="IMEI3" name="IMEI3" pattern="[0-9]"title="Please enter minimum 15 and maximum 16 digit only"maxlength="16"><label for="IMEI3">3</label></div><div class="input-field col s12 m6"><input type="text" id="IMEI4" name="IMEI4[]" pattern="[0-9]"title="Please enter minimum 15 and maximum 16 digit only"maxlength="16"><label for="IMEI4">4</label></div></div><div style="cursor:pointer;background-color:red;" class="remove_field btn right btn-info">Remove</div></div>'
                    ); //add input box
                }
            });
            $(wrapper).on("click", ".remove_field", function (e) { //user click on remove text
                e.preventDefault();
                $(this).parent('div').remove();
                x--;
            })
        });
 

  //Tax paid status-----------dropdown
	$.getJSON('./getDropdownList/CUSTOMS_TAX_STATUS', function(data) {
		for (i = 0; i < data.length; i++) {
			$('<option>').val(data[i].value).text(data[i].interp)
			.appendTo('#taxPaidStatus');
		}
	});
	

	populateCountries
	(   
			"country"
	);

	
    /*filterFileStatus,deviceType,taxPaidStatus*/