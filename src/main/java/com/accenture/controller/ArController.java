package com.accenture.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.binding.CitizenApp;
import com.accenture.service.ArService;

@RestController
public class ArController {
	
	private ArService arService;
	
	public ArController(ArService arService)
	{
		this.arService = arService;
	}
	
    @PostMapping("/app")
	public ResponseEntity<String> CreateCitizenApp(@RequestBody CitizenApp ctznApp)
	{
		Integer appId = arService.createApplication(ctznApp);
		
		if(appId > 0)
		{
			return new ResponseEntity<>("App onboarding done successfully !! Your App ID is : "+appId, HttpStatus.OK);
		
		}else {
			return new ResponseEntity<>("Invalid SSN, Sorry Unable to process your request", HttpStatus.BAD_REQUEST);
		}
	}
}
