
/*$.getScript('../resources/project_js/CLDRPluralRuleParser.js');
	*/
$('input').on('invalid', function(e) {
    setTimeout(function(){
        $('html, body').animate({scrollTop: document.documentElement.scrollTop - 15}, 0);
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
console.log(" fileName "+fileName+" fileType  "+fileType+" txnId "+txnId+"  doc_TypeTag "+doc_TypeTag)
	$.ajax({
		url : "Consignment/dowloadFiles/"+fileType+'/'+fileName+'/'+txnId+'/'+doc_TypeTag,
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		type : 'GET',
		success : function(data) {
			console.log(data);
			if(data.url=='Not Found')
				{
				
				$('#fileFormateModal').openModal({
					dismissible:false
				});
				$('#fileErrormessage').text('')
				$('#fileErrormessage').text($.i18n('fileNotFound'));
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
			else if(fileSize>='2000'){
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
		fileSize = Math.floor(fileSize/1000) + 'KB';

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
	
	


	function previewFile(srcFilePath,srcFileName,txnId){
		window.filePath = srcFilePath;
		window.fileName = srcFileName;
		window.fileExtension = fileName.replace(/^.*\./, '');
		window.FinalLink = filePath.concat(txnId).concat('/'+fileName);
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