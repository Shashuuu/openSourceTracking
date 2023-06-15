package com.ase.osts.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "project_client")
public class ProjectClientDomain implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "IDGENERATOR", sequenceName = "PROJECT_CLIENT_ID_SEQUENCE", initialValue = 1) 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IDGENERATOR")
	@Column(name = "project_client_id")
    private Long projectClientId;
	
	/*@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_client_role", referencedColumnName = "project_client_role_id")
	private ProjectClientRoleDomain projectClientRole;*/
	
	@Column(name = "project_client_role")
	private String projectClientRole;
	
	@Column(name = "project_client_name")
    private String projectClientName;
	
	@Column(name = "project_client_email")
    private String projectClientEmail;
	
	@Column(name = "project_client_password")
    private String projectClientPassword;

	public Long getProjectClientId() {
		return projectClientId;
	}

	public void setProjectClientId(Long projectClientId) {
		this.projectClientId = projectClientId;
	}

	public String getProjectClientName() {
		return projectClientName;
	}

	public void setProjectClientName(String projectClientName) {
		this.projectClientName = projectClientName;
	}

	public String getProjectClientEmail() {
		return projectClientEmail;
	}

	public void setProjectClientEmail(String projectClientEmail) {
		this.projectClientEmail = projectClientEmail;
	}

	public String getProjectClientPassword() {
		return projectClientPassword;
	}

	public void setProjectClientPassword(String projectClientPassword) {
		this.projectClientPassword = projectClientPassword;
	}

	public String getProjectClientRole() {
		return projectClientRole;
	}

	public void setProjectClientRole(String projectClientRole) {
		this.projectClientRole = projectClientRole;
	}
	
}