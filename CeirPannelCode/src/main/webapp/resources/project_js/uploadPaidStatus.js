 $(document).ready(function () {
         pageRendering();
     });

	
 var roleType = $("body").attr("data-roleType");
 var userId = $("body").attr("data-userID");
 var currentRoleType = $("body").attr("data-selected-roleType"); 
 var featureId =12;
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
        	filter(); 
        
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
 

    
    
    
    
    
    var sourceType =localStorage.getItem("sourceType");
    function filter()
    {       	 
    	console.log("source type value=="+sourceType);
    	var sessionFlag;
    	if(sourceType==null){
    		sessionFlag=2;
    		console.log("sesion value set to "+sessionFlag);
    	}
    	else{
    		sessionFlag=1;
    		console.log("sesion value set to "+sessionFlag);
    	}
    	table('./headers?type=userPaidStatus','./user-paid-status-data?sessionFlag='+sessionFlag);
    		
    	
    		localStorage.removeItem('sourceType');
    	
    }

    
    
    function table(url,dataUrl){
    	var request={
    			"endDate":$('#endDate').val(),
    			"startDate":$('#startDate').val(),
    			"taxPaidStatus":parseInt($('#taxPaidStatus').val()),
    			"userId":parseInt(userId),
    			"featureId":parseInt(featureId),
    			"userTypeId": parseInt($("body").attr("data-userTypeID")),
    			"userType":$("body").attr("data-roleType"),	
    			"deviceIdType":parseInt($('#deviceIDType').val()),
    			"deviceType":parseInt($('#deviceType').val()),
    			"txnId":$('#Search').val()
    			}
    	
    	$.ajax({
    		url: url,
    		type: 'POST',
    		dataType: "json",
    		success: function(result){
    			var table=	$("#data-table-simple").DataTable({
    				destroy:true,
    				"serverSide": true,
    				orderCellsTop : true,
    				"ordering" : false,
    				"bPaginate" : true,
    				"bFilter" : true,
    				"bInfo" : true,
    				"bSearchable" : true,
    				ajax: {
    					url : dataUrl,
    					type: 'POST',
    					dataType: "json",
    					data : function(d) {
    						d.filter = JSON.stringify(request); 
    					}

    				},
    				"columns": result
    			});

    		//	$('div#initialloader').delay(300).fadeOut('slow');
    		/*	$('div#initialloader').fadeOut('slow');*/
    		},
    		error: function (jqXHR, textStatus, errorThrown) {
    			console.log("error in ajax");
    		}
    	});
    }


    function pageRendering(){
    	pageButtons('./upload-paid-status/pageRendering?type=userPaidStatus');

    	localStorage.removeItem('sourceType');

    }
    
    
    function pageButtons(url){
    	$.ajax({
    		url: url,
    		type: 'POST',
    		dataType: "json",
    		success: function(data){
    			data.userStatus == "Disable" ? $('#btnLink').addClass( "eventNone" ) : $('#btnLink').removeClass( "eventNone" );


    			var elem='<p class="PageHeading">'+data.pageTitle+'</p>';		
    			$("#pageHeader").append(elem);
    			var button=data.buttonList;

				
				
    			var date=data.inputTypeDateList;
    			for(i=0; i<date.length; i++){
    				if(date[i].type === "date"){
    					$("#tableDiv").append("<div class='col s6 m2 l2 responsiveDiv'>"+
    							"<div id='enddatepicker' class='input-group'>"+
    							"<label for='TotalPrice'>"+date[i].title
    							+"</label>"+"<input class='form-control datepicker' type='text' id="+date[i].id+" autocomplete='off'>"+
    							"<span	class='input-group-addon' style='color: #ff4081'>"+
    							"<i	class='fa fa-calendar' aria-hidden='true' style='float: right; margin-top: -37px;'>"+"</i>"+"</span>");

    				}else if(date[i].type === "text"){
    					$("#tableDiv").append("<div class='input-field col s6 m2 filterfield' style='margin-top: 22px;'><input type="+date[i].type+" id="+date[i].id+" maxlength='19' /><label for='TransactionID' class='center-align'>"+date[i].title+"</label></div>");
    				}
    			} 

    			// dynamic dropdown portion
    			var dropdown=data.dropdownList;
    			for(i=0; i<dropdown.length; i++){
    				var dropdownDiv=
    					$("#tableDiv").append("<div class='col s6 m2 l2 selectDropdwn'>"+
    							"<br>"+
    							"<div class='select-wrapper select2 form-control boxBorder boxHeight initialized'>"+
    							"<span class='caret'>"+"</span>"+
    							"<input type='text' class='select-dropdown' readonly='true' data-activates='select-options-1023d34c-eac1-aa22-06a1-e420fcc55868' value='Consignment Status'>"+

    							"<select id="+dropdown[i].id+" class='select2 form-control boxBorder boxHeight initialized'>"+
    							"<option>"+dropdown[i].title+
    							"</option>"+
    							"</select>"+
    							"</div>"+
    					"</div>");
    			}

    			

    			
    			$("#tableDiv").append("<div class='col s12 m1'><input type='button' class='btn primary botton' value='filter' id='submitFilter' /></div>");
				$("#tableDiv").append("<div class='col s12 m1'><a href='JavaScript:void(0)' onclick='exportConsignmentData()' type='button' class='export-to-excel right'>Export <i class='fa fa-file-excel-o' aria-hidden='true'></i></a></div>");
				for(i=0; i<button.length; i++){
					$('#'+button[i].id).text(button[i].buttonTitle);
					if(button[i].type === "HeaderButton"){
						$('#'+button[i].id).attr("href", button[i].buttonURL);
					}
					else{
						$('#'+button[i].id).attr("onclick", button[i].buttonURL);
					}
				}


    			//Tax paid status-----------dropdown
    			$.getJSON('./getDropdownList/CUSTOMS_TAX_STATUS', function(data) {
    				for (i = 0; i < data.length; i++) {
    					$('<option>').val(data[i].value).text(data[i].interp)
    					.appendTo('#taxPaidStatus');
    				}
    			});
    			
    			
    			$('.datepicker').datepicker({
    				dateFormat: "yy-mm-dd"
    				});
    		}
    	}); 	
    }



    
    
    /*filterFileStatus,deviceType,taxPaidStatus*/