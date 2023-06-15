package com.ase.osts.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ase.osts.domain.OSProjectStateDomain;

@Repository
@Transactional
public interface OSProjectStateRepository extends CrudRepository<OSProjectStateDomain, Long> {
	@Query("select osp from OSProjectStateDomain osp where osp.openSourceProjectStateName = :openSourceProjectStateName")
	public OSProjectStateDomain getOSProjectStateByName(@Param("openSourceProjectStateName") String openSourceProjectStateName);

	@Query(value = "select * from open_source_project c where c.open_source_project_state_id = ?1", nativeQuery = true)
	public OSProjectStateDomain getOSProjecStatetById(Long openSourceProjectStateId);
	 
	@Query("select osps from OSProjectStateDomain osps")
	public ArrayList<OSProjectStateDomain> getAllOSProjectStates();

	@Modifying
    @Transactional
	@Query(value = "DELETE FROM open_source_project_state where open_source_project_state_name = :openSourceProjectStateName", nativeQuery = true)
	public void deleteOSProjectStateBytName(@Param("openSourceProjectStateName") String openSourceProjectStateName);
	
}