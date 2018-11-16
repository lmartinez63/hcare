package com.landl.hcare.entity;

import com.landl.hcare.model.AuditModel;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Entity
@Table(name="email")
public class Email  extends AuditModel {
    @Id
    @GeneratedValue(generator = "email_generator")
    @SequenceGenerator(
            name = "email_generator",
            sequenceName = "email_sequence",
            initialValue = 1000
    )
    private Long id;

    @Column(name="send_to")
    private String sendTo;

    @Column(name="send_from")
    private String sendFrom;

    @Column(name="subject")
    private String subject;

    @Column(name="body")
    private String body;

    @Column(name="status")
    private Integer status;

    @Column(name="date_sent")
    private Date dateSent;

    @Column(name="email_template_id")
    private Long emailTemplateId;

    @Transient
    private EmailTemplate emailTemplate;

    @Transient
    private Map<String, Object> dataSource;

    @Transient
    private List<Attachment> attachmentList;

    public Email() {
    }

    public String getSendTo() {
        return sendTo;
    }

    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }

    public String getSendFrom() {
        return sendFrom;
    }

    public void setSendFrom(String sendFrom) {
        this.sendFrom = sendFrom;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getDateSent() {
        return dateSent;
    }

    public void setDateSent(Date dateSent) {
        this.dateSent = dateSent;
    }

    public Long getEmailTemplateId() {
        return emailTemplateId;
    }

    public void setEmailTemplateId(Long emailTemplateId) {
        this.emailTemplateId = emailTemplateId;
    }

    public EmailTemplate getEmailTemplate() {
        return emailTemplate;
    }

    public void setEmailTemplate(EmailTemplate emailTemplate) {
        this.emailTemplate = emailTemplate;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Map<String, Object> getDataSource() {
        return dataSource;
    }

    public void setDataSource(Map<String, Object> dataSource) {
        this.dataSource = dataSource;
    }

    public void putAttachment(Attachment attachment) {
        this.attachmentList.add(attachment);
    }

    public void setAttachmentList(List<Attachment> attachmentList) {
        this.attachmentList = attachmentList;
    }

    public List<Attachment> getAttachmentList() {
        return attachmentList;
    }
}
