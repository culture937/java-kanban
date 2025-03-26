class Subtask {
    int id;
    String name;
    String description;
    int epicId;
    Status status;

    public Subtask(int id, String name, String description, int epicId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.epicId = epicId;
        this.status = Status.NEW;
    }
}