package interfaces;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import environnement.Outils;

//interface d'authentification

public class Authentification extends JFrame implements ActionListener, FocusListener, MouseListener, KeyListener {

	// declarer les composants important de l'interface
	private JPanel pcenter, psouth;
	private JButton bsignin;
	private JTextField tuser;
	private JPasswordField tpass_word;
	private JLabel limg1, lmsg;

	// Constructeur
	public Authentification() {

		// titre de la fenetre
		setTitle("Authentification");
		// taille de la fenetre
		setSize(605, 480);
		// arreter l'execution à la fermeture de la fenetre
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// center l'afichage de la fenetre
		setLocationRelativeTo(this);

		// empecher de redimensionner la fenetre
		setResizable(false);

		// modification de l'icone de la fenetre
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.getImage("Images/imageDeFond1.png");
		setIconImage(img);

		// ceration du panneau centre
		pcenter = new JPanel();
		pcenter.setBackground(Color.yellow);

		// ceration du panneau sud
		psouth = new JPanel();
		psouth.setBackground(Color.yellow);

		// ajouet le panneau centre au centre
		add(pcenter, "Center");

		// ajouet le panneau sud en bas
		add(psouth, "South");

		// ajouter une image de fond au panneau centre
		limg1 = new JLabel("");
		limg1.setIcon(new ImageIcon("Images/imageDeFond1.png"));
		limg1.setBounds(0, 5, 498, 348);
		pcenter.add(limg1);

		// ajouter un messager au panneau sud
		lmsg = new JLabel("Entrez votre login et votre mot de passe");

		psouth.add(lmsg);

		// ajouter deux JtextField; un pour le login et l'autre pour le passWord au
		// panneau sud
		tuser = new JTextField(10);
		tuser.addMouseListener(this);
		psouth.add(tuser);

		tpass_word = new JPasswordField(10);
		tpass_word.addMouseListener(this);
		psouth.add(tpass_word);

		// ajouter un bouton de connexion au panneau sud
		bsignin = new JButton("Sign in");
		psouth.add(bsignin);
		bsignin.addActionListener(this);
		bsignin.addKeyListener(this);

	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}

	// quand le curseur entre dans le jtextfield la couleur du jtextfield deviendra
	// grise
	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == tuser) {
			tuser.setBackground(Color.lightGray);

		} else {
			tpass_word.setBackground(Color.lightGray);
		}

	}

	// quand le curseur sort dans le jtextfield la couleur du jtextfield deviendra
	// blanche
	public void mouseExited(MouseEvent e) {
		if (e.getSource() == tpass_word) {
			tuser.setBackground(Color.WHITE);
		} else {
			tpass_word.setBackground(Color.WHITE);
		}

	}

	public void focusGained(FocusEvent e) {

	}

	public void focusLost(FocusEvent e) {

	}

	public void actionPerformed(ActionEvent e) {
		// recuperer le contenu djtextfield
		String login = tuser.getText().toString();

		// recuperer le contenu du JPasswordField
		@SuppressWarnings("deprecation")
		String motdepasse = tpass_word.getText().toString();

		if (e.getSource() == bsignin) {
			String reponse = Outils.authetification(login, motdepasse);

			if (reponse.equals("ok")) {

				// ouvrir la page d'accueil
				PagedAccueil p = new PagedAccueil();
				p.setVisible(true);
				// fermer la page actuelle
				dispose();

			} else {
				lmsg.setText(reponse);
			}
		}

	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void keyPressed(KeyEvent e) {

		// recuperer le contenu djtextfield
		String login = tuser.getText().toString();

		// recuperer le contenu du JPasswordField
		@SuppressWarnings("deprecation")
		String motdepasse = tpass_word.getText().toString();

		// si on clique sur entré
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			String reponse = Outils.authetification(login, motdepasse);

			if (reponse.equals("ok")) {

				// ouvrir la page d'accueil
				PagedAccueil p = new PagedAccueil();
				p.setVisible(true);
				// fermer la page actuelle
				dispose();

			} else {
				lmsg.setText(reponse);
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
