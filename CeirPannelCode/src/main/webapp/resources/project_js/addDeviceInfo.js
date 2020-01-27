
	 var nationalID = $("body").attr("session-value");
	
	 window.parent.$('#langlist').on('change', function() {
		var lang=window.parent.$('#langlist').val() == 'km' ? 'km' : 'en';
		window.location.assign("./uploadPaidStatus?via=other&NID="+nationalID+"&lang="+lang);
	}); 
	
var lang_param =window.parent.$('#langlist').val() == 'km' ? 'km' : 'en';
	$.i18n().locale = lang_param;
	var successMsg;
	$.i18n().load( {
		'en': './resources/i18n/en.json',
		'km': './resources/i18n/km.json'
	} ).done( function() { 
		successMsg=$.i18n('successMsg');
	});


	 $('#ok,#redirectToPage').click(function(){
		 window.location.replace("./uploadPaidStatus?via=other&NID="+nationalID);
		});

	 function redirectToPage(){
		 
	 }
	function submitDeviceInfo(){
		 var formData= new FormData();	
		 var fieldId=1;	
			 var regularizeDeviceDbs =[];
			 $('.deviceInformation').each(function() {	
				 var deviceType1=$('#deviceType'+fieldId).val();
					var serialNumber1=$('#serialNumber'+fieldId).val();
					var deviceIdType1=$('#deviceIdType'+fieldId).val();
					var taxStatus1=$('#taxStatus'+fieldId).val();
					var deviceStatus1=$('#deviceStatus'+fieldId).val();
					var Price1=$('#Price'+fieldId).val();
					var Currency1=$('#Currency'+fieldId).val();
					var IMEI1=$('#IMEIA'+fieldId).val();
					var IMEI2=$('#IMEIB'+fieldId).val();
					var IMEI3=$('#IMEIC'+fieldId).val();
					var IMEI4=$('#IMEID'+fieldId).val();
					var deviceCountry=$('#country'+fieldId).val();
					var multipleSimStatus1=$('#multipleSimStatus1'+fieldId).val();
				console.log("serialNumber1="+serialNumber1+" deviceIdType1="+deviceIdType1+" taxStatus1="+taxStatus1+" deviceStatus1="+deviceStatus1+" Price1="+Price1+" Currency1="+Currency1)
				var deviceInfo=
				{
					      "country": deviceCountry,
					      "currency": parseInt(Currency1),
					      "deviceIdType": parseInt(deviceIdType1),
					      "deviceSerialNumber": serialNumber1,
					      "deviceStatus": parseInt(deviceStatus1),
					      "deviceType": parseInt(deviceType1),
					      "firstImei": parseInt(IMEI1),
					      "secondImei": parseInt(IMEI2),
					      "thirdImei": parseInt(IMEI3),
					      "fourthImei": parseInt(IMEI4),
					      "multiSimStatus": deviceStatus1,
					      "price": parseFloat(Price1),
					      "taxPaidStatus": parseInt(taxStatus1),
					      "nid":nationalID,
					      "txnId":""
				}
				regularizeDeviceDbs.push(deviceInfo);  
				fieldId++;
			});
			 var request={
					 "country": null,
					 "email": null,
					  "firstName": null,
					  "lastName": null,
					  "locality": null,
					  "middleName": null,
					  "nid": nationalID,
					  "phoneNo": null,
					  "propertyLocation": null,
					  "province": null,
					  "street": null,
					  "regularizeDeviceDbs":regularizeDeviceDbs,
					  
					}
			 formData.append("request",JSON.stringify(request));
			$.ajax({
				url: './uploadPaidStatusForm',
				type: 'POST',
				data: formData,
				processData: false,
				contentType: false,
				success: function (data, textStatus, jqXHR) {
					console.log("in suucess method");
					console.log(data);
				   
					// $('#updateConsignment').modal();
					if(data.errorCode==0){

					//	$('#sucessMessage').text('');
					    $('#regularisedDevice').openModal();
						$('#dynamicTxnId').text(data.txnId);
					}
					else{
						//$('#sucessMessage').text('');
						$('#regularisedDevice').openModal();
						$('#sucessMessage').text();
						$('#sucessMessage').text(data.message);
						
					}
					//sessionStorage.removeItem("nationalId");
				},
				error: function (jqXHR, textStatus, errorThrown) {
					console.log("error in ajax")
				
				}
			});
			return false;

			 } 

   	 populateCountries
 	(   
 			"country1"
 	);
	
	
function defaultDeviceForm(){
		var allowed =parseInt(localStorage.getItem("allowed"));
		var current =parseInt(localStorage.getItem("current"));
		console.log("allowed="+allowed+"& current="+current)
	
		var incrementedCurrent='';
		$.getJSON('./getDropdownList/CUSTOMS_TAX_STATUS', function(data) {
			/* for (i = 0; i < data.length; i++) {
				$('<option>').val(data[i].value).text(data[i].interp)
				.appendTo('#taxStatus1');
				console.log("...........");} */
							if(allowed>current)
					{
					//console.log("-------------------");
		                $('#taxStatus1').prop('disabled', 'disabled');
		                $('<option  selected>').val("2").text("Regularized").appendTo('#taxStatus1');
					}
				else{
					//console.log("++++++++++++=");
					 for (i = 0; i < data.length; i++) {
					$('<option>').val(data[i].value).text(data[i].interp)
					.appendTo('#taxStatus1');
					//console.log("...........");} 
				    }
				
			}
				 incrementedCurrent=current+1;
					console.log("set increment cuurent value="+incrementedCurrent);
					localStorage.removeItem('incrementedCurrent');
					localStorage.setItem("incrementedCurrent", incrementedCurrent);
				var www =parseInt(localStorage.getItem("incrementedCurrent"));
				
						});
		

		$.getJSON('./getDropdownList/DEVICE_TYPE', function(data) {
			for (i = 0; i < data.length; i++) {
				$('<option>').val(data[i].value).text(data[i].interp)
				.appendTo('#deviceType1');
				//console.log("...........");
			}
		});


		
		$.getJSON('./getDropdownList/DEVICE_ID_TYPE', function(data) {
			for (i = 0; i < data.length; i++) {
				$('<option>').val(data[i].value).text(data[i].interp)
				.appendTo('#deviceIdType1');
				//console.log("...........");
			}
		});
		
		$.getJSON('./getDropdownList/currency', function(data) {
			for (i = 0; i < data.length; i++) {
				$('<option>').val(data[i].value).text(data[i].interp)
				.appendTo('#Currency1');
				//console.log("...........");
			}
		});
		
		$.getJSON('./getDropdownList/MULTI_SIM_STATUS', function(data) {
			for (i = 0; i < data.length; i++) {
				$('<option>').val(data[i].value).text(data[i].interp)
				.appendTo('#multipleSimStatus1');
				//console.log("...........");
			}
		});
		
		$.getJSON('./getDropdownList/DEVICE_STATUS', function(data) {
			for (i = 0; i < data.length; i++) {
				$('<option>').val(data[i].value).text(data[i].interp)
				.appendTo('#deviceStatus1');
				//console.log("...........");
			}
		});
		
		
		 }
	

	
	
	
	
	 	  function RegularisedDeviceForm(){
		
        var max_fields = 15; //maximum input boxes allowed
        var wrapper = $(".mainDeviceInformation"); //Fields wrapper
        var add_button = $(".add_field_button"); //Add button ID
        var x = 1; //initlal text box count
        var id=2;
        
      
        var allowed =parseInt(localStorage.getItem("allowed")); 
        $(add_button).click(function (e) { //on add input button click
            e.preventDefault();
            var incrementedCurrent =parseInt(localStorage.getItem("incrementedCurrent"));
            
            console.log("&&&&&&&&&&&"+incrementedCurrent);
            if (x < max_fields) { //max input box allowed
                x++; //text box increment
               
                console.log("incremented value="+id)
                $(wrapper).append(
                '<div id="deviceInformation'+id+'" class="deviceInformation"><div class="row"><div class="col s12 m6"><label for="deviceType">Device Type <spanclass="star">*</span></label><select class="browser-default" required id="deviceType'+id+'"><option value="" disabled selected>Device Type</option></select></div><div class="col s12 m6"><label for="deviceIdType'+id+'">Device ID Type <span class="star">*</span></label><select class="browser-default" required id="deviceIdType'+id+'"><option value="" disabled selected>Select Device ID Type</option></select></div><div class="col s12 m6"><label for="multipleSimStatus'+id+'">Multiple Sim Status <span class="star">*</span></label><select class="browser-default" required id="multipleSimStatus'+id+'"><option value="" disabled selected>Multiple Sim Status</option></select></div><div class="col s12 m6"><label for="deviceCountry">Country bought From <span class="star">*</span></label><select id="country'+id+'" class="browser-default" required class="mySelect" style="padding-left: 0;" required></select></div><div class="input-field col s12 m6" style="margin-top: 28px;"><input type="text" id="serialNumber'+id+'" required name="serialNumber'+id+'" pattern="[A-Za-z0-9]{0,15}" title="Please enter your device serial number first" maxlength="15"><label for="serialNumber'+id+'">Device Serial Number <span class="star">*</span></label></div><div class="col s12 m6"><label for="taxStatus'+id+'">Tax paid Status <span class="star">*</span></label><select class="browser-default" required id="taxStatus'+id+'"><option value="" disabled selected>Tax paid Status</option></select></div></div><div class="row"><div class="col s12 m6" style="margin-top: -10px;"><label for="deviceStatus'+id+'">Device Status <span class="star">*</span></label><select class="browser-default" required id="deviceStatus'+id+'"><option value="" disabled selected>Select Device Status</option></select></div><div class="input-field col s12 m6 l6"><input type="text" name="Price" id="Price'+id+'" required maxlength="30"><label for="Price'+id+'">Price <span class="star">*</span></label></div><div class="col s12 m6"><label for="Currency'+id+'">Currency <span class="star">*</span></label><select class="browser-default" required id="Currency'+id+'"><option value="" disabled selected>Select Currency</option></select></div></div><div class="row"><div class="col s12 m12"><p>IMEI/MEID/ESN</p></div><div class="input-field col s12 m6"><input type="text" id="IMEIA'+id+'" required name="IMEI1" pattern="[0-9]{15,16}"title="Please enter minimum 15 and maximum 16 digit only"maxlength="16"><label for="IMEIA'+id+'">1 <span class="star">*</span></label></div><div class="input-field col s12 m6"><input type="text"  id="IMEIB'+id+'" name="IMEI2" pattern="[0-9]{15,16}"title="Please enter minimum 15 and maximum 16 digit only"maxlength="16"><label for="IMEIB'+id+'">2</label></div><div class="input-field col s12 m6"><input type="text" id="IMEIC'+id+'"  name="IMEI3" pattern="[0-9]{15,16}"title="Please enter minimum 15 and maximum 16 digit only"maxlength="16"><label for="IMEIC'+id+'">3</label></div><div class="input-field col s12 m6"><input type="text" id="IMEID'+id+'"  name="IMEI4[]" pattern="[0-9]{15,16}"title="Please enter minimum 15 and maximum 16 digit only"maxlength="16"><label for="IMEID'+id+'">4</label></div></div><div style="cursor:pointer;background-color:red;" class="remove_field btn right btn-info">Remove</div></div>'
                );  //add input box
              
                populateCountries
             	(   
             			"country"+id
             	);
         
               
                var regularised=incrementedCurrent+1;
                incrementedCurrent=regularised;
               
                console.log("regularised"+regularised);
                console.log("allowed current value=="+incrementedCurrent)
                $.getJSON('./getDropdownList/CUSTOMS_TAX_STATUS', function(data) {
                	var dropdownid=id-1;
                	if(regularised <= allowed){
                		
               	$('#taxStatus'+dropdownid).prop('disabled', 'disabled');
           		$('<option  selected>').val("2").text("Regularized").appendTo('#taxStatus'+dropdownid);
           			//console.log("+++++taxStatus"+dropdownid);
           		}
               	else{
               		for (i = 0; i < data.length; i++) {
               			$('<option>').val(data[i].value).text(data[i].interp)
               			.appendTo('#taxStatus'+dropdownid);
               			// $('#taxStatus'+dropdownid).prop('disabled', 'false');
               			console.log("+++++taxStatus"+dropdownid);
               		}
               	}
            	});




          

            	$.getJSON('./getDropdownList/DEVICE_TYPE', function(data) {
            		var dropdownid=id-1;
            		for (i = 0; i < data.length; i++) {
            			$('<option>').val(data[i].value).text(data[i].interp)
            			.appendTo('#deviceType'+dropdownid);
            			console.log('#deviceType'+dropdownid)
            		}
            	});


            	
            	$.getJSON('./getDropdownList/DEVICE_ID_TYPE', function(data) {
            		var dropdownid=id-1;
            		for (i = 0; i < data.length; i++) {
            			$('<option>').val(data[i].value).text(data[i].interp)
            			.appendTo('#deviceIdType'+dropdownid);
            			console.log('#deviceIdType'+dropdownid);
            		}
            	});
            	
            	$.getJSON('./getDropdownList/currency', function(data) {
            		var dropdownid=id-1;
            		for (i = 0; i < data.length; i++) {
            			$('<option>').val(data[i].value).text(data[i].interp)
            			.appendTo('#Currency'+dropdownid);
            			console.log('#Currency'+dropdownid);
            		}
            	});
            	
            	$.getJSON('./getDropdownList/MULTI_SIM_STATUS', function(data) {
            		var dropdownid=id-1;
            		for (i = 0; i < data.length; i++) {
            			$('<option>').val(data[i].value).text(data[i].interp)
            			.appendTo('#multipleSimStatus'+dropdownid);
            			console.log('#multipleSimStatus'+dropdownid);
            		}
            	});
            	
            	$.getJSON('./getDropdownList/DEVICE_STATUS', function(data) {
            		var dropdownid=id-1;
            		for (i = 0; i < data.length; i++) {
            			$('<option>').val(data[i].value).text(data[i].interp)
            			.appendTo('#deviceStatus'+dropdownid);
            			console.log('#deviceStatus'+dropdownid);
            		}
            	});
                
            	
            	id++;
            	 console.log("regularised before set session=="+regularised);
                 localStorage.removeItem('incrementedCurrent');
             	localStorage.setItem("incrementedCurrent", regularised);
            }
           
        });
        $(wrapper).on("click", ".remove_field", function (e) { //user click on remove text
            e.preventDefault();
            $(this).parent('div').remove();
            x--;
        })
	}


      $(document).ready(function () {
    	  	
    	   
    	   defaultDeviceForm()
        
        }); 
       RegularisedDeviceForm()


    