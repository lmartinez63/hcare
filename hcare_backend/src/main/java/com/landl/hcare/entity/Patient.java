package com.landl.hcare.entity;

import com.landl.hcare.model.AuditModel;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name="patient")
public class Patient extends AuditModel {
    @Id
    @GeneratedValue(generator = "patient_generator")
    @SequenceGenerator(
            name = "patient_generator",
            sequenceName = "patient_sequence",
            initialValue = 1000
    )
    private Long id;

    @Column(name="patient_code")
    private String patientCode;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="document_number")
    private String documentNumber;

    @Column(name="document_type")
    private String documentType;

    @Column(name="birthday")
    private Date birthday;

    @Column(name="email_address")
    private String emailAddress;

    @Column(name="address")
    private String address;

    @Column(name="civil_status")
    private String civilStatus;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="cel_phone_number")
    private String celPhoneNumber;


    public Patient() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatientCode() {
        return patientCode;
    }

    public void setPatientCode(String patientCode) {
        this.patientCode = patientCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getCivilStatus() {
        return civilStatus;
    }

    public void setCivilStatus(String civilStatus) {
        this.civilStatus = civilStatus;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCelPhoneNumber() {
        return celPhoneNumber;
    }

    public void setCelPhoneNumber(String celPhoneNumber) {
        this.celPhoneNumber = celPhoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}