function userloginGraph() {

	var obj={
			"startDate":"",
			"endDate":""
	}
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$.ajaxSetup({
		headers:
		{ 'X-CSRF-TOKEN': token }
	});
	$.ajax({
		type : 'POST',
		url : './userLoginGraph',
		contentType : "application/json",
		dataType : 'html',
		data : JSON.stringify(obj),
		/*beforeSend : function() {
			$("#loading-image").show();
		},*/
		success : function(data) {
			var response = JSON.parse(data);
			console.log(response)
			graph(response,'lineGraph','line','User Login Line Graph')
			graph(response,'barGraph','bar','User Login Bar Graph')
			graph(response,'pieGraph','pie','User Login Pie Graph')
			graph(response,'donutGraph','doughnut','User Login Doughnut Graph')
			graph(response,'gaugeGraph','gauge','User Login Doughnut Graph')
			graph(response,'horizontalBarGraph','horizontalBar','User Login HorizontalBar Graph')	
			
		},
		error : function() {
		}
	});
}


function graph(response,id,chartType,chartTitle)
{
  var date=[];
  var noOfUsers=[];
  var uniqueUsers=[];
  var pieLabelName;
  var pieData=[];
	for(var i=0;i<response['line'].length;i++){
		noOfUsers.push(response['line'][i].noUserLogged);
	   	 date.push(response['line'][i].createdOn);
	   	uniqueUsers.push(response['line'][i].uniqueUserLogged);
	    }
	//console.log("date: "+date);
	   //console.log("noOfUsers: "+noOfUsers);
	    //console.log("uniqueUserLogged: "+);	
// pie
	for(var i=0;i<response['pie'].length;i++){
		pieLabelName=response['pie'][i].labels;
		pieData.push(response['pie'][i].noUserLogged);
		pieData.push(response['pie'][i].uniqueUserLogged);
	    }
	
    if(chartType=='pie' || chartType=='doughnut'){
    
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
        	    plugins: {
				    datalabels: {
				      formatter: (value, ctx) => {
				    	  let datasets = ctx.chart.data.datasets;

				          if (datasets.indexOf(ctx.dataset) === datasets.length - 1) {
				            let sum = datasets[0].data.reduce((a, b) => a + b, 0);
				            let percentage = Math.round((value / sum) * 100) + '%';
				            return percentage;
				          } else {
				            return percentage;
				          }
				          },
				      color: '#fff',
				    }
				  }
        	    
        	}
        });
    	
    }
    else if(chartType=='gauge'){

        
        var ctx = document.getElementById(''+id+'').getContext('2d');
        var chart = new Chart(ctx, {
          // The type of chart we want to create
          type: 'doughnut',

          // The data for our dataset
          data: {
            labels: pieLabelName,
            datasets: [ {
            	 backgroundColor: [
            		   'rgba(255, 99, 132, 0.2)',
                       'rgba(54, 162, 235, 0.2)'],
                data: pieData
            }],
            borderWidth: 0
          },

          // Configuration options go here
          options: {
        	    responsive: false,
        	    maintainAspectRatio: false,
        	    rotation: 1 * Math.PI,
                circumference: 1 * Math.PI,
         	    plugins: {
				    datalabels: {
				      formatter: (value, ctx) => {
				    	  let datasets = ctx.chart.data.datasets;

				          if (datasets.indexOf(ctx.dataset) === datasets.length - 1) {
				            let sum = datasets[0].data.reduce((a, b) => a + b, 0);
				            let percentage = Math.round((value / sum) * 100) + '%';
				            return percentage;
				          } else {
				            return percentage;
				          }
				          },
				      color: '#fff',
				    }
				  }
             }
        });
    	
    
    }
    
    else if(chartType == 'horizontalBar'){
    	var bar_ctx = document.getElementById(''+id+'');
    	var bar_chart = new Chart(bar_ctx, {
    	    type: ''+chartType+'',
    	    data: {
    	        labels: date,
    	        datasets: [
    	        	{
    	                label: "Number Of Users",
    	                backgroundColor: "#512DA8",
						hoverBackgroundColor: "#7E57C2",
    	                data: noOfUsers
    	            },
    	            {
    	                label: "Unique Users",
    	            	backgroundColor: "#FFA000",
						hoverBackgroundColor: "#FFCA28",
    	                data: uniqueUsers
    	            }]
    	    },
    	    options: {
    	        responsive: false,
        	    maintainAspectRatio: false,
    	     		animation: {
    	        	duration: 10,
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
    	            gridLines: { display: false },
    	            }],
    	          yAxes: [{ 
    	          	stacked: true
    	           
    	            }],
    	        }, // scales
    	        legend: {display: true}
    	    } // options
    	   }
    	);
    }
    else{
    var ctx = document.getElementById(''+id+'').getContext('2d');
    var chart = new Chart(ctx, {
      // The type of chart we want to create
      type: ''+chartType+'',

      // The data for our dataset
      data: {
        labels: date,
        datasets: [ {
            label: "Number Of Users",
            backgroundColor:  'rgb(70, 191, 189)',
            borderColor: 'rgb(70, 191, 189)',
            data: noOfUsers
        },
        {
            label: "Unique Users",
          backgroundColor: 'rgb(235, 203, 138)',
            borderColor:  'rgb(235, 203, 138)',
            data: uniqueUsers
        }]
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
                   gridLines: {
                      display: false
                   }
                }],
                yAxes: [{
                   gridLines: {
                      display: false
                   }
                }]
             }           
             
    	}
    });
    }   
}

