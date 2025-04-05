class Subtask extends Task {
    private int epicId;

    Subtask(String title, String description, int epicId) {
        super(title, description, epicId);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }
}
