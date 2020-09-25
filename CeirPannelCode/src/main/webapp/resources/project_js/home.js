
$(document).ready(function () {
	var ip=$("body").attr("data-ip");
	var port=$("body").attr("data-port");

	var currentTime = new Date()
	var month = ("0" + (currentTime.getMonth() + 1)).slice(-2)
	var day = ("0" + (currentTime.getDate())).slice(-2)
	var hours=("0"+ (currentTime.getHours() - 6)).slice(-2)
	var year = currentTime.getFullYear();
	var finalVal=year+"-"+month+"-"+day+" "+hours+":"+currentTime.getMinutes()+":"+currentTime.getSeconds();
//	("0" + currentHours).slice(-2)
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$.ajaxSetup({
		headers:
		{ 'X-CSRF-TOKEN': token }
	});
	$.ajax({
		type : 'GET',
		url : ''+ip+''+port+'/substation/station/get',
		contentType : "application/json",
		success: function(data){
			//	console.log(data);
			for(var i=0 ;i<data.length;i++){
				if(finalVal <= data[i].lastIntervalPacketDate){
					var classN="\"trWidht statusColor \"";
					$('#activeDeviceTable').append('<tr  class='+classN+'><td onclick=redirectToTable("'+data[i].unitID+'");isActive(3);>'+data[i].substation+'</td><tr>');
				}
				else if( data[i].lastIntervalPacketDate == null){
					var classN="\"trWidht statusNotAvailableColor \"";

					$('#activeDeviceTable').append("<tr class="+classN+"><td>"+data[i].substation+"</td><tr>");


				}
				else{

					var classN="\"trWidht NotstatusColor \"";
					$('#activeDeviceTable').append('<tr  class='+classN+'><td onclick=redirectToTable("'+data[i].unitID+'");isActive(3);>'+data[i].substation+'</td><tr>');
					
				}
				$('div#initialloader').delay(300).fadeOut('slow');
			}
		}

	});


});



$('#btn-Convert-Html2Image').on('click', function() {
	html2canvas($('#activeDeviceTable'), {
		onrendered: function(canvas) {                                      

			var saveAs = function(uri, filename) {
				var link = document.createElement('a');
				if (typeof link.download === 'string') {
					document.body.appendChild(link); // Firefox requires the link to be in the body
					link.download = filename;
					link.href = uri;
					link.click();
					document.body.removeChild(link); // remove the link when done
				} else {
					location.replace(uri);
				}
			};

			var img = canvas.toDataURL("image/png"),
			uri = img.replace(/^data:image\/[^;]/, 'data:application/octet-stream');

			saveAs(uri, 'tableExport.png');
		}
	}); 
});

function isActive(feature){	
	window.parent.$('.navData li:nth-child(1)').removeClass("active");
	window.parent.$('.navData li a').each(function(){
		if($(this).attr('data-featureid') == feature){    	 
			$(this).closest('li').addClass("inactive");
			$(this).closest('li').removeClass("active");
			$(this).closest('li').removeClass("inactive");
			$(this).closest('li').addClass("active"); 
		}
	})
}


// get detail based on Unit ID
function redirectToTable(recieveID){
	window.location = "./report?redirectURL=viaDashboard&unitID="+recieveID+"&back=./Home";
				}
