$(function() {
	$('html, body').animate({scrollTop: document.documentElement.scrollTop + 200}, 0);
});


$('input,select,textArea,button').attr('title', window.webkitURL ? ' ' : '');
var txnIdValue = $("body").attr("session-valueTxnID");
var data_lang_param =$("body").attr("data-lang-param") == 'km' ? 'km' : 'en';
var langDropdownValue=$("body").attr("data-lang-param");
var transactionIDValue= txnIdValue == 'null' ? $('#transactionID').val() : txnIdValue;





function fileDownload(fileName,fileType,txnId,doc_TypeTag)
{
	fileName=fileName.split("%20").join(" ");
	if(fileName=='')
	{
		fileName='blankFile';
	}


	$.ajax({
		url : "Consignment/dowloadFiles/"+fileType+'/'+fileName+'/'+txnId+'/'+doc_TypeTag,
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		type : 'GET',
		success : function(data) {
			if(data.url=='Not Found')
			{

				$('#fileFormateModal').openModal({
					dismissible:false
				});
				$('#fileErrormessage').text('')
				$('#fileErrormessage').text($.i18n('fileNotFound'));
			}
			else if(data.filePath=='imageType')
			{
				////alert("image type");
				$("#viewuplodedModel").openModal();
				$("#fileSource").attr("src",data.url);
			}
			else{
			
				window.location.href=data.url;

			}

		},
		error : function() {

		}
	});
}








$("input[type=file]").keypress(function(ev) {
	return false;
	//ev.preventDefault(); //works as well

});
var featureId = window.parent.$('.navData li.active a').attr('data-featureid')









function isFileValid(id) {

	var uploadedFileName = $("#"+id).val();
	uploadedFileName = uploadedFileName.replace(/^.*[\\\/]/, '');
	var ext = uploadedFileName.split('.').pop();
	$('#FilefieldId').val(id);
	var fileSize = ($("#"+id)[0].files[0].size);
	fileSize = (Math.round((fileSize / 1024) * 100) / 100)
	if (uploadedFileName.length > 30) {
		window.parent.$('#fileFormateModal').openModal({
			dismissible:false
		});
	} 
	else if(ext!='csv')
	{
		window.parent.$('#fileFormateModal').openModal({
			dismissible:false
		});
	}
	else if(fileSize>='10000'){
		window.parent.$('#fileFormateModal').openModal({
			dismissible:false
		});
	}
	else {
		//////console.log("file formate is correct")

	}


}


function isImageValid(id) {
	
	var uploadedFileName = $("#"+id).val();
	uploadedFileName = uploadedFileName.replace(/^.*[\\\/]/, '');
	////alert("file extension=="+uploadedFileName)
	var ext = uploadedFileName.split('.').pop();

	var fileSize = ($("#"+id)[0].files[0].size);
	/*fileSize = (Math.round((fileSize / 100000) * 100) / 100)
		//alert("----"+fileSize);*/
	fileSize = Math.floor(fileSize/1000);
	
	$('#FilefieldId').val(id);
	////alert(uploadedFileName+"----------"+ext+"----"+fileSize)
	var fileExtension =ext.toLowerCase();
	////console.log("file type: "+fileExtension);
	var extArray = ["png", "jpg","jpeg","gif","bmp","gif"];
	var isInArray =extArray.includes(fileExtension);
	////console.log("isInArray: "+isInArray)
	if (uploadedFileName.length > 30) {
		$('#fileFormateModal').openModal();
		$('#fileErrormessage').text('');
		$('#fileErrormessage').text($.i18n('imageValidationName'));
	} 
	else if(isInArray ==false)
	{
		$('#fileFormateModal').openModal({
			dismissible:false
		});
		$('#fileErrormessage').text('');
		$('#fileErrormessage').text($.i18n('imageMessage'));

	}
	else if(fileSize>=5000){
		$('#fileFormateModal').openModal({
			dismissible:false
		});
		$('#fileErrormessage').text('');
		$('#fileErrormessage').text($.i18n('imageSize'));	
	}
}

function isPdfAndImageValid(id) {

	var uploadedFileName = $("#"+id).val();
	uploadedFileName = uploadedFileName.replace(/^.*[\\\/]/, '');
	////alert("file extension=="+uploadedFileName)
	var ext = uploadedFileName.split('.').pop();

	var fileSize = ($("#"+id)[0].files[0].size);
	/*fileSize = (Math.round((fileSize / 100000) * 100) / 100)
		//alert("----"+fileSize);*/
	fileSize = Math.floor(fileSize/1000);
	$('#FilefieldId').val(id);
	////alert(uploadedFileName+"----------"+ext+"----"+fileSize)
	var fileExtension =ext.toLowerCase();
	////console.log("file type: "+fileExtension);
	var extArray = ["png","jpg","jpeg","gif","bmp","gif","pdf"];
	var isInArray =extArray.includes(fileExtension);
	////console.log("isInArray: "+isInArray)
	if (uploadedFileName.length > 30) {
		$('#fileFormateModal').openModal();
		$('#fileErrormessage').text('');
		$('#fileErrormessage').text($.i18n('imageValidationName'));
	} 
	else if(isInArray ==false)
	{
		$('#fileFormateModal').openModal({
			dismissible:false
		});
		$('#fileErrormessage').text('');
		$('#fileErrormessage').text($.i18n('imageAndPdfMsg'));

	}
	else if(fileSize>=5000){
		$('#fileFormateModal').openModal({
			dismissible:false
		});
		$('#fileErrormessage').text('');
		$('#fileErrormessage').text($.i18n('imageSize'));	
	}
}



function previewFile(srcFilePath,srcFileName,txnId,doctype){
	////console.log("doctype=="+doctype)
	window.filePath = srcFilePath;
	window.fileName = srcFileName;
	window.fileExtension = fileName.replace(/^.*\./, '');
	if(doctype=='')
	{
		window.FinalLink = filePath.concat(txnId).concat('/'+fileName);
	}
	else if(doctype==undefined)
	{
		window.FinalLink = filePath.concat(txnId).concat('/'+fileName);
	}else{
		window.FinalLink = filePath.concat(txnId).concat('/'+doctype).concat('/'+fileName);
	}

	//////console.log(FinalLink);
	if(filePath == null || filePath == "" || filePath == undefined && fileName == null || fileName == "" || fileName == undefined ){
		////console.log("File is not Avialable")
	}else if(fileExtension=="jpg" || fileExtension=="jpeg" || fileExtension=="png" || fileExtension=="gif" || fileExtension=="PNG" ){
		$("#fileSource").attr("src",FinalLink);
		$("#viewuplodedModel").openModal();
	}else{
		window.open(FinalLink);
	}
}




function messageWindow(message){
	fadetime=2000;
	window.parent.$("#modalMessageBody").empty();
	window.parent.$("#modalMessageBody").append(' <label id="success" style="color: red;font-size:14px;">'+message+'</label>');
	window.parent.$('#error_Modal').openModal();

	window.parent.$('#error_Modal').fadeIn().delay(fadetime).fadeOut();
	setTimeout(function() {
		window.parent.$('#error_Modal').closeModal();
	}, fadetime);

}




function errorMessageReg(message){
	fadetime=2000;
	window.parent.$("#modalMessageBodyReg").empty();
	window.parent.$("#modalMessageBodyReg").append(' <label id="success" style="color: red;font-size:14px;">'+message+'</label>');
	window.parent.$('#error_Modal_reg').openModal();

	window.parent.$('#error_Modal_reg').fadeIn().delay(fadetime).fadeOut();
	setTimeout(function() {
		window.parent.$('#error_Modal_reg').closeModal();
	}, fadetime);
}




$('body').on('click', '#content', function() {
	$('#profile-dropdown', window.parent.document).css("display", "none");
	$('.profileInfo a', window.parent.document).removeClass("active");			
});



function closeViewPage(){
	var txnId=$("body").attr("data-txnID");
	var reqSource=$("body").attr("data-source");
	if(reqSource=='menu'){
	txnId='';	
	}
	else if(reqSource=='filter'){
		reqSource="menu"
			txnId='';
	}
//	alert(reqSource+"-"+txnId)
	window.location.replace("./stolenRecovery?FeatureId=5&txnID="+txnId+"&source="+reqSource);

}

function previewRegistrtionFile(srcFilePath,srcFileName){

	window.filePath = srcFilePath;
	window.fileName = srcFileName;
	window.fileExtension = fileName.replace(/^.*\./, '');
	window.FinalLink = filePath.concat(fileName);

	if(filePath == null || filePath == "" || filePath == undefined && fileName == null || fileName == "" || fileName == undefined ){
		//////console.log("File is not Avialable")
	}else if(fileExtension=="jpg" || fileExtension=="jpeg" || fileExtension=="png" || fileExtension=="gif" ){
		$("#fileSource").attr("src",FinalLink);
		$("#viewuplodedModel").openModal();
	}else{
		window.open(FinalLink);
	}
}


function checkDuplicateImei(CheckIMEI1,CheckIMEI2,CheckIMEI3,CheckIMEI4){
	if(CheckIMEI1==CheckIMEI2 && CheckIMEI2!="" || CheckIMEI1==CheckIMEI3 && CheckIMEI3!="" || CheckIMEI1==CheckIMEI4 && CheckIMEI4!="" || CheckIMEI2==CheckIMEI3 && CheckIMEI3!="" || CheckIMEI2==CheckIMEI4 && CheckIMEI4!="" || CheckIMEI3==CheckIMEI4 && CheckIMEI4!="" ){
		//duplicate IMEI found
		return true;
	}
	else{
		//duplicate IMEI not found"
		return false;
	}
}

function luhn_checksum(code) {
	var IMEI1toPass = code;
	//console.log("IMEI passed to luhn="+IMEI1toPass);
    var parity = len % 2
    var sum = 0
    for (var i = len-1; i >= 0; i--) {
        var d = parseInt(IMEI1toPass.charAt(i))
        if (i % 2 == parity) { d *= 2 }
        if (d > 9) { d -= 9 }
        sum += d
    }
    return sum % 10
}

function luhnCheck(IMEILUHN,IMEIType){
	if($("#"+IMEIType).val()==0){
	var IMEIlenth1=$("#"+IMEILUHN).val().length;
	var IMEI1LastDigit=$("#"+IMEILUHN).val();
	//console.log("IMEI1LastDigit=="+IMEI1LastDigit);
	if (IMEIlenth1==15){
		 var res=luhn_checksum($("#"+IMEILUHN).val());
		//console.log(res);
		if(res==0){
			//IMEI number passed luhn alogoritham .
			$('#errorMsgOnModal').text('');
		}
		else
			{
			//IMEI number passed not passed luhn alogoritham.
			$("#"+IMEILUHN).val('');
			$('#errorMsgOnModal').text('');
			$('#errorMsgOnModal').text($.i18n('luhnCheckMessage'));
			}
	}
	}
	  }
