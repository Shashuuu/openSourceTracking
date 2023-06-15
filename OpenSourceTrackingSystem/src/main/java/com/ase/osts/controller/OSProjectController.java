package com.ase.osts.controller;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ase.osts.dao.OSProjectDao;
import com.ase.osts.domain.OSProjectDomain;
import com.ase.osts.domain.OSProjectStateDomain;
import com.ase.osts.util.Constants;


@Service
@RestController
@CrossOrigin
public class OSProjectController {

	@Autowired
	public OSProjectDao osProjectDao;
	
	@RequestMapping(path = "/addOSProject", method = RequestMethod.POST)
	public ResponseEntity<String> createOSProject(@RequestBody OSProjectDomain osProject) throws IOException, Exception {
		String message = null;
		try {
			message = osProjectDao.addOSProject(osProject);
		} catch(Exception e) {
			return new ResponseEntity<String>("Internal Server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(message.equals(Constants.PROJ_REGISTRATION_SUCCESS))
			return new ResponseEntity<String>(message, HttpStatus.OK);
		else if(message.equals(Constants.PROJ_ALREADY_EXISTS))
			return new ResponseEntity<String>(message, HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(path = "/getOSProjectsList", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<OSProjectDomain>> getOSProjects() throws IOException, Exception {
		ArrayList<OSProjectDomain> osProjectsList;
		try {
			osProjectsList = osProjectDao.getOSProjectsList();
		} catch(Exception e) {
			throw new Exception("Error occurred while getting open source project");
		}
		return new ResponseEntity<ArrayList<OSProjectDomain>>(osProjectsList, HttpStatus.OK);
	}
	
	@RequestMapping(path = "/updateOSProject", method = RequestMethod.PUT)
	public ResponseEntity<String> updateOSProject(@RequestBody OSProjectDomain osProject) throws IOException, Exception {
		String message = null;
		try {
			message = osProjectDao.updateOSProject(osProject);
		} catch(Exception e) {
			return new ResponseEntity<String>("Internal Server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(message.equals(Constants.PROJ_UPDATED_SUCCESS))
		return new ResponseEntity<String>(message, HttpStatus.OK);
		else
			return new ResponseEntity<String>(message, HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(path = "/deleteOSProject", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteOSProject(@RequestBody OSProjectDomain osProject) throws IOException, Exception {
		String message = null;
		try {
			message = osProjectDao.deleteOSProject(osProject);
		} catch(Exception e) {
			return new ResponseEntity<String>("Internal Server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(message.equals(Constants.PROJ_DELETED_STATUS))
		return new ResponseEntity<String>(message, HttpStatus.OK);
		else
			return new ResponseEntity<String>(message, HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(path = "/addOSProjectState", method = RequestMethod.POST)
	public ResponseEntity<String> addOSProjectState(@RequestBody OSProjectStateDomain osProjectState) throws IOException, Exception {
		String message = null;
		try {
			message = osProjectDao.addOSProjectState(osProjectState);
		} catch(Exception e) {
			return new ResponseEntity<String>("Internal Server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(message.equals(Constants.PROJ_STATE_SUCCESS))
			return new ResponseEntity<String>(message, HttpStatus.OK);
		else if(message.equals(Constants.PROJ_STATE_ALREADY_EXISTS))
			return new ResponseEntity<String>(message, HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
	}
}
