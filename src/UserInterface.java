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

            if (task.equals("end")) {
                break;
            }
            todoList.add(task);
        }

        todoList.printAll();
    }
}
