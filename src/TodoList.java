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

                // shows table
                System.out.println("Task_id    Task                          Stage");

                // Iterate through the data in the result set and display it.
                while (resultSet.next()) {
                    int task_id = resultSet.getInt("Task_id");
                    String task = resultSet.getString("Task");
                    String stage = resultSet.getString("Stage");

                    System.out.format("%-11d%-30s%-10s", task_id, task, stage);
                    System.out.println();

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

        int idToEdit = Integer.parseInt(sql.replaceFirst(".*?(\\d+).*", "$1"));

        // checks if task id is present in the database
        return this.ids.contains(idToEdit);
    }

    public void viewAllTasks() {

        String sql = "select * from list where Status != 'inactive';";
        connect(1, sql);

    }

    public void add(String task) {

        String sql = "insert into list(Task, Stage, Status) values('"+task+"', 'pending', 'active')";
        connect(2, sql);

    }

    public void delete(int id) {

        String sql = "update list set status = 'inactive' where Task_id = "+id+";";
        connect(3, sql);

    }

    public void update(int id, String newTask) {

        String sql = "update list set task = '"+newTask+"' where task_id = "+id;
        connect(4, sql);

    }

    public void update(int id, String newTask, String stage) {

        String sql = "update list set task = '"+newTask+"', stage = '"+stage+"' where task_id = "+id;
        connect(4, sql);

    }

    public void exit() {
        System.out.println("\nExiting program .... Bye Bye!");
    }

}
