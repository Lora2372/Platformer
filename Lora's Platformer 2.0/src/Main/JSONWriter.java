package Main;

import java.io.File;
import java.io.IOException;  
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.json.simple.JSONObject;  

public class JSONWriter 
{  
  
    @SuppressWarnings("unchecked")
	public void writeFile() 
    {  
  
        JSONObject countryObj = new JSONObject();  
        countryObj.put("Name", "Lora");  
        countryObj.put("Level", "DeepWoods");  
  
        try 
        {  
            File myFile = new File(System.getProperty("user.home") + "\\Loras Platformer");
            myFile.mkdir();
            
            Path path = Paths.get(System.getProperty("user.home") + "\\Loras Platformer\\saveFile.json");
         
            OutputStream outputStream = Files.newOutputStream(path);
       
            outputStream.write(countryObj.toJSONString().getBytes(Charset.forName("UTF-8")));
            outputStream.flush();
            outputStream.close();
        } 
        catch (IOException e) 
        {  
            e.printStackTrace();  
        }  
    }
} 