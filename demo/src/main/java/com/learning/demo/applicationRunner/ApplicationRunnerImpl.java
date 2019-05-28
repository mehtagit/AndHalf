package com.learning.demo.applicationRunner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

/*Application Runner
Application Runner is an interface used to execute the code after the 
Spring Boot application started. 

An option argument is prefixed with --. For example, --format=xml is a valid option argument / getOptionNames(). 
If the argument value is not prefixed with --, it is a plain argument /getNonOptionArgs().
* */
@Service
public class ApplicationRunnerImpl implements ApplicationRunner {

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("Application Runner " + args.getOptionNames()); // --format=JMLK
		System.out.println("Application Runner " + args.getNonOptionArgs()); // java helo.class Apps * it
		System.out.println("Application Runner " + args.getOptionValues("format"));
		System.out.println("Application Runner " + args.containsOption("format"));
	}

}
