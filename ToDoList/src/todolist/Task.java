package todolist;

import java.time.LocalDate;

public class Task {

    //All components of a Task
    private String title;
    private String description = "";
    private LocalDate dueDate;
    private Integer importance;
    private TaskList isPartOf;
    
    //Initially creates a Task. All components set at once.
    public Task(String t, LocalDate due, String desc, Integer imp, TaskList addToList) {
        title = t;
        dueDate = due;
        description = desc;
        importance = imp;
        isPartOf = addToList;
    }
    
    //Used to edit existing tasks
    public void editTask(String t, LocalDate due, String desc, Integer imp, TaskList addToList) {
        title = t;
        dueDate = due;
        description = desc;
        importance = imp;
        if (addToList!=this.isPartOf) { //If selected user-list is different from list already set for task
            if (this.isPartOf == null) { addToList.getTasks().add(this); } //If it was not in a list before, add to selected list
            else if (addToList == null) { //If task was in a list, but is now being removed, remove from user-list
                this.isPartOf.getTasks().remove(this);
            }
            else { //Else, task is being transfered between lists, remove from previous list, add to new list
                this.isPartOf.getTasks().remove(this);
                addToList.getTasks().add(this);
            }
            this.isPartOf = addToList; //Set task's list value to new list
        }
    }
    
    //Set statements used to edit tasks after they have been made?
    public void setTitle(String t) { title = t; }
    public void setDueDate(LocalDate due) { dueDate = due; }
    public void setDescription(String desc) { description = desc; }
    public void setImportance(Integer imp) { importance = imp; }
    public void setUserList(TaskList partOf) { isPartOf = partOf; }
    
    //Get statements used to print data into corrects boxes on the home screen?
    public String getTitle() { return title; }
    public LocalDate getDueDate() { return dueDate; }
    public String getDescription() { return description; }
    public Integer getImportance() { return importance; }
    public TaskList getUserList() { return isPartOf; }
    
    @Override
    public String toString() {
        String printTask = title + "\nDue: " + dueDate;
        if (!description.equals("")) { printTask+= "\nDescription: " + description; }
        return printTask;
    }
}
