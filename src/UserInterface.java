import java.util.Scanner;

public class UserInterface {

    private Scanner scanner;
    private TodoList todoList;

    public UserInterface(Scanner scanner, TodoList todoList) {
        this.scanner = scanner;
        this.todoList = todoList;
    }

    public void start() {

        System.out.println("To Do List: ");

        todoList.viewAllTasks();

        while (true) {

            System.out.print("\nPress 1 to add tasks, 2 to edit list, 3 to view tasks or 4 to exit: ");

            int command = Integer.parseInt(scanner.nextLine());

            // add task
            if (command == 1) {

                while (true) {

                    boolean addNew = false;

                    System.out.print("Enter your task: ");
                    String task = scanner.nextLine();

                    todoList.add(task);

                    while(true) {

                        System.out.print("Do you want to add another task? (y/n): ");
                        String addTask = scanner.nextLine();

                        if (addTask.equals("y")) {
                            addNew = true;
                            break;

                        } else if (addTask.equals("n")) {
                            break;

                        } else {
                            System.out.println("Invalid input.");
                        }
                    }

                    // exits loop if user doesn't want to add new task
                    if (!addNew) {
                        break;
                    }

                }

            // Edit list
            } else if (command == 2) {

                while (true) {

                    System.out.print("Do you want to perform any actions on the tasks? (y/n): ");
                    String doAction = scanner.nextLine();

                    if (doAction.equals("y")) {

                        // Update or delete
                        System.out.print("Do you want to update or delete a task? (u/d): ");
                        String action = scanner.nextLine();

                        // Update
                        if (action.equals("u")) {

                            while(true) {

                                System.out.print("Enter the id of task you want to update: ");
                                int id = Integer.parseInt(scanner.nextLine());

                                // TODO: 7/6/2023 give user option to change task description or stage or both

                                System.out.print("Enter new task description: ");
                                String newTask = scanner.nextLine();

                                System.out.print("Do you want to change the stage of task? (y/n): ");
                                String editStage = scanner.nextLine();

                                if (editStage.equals("y")) {

                                    System.out.print("Enter task stage (pending/ongoing/complete): ");
                                    String taskStage = scanner.nextLine();

                                    if (taskStage.equals("pending") || taskStage.equals("ongoing") || taskStage.equals("complete")) {

                                        todoList.update(id, newTask, taskStage);
                                        break;

                                    } else {

                                        System.out.println("Invalid input");
                                    }

                                } else if (editStage.equals("n")) {

                                    todoList.update(id, newTask);
                                    break;

                                } else {

                                    System.out.println("Invalid input.");
                                }
                            }
                        }

                        // Delete
                        else if (action.equals("d")) {
                            System.out.print("Enter the id of task you want to delete: ");
                            int id = Integer.parseInt(scanner.nextLine());

                            todoList.delete(id);
                            break;

                            // Invalid
                        } else {
                            System.out.println("Invalid input");
                        }

                    } else if (doAction.equals("n")) {
                        break;

                    } else {
                        System.out.println("Invalid input!");
                    }
                }

            // View list
            } else if (command == 3) {
                todoList.viewAllTasks();

            // Exit program
            } else if (command == 4) {
                todoList.exit();
                break;

            // Error message
            } else {
                System.out.println("Invalid input!");
            }
        }
    }
}
