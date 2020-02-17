
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
    	else if(type=="email"){
            textbox.setCustomValidity($.i18n('requiredMsg_email'));	
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