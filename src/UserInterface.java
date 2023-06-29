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

        todoList.printAll();
    }
}
