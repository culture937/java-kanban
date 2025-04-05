import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

class TaskManager {
    private int cur_id = 1;
    private Map<Integer, Task> tasks = new HashMap<>();
    private Map<Integer, Epic> epics = new HashMap<>();
    private Map<Integer, Subtask> subtasks = new HashMap<>();
    public void printAllTasks() {
        System.out.println("\n Задачи");
        for (Task task : tasks.values()) {
            System.out.println("ID: " + task.id + " | Название: " + task.title + " | Статус: " + task.status);
        }

        System.out.println("\n Эпики");
        for (Epic epic : epics.values()) {
            System.out.println("ID: " + epic.id + " | Название: " + epic.title + " | Статус: " + epic.status);
            System.out.println("   Подзадачи: " + epic.id);
        }

        System.out.println("\n Подзадачи");
        for (Subtask subtask : subtasks.values()) {
            System.out.println("ID: " + subtask.id + " | Название: " + subtask.title + " | Статус: " + subtask.status + " | Эпик ID: " + subtask.id);
        }

    }

    public void clear(){
        tasks.clear();
        for(Epic epic : epics.values()){
            epic.getSubtaskIds().clear();
        }
        epics.clear();
        subtasks.clear();
    }

    public Task getTask(int id){
        return tasks.get(id);
    }

    public Epic getEpic(int id){
        return epics.get(id);
    }

    public Subtask getSubtask(int id){
        return subtasks.get(id);
    }

    public void add(Epic epic){
        epics.put(cur_id, epic);
        ++cur_id;
    }

    public void add(Task task){
        tasks.put(cur_id, task);
        ++cur_id;
    }

    public void add(int id, Subtask subtask){
        subtasks.put(cur_id, subtask);
        epics.get(id).addSubtask(cur_id);
        ++cur_id;
    }

    public void update(int id, Epic epic){
        epics.put(id, epic);
    }

    public void update(int id, Subtask subtask){
        subtasks.put(id, subtask);
    }

    public void update(int id, Task task){
        tasks.put(id, task);
    }

    public void removeTask(int id){
        tasks.remove(id);
    }

    public void removeSubtask(int id){
        for(Epic epic : epics.values()){
            for(int i = 0; i < epic.getSubtaskIds().size(); ++i){
                if(epic.getSubtaskIds().get(i) == id){
                    epic.getSubtaskIds().remove(i);
                }
            }
        }
        subtasks.remove(id);
    }

    public void removeEpic(int id){
        epics.remove(id);
    }

    public ArrayList<Integer> getEpicsSubtasks(int id){
        return epics.get(id).getSubtaskIds();
    }

    public void createEpic(String title, String description){
        epics.put(cur_id, new Epic(title, description, cur_id));
        ++cur_id;
    }

    public void createTask(String title, String description){
        tasks.put(cur_id, new Task(title, description, cur_id));
        ++cur_id;
    }

    //smotri kak mogu
    public void createSubtaskTask(String title, String description, int id){
        subtasks.put(cur_id, new Subtask(title, description, cur_id));
        epics.get(id).addSubtask(cur_id);
        ++cur_id;
    }
}