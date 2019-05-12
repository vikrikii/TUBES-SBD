/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes_sbd;

import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author chvik
 */
public class File_Input {
    
    public void inputBasic(String[]query, String[]projection, String selection,String table, String hasil){
    try(FileWriter fw = new FileWriter("F:\\KULYEAH!1!1\\4. SEMESTER 4\\SBD\\Tubes Imoet\\src\\shared_pool.txt")) {  
        fw.write("Query : ");
        for (int i = 0; i < query.length; i++) {
            fw.write(query[i]+" ");
        }
        fw.write("projection : ");
        for (int i = 0; i < projection.length; i++) {
            fw.write(projection[i]+" ");
        }
        fw.write("selection : ");
        fw.write(selection+" ");

        fw.write(table);
        fw.write("cost : "+hasil);
        

    } catch (IOException e) {
    // exception handling
        System.out.println("Tidak Masuk");
    }  
    
    }
    public void inputJoin(String[]query, String[]projection, String[]join,String table,String tableJ, String hasil){
    try(FileWriter fw = new FileWriter("F:\\KULYEAH!1!1\\4. SEMESTER 4\\SBD\\Tubes Imoet\\src\\shared_pool.txt")) {  
        fw.write("Query : ");
        for (int i = 0; i < query.length; i++) {
            fw.write(query[i]+" ");
        }
        fw.write("projection : ");
        for (int i = 0; i < projection.length; i++) {
            fw.write(projection[i]+" ");
        }

        fw.write("Join : ");
        for (int i = 0; i < join.length; i++) {
            fw.write(join[i]+" ");
        }
        fw.write(table+ " "+tableJ);
        fw.write("cost : "+hasil);
        

    } catch (IOException e) {
    // exception handling
        System.out.println("Tidak Masuk");
    }  
    
    }
    
    public void inputJoin1(String[]query, String[]projection,String[]projection1, String[]join,String table,String tableJ, String hasil){
    try(FileWriter fw = new FileWriter("F:\\KULYEAH!1!1\\4. SEMESTER 4\\SBD\\Tubes Imoet\\src\\shared_pool.txt")) {  
        fw.write("Query : ");
        for (int i = 0; i < query.length; i++) {
            fw.write(query[i]+" ");
        }
        fw.write("projection : ");
        for (int i = 0; i < projection.length; i++) {
            fw.write(projection[i]+" ");
        }
         for (int i = 0; i < projection1.length; i++) {
            fw.write(projection1[i]+" ");
        }

        fw.write("Join : ");
        for (int i = 0; i < join.length; i++) {
            fw.write(join[i]+" ");
        }
        fw.write(table+ " "+tableJ);
        fw.write("cost : "+hasil);
        
    } catch (IOException e) {
    // exception handling
        System.out.println("Tidak Masuk");
    }  
    
    }
}