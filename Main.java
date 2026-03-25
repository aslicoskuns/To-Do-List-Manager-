import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Main{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        TaskController controller = new TaskController();

        System.out.println("---------------------------------");
        System.out.println("   STARTING TO-DO LIST MANAGER   ");
        System.out.println("---------------------------------");

        boolean isRunning = true;

        while(isRunning){
            System.out.println("\n------MENU--------");
            System.out.println("1.Add Task");
            System.out.println("2.View Pending Tasks List");
            System.out.println("3.View Completed Tasks List");
            System.out.println("4.Complete Task");
            System.out.println("5.Undo Last Command");
            System.out.println("6.Delete Task");
            System.out.println("7.Exit");
            System.out.println("Your choice: ");

            String choice = scanner.nextLine();

            switch (choice){
                case "1": //add
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();

                    System.out.print("Enter description: ");
                    String desc = scanner.nextLine();

                    System.out.print("Deadline (GG/AA/YYYY): ");
                    String dateInput = scanner.nextLine().trim();

                    Date deadline;
                    try {

                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                        formatter.setLenient(false);
                        deadline = formatter.parse(dateInput);

                    } catch (Exception e) {
                        System.out.println("ERROR: Invalid date format : deadline is chosen as today.");
                        deadline = new Date();
                    }

                    controller.createNewTask(title, desc, deadline);
                    break;

                case "2"://view pending
                    controller.viewList(TaskStatus.PENDING);
                    break;

                case "3"://view completed
                    controller.viewList(TaskStatus.COMPLETED);
                    break;

                case "4"://complete
                    controller.viewList(TaskStatus.PENDING);
                    System.out.println("Enter the number of tasks you want to complete: ");
                    try{
                        int index = Integer.parseInt(scanner.nextLine());
                        Task t = controller.getTask(index -1);
                        if(t != null) controller.markTaskCompleted(t);
                        else System.out.println("ERROR: Task does not exist.");
                    }
                    catch (Exception e){
                        System.out.println("ERROR: Please enter a number.");
                    }
                    break;

                case "5"://undo
                    controller.undoLastAction();
                    break;

                case "6"://delete
                    controller.viewList(TaskStatus.PENDING);
                    System.out.println("Enter the number of tasks you want to delete:");
                    try{
                        int index = Integer.parseInt(scanner.nextLine());
                        Task t = controller.getTask(index - 1);
                        if (t != null) controller.deleteTask(t);
                        else System.out.println("ERROR: Task does not exist.");
                    }
                    catch (Exception e){
                        System.out.println("ERROR: Please enter a number.");
                    }
                    break;

                case "7"://exit
                    System.out.println("See you next time!");
                    isRunning = false;
                    break;

                default:
                    System.out.println("ERROR:Invalid choice.");
              }
        }
        scanner.close();
    }
}