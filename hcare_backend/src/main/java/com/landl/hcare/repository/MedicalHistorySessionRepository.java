package com.landl.hcare.repository;

import com.landl.hcare.entity.MedicalHistorySession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalHistorySessionRepository extends JpaRepository<MedicalHistorySession, Long>{
}
