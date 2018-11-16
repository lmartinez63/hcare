package com.landl.hcare.service;

import com.landl.hcare.entity.EmailTemplate;
import com.landl.hcare.repository.EmailTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmailTemplateServiceImpl implements EmailTemplateService{

    @Autowired
    EmailTemplateRepository emailTemplateRepository;

    public EmailTemplate save(EmailTemplate emailTemplate){
        return emailTemplateRepository.save(emailTemplate);
    }

    public List<EmailTemplate> findAll(){
        return emailTemplateRepository.findAll();
    }

    public Optional<EmailTemplate> findById(Long emailTemplateId){
        return emailTemplateRepository.findById(emailTemplateId);
    }
}
