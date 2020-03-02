package org.gl.ceir.Class.HeadersTitle;

import java.util.List;

import javax.annotation.PostConstruct;

import org.gl.ceir.CeirPannelCode.Model.ActionModel;
import org.gl.ceir.configuration.Translator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class IconsState {
	@Autowired
	Translator Translator;

	@Value ("${projectPath}")
	String projectPath;

	String className = "emptyClass";
	String disableIconClass = "eventNone";
	String emptyURL="JavaScript:void(0);";
	private final Logger log = LoggerFactory.getLogger(getClass());
	// fa fa icons
	String errorIcon="\"fa fa-exclamation-circle  error-icon\"";
	String downloadIcon="\"fa fa-download download-icon\""; 
	String viewIcon="\"fa fa-eye teal-text view-icon\"";
	String editIcon="\"fa fa-pencil edit-icon\""; 
	String deletionIcon="\"fa fa-trash delete-icon\"";
	String replyIcon="\"fa fa-reply reply-icon\""; 
	String approveIcon = "\"fa fa-check-circle-o approve-icon\"";
	String rejectIcon = "\"fa fa-user-times reject-icon\"";
	String payTaxIcon = "\"fa fa-money pay-tax-icon\"";
	String ListIcon =  "\"fa fa-list list-icon\"";
	String plusIcon = "\"fa fa-plus-square download-icon\"";

	// icon title  
	String errorIconTitle= null;
	String downloadIconTitle=null;
	String viewIconTitle=null;
	String editIconTitle=null;
	String deleteIconTitle=null; 
	String replyIconTitle=null;
	String approveIconTitle=null;
	String rejectIconTitle=null;
	String payTaxIconTitle =null;
	String ListIconTittle = null;
	String plusIconTittle = null;

	String disableErrorIcon="\"fa fa-exclamation-circle error-icon disable\""; 
	String disableDownloadIcon="\"fa fa-download download-icon disable\""; 
	String disableViewIcon="\"fa fa-eye view-icon disable\"";
	String disableEditIcon="\"fa fa-pencil edit-icon disable\""; 
	String disableDeletionIcon="\"fa fa-trash delete-icon disable\"";
	String disableReplyIcon="\"fa fa-reply reply-icon disable\""; 
	String disableApproveIcon = "\"fa fa-check-circle-o approve-icon disable\"";
	String disableRejectIcon = "\"fa fa-user-times reject-icon disable\"";
	String disablePayTaxICon =  "\"fa fa-money pay-tax-icon disable\"";
	String defaultTagName="DEFAULT";

	public String state(String fileName,String txnId ,String status,String userStatus) {
		// URL link
		executePostConstruct();
		/*String downloadURL = "./dowloadFiles/actual/"+fileName.replace(" ", "%20")+"/"+txnId+"/"+defaultTagName+"";*/
		//String errorURL = "./dowloadFiles/error/"+fileName.replace(" ", "%20")+"/"+txnId+"/"+defaultTagName+"";
		String errorURL = "consignmentFileDownload('"+fileName.replace(" ", "%20")+"','error','"+txnId+"','"+defaultTagName+"')";
		String downloadURL = "consignmentFileDownload('"+fileName.replace(" ", "%20")+"','actual','"+txnId+"','"+defaultTagName+"')";
		String viewAction="viewConsignmentDetails('"+txnId+"')"; 
		String editAction="EditConsignmentDetails('"+txnId+"')";
		String deleteAction ="DeleteConsignmentRecord('"+txnId+"')";
		log.info("downloadURL::::::::::::::"+downloadURL);

		// state related Code 
		String error="<a onclick="+errorURL+"><i class="+errorIcon+" aria-hidden=\"true\" title="
				+errorIconTitle+"></i></a>";
		String download="<a onclick="+downloadURL+" ><i class="
				+downloadIcon+" aria-hidden=\"true\" title="
				+downloadIconTitle+" ></i></a>"; 
		String view="<a onclick="+viewAction+"><i class="+viewIcon+" aria-hidden=\"true\" title="
				+viewIconTitle+" ></i></a>";
		String edit="<a onclick="+editAction+"><i class="
				+editIcon+" aria-hidden=\"true\"  title="
				+editIconTitle+"></i></a>"; 
		String delete="<a onclick="+deleteAction+" class=\"waves-effect waves-light modal-trigger\"><i class="
				+deletionIcon+" aria-hidden=\"true\"  title="
				+deleteIconTitle+"></i></a>"; 
		String reply="<a href="+emptyURL+" class=\"waves-effect waves-light modal-trigger\"><i class="
				+deletionIcon+" aria-hidden=\"true\"  title="
				+replyIconTitle+"></i></a>";



		if(("0".equals(status)) && "Approved".equals(userStatus)) {
			error="<a onclick="+errorURL+" class="+disableIconClass+"><i  class="
					+disableErrorIcon+" aria-hidden=\"true\" title="
					+errorIconTitle+"  ></i></a>"; 
		}

		else if(("1".equals(status)) && "Approved".equals(userStatus)) {
			error="<a onclick="+errorURL+" class="+disableIconClass+"><i  class="
					+disableErrorIcon+" aria-hidden=\"true\" title="
					+errorIconTitle+"  ></i></a>"; 
			edit="<a onclick="+editAction+" class="+disableIconClass+"><i class="
					+disableEditIcon+" aria-hidden=\"true\"  title="
					+editIconTitle+"></i></a>"; 
			delete="<a onclick="+deleteAction+" class=\"waves-effect waves-light modal-trigger eventNone\"><i class="
					+disableDeletionIcon+" aria-hidden=\"true\" title="
					+deleteIconTitle+"></i></a>";
		}
		else if(("3".equals(status) || "5".equals(status) || "6".equals(status) || "8".equals(status) || "9".equals(status))  && "Approved".equals(userStatus)) {
			error="<a onclick="+errorURL+" class="+disableIconClass+"><i  class="
					+disableErrorIcon+" aria-hidden=\"true\" title="
					+errorIconTitle+"  ></i></a>"; 
			edit="<a onclick="+editAction+" class="+disableIconClass+"><i class="
					+disableEditIcon+" aria-hidden=\"true\" title="
					+editIconTitle+"></i></a>"; 
			delete="<a onclick="+deleteAction+" class=\"waves-effect waves-light modal-trigger eventNone\"><i class="
					+disableDeletionIcon+" aria-hidden=\"true\" title="
					+deleteIconTitle+"></i></a>";
		}else if(("4".equals(status) || "7".equals(status)) && "Approved".equals(userStatus)) {
			delete="<a onclick="+deleteAction+" class=\"waves-effect waves-light modal-trigger eventNone\"><i class="
					+disableDeletionIcon+" aria-hidden=\"true\" title="
					+deleteIconTitle+"></i></a>";

		}
		else if("Disable".equals(userStatus)) {
			log.info("CURRENT USER CANN'T ACCESS BCOZ STATUS IS::::::"+userStatus);
			error="<a onclick="+errorURL+" class="+disableIconClass+"><i class="+disableErrorIcon+" aria-hidden=\"true\" title="
					+errorIconTitle+" ></i></a>";
			download="<a onclick="+downloadURL+"  class="+disableIconClass+"><i class="
					+disableDownloadIcon+" aria-hidden=\"true\"  title="
					+downloadIconTitle+" ></i></a>"; 
			edit="<a onclick="+editAction+" class="+disableIconClass+"><i class="
					+disableEditIcon+" aria-hidden=\"true\"  title="
					+editIconTitle+"></i></a>"; 
			delete="<a onclick="+deleteAction+" class=\"waves-effect waves-light modal-trigger eventNone\" ><i class="
					+disableDeletionIcon+" aria-hidden=\"true\" title="
					+deleteIconTitle+"></i></a>"; 			
		}

		String action=error.concat(download).concat(view).concat(edit).concat(delete);		  
		return action;

	}




	public String stockState(String fileName,String txnId,String status,String userStatus) {
		// URL link 
		executePostConstruct();
		String emptyURL="JavaScript:void(0);"; 
		//String downloadURL = "./Consignment/dowloadFiles/actual/"+fileName.replace(" ", "%20")+"/"+txnId+"/"+defaultTagName+"";
		String downloadURL = "fileDownload('"+fileName.replace(" ", "%20")+"','actual','"+txnId+"','"+defaultTagName+"')";
		String errorURL = "fileDownload('"+fileName.replace(" ", "%20")+"','error','"+txnId+"','"+defaultTagName+"')";
		//String errorURL = "./Consignment/dowloadFiles/error/"+fileName.replace(" ", "%20")+"/"+txnId+"/"+defaultTagName+"";	
		String viewAction="viewUploadedStockDetails('"+txnId+"')"; 
		String editAction="EditUploadedStockDetails('"+txnId+"')";
		String deleteAction ="DeleteStockRecord('"+txnId+"')";

		// state related Code 
		String error="<a onclick="+errorURL+"><i class="+errorIcon+" aria-hidden=\"true\" title="
				+errorIconTitle+" ></i></a>";
		String download="<a onclick="+downloadURL+" ><i class="
				+downloadIcon+" aria-hidden=\"true\"  title="
				+downloadIconTitle+" ></i></a>"; 
		String view="<a onclick="+viewAction+"><i class="+viewIcon+" aria-hidden=\"true\" title="
				+viewIconTitle+" ></i></a>";
		String edit="<a onclick="+editAction+"><i class="
				+editIcon+" aria-hidden=\"true\" title="
				+editIconTitle+"></i></a>"; 
		String delete="<a onclick="+deleteAction+" class=\"waves-effect waves-light modal-trigger\"><i class="
				+deletionIcon+" aria-hidden=\"true\"  title="
				+deleteIconTitle+"></i></a>"; 
		String reply="<a href="+emptyURL+" class=\"waves-effect waves-light modal-trigger\"><i class="
				+deletionIcon+" aria-hidden=\"true\"  title="
				+replyIconTitle+"></i></a>";

		if(("0".equals(status))  && "Approved".equals(userStatus)) {
			error="<a onclick="+errorURL+" class="+disableIconClass+"><i  class="
					+disableErrorIcon+" aria-hidden=\"true\" title="
					+errorIconTitle+"  ></i></a>"; 
		}

		else if(("8".equals(status) || "4".equals(status) ||"6".equals(status) || "1".equals(status))  && "Approved".equals(userStatus)) {
			error="<a onclick="+errorURL+" class="+disableIconClass+"><i  class="
					+disableErrorIcon+" aria-hidden=\"true\" title="
					+errorIconTitle+"  ></i></a>"; 
			edit="<a onclick="+editAction+" class="+disableIconClass+"><i class="
					+disableEditIcon+" aria-hidden=\"true\" title="
					+editIconTitle+"></i></a>"; 
			delete="<a onclick="+deleteAction+" class=\"waves-effect waves-light modal-trigger eventNone\"><i class="
					+disableDeletionIcon+" aria-hidden=\"true\"  title="
					+deleteIconTitle+"></i></a>";
		}

		else if("3".equals(status) && "Approved".equals(userStatus)) {
			error="<a onclick="+errorURL+" class="+disableIconClass+"><i  class="
					+disableErrorIcon+" aria-hidden=\"true\" title="
					+errorIconTitle+"  ></i></a>"; 
			edit="<a onclick="+editAction+" class="+disableIconClass+"><i class="
					+disableEditIcon+" aria-hidden=\"true\" title="
					+editIconTitle+"></i></a>"; 
			delete="<a onclick="+deleteAction+" class=\"waves-effect waves-light modal-trigger eventNone\"><i class="
					+disableDeletionIcon+" aria-hidden=\"true\"  title="
					+deleteIconTitle+"></i></a>";
		}
		else if("7".equals(status) && "Approved".equals(userStatus)) {
			delete="<a onclick="+deleteAction+" class=\"waves-effect waves-light modal-trigger eventNone\"><i class="
					+disableDeletionIcon+" aria-hidden=\"true\"  title="
					+deleteIconTitle+"></i></a>";
			download="<a onclick="+downloadURL+"  class="+disableIconClass+"><i class="
					+disableDownloadIcon+" aria-hidden=\"true\"  title="
					+downloadIconTitle+" ></i></a>"; 
		}

		else if("Disable".equals(userStatus)) {
			log.info("CURRENT USER CANN'T ACCESS BCOZ STATUS IS::::::"+userStatus);
			error="<a onclick="+errorURL+" class="+disableIconClass+"><i class="+disableErrorIcon+" aria-hidden=\"true\" title="
					+errorIconTitle+" ></i></a>";
			download="<a onclick="+downloadURL+"  class="+disableIconClass+"><i class="
					+disableDownloadIcon+" aria-hidden=\"true\"  title="
					+downloadIconTitle+" ></i></a>"; 
			edit="<a onclick="+editAction+" class="+disableIconClass+"><i class="
					+disableEditIcon+" aria-hidden=\"true\"  title="
					+editIconTitle+"></i></a>"; 
			delete="<a onclick="+deleteAction+" class=\"waves-effect waves-light modal-trigger eventNone\"><i class="
					+disableDeletionIcon+" aria-hidden=\"true\"  title="
					+deleteIconTitle+"></i></a>"; 			
		}
		String action=error.concat(download).concat(view).concat(edit).concat(delete);
		return action;
	}


	/********************************** Icons for custom Stock  **********************************/ 

	public String customStockState(String fileName,String txnId,String status,String userStatus) {
		executePostConstruct();
		// URL link 
		String emptyURL="JavaScript:void(0);"; 
		//String downloadURL = "./Consignment/dowloadFiles/actual/"+fileName.replace(" ", "%20")+"/"+txnId+"/"+defaultTagName+"";
		String downloadURL = "fileDownload('"+fileName.replace(" ", "%20")+"','actual','"+txnId+"','"+defaultTagName+"')";
		String errorURL = "fileDownload('"+fileName.replace(" ", "%20")+"','error','"+txnId+"','"+defaultTagName+"')";
		//String errorURL = "./Consignment/dowloadFiles/error/"+fileName.replace(" ", "%20")+"/"+txnId+"/"+defaultTagName+"";	
		String viewAction="viewUploadedStockDetails('"+txnId+"')"; 
		String editAction="EditUploadedStockDetails('"+txnId+"')";
		String deleteAction ="DeleteStockRecord('"+txnId+"')";

		// state related Code 
		String error="<a onclick="+errorURL+"><i class="+errorIcon+" aria-hidden=\"true\" title="
				+errorIconTitle+" ></i></a>";
		String download="<a onclick="+downloadURL+" ><i class="
				+downloadIcon+" aria-hidden=\"true\"  title="
				+downloadIconTitle+" ></i></a>"; 
		String view="<a onclick="+viewAction+"><i class="+viewIcon+" aria-hidden=\"true\" title="
				+viewIconTitle+" ></i></a>";
		String edit="<a onclick="+editAction+"><i class="
				+editIcon+" aria-hidden=\"true\" title="
				+editIconTitle+"></i></a>"; 
		String delete="<a onclick="+deleteAction+" class=\"waves-effect waves-light modal-trigger\"><i class="
				+deletionIcon+" aria-hidden=\"true\"  title="
				+deleteIconTitle+"></i></a>"; 
		
		if("0".equals(status) || "1".equals(status) ||  "3".equals(status) || "10".equals(status)  && "Approved".equals(userStatus)) {
			error="<a onclick="+errorURL+" class="+disableIconClass+"><i  class="
					+disableErrorIcon+" aria-hidden=\"true\" title="
					+errorIconTitle+"  ></i></a>";
			edit="<a onclick="+editAction+" class="+disableIconClass+"><i class="
					+disableEditIcon+" aria-hidden=\"true\"  title="
					+editIconTitle+"></i></a>"; 
			delete="<a onclick="+deleteAction+" class=\"waves-effect waves-light modal-trigger eventNone\" ><i class="
					+disableDeletionIcon+" aria-hidden=\"true\" title="
					+deleteIconTitle+"></i></a>"; 
		}

		else if("1".equals(status) || "3".equals(status) || "10".equals(status)  && "Approved".equals(userStatus)) {
			edit="<a onclick="+editAction+" class="+disableIconClass+"><i class="
					+disableEditIcon+" aria-hidden=\"true\" title="
					+editIconTitle+"></i></a>"; 

		}
		else if("3".equals(status) && "Approved".equals(userStatus)) {
			error="<a onclick="+errorURL+" class="+disableIconClass+"><i  class="
					+disableErrorIcon+" aria-hidden=\"true\" title="
					+errorIconTitle+"  ></i></a>"; 
			edit="<a onclick="+editAction+" class="+disableIconClass+"><i class="
					+disableEditIcon+" aria-hidden=\"true\" title="
					+editIconTitle+"></i></a>"; 
			delete="<a onclick="+deleteAction+" class=\"waves-effect waves-light modal-trigger eventNone\"><i class="
					+disableDeletionIcon+" aria-hidden=\"true\"  title="
					+deleteIconTitle+"></i></a>"; 	
		}

		else if("Disable".equals(userStatus)) {
			log.info("CURRENT USER CANN'T ACCESS BCOZ STATUS IS::::::"+userStatus);
			error="<a onclick="+errorURL+" class="+disableIconClass+"><i class="+disableErrorIcon+" aria-hidden=\"true\" title="
					+errorIconTitle+" ></i></a>";
			download="<a onclick="+downloadURL+"  class="+disableIconClass+"><i class="
					+disableDownloadIcon+" aria-hidden=\"true\"  title="
					+downloadIconTitle+" ></i></a>"; 
			edit="<a onclick="+editAction+" class="+disableIconClass+"><i class="
					+disableEditIcon+" aria-hidden=\"true\"  title="
					+editIconTitle+"></i></a>"; 
			delete="<a onclick="+deleteAction+" class=\"waves-effect waves-light modal-trigger eventNone\"><i class="
					+disableDeletionIcon+" aria-hidden=\"true\"  title="
					+deleteIconTitle+"></i></a>"; 			
		}
		String action=error.concat(download).concat(view).concat(edit).concat(delete);
		return action;
	}










	/********************************** Icons for Stolen **********************************/ 

	public String stolenState(String fileName,String txnId ,String status,String userStatus, String requestType,int id,Integer qty) {
		executePostConstruct();
		// URL link 
		String file = fileName == null ? null : fileName.replace(" ", "%20");
		String emptyURL="JavaScript:void(0);"; 
		String errorURL = "fileDownload('"+file+"','error','"+txnId+"','"+defaultTagName+"')";
		//String errorURL = "./Consignment/dowloadFiles/error/"+file+"/"+txnId+"/"+defaultTagName+"";	
		//String downloadURL = "./Consignment/dowloadFiles/actual/"+file+"/"+txnId+"/"+defaultTagName+"";
		String downloadURL = "fileDownload('"+file+"','actual','"+txnId+"','"+defaultTagName+"')";
		String editAction="openFileStolenUpdate('"+txnId+"','"+requestType+"','"+id+"','"+qty+"')";
		String deleteAction ="DeleteConsignmentRecord('"+txnId+"','"+id+"')";


		// state related Code 
		String error="<a onclick="+errorURL+"><i class="+errorIcon+" aria-hidden=\"true\" title="
				+errorIconTitle+" ></i></a>";
		String download="<a onclick="+downloadURL+" ><i class="
				+downloadIcon+" aria-hidden=\"true\" title="
				+downloadIconTitle+" ></i></a>"; 
		String edit="<a onclick="+editAction+"><i class="+editIcon+" aria-hidden=\"true\"  title="
				+editIconTitle+"></i></a>"; 
		String delete="<a onclick="+deleteAction+" class=\"waves-effect waves-light modal-trigger\"><i class="+deletionIcon+" aria-hidden=\"true\"  title="
				+deleteIconTitle+"></i></a>"; 


		if("0".equals(status) || "2".equals(status) && "Approved".equals(userStatus) ) {
			error="<a onclick="+errorURL+" class="+disableIconClass+"><i class="
					+disableErrorIcon+" aria-hidden=\"true\" title="
					+errorIconTitle+"  ></i></a>"; 
			edit="<a onclick="+editAction+" class="+disableIconClass+"><i class="
					+disableEditIcon+" aria-hidden=\"true\"  title="
					+editIconTitle+"></i></a>";
		}
		else if( "1".equals(status) && "Approved".equals(userStatus)) {
			error="<a onclick="+errorURL+" class="+disableIconClass+"><i class="
					+disableErrorIcon+" aria-hidden=\"true\" title="
					+errorIconTitle+"  ></i></a>"; 
			edit="<a onclick="+editAction+" class="+disableIconClass+"><i class="
					+disableEditIcon+" aria-hidden=\"true\"  title="
					+editIconTitle+"></i></a>"; 
			delete="<a onclick="+deleteAction+" class=\"waves-effect waves-light modal-trigger eventNone\" ><i class="
					+disableDeletionIcon+" aria-hidden=\"true\"  title="
					+deleteIconTitle+"></i></a>"; 
		}

		else  if("2".equals(status) && "Approved".equals(userStatus) ) {
			delete="<a onclick="+deleteAction+" class=\"waves-effect waves-light modal-trigger eventNone\" ><i class="
					+disableDeletionIcon+" aria-hidden=\"true\"  title="
					+deleteIconTitle+"></i></a>"; 
		} 


		else if("Disable".equals(userStatus)) {
			log.info("CURRENT USER CANN'T ACCESS BCOZ STATUS IS::::::"+userStatus);
			error="<a onclick="+errorURL+" class="+disableIconClass+"><i class="+disableErrorIcon+" aria-hidden=\"true\" title="
					+errorIconTitle+" ></i></a>";
			download="<a onclick="+downloadURL+"  class="+disableIconClass+"><i class="
					+disableDownloadIcon+" aria-hidden=\"true\"  title="
					+downloadIconTitle+" ></i></a>"; 
			edit="<a onclick="+editAction+" class="+disableIconClass+"><i class="
					+disableEditIcon+" aria-hidden=\"true\"  title="
					+editIconTitle+"></i></a>"; 
			delete="<a onclick="+deleteAction+" class=\"waves-effect waves-light modal-trigger eventNone\" ><i class="
					+disableDeletionIcon+" aria-hidden=\"true\"  title="
					+deleteIconTitle+"></i></a>"; 
		}

		String action=error.concat(download).concat(edit).concat(delete);	
		return action;

	}


	/********************************** Icons for custom **********************************/ 

	public String customState(String fileName,String txnId ,String status,String userStatus,String displayName) {

		// URL link 
		// call post construct
		executePostConstruct();
		String emptyURL="JavaScript:void(0);"; 
		//String downloadURL = "./dowloadFiles/actual/"+fileName.replace(" ", "%20")+"/"+txnId+"/"+defaultTagName+"";
		String downloadURL = "fileDownload('"+fileName.replace(" ", "%20")+"','actual','"+txnId+"','"+defaultTagName+"')";
		String viewAction="viewConsignmentDetails('"+txnId+"')"; 
		String approveAction = "openApprovePopUp('" + txnId + "','"+displayName.replaceAll( " ", "+20")+ "')";

		/* String escapedString = queryParser.escape(approveAction); */
		String rejectAction = "openDisapprovePopup('"+txnId+"','"+displayName.replaceAll( " ", "+20")+"')";


		// state related Code 
		String download="<a onclick="+downloadURL+" ><i class="
				+downloadIcon+" aria-hidden=\"true\"  title="
				+downloadIconTitle+" ></i></a>"; 

		String view="<a onclick="+viewAction+"><i class="+viewIcon+" aria-hidden=\"true\" title="
				+viewIconTitle+" ></i></a>";

		String approve = "<a onclick="+approveAction+"><i class="+approveIcon+" aria-hidden=\"true\" title="
				+approveIconTitle+" ></i></a>";   


		String reject = "<a onclick="+rejectAction+"><i class="+rejectIcon+" aria-hidden=\"true\" title="
				+rejectIconTitle+" ></i></a>";

		if("6".equals(status) || "7".equals(status) || "9".equals(status) && "Approved".equals(userStatus) ) {
			approve = "<a onclick="+approveAction+" class="+disableIconClass+"><i class="+disableApproveIcon+" aria-hidden=\"true\" title="
					+approveIconTitle+" ></i></a>";
			reject = "<a onclick="+rejectAction+" class="+disableIconClass+"><i class="+disableRejectIcon+" aria-hidden=\"true\" title="
					+rejectIconTitle+" ></i></a>";


		}

		else if("Disable".equals(userStatus)) {
			log.info("CURRENT USER CANN'T ACCESS BCOZ STATUS IS::::::"+userStatus);
			approve = "<a onclick="+approveAction+" class=\"eventNone\"><i class="+disableApproveIcon+" aria-hidden=\"true\" title="
					+approveIconTitle+" ></i></a>";
			reject = "<a onclick="+rejectAction+" class=\"eventNone\"><i class="+disableRejectIcon+" aria-hidden=\"true\" title="
					+rejectIconTitle+" ></i></a>";
			download="<a onclick="+downloadURL+"  class="+disableIconClass+"><i class="
					+downloadIcon+" aria-hidden=\"true\"  title="
					+downloadIconTitle+" ></i></a>"; 

		}


		String action=download.concat(view).concat(approve).concat(reject);	
		return action;
	}

	/********************************** Icons for Admin Consignment **********************************/ 

	public String adminState(String fileName,String txnId ,String status,String userStatus,String companyName) {
		executePostConstruct();
		// URL link 
		String errorURL = "consignmentFileDownload('"+fileName.replace(" ", "%20")+"','error','"+txnId+"','"+defaultTagName+"')";
		String downloadURL = "consignmentFileDownload('"+fileName.replace(" ", "%20")+"','actual','"+txnId+"','"+defaultTagName+"')";
		String viewAction="viewConsignmentDetails('"+txnId+"')";
		if(companyName == null) {
			companyName= " ";
		}
		String approveAction = "openApprovePopUp('" + txnId+ "','"+companyName.replaceAll( " ", "+20")+ "')";
		//String approveAction = "openApprovePopUp('"+txnId+"')";
		String rejectAction = "openDisapprovePopup('"+txnId+"','"+companyName.replaceAll( " ", "+20")+"')";

		String deleteAction ="DeleteConsignmentRecord('"+txnId+"')";


		log.info("<><><><>status<><><><><>"+status+"<><><><><>userStatus<><><><><>"+userStatus);


		// state related Code 
		
		String error="<a onclick="+errorURL+"><i class="+errorIcon+" aria-hidden=\"true\" title="
				+errorIconTitle+"></i></a>";
		
		String download="<a onclick="+downloadURL+" ><i class="+downloadIcon+" aria-hidden=\"true\" title="
				+downloadIconTitle+" ></i></a>"; 
		
		String view="<a onclick="+viewAction+"><i class="+viewIcon+" aria-hidden=\"true\" title="
				+viewIconTitle+" ></i></a>";

		String approve = "<a onclick="+approveAction+"><i class="+approveIcon+" aria-hidden=\"true\" title="
				+approveIconTitle+" ></i></a>";   


		String reject = "<a onclick="+rejectAction+"><i class="+rejectIcon+" aria-hidden=\"true\" title="
				+rejectIconTitle+" ></i></a>";

		String delete="<a onclick="+deleteAction+" class=\"waves-effect waves-light modal-trigger\"><i class="+deletionIcon+" aria-hidden=\"true\"  title="
				+deleteIconTitle+"></i></a>";
		
		

		//Disable View
		if(("3".equals(status))  && "Approved".equals(userStatus) ) {
			error="<a onclick="+errorURL+" class=\"eventNone\"><i class="+disableErrorIcon+" aria-hidden=\"true\" title="
					+errorIconTitle+" ></i></a>";
		}
		else if(("4".equals(status))  && "Approved".equals(userStatus)) {
			reject = "<a onclick="+rejectAction+" class=\"eventNone\"><i class="+disableRejectIcon+" aria-hidden=\"true\" title="
					+rejectIconTitle+" ></i></a>";
		}else if(("6".equals(status))   && "Approved".equals(userStatus)) {
			approve = "<a onclick="+approveAction+" class=\"eventNone\"><i class="+disableApproveIcon+" aria-hidden=\"true\" title="
					+approveIconTitle+" ></i></a>";
			error="<a onclick="+errorURL+" class=\"eventNone\"><i class="+disableErrorIcon+" aria-hidden=\"true\" title="
					+errorIconTitle+" ></i></a>";
		}
		else if(("5".equals(status) ||  "8".equals(status) || "9".equals(status)) && "Approved".equals(userStatus)) {
			error="<a onclick="+errorURL+" class=\"eventNone\"><i class="+disableErrorIcon+" aria-hidden=\"true\" title="
					+errorIconTitle+" ></i></a>";
			approve = "<a onclick="+approveAction+" class=\"eventNone\"><i class="+disableApproveIcon+" aria-hidden=\"true\" title="
					+approveIconTitle+" ></i></a>";
			reject = "<a onclick="+rejectAction+" class=\"eventNone\"><i class="+disableRejectIcon+" aria-hidden=\"true\" title="
					+rejectIconTitle+" ></i></a>";
			delete="<a onclick="+deleteAction+" class=\"eventNone\"><i class="
					+disableDeletionIcon+" aria-hidden=\"true\" title="
					+deleteIconTitle+"></i></a>"; 
		}else if(("7".equals(status))   && "Approved".equals(userStatus)) {
			approve = "<a onclick="+approveAction+" class=\"eventNone\"><i class="+disableApproveIcon+" aria-hidden=\"true\" title="
					+approveIconTitle+" ></i></a>";
			reject = "<a onclick="+rejectAction+" class=\"eventNone\"><i class="+disableRejectIcon+" aria-hidden=\"true\" title="
					+rejectIconTitle+" ></i></a>";
			delete="<a onclick="+deleteAction+" class=\"eventNone\"><i class="
					+disableDeletionIcon+" aria-hidden=\"true\" title="
					+deleteIconTitle+"></i></a>"; 
		}


		else if("Disable".equals(userStatus)) {
			log.info("CURRENT USER CANN'T ACCESS BCOZ STATUS IS::::::"+userStatus);
			error="<a onclick="+errorURL+" class=\"eventNone\"><i class="+disableErrorIcon+" aria-hidden=\"true\" title="
					+errorIconTitle+" ></i></a>";
			download="<a onclick="+downloadURL+"  class=\"eventNone\"><i class="+disableDownloadIcon+" aria-hidden=\"true\"  title="
					+downloadIconTitle+" ></i></a>"; 
			approve = "<a onclick="+approveAction+" class=\"eventNone\"><i class="+disableApproveIcon+" aria-hidden=\"true\" title="
					+approveIconTitle+" ></i></a>";
			reject = "<a onclick="+rejectAction+" class=\"eventNone\"><i class="+disableRejectIcon+" aria-hidden=\"true\" title="
					+rejectIconTitle+" ></i></a>";
			delete="<a onclick="+deleteAction+" class=\"eventNone\"><i class="
					+disableDeletionIcon+" aria-hidden=\"true\" title="
					+deleteIconTitle+"></i></a>"; 
		}


		String action=error.concat(download).concat(view).concat(approve).concat(reject).concat(delete);	
		return action;
	}

	/********************************** Icons for Grievance **********************************/ 

	public String grievanceState(String fileName,String txnId ,String grievanceId,String status,String userStatus,int userId) {
		executePostConstruct();
		String replyAction = "grievanceReply('"+userId+"','"+grievanceId+"','"+txnId+"')";
		String viewAction = "viewGrievanceHistory('"+grievanceId+"','"+projectPath+"')";

		// state related Code 
		String reply = "<a onclick="+replyAction+"><i class="+replyIcon+" aria-hidden=\"true\" title="
				+replyIconTitle+" ></i></a>";
		String view="<a onclick="+viewAction+"><i class="+viewIcon+" aria-hidden=\"true\" title="
				+viewIconTitle+" ></i></a>";

		log.info("status is--->" +status+"---userStatus---->"+userStatus);
		//Disable reply
		if(("0".equals(status) || "1".equals(status) || "3".equals(status)) && "Approved".equals(userStatus)) {
			reply = "<a onclick="+replyAction+" class=\"eventNone\"><i class="+disableReplyIcon+" aria-hidden=\"true\" title="
					+replyIconTitle+" ></i></a>";

		}else if(("0".equals(status)) && "Approved".equals(userStatus)) {
			view="<a onclick="+viewAction+" class=\"eventNone\"><i class="+disableViewIcon+" aria-hidden=\"true\" title="
					+viewIconTitle+" ></i></a>";
		}
		else if("Disable".equals(userStatus)) {
			log.info("CURRENT USER CANN'T ACCESS BCOZ STATUS IS::::::"+userStatus);
			reply = "<a onclick="+replyAction+" class=\"eventNone\"><i class="+disableReplyIcon+" aria-hidden=\"true\" title="
					+replyIconTitle+" ></i></a>";
			

		}


		String action=reply.concat(view);
		return action;
	}



	/********************************** Icons for Custom Grievance **********************************/ 

	public String customGrievanceState(String fileName,String txnId ,String grievanceId,String status,String userStatus,int userId) {
		executePostConstruct();
		String replyAction = "grievanceReply('"+userId+"','"+grievanceId+"','"+txnId+"')";
		String viewAction = "viewGrievanceHistory('"+grievanceId+"')";

		// state related Code 
		String reply = "<a onclick="+replyAction+"><i class="+replyIcon+" aria-hidden=\"true\" title="
				+replyIconTitle+" ></i></a>";
		String view="<a onclick="+viewAction+"><i class="+viewIcon+" aria-hidden=\"true\" title="
				+viewIconTitle+" ></i></a>";


		log.info("status is--->" +status+"---userStatus---->"+userStatus);
		//Disable reply
				if(("0".equals(status) || "1".equals(status) || "2".equals(status) ||"3".equals(status)) && "Approved".equals(userStatus)) {
					reply = "<a onclick="+replyAction+" class=\"eventNone\"><i class="+disableReplyIcon+" aria-hidden=\"true\" title="
							+replyIconTitle+" ></i></a>";

				}else if(("0".equals(status)) && "Approved".equals(userStatus)) {
					view="<a onclick="+viewAction+" class=\"eventNone\"><i class="+disableViewIcon+" aria-hidden=\"true\" title="
							+viewIconTitle+" ></i></a>";
				}
				else if("Disable".equals(userStatus)) {
					log.info("CURRENT USER CANN'T ACCESS BCOZ STATUS IS::::::"+userStatus);
					reply = "<a onclick="+replyAction+" class=\"eventNone\"><i class="+disableReplyIcon+" aria-hidden=\"true\" title="
							+replyIconTitle+" ></i></a>";
				}

		String action=reply.concat(view);
		return action;
	}


	/********************************* Icons for Admin Grievance *********************************/ 

	public String adminGrievanceState(String fileName,String txnId ,String grievanceId,String status,String userStatus,int userId) {
		executePostConstruct();
		String replyAction = "grievanceReply('"+userId+"','"+grievanceId+"','"+txnId+"')";
		String viewAction = "viewGrievanceHistory('"+grievanceId+"')";

		// state related Code 
		String reply = "<a onclick="+replyAction+"><i class="+replyIcon+" aria-hidden=\"true\" title="
				+replyIconTitle+" ></i></a>";
		String view="<a onclick="+viewAction+"><i class="+viewIcon+" aria-hidden=\"true\" title="
				+viewIconTitle+" ></i></a>";

		log.info("status is--->" +status+"---userStatus---->"+userStatus);

		//Disable reply
		if(("2".equals(status) || "3".equals(status)) && "Approved".equals(userStatus))  {
			reply = "<a onclick="+replyAction+" class=\"eventNone\"><i class="+disableReplyIcon+" aria-hidden=\"true\" title="
					+replyIconTitle+" ></i></a>";

		}

		else if("Disable".equals(userStatus)) {
			log.info("CURRENT USER CANN'T ACCESS BCOZ STATUS IS::::::"+userStatus);
			reply = "<a onclick="+replyAction+" class=\"eventNone\"><i class="+disableReplyIcon+" aria-hidden=\"true\" title="
					+replyIconTitle+" ></i></a>";
		}


		String action=reply.concat(view);
		return action;
	}






	/********************************* Icons for Admin *********************************/ 

	public String adminStockState(String fileName,String txnId ,String status,String userStatus) {
		executePostConstruct();
		// URL link 
		String emptyURL="JavaScript:void(0);"; 
		String errorURL = "fileDownload('"+fileName.replace(" ", "%20")+"','error','"+txnId+"','"+defaultTagName+"')";
		//String errorURL = "./Consignment/dowloadFiles/error/"+fileName.replace(" ", "%20")+"/"+txnId+"/"+defaultTagName+"";
		//String downloadURL = "./Consignment/dowloadFiles/actual/"+fileName.replace(" ", "%20")+"/"+txnId+"/"+defaultTagName+"";
		String downloadURL = "fileDownload('"+fileName.replace(" ", "%20")+"','actual','"+txnId+"','"+defaultTagName+"')";
		String viewAction="viewUploadedStockDetails('"+txnId+"')";  
		String approveAction = "ApproveStock('"+txnId+"')";
		String rejectAction = "disApproveStock('"+txnId+"')";
		String deleteAction ="DeleteStockRecord('"+txnId+"')";
		// state related Code 

		String download="<a onclick="+downloadURL+" ><i class="
				+downloadIcon+" aria-hidden=\"true\" title="
				+downloadIconTitle+" ></i></a>"; 

		String error="<a onclick="+errorURL+"><i class="+errorIcon+" aria-hidden=\"true\" title="
				+errorIconTitle+"></i></a>";

		String view="<a onclick="+viewAction+"><i class="+viewIcon+" aria-hidden=\"true\" title="
				+viewIconTitle+" ></i></a>";	


		String approve = "<a onclick="+approveAction+"><i class="+approveIcon+" aria-hidden=\"true\" title="
				+approveIconTitle+" ></i></a>"; 


		String reject = "<a onclick="+rejectAction+"><i class="+rejectIcon+" aria-hidden=\"true\" title="
				+rejectIconTitle+" ></i></a>";




		String delete="<a onclick="+deleteAction+" class=\"waves-effect waves-light modal-trigger\"><i class="
				+deletionIcon+" aria-hidden=\"true\" title="
				+deleteIconTitle+"></i></a>"; 



		if(("1".equals(status)) && "Approved".equals(userStatus)) {
			error="<a onclick="+errorURL+" class="+disableIconClass+"><i  class="
					+disableErrorIcon+" aria-hidden=\"true\" title="
					+errorIconTitle+"  ></i></a>"; 
			delete="<a onclick="+deleteAction+" class=\"waves-effect waves-light modal-trigger eventNone\"><i class="
					+disableDeletionIcon+" aria-hidden=\"true\" title="
					+deleteIconTitle+"></i></a>";
			approve = "<a onclick="+approveAction+" class=\"eventNone\"><i class="+disableApproveIcon+" aria-hidden=\"true\" title="
					+approveIconTitle+" ></i></a>";
			reject = "<a onclick="+rejectAction+" class=\"eventNone\"><i class="+disableRejectIcon+" aria-hidden=\"true\" title="
					+rejectIconTitle+" ></i></a>";
		}

		if("7".equals(status)) {
			delete="<a onclick="+deleteAction+" class=\"waves-effect waves-light modal-trigger eventNone\"><i class="
					+disableDeletionIcon+" aria-hidden=\"true\" title="
					+deleteIconTitle+"></i></a>";
			reject = "<a onclick="+rejectAction+" class=\"eventNone\"><i class="+disableRejectIcon+" aria-hidden=\"true\" title="
					+rejectIconTitle+" ></i></a>";
			download="<a onclick="+downloadURL+"  class="+disableIconClass+"><i class="
					+disableDownloadIcon+" aria-hidden=\"true\" title="
					+downloadIconTitle+" ></i></a>"; 

		}

		else if(("5".equals(status)  || "8".equals(status) || "9".equals(status)) && "Approved".equals(userStatus)) {
				delete="<a onclick="+deleteAction+" class=\"waves-effect waves-light modal-trigger eventNone\"><i class="
					+disableDeletionIcon+" aria-hidden=\"true\" title="
					+deleteIconTitle+"></i></a>";
		}
		
		else if (("6".equals(status)) && "Approved".equals(userStatus)) {
			error="<a onclick="+errorURL+" class="+disableIconClass+"><i  class="
					+disableErrorIcon+" aria-hidden=\"true\" title="
					+errorIconTitle+"  ></i></a>"; 
			approve = "<a onclick="+approveAction+" class=\"eventNone\"><i class="+disableApproveIcon+" aria-hidden=\"true\" title="
					+approveIconTitle+" ></i></a>";
			
		}else if (("3".equals(status)) && "Approved".equals(userStatus)) {
			error="<a onclick="+errorURL+" class="+disableIconClass+"><i  class="
					+disableErrorIcon+" aria-hidden=\"true\" title="
					+errorIconTitle+"  ></i></a>"; 
		}
		
		else if("Disable".equals(userStatus)) {
			log.info("CURRENT USER CANN'T ACCESS BCOZ STATUS IS::::::"+userStatus);
			error="<a onclick="+errorURL+" class="+disableIconClass+"><i  class="
					+disableErrorIcon+" aria-hidden=\"true\" title="
					+errorIconTitle+"  ></i></a>"; 
			delete="<a onclick="+deleteAction+" class=\"waves-effect waves-light modal-trigger eventNone\"><i class="
					+disableDeletionIcon+" aria-hidden=\"true\" title="
					+deleteIconTitle+"></i></a>";
			approve = "<a onclick="+approveAction+" class=\"eventNone\"><i class="+disableApproveIcon+" aria-hidden=\"true\" title="
					+approveIconTitle+" ></i></a>";
			reject = "<a onclick="+rejectAction+" class=\"eventNone\"><i class="+disableRejectIcon+" aria-hidden=\"true\" title="
					+rejectIconTitle+" ></i></a>";
			download="<a onclick="+downloadURL+"  class="+disableIconClass+"><i class="
					+disableDownloadIcon+" aria-hidden=\"true\" title="
					+downloadIconTitle+" ></i></a>"; 

		}

		String action=download.concat(error).concat(view).concat(approve).concat(reject).concat(delete);	
		return action;

	}




	/********************************** Icons for AdminRegistrationRequest **********************************/ 

	public String adminRegistrationRequest(String userId ,String userStatus,String AdminCurrentStatus,String createdOn,String roles, String type,String id,String username,String status) {
		executePostConstruct();
		// URL link 
		String emptyURL="JavaScript:void(0);"; 
		String approveAction = "userApprovalPopup("+userId+",'"+createdOn.replace(" ", "=")+"','"+username+"')";

		String viewAction="trcInformation?id="+id+"&roles="+roles.replace(" ", "=")+"&type="+type;
		String rejectAction = "userRejectPopup("+userId+")";
		String editAction="userChangeStatus('"+userId+"')";
		

		log.info("status---->"+status+"---------AdminCurrentStatus------>"+AdminCurrentStatus+" userStatus----------->" +userStatus);

		// state related Code 


		String view="<a href="+viewAction+"><i class="+viewIcon+" aria-hidden=\"true\" title="
				+viewIconTitle+" ></i></a>";
		
		String edit="<a onclick="+editAction+"><i class="+editIcon+" aria-hidden=\"true\"  title="
				+editIconTitle+"></i></a>"; 

		String approve = "<a onclick="+approveAction+"><i class="+approveIcon+" aria-hidden=\"true\" title="
				+approveIconTitle+" ></i></a>";   


		String reject = "<a onclick="+rejectAction+"><i class="+rejectIcon+" aria-hidden=\"true\" title="
				+rejectIconTitle+" ></i></a>";

		
		if (("0".equals(status) || "1".equals(status)) && "Approved".equals(userStatus)) {
			approve = "<a onclick="+approveAction+" class=\"eventNone\"><i class="+disableApproveIcon+" aria-hidden=\"true\" title="
					+approveIconTitle+" ></i></a>";
			reject = "<a onclick="+rejectAction+" class=\"eventNone\"><i class="+disableRejectIcon+" aria-hidden=\"true\" title="
					+rejectIconTitle+" ></i></a>";
		}else if(("3".equals(status)) && "Approved".equals(userStatus)) {
			approve = "<a onclick="+approveAction+" class=\"eventNone\"><i class="+disableApproveIcon+" aria-hidden=\"true\" title="
					+approveIconTitle+" ></i></a>";
		}else if(("4".equals(status)) && "Approved".equals(userStatus)) {
			reject = "<a onclick="+rejectAction+" class=\"eventNone\"><i class="+disableRejectIcon+" aria-hidden=\"true\" title="
					+rejectIconTitle+" ></i></a>";
		}else if(("5".equals(status) || "6".equals(status)) && "Approved".equals(userStatus)) {
			approve = "<a onclick="+approveAction+" class=\"eventNone\"><i class="+disableApproveIcon+" aria-hidden=\"true\" title="
					+approveIconTitle+" ></i></a>";
			reject = "<a onclick="+rejectAction+" class=\"eventNone\"><i class="+disableRejectIcon+" aria-hidden=\"true\" title="
					+rejectIconTitle+" ></i></a>";
			
		}
		else if("Disable".equals(userStatus)) {
			log.info("CURRENT USER CANN'T ACCESS BCOZ STATUS IS::::::"+userStatus);

			approve = "<a onclick="+approveAction+" class=\"eventNone\"><i class="+disableApproveIcon+" aria-hidden=\"true\" title="
					+approveIconTitle+" ></i></a>";
			reject = "<a onclick="+rejectAction+" class=\"eventNone\"><i class="+disableRejectIcon+" aria-hidden=\"true\" title="
					+rejectIconTitle+" ></i></a>";
			edit="<a onclick="+editAction+" class="+disableIconClass+"><i class="
					+disableEditIcon+" aria-hidden=\"true\"  title="
					+editIconTitle+"></i></a>"; 

		}
		String action=view.concat(edit).concat(approve).concat(reject);		  
		return action;

	}



	/********************************* Icons for DashBoard Notification *********************************/


	public String dashboardIcon(String userStatus,Integer featureID,String txnID,Integer userID,String roleType) {
		executePostConstruct();
		// URL link
		String viewAction = featureID == 3 ?"./Consignment/viewConsignment?txnID="+txnID+"" :
			featureID == 4 ? "./assignDistributor?txnID="+txnID+"&userTypeId="+roleType+"":
				featureID == 0 ? "./stolenRecovery?txnID="+txnID+"" :
					featureID == 6 ? "./grievanceManagement?txnID="+txnID+"" :
						featureID == 7 ? "./stolenRecovery?txnID="+txnID+"" :
							featureID == 8 ? "./registrationRequest?txnID="+txnID+"" :
								featureID == 11 ? "./manageTypeDevices?txnID="+txnID+"":
									featureID == 12 ? "./uploadPaidStatus?via=other&txnID="+txnID+"" :
										"JavaScript:void(0);";
		//System.out.println("featureID::::::::::"+featureID);
		// state related Code
		String view=null;
		if(featureID == 3 || featureID == 4 || featureID == 6 || featureID == 7) {
			view="<a href="+viewAction+"><i class="+viewIcon+" aria-hidden=\"true\" title="
					+viewIconTitle+" ></i></a>";
		}
		else {

			view="<a href="+viewAction+" class="+disableIconClass+"><i class="+disableViewIcon+" aria-hidden=\"true\" title="
					+viewIconTitle+" ></i></a>";
		}
		String action=view;
		return action;

	}

	/********************************** Icons for TRC Manage Type Datatable **********************************/ 


	public String trcManageIcons(String status,Integer id,String fileName,String txnId,String userStatus) {	
		// URL link 
		//String downloadURL = "JavaScript:void(0)";
		//String downloadURL = "./Consignment/dowloadFiles/actual/"+fileName.replace(" ", "%20")+"/"+txnId+"/"+defaultTagName+"";
		String downloadURL = "fileDownload('"+fileName.replace(" ", "%20")+"','actual','"+txnId+"','"+defaultTagName+"')";
		String viewAction="viewByID("+id+",'view','"+projectPath+"')";
		String editAction= "viewByID("+id+",'edit')";
		// state related Code 
		String download="<a onclick="+downloadURL+" ><i class="
				+downloadIcon+" aria-hidden=\"true\" title="
				+downloadIconTitle+" ></i></a>"; 

		String view="<a onclick="+viewAction+"><i class="+viewIcon+" aria-hidden=\"true\" title="
				+viewIconTitle+" ></i></a>";

		String edit="<a onclick="+editAction+"><i class="
				+editIcon+" aria-hidden=\"true\"  title="
				+editIconTitle+"></i></a>"; 
		
		if(("0".equals(status)) && "Approved".equals(userStatus)) {
			edit="<a onclick="+editAction+" class="+disableIconClass+"><i class="
					+disableEditIcon+" aria-hidden=\"true\"  title="
					+editIconTitle+"></i></a>"; 
		}else if("Disable".equals(userStatus)) {
			log.info("CURRENT USER CANN'T ACCESS BCOZ STATUS IS::::::"+userStatus);
			edit="<a onclick="+editAction+" class="+disableIconClass+"><i class="
					+disableEditIcon+" aria-hidden=\"true\"  title="
					+editIconTitle+"></i></a>"; 
			download="<a onclick="+downloadURL+"  class="+disableIconClass+"><i class="
					+disableDownloadIcon+" aria-hidden=\"true\"  title="
					+downloadIconTitle+" ></i></a>"; 
		
		}
		

		String action=download.concat(view).concat(edit);		  
		return action;

	}


	/********************************** Icons for UPS **********************************/ 	
	public String userPaidStatusIcon(Long imei1) {
		executePostConstruct();
		String payTaxAction ="taxPaid('"+imei1+"')";
		String viewAction="viewDetails('"+imei1+"')";
		String deleteAction= "deleteByImei('"+imei1+"')";

		String taxPaid="<a onclick="+payTaxAction+"><i class="
				+payTaxIcon+" aria-hidden=\"true\" title="
				+payTaxIconTitle+"></i></a>";
		String view="<a onclick="+viewAction+"><i class="+viewIcon+" aria-hidden=\"true\" title="
				+viewIconTitle+" ></i></a>";

		String delete="<a onclick="+deleteAction+"><i class="
				+deletionIcon+" aria-hidden=\"true\" title="
				+deleteIconTitle+"></i></a>";

		String action = taxPaid.concat(view).concat(delete);
		return action;
	}


	/********************************** Icons for AdminUPS **********************************/ 	
	public String adminUserPaidStatusIcon(Long imei1,String createdOn,String txnId) {
		executePostConstruct();
		String viewAction="viewDetails('"+imei1+"')";

		String approveAction ="deviceApprovalPopup("+imei1+",'"+createdOn.replace(" ", "=")+"','"+txnId+"')";
		String rejectAction= "userRejectPopup('"+imei1+"','"+txnId+"')";


		String view="<a onclick="+viewAction+"><i class="+viewIcon+" aria-hidden=\"true\" title="
				+viewIconTitle+" ></i></a>";
		String approve = "<a onclick="+approveAction+"><i class="+approveIcon+" aria-hidden=\"true\" title="
				+approveIconTitle+" ></i></a>";   
		String reject = "<a onclick="+rejectAction+"><i class="+rejectIcon+" aria-hidden=\"true\" title="
				+rejectIconTitle+" ></i></a>";


		String action = view.concat(approve).concat(reject);
		return action;
	}



	/********************************** Icons for Operator **********************************/ 
	public String greyBlackIcon(String userStatus,String fileName) {
		executePostConstruct();
		String downloadURL = "./dowloadFiles/"+fileName.replace(" ", "%20")+"/";

		String download="<a onclick="+downloadURL+" ><i class="
				+downloadIcon+" aria-hidden=\"true\" title="
				+downloadIconTitle+" ></i></a>"; 


		if("Disable".equals(userStatus)) {
			log.info("CURRENT USER CANN'T ACCESS BCOZ STATUS IS::::::"+userStatus);

			download="<a onclick="+downloadURL+"  class="+disableIconClass+"><i class="
					+disableDownloadIcon+" aria-hidden=\"true\" title="
					+downloadIconTitle+" ></i></a>"; 
		}
		String action=download;
		return action;

	}





	/********************************* Icons for Stolen *********************************/

	public String blockUnblockState(String fileName,String txnId ,String status,String userStatus, String requestType,int id,Integer qty,String source) {
		executePostConstruct();
		// URL link
		String file = fileName == null ? null : fileName.replace(" ", "%20");

		String viewAction="";
		String editAction="";
		String emptyURL="JavaScript:void(0);";
		String errorURL = "fileDownload('"+file+"','error','"+txnId+"','"+defaultTagName+"')";
		//String errorURL = "./Consignment/dowloadFiles/error/"+file+"/"+txnId+"/"+defaultTagName+"";
		//String downloadURL = "./Consignment/dowloadFiles/actual/"+file+"/"+txnId+"/"+defaultTagName+"";
		String downloadURL = "fileDownload('"+file+"','actual','"+txnId+"','"+defaultTagName+"')";
		String deleteAction ="DeleteConsignmentRecord('"+txnId+"','"+id+"')";


		if(source.equals("3") && status.equals("0")) {
			editAction="viewDeviceDetails('"+txnId+"','edit','"+requestType+"')";
			viewAction="viewDeviceDetails('"+txnId+"','view','"+requestType+"')";
		}else if(source.equals("4") && status.equals("0")) {
			editAction="viewblockImeiDevice('"+txnId+"','edit','"+requestType+"')";
			viewAction="viewblockImeiDevice('"+txnId+"','view','"+requestType+"')";
		}

		// state related Code
		String error="<a onclick="+errorURL+"><i class="+errorIcon+" aria-hidden=\"true\" title="
				+errorIconTitle+" ></i></a>";
		String download="<a onclick="+downloadURL+" ><i class="
				+downloadIcon+" aria-hidden=\"true\" title="
				+downloadIconTitle+" ></i></a>";
		String edit="<a onclick="+editAction+"><i class="+editIcon+" aria-hidden=\"true\" title="
				+editIconTitle+"></i></a>";
		String view="<a onclick="+viewAction+"><i class="+viewIcon+" aria-hidden=\"true\" title="
				+viewIconTitle+" ></i></a>";
		String delete="<a onclick="+deleteAction+" class=\"waves-effect waves-light modal-trigger\"><i class="+deletionIcon+" aria-hidden=\"true\" title="
				+deleteIconTitle+"></i></a>";


       
		if(source.equals("3") && status.equals("0")) {
			error="<a onclick="+errorURL+" class="+disableIconClass+"><i class="
					+disableErrorIcon+" aria-hidden=\"true\" title="
					+errorIconTitle+" ></i></a>";

		}
		else if(source.equals("4") && status.equals("0")) {
			download="<a onclick="+downloadURL+"  class="+disableIconClass+"><i class="
					+disableDownloadIcon+" aria-hidden=\"true\" title="
					+downloadIconTitle+" ></i></a>";
			error="<a onclick="+errorURL+" class="+disableIconClass+"><i class="
					+disableErrorIcon+" aria-hidden=\"true\" title="
					+errorIconTitle+" ></i></a>";
		}else if(status.equals("0") || status.equals("3")) {
			download="<a onclick="+downloadURL+"  class="+disableIconClass+"><i class="
					+disableDownloadIcon+" aria-hidden=\"true\" title="
					+downloadIconTitle+" ></i></a>";
		}






		/*
		 * for (ActionModel actionModel : actionResponse) {
		 *
		 *
		 * switch(actionModel.getState()) { case 0: error=disableHandling(actionModel,
		 * errorURL); log.info("------------------ case 0"); break; case 1:
		 * error=disableHandling(actionModel, errorURL);
		 * log.info("------------------ case 1"); break; case 2:
		 * error=disableHandling(actionModel, errorURL);
		 * log.info("------------------ case 2"); break; case 3:
		 * error=enableHandling(actionModel,errorURL);
		 * log.info("------------------ case 3"); break; case 4:
		 * error=disableHandling(actionModel, errorURL);
		 * log.info("------------------ case 4"); break; case 5:
		 * error=disableHandling(actionModel, errorURL);
		 * log.info("------------------ case 5"); break; } }
		 */


		String action=error.concat(download).concat(view).concat(edit).concat(delete);
		return action;

	}





	/********************************** Icons for Admin MEssage Management**********************************/ 

	public String adminMessageIcons(String userStatus, String tag) { 
		executePostConstruct();
		String editAction="updateDetails('"+tag+"')";
		String viewAction="viewDetails('"+tag+"')";


		// state related Code 
		String edit="<a onclick="+editAction+"><i class="+editIcon+" aria-hidden=\"true\"  title="
				+editIconTitle+"></i></a>"; 
		String view="<a onclick="+viewAction+"><i class="+viewIcon+" aria-hidden=\"true\" title="
				+viewIconTitle+" ></i></a>";

		if("Disable".equals(userStatus)) {
			log.info("CURRENT USER CANN'T ACCESS BCOZ STATUS IS::::::"+userStatus);

			edit="<a onclick="+editAction+" class="+disableIconClass+"><i class="
					+disableEditIcon+" aria-hidden=\"true\"  title="
					+editIconTitle+"></i></a>"; 

		}

		String action=view.concat(edit);	
		return action;

	}


	/********************************** Icons for AdminConfig Management**********************************/ 

	public String adminConfigIcons(String userStatus, String tag) { 
		executePostConstruct();
		String editAction="updateDetails('"+tag+"')";
		String viewAction="viewDetails('"+tag+"')";


		// state related Code 
		String edit="<a onclick="+editAction+"><i class="+editIcon+" aria-hidden=\"true\"  title="
				+editIconTitle+"></i></a>"; 
		String view="<a onclick="+viewAction+"><i class="+viewIcon+" aria-hidden=\"true\" title="
				+viewIconTitle+" ></i></a>";

		if("Disable".equals(userStatus)) {
			log.info("CURRENT USER CANN'T ACCESS BCOZ STATUS IS::::::"+userStatus);

			edit="<a onclick="+editAction+" class="+disableIconClass+"><i class="
					+disableEditIcon+" aria-hidden=\"true\"  title="
					+editIconTitle+"></i></a>"; 

		}

		String action=view.concat(edit);	
		return action;

	}







	/********************************** Icons for Policy Config Management**********************************/ 

	public String policyConfigIcons(String userStatus, String tag) { 
		executePostConstruct();
		String editAction="updateDetails('"+tag+"')";
		String viewAction="viewDetails('"+tag+"')";

		// state related Code 
		String edit="<a onclick="+editAction+"><i class="+editIcon+" aria-hidden=\"true\"  title="
				+editIconTitle+"></i></a>"; 
		String view="<a onclick="+viewAction+"><i class="+viewIcon+" aria-hidden=\"true\" title="
				+viewIconTitle+" ></i></a>";

		if("Disable".equals(userStatus)) {
			log.info("CURRENT USER CANN'T ACCESS BCOZ STATUS IS::::::"+userStatus);

			edit="<a onclick="+editAction+" class="+disableIconClass+"><i class="
					+disableEditIcon+" aria-hidden=\"true\"  title="
					+editIconTitle+"></i></a>"; 

		}

		String action=view.concat(edit);	
		return action;

	}


	/********************************** Icons for Admin TRC Manage Type Datatable **********************************/ 


	public String trcAdminManageIcons(String status,Integer id,String fileName,String txnId, String adminApproveStatus,String userStatus) {
		executePostConstruct();
		String viewAction="ImporterviewByID("+id+",'view','"+projectPath+"')";
	//	String downloadURL = "./dowloadFiles/actual/"+fileName.replace(" ", "%20")+"/"+txnId+"/"+defaultTagName+"";
		String downloadURL = "fileDownload('"+fileName.replace(" ", "%20")+"','actual','"+txnId+"','"+defaultTagName+"')";
		String approveAction = "openApproveTACPopUp('"+txnId+"','')";
		String rejectAction= "openDisapproveTACPopUp('"+txnId+"','')";


		String view="<a onclick="+viewAction+"><i class="+viewIcon+" aria-hidden=\"true\" title="
				+viewIconTitle+" ></i></a>";

		String download="<a onclick="+downloadURL+" ><i class="
				+downloadIcon+" aria-hidden=\"true\" title="
				+downloadIconTitle+" ></i></a>";
		String approve = "<a onclick="+approveAction+"><i class="+approveIcon+" aria-hidden=\"true\" title="
				+approveIconTitle+" ></i></a>";   
		String reject = "<a onclick="+rejectAction+"><i class="+rejectIcon+" aria-hidden=\"true\" title="
				+rejectIconTitle+" ></i></a>";

		if("6".equals(status)) {
			approve = "<a onclick=" + approveAction + " class=\"eventNone\"><i class=" + disableApproveIcon
					+ " aria-hidden=\"true\" title=" + approveIconTitle + " ></i></a>";
		}else if("7".equals(status)) {
			reject = "<a onclick=" + rejectAction + " class=\"eventNone\"><i class=" + disableRejectIcon
					+ " aria-hidden=\"true\" title=" + rejectIconTitle + " ></i></a>";
			download = "<a href=" + downloadURL + " ><i class=" + downloadIcon
					+ " aria-hidden=\"true\" title=" + downloadIconTitle + " ></i></a>";
		}else if(("0".equals(status) || "1".equals(status) || "2".equals(status) || "4".equals(status) || "8".equals(status)) && "Approved".equals(userStatus)) {
			approve = "<a onclick=" + approveAction + " class=\"eventNone\"><i class=" + disableApproveIcon
					+ " aria-hidden=\"true\" title=" + approveIconTitle + " ></i></a>";
			reject = "<a onclick=" + rejectAction + " class=\"eventNone\"><i class=" + disableRejectIcon
					+ " aria-hidden=\"true\" title=" + rejectIconTitle + " ></i></a>";
		}


		String action = view.concat(download).concat(approve).concat(reject);
		return action;

	}

	/********************************** Icons for adminBlockUnblock **********************************/ 

	public String adminBlockUnblock(String fileName, String txnId, String status,
			String userStatus, String requestType, int id, Integer qty, String source) {
		String file = fileName == null ? null : fileName.replace(" ", "%20");
		String viewAction = "";
		//String downloadURL = "./Consignment/dowloadFiles/actual/" + file + "/" + txnId +"/"+defaultTagName+ "";
		String downloadURL = "fileDownload('"+file+"','actual','"+txnId+"','"+defaultTagName+"')";
		String approveAction = "deviceApprovalPopup('" + txnId + "','" + requestType + "')";
		String rejectAction = "userRejectPopup('" + txnId + "','" + requestType + "')";
	//	String errorURL = "./Consignment/dowloadFiles/error/"+file+"/"+txnId+"/"+defaultTagName+"";
		String errorURL = "fileDownload('"+file+"','error','"+txnId+"','"+defaultTagName+"')";
		String editAction="";
		String deleteAction ="DeleteConsignmentRecord('"+txnId+"','"+id+"')";

		
		log.info("============actionResponse=======" + status);
		if(source.equals("3")) {
			editAction="viewDeviceDetails('"+txnId+"','edit','"+requestType+"')";
			viewAction="viewDeviceDetails('"+txnId+"','view','"+requestType+"')";

		}
		else if(source.equals("4")) {
			editAction="viewblockImeiDevice('"+txnId+"','edit','"+requestType+"')";
			viewAction="viewblockImeiDevice('"+txnId+"','view','"+requestType+"')";
		}
		
		// state related Code
		String view="<a onclick="+viewAction+"><i class="
				+viewIcon+" aria-hidden=\"true\" title=" +viewIconTitle+" ></i></a>";
		String edit="<a onclick="+editAction+"><i class="+editIcon+" aria-hidden=\"true\" title="
				+editIconTitle+"></i></a>";
		String download="<a onclick="+downloadURL+" ><i class="
				+downloadIcon+" aria-hidden=\"true\" title="
				+downloadIconTitle+" ></i></a>";
		String approve ="<a onclick="+approveAction+"><i class="
						+approveIcon+" aria-hidden=\"true\" title=" +approveIconTitle+" ></i></a>";
		String reject = "<a onclick="+rejectAction+"><i class="
						+rejectIcon+" aria-hidden=\"true\" title=" +rejectIconTitle+" ></i></a>";

		String delete="<a onclick="+deleteAction+" class=\"waves-effect waves-light modal-trigger\"><i class="+deletionIcon+" aria-hidden=\"true\" title="
				+deleteIconTitle+"></i></a>";
		
		String error="<a onclick="+errorURL+"><i class="+errorIcon+" aria-hidden=\"true\" title="
				+errorIconTitle+" ></i></a>";
		
		log.info("source type--->" +source);
		
				if(("0".equals(status) || "1".equals(status)) && "Approved".equals(userStatus)){
					approve = "<a onclick="+approveAction+" class=\"eventNone\"><i class="+disableApproveIcon+" aria-hidden=\"true\" title="
							+approveIconTitle+" ></i></a>";
					reject = "<a onclick="+rejectAction+" class=\"eventNone\"><i class="+disableRejectIcon+" aria-hidden=\"true\" title="
							+rejectIconTitle+" ></i></a>";
					delete="<a onclick="+deleteAction+" class=\"eventNone\"><i class="+disableDeletionIcon+" aria-hidden=\"true\" title="
							+deleteIconTitle+"></i></a>"; 
					error="<a onclick="+errorURL+" class=\"eventNone\"><i class="+disableErrorIcon+" aria-hidden=\"true\" title="
							+errorIconTitle+" ></i></a>";
					
				}else if(("1".equals(status)) && "Approved".equals(userStatus)) {
					approve = "<a onclick="+approveAction+" class=\"eventNone\"><i class="+disableApproveIcon+" aria-hidden=\"true\" title="
							+approveIconTitle+" ></i></a>";
					reject = "<a onclick="+rejectAction+" class=\"eventNone\"><i class="+disableRejectIcon+" aria-hidden=\"true\" title="
							+rejectIconTitle+" ></i></a>";
					delete="<a onclick="+deleteAction+" class=\"eventNone\"><i class="+disableDeletionIcon+" aria-hidden=\"true\" title="
							+deleteIconTitle+"></i></a>"; 
					edit="<a onclick="+editAction+" class=\"eventNone\"><i class="+disableEditIcon+" aria-hidden=\"true\" title="
							+editIconTitle+"></i></a>"; 
					error="<a onclick="+errorURL+" class=\"eventNone\"><i class="+disableErrorIcon+" aria-hidden=\"true\" title="
							+errorIconTitle+" ></i></a>";
					
					
				}if("2".equals(status)) {
					error="<a onclick="+errorURL+" class=\"eventNone\"><i class="+disableErrorIcon+" aria-hidden=\"true\" title="
							+errorIconTitle+" ></i></a>";
					
				} if("3".equals(status)) {
					download="<a onclick="+downloadURL+"  class=\"eventNone\"><i class="+disableDownloadIcon+" aria-hidden=\"true\" title="
							+downloadIconTitle+" ></i></a>";
					approve = "<a onclick="+approveAction+" class=\"eventNone\"><i class="+disableApproveIcon+" aria-hidden=\"true\" title="
							+approveIconTitle+" ></i></a>";
					
					reject = "<a onclick="+rejectAction+" class=\"eventNone\"><i class="+disableRejectIcon+" aria-hidden=\"true\" title="
							+rejectIconTitle+" ></i></a>";
					delete="<a onclick="+deleteAction+" class=\"eventNone\"><i class="+disableDeletionIcon+" aria-hidden=\"true\" title="
							+deleteIconTitle+"></i></a>"; 
				
					
				}
				else if(("4".equals(status)) && "Approved".equals(userStatus)){
					reject = "<a onclick="+rejectAction+" class=\"eventNone\"><i class="+disableRejectIcon+" aria-hidden=\"true\" title="
							+rejectIconTitle+" ></i></a>";
				}
				else if(("5".equals(status)) && "Approved".equals(userStatus)){
					edit="<a onclick="+editAction+" class=\"eventNone\"><i class="+disableEditIcon+" aria-hidden=\"true\" title="
							+editIconTitle+"></i></a>"; 
					approve = "<a onclick="+approveAction+" class=\"eventNone\"><i class="+disableApproveIcon+" aria-hidden=\"true\" title="
							+approveIconTitle+" ></i></a>";
					error="<a onclick="+errorURL+" class=\"eventNone\"><i class="+disableErrorIcon+" aria-hidden=\"true\" title="
							+errorIconTitle+" ></i></a>";
				}else if(source.equals("4")) {
					download="<a onclick="+downloadURL+"  class=\"eventNone\"><i class="+disableDownloadIcon+" aria-hidden=\"true\" title="
							+downloadIconTitle+" ></i></a>";
				} 
				
		

				if ("Disable".equals(userStatus)) {
					log.info("CURRENT USER CANN'T ACCESS BCOZ STATUS IS::::::" + userStatus);
					approve = "<a onclick="+approveAction+" class=\"eventNone\"><i class="+disableApproveIcon+" aria-hidden=\"true\" title="
							+approveIconTitle+" ></i></a>";
					reject = "<a onclick="+rejectAction+" class=\"eventNone\"><i class="+disableRejectIcon+" aria-hidden=\"true\" title="
							+rejectIconTitle+" ></i></a>";
					delete="<a onclick="+deleteAction+" class=\"eventNone\"><i class="+disableDeletionIcon+" aria-hidden=\"true\" title="
							+deleteIconTitle+"></i></a>"; 
					edit="<a onclick="+editAction+" class=\"eventNone\"><i class="+disableEditIcon+" aria-hidden=\"true\" title="
							+editIconTitle+"></i></a>";
					download="<a onclick="+downloadURL+"  class="+disableIconClass+"><i class="
							+disableDownloadIcon+" aria-hidden=\"true\" title="
							+downloadIconTitle+" ></i></a>";
					error="<a onclick="+errorURL+" class=\"eventNone\"><i class="+disableErrorIcon+" aria-hidden=\"true\" title="
							+errorIconTitle+" ></i></a>";
					
				}


				String action = download.concat(error).concat(view).concat(approve).concat(reject).concat(edit).concat(delete);
				return action;
	}	



	/********************************** Icons for StolenlawfulAgency **********************************/ 

	public String StolenlawfulAgency(String fileName,String txnId ,String status,String userStatus, String requestType,int id,Integer qty,String source, String requestTypeValue) {
		executePostConstruct();
		// URL link 
		String file = fileName == null ? null : fileName.replace(" ", "%20");

		log.info("@@@@  "+source+"&&"+requestTypeValue);

		String viewAction="";
		String editAction="";
		String emptyURL="JavaScript:void(0);"; 
	//	String errorURL = "./Consignment/dowloadFiles/error/"+file+"/"+txnId+"/"+defaultTagName+"";	
		String errorURL = "fileDownload('"+file+"','error','"+txnId+"','"+defaultTagName+"')";
		//String downloadURL = "./Consignment/dowloadFiles/actual/"+file+"/"+txnId+"/"+defaultTagName+"";
		String downloadURL = "fileDownload('"+file+"','actual','"+txnId+"','"+defaultTagName+"')";
		String deleteAction ="DeleteConsignmentRecord('"+txnId+"','"+id+"')";

		if(source.equals("5") && requestTypeValue.equals("0")) {
			//check for Stolen/Indvisual
		
			editAction="openStolenRecoveryPage('editIndivisualsStolen','edit','"+txnId+"')";
			viewAction="openStolenRecoveryPage('editIndivisualsStolen','view','"+txnId+"')";

		}
		else if(source.equals("6") && requestTypeValue.equals("0")) {
			//check for Stolen/Company
			
			editAction="openStolenRecoveryPage('editCompanyStolen','edit','"+txnId+"')";
			viewAction="openStolenRecoveryPage('editCompanyStolen','view','"+txnId+"')";

		}
		else if(source.equals("4") && requestTypeValue.equals("1")) {
			//check for Recovery/single
			
			editAction="openStolenRecoveryPage('editIndivisualRecovery','edit','"+txnId+"')";
			viewAction="openStolenRecoveryPage('editIndivisualRecovery','view','"+txnId+"')";

		}
		else if(source.equals("6") && requestTypeValue.equals("1")) {
			//check for Recovery/company
			
			editAction="openStolenRecoveryPage('editCompanyRecovery','edit','"+txnId+"')";
			viewAction="openStolenRecoveryPage('editCompanyRecovery','view','"+txnId+"')";

		}
		/*
		 * else {
		 * 
		 * log.info("else condition..");
		 * editAction="openStolenRecoveryPage('editCompanyRecovery','edit')";
		 * viewAction="openStolenRecoveryPage('editCompanyRecovery','view')";
		 * 
		 * }
		 */


		// state related Code 
		String error="<a onclick="+errorURL+"><i class="+errorIcon+" aria-hidden=\"true\" title="
				+errorIconTitle+" ></i></a>";
		String download="<a onclick="+downloadURL+" ><i class="
				+downloadIcon+" aria-hidden=\"true\" title="
				+downloadIconTitle+" ></i></a>"; 
		String edit="<a onclick="+editAction+"><i class="+editIcon+" aria-hidden=\"true\"  title="
				+editIconTitle+"></i></a>"; 
		String view="<a onclick="+viewAction+"><i class="+viewIcon+" aria-hidden=\"true\" title="
				+viewIconTitle+" ></i></a>";
		String delete="<a onclick="+deleteAction+" class=\"waves-effect waves-light modal-trigger\"><i class="
				+deletionIcon+" aria-hidden=\"true\"  title="
				+deleteIconTitle+"></i></a>"; 
		
		
		if(("0".equals(status)) && "Approved".equals(userStatus)) {
			error="<a onclick="+errorURL+" class="+disableIconClass+"><i  class="+disableErrorIcon+" aria-hidden=\"true\" title="
					+errorIconTitle+"  ></i></a>"; 
		}else if(("1".equals(status) || "2".equals(status)) && "Approved".equals(userStatus)) {
			error="<a onclick="+errorURL+" class="+disableIconClass+"><i class="+disableErrorIcon+" aria-hidden=\"true\" title="
					+errorIconTitle+" ></i></a>";
			edit="<a onclick="+editAction+" class="+disableIconClass+"><i class="
					+disableEditIcon+" aria-hidden=\"true\"  title="
					+editIconTitle+"></i></a>"; 
			delete="<a onclick="+deleteAction+" class=\"waves-effect waves-light modal-trigger eventNone\" ><i class="
					+disableDeletionIcon+" aria-hidden=\"true\"  title="
					+deleteIconTitle+"></i></a>"; 
			
		}
		


		if("Disable".equals(userStatus)) {
			log.info("CURRENT USER CANN'T ACCESS BCOZ STATUS IS::::::"+userStatus);
			error="<a onclick="+errorURL+" class="+disableIconClass+"><i class="+disableErrorIcon+" aria-hidden=\"true\" title="
					+errorIconTitle+" ></i></a>";
			download="<a onclick="+downloadURL+"  class="+disableIconClass+"><i class="
					+disableDownloadIcon+" aria-hidden=\"true\"  title="
					+downloadIconTitle+" ></i></a>"; 
			edit="<a onclick="+editAction+" class="+disableIconClass+"><i class="
					+disableEditIcon+" aria-hidden=\"true\"  title="
					+editIconTitle+"></i></a>"; 
			delete="<a onclick="+deleteAction+" class=\"waves-effect waves-light modal-trigger eventNone\" ><i class="
					+disableDeletionIcon+" aria-hidden=\"true\"  title="
					+deleteIconTitle+"></i></a>"; 

		}

		String action=error.concat(download).concat(view).concat(edit).concat(delete);	
		return action;

	}

	/********************************** Icons for Audit Management**********************************/ 

	public String auditManagementIcons(String userStatus,String userId,String id) { 
		executePostConstruct();
		String viewAction="viewDetails('"+id+"')";

		// state related Code 
		String view="<a onclick="+viewAction+"><i class="+viewIcon+" aria-hidden=\"true\" title="
				+viewIconTitle+" ></i></a>";


		String action=view;
		return action;

	}
	/********************************** Icons for AdminUPS **********************************/ 	
	public String manageUserIcon(Long imei1,String createdOn,String txnId) {
		String viewAction="viewDetails('"+imei1+"')";

		String editAction="";


		String view="<a onclick="+viewAction+"><i class="+viewIcon+" aria-hidden=\"true\" title="
				+viewIconTitle+" ></i></a>";
		String edit="<a onclick="+editAction+"><i class="+editIcon+" aria-hidden=\"true\"  title="
				+editIconTitle+"></i></a>"; 


		String action = view.concat(edit);
		return action;
	}
	public String endUserGrievanceState(String fileName,String txnId ,String grievanceId,Integer userId,String StatusofGrievance) {
		executePostConstruct();
		log.info(" entry in set view in data table.....");
		String replyAction = "endUserGrievanceReply('"+userId+"','"+grievanceId+"','"+txnId+"')";
		String viewAction = "endUserviewGrievanceHistory('"+grievanceId+"','"+projectPath+"','"+userId+"')";

		// state related Code 
		String reply = "<a onclick="+replyAction+"><i class="+replyIcon+" aria-hidden=\"true\" title="
				+replyIconTitle+" ></i></a>";
		String view="<a onclick="+viewAction+"><i class="+viewIcon+" aria-hidden=\"true\" title="
				+viewIconTitle+" ></i></a>";

		log.info("set StatusofGrievance....." +StatusofGrievance);

		//Disable reply
		if( "0".equals(StatusofGrievance) || "3".equals(StatusofGrievance)) {
			reply = "<a onclick="+replyAction+" class=\"eventNone\"><i class="+disableReplyIcon+" aria-hidden=\"true\" title="
					+replyIconTitle+" ></i></a>";

		}
		/*
		 * //Disable reply if( "0".equals(status) || "1".equals(status) ||
		 * "3".equals(status)) { reply =
		 * "<a onclick="+replyAction+" class=\"eventNone\"><i class="
		 * +disableReplyIcon+" aria-hidden=\"true\" title="
		 * +replyIconTitle+" ></i></a>";
		 * 
		 * }else if("0".equals(status)) {
		 * view="<a onclick="+viewAction+" class=\"eventNone\"><i class="
		 * +disableViewIcon+" aria-hidden=\"true\" title=" +viewIconTitle+" ></i></a>";
		 * } else if("Disable".equals(userStatus)) {
		 * log.info("CURRENT USER CANN'T ACCESS BCOZ STATUS IS::::::"+userStatus); reply
		 * = "<a onclick="+replyAction+"><i class="
		 * +disableReplyIcon+" aria-hidden=\"true\" title="
		 * +replyIconTitle+" ></i></a>"; view="<a onclick="+viewAction+"><i class="
		 * +disableViewIcon+" aria-hidden=\"true\" title=" +viewIconTitle+" ></i></a>";
		 * 
		 * }
		 */

		String action=reply.concat(view);
		return action;
	}


	/********************************** Icons for Importer TRC Datatable **********************************/ 


	public String importalTrcManageIcons(String status,Integer id,String fileName,String txnId,String userStatus) {	
		// URL link 
		//String downloadURL = "JavaScript:void(0)";

		String viewAction="ImporterviewByID("+id+",'view','"+projectPath+"')";
		String editAction= "ImporterviewByID("+id+",'edit')";
		String deleteAction = "DeleteTacRecord('"+txnId+"',"+id+")";
		// state related Code 


		String view="<a onclick="+viewAction+"><i class="+viewIcon+" aria-hidden=\"true\" title="
				+viewIconTitle+" ></i></a>";

		String edit="<a onclick="+editAction+"><i class="
				+editIcon+" aria-hidden=\"true\"  title="
				+editIconTitle+"></i></a>"; 
		String delete="<a onclick="+deleteAction+" class=\"waves-effect waves-light modal-trigger\"><i class="
				+deletionIcon+" aria-hidden=\"true\"  title="
				+deleteIconTitle+"></i></a>";
		if(("7".equals(status) || "8".equals(status)) && "Approved".equals(userStatus)) {
			delete="<a onclick="+deleteAction+" class=\"waves-effect waves-light modal-trigger eventNone\"><i class="
					+disableDeletionIcon+" aria-hidden=\"true\"  title="
					+deleteIconTitle+"></i></a>";	
		}else if(("1".equals(status) || "3".equals(status) || "4".equals(status) || "6".equals(status)) && "Approved".equals(userStatus)) {
			edit="<a onclick="+editAction+" class="+disableIconClass+"><i class="
					+disableEditIcon+" aria-hidden=\"true\"  title="
					+editIconTitle+"></i></a>"; 
			delete="<a onclick="+deleteAction+" class=\"waves-effect waves-light modal-trigger eventNone\"><i class="
					+disableDeletionIcon+" aria-hidden=\"true\"  title="
					+deleteIconTitle+"></i></a>";
		}else if("Disable".equals(userStatus)) {
			log.info("CURRENT USER CANN'T ACCESS BCOZ STATUS IS::::::"+userStatus);
			edit="<a onclick="+editAction+" class="+disableIconClass+"><i class="
					+disableEditIcon+" aria-hidden=\"true\"  title="
					+editIconTitle+"></i></a>"; 
			delete="<a onclick="+deleteAction+" class=\"waves-effect waves-light modal-trigger eventNone\" ><i class="
					+disableDeletionIcon+" aria-hidden=\"true\"  title="
					+deleteIconTitle+"></i></a>"; 
		}

		String action=view.concat(edit).concat(delete);		  
		return action;

	}

	/********************************** Icons for Device Activation **********************************/ 	

	public String deviceActivationIcon(Long imei1,String createdOn,String txnId) {
		executePostConstruct();
		String viewAction="viewDetails('"+imei1+"')";
		String editAction="";


		String view="<a onclick="+viewAction+"><i class="+viewIcon+" aria-hidden=\"true\" title="
				+viewIconTitle+" ></i></a>";
		String edit="<a onclick="+editAction+"><i class="+editIcon+" aria-hidden=\"true\"  title="
				+editIconTitle+"></i></a>"; 


		String action = view.concat(edit);
		return action;
	}

	/********************************** Icons for Manage Users**********************************/ 

	public String manageUserIcons(String id, String passportNo) { 
		executePostConstruct();
		String viewAction="viewDetails('"+id+"')";
		String editAction="";
		String ListAction = "deviceActivation?passportNo="+passportNo+"";
		String AddAction = "";
		// state related Code 
		String view="<a onclick="+viewAction+"><i class="+viewIcon+" aria-hidden=\"true\" title="
				+viewIconTitle+" ></i></a>";
		String edit="<a onclick="+editAction+"><i class="+editIcon+" aria-hidden=\"true\"  title="
				+editIconTitle+"></i></a>"; 
		String List = "<a href="+ListAction+"><i class="+ListIcon+" aria-hidden=\"true\"  title="
				+ListIconTittle+"></i></a>"; 
		String Add = "<a onclick="+AddAction+"><i class="+plusIcon+" aria-hidden=\"true\"  title="
				+plusIconTittle+"></i></a>"; 

		String action = view.concat(edit).concat(List).concat(Add);
		return action;

	}





	/********************************** Icons for Importer Admin TRC Manage Type Datatable **********************************/ 


	public String trcAdminImporterManageIcons(String status,Integer id,String fileName,String txnId) {	
		executePostConstruct();
		String viewAction="viewByID("+id+",'view')";
		//String downloadURL = "./Consignment/dowloadFiles/actual/"+fileName.replace(" ", "%20")+"/"+txnId+"/"+defaultTagName+"";
		String downloadURL = "fileDownload('"+fileName.replace(" ", "%20")+"','actual','"+txnId+"','"+defaultTagName+"')";
		String approveAction = "openApproveTACPopUp('"+txnId+"','')";
		String rejectAction= "openDisapproveTACPopUp('"+txnId+"','')";


		String view="<a onclick="+viewAction+"><i class="+viewIcon+" aria-hidden=\"true\" title="
				+viewIconTitle+" ></i></a>";

		String download="<a onclick="+downloadURL+" ><i class="
				+downloadIcon+" aria-hidden=\"true\" title="
				+downloadIconTitle+" ></i></a>";
		String approve = "<a onclick="+approveAction+"><i class="+approveIcon+" aria-hidden=\"true\" title="
				+approveIconTitle+" ></i></a>";   
		String reject = "<a onclick="+rejectAction+"><i class="+rejectIcon+" aria-hidden=\"true\" title="
				+rejectIconTitle+" ></i></a>";





		String action = view.concat(download).concat(approve).concat(reject);
		return action;

	}



	/********************************** Icons for Admin TRC Manage Type Admin Datatable **********************************/ 


	public String trcAdminIcons(String status,Integer id,String fileName,String txnId, String adminApproveStatus) {	
		executePostConstruct();
		String viewAction="viewByID("+id+",'view','"+projectPath+"')";
	//	String downloadURL = "./dowloadFiles/actual/"+fileName.replace(" ", "%20")+"/"+txnId+"/"+defaultTagName+"";
		String downloadURL = "fileDownload('"+fileName.replace(" ", "%20")+"','actual','"+txnId+"','"+defaultTagName+"')";
		String approveAction = "openApproveTACPopUp('"+txnId+"','')";
		String rejectAction= "openDisapproveTACPopUp('"+txnId+"','')";


		String view="<a onclick="+viewAction+"><i class="+viewIcon+" aria-hidden=\"true\" title="
				+viewIconTitle+" ></i></a>";

		String download="<a onclick="+downloadURL+" ><i class="
				+downloadIcon+" aria-hidden=\"true\" title="
				+downloadIconTitle+" ></i></a>";
		String approve = "<a onclick="+approveAction+"><i class="+approveIcon+" aria-hidden=\"true\" title="
				+approveIconTitle+" ></i></a>";   
		String reject = "<a onclick="+rejectAction+"><i class="+rejectIcon+" aria-hidden=\"true\" title="
				+rejectIconTitle+" ></i></a>";


		if("0".equals(adminApproveStatus)) {
			approve = "<a onclick=" + approveAction + " class=\"eventNone\"><i class=" + disableApproveIcon
					+ " aria-hidden=\"true\" title=" + approveIconTitle + " ></i></a>";
		}else if("1".equals(adminApproveStatus)) {
			reject = "<a onclick=" + rejectAction + " class=\"eventNone\"><i class=" + disableRejectIcon
					+ " aria-hidden=\"true\" title=" + rejectIconTitle + " ></i></a>";
			download = "<a href=" + downloadURL + " ><i class=" + downloadIcon
					+ " aria-hidden=\"true\" title=" + downloadIconTitle + " ></i></a>";
		}

		String action = view.concat(download).concat(approve).concat(reject);
		return action;

	}

	
	/********************************** Icons for Field Management**********************************/ 

	public String fieldManagementIcons(String id,String tag,String interp,String tagId) { 
		executePostConstruct();
		
		String editAction= "FieldViewByID('"+tag+"','"+id+"')";
		String deleteAction = "DeleteFieldRecord('"+id+"')";
		// state related Code 


		String edit="<a onclick="+editAction+"><i class="
				+editIcon+" aria-hidden=\"true\"  title="
				+editIconTitle+"></i></a>"; 
		String delete="<a onclick="+deleteAction+" class=\"waves-effect waves-light modal-trigger\"><i class="
				+deletionIcon+" aria-hidden=\"true\"  title="
				+deleteIconTitle+"></i></a>";		

		String action=edit.concat(delete);
		return action;

	}
	
	/********************************** Icons for Port Management**********************************/ 

	public String portManagementIcons(String id) { 
		executePostConstruct();
		
		String editAction= "PortViewByID('"+id+"')";
		String deleteAction = "DeletePortRecord('"+id+"')";
		// state related Code 


		String edit="<a onclick="+editAction+"><i class="
				+editIcon+" aria-hidden=\"true\"  title="
				+editIconTitle+"></i></a>"; 
		String delete="<a onclick="+deleteAction+" class=\"waves-effect waves-light modal-trigger\"><i class="
				+deletionIcon+" aria-hidden=\"true\"  title="
				+deleteIconTitle+"></i></a>";		

		String action=edit.concat(delete);
		return action;

	}
	
	/********************************** Icons for currency Management**********************************/ 

	public String currencyManagementIcons(String id, String userStatus) { 
		executePostConstruct();
		
		String editAction= "currencyViewByID('"+id+"')";
		
		// state related Code 


		String edit="<a onclick="+editAction+"><i class="
				+editIcon+" aria-hidden=\"true\"  title="
				+editIconTitle+"></i></a>"; 
			

		String action=edit;
		return action;

	}
	
	
	/********************************** Icons for AdminStolen **********************************/ 

	public String AdminStolenlawfulAgency(String fileName,String txnId ,String status,String userStatus, String requestType,int id,Integer qty,String source, String requestTypeValue) {
		executePostConstruct();
		// URL link 
		String file = fileName == null ? null : fileName.replace(" ", "%20");

		log.info("source  "+source+"  requestTypeValue-->"+requestTypeValue+"  requestType-->"+requestType);

		String viewAction="";
		String editAction="";
		String emptyURL="JavaScript:void(0);"; 
		String approveAction = "deviceApprovalPopup('" + txnId + "','"+requestTypeValue+"')";
		String rejectAction = "userRejectPopup('" + txnId + "','" + requestTypeValue + "')";
	//	String errorURL = "./Consignment/dowloadFiles/error/"+file+"/"+txnId+"/"+defaultTagName+"";	
		String errorURL = "fileDownload('"+file+"','error','"+txnId+"','"+defaultTagName+"')";
		//String downloadURL = "./Consignment/dowloadFiles/actual/"+file+"/"+txnId+"/"+defaultTagName+"";
		String downloadURL = "fileDownload('"+file+"','actual','"+txnId+"','"+defaultTagName+"')";
		String deleteAction ="DeleteConsignmentRecord('"+txnId+"','"+id+"')";

		if(source.equals("5") && requestTypeValue.equals("0")) {
			//check for Stolen/Indvisual
		
			editAction="openStolenRecoveryPage('editIndivisualsStolen','edit','"+txnId+"')";
			viewAction="openStolenRecoveryPage('editIndivisualsStolen','view','"+txnId+"')";

		}
		else if(source.equals("6") && requestTypeValue.equals("0")) {
			//check for Stolen/Company
			
			editAction="openStolenRecoveryPage('editCompanyStolen','edit','"+txnId+"')";
			viewAction="openStolenRecoveryPage('editCompanyStolen','view','"+txnId+"')";

		}
		else if(source.equals("4") && requestTypeValue.equals("1")) {
			//check for Recovery/single
			
			editAction="openStolenRecoveryPage('editIndivisualRecovery','edit','"+txnId+"')";
			viewAction="openStolenRecoveryPage('editIndivisualRecovery','view','"+txnId+"')";

		}
		else if(source.equals("6") && requestTypeValue.equals("1")) {
			//check for Recovery/company
			
			editAction="openStolenRecoveryPage('editCompanyRecovery','edit','"+txnId+"')";
			viewAction="openStolenRecoveryPage('editCompanyRecovery','view','"+txnId+"')";

		}
		/*
		 * else {
		 * 
		 * log.info("else condition..");
		 * editAction="openStolenRecoveryPage('editCompanyRecovery','edit')";
		 * viewAction="openStolenRecoveryPage('editCompanyRecovery','view')";
		 * 
		 * }
		 */


		// state related Code 
		String error="<a onclick="+errorURL+"><i class="+errorIcon+" aria-hidden=\"true\" title="
				+errorIconTitle+" ></i></a>";
		String download="<a onclick="+downloadURL+" ><i class="
				+downloadIcon+" aria-hidden=\"true\" title="
				+downloadIconTitle+" ></i></a>"; 
		String edit="<a onclick="+editAction+"><i class="+editIcon+" aria-hidden=\"true\"  title="
				+editIconTitle+"></i></a>"; 
		String view="<a onclick="+viewAction+"><i class="+viewIcon+" aria-hidden=\"true\" title="
				+viewIconTitle+" ></i></a>";
		String delete="<a onclick="+deleteAction+" class=\"waves-effect waves-light modal-trigger\"><i class="
				+deletionIcon+" aria-hidden=\"true\"  title="
				+deleteIconTitle+"></i></a>"; 
		String approve ="<a onclick="+approveAction+"><i class="
				+approveIcon+" aria-hidden=\"true\" title=" +approveIconTitle+" ></i></a>";
		String reject = "<a onclick="+rejectAction+"><i class="
				+rejectIcon+" aria-hidden=\"true\" title=" +rejectIconTitle+" ></i></a>";


		if(("0".equals(status) || "1".equals(status)) && "Approved".equals(userStatus)){
			approve = "<a onclick="+approveAction+" class=\"eventNone\"><i class="+disableApproveIcon+" aria-hidden=\"true\" title="
					+approveIconTitle+" ></i></a>";
			reject = "<a onclick="+rejectAction+" class=\"eventNone\"><i class="+disableRejectIcon+" aria-hidden=\"true\" title="
					+rejectIconTitle+" ></i></a>";
			delete="<a onclick="+deleteAction+" class=\"eventNone\"><i class="+disableDeletionIcon+" aria-hidden=\"true\" title="
					+deleteIconTitle+"></i></a>"; 
			error="<a onclick="+errorURL+" class=\"eventNone\"><i class="+disableErrorIcon+" aria-hidden=\"true\" title="
					+errorIconTitle+" ></i></a>";
			
		}else if(("1".equals(status)) && "Approved".equals(userStatus)) {
			approve = "<a onclick="+approveAction+" class=\"eventNone\"><i class="+disableApproveIcon+" aria-hidden=\"true\" title="
					+approveIconTitle+" ></i></a>";
			reject = "<a onclick="+rejectAction+" class=\"eventNone\"><i class="+disableRejectIcon+" aria-hidden=\"true\" title="
					+rejectIconTitle+" ></i></a>";
			delete="<a onclick="+deleteAction+" class=\"eventNone\"><i class="+disableDeletionIcon+" aria-hidden=\"true\" title="
					+deleteIconTitle+"></i></a>"; 
			edit="<a onclick="+editAction+" class=\"eventNone\"><i class="+disableEditIcon+" aria-hidden=\"true\" title="
					+editIconTitle+"></i></a>"; 
			error="<a onclick="+errorURL+" class=\"eventNone\"><i class="+disableErrorIcon+" aria-hidden=\"true\" title="
					+errorIconTitle+" ></i></a>";
			
			
		}if("2".equals(status)) {
			error="<a onclick="+errorURL+" class=\"eventNone\"><i class="+disableErrorIcon+" aria-hidden=\"true\" title="
					+errorIconTitle+" ></i></a>";
			
		} if("3".equals(status)) {
			download="<a onclick="+downloadURL+"  class=\"eventNone\"><i class="+disableDownloadIcon+" aria-hidden=\"true\" title="
					+downloadIconTitle+" ></i></a>";
			approve = "<a onclick="+approveAction+" class=\"eventNone\"><i class="+disableApproveIcon+" aria-hidden=\"true\" title="
					+approveIconTitle+" ></i></a>";
			
			reject = "<a onclick="+rejectAction+" class=\"eventNone\"><i class="+disableRejectIcon+" aria-hidden=\"true\" title="
					+rejectIconTitle+" ></i></a>";
			delete="<a onclick="+deleteAction+" class=\"eventNone\"><i class="+disableDeletionIcon+" aria-hidden=\"true\" title="
					+deleteIconTitle+"></i></a>"; 
		
			
		}
		else if(("4".equals(status)) && "Approved".equals(userStatus)){
			reject = "<a onclick="+rejectAction+" class=\"eventNone\"><i class="+disableRejectIcon+" aria-hidden=\"true\" title="
					+rejectIconTitle+" ></i></a>";
		}
		else if(("5".equals(status)) && "Approved".equals(userStatus)){
			edit="<a onclick="+editAction+" class=\"eventNone\"><i class="+disableEditIcon+" aria-hidden=\"true\" title="
					+editIconTitle+"></i></a>"; 
			approve = "<a onclick="+approveAction+" class=\"eventNone\"><i class="+disableApproveIcon+" aria-hidden=\"true\" title="
					+approveIconTitle+" ></i></a>";
			error="<a onclick="+errorURL+" class=\"eventNone\"><i class="+disableErrorIcon+" aria-hidden=\"true\" title="
					+errorIconTitle+" ></i></a>";
		}else if(source.equals("4")) {
			download="<a onclick="+downloadURL+"  class=\"eventNone\"><i class="+disableDownloadIcon+" aria-hidden=\"true\" title="
					+downloadIconTitle+" ></i></a>";
		} 
		


		if ("Disable".equals(userStatus)) {
			log.info("CURRENT USER CANN'T ACCESS BCOZ STATUS IS::::::" + userStatus);
			approve = "<a onclick="+approveAction+" class=\"eventNone\"><i class="+disableApproveIcon+" aria-hidden=\"true\" title="
					+approveIconTitle+" ></i></a>";
			reject = "<a onclick="+rejectAction+" class=\"eventNone\"><i class="+disableRejectIcon+" aria-hidden=\"true\" title="
					+rejectIconTitle+" ></i></a>";
			delete="<a onclick="+deleteAction+" class=\"eventNone\"><i class="+disableDeletionIcon+" aria-hidden=\"true\" title="
					+deleteIconTitle+"></i></a>"; 
			edit="<a onclick="+editAction+" class=\"eventNone\"><i class="+disableEditIcon+" aria-hidden=\"true\" title="
					+editIconTitle+"></i></a>";
			download="<a onclick="+downloadURL+"  class="+disableIconClass+"><i class="
					+disableDownloadIcon+" aria-hidden=\"true\" title="
					+downloadIconTitle+" ></i></a>";
			error="<a onclick="+errorURL+" class=\"eventNone\"><i class="+disableErrorIcon+" aria-hidden=\"true\" title="
					+errorIconTitle+" ></i></a>";
			
		}

		
		
	
		
		//String action=error.concat(download).concat(view).concat(edit).concat(delete);	
		String action = download.concat(error).concat(view).concat(approve).concat(reject).concat(edit).concat(delete);
		return action;
	}
	
	@PostConstruct
	public void executePostConstruct() {
		errorIconTitle=Translator.toLocale("titles.Error_File");
		downloadIconTitle=Translator.toLocale("titles.Download");
		viewIconTitle=Translator.toLocale("titles.View"); 
		editIconTitle=Translator.toLocale("titles.Edit");
		deleteIconTitle=Translator.toLocale("titles.Delete"); 
		replyIconTitle=Translator.toLocale("titles.Reply");
		approveIconTitle=Translator.toLocale("titles.Approve");
		rejectIconTitle=Translator.toLocale("titles.Reject");
		payTaxIconTitle =Translator.toLocale("titles.Pay_Tax");
		ListIconTittle = Translator.toLocale("titles.List");
		plusIconTittle = Translator.toLocale("titles.Add_device");
	}



}
