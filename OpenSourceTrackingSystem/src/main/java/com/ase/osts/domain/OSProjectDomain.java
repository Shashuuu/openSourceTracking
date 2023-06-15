 package com.ase.osts.domain;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "open_source_project")
public class OSProjectDomain implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "IDGENERATOR", sequenceName = "OSPROJECT_IDSEQUENCE", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IDGENERATOR")
	@Column(name = "open_source_project_id")
    private Long openSourceProjectId;
	
	@Column(name = "open_source_project_name")
	private String openSourceProjectName;
	
	@Column(name = "open_source_project_license")
	private String openSourceProjectLicense;
	
	@Column(name = "open_source_project_description")
	private String openSourceProjectDescription;
	
	@Column(name = "open_source_project_url")
	private String openSourceProjectURL;
	
	/*@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "open_source_project_state", referencedColumnName = "open_source_project_state_id")
	private OSProjectStateDomain openSourceProjectState;*/
	
	@Column(name = "open_source_project_state")
	private String openSourceProjectState;
	
	@Column(name = "open_source_project_create_date")
	private Date openSourceProjectCreateDate;
	
	@Column(name = "open_source_project_update_date")
	private Date openSourceProjectUpdateDate;
	
	@Column(name = "open_source_project_created_by")
	private Long openSourceProjectCreatedBy;
	
	@Column(name = "open_source_project_updated_by")
	private Long openSourceProjectUpdatedBy;

	public Long getOpenSourceProjectId() {
		return openSourceProjectId;
	}

	public void setOpenSourceProjectId(Long openSourceProjectId) {
		this.openSourceProjectId = openSourceProjectId;
	}

	public String getOpenSourceProjectName() {
		return openSourceProjectName;
	}

	public void setOpenSourceProjectName(String openSourceProjectName) {
		this.openSourceProjectName = openSourceProjectName;
	}

	public String getOpenSourceProjectLicense() {
		return openSourceProjectLicense;
	}

	public void setOpenSourceProjectLicense(String openSourceProjectLicense) {
		this.openSourceProjectLicense = openSourceProjectLicense;
	}

	public String getOpenSourceProjectDescription() {
		return openSourceProjectDescription;
	}

	public void setOpenSourceProjectDescription(String openSourceProjectDescription) {
		this.openSourceProjectDescription = openSourceProjectDescription;
	}

	public String getOpenSourceProjectURL() {
		return openSourceProjectURL;
	}

	public void setOpenSourceProjectURL(String openSourceProjectURL) {
		this.openSourceProjectURL = openSourceProjectURL;
	}

	public Date getOpenSourceProjectCreateDate() {
		return openSourceProjectCreateDate;
	}

	public void setOpenSourceProjectCreateDate(Date openSourceProjectCreateDate) {
		this.openSourceProjectCreateDate = openSourceProjectCreateDate;
	}

	public Date getOpenSourceProjectUpdateDate() {
		return openSourceProjectUpdateDate;
	}

	public void setOpenSourceProjectUpdateDate(Date openSourceProjectUpdateDate) {
		this.openSourceProjectUpdateDate = openSourceProjectUpdateDate;
	}

	public Long getOpenSourceProjectCreatedBy() {
		return openSourceProjectCreatedBy;
	}

	public void setOpenSourceProjectCreatedBy(Long openSourceProjectCreatedBy) {
		this.openSourceProjectCreatedBy = openSourceProjectCreatedBy;
	}

	public Long getOpenSourceProjectUpdatedBy() {
		return openSourceProjectUpdatedBy;
	}

	public void setOpenSourceProjectUpdatedBy(Long openSourceProjectUpdatedBy) {
		this.openSourceProjectUpdatedBy = openSourceProjectUpdatedBy;
	}

	public String getOpenSourceProjectState() {
		return openSourceProjectState;
	}

	public void setOpenSourceProjectState(String openSourceProjectState) {
		this.openSourceProjectState = openSourceProjectState;
	}

}
