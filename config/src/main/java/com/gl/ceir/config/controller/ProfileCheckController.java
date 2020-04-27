package com.gl.ceir.config.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileCheckController {

	private static final Logger logger = LogManager.getLogger(ProfileCheckController.class);

	
	@Autowired
    Environment environment;

	@GetMapping("/checkProfile")
    public String[] checkProfile() {

        String[] activeProfiles = environment.getActiveProfiles();      // it will return String Array of all active profile.  
        
        for(String profile:activeProfiles) {
            logger.info(profile);
        }
        
        return activeProfiles;
    }
}
