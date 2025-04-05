import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Epic extends Task {
    private ArrayList<Integer> subtaskIds = new ArrayList<>();
    public Epic(String title, String description, int id) {
        super(title, description, id);
    }

    public ArrayList<Integer> getSubtaskIds() {
        return subtaskIds;
    }

    public void addSubtask(int id) {
        subtaskIds.add(id);
    }
}
