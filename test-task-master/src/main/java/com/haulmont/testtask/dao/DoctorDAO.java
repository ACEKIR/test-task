package com.haulmont.testtask.dao;

import com.haulmont.testtask.ConnectJDBC;
import com.haulmont.testtask.entity.DoctorEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {

    public DoctorDAO() {
    }

    public List<DoctorEntity> getAll() {

        List<DoctorEntity> doctors = new ArrayList<>();

        String sql = "SELECT doctor_id, surname, name, patronymic," +
                "specialization FROM doctors";
        try (Connection connection = ConnectJDBC.getConnect()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                DoctorEntity doctorEntity = new DoctorEntity();
                doctorEntity.setDoctor_id(resultSet.getInt("doctor_id"));
                doctorEntity.setName(resultSet.getString("name"));
                doctorEntity.setSurname(resultSet.getString("surname"));
                doctorEntity.setPatronymic(resultSet.getString("patronymic"));
                doctorEntity.setSpecialization(resultSet.getString("specialization"));
                doctors.add(doctorEntity);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctors;
    }

    public DoctorEntity getById(Long doctor_id){
        PreparedStatement preparedStatement = null;
        DoctorEntity doctorEntity = new DoctorEntity();
        String sql ="SELECT * FROM doctors WHERE doctor_id = ?";
        try (Connection connection = ConnectJDBC.getConnect()){
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, doctor_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            doctorEntity.setDoctor_id(resultSet.getLong("doctor_id"));
            doctorEntity.setSurname(resultSet.getString("surname"));
            doctorEntity.setName(resultSet.getString("name"));
            doctorEntity.setPatronymic(resultSet.getString("patronymic"));
            doctorEntity.setSpecialization(resultSet.getString("specialization"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctorEntity;
    }

    public void insert(DoctorEntity doctorEntity) {

        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO doctors(doctor_id, surname, name, patronymic, specialization) VALUES(?,?,?,?,?)";
        try (Connection connection = ConnectJDBC.getConnect()) {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, doctorEntity.getDoctor_id());
            preparedStatement.setString(2, doctorEntity.getSurname());
            preparedStatement.setString(3, doctorEntity.getName());
            preparedStatement.setString(4, doctorEntity.getPatronymic());
            preparedStatement.setString(5, doctorEntity.getSpecialization());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(DoctorEntity doctorEntity) {
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE doctors SET surname=?,name=?, " +
                "patronymic=?,specialization=? WHERE doctor_id=?";

        try (Connection connection = ConnectJDBC.getConnect()) {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, doctorEntity.getSurname());
            preparedStatement.setString(2, doctorEntity.getName());
            preparedStatement.setString(3, doctorEntity.getPatronymic());
            preparedStatement.setString(4, doctorEntity.getSpecialization());
            preparedStatement.setLong(5, doctorEntity.getDoctor_id());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void delete(DoctorEntity doctorEntity) {
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM doctors WHERE doctor_id = ?";

        try (Connection connection = ConnectJDBC.getConnect()) {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, doctorEntity.getDoctor_id());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
