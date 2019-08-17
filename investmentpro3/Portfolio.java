/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package investmentpro3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author markf
 */
public class Portfolio {
    static ArrayList<Investment> investlist =new ArrayList<>();

    public ArrayList<Investment> getInvestlist() {
        return investlist;
    }
    HashMap<String, Investment> hm=
            new HashMap<String, Investment>();
    String filename="data.txt";
    File file=new File(filename);
    PrintWriter outputStream = null;
    Scanner StreamObject = null;
    Scanner input =new Scanner(System.in);
    
    String enter,put,sell;
    private String symbol;
    private String name;
    private String quantity;
    private String price;
    String str;
    Pattern p;
    public boolean quantity_Validity(String quantity){
        int check;
        try{
            check=Integer.parseInt(quantity);

        }catch(Exception e){
            return false;
        }
        if(check<0)
            return false;
       
        return true;
        
    }
    public boolean price_Validity(String price){
        double check;
        try{
            check=Double.parseDouble(price);

        }catch(Exception e){
            return false;
        }
        if(check<0)
            return false;
       
        return true;
    }
    public boolean checkifUnique(String a,String b){
        if(b.equals("symbol")){
        for(int i=0;i<investlist.size();i++){
            if(a.equals(investlist.get(i).getSymbol())){
                return false;
            }
        }}else if(b.equals("name")) {
        for(int i=0;i<investlist.size();i++){
            if(a.equals(investlist.get(i).getName())){
                return false;
            }
        }}
        return true;
    }
    public boolean checkifAdd(String a,String b){
       
        for(int i=0;i<investlist.size();i++){
            if(a.equals(investlist.get(i).getSymbol())){
                if(b.equals(investlist.get(i).getName()))
                return true;
            }
        }
        return false;
    }
    public void readFile(){
        int i=0;
        String[] store = new String[6]; 
        MutualFund mutualfund;
        Stock stock;
        
        if(!investlist.isEmpty()){
           investlist.removeAll(investlist);
        }
       
        try {           
            StreamObject =
            new Scanner(new FileInputStream("data.txt"));               
                 
        } catch (Exception e) {           
                 System.out.println("Error opening the file data.txt.");
                 System.exit(0);        
        }       
        while(StreamObject.hasNext()){
            if(!StreamObject.next().isEmpty()){         
                String []parts=StreamObject.nextLine().split("“");    
                store[i]=parts[1].replace("", "");
                i++;
                if(i==6){  
                    if(store[0].equals("stock")){       
                        stock=new Stock(store[0],store[1],store[2],
                        Integer.parseInt(store[3]),Double.parseDouble(store[4]));
                        investlist.add(stock);
                        addToMap(store[1],store[2],stock);
                    }
                    else if(store[0].equals("mutualfund")){
                        mutualfund=new MutualFund(store[0],store[1],store[2],
                        Integer.parseInt(store[3]),Double.parseDouble(store[4]));
                        investlist.add(mutualfund);
                        addToMap(store[1],store[2],mutualfund);
                    }
                    i=0;
                }
            }    
        }
        StreamObject.close();           
    }
    public void addToMap(String name, String symbol, Investment object){
        String []parts_name=name.split(" ");
        String []parts_symbol=symbol.split(" ");
        for(int i=0;i<parts_name.length;i++)
        hm.put(parts_name[i], object);    
        for(int i=0;i<parts_symbol.length;i++)     
        hm.put(parts_symbol[i], object);
    }
    public void writeToFile(String context, boolean ifTrue){
        try {           
                 outputStream =
                         new PrintWriter(new FileOutputStream("data.txt",ifTrue));
                 outputStream.println(context);
             } catch (Exception e) {           
                 System.out.println("Error opening the file data.txt.");
                 System.exit(0);        
             }       
             outputStream.close();
    }
    public void writeToFile(Investment context, boolean ifTrue){
        try {           
                 outputStream = new PrintWriter(new FileOutputStream("data.txt",ifTrue));
                 outputStream.println(context);
             } catch (Exception e) {           
                 System.out.println("Error opening the file data.txt.");
                 System.exit(0);        
             }       
             outputStream.close();
    }
    
}
