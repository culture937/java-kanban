package Tasks;

import java.util.ArrayList;
import java.util.Map;

public class Epic extends Task {
    //bez public setStatus ne mogu obnovit status epica v task managare
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
        boolean is_new = true;
        boolean is_done = true;
        if(subtaskIds.size() == 0){
            this.setStatus(Status.DONE);
        }
        for(int id : subtaskIds){
            if(getStatus() == Status.IN_PROGRESS){
                this.setStatus(Status.IN_PROGRESS);
                return;
            }else if(subtasks.get(id).getStatus() == Status.NEW){
                is_done = false;
            }else if(subtasks.get(id).getStatus() == Status.DONE){
                is_new = false;
            }
        }
        if(is_new){
            this.setStatus(Status.NEW);
        }else if(is_done){
            this.setStatus(Status.DONE);
        }
    }
}
