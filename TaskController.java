import java.util.Date;
import java.util.List;

public class TaskController {

    private TaskList taskList;
    private TaskListPage taskListPage;
    private TaskCreationForm taskCreationForm;
    private ConfirmationMessage confirmationMessage;

    private Task lastProcessedTask;
    private String lastAction;

    public TaskController(){
        this.taskList = new TaskList();
        this.taskListPage = new TaskListPage();
        this.taskCreationForm = new TaskCreationForm();
        this.confirmationMessage = new ConfirmationMessage();
        this.lastAction = "NONE";
    }

    public void createNewTask(String title, String description, Date deadline){

        Task newTask = new Task(title, description, deadline);

        taskList.addTask(newTask);

        lastProcessedTask = newTask;
        lastAction = "ADD";

        confirmationMessage.showMessage("Task added successfully: " + title);
    }

    public void markTaskCompleted(Task task){
        if(task == null) return;

        task.updateStatus(TaskStatus.COMPLETED);
        lastProcessedTask = task;
        lastAction = "COMPLETE";

        confirmationMessage.showMessage("Task completed successfully.");
    }

    public void deleteTask(Task task){
        if(task == null) return;

        taskList.removeTask(task);

        lastProcessedTask = task;
        lastAction = "DELETE";

        confirmationMessage.showMessage("Task deleted successfully.");
    }

    public void viewList(TaskStatus status){
        List<Task> filteredTasks = taskList.filterByStatus(status);
        taskListPage.displayList(filteredTasks);
    }

    public Task getTask(int id) {
        return taskList.getTask(id);
    }

    public void undoLastAction(){
        System.out.println("Undoing last action...");

        if(lastAction.equals("ADD")){
            taskList.removeTask(lastProcessedTask);
            confirmationMessage.showMessage("Last added task removed successfully.");
        }
        else if(lastAction.equals("DELETE")){
            taskList.addTask(lastProcessedTask);
            confirmationMessage.showMessage("Last deleted task added back successfully.");
        }
        else if(lastAction.equals("COMPLETE")){
            lastProcessedTask.updateStatus(TaskStatus.PENDING);
            confirmationMessage.showMessage("Completed action is taken back successfully.");
        }
        else {
            System.out.println("There is no action to undo.");
        }

        lastAction = "NONE";
    }

    public TaskList getTaskList() {
        return taskList;
    }
}
