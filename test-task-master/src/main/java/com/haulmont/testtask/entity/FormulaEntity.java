package com.haulmont.testtask.entity;

import java.sql.Date;

public class FormulaEntity {
    private long doctor_id;
    private long patient_id;
    private long priority_id;
    private String description;
    private Date creation_date;
    private int validity;

    public FormulaEntity(long doctor_id, long patient_id, long priority_id, String description, Date creation_date, int validity) {
        this.doctor_id = doctor_id;
        this.patient_id = patient_id;
        this.priority_id = priority_id;
        this.description = description;
        this.creation_date = creation_date;
        this.validity = validity;
    }

    public FormulaEntity() {
    }

    public long getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(long doctor_id) {
        this.doctor_id = doctor_id;
    }

    public long getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(long patient_id) {
        this.patient_id = patient_id;
    }

    public long getPriority_id() {
        return priority_id;
    }

    public void setPriority_id(long priority_id) {
        this.priority_id = priority_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }

    public int getValidity() {
        return validity;
    }

    public void setValidity(int validity) {
        this.validity = validity;
    }
}
