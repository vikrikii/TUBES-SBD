/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes_sbd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author chvik
 */
public class menu5 {
    
    String line="";
    public void readSharedPool(){
    try (BufferedReader br1 = new BufferedReader(new FileReader("F:\\KULYEAH!1!1\\4. SEMESTER 4\\SBD\\Tubes Imoet\\src\\shared_pool.txt")))
        {
            while ((line = br1.readLine()) != null) {
                // use comma as separator
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

