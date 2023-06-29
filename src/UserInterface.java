import java.util.Scanner;
public class UserInterface {
    private Scanner scanner;
    private TodoList todoList;

    public UserInterface(Scanner scanner, TodoList todoList) {
        this.scanner = scanner;
        this.todoList = todoList;
    }

    public void start() {
        while(true) {
            System.out.print("Enter your task: ");
            String task = scanner.nextLine();
            todoList.add(task);

            System.out.print("Do you want to add another task? (yes/no): ");
            String add_task = scanner.nextLine();

            if (add_task.equals("no")) {
                break;
            }
        }

        // displays tasks on the list
        todoList.viewAllTasks();

        while (true){
            System.out.print("Do you want to perform any actions on the tasks? (yes/no): ");
            String do_action = scanner.nextLine();

            if (do_action.equals("yes")) {

                // Update or delete
                System.out.print("Do you want to Update or Delete a task? (u/d): ");
                String action = scanner.nextLine();

                // Update
                if (action.equals("u")) {
                    System.out.print("Enter the id of task you want to update: ");
                    int id = Integer.parseInt(scanner.nextLine());

                    if (todoList.contains_id(id)) {
                        System.out.print("Enter new description for task: ");
                        String new_task = scanner.nextLine();

                        todoList.update(id, new_task);
                    } else {
                        System.out.println("Task ID not found in the list.");
                    }

                // Delete
                } else if (action.equals("d")) {
                    System.out.print("Enter the id of task you want to delete: ");
                    int id = Integer.parseInt(scanner.nextLine());

                    todoList.delete(id);

                // Invalid
                } else {
                    System.out.println("Couldn't understand the action.");
                }

            // Exit program
            } else if (do_action.equals("no")) {
                todoList.exit();
                break;

            } else {
                System.out.println("Invalid response.");
            }
        }
    }
}
