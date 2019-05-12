/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes_sbd;
import java.io.*;
import java.util.*;
import java.nio.*; 
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;

/**
 *
 * @author chvik
 */
public class Tubes_sbd {

     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      
            menu1 menu1 = new menu1();
            menu2 menu2 = new menu2();
            menu3 menu3 = new menu3();
            menu4 menu4 = new menu4();
            menu5 menu5 = new menu5();

            System.out.println(">> Menu Utama");
            System.out.println("1. BFR dan Fanout Ratio Tiap Tabel");
            System.out.println("2. Blok Data + Blok Index");
            System.out.println("3. Jumlah Blok Untuk Pencarian Record");
            System.out.println("4. QEP dan Cost");
            System.out.println("5. Isi Shared Pool");
            System.out.println(">> Pilih menu :......");
            Scanner pil = new Scanner (System.in);
            int pilihan =pil.nextInt();

            System.out.println("__________________________________________");
            if(pilihan==1){
                menu1.BFRandFR();
            }else if(pilihan==2){
                menu2.blockdataIndex();
            }else if(pilihan==3){
                menu3.record();
            }else if(pilihan==4){
                 menu4.cekQuery();
            }else if(pilihan==5){
                menu5.readSharedPool();
            }else{
                System.out.println("Menu Tidak Valid");
            }

            System.out.println("__________________________________________");
        
    }
}
