/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package investmentpro3;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author markf
 */
public class InvestmentPro3 extends JFrame{

    /**
     * @param args the command line arguments
     */
    
    public static final String Text_Area = "\n\n\n\n  Welcome to Investment Portfolio\n"
            + "\n\n\n  Choose a command from the Commands menu to buy or sell\n  an investment"
            + ", update prices for all investments, get gain for\n  the portfolio,"
            + "  search for relevant investment,or quit\n  the program.";
    public static final int WIDTH=550;
    public static final int HEIGHT=450;
    public static final String buffer="            ";
    
    public static final String wordsOn_type="Type";
    public static final String wordsOn_symbol="Symbol";
    public static final String wordsOn_name="Name";
    public static final String wordsOn_quantity="Quantity";
    public static final String wordsOn_price="Price";
    public static final String wordsOn_buyPanel="Buying an investment";
    public static final String wordsOn_sellPanel="Selling an investment";
    public static final String wordsOn_updatePanel="Updating an investment";
    public static final String wordsOn_Searching="Searching an investment";


            
    //" buy, sell, update, getGain, search, load, and quit.
    private JPanel mainPanel;
    private JPanel buyPanel;
    private JPanel sell;
    private JPanel update;
    private JPanel getGain;
    private JPanel search;
    private JPanel welcome;
    private CardLayout card=null;
    
    private JTextArea textArea_onFrame;
    
    private JLabel state;
    private JLabel type;
    private JLabel symbol;
    private JLabel name;
    private JLabel quantity;
    private JLabel price;
    
    
    public InvestmentPro3()
    {
        super();
        this.setSize(WIDTH,HEIGHT);
        this.setTitle("Investment Portfolio");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        card=new CardLayout(5,5);
        mainPanel=new JPanel(card);
        welcomePanel wel=new welcomePanel(card, mainPanel);
        buyPanel buy=new buyPanel(card, mainPanel);
        sellPanel sell=new sellPanel(card,mainPanel);
        updatePanel update = new updatePanel(card,mainPanel);
        gainPanel gain = new gainPanel(card,mainPanel);
        searchPanel search = new searchPanel(card,mainPanel);
        
         
         mainPanel.add(wel.getPanel(),"p1");
         mainPanel.add(buy.getPanel(), "p2");
         mainPanel.add(sell.getPanel(),"p3");
         mainPanel.add(update.getPanel(),"p4");
         mainPanel.add(gain.getPanel(),"p5");
        mainPanel.add(search.getPanel(),"p6");
                
        this.add(mainPanel);
        
   
    }
    public static void main(String[] args) {
        InvestmentPro3 GUI = new InvestmentPro3();
        GUI.setVisible(true);
        
// TODO code application logic here
    }
 
}

