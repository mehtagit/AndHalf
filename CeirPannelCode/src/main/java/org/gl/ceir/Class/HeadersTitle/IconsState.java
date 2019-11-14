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
	String replyIcon="\"fa-reply\""; 
	
	// icon title  
	String errorIconTitle="Error-File";
	String downloadIconTitle="Download"; 
	String viewIconTitle="View"; 
	String editIconTitle="Edit"; 
	String deleteIconTitle="Delete"; 
	String replyIconTitle="Reply";
		
	
	String disableErrorIcon="\"fa fa-exclamation-circle error-icon disable\""; 
	String disableDownloadIcon="\"fa fa-download download-icon disable\""; 
	String disableViewIcon="\"fa fa-eye view-icon disable\"";
	String disableEditIcon="\"fa fa-pencil edit-icon disable\""; 
	String disableDeletionIcon="\"fa fa-trash delete-icon disable\"";
	String disableReplyIcon="\"fa-reply\""; 
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

		
			
		  if(("0".equals(status) || "4".equals(status) || "7".equals(status)) && "Active".equals(userStatus)) {
		  error="<a href="+errorURL+" class="+disableIconClass+"><i  class="
		  +disableErrorIcon+" aria-hidden=\"true\" title="
		  +errorIconTitle+"  ></i></a>"; 
		  }
		  
		  else if(("1".equals(status)) && "Active".equals(userStatus)) {
			  error="<a href="+errorURL+" class="+disableIconClass+"><i  class="
					  +disableErrorIcon+" aria-hidden=\"true\" title="
					  +errorIconTitle+"  ></i></a>"; 
			  edit="<a onclick="+editAction+" class="+disableIconClass+"><i class="
						+disableEditIcon+" aria-hidden=\"true\"  title="
						+editIconTitle+"></i></a>"; 
		  	}
		   else if(("3".equals(status) || "5".equals(status) || "6".equals(status) || "8".equals(status) || "9".equals(status))  && "Active".equals(userStatus)) {
			  error="<a href="+errorURL+" class="+disableIconClass+"><i  class="
					  +disableErrorIcon+" aria-hidden=\"true\" title="
					  +errorIconTitle+"  ></i></a>"; 
			  edit="<a onclick="+editAction+" class="+disableIconClass+"><i class="
						+disableEditIcon+" aria-hidden=\"true\" title="
						+editIconTitle+"></i></a>"; 
			  delete="<a onclick="+deleteAction+" class=\"waves-effect waves-light modal-trigger\" class="+disableIconClass+"><i class="
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
				delete="<a onclick="+deleteAction+" class=\"waves-effect waves-light modal-trigger\" class="+disableIconClass+"><i class="
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

		if(("0".equals(status))  && "Active".equals(userStatus)) {
			  error="<a href="+errorURL+" class="+disableIconClass+"><i  class="
			  +disableErrorIcon+" aria-hidden=\"true\" title="
			  +errorIconTitle+"  ></i></a>"; 
			  }
			  
			  else if(("1".equals(status) || "3".equals(status)) && "Active".equals(userStatus)) {
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
					delete="<a onclick="+deleteAction+" class=\"waves-effect waves-light modal-trigger\" class="+disableIconClass+"><i class="
													+disableDeletionIcon+" aria-hidden=\"true\"  title="
													+deleteIconTitle+"></i></a>"; 			
			  }
			 String action=error.concat(download).concat(view).concat(edit).concat(delete);
			return action;
		}
	
	
	
	/********************************** Icons for Stolen **********************************/ 

public String stolenState(String fileName,String txnId ,String status,String userStatus) {
	// URL link 
	String emptyURL="JavaScript:void(0);"; 
	String errorURL = "./dowloadFiles/error/"+fileName+"/"+txnId+"";	
	String editAction="EditConsignmentDetails('"+txnId+"')";
	String deleteAction ="DeleteConsignmentRecord('"+txnId+"')";


	// state related Code 
	String error="<a href="+errorURL+"><i class="+errorIcon+" aria-hidden=\"true\" title="
	+errorIconTitle+" style=\"color: red; font-size:20px; margin-right:0px;\"></i></a>";
	
	String edit="<a onclick="+editAction+"><i class="+editIcon+" aria-hidden=\"true\" style=\"font-size: 20px; margin:0 15px 0 15px; color: #006994\" title="
			+editIconTitle+"></i></a>"; 
	
	String delete="<a onclick="+deleteAction+" class=\"waves-effect waves-light modal-trigger\"><i class="+deletionIcon+" aria-hidden=\"true\" style=\"font-size: 20px; color: red;\" title="
	+deleteIconTitle+"></i></a>"; 

	 if("1".equals(status)) {
		delete="<a onclick="+deleteAction+" class=\"waves-effect waves-light modal-trigger\" class="+disableIconClass+"><i class="
		+deletionIcon+" aria-hidden=\"true\" style=\"font-size: 20px; color: red;\" title="
		+deleteIconTitle+"></i></a>"; 
	}
	
	
	else if("Disable".equals(userStatus)) {
	log.info("CURRENT USER CANN'T ACCESS BCOZ STATUS IS::::::"+userStatus);
	error="<a href="+errorURL+" class="+disableIconClass+"><i class="+errorIcon+" aria-hidden=\"true\" title="
	+errorIconTitle+" style=\"color: red; font-size:20px; margin-right:15px;\"></i></a>";
	edit="<a onclick="+editAction+" class="+disableIconClass+"><i class="
			+editIcon+" aria-hidden=\"true\" style=\"font-size: 20px; margin:0 15px 0 15px; color: #006994\" title="
			+editIconTitle+"></i></a>"; 
	delete="<a onclick="+deleteAction+" class=\"waves-effect waves-light modal-trigger\" class="+disableIconClass+"><i class="
	+deletionIcon+" aria-hidden=\"true\" style=\"font-size: 20px; color: red;\" title="
	+deleteIconTitle+"></i></a>"; 
	}

	String action=error.concat(edit).concat(delete);	
	return action;

	}
}

