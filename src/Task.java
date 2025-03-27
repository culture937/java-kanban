class Task {
    protected int id;
    protected String title;
    protected String description;
    protected Status status;

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.status = Status.NEW; // По умолчанию статус NEW
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
