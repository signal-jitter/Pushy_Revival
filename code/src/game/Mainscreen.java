package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Mainscreen extends JFrame implements ActionListener{			//Levelauswahl


		/**
	 * 
	 */
	private static final long serialVersionUID = -6591113863300844740L;
	
		JLabel top1 = new JLabel ("Willkommen");							//Erzeugung der Swing Elemente
		JLabel top2 = new JLabel ("bei");
		JLabel top3 = new JLabel ("Pushy-Revival");
		JLabel level = new JLabel ("Wähle ein Level:");
		JLabel story = new JLabel ("Neuauflage des Spiels Pushy, programmiert 2000 von Ralf zur Linde.");
		JLabel task = new JLabel  ("Das Ziel besteht darin, die Spielfigur in ein Ziel zu steuern (w,a,s,d auf der Tastatur)");
		JButton l1 = new JButton ("Level #1");
		JButton l2 = new JButton ("Level #2");
		JButton l3 = new JButton ("Level #3");
		JButton l4 = new JButton ("Level #4");
		JButton l5 = new JButton ("Level #5");
		JButton l6 = new JButton ("Level #6");
		JButton l7 = new JButton ("Level #7");
		JButton l8 = new JButton ("Level #8");
		JButton l9 = new JButton ("Level #9");
		JButton l10 = new JButton ("Level #10");
		JButton l11 = new JButton ("Level #11");
		JButton l12 = new JButton ("Level #12");
		JButton bAbout = new JButton ("About");
	
	public Mainscreen() {
		
	}	
	public void run() throws IOException{
		
		JPanel topPanel = new JPanel();						//Erzeugung der Panel Elemente
		topPanel.setLayout(new GridLayout(1, 5));
		
		JPanel taskPanel = new JPanel();
		taskPanel.setLayout(new GridLayout(3, 1));
		
		JPanel levelPanel = new JPanel();
		levelPanel.setLayout(new GridLayout(4, 3));
		
		JPanel aboutPanel = new JPanel();
		
		PlayerStatistic pStat = new PlayerStatistic();        //Abrufklasse des Levelfortschrittes (darin wird die player.txt ausgelesen/ eingelesen)
		
		
		this.setSize(700, 500);								//Initialisierung des Frames
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocation(500, 300);
		this.setLayout(new GridLayout(4, 1));
		
		ImageIcon topIcon = new ImageIcon("resources/images/Pushy_w.png");   //JFrame Icon und Überschrift
		this.setIconImage(topIcon.getImage());
		this.setTitle("Pushy - Revival");
		
		
		this.add(topPanel);										//Hinzufügen der Panels auf das GridLayout-Frame
		this.add(taskPanel);
		this.add(levelPanel);
		this.add(aboutPanel);
		
		top1.setForeground(Color.green);				//Bearbeitung/ Eigenschaften der Labels
		top1.setFont(new Font("Dialog",0,20));
		top1.setHorizontalAlignment(JLabel.CENTER);
		
		top2.setForeground(Color.GREEN);
		top2.setFont(new Font("Dialog",0,20));
		top2.setHorizontalAlignment(JLabel.CENTER);
		
		top3.setForeground(Color.GREEN);
		top3.setFont(new Font("Dialog",0,20));
		top3.setHorizontalAlignment(JLabel.CENTER);
		
		story.setHorizontalAlignment(JLabel.CENTER);
		task.setHorizontalAlignment(JLabel.CENTER);
		story.setFont(new Font("Dialog",0,15));
		task.setFont(new Font("Dialog",0,15));
		
		level.setHorizontalAlignment(JLabel.CENTER);
		level.setFont(new Font("Dialog",0,20));
		
		
		
		
		 
		
		topPanel.add(new ConvertImage2Panel("resources/images/Pushy_w.png"));		//Hinzufügen der Swing-Elemente auf die Panels			
		
		topPanel.add(top1);
		topPanel.add(top2);
		topPanel.add(top3);
		topPanel.add(new ConvertImage2Panel("resources/images/Pushy_w.png"));
		
		taskPanel.add(story);
		taskPanel.add(task);
		taskPanel.add(level);
		
		levelPanel.add(l1);
		l1.addActionListener(this);
		l1.setEnabled(false);
		
		levelPanel.add(l2);
		l2.addActionListener(this);
		l2.setEnabled(false);
		
		levelPanel.add(l3);
		l3.addActionListener(this);
		l3.setEnabled(false);
		
		levelPanel.add(l4);
		l4.addActionListener(this);
		l4.setEnabled(false);
		
		levelPanel.add(l5);
		l5.addActionListener(this);
		l5.setEnabled(false);
		
		levelPanel.add(l6);
		l6.addActionListener(this);
		l6.setEnabled(false);
		
		levelPanel.add(l7);
		l7.addActionListener(this);
		l7.setEnabled(false);
		
		levelPanel.add(l8);
		l8.addActionListener(this);
		l8.setEnabled(false);
		
		levelPanel.add(l9);
		l9.addActionListener(this);
		l9.setEnabled(false);
		
		levelPanel.add(l10);
		l10.addActionListener(this);
		l10.setEnabled(false);
		
		levelPanel.add(l11);
		l11.addActionListener(this);
		l11.setEnabled(false);
		
		levelPanel.add(l12);
		l12.addActionListener(this);
		l12.setEnabled(false);
		
		
		aboutPanel.add(bAbout);
		bAbout.addActionListener(this);
		
		for(int i = pStat.load(); i > 0 ; i --) {		//LEVELFORTSCHRITT  - Die Methode .load() aus der Klasse PlayerStatistics wird aufgerufen und liefert den Levelfortschritt
														// Schleife geht alle gespielten Level durch und aktiviert das jeweilige im switch case
		switch (i) {									//Die gespielten Level + aktuelles Level werden freigeschaltet
		case 1:l1.setEnabled(true); break;				//Die anderen bleiben Deaktiviert
		case 2:l2.setEnabled(true); break;
		case 3:l3.setEnabled(true); break;
		case 4:l4.setEnabled(true); break;
		case 5:l5.setEnabled(true); break;
		case 6:l6.setEnabled(true); break;
		case 7:l7.setEnabled(true); break;
		case 8:l8.setEnabled(true); break;
		case 9:l9.setEnabled(true); break;
		case 10:l10.setEnabled(true); break;
		case 11:l11.setEnabled(true); break;
		case 12:l12.setEnabled(true); break;
			
		}
		}
		
		this.setVisible(true);				//Alle Elemente werden sichtbar gemacht
		topPanel.setVisible(true);
		taskPanel.setVisible(true);
		levelPanel.setVisible(true);
		aboutPanel.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {							//ActionEvents der Level-Buttons und des About-Buttons
		if(ae.getSource() == l1) {
			Playground play = new Playground(1);
			
		}
		if(ae.getSource() == l2) {
			Playground play = new Playground(2);
	
		}
		if(ae.getSource() == l3) {
			Playground play = new Playground(3);
			
		}
		if(ae.getSource() == l4) {
			Playground play = new Playground(4);
			
		}
		if(ae.getSource() == l5) {
			Playground play = new Playground(5);
		}
		if(ae.getSource() == l6) {
			Playground play = new Playground(6);
		}
		if(ae.getSource() == l7) {
			Playground play = new Playground(7);
		}
		if(ae.getSource() == l8) {
			Playground play = new Playground(8);
		}
		if(ae.getSource() == l9) {
			Playground play = new Playground(9);
		}
		if(ae.getSource() == l10) {
			Playground play = new Playground(10);
		}
		if(ae.getSource() == l11) {
			Playground play = new Playground(11);
		}
		if(ae.getSource() == l12) {
			Playground play = new Playground(12);
		}
		if(ae.getSource() == bAbout) {						//Durch klicken des About-Buttons wird ein About Fenster erzeugt
			JFrame about = new JFrame("About");
			about.setSize(600, 300);
			about.setDefaultCloseOperation(EXIT_ON_CLOSE);
			about.setLocation(500, 500);
			about.setLayout(new GridLayout(5,1));
			JLabel textabout = new JLabel ("About");
			JLabel textabout1 = new JLabel ("Original: Puschi - Jahr 2000 von Ralf zur Linde (Lernwerkstatt)");
			JLabel textabout2 = new JLabel ("Revival: 2018 von Jannis Görlinger und Sören Seeger im Rahmen einer BG-Technik Projektarbeit");
			JLabel textabout3 = new JLabel ("Der Programmcode/ Logik sowie alle Grafiken sind selbst erstellt worden !");
			JLabel textabout4 = new JLabel ("Das Ziel: Von Null zu einem fertigen Spiel!  Viel Spaß!");
			about.add(textabout);
			about.add(textabout1);
			about.add(textabout2);
			about.add(textabout3);
			about.add(textabout4);
			about.setVisible(true);
			
			
		}
		
	}
}
