package Tasks;

import java.util.ArrayList;

public class Epic extends Task {
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
}
