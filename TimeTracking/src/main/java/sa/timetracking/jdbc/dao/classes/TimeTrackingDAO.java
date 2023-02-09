package sa.timetracking.jdbc.dao.classes;


import logger.classes.Logger;
import logger.config.MyProperties;
import sa.timetracking.jdbc.dao.interfaces.ITimeTrackingDAO;
import sa.timetracking.jdbc.dto.TimeTracking;
import sa.timetracking.jdbc.mapper.Mapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TimeTrackingDAO implements ITimeTrackingDAO {

    @Override
    public List<TimeTracking> getAll() {
        List<TimeTracking> timeTrackingList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(MyProperties.getDatabaseUrl(),
                MyProperties.getDatabaseUser(), MyProperties.getDatabasePassword());
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT t_t.id,t_t.employeeID, e.name, e.age," +
                     " e.phone_number, t_t.projectID, p.name,\n" +
                     "\t\tt_t.worked_hours, t_t.project_ownerID, p_o.name, t_t.taskID, t.name,\n" +
                     "        t_t.start_date, t_t.end_date, t_t.customerID, c.name, c.contact_number,\n" +
                     "        t_t.recordID, r.status, t_t.comment\n" +
                     "FROM time_tracking AS t_t\n" +
                     "INNER JOIN record_status as r ON t_t.recordID=r.id\n" +
                     "INNER JOIN task as t ON t_t.taskID=t.id\n" +
                     "INNER JOIN project_owner AS p_o ON t_t.project_ownerID=p_o.id\n" +
                     "INNER JOIN project AS p ON t_t.projectID=p.id\n" +
                     "INNER JOIN employee AS e ON t_t.employeeID=e.id\n" +
                     "INNER JOIN customer AS c ON t_t.customerID=c.id ORDER BY t_t.id;")) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                timeTrackingList.add(Mapper.toTimeTracking(resultSet));
            }
            Logger.info("Time Tracking List created.");

        } catch (SQLException e) {
            Logger.error(Arrays.toString(e.getStackTrace()) + e.getMessage());
            throw new RuntimeException();
        }
        return timeTrackingList;
    }

    @Override
    public TimeTracking getById(final Integer id) {
        try (Connection connection = DriverManager.getConnection(MyProperties.getDatabaseUrl(),
                MyProperties.getDatabaseUser(), MyProperties.getDatabasePassword());
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT t_t.id,t_t.employeeID, e.name, e.age," +
                     " e.phone_number, t_t.projectID, p.name,\n" +
                     "\t\tt_t.worked_hours, t_t.project_ownerID, p_o.name, t_t.taskID, t.name,\n" +
                     "        t_t.start_date, t_t.end_date, t_t.customerID, c.name, c.contact_number,\n" +
                     "        t_t.recordID, r.status, t_t.comment\n" +
                     "FROM time_tracking AS t_t\n" +
                     "INNER JOIN record_status as r ON t_t.recordID=r.id\n" +
                     "INNER JOIN task as t ON t_t.taskID=t.id\n" +
                     "INNER JOIN project_owner AS p_o ON t_t.project_ownerID=p_o.id\n" +
                     "INNER JOIN project AS p ON t_t.projectID=p.id\n" +
                     "INNER JOIN employee AS e ON t_t.employeeID=e.id\n" +
                     "INNER JOIN customer AS c ON t_t.customerID=c.id " +
                     "WHERE t_t.id=?;")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Logger.info("Time Tracking item with id: " + id + " selected from database.");
                return Mapper.toTimeTracking(resultSet);
            } else {
                Logger.info("Time Tracking with id: " + id + " not found in database.");
                return null;
            }
        } catch (SQLException e) {
            Logger.error(Arrays.toString(e.getStackTrace()) + e.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public boolean update(final TimeTracking object) {
        try (Connection connection = DriverManager.getConnection(MyProperties.getDatabaseUrl(),
                MyProperties.getDatabaseUser(), MyProperties.getDatabasePassword());
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE timesheet.time_tracking as t" +
                     " set t.employeeID=?, t.projectID=?, t.worked_hours=?, t.project_ownerID=?, t.taskID=?, " +
                     "t.start_date=?, t.end_date=?, t.customerID=?, t.recordID=?, t.comment=? " +
                     "WHERE t.id=? ")) {

            if (new EmployeeDAO().exists(object.getEmployee().getId())) {
                preparedStatement.setInt(1, object.getEmployee().getId());
            } else {
                Logger.info("Employee with id : " + object.getEmployee().getId() + " does not exist in database.");
                return false;
            }

            if (new ProjectDAO().exists(object.getProject().getId())) {
                preparedStatement.setInt(2, object.getProject().getId());
            } else {
                Logger.info("Project with id : " + object.getProject().getId() + " does not exist in database.");
                return false;
            }

            if (object.getWorkedHours() > 0) {
                preparedStatement.setInt(3, object.getWorkedHours());
            } else {
                Logger.info("Negative number of hours.");
                return false;
            }

            if (new ProjectOwnerDAO().exists(object.getProjectOwner().getId())) {
                preparedStatement.setInt(4, object.getProjectOwner().getId());
            } else {
                Logger.info("Project Owner with id : " + object.getProjectOwner().getId() + " does not exist in database.");
                return false;
            }

            if (new TaskDAO().exists(object.getTask().getId())) {
                preparedStatement.setInt(5, object.getTask().getId());
            } else {
                Logger.info("Task with id : " + object.getTask().getId() + " does not exist in database.");
                return false;
            }

            preparedStatement.setDate(6, Date.valueOf(object.getStart().toString()));
            preparedStatement.setDate(7, Date.valueOf(object.getEnd().toString()));

            if (new CustomerDAO().exists(object.getCustomer().getId())) {
                preparedStatement.setInt(8, object.getCustomer().getId());
            } else {
                Logger.info("Customer with id : " + object.getCustomer().getId() + " does not exist in database.");
                return false;
            }

            if (new RecordStatusDAO().exists(object.getRecordStatus().getId())) {
                preparedStatement.setInt(9, object.getRecordStatus().getId());
            } else {
                Logger.info("Record Status with id : " + object.getRecordStatus().getId() + " does not exist in database.");
                return false;
            }

            preparedStatement.setString(10, object.getComment());
            preparedStatement.setInt(11, object.getId());

            if (preparedStatement.executeUpdate() > 0) {
                Logger.info("Time Tracking item with id : " + object.getId() + " updated in database.");
                return true;
            } else {
                Logger.info("Time Tracking with id: " + object.getId() + " not found in database.");
                return false;
            }

        } catch (SQLException e) {
            Logger.error(Arrays.toString(e.getStackTrace()) + e.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public boolean delete(final TimeTracking object) {
        try (Connection connection = DriverManager.getConnection(MyProperties.getDatabaseUrl(),
                MyProperties.getDatabaseUser(), MyProperties.getDatabasePassword());
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM timesheet.time_tracking " +
                     "WHERE time_tracking.id=?")) {

            preparedStatement.setInt(1, object.getId());

            if (preparedStatement.executeUpdate() > 0) {
                Logger.info("Time Tracking item with id: " + object.getId() + " deleted from database.");
                return true;
            } else {
                Logger.info("Time Tracking item with id: " + object.getId() + " not found in database.");
                return false;
            }

        } catch (SQLException e) {
            Logger.error(Arrays.toString(e.getStackTrace()) + e.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public boolean create(final TimeTracking object) {
        try (Connection connection = DriverManager.getConnection(MyProperties.getDatabaseUrl(),
                MyProperties.getDatabaseUser(), MyProperties.getDatabasePassword());
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO timesheet.time_tracking" +
                     "(employeeID, projectID, worked_hours, project_ownerID, taskID, start_date, end_date, customerID, recordID, comment) " +
                     "values(?,?,?,?,?,?,?,?,?,?)")) {

            if (new EmployeeDAO().exists(object.getEmployee().getId())) {
                preparedStatement.setInt(1, object.getEmployee().getId());
            } else {
                Logger.info("Employee with id : " + object.getEmployee().getId() + " does not exist in database.");
                return false;
            }

            if (new ProjectDAO().exists(object.getProject().getId())) {
                preparedStatement.setInt(2, object.getProject().getId());
            } else {
                Logger.info("Project with id : " + object.getProject().getId() + " does not exist in database.");
                return false;
            }

            if (object.getWorkedHours() > 0) {
                preparedStatement.setInt(3, object.getWorkedHours());
            } else {
                Logger.info("Negative number of hours.");
                return false;
            }

            if (new ProjectOwnerDAO().exists(object.getProjectOwner().getId())) {
                preparedStatement.setInt(4, object.getProjectOwner().getId());
            } else {
                Logger.info("Project Owner with id : " + object.getProjectOwner().getId() + " does not exist in database.");
                return false;
            }

            if (new TaskDAO().exists(object.getTask().getId())) {
                preparedStatement.setInt(5, object.getTask().getId());
            } else {
                Logger.info("Task with id : " + object.getTask().getId() + " does not exist in database.");
                return false;
            }

            preparedStatement.setDate(6, Date.valueOf(object.getStart().toString()));
            preparedStatement.setDate(7, Date.valueOf(object.getEnd().toString()));

            if (new CustomerDAO().exists(object.getCustomer().getId())) {
                preparedStatement.setInt(8, object.getCustomer().getId());
            } else {
                Logger.info("Customer with id : " + object.getCustomer().getId() + " does not exist in database.");
                return false;
            }

            if (new RecordStatusDAO().exists(object.getRecordStatus().getId())) {
                preparedStatement.setInt(9, object.getRecordStatus().getId());
            } else {
                Logger.info("Record Status with id : " + object.getRecordStatus().getId() + " does not exist in database.");
                return false;
            }

            preparedStatement.setString(10, object.getComment());

            if (preparedStatement.executeUpdate() > 0) {
                Logger.info("Time Tracking item: " + object.getId() + " added in database.");
                return true;
            } else {
                Logger.info("Time Tracking item: " + object.getId() + " failed to add in database.");
                return false;
            }

        } catch (SQLException e) {
            Logger.error(Arrays.toString(e.getStackTrace()) + e.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public boolean exists(final Integer id) {
        try (Connection connection = DriverManager.getConnection(MyProperties.getDatabaseUrl(),
                MyProperties.getDatabaseUser(), MyProperties.getDatabasePassword());
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM timesheet.time_tracking WHERE" +
                     " time_tracking.id=?")) {

            preparedStatement.setInt(1, id);

            if (preparedStatement.execute()) {
                Logger.info("Time Tracking with id: " + id + " exists in database.");
                return true;
            } else {
                Logger.info("Time Tracking with id: " + id + " does not exists in database.");
                return false;
            }

        } catch (SQLException e) {
            Logger.error(Arrays.toString(e.getStackTrace()) + e.getMessage());
            throw new RuntimeException();
        }
    }
}
