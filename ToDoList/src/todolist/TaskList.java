package todolist;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TaskList {
    String listName;
    ObservableList tasks;
    
    TaskList(String name) {
        listName = name;
        tasks = FXCollections.<Task>observableArrayList();
    }
    
    @Override
    public String toString() { return listName; }
    
}
