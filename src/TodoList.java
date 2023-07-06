import java.sql.*;
import java.util.ArrayList;

public class TodoList {

    private ArrayList<Integer> ids;

    public TodoList() {
        this.ids = new ArrayList<>();
    }

    public void connect(int command, String sql) {

        try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/todolist", "root", "hello123");

            Statement statement = connection.createStatement();

            // command = 1 - Display
            if (command == 1) {
                ResultSet resultSet = statement.executeQuery(sql);

            System.out.print("Task_id     Task\n");

            while (resultSet.next()) {
                System.out.println(resultSet.getInt("Task_id") + "         " + resultSet.getString("Task"));
            }

            // command = 2 - Insert
            } else if(command == 2) {
                statement.executeUpdate(sql);

            // command = 3 - Delete
            } else if(command == 3) {
                if (contains_id(statement, sql)) {
                    statement.executeUpdate(sql);
                    System.out.println("Task deleted successfully.");
                } else {
                    System.out.println("Task ID could not be found.");
                }

            // command = 4 - Update
            } else if (command == 4) {
                if (contains_id(statement, sql)) {
                    statement.executeUpdate(sql);
                    System.out.println("Task updated successfully.");
                } else {
                    System.out.println("Task ID does not exist.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean contains_id(Statement statement, String sql) throws SQLException {

        // Stores task ids present in the database
        ResultSet resultSet = statement.executeQuery("select task_id from list;");

        while (resultSet.next()) {
            this.ids.add(resultSet.getInt("Task_id"));
        }

        int id_to_edit = Integer.parseInt(sql.replaceFirst(".*?(\\d+).*", "$1"));

        // checks if task id is present in the database
        return this.ids.contains(id_to_edit);
    }

    public void add(String task) {
        String sql = "insert into list(Task) values('"+task+"')";
        connect(2, sql);
    }

    public void delete(int id) {
        String sql = "delete from list where Task_id = "+id+";";
        connect(3, sql);
    }

    public void update(int id, String new_task) {
        String sql = "update list set task = '"+new_task+"' where task_id = "+id;
        connect(4, sql);
    }

    public void exit() {
        System.out.println("\nExiting program .... Bye Bye!");
    }


    public void viewAllTasks() {
        String sql = "select * from list;";
        connect(1, sql);
    }
}
