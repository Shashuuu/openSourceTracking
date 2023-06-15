package com.ase.osts.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ase.osts.dao.ProjectClientDao;
import com.ase.osts.domain.ProjectClientDomain;
import com.ase.osts.domain.ProjectClientRoleDomain;

@Service
@RestController
@CrossOrigin
public class ProjectClientController {

	@Autowired
	public ProjectClientDao projectClientDao;
	
	@RequestMapping(path = "/registerProjectClient", method = RequestMethod.POST)
	public ResponseEntity<String> registerProjectClient(@RequestBody ProjectClientDomain projectClient) throws IOException, Exception {
		String response = null;
		try {
			response = projectClientDao.registerProjectClient(projectClient);
		} catch(Exception e) {
			return new ResponseEntity<String>("Internal Server Occurred", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(response.equals("ProjectClient is registered Successfully")) {
			
		return new ResponseEntity<String>(response, HttpStatus.OK);
		}
		else if(response.equals("ProjectClient already exists") || response.equals("Invalid Project Role")) {
			return new ResponseEntity<String>(response, HttpStatus.NOT_FOUND);
		} 
		else {
			return new ResponseEntity<String>(response, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(path = "/getProjectClient", method = RequestMethod.GET)
	public ResponseEntity<ProjectClientDomain> getProjectClient(@RequestBody ProjectClientDomain projectClient) throws IOException, Exception {
		ProjectClientDomain projectClientDomain = null;
		try {
			projectClientDomain = projectClientDao.getProjectClient(projectClient);
		} catch(Exception e) {
			throw new Exception("Exception occurred while getting Project Client details");
		}
		if (projectClientDomain != null) {
			return new ResponseEntity<ProjectClientDomain>(projectClientDomain, HttpStatus.OK);	
		}
		else {
			return new ResponseEntity<ProjectClientDomain>(projectClientDomain, HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(path = "/updateProjectClient", method = RequestMethod.PUT)
	public ResponseEntity<String> updateProjectClient(@RequestBody ProjectClientDomain projectClient) throws IOException, Exception {
		String message = null;
		try {
			message = projectClientDao.updateProjectClient(projectClient);
		} catch(Exception e) {
			System.out.println("Exception occurred while updating Project client details");
			return new ResponseEntity<String>("Inconvenience error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(message.equals("project client is updated successfully"))
		return new ResponseEntity<String>(message, HttpStatus.OK);
		else
			return new ResponseEntity<String>(message, HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(path = "/deleteProjectClient", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteProjectClient(@RequestBody ProjectClientDomain projectClient) throws IOException, Exception {
		String message = null;
		try {
			message = projectClientDao.deleteProjectClient(projectClient);
		} catch(Exception e) {
			throw new Exception("Exception occurred while deleting project client details");
		}
		if(message.equals("ProjectClient account deletion successful"))
		return new ResponseEntity<String>(message, HttpStatus.OK);
		else
			return new ResponseEntity<String>(message, HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(path = "/validateProjectClient", method = RequestMethod.POST)
	public ResponseEntity<?> validateProjectClient(@RequestBody ProjectClientDomain projectClient) throws IOException, Exception {
		ProjectClientDomain clientDomain = null;
		try {
			clientDomain = projectClientDao.validateProjectClient(projectClient);
		} catch(Exception e) {
			System.out.println("Exception occurred while validating a project client");
			return new ResponseEntity<>("Exception Occurred", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(clientDomain != null) {
		return new ResponseEntity<>(clientDomain, HttpStatus.OK);
		}
		
		else {
			return new ResponseEntity<>("Invalid Credentials", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(path = "/addProjectClientRole", method = RequestMethod.POST)
	public ResponseEntity<String> addProjectClientRole(@RequestBody ProjectClientRoleDomain projectClientRole) throws IOException, Exception {
		String response = null;
		try {
			response = projectClientDao.addProjectClientRole(projectClientRole);
		} catch(Exception e) {
			return new ResponseEntity<String>("Internal Server Occurred", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(response.equals("ProjectClient role is added Successfully")) {
			
			System.out.println ("success");
		return new ResponseEntity<String>(response, HttpStatus.OK);
		}
		else if(response.equals("ProjectClient Role already exists")) {
			System.out.println("not found");
			return new ResponseEntity<String>(response, HttpStatus.NOT_FOUND);
		}
		else {
			System.out.println("Failed");
			return new ResponseEntity<String>(response, HttpStatus.BAD_REQUEST);
		}
	}
	
}
