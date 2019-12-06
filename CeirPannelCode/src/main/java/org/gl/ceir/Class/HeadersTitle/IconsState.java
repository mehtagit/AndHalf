package org.gl.ceir.Class.HeadersTitle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class IconsState {
	String className = "emptyClass";
	String disableIconClass = "eventNone";
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
	
	// icon title  
	String errorIconTitle="Error-File";
	String downloadIconTitle="Download"; 
	String viewIconTitle="View"; 
	String editIconTitle="Edit"; 
	String deleteIconTitle="Delete"; 
	String replyIconTitle="Reply";
	String approveIconTitle="Approve";
	String rejectIconTitle="Reject";
		
	
	String disableErrorIcon="\"fa fa-exclamation-circle error-icon disable\""; 
	String disableDownloadIcon="\"fa fa-download download-icon disable\""; 
	String disableViewIcon="\"fa fa-eye view-icon disable\"";
	String disableEditIcon="\"fa fa-pencil edit-icon disable\""; 
	String disableDeletionIcon="\"fa fa-trash delete-icon disable\"";
	String disableReplyIcon="\"fa fa-reply reply-icon disable\""; 
	String disableApproveIcon = "\"fa fa-check-circle-o approve-icon disable\"";
	String disableRejectIcon = "\"fa fa-user-times reject-icon disable\"";
	
	
	
	public String state(String fileName,String txnId ,String status,String userStatus) {
		// URL link 
		String emptyURL="JavaScript:void(0);"; 
		String downloadURL = "./dowloadFiles/actual/"+fileName+"/"+txnId+"";
		String errorURL = "./dowloadFiles/error/"+fileName+"/"+txnId+"";	
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
		String downloadURL = "./dowloadFiles/actual/"+fileName+"/"+txnId+"";
		String errorURL = "./dowloadFiles/error/"+fileName+"/"+txnId+"";	
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
		String downloadURL = "./dowloadFiles/actual/"+fileName+"/"+txnId+"";
		String errorURL = "./dowloadFiles/error/"+fileName+"/"+txnId+"";	
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

public String stolenState(String fileName,String txnId ,String status,String userStatus, String requestType,int id) {
	// URL link 
	String emptyURL="JavaScript:void(0);"; 
	String errorURL = "./dowloadFiles/error/"+fileName+"/"+txnId+"";	
	String downloadURL = "./dowloadFiles/actual/"+fileName+"/"+txnId+"";
	String editAction="openFileStolenUpdate('"+txnId+"','"+requestType+"','"+id+"')";
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
String downloadURL = "./dowloadFiles/actual/"+fileName+"/"+txnId+"";
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

if("5".equals(status) || "7".equals(status) || "8".equals(status) && "Approved".equals(userStatus) ) {
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

public String adminState(String fileName,String txnId ,String status,String userStatus) {
// URL link 
String emptyURL="JavaScript:void(0);"; 
String viewAction="viewConsignmentDetails('"+txnId+"')"; 

String approveAction = "openApprovePopUp('"+txnId+"')";
String rejectAction = "openDisapprovePopup('"+txnId+"')";

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
	String viewAction = "viewGrievanceHistory('"+grievanceId+"')";
	
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
		String errorURL = "./dowloadFiles/error/"+fileName+"/"+txnId+"";
		String downloadURL = "./dowloadFiles/actual/"+fileName+"/"+txnId+"";
		String viewAction="viewUploadedStockDetails('"+txnId+"')";  
		String approveAction = null;
		String rejectAction = null;
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



if(("0".equals(status) || "4".equals(status) || "7".equals(status)) && "Approved".equals(userStatus)) {

}

else if(("1".equals(status)) && "Approved".equals(userStatus)) {

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

public String adminRegistrationRequest(String Id ,String status,String userStatus,String AdminCurrentStatus,String createdOn,String roles, String type) {
	// URL link 
	String emptyURL="JavaScript:void(0);"; 
	String approveAction = "userApprovalPopup("+Id+","+createdOn+")";
	String viewAction="trcInformation?id="+Id+"&roles="+roles+"&type="+type;
	String rejectAction = "userRejectPopup("+Id+","+createdOn+")";
	
	log.info("status---->"+status+"-------userStatus---->"+userStatus+"-------AdminCurrentStatus------>"+AdminCurrentStatus);

	// state related Code 

	
	String view="<a href="+viewAction+"><i class="+viewIcon+" aria-hidden=\"true\" title="
							+viewIconTitle+" ></i></a>";

	String approve = "<a onclick="+approveAction+"><i class="+approveIcon+" aria-hidden=\"true\" title="
		+approveIconTitle+" ></i></a>";   


	String reject = "<a onclick="+rejectAction+"><i class="+rejectIcon+" aria-hidden=\"true\" title="
		+rejectIconTitle+" ></i></a>";
	

	
	if("Approved".equals(AdminCurrentStatus) || "Rejected".equals(AdminCurrentStatus)  && "Approved".equals(status)) {
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


public String dashboardIcon(String userStatus) {
	// URL link 
	String viewAction="./Consignment/viewConsignment"; 
	
	// state related Code 
	String view="<a href="+viewAction+"><i class="+viewIcon+" aria-hidden=\"true\" title="
							+viewIconTitle+" ></i></a>";
	String action=view;		  
	return action;
	 
}







}

