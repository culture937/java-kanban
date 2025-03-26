import java.util.ArrayList;
import java.util.List;

class Epic {
    int id;
    String name;
    String description;
    List<Integer> subtaskIds = new ArrayList<>();
    Status status;

    public Epic(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = Status.NEW;
    }
}
