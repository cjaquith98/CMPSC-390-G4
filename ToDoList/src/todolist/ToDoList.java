
package todolist;

import java.time.LocalDate;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ToDoList extends Application {
    //Variables for task values
     ListView<Task> tasks;
     ObservableList<Task> allTaskList = FXCollections.<Task>observableArrayList(); //A list for all tasks. Tasks automatically added here
     
     String listName;
     ListView<TaskList> lists;
     ObservableList<TaskList> lystList = FXCollections.<TaskList>observableArrayList(); //List of user-created lists
     
  public static void main(String[] args) {
        launch(args);
        
        WriteJson.jsonStorage(); //calls jsonStorage method, from WriteJson Class, to execute          
    }


    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, 500, 600);
        primaryStage.setTitle("ToDoList");
        primaryStage.setScene(scene);
        
        //LOGO
        Image image = new Image("resources/logo.png");  
        ImageView imageView = new ImageView(image); 
        imageView.setX(0); imageView.setY(0); 
        imageView.setFitHeight(100); imageView.setFitWidth(100); 
        imageView.setPreserveRatio(true);  
      
        //Blue border
        Rectangle bottomBorder = new Rectangle(500, 20);
        bottomBorder.setLayoutX(0); bottomBorder.setLayoutY(580);
        bottomBorder.setFill(Color.DEEPSKYBLUE);
        
        //LABELS
        //Scene label
        Label title = new Label();
        title.setText("To-Do List");
        title.setLayoutX(100); title.setLayoutY(40);
        title.setFont(new Font("Arial", 40));
      
        //Task View label
        Label taskLabel = new Label();
        taskLabel.setText("Tasks");
        taskLabel.setLayoutX(170); taskLabel.setLayoutY(130);
        taskLabel.setFont(new Font("Arial", 24));
        
        //List view label
        Label listLabel = new Label();
        listLabel.setText("Lists");
        listLabel.setLayoutX(10); listLabel.setLayoutY(130);
        listLabel.setFont(new Font("Arial", 24));
        
        //END LABELS
        
        //BUTTONS
        //Show-all-tasks button; writes all tasks in task listview
        Button showAllTasksBtn = new Button("Show All Tasks");
        showAllTasksBtn.setLayoutX(10); showAllTasksBtn.setLayoutY(160);
        showAllTasksBtn.setMinWidth(150);
        showAllTasksBtn.setOnAction(event -> {
            tasks.setItems(allTaskList);
            taskLabel.setText("Tasks");
        });
        
        //Create-a-Task button; opens task screen
        Button createNewTaskBtn = new Button();
        createNewTaskBtn.setText("New Task");
        createNewTaskBtn.setLayoutX(10); createNewTaskBtn.setLayoutY(420);
        createNewTaskBtn.setMinWidth(150);
        createNewTaskBtn.setOnAction(new EventHandler<ActionEvent>() { //Button opens task creation screen
            @Override
             public void handle(ActionEvent event) { taskScreen(null); }
        });
        
        //Create-a-List button; opens list screen
        Button createNewListBtn = new Button();
        createNewListBtn.setText("New List");
        createNewListBtn.setLayoutX(10); createNewListBtn.setLayoutY(450);
        createNewListBtn.setMinWidth(150);
        createNewListBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
             public void handle(ActionEvent event) { listScreen(null); }
        });
        
        //Edit button, edits tasks and lists
        Button editTaskBtn = new Button();
        editTaskBtn.setText("Edit Selected Item");
        editTaskBtn.setLayoutX(10); editTaskBtn.setLayoutY(480);
        editTaskBtn.setMinWidth(150);
        
        editTaskBtn.setOnAction((ActionEvent event) -> {
            TaskList listToEdit = lists.getSelectionModel().getSelectedItem(); //Check if item is selected. Pass selected item to correct screen
            Task taskToEdit = tasks.getSelectionModel().getSelectedItem();
            if (listToEdit!=null) { listScreen(listToEdit); }
            else if (taskToEdit!=null) { taskScreen(taskToEdit); }
        });
         
        //Delete button, deletes tasks and lists
        Button deleteBtn = new Button("Delete Selected Item");
        deleteBtn.setLayoutX(10); deleteBtn.setLayoutY(510);
        deleteBtn.setMinWidth(150); 
        
        deleteBtn.setOnAction(event -> {
            TaskList listToDelete = lists.getSelectionModel().getSelectedItem(); //Checks if item is selected.
            Task taskToDelete = tasks.getSelectionModel().getSelectedItem();
            
            if(listToDelete!=null) { lystList.remove(listToDelete); } //Removes user-list from the list listview
            else if (taskToDelete!=null){ //Removes task from both all-task list, and from user-list, if it is a part of one
                TaskList listToDeleteFrom = taskToDelete.getUserList();
                allTaskList.remove(taskToDelete); 
                if (listToDeleteFrom!=null) { listToDeleteFrom.getTasks().remove(taskToDelete); }
            }
        });
        //END BUTTONS

        //ListView to display tasks
        tasks = new ListView<>(allTaskList);
        tasks.setLayoutX(170); tasks.setLayoutY(160);
        tasks.setMaxHeight((deleteBtn.getLayoutY()+25) - tasks.getLayoutY()); tasks.setMinWidth(320);
        tasks.setOnMouseClicked(event -> { //Clicking on tasks deselects any item from listView so delete and edit buttons work
            lists.getSelectionModel().clearSelection();
        });
        
        //ListView to display user-created lists
        lists = new ListView<>(lystList);
        lists.setLayoutX(10); lists.setLayoutY(190);
        lists.setMaxHeight(220); lists.setMaxWidth(150);
        
        lists.setOnMouseClicked(event -> { //Clicking on lists writes tasks from that list into task view; Deselects any item in task view
            tasks.setItems(lists.getSelectionModel().getSelectedItem().getTasks());
            taskLabel.setText(lists.getSelectionModel().getSelectedItem().getListName());
            tasks.getSelectionModel().clearSelection();
        });
         
          
        //Add all things
        root.getChildren().addAll(imageView, title, listLabel, taskLabel, bottomBorder);
        root.getChildren().addAll(createNewTaskBtn, createNewListBtn, editTaskBtn, deleteBtn, showAllTasksBtn, tasks, lists);
        
        primaryStage.show();
    }

    
    
    public void taskScreen(Task taskToEdit){
        
        Stage newWindow = new Stage();
        Group root = new Group();
        Scene scene = new Scene(root, 500, 600);
        newWindow.setTitle("Task Screen");
        newWindow.setScene(scene);
        
        //LOGO
        Image image = new Image("resources/logo.png");  
        ImageView logo = new ImageView(image); 
        logo.setX(0); logo.setY(0); 
        logo.setFitHeight(100); logo.setFitWidth(100); 
        logo.setPreserveRatio(true);  
      
        //Blue border
        Rectangle bottomBorder = new Rectangle(600, 20);
        bottomBorder.setLayoutX(0); bottomBorder.setLayoutY(580);
        bottomBorder.setFill(Color.DEEPSKYBLUE);
        
        int xLayout = 100; //the x line up of all labels and boxes
        
        //INFO BOXES
        int yLayout = 35; //Vertical space between each box
        
        //Task name
        TextField nameBox = new TextField();
        nameBox.setLayoutX(xLayout); nameBox.setLayoutY(130);
        nameBox.setPrefHeight(25);
  
        //Due date
        DatePicker dateBox = new DatePicker();
        dateBox.setLayoutX(xLayout); dateBox.setLayoutY(nameBox.getLayoutY()+nameBox.getPrefHeight()+yLayout);
        dateBox.setPrefHeight(25);
        
        //Description
        TextArea descBox = new TextArea();
        descBox.setLayoutX(xLayout); descBox.setLayoutY(dateBox.getLayoutY()+dateBox.getPrefHeight()+yLayout);
        descBox.setPrefSize(250, 100);

        //Importance
        Spinner<Integer> priorityBox = new Spinner(1, 3, 1, -1);
        priorityBox.setLayoutX(xLayout); priorityBox.setLayoutY(descBox.getLayoutY()+descBox.getPrefHeight()+yLayout);
        priorityBox.setPrefHeight(25);
        
        //Add to user-list
        ComboBox<TaskList> userLists = new ComboBox(lystList);
        userLists.setLayoutX(xLayout); userLists.setLayoutY(priorityBox.getLayoutY()+priorityBox.getPrefHeight()+yLayout);
        userLists.setPrefHeight(25);
        //END INFO BOXES
        
        if (taskToEdit!=null) { //If Task is being edited, fill boxes with task info
            nameBox.setText(taskToEdit.getTitle());
            dateBox.setValue(taskToEdit.getDueDate());
            descBox.setText(taskToEdit.getDescription());
            priorityBox.getValueFactory().setValue(taskToEdit.getImportance());
            userLists.getItems().add(0, null); //Add null option to combobox so user can remove task from list
            userLists.setValue(taskToEdit.getUserList());
        }
        
        //LABELS
        Font labelFont = new Font("Arial", 14);
        int labelYLayout = 20; //Vertical space between labels and the boxes they belong to
        
        //Scene Label
        Label sceneLabel = new Label();
        sceneLabel.setText("To-Do List");
        sceneLabel.setLayoutX(xLayout); sceneLabel.setLayoutY(50);
        sceneLabel.setFont(new Font("Arial", 24));
        
        //Task name label
        Label taskNameLabel = new Label();
        taskNameLabel.setText("Task Name");
        taskNameLabel.setLayoutX(xLayout); taskNameLabel.setLayoutY(nameBox.getLayoutY()-labelYLayout);
        taskNameLabel.setFont(labelFont);
        
        //due date label
        Label dateLabel = new Label();
        dateLabel.setText("Due Date");
        dateLabel.setLayoutX(xLayout); dateLabel.setLayoutY(dateBox.getLayoutY()-labelYLayout);
        dateLabel.setFont(labelFont);
        
        //description label
        Label descLabel = new Label();
        descLabel.setText("Description");
        descLabel.setLayoutX(xLayout); descLabel.setLayoutY(descBox.getLayoutY()-labelYLayout);
        descLabel.setFont(labelFont);
        
        //priority label
        Label priorityLabel = new Label();
        priorityLabel.setText("Importance (1-Most 3-Least)");
        priorityLabel.setLayoutX(xLayout); priorityLabel.setLayoutY(priorityBox.getLayoutY()-labelYLayout);
        priorityLabel.setFont(labelFont);
        
        //add list label
        Label addListLabel = new Label();
        addListLabel.setText("Add to list:");
        addListLabel.setLayoutX(xLayout); addListLabel.setLayoutY(userLists.getLayoutY()-labelYLayout);
        addListLabel.setFont(labelFont);
        //END LABELS
        
        //Submit task button; makes a task object, or rewrites an existing one
        Button submitTaskBtn = new Button();
        submitTaskBtn.setText("Submit");
        submitTaskBtn.setPrefHeight(30);
        submitTaskBtn.setLayoutX(xLayout); submitTaskBtn.setLayoutY(userLists.getLayoutY()+userLists.getPrefHeight()+20);
        submitTaskBtn.setFont(new Font("Arial", 20));
        
        submitTaskBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
             public void handle(ActionEvent event) {
                //Get info from boxes
                String taskName = nameBox.getText();
                LocalDate date = dateBox.getValue();
                String description = descBox.getText();
                Integer importance = priorityBox.getValue();
                TaskList addToList = userLists.getValue();
              
               if (taskToEdit==null) { //If new task is being made
                    Task newTask = new Task(taskName, date, description, importance, addToList); //Create task
                    allTaskList.add(newTask); //Add task to all-tasks list always
                    if (addToList!=null) { addToList.getTasks().add(newTask); } //Add it to user-list if user-list selected
                }
                else { //Else, existing task is being edited
                    taskToEdit.editTask(taskName, date, description, importance, addToList); //Rewrites all attributes
                    int allTaskIndex = allTaskList.indexOf(taskToEdit); //Task's place in allTaskList
                    allTaskList.set(allTaskIndex, taskToEdit); //Immediately updates taskview for all-tasks list
                    if (taskToEdit.getUserList()!=null) { //Immediately updates taskview for user-list, if task is part of one
                        int userIndex = taskToEdit.getUserList().getTasks().indexOf(taskToEdit);
                        taskToEdit.getUserList().getTasks().set(userIndex, taskToEdit);
                    }
                    lystList.remove(0); //Remove null option from lystList so it will not show in listview
                }
                newWindow.close(); //Close task window
              
             
            }
        });
        
        //Add all things
        root.getChildren().addAll(logo, sceneLabel, bottomBorder, submitTaskBtn);
        root.getChildren().addAll(nameBox, dateBox, descBox, priorityBox, userLists);
        root.getChildren().addAll(taskNameLabel, dateLabel, descLabel, priorityLabel, addListLabel);
        
        newWindow.show();
        
    }
    
    
    public void listScreen(TaskList listToEdit){
                   
        Stage newWindow = new Stage();
        Group root = new Group();
        Scene scene = new Scene(root, 250, 300);
        newWindow.setTitle("List Screen");
        newWindow.setScene(scene);
        
        //LOGO
        Image image = new Image("resources/logo.png");  
        ImageView logo = new ImageView(image); 
        logo.setX(0); logo.setY(0); 
        logo.setFitHeight(50); logo.setFitWidth(50); 
        logo.setPreserveRatio(true);  
        
        //Blue border
        Rectangle bottomBorder = new Rectangle(250, 10);
        bottomBorder.setLayoutX(0); bottomBorder.setLayoutY(290);
        bottomBorder.setFill(Color.DEEPSKYBLUE);
        
        //Scene label
        Label title = new Label();
        title.setText("To-Do List");
        title.setLayoutX(50); title.setLayoutY(20);
        title.setFont(new Font("Arial", 15));
        
        //Info box for list name
        TextField nameBox = new TextField();
        nameBox.setLayoutX(50); nameBox.setLayoutY(100);
        
        //Name label
        Label nameLabel = new Label();
        nameLabel.setText("List Name");
        nameLabel.setLayoutX(50); nameLabel.setLayoutY(nameBox.getLayoutY() - 20);
        nameLabel.setFont(new Font("Arial", 12));
        
        if (listToEdit!=null) { nameBox.setText(listToEdit.toString()); } //If list being edited, fill name box with list name
        
        //Create list button; Makes list object and adds it to lystList view in main screen
        Button submitListBtn = new Button();
        submitListBtn.setText("Submit");
        submitListBtn.setLayoutX(50); submitListBtn.setLayoutY(nameBox.getLayoutY() + 40);
        
        submitListBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
             public void handle(ActionEvent event) {
              String listName = nameBox.getText();
                if (listToEdit==null) { //If not editing list, make new one
                    TaskList newList = new TaskList(listName);
                    lystList.add(newList);
                }
                else { //Else, change existing list name. Replace old list with new one in listview
                    listToEdit.setListName(listName);
                    int lystIndex = lystList.indexOf(listToEdit);
                    lystList.set(lystIndex, listToEdit);
                }
                newWindow.close();
            }
        });
        
        
       //Add all things
        root.getChildren().addAll(logo, title, bottomBorder, submitListBtn, nameBox, nameLabel);
      
      newWindow.show();
  
    }
    

    
}

