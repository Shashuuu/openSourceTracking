package com.ase.osts.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ase.osts.domain.ProjectClientRoleDomain;


public interface ProjectClientRoleRepository extends CrudRepository<ProjectClientRoleDomain, Long>{
		 
	 @Query("select p from ProjectClientRoleDomain p where p.projectClientRoleName = :projectClientRoleName")
	 public ProjectClientRoleDomain getProjectClientRoleByName(@Param("projectClientRoleName") String projectClientRoleName);
	 
}