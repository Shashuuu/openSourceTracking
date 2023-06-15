package com.ase.osts.daoImpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ase.osts.dao.OSProjectDao;
import com.ase.osts.domain.OSProjectDomain;
import com.ase.osts.domain.OSProjectStateDomain;
import com.ase.osts.repository.OSProjectRepository;
import com.ase.osts.repository.OSProjectStateRepository;
import com.ase.osts.util.Constants;

@Service
public class OSProjectDaoImpl implements OSProjectDao {

	@Autowired
	private OSProjectRepository osProjectRepo;
	
	@Autowired
	private OSProjectStateRepository osProjectStateRepo;

	@Override
	public String addOSProjectState(OSProjectStateDomain osProjectStateDetails) {
		if (osProjectStateDetails != null && osProjectStateDetails.getOpenSourceProjectStateName() != null) {
			try {
				OSProjectStateDomain projectStateName = osProjectStateRepo
						.getOSProjectStateByName(osProjectStateDetails.getOpenSourceProjectStateName());
				if (projectStateName != null) {
					System.out.println("Open Source Project state already exists");
					return Constants.PROJ_STATE_ALREADY_EXISTS;
				} else {
					osProjectStateRepo.save(osProjectStateDetails);
					return "Open Source Project state is created successfully";
				}

			} catch (Exception e) {
				throw e;
			}
		}
		return "Open Source Project state details are empty";
	}
	
	@Override
	public String addOSProject(OSProjectDomain osProjectDetails) {
		if (osProjectDetails != null && osProjectDetails.getOpenSourceProjectName() != null) {
			try {
				OSProjectDomain projectName = osProjectRepo
						.getOSProjectByName(osProjectDetails.getOpenSourceProjectName());
				if (projectName != null) {
					System.out.println(Constants.PROJ_ALREADY_EXISTS);
					return Constants.PROJ_ALREADY_EXISTS;
				} else {
					osProjectDetails.setOpenSourceProjectCreateDate(new Date(Calendar.getInstance().getTime().getTime()));
					osProjectDetails.setOpenSourceProjectUpdateDate(new Date(Calendar.getInstance().getTime().getTime()));
					if (osProjectDetails.getOpenSourceProjectState() == null) {
						osProjectDetails.setOpenSourceProjectState("In Process");
					}
					osProjectRepo.save(osProjectDetails);
					return Constants.PROJ_REGISTRATION_SUCCESS;
				}

			} catch (Exception e) {
				throw e;
			}
		}
		return "Open Source Project details are empty";
	}

	@Override
	public ArrayList<OSProjectDomain> getOSProjectsList() {
		try {
			ArrayList<OSProjectDomain> projectsList = (ArrayList<OSProjectDomain>) osProjectRepo.getAllOSProjects();
			return projectsList;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public String deleteOSProject(OSProjectDomain osProjectDetails) {
		OSProjectDomain b = osProjectRepo.getOSProjectById(osProjectDetails.getOpenSourceProjectId());
		if (b != null && osProjectDetails != null) {
			try {
				System.out.println("Project Name is" + osProjectDetails.getOpenSourceProjectName());
				osProjectRepo.deleteOSProjectByProjectName(osProjectDetails.getOpenSourceProjectName());
			} catch (Exception e) {
				throw e;
			}
			return Constants.PROJ_DELETED_STATUS;
		}
		return "Failed to delete open source project";
	}

	@Override
	public String updateOSProject(OSProjectDomain osProjectDetails) {
		if (osProjectDetails != null) {
			OSProjectDomain osProject = osProjectRepo.getOSProjectById(osProjectDetails.getOpenSourceProjectId());
			try {
				if (osProject != null) {

					if (osProjectDetails.getOpenSourceProjectDescription() != null) {
						osProject.setOpenSourceProjectDescription(osProjectDetails.getOpenSourceProjectDescription());
					}
					if (osProjectDetails.getOpenSourceProjectLicense() != null) {
						osProject.setOpenSourceProjectLicense(osProjectDetails.getOpenSourceProjectLicense());
					}
					if (osProjectDetails.getOpenSourceProjectName() != null) {
						osProject.setOpenSourceProjectName(osProjectDetails.getOpenSourceProjectName());
					}
					if (osProjectDetails.getOpenSourceProjectState() != null) {
						osProject.setOpenSourceProjectState(osProjectDetails.getOpenSourceProjectState());
					}
					if (osProjectDetails.getOpenSourceProjectUpdatedBy() != null) {
						osProject.setOpenSourceProjectUpdatedBy(osProjectDetails.getOpenSourceProjectUpdatedBy());
					}
					if (osProjectDetails.getOpenSourceProjectURL() != null) {
						osProject.setOpenSourceProjectURL(osProjectDetails.getOpenSourceProjectURL());
					}
					osProject.setOpenSourceProjectUpdateDate(new Date(Calendar.getInstance().getTime().getTime()));
					osProjectRepo.save(osProject);
					return Constants.PROJ_UPDATED_SUCCESS;
				}
				return "Failed to update open source project as it is not in DB";
			} catch (Exception e) {
				throw e;

			}
		}
		return "Failed to update open source project";
	}

}
