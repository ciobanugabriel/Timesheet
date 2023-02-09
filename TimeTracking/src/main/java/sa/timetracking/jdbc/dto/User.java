package sa.timetracking.jdbc.dto;

public class User {
    private Integer id;
    private String name;
    private String password;
    private Integer employeeID;

    private boolean isAdmin;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(String name, String password, boolean isAdmin, Integer employeeID) {
        this.name = name;
        this.password = password;
        this.employeeID = employeeID;
        this.isAdmin = isAdmin;
    }

    public User(Integer id, String name, String password, boolean isAdmin, Integer employeeID) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.employeeID = employeeID;
        this.isAdmin = isAdmin;
    }

    public Integer getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
