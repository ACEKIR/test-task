package com.haulmont.testtask.entity;

public class PatientEntity {
    @Override
    public String toString() {
        return "PatientEntity{" +
                "surname='" + surname + '\'' +
                '}';
    }

    private long patient_id;
    private String name;
    private String surname;
    private String patronymic;
    private String phone_number;

    public PatientEntity(long patient_id, String name, String surname, String patronymic, String phone_number) {
        this.patient_id = patient_id;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.phone_number = phone_number;
    }

    public PatientEntity() {

    }

    public PatientEntity(String name, String surname, String patronymic, String phone_number) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.phone_number = phone_number;
    }

    public long getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(long patient_id) {
        this.patient_id = patient_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
