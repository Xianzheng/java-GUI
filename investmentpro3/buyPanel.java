/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package investmentpro3;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.AncestorListener;

/**
 *
 * @author markf
 */

public class buyPanel extends JFrame implements ActionListener{
    public static final String buffer="            ";
    private Portfolio port;
    private JPanel buy;
    private JPanel north;
    private JPanel south;
    private JPanel center;
    private JPanel east;
    private JPanel west;
    private JPanel mainPanel;
    
    private JLabel sentence;
    private JLabel type_Label;
    private JLabel symbol_Label;
    private JLabel name_Label;
    private JLabel quantity_Label;
    private JLabel price_Label;
    private JLabel message_Label;
    
    private JTextField type_TextField;
    private JTextField name_TextField;
    private JTextField symbol_TextField;
    private JTextField quantity_TextField;
    private JTextField price_TextField;
    
    private JTextArea message;
    private JScrollPane jsp;
    
    private JComboBox typelist;
    private CardLayout card;
    private JButton reset_button;
    private JButton buy_button;
    private String[] type={"Stock","MutualFund"};
    public buyPanel(CardLayout Layout, JPanel panel){
        Box h1Box=Box.createHorizontalBox();
        Box v1Box=Box.createVerticalBox();       
        Box h2Box=Box.createHorizontalBox();
        Box v2Box=Box.createVerticalBox();       
        Box h3Box=Box.createHorizontalBox();
        Box v3Box=Box.createVerticalBox();       
        Box h4Box=Box.createHorizontalBox();
        Box v4Box=Box.createVerticalBox();       
        Box h5Box=Box.createHorizontalBox();
        Box v5Box=Box.createVerticalBox();
        Box h6Box=Box.createHorizontalBox();
        Box v6Box=Box.createVerticalBox();
        Box h7Box=Box.createHorizontalBox();
        Box v7Box=Box.createVerticalBox();
        
        port =new Portfolio();
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~message box~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~      
        message=new JTextArea(7,30);
        jsp= new JScrollPane(message);
        jsp.setSize(7, 30);
        jsp.setEnabled(rootPaneCheckingEnabled);
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~button set~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~       
        Buy_ButtonListener buy_listen =new Buy_ButtonListener();
        Reset_ButtonListener reset_listen =new Reset_ButtonListener();
        reset_button=new JButton("Reset");
        reset_button.addActionListener(reset_listen);
        buy_button=new JButton("  Buy ");
        buy_button.addActionListener(buy_listen);
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Label set~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        sentence=new JLabel("Buying a new Investment");
        type_Label=new JLabel(buffer+"Type");
        symbol_Label=new JLabel(buffer+"Symbol");
        name_Label=new JLabel(buffer+"Name");
        quantity_Label=new JLabel(buffer+"Quantity");
        price_Label=new JLabel(buffer+"Price");
        message_Label=new JLabel("Messages");
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~JText set~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~        
        symbol_TextField = new JTextField();      
        name_TextField = new JTextField();  
        quantity_TextField = new JTextField(); 
        price_TextField = new JTextField();
       
               
        mainPanel = panel;
        card = Layout;
//~~~~~~~~~~~~~~~~~~~~~~~Panel set~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        buy=new JPanel();
        buy.setLayout(new BorderLayout());
        north=new JPanel();
        north.setLayout(new GridLayout(2,1));
        south=new JPanel();  
        east=new JPanel();
        east.setLayout(new GridLayout(2,1));
        center=new JPanel();
        center.setLayout(new GridLayout(5,1));
        west=new JPanel();
        west.setLayout(new GridLayout(5,1));
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Combobox~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        typelist=new JComboBox(type); 
        typelist.addActionListener(this);
        typelist.setPreferredSize(new Dimension(10,10));
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Content in west panel~~~~~~~~~~~~~~~~~~~~~~~
        
        west.add(type_Label);
        west.add(symbol_Label);
        west.add(name_Label);
        west.add(quantity_Label);
        west.add(price_Label);
       
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~For Better Look adject center~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        h1Box.add(Box.createHorizontalStrut(30));
        h1Box.add(typelist);
        h1Box.add(Box.createHorizontalStrut(120));
        h1Box.add(reset_button);
        h1Box.add(Box.createHorizontalStrut(60));
        
        v1Box.add(Box.createVerticalStrut(10));
        v1Box.add(h1Box);
        v1Box.add(Box.createVerticalStrut(10));
        
        h2Box.add(Box.createHorizontalStrut(30));
        h2Box.add(symbol_TextField);
        h2Box.add(Box.createHorizontalStrut(300));
             
        v2Box.add(Box.createVerticalStrut(7));
        v2Box.add(h2Box);
        v2Box.add(Box.createVerticalStrut(7));
        
        h3Box.add(Box.createHorizontalStrut(30));
        h3Box.add(name_TextField);
        h3Box.add(Box.createHorizontalStrut(180));
             
        v3Box.add(Box.createVerticalStrut(7));
        v3Box.add(h3Box);
        v3Box.add(Box.createVerticalStrut(7));
        
        h4Box.add(Box.createHorizontalStrut(30));
        h4Box.add(quantity_TextField);
        h4Box.add(Box.createHorizontalStrut(215));
        h4Box.add(buy_button);
        h4Box.add(Box.createHorizontalStrut(60));
             
        v4Box.add(Box.createVerticalStrut(7));
        v4Box.add(h4Box);
        v4Box.add(Box.createVerticalStrut(7));
        
        h5Box.add(Box.createHorizontalStrut(30));
        h5Box.add(price_TextField);
        h5Box.add(Box.createHorizontalStrut(340));
             
        v5Box.add(Box.createVerticalStrut(7));
        v5Box.add(h5Box);
        v5Box.add(Box.createVerticalStrut(7));
        //vBox.add(symbol_TextField,Box.createHorizontalStrut(200));
        center.add(v1Box);
        center.add(v2Box);
        center.add(v3Box);
        center.add(v4Box);
        center.add(v5Box);
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~adjust south~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
        
        //message.addAncestorListener((AncestorListener) this);
        
        h6Box.add(message_Label);
        h6Box.add(Box.createHorizontalStrut(460));
        
       
        h7Box.add(jsp);
       
        v6Box.add(Box.createVerticalStrut(7));
        v6Box.add(h6Box);
        v6Box.add(Box.createVerticalStrut(7));
        v6Box.add(h7Box);
       
        south.add(v6Box);

       JMenu menu=new JMenu("Commends");
        JMenuItem buyChoice=new JMenuItem("buy");
        buyChoice.addActionListener(this);
        menu.add(buyChoice);
       
        JMenuItem sellChoice=new JMenuItem("sell");
        sellChoice.addActionListener(this);
        menu.add(sellChoice);
        
        JMenuItem updateChoice=new JMenuItem("update");
        updateChoice.addActionListener(this);
        menu.add(updateChoice);
       
        JMenuItem gainChoice=new JMenuItem("getgain");
        gainChoice.addActionListener(this);
        menu.add(gainChoice);
        
        JMenuItem searchChoice=new JMenuItem("search");
        searchChoice.addActionListener(this);
        menu.add(searchChoice);
        
        JMenuItem quitChoice=new JMenuItem("quit");
        quitChoice.addActionListener(this);
        menu.add(quitChoice);
        
        JMenuBar bar=new JMenuBar();
        bar.add(menu);
        north.add(bar);
        north.add(sentence);
      //  buy.add(bar);
        buy.add(north,BorderLayout.NORTH);
        buy.add(south,BorderLayout.SOUTH);
        buy.add(center,BorderLayout.CENTER);
        buy.add(east,BorderLayout.EAST);
        buy.add(west,BorderLayout.WEST);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String commandString=e.getActionCommand();
        if(commandString.equals("buy")){
           card.show(mainPanel, "p2");
        }
        if(commandString.equals("sell")){
           card.show(mainPanel, "p3");
        }
        if(commandString.equals("update")){
           card.show(mainPanel, "p4");
        }
        if(commandString.equals("getgain")){
           card.show(mainPanel, "p5");
           
           
        }
        if(commandString.equals("search")){
           card.show(mainPanel, "p6");
        }
        if(commandString.equals("quit")){
           System.exit(0);
        }
        
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public JPanel getPanel(){
        return buy;
    }
    public boolean checkSymbolUnique(String a){
        for(int i=0;i<Portfolio.investlist.size();i++){
            if(Portfolio.investlist.get(i).getSymbol().equalsIgnoreCase(a))
                return false;
        }
        return true;
    }
    public boolean checkNameUnique(String a){
        for(int i=0;i<Portfolio.investlist.size();i++){
            if(Portfolio.investlist.get(i).getName().equalsIgnoreCase(a))
                return false;
        }
        return true;
    }
    
    
class Reset_ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            typelist.getItemAt(0);
            symbol_TextField.setText("");
            name_TextField.setText("");
            quantity_TextField.setText("");
            price_TextField.setText("");
            message.setText("");
        }
    
}
    
class Buy_ButtonListener implements ActionListener {
     
    public void actionPerformed(ActionEvent e) {
        
        String type_input=(String)typelist.getSelectedItem();
        
        String symbol_input=symbol_TextField.getText();
        if(symbol_input.isEmpty()||!checkSymbolUnique(symbol_input))
            message.append("Symbol can not be empty, and it must be unique");
        
        String name_input=name_TextField.getText();
        if(name_input.isEmpty()||!checkNameUnique(name_input))
            message.append("\nName can not be empty, and it must be unique");
        
        String quantity_input=quantity_TextField.getText();
        if(!port.quantity_Validity(quantity_input))
             message.append("\nQuantity must be integer, and can not less than 0");
        
        String price_input=price_TextField.getText();
        if(!port.price_Validity(price_input))
             message.append("\nPrice must be numbers,, and can not less than 0");
       
        if(symbol_input.isEmpty()||!checkSymbolUnique(symbol_input)||name_input.isEmpty()
         ||!checkNameUnique(name_input)||!port.quantity_Validity(quantity_input)||!port.price_Validity(price_input)){
            message.append("\nError happens, can not add!!\n");
            message.append("\n");
        }else{
           
           if(type_input.equals("Stock")){
               Stock stock=new Stock(type_input,symbol_input,name_input,
                       Integer.parseInt(quantity_input),Double.parseDouble(price_input));
               Portfolio.investlist.add(stock);
                
               message.append("\nAdd Stock Successfully\n");
               message.append("\n");
           }
           if(type_input.equals("MutualFund")){
               MutualFund fund=new MutualFund(type_input,symbol_input,name_input,
                       Integer.parseInt(quantity_input),Double.parseDouble(price_input));
               Portfolio.investlist.add(fund);
               
               message.append("\nAdd MutualFund Successfully\n");
               message.append("\n");
           }
         
        }
        
        //To change body of generated methods, choose Tools | Templates.
    }
}
}




