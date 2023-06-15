package com.ase.osts.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ase.osts.domain.OSProjectDomain;

@Repository
@Transactional
public interface OSProjectRepository extends CrudRepository<OSProjectDomain, Long> {
	@Query("select osp from OSProjectDomain osp where osp.openSourceProjectName = :openSourceProjectName")
	public OSProjectDomain getOSProjectByName(@Param("openSourceProjectName") String openSourceProjectName);

	@Query(value = "select * from open_source_project c where c.open_source_project_id = ?1", nativeQuery = true)
	public OSProjectDomain getOSProjectById(Long openSourceProjectId);
	 
	@Query("select osp from OSProjectDomain osp")
	public ArrayList<OSProjectDomain> getAllOSProjects();

	@Modifying
    @Transactional
	@Query(value = "DELETE FROM open_source_project where open_source_project_name = :openSourceProjectName", nativeQuery = true)
	public void deleteOSProjectByProjectName(@Param("openSourceProjectName") String openSourceProjectName);
	
}