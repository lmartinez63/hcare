package com.landl.hcare.service;

import com.landl.hcare.entity.RecentSurgery;
import com.landl.hcare.repository.RecentSurgeryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecentSurgeryServiceImpl implements RecentSurgeryService{

    @Autowired
    RecentSurgeryRepository recentSurgeryRepository;

    public RecentSurgery save(RecentSurgery recentSurgery){
        return recentSurgeryRepository.save(recentSurgery);
    }

    public List<RecentSurgery> findAll(){
        return recentSurgeryRepository.findAll();
    }

    public Optional<RecentSurgery> findById(Long recentSurgeryId){
        return recentSurgeryRepository.findById(recentSurgeryId);
    }
}
