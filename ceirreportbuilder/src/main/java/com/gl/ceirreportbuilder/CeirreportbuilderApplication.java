package com.gl.ceirreportbuilder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.gl.ceirreportbuilder.controller.ReportMainController;

@SpringBootApplication(scanBasePackages= {"com.gl.ceirreportbuilder"})
public class CeirreportbuilderApplication {
	
	public static void main(String[] args) {
		ApplicationContext context = null;
		try {
			context = SpringApplication.run(CeirreportbuilderApplication.class, args);
			ReportMainController reportMainController = (ReportMainController) context.getBean("reportMainController");
			reportMainController.createScheduleReports(context);
//			context = null;
			/*int exitCode = SpringApplication.exit(context, new ExitCodeGenerator() {
				@Override
				public int getExitCode() {
			        // return the error code
			        return 0;
			    }
			});
			System.exit(exitCode);*/
		}catch( Exception ex) {
			ex.printStackTrace();
		}finally {
			if( context != null )
				context = null;
		}
	}
	
}
