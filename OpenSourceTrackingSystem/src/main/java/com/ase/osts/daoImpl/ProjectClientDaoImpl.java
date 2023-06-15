package com.ase.osts.daoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ase.osts.dao.ProjectClientDao;
import com.ase.osts.domain.ProjectClientDomain;
import com.ase.osts.domain.ProjectClientRoleDomain;
import com.ase.osts.repository.ProjectClientRepository;
import com.ase.osts.repository.ProjectClientRoleRepository;


@Service
public class ProjectClientDaoImpl implements ProjectClientDao {

	@Autowired
    private ProjectClientRepository projectClientRepo;
	
	@Autowired
    private ProjectClientRoleRepository projectClientRoleRepo;
	
	
	
	@Override
	public String registerProjectClient(ProjectClientDomain projectClient) {
		if(projectClient!= null && projectClient.getProjectClientEmail()!=null && projectClient.getProjectClientRole()!= null) {
			try {
				ProjectClientDomain projectClientDomain = projectClientRepo.getProjectClientByEmailId(projectClient.getProjectClientEmail());
				ProjectClientRoleDomain projectClientRoleDomain = projectClientRoleRepo.getProjectClientRoleByName(projectClient.getProjectClientRole());

				if(projectClientDomain != null) {
					return "ProjectClient already exists";
				}
				else {
					if (projectClientRoleDomain == null) {
						return "Invalid Project Role";
					}
					projectClientRepo.save(projectClient);
					return "ProjectClient is registered Successfully";
				}
				
			} catch( Exception e) {
				throw e;
			
			}
		}
		return "Client details are empty";
	}

	@Override
	public ProjectClientDomain getProjectClient(ProjectClientDomain projectClient) {
		if(projectClient!= null && projectClient.getProjectClientEmail()!= null) {
			try {
				System.out.println("Project Client email is"+projectClient.getProjectClientEmail());
				ProjectClientDomain projectClientDomain = projectClientRepo.getProjectClientByEmailId(projectClient.getProjectClientEmail());
				if (projectClientDomain!= null) {
					return projectClientDomain;
				}
			} catch( Exception e) {
				throw e;
			
			}	
		}
		
		return null;
	}

	@Override
	public String updateProjectClient(ProjectClientDomain projectClient) {
		ProjectClientDomain u = projectClientRepo.getProjectClientByEmailId(projectClient.getProjectClientEmail());
		if(projectClient!= null && u!= null) {
			try {
				System.out.println("Project Client email is"+projectClient.getProjectClientEmail());
				if(projectClient.getProjectClientPassword()!= null)
				u.setProjectClientPassword(projectClient.getProjectClientPassword());
				if(projectClient.getProjectClientRole()!= null)
					u.setProjectClientRole(projectClient.getProjectClientRole());
				projectClientRepo.save(u);
				return "project client updated successfully";
			} catch( Exception e) {
				throw e;
			
			}	
		}
		return " Failed to update client details";
	}

	@Override
	public String deleteProjectClient(ProjectClientDomain projectClient) {
		if(projectClient!= null) {
			try {
				System.out.println("ProjectClientId is"+projectClient.getProjectClientId());
				projectClientRepo.deleteProjectClientByClientId(projectClient.getProjectClientId());
				return "Project client deletion successful";
			} catch( Exception e) {
				System.out.println("Exception occured during project client deletion:"+e);
				throw e;
			
			}
		}
		
		return "Failed to delete project client account";
	}

	@Override
	public ProjectClientDomain validateProjectClient(ProjectClientDomain projectClient) {
		if (projectClient != null) {
			try {
				ProjectClientDomain validProjectClient = projectClientRepo.getProjectClientByEmailIdAndPassword(
						projectClient.getProjectClientEmail(), projectClient.getProjectClientPassword());

				if (validProjectClient != null) {
					System.out.println("Project client credentials matched");
					return validProjectClient;
				}

				else {
					System.out.println("Invalid Credentials");
					return null;

				}
			} catch (Exception e) {
				System.out.println("Exception occured during project client login" + e);
				throw e;
			}
		}
		return null;
	}
	
	@Override
	public String addProjectClientRole(ProjectClientRoleDomain projectClientRole) {
		if(projectClientRole!= null && projectClientRole.getProjectClientRoleName()!=null) {
			try {
				ProjectClientRoleDomain projectClientRoleDomain = projectClientRoleRepo.getProjectClientRoleByName(projectClientRole.getProjectClientRoleName());

				if(projectClientRoleDomain != null) {
					System.out.println("Project Client Role is already existing");
					return "ProjectClient Role already exists";
				}
				else {
					projectClientRoleRepo.save(projectClientRole);
					return "Project Client Role is added Successfully";
				}
				
			} catch( Exception e) {
				throw e;
			
			}
		}
		return "project Client role details are empty";
	}

}
