[
	  './resources/graph_js/chart.min.js',
	  './resources/js/materialize.js',
	  './resources/custom_js/bootstrap.min.js',
	  './resources/js/plugins/perfect-scrollbar/perfect-scrollbar.min.js'
	].forEach(function(src) {
		$('body').append('<script type="text/javascript" src='+src+' async defer><\/script>');

	});

function activeDeviceGraph() {

	var graphRequest={
			  "columns": [
			      "Approved TAC",
			      "Rejected TAC"
			    ],
			  "reportnameId": 31
			}
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$.ajaxSetup({
		headers:
		{ 'X-CSRF-TOKEN': token }
	});
	$.ajax({
		type : 'POST',
		url : './report/data',
		contentType : "application/json",
		dataType : 'html',
		data : JSON.stringify(graphRequest),
		/*beforeSend : function() {
			$("#loading-image").show();
		},*/
		success : function(data) {
			var response = JSON.parse(data);
			graph(response,'pieGraph','pie','User Login Pie Graph')
			
		},
		error : function() {
		}
	});
}


function graph(response,id,chartType,chartTitle)
{

  var pieLabelName=response['columns'];
  var pieData=[];
	for(var i=0;i<response['rowData'].length;i++){
		pieData.push(response['rowData'][i].approvedTAC);
		pieData.push(response['rowData'][i].rejectedTAC);
	}
	
	 if(chartType=='pie'){
    
        var ctx = document.getElementById(''+id+'').getContext('2d');
        var chart = new Chart(ctx, {
          // The type of chart we want to create
          type: ''+chartType+'',

          // The data for our dataset
          data: {
            labels: pieLabelName,
            datasets: [ {
            	 backgroundColor: [
            		 '#512DA8',
                     '#D32F2F'],
                data: pieData
            }]
          },

          // Configuration options go here
          options: {
        	    responsive: false,
        	    maintainAspectRatio: false,
        	    
        	}
        });
    	
    }
    
    
}