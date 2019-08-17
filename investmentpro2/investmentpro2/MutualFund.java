/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package investmentpro2;

/**
 *
 * @author markf
 */
public class MutualFund extends Investment{
    
    private String type;
    public MutualFund(String type,String symbol, String name, int quantity, double price) {
        super(symbol, name, quantity, price);
        this.type=type;
        super.setBookValue(super.getPrice()*super.getQuantity());
    }
    @Override
    public String toString() {
        return "type = "+"“"+type+"”"+"\n"+
                super.toString();
    }
    @Override
    public double getGain(){
        double change= super.getPrice()*super.getQuantity()-45.00-super.getBookValue();
        System.out.println(super.getBookValue());
       return change;
    }
}
