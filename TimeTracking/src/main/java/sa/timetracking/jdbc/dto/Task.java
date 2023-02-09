package sa.timetracking.jdbc.dto;

public class Task {
    private Integer id;
    private String name;

    public Task() {
    }

    public Task(Integer id) {
        this.id = id;
    }

    public Task(String name) {
        this.name = name;
    }

    public Task(final Integer id, final String name) {
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
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
