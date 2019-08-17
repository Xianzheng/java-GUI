/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package investmentpro3;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class welcomePanel extends JFrame implements ActionListener{
    public static final String Text_Area = "\n\n\n\n  Welcome to Investment Portfolio\n"
            + "\n\n\n  Choose a command from the Commands menu to buy or sell\n  an investment"
            + ", update prices for all investments, get gain for\n  the portfolio,"
            + "  search for relevant investment,or quit\n  the program.";
    private JTextArea textArea;
    private JPanel welcome;
    private JPanel mainPanel;
    private CardLayout card;
    
    public welcomePanel(CardLayout Layout, JPanel panel){
        
        mainPanel = panel;
        card = Layout;
        welcome=new JPanel();
        textArea =new JTextArea();
        
        welcome.setLayout(new BorderLayout());
        textArea.setText(Text_Area);
        
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
        welcome.add(bar,BorderLayout.NORTH);
        welcome.add(textArea,BorderLayout.CENTER);
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
        return welcome;
    }
}

