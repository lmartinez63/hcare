package com.landl.hcare.entity;

import com.landl.hcare.common.UtilityTools;
import com.landl.hcare.model.AuditModel;

import javax.persistence.*;

@Entity
@Table(name="property")
public class Property extends AuditModel {
    @Id
    @GeneratedValue(generator = "property_generator")
    @SequenceGenerator(
            name = "property_generator",
            sequenceName = "property_sequence",
            initialValue = 1000,
            allocationSize = 1
    )
    private Long id;

    @Column(name="property_code")
    private String propertyCode;

    @Column(name="property_value")
    private String propertyValue;

    public Property() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPropertyCode() {
        return propertyCode;
    }

    public void setPropertyCode(String propertyCode) {
        this.propertyCode = propertyCode;
    }

    public String getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }
}
