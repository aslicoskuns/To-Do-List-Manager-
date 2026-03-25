import java.util.List;
public class TaskListPage {

    private TaskStatus currentView;

    public void displayList(List<Task> tasksToShow){
        System.out.println("\n=== TASK LIST ===");

        if(tasksToShow.isEmpty()){
            System.out.println("Task list is empty.");
        }else{
            for(int i = 0; i<tasksToShow.size(); i++){
                System.out.println((i+1) + "." + tasksToShow.get(i));
            }
        }
        System.out.println("=====================\n");
    }

    public void onSelectTask(Task t){
        System.out.println("Selected task: " + t.getTitle());
    }

    public void onClickDelete(){
        System.out.println("'Delete' button is clicked.");
    }

    public void onClickComplete(){
        System.out.println("'Complete' button is clicked.");
    }

    public void onClickUndo(){
        System.out.println("'Undo' button is clicked.");
    }

}
