package com.landl.hcare.entity;

import com.landl.hcare.model.AuditModel;

import javax.persistence.*;
import java.util.Date;
import java.util.Map;

@Entity
@Table(name="attachment")
public class Attachment extends AuditModel {
    @Id
    @GeneratedValue(generator = "attachment_generator")
    @SequenceGenerator(
            name = "attachment_generator",
            sequenceName = "attachment_sequence",
            initialValue = 1000
    )
    private Long id;

    @Column(name="content")
    private byte[] content;

    @Column(name="email_id")
    private Long emailId;

    @Column(name="file_name")
    private String fileName;

    public Attachment() {
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public Long getEmailId() {
        return emailId;
    }

    public void setEmailId(Long emailId) {
        this.emailId = emailId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
