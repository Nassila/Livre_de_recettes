package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

@SuppressWarnings("serial")
public class RechercherRecette extends JFrame implements ActionListener {

	private JPanel pcenter, pnorth;
	private JLabel limg1, lmsg, lmsg1, lnom, lingredients, lprepa;
	private JButton brecherche, bdeconn, bsup, bmodif, bretour, bconsulter;
	private JTextField tnom;
	private JTextArea tingredients, tpreparation;
	private JScrollPane scrollp, scrollp2;
	private JMenuBar menu;
	private JMenu file, help;
	private JMenuItem deconnexion, quitter, aprpos, partager;
	private Statement state = null;
	private ResultSet result = null;
	private PreparedStatement prepare = null;
	private String id = null;

	// constructeur
	public RechercherRecette() {

		// titre de la fenetre
		setTitle("Page d'accueil");
		// taille de la fenetre
		setSize(605, 490);
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

		// creation de la barre de menu
		menu = new JMenuBar();
		setJMenuBar(menu);

		file = new JMenu("File");
		menu.add(file);

		// création de l'item "partager"
		partager = new JMenuItem("Partager");
		file.add(partager);
		// raccourci clavier; quand on appuie sur CTRL+P on partage la rectte par mail
		partager.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
		partager.addActionListener(this);
		partager.setVisible(false);

		deconnexion = new JMenuItem("Deconnexion");
		file.add(deconnexion);
		// raccourci clavier; quand on appuie sur CTRL+D on se deconnecte
		deconnexion.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
		deconnexion.addActionListener(this);

		// création de l'item "quitter"
		quitter = new JMenuItem("Quitter");
		file.add(quitter);
		// raccourci clavier; quand on appuie sur ALT+Q on quitte l'application
		quitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.ALT_MASK));
		quitter.addActionListener(this);

		help = new JMenu("Help");
		menu.add(help);

		// creation de l'item apropos
		aprpos = new JMenuItem("A propos de l'application");
		help.add(aprpos);
		aprpos.addActionListener(this);

		// ceration du panneau centre
		pcenter = new JPanel()

		{
			// ajouter une image de fond
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);

				ImageIcon m = new ImageIcon("Images/imageDeFond2.jpg");
				Image monImage = m.getImage();
				g.drawImage(monImage, 0, 0, this);

			}
		};
		// ajouter une couleur au panneau
		pcenter.setBackground(Color.yellow);

		pcenter.setLayout(new BorderLayout());

		// ajouet le panneau centre au centre de la fentre
		add(pcenter, "Center");

		pcenter.setLayout(new GridLayout(3, 3));

		lnom = new JLabel("Nom de la recette :");
		lnom.setFont(new Font("Tahoma", Font.BOLD, 16));
		pcenter.add(lnom);

		lingredients = new JLabel("Ignredients :");
		pcenter.add(lingredients);
		lingredients.setFont(new Font("Tahoma", Font.BOLD, 16));
		lingredients.setVisible(false);

		lprepa = new JLabel("Preparation :");
		pcenter.add(lprepa);
		lprepa.setFont(new Font("Tahoma", Font.BOLD, 16));
		lprepa.setVisible(false);

		tnom = new JTextField(8);
		tnom.setVisible(true);
		pcenter.add(tnom);

		// tingredients = new JTextArea(15,15);
		tingredients = new JTextArea(8, 8);
		scrollp = new JScrollPane(tingredients);
		pcenter.add(scrollp);
		scrollp.setVisible(false);
		tingredients.setVisible(false);

		// tpreparation = new JTextArea(15,15);
		tpreparation = new JTextArea(8, 8);
		scrollp2 = new JScrollPane(tpreparation);
		pcenter.add(scrollp2);
		scrollp2.setVisible(false);
		tpreparation.setVisible(false);

		// creation du panneau nord
		pnorth = new JPanel();
		pnorth.setBackground(new Color(191, 136, 95));
		add(pnorth, "North");

		// création des boutons
		brecherche = new JButton("Recherche");
		pnorth.add(brecherche);
		brecherche.addActionListener(this);

		bconsulter = new JButton("Consulter");
		pnorth.add(bconsulter);
		bconsulter.addActionListener(this);
		bconsulter.setVisible(false);

		bsup = new JButton("Supprimer");
		pnorth.add(bsup);
		bsup.addActionListener(this);
		bsup.setVisible(false);

		bmodif = new JButton("Modifier");
		pnorth.add(bmodif);
		bmodif.addActionListener(this);
		bmodif.setVisible(false);

		bretour = new JButton("Retour");
		pnorth.add(bretour);
		bretour.addActionListener(this);

		bdeconn = new JButton("Deconnexion");
		pnorth.add(bdeconn);
		bdeconn.addActionListener(this);

		lmsg1 = new JLabel("");
		pcenter.add(lmsg1);

		// pour l'affichage d'un message d'erreur
		lmsg = new JLabel("");
		lmsg.setFont(new Font("Tahoma", Font.BOLD, 12));
		pcenter.add(lmsg);

	}

	public void actionPerformed(ActionEvent e) {

		// si on appuie sur le bouton deconnexion
		if (e.getSource() == bdeconn || e.getSource() == deconnexion) {
			// on revient à la page d'authentification
			Authentification a = new Authentification();
			a.setVisible(true);
			dispose();

			// si on appuie sur le bouton deconnexion
		} else if (e.getSource() == brecherche) {
			String name = tnom.getText();

			try {

				// requête SQL
				String sql = "SELECT `id`, `nom`, `ingredients`, `preparation` FROM `recettes` WHERE `nom` = ? ;";

				// creer la connexion avec la base de données
				prepare = Connexion.connexionBD().prepareStatement(sql);
				prepare.setString(1, name);

				// executer la requête
				result = prepare.executeQuery();

				if (result.next()) {
					scrollp.setVisible(true);
					tingredients.setVisible(true);
					scrollp2.setVisible(true);
					tpreparation.setVisible(true);
					bmodif.setVisible(true);
					bsup.setVisible(true);
					bconsulter.setVisible(true);
					partager.setVisible(true);
					lprepa.setVisible(true);
					lingredients.setVisible(true);

					id = result.getString("id");
					lmsg.setText("");

					String nom = result.getString("nom");
					tnom.setText(nom);

					String ingredients = result.getString("ingredients");
					tingredients.setText(ingredients);

					String preparation = result.getString("preparation");
					tpreparation.setText(preparation);

				} else {
					lmsg.setText("Cette recette n'existe pas");
					tnom.setText("");
					tingredients.setText("");
					tpreparation.setText("");
					scrollp.setVisible(false);
					scrollp2.setVisible(false);
					tingredients.setVisible(false);
					tpreparation.setVisible(false);
					bmodif.setVisible(false);
					bsup.setVisible(false);
					bconsulter.setVisible(false);
					partager.setVisible(false);
					lprepa.setVisible(false);
					lingredients.setVisible(false);

				}

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		} else if (e.getSource() == bsup) {
			try {
				// creer la connexion avec la base de données
				state = Connexion.connexionBD().createStatement();
				String name = tnom.getText();
				// requête SQL
				String sql = "DELETE FROM `recettes` WHERE `nom` = ?;";

				// executer la requête
				prepare = Connexion.connexionBD().prepareStatement(sql);
				prepare.setString(1, name);
				prepare.executeUpdate();

				lmsg.setText("Cette recette a bien été supprimée");
				tnom.setText("");
				tingredients.setText("");
				tpreparation.setText("");

				scrollp.setVisible(false);
				scrollp2.setVisible(false);
				tingredients.setVisible(false);
				tpreparation.setVisible(false);
				bmodif.setVisible(false);
				bsup.setVisible(false);
				bconsulter.setVisible(false);
				partager.setVisible(false);
				lprepa.setVisible(false);
				lingredients.setVisible(false);

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		} else if (e.getSource() == bretour) {
			PagedAccueil p = new PagedAccueil();
			p.setVisible(true);
			dispose();
		} else if (e.getSource() == bmodif) {
			try {

				// creer la connexion avec la base de données
				// state = Connexion.connexionBD().createStatement();
				String name = tnom.getText();

				// requête SQL
				String sql = "UPDATE `recettes` SET `nom`= ?,`ingredients`= ?,`preparation`= ? WHERE `id`= ?;";

				// executer la requête
				prepare = Connexion.connexionBD().prepareStatement(sql);
				prepare.setString(1, tnom.getText());
				prepare.setString(2, tingredients.getText());
				prepare.setString(3, tpreparation.getText());
				prepare.setString(4, id);
				prepare.executeUpdate();

				lmsg.setText("Cette recette a bien été modifiée");
				tnom.setText("");
				tingredients.setText("");
				tpreparation.setText("");

				tingredients.setVisible(false);
				scrollp.setVisible(false);
				scrollp2.setVisible(false);
				tpreparation.setVisible(false);
				bmodif.setVisible(false);
				bsup.setVisible(false);
				bconsulter.setVisible(false);
				partager.setVisible(false);
				lprepa.setVisible(false);
				lingredients.setVisible(false);

			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == quitter) {
			// une petite fenetre de confirmation apparait
			int input = JOptionPane.showConfirmDialog(this, "Voulez vous vraiment quitter ?", "Confirmation",
					JOptionPane.YES_NO_OPTION);
			if (input == 0) {// si l'utilisateur appuie sur "yes" input reçoit 0
				System.exit(0);
			}

		} else if (e.getSource() == aprpos) {
			// ouvrir la fenetre Apropos
			Apropos a = new Apropos();
			a.setVisible(true);

		} else if (e.getSource() == partager) {
			String objet = tnom.getText();
			String text = "Ingrediants : \n " + tingredients.getText() + "\n" + "Preparation : \n"
					+ tpreparation.getText();
			Mail mail = new Mail(objet, text);
			mail.setVisible(true);

		} else if (e.getSource() == bconsulter) {
			String objet = tnom.getText();
			String text = "Ingrediants : \n " + tingredients.getText() + "\n" + "Preparation : \n"
					+ tpreparation.getText();
			ConsulterRecette consulter = new ConsulterRecette(objet, text);
			consulter.setVisible(true);

		}
	}

}
