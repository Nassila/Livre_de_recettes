package Main;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Mail extends JFrame implements ActionListener{

	private JPanel pcenter, pnorth;
	private JLabel ladressemail,lmotdepass,ldestinataire;
	private JButton bvalider, bannuler;
	private JTextField tadressemail, tdestination;
	private JPasswordField tmotdepass;

	String objet =" ";
	String text =" ";

	public Mail(String objet, String text) {
		this.objet = objet;
		this.text = text;

		//titre de la fenetre
		setTitle("Mail");
		//taille de la fenetre 
		setSize(450, 150);

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

		//ajouter une couleur au panneau
		pcenter.setBackground(new Color(191, 136, 95));

		pcenter.setLayout(new BorderLayout());

		//ajouet le panneau centre au centre
		add(pcenter,"Center");

		pcenter.setLayout(new GridLayout(3, 2));

		ladressemail = new JLabel("Votre adresse mail :");
		ladressemail.setFont(new Font("Tahoma", Font.BOLD, 14));
		pcenter.add(ladressemail);

		tadressemail = new JTextField(15);
		pcenter.add(tadressemail);

		lmotdepass = new JLabel("Votre mot de passe :");
		pcenter.add(lmotdepass);
		lmotdepass.setFont(new Font("Tahoma", Font.BOLD, 14));

		tmotdepass =  new JPasswordField(15);
		pcenter.add(tmotdepass);

		ldestinataire = new JLabel("Adresse du destinataire :");
		pcenter.add(ldestinataire);
		ldestinataire.setFont(new Font("Tahoma", Font.BOLD, 14));

		tdestination  =  new JTextField(15);
		pcenter.add(tdestination);


		//creation du panneau nord
		pnorth = new JPanel();
		pnorth.setBackground(new Color(191, 136, 95));
		add(pnorth,"North");

		//création des boutons
		bvalider = new JButton("Valider");
		pnorth.add(bvalider);
		bvalider.addActionListener(this);

		bannuler = new JButton("Annuler");
		pnorth.add(bannuler);
		bannuler.addActionListener(this);


	}

	public void actionPerformed(ActionEvent e) {

		//si on appuie suir le bouton "sauvegarder"
		if(e.getSource()==bvalider) {
			String from = tadressemail.getText();
			String to = tdestination.getText();
			String password = tmotdepass.getText();
			try {
				SendEmail.sendMail(from, to, password, objet, text);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			dispose();

		}else if (e.getSource() == bannuler) {

			dispose();
		}


	}}
