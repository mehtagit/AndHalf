$('input').on('invalid', function(e) {
    setTimeout(function(){
        $('html, body').animate({scrollTop: document.documentElement.scrollTop}, 0);
       // $('html, body').animate({scrollTop: document.documentElement.scrollDown}, 0);
    }, 0);
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
	console.log(" fileName "+fileName+" fileType  "+fileType+" txnId "+txnId+"  doc_TypeTag "+doc_TypeTag)

	$.ajax({
		url : "Consignment/dowloadFiles/"+fileType+'/'+fileName+'/'+txnId+'/'+doc_TypeTag,
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		type : 'GET',
		success : function(data) {
			console.log(data);
			console.log(data.filePath);
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
				//alert("image type");
				$("#viewuplodedModel").openModal();
				$("#fileSource").attr("src",data.url);
				}
			else{
				console.log("file is found");
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

	
	
	
	
	
	
	
	
	function isFileValid(id,hiddenVal,editInputID) {

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
				console.log("file formate is correct")
				
			}
			

		}
	
	
	

	function isImageValid(id) {
		
		var uploadedFileName = $("#"+id).val();
		uploadedFileName = uploadedFileName.replace(/^.*[\\\/]/, '');
		//alert("file extension=="+uploadedFileName)
		var ext = uploadedFileName.split('.').pop();

		var fileSize = ($("#"+id)[0].files[0].size);
		/*fileSize = (Math.round((fileSize / 100000) * 100) / 100)
		alert("----"+fileSize);*/
		fileSize = Math.floor(fileSize/1000);
		$('#FilefieldId').val(id);
		//alert(uploadedFileName+"----------"+ext+"----"+fileSize)
		var areEqual =ext.toLowerCase()=='png';
	
		//alert(areEqual);
		if(areEqual==true)
			{
			ext='PNG';
			}
		if (uploadedFileName.length > 30) {
			$('#fileFormateModal').openModal();
			$('#fileErrormessage').text('');
			$('#fileErrormessage').text($.i18n('imageMessage'));
		} 
		else if(ext !='PNG')
		{
			$('#fileFormateModal').openModal({
				dismissible:false
			});
			$('#fileErrormessage').text('');
			$('#fileErrormessage').text($.i18n('imageMessage'));

		}
		else if(fileSize>=100){
			$('#fileFormateModal').openModal({
				dismissible:false
			});
			$('#fileErrormessage').text('');
			$('#fileErrormessage').text($.i18n('imageSize'));	
		}
		


	}
	
	


	function previewFile(srcFilePath,srcFileName,txnId,doctype){
		console.log("doctype=="+doctype)
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
		
		console.log(FinalLink);
		if(filePath == null || filePath == "" || filePath == undefined && fileName == null || fileName == "" || fileName == undefined ){
			console.log("File is not Avialable")
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