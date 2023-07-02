import java.sql.*;
import java.util.HashMap;

public class TodoList {
    private int task_id;
    private HashMap<Integer, String> list;

    public TodoList() {
        this.list = new HashMap<Integer, String>();
        this.task_id = 0;
    }

    public void Connect(int command, String sql) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/todolist", "root", "hello123");

            Statement statement = connection.createStatement();

            // command = 1 - Display
            if (command == 1) {
                ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                System.out.println(resultSet.getString("Task"));
            }

            // command = 2 - Insert, Update, Delete
            } else if(command == 2) {
                statement.executeUpdate(sql);
            }
//            ResultSet resultSet = statement.executeQuery("select * from list;");

//            while (resultSet.next()) {
//                System.out.println(resultSet.getString("Task"));
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void add(String task) {
        this.task_id++;
        String sql = "insert into list(Task) values('"+task+"')";
        Connect(2, sql);
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
        String sql = "select * from list;";
        Connect(1, sql);
    }
}
