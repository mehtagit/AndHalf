/*[

	'./resources/js/materialize.js',
	'./resources/custom_js/bootstrap.min.js',
	'./resources/js/plugins/perfect-scrollbar/perfect-scrollbar.min.js'
	].forEach(function(src) {
		$('body').append('<script type="text/javascript" src='+src+' async defer><\/script>');

	});
	

*/
function activeDeviceGraph() {
	[29,29].forEach(function(reportnameId) {
		var graphRequest=null;
		var chartID=null;
		var type=null;
		var title=null;
		if(reportnameId == 29){
			graphRequest={
					 "columns": [
						    "Date",
						    "Operator Name",
						    "Total IMEI"
						  ],
						  "groupBy": "Operator Name",
						  "reportnameId": 29,
						  "file" : 0, 
						  "pageSize" :55, 
						  "pageNo" :0
			}
			
			
			chartID='horizontalBarGraph';
			type='horizontalBar';
			title='User Login HorizontalBar Graph';
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
			async:false,
			data : JSON.stringify(graphRequest),
			/*beforeSend : function() {
				$("#loading-image").show();
			},*/
			success : function(data) {
				
				var response = JSON.parse(data);
				//graph(response,chartID,type,title);
				graph(response,'horizontalBarGraph','line','User Login HorizontalBar Graph');

			},
			error : function() {
			}
		});
	});

}


function graph(response,id,chartType,chartTitle)
{
  var date=[];
  var operatorName=[];
  var totalImei=[];
  var QB=[];
  var seatel=[];
  var smart=[];
  var cellcard=[];
  var metfone=[];
  //var pieLabelName=response['columns'];
  var pieLabelName=['No of user logged','Unique user logged'];
  var pieData=[];
	   //console.log("repsonse-->"+JSON.stringify(response));
		/*noOfUsers.push(52,45,76,87,89);
	   	date.push('05-09-2020','06-09-2020','07-09-2020','08-09-2020','09-09-2020');
	   	uniqueUsers.push(55,21,43,65,76);*/
	   	
	   	//console.log("date: "+date);
	    //console.log("noOfUsers: "+noOfUsers);
	    //console.log("uniqueUserLogged: "+);	
	   	
	   	for(var i=0;i<response['rowData'].length;i++){
	   		QB.push(response['rowData'][i]['QB']);
	   		seatel.push(response['rowData'][i]['SEATEL']);
	   		smart.push(response['rowData'][i]['SMART']);
	   		cellcard.push(response['rowData'][i]['CELLCARD']);
	   		metfone.push(response['rowData'][i]['METFONE']);
	   		date.push(response['rowData'][i]['Date']);
	   		//totalImei.push(response['rowData'][i]['Total IMEI']);

	   	}
	   	
	    var ctx = document.getElementById(''+id+'').getContext('2d');
	    var chart = new Chart(ctx, {
	      // The type of chart we want to create
	      type: ''+chartType+'',

	      // The data for our dataset
	      data: {
	        labels: date,
	        datasets: [{
	            label: "QB",
	            //backgroundColor:  'rgb(70, 191, 189)',
	            borderColor:  'rgb(235, 203, 138)',
	            data: QB,
	            fill: false
	            
	        },
	        {
	            label: "Seatel",
	            //backgroundColor:  'rgb(70, 191, 189)',
	            borderColor: 'rgb(70, 191, 189)',
	            data: seatel,
	            fill: false
	            
	        },
	        {
	            label: "Smart",
	            //backgroundColor:  'rgb(70, 191, 189)',
	            borderColor:  '#512DA8',
	            data: smart,
	            fill: false
	            
	        },
	        {
	            label: "Cellcard",
	            //backgroundColor:  'rgb(70, 191, 189)',
	            borderColor: '#D32F2F',
	            data: cellcard,
	            fill: false
	            
	        },
	        {
	            label: "Metfone",
	            //backgroundColor:  'rgb(70, 191, 189)',
	            borderColor:  '#FFA000',
	            data: metfone,
	            fill: false
	            
	        },
	        
	      /* {
	            label: "Date",
	            //backgroundColor: 'rgb(235, 203, 138)',
	            borderColor:  'rgb(235, 203, 138)',
	            data: date,
	            fill: false
	       }*/]
	      },

	      // Configuration options go here
	      options: {
	    	    responsive: false,
	    	    maintainAspectRatio: false,
	    	    elements: {
	                point:{
	                    radius: 0
	                }
	            },
	    	    plugins: {
				    datalabels: {
				        display: false,
				    },
				    anchor :'end',
		            align :'top',
		            // and if you need to format how the value is displayed...
		            formatter: function(value, context) {
		                return GetValueFormatted(value);
		            }
				},
				
	    	    scales: {
	                xAxes: [{
	                   stacked: true, 
	                   gridLines: {
	                	   
	                      display: false
	                   }
	                }],
	                yAxes: [{
	                   stacked: true,
	                   gridLines: {
	                      display: false
	                   }
	                }]
	             },           
				  legend: {display: true}
	    	}
	    });
	    
    	
   
    $('div#initialloader').delay(300).fadeOut('slow');
}



//boxes
$(document).ready(function(){
	$('div#initialloader').fadeIn('fast');
	var url;

	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$.ajaxSetup({
	headers:
	{ 'X-CSRF-TOKEN': token }
	});
	var graphRequest={
			  "reportnameId": 29,
			  "file" : 0, 
			  "pageSize" :5, 
			  "pageNo" :0
	}
	
	$.ajax({
		type : 'POST',
		url : './report/data',
		contentType : "application/json",
		data : JSON.stringify(graphRequest),
		success: function(data){
			
var i=0;
				Object.keys(data['rowData'][0]).map(function(key){ 
				if(key == 'Date'){
					$('#dateVal').text('Last Update Date: '+data['rowData'][0][key]);
				}
				else{
					if(i == 0){ $('#firstTD').text(data['rowData'][0]['Total IMEI']);}
					else if(i == 1){$('#secondTD').text(data['rowData'][1]['Total IMEI']);}
					else if(i == 2){$('#thirdTD').text(data['rowData'][2]['Total IMEI']);}
					else if(i == 3){$('#fourthTD').text(data['rowData'][3]['Total IMEI']);}
					else if(i == 4){$('#fifthTD').text(data['rowData'][4]['Total IMEI']);}
					
					$("#infoBox").append("<div class='round-circle-center-responsive'><div class='round-circle'><h6 class='right' style='width: 105px;'>"+key+"</h6><p class='circle-para right' style='position:absolute;margin-top:62px;width: 180px;margin-left: 5px;padding-right: 0px !important;'><b id="+i+++">"+data['rowData'][0][key]+"</b> </p><div class='icon-div center'><i class='fa fa-puzzle-piece test-icon' aria-hidden='true'></i></div></div>");
				
				}
				$('div#initialloader').delay(300).fadeOut('slow');
				});
		}
	
	});
	
});


var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
$.ajaxSetup({
headers:
{ 'X-CSRF-TOKEN': token }
});
$.ajax({
	type : 'POST',
	url : './mobileDevice/report/count',
	contentType : "application/json",
	async:false,
	data : JSON.stringify({"reportnameId": 28,"file" : 0,"pageSize" :1,"pageNo" :0}),
	success: function(data){
		Object.keys(data['rowData'][0]).map(function(key){ 
			if(key != 'Date'){
				
				$("#infoBox").append("<div class='round-circle-center-responsive'><div class='round-circle'><h6 class='right' style='width: 105px;'>"+key+"</h6><p class='circle-para right' style='position:absolute;margin-top:62px;width: 180px;margin-left: 5px;padding-right: 0px !important;'><b>"+data['rowData'][0][key]+"</b> </p><div class='icon-div center'><i class='fa fa-puzzle-piece test-icon' aria-hidden='true'></i></div></div>");
			}
			
		});
	}
});
