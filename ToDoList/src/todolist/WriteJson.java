package todolist;

import org.json.simple.JSONObject;


import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import todolist.Task;

    @SuppressWarnings("unchecked")
public class WriteJson {        
        public static void jsonStorage(){
        //Creating local object Task to test in json storage     
            LocalDate testDate = LocalDate.of(2020,3,30);
            String title = "TestTask";
            int importance = 1;
            String desc = "This is a test description";
            Task testTask = new Task(title, testDate, importance, desc);            
        //Creating a JSONObject object
            JSONObject jsonObject = new JSONObject();
        //Inserting key-value pairs into the json object
            jsonObject.put("Title:", title);
            jsonObject.put("Date:", testDate);
            jsonObject.put("Leve of Importance:", importance);
            jsonObject.put("Description:", desc);

      try {
         FileWriter file = new FileWriter("Storage.json");
         file.write(jsonObject.toJSONString());
         file.close();
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      System.out.println("JSON file created: "+jsonObject);
   }
}
