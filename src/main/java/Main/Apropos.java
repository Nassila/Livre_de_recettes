package Main;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Apropos extends JFrame {

	private JPanel pcentre,panel;
	private JLabel lfond,lfond1,lfond2;
	private JScrollPane scrollp;

	public  Apropos() {
		setTitle("Apropos");
		setSize(260, 160);
		setLocationRelativeTo(this);
		setResizable(false);

		//modification de l'icone de la fenetre
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.getImage("Images/imageDeFond1.png");
		setIconImage(img);

		//creation du panneau de la fenetre 	 
		panel=new JPanel();
		add(panel,"Center");
		panel.setBackground(new Color(132,141,230));

		panel.setLayout(new BorderLayout());
		lfond=new JLabel("Cette application a été programmée en utilisant le langage de programmation orienté objet JAVA sous l'IDE eclipse \n");
		scrollp = new JScrollPane(lfond);
		panel.add(scrollp);

	}

}

