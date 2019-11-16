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
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class AjouterRecette extends JFrame implements ActionListener{
	private JPanel pcenter, pnorth;
	private JLabel lnom,lingredients,lprepa, lmsg1;
	private JButton bdeconn, benregistrer, bretour;
	private JTextField tnom, tingredients, tpreparation;
	//private JTextArea tingredients, tpreparation;
	private JMenuBar menu;
	private JMenu file,help;
	private JMenuItem deconnexion,quitter,aprpos;
	java.sql.Statement state = null;
	ResultSet result = null;

	public AjouterRecette() {

		//titre de la fenetre
		setTitle("Ajout");
		//taille de la fenetre 
		setSize(605, 490);
		//arreter l'execution à la fermeture de la fenetre
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//center l'afichage de la fenetre 
		setLocationRelativeTo(this);

		//empecher de redimensionner la fenetre
		setResizable(false);

		//modification de l'icone de la fenetre
		Toolkit kit=Toolkit.getDefaultToolkit();
		Image img=kit.getImage("Images/imageDeFond1.png");
		setIconImage(img);

		//creation de la barre de menu 
		menu =new JMenuBar();
		setJMenuBar(menu);

		file=new JMenu("File");
		menu.add(file);

		deconnexion=new JMenuItem("Deconnexion");
		file.add(deconnexion);
		//raccourci clavier; quand on appuie sur CTRL+D on se deconnecte
		deconnexion.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,InputEvent.CTRL_MASK));
		deconnexion.addActionListener(this);

		//création de l'item "quitter"
		quitter=new JMenuItem("Quitter");
		file.add(quitter);
		//raccourci clavier; quand on appuie sur ALT+Q on quitte l'application
		quitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,InputEvent.ALT_MASK));
		quitter.addActionListener(this);

		help=new JMenu("Help");
		menu.add(help);

		//creation de l'item apropos
		aprpos=new JMenuItem("A propos de l'application");
		help.add(aprpos);
		aprpos.addActionListener(this);


		//ceration du panneau centre 
		pcenter = new JPanel()

		{
			//ajouter une image de fond
			protected void paintComponent(Graphics g) 
			{
				super.paintComponent(g);

				ImageIcon m = new ImageIcon("Images/imageDeFond2.jpg");
				Image monImage = m.getImage();
				g.drawImage(monImage, 0, 0,this);

			}
		};
		//ajouter une couleur au panneau
		pcenter.setBackground(Color.yellow);

		pcenter.setLayout(new BorderLayout());

		//ajouet le panneau centre au centre
		add(pcenter,"Center");

		pcenter.setLayout(new GridLayout(3, 3));

		lnom = new JLabel("Nom de la recette :");
		lnom.setFont(new Font("Tahoma", Font.BOLD, 16));
		pcenter.add(lnom);
		
		lingredients = new JLabel("Ignredients :");
		pcenter.add(lingredients);
		lingredients.setFont(new Font("Tahoma", Font.BOLD, 16));
	
		lprepa = new JLabel("Preparation :");
		pcenter.add(lprepa);
		lprepa.setFont(new Font("Tahoma", Font.BOLD, 16));
	
		tnom = new JTextField(15);
		pcenter.add(tnom);

		//tingredients = new JTextArea(15,15);
		tingredients =  new JTextField(15);
		pcenter.add(tingredients);

		//tpreparation = new JTextArea(15,15);
		tpreparation  =  new JTextField(15);
		pcenter.add(tpreparation);
		
		lmsg1 = new JLabel("");
		pcenter.add(lmsg1);

		//creation du panneau nord
		pnorth = new JPanel();
		pnorth.setBackground(new Color(191, 136, 95));
		add(pnorth,"North");

		//création des boutons
		benregistrer = new JButton("Sauvegarder");
		pnorth.add(benregistrer);
		benregistrer.addActionListener(this);

		bretour = new JButton("Retour");
		pnorth.add(bretour);
		bretour.addActionListener(this);

		bdeconn = new JButton("Deconnexion");
		pnorth.add(bdeconn);
		bdeconn.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {

		//si on appuie suir le bouton "sauvegarder"
		if(e.getSource()==benregistrer) {


			//recuperer les text dans les Jtext
			String name = tnom.getText();
			String ing = tingredients.getText();
			String prepa = tpreparation.getText();

			try {
				//creer la connexion avec la base de données
				state = Connexion.connexionBD().createStatement();

				//requête SQL
				String sql= "INSERT INTO `recettes` (`nom`, `ingredients`, `preparation`) VALUES ('"+name+"', '"+ing+"', '"+prepa+"');";
				//executer la requête
				state.executeUpdate(sql);


			} catch (SQLException e1) {
				e1.printStackTrace();
			}


			PagedAccueil p = new PagedAccueil();
			p.setVisible(true);
			dispose();

			//si on appuie sur le bouton "deconnexion"
		}else if (e.getSource()==bdeconn || e.getSource() == deconnexion) {

			Authentification a = new Authentification();
			a.setVisible(true);
			dispose();

		}else if (e.getSource() == bretour) {
			PagedAccueil p = new PagedAccueil();
			p.setVisible(true);
			dispose();
		}else if (e.getSource()==quitter) {
			//une petite fenetre de confirmation apparait
			int input = JOptionPane.showConfirmDialog(this, "Voulez vous vraiment quitter ?","Confirmation",JOptionPane.YES_NO_OPTION);
			if (input == 0) {//si l'utilisateur appuie sur "yes" input reçoit 0
				System.exit(0);
			}

		}else
			if (e.getSource()==aprpos) {
				//ouvrir la fenetre Apropos	
				Apropos a=new Apropos();
				a.setVisible(true);

			}
	}
}
