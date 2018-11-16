package com.landl.hcare.service;


import com.landl.hcare.entity.Email;
import com.landl.hcare.entity.EmailTemplate;

import java.util.List;
import java.util.Optional;

public interface EmailTemplateService {

    public EmailTemplate save(EmailTemplate emailTemplate);

    public List<EmailTemplate> findAll();

    public Optional<EmailTemplate> findById(Long emailTemplateId);

}
