import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TaskManager {
    private int nextId = 1;
    private Map<Integer, Task> tasks = new HashMap<>();
    private Map<Integer, Epic> epics = new HashMap<>();
    private Map<Integer, Subtask> subtasks = new HashMap<>();

    public Task createTask(String title, String description) {
        Task task = new Task(title, description);
        task.setId(nextId++);
        tasks.put(task.getId(), task);
        return task;
    }

    public Epic createEpic(String title, String description) {
        Epic epic = new Epic(title, description);
        epic.setId(nextId++);
        epics.put(epic.getId(), epic);
        return epic;
    }

    public Subtask createSubtask(String title, String description, int epicId) {
        if (!epics.containsKey(epicId)) return null;

        Subtask subtask = new Subtask(title, description, epicId);
        subtask.setId(nextId++);
        subtasks.put(subtask.getId(), subtask);
        epics.get(epicId).addSubtask(subtask.getId());
        updateEpicStatus(epicId); // Обновляем статус эпика
        return subtask;
    }

    public Task getTask(int id) {
        return tasks.get(id);
    }

    public Epic getEpic(int id) {
        return epics.get(id);
    }

    public Subtask getSubtask(int id) {
        return subtasks.get(id);
    }

    public void deleteTask(int id) {
        tasks.remove(id);
    }

    public void deleteEpic(int id) {
        if (!epics.containsKey(id)) return;
        List<Integer> subtaskIds = epics.get(id).getSubtaskIds();
        for (int subId : subtaskIds) {
            subtasks.remove(subId);
        }
        epics.remove(id);
    }

    public void deleteSubtask(int id) {
        if (!subtasks.containsKey(id)) return;
        int epicId = subtasks.get(id).getEpicId();
        subtasks.remove(id);
        epics.get(epicId).removeSubtask(id);
        updateEpicStatus(epicId); // Обновляем статус эпика
    }

    public void updateTaskStatus(int id, Status newStatus) {
        if (tasks.containsKey(id)) {
            tasks.get(id).status = newStatus;
        } else if (subtasks.containsKey(id)) {
            subtasks.get(id).status = newStatus;
            updateEpicStatus(subtasks.get(id).getEpicId()); // Обновляем статус эпика
        }
    }

    private void updateEpicStatus(int epicId) {
        if (!epics.containsKey(epicId)) return;

        Epic epic = epics.get(epicId);
        List<Integer> subtaskIds = epic.getSubtaskIds();

        if (subtaskIds.isEmpty()) {
            epic.status = Status.NEW;
            return;
        }

        boolean allDone = true;
        boolean hasInProgress = false;

        for (int subId : subtaskIds) {
            Status subStatus = subtasks.get(subId).status;
            if (subStatus == Status.IN_PROGRESS) hasInProgress = true;
            if (subStatus != Status.DONE) allDone = false;
        }

        if (allDone) {
            epic.status = Status.DONE;
        } else if (hasInProgress) {
            epic.status = Status.IN_PROGRESS;
        } else {
            epic.status = Status.NEW;
        }
    }
}