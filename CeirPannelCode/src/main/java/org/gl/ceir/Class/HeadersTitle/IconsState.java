package org.gl.ceir.Class.HeadersTitle;

import org.gl.ceir.CeirPannelCode.Model.ActionModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.net.URLEncoder;
import java.util.List;
@Component
public class IconsState {
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
	String errorIconTitle="Error-File";
	String downloadIconTitle="Download"; 
	String viewIconTitle="View"; 
	String editIconTitle="Edit"; 
	String deleteIconTitle="Delete"; 
	String replyIconTitle="Reply";
	String approveIconTitle="Approve";
	String rejectIconTitle="Reject";
	String payTaxIconTitle ="Pay Tax";
	String ListIconTittle = "List";
	String plusIconTittle = "Add device";

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

		String downloadURL = "./dowloadFiles/actual/"+fileName.replace(" ", "%20")+"/"+txnId+"/"+defaultTagName+"";
		String errorURL = "./dowloadFiles/error/"+fileName.replace(" ", "%20")+"/"+txnId+"/"+defaultTagName+"";
		log.info("downloadURL::::::::::::::"+downloadURL);
		String viewAction="viewConsignmentDetails('"+txnId+"')"; 
		String editAction="EditConsignmentDetails('"+txnId+"')";
		String deleteAction ="DeleteConsignmentRecord('"+txnId+"')";


		// state related Code 
		String error="<a href="+errorURL+"><i class="+errorIcon+" aria-hidden=\"true\" title="
				+errorIconTitle+"></i></a>";
		String download="<a href="+downloadURL+" download=\"download\"><i class="
				+downloadIcon+" aria-hidden=\"true\" title="
				+downloadIconTitle+" download=\"download\"></i></a>"; 
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



		if(("0".equals(status) || "4".equals(status) || "7".equals(status)) && "Approved".equals(userStatus)) {
			error="<a href="+errorURL+" class="+disableIconClass+"><i  class="
					+disableErrorIcon+" aria-hidden=\"true\" title="
					+errorIconTitle+"  ></i></a>"; 
			delete="<a onclick="+deleteAction+" class=\"waves-effect waves-light modal-trigger eventNone\"><i class="
					+disableDeletionIcon+" aria-hidden=\"true\" title="
					+deleteIconTitle+"></i></a>";
		}

		else if(("1".equals(status)) && "Approved".equals(userStatus)) {
			error="<a href="+errorURL+" class="+disableIconClass+"><i  class="
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
			error="<a href="+errorURL+" class="+disableIconClass+"><i  class="
					+disableErrorIcon+" aria-hidden=\"true\" title="
					+errorIconTitle+"  ></i></a>"; 
			edit="<a onclick="+editAction+" class="+disableIconClass+"><i class="
					+disableEditIcon+" aria-hidden=\"true\" title="
					+editIconTitle+"></i></a>"; 
			delete="<a onclick="+deleteAction+" class=\"waves-effect waves-light modal-trigger eventNone\"><i class="
					+disableDeletionIcon+" aria-hidden=\"true\" title="
					+deleteIconTitle+"></i></a>";
		}
		else if("Disable".equals(userStatus)) {
			log.info("CURRENT USER CANN'T ACCESS BCOZ STATUS IS::::::"+userStatus);
			error="<a href="+errorURL+" class="+disableIconClass+"><i class="+disableErrorIcon+" aria-hidden=\"true\" title="
					+errorIconTitle+" ></i></a>";
			download="<a href="+downloadURL+" download=\"download\" class="+disableIconClass+"><i class="
					+disableDownloadIcon+" aria-hidden=\"true\"  title="
					+downloadIconTitle+" download=\"download\"></i></a>"; 
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
		String emptyURL="JavaScript:void(0);"; 
		String downloadURL = "./Consignment/dowloadFiles/actual/"+fileName.replace(" ", "%20")+"/"+txnId+"/"+defaultTagName+"";
		String errorURL = "./Consignment/dowloadFiles/error/"+fileName.replace(" ", "%20")+"/"+txnId+"/"+defaultTagName+"";	
		String viewAction="viewUploadedStockDetails('"+txnId+"')"; 
		String editAction="EditUploadedStockDetails('"+txnId+"')";
		String deleteAction ="DeleteStockRecord('"+txnId+"')";

		// state related Code 
		String error="<a href="+errorURL+"><i class="+errorIcon+" aria-hidden=\"true\" title="
				+errorIconTitle+" ></i></a>";
		String download="<a href="+downloadURL+" download=\"download\"><i class="
				+downloadIcon+" aria-hidden=\"true\"  title="
				+downloadIconTitle+" download=\"download\"></i></a>"; 
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
			error="<a href="+errorURL+" class="+disableIconClass+"><i  class="
					+disableErrorIcon+" aria-hidden=\"true\" title="
					+errorIconTitle+"  ></i></a>"; 
		}

		else if(("1".equals(status))  && "Approved".equals(userStatus)) {
			error="<a href="+errorURL+" class="+disableIconClass+"><i  class="
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
			error="<a href="+errorURL+" class="+disableIconClass+"><i  class="
					+disableErrorIcon+" aria-hidden=\"true\" title="
					+errorIconTitle+"  ></i></a>"; 
			edit="<a onclick="+editAction+" class="+disableIconClass+"><i class="
					+disableEditIcon+" aria-hidden=\"true\" title="
					+editIconTitle+"></i></a>"; 
		}

		else if("Disable".equals(userStatus)) {
			log.info("CURRENT USER CANN'T ACCESS BCOZ STATUS IS::::::"+userStatus);
			error="<a href="+errorURL+" class="+disableIconClass+"><i class="+disableErrorIcon+" aria-hidden=\"true\" title="
					+errorIconTitle+" ></i></a>";
			download="<a href="+downloadURL+" download=\"download\" class="+disableIconClass+"><i class="
					+disableDownloadIcon+" aria-hidden=\"true\"  title="
					+downloadIconTitle+" download=\"download\"></i></a>"; 
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
		// URL link 
		String emptyURL="JavaScript:void(0);"; 
		String downloadURL = "./Consignment/dowloadFiles/actual/"+fileName.replace(" ", "%20")+"/"+txnId+"/"+defaultTagName+"";
		String errorURL = "./Consignment/dowloadFiles/error/"+fileName.replace(" ", "%20")+"/"+txnId+"/"+defaultTagName+"";	
		String viewAction="viewUploadedStockDetails('"+txnId+"')"; 
		String editAction="EditUploadedStockDetails('"+txnId+"')";
		String deleteAction ="DeleteStockRecord('"+txnId+"')";

		// state related Code 
		String error="<a href="+errorURL+"><i class="+errorIcon+" aria-hidden=\"true\" title="
				+errorIconTitle+" ></i></a>";
		String download="<a href="+downloadURL+" download=\"download\"><i class="
				+downloadIcon+" aria-hidden=\"true\"  title="
				+downloadIconTitle+" download=\"download\"></i></a>"; 
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

		if("0".equals(status) || "1".equals(status) ||  "3".equals(status) || "10".equals(status)  && "Approved".equals(userStatus)) {
			error="<a href="+errorURL+" class="+disableIconClass+"><i  class="
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
			error="<a href="+errorURL+" class="+disableIconClass+"><i  class="
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
			error="<a href="+errorURL+" class="+disableIconClass+"><i class="+disableErrorIcon+" aria-hidden=\"true\" title="
					+errorIconTitle+" ></i></a>";
			download="<a href="+downloadURL+" download=\"download\" class="+disableIconClass+"><i class="
					+disableDownloadIcon+" aria-hidden=\"true\"  title="
					+downloadIconTitle+" download=\"download\"></i></a>"; 
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
		// URL link 
		String file = fileName == null ? null : fileName.replace(" ", "%20");
		String emptyURL="JavaScript:void(0);"; 
		String errorURL = "./Consignment/dowloadFiles/error/"+file+"/"+txnId+"/"+defaultTagName+"";	
		String downloadURL = "./Consignment/dowloadFiles/actual/"+file+"/"+txnId+"/"+defaultTagName+"";
		String editAction="openFileStolenUpdate('"+txnId+"','"+requestType+"','"+id+"','"+qty+"')";
		String deleteAction ="DeleteConsignmentRecord('"+txnId+"','"+id+"')";


		// state related Code 
		String error="<a href="+errorURL+"><i class="+errorIcon+" aria-hidden=\"true\" title="
				+errorIconTitle+" ></i></a>";
		String download="<a href="+downloadURL+" download=\"download\"><i class="
				+downloadIcon+" aria-hidden=\"true\" title="
				+downloadIconTitle+" download=\"download\"></i></a>"; 
		String edit="<a onclick="+editAction+"><i class="+editIcon+" aria-hidden=\"true\"  title="
				+editIconTitle+"></i></a>"; 
		String delete="<a onclick="+deleteAction+" class=\"waves-effect waves-light modal-trigger\"><i class="+deletionIcon+" aria-hidden=\"true\"  title="
				+deleteIconTitle+"></i></a>"; 


		if("0".equals(status) || "2".equals(status) && "Approved".equals(userStatus) ) {
			error="<a href="+errorURL+" class="+disableIconClass+"><i class="
					+disableErrorIcon+" aria-hidden=\"true\" title="
					+errorIconTitle+"  ></i></a>"; 
			edit="<a onclick="+editAction+" class="+disableIconClass+"><i class="
					+disableEditIcon+" aria-hidden=\"true\"  title="
					+editIconTitle+"></i></a>";
		}
		else if( "1".equals(status) && "Approved".equals(userStatus)) {
			error="<a href="+errorURL+" class="+disableIconClass+"><i class="
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
			error="<a href="+errorURL+" class="+disableIconClass+"><i class="+disableErrorIcon+" aria-hidden=\"true\" title="
					+errorIconTitle+" ></i></a>";
			download="<a href="+downloadURL+" download=\"download\" class="+disableIconClass+"><i class="
					+disableDownloadIcon+" aria-hidden=\"true\"  title="
					+downloadIconTitle+" download=\"download\"></i></a>"; 
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

		String emptyURL="JavaScript:void(0);"; 
		String downloadURL = "./dowloadFiles/actual/"+fileName.replace(" ", "%20")+"/"+txnId+"/"+defaultTagName+"";
		String viewAction="viewConsignmentDetails('"+txnId+"')"; 
		String approveAction = "openApprovePopUp('" + txnId + "','"+displayName.replaceAll( " ", "+20")+ "')";

		/* String escapedString = queryParser.escape(approveAction); */
		String rejectAction = "openDisapprovePopup('"+txnId+"','"+displayName.replaceAll( " ", "+20")+"')";


		// state related Code 
		String download="<a href="+downloadURL+" download=\"download\"><i class="
				+downloadIcon+" aria-hidden=\"true\"  title="
				+downloadIconTitle+" download=\"download\"></i></a>"; 

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
			approve = "<a onclick="+approveAction+" class="+disableIconClass+"><i class="+disableApproveIcon+" aria-hidden=\"true\" title="
					+approveIconTitle+" ></i></a>";
			reject = "<a onclick="+rejectAction+" class="+disableIconClass+"><i class="+disableRejectIcon+" aria-hidden=\"true\" title="
					+rejectIconTitle+" ></i></a>";

			download="<a href="+downloadURL+" download=\"download\" class="+disableIconClass+"><i class="
					+downloadIcon+" aria-hidden=\"true\"  title="
					+downloadIconTitle+" download=\"download\"></i></a>"; 

		}


		String action=download.concat(view).concat(approve).concat(reject);	
		return action;
	}

	/********************************** Icons for Admin Consignment **********************************/ 

	public String adminState(String fileName,String txnId ,String status,String userStatus,String companyName) {
		// URL link 
		String emptyURL="JavaScript:void(0);"; 
		String viewAction="viewConsignmentDetails('"+txnId+"')"; 
		String approveAction = "openApprovePopUp('" + txnId + "','"+companyName.replaceAll( " ", "+20")+ "')";
		//String approveAction = "openApprovePopUp('"+txnId+"')";
		String rejectAction = "openDisapprovePopup('"+txnId+"','"+companyName.replaceAll( " ", "+20")+"')";

		String deleteAction ="DeleteConsignmentRecord('"+txnId+"')";


		log.info("<><><><>status<><><><><>"+status+"<><><><><>userStatus<><><><><>"+userStatus);


		// state related Code 


		String view="<a onclick="+viewAction+"><i class="+viewIcon+" aria-hidden=\"true\" title="
				+viewIconTitle+" ></i></a>";

		String approve = "<a onclick="+approveAction+"><i class="+approveIcon+" aria-hidden=\"true\" title="
				+approveIconTitle+" ></i></a>";   


		String reject = "<a onclick="+rejectAction+"><i class="+rejectIcon+" aria-hidden=\"true\" title="
				+rejectIconTitle+" ></i></a>";

		String delete="<a onclick="+deleteAction+" class=\"waves-effect waves-light modal-trigger\"><i class="+deletionIcon+" aria-hidden=\"true\"  title="
				+deleteIconTitle+"></i></a>";

		//Disable View
		if( "9".equals(status)  && "Approved".equals(userStatus) ) {
			view="<a onclick="+viewAction+" class=\"eventNone\"><i class="+disableViewIcon+" aria-hidden=\"true\" title="
					+viewIconTitle+" ></i></a>";
			approve = "<a onclick="+approveAction+" class=\"eventNone\"><i class="+disableApproveIcon+" aria-hidden=\"true\" title="
					+approveIconTitle+" ></i></a>";
			reject = "<a onclick="+rejectAction+" class=\"eventNone\"><i class="+disableRejectIcon+" aria-hidden=\"true\" title="
					+rejectIconTitle+" ></i></a>";
			delete="<a onclick="+deleteAction+" class=\"eventNone\"><i class="
					+disableDeletionIcon+" aria-hidden=\"true\" title="
					+deleteIconTitle+"></i></a>"; 
		}
		else if("4".equals(status)  && "Approved".equals(userStatus) ) {
			approve = "<a onclick="+approveAction+" class=\"eventNone\"><i class="+disableApproveIcon+" aria-hidden=\"true\" title="
					+approveIconTitle+" ></i></a>";
			reject = "<a onclick="+rejectAction+" class=\"eventNone\"><i class="+disableRejectIcon+" aria-hidden=\"true\" title="
					+rejectIconTitle+" ></i></a>";
		}
		else if("5".equals(status) || "6".equals(status) || "7".equals(status) || "8".equals(status)  && "Approved".equals(userStatus) ) {
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
			approve = "<a onclick="+approveAction+"><i class="+disableApproveIcon+" aria-hidden=\"true\" title="
					+approveIconTitle+" ></i></a>";
			reject = "<a onclick="+rejectAction+" class=\"+disableIconClass+\"><i class="+disableRejectIcon+" aria-hidden=\"true\" title="
					+rejectIconTitle+" ></i></a>";
			delete="<a onclick="+deleteAction+" class=\"waves-effect waves-light modal-trigger eventNone\" ><i class="
					+disableDeletionIcon+" aria-hidden=\"true\"  title="
					+deleteIconTitle+"></i></a>"; 
		}


		String action=view.concat(approve).concat(reject).concat(delete);	
		return action;
	}

	/********************************** Icons for Grievance **********************************/ 

	public String grievanceState(String fileName,String txnId ,String grievanceId,String status,String userStatus,int userId) {
		String replyAction = "grievanceReply('"+userId+"','"+grievanceId+"','"+txnId+"')";
		String viewAction = "viewGrievanceHistory('"+grievanceId+"','"+projectPath+"')";

		// state related Code 
		String reply = "<a onclick="+replyAction+"><i class="+replyIcon+" aria-hidden=\"true\" title="
				+replyIconTitle+" ></i></a>";
		String view="<a onclick="+viewAction+"><i class="+viewIcon+" aria-hidden=\"true\" title="
				+viewIconTitle+" ></i></a>";



		//Disable reply
		if( "0".equals(status) || "1".equals(status) || "3".equals(status)) {
			reply = "<a onclick="+replyAction+" class=\"eventNone\"><i class="+disableReplyIcon+" aria-hidden=\"true\" title="
					+replyIconTitle+" ></i></a>";

		}else if("0".equals(status)) {
			view="<a onclick="+viewAction+" class=\"eventNone\"><i class="+disableViewIcon+" aria-hidden=\"true\" title="
					+viewIconTitle+" ></i></a>";
		}
		else if("Disable".equals(userStatus)) {
			log.info("CURRENT USER CANN'T ACCESS BCOZ STATUS IS::::::"+userStatus);
			reply = "<a onclick="+replyAction+"><i class="+disableReplyIcon+" aria-hidden=\"true\" title="
					+replyIconTitle+" ></i></a>";
			view="<a onclick="+viewAction+"><i class="+disableViewIcon+" aria-hidden=\"true\" title="
					+viewIconTitle+" ></i></a>";

		}


		String action=reply.concat(view);
		return action;
	}
	
	

	/********************************** Icons for Custom Grievance **********************************/ 

	public String customGrievanceState(String fileName,String txnId ,String grievanceId,String status,String userStatus,int userId) {
		String replyAction = "grievanceReply('"+userId+"','"+grievanceId+"','"+txnId+"')";
		String viewAction = "viewGrievanceHistory('"+grievanceId+"')";

		// state related Code 
		String reply = "<a onclick="+replyAction+"><i class="+replyIcon+" aria-hidden=\"true\" title="
				+replyIconTitle+" ></i></a>";
		String view="<a onclick="+viewAction+"><i class="+viewIcon+" aria-hidden=\"true\" title="
				+viewIconTitle+" ></i></a>";



		//Disable reply
		if( "2".equals(status) || "3".equals(status)) {
			reply = "<a onclick="+replyAction+" class=\"eventNone\"><i class="+disableReplyIcon+" aria-hidden=\"true\" title="
					+replyIconTitle+" ></i></a>";

		}else if("0".equals(status)) {
			view="<a onclick="+viewAction+" class=\"eventNone\"><i class="+disableViewIcon+" aria-hidden=\"true\" title="
					+viewIconTitle+" ></i></a>";
		}
		else if("Disable".equals(userStatus)) {
			log.info("CURRENT USER CANN'T ACCESS BCOZ STATUS IS::::::"+userStatus);
			reply = "<a onclick="+replyAction+"><i class="+disableReplyIcon+" aria-hidden=\"true\" title="
					+replyIconTitle+" ></i></a>";
			view="<a onclick="+viewAction+"><i class="+disableViewIcon+" aria-hidden=\"true\" title="
					+viewIconTitle+" ></i></a>";

		}


		String action=reply.concat(view);
		return action;
	}


	/********************************* Icons for Admin Grievance *********************************/ 

	public String adminGrievanceState(String fileName,String txnId ,String grievanceId,String status,String userStatus,int userId) {
		String replyAction = "grievanceReply('"+userId+"','"+grievanceId+"','"+txnId+"')";
		String viewAction = "viewGrievanceHistory('"+grievanceId+"')";

		// state related Code 
		String reply = "<a onclick="+replyAction+"><i class="+replyIcon+" aria-hidden=\"true\" title="
				+replyIconTitle+" ></i></a>";
		String view="<a onclick="+viewAction+"><i class="+viewIcon+" aria-hidden=\"true\" title="
				+viewIconTitle+" ></i></a>";



		//Disable reply
		if( "2".equals(status) || "3".equals(status)) {

		}else if("0".equals(status)) {

		}
		else if("Disable".equals(userStatus)) {
			log.info("CURRENT USER CANN'T ACCESS BCOZ STATUS IS::::::"+userStatus);
			reply = "<a onclick="+replyAction+"><i class="+disableReplyIcon+" aria-hidden=\"true\" title="
					+replyIconTitle+" ></i></a>";
			view="<a onclick="+viewAction+"><i class="+disableViewIcon+" aria-hidden=\"true\" title="
					+viewIconTitle+" ></i></a>";

		}


		String action=reply.concat(view);
		return action;
	}






	/********************************* Icons for Admin *********************************/ 

	public String adminStockState(String fileName,String txnId ,String status,String userStatus) {
		// URL link 
		String emptyURL="JavaScript:void(0);"; 
		String errorURL = "./Consignment/dowloadFiles/error/"+fileName.replace(" ", "%20")+"/"+txnId+"/"+defaultTagName+"";
		String downloadURL = "./Consignment/dowloadFiles/actual/"+fileName.replace(" ", "%20")+"/"+txnId+"/"+defaultTagName+"";
		String viewAction="viewUploadedStockDetails('"+txnId+"')";  
		String approveAction = "ApproveStock('"+txnId+"')";
		String rejectAction = "disApproveStock('"+txnId+"')";
		String deleteAction ="DeleteStockRecord('"+txnId+"')";
		// state related Code 

		String download="<a href="+downloadURL+" download=\"download\"><i class="
				+downloadIcon+" aria-hidden=\"true\" title="
				+downloadIconTitle+" download=\"download\"></i></a>"; 

		String error="<a href="+errorURL+"><i class="+errorIcon+" aria-hidden=\"true\" title="
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



		if(("1".equals(status) || "3".equals(status) || "6".equals(status) || "7".equals(status)) && "Approved".equals(userStatus)) {
			error="<a href="+errorURL+" class="+disableIconClass+"><i  class="
					+disableErrorIcon+" aria-hidden=\"true\" title="
					+errorIconTitle+"  ></i></a>"; 
			delete="<a onclick="+deleteAction+" class=\"waves-effect waves-light modal-trigger eventNone\"><i class="
					+disableDeletionIcon+" aria-hidden=\"true\" title="
					+deleteIconTitle+"></i></a>";

		}


		else if(("3".equals(status) || "5".equals(status) || "6".equals(status) || "8".equals(status) || "9".equals(status)) && "Approved".equals(userStatus)) {

			delete="<a onclick="+deleteAction+" class=\"waves-effect waves-light modal-trigger eventNone\"><i class="
					+disableDeletionIcon+" aria-hidden=\"true\" title="
					+deleteIconTitle+"></i></a>";
		}
		else if("Disable".equals(userStatus)) {
			log.info("CURRENT USER CANN'T ACCESS BCOZ STATUS IS::::::"+userStatus);

			download="<a href="+downloadURL+" download=\"download\" class="+disableIconClass+"><i class="
					+disableDownloadIcon+" aria-hidden=\"true\" title="
					+downloadIconTitle+" download=\"download\"></i></a>"; 

			delete="<a onclick="+deleteAction+" class=\"waves-effect waves-light modal-trigger eventNone\" ><i class="
					+disableDeletionIcon+" aria-hidden=\"true\" title="
					+deleteIconTitle+"></i></a>"; 
		}

		String action=download.concat(error).concat(view).concat(approve).concat(reject).concat(delete);	
		return action;

	}




	/********************************** Icons for AdminRegistrationRequest **********************************/ 

	public String adminRegistrationRequest(String userId ,String userStatus,String AdminCurrentStatus,String createdOn,String roles, String type,String id) {
		// URL link 
		String emptyURL="JavaScript:void(0);"; 
		String approveAction = "userApprovalPopup("+userId+",'"+createdOn.replace(" ", "=")+"')";

		String viewAction="trcInformation?id="+id+"&roles="+roles+"&type="+type;
		String rejectAction = "userRejectPopup("+userId+")";

		//log.info("userId---->"+userId+"-------id---->"+id+"-------AdminCurrentStatus------>"+AdminCurrentStatus+"---createdOn--"+createdOn);

		// state related Code 


		String view="<a href="+viewAction+"><i class="+viewIcon+" aria-hidden=\"true\" title="
				+viewIconTitle+" ></i></a>";

		String approve = "<a onclick="+approveAction+"><i class="+approveIcon+" aria-hidden=\"true\" title="
				+approveIconTitle+" ></i></a>";   


		String reject = "<a onclick="+rejectAction+"><i class="+rejectIcon+" aria-hidden=\"true\" title="
				+rejectIconTitle+" ></i></a>";



		if("Approved".equals(AdminCurrentStatus) || "Rejected".equals(AdminCurrentStatus)) {
			approve = "<a onclick="+approveAction+" class=\"eventNone\"><i class="+disableApproveIcon+" aria-hidden=\"true\" title="
					+approveIconTitle+" ></i></a>";
			reject = "<a onclick="+rejectAction+" class=\"eventNone\"><i class="+disableRejectIcon+" aria-hidden=\"true\" title="
					+rejectIconTitle+" ></i></a>";

		}

		else if("Disable".equals(userStatus)) {
			log.info("CURRENT USER CANN'T ACCESS BCOZ STATUS IS::::::"+userStatus);

			approve = "<a onclick="+approveAction+"><i class="+disableApproveIcon+" aria-hidden=\"true\" title="
					+approveIconTitle+" ></i></a>";
			reject = "<a onclick="+rejectAction+" class=\"+disableIconClass+\"><i class="+disableRejectIcon+" aria-hidden=\"true\" title="
					+rejectIconTitle+" ></i></a>";

		}

		String action=view.concat(approve).concat(reject);		  
		return action;

	}



	/********************************** Icons for DashBoard Notification **********************************/ 


	public String dashboardIcon(String userStatus,Integer featureID) {
		// URL link 
		String viewAction = featureID == 3 ?"./Consignment/viewConsignment" :
			featureID == 4 ? "./assignDistributor": 
				featureID == 5 ? "./stolenRecovery" :
					featureID == 6 ? "./grievanceManagement" :
						featureID == 7 ? "./stolenRecovery" :
							featureID == 8 ? "./registrationRequest" :
								featureID == 11 ? "./manageTypeDevices":
									featureID == 12 ? "./uploadPaidStatus" :
										"JavaScript:void(0);";
		System.out.println("featureID::::::::::"+featureID);
		// state related Code 
		String view="<a href="+viewAction+"><i class="+viewIcon+" aria-hidden=\"true\" title="
				+viewIconTitle+" ></i></a>";
		String action=view;		  
		return action;

	}

	/********************************** Icons for TRC Manage Type Datatable **********************************/ 


	public String trcManageIcons(String status,Integer id,String fileName,String txnId) {	
		// URL link 
		//String downloadURL = "JavaScript:void(0)";
		String downloadURL = "./Consignment/dowloadFiles/actual/"+fileName.replace(" ", "%20")+"/"+txnId+"/"+defaultTagName+"";
		String viewAction="viewByID("+id+",'view')";
		String editAction= "viewByID("+id+",'edit')";
		// state related Code 
		String download="<a href="+downloadURL+" download=\"download\"><i class="
				+downloadIcon+" aria-hidden=\"true\" title="
				+downloadIconTitle+" download=\"download\"></i></a>"; 

		String view="<a onclick="+viewAction+"><i class="+viewIcon+" aria-hidden=\"true\" title="
				+viewIconTitle+" ></i></a>";

		String edit="<a onclick="+editAction+"><i class="
				+editIcon+" aria-hidden=\"true\"  title="
				+editIconTitle+"></i></a>"; 


		String action=download.concat(view).concat(edit);		  
		return action;

	}


	/********************************** Icons for UPS **********************************/ 	
	public String userPaidStatusIcon(Long imei1) {
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

		String downloadURL = "./dowloadFiles/"+fileName.replace(" ", "%20")+"/";

		String download="<a href="+downloadURL+" download=\"download\"><i class="
				+downloadIcon+" aria-hidden=\"true\" title="
				+downloadIconTitle+" download=\"download\"></i></a>"; 


		if("Disable".equals(userStatus)) {
			log.info("CURRENT USER CANN'T ACCESS BCOZ STATUS IS::::::"+userStatus);

			download="<a href="+downloadURL+" download=\"download\" class="+disableIconClass+"><i class="
					+disableDownloadIcon+" aria-hidden=\"true\" title="
					+downloadIconTitle+" download=\"download\"></i></a>"; 
		}
		String action=download;
		return action;

	}




	/********************************** Icons for Stolen **********************************/ 

	public String blockUnblockState(List<ActionModel> actionResponse,String fileName,String txnId ,String status,String userStatus, String requestType,int id,Integer qty,String source) {
		// URL link 
		String file = fileName == null ? null : fileName.replace(" ", "%20");

		String viewAction="";
		String editAction="";
		String emptyURL="JavaScript:void(0);"; 
		String errorURL = "./Consignment/dowloadFiles/error/"+file+"/"+txnId+"/"+defaultTagName+"";	
		String downloadURL = "./Consignment/dowloadFiles/actual/"+file+"/"+txnId+"/"+defaultTagName+"";


		if(source.equals("3")) {
			editAction="viewDeviceDetails('"+txnId+"','edit','"+requestType+"')";
			viewAction="viewDeviceDetails('"+txnId+"','view','"+requestType+"')";

		}
		else if(source.equals("4")) {
			editAction="viewblockImeiDevice('"+txnId+"','edit','"+requestType+"')";
			viewAction="viewblockImeiDevice('"+txnId+"','view','"+requestType+"')";

		}
		// state related Code 
		String error="<a href="+errorURL+"><i class="+errorIcon+" aria-hidden=\"true\" title="
				+errorIconTitle+" ></i></a>";
		String download="<a href="+downloadURL+" download=\"download\"><i class="
				+downloadIcon+" aria-hidden=\"true\" title="
				+downloadIconTitle+" download=\"download\"></i></a>"; 
		String edit="<a onclick="+editAction+"><i class="+editIcon+" aria-hidden=\"true\"  title="
				+editIconTitle+"></i></a>"; 
		String view="<a onclick="+viewAction+"><i class="+viewIcon+" aria-hidden=\"true\" title="
				+viewIconTitle+" ></i></a>";


		for (ActionModel actionModel : actionResponse) {

			
			switch(actionModel.getState()) {
			case 0:
				error=disableHandling(actionModel, errorURL);
				log.info("------------------ case 0");
				break;
			case 1:
				error=disableHandling(actionModel, errorURL);
				log.info("------------------ case 1");
				break;
			case 2:
				error=disableHandling(actionModel, errorURL);
				log.info("------------------ case 2");
				break;
			case 3:
				error=enableHandling(actionModel,errorURL);
				log.info("------------------ case 3");
				break;
			case 4:
				error=disableHandling(actionModel, errorURL);
				log.info("------------------ case 4");
				break;
			case 5:
				error=disableHandling(actionModel, errorURL);
				log.info("------------------ case 5");
				break;
				}
	}

		String action=error.concat(download).concat(view).concat(edit);	
		return action;

	}

private String enableHandling(ActionModel actionModel,String errorURL) {
		//log.info("in Action enableHandling--->" +actionModel.getState());
		return "<a href="+errorURL+"><i class="+errorIcon+" aria-hidden=\"true\" title="+errorIconTitle+" ></i></a>";
}


private String disableHandling(ActionModel actionModel,String errorURL) {
	//log.info("in Action disableHandling--->" +actionModel.getState());
	return "<a href="+errorURL+" class="+disableIconClass+"><i class="+disableErrorIcon+" aria-hidden=\"true\" title="+errorIconTitle+"  ></i></a>";
}




	


	/********************************** Icons for Admin MEssage Management**********************************/ 

	public String adminMessageIcons(String userStatus, String tag) { 
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


	public String trcAdminManageIcons(String status,Integer id,String fileName,String txnId) {	
		String viewAction="viewByID("+id+",'view')";
		String downloadURL = "./Consignment/dowloadFiles/actual/"+fileName.replace(" ", "%20")+"/"+txnId+"/"+defaultTagName+"";
		String approveAction = "openApproveTACPopUp('"+txnId+"','')";
		String rejectAction= "openDisapproveTACPopUp('"+txnId+"','')";


		String view="<a onclick="+viewAction+"><i class="+viewIcon+" aria-hidden=\"true\" title="
				+viewIconTitle+" ></i></a>";

		String download="<a href="+downloadURL+" download=\"download\"><i class="
				+downloadIcon+" aria-hidden=\"true\" title="
				+downloadIconTitle+" download=\"download\"></i></a>";
		String approve = "<a onclick="+approveAction+"><i class="+approveIcon+" aria-hidden=\"true\" title="
				+approveIconTitle+" ></i></a>";   
		String reject = "<a onclick="+rejectAction+"><i class="+rejectIcon+" aria-hidden=\"true\" title="
				+rejectIconTitle+" ></i></a>";


		String action = view.concat(download).concat(approve).concat(reject);
		return action;

	}

	/********************************** Icons for adminBlockUnblock **********************************/ 

	public String adminBlockUnblock(List<ActionModel> actionResponse, String fileName, String txnId, String status,
			String userStatus, String requestType, int id, Integer qty, String source) {
		String file = fileName == null ? null : fileName.replace(" ", "%20");
		String viewAction = "";
		String downloadURL = "./Consignment/dowloadFiles/actual/" + file + "/" + txnId +"/"+defaultTagName+ "";
		String approveAction = "deviceApprovalPopup('" + txnId + "','" + requestType + "')";
		String rejectAction = "userRejectPopup('" + txnId + "','" + requestType + "')";
		log.info("============actionResponse=======" + actionResponse);
		if(source.equals("3")) {
			viewAction="viewDeviceDetails('"+txnId+"','view','"+requestType+"')";

		}
		else if(source.equals("4")) {
			viewAction="viewblockImeiDevice('"+txnId+"','view','"+requestType+"')";
		}
		// state related Code
		String view="<a onclick="+viewAction+"><i class="
				+viewIcon+" aria-hidden=\"true\" title=" +viewIconTitle+" ></i></a>";
		String download="<a href="+downloadURL+" download=\"download\"><i class="
				+downloadIcon+" aria-hidden=\"true\" title="
				+downloadIconTitle+" download=\"download\"></i></a>"; String approve =
				"<a onclick="+approveAction+"><i class="
						+approveIcon+" aria-hidden=\"true\" title=" +approveIconTitle+" ></i></a>";
				String reject = "<a onclick="+rejectAction+"><i class="
						+rejectIcon+" aria-hidden=\"true\" title=" +rejectIconTitle+" ></i></a>";


				for (ActionModel actionModel : actionResponse) {
					if (actionModel.getState() == 2) {
						view = "<a onclick=" + viewAction + "><i class=" + viewIcon + " aria-hidden=\"true\" title="
								+ viewIconTitle + " ></i></a>";
						download = "<a href=" + downloadURL + " download=\"download\"><i class=" + downloadIcon
								+ " aria-hidden=\"true\" title=" + downloadIconTitle + " download=\"download\"></i></a>";
						approve = "<a onclick=" + approveAction + "><i class=" + approveIcon
								+ " aria-hidden=\"true\" title=" + approveIconTitle + " ></i></a>";
						reject = "<a onclick=" + rejectAction + "><i class=" + rejectIcon
								+ " aria-hidden=\"true\" title=" + rejectIconTitle + " ></i></a>";

					} else if (actionModel.getState() == 4 || actionModel.getState() == 5) {
						view = "<a onclick=" + viewAction + "><i class=" + viewIcon + " aria-hidden=\"true\" title="
								+ viewIconTitle + " ></i></a>";
						download = "<a href=" + downloadURL + " download=\"download\"><i class=" + downloadIcon
								+ " aria-hidden=\"true\" title=" + downloadIconTitle + " download=\"download\"></i></a>";
						approve = "<a onclick=" + approveAction + " class=\"eventNone\"><i class=" + disableApproveIcon
								+ " aria-hidden=\"true\" title=" + approveIconTitle + " ></i></a>";
						reject = "<a onclick=" + rejectAction + " class=\"eventNone\"><i class=" + disableRejectIcon
								+ " aria-hidden=\"true\" title=" + rejectIconTitle + " ></i></a>";

					}
				}

				if ("Disable".equals(userStatus)) {
					log.info("CURRENT USER CANN'T ACCESS BCOZ STATUS IS::::::" + userStatus);


				}


				String action = view.concat(download).concat(approve).concat(reject);
				return action;
	}	



	/********************************** Icons for StolenlawfulAgency **********************************/ 

	public String StolenlawfulAgency(String fileName,String txnId ,String status,String userStatus, String requestType,int id,Integer qty,String source, String requestTypeValue) {
		// URL link 
		String file = fileName == null ? null : fileName.replace(" ", "%20");

		log.info("@@@@  "+source+"&&"+requestTypeValue);

		String viewAction="";
		String editAction="";
		String emptyURL="JavaScript:void(0);"; 
		String errorURL = "./Consignment/dowloadFiles/error/"+file+"/"+txnId+"/"+defaultTagName+"";	
		String downloadURL = "./Consignment/dowloadFiles/actual/"+file+"/"+txnId+"/"+defaultTagName+"";
		String deleteAction = "JavaScript:void(0);"; 


		if(source.equals("5") && requestTypeValue.equals("0")) {
			//check for Stolen/Indvisual
			log.info("edit and view   Indivisual Stolen");
			editAction="openStolenRecoveryPage('editIndivisualsStolen','edit')";
			viewAction="openStolenRecoveryPage('editIndivisualsStolen','view')";

		}
		else if(source.equals("6") && requestTypeValue.equals("0")) {
			//check for Stolen/Company
			log.info("edit and view   Company Stolen");
			editAction="openStolenRecoveryPage('editCompanyStolen','edit')";
			viewAction="openStolenRecoveryPage('editCompanyStolen','view')";

		}
		else if(source.equals("4") && requestTypeValue.equals("1")) {
			//check for Recovery/single
			log.info("edit and view   single Recovery");
			editAction="openStolenRecoveryPage('editIndivisualRecovery','edit')";
			viewAction="openStolenRecoveryPage('editIndivisualRecovery','view')";

		}
		else if(source.equals("6") && requestTypeValue.equals("1")) {
			//check for Recovery/company
			log.info("edit and view   Company Recovery");
			editAction="openStolenRecoveryPage('editCompanyRecovery','edit')";
			viewAction="openStolenRecoveryPage('editCompanyRecovery','view')";

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
		String error="<a href="+errorURL+"><i class="+errorIcon+" aria-hidden=\"true\" title="
				+errorIconTitle+" ></i></a>";
		String download="<a href="+downloadURL+" download=\"download\"><i class="
				+downloadIcon+" aria-hidden=\"true\" title="
				+downloadIconTitle+" download=\"download\"></i></a>"; 
		String edit="<a onclick="+editAction+"><i class="+editIcon+" aria-hidden=\"true\"  title="
				+editIconTitle+"></i></a>"; 
		String view="<a onclick="+viewAction+"><i class="+viewIcon+" aria-hidden=\"true\" title="
				+viewIconTitle+" ></i></a>";
		String delete="<a onclick="+deleteAction+" class=\"waves-effect waves-light modal-trigger\"><i class="
				+deletionIcon+" aria-hidden=\"true\"  title="
				+deleteIconTitle+"></i></a>"; 




		if("Disable".equals(userStatus)) {
			log.info("CURRENT USER CANN'T ACCESS BCOZ STATUS IS::::::"+userStatus);
			error="<a href="+errorURL+" class="+disableIconClass+"><i class="+disableErrorIcon+" aria-hidden=\"true\" title="
					+errorIconTitle+" ></i></a>";
			download="<a href="+downloadURL+" download=\"download\" class="+disableIconClass+"><i class="
					+disableDownloadIcon+" aria-hidden=\"true\"  title="
					+downloadIconTitle+" download=\"download\"></i></a>"; 
			edit="<a onclick="+editAction+" class="+disableIconClass+"><i class="
					+disableEditIcon+" aria-hidden=\"true\"  title="
					+editIconTitle+"></i></a>"; 

		}

		String action=error.concat(download).concat(view).concat(edit).concat(delete);	
		return action;

	}

	/********************************** Icons for Audit Management**********************************/ 

	public String auditManagementIcons(String userStatus,String userId,String id) { 

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
public String endUserGrievanceState(String fileName,String txnId ,String grievanceId,Integer userId) {
		
		log.info(" entry in set view in data table.....");
		String replyAction = "endUserGrievanceReply('"+userId+"','"+grievanceId+"','"+txnId+"')";
		String viewAction = "endUserviewGrievanceHistory('"+grievanceId+"','"+projectPath+"','"+userId+"')";

		// state related Code 
		String reply = "<a onclick="+replyAction+"><i class="+replyIcon+" aria-hidden=\"true\" title="
				+replyIconTitle+" ></i></a>";
		String view="<a onclick="+viewAction+"><i class="+viewIcon+" aria-hidden=\"true\" title="
				+viewIconTitle+" ></i></a>";

		log.info("set view in data table.....");

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


/********************************** Icons for Importal TRC Datatable **********************************/ 


public String importalTrcManageIcons(String status,Integer id,String fileName,String txnId) {	
	// URL link 
	//String downloadURL = "JavaScript:void(0)";

	String viewAction="viewByID("+id+",'view')";
	String editAction= "viewByID("+id+",'edit')";
	String deleteAction = "JavaScript:void(0);"; 
	
	// state related Code 
	

	String view="<a onclick="+viewAction+"><i class="+viewIcon+" aria-hidden=\"true\" title="
			+viewIconTitle+" ></i></a>";

	String edit="<a onclick="+editAction+"><i class="
			+editIcon+" aria-hidden=\"true\"  title="
			+editIconTitle+"></i></a>"; 
	
	String delete="<a onclick="+deleteAction+" class=\"waves-effect waves-light modal-trigger\"><i class="
			+deletionIcon+" aria-hidden=\"true\"  title="
			+deleteIconTitle+"></i></a>"; 

	String action=view.concat(edit).concat(delete);		  
	return action;

}

/********************************** Icons for Device Activation **********************************/ 	

public String deviceActivationIcon(Long imei1,String createdOn,String txnId) {
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

public String manageUserIcons(String id) { 

	String viewAction="viewDetails('"+id+"')";
	String editAction="";
	String ListAction ="";
	String AddAction = "";
	// state related Code 
	String view="<a onclick="+viewAction+"><i class="+viewIcon+" aria-hidden=\"true\" title="
			+viewIconTitle+" ></i></a>";
	String edit="<a onclick="+editAction+"><i class="+editIcon+" aria-hidden=\"true\"  title="
			+editIconTitle+"></i></a>"; 
	String List = "<a onclick="+ListAction+"><i class="+ListIcon+" aria-hidden=\"true\"  title="
			+ListIconTittle+"></i></a>"; 
	String Add = "<a onclick="+AddAction+"><i class="+plusIcon+" aria-hidden=\"true\"  title="
			+plusIconTittle+"></i></a>"; 

	String action = view.concat(edit).concat(List).concat(Add);
	return action;

}

}
