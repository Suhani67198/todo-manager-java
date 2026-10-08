//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- To-Do Manager ---");
            System.out.println("1. Add task");
            System.out.println("2. View tasks");
            System.out.println("3. Mark task as done");
            System.out.println("4. Delete task");
            System.out.println("5. Exit");
            System.out.print("Choose: ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1" -> {
                    System.out.print("Task title: ");
                    String title = sc.nextLine().trim();
                    if (title.isEmpty()) {
                        System.out.println("Title cannot be empty.");
                    } else {
                        manager.addTask(title);
                        System.out.println("Task added.");
                    }
                }
                case "2" -> manager.listTasks();
                case "3" -> {
                    System.out.print("Task id: ");
                    int id = readId(sc);
                    System.out.println(manager.markDone(id) ? "Marked done." : "Task not found.");
                }
                case "4" -> {
                    System.out.print("Task id: ");
                    int id = readId(sc);
                    System.out.println(manager.deleteTask(id) ? "Deleted." : "Task not found.");
                }
                case "5" -> {
                    System.out.println("Bye!");
                    return;
                }
                default -> System.out.println("Invalid choice, try again.");
            }
        }
    }

    private static int readId(Scanner sc) {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}