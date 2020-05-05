package todolist;

import java.io.*;
import com.google.gson.*;
import com.google.gson.stream.*;
import com.google.gson.annotations.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import javafx.collections.ObservableList;
import todolist.Task;
import todolist.TaskList;



    @SuppressWarnings("unchecked")
public class WriteJson {     
        public static String dateRead;
        public static void jsonTaskStorage(ArrayList<Task> t) throws IOException {
            Gson input = new Gson();//create gson object     
            String input2 = input.toJson(t); //convert to string to write to json
            FileWriter file = new FileWriter("V:\\Computer Programming\\CMPSC-390-G4\\ToDoList\\Storage.json");
            try{
                file.write(input2); //writing object to json file
                System.out.println("Successfully copied object to file...");
                System.out.println("\nJSON Object: " + input2);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                file.flush();
                file.close();
            }
    }
    }
