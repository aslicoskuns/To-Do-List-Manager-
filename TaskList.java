import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<Task> tasks;

    public TaskList(){
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task){
        tasks.add(task);
    }

    public void removeTask(Task task){
        tasks.remove(task);
    }

    public List<Task> getTask(){
        return tasks;
    }

    public Task getTask(int id) {
        if (id >= 0 && id < tasks.size()) {
            return tasks.get(id);
        }
        return null;
    }

    public List<Task> filterByStatus(TaskStatus status){
        List <Task> filteredList = new ArrayList<>();

        for(Task t : tasks){
            if(t.getStatus() == status){
                filteredList.add(t);
            }
        }
        return filteredList;
    }
}
