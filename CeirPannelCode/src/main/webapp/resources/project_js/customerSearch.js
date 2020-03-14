	$('#btnLink').css({"display":"none"});	
	var roleType = $("body").attr("data-roleType");
	var userId = $("body").attr("data-userID");
	var roleType = $("body").attr("data-roleType"); 

	
	
	function hide() {
		var deviceDropDown =  document.getElementById("deviceType");
		var deviceIdType = deviceDropDown.options[deviceDropDown.selectedIndex].text;
		var msisdn = $('#msisdn').val();
		var imei =  $('#imei').val();
		var deviceIdType =  deviceIdType;
		//sessionStorage.setItem("roleType",roleType);
		//sessionStorage.setItem("tagId", tagId);
		window.location.replace("./search?via=other&msisdn="+msisdn+"&imei="+imei+"&deviceIdType="+deviceIdType);
		return false;
	}
	
	$.getJSON('./getDropdownList/DEVICE_ID_TYPE', function(data) {
		for (i = 0; i < data.length; i++) {
			$('<option>').val(data[i].value).text(data[i].interp)
			.appendTo('#deviceType');
		}
	});
	
	
	function clearFields(){
		$('#msisdn').val('');
		$('#imei').val('');
		$("#deviceType").val('');
	}
	
	