import java.util.Date;

public class Task {

    private String title;
    private String description;
    private Date deadline;
    private TaskStatus status ;

    public Task(String title, String description, Date deadline){
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.status = TaskStatus.PENDING;

    }

    public String getTitle(){
        return title;
    }

    public String getDescription(){
        return description;
    }

    public Date getDeadline(){
        return deadline;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void updateStatus(TaskStatus newStatus){
        this.status = newStatus;
        System.out.println("Status updated to : " + newStatus);
    }

    @Override
    public String toString(){
        return "Task : " + title + " -> " + description + " | " + " Deadline: " + deadline + " |  Status : " + status ;
    }

    public void updateDetails(String title, String description, Date deadline){
        this.title = title;
        this.description = description;
        this.deadline = deadline;
    }


}
