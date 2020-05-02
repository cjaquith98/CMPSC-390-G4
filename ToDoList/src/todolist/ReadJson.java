package todolist;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Iterator;

import todolist.Task;


public class ReadJson {

    @SuppressWarnings("unchecked")
    public static void WriteJsonStorage(LocalDate d) {
        JSONParser jsonParser = new JSONParser();
        
        try {
            //reading json file in
            FileReader reader = new FileReader("Storage.json");
            
            //parsing 
            JSONObject jsonObj = (JSONObject) jsonParser.parse(reader);
            JSONArray jsonArr = (JSONArray) jsonObj.get("");

            Iterator<?> i = jsonArr.iterator();
            
            //loop to go through json file
            while (i.hasNext()) {
                JSONObject obj = (JSONObject) i.next();
                int lvl = (int) obj.get("Level of Importance");
                String title = (String) obj.get("Title"); 
                String desc = (String) obj.get("Description");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

