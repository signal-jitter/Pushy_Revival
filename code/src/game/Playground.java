package game;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


public class Playground extends JFrame implements KeyListener, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6539344528111111833L;
	JFrame GUI = new JFrame();								//Spielfeld Fenster erzeugen
	//JFrame succsessInfo = new JFrame ();					//Objekt des "Level geschafft" Fensters
	Sound snd = new Sound(); 								//Objekt dser Soundausgabe
	Succsess suc = new Succsess();
	int savex = 0;											//Position der Spielfigunr (x,y)
	int savey = 0;
	int[][] filling = new int[12][20];						//Erzeugung des Spielfeld Array (wird im Konstruktor mit entsprechender Map geladen)
	public int thisLevel = 1;								//Information über aktuelles Level (wird im Konstruktor gesetzt -> Default Level 1)
	public int anzKugeln = 0;
	JMenuBar mb = new JMenuBar();
	JMenu mGame = new JMenu("Spiel");
	JMenu mHelp = new JMenu("Hilfe");
	JMenuItem mData = new JMenuItem("Neustart");
	JMenuItem mExit = new JMenuItem("Beenden");
	JMenuItem mInfo = new JMenuItem("Info");
	int succsess = 0;
	
	//JButton hauptmenü = new JButton("zum Hauptmenü");    //Button im "Level geschafft" Fenster (Global, da mit ActionListener)
	//JButton next = new JButton("nächstes Level");
	
	
	
	public Playground (int level) {
		GUI.setSize(1280,768);							//Eigenschaften des Spielfensters
		GUI.setLocation(0, 0);
		GUI.addKeyListener(this);						//KeyListener w,a,s,d
		GUI.setTitle("Pushy - Level "+Integer.valueOf(level).toString());  //Titel des Fenster (variabel für jedes Level)
		ImageIcon topIcon = new ImageIcon("resources/images/Pushy_w.png");   //JFrame Icon und Überschrift
		GUI.setIconImage(topIcon.getImage());
	
		
		
		mb.add(mGame);
		mb.add(mHelp);
		mGame.add(mData);
		mGame.add(mExit);
		mHelp.add(mInfo);
		mData.addActionListener(this);
		mExit.addActionListener(this);
		mInfo.addActionListener(this);
		setJMenuBar(mb);
		GUI.setJMenuBar(mb);
		
		thisLevel = level; 								 //Aktuelles Level global verfügbar
		Levelmanager lvm = new Levelmanager();			 //Objekt der Levelmaps
		filling = lvm.level(level);						 //Lade Map des ausgewählten Level in das Spiel-Array
		check();
		init();										 	 //SPIELFELD ZEICHNEN Methode
		search();										 //Position von Pushy auf dem Spielfeld wird ermittelt

	}
	
	public void init () {			//Spielfeld Zeichnen (bei jeder Bewegung der Spielfigur)
		
		
		
		JPanel background = new JPanel ();				  //Auf das Panel wird die Map/ Array abgebildet
		background.setLayout(new GridLayout(12,20,0,0));
		GUI.add(background);							//Hinzufügen zum Spielfenster
		
		
		int x;								//SPIELFELD WIRD GEZEICHNET !!!!!!
		int y;
		
		int wait = 0;

		for (int i = 0; i < 240; i++) {   //Schleife durchläuft alle Felder (12*20 = 240 Felder)
			
				x= i/20;   									 //Alg. : Konvertieren von 1D (Gridlayout Befüllen) in 2D (Array-Map)
				y= i%20;
			switch(filling[x][y]) {  						  //Setzt für entsprechenden Wert im Array passendes Panel mit Bild ins GridLayout  (siehe Convert2ImagePanel Klasse!)
				case 0: background.add(new ConvertImage2Panel("resources/images/Rock.png"));
				break;
				case 1:
					background.add(new ConvertImage2Panel("resources/images/ground.png"));
					break;
				case 2:
					background.add(new ConvertImage2Panel("resources/images/wood.png"));
					break;
				case 3:
					background.add(new ConvertImage2Panel("resources/images/Border.png"));
					break;
				case 4:
					background.add(new ConvertImage2Panel("resources/images/Button.png"));
					break;
				
				case 8:
					background.add(new ConvertImage2Panel("resources/images/home.png"));
					break;
					
				case 9:
					background.add(new ConvertImage2Panel("resources/images/Pushy_w.png"));
					break;
					
				case 10:
					background.add(new ConvertImage2Panel("resources/images/Pushy_a.png"));
					break;
				case 11:
					background.add(new ConvertImage2Panel("resources/images/Pushy_s.png"));
					break;
				case 12:
					background.add(new ConvertImage2Panel("resources/images/Pushy_d.png"));
					break;
				case 13:
					Icon icon = new ImageIcon("resources/images/giphy4.gif");     //Gif-Animation als Icon auf einem Label
		            try {
		            	background.add(new JLabel(icon));
		            } catch (Exception e) {
		            };

					break;
				case 14:
					background.add(new ConvertImage2Panel("resources/images/blueBall.png"));
					break;
				case 15:
					background.add(new ConvertImage2Panel("resources/images/blueGoal.png"));
					break;
				case 16:
					background.add(new ConvertImage2Panel("resources/images/redBall.png"));
					break;
				case 17:
					background.add(new ConvertImage2Panel("resources/images/redGoal.png"));
					break;
				case 18:
					background.add(new ConvertImage2Panel("resources/images/yellowBall.png"));
					break;
				case 19:
					background.add(new ConvertImage2Panel("resources/images/yellowGoal.png"));
					break;
				
		}	
		
		GUI.setVisible(true);
		background.setVisible(true);			
		}	
	}
	
	
	public void check() {	// Überprüfung, wie viele Kugeln sich auf dem Spielfeld befinden				
							
		
		for(int i = 0; i<12;i++ ) {
			for(int m =0;m<20;m++) {			
				if(filling[i][m]== 14 || filling[i][m]== 16 || filling[i][m]== 18 ) {  
					
					anzKugeln ++;  //Die Anzahl wird in einer Variable gespeichert (erst wenn alle Kugeln abgearbeitetet sind, darf Pushy ins Ziel)
					
				}
			}
			
		}
	
	}	
	
	
	public void setBorderDisabled() {	// "Deaktivierung" der Laser durch überschreiben mit 1	 			
		 								//Wird ausgeführt, wenn Pushy auf den Button kommt
		for(int i = 0; i<12;i++ ) {
			for(int m =0;m<20;m++) {			
				if(filling[i][m]== 3) {  
					filling[i][m]=1;
				}
			}
			
		}
	
	}	
		
		
		
		
	

	public void search() {					//Sucht akt. Position von Pushy
		
		for(int i = 0; i<12;i++ ) {
			for(int m =0;m<20;m++) {			
				if(filling[i][m]== 9 || filling[i][m]== 10 || filling[i][m]== 11 || filling[i][m]== 12) {  //Pushy in allen Variationen --> Blickrichtung (w,a,s,d)
					savex = i;
					savey = m;
					i = 11;			//Wenn gefunden Schleifen beenden! --> Abbruchbedingung erfüllen
					m = 19;
				}
			}
			
		}
	
	}
	
public void move (int wasd) {				//Pushy versetzen
									//Pushy suchen
	
	//###########Forward##########
	
		if(wasd==1) {                       //EINGABE W				
			if (filling[savex-1][savey] == 1) {		//wenn 1 weiter oben nichts ist
			filling[savex-1][savey]=9;				//Pushy im Array 1 hoch setzen
			filling[savex][savey]=1;				//Alte Position leeren
			savex = savex -1;						//Neue Position Pushys speichern
			savey = savey;
			init();									//neu Zeichnen
		}
			else if (filling[savex-1][savey] == 2 && filling[savex-2][savey]== 1 ) {  // wenn 1 weiter oben Holz ist UND über dem Holz nichts ist!
				filling[savex-2][savey]=2;  //Holz und Pushy 1 hoch setzen
				filling[savex-1][savey]=9;
				filling[savex][savey]=1;
				savex = savex -1;						//Neue Position Pushys speichern
				savey = savey;
				init();
			}
			
		
			else if (filling[savex-1][savey] == 8 && anzKugeln==0) { 		//wenn 1 weiter oben das Haus ist UND keine Kugeln mehr auf dem Spielfeld sind
				filling[savex-1][savey]=13;					//Animation
				filling[savex][savey]=1;
				savex = savex -1;						//Neue Position Pushys speichern
				savey = savey;
				init();								//Playgruond neu zeichnen um Gif-Animation zu sehen
				snd.run();							//Ziel-Sound abspielen (KLASSE SOUND!)
				suc.run(thisLevel, GUI);			//Succsess-Fenster öffnen mit Übergabe des akt. Levels und der JFrame Referenz (KLASSE SUCCESS!)
				GUI.removeKeyListener(this);      //Steuerung der Spielfigur ist nach betreten des Ziels nicht mehr möglich!
				
				
				
				
			}
			
			
			
			else if (filling[savex-1][savey] == 14 &&( filling[savex-2][savey]== 1 || filling[savex-2][savey]== 15 ) ) {  // KUGELN// wenn 1 weiter oben BLAU Kugel ist UND über der Kugel nichts ist!
				System.out.println("Blau");
				if(filling[savex-2][savey]== 15) {	//Abfrage, ob vor Pushy das Rote Feld ist
					filling[savex-1][savey]=9;		// Falls ja, dann Pushy 1 nach oben und Kugel weg
					filling[savex][savey]=1;
					savex = savex -1;						//Neue Position Pushys speichern
					savey = savey;
					anzKugeln --;    //Anzahl der noch auf dem Spielfeld vorhandenen Kugeln um 1 runterzählen
					init();
				}else {
				filling[savex-2][savey]=14;  //Kugel und Pushy 1 hoch setzen
				filling[savex-1][savey]=9;
				filling[savex][savey]=1;
				savex = savex -1;						//Neue Position Pushys speichern
				savey = savey;
				init();
				}
			}
			
			else if (filling[savex-1][savey] == 16 &&( filling[savex-2][savey]== 1|| filling[savex-2][savey]== 17  ) ) {  // wenn 1 weiter oben ROT Kugel ist UND über der Kugel nichts ist!
				if(filling[savex-2][savey]== 17) {	//Abfrage, ob vor Pushy das Blaue Feld ist
					filling[savex-1][savey]=9;		// Falls ja, dann Pushy 1 nach oben und Kugel weg
					filling[savex][savey]=1;
					savex = savex -1;						//Neue Position Pushys speichern
					savey = savey;
					anzKugeln --;
					init();
				}else {
				filling[savex-2][savey]=16;  //Kugel und Pushy 1 hoch setzen
				filling[savex-1][savey]=9;
				filling[savex][savey]=1;
				savex = savex -1;						//Neue Position Pushys speichern
				savey = savey;
				init();
				}
			}
			
			else if (filling[savex-1][savey] == 18 && (filling[savex-2][savey]== 1|| filling[savex-2][savey]== 19 )  ) {  // wenn 1 weiter oben GELBE Kugel ist UND über der Kugel nichts ist!
				if(filling[savex-2][savey]== 19) { 	//Abfrage, ob vor Pushy das Gelbe Feld ist
					filling[savex-1][savey]=9; 		// Falls ja, dann Pushy 1 nach oben und Kugel weg
					filling[savex][savey]=1;
					savex = savex -1;						//Neue Position Pushys speichern
					savey = savey;
					anzKugeln --;
					init();
				}else {
				filling[savex-2][savey]=18;  //Kugel und Pushy 1 hoch setzen
				filling[savex-1][savey]=9;
				filling[savex][savey]=1;
				savex = savex -1;						//Neue Position Pushys speichern
				savey = savey;
				init();
				}
			}
			
			
			else if(filling[savex-1][savey]== 4) {// Ausschalten der Grenzen, wenn Button pressed
				
				setBorderDisabled();
				filling[savex-1][savey]=9;
				filling[savex][savey]=1;
				savex = savex -1;						//Neue Position Pushys speichern
				savey = savey;
				init();
			}
		}
		
		
		//##########LEFT##########
		
		
		if(wasd==2) {										// Wenn Pushy nach links läuft
			if (filling[savex][savey-1] == 1) {				// Logik analog zu "nach oben Laufen"
			filling[savex][savey-1]=10;
			filling[savex][savey]=1;
			savex = savex ;						//Neue Position Pushys speichern
			savey = savey - 1;
			init();
		}
			
			else if (filling[savex][savey-1] == 2 && filling[savex][savey-2]== 1) {
				filling[savex][savey-2]=2;
				filling[savex][savey-1]=10;
				filling[savex][savey]=1;
				savex = savex ;						//Neue Position Pushys speichern
				savey = savey - 1;
				init();
			}
			
			else if (filling[savex][savey-1] == 8 && anzKugeln == 0) {
				filling[savex][savey-1]=13;
				filling[savex][savey]=1;
				savex = savex ;						//Neue Position Pushys speichern
				savey = savey - 1;
				init();								//Playgruond neu zeichnen um Gif-Animation zu sehen
				snd.run();							//Ziel-Sound abspielen (KLASSE SOUND!)
				suc.run(thisLevel, GUI);			//Succsess-Fenster öffnen mit Übergabe des akt. Levels und der JFrame Referenz (KLASSE SUCCESS!)
				GUI.removeKeyListener(this);      //Steuerung der Spielfigur ist nach betreten des Ziels nicht mehr möglich!
			}
			
			
			
			else if (filling[savex][savey-1] == 14 && (filling[savex][savey-2]== 1 || filling[savex][savey-2]== 15) ) {  
				if(filling[savex][savey-2]== 15) {
					filling[savex][savey-1]=10;
					filling[savex][savey]=1;
					savex = savex ;						//Neue Position Pushys speichern
					savey = savey - 1;
					anzKugeln --;
					init();
				}else {
				
				filling[savex][savey-2]=14;  
				filling[savex][savey-1]=10;
				filling[savex][savey]=1;
				savex = savex ;						//Neue Position Pushys speichern
				savey = savey - 1;
				init();
				}
			}
			
			else if (filling[savex][savey-1] == 16 && (filling[savex][savey-2]== 1 || filling[savex][savey-2]== 17)) { 
				if(filling[savex][savey-2]== 17) {
					filling[savex][savey-1]=10;
					filling[savex][savey]=1;
					savex = savex ;						//Neue Position Pushys speichern
					savey = savey - 1;
					anzKugeln --;
					init();
				}else {
				filling[savex][savey-2]=16; 
				filling[savex][savey-1]=10;
				filling[savex][savey]=1;
				savex = savex ;						//Neue Position Pushys speichern
				savey = savey - 1;
				init();
				}
			}
			
			else if (filling[savex][savey-1] == 18 && (filling[savex][savey-2]== 1 || filling[savex][savey-2]== 19)) {  
				if(filling[savex][savey-2]== 19) {
					filling[savex][savey-1]=10;
					filling[savex][savey]=1;
					savex = savex ;						//Neue Position Pushys speichern
					savey = savey - 1;
					anzKugeln --;
					init();
				}else {
				filling[savex][savey-2]=18;  
				filling[savex][savey-1]=10;
				filling[savex][savey]=1;
				savex = savex ;						//Neue Position Pushys speichern
				savey = savey - 1;
				init();
				}
			}
			
			
			else if(filling[savex][savey-1]== 4) {
				setBorderDisabled();
				filling[savex][savey-1]=10;
				filling[savex][savey]=1;
				savex = savex ;						//Neue Position Pushys speichern
				savey = savey - 1;
				init();
			}	
		}
		
		
		//##########DOWN##########
		
		if(wasd==3) {									//wenn Pushy nach unten läuft
			if (filling[savex+1][savey] == 1) {			// Logik analog zu "nach oben Laufen"
			filling[savex+1][savey]=11;
			filling[savex][savey]=1;
			savex = savex +1 ;						//Neue Position Pushys speichern
			savey = savey;
			init();
		}
			
			else if (filling[savex+1][savey] == 2 && filling[savex+2][savey]== 1) {
				filling[savex+2][savey]=2;
				filling[savex+1][savey]=11;
				filling[savex][savey]=1;
				savex = savex +1 ;						//Neue Position Pushys speichern
				savey = savey;
				init();
			}
			
			else if (filling[savex+1][savey] == 8 && anzKugeln == 0) {
				filling[savex+1][savey]=13;
				filling[savex][savey]=1;
				savex = savex +1 ;						//Neue Position Pushys speichern
				savey = savey;
				init();								//Playgruond neu zeichnen um Gif-Animation zu sehen
				snd.run();							//Ziel-Sound abspielen (KLASSE SOUND!)
				suc.run(thisLevel, GUI);			//Succsess-Fenster öffnen mit Übergabe des akt. Levels und der JFrame Referenz (KLASSE SUCCESS!)
				GUI.removeKeyListener(this);      //Steuerung der Spielfigur ist nach betreten des Ziels nicht mehr möglich!
			}
			
			
			
			else if (filling[savex+1][savey] == 14 && (filling[savex+2][savey]== 1 || filling[savex+2][savey]== 15)) {  
				if(filling[savex+2][savey]== 15) {
					filling[savex+1][savey]=11;
					filling[savex][savey]=1;
					savex = savex +1 ;						//Neue Position Pushys speichern
					savey = savey;
					anzKugeln --;
					init();
				}else {
				filling[savex+2][savey]=14;  
				filling[savex+1][savey]=11;
				filling[savex][savey]=1;
				savex = savex +1 ;						//Neue Position Pushys speichern
				savey = savey;
				init();
				}
			}
			
			else if (filling[savex+1][savey] == 16 && (filling[savex+2][savey]== 1 || filling[savex+2][savey]== 17)) { 
				if(filling[savex+2][savey]== 17) {
					filling[savex+1][savey]=11;
					filling[savex][savey]=1;
					savex = savex +1 ;						//Neue Position Pushys speichern
					savey = savey;
					anzKugeln --;
					init();
				}else {
				filling[savex+2][savey]=16; 
				filling[savex+1][savey]=11;
				filling[savex][savey]=1;
				savex = savex +1 ;						//Neue Position Pushys speichern
				savey = savey;
				init();
				}
			}
			
			else if (filling[savex+1][savey] == 18 && (filling[savex+2][savey]== 1 || filling[savex+2][savey]== 19)) {  
				if(filling[savex+2][savey]== 19) {
					filling[savex+1][savey]=11;
					filling[savex][savey]=1;
					savex = savex +1 ;						//Neue Position Pushys speichern
					savey = savey;
					anzKugeln --;
					init();
				}else {
				filling[savex+2][savey]=18;  
				filling[savex+1][savey]=11;
				filling[savex][savey]=1;
				savex = savex +1 ;						//Neue Position Pushys speichern
				savey = savey;
				init();
				}
			}
			
			else if(filling[savex+1][savey]== 4) {
				setBorderDisabled();
				filling[savex+1][savey]=11;
				filling[savex][savey]=1;
				savex = savex +1 ;						//Neue Position Pushys speichern
				savey = savey;
				init();
				
			}
		}
		
		
		//##########RIGHT##########
		
		if(wasd==4) {									//wenn Pushy nach rechts läuft
			if (filling[savex][savey+1] == 1) {			// Logik analog zu "nach oben Laufen"
			filling[savex][savey+1]=12;
			filling[savex][savey]=1;
			savex = savex;						//Neue Position Pushys speichern
			savey = savey + 1;
			init();
		}
			else if (filling[savex][savey+1] == 2 && filling[savex][savey+2]== 1) {
				filling[savex][savey+2]=2;
				filling[savex][savey+1]=12;
				filling[savex][savey]=1;
				savex = savex;						//Neue Position Pushys speichern
				savey = savey + 1;
				init();
			}
			else if (filling[savex][savey+1] == 8 && anzKugeln == 0) {
				filling[savex][savey+1]=13;
				filling[savex][savey]=1;
				savex = savex;						//Neue Position Pushys speichern
				savey = savey + 1;
				init();								//Playgruond neu zeichnen um Gif-Animation zu sehen
				snd.run();							//Ziel-Sound abspielen (KLASSE SOUND!)
				suc.run(thisLevel, GUI);			//Succsess-Fenster öffnen mit Übergabe des akt. Levels und der JFrame Referenz (KLASSE SUCCESS!)
				GUI.removeKeyListener(this);      //Steuerung der Spielfigur ist nach betreten des Ziels nicht mehr möglich!
			}
			
			
			
			else if (filling[savex][savey+1] == 14 && (filling[savex][savey+2]== 1 || filling[savex][savey+2]== 15)) {
				
				if(filling[savex][savey+2]== 15) {
					filling[savex][savey+1]=12;
					filling[savex][savey]=1;
					savex = savex;						//Neue Position Pushys speichern
					savey = savey + 1;
					anzKugeln --;
					init();
				}else {
				filling[savex][savey+2]=14;  
				filling[savex][savey+1]=12;
				filling[savex][savey]=1;
				savex = savex;						//Neue Position Pushys speichern
				savey = savey + 1;
				init();
				}
			}
			
			else if (filling[savex][savey+1] == 16 && (filling[savex][savey+2]== 1 || filling[savex][savey+2]== 17)) { 
				
				if(filling[savex][savey+2]== 17) {
					filling[savex][savey+1]=12;
					filling[savex][savey]=1;
					savex = savex;						//Neue Position Pushys speichern
					savey = savey + 1;
					anzKugeln --;
					init();
				}else {
				filling[savex][savey+2]=16; 
				filling[savex][savey+1]=12;
				filling[savex][savey]=1;
				savex = savex;						//Neue Position Pushys speichern
				savey = savey + 1;
				init();
				}
			}
			
			else if (filling[savex][savey+1] == 18 && (filling[savex][savey+2]== 1 || filling[savex][savey+2]== 19)) {
				
				if(filling[savex][savey+2]== 19) {
					filling[savex][savey+1]=12;
					filling[savex][savey]=1;
					savex = savex;						//Neue Position Pushys speichern
					savey = savey + 1;
					anzKugeln --;
					init();
				}else {
				filling[savex][savey+2]=18;  
				filling[savex][savey+1]=12;
				filling[savex][savey]=1;
				savex = savex;						//Neue Position Pushys speichern
				savey = savey + 1;
				init();
				}
			} 
			
			
			else if(filling[savex][savey+1]== 4) {
				setBorderDisabled();
				filling[savex][savey+1]=12;
				filling[savex][savey]=1;
				savex = savex;						//Neue Position Pushys speichern
				savey = savey + 1;
				init();
			}
		}
	}


public void keyPressed(KeyEvent ae) {	
}

@Override
public void keyReleased(KeyEvent ae) {				//KeyEvent zur Eingabe w,a,s,d
	if(ae.getKeyChar()== 'w') {
		move(1);								//Methode wird passend aufgerufen
	}
	if(ae.getKeyChar()== 'a') {
		move(2);
	}
	if(ae.getKeyChar()== 's') {
		move(3);
	}
	if(ae.getKeyChar()== 'd') {
		move(4);
	}
	
}

@Override
public void keyTyped(KeyEvent ae) {

	


}

@Override
public void actionPerformed(ActionEvent e) {				//ActionListener des Drop-Down Menüs
	
    if(e.getSource() == mData) {
		GUI.setVisible(false);
		Playground play = new Playground (thisLevel);
	}
	
	if(e.getSource()== mExit) {
		
		GUI.setVisible(false);
	}
	
	if(e.getSource()==mInfo) {
		HelpScreen hs = new HelpScreen();
	}

	
}

}



