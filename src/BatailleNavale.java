import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Cette classe, est un jeu de bataille navale avec une interface swing
 * Code permanent: BOUD31109107
 * date: 22 juillet 2017
 * version: 1.0.0
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
    private ArrayList<String> arraySolution = new ArrayList<String>();
    private ArrayList<String> coordHorizontalBoats = new ArrayList<String>();
    private ArrayList<String> coordVerticalBoats = new ArrayList<String>();
    private Integer shotsRemaining = 0;
    private Integer shotsToCompleteGame = 0;
    private Boolean gameStarted = false;

    //panel
    private JPanel topPanel,
            middlePanel,
            bottomPanel,
            gamePanel,
            grid = new JPanel(null),
            statusPanel = new JPanel(null);

    //button
    private JButton playBtn;
    private JButton[][] buttonGrid = new JButton[ROWS][COLS];
    private JButton quitBtn;

    //radio button
    private JRadioButton btnRadio1 = new JRadioButton("Débutant"),
                         btnRadio2 = new JRadioButton("Intermédiaire"),
                         btnRadio3 = new JRadioButton("Expert");

    //button group
    private ButtonGroup groupBtnRadio;

    //label
    private JLabel infoBox;
    private JLabel statusLabel;

    //textfield
    private JTextField playerName;

    //event listener
    private ActionListener eventListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evenement) {
            eventDispatcher(evenement);
        }
    };
    //constructor
    public BatailleNavale(){

    }

    /**
     * Methode init game qui initialise le frame du jeu.
     * */
    public void initGame(){
        frame = new JFrame("BATTLESHIP!");
        frame.setSize(WIDTH_FRAME, HEIGHT_FRAME);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.gray);
        launchMenu();
    }

    //launch first page

    /**
     * methode qui initialise le menu principale, page d'accueil
     * prend aucun parametre
     */
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

    /**
     * methode qui initialise le premier panneau de 4 celui en position 0,0
     * elle y ajoute la difficulté et le bouton pour initialiser la partie
     * elle ajoute aussi un espace pour les messages d'information
     *
     */
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

    /**
     *
     * ajoute au panneau central le logo ascii
     */
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

    /**
     * ajoute au panneau du bas un champ textfield pour inscrire le nom du joueur.
     */
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

    /**
     * methode qui retourne la difficiulté choisi par le joueur
     * 1 = debutant
     * 2 = intermediaire
     * 3 = expert
     * @return Integer
     */
    private Integer getSelectedDifficulty(){
        return btnRadio1.isSelected()?1:btnRadio2.isSelected()?2:btnRadio3.isSelected()?3:0;
    }

    //return player name

    /**
     * methode qui retourne le nom du joueur
     * @return String nomDuJoueur
     */
    private String getPlayerName(){
        return playerName.getText().toUpperCase();
    }

    /**
     * la methode cache le panneau du milieu et celui du bas pour laisse la place a la grille de jeu
     * methode qui indique au joueur d'effectuer son premier tir
     * intitialise le nombre de coup possible pour le joueur selon la difficulté choisi
     * genere la grille de solution
     * appel la methode setupGame()
     */
    private void launchGame(){
        gamePanel = new JPanel(null);
        infoBox.setText(getPlayerName()+", éxécutez votre premier tir.");
        infoBox.setVisible(true);
        middlePanel.setVisible(false);
        bottomPanel.setVisible(false);
        shotsToCompleteGame = 0;
        shotsRemaining = getSelectedDifficulty()==1?45:getSelectedDifficulty()==2?35:25;
        gridSolution = JeuUtils.genererGrilleSolution().toCharArray();
        setupGame();
        getCoordsBoats();
        setupBoatsName();

        gameStarted = true;
    }


    /**
     * methode qui met en place la grille et ajoute les buttons a celle-ci avec les evenement liés
     * ajoute le nombre de coup restant au joueur
     * ajoute les bateaux a couler
     * ajoute le bouton quitter dans le bas de la fenetre
     * initialise la variable shotsToCompleteGame pour savoir combien de coup il reste avant d'avoir gagné la partie
     */
    private void setupGame(){
        // reset if game already start
        if (gameStarted){
            resetGrid();
        }
        gamePanel.setBackground(Color.lightGray);
        gamePanel.setBounds(0, HEIGHT_FRAME/4, WIDTH_FRAME, HEIGHT_FRAME-(HEIGHT_FRAME/4));
        grid.setLayout(new GridLayout(ROWS, COLS, -1, -1));
        grid.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));

        grid.setBounds(20, 20, 300, 300);
        grid.setBorder(BorderFactory.createLineBorder(Color.black));

        //panel beside the grid
        statusPanel.setBounds(grid.getX()+grid.getWidth()+30, grid.getY(), 230, 300);
        statusPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        //label for remaining ammo
        statusLabel = new JLabel("", SwingConstants.CENTER);
        statusLabel.setOpaque(true);
        statusLabel.setBounds(0, 0, 230, 40);
        statusLabel.setForeground(Color.black);
        statusLabel.setVisible(true);
        statusLabel.setText("Réserve de munitions: \n" + shotsRemaining);
        statusPanel.add(statusLabel);

        //boats status
        JLabel b1 = new JLabel("CUIRASSE", SwingConstants.CENTER);
        JLabel b2 = new JLabel("CROISSEUR", SwingConstants.CENTER);
        JLabel b3 = new JLabel("SOUS-MARIN", SwingConstants.CENTER);
        JLabel b4 = new JLabel("DESTROYER", SwingConstants.CENTER);

        b1.setBounds(0, 90, 230, 40);
        b2.setBounds(0, b1.getY()+50, b1.getWidth(), b1.getHeight());
        b3.setBounds(0, b2.getY()+50, b1.getWidth(), b1.getHeight());
        b4.setBounds(0, b3.getY()+50, b1.getWidth(), b1.getHeight());

        b1.setForeground(Color.lightGray);
        b2.setForeground(Color.lightGray);
        b3.setForeground(Color.lightGray);
        b4.setForeground(Color.lightGray);

        statusPanel.add(b1);
        statusPanel.add(b2);
        statusPanel.add(b3);
        statusPanel.add(b4);

        //quit button
        quitBtn = new JButton("Quitter");
        quitBtn.setBounds((gamePanel.getWidth()/2)-(60/2), gamePanel.getHeight()-100, 100, 30);
        quitBtn.setForeground(Color.cyan);
        quitBtn.setVisible(true);
        quitBtn.addActionListener(eventListener);



        //add buttons to grid
        Integer position = 0;
        for (int row = 0; row < buttonGrid.length; row++) {
            for (int col = 0; col < buttonGrid[row].length; col++) {
                buttonGrid[row][col] = new JButton();
                buttonGrid[row][col].setVisible(true);
                buttonGrid[row][col].addActionListener(eventListener);
                buttonGrid[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                grid.add(buttonGrid[row][col]);

                if(gridSolution[position]=='B'){
                    arraySolution.add(row+"-"+col);
                }
                position++;
            }
        }

        gamePanel.add(grid);
        gamePanel.add(quitBtn);
        gamePanel.add(statusPanel);
        frame.add(gamePanel);

        //get total shot to complete the game
        for (int i = 0, x = 0; i < gridSolution.length; i++){
            if(gridSolution[i]=='B'){
                shotsToCompleteGame++;
            }
        }
    }

    //action event
    /**
     * cette methode gere les differents click sur l'interface
     * @param e actionEvent ayant créer l'action
     */
    private void eventDispatcher(ActionEvent e){
        if (e.getSource() == playBtn || e.getSource() == playerName){
            if((getPlayerName().length() >= 3 && getPlayerName().length() <= 10) && getSelectedDifficulty() != 0){
                launchGame();
            }else {
                JOptionPane.showMessageDialog(frame, "Nom obligatoire et doit contenir entre 3 et 10 caractères inclusivement.");
            }
        }else if(e.getSource() == quitBtn){
            quitGame();
        }else{
            int position = 0;
            for (int row = 0; row < buttonGrid.length; row++) {
                for (int col = 0; col < buttonGrid[row].length; col++) {
                    if (buttonGrid[row][col] == e.getSource()) {
                        checkHit(position, buttonGrid[row][col], row, col);
                    }
                    position++;
                }
            }
        }
    }

    /**
     * methode qui gere le clic sur un bouton de la grille
     * @param position position entre 1 et 64
     * @param currentButton le boutton qui a été cliquer
     * @param row la rangé du boutton actuellement cliquer
     * @param col la colone du boutton actuellement cliquer
     */
    private void checkHit(Integer position, JButton currentButton, Integer row, Integer col){
        if(position <= gridSolution.length){
            Character find = gridSolution[position];
            if(find.equals('B')){
                currentButton.setText("B");
                currentButton.setBackground(Color.cyan);
                currentButton.setOpaque(true);
                infoBox.setText("Tir réussi!");

                shotsToCompleteGame--;
            }else if (find.equals('Z')){
                infoBox.setText("Tir redondant!");
            }else {
                currentButton.setText("X");
                infoBox.setText("Tir manqué!");
            }
            gridSolution[position] = 'Z';
        }
        shotsRemaining--;
        statusLabel.setText("Réserve de munitions: "+ shotsRemaining);
        if(shotsRemaining < shotsToCompleteGame){
            JOptionPane.showMessageDialog(frame, "Il ne reste plus assez de coup pour gagné la partie:(");
            infoBox.setText(getPlayerName()+ " vous avez perdu!");
        }
        if(shotsToCompleteGame == 0){
            JOptionPane.showMessageDialog(frame, "Félicitation "+getPlayerName()+" vous avez gagné la partie");
            infoBox.setText(getPlayerName()+ " vous avez gagné!");
        }
    }

    /**
     * methode qui remet supprime les boutons de la grille
     * et supprime le panneau de status pour les coups restant
     */
    private void resetGrid(){
        statusPanel.remove(statusLabel);
        for (int row = 0; row < buttonGrid.length; row++) {
            for (int col = 0; col < buttonGrid[row].length; col++) {
                if(buttonGrid[row][col] != null){
                    buttonGrid[row][col].setVisible(false);
                    grid.remove(buttonGrid[row][col]);
                }
            }
        }
    }

    /**
     * methode qui donne les coordonné des bateaux horizontal et vertical
     * dans les variables coordHorizontalBoats et coordVerticalBoats
     */
    private void getCoordsBoats(){
        ArrayList<String> listToRemove = new ArrayList<String>();
        for (int i = 0; i < arraySolution.size(); i++) {
            String[] splitValue = arraySolution.get(i).split("-");
            String[] previousValue;
            String[] nextValue;

            if(i > 0){
                previousValue = arraySolution.get(i-1).split("-");
            }else{
                previousValue = splitValue;
            }
            if (i < arraySolution.size() - 1){
                nextValue = arraySolution.get(i+1).split("-");
            }else {
                nextValue = splitValue;
            }

            Integer previousRow = Integer.parseInt(previousValue[0]); //vertical
            Integer previousCol = Integer.parseInt(previousValue[1]); //horizontal
            Integer currentRow = Integer.parseInt(splitValue[0]);
            Integer currentCol = Integer.parseInt(splitValue[1]);
            Integer nextRow = Integer.parseInt(nextValue[0]);
            Integer nextCol = Integer.parseInt(nextValue[1]);

            if((currentRow.equals(nextRow) || currentRow.equals(previousRow)) &&
                    (currentCol.equals(nextCol-1) || currentCol.equals(previousCol+1))){
                listToRemove.add(arraySolution.get(i));
            }
        }
        coordHorizontalBoats = listToRemove;
        arraySolution.removeAll(listToRemove);
        coordVerticalBoats = arraySolution;
    }

    /**
     * methode incomplete qui avait pour but de recuperer les grandeurs pour chaque bateau
     */
    private void setupBoatsName(){
        for(int i = 0; i < coordHorizontalBoats.size(); i++){
            Integer positionSuivante = null;
            Integer position = Integer.parseInt(coordHorizontalBoats.get(i).split("-")[1]);

            if(i+2 <= coordHorizontalBoats.size()){
                positionSuivante = Integer.parseInt(coordHorizontalBoats.get(i+1).split("-")[1]);
            }else {
                positionSuivante = null;
            }
            if(positionSuivante != null){
                if(position.equals(positionSuivante-1)){
//                    println("ajouter dans array");
                }else {
//                    println("new array");
                }
            }else {
//                println("ajouter le dernier au meme array");
            }
        }
    }

    /**
     * methode qui quitte la partie actuelle et ramene vers la premiere page
     * pour commencer une nouvelle partie
     */
    private void quitGame(){
        resetGrid();
        gamePanel.setVisible(false);
        topPanel.setVisible(true);
        middlePanel.setVisible(true);
        bottomPanel.setVisible(true);
        playerName.setText("");
        playerName.requestFocusInWindow();
        gameStarted = false;
    }
}
