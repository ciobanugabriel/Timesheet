package sa.timetracking.jdbc;




import sa.timetracking.jdbc.dao.classes.*;
import sa.timetracking.jdbc.dao.interfaces.*;
import sa.timetracking.jdbc.dto.Customer;
import sa.timetracking.jdbc.dto.TimeTracking;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class Menu {
    public static void main(String[] args) {
        List<TimeTracking> timeTrackingList = new TimeTrackingDAO().getAll();
        for(TimeTracking timeTracking : timeTrackingList){
            System.out.println(timeTracking);
        }
    }
}
