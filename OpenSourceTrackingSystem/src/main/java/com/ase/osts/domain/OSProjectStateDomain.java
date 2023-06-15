package com.ase.osts.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "open_source_project_state")
public class OSProjectStateDomain {
	
	@Id
	@SequenceGenerator(name = "IDGENERATOR", sequenceName = "OSPROJECT_STATE_IDSEQUENCE", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IDGENERATOR")
	@Column(name = "open_source_project_state_id")
    private Long openSourceProjectStateId;
	
	@Column(name = "open_source_project_state_name")
	private String openSourceProjectStateName;
	
	@Column(name = "open_source_project_state_description")
	private String openSourceProjecStatetDescription;
	
	/*@OneToOne(mappedBy = "openSourceProjectState")
    private OSProjectDomain osProjectDomain;*/
	
	public Long getOpenSourceProjectStateId() {
		return openSourceProjectStateId;
	}

	public void setOpenSourceProjectStateId(Long openSourceProjectStateId) {
		this.openSourceProjectStateId = openSourceProjectStateId;
	}

	public String getOpenSourceProjectStateName() {
		return openSourceProjectStateName;
	}

	public void setOpenSourceProjectStateName(String openSourceProjectStateName) {
		this.openSourceProjectStateName = openSourceProjectStateName;
	}

	public String getOpenSourceProjecStatetDescription() {
		return openSourceProjecStatetDescription;
	}

	public void setOpenSourceProjecStatetDescription(String openSourceProjecStatetDescription) {
		this.openSourceProjecStatetDescription = openSourceProjecStatetDescription;
	}

}
