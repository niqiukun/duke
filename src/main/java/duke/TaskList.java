package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty list of tasks.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a list of tasks using imported raw string from the file system.
     *
     * @param s the raw string loaded from the file system.
     * @throws DukeException if the raw string cannot be formatted into tasks.
     */
    public TaskList(String s) throws DukeException {
        assert s.length() > 0 : s;
        String[] lines = s.split("\n");
        tasks = new ArrayList<>();
        for (String line : lines) {
            addTask(Task.fromImportFormat(line));
        }
    }

    /**
     * Gets the number of tasks in the list.
     *
     * @return the number of tasks in the list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Gets the list of tasks.
     *
     * @return the list of tasks.
     */
    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    /**
     * Gets a particular task from the list.
     *
     * @param taskNum the index number of the task to be retrieved.
     * @return the task retrieved from the list.
     * @throws DukeException if the task cannot be found.
     */
    public Task getTask(int taskNum) throws DukeException {
        if (taskNum < 1 || taskNum > tasks.size()) {
            throw new DukeException("You do not have a task with index " + taskNum + ".");
        }
        return tasks.get(taskNum - 1);
    }

    /**
     * Adds a task to the list.
     *
     * @param task the task to be added to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a particular task from the list.
     *
     * @param taskNum the index number of the task to be deleted.
     * @throws DukeException if the task cannot be found.
     */
    public void deleteTask(int taskNum) throws DukeException {
        if (taskNum < 1 || taskNum > tasks.size()) {
            throw new DukeException("You do not have a task with index " + taskNum + ".");
        }
        tasks.remove(taskNum - 1);
    }

    /**
     * Finds tasks from the list with descriptions matching the keyword.
     *
     * @param keyword the keyword to search for tasks.
     */
    public TaskList findTask(String keyword) {
        TaskList newList = new TaskList();
        for (Task myTask : tasks) {
            if (myTask.getDescription().contains(keyword)) {
                newList.addTask(myTask);
            }
        }
        return newList;
    }

    @Override
    public String toString() {
        StringBuilder myBuilder = new StringBuilder();
        for (int i = 1; i <= tasks.size(); i++) {
            Task myTask = tasks.get(i - 1);
            myBuilder.append(i + "." + myTask);
            if (i < tasks.size()) {
                myBuilder.append("\n");
            }
        }
        return myBuilder.toString();
    }
}
