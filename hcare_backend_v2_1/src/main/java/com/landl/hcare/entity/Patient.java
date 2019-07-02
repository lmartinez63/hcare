package com.landl.hcare.entity;

import com.google.gson.JsonArray;
import com.landl.hcare.common.UtilityTools;
import com.landl.hcare.model.AuditModel;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Entity
@Audited
@Table(name="patient")
public class Patient extends AuditModel {
    @Id
    @GeneratedValue(generator = "patient_generator")
    @SequenceGenerator(
            name = "patient_generator",
            sequenceName = "patient_sequence",
            initialValue = 2500,
            allocationSize = 1
    )
    private Long id;

    @Column(name="history_code")
    private Long historyCode;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Transient
    @NotAudited
    private String fullName;

    @Column(name="document_number",unique=true)
    private String documentNumber;

    @Column(name="document_type")
    private Integer documentType;

    @Column(name="birthday")
    private Date birthday;

    @Column(name="birthplace_province")
    private Integer birthplaceProvince;

    @Column(name="birthplace_department")
    private Integer birthplaceDepartment;

    @Column(name="birthplace_country")
    private Integer birthplaceCountry;

    @Column(name="insurance_type")
    private Integer insuranceType;

    @Column(name="insurance_number")
    private String insuranceNumber;

    @Column(name="breed")
    private Integer breed;

    @Column(name="occupation")
    private String occupation;

    @Column(name="degree_instruction")
    private Integer degreeInstruction;

    @Column(name="person_in_charge")
    private String personInCharge;

    @Column(name="phonenumber_person_in_charge")
    private String phonenumberPersonInCharge;

    @Column(name="religion")
    private String religion;

    @Column(name="email_address")
    private String emailAddress;

    @Column(name="address")
    private String address;

    @Column(name="address_district")
    private Integer addressDistrict;

    @Column(name="civil_status")
    private Integer civilStatus;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="cel_phone_number")
    private String celPhoneNumber;

    @Column(name="gender")
    private Integer gender;

    @Transient
    @NotAudited
    private Directory directory;

    @Transient
    @NotAudited
    private List<Map> files;

    public Patient() {
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

    public Integer getDocumentType() {
        return documentType;
    }

    public void setDocumentType(Integer documentType) {
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

    public Integer getCivilStatus() {
        return civilStatus;
    }

    public void setCivilStatus(Integer civilStatus) {
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

    public Integer getAddressDistrict() {
        return addressDistrict;
    }

    public void setAddressDistrict(Integer addressDistrict) {
        this.addressDistrict = addressDistrict;
    }

    public Long getHistoryCode() {
        return historyCode;
    }

    public void setHistoryCode(Long historyCode) {
        this.historyCode = historyCode;
    }

    public String getFullName() {
        return UtilityTools.isNull(this.firstName) + " " + UtilityTools.isNull(this.lastName);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Directory getDirectory() {
        return directory;
    }

    public void setDirectory(Directory directory) {
        this.directory = directory;
    }

    public List<Map> getFiles() {
        return files;
    }

    public void setFiles(List<Map> files) {
        this.files = files;
    }

    public Integer getBirthplaceProvince() {
        return birthplaceProvince;
    }

    public void setBirthplaceProvince(Integer birthplaceProvince) {
        this.birthplaceProvince = birthplaceProvince;
    }

    public Integer getBirthplaceDepartment() {
        return birthplaceDepartment;
    }

    public void setBirthplaceDepartment(Integer birthplaceDepartment) {
        this.birthplaceDepartment = birthplaceDepartment;
    }

    public Integer getBirthplaceCountry() {
        return birthplaceCountry;
    }

    public void setBirthplaceCountry(Integer birthplaceCountry) {
        this.birthplaceCountry = birthplaceCountry;
    }

    public Integer getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(Integer insuranceType) {
        this.insuranceType = insuranceType;
    }

    public String getInsuranceNumber() {
        return insuranceNumber;
    }

    public void setInsuranceNumber(String insuranceNumber) {
        this.insuranceNumber = insuranceNumber;
    }

    public Integer getBreed() {
        return breed;
    }

    public void setBreed(Integer breed) {
        this.breed = breed;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public Integer getDegreeInstruction() {
        return degreeInstruction;
    }

    public void setDegreeInstruction(Integer degreeInstruction) {
        this.degreeInstruction = degreeInstruction;
    }

    public String getPersonInCharge() {
        return personInCharge;
    }

    public void setPersonInCharge(String personInCharge) {
        this.personInCharge = personInCharge;
    }

    public String getPhonenumberPersonInCharge() {
        return phonenumberPersonInCharge;
    }

    public void setPhonenumberPersonInCharge(String phonenumberPersonInCharge) {
        this.phonenumberPersonInCharge = phonenumberPersonInCharge;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }
}