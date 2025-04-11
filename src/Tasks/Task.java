package Tasks;

public class Task extends BaseTask{


    public Task(String title, String description, Status status, int id) {
        super(title, description, status, id);
    }

    public Task(String title, String description, int id) {
        super(title, description, id);
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
