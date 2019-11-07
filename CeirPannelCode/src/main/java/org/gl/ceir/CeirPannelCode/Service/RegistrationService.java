package org.gl.ceir.CeirPannelCode.Service;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream; 
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.gl.ceir.CeirPannelCode.Feignclient.UserRegistrationFeignImpl;
import org.gl.ceir.CeirPannelCode.Model.Otp;
import org.gl.ceir.CeirPannelCode.Model.OtpResponse;
import org.gl.ceir.CeirPannelCode.Model.Registration;
import org.gl.ceir.CeirPannelCode.Model.SecurityQuestion;
import org.gl.ceir.CeirPannelCode.Model.Usertype;
import org.gl.ceir.CeirPannelCode.Util.GenerateRandomDigits;
import org.gl.ceir.CeirPannelCode.Util.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
@Service
public class RegistrationService {
	@Value("${FilePath1}") 
	String filePath; 
	@Autowired
	UserRegistrationFeignImpl registrationFeignImpl;
	@Autowired
	UserRegistrationFeignImpl userRegistrationFeignImpl;
	@Autowired
	GenerateRandomDigits randomDigits;
	private final Logger log = LoggerFactory.getLogger(getClass());
	public ModelAndView registrationView() {
		log.info("view registration page starting point");
		ModelAndView mv=new ModelAndView();
		mv.setViewName("registration");
		List<Usertype> usertypeList=registrationFeignImpl.userypeList();
		List<SecurityQuestion> securityQuestionList=registrationFeignImpl.securityQuestionList();
		mv.addObject("usertypes",usertypeList);
		mv.addObject("questions",securityQuestionList);
		log.info("view registration page ending point");
		return mv;                
	}
	public ModelAndView saveRegistration(Registration registration, MultipartFile file,HttpSession session) throws IOException {
		log.info("save registration page starting point");
		//log.info("roles length:  "+registration.getRoles().length);
		log.info("registration data:  "+registration); 
		ModelAndView mv=new ModelAndView(); 
		String validCaptcha=(String)session.getAttribute("captcha_security");
		log.info("captch from session:  "+validCaptcha); 
		if(registration.getCaptcha().equals(validCaptcha)) {
		if(registration.getRePassword().equals(registration.getPassword())) {
			String username=randomDigits.getAlphaNumericString(4)+randomDigits.getNumericString(4)+randomDigits.getAlphaNumericString(1);
			registration.setUsername(username);
			StringBuilder combinedPath=new StringBuilder(filePath).append("/"+username);
			String finalPath=new String(combinedPath); 
			if("Individual".equals(registration.getType())){
				if(file.isEmpty()==true) { 
					log.info("if file is empty");
					mv.addObject("msg","please upload national information");
					mv.setViewName("registration");
				}     
				else{ 
					log.info("if user is individual  "); 
					log.info("file name: " +file.getOriginalFilename());
					log.info("finalPath:   "+finalPath);  
					 File dir = new File(finalPath);
					  if (!dir.exists()) dir.mkdirs();
					byte barr[]=file.getBytes();
					BufferedOutputStream bout=new BufferedOutputStream(new FileOutputStream(finalPath + "/" + file.getOriginalFilename()));
					bout.write(barr);
					bout.flush();
					bout.close();
					OtpResponse response=userRegistrationFeignImpl.registration(registration);
					log.info("registration response:  "+response);
					if(response.getStatusCode()==200) {
					mv.addObject("msg",response.getResponse());
					mv.addObject("userId",response.getUserId());
					mv.setViewName("verifyOtp"); 
					}
					else {
						mv.addObject("msg",response.getResponse());
						mv.setViewName("registration");  
					}
				}  
			}  
			else { 
				OtpResponse response=userRegistrationFeignImpl.registration(registration);
				if(response.getStatusCode()==200) {
					mv.addObject("msg",response.getResponse());
					mv.addObject("userId",response.getUserId());
					mv.setViewName("verifyOtp"); 
					}
					else {
						mv.addObject("msg",response.getResponse());
						mv.setViewName("registration");  
					}
			}
			log.info("save registration page end point");
		}  
		else {
			mv.addObject("msg","Password and Re password must be same");
			mv.setViewName("registration");      
		}
		}
		else {
			mv.setViewName("Please enter valid captcha");
			mv.setViewName("registration"); 	
		}
		return mv;                 
	}  

	public String otpPage() {
		log.info("inside verify otp page");
		return "verifyOtp";  
	} 

	public HttpResponse verifyOtp(Otp otp) {
		log.info("inside verify otp controller");
		log.info("otp data:  "+otp);    
		HttpResponse response=userRegistrationFeignImpl.otpValidate(otp);
		log.info("verify otp api response:  "+response);
		log.info("exit from verify otp controller");
		return response;
	}
	
	public List<SecurityQuestion> securityQuestionList(){
		log.info("inside security question controller");
		List<SecurityQuestion> questionList =userRegistrationFeignImpl.securityQuestionList();
		return questionList; 
	}
	public HttpResponse resendOtp(Integer id) {
		log.info("inside resend otp controller");
		log.info("id:   "+id);     
		HttpResponse response=userRegistrationFeignImpl.otpResend(id); 
		log.info("resend otp api response:  "+response);
		log.info("exit from resend otp controller");
		return response;
	}
	
	public void captcha(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException{
		response.setContentType("image/jpg");
		int iTotalChars = 6;
		int iHeight = 40;
		int iWidth = 150; 
		Font fntStyle1 = new Font("Arial", Font.BOLD, 30);
		Random randChars = new Random();
		String sImageCode = (Long.toString(Math.abs(randChars.nextLong()), 36)).substring(0, iTotalChars);
		BufferedImage biImage = new BufferedImage(iWidth, iHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2dImage = (Graphics2D) biImage.getGraphics();
		int iCircle = 15;
		for (int i = 0; i < iCircle; i++) {
			g2dImage.setColor(new Color(randChars.nextInt(255), randChars.nextInt(255), randChars.nextInt(255)));
		}
		g2dImage.setFont(fntStyle1);
		for (int i = 0; i < iTotalChars; i++) {
			g2dImage.setColor(new Color(randChars.nextInt(255), randChars.nextInt(255), randChars.nextInt(255)));
			if (i % 2 == 0) {
				g2dImage.drawString(sImageCode.substring(i, i + 1), 25 * i, 24);
			} else {
				g2dImage.drawString(sImageCode.substring(i, i + 1), 25 * i, 35);
			}
		}
		OutputStream osImage = response.getOutputStream();
		ImageIO.write(biImage, "jpeg", osImage);
		g2dImage.dispose();
		session.setAttribute("captcha_security", sImageCode);
	}
}
