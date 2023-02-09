package sa.timetracking.jdbc.dao.classes;

import logger.classes.Logger;
import logger.config.MyProperties;
import sa.timetracking.jdbc.dao.interfaces.IUserDao;
import sa.timetracking.jdbc.dto.User;
import sa.timetracking.jdbc.mapper.Mapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserDAO implements IUserDao {


    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(MyProperties.getDatabaseUrl(),
                MyProperties.getDatabaseUser(), MyProperties.getDatabasePassword());
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM timesheet.user")) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                users.add(Mapper.toUser(resultSet));
            }
            Logger.info("User List created.");

        } catch (SQLException e) {
            Logger.error(Arrays.toString(e.getStackTrace()) + e.getMessage());
            throw new RuntimeException();
        }
        return users;
    }

    @Override
    public User getById(Integer id) {
        try (Connection connection = DriverManager.getConnection(MyProperties.getDatabaseUrl(),
                MyProperties.getDatabaseUser(), MyProperties.getDatabasePassword());
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM timesheet.user WHERE user.id=?")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Logger.info("User: " + resultSet.getString(2) + " selected from database.");
                return Mapper.toUser(resultSet);
            } else {
                Logger.info("User with id: " + id + " not found in database.");
                return null;
            }
        } catch (SQLException e) {
            Logger.error(Arrays.toString(e.getStackTrace()) + e.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public boolean update(User object) {
        try (Connection connection = DriverManager.getConnection(MyProperties.getDatabaseUrl(),
                MyProperties.getDatabaseUser(), MyProperties.getDatabasePassword());
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE timesheet.user set user.name=?,"
                     + "user.password=?, user.isAdmin=?, user.employeeID=? WHERE user.id=? ")) {

            preparedStatement.setString(1, object.getName());
            preparedStatement.setString(2, object.getPassword());
            preparedStatement.setBoolean(3, object.isAdmin());
            preparedStatement.setInt(4, object.getEmployeeID());
            preparedStatement.setInt(5, object.getId());

            if (preparedStatement.executeUpdate() > 0) {
                Logger.info("User: " + object.getName() + " updated in database.");
                return true;
            } else {
                Logger.info("User with id: " + object.getId() + " not found in database.");
                return false;
            }

        } catch (SQLException e) {
            Logger.error(Arrays.toString(e.getStackTrace()) + e.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public boolean delete(User object) {
        try (Connection connection = DriverManager.getConnection(MyProperties.getDatabaseUrl(),
                MyProperties.getDatabaseUser(), MyProperties.getDatabasePassword());
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM timesheet.user WHERE user.id=?")) {

            preparedStatement.setInt(1, object.getId());

            if (preparedStatement.executeUpdate() > 0) {
                Logger.info("User: " + object.getName() + " deleted from database.");
                return true;
            } else {
                Logger.info("User with id: " + object.getId() + " not found in database.");
                return false;
            }

        } catch (SQLException e) {
            Logger.error(Arrays.toString(e.getStackTrace()) + e.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public boolean create(User object) {
        try (Connection connection = DriverManager.getConnection(MyProperties.getDatabaseUrl(),
                MyProperties.getDatabaseUser(), MyProperties.getDatabasePassword());
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO timesheet.user(name, password, isAdmin, employeeID)" +
                     " values(?, ?, ?, ?)")) {

            preparedStatement.setString(1, object.getName());
            preparedStatement.setString(2, object.getPassword());
            preparedStatement.setBoolean(3, object.isAdmin());
            preparedStatement.setInt(4, object.getEmployeeID());


            if (preparedStatement.executeUpdate() > 0) {
                Logger.info("User: " + object.getName() + " added in database.");
                return true;
            } else {
                Logger.info("User: " + object.getName() + " failed to add in database.");
                return false;
            }

        } catch (SQLException e) {
            Logger.error(Arrays.toString(e.getStackTrace()) + e.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public boolean exists(Integer id) {
        try (Connection connection = DriverManager.getConnection(MyProperties.getDatabaseUrl(),
                MyProperties.getDatabaseUser(), MyProperties.getDatabasePassword());
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM timesheet.user WHERE user.id=?")) {

            preparedStatement.setInt(1, id);

            if (preparedStatement.execute()) {
                Logger.info("User with id: " + id + " exists in database.");
                return true;
            } else {
                Logger.info("User with id: " + id + " does not exists in database.");
                return false;
            }

        } catch (SQLException e) {
            Logger.error(Arrays.toString(e.getStackTrace()) + e.getMessage());
            throw new RuntimeException();
        }
    }
}
