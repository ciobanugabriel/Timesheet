package sa.timetracking.jdbc.dto;

public class Project {
    private Integer id;
    private String name;

    public Project() {
    }

    public Project(Integer id) {
        this.id = id;
    }

    public Project(String name) {
        this.name = name;
    }

    public Project(final Integer id, final String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
