import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * Created by DavidBoutet on 17-07-10.
 */
public class BatailleNavale {
    //instance variables
    private JFrame frame;
    private ActionListener btnListener;
    private HashMap componentMap;

    //class constant
    public final static int WIDTH_FRAME = 600;
    public final static int HEIGHT_FRAME = 580;
    public final static int BTN_WIDTH = 130;
    public final static int BTN_HEIGHT = 30;
    public final static String LOGOASCII = "" +
            " ____    ____  ______   ____  ____  _      _        ___ \n" +
            "|    \\  /    ||      | /    ||    || |    | |      /  _]\n" +
            "|  o  )|  o  ||      ||  o  | |  | | |    | |     /  [_ \n" +
            "|     ||     ||_|  |_||     | |  | | |___ | |___ |    _]\n" +
            "|  O  ||  _  |  |  |  |  _  | |  | |     ||     ||   [_ \n" +
            "|     ||  |  |  |  |  |  |  | |  | |     ||     ||     |\n" +
            "|_____||__|__|  |__|  |__|__||____||_____||_____||_____|\n" +
            "                                                         \n" +
            "       ____    ____  __ __   ____  _        ___           \n" +
            "      |    \\  /    ||  |  | /    || |      /  _]          \n" +
            "      |  _  ||  o  ||  |  ||  o  || |     /  [_           \n" +
            "      |  |  ||     ||  |  ||     || |___ |    _]          \n" +
            "      |  |  ||  _  ||  :  ||  _  ||     ||   [_           \n" +
            "      |  |  ||  |  | \\   / |  |  ||     ||     |          \n" +
            "      |__|__||__|__|  \\_/  |__|__||_____||_____|";


    public BatailleNavale(){

    }

    public void initGame(){
        frame = new JFrame("BATTLESHIP!");
        frame.setSize(WIDTH_FRAME, HEIGHT_FRAME);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        launchMenu();
    }

    private void launchMenu(){
        JPanel topPanel = topPanel(),
               middlePanel = middlePanel(),
               bottomPanel = bottomPanel();

        frame.getContentPane().add(topPanel);
        frame.getContentPane().add(middlePanel);
        frame.getContentPane().add(bottomPanel);
        frame.setVisible(true);
    }

    private JPanel topPanel(){
        JPanel topPanel = new JPanel(null);

        JLabel difficulty = new JLabel("Niveau: ");

        topPanel.setBackground(Color.white);
        topPanel.setBounds(0, 0, WIDTH_FRAME, HEIGHT_FRAME / 4);
        //difficulty
        difficulty.setBounds(30, (topPanel.getHeight()/2)-(30/2), 100, 30);
        topPanel.add(difficulty);
        final ButtonGroup groupBtnRadio = new ButtonGroup();
        final JRadioButton btnRadio1 = new JRadioButton("Débutant"),
                btnRadio2 = new JRadioButton("Intermédiaire"),
                btnRadio3 = new JRadioButton("Expert");

        //radio 1
        btnRadio1.setBounds(difficulty.getX()+60, (topPanel.getHeight()/2)-(30/2), BTN_WIDTH, BTN_HEIGHT);
        btnRadio1.setSelected(true);

        //radio 2
        btnRadio2.setBounds(btnRadio1.getX() + btnRadio1.getWidth(),
                btnRadio1.getY(), BTN_WIDTH, BTN_HEIGHT);

        //radio 3
        btnRadio3.setBounds(btnRadio2.getX() + btnRadio2.getWidth(),
                btnRadio2.getY(), BTN_WIDTH, BTN_HEIGHT);

        //group radio button
        groupBtnRadio.add(btnRadio1);
        groupBtnRadio.add(btnRadio2);
        groupBtnRadio.add(btnRadio3);

        //button
        final JButton btn = new JButton("Jouer");
        btn.setBounds(btnRadio3.getX() + btnRadio3.getWidth(), btnRadio3.getY(), 100, 30);
        btn.setForeground(Color.cyan);

        topPanel.add(btnRadio1);
        topPanel.add(btnRadio2);
        topPanel.add(btnRadio3);
        topPanel.add(btn);

        btnListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evenement) {
                if (evenement.getSource() == btn){

                }
            }
        };

        btn.addActionListener(btnListener);

        return topPanel;
    }

    private JPanel middlePanel(){
        JPanel middlePanel = new JPanel(null);
        middlePanel.setBackground(Color.white);
        middlePanel.setBounds(0,WIDTH_FRAME/4,WIDTH_FRAME,HEIGHT_FRAME/2);
        JTextArea logo = new JTextArea(LOGOASCII);
        logo.setEditable(false);
        logo.setFont (new Font("Courier", Font.PLAIN, 17));
        logo.setBounds(20,0,WIDTH_FRAME,HEIGHT_FRAME/2);
        logo.setForeground(Color.cyan);
        middlePanel.add(logo);
        return middlePanel;
    }

    private JPanel bottomPanel(){
        JPanel bottomPanel = new JPanel(null);
        bottomPanel.setBackground(Color.white);
        bottomPanel.setBounds(0, HEIGHT_FRAME / 4 * 3, WIDTH_FRAME, HEIGHT_FRAME / 4);

        JLabel name = new JLabel("Nom du joueur: ");
        name.setBounds((WIDTH_FRAME/2-110/2)-50, bottomPanel.getHeight()/2-30/2, 110, 30);
        JTextArea areaName = new JTextArea();
        areaName.setBounds(name.getX()+name.getWidth()+10, name.getY(), 150, 30);
        areaName.setBorder(BorderFactory.createLineBorder(Color.cyan, 1));
        areaName.setFont(new Font("Courier", Font.BOLD, 24));
        areaName.requestFocusInWindow();

        bottomPanel.add(name);
        bottomPanel.add(areaName);
        return bottomPanel;
    }

    private void launchGame(){


    }


    public static void println(Object o){
        System.out.println(o);
    }
}
