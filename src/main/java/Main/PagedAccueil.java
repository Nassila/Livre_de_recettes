package Main;



import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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




@SuppressWarnings("serial")
public class PagedAccueil extends JFrame implements ActionListener {

	private JPanel pcenter, pnorth;
	private JButton brecherche,bajouter,bdeconn,baffichliste;
	private JTable table;
	private JScrollPane scrollp;
	private JMenuBar menu;
	private JMenu file,help;
	private JMenuItem deconnexion,quitter,aprpos;
	private Statement state = null;
	private ResultSet result = null;
	private PreparedStatement prepare = null;
	//Object[][] data = null;
	String  title[] = {"Numero", "Nom", "Ingredients", "Preparation"};


	//constructeur
	public PagedAccueil() {

		//titre de la fenetre
		setTitle("Page d'accueil");
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

		//ajouet le panneau centre au centre de la fentre
		add(pcenter,"Center");

		//creation du panneau nord
		pnorth = new JPanel();
		pnorth.setBackground(new Color(191, 136, 95));
		add(pnorth,"North");

		//table = new JTable(data, title);


		//création des boutons
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



	public static void main(String[] args) {
		PagedAccueil a = new PagedAccueil();
		//rendre visible la fenetre
		a.setVisible(true);
	}



	public void actionPerformed(ActionEvent e) {
		//si on appuie sur le bouton "deconnexion"
		if (e.getSource()==bdeconn || e.getSource() == deconnexion ) {
			//on revient à la page d'authentification
			Authentification a = new Authentification();
			a.setVisible(true);
			dispose();

			//si on appuie sur le bouton "Ajouter recette"
		}else if (e.getSource()==bajouter) {
			//alors on ouvre la fenetre d'ajout
			AjouterRecette a = new AjouterRecette();
			a.setVisible(true);
			dispose();

			//si on appuie sur le bouton Recherche
		} else if (e.getSource()==brecherche) {
			RechercherRecette r = new RechercherRecette();
			r.setVisible(true);
			dispose();


			//si on appuie sur le bouton Toutes les recettes
		}else if (e.getSource() == baffichliste) {


			try {


				//creer la connexion avec la base de données
				state = Connexion.connexionBD().createStatement();

				//requête SQL
				String sql= "SELECT * FROM `recettes`;";

				//executer la requête
				result = state.executeQuery(sql);


				ResultSetMetaData resultMeta = result.getMetaData();

				String[] columnNames = new String[resultMeta.getColumnCount()];

				for (int i = 1; i <= resultMeta.getColumnCount(); i++){   
					columnNames[i-1]=resultMeta.getColumnName(i);
				} 

				List<Object[]> data = new ArrayList<Object[]>();

				while(result.next()) {
					Object[] line = new Object[resultMeta.getColumnCount()]; // on créé un tableau pour stocker la ligne courante
					for (int i = 1; i <= resultMeta.getColumnCount(); i++) {
						line[i-1]=result.getObject(i);          
					}
					data.add(line); // on ajoute la ligne à la liste

				}
				//table = new JTable( data.stream().toArray(Object[][]::new), columnNames );
				scrollp =new JScrollPane(table);
				pcenter.add(new JScrollPane(table));

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			//si on appuie sur le l'item du menu "Quitter"
		}
		if (e.getSource()==quitter) {
			//une petite fenetre de confirmation apparait
			int input = JOptionPane.showConfirmDialog(this, "Voulez vous vraiment quitter ?","Confirmation",JOptionPane.YES_NO_OPTION);
			if (input == 0) {//si l'utilisateur appuie sur "yes" input reçoit 0
				System.exit(0);
			}

		}
		if (e.getSource()==aprpos) {
			//ouvrir la fenetre Apropos	
			Apropos a=new Apropos();
			a.setVisible(true);

		}

	}

}
