package com.haulmont.testtask.dao;

import com.haulmont.testtask.ConnectJDBC;
import com.haulmont.testtask.entity.PriorityEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PriorityDAO {
    public List<PriorityEntity> getAll() {
        List<PriorityEntity> prioritys = new ArrayList<>();

        String sql = "SELECT priority_id, priority_name FROM priority";
        try (Connection connection = ConnectJDBC.getConnect()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                PriorityEntity priorityEntity = new PriorityEntity();
                priorityEntity.setPriority_id(resultSet.getLong("priority_id"));
                priorityEntity.setPriority_name(resultSet.getString("priority_name"));
                prioritys.add(priorityEntity);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prioritys;
    }

    public void insert(PriorityEntity priority) {
        String sql = "INSERT INTO priority(priority_id, priority_name) values(?,?)";
        PreparedStatement preparedStatement = null;
        try (Connection connection = ConnectJDBC.getConnect()) {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, priority.getPriority_id());
            preparedStatement.setString(2, priority.getPriority_name());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void update(PriorityEntity priority){
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE priority SET priority_name = ? WHERE priority_id = ?";

        try (Connection connection = ConnectJDBC.getConnect()) {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,priority.getPriority_name());
            preparedStatement.setLong(2,priority.getPriority_id());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void delete(PriorityEntity priority) {
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM priority WHERE priority_id = ?";

        try (Connection connection = ConnectJDBC.getConnect()) {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,priority.getPriority_id());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PriorityEntity getById(long priority_id) {
        PreparedStatement preparedStatement = null;
        PriorityEntity priorityEntity = new PriorityEntity();
        String sql ="SELECT * FROM priority WHERE priority_id = ?";
        try (Connection connection = ConnectJDBC.getConnect()){
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, priority_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            priorityEntity.setPriority_id(resultSet.getLong("priority_id"));
            priorityEntity.setPriority_name(resultSet.getString("priority_name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return priorityEntity;
    }
}
