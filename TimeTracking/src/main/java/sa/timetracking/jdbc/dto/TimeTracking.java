package sa.timetracking.jdbc.dto;

import java.sql.Date;

public class TimeTracking {
    private Integer id;
    private Employee employee;
    private Project project;
    private Integer workedHours;
    private ProjectOwner projectOwner;
    private Task task;
    private Date start;
    private Date end;
    private Customer customer;
    private RecordStatus recordStatus;
    private String comment;

    public TimeTracking() {
    }

    public TimeTracking(Integer id) {
        this.id = id;
    }

    public TimeTracking(Employee employee, Project project, Integer workedHours, ProjectOwner projectOwner,
                        Task task, Date start, Date end, Customer customer, RecordStatus recordStatus, String comment) {
        this.employee = employee;
        this.project = project;
        this.workedHours = workedHours;
        this.projectOwner = projectOwner;
        this.task = task;
        this.start = start;
        this.end = end;
        this.customer = customer;
        this.recordStatus = recordStatus;
        this.comment = comment;
    }

    public TimeTracking(final Integer id, final Employee employee, final Project project, final Integer workedHours,
                        final ProjectOwner projectOwner, final Task task, final Date start, final Date end, final Customer customer,
                        final RecordStatus recordStatus, final String comment) {
        this.id = id;
        this.employee = employee;
        this.project = project;
        this.workedHours = workedHours;
        this.projectOwner = projectOwner;
        this.task = task;
        this.start = start;
        this.end = end;
        this.customer = customer;
        this.recordStatus = recordStatus;
        this.comment = comment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(final Employee employee) {
        this.employee = employee;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(final Project project) {
        this.project = project;
    }

    public Integer getWorkedHours() {
        return workedHours;
    }

    public void setWorkedHours(final Integer workedHours) {
        this.workedHours = workedHours;
    }

    public ProjectOwner getProjectOwner() {
        return projectOwner;
    }

    public void setProjectOwner(final ProjectOwner projectOwner) {
        this.projectOwner = projectOwner;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(final Task task) {
        this.task = task;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(final Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(final Date end) {
        this.end = end;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(final Customer customer) {
        this.customer = customer;
    }

    public RecordStatus getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(final RecordStatus recordStatus) {
        this.recordStatus = recordStatus;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(final String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "TimeTracking{" +
                "id=" + id +
                ", employee=" + employee.getName() +
                ", project=" + project.getName() +
                ", workedHours=" + workedHours +
                ", projectOwner=" + projectOwner.getName() +
                ", task=" + task.getName() +
                ", start=" + start +
                ", end=" + end +
                ", customer=" + customer.getName() +
                ", recordStatus=" + recordStatus.getStatus() +
                ", comment='" + comment + '\'' +
                '}';
    }
}
