package yoccojack;

import java.io.*;
import java.util.*;
import java.net.*;
import com.google.gson.Gson;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

public class Yoccojack {
       private static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try{
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);
            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }
    
    
    static class draws{
        String playerA;
        String playerB;
    }
    
    static class plays {
        String playerA;
        String playerB;
        List<draws> items;
    }
    
  public static void main(String[] args) throws Exception {
      String json = readUrl("https://s3-eu-west-1.amazonaws.com/" + 
              "yoco-testing/tests.json");
      Gson gson = new Gson();
      plays page =  gson.fromJson(json, plays.class);
      
      System.out.println(page.playerA);
      for (draws item : page.items)
          System.out.println(" " + item.playerA);
  }
}
