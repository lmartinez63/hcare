package com.landl.hcare.repository;

import com.landl.hcare.entity.ClinicHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicHistoryRepository extends JpaRepository<ClinicHistory, Long>{
}
