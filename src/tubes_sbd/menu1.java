/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes_sbd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
/**
 *
 * @author chvik
 */
public class menu1 {
    
    //retrieve file
   String init = "F:\\KULYEAH!1!1\\4. SEMESTER 4\\SBD\\Tubes Imoet\\src\\file\\init.csv";
   String kendaraan = "F:\\KULYEAH!1!1\\4. SEMESTER 4\\SBD\\Tubes Imoet\\src\\file\\kendaraan.csv";
   String transactions = "F:\\KULYEAH!1!1\\4. SEMESTER 4\\SBD\\Tubes Imoet\\src\\file\\transactions.csv";
   String users = "F:\\KULYEAH!1!1\\4. SEMESTER 4\\SBD\\Tubes Imoet\\src\\file\\users.csv";
    
   
    String line = "";
    String csvSplitBy = ",";
    String[] tableKendaraan = new String[100];
    String[] tableTransactions = new String[100];
    String[] tableUsers = new String[100];
    String[] tableInit = new String[100];
    
    public void BFRandFR() {
        //masukkan data pada csv kendaraan kedalam array tableKendaraan
        try (BufferedReader br = new BufferedReader(new FileReader(kendaraan))) {
            while ((line = br.readLine()) != null) {
                tableKendaraan = line.split(csvSplitBy); 
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        //masukkan data pada csv transactions kedalam array tableTransactions
        try (BufferedReader br1 = new BufferedReader(new FileReader(transactions))) {
            while ((line = br1.readLine()) != null) {
                tableTransactions = line.split(csvSplitBy);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        //masukkan data pada csv users kedalam array tableUsers
        try (BufferedReader br1 = new BufferedReader(new FileReader(users))) {
            while ((line = br1.readLine()) != null) {
                tableUsers = line.split(csvSplitBy);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        try (BufferedReader br1 = new BufferedReader(new FileReader(init))) {
            while ((line = br1.readLine()) != null) {
                tableInit = line.split(csvSplitBy);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
         //selesai
         
        int P = Integer.valueOf(tableInit[0]);
        int B = Integer.valueOf(tableInit[1]);
        int Ru = Integer.valueOf(tableUsers[7]);
        int vu = Integer.valueOf(tableUsers[9]);
        int Rt = Integer.valueOf(tableTransactions[4]);
        int vt = Integer.valueOf(tableTransactions[6]);
        int Rk = Integer.valueOf(tableKendaraan[6]);
        int vk = Integer.valueOf(tableKendaraan[8]);
        
        showUsers("Users", B, P, Ru, vu);
        showTransactions("Transactions", B, P, Rt, vt);
        showKendaraan("Kendaraan", B, P, Rk, vk);
    }
    
    
    //function - function
    public void showUsers(String name, int B, int P, int R, int v) {
        double x = countBfr(B,R);
        double y = countFanoutRatio(B,P,v);
        System.out.println("BFR - Tabel "+name+" : "+x);
        System.out.println("Fan Out Ratio - Tabel "+name+" : "+y);
        System.out.println("__________________________________________");
    }
    
    public void showTransactions(String name, int B, int P, int R, int v) {
        double x = countBfr(B,R);
        double y = countFanoutRatio(B,P,v);
        System.out.println("BFR - Tabel "+name+" : "+x);
        System.out.println("Fan Out Ratio - Tabel "+name+" : "+y);
        System.out.println("__________________________________________");
    }
    
    public void showKendaraan(String name, int B, int P, int R, int v) {
        double x = countBfr(B,R);
        double y = countFanoutRatio(B,P,v);
        System.out.println("BFR - Tabel "+name+" : "+x);
        System.out.println("Fan Out Ratio - Tabel "+name+" : "+y);
    }
    
    //function hitung BFR dan FR
    public double countBfr(int B, int R){
        int x = B/R;
        double hasil = Math.floor(x);
        return hasil;
    }
    
    public double countFanoutRatio(int B, int P, int v) {
        int y = B/(v+P);
        double hasil = Math.ceil(y);
        return hasil;
    }
}
