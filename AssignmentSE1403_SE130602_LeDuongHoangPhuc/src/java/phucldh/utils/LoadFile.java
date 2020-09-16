/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phucldh.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author DELL
 */
public class LoadFile {

    Map<String, String> list;

    public LoadFile(Map<String, String> list) {
        this.list = list;
    }

    public LoadFile() {
        
    }

    public Map<String, String> getList() {
        return list;
    }

    public void setList(Map<String, String> list) {
        this.list = list;
    }

    public void load(String file_path) throws FileNotFoundException, IOException{

   
        File file = new File(file_path.trim());

        FileReader reader = new FileReader(file_path);
        BufferedReader br = new BufferedReader(reader);
        String line = br.readLine();
        while (line != null) {

//            System.out.println(line);
            String[] part = line.split("=");
            if(part.length == 2){
                 String key = part[0];
                String value = part[1];
                if (this.list == null) {
                    this.list = new HashMap<>();
                }
                this.list.put(key, value);
            }
            line = br.readLine();
        }
        br.close();
        reader.close();
     
        }
        
    
}
