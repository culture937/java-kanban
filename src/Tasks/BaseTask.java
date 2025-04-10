package Tasks;

public class BaseTask {
    protected int id;
    protected String title;
    protected String description;
    protected Status status;

    public BaseTask(String title, String description, int id) {
        this.title = title;
        this.description = description;
        this.status = Status.NEW; // По умолчанию статус NEW
        this.id = id;
    }

    public BaseTask(String title, String description, Status status, int id) {
        this.title = title;
        this.description = description;
        this.status = Status.NEW; // По умолчанию статус NEW
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    protected void setStatus(Status status) {
        this.status = status;
    }
}
