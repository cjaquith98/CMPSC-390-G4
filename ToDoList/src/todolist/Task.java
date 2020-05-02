package todolist;

import java.time.LocalDate;

public class Task {

    //All components of a Task
    private String title;
    private String description = "";
    private LocalDate dueDate;
    private Integer importance;
    
    //Initially creates a Task. All components set at once.
    public Task(String t, LocalDate due, Integer imp, String desc) {
        title = t;
        dueDate = due;
        importance = imp;
        description = desc;
    }
    
    //Set statements used to edit tasks after they have been made?
    public void setTitle(String t) { title = t; }
    public void setDueDate(LocalDate due) { dueDate = due; }
    public void setImportance(Integer imp) { importance = imp; }
    public void setDescription(String desc) { description = desc; }
    
    //Get statements used to print data into corrects boxes on the home screen?
    public String getTitle() { return title; }
    public LocalDate getDueDate() { return dueDate; }
    public Integer getImportance() { return importance; }
    public String getDescription() { return description; }
}
