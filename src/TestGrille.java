
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TestGrille implements ActionListener{
   
   JFrame fenetre = new JFrame();
   JPanel panneauGrille = new JPanel();

   public TestGrille () {
      init();
   }
   
   private void init () {
      //initialisation de la fenetre
      fenetre.setBounds(500, 300, 500, 500);
      fenetre.setLayout(null);
      fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      //initialisation de la grille 8 X 8 avec hgap et vgap à -1
      panneauGrille = new JPanel(new GridLayout(8, 8, -1, -1));
      panneauGrille.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
      panneauGrille.setBounds(50, 50, 400, 400);
      
      //Ajout des boutons dans chaque case de la grille
      for (int i = 0; i < (8 * 8); i++){
         //Creer un bouton (sans texte)
         JButton btn = new JButton();
         
         //ajouter un ecouteur a chaque bouton de la grille
         btn.addActionListener(this);  
         
         //mettre une bordure noire a chaque bouton de la grille
         btn.setBorder(BorderFactory.createLineBorder(Color.BLACK));
         
         //ajouter le bouton a la grille
         panneauGrille.add(btn);
     }
      
      fenetre.getContentPane().add(panneauGrille);
      fenetre.setVisible(true);
   }
   
   //EXEMPLE D'EVENEMENT AU CLIC D'UNE CASE (D'UN BOUTON) DE LA GRILLE
   public void actionPerformed (ActionEvent e) {
      //lorsqu'on clique sur une case de la grille, une fenetre surgissante
      //affiche "Coucou !".
      JOptionPane.showMessageDialog(fenetre, "Coucou !", "Exemple", 
                                    JOptionPane.PLAIN_MESSAGE);
   }

   public static void main (String [] args) {
      new TestGrille();
   }

}
