
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
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ToDoList extends Application {
     String task;
     String description;
     LocalDate date;
     int priority;
     ObservableList<String> taskList;
     
     String list;
     ObservableList<String> lystList;
     
    
  public static void main(String[] args) {
        launch(args);
        
        //calls jsonStorage method, from WriteJson Class, to execute
        WriteJson.jsonStorage();        
    }


    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, 600, 600);
        
        primaryStage.setTitle("ToDoList");
        primaryStage.setScene(scene);
        
        Image image = new Image("resources/logo.png");  
      
      //Setting the image view 
      ImageView imageView = new ImageView(image); 
      
      //Setting the position of the image 
      imageView.setX(0); 
      imageView.setY(0); 
      
      //setting the fit height and width of the image view 
      imageView.setFitHeight(100); 
      imageView.setFitWidth(100); 
      
      //Setting the preserve ratio of the image view 
      imageView.setPreserveRatio(true);  
      
      
      Label title = new Label();
      title.setText("To-Do List");
      title.setLayoutX(250);
      title.setFont(new Font("Arial", 24));
      
       Button createNewTaskBtn = new Button();
        createNewTaskBtn.setText("Create new Task");
        createNewTaskBtn.setLayoutX(250);
        createNewTaskBtn.setLayoutY(100);
        
         Button createNewListBtn = new Button();
        createNewListBtn.setText("Create new List");
        createNewListBtn.setLayoutX(0);
        createNewListBtn.setLayoutY(100);
        
        Label listLabel = new Label();
        listLabel.setText("Lists");
        listLabel.setLayoutX(0);
        listLabel.setLayoutY(150);
        listLabel.setFont(new Font("Arial", 24));
        
        Label taskLabel = new Label();
        taskLabel.setText("Tasks");
        taskLabel.setLayoutX(250);
        taskLabel.setLayoutY(150);
        taskLabel.setFont(new Font("Arial", 24));
        
        //ListView were the list will be displayed
        lystList = FXCollections.<String>observableArrayList();
        ListView<String> lists = new ListView<>(lystList);
        lists.setLayoutX(0);
        lists.setLayoutY(200);
        lists.setMaxHeight(300);
        lists.setMaxWidth(200);
        
        //possible way to display tasks from a certian list
        Button getListBtn = new Button("Open List");
         getListBtn.setLayoutY(540);

        getListBtn.setOnAction(event -> {
           
             String selectedList = lists.getSelectionModel().getSelectedItem();
             System.out.println(selectedList);
             
        });
        
        
        //ListView were the tasks will be displayed
        taskList = FXCollections.<String>observableArrayList();
        ListView<String> tasks = new ListView<>(taskList);
        tasks.setLayoutX(250);
        tasks.setLayoutY(200);
        tasks.setMaxHeight(200);
        tasks.setMaxWidth(250);
        
         
        Rectangle bottomBorder = new Rectangle(600, 20);
        bottomBorder.setLayoutX(0);
        bottomBorder.setLayoutY(580);
        bottomBorder.setFill(Color.DEEPSKYBLUE);

           
        root.getChildren().add(imageView);
        root.getChildren().add(title);
        root.getChildren().add(createNewTaskBtn);
        root.getChildren().add(createNewListBtn);
        root.getChildren().add(bottomBorder);
        root.getChildren().add(listLabel);
        root.getChildren().add(taskLabel);
        root.getChildren().add(tasks);
        root.getChildren().add(getListBtn);
        root.getChildren().add(lists);
        
         createNewTaskBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
             public void handle(ActionEvent event) {
               taskScreen();
               
            }
        });
         
         
         createNewListBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
             public void handle(ActionEvent event) {
               listScreen();   
            }
        });
         
          
        primaryStage.show();
    }

    
    
    public void taskScreen(){
        
        Stage newWindow = new Stage();
               
   
        Group root = new Group();
        Scene scene = new Scene(root, 600, 600);
        
        newWindow.setTitle("Task Screen");
        newWindow.setScene(scene);
        
        Image image = new Image("resources/logo.png");  
      
      //Setting the image view 
      ImageView logo = new ImageView(image); 
      
      //Setting the position of the image 
      logo.setX(0); 
      logo.setY(0); 
      
      //setting the fit height and width of the image view 
      logo.setFitHeight(100); 
      logo.setFitWidth(100); 
      
      //Setting the preserve ratio of the image view 
      logo.setPreserveRatio(true);  
      
      
      Label title = new Label();
      title.setText("To-Do List");
      title.setLayoutX(250);
      title.setFont(new Font("Arial", 24));
      
       
        
        TextField taskTextbox = new TextField();
        taskTextbox.setPrefSize(300, 50);
        taskTextbox.setLayoutX(150);
        taskTextbox.setLayoutY(100);
        taskTextbox.setPromptText("Enter your Task....");
  
        
        DatePicker datePicker = new DatePicker();
        datePicker.setLayoutX(150);
        datePicker.setLayoutY(180);

        
        TextField descriptionTextbox = new TextField();
        descriptionTextbox.setPrefSize(300, 100);
        descriptionTextbox.setLayoutX(150);
        descriptionTextbox.setLayoutY(230);
        descriptionTextbox.setPromptText("Enetr description for task... ");
        

        
        TextField subTasksTextbox = new TextField();
        subTasksTextbox.setLayoutX(150);
        subTasksTextbox.setLayoutY(340);

        
        TextField priorityTextbox = new TextField();
        priorityTextbox.setLayoutX(150);
        priorityTextbox.setLayoutY(380);
        
        
        Button createTaskBtn = new Button();
        createTaskBtn.setText("Create Task");
        createTaskBtn.setPrefSize(300, 50);
        createTaskBtn.setLayoutX(150);
        createTaskBtn.setLayoutY(430);
        createTaskBtn.setFont(new Font("Arial", 20));

        
        Rectangle bottomBorder = new Rectangle(600, 20);
        bottomBorder.setLayoutX(0);
        bottomBorder.setLayoutY(580);
        bottomBorder.setFill(Color.DEEPSKYBLUE);
        
        createTaskBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
             public void handle(ActionEvent event) {
              task = taskTextbox.getText();
              date = datePicker.getValue();
              taskList.add(task + " " + date);
              //creates a new Task Object
              //Task newTask = new Task();
              
              
            }
        });
        
       
        root.getChildren().add(logo);
        root.getChildren().add(title);
        root.getChildren().add(createTaskBtn);
        root.getChildren().add(taskTextbox);
        root.getChildren().add(datePicker);
        root.getChildren().add(descriptionTextbox);
        root.getChildren().add(subTasksTextbox);
        root.getChildren().add(priorityTextbox);
        root.getChildren().add(bottomBorder);
        
        newWindow.show();
        
    }
    
    
    public void listScreen(){
                   
        Stage newWindow = new Stage();
               
        newWindow.setHeight(300);
        newWindow.setWidth(300);
 
        Group root = new Group();
        Scene scene = new Scene(root, 600, 600);
        
        newWindow.setTitle("List Screen");
        newWindow.setScene(scene);
        
        Image image = new Image("resources/logo.png");  
      
      //Setting the image view 
      ImageView logo = new ImageView(image); 
      
      //Setting the position of the image 
      logo.setX(0); 
      logo.setY(0); 
      
      //setting the fit height and width of the image view 
      logo.setFitHeight(50); 
      logo.setFitWidth(50); 
      
      //Setting the preserve ratio of the image view 
      logo.setPreserveRatio(true);  
      
      Label title = new Label();
      title.setText("To-Do List");
      title.setLayoutX(100);
      title.setFont(new Font("Arial", 15));
      
       Button createListBtn = new Button();
        createListBtn.setText("Create List");
        createListBtn.setLayoutX(150);
        createListBtn.setLayoutY(100);
        
        TextField listTextbox = new TextField();
        listTextbox.setLayoutX(0);
        listTextbox.setLayoutY(100);
        listTextbox.setPromptText("Enter New List....");
        
        Rectangle bottomBorder = new Rectangle(300, 10);
        bottomBorder.setLayoutX(0);
        bottomBorder.setLayoutY(255);
        bottomBorder.setFill(Color.DEEPSKYBLUE);
        
        createListBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
             public void handle(ActionEvent event) {
              list = listTextbox.getText();
              lystList.add(list);
            }
        });
        
        
       root.getChildren().add(logo);
       root.getChildren().add(title);
       root.getChildren().add(createListBtn);
       root.getChildren().add(listTextbox);
       root.getChildren().add(bottomBorder);
      
      newWindow.show();
  
    }
    

    
}

