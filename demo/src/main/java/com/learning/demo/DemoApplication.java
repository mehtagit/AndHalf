package com.learning.demo;

import org.dmfs.rfc5545.DateTime;
import org.dmfs.rfc5545.recur.InvalidRecurrenceRuleException;
import org.dmfs.rfc5545.recur.RecurrenceRule;
//
//@SpringBootApplication
//@Component
//@EnableAutoConfiguration
//@EnableCaching
public class DemoApplication {

	
	public static void main(String[] args) throws InvalidRecurrenceRuleException {
	//	ApplicationContext context = SpringApplication.run(DemoApplication.class, args);
		
		
		RecurrenceRule rule = new RecurrenceRule("FREQ=WEEKLY;BYDAY=MO;UNTIL=20190531T000000Z");
		DateTime dateTime = rule.getUntil();
		System.out.println(dateTime);
	}

}
