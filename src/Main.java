import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try {

            Scanner scanner = new Scanner(System.in);
            TodoList todoList = new TodoList();
            UserInterface userInterface = new UserInterface(scanner, todoList);

            userInterface.start();

        } catch (Exception e) {

            System.out.println("Sorry, something went wrong. Please run the program again.");
    }
    }
}