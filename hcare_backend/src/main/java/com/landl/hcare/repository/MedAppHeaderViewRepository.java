package com.landl.hcare.repository;

import com.landl.hcare.entity.MedAppHeaderView;
import com.landl.hcare.entity.MedicalAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MedAppHeaderViewRepository extends JpaRepository<MedAppHeaderView, Long>{
    List<MedAppHeaderView> findByLmatLanguageAndLmasLanguageOrderByMaDateAppointmentDesc(String lmatLanguage,String lmasLanguage);
}
