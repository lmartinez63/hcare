package com.landl.hcare.repository;

import com.landl.hcare.entity.Page;
import com.landl.hcare.entity.PageButton;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PageButtonRepository extends JpaRepository<PageButton, Long>{
}
