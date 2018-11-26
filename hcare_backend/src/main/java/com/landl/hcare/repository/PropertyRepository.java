package com.landl.hcare.repository;

import com.landl.hcare.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long>{
    List<Property> findByPropertyCode(String property);
}
