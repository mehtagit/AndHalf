
package org.gl.ceir.CeirPannelCode;

import java.util.ArrayList;

import org.gl.ceir.CeirPannelCode.config.Model.DeviceSnapShot;
import org.gl.ceir.CeirPannelCode.config.Model.DuplicateImeiMsisdn;
import org.gl.ceir.CeirPannelCode.config.Model.ImeiMsisdnIdentity;
import org.gl.ceir.CeirPannelCode.config.Model.Constants.ImeiStatus;
import org.gl.ceir.CeirPannelCode.config.Service.DeviceSnapShotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;



/*@EnableAutoConfiguration*/

/*
 * @ComponentScan(basePackages ="org.gl.ceir.CeirPannelCode", excludeFilters =
 * {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes =
 * {WebSecurityConfig.class, UserValidator.class, UserService.class,
 * BCryptPasswordEncoder.class} )})
 */
/*@EnableAutoConfiguration
@SpringBootConfiguration
@ComponentScan(basePackageClasses = "org.gl.ceir.CeirPannelCode")*/


@EnableFeignClients
@EnableAutoConfiguration
@SpringBootConfiguration 
@ComponentScan(basePackages ="org.gl.ceir.CeirPannelCode")
public class App extends SpringBootServletInitializer
{
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(App.class);
	} 
	@Autowired
	private DeviceSnapShotService service;

	private static DeviceSnapShot convertRequestToDeviceSnapShot() {
		DeviceSnapShot deviceSnapShot = new DeviceSnapShot();
		deviceSnapShot.setImei(898989L);
		// deviceSnapShot.setFailedRuleId(request.getFailRule().getId().toString());
		// deviceSnapShot.setFailedRuleName(request.getFailRule().getName());
		deviceSnapShot.setDuplicateImeiMsisdns(new ArrayList<>());
		deviceSnapShot.getDuplicateImeiMsisdns().add(convertToDuplicateImeiMsisdn());
		deviceSnapShot.getDuplicateImeiMsisdns().get(0).setDeviceSnapShot(deviceSnapShot);
		return deviceSnapShot;
	}

	private static DuplicateImeiMsisdn convertToDuplicateImeiMsisdn() {
		DuplicateImeiMsisdn duplicateImeiMsisdn = new DuplicateImeiMsisdn();
		duplicateImeiMsisdn.setImeiMsisdnIdentity(new ImeiMsisdnIdentity(898989L, 9090909L));
		duplicateImeiMsisdn.setFileName("file");

		duplicateImeiMsisdn.setImeiStatus(ImeiStatus.AUTO_REGULARIZED);
		duplicateImeiMsisdn.setImsi(3232L);
		duplicateImeiMsisdn.setRegulizedByUser(Boolean.FALSE);
		return duplicateImeiMsisdn;

	}
	

	/*
	 * public static void main( String[] args ) { System.out.println( "inside main"
	 * ); SpringApplication.run(App.class, args);
	 * 
	 * }
	 */
	
	
}