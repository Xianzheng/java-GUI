
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

public class gainPanel extends JFrame implements ActionListener{
    public static final String buffer="            ";
    private Thread thread = null;
    private JPanel buy;
    private JPanel north;
    private JPanel south;
    private JPanel center;
    private JPanel east;
    private JPanel west;
    private JPanel mainPanel;
    private JLabel sentence;
    private JLabel gain_Label;
    private JLabel message_Label;
    
    private JButton button;
    private JTextField gain_TextField;
    
    
    private JTextArea message;
    private JScrollPane jsp;
    
    private CardLayout card;
    
    private String[] type={"Stock","MutualFund"};
    
    private double price;
    private int quantity;
    private double bookvalue;
    public  gainPanel(CardLayout Layout, JPanel panel){
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
        
        gain_Label=new JLabel("Get Total Gain");
        message_Label=new JLabel("Individual Gain");
        message=new JTextArea(7,50);
        jsp= new JScrollPane(message);
        
        ButtonListener get_listen=new ButtonListener();
        jsp.setEnabled(false);
        button=new JButton("get");
        button.addActionListener(get_listen);
        sentence=new JLabel("Getting Total gain");
   
        gain_TextField = new JTextField();
        
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
        center.setLayout(new FlowLayout());
        west=new JPanel();
        west.setLayout(new GridLayout(3,1));
        jsp.setPreferredSize(new Dimension(500,400));
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Combobox
       
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Content in west panel
        
      
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        h1Box.add(Box.createHorizontalStrut(30));
        h1Box.add(gain_Label);
        h1Box.add(Box.createHorizontalStrut(30));
        h1Box.add(gain_TextField);
        h1Box.add(Box.createHorizontalStrut(70));
        h1Box.add(button);
        h1Box.add(Box.createHorizontalStrut(70));
        
        h2Box.add(Box.createHorizontalStrut(30));
        h2Box.add(message_Label);
        h2Box.add(Box.createHorizontalStrut(450));
        
        h3Box.add(Box.createHorizontalStrut(30));
        h3Box.add(jsp);
        h3Box.add(Box.createHorizontalStrut(30));
        
        v1Box.add(Box.createVerticalStrut(22));
        v1Box.add(h1Box);
        v1Box.add(Box.createVerticalStrut(22));
        v1Box.add(h2Box);
        v1Box.add(Box.createVerticalStrut(15));
        v1Box.add(h3Box);
        

        
        center.add(v1Box);

    
        
       
        
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
class ButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            double gain[]=new double[10];
            double Totalgain=0;
            for(int i=0;i<Portfolio.investlist.size();i++){
                price=Portfolio.investlist.get(i).getPrice();
                quantity=Portfolio.investlist.get(i).getQuantity();
                bookvalue=Portfolio.investlist.get(i).getBookValue();
                gain[i]=price*quantity-bookvalue;
            
                 message.append("Gain for Investment Item "+(i+1)+"is"
                    +price+"*"+quantity+"-"+bookvalue+"="+gain[i]+"\n");
            }
            for(int i=0;i<gain.length;i++){
                Totalgain+=gain[i];
            }
            String s=String.valueOf(Totalgain);
            gain_TextField.setText(s);
            
        }
    
}
    
}



