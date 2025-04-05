public class Task {
    protected int id;
    protected String title;
    protected String description;
    protected Status status;

    public Task(String title, String description, int id) {
        this.title = title;
        this.description = description;
        this.status = Status.NEW; // По умолчанию статус NEW
        this.id = id;
    }

    public Task(String title, String description, Status status, int id) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
