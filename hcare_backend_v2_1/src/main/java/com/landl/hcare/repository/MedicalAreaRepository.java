package com.landl.hcare.repository;

import com.landl.hcare.entity.MedicalArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalAreaRepository extends JpaRepository<MedicalArea, Long>{
}
