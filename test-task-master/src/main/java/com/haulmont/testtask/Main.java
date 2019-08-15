package com.haulmont.testtask;

import com.haulmont.testtask.dao.DoctorDAO;
import com.haulmont.testtask.dao.FormulaDAO;
import com.haulmont.testtask.dao.PatientDAO;
import com.haulmont.testtask.dao.PriorityDAO;
import com.haulmont.testtask.entity.DoctorEntity;
import com.haulmont.testtask.entity.FormulaEntity;
import com.haulmont.testtask.entity.PatientEntity;
import com.haulmont.testtask.entity.PriorityEntity;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, ParseException {
/*
       DoctorDAO doctorDAO = new DoctorDAO();
        DoctorEntity doctor1 = new DoctorEntity();
        doctor1.setDoctor_id(9);
        doctor1.setSurname("Богданова");
        doctor1.setName("Алена");
        doctor1.setPatronymic("Петровна");
        doctor1.setSpecialization("Кардиолог");
        //doctorDAO.insert(doctor1);
        //doctorDAO.update(doctor1);
        //doctorDAO.delete(doctor1);

        List<DoctorEntity> doctors = doctorDAO.getAll();

        for (int i = 0; i < doctors.size(); i++) {
            doctor1 = doctors.get(i);
            System.out.println(doctor1.getDoctor_id() + ") " +
                    doctor1.getSurname() + " " +
                    doctor1.getName() + " " +
                    doctor1.getPatronymic() + " - " +
                    doctor1.getSpecialization());
        }

*/
/*
        PatientEntity patient1 =  new PatientEntity();
        PatientDAO patientDAO = new PatientDAO();

        patient1.setPatient_id(1);
        patient1.setSurname("fsdfsd");
        patient1.setName("pppppp");
        patient1.setPatronymic("fsdfsd");
        patient1.setPhone_number("25525225");

        //patientDAO.insert(patient1);
        //patientDAO.update(patient1);
        //patientDAO.delete(patient1);


        List<PatientEntity> patients = patientDAO.getAll();

        for (int i=0 ; i< patients.size(); i++){
            patient1 = patients.get(i);
            System.out.println(patient1.getPatient_id()+") " + patient1.getSurname() + " " +
            patient1.getName() + " " + patient1.getPatronymic() + " - " + patient1.getPhone_number() );
        }

*/
/*
        PriorityEntity priority = new PriorityEntity();
        PriorityDAO priorityDAO = new PriorityDAO();

        priority.setPriority_id(4);
        priority.setPriority_name("aaa");
        //priorityDAO.insert(priority);
        //priorityDAO.update(priority);
        //priorityDAO.delete(priority);
        List<PriorityEntity> prioritys = priorityDAO.getAll();

        for (int i=0 ; i< prioritys.size(); i++){
            priority = prioritys.get(i);
            System.out.println(priority.getPriority_id() + ") " + priority.getPriority_name());
        }
*/
/*
        //string containing date
        String strDate = "2019-02-01";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        java.util.Date date = sdf.parse(strDate);
        java.sql.Date sqlDate = new Date(date.getTime());

        FormulaEntity formula1 = new FormulaEntity();
        formula1.setDoctor_id(2);
        formula1.setPatient_id(3);
        formula1.setPriority_id(2);
        formula1.setDescription("aaa");
        formula1.setCreation_date(sqlDate);
        formula1.setValidity(1);

        FormulaDAO formulaDAO = new FormulaDAO();

        //formulaDAO.insert(formula1);
        //formulaDAO.update(formula1);
        //formulaDAO.delete(formula1);
        PatientDAO patientDAO = new PatientDAO();
        DoctorDAO doctorDAO = new DoctorDAO();
        PriorityDAO priorityDAO = new PriorityDAO();
        List<FormulaEntity> formulaEntities = formulaDAO.getAll();
        for (int i=0 ; i< formulaEntities.size(); i++){
            formula1 = formulaEntities.get(i);
            System.out.println( doctorDAO.getById(formula1.getDoctor_id()).getSurname() +" - " +
                    patientDAO.getById(formula1.getPatient_id()).getSurname()+ " - " +
                    priorityDAO.getById(formula1.getPriority_id()).getPriority_name()+" - " +
                    formula1.getDescription() + " - "+ formula1.getCreation_date() + formula1.getValidity());
        }
        */

    }

}
