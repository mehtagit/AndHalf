var lang=window.parent.$('#langlist').val() == 'km' ? 'km' : 'en';


		$.i18n().locale = lang;	
		
		$.i18n().load( {
			'en': '../resources/i18n/en.json',
			'km': '../resources/i18n/km.json'
		} ).done( function() { 
		});
function InvalidMsg(textbox,type) {
    if (textbox.value == '') {
    	if(type=="input"){
        textbox.setCustomValidity($.i18n('requiredMsg_input'));
    	}
    	else if(type=="date"){
            textbox.setCustomValidity($.i18n('requiredMsg_date'));	
    	}
    	else if(type=="select"){
            textbox.setCustomValidity($.i18n('requiredMsg_select'));	
    	}
    	else if(type=="fileType"){
            textbox.setCustomValidity($.i18n('requiredMsg_fileType'));	
    	}
    	
    }
  /*  else if(textbox.validity.typeMismatch){
        
        if(type=="input"){
        	textbox.setCustomValidity($.i18n('requiredMsg_input'));
        	}
        	else if(type=="date"){
        		textbox.setCustomValidity($.i18n('requiredMsg_date'));	
        	}
        	else if(type=="select"){
        		textbox.setCustomValidity($.i18n('requiredMsg_select'));	
        	}
        	else if(type=="fileType"){
        		textbox.setCustomValidity($.i18n('requiredMsg_fileType'));
        	}
        	
        
    }*/
    else {
        textbox.setCustomValidity('');
    }
    return true;
}