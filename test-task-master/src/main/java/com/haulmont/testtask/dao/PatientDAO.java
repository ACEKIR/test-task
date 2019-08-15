package com.haulmont.testtask.dao;

import com.haulmont.testtask.ConnectJDBC;
import com.haulmont.testtask.entity.PatientEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {

    public List<PatientEntity> getAll() {

        List<PatientEntity> patients = new ArrayList<>();

        String sql = "SELECT patient_id, surname, name, patronymic, phone_number FROM patients";
        try (Connection connection = ConnectJDBC.getConnect()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                PatientEntity patientEntity = new PatientEntity();
                patientEntity.setPatient_id(resultSet.getLong("patient_id"));
                patientEntity.setSurname(resultSet.getString("surname"));
                patientEntity.setName(resultSet.getString("name"));
                patientEntity.setPatronymic(resultSet.getString("patronymic"));
                patientEntity.setPhone_number(resultSet.getString("phone_number"));
                patients.add(patientEntity);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }

    public PatientEntity getById(Long patient_id){
        PreparedStatement preparedStatement = null;
        PatientEntity patientEntity = new PatientEntity();
        String sql ="SELECT * FROM patients WHERE patient_id = ?";
        try (Connection connection = ConnectJDBC.getConnect()){
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, patient_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            patientEntity.setPatient_id(resultSet.getLong("patient_id"));
            patientEntity.setSurname(resultSet.getString("surname"));
            patientEntity.setName(resultSet.getString("name"));
            patientEntity.setPatronymic(resultSet.getString("patronymic"));
            patientEntity.setPhone_number(resultSet.getString("phone_number"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patientEntity;
    }

    public void insert(PatientEntity patientEntity) {
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO patients(patient_id, surname, name, patronymic, phone_number) VALUES (?,?,?,?,?)";
        try (Connection connection = ConnectJDBC.getConnect()) {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, patientEntity.getPatient_id());
            preparedStatement.setString(2, patientEntity.getSurname());
            preparedStatement.setString(3, patientEntity.getName());
            preparedStatement.setString(4, patientEntity.getPatronymic());
            preparedStatement.setString(5, patientEntity.getPhone_number());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(PatientEntity patientEntity) {
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE patients SET surname=?, name=?, patronymic=?, phone_number=? WHERE patient_id=?";

        try (Connection connection = ConnectJDBC.getConnect()) {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, patientEntity.getSurname());
            preparedStatement.setString(2, patientEntity.getName());
            preparedStatement.setString(3, patientEntity.getPatronymic());
            preparedStatement.setString(4, patientEntity.getPhone_number());
            preparedStatement.setLong(5, patientEntity.getPatient_id());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(PatientEntity patientEntity) {
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM patients WHERE patient_id = ?";

        try (Connection connection = ConnectJDBC.getConnect()) {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, patientEntity.getPatient_id());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
