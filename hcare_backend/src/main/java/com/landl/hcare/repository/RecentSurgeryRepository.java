package com.landl.hcare.repository;

import com.landl.hcare.entity.RecentSurgery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecentSurgeryRepository extends JpaRepository<RecentSurgery, Long>{
}
