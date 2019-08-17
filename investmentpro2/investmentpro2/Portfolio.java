/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package investmentpro2;

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
    ArrayList<Investment> investlist =new ArrayList<>();
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
    
    
    public void command(){
        
        while(true){
            System.out.println("Please enter the command including :"
               + " buy, sell, update, getGain, search, load, and quit.");
            enter=input.nextLine();
            if(enter.equalsIgnoreCase("buy")||enter.equalsIgnoreCase("b"))
            {
                buy();              
            }
            else if(enter.equalsIgnoreCase("sell")||enter.equalsIgnoreCase("s"))
            {
               if(investlist.isEmpty()){
                   System.out.println("There is not investment, you can not sell");
               } else{
                   sell();
               }
            }
            else if(enter.equalsIgnoreCase("update")||enter.equalsIgnoreCase("u")
               ||enter.equalsIgnoreCase("updateprice"))
            {  
               if(investlist.isEmpty()){
                   System.out.println("There is not investment, you can not updatePrice");
               } else{
                   update();
               }
            }
            else if(enter.equalsIgnoreCase("getGain")||enter.equalsIgnoreCase("g"))
            {
                if(investlist.isEmpty()){
                   System.out.println("There is not investment, you can not getGain");
               } else{
                   getGain();
               }
            }
            else if(enter.equalsIgnoreCase("search"))
            {
                search();
            }
            else if(enter.equalsIgnoreCase("load")||enter.equalsIgnoreCase("l"))
            {
                readFile();
                for(int i=0;i<investlist.size();i++)
                {
                    System.out.println(investlist.get(i).toString());
                    hm.put(investlist.get(i).getSymbol(), investlist.get(i));
                    hm.put(investlist.get(i).getName(), investlist.get(i));
                }
                
            }
            else if(enter.equalsIgnoreCase("quit")||enter.equalsIgnoreCase("q")
             ||enter.equalsIgnoreCase("exit")||enter.equalsIgnoreCase("buy") )
            {
                System.exit(0);
            }
        }  
    }
    public void search(){

        System.out.println("Enter s m c for single mutiple combined search");
        enter=input.nextLine();
        while(!enter.equalsIgnoreCase("s")&&!enter.equalsIgnoreCase("n")&&!enter.equalsIgnoreCase("c"))
        {
            System.out.println("Not correct enter, type again");
            enter=input.nextLine();
        }
        if(enter.equalsIgnoreCase("s"))
        {
            System.out.println("Enter single keyword");
            enter=input.nextLine();
            System.out.println(hm.get(enter));
        }
        if(enter.equalsIgnoreCase("m"))
        {
            System.out.println("Enter multiple keyword");
            enter=input.nextLine();
            String []parts=enter.split(" ");
            for(int i=0;i<parts.length;i++)
            System.out.println(hm.get(parts[i]));
        }
        if(enter.equalsIgnoreCase("c"))
        {
            System.out.println("Enter combined keyword");
            enter=input.nextLine();
            String []parts=enter.split(" ");            
            System.out.println(hm.get(parts[0]));
        }
        
    }
    public void getGain(){
        int gain=0;
        for(int i=0;i<investlist.size();i++)
            gain+=investlist.get(i).getGain();
        System.out.println(gain);
    }
    public void update(){
        System.out.println("Enter either symbol or name you want to update");
        put=input.nextLine();
        while(!hm.containsKey(put)){
            System.out.println("No found, enter again");
            put=input.nextLine();
        }
        System.out.println("Enter the price you want to update");
        price=input.nextLine();
        while(!checkifDouble(price)){
            System.out.println("it's not a integer or less than 0, try again");
            price=input.nextLine();
        }
        hm.get(put).updatePrice(Double.parseDouble(price));
        writeToFile("",false);
        for(int i=0;i<investlist.size();i++)
        writeToFile(investlist.get(i).toString(),true);         
    }
    public void buy(){
        System.out.println("choose either buy stock or mutual fund, or add more");
        System.out.println("Enter either add or buy");
        enter=input.nextLine();
        while(!enter.equalsIgnoreCase("add")&&!enter.equalsIgnoreCase("buy")){
            System.out.println("input should be buy or add, try again");
            enter=input.nextLine();
        }
        if(enter.equalsIgnoreCase("buy")){
            System.out.println("Choosing buy stock or mutualfund, enter either stock or mutualfund");
            enter=input.nextLine();
            while(!enter.equalsIgnoreCase("stock")&&!enter.equalsIgnoreCase("mutualfund"))
            {
                System.out.println("input should be stock or mutualfund, try again");
                enter=input.nextLine();
            }
            if(enter.equalsIgnoreCase("stock")){
            
                Invest_type(1,"stock");                 
            }
            if(enter.equalsIgnoreCase("mutualfund")){
            
                Invest_type(2,"mutualfund");                 
            }
        }
        if(enter.equalsIgnoreCase("add")){
            if(!investlist.isEmpty())
            add();
            if(investlist.isEmpty()){
                System.out.println("There is no investment, you can not add");
            }
                
        }
    }
    public void sell(){
        System.out.println("Enter either symbol or name you want to sell");
        put=input.nextLine();
        while(!hm.containsKey(put)){
            System.out.println("No found, enter again");
            put=input.nextLine();
        }
        System.out.println("Enter the quantity you want to sell");
        quantity=input.nextLine();
        while(!checkifInteger(quantity)){
            System.out.println("it's not a integer or less than 0, try again");
            quantity=input.nextLine();
        }
        while(Integer.parseInt(quantity)>hm.get(put).getQuantity()){
            System.out.println("The storage is not enough, try small");
            quantity=input.nextLine();
        }
        hm.get(put).sell(Integer.parseInt(quantity));
        if(hm.get(put).getQuantity()==0){
            hm.remove(put);
            for(int i=0;i<investlist.size();i++){
                if(investlist.get(i).getQuantity()==0)
                investlist.remove(i);
            }
        }
        writeToFile("",false);
        for(int i=0;i<investlist.size();i++)
        writeToFile(investlist.get(i).toString(),true);
    }
    public void Invest_type(int num,String type){
        System.out.println("Symbol: ");
        symbol=input.nextLine();
        while(checkifUnique(symbol)==false){
            System.out.println("It already exist, try again");
            symbol=input.nextLine();
        }
        System.out.println("name:");
        name=input.nextLine();
        while(checkifUnique(name)==false){
            System.out.println("It already exist, try again");
            name=input.nextLine();
        }
        System.out.println("quantity:");
        quantity=input.nextLine();
        while(!checkifInteger(quantity)){
            System.out.println("it's not a integer or less than 0, try again");
            quantity=input.nextLine();
        }
        System.out.println("price");
        price=input.nextLine();
        while(!price.matches("-?[0-9]+.*[0-9]*")||Double.parseDouble(price)<0){
            System.out.println("price entered is not a number "
                    + "or it less than 0,try again");
            price=input.nextLine();
        }
        if(num==1){
            Stock stock=new Stock(type,symbol,name,Integer.parseInt(quantity),
                    Double.parseDouble(price));
            addToMap(symbol,name,stock);
            writeToFile(stock,true);
        }else if(num==2){
            MutualFund fund=new MutualFund(type,symbol,name,Integer.parseInt(quantity)
                    ,Double.parseDouble(price));
            investlist.add(fund);
            addToMap(symbol,name,fund);
            writeToFile(fund,true);
        }
 
    }
    public void add(){
        System.out.println("Enter either symbol or name you want to add");
            put=input.nextLine();
            while(!hm.containsKey(put)){
                System.out.println("No found, enter again");
                put=input.nextLine();
            }
            System.out.println("Enter the quantity you want to add");
            quantity=input.nextLine();
            while(!checkifInteger(quantity)){
                System.out.println("it's not a integer or less than 0, try again");
                quantity=input.nextLine();
            }
            hm.get(put).add(Integer.parseInt(quantity));
            System.out.println(investlist.size());
            writeToFile("",false);
            for(int i=0;i<investlist.size();i++)
            writeToFile(investlist.get(i).toString(),true);
    }
    public boolean checkifInteger(String a){
  
        for(int i=0;i<a.length();i++){
            if(!Character.isDigit(a.charAt(i)))
                return false;      
        }
        if(a.charAt(0)=='0')
            return false;
        return true;
    }
    public boolean checkifDouble(String a){
        if(!a.matches("-?[0-9]+.*[0-9]*")||Double.parseDouble(a)<0){
            return false;
        }
        return true;      
    }
    public boolean checkifUnique(String a){
        if(hm.containsKey(a)){
            return false;
        }
        return true;
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
                store[i]=parts[1].replace("”", "");
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
