package com.landl.hcare.service;


import com.landl.hcare.entity.RecentSurgery;

import java.util.List;
import java.util.Optional;

public interface RecentSurgeryService {

    public RecentSurgery save(RecentSurgery recentSurgery);

    public List<RecentSurgery> findAll();

    public Optional<RecentSurgery> findById(Long recentSurgeryId);

}
