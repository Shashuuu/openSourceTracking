package com.ase.osts.dao;

import com.ase.osts.domain.ProjectClientDomain;
import com.ase.osts.domain.ProjectClientRoleDomain;

public interface ProjectClientDao {

	public String registerProjectClient(ProjectClientDomain projectClient);
	public ProjectClientDomain getProjectClient(ProjectClientDomain projectClient);
	public String updateProjectClient(ProjectClientDomain projectClient);
	public String deleteProjectClient(ProjectClientDomain projectClient);
	public ProjectClientDomain validateProjectClient(ProjectClientDomain projectClient);
	public String addProjectClientRole(ProjectClientRoleDomain projectClientRole);
}
