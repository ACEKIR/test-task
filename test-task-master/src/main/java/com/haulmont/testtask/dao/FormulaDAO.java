package com.haulmont.testtask.dao;

import com.haulmont.testtask.ConnectJDBC;
import com.haulmont.testtask.entity.FormulaEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FormulaDAO {
    public List<FormulaEntity> getAll(){
        List<FormulaEntity> formulaEntities = new ArrayList<>();
        String sql="SELECT doctor_id, " +
                "patient_id,priority_id, description," +
                "creation_date, validity FROM formula";
        try (Connection connection = ConnectJDBC.getConnect()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                FormulaEntity formulaEntity = new FormulaEntity();
                formulaEntity.setDoctor_id(resultSet.getLong("doctor_id"));
                formulaEntity.setPatient_id(resultSet.getLong("patient_id"));
                formulaEntity.setPriority_id(resultSet.getLong("priority_id"));
                formulaEntity.setDescription(resultSet.getString("description"));
                formulaEntity.setCreation_date(resultSet.getDate("creation_date"));
                formulaEntity.setValidity(resultSet.getInt("validity"));
                formulaEntities.add(formulaEntity);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return formulaEntities;
    }
    public FormulaEntity getById(Long doctor_id, Long patient_id, Long priority_id){
        PreparedStatement preparedStatement = null;
        FormulaEntity formulaEntity = new FormulaEntity();
        String sql ="SELECT * FROM formula WHERE doctor_id=? and patient_id=? and priority_id = ?";
        try (Connection connection = ConnectJDBC.getConnect()){
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, doctor_id);
            preparedStatement.setLong(2, patient_id);
            preparedStatement.setLong(3, priority_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            formulaEntity.setDescription(resultSet.getString("description"));
            formulaEntity.setCreation_date(resultSet.getDate("creation_date"));
            formulaEntity.setValidity(resultSet.getInt("validity"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return formulaEntity;
    }
    public void insert(FormulaEntity formulaEntity){
        String sql = "INSERT INTO formula(doctor_id, patient_id,priority_id, description, creation_date, validity) VALUES (?,?,?,?,?,?) ";
        PreparedStatement preparedStatement = null;
        try (Connection connection = ConnectJDBC.getConnect()) {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,formulaEntity.getDoctor_id());
            preparedStatement.setLong(2,formulaEntity.getPatient_id());
            preparedStatement.setLong(3,formulaEntity.getPriority_id());
            preparedStatement.setString(4,formulaEntity.getDescription());
            preparedStatement.setDate(5,formulaEntity.getCreation_date());
            preparedStatement.setInt(6,formulaEntity.getValidity());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void update(FormulaEntity formulaEntity){
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE formula SET description = ? ,creation_date = ? , validity = ?  WHERE doctor_id = ? and patient_id = ? and priority_id = ?";
        try (Connection connection = ConnectJDBC.getConnect()) {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,formulaEntity.getDescription());
            preparedStatement.setDate(2,formulaEntity.getCreation_date());
            preparedStatement.setInt(3,formulaEntity.getValidity());
            preparedStatement.setLong(4,formulaEntity.getDoctor_id());
            preparedStatement.setLong(5,formulaEntity.getPatient_id());
            preparedStatement.setLong(6,formulaEntity.getPriority_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void delete(FormulaEntity formulaEntity){
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM formula WHERE doctor_id = ? and patient_id = ? and priority_id = ?";

        try (Connection connection = ConnectJDBC.getConnect()) {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,formulaEntity.getDoctor_id());
            preparedStatement.setLong(2,formulaEntity.getPatient_id());
            preparedStatement.setLong(3,formulaEntity.getPriority_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
