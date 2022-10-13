package com.accenture.service;

import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.accenture.binding.CitizenApp;
import com.accenture.entity.CitizenAppEntity;
import com.accenture.repository.CitizenAppRepository;

@Service
public class ArServiceImpl implements ArService{

	private CitizenAppRepository ctznAppRepo;
	
	public ArServiceImpl(CitizenAppRepository ctznAppRepo)
	{
		this.ctznAppRepo = ctznAppRepo;	
	}
	
	@Override
	public Integer createApplication(CitizenApp app) {
		
		String endpointUrl = "https://ssa-web-api.herokuapp.com/ssn/{ssn}";
		
		RestTemplate rt = new RestTemplate();
		
		ResponseEntity<String> resEntity = rt.getForEntity(endpointUrl, String.class , app.getSsn());
		
		String stateName = resEntity.getBody();
		
		if("New Jersey".equals(stateName))
		{
			CitizenAppEntity entity = new CitizenAppEntity();
			BeanUtils.copyProperties(app, entity);
			entity.setStateName(stateName);
			CitizenAppEntity save = ctznAppRepo.save(entity);
			
			return save.getAppId();
		}
		
		return 0;
	}

}
