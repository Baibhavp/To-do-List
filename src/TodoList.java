import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class TodoList {
    private int task_id;
    private HashMap<Integer, String> list;

    public TodoList() {
        this.list = new HashMap<Integer, String>();
        this.task_id = 0;
    }

    public void Connect(int command, String sql) {
        ArrayList<Integer> ids = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/todolist", "root", "hello123");

            Statement statement = connection.createStatement();

            // command = 1 - Display
            if (command == 1) {
                ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                System.out.println(resultSet.getString("Task"));
            }

            // command = 2 - Insert, Update
            } else if(command == 2) {
                statement.executeUpdate(sql);

            // command = 3 - Delete
            } else if(command == 3) {
                ResultSet resultSet = statement.executeQuery("select task_id from list;");

                while (resultSet.next()) {
                    ids.add(resultSet.getInt("Task_id"));
                }

                // Gets task_id to be deleted
                int id_to_delete = Integer.parseInt(sql.replaceFirst(".*?(\\d+).*", "$1"));

                if (ids.contains(id_to_delete)) {
                    statement.executeUpdate(sql);
                    System.out.println("Task deleted successfully.");
                } else {
                    System.out.println("Task ID could not be found.");
                }

            }
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
        String sql = "delete from list where Task_id = "+id+";";
        Connect(3, sql);
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
