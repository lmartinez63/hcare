package com.landl.hcare.service;


import com.landl.hcare.common.UtilityTools;
import com.landl.hcare.entity.Attachment;
import com.landl.hcare.entity.Email;
import com.landl.hcare.entity.EmailTemplate;
import com.landl.hcare.repository.EmailRepository;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.mail.internet.MimeMessage;

@Component
public class EmailServiceImpl implements EmailService {

    @Autowired
    public JavaMailSender emailSender;

    @Autowired
    public Configuration freemarkerConfig;

    @Autowired
    public UtilityTools utilityTools;

    @Autowired
    EmailRepository emailRepository;

    public void sendSimpleMessage(Email email) throws Exception{
        MimeMessage message = emailSender.createMimeMessage();
        // pass 'true' to the constructor to create a multipart message
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(email.getSendTo());
        helper.setSubject(email.getSubject());
        helper.setText(email.getBody(), true);
        if(email.getAttachmentList() != null){
            for (Attachment attachment : email.getAttachmentList()) {
                helper.addAttachment(attachment.getFileName(), new ByteArrayResource(attachment.getContent())); }
        }

        emailSender.send(message);
    }

    public void buildEmailFromEmailTemplate(Email email) throws Exception {
        EmailTemplate emailTemplate = email.getEmailTemplate();
        Map<String, Object> dataSource = email.getDataSource();
        if (emailTemplate.getSendTo().contains("{{")) {
            email.setSendTo(UtilityTools.getFormatedValue(emailTemplate.getSendTo(), dataSource));
        }
        if (emailTemplate.getSendFrom().contains("{{")) {
            email.setSendFrom(UtilityTools.getFormatedValue(emailTemplate.getSendFrom(), dataSource));
        }
        if (emailTemplate.getSubject().contains("{{")) {
            email.setSubject(UtilityTools.getFormatedValue(emailTemplate.getSubject(), dataSource));
        }

        // Using a subfolder such as /templates here
        freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates");

        Map<String, Object> datasource = email.getDataSource();
        Template t = freemarkerConfig.getTemplate(emailTemplate.getTemplateFileName());
        String textTemplate = FreeMarkerTemplateUtils.processTemplateIntoString(t, datasource);
        email.setBody(textTemplate);
    }


    /* Repository Methods*/

    public Email save(Email email){
        return emailRepository.save(email);
    }

    public List<Email> findAll(){
        return emailRepository.findAll();
    }
    public List<Email> findByStatus(Integer status){
        return emailRepository.findByStatus(status);
    }

    public Optional<Email> findById(Long emailId){
        return emailRepository.findById(emailId);
    }
}