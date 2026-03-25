import java.util.Date;
import java.text.SimpleDateFormat;
public class TaskTester {

    public static void main(String[] args){
        TaskTester tester = new TaskTester();
        tester.testCreateTask();
        tester.testMarkCompleted();
        tester.testDeleteTask();
        tester.testUndoAction();
        tester.testViewList();
    }

    private Date returnDate(String deadline) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            return format.parse(deadline);
        } catch (Exception e) {
            return new Date();
        }
    }

    public void testCreateTask(){
        System.out.println("TEST 1 : ADD TASK");
        TaskController controller = new TaskController();
        controller.createNewTask("BIM209 project","Draw use case diagram",returnDate("14/12/2025"));
        Task t = controller.getTask(0);
        if(t != null && t.getTitle().equals("BIM209 project") && t.getDescription().equals("Draw use case diagram") && t.getDeadline().equals(returnDate("14/12/2025"))){
            System.out.println("TEST 1 PASSED");
        }else{
            System.out.println("TEST 1 FAILED");
        }
    }

    public void testMarkCompleted(){
        System.out.println("TEST 2 : MARK COMPLETED");
        TaskController controller = new TaskController();
        controller.createNewTask("Go to supermarket.","Buy milk.",returnDate("12/12/2025"));
        Task t = controller.getTask(0);
        controller.markTaskCompleted(t);
        if(t.getStatus() == TaskStatus.COMPLETED){
            System.out.println("TEST 2 PASSED");
        }else{
            System.out.println("TEST 2 FAILED: Status is still " + t.getStatus());
        }
    }

    public void testDeleteTask(){
        System.out.println("TEST 3 : DELETE TASK");
        TaskController controller = new TaskController();
        controller.createNewTask("Go to supermarket.","Buy milk.",returnDate("12/12/2025"));
        Task t = controller.getTask(0);
        controller.deleteTask(t);
        Task t2 = controller.getTask(0);
        if(t2 == null){
            System.out.println("TEST 3 PASSED");
        }else{
            System.out.println("TEST 3 FAILED");
        }
    }

    public void testUndoAction(){
        System.out.println("TEST 4 : UNDO LAST ACTION");
        TaskController controller = new TaskController();
        controller.createNewTask("Go to supermarket.","Buy milk.",returnDate("12/12/2025"));
        Task t = controller.getTask(0);
        controller.markTaskCompleted(t);
        controller.undoLastAction();
        if(t.getStatus() == TaskStatus.PENDING){
            System.out.println("TEST 4 PASSED");
        }else{
            System.out.println("TEST 4 FAILED");
        }
    }

    public void testViewList(){
        System.out.println("TEST 5 : VIEW LIST");
        TaskController controller = new TaskController();
        controller.createNewTask("Task A","desc A",returnDate("12/12/2025"));
        controller.createNewTask("Task B","desc B",returnDate("12/12/2025"));
        controller.createNewTask("Task C","desc C",returnDate("12/12/2025"));
        Task t = controller.getTask(0);
        controller.markTaskCompleted(t);
        int pendingNo = controller.getTaskList().filterByStatus(TaskStatus.PENDING).size();
        int completedNo = controller.getTaskList().filterByStatus(TaskStatus.COMPLETED).size();

        if(pendingNo == 2 && completedNo == 1){
            System.out.println("TEST 5 PASSED");
        }else {
            System.out.println("TEST 5 FAILED");
        }


    }
}






