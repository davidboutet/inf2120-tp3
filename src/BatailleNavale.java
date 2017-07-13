import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by DavidBoutet on 17-07-10.
 */
public class BatailleNavale {
    //constant
    public final static int WIDTH_FRAME = 600;
    public final static int HEIGHT_FRAME = 580;
    public final static int BTN_WIDTH = 130;
    public final static int BTN_HEIGHT = 30;
    private static final int ROWS = 8;
    private static final int COLS = ROWS;
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

    //frame
    private JFrame frame;
    private char[] gridSolution;
    private Integer shotsRemaining = 0;
    private Integer shotsToCompleteGame = 0;

    //panel
    private JPanel topPanel,
            middlePanel,
            bottomPanel,
            gamePanel;

    //button
    private JButton playBtn;
    private JButton[][] buttonGrid = new JButton[ROWS][COLS];

    //radio button
    private JRadioButton btnRadio1 = new JRadioButton("Débutant"),
                         btnRadio2 = new JRadioButton("Intermédiaire"),
                         btnRadio3 = new JRadioButton("Expert");

    //button group
    private ButtonGroup groupBtnRadio;

    //label
    private JLabel infoBox;

    //textfield
    private JTextField playerName;

    //event listener
    private ActionListener eventListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evenement) {
            eventDispatcher(evenement);
        }
    };

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

    //launch first page
    private void launchMenu(){
        JPanel topPanel = topPanel(),
               middlePanel = middlePanel(),
               bottomPanel = bottomPanel();

        frame.getContentPane().add(topPanel);
        frame.getContentPane().add(middlePanel);
        frame.getContentPane().add(bottomPanel);
        frame.setVisible(true);
        playerName.requestFocusInWindow();
    }

    //top panel
    private JPanel topPanel(){
        topPanel = new JPanel(null);
        JLabel difficulty = new JLabel("Niveau: ");

        topPanel.setBackground(Color.white);
        topPanel.setBounds(0, 0, WIDTH_FRAME, HEIGHT_FRAME / 4);

        //difficulty
        difficulty.setBounds(30, (topPanel.getHeight()/2)-(30/2), 100, 30);

        groupBtnRadio = new ButtonGroup();

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
        playBtn = new JButton("Jouer");
        playBtn.setBounds(btnRadio3.getX() + btnRadio3.getWidth(), btnRadio3.getY(), 100, 30);
        playBtn.setForeground(Color.cyan);

        //info box
        infoBox = new JLabel("", SwingConstants.CENTER);
        infoBox.setOpaque(true);
        infoBox.setBackground(Color.black);
        infoBox.setBounds(0, (HEIGHT_FRAME / 4) - 40, WIDTH_FRAME, 40);
        infoBox.setForeground(Color.white);
        infoBox.setVisible(false);


        topPanel.add(difficulty);
        topPanel.add(btnRadio1);
        topPanel.add(btnRadio2);
        topPanel.add(btnRadio3);
        topPanel.add(playBtn);
        topPanel.add(infoBox);

        playBtn.addActionListener(eventListener);

        return topPanel;
    }

    //middle panel
    private JPanel middlePanel(){
        middlePanel = new JPanel(null);
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

    //bottom panel
    private JPanel bottomPanel(){
        bottomPanel = new JPanel(null);
        bottomPanel.setBackground(Color.white);
        bottomPanel.setBounds(0, HEIGHT_FRAME / 4 * 3, WIDTH_FRAME, HEIGHT_FRAME / 4);

        JLabel name = new JLabel("Nom du joueur: ");
        name.setBounds((WIDTH_FRAME/2-110/2)-50, bottomPanel.getHeight()/2-30/2, 110, 30);
        playerName = new JTextField();
        playerName.setBounds(name.getX()+name.getWidth()+10, name.getY(), 150, 30);
        playerName.setBorder(BorderFactory.createLineBorder(Color.cyan, 1));
        playerName.setFont(new Font("Courier", Font.BOLD, 24));
        playerName.addActionListener(eventListener);

        bottomPanel.add(name);
        bottomPanel.add(playerName);
        return bottomPanel;
    }

    //return selected difficulty
    private Integer getSelectedDifficulty(){
        return btnRadio1.isSelected()?1:btnRadio2.isSelected()?2:btnRadio3.isSelected()?3:0;
    }

    //return player name
    private String getPlayerName(){
        return playerName.getText();
    }

    //action event
    private void eventDispatcher(ActionEvent e){
        if (e.getSource() == playBtn || e.getSource() == playerName){
            if((getPlayerName().length() >= 3 && getPlayerName().length() <= 10) && getSelectedDifficulty() != 0){
                launchGame();
            }else {
                JOptionPane.showMessageDialog(frame, "Nom obligatoire et doit contenir entre 3 et 10 caractères inclusivement.");
            }
        }else{
            int position = 0;
            for (int row = 0; row < buttonGrid.length; row++) {
                for (int col = 0; col < buttonGrid[row].length; col++) {
                    if (buttonGrid[row][col] == e.getSource()) {
                        checkHit(position, buttonGrid[row][col]);
                    }
                    position++;
                }
            }
        }
    }

    private void launchGame(){
        infoBox.setText(getPlayerName()+", éxécutez votre premier tir.");
        infoBox.setVisible(true);
        middlePanel.setVisible(false);
        bottomPanel.setVisible(false);
        shotsRemaining = getSelectedDifficulty()==1?45:getSelectedDifficulty()==2?35:25;
        setupGame();
    }


    private void setupGame(){
        JPanel board = new JPanel(null);
        gamePanel = new JPanel(null);
        gamePanel.setBackground(Color.red);
        gamePanel.setBounds(0, HEIGHT_FRAME/4, WIDTH_FRAME, HEIGHT_FRAME-(HEIGHT_FRAME/4));
        board.setLayout(new GridLayout(8, 8));
        board.setBounds(0, 0, 300, 300);

        for (int row = 0; row < buttonGrid.length; row++) {
            for (int col = 0; col < buttonGrid[row].length; col++) {
                buttonGrid[row][col] = new JButton();
                buttonGrid[row][col].addActionListener(eventListener);
                board.add(buttonGrid[row][col]);
            }
        }

        gamePanel.add(board);
        frame.add(gamePanel);
        gridSolution = JeuUtils.genererGrilleSolution().toCharArray();
        for (int i = 0; i < gridSolution.length; i++){
            if(gridSolution[i]=='B'){
                shotsToCompleteGame++;
            }
        }
    }

    private void checkHit(Integer position, JButton currentButton){
        if(position <= gridSolution.length){
            if(shotsRemaining < shotsToCompleteGame){
                JOptionPane.showMessageDialog(frame, "Il ne reste plus assez de coup pour gagné la partie:(");
                launchGame();
            }
            Character find = gridSolution[position];
            if(find.equals('B')){
                currentButton.setText("B");
                currentButton.setBackground(Color.cyan);
                currentButton.setOpaque(true);
                infoBox.setText("Tir réussi!");
                shotsToCompleteGame--;
            }else if (find.equals('Z')){
                println("redondant");
            }else {
                currentButton.setText("X");
                infoBox.setText("Tir manqué!");
            }
            gridSolution[position] = 'Z';
        }
        shotsRemaining--;
    }



    public static void println(Object o){
        System.out.println(o);
    }
}
