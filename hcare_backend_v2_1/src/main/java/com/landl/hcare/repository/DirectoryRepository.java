package com.landl.hcare.repository;

import com.landl.hcare.entity.Directory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectoryRepository extends JpaRepository<Directory, Long>{
    Directory findByEntityName(String entityName);
}
