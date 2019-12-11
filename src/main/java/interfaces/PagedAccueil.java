package interfaces;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;

import environnement.Outils;
import net.proteanit.sql.DbUtils;

@SuppressWarnings("serial")
public class PagedAccueil extends JFrame implements ActionListener {

	private JPanel pcenter, pnorth;
	private JButton brecherche, bajouter, bdeconn, baffichliste;
	private JTable table;
	private JScrollPane scrollp;
	private JMenuBar menu;
	private JMenu file, help;
	private JMenuItem deconnexion, quitter, aprpos;
	private ResultSet result = null;

	// constructeur
	public PagedAccueil() {

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

		// ajouet le panneau centre au centre de la fentre
		add(pcenter, "Center");

		// creation de la table
		table = new JTable();
		scrollp = new JScrollPane(table);
		pcenter.add(scrollp);

		// rendre la table non editable
		table.setEnabled(false);

		// creation du panneau nord
		pnorth = new JPanel();
		pnorth.setBackground(new Color(191, 136, 95));
		add(pnorth, "North");

		// création des boutons
		brecherche = new JButton("Rechercher recette");
		pnorth.add(brecherche);
		brecherche.addActionListener(this);

		baffichliste = new JButton("Toutes les recettes");
		pnorth.add(baffichliste);
		baffichliste.addActionListener(this);

		bajouter = new JButton("Ajouter recette");
		pnorth.add(bajouter);
		bajouter.addActionListener(this);

		bdeconn = new JButton("Deconnexion");
		pnorth.add(bdeconn);
		bdeconn.addActionListener(this);

	}

	public void actionPerformed(ActionEvent e) {
		// si on appuie sur le bouton "deconnexion"
		if (e.getSource() == bdeconn || e.getSource() == deconnexion) {
			// on revient à la page d'authentification
			Authentification a = new Authentification();
			a.setVisible(true);
			dispose();

			// si on appuie sur le bouton "Ajouter recette"
		} else if (e.getSource() == bajouter) {
			// alors on ouvre la fenetre d'ajout
			AjouterRecette a = new AjouterRecette();
			a.setVisible(true);
			dispose();

			// si on appuie sur le bouton Recherche
		} else if (e.getSource() == brecherche) {
			RechercherRecette r = new RechercherRecette();
			r.setVisible(true);
			dispose();

			// si on appuie sur le bouton Toutes les recettes
		} else if (e.getSource() == baffichliste) {

			result = Outils.afficherListeRecette();

			table.setModel(DbUtils.resultSetToTableModel(result));

			// si on appuie sur le l'item du menu "Quitter"
		}
		if (e.getSource() == quitter) {
			// une petite fenetre de confirmation apparait
			int input = JOptionPane.showConfirmDialog(this, "Voulez vous vraiment quitter ?", "Confirmation",
					JOptionPane.YES_NO_OPTION);
			if (input == 0) {// si l'utilisateur appuie sur "yes" input reçoit 0
				System.exit(0);
			}

		}
		if (e.getSource() == aprpos) {
			// ouvrir la fenetre Apropos
			Apropos a = new Apropos();
			a.setVisible(true);

		}

	}

}
