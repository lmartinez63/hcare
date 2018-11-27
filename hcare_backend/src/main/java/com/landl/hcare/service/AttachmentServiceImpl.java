package com.landl.hcare.service;

import com.landl.hcare.entity.Attachment;
import com.landl.hcare.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttachmentServiceImpl implements AttachmentService{

    @Autowired
    AttachmentRepository attachmentRepository;

    public Attachment save(Attachment attachment){
        return attachmentRepository.save(attachment);
    }

    public List<Attachment> findAll(){
        return attachmentRepository.findAll();
    }

    public Optional<Attachment> findById(Long attachmentId){
        return attachmentRepository.findById(attachmentId);
    }

    public List<Attachment> findByEntityAndEntityId(String entity, Long entityId){
        return  attachmentRepository.findByEntityAndEntityId(entity,entityId);
    }
}
