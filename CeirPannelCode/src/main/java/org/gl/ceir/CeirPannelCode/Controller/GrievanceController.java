package org.gl.ceir.CeirPannelCode.Controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpSession;

import org.gl.ceir.CeirPannelCode.Feignclient.FeignCleintImplementation;
import org.gl.ceir.CeirPannelCode.Model.GenricResponse;
import org.gl.ceir.CeirPannelCode.Model.GrievanceModel;
import org.gl.ceir.CeirPannelCode.Model.StockUploadModel;
import org.gl.ceir.CeirPannelCode.Util.UtilDownload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GrievanceController {

	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired

	FeignCleintImplementation feignCleintImplementation;
	@Autowired
	UtilDownload utildownload;
	
	GrievanceModel grievance= new GrievanceModel();
	GenricResponse response = new GenricResponse();
	
	@RequestMapping(value=
		{"grievanceManagement"},method={org.springframework.web.bind.annotation.
				RequestMethod.GET,org.springframework.web.bind.annotation.RequestMethod.POST}
			)
	    public ModelAndView viewGrievance(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		 
		log.info(" view Grievance entry point."); 
		 mv.setViewName("grievanceManagement");
		log.info(" view Grievance exit point."); 
		return mv; 
	}
	
	
	@RequestMapping(value= {"/saveGrievance"},method={org.springframework.web.bind.annotation.RequestMethod.POST}) 
	public @ResponseBody GenricResponse registerConsignment(@RequestParam(name="txnId",required = false) String txnId,@RequestParam(name="categoryId",required = false) int categoryId
			,@RequestParam(name="remarks",required = false) String remarks,@RequestParam(name="file",required = false) MultipartFile file,HttpSession session) {

		int userId= (int) session.getAttribute("userid");
		String roletype=session.getAttribute("usertype").toString();

		
		log.info("save grievance  entry point.");

		String grevnceId=utildownload.getTxnId();
		grevnceId = "G"+grevnceId;
		log.info("Random  genrated transaction number ="+grevnceId);

		
		try {
			byte[] bytes = file.getBytes();
			String rootPath = "/home/ubuntu/apache-tomcat-9.0.4/webapps/Design/"+grevnceId+"/";
			File dir = new File(rootPath + File.separator);

			if (!dir.exists()) 
				dir.mkdirs();
			// Create the file on server
			// Calendar now = Calendar.getInstance();

			File serverFile = new File(rootPath+file.getOriginalFilename());
			log.info("uploaded file path on server" + serverFile);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(bytes);
			stream.close();

		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		// set reaquest parameters into model class
		
		grievance.setFileName(file.getOriginalFilename());
		grievance.setCategoryId(categoryId);
		grievance.setRemarks(remarks);
		grievance.setTxnId(txnId);
		
		log.info("grievance form parameters passed to save grievance api "+grievance);
		response = feignCleintImplementation.saveGrievance(grievance);
		log.info("response from register consignment api"+response);
		log.info("upload stock  exit point.");
		return response;


	}

}

