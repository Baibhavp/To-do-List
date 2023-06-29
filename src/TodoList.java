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

    public void printAll() {
        for (int task_id: list.keySet()) {
            String key = String.valueOf(task_id);
            String value = list.get(task_id);
            System.out.println(key + " " + value);
        }
    }
}
