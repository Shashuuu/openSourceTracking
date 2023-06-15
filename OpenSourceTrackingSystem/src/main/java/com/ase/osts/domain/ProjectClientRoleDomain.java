package com.ase.osts.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "project_client_role")
public class ProjectClientRoleDomain {

	@Id
	@SequenceGenerator(name = "IDGENERATOR", sequenceName = "PROJECT_CLIENT_ROLE_ID_SEQUENCE", initialValue = 1) 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IDGENERATOR")
	@Column(name = "project_client_role_id")
    private Long projectClientRoleId;
	
	@Column(name = "project_client_role_name")
	private String projectClientRoleName;
	
	@Column(name = "project_client_role_desc")
    private String projectClientRoleDescription;
	
	@Column(name = "created_by")
    private String createdBy;
	
   /* @OneToOne(mappedBy = "projectClientRole")
    private ProjectClientDomain projectClientDomain;*/

	public Long getProjectClientRoleId() {
		return projectClientRoleId;
	}

	public void setProjectClientRoleId(Long projectClientRoleId) {
		this.projectClientRoleId = projectClientRoleId;
	}

	public String getProjectClientRoleName() {
		return projectClientRoleName;
	}

	public void setProjectClientRoleName(String projectClientRoleName) {
		this.projectClientRoleName = projectClientRoleName;
	}

	public String getProjectClientRoleDescription() {
		return projectClientRoleDescription;
	}

	public void setProjectClientRoleDescription(String projectClientRoleDescription) {
		this.projectClientRoleDescription = projectClientRoleDescription;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

		
}
