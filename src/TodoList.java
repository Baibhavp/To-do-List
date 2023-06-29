import java.util.HashMap;

public class TodoList {
    private int task_id;
    private HashMap<Integer, String> list;

    public TodoList() {
        this.list = new HashMap<Integer, String>();
        this.task_id = 0;
    }

    public void add(String task) {
        this.task_id++;
        this.list.put(this.task_id, task);
    }

    public void delete(int id) {
        if (list.containsKey(id)) {
            list.remove(id);
            System.out.println("Task deleted successfully.");

        } else {
            System.out.println("Task ID not found in the list.");
        }
    }

    public void update(int id, String new_task) {
        list.put(id, new_task);
        System.out.println("Task updated successfully.");
    }

    public void exit() {
        System.out.println("Exiting program .... Bye Bye!");
    }

    public boolean contains_id(int id) {
        return list.containsKey(id);
    }

    public void viewAllTasks() {
        for (int task_id: list.keySet()) {
            String key = String.valueOf(task_id);
            String value = list.get(task_id);
            System.out.println(key + " " + value);
        }
    }
}
