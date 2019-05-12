/*
 * To change this license header, choose License Headers in Project Initerties.
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
public class menu2 {

    
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
    
    //inisiasi pemanggilan menu 1 buat perhitungan block data dan block index
    menu1 menu1 = new menu1();
    
    public void blockdataIndex() {
        //masukkan csv users ke array tablesUsers
        try (BufferedReader br = new BufferedReader(new FileReader(users))) {
            while ((line = br.readLine()) != null) {
                // use comma as separator
                tableUsers = line.split(csvSplitBy);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //selesai
        
        //masukkan csv treatment ke array tablesTreatment
        try (BufferedReader br1 = new BufferedReader(new FileReader(transactions))) {
            while ((line = br1.readLine()) != null) {
                // use comma as separator
                tableTransactions = line.split(csvSplitBy);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //selesai
        
        //masukkan csv memiliki ke array tablesMemiliki
        try (BufferedReader br1 = new BufferedReader(new FileReader(kendaraan))) {
            while ((line = br1.readLine()) != null) {
                // use comma as separator
                tableKendaraan = line.split(csvSplitBy);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        //masukkan csv init ke array tablesInit
        try (BufferedReader br1 = new BufferedReader(new FileReader(init))) {
            while ((line = br1.readLine()) != null) {
                // use comma as separator
                tableInit = line.split(csvSplitBy);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //selesai     
        
        
        int P = Integer.valueOf(tableInit[0]);
        int B = Integer.valueOf(tableInit[1]);
        int Ru = Integer.valueOf(tableUsers[7]);
        int nu = Integer.valueOf(tableUsers[8]);
        int vu = Integer.valueOf(tableUsers[9]);
        int Rt = Integer.valueOf(tableTransactions[4]);
        int nt = Integer.valueOf(tableTransactions[5]);
        int vt = Integer.valueOf(tableTransactions[6]);
        int Rk = Integer.valueOf(tableKendaraan[6]);
        int nk = Integer.valueOf (tableKendaraan[7]);
        int vk = Integer.valueOf(tableKendaraan[8]);
        
        showUsers("Users", B, P, Ru, nu, vu);
        showTransactions("Transactions", B, P, Rt, nt, vt);
        showKendaraan("Kendaraan", B, P, Rk, nk, vk);
    
    }
    
    public void showUsers(String name, int B, int P, int R, int n, int v) {
        double x = countBlockData(B,R,n);
        double y = countBlockIndex(B,P,n,v);
        System.out.println("Tabel Data - "+name+" : "+x);
        System.out.println("Index - "+name+" : "+y);
        System.out.println("__________________________________________");
    }
    
    public void showTransactions(String name, int B, int P, int R, int n, int v) {
        double x = countBlockData(B,R,n);
        double y = countBlockIndex(B,P,n,v);
        System.out.println("Tabel Data - "+name+" : "+x);
        System.out.println("Index - "+name+" : "+y);
        System.out.println("__________________________________________");
    }
    
    public void showKendaraan(String name, int B, int P, int R, int n, int v) {
        double x = countBlockData(B,R,n);
        double y = countBlockIndex(B,P,n,v);
        System.out.println("Tabel Data - "+name+" : "+x);
        System.out.println("Index - "+name+" : "+y);
    }
    
    public double countBlockData(int B, int R, int n){
        double x = menu1.countBfr(B, R);
        double y = n/x;
        double hasil = Math.ceil(y);
        return hasil;
    }
    
    public double countBlockIndex(int B, int P, int n, int v) {
        double x = menu1.countFanoutRatio(B, P, v);
        double y = n/x;
        double hasil = Math.ceil(y);
        return hasil;
    }
}
