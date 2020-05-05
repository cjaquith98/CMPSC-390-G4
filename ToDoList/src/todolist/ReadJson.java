
package todolist;


import java.time.LocalDate;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import com.google.gson.*;
import com.google.gson.stream.*;
import com.google.gson.annotations.*;


public class ReadJson {
    
    
    public static void readJson(){
    JSONParser parser = new JSONParser();
    
    try(FileReader file = new FileReader("V:\\Computer Programming\\CMPSC-390-G4-master\\ToDoList\\Storage.json")){
        Object obj = parser.parse(file); //parsing file
        JSONObject jsonObject = (JSONObject) obj; //taking jsonobject from file and bringing into program
        
        //creating local date and parsing to string
        LocalDate date = LocalDate.now();
        String lDate = date.toString();
        
        //displays the json object with the local date 
        String name = (String) jsonObject.get(lDate).toString();
        
        System.out.println("Task is: "+ name);
        
        
    }
    catch(FileNotFoundException e){e.printStackTrace();}
    catch(IOException e){e.printStackTrace();}
    catch(ParseException e){e.printStackTrace();}
    catch(Exception e){e.printStackTrace();}
    
    
    }
}
