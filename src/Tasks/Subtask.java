package Tasks;

public class Subtask extends BaseTask {
    private int epicId;

    public Subtask(String title, String description, int epicId) {
        super(title, description, epicId);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
