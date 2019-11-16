package Main;


import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

//interface d'authentification

public class Authentification extends JFrame implements ActionListener,FocusListener,MouseListener{

	//declarer les composants important de l'interface
	private JPanel pcenter, psouth;
	private JButton bsignin;
	private JTextField tuser;
	private JPasswordField tpass_word;
	private JLabel limg1, lmsg;
	java.sql.Statement st = null;
	ResultSet r = null;


	//Constructeur
	public Authentification() {

		//titre de la fenetre
		setTitle("Authentification");
		//taille de la fenetre 
		setSize(605, 480);
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

		//ceration du panneau centre 
		pcenter = new JPanel();
		pcenter.setBackground(Color.yellow);

		//ceration du panneau sud
		psouth = new JPanel();
		psouth.setBackground(Color.yellow);

		//ajouet le panneau centre au centre
		add(pcenter,"Center");

		//ajouet le panneau sud en bas
		add(psouth,"South");



		//ajouter une image de fond au panneau centre
		limg1 = new JLabel("");
		limg1.setIcon(new ImageIcon("Images/imageDeFond1.png"));
		limg1.setBounds(0, 5, 498, 348);
		pcenter.add(limg1);

		//ajouter un messager au panneau sud
		lmsg = new JLabel("Entrez votre login et votre mot de passe");

		psouth.add(lmsg);

		//ajouter deux JtextField; un pour le login et l'autre pour le passWord au panneau sud
		tuser = new JTextField(10);
		tuser.addMouseListener(this);
		psouth.add(tuser);

		tpass_word = new JPasswordField(10);
		tpass_word.addMouseListener(this);
		psouth.add(tpass_word);


		//ajouter un bouton de connexion au panneau sud
		bsignin = new JButton("Sign in");
		psouth.add(bsignin);
		bsignin.addActionListener(this);



	}


	
	public void mouseClicked(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}
	//quand le curseur entre dans le jtextfield la couleur du jtextfield deviendra grise
	public void mouseEntered(MouseEvent e) {
		if(e.getSource()==tuser) {
			tuser.setBackground(Color.lightGray);
			
		}else {tpass_word.setBackground(Color.lightGray);
		        }



	}
	//quand le curseur sort dans le jtextfield la couleur du jtextfield deviendra blanche
	public void mouseExited(MouseEvent e) {
		if (e.getSource()== tpass_word) {
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
		//recuperer le contenu djtextfield
		String login = tuser.getText().toString();

		//recuperer le contenu du JPasswordField
		@SuppressWarnings("deprecation")
		String motdepasse = tpass_word.getText().toString();

		//creer un requête SQL 
		try {
			//creer la connexion avec la base de données
			st = Connexion.connexionBD().createStatement();
			//requête SQL
			String sql= "SELECT `user_name`, `pass_word` FROM `utilisateurs`";
			//executer la requête
			r = st.executeQuery(sql);
            //si le bouton "sign in" est pressé
			
			
			if (e.getSource()==bsignin) {
                //et si les champs sont vides 
				if(login.equals("") || motdepasse.equals("")){
                    //alors afficher un message d'erreur
					lmsg.setText("Veuillez remplir les champs ci-dessus");
                 //sinon
				} else{
					while(r.next()){
                        //recuperer des données de la base de données
						String login1=r.getString("user_name");
						String motdepasse1=r.getString("pass_word");
						//si la valeur des les champs est egale à la valeur des la base de données
						if(login1.equals(login) && motdepasse1.equals(motdepasse)){
							//alors la connexion est acceptée et il ouvre la page d'accueil 
							lmsg.setText("");
							//ouvrir la page d'accueil
							PagedAccueil p = new PagedAccueil();
							p.setVisible(true);
							//fermer la page actuelle
							dispose();


						}else {
							lmsg.setText("Erreur");
						}
					}}

			}	

		} catch (SQLException e1) {
			e1.printStackTrace();
		}



	}



}


