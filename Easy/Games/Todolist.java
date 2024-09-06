import java.util.ArrayList;
import java.util.Scanner;

public class Todolist {
    public static void main(String[] args) {
        ArrayList<String> tasks = new ArrayList<>();
        try (Scanner in = new Scanner(System.in)) {
            while (true) {
                System.out.println("====================");
                System.out.println("To-do-List");
                System.out.println("1.Add a task");
                System.out.println("2.View a task");
                System.out.println("3.Mark task as done");
                System.out.println("4.Exit");
                System.out.println("Please select options ");
                int choice = in.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("Enter your daily tasks");
                        in.nextLine(); // Consume newline character
                        String task = in.nextLine();
                        tasks.add(task);
                        System.out.println("Task is added");
                        break;

                    case 2:
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println((i + 1) + "." + tasks.get(i));
                        }
                        break;

                    case 3: {
                        if (tasks.isEmpty()) {
                            System.out.println("Task is Empty");
                        } else {
                            System.out.println("Enter the task number to mark as done");
                            int taskNumber = in.nextInt();
                            if (taskNumber >= 1 && taskNumber <= tasks.size()) {
                                String removedTask = tasks.remove(taskNumber - 1);
                                System.out.println("Marked task as done " + removedTask);
                            } else {
                                System.out.println("Invalid task number");
                            }
                        }
                        break;
                    }

                    case 4: {
                        System.exit(0);
                        break;
                    }
                    default:
                        break;
                }
            }
        }
    }
}

