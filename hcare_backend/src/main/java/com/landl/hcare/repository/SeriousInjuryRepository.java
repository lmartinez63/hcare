package com.landl.hcare.repository;

import com.landl.hcare.entity.SeriousInjury;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeriousInjuryRepository extends JpaRepository<SeriousInjury, Long>{
}
