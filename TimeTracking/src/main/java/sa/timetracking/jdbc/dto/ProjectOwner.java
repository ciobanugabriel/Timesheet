package sa.timetracking.jdbc.dto;

public class ProjectOwner {
    private Integer id;
    private String name;

    public ProjectOwner() {
    }

    public ProjectOwner(Integer id) {
        this.id = id;
    }

    public ProjectOwner(String name) {
        this.name = name;
    }

    public ProjectOwner(final Integer id, final String name) {
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
        return "ProjectOwner{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
