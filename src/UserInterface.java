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
                        String add_task = scanner.nextLine();

                        if (add_task.equals("y")) {
                            addNew = true;
                            break;

                        } else if (add_task.equals("n")) {
                            // TODO: 7/6/2023 prints invalid input
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
                    String do_action = scanner.nextLine();

                    if (do_action.equals("y")) {

                        // Update or delete
                        System.out.print("Do you want to Update or Delete a task? (u/d): ");
                        String action = scanner.nextLine();

                        // Update
                        if (action.equals("u")) {

                            while(true) {

                                System.out.print("Enter the id of task you want to update: ");
                                int id = Integer.parseInt(scanner.nextLine());

                                System.out.print("Enter new task description: ");
                                String new_task = scanner.nextLine();

                                System.out.print("Do you want to change the stage of task? (y/n): ");
                                String editStage = scanner.nextLine();

                                if (editStage.equals("y")) {

                                    System.out.print("Enter task stage (pending/ongoing/complete): ");
                                    String task_stage = scanner.nextLine();

                                    if (task_stage.equals("pending") || task_stage.equals("ongoing") || task_stage.equals("complete")) {

                                        todoList.update(id, new_task, task_stage);
                                        break;

                                    } else {

                                        System.out.println("Invalid input");
                                    }

                                } else if (editStage.equals("n")) {

                                    todoList.update(id, new_task);
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

                    } else if (do_action.equals("n")) {
                        // TODO: 7/6/2023 breaks out of loop and prints invalid input
                        break;

                    } else {
                        System.out.println("Invalid input");
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
                System.out.println("Invalid input");
            }
        }
    }
}
