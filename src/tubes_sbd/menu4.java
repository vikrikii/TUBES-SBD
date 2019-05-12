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
 
public class menu4 {

   String init = "F:\\KULYEAH!1!1\\4. SEMESTER 4\\SBD\\Tubes Imoet\\src\\file\\init.csv";
   String kendaraan = "F:\\KULYEAH!1!1\\4. SEMESTER 4\\SBD\\Tubes Imoet\\src\\file\\kendaraan.csv";
   String transactions = "F:\\KULYEAH!1!1\\4. SEMESTER 4\\SBD\\Tubes Imoet\\src\\file\\transactions.csv";
   String users = "F:\\KULYEAH!1!1\\4. SEMESTER 4\\SBD\\Tubes Imoet\\src\\file\\users.csv";
    
   
    
   String line = "";
    String csvSplitBy = ",";
    Boolean cek = false;
    String[] tableUsers = new String[100];
    String[] tableKendaraan = new String[100];
    String[] tableTransactions = new String[100];
    String[] tableInit = new String[100];
    String[] arrayOfQuery = new String[100];
    String[] arrayOfColumn = new String[100];
    String[] arrayOfColumn1 = null;
    String[] arrayWhere = null;
    String[] tabel=null;
    String[] arrayOfAll = new String[7];
    String[] arrayOfAllJ = new String[7];
    String hasilcost;
    String hasil;

    public void cekQuery(){
        //masukkan csv users ke array tableUsers
        try (BufferedReader br = new BufferedReader(new FileReader(users))) {
            while ((line = br.readLine()) != null) {
                // use comma as separator
                tableUsers = line.split(csvSplitBy);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //selesai
        
        //masukkan csv kendaraan ke array tableKendaraan
        try (BufferedReader br1 = new BufferedReader(new FileReader(kendaraan))) {
            while ((line = br1.readLine()) != null) {
                // use comma as separator
                tableKendaraan = line.split(csvSplitBy);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //selesai
        
        //masukkan csv transactions ke array tableTransactions
        try (BufferedReader br1 = new BufferedReader(new FileReader(transactions))) {
            while ((line = br1.readLine()) != null) {
                // use comma as separator
                tableTransactions = line.split(csvSplitBy);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //selesai
        
        //masukkan csv init ke array tableInit
        try (BufferedReader br1 = new BufferedReader(new FileReader(init))) {
            while ((line = br1.readLine()) != null) {
                // use comma as separator
                tableInit = line.split(csvSplitBy);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //selesai
        
        System.out.println("enter query :");
            Scanner scanner = new Scanner (System.in);
            String query = scanner.nextLine();
            arrayOfQuery=query.split(" ");
        

            if(arrayOfQuery[0].equals("select") && arrayOfQuery[2].equals("from") && arrayOfQuery[arrayOfQuery.length-1].charAt(arrayOfQuery[arrayOfQuery.length-1].length()-1)==';'){
                arrayOfColumn=arrayOfQuery[1].split(",");
                tabel = arrayOfQuery[3].split(" ");
                arrayWhere = arrayOfQuery[5].split("=");
                String where = arrayWhere[0];
                System.out.println(where);
                File_Input input = new File_Input();
                if(tabel[0].equals(tableUsers[0])){
                    
                    int P = Integer.valueOf(tableInit[0]);
                    int B = Integer.valueOf(tableInit[1]);
                    int v = Integer.valueOf(tableUsers[9]);
                    int n = Integer.valueOf(tableUsers[8]);
                    int R = Integer.valueOf(tableUsers[7]);
                    int R1 = Integer.valueOf(tableTransactions[4]);
                    int n1 = Integer.valueOf(tableTransactions[5]);
                    double bfr = countBfr(B, R);
                    double b = countb(n, bfr);
                    String TableName ="users";
                  
                if((arrayOfQuery.length <= 6)&&(!arrayOfQuery[1].equals("*"))){
                    System.out.println("Table Name : "+TableName);
                    System.out.println("Column List : ");
                    for (int i = 0; i < arrayOfColumn.length; i++) {
                        for(int j=0 ; j< tableUsers.length; j++){
                                if(arrayOfColumn[i].equals(tableUsers[j])){
                                System.out.println("- "+arrayOfColumn[i]+",");
                        }
                      }
                    }
                    System.out.println("");
                    System.out.println("#QEP 1");
                    System.out.println("Projection ");
                    for (int i = 0; i < arrayOfColumn.length; i++) {
                        for(int j=0 ; j< tableUsers.length; j++){
                                if(arrayOfColumn[i].equals(tableUsers[j])){
                                System.out.println("- "+arrayOfColumn[i]+",");
                        }
                      }
                    }
       
                    System.out.println("Selection ");
                    if(where.equals(tableUsers[1])){
                        System.out.println(arrayOfQuery[5]+" -- A1");
                    
                        System.out.println(TableName);
                        //cost with key
                        double costA1 = countA1Key(b);
                        System.out.println("cost : "+costA1);
                    
                        System.out.println("");
                    
                        System.out.println("#QEP 2");
                        System.out.println("Projection ");
                        for (int i = 0; i < arrayOfColumn.length; i++) {
                            for(int j=0 ; j< tableUsers.length; j++){
                                if(arrayOfColumn[i].equals(tableUsers[j])){
                                System.out.println("- "+arrayOfColumn[i]+",");
                                }
                            }
                        }
                        System.out.println("Selection ");
                        System.out.println(arrayOfQuery[5]+" -- A2");

                        System.out.println(TableName);

                        double costA2 =countA2(b, B, v, P) ;
                        System.out.println("cost : "+costA2);
                        if(costA1>costA2){
                            System.out.println("QEP Optimal #QEP2");
                            hasilcost = Double.toString(costA2);
                            hasil = (hasilcost+" --A2");
                        }else{
                            System.out.println("QEP Optimal #QEP1");
                            hasilcost = Double.toString(costA1);
                            hasil = (hasilcost+" --A1");
                        }
                        input.inputBasic(arrayOfQuery, arrayOfColumn, arrayOfQuery[5], TableName, hasil);
                    }else{
                        System.out.println(arrayOfQuery[5]+" -- A1");
                    
                        System.out.println(TableName);
                        //cost with non-key
                        double costA1 = countA1NKey(b);
                        System.out.println("cost : "+costA1);
                    
                        System.out.println("");
                    
                        System.out.println("#QEP 2");
                        System.out.println("Projection ");
                        for (int i = 0; i < arrayOfColumn.length; i++) {
                            for(int j=0 ; j< tableUsers.length; j++){
                                if(arrayOfColumn[i].equals(tableUsers[j])){
                                    System.out.println("- "+arrayOfColumn[i]+",");
                                }
                            }
                        }
                        System.out.println("Selection ");
                        System.out.println(arrayOfQuery[5]+" -- A3");

                        System.out.println(TableName);
                        
                        double costA3 =countA3(b, B, v, P) ;
                        System.out.println("cost : "+costA3);
                        if(costA1>costA3){
                            System.out.println("QEP Optimal #QEP2");
                            hasilcost = Double.toString(costA3);
                            hasil = (hasilcost+" --A3");
                        }else{
                            System.out.println("QEP Optimal #QEP1");
                            hasilcost = Double.toString(costA1);
                            hasil = (hasilcost+" --A1");
                        }
                    }
                    input.inputBasic(arrayOfQuery, arrayOfColumn, arrayOfQuery[5], TableName, hasil);
                    
                }else if((arrayOfQuery.length <= 6)&&(arrayOfQuery[1].equals("*"))){
                   System.out.println("Table Name : "+TableName);
                   System.out.println("Column List : ");
                       for (int i = 1; i < tableUsers.length-3; i++) {
                           System.out.println("- "+tableUsers[i]);
                           
                       }
                    System.out.println("");
                    System.out.println("#QEP 1");
                    System.out.println("Projection ");
                    for (int i = 1; i < tableUsers.length-3; i++) {
                           System.out.println("- "+tableUsers[i]);
                           arrayOfAll[i-1]=tableUsers[i];
                           
                       }
                    System.out.println("Selection ");
                    if(where.equals(tableUsers[1])){
                        System.out.println(arrayOfQuery[5]+" -- A1");
                    
                        System.out.println(TableName);
                        //cost with key
                        double costA1 = countA1Key(b);
                        System.out.println("cost : "+costA1);
                    
                        System.out.println("");
                    
                        System.out.println("#QEP 2");
                        System.out.println("Projection ");
                        for (int i = 1; i < tableUsers.length-3; i++) {
                            System.out.println("- "+tableUsers[i]);
                            arrayOfAll[i-1]=tableUsers[i];
                           
                        }
                        System.out.println("Selection ");
                        System.out.println(arrayOfQuery[5]+" -- A2");

                        System.out.println(TableName);

                        double costA2 =countA2(b, B, v, P) ;
                        System.out.println("cost : "+costA2);
                        if(costA1>costA2){
                            System.out.println("QEP Optimal #QEP2");
                            hasilcost = Double.toString(costA2);
                            hasil = (hasilcost+" --A2");
                        }else{
                            System.out.println("QEP Optimal #QEP1");
                            hasilcost = Double.toString(costA1);
                            hasil = (hasilcost+" --A1");
                        }
                        input.inputBasic(arrayOfQuery, arrayOfAll, arrayOfQuery[5], TableName, hasil);
                    }else{
                        System.out.println(arrayOfQuery[5]+" -- A1");
                    
                        System.out.println(TableName);
                        //cost with non-key
                        double costA1 = countA1NKey(b);
                        System.out.println("cost : "+costA1);
                    
                        System.out.println("");
                    
                        System.out.println("#QEP 2");
                        System.out.println("Projection ");
                        for (int i = 1; i < tableUsers.length-3; i++) {
                            System.out.println("- "+tableUsers[i]);
                           
                        }
                        System.out.println("Selection ");
                        System.out.println(arrayOfQuery[5]+" -- A3");

                        System.out.println(TableName);

                        double costA3 =countA3(b, B, v, P) ;
                        System.out.println("cost : "+costA3);
                        if(costA1>costA3){
                            System.out.println("QEP Optimal #QEP2");
                            hasilcost = Double.toString(costA3);
                            hasil = (hasilcost+" --A3");
                        }else{
                            System.out.println("QEP Optimal #QEP1");
                            hasilcost = Double.toString(costA1);
                            hasil = (hasilcost+" --A1");
                        }
                        input.inputBasic(arrayOfQuery, arrayOfAll, arrayOfQuery[5], TableName, hasil);
                    }
                    
                    
                } else{
                    if(arrayOfQuery[4].equals("join")&&(arrayOfQuery[1].equals("*"))){
                        String TableJoined = "";
                        if(arrayOfQuery[5].equals(tableTransactions[0])){
                            TableJoined=tableTransactions[0];
                        }
                        if(arrayOfQuery[6].equals("using")){
                            arrayOfColumn1=arrayOfQuery[7].split(";");
                        }
                        System.out.println("Table Name : "+TableName);
                        System.out.println("Column List : ");
                        for (int i = 1; i < tableUsers.length-3; i++) {
                           System.out.println(tableUsers[i]+", ");
                           arrayOfAll[i-1] = tableUsers[i];
                        }
                        System.out.println("Table Join : "+TableJoined);
                        System.out.println("Column List : ");  
                        for (int i = 0; i < arrayOfColumn1.length; i++) {
                            for(int j=0 ; j< tableTransactions.length; j++){
                                if(TableJoined.equals(tableTransactions[0])){
                                    if(arrayOfColumn1[i].equals(tableTransactions[j])){
                                        System.out.println(arrayOfColumn1[i]+", ");
                                    }
                                }
                            }
                        }
                        System.out.println("#QEP 1");
                        System.out.println("PROJECTION ");
                        for (int i = 1; i < tableUsers.length-3; i++) {
                           System.out.println(tableUsers[i]+", ");
                           arrayOfAll[i-1] = tableUsers[i];
                        }
                        for(int j=1 ; j< tableTransactions.length-3; j++){
                            System.out.println(tableTransactions[j]+", ");
                            arrayOfAllJ[j-1] = tableTransactions[j];
                        }
                        System.out.println("-- on the fly");
                        System.out.println("JOIN");
                        for (int i = 0; i < arrayOfColumn1.length; i++) {
                            for(int j=0 ; j< tableTransactions.length; j++){
                                if(TableJoined.equals(tableTransactions[0])){
                                    if(arrayOfColumn1[i].equals(tableTransactions[j])){
                                        System.out.println(arrayOfColumn1[i]+", ");
                                    }
                                }
                            }
                        }
                        System.out.println("Tables");
                        System.out.println(TableName +"    "+ TableJoined);
                        double bfrL =countBfr(B, R);
                        double bl = countb(n, bfrL);
                        double bfrR  = countBfr(B, R1);
                        double br = countb(n1, bfrR);
                        double cost1 = countBNLJ(br, bl);
                        System.out.println("COST  : "+cost1);

                        System.out.println("");

                        System.out.println("#QEP 2");
                        System.out.println("PROJECTION ");
                        for (int i = 1; i < tableUsers.length-3; i++) {
                           System.out.println(tableUsers[i]+", ");
                           arrayOfAll[i-1] = tableUsers[i];
                        }
                        for(int j=1 ; j< tableTransactions.length-3; j++){
                            System.out.println(tableTransactions[j]+", ");
                            arrayOfAllJ[j-1] = tableTransactions[j];
                        }
                        System.out.println("-- on the fly");
                        System.out.println("JOIN");
                        for (int i = 0; i < arrayOfColumn1.length; i++) {
                            for(int j=0 ; j< tableTransactions.length; j++){
                                if(TableJoined.equals(tableTransactions[0])){
                                    if(arrayOfColumn1[i].equals(tableTransactions[j])){
                                        System.out.println(arrayOfColumn1[i]+", ");
                                    }
                                }
                            }
                        }
                        System.out.println("Tables");
                        System.out.println(TableJoined +"    "+ TableName);
                        double bfrL1 =countBfr(B, R);
                        double bl1 = countb(n, bfrL1);
                        double bfrR1  = countBfr(B, R1);
                        double br1 = countb(n1, bfrR);
                        double cost2 = countBNLJ(bl1, br1);
                        System.out.println("COST : "+cost2);
                        System.out.println("");
                        if(cost1<cost2){
                            System.out.println("QEP OPTIMAL : #QEP 1");
                            hasilcost = Double.toString(cost1);
                            hasil = (hasilcost+" --BNLJ");
                        }else{
                            System.out.println("QEP OPTIMAL : #QEP 2");
                            hasilcost = Double.toString(cost2);
                            hasil = (hasilcost+" --BNLJ");
                        }
                        input.inputJoin1(arrayOfQuery, arrayOfAll, arrayOfAllJ, arrayOfColumn1,TableName,TableJoined, hasil);
                                          
                    }
                else if(arrayOfQuery[4].equals("join")&&(!arrayOfQuery[1].equals("*"))){
                        String TableJoined = "";
                        if(arrayOfQuery[5].equals(tableTransactions[0])){
                            TableJoined=tableTransactions[0];
                        }
                        if(arrayOfQuery[6].equals("using")){
                            arrayOfColumn1=arrayOfQuery[7].split(";");
                        }
                        System.out.println("Table Name : "+TableName);
                        System.out.println("Column List : ");
                        for (int i = 0; i < arrayOfColumn.length; i++) {
                            for(int j=0 ; j< tableUsers.length; j++){
                                if(TableName.equals(tableUsers[0])){
                                    if(arrayOfColumn[i].equals(tableUsers[j])){
                                        System.out.println(arrayOfColumn[i]+", ");
                                    }
                                }
                            }
                        }                   
                        System.out.println("Table Join : "+TableJoined);
                        System.out.println("Column List : ");  
                        for (int i = 0; i < arrayOfColumn1.length; i++) {
                            for(int j=0 ; j< tableTransactions.length; j++){
                                if(TableJoined.equals(tableTransactions[0])){
                                    if(arrayOfColumn1[i].equals(tableTransactions[j])){
                                        System.out.println(arrayOfColumn1[i]+", ");
                                    }
                                }
                            }
                        }
                        System.out.println("");
                        System.out.println("#QEP 1");
                        System.out.println("PROJECTION ");
                        for (int i = 0; i < arrayOfColumn.length; i++) {
                            for(int j=0 ; j< tableUsers.length; j++){
                                if(TableName.equals(tableUsers[0])){
                                    if(arrayOfColumn[i].equals(tableUsers[j])){
                                        System.out.println(arrayOfColumn[i]+", ");
                                    }
                                }
                            }
                        }
                        System.out.println("-- on the fly");
                        System.out.println("JOIN");
                        for (int i = 0; i < arrayOfColumn1.length; i++) {
                            for(int j=0 ; j< tableTransactions.length; j++){
                                if(TableJoined.equals(tableTransactions[0])){
                                    if(arrayOfColumn1[i].equals(tableTransactions[j])){
                                        System.out.println(arrayOfColumn1[i]+", ");
                                    }
                                }
                            }
                        }
                        System.out.println("Tables");
                        System.out.println(TableName +"    "+ TableJoined);
                        double bfrL =countBfr(B, R);
                        double bl = countb(n, bfrL);
                        double bfrR  = countBfr(B, R1);
                        double br = countb(n1, bfrR);
                        double cost1 = countBNLJ(br, bl);
                        System.out.println("COST : "+cost1);

                        System.out.println("");

                        System.out.println("#QEP 2");
                        System.out.println("PROJECTION ");
                        for (int i = 0; i < arrayOfColumn.length; i++) {
                            for(int j=0 ; j< tableUsers.length; j++){
                                if(TableName.equals(tableUsers[0])){
                                    if(arrayOfColumn[i].equals(tableUsers[j])){
                                        System.out.println(arrayOfColumn[i]+", ");
                                    }
                                }
                            }
                        }
                        System.out.println("-- on the fly");
                        System.out.println("JOIN");
                        for (int i = 0; i < arrayOfColumn1.length; i++) {
                            for(int j=0 ; j< tableTransactions.length; j++){
                                if(TableJoined.equals(tableTransactions[0])){
                                    if(arrayOfColumn1[i].equals(tableTransactions[j])){
                                        System.out.println(arrayOfColumn1[i]+", ");
                                    }
                                }
                            }
                        }
                        System.out.println("Tables");
                        System.out.println(TableJoined +"    "+ TableName);
                        double bfrL1 =countBfr(B, R);
                        double bl1 = countb(n, bfrL1);
                        double bfrR1  = countBfr(B, R1);
                        double br1 = countb(n1, bfrR);
                        double cost2 = countBNLJ(bl1, br1);
                        System.out.println("COST : "+cost2);
                        System.out.println("");
                        if(cost1<cost2){
                            System.out.println("QEP OPTIMAL : #QEP 1");
                            hasilcost = Double.toString(cost1);
                            hasil = (hasilcost+" --BNLJ");
                        }else{
                            System.out.println("QEP OPTIMAL : #QEP 2");
                            hasilcost = Double.toString(cost2);
                            hasil = (hasilcost+" --BNLJ");
                        }
                        input.inputJoin(arrayOfQuery, arrayOfColumn,arrayOfColumn1,TableName,TableJoined, hasil);
                        
                    }
                        
                    
                }
                       
                }else if(tabel[0].equals(tableKendaraan[0])){
                int B = Integer.valueOf(tableInit[1]);
                int v = Integer.valueOf(tableKendaraan[8]);
                int n = Integer.valueOf(tableKendaraan[7]);
                int R = Integer.valueOf(tableKendaraan[6]);
                int P = Integer.valueOf(tableInit[0]);
                int R1 = Integer.valueOf(tableTransactions[4]);
                int n1 = Integer.valueOf(tableTransactions[5]);
                double bfr = countBfr(B, R);
                double b = countb(n, bfr);
                String TableName ="";
                TableName=tableKendaraan[0];
                if((arrayOfQuery.length <= 6)&&(!arrayOfQuery[1].equals("*"))){
                    System.out.println("Table Name : "+TableName);
                    System.out.println("Column List : ");
                    for (int i = 0; i < arrayOfColumn.length; i++) {
                        for(int j=0 ; j< tableKendaraan.length-3; j++){
                                if(arrayOfColumn[i].equals(tableKendaraan[j])){
                                System.out.println(arrayOfColumn[i]+",");
                        }
                      }
                    }
                    System.out.println("");
                    System.out.println("#QEP 1");
                    System.out.println("Projection ");
                    for (int i = 0; i < arrayOfColumn.length; i++) {
                        for(int j=0 ; j< tableKendaraan.length; j++){
                                if(arrayOfColumn[i].equals(tableKendaraan[j])){
                                System.out.println("- "+arrayOfColumn[i]+",");
                        }
                      }
                    }
       
                    System.out.println("Selection ");
                    if(where.equals(tableKendaraan[1])){
                        System.out.println(arrayOfQuery[5]+" -- A1");
                    
                        System.out.println(TableName);
                        //cost with key
                        double costA1 = countA1Key(b);
                        System.out.println("cost : "+costA1);
                    
                        System.out.println("");
                    
                        System.out.println("#QEP 2");
                        System.out.println("Projection ");
                        for (int i = 0; i < arrayOfColumn.length; i++) {
                            for(int j=0 ; j< tableKendaraan.length; j++){
                                if(arrayOfColumn[i].equals(tableKendaraan[j])){
                                System.out.println("- "+arrayOfColumn[i]+",");
                                }
                            }
                        }
                        System.out.println("Selection ");
                        System.out.println(arrayOfQuery[5]+" -- A2");
                           
                        System.out.println(TableName);
                        System.out.println("____________________________");
                        
                        double costA2 =countA2(b, B, v, P) ;
                        System.out.println("cost : "+costA2);
                        if(costA1>costA2){
                            System.out.println("QEP Optimal #QEP2");
                            
                            hasilcost = Double.toString(costA2);
                            hasil = (hasilcost+" --A2");
                        }else{
                            System.out.println("QEP Optimal #QEP1");
                            hasilcost = Double.toString(costA1);
                            hasil = (hasilcost+" --A1");
                        }
                        input.inputBasic(arrayOfQuery, arrayOfColumn, arrayOfQuery[5], TableName, hasil);
                    }else{
                        System.out.println(arrayOfQuery[5]+" -- A1");
                    
                        System.out.println(TableName);
                        //cost with non-key

                        double costA1 = countA1NKey(b);
                        System.out.println("cost : "+costA1);
                    
                        System.out.println("");
                    
                        System.out.println("#QEP 2");
                        System.out.println("Projection ");
                        for (int i = 0; i < arrayOfColumn.length; i++) {
                            for(int j=0 ; j< tableKendaraan.length; j++){
                                if(arrayOfColumn[i].equals(tableKendaraan[j])){
                                    System.out.println("- "+arrayOfColumn[i]+",");
                                }
                            }
                        }
                        System.out.println("Selection ");
                        System.out.println(arrayOfQuery[5]+" -- A3");

                        System.out.println(TableName);
                        
                        

                        double costA3 =countA3(b, B, v, P) ;
                        System.out.println("cost : "+costA3);
                        if(costA1>costA3){
                            System.out.println("QEP Optimal #QEP2");
                            hasilcost = Double.toString(costA3);
                            hasil = (hasilcost+" --A3");
                        }else{
                            System.out.println("QEP Optimal #QEP1");
                            hasilcost = Double.toString(costA1);
                            hasil = (hasilcost+" --A1");
                        }
                        input.inputBasic(arrayOfQuery, arrayOfColumn, arrayOfQuery[5], TableName, hasil);
                    }
                }else if((arrayOfQuery.length <= 6)&&(arrayOfQuery[1].equals("*"))){
                   System.out.println("Table Name : "+TableName);
                   System.out.println("Column List : ");
                       for (int i = 1; i < tableKendaraan.length-3; i++) {
                           System.out.println(tableKendaraan[i]);
                       }
                    System.out.println("");
                    System.out.println("#QEP 1");
                    System.out.println("Projection ");
                    for (int i = 1; i < tableKendaraan.length-3; i++) {
                           System.out.println("- "+tableKendaraan[i]);
                           arrayOfAll[i-1] = tableKendaraan[i];
                           
                       }
                    System.out.println("Selection ");
                    if(where.equals(tableKendaraan[1])){
                        System.out.println(arrayOfQuery[5]+" -- A1");
                    
                        System.out.println(TableName);
                        //cost with key
                        double costA1 = countA1Key(b);
                        System.out.println("cost : "+costA1);
                    
                        System.out.println("");
                    
                        System.out.println("#QEP 2");
                        System.out.println("Projection ");
                        for (int i = 1; i < tableKendaraan.length-3; i++) {
                            System.out.println("- "+tableKendaraan[i]);
                            arrayOfAll[i-1] = tableKendaraan[i];
                           
                        }
                        System.out.println("Selection ");
                        System.out.println(arrayOfQuery[5]+" -- A2");

                        System.out.println(TableName);

                        double costA2 =countA2(b, B, v, P) ;
                        System.out.println("cost : "+costA2);
                        if(costA1>costA2){
                            System.out.println("QEP Optimal #QEP2");
                            hasilcost = Double.toString(costA2);
                            hasil = (hasilcost+" --A2");
                        }else{
                            System.out.println("QEP Optimal #QEP1");
                            hasilcost = Double.toString(costA1);
                            hasil = (hasilcost+" --A1");
                        }
                        input.inputBasic(arrayOfQuery, arrayOfAll, arrayOfQuery[5], TableName, hasil);
                    }else{
                        System.out.println(arrayOfQuery[5]+" -- A1");
                    
                        System.out.println(TableName);
                        //cost with non-key
                        double costA1 = countA1NKey(b);
                        System.out.println("cost : "+costA1);
                    
                        System.out.println("");
                    
                        System.out.println("#QEP 2");
                        System.out.println("Projection ");
                        for (int i = 1; i < tableKendaraan.length-3; i++) {
                            System.out.println("- "+tableKendaraan[i]);
                           
                        }
                        System.out.println("Selection ");
                        System.out.println(arrayOfQuery[5]+" -- A3");

                        System.out.println(TableName);

                        double costA3 =countA3(b, B, v, P) ;
                        System.out.println("cost : "+costA3);
                        if(costA1>costA3){
                            System.out.println("QEP Optimal #QEP2");
                            hasilcost = Double.toString(costA3);
                            hasil = (hasilcost+" --A3");
                        }else{
                            System.out.println("QEP Optimal #QEP1");
                            hasilcost = Double.toString(costA1);
                            hasil = (hasilcost+" --A1");
                        }
                        input.inputBasic(arrayOfQuery, arrayOfAll, arrayOfQuery[5], TableName, hasil);
                    }   
                       
                   
                }else{
                    if(arrayOfQuery[4].equals("join")&&(arrayOfQuery[1].equals("*"))){
                        String TableJoined = "";
                        if(arrayOfQuery[5].equals(tableTransactions[0])){
                            TableJoined=tableTransactions[0];
                        }
                        if(arrayOfQuery[6].equals("using")){
                            arrayOfColumn1=arrayOfQuery[7].split(";");
                        }
                        System.out.println("Table Name : "+TableName);
                        System.out.println("Column List : ");
                        for (int i = 1; i < tableKendaraan.length-3; i++) {
                           System.out.println(tableKendaraan[i]+", ");
                           arrayOfAll[i-1] = tableKendaraan[i];
                        }
                        System.out.println("Table Join : "+TableJoined);
                        System.out.println("Column List : ");  
                        for (int i = 0; i < arrayOfColumn1.length; i++) {
                            for(int j=0 ; j< tableTransactions.length; j++){
                                if(TableJoined.equals(tableTransactions[0])){
                                    if(arrayOfColumn1[i].equals(tableTransactions[j])){
                                        System.out.println(arrayOfColumn1[i]+", ");
                                    }
                                }
                            }
                        }
                        System.out.println("#QEP 1");
                        System.out.println("PROJECTION ");
                        for (int i = 1; i < tableKendaraan.length-3; i++) {
                           System.out.println(tableKendaraan[i]+", ");
                           arrayOfAll[i-1] = tableKendaraan[i];
                        }
                        for(int j=1 ; j< tableTransactions.length-3; j++){
                            System.out.println(tableTransactions[j]+", ");
                            arrayOfAllJ[j-1] = tableTransactions[j];
                        }
                        System.out.println("-- on the fly");
                        System.out.println("JOIN");
                        for (int i = 0; i < arrayOfColumn1.length; i++) {
                            for(int j=0 ; j< tableTransactions.length; j++){
                                if(TableJoined.equals(tableTransactions[0])){
                                    if(arrayOfColumn1[i].equals(tableTransactions[j])){
                                        System.out.println(arrayOfColumn1[i]+", ");
                                    }
                                }
                            }
                        }
                        System.out.println("Tables");
                        System.out.println(TableName +"    "+ TableJoined);
                        double bfrL =countBfr(B, R);
                        double bl = countb(n, bfrL);
                        double bfrR  = countBfr(B, R1);
                        double br = countb(n1, bfrR);
                        double cost1 = countBNLJ(br, bl);
                        System.out.println("COST  : "+cost1);

                        System.out.println("");

                        System.out.println("#QEP 2");
                        System.out.println("PROJECTION ");
                        for (int i = 1; i < tableKendaraan.length-3; i++) {
                           System.out.println(tableKendaraan[i]+", ");
                           arrayOfAll[i-1] = tableKendaraan[i];
                        }
                        for(int j=1 ; j< tableTransactions.length-3; j++){
                            System.out.println(tableTransactions[j]+", ");
                            arrayOfAllJ[j-1] = tableTransactions[j];
                        }
                        System.out.println("-- on the fly");
                        System.out.println("JOIN");
                        for (int i = 0; i < arrayOfColumn1.length; i++) {
                            for(int j=0 ; j< tableTransactions.length; j++){
                                if(TableJoined.equals(tableTransactions[0])){
                                    if(arrayOfColumn1[i].equals(tableTransactions[j])){
                                        System.out.println(arrayOfColumn1[i]+", ");
                                    }
                                }
                            }
                        }
                        System.out.println("Tables");
                        System.out.println(TableJoined +"    "+ TableName);
                        double bfrL1 =countBfr(B, R);
                        double bl1 = countb(n, bfrL1);
                        double bfrR1  = countBfr(B, R1);
                        double br1 = countb(n1, bfrR);
                        double cost2 = countBNLJ(bl1, br1);
                        System.out.println("COST : "+cost2);
                        System.out.println("");
                        if(cost1<cost2){
                            System.out.println("QEP OPTIMAL : #QEP 1");
                            hasilcost = Double.toString(cost1);
                            hasil = (hasilcost+" --BNLJ");
                        }else{
                            System.out.println("QEP OPTIMAL : #QEP 2");
                            hasilcost = Double.toString(cost2);
                            hasil = (hasilcost+" --BNLJ");
                        }
                        input.inputJoin1(arrayOfQuery, arrayOfAll, arrayOfAllJ, arrayOfColumn1,TableName,TableJoined, hasil);
                    }else if(arrayOfQuery[4].equals("join")&&(!arrayOfQuery[1].equals("*"))){
                        String TableJoined = "";
                        if(arrayOfQuery[5].equals(tableTransactions[0])){
                            TableJoined=tableTransactions[0];
                        }
                        if(arrayOfQuery[6].equals("using")){
                            arrayOfColumn1=arrayOfQuery[7].split(";");
                        }
                        System.out.println("Table Name : "+TableName);
                        System.out.println("Column List : ");
                        for (int i = 0; i < arrayOfColumn.length; i++) {
                            for(int j=0 ; j< tableKendaraan.length; j++){
                                if(TableName.equals(tableKendaraan[0])){
                                    if(arrayOfColumn[i].equals(tableKendaraan[j])){
                                        System.out.println(arrayOfColumn[i]+", ");
                                    }
                                }
                            }
                        }                   
                        System.out.println("Table Join : "+TableJoined);
                        System.out.println("Column List : ");  
                        for (int i = 0; i < arrayOfColumn1.length; i++) {
                            for(int j=0 ; j< tableTransactions.length; j++){
                                if(TableJoined.equals(tableTransactions[0])){
                                    if(arrayOfColumn1[i].equals(tableTransactions[j])){
                                        System.out.println(arrayOfColumn1[i]+", ");
                                    }
                                }
                            }
                        }
                        System.out.println("");
                        System.out.println("#QEP 1");
                        System.out.println("PROJECTION ");
                        for (int i = 0; i < arrayOfColumn.length; i++) {
                            for(int j=0 ; j< tableKendaraan.length; j++){
                                if(TableName.equals(tableKendaraan[0])){
                                    if(arrayOfColumn[i].equals(tableKendaraan[j])){
                                        System.out.println(arrayOfColumn[i]+", ");
                                    }
                                }
                            }
                        }
                        System.out.println("-- on the fly");
                        System.out.println("JOIN");
                        for (int i = 0; i < arrayOfColumn1.length; i++) {
                            for(int j=0 ; j< tableTransactions.length; j++){
                                if(TableJoined.equals(tableTransactions[0])){
                                    if(arrayOfColumn1[i].equals(tableTransactions[j])){
                                        System.out.println(arrayOfColumn1[i]+", ");
                                    }
                                }
                            }
                        }
                        System.out.println("Tables");
                        System.out.println(TableName +"    "+ TableJoined);
                        double bfrL =countBfr(B, R);
                        double bl = countb(n, bfrL);
                        double bfrR  = countBfr(B, R1);
                        double br = countb(n1, bfrR);
                        double cost1 = countBNLJ(br, bl);
                        System.out.println("COST : "+cost1);

                        System.out.println("");

                        System.out.println("#QEP 2");
                        System.out.println("PROJECTION ");
                        for (int i = 0; i < arrayOfColumn.length; i++) {
                            for(int j=0 ; j< tableKendaraan.length; j++){
                                if(TableName.equals(tableKendaraan[0])){
                                    if(arrayOfColumn[i].equals(tableKendaraan[j])){
                                        System.out.println(arrayOfColumn[i]+", ");
                                    }
                                }
                            }
                        }
                        System.out.println("-- on the fly");
                        System.out.println("JOIN");
                        for (int i = 0; i < arrayOfColumn1.length; i++) {
                            for(int j=0 ; j< tableTransactions.length; j++){
                                if(TableJoined.equals(tableTransactions[0])){
                                    if(arrayOfColumn1[i].equals(tableTransactions[j])){
                                        System.out.println(arrayOfColumn1[i]+", ");
                                    }
                                }
                            }
                        }
                        System.out.println("Tables");
                        System.out.println(TableJoined +"    "+ TableName);
                        double bfrL1 =countBfr(B, R);
                        double bl1 = countb(n, bfrL1);
                        double bfrR1  = countBfr(B, R1);
                        double br1 = countb(n1, bfrR);
                        double cost2 = countBNLJ(bl1, br1);
                        System.out.println("COST : "+cost2);
                        System.out.println("");
                        if(cost1<cost2){
                            System.out.println("QEP OPTIMAL : #QEP 1");
                            hasilcost = Double.toString(cost1);
                            hasil = (hasilcost+" --BNLJ");
                        }else{
                            System.out.println("QEP OPTIMAL : #QEP 2");
                            hasilcost = Double.toString(cost2);
                            hasil = (hasilcost+" --BNLJ");
                        }
                        input.inputJoin(arrayOfQuery, arrayOfColumn,arrayOfColumn1,TableName,TableJoined, hasil);
                        
                    }
                }
            }else{
                System.out.println("SQL ERROR kendaraan");
        }        
    }else{
            System.out.println("SQL ERROR TANPA ;");
        }
            
    }
        
     public double countA1Key(double b){
         double hasil = b/2;
         return hasil ;
     }
     
     public double countA1NKey(double b){
         double hasil =b;
         return hasil ;
     }
     
     public double countA2(double b, int B, int v, int P){
         int x = B / (v+P) ;
         double y = Math.floor(x);
         double log = Math.log(b) / Math.log(y);
         double h1 = Math.ceil(log);
         double hasil =h1+1 ;
         return hasil ;
     }
     
     public double countA3(double b,int B, int v, int P){
         int x = B / (v+P) ;
         double y = Math.floor(x);
         double log = Math.log(b) / Math.log(y);
         double h1 = Math.ceil(log);
         double hasil = h1+b;
         return hasil ;
     }
     
     public double countBNLJ(double br, double bs){
         double hasil = (br*bs)+br;
         return hasil;
     }
     
     public double countBfr(int B, int R){
         int x = B/R;
         double hasil = Math.floor(x);
         return hasil;
     }
     
     public double countb(int n, double bfr){
         double x = n/bfr;
         double hasil = Math.ceil(x);
         return hasil;
     }
     
}