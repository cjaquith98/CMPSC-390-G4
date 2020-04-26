package todolist;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TaskList {
    private String listName;
    private ObservableList<Task> tasks;
    
    TaskList(String name) {
        listName = name;
        tasks = FXCollections.<Task>observableArrayList();
    }
    
    public String getListName() { return listName; }
    public ObservableList<Task> getTasks() { return tasks; }
    
    public void setListName(String n) { listName = n; }
    
    @Override
    public String toString() { return listName; }
    
}
