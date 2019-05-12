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
public class menu3 {
    
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
    
    menu1 menu1 = new menu1();
    
    public void record() {
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
        
        //selesai 
        
        try (BufferedReader br1 = new BufferedReader(new FileReader(init))) {
            while ((line = br1.readLine()) != null) {
                tableInit = line.split(csvSplitBy);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
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
        
        String namaUsers = tableUsers[0];       
        String namaTransactions = tableTransactions[0];
        String namaKendaraan = tableKendaraan[0];
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Cari Record ke- : ");
        int rec = sc.nextInt();
        System.out.println("__________________________________________");
        System.out.println("Nama Tabel : ");
        String name = sc.next();
        System.out.println("__________________________________________");
        
        if (rec <= nu && namaUsers.equals(name)) {
            double i = countWithIndex(B, P, vu, rec);
            double j = countWithoutIndex(B, Ru, rec);
            System.out.println("Menggunakan indeks, jumlah blok yang diakses : "+i+" blok");
            System.out.println("Tanpa indeks, jumlah blok yang diakses : "+j+" blok");    
            
        } else if (rec <= nt && namaTransactions.equals(name)) {
            double i = countWithIndex(B, P, vt, rec);
            double j = countWithoutIndex(B, Rt, rec);
            System.out.println("Menggunakan indeks, jumlah blok yang diakses : "+i+" blok");
            System.out.println("Tanpa indeks, jumlah blok yang diakses : "+j+" blok");
            
        } else if (rec <= nt && namaKendaraan.equals(name)) {
            double i = countWithIndex(B, P, vk, rec);
            double j = countWithoutIndex(B, Rk, rec);
            System.out.println("Menggunakan indeks, jumlah blok yang diakses : "+i+" blok");
            System.out.println("Tanpa indeks, jumlah blok yang diakses : "+j+" blok");
            
        } else if ((rec>nu) || (rec>nt) || (rec>nk)){
            System.out.println("Rekord Melebihi Batas");
        }     
        
        else {
            System.out.println("Tabel Salah atau Tidak Ditemukan");
        }
    }
    
    //rekord ke i / fanoutratio
    public double countWithIndex(int B, int P, int v, int x){
        double fr = menu1.countFanoutRatio(B, P, v);
        double a = x/fr;
        double hasil = Math.ceil(a);
        return hasil;
    }
    
    //rekord ke i / bfr
    public double countWithoutIndex(int B, int R, int x){
        double bfr = menu1.countBfr(B, R);
        double a = x/bfr;
        double hasil = Math.ceil(a);
        return hasil;
    }
}
