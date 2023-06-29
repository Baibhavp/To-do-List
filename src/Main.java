import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TodoList todoList = new TodoList();
        UserInterface userInterface = new UserInterface(scanner, todoList);

        userInterface.start();
    }
}