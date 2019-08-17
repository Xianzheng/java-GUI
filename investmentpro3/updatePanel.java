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

public class updatePanel extends JFrame implements ActionListener{
    public static final String buffer="            ";
    static int get=-1;
    Portfolio port;
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
    private JButton prev_button;
    private JButton next_button;
    private JButton save_button;
    private String[] type={"Stock","MutualFund"};
    public updatePanel(CardLayout Layout, JPanel panel){
        port=new Portfolio();
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
        
        message=new JTextArea(7,50);
        jsp= new JScrollPane(message);
        jsp.setSize(7, 50);
        jsp.setEnabled(rootPaneCheckingEnabled);
       
        Move_ButtonListener move_listen =new Move_ButtonListener();
        prev_button=new JButton("Prev");
        prev_button.addActionListener(move_listen);
        next_button=new JButton("Next");
        next_button.addActionListener(move_listen);
        save_button=new JButton("Save");
        save_button.addActionListener(move_listen);
 
        sentence=new JLabel("Update a price for Investment");
        type_Label=new JLabel(buffer+"Type");
        symbol_Label=new JLabel(buffer+"Symbol");
        name_Label=new JLabel(buffer+"Name");
        quantity_Label=new JLabel(buffer+"Quantity");
        price_Label=new JLabel(buffer+"Price");
        message_Label=new JLabel("Messages");
        
        
        symbol_TextField = new JTextField();
        symbol_TextField.setEditable(false);
        
        name_TextField = new JTextField();
        name_TextField.setEditable(false);
        quantity_TextField = new JTextField();
        price_TextField = new JTextField();
       
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
        center.setLayout(new GridLayout(3,1));
        west=new JPanel();
        west.setLayout(new GridLayout(3,1));
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Combobox
        typelist=new JComboBox(type); 
        typelist.addActionListener(this);
        typelist.setPreferredSize(new Dimension(10,10));
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Content in west panel

        west.add(symbol_Label);
        west.add(name_Label);
        west.add(price_Label);

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        h1Box.add(Box.createHorizontalStrut(30));
        h1Box.add(symbol_TextField);
        h1Box.add(Box.createHorizontalStrut(180));
        h1Box.add(prev_button);
        h1Box.add(Box.createHorizontalStrut(60));
        
        v1Box.add(Box.createVerticalStrut(22));
        v1Box.add(h1Box);
        v1Box.add(Box.createVerticalStrut(22));

        h4Box.add(Box.createHorizontalStrut(30));
        h4Box.add(name_TextField);
        h4Box.add(Box.createHorizontalStrut(80));
        h4Box.add(next_button);
        h4Box.add(Box.createHorizontalStrut(60));
             
        v4Box.add(Box.createVerticalStrut(20));
        v4Box.add(h4Box);
        v4Box.add(Box.createVerticalStrut(20));
        
        h5Box.add(Box.createHorizontalStrut(30));
        h5Box.add(price_TextField);
        h5Box.add(Box.createHorizontalStrut(218));
        h5Box.add(save_button);
        h5Box.add(Box.createHorizontalStrut(60));
             
        v5Box.add(Box.createVerticalStrut(25));
        v5Box.add(h5Box);
        v5Box.add(Box.createVerticalStrut(25));
        //vBox.add(symbol_TextField,Box.createHorizontalStrut(200));
        center.add(v1Box);
        //center.add(v2Box);
        //center.add(v3Box);
        center.add(v4Box);
        center.add(v5Box);
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~south

        h6Box.add(message_Label);
        h6Box.add(Box.createHorizontalStrut(460));
        h7Box.add(Box.createHorizontalStrut(30));
        h7Box.add(jsp); 
        v6Box.add(Box.createVerticalStrut(7));
        v6Box.add(h6Box);
        v6Box.add(Box.createVerticalStrut(7));
        v6Box.add(h7Box);
        south.add(v6Box);
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~MENU~~~~~~~~~~~~~~~~~~~~
        
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
       // this.setJMenuBar(bar);
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
class Move_ButtonListener implements ActionListener {
    
        @Override
        public void actionPerformed(ActionEvent e) {
            
            String move=e.getActionCommand();
            if(move.equals("Prev")){
               
                if(Portfolio.investlist.isEmpty()){
                    message.append("\nList is empty, you can not move\n");
                }else{
                    if(get>=1)//only possition of list must bigger and equal 0 
                        get--;
                    if(get>=0){
                        symbol_TextField.setText(Portfolio.investlist.get(get).getSymbol());
                        name_TextField.setText(Portfolio.investlist.get(get).getName());
                    }else{
                        message.append("\n\nThere is nothing in previous");
                    }
                }
                
            }
            if(move.equals("Next")){
                if(Portfolio.investlist.isEmpty()){
                    message.append("\nList is empty, you can not move\n");
                }else{
                    if(get<Portfolio.investlist.size()-1){
                        get++;
                    symbol_TextField.setText(Portfolio.investlist.get(get).getSymbol());
                    name_TextField.setText(Portfolio.investlist.get(get).getName());
                    }else{
                        message.append("\nThere is nothing in next");
                    }
                
                }
            }
            if(move.equals("Save")){
                if(!port.price_Validity(price_TextField.getText()))
                   message.append("\nprice should be numbers and bigger than 0");
                
                if(symbol_TextField.getText().isEmpty())
                   message.append("\n Symbol can not be empty, you can use prev or next to get");
                
                if(name_TextField.getText().isEmpty())
                   message.append("\n Name can not be empty, you can use prev or next to get");
                   
                if(symbol_TextField.getText().isEmpty()||name_TextField.getText().isEmpty()
                   ||!port.price_Validity(price_TextField.getText())){
                    message.append("\nError happens, you can not upate\n");
                }else{
                    Portfolio.investlist.get(get).setPrice(Double.parseDouble(price_TextField.getText()));
                    message.append("\n\nUpdate success, current data are");
                    message.append("\n"+Portfolio.investlist.get(get).toString());
                }
            }
            
    
        }

}
}
