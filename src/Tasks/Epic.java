package Tasks;

import java.util.ArrayList;
import java.util.Map;

public class Epic extends BaseTask {
    private ArrayList<Integer> subtaskIds = new ArrayList<>();
    public Epic(String title, String description, int id) {
        super(title, description, id);
    }

    public ArrayList<Integer> getSubtaskIds() {
        ArrayList<Integer> ids = new ArrayList<Integer>();
        for(int i = 0; i < subtaskIds.size(); ++i){
            ids.add(subtaskIds.get(i));
        }
        return ids;
    }

    public void addSubtask(int id) {
        subtaskIds.add(id);
    }

    public void updateStatus(Map<Integer, Subtask> subtasks) {
        if (subtaskIds.isEmpty()) {
            this.setStatus(Status.NEW);
            return;
        }

        boolean allNew = true;
        boolean allDone = true;

        for (int id : subtaskIds) {
            Status status = subtasks.get(id).getStatus();

            if (status != Status.NEW) {
                allNew = false;
            }
            if (status != Status.DONE) {
                allDone = false;
            }
        }

        if (allNew) {
            this.setStatus(Status.NEW);
        } else if (allDone) {
            this.setStatus(Status.DONE);
        } else {
            this.setStatus(Status.IN_PROGRESS);
        }
    }


}
