package TaskManager;

import Tasks.Epic;
import Tasks.Status;
import Tasks.Subtask;
import Tasks.Task;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

class TaskManager{
    private int cur_id = 1;
    private Map<Integer, Task> tasks = new HashMap<>();
    private Map<Integer, Epic> epics = new HashMap<>();
    private Map<Integer, Subtask> subtasks = new HashMap<>();
    public void printAllTasks() {
        System.out.println("\n Задачи");
        for (Task task : tasks.values()) {
            System.out.println("ID: " + task.getId() + " | Название: " + task.getTitle() + " | Статус: " + task.getStatus());
        }

        System.out.println("\n Эпики");
        for (Epic epic : epics.values()) {
            System.out.println("ID: " + epic.getId() + " | Название: " + epic.getTitle() + " | Статус: " + epic.getStatus());
            System.out.println("   Подзадачи: " + epic.getId());
        }

        System.out.println("\n Подзадачи");
        for (Subtask subtask : subtasks.values()) {
            System.out.println("ID: " + subtask.getId() + " | Название: " + subtask.getTitle() + " | Статус: " + subtask.getStatus() + " | Эпик ID: " + subtask.getId());
        }

    }

    public void clearTasks(){
        tasks.clear();
    }

    public void clearEpics(){
        for(Epic epic : epics.values()){
            epic.getSubtaskIds().clear();
        }
        epics.clear();
        subtasks.clear();
    }

    public void clearSubTasks(){
        for(Epic epic : epics.values()){
            epic.getSubtaskIds().clear();
            epic.updateStatus(subtasks);
        }
        subtasks.clear();
    }


    public Map<Integer, Task> getTasks(){
        return tasks;
    }

    public Map<Integer, Epic> getEpics(){
        return epics;
    }

    public Map<Integer, Subtask> getSubtasks(){
        return subtasks;
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


    public void add(Subtask subtask){
        subtasks.put(cur_id, subtask);
        epics.get(subtask.getEpicId()).addSubtask(cur_id);
        epics.get(subtask.getEpicId()).updateStatus(subtasks);
        ++cur_id;
    }

    public void update(Epic epic){
        epics.put(epic.getId(), epic);
    }

    public void update(Subtask subtask){
        subtasks.put(subtask.getEpicId(), subtask);
        epics.get(subtask.getEpicId()).updateStatus(subtasks);
    }

    public void update(Task task){
        tasks.put(task.getId(), task);
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
            epic.updateStatus(subtasks);
        }
        subtasks.remove(id);
    }

    public void removeEpic(int id){
        epics.get(id).getSubtaskIds().clear();
        epics.remove(id);
    }

    public ArrayList<Subtask> getEpicsSubtasks(int id){
        ArrayList<Integer> ids = epics.get(id).getSubtaskIds();
        ArrayList<Subtask> sub = new ArrayList<Subtask>();
        for (Integer subtaskId : epics.get(id).getSubtaskIds()) {
            sub.add(subtasks.get(subtaskId));
        }
        return sub;
    }

    public void createEpic(String title, String description){
        epics.put(cur_id, new Epic(title, description, cur_id));
        ++cur_id;
    }

    public void createTask(String title, String description){
        tasks.put(cur_id, new Task(title, description, cur_id));
        ++cur_id;
    }


    public void createSubtaskTask(String title, String description, int id){
        subtasks.put(cur_id, new Subtask(title, description, cur_id));
        epics.get(id).addSubtask(cur_id);
        ++cur_id;
    }


}