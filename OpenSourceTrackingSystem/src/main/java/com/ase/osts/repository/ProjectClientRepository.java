package com.ase.osts.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ase.osts.domain.ProjectClientDomain;

public interface ProjectClientRepository extends CrudRepository<ProjectClientDomain, Long> {

	@Query("select c from ProjectClientDomain c where c.projectClientEmail = :projectClientEmail")
	public ProjectClientDomain getProjectClientByEmailId(@Param("projectClientEmail") String projectClientEmail);

	@Query("select c from ProjectClientDomain c where c.projectClientEmail = :projectClientEmail and c.projectClientPassword = :projectClientPassword")
	public ProjectClientDomain getProjectClientByEmailIdAndPassword(
			@Param("projectClientEmail") String projectClientEmail,
			@Param("projectClientPassword") String projectClientPassword);

	@Query(value = "select * from project_client c where c.project_client_id = ?1", nativeQuery = true)
	public ProjectClientDomain getProjectClientByProjectClientId(String projectClientId);

	public ProjectClientDomain findByProjectClientPassword(String password);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM project_client where project_client_id = :projectClientId", nativeQuery = true)
	public void deleteProjectClientByClientId(@Param("projectClientId") Long projectClientId);

}