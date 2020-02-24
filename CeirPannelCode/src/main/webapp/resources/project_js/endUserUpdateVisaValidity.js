   function hide() {
            var In = $('#nidForEndUser').val()
            if (In == "black") {

                $("#match-data").css("display", "block");
                $("#EndUserInfoForm").css("display", "none");
                $("#submitbtn").css("display", "none");
                $("#footer-submit").css("display", "block");

            } else if (In == "blue") {
                $("#match-data").css("display", "none");
                $("#EndUserInfoForm").css("display", "block");
                $("#submitbtn").css("display", "none");
                $("#footer-submit").css("display", "block");
            }
        }
   
 
 function   findEndUserByNid(){
	 var passport=$('#nidForEndUser').val();
	
		$.ajax({
			url: './findEndUserByNid?findEndUserByNid='+passport,
			type: 'POST',
			processData: false,
			contentType: false,
			success: function (data, textStatus, jqXHR) {

				console.log(JSON.stringify(data));
			
				if(data.errorCode==1)
					{
					
					 $("#match-data").css("display", "block");
		                $("#EndUserInfoForm").css("display", "block");
		                $("#submitbtn").css("display", "none");
		                $("#footer-submit").css("display", "block");
		                
		                $("#passportFileNameDiv").css("display", "block")
		                $("#passportFileDiv").css("display", "none");
		                $("#passportFileName").prop('readonly', true);
		                $('#endUserTxnId').val(data.data.txnId);
		                $("#passportFileName").val(data.data.passportFileName).prop('readonly', true);
		                $('#endUserpassportNumber').val(data.data.nid).prop('readonly', true);
		                 $('#endUserfirstName').val(data.data.firstName).prop('readonly', true);
		                  $('#endUsermiddleName').val(data.data.middleName).prop('readonly', true);
		                   $('#endUserlastName').val(data.data.lastName).prop('readonly', true);
		                    $('#endUseraddress').val(data.data.propertyLocation).prop('readonly', true);
		                     $('#endUserstreetNumber').val(data.data.street).prop('readonly', true);
		                      $('#endUserlocality').val(data.data.locality).prop('readonly', true);
		                       $('#endUservillage').val(data.data.village).prop('readonly', true);
		                        $('#endUsercommune').val(data.data.commune).prop('readonly', true);
		                         $('#endUserdistrict').val(data.data.district).prop('readonly', true);
		                          $('#endUserpin').val(data.data.postalCode).prop('readonly', true);
		                           $('#country').val(data.data.country).change().attr("disabled", true);
		                            $('#state').val(data.data.province).attr("disabled", true);
		                             $('#phone').val(data.data.phoneNo).prop('readonly', true);
		                              $('#endUservisaType').val(data.data.visaDb[0].visaType).attr("disabled", true);
		                             // $('#endUserdatepickerDiv').attr("disabled", true);
		                               $("#endUserdatepickerDiv").css("pointer-events","none");
		                                $('#endUserdatepicker1').val(data.data.visaDb[0].entryDateInCountry).prop('readonly', true);
		                                 $('#endUserdatepicker1').prop('readonly', true);
		                                 $('#endUseruploadnationalID').val(data.data.endUseruploadnationalID);
		                                  $('#endUserdatepicker').val(data.data.visaDb[0].visaExpiryDate).prop('readonly', true);
		                                   $('#endUseremailID').val(data.data.email).prop('readonly', true); 
		               
		                                   
		                
		                
					}
				else{
					 $("#match-data").css("display", "none");
		                $("#EndUserInfoForm").css("display", "block");
		                $("#submitbtn").css("display", "none");
		                $("#footer-submit").css("display", "block");
		                $('#endUserpassportNumber').val(passport);                  
		                
		                
		      }
			},
			error: function (jqXHR, textStatus, errorThrown) {
				console.log("error in ajax")
			}
		});
		return false;
 }
 
 function updateEndDateVisaDetails()
 {
	    var endUserpassportNumber=$('#endUserpassportNumber').val();
		var endUserfirstName=$('#endUserfirstName').val();
		var endUsermiddleName=$('#endUsermiddleName').val();
		var endUserlastName=$('#endUserlastName').val();
		var endUseraddress=$('#endUseraddress').val();
		var endUserstreetNumber=$('#endUserstreetNumber').val();
		var endUserlocality=$('#endUserlocality').val();
		var endUservillage=$('#endUservillage').val();
		var endUsercommune=$('#endUsercommune').val();
		var endUserdistrict=$('#endUserdistrict').val();
		var endUserpin=$('#endUserpin').val();
		var country=$('#country').val();
		var state=$('#state').val();
		var phone=$('#phone').val();
		var endUservisaType=$('#endUservisaType').val();
		var endUserdatepicker1=$('#endUserdatepicker1').val();
		var endUseruploadnationalID=$('#endUseruploadnationalID').val();
		var endUserdatepicker=$('#endUserdatepicker').val();
		
		var doc_type_numeric=$("#doc_type option:selected").attr("docValue");
		
		var village=$('#village').val();
		var district=$('#district').val();
		var commune=$('#commune').val();
		var postalcode=$('#postalcode').val();

	 
		$.ajax({
			url: './findEndUserByNid?Nid='+grievanceId,
			type: 'GET',
			processData: false,
			contentType: false,
			success: function (data, textStatus, jqXHR) {

				console.log(JSON.stringify(data));
			},
			error: function (jqXHR, textStatus, errorThrown) {
				console.log("error in ajax")
			}
		});
		return false;
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
                   '<div class="row"><div class="col s12 m12"><div class="col s12 m6"><label for="deviceIdType">Device ID Type <span class="star">*</span></label><select class="browser-default" id="deviceType"><option value="" disabled selected>Select Device ID Type</option><option value="IMEI">IMEI</option><option value="ESN">ESN</option><option value="MEID">MEID</option></select></div><div class="col s12 m6"><label for="deviceType">Multiple Sim Status <span class="star">*</span></label><select class="browser-default" id="deviceType"><option value="" disabled selected>Multiple Sim Status</option><option value="Yes">Yes</option><option value="No">No</option></select></div><div class="col s12 m6"><label for="deviceType">Device Type <span class="star">*</span></label><select class="browser-default" id="deviceType"><option value="" disabled selected>Device Type</option><option value="Handheld">Handheld</option><option value="MobilePhone">Mobile Phone/Feature phone</option><option value="Vehicle">Vehicle</option><option value="Portable">Portable(include PDA)</option><option value="Module">Module</option><option value="Dongle">Dongle</option><option value="WLAN">WLAN Router</option><option value="Modem">Modem</option><option value="Smartphone">Smartphone</option><option value="Computer">Connected Computer</option><option value="Tablet">Tablet</option><option value="e-Book">e-Book</option></select></div><div class="col s12 m12"><p>IMEI/MEID/ESN</p></div><div class="input-field col s12 m6"><input type="text" id="IMEI1" name="IMEI1" pattern="[0-9]"title="Please enter minimum 15 and maximum 16 digit only" maxlength="16"><label for="IMEI1">1 <span class="star">*</span></label></div><div class="input-field col s12 m6"><input type="text" id="IMEI2" name="IMEI2" pattern="[0-9]" title="Please enter minimum 15 and maximum 16 digit only" maxlength="16"><label for="IMEI2">2</label></div><div class="input-field col s12 m6"><input type="text" id="IMEI3" name="IMEI3" pattern="[0-9]" title="Please enter minimum 15 and maximum 16 digit only"maxlength="16"><label for="IMEI3">3</label></div><div class="input-field col s12 m6"><input type="text" id="IMEI4" name="IMEI4[]" pattern="[0-9]" title="Please enter minimum 15 and maximum 16 digit only"maxlength="16"><label for="IMEI4">4</label></div><div class="input-field col s12 m6"><input type="text" id="serialNumber" name="serialNumber" pattern="[0-9]"title="Please enter your device serial number first" maxlength="20"><label for="serialNumber">Device Serial Number <span class="star">*</span></label></div><div class="input-field col s12 m6"><input type="text" id="simNumber" name="simNumber" pattern="[0-9]" title="Please enter your sim number" maxlength="20"><label for="simNumber">Sim Number</label></div><div style="cursor:pointer;background-color:red;" class="remove_field btn right btn-info">Remove</div></div></div>'
               ); //add input box
           }
       });
       $(wrapper).on("click", ".remove_field", function (e) { //user click on remove text
           e.preventDefault();
           $(this).parent('div').remove();
           x--;
       })
   });
   
   
   
   populateCountries(
           "country",
           "state"
       );
       populateStates(
           "country",
           "state"
       );
       
       
   	$.getJSON('./getDropdownList/VISA_TYPE', function(data) {
		for (i = 0; i < data.length; i++) {
			$('<option>').val(data[i].value).text(data[i].interp)
			.appendTo('#endUservisaType');
			
		}
	});
   	
  
    
    $('#endUserdatepicker1,#endUserdatepicker').datepicker({
    	dateFormat: "yy-mm-dd"
    });

    
    
    
    function updateVisaDetails(){
    	
    	var formData = new FormData()
    	var passportNumber=$('#endUserpassportNumber').val();
    	var firstName=$('#endUserfirstName').val();
    	var middleName =$('#endUsermiddleName').val();
        var lastName=$('#endUserlastName').val();
        var address=$('#endUseraddress').val();
        var street=$('#endUserstreetNumber').val();
        var locality=$('#endUserlocality').val();
        var village= $('#endUservillage').val();
        var commune=$('#endUsercommune').val();
        var district=$('#endUserdistrict').val();
        var postalCode=$('#endUserpin').val();
        var country=$('#country').val();
        var province=$('#state').val();
        var phoneNo=$('#phone').val();
        var visaType=$('#endUservisaType').val(); 
        var entryDate=$('#endUserdatepicker1').val();
        var expiryDate=$('#endUserdatepicker').val();
        var email=$('#endUseremailID').val();  
        var visaDb=[];
        var txnid=$('#endUserTxnId').val();
        var visa={
				"visaType":visaType,
				"visaExpiryDate":expiryDate,
				"entryDateInCountry":entryDate,
			}
        var passportFileName=$('#passportFileName').val();
        if(passportFileName=="")
        	{
        
        	 formData.append('passportImage', $('#uploadPassportID')[0].files[0]);
        	}
        else{
        	 formData.append('passportImage',passportFileName );
        }
       
        visaDb.push(visa);
        var request={	
        		"nid":passportNumber,
        		"firstName":firstName,
        		"middleName":middleName,
        		"lastName":lastName,
        		"propertyLocation":address,
        		"street":street,
        		"locality":locality,
        		"district":district,
        		"commune":commune,
        		"village":village,
        		"postalCode":postalCode,
        		"province":province,
        		"country":country,
        		"email":email,
        		"phoneNo":phoneNo,
        		"visaDb":visaDb,
        		"txnId":txnid
        }
       
        
        formData.append('visaImage', $('#endUseruploadnationalID')[0].files[0]);
    	formData.append("request",JSON.stringify(request));
    	
        $.ajax({
			url: './updateEndUSerVisaValidity',
			type: 'POST',
			processData: false,
			contentType: false,
			data: formData,
			success: function (data, textStatus, jqXHR) {
				
				console.log(JSON.stringify(data));
				if(data.errorCode==5){
					$('#successMsg').openModal();
					$('#messageResponse').text(data.message);
				    $("#updateVisaButton").prop('disabled', true);
				}
				else{
					$('#successMsg').openModal();
					$('#messageResponse').text(data.message);
				    $("#updateVisaButton").prop('disabled', true);
				}
			},
			error: function (jqXHR, textStatus, errorThrown) {
				console.log("error in ajax")
			}
		});
		return false;
    }
    