/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package investmentpro3;

/**
 *
 * @author markf
 */
public class Stock extends Investment{
    
    private String type;

    public Stock(String type,String symbol, String name, int quantity, double price) {
        super(symbol, name, quantity, price);
        this.type=type;
        super.setBookValue(super.getPrice()*this.getQuantity()+9.99);
    }
    @Override
    public String toString() {
        return "type = "+type+"\n"+
                super.toString();
    }
    @Override
    public double getGain(){
        double change= super.getPrice()*super.getQuantity()-9.99-super.getBookValue();
       return change;
    }
   
    
}
