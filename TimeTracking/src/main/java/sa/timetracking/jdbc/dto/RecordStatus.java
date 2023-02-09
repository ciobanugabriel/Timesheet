package sa.timetracking.jdbc.dto;

public class RecordStatus {
    private Integer id;
    private String status;

    public RecordStatus() {
    }

    public RecordStatus(Integer id) {
        this.id = id;
    }

    public RecordStatus(String status) {
        this.status = status;
    }

    public RecordStatus(final Integer id, final String status) {
        this.id = id;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RecordStatus{" +
                "id=" + id +
                ", status='" + status + '\'' +
                '}';
    }
}
