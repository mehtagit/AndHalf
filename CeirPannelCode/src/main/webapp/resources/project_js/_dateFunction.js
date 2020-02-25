$('#consignmentTableDIv div:last').after('<p id="errorMsg" style="color: red;font-size: 12px;position: absolute;left: 40px;margin: 0;top: 122px;"class="left"></p>')
$('#registrationTableDiv div:last').after('<p id="errorMsg" style="color: red;font-size: 12px;position: absolute;left: 40px;margin: 0;top: 122px;"class="left"></p>')
$('#greivanceTableDiv div:last').after('<p id="errorMsg" style="color: red;font-size: 12px;position: absolute;left: 40px;margin: 0;top: 122px;"class="left"></p>')
$('#FieldTableDiv div:last').after('<p id="errorMsg" style="color: red;font-size: 12px;position: absolute;left: 40px;margin: 0;top: 122px;"class="left"></p>')
$('#updateModal div:last').after('<p id="errorMsg" style="color: red;font-size: 12px;position: absolute;left: 40px;margin: 0;top: 122px;"class="left"></p>')
$('#ImporterAdmintypeAprroveTable div:last').after('<p id="errorMsg" style="color: red;font-size: 12px;position: absolute;left: 40px;margin: 0;top: 122px;"class="left"></p>')

function myStringToDate(str) {
  var arr  = str.split("-"); // split string at slashes to make an array
  var yyyy = arr[2] - 0; // subtraction converts a string to a number
  var mm = arr[1] - 1; // subtract 1 because stupid JavaScript month numbering
  var dd   = arr[0] - 0; // subtraction converts a string to a number 
  return new Date(yyyy-mm-dd); // this gets you your date
}

function checkDate(startDate,endDate) {
	var lang=window.parent.$('#langlist').val() == 'km' ? 'km' : 'en';
	$.i18n().locale = lang;	
	$.i18n().load( {
		'en': '../resources/i18n/en.json',
		'km': '../resources/i18n/km.json',
		'en': './resources/i18n/en.json',
		'km': './resources/i18n/km.json'
	} ).done( function() { 
		
	});
    var input1 = myStringToDate(startDate.value);
    var input2 = myStringToDate(endDate.value);
    if (input2.getTime() ==  input1.getTime()) {
    	$('#errorMsg').text('');
    	$('#'+endDate.id).css('border-color', '');
    	$('#submitFilter,#consignmentSubbmitButton,#filterFieldTable').removeClass( "eventNone" );
    	
    	
    } 
    else if(input2.getTime() <  input1.getTime()){
    	$('#'+endDate.id).css('border-color', 'red');
    	$('#errorMsg').text($.i18n(endDate.id));
    	$('#submitFilter,#consignmentSubbmitButton,#filterFieldTable').addClass( "eventNone" );
    	$('#consignmentSubbmitButton').addClass( "eventNone" );
    }
    else{
    	$('#errorMsg').text('');
    	$('#'+endDate.id).css('border-color', '');
    	$('#submitFilter,#consignmentSubbmitButton').removeClass( "eventNone" );
    }
}
