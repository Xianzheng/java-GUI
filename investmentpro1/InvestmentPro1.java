/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package investmentpro1;
import java.util.*;

/**
 * Build a investment portfolio
 * @author Xianzheng Fang
 */
public class InvestmentPro1 {
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        Stock td =new Stock("TD","td bank",500,50.00);
        MutualFund fund=new MutualFund("CIG677","CI Signature Select Canadian",450,20.00);
        Portfolio portfolio=new Portfolio();
        portfolio.add(td);
        portfolio.add(fund);
        Command com=new Command(portfolio);
        com.run();
        
    }    
 }
class Command{
    Portfolio portfolio;
    public Command(Portfolio portfolio){
        this.portfolio=portfolio;
    }
    public void run()
    {
        while(true){
            String enter,enter1;
            String symbol="",string="",range="";
            double ActualPrice, gain=0,price=0;
            int quantity=0;
            System.out.println("enter your command, including buy,sell,update,getGain,search");
            Scanner input =new Scanner(System.in);
            enter=input.nextLine();
            if(enter.equals("buy")){//if user enter buy they can choose buy new investment or at exist
               System.out.println("enter the kind of investment you would like to buy (either stock or mutualfund)");
               System.out.println("Or type add to add more quality to exist investment");
               enter1=input.nextLine();
                if(enter1.equalsIgnoreCase("stock")){
                    System.out.println("Please enter the symbol of stock");
                    symbol=input.nextLine();
                    while(portfolio.checkInSymbol(symbol)==-1){//the symbol is unique 
                        System.out.println("The symbol you enter is already exist, try again");
                        symbol=input.nextLine();
                    }
                    System.out.println("Please enter the Name of stock");
                    string=input.nextLine();
                   while(string.isEmpty()){
                        System.out.println("The Name you enter is invalid, try again");
                        symbol=input.nextLine();
                    }
                    System.out.println("Please enter the quantity of stock");              
                    quantity=input.nextInt();
                    System.out.println("Please enter the price of stock");
                    price=input.nextDouble();
                    Stock stock=new Stock(symbol,string,quantity,price);
                    portfolio.stocklist.add(stock);
                    }else if(enter1.equalsIgnoreCase("mutualfund")){
                    System.out.println("Please enter the symbol of fund");
                    symbol=input.nextLine();
                    while(portfolio.checkInSymbol(symbol)==-1){
                        System.out.println("The symbol you enter is already exist, try again");
                        symbol=input.nextLine();
                    }
                    System.out.println("Please enter the Name of fund");
                    string=input.nextLine();
                    while(string.isEmpty()){
                        System.out.println("The Name you enter is invalid, try again");
                        symbol=input.nextLine();
                    }
                    System.out.println("Please enter the quantity of fund");
                    quantity=input.nextInt();
                    System.out.println("Please enter the price of fund");
                    price=input.nextDouble();
                    MutualFund fund=new MutualFund(symbol,string,quantity,price);
                    portfolio.fundlist.add(fund);
                    }else if(enter1.equalsIgnoreCase("add")){//add the quantity and it will change the bookvalue at same time
                        System.out.println("Please enter the symbol to add");
                        symbol=input.nextLine();
                        System.out.println("the quantity want to add");
                        quantity=input.nextInt();
                        for(int i=0;i<portfolio.stocklist.size();i++){
                            if(symbol.equalsIgnoreCase(portfolio.stocklist.get(i).getSymbol()))
                            portfolio.stocklist.get(i).addQuantity(quantity);;
                        }
                        for(int i=0;i<portfolio.fundlist.size();i++){
                            if(symbol.equalsIgnoreCase(portfolio.fundlist.get(i).getSymbol()))
                            portfolio.fundlist.get(i).addQuantity(quantity);
                        }
                            
                    }
            }
            if(enter.equalsIgnoreCase("sell")){// the comend is sell, it will change the quantity of exist investment
                System.out.println("provide symbol of investment:");// if quantity is zero will remove from portfolio
                symbol=input.nextLine();
                //System.out.println("provide actual price of investment:");
                //ActualPrice=input.nextDouble();
                System.out.println("provide quantity of investment:");
                quantity=input.nextInt();

                if(portfolio.checkStockList(symbol)>=0){
                    int i=portfolio.checkStockList(symbol);
                    if(quantity<=portfolio.stocklist.get(i).getQuantity()){
                    portfolio.stocklist.get(i).getpaymentReceived(quantity);
                    if(portfolio.stocklist.get(i).getQuantity()==0)
                        portfolio.stocklist.remove(i);
                    }else{
                     System.out.println("The quantity is not enough");
                    }                       
                }else if(portfolio.checkFundList(symbol)>=0){
                    int i=portfolio.checkFundList(symbol);
                    if(quantity<portfolio.fundlist.get(i).getQuantity()){
                    portfolio.fundlist.get(i).getpaymentReceived(quantity);
                    if(portfolio.fundlist.get(i).getQuantity()==0)
                        portfolio.fundlist.remove(i);
                    }else{
                     System.out.println("The quantity is not enough");
                    }                       
                }
            }else if(enter.equalsIgnoreCase("update")){//update the all price for all investment
                                                       //don't know why
                System.out.println("Please type the price you want to upgrade for all investment");
                ActualPrice=input.nextDouble();
                for(int i=0;i<portfolio.stocklist.size();i++)
                portfolio.stocklist.get(i).updatePrice(ActualPrice);
                for(int i=0;i<portfolio.fundlist.size();i++)
                portfolio.fundlist.get(i).updatePrice(ActualPrice);
                   
            }else if(enter.equalsIgnoreCase("getGain")){//all new value minors old value
                gain=portfolio.getGain(gain);
                System.out.println(gain);
            }else if(enter.equalsIgnoreCase("search")){
                System.out.println("provide symbol of investment:");
                symbol=input.nextLine();
                System.out.println("provide a set of keywords that appear in the name of an investment:");
                string=input.nextLine();
                System.out.println("provide price range of investment:");
                range=input.nextLine();
                
                if(string.isEmpty()&&range.isEmpty()&&symbol.length()!=0){//search symbol only type symbol
                    for(int i=0;i<portfolio.stocklist.size();i++){
                        if(portfolio.checkStockSymbol(symbol, i)>=0){
                            portfolio.stocklist.get(portfolio.checkStockSymbol(symbol,i)).ShowAttribute();                      
                        }
                    }
                    for(int i=0;i<portfolio.fundlist.size();i++){
                        if(portfolio.checkFundSymbol(symbol, i)>=0){
                            portfolio.fundlist.get(portfolio.checkFundSymbol(symbol,i)).ShowAttribute();                      
                        }
                    }
                }else if(symbol.isEmpty()&&range.isEmpty()&&string.length()!=0){//only type a set of Name
                    for(int i=0;i<portfolio.stocklist.size();i++){
                       if(portfolio.checkStockName(string, i)>=0){
                          portfolio.stocklist.get(portfolio.checkStockName(string, i)).ShowAttribute();
                       }
                    }
                    for(int i=0;i<portfolio.fundlist.size();i++){
                       if(portfolio.checkFundName(string, i)>=0){
                          portfolio.fundlist.get(portfolio.checkFundName(string, i)).ShowAttribute();
                       }
                    } 
                }else if(symbol.isEmpty()&&string.isEmpty()&&range.length()!=0){//only type range only have number- or -number
                
                    for(int i=0;i<portfolio.stocklist.size();i++){
                       // System.out.println("portfolio.checkRange(range, i)"+portfolio.checkRange(range, i));
                       if(portfolio.checkStockRange(range, i)>=0){
                          if(portfolio.checkStockRange(range, i)>=0)
                          portfolio.stocklist.get(portfolio.checkStockRange(range, i)).ShowAttribute();
                       }            
                    }
                    for(int i=0;i<portfolio.fundlist.size();i++){
           
                       if(portfolio.checkFundRange(range, i)>=0){
                          if(portfolio.checkFundRange(range, i)>=0)
                          portfolio.fundlist.get(portfolio.checkFundRange(range, i)).ShowAttribute();
                       }            
                    }
                }else if(string.isEmpty()&&range.length()!=0&&symbol.length()!=0){//search both symbol and range
                   for(int i=0;i<portfolio.stocklist.size();i++){
                       if(portfolio.checkStockSymbol(symbol, i)>=0&&portfolio.checkStockRange(range, i)>=0){
                           portfolio.stocklist.get(portfolio.checkStockRange(range, i)).ShowAttribute();
                       }
                   }
                   for(int i=0;i<portfolio.fundlist.size();i++){
                       if(portfolio.checkFundSymbol(symbol, i)>=0&&portfolio.checkFundRange(range, i)>=0){
                           portfolio.fundlist.get(portfolio.checkFundRange(range, i)).ShowAttribute();
                       }
                   }
                }else if(symbol.isEmpty()&&range.length()!=0&&string.length()!=0){//search range and name
                    for(int i=0;i<portfolio.stocklist.size();i++){
                       if(portfolio.checkStockName(string, i)>=0&&portfolio.checkStockRange(range, i)>=0){
                           portfolio.stocklist.get(portfolio.checkStockRange(range, i)).ShowAttribute();
                       }
                    }
                    for(int i=0;i<portfolio.fundlist.size();i++){
                       if(portfolio.checkFundName(string, i)>=0&&portfolio.checkFundRange(range, i)>=0){
                           portfolio.fundlist.get(portfolio.checkFundRange(range, i)).ShowAttribute();
                       }
                    }
                }else if(range.isEmpty()&&symbol.length()!=0&&string.length()!=0){//search symbol and Name
                    for(int i=0;i<portfolio.stocklist.size();i++){
                        if(portfolio.checkStockSymbol(symbol, i)>=0&&portfolio.checkStockName(string, i)>=0){
                           portfolio.stocklist.get(portfolio.checkStockSymbol(symbol, i)).ShowAttribute();
                       }
                    }
                    for(int i=0;i<portfolio.fundlist.size();i++){
                        if(portfolio.checkFundSymbol(symbol, i)>=0&&portfolio.checkFundName(string, i)>=0){
                           portfolio.fundlist.get(portfolio.checkFundSymbol(symbol, i)).ShowAttribute();
                       }
                    }
                } else if(range.isEmpty()&&string.isEmpty()&&symbol.isEmpty()){//all are empty
                    for(int i=0;i<portfolio.stocklist.size();i++)
                       portfolio.stocklist.get(i).ShowAttribute(); 
                    for(int i=0;i<portfolio.fundlist.size();i++)
                       portfolio.fundlist.get(i).ShowAttribute(); 
                }else {//search all
                   for(int i=0;i<portfolio.stocklist.size();i++){
                        if(portfolio.checkStockSymbol(symbol, i)>=0&&portfolio.checkStockName(string, i)>=0&&portfolio.checkStockRange(range, i)>=0){
                           portfolio.stocklist.get(portfolio.checkStockSymbol(symbol, i)).ShowAttribute();
                       }
                    } 
                   for(int i=0;i<portfolio.fundlist.size();i++){
                        if(portfolio.checkFundSymbol(symbol, i)>=0&&portfolio.checkFundName(string, i)>=0&&portfolio.checkFundRange(range, i)>=0){
                           portfolio.fundlist.get(portfolio.checkFundSymbol(symbol, i)).ShowAttribute();
                       }
                    } 
                }                       
            }
             
        }
    }
    
}

class Stock{//stock class
    private String symbol;
    private String name;
    private int quantity;
    private double price;
    private double initialPrice;
    private double BookValue;
    
    public Stock(String symbol,String name, int quantity, double price)//stock construcor
    {
        this.symbol=symbol;
        this.name=name;
        this.quantity=quantity;
        this.price=price;
        this.initialPrice=price;
        this.BookValue=price*quantity+9.99;
    }
    
    public double updatePrice(double price){//updatePrice methos
        //price=(double)(10+Math.random()*100);
        this.price=price;
        return price;
    }    
    public double getGain(){//gain method
       double change=0;
       if(initialPrice!=price)
       change= this.price*this.quantity-9.99-BookValue;
       return change;
    }
    public void getpaymentReceived(int sell){//sell metho
        double paymentReceived=sell*price-9.99;
        BookValue=BookValue*(quantity-sell)/quantity;
        quantity-=sell;
        System.out.println("payment Received is "+paymentReceived);
        System.out.println("Book Value is "+BookValue);
        System.out.println("quantity is "+quantity);  
    }
    public void ShowAttribute(){//show method
        System.out.println("sysmbol: "+symbol+" name: "+name+" quantity: "+quantity+
                " price: "+price+" BookValue: "+BookValue);
    }
    public String getSymbol(){//all get method
        return symbol;
    }
    public double getPrice(){
        return price;
    }
    public double getQuantity(){
        return quantity;
    }
    public String getName(){
        return name;
    }
    public void addQuantity(int quantity){
        this.quantity+=quantity;
        this.BookValue=price*quantity+9.99;
    }
  
}

class MutualFund{//fund class
    private String symbol;
    private String name;
    private int quantity;
    private double price;
    private double initialPrice;
    private double BookValue;
    public MutualFund(String symbol,String name, int quantity, double price)// fund constructor
    {
        this.symbol=symbol;
        this.name=name;
        this.quantity=quantity;
        this.price=price;
        this.initialPrice=price;
        this.BookValue=price*quantity;
    }
    public double updatePrice(double price){//updateprice for fund
        //price=(double)(10+Math.random()*100);
        this.price=price;
        return this.price;
    }

    public double getGain(){//get gain for fund investment
       double change=0;
       if(initialPrice!=price)
       change= this.price*this.quantity-45.00-BookValue;
       return change; 
    }
    public void getpaymentReceived(int sell){//sell method
        double paymentReceived=sell*price-45.00;
        BookValue=BookValue*(quantity-sell)/quantity;
        quantity-=sell;
        
        System.out.println("payment Received is "+paymentReceived);
        System.out.println("Book Value is "+BookValue);
        System.out.println("quantity is "+quantity);   
    }
    public void ShowAttribute(){//show for sell investment
        System.out.println("sysmbol: "+symbol+" name: "+name+" quantity: "+quantity+
                " price: "+price+" BookValue: "+BookValue);
    }
    public String getSymbol(){//all getter
        return symbol;
    }
    public double getPrice(){
        return price;
    }
    public double getQuantity(){
        return quantity;
    }
    public String getName(){
        return name;
    }
    public void addQuantity(int quantity){
        this.quantity+=quantity;
         this.BookValue=price*quantity+9.99;
    }
}
class Portfolio{// class portfolio
    
    LinkedList<Stock> stocklist=new LinkedList<>();//list to restore all stock
    LinkedList<MutualFund> fundlist=new LinkedList<>();//list to restore all fund
    void getSize(LinkedList stocklist){
        System.out.println(stocklist.size());
    }
    void add(Stock stock){//fund method
        stocklist.add(stock);
    }
    void add(MutualFund fund){
        fundlist.add(fund);
    }
    int checkStockList(String key){//method useful for search
        int check=-1;
        
        //System.out.println(stocklist.get(0).getQuantity());
        for(int i=0;i<stocklist.size();i++)
        {
            if(key.equalsIgnoreCase(stocklist.get(i).getSymbol()))
            check=i;
        }
        return check;
    }
    int checkStockSymbol(String key, int i){//method for search
        int check=-1;
        if(key.equalsIgnoreCase(stocklist.get(i).getSymbol()))
            check=i;
        return check;
    }
    int checkStockName(String key, int i){//method fpr search
        int check=-1;
        StringTokenizer token=new StringTokenizer(stocklist.get(i).getName()," ");
        while(token.hasMoreElements()){
               if(key.equalsIgnoreCase(token.nextToken()))
               check=i;
           }
        return check;
    }
    int checkStockRange(String key, int i){
        int check=-1;
        String[] parts=key.split("-");       
        if(key.charAt(0)=='-'){
            if(Integer.parseInt(parts[1])>(int)stocklist.get(i).getPrice()){
            check=i;
            }
        }
        
        if(key.endsWith("-")){
            if(Integer.parseInt(parts[0])<(int)stocklist.get(i).getPrice()){
            check=i;
            }
        }
        
        return check;
    }
    int checkFundList(String symbol){
        int check=-1;
        //System.out.println(fundlist.get(0).getQuantity());
        for(int i=0;i<fundlist.size();i++)
        {
            if(symbol.equalsIgnoreCase(fundlist.get(i).getSymbol())){
            check=i;
            }
        }
        if(check==-1)
            System.out.println("the symbol is not found");
        return check;
    }
    int checkFundSymbol(String key, int i){
        int check=-1;
        if(key.equalsIgnoreCase(fundlist.get(i).getSymbol()))
            check=i;
        return check;
    }
    int checkFundName(String key, int i){
        int check=-1;
        StringTokenizer token=new StringTokenizer(fundlist.get(i).getName()," ");
        while(token.hasMoreElements()){
               if(key.equalsIgnoreCase(token.nextToken()))
               check=i;
           }
        return check;
    }
    int checkFundRange(String key, int i){
        int check=-1;
        String[] parts=key.split("-");       
        if(key.charAt(0)=='-'){
            if(Integer.parseInt(parts[1])>(int)fundlist.get(i).getPrice()){
            check=i;
            }
        }
        
        if(key.endsWith("-")){
            if(Integer.parseInt(parts[0])<(int)stocklist.get(i).getPrice()){
            check=i;
            }
        }
        
        return check;
    }
    double getGain(double gain){//get all gain
        for(int i=0;i<stocklist.size();i++)
        {
            gain+=stocklist.get(i).getGain();
            //System.out.println(stocklist.get(i).getGain());
        }
        for(int i=0;i<fundlist.size();i++)
        {  
            gain+=fundlist.get(i).getGain();
        }
        return gain;
    }
    int checkInSymbol(String symbol){//check input for same symbol
        int check=0;
        for(int i=0;i<stocklist.size();i++)
        if(symbol.equalsIgnoreCase(stocklist.get(i).getSymbol()))
            check=-1;
       
        for(int i=0;i<fundlist.size();i++)
        if(symbol.equalsIgnoreCase(fundlist.get(i).getSymbol()))
            check=-1;
        return check;
    }
}