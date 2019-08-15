package com.haulmont.testtask.entity;

public class DoctorEntity {
    private long doctor_id;
    private String name;
    private String surname;
    private String patronymic;
    private String specialization;

    public DoctorEntity(){}
    public DoctorEntity(long doctor_id, String name, String surname, String patronymic, String specialization) {
        this.doctor_id = doctor_id;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.specialization = specialization;
    }
    public DoctorEntity(String name, String surname, String patronymic, String specialization) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return "DoctorEntity{" +
                "doctor_id=" + doctor_id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", specialization='" + specialization + '\'' +
                '}';
    }

    public long getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(long doctor_id) {
        this.doctor_id = doctor_id;
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

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
