package com.ase.osts.dao;

import java.util.ArrayList;

import com.ase.osts.domain.OSProjectDomain;
import com.ase.osts.domain.OSProjectStateDomain;

public interface OSProjectDao {
	public String addOSProject(OSProjectDomain osProjectDetails);
	public ArrayList<OSProjectDomain> getOSProjectsList();
	public String deleteOSProject(OSProjectDomain osProjectDetails);
	public String updateOSProject(OSProjectDomain osProjectDetails);
	public String addOSProjectState(OSProjectStateDomain osProjectState);

}
