import java.util.HashMap;

public class TodoList {
    private String task;
    private int task_id;
    private HashMap<Integer, String> list;

    public TodoList(String task) {
        this.task = task;
        this.list = new HashMap<Integer, String>();
    }
}
