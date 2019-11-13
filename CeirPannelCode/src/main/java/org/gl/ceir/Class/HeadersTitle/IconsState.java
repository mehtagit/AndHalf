package org.gl.ceir.Class.HeadersTitle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class IconsState {
	String className = "emptyClass";
	private final Logger log = LoggerFactory.getLogger(getClass());
	public String state(String fileName,String txnId ,String status) {
		String errorIcon="\"fa fa-exclamation-circle\"";
		String downloadIcon="\"fa fa-download\""; 
		String viewIcon="\"fa fa-eye teal-text\"";
		String editIcon="\"fa fa-pencil\""; 
		String deletionIcon="\"fa fa-trash\"";
		String replyIcon="\"fa-reply\""; 
		// URL link 
		String emptyURL="JavaScript:void(0);"; 
		String downloadURL = "./dowloadFiles/actual/"+fileName+"/"+txnId+"";
		String errorURL = "./dowloadFiles/eror/"+fileName+"/"+txnId+"";			
		String viewAction="viewConsignmentDetails('"+txnId+"')"; 
		String editAction="EditConsignmentDetails('"+txnId+"')";
		String deleteAction ="DeleteConsignmentRecord('"+txnId+"')";
		// icon title  
		String errorIconTitle="Error-File";
		String downloadIconTitle="Download"; 
		String viewIconTitle="View"; 
		String editIconTitle="Edit"; 
		String deleteIconTitle="Delete"; 
		String replyIconTitle="Reply";

		// state related Code 
		String error="<a href="+errorURL+"><i class="+errorIcon+" aria-hidden=\"true\" title="
				+errorIconTitle+" style=\"color: red; font-size:20px; margin-right:15px;\"></i></a>";
		String download="<a href="+downloadURL+" download=\"download\"><i class="
						+downloadIcon+" aria-hidden=\"true\" style=\"font-size: 20px; color:#2e8b57\" title="
						+downloadIconTitle+" download=\"download\"></i></a>"; 
		String view="<a onclick="+viewAction+"><i class="+viewIcon+" aria-hidden=\"true\" title="
								+viewIconTitle+" style=\"font-size: 20px; margin:0 0 0 15px;\"></i></a>";
		String edit="<a onclick="+editAction+"><i class="
								+editIcon+" aria-hidden=\"true\" style=\"font-size: 20px; margin:0 15px 0 15px; color: #006994\" title="
								+editIconTitle+"></i></a>"; 
		String delete="<a onclick="+deleteAction+" class=\"waves-effect waves-light modal-trigger\"><i class="
										+deletionIcon+" aria-hidden=\"true\" style=\"font-size: 20px; color: red;\" title="
										+deleteIconTitle+"></i></a>"; 
		String reply="<a href="+emptyURL+" class=\"waves-effect waves-light modal-trigger\"><i class="
												+deletionIcon+" aria-hidden=\"true\" style=\"font-size: 20px; color: red;\" title="
												+replyIconTitle+"></i></a>";

		
		
		  if("0".equals(status)) {
		  error="<a href="+errorURL+" class="+className+"><i  class="
		  +errorIcon+" aria-hidden=\"true\" title="
		  +errorIconTitle+" style=\"color: red; font-size:20px; margin-right:15px;\" ></i></a>"
		  ; download="<a href="+downloadURL+" class="
		  +className+" download=\"download\"><i class="
		  +downloadIcon+" aria-hidden=\"true\" style=\"font-size: 20px; color:#2e8b57\" title="
		  +downloadIconTitle+" download=\"download\" ></i></a>";
		  
		  edit="<a onclick="+editAction+" class="+className+"><i class="
		  +editIcon+" aria-hidden=\"true\" style=\"font-size: 20px; margin:0 15px 0 15px; color: #006994\" title="
		  +editIconTitle+"></i></a>"; } else if("1".equals(status)) {
		  delete="<a class="+className+" onclick="
		  +deleteAction+" class=\"waves-effect waves-light modal-trigger\"><i class="
		  +deletionIcon+" aria-hidden=\"true\" style=\"font-size: 20px; color: red;\" title="
		  +deleteIconTitle+"></i></a>";
		  view="<a class="+className+" onclick="+viewAction+"><i class="
		  +viewIcon+" aria-hidden=\"true\" title="
		  +viewIconTitle+" style=\"font-size: 20px; margin:0 0 0 15px;\"></i></a>"; }
		  else if("2".equals(status)) {
		  view="<a class="+className+" onclick="+viewAction+"><i class="
		  +viewIcon+" aria-hidden=\"true\" title="
		  +viewIconTitle+" style=\"font-size: 20px; margin:0 0 0 15px;\" ></i></a>";
		  reply="<a class="+className+" href="
		  +emptyURL+" class=\"waves-effect waves-light modal-trigger\"><i class="
		  +replyIcon+" aria-hidden=\"true\" style=\"font-size: 20px; color: red;\" title="
		  +replyIconTitle+"></i></a>"; 
		  }
		  String action=error.concat(download).concat(view).concat(edit).concat(delete);		  
		return action;
		 
	}
}
