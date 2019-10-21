/**
 * 
 */

function simpless()
{
alert("this  is js");	
}

function isNumericValue(number)
{
	return /^([0-9])*$/i.test(number);
}
function isAlphanumericValue(number){
	return /^[a-zA-Z0-9  ]*$/i.test(number);
	
}
function isAlphabateic(number){
	return /^[a-zA-Z  ]*$/i.test(number);
	
}

function isEmail(number){
	return /^[a-zA-Z  ]*$/i.test(number);
	
}

function isNumericWithComma(number){
	 return /^[0-9]*([,]([0-9]*)*)*$/i.test(number);
}

function dateValidation(startDate,endDate)
{
	var result="";
	if(Date.parse(startDate)>Date.parse(endDate)){
		result="smaller";
	}
	return result;
}



var _Validator = {
		
        _isNumeric: function (v) {
        	   
        	return /^([0-9])*$/i.test(v);
        },
        _isAlphanumeric: function (v) {
                return /^[a-zA-Z0-9  ]*$/i.test(v);
        },
        _isAlphabateic: function (v) {
                return /^[a-zA-Z  ]*$/i.test(v);
        },
        _isEmail: function (v) {
                return /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/i.test(v);
                },
                
        _isNumericWithComma: function (v) {
                return /^[0-9]*([,]([0-9]*)*)*$/i.test(v);
        },
        _removeSpace: function (v) {
                return v.split(' ').join('');
        },
        _notSp: function (v) {
                return /^([a-zA-Z0-9_ ])*$/i.test(v);
        },
        _escapeQu: function (id) { //console.log(id);
                var valText = $('#' + id).val();
                $('#' + id).val(valText.replace(/\'/g, ""));
                //return /^([^'"])*$/i.test(v);
        },
        _isFileExtensionValid: function(fileName,arrExtensions) {
                var ext = (fileName).split(".");
                ext = ext[ext.length-1].toLowerCase();
                if (arrExtensions.lastIndexOf(ext) == -1) {                        
                        return false;		
                 }       
                return true;
        },
        _regex: function (v, t) {
                var reg;
                if (t == ')') {
                        reg = new RegExp('^([)]{1,3})$');
                } else if (t == '(') {
                        reg = new RegExp('^([(]{1,3})$');
                } else if (t.trim().toUpperCase() == 'DOUBLE') {
                        reg = new RegExp('^([0-9.]*)$');
                } else if (t.trim().toUpperCase() == 'DATE') {
                        reg = new RegExp('^((0?[13578]|10|12)(-|\/)(([1-9])|(0[1-9])|([12])([0-9]?)|(3[01]?))(-|\/)((19)([2-9])(\d{1})|(20)([01])(\d{1})|([8901])(\d{1}))|(0?[2469]|11)(-|\/)(([1-9])|(0[1-9])|([12])([0-9]?)|(3[0]?))(-|\/)((19)([2-9])(\d{1})|(20)([01])(\d{1})|([8901])(\d{1})))$');
                } else if (t.trim().toUpperCase() == 'INT' || t.trim().toUpperCase() == 'BIGINT') {
                        reg = new RegExp('^([0-9]*)$');
                } else if (t.trim().toUpperCase() == 'STRING') {
                        reg = new RegExp('^([a-zA-Z0-9]*)$');
                } else if (t.trim().toUpperCase() == "NUMBER") {
                        reg = new RegExp('^[+-]?[0-9]{1,9}(?:\.[0-9]{1,2})?$');
                }
                return reg.test(v);
        },
        _hasDuplicatesInArray(array, defaultD) {
                var valuesSoFar = [];
                for (var i = 0; i < array.length; ++i) {
                        var value = array[i];
                        if (value !== defaultD) {
                                if (valuesSoFar.indexOf(value) !== -1) {
                                        return true;
                                }   
                        }
                        valuesSoFar.push(value);
                }
                return false;
        }
        
}
