package com.landl.hcare.repository;

import com.landl.hcare.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long>{
    @Query(value = "select p.* from menu p \n" +
            "inner join role_page_section_field rpsf on rpsf.page_id = p.id\n" +
            "inner join user_roles ur on ur.role_id = rpsf.role_id\n" +
            "inner join user_profile up on up.id = ur.user_id where p.page_code = :pageCode and up.username = :username", nativeQuery = true)
    List<Menu> findMenuListByUserCode(String pageCode);
}
