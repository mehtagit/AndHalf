

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
			graph(response,'lineGraph','line','User Login Line Graph')
			graph(response,'barGraph','bar','User Login Bar Graph')
		},
		error : function() {
		}
	});
}

/*function areaGraph(response,gradientColor1, gradientColor2, opacity,id){
    var timeduration=[];
    var date=[];
    var duration_name;
    for(var i=0;i<response.length;i++){
   	 timeduration.push(response[i].timeduration);
   	 date.push(response[i].createdOn);
   	 duration_name=response[0].duration_name;
    }
    var color1 = gradientColor1;
	var color2 = gradientColor2;
    if(gradientColor1.includes("#")){
		color1 = hexToRgb(gradientColor1);
	}
	if(gradientColor2.includes("#")){
		color2= hexToRgb(gradientColor2);
	}

    var ctx = document.getElementById(id).getContext("2d");
    var gradientStroke = ctx.createLinearGradient(0, 500, 0, 100);
    gradientStroke.addColorStop(0, color1.replace(')', ', '+opacity+')').replace('rgb', 'rgba'));
    gradientStroke.addColorStop(1, color2.replace(')', ', '+opacity+')').replace('rgb', 'rgba')); //pink colors
    
    var lineGradientStroke = ctx.createLinearGradient(500, 0, 100, 0);
    lineGradientStroke.addColorStop(0, gradientColor1);
    lineGradientStroke.addColorStop(1, gradientColor2); //pink colors
    
    var data = {
    		 labels: date,
    	        datasets: [{
    	          label:duration_name,
    	          fill: true,
    	         backgroundColor: gradientStroke,
   	          borderColor:lineGradientStroke,
   	          borderWidth: 2,
   	          borderDash: [],
   	          borderDashOffset: 0.0,
   	          pointBackgroundColor: gradientColor1.replace(')', ', '+opacity+')').replace('rgb', 'rgba'),
   	          pointBorderColor: 'rgba(255,255,255,0)',
   	          pointHoverBackgroundColor:gradientColor1,
   	          pointBorderWidth: 20,
   	          pointHoverRadius: 4,
   	          pointHoverBorderWidth: 15,
   	          pointRadius: 4,
   	          data: timeduration,
      }]
    };  
    
 //   if(chart44) chart44.destroy();
    chart44 = new Chart(ctx, {
      type: 'line',
      data: data,
      options: gradientChartOptionsConfigurationWithTooltipPurple
      
    });
}*/

function graph(response,id,chartType,chartTitle)
{
  var date=[];
  var noOfUsers=[];
  var uniqueUsers=[];
	for(var i=0;i<response.length;i++){
		noOfUsers.push(response[i].noUserLogged);
	   	 date.push(response[i].createdOn);
	   	uniqueUsers.push(response[i].uniqueUserLogged);
	    }
	//console.log("date: "+date);
	   //console.log("noOfUsers: "+noOfUsers);
	    //console.log("uniqueUserLogged: "+);	

    
    
    var ctx = document.getElementById(''+id+'').getContext('2d');
    var chart = new Chart(ctx, {
      // The type of chart we want to create
      type: ''+chartType+'',

      // The data for our dataset
      data: {
        labels: date,
        datasets: [ {
            label: "Number Of Users",
            backgroundColor: 'rgb(255, 99, 132)',
            borderColor: 'rgb(255, 99, 132)',
            data: noOfUsers
        },
        {
            label: "Unique Users",
            backgroundColor: 'black',
            borderColor: 'black',
            data: uniqueUsers
        }]
      },

      // Configuration options go here
      options: {
    	    responsive: false,
    	    maintainAspectRatio: false,
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