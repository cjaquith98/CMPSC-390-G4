package todolist;

import java.io.FileNotFoundException;
import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;


import todolist.Task;

    @SuppressWarnings("unchecked")
public class WriteJson {


    @SuppressWarnings("unchecked")
    public static void WriteJsonStorage(Task t) throws FileNotFoundException {
            //Creating local object Task to test in json storage     
            LocalDate date = t.getDueDate();
            String title = t.getTitle();
            int importance = t.getImportance();
            String desc = t.getDescription();
            //Creating a JSONObject object
            JSONObject taskDetails = new JSONObject();
            //Inserting key-value pairs into the json object
            taskDetails.put("Title:", title);
            taskDetails.put("Date:", date);
            taskDetails.put("Level of Importance:", importance);
            taskDetails.put("Description:", desc);

            JSONObject taskObject = new JSONObject();
            taskObject.put(date, taskDetails);

            //putting objects into json array
            JSONArray array2 = new JSONArray();
            array2.add(taskDetails);

            PrintWriter file = null;
            try{
                file = new PrintWriter("V:\\Computer Programming\\CMPSC-390-G4\\ToDoList\\Storage.json");
                file.write(taskDetails.toJSONString());
                
            } catch (IOException e) {
                e.printStackTrace();
                
            }

    
}
    }
