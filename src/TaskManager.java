import java.util.Map;
import java.util.HashMap;

class TaskManager {
    private int nextId = 1;
    private Map<Integer, Task> tasks = new HashMap<>();
    private Map<Integer, Epic> epics = new HashMap<>();
    private Map<Integer, Subtask> subtasks = new HashMap<>();

    public Task createTask(String name, String description) {
        Task task = new Task(nextId++, name, description);
        tasks.put(task.id, task);
        return task;
    }

    public Epic createEpic(String name, String description) {
        Epic epic = new Epic(nextId++, name, description);
        epics.put(epic.id, epic);
        return epic;
    }

    public Subtask createSubtask(String name, String description, int epicId) {
        if (!epics.containsKey(epicId)) return null;
        Subtask subtask = new Subtask(nextId++, name, description, epicId);
        subtasks.put(subtask.id, subtask);
        epics.get(epicId).subtaskIds.add(subtask.id);
        updateEpicStatus(epicId);
        return subtask;
    }

    public void updateTaskStatus(int id, Status newStatus) {
        if (tasks.containsKey(id)) {
            tasks.get(id).status = newStatus;
        } else if (subtasks.containsKey(id)) {
            Subtask subtask = subtasks.get(id);
            subtask.status = newStatus;
            updateEpicStatus(subtask.epicId);
        }
    }

    private void updateEpicStatus(int epicId) {
        if (!epics.containsKey(epicId)) return;

        Epic epic = epics.get(epicId);
        if (epic.subtaskIds.isEmpty()) {
            epic.status = Status.NEW;
            return;
        }

        boolean allDone = true;
        boolean allNew = true;

        for (int subtaskId : epic.subtaskIds) {
            Subtask subtask = subtasks.get(subtaskId);
            if (subtask.status != Status.DONE) allDone = false;
            if (subtask.status != Status.NEW) allNew = false;
        }

        if (allDone) {
            epic.status = Status.DONE;
        } else if (allNew) {
            epic.status = Status.NEW;
        } else {
            epic.status = Status.IN_PROGRESS;
        }
    }

    public void deleteTask(int id) {
        tasks.remove(id);
    }

    public void deleteEpic(int id) {
        if (!epics.containsKey(id)) return;
        for (int subtaskId : epics.get(id).subtaskIds) {
            subtasks.remove(subtaskId);
        }
        epics.remove(id);
    }

    public void deleteSubtask(int id) {
        if (!subtasks.containsKey(id)) return;
        int epicId = subtasks.get(id).epicId;
        subtasks.remove(id);
        epics.get(epicId).subtaskIds.remove(Integer.valueOf(id));
        updateEpicStatus(epicId);
    }

    public void printAllTasks() {
        System.out.println("\n Задачи");
        for (Task task : tasks.values()) {
            System.out.println("ID: " + task.id + " | Название: " + task.name + " | Статус: " + task.status);
        }

        System.out.println("\n Эпики");
        for (Epic epic : epics.values()) {
            System.out.println("ID: " + epic.id + " | Название: " + epic.name + " | Статус: " + epic.status);
            System.out.println("   Подзадачи: " + epic.subtaskIds);
        }

        System.out.println("\n Подзадачи");
        for (Subtask subtask : subtasks.values()) {
            System.out.println("ID: " + subtask.id + " | Название: " + subtask.name + " | Статус: " + subtask.status + " | Эпик ID: " + subtask.epicId);
        }
    }

}
