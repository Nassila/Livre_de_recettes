package Main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

public class ConsulterRecette extends JFrame implements ActionListener {
	private JPanel pcenter, pnorth;
	private JButton bimprimer, bfermer, bpartager;
	private JTextArea recette;
	private JScrollPane scrollp;
	private JMenuBar menu;
	private JMenu file, help;
	private JMenuItem quitter, aprpos, imprimer, partager;
	String Objet = "";
	String Text = "";

	public ConsulterRecette(String objet, String text) {
		this.Objet = objet;
		this.Text = text;
		// titre de la fenetre
		setTitle("Consulter");
		// taille de la fenetre
		setSize(555, 440);

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

		// création de l'item "imprimer"
		imprimer = new JMenuItem("Imprimer");
		file.add(imprimer);
		// raccourci clavier; quand on appuie sur CTRL+I on imprime la rectte
		imprimer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));
		imprimer.addActionListener(this);
		imprimer.setVisible(false);

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

		// ajouet le panneau centre au centre
		add(pcenter, "Center");

		recette = new JTextArea(20, 40);
		scrollp = new JScrollPane(recette);
		pcenter.add(scrollp);
		recette.setText(Objet + "\n" + Text);
		recette.setEditable(false);

		// creation du panneau nord
		pnorth = new JPanel();
		pnorth.setBackground(new Color(191, 136, 95));
		add(pnorth, "North");

		// création des boutons

		bimprimer = new JButton("Imprimer");
		pnorth.add(bimprimer);
		bimprimer.addActionListener(this);

		bpartager = new JButton("Partager");
		pnorth.add(bpartager);
		bpartager.addActionListener(this);

		bfermer = new JButton("Fermer");
		pnorth.add(bfermer);
		bfermer.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {

		// si on appuie sur le bouton "imprimer"
		if (e.getSource() == bimprimer || e.getSource() == imprimer) {

			try {
				boolean b = recette.print();
				if (b) {
					JOptionPane.showMessageDialog(null, "Donne printing", "Information",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Printing!", "Printer", JOptionPane.ERROR_MESSAGE);
				}

			} catch (PrinterException e1) {
				JOptionPane.showMessageDialog(null, e1);
			}
		} else if (e.getSource() == bfermer) {

			dispose();

		} else if (e.getSource() == aprpos) {
			// ouvrir la fenetre Apropos
			Apropos a = new Apropos();
			a.setVisible(true);

		} else if (e.getSource() == partager || e.getSource() == bpartager) {
			String objet = "Recette";
			String text = recette.getText();
			Mail mail = new Mail(objet, text);
			mail.setVisible(true);
		}

	}
}
