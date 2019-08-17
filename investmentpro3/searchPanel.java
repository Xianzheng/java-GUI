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

public class searchPanel extends JFrame implements ActionListener{
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
    public searchPanel(CardLayout Layout, JPanel panel){
        port =new Portfolio();
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
        
        message=new JTextArea(7,40);
        jsp= new JScrollPane(message);
        jsp.setSize(7, 30);
        jsp.setEnabled(rootPaneCheckingEnabled);
       
        //message.setLineWrap(true);
       // message.setSize(100, 100);
        //message.
        
        //message.setLineWrap(true);
        //message.setEnabled(true);
        Reset_ButtonListener reset_listen =new Reset_ButtonListener();
        Search_ButtonListener  search_listen =new Search_ButtonListener();
        reset_button=new JButton(" Reset");
        reset_button.addActionListener(reset_listen);
        buy_button=new JButton("Search");
        buy_button.addActionListener(search_listen);
 
        sentence=new JLabel("Searching Investments");
        type_Label=new JLabel(buffer+"Keywords");
        symbol_Label=new JLabel(buffer+"Symbol");
        name_Label=new JLabel(buffer+"Name");
        quantity_Label=new JLabel(buffer+"Low Price");
        price_Label=new JLabel(buffer+"High Price");
        message_Label=new JLabel("Messages");
        
        
        symbol_TextField = new JTextField();
        symbol_TextField.addActionListener(this);
        
        name_TextField = new JTextField();
        name_TextField.addActionListener(this);
        quantity_TextField = new JTextField();
        quantity_TextField.addActionListener(this);
        price_TextField = new JTextField();
        price_TextField.addActionListener(this);
        
        
        
        mainPanel = panel;
        card = Layout;
        //~~~~~~~~~~~~~~~~~~~~~~~Panel
        buy=new JPanel();
        buy.setLayout(new BorderLayout());
        north=new JPanel();
        north.setLayout(new GridLayout(2,1));
        south=new JPanel();  
       // south.setLayout(new GridLayout(2,1));
        east=new JPanel();
        east.setLayout(new GridLayout(2,1));
        center=new JPanel();
        center.setLayout(new GridLayout(4,1));
        west=new JPanel();
        west.setLayout(new GridLayout(1,1));
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Combobox
        typelist=new JComboBox(type); 
        typelist.addActionListener(this);
        typelist.setPreferredSize(new Dimension(10,10));
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Content in west panel
        Box vBox=Box.createVerticalBox(); 
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(symbol_Label);
        vBox.add(Box.createVerticalStrut(23));
        vBox.add(name_Label);
        vBox.add(type_Label);
        vBox.add(Box.createVerticalStrut(26));
        vBox.add(quantity_Label);
        vBox.add(Box.createVerticalStrut(29));
        vBox.add(price_Label);
        //vBox.add(Box.createVerticalStrut(7));
        
        west.add(vBox);
        
       
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
       /* h1Box.add(Box.createHorizontalStrut(30));
        h1Box.add(typelist);
        h1Box.add(Box.createHorizontalStrut(120));
        h1Box.add(reset_button);
        h1Box.add(Box.createHorizontalStrut(60));
        
        v1Box.add(Box.createVerticalStrut(10));
        v1Box.add(h1Box);
        v1Box.add(Box.createVerticalStrut(10));*/
        
        h2Box.add(Box.createHorizontalStrut(30));
        h2Box.add(symbol_TextField);
        h2Box.add(Box.createHorizontalStrut(120));
        h2Box.add(reset_button);
        h2Box.add(Box.createHorizontalStrut(60));
        
        v2Box.add(Box.createVerticalStrut(23));
        v2Box.add(h2Box);
        v2Box.add(Box.createVerticalStrut(23));
        
        h3Box.add(Box.createHorizontalStrut(30));
        h3Box.add(name_TextField);
        h3Box.add(Box.createHorizontalStrut(180));
             
        v3Box.add(Box.createVerticalStrut(12));
        v3Box.add(h3Box);
        v3Box.add(Box.createVerticalStrut(12));
        
        h4Box.add(Box.createHorizontalStrut(30));
        h4Box.add(quantity_TextField);
        h4Box.add(Box.createHorizontalStrut(200));
        h4Box.add(buy_button);
        h4Box.add(Box.createHorizontalStrut(60));
             
        v4Box.add(Box.createVerticalStrut(12));
        v4Box.add(h4Box);
        v4Box.add(Box.createVerticalStrut(12));
        
        h5Box.add(Box.createHorizontalStrut(30));
        h5Box.add(price_TextField);
        h5Box.add(Box.createHorizontalStrut(337));
             
        v5Box.add(Box.createVerticalStrut(12));
        v5Box.add(h5Box);
        v5Box.add(Box.createVerticalStrut(12));
        //vBox.add(symbol_TextField,Box.createHorizontalStrut(200));
      //  center.add(v1Box);
        center.add(v2Box);
        center.add(v3Box);
        center.add(v4Box);
        center.add(v5Box);
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~south
        
        //message.addAncestorListener((AncestorListener) this);
        
        h6Box.add(message_Label);
        h6Box.add(Box.createHorizontalStrut(460));
        
        //h7Box.add(Box.createHorizontalStrut(40));
        h7Box.add(jsp);
        //h7Box.add(Box.createHorizontalStrut(40));
        v6Box.add(Box.createVerticalStrut(7));
        v6Box.add(h6Box);
        v6Box.add(Box.createVerticalStrut(7));
        v6Box.add(h7Box);
        //v6Box.add(Box.createVerticalStrut(7));
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
    public boolean searchForKey(String key, int i){
        String temp2[]=key.split(" ");
        String temp1[]=Portfolio.investlist.get(i).getName().split(" ");
        int k=temp1.length;//search
        int j=temp2.length;//input
        int count=0;
        for(int x=0;x<j;x++)
            {
            for(int z=0;z<k;z++){
                if(temp2[x].equalsIgnoreCase(temp1[z]))
                count++;
                }
            }
        if(count==j)
        return true;
        return false;
    }
    public boolean SearchForSymbol(String Symbol, int i){
        if(Portfolio.investlist.get(i).getSymbol().equals(Symbol))
            return true;
        return false;
    }
    public boolean SearchForLow(double price, int i){
        if(Portfolio.investlist.get(i).getPrice()>price)
            return true;
        return false;
    }
    public boolean SearchForHigh(double price, int i){
        if(Portfolio.investlist.get(i).getPrice()<price)
            return true;
        return false;
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
class Search_ButtonListener implements ActionListener {

        @Override
        
        public void actionPerformed(ActionEvent e) {
            String symbol_input=symbol_TextField.getText();
        
            String name_input=name_TextField.getText();
            
            String low_price_input=quantity_TextField.getText();
            //if(!port.price_Validity(low_price_input))
            //message.append("\nPrice must be numbers,, and can not less than 0");
            
            String high_price_input=price_TextField.getText();
            //if(!port.price_Validity(high_price_input))
            //message.append("\nPrice must be numbers, and can not less than 0");
            
            if(Portfolio.investlist.isEmpty()){
                message.append("\nList is empty, there is nothing");
            }else{
                if(symbol_input.isEmpty()&&name_input.isEmpty()&&low_price_input.isEmpty()
                        &&high_price_input.isEmpty()){
                    for(int i=0;i<Portfolio.investlist.size();i++)
                    message.append(Portfolio.investlist.get(i).toString()+"\n");
                }
                if(!symbol_input.isEmpty()&&name_input.isEmpty()&&low_price_input.isEmpty()
                        &&high_price_input.isEmpty()){
                    int check=0;
                    for(int i=0;i<Portfolio.investlist.size();i++){
                        if(SearchForSymbol(symbol_input,i)){
                            message.append(Portfolio.investlist.get(i).toString()+"\n");
                            check++;
                        }
                    }if(check==0){
                        message.append("\nThere is no found\n");
                    }
                }
                if(symbol_input.isEmpty()&&!name_input.isEmpty()&&low_price_input.isEmpty()
                        &&high_price_input.isEmpty()){
                    int check=0;
                    for(int i=0;i<Portfolio.investlist.size();i++){     
                        if(searchForKey(name_input,i)){
                            message.append(Portfolio.investlist.get(i).toString()+"\n");
                            check++;
                        }
                    }if(check==0){
                        message.append("\nThere is no found\n");
                    }
                }
                if(symbol_input.isEmpty()&&name_input.isEmpty()&&!low_price_input.isEmpty()
                        &&high_price_input.isEmpty()){
                    int check=0;
                    if(!port.price_Validity(low_price_input)){
                        message.append("\nPrice should be numbers and no negative\n");
                    }else{
                        
                        for(int i=0;i<Portfolio.investlist.size();i++){
                            if(SearchForLow(Double.parseDouble(low_price_input),i)){
                                message.append(Portfolio.investlist.get(i).toString()+"\n");
                                check++;
                            }
                        }if(check==0){
                            message.append("\nThere is no found\n");
                        }
                    }
                    
                }
                if(symbol_input.isEmpty()&&name_input.isEmpty()&&low_price_input.isEmpty()
                        &&!high_price_input.isEmpty()){
                    int check=0;
                    if(!port.price_Validity(high_price_input)){
                        message.append("\nPrice should be numbers and no negative\n");
                    }else{
                        
                        for(int i=0;i<Portfolio.investlist.size();i++){
                            if(SearchForHigh(Double.parseDouble(high_price_input),i)){
                                message.append("\n"+Portfolio.investlist.get(i).toString()+"\n");
                                check++;
                            }
                        }
                        if(check==0){
                            message.append("\nThere is no find\n");
                        }
                    }
                    
                }
                if(!symbol_input.isEmpty()&&!name_input.isEmpty()&&low_price_input.isEmpty()
                        &&high_price_input.isEmpty()){
                    int check=0;
                    for(int i=0;i<Portfolio.investlist.size();i++){
                        if(SearchForSymbol(symbol_input,i)&&searchForKey(name_input,i)){
                            message.append("\n"+Portfolio.investlist.get(i).toString()+"\n");
                        check++;
                        }
                    }
                    if(check==0){
                        message.append("\nThere is no found\n");
                    }
                }
                if(!symbol_input.isEmpty()&&name_input.isEmpty()&&!low_price_input.isEmpty()
                        &&high_price_input.isEmpty()){
                    int check=0;
                    if(!port.price_Validity(low_price_input)){
                        message.append("\nPrice should be numbers and no negative\n");
                    }else{
                        
                        for(int i=0;i<Portfolio.investlist.size();i++){
                            if(SearchForSymbol(symbol_input,i)&&SearchForLow(Double.parseDouble(low_price_input),i)){
                                message.append(Portfolio.investlist.get(i).toString()+"\n");
                            check++;
                            }
                        }
                        if(check==0){
                             message.append("\nThere is no found\n");
                        }
                    }
                }
                if(!symbol_input.isEmpty()&&name_input.isEmpty()&&low_price_input.isEmpty()
                        &&!high_price_input.isEmpty()){
                    int check=0;
                    if(!port.price_Validity(high_price_input)){
                        message.append("\nPrice should be numbers and no negative\n");
                    }else{
                        
                        for(int i=0;i<Portfolio.investlist.size();i++){
                            if(SearchForSymbol(symbol_input,i)&&SearchForHigh(Double.parseDouble(high_price_input),i)){
                                message.append(Portfolio.investlist.get(i).toString()+"\n");
                            check++;
                            }
                        }
                        if(check==0){
                             message.append("\nThere is no found\n");
                        }
                    }
                }
                if(symbol_input.isEmpty()&&!name_input.isEmpty()&&!low_price_input.isEmpty()
                        &&high_price_input.isEmpty()){
                    int check=0;
                    if(!port.price_Validity(low_price_input)){
                        message.append("\nPrice should be numbers and no negative\n");
                    }else{
                        
                        for(int i=0;i<Portfolio.investlist.size();i++){
                            if(searchForKey(name_input,i)&&SearchForLow(Double.parseDouble(low_price_input),i)){
                                message.append(Portfolio.investlist.get(i).toString()+"\n");
                            check++;
                            }
                        }
                        if(check==0){
                             message.append("\nThere is no found\n");
                        }
                    }
                }
                if(symbol_input.isEmpty()&&!name_input.isEmpty()&&low_price_input.isEmpty()
                        &&!high_price_input.isEmpty()){
                    int check=0;
                    if(!port.price_Validity(high_price_input)){
                        message.append("\nPrice should be numbers and no negative\n");
                    }else{
                        
                        for(int i=0;i<Portfolio.investlist.size();i++){
                            if(searchForKey(name_input,i)&&SearchForHigh(Double.parseDouble(high_price_input),i)){
                                message.append(Portfolio.investlist.get(i).toString()+"\n");
                            check++;
                            }
                        }
                        if(check==0){
                             message.append("\nThere is no found\n");
                        }
                    }
                }
                if(symbol_input.isEmpty()&&name_input.isEmpty()&&!low_price_input.isEmpty()
                        &&!high_price_input.isEmpty()){
                    int check=0;
                    if(!port.price_Validity(low_price_input)||!port.price_Validity(high_price_input)){
                        message.append("\nPrice should be numbers and no negative\n");
                    }else{
                 
                        for(int i=0;i<Portfolio.investlist.size();i++){
                            if(SearchForLow(Double.parseDouble(low_price_input),i)&&SearchForHigh(Double.parseDouble(high_price_input),i)){
                                message.append(Portfolio.investlist.get(i).toString()+"\n");
                            check++;
                            }
                        }
                        if(check==0){
                             message.append("\nThere is no found\n");
                        }
                    }
                }
                if(!symbol_input.isEmpty()&&!name_input.isEmpty()&&!low_price_input.isEmpty()
                        &&high_price_input.isEmpty()){
                    int check=0;
                    if(!port.price_Validity(low_price_input)){
                        message.append("\nPrice should be numbers and no negative\n");
                    }else{
                 
                        for(int i=0;i<Portfolio.investlist.size();i++){
                            if(SearchForLow(Double.parseDouble(low_price_input),i)
                             &&SearchForSymbol(symbol_input,i)&&searchForKey(name_input,i)){
                                message.append(Portfolio.investlist.get(i).toString()+"\n");
                            check++;
                            }
                        }
                        if(check==0){
                             message.append("\nThere is no found\n");
                        }
                    }
                }
                if(!symbol_input.isEmpty()&&!name_input.isEmpty()&&low_price_input.isEmpty()
                        &&!high_price_input.isEmpty()){
                    int check=0;
                    if(!port.price_Validity(high_price_input)){
                        message.append("\nPrice should be numbers and no negative\n");
                    }else{
                 
                        for(int i=0;i<Portfolio.investlist.size();i++){
                            if(SearchForHigh(Double.parseDouble(high_price_input),i)
                             &&SearchForSymbol(symbol_input,i)&&searchForKey(name_input,i)){
                                message.append(Portfolio.investlist.get(i).toString()+"\n");
                            check++;
                            }
                        }
                        if(check==0){
                             message.append("\nThere is no found\n");
                        }
                    }
                }
                if(!symbol_input.isEmpty()&&name_input.isEmpty()&&!low_price_input.isEmpty()
                        &&!high_price_input.isEmpty()){
                    int check=0;
                    if(!port.price_Validity(high_price_input)&&!port.price_Validity(low_price_input)){
                        message.append("\nPrice should be numbers and no negative\n");
                    }else{
                 
                        for(int i=0;i<Portfolio.investlist.size();i++){
                            if(SearchForHigh(Double.parseDouble(high_price_input),i)
                             &&SearchForSymbol(symbol_input,i)&&SearchForLow(Double.parseDouble(low_price_input),i)){
                                message.append(Portfolio.investlist.get(i).toString()+"\n");
                            check++;
                            }
                        }
                        if(check==0){
                             message.append("\nThere is no found\n");
                        }
                    }
                }
                if(symbol_input.isEmpty()&&!name_input.isEmpty()&&!low_price_input.isEmpty()
                        &&!high_price_input.isEmpty()){
                    int check=0;
                    if(!port.price_Validity(high_price_input)&&!port.price_Validity(low_price_input)){
                        message.append("\nPrice should be numbers and no negative\n");
                    }else{
                 
                        for(int i=0;i<Portfolio.investlist.size();i++){
                            if(SearchForHigh(Double.parseDouble(high_price_input),i)
                             &&searchForKey(name_input,i)&&SearchForLow(Double.parseDouble(low_price_input),i)){
                                message.append(Portfolio.investlist.get(i).toString()+"\n");
                            check++;
                            }
                        }
                        if(check==0){
                             message.append("\nThere is no found\n");
                        }
                    }
                }
                if(!symbol_input.isEmpty()&&!name_input.isEmpty()&&!low_price_input.isEmpty()
                        &&!high_price_input.isEmpty()){
                    int check=0;
                    if(!port.price_Validity(high_price_input)&&!port.price_Validity(low_price_input)){
                        message.append("\nPrice should be numbers and no negative\n");
                    }else{
                 
                        for(int i=0;i<Portfolio.investlist.size();i++){
                            if(SearchForHigh(Double.parseDouble(high_price_input),i)
                             &&searchForKey(name_input,i)&&SearchForLow(Double.parseDouble(low_price_input),i)
                               &&SearchForSymbol(symbol_input,i)){
                                message.append(Portfolio.investlist.get(i).toString()+"\n");
                            check++;
                            }
                        }
                        if(check==0){
                             message.append("\nThere is no found\n");
                        }
                    }
                }
                
            }
        }
    
}
}

