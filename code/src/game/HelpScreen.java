package game;

import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class HelpScreen {
	
	

	public HelpScreen(){
		JLabel tStone = new JLabel("Auf dieser Fläche kann Pushy frei bewegt werden");
		JLabel tRock = new JLabel("Diese Steine können nicht verschoben/ überquert werden");
		JLabel tWood = new JLabel("Von diesen Holzblöcken kann jeweils nur einer gleichzeitig verschoben weden");
		JLabel tBALL = new JLabel("Die farbigen Bälle müssen in die jeweiligen Ziele geschoben werden");
		JLabel tGoal = new JLabel("In diese Ziele müsssen die Bälle geschoben werden");
		JLabel tbutton = new JLabel("Wenn du auf dieses Feld kommst, werden die Laserschranken deaktiviert");
		JLabel tborder = new JLabel("Überquere den Button um die Laserschranken zu deaktiviren/passieren zu können");
		JLabel tpushy = new JLabel("Das ist Pushy..er möchte in sein Haus gesteuert werden");
		JLabel thome  = new JLabel("Steuer Pushy in sein Haus um das Level zu gewinnen..schiebe vorher alle Kugeln in die Ziele!");
		
		JFrame f = new JFrame();
		f.setLayout(new GridLayout(9,2));
		f.setTitle("Hilfe");
		
		
		ImageIcon topIcon = new ImageIcon("resources/images/Pushy_w.png");   //JFrame Icon und Überschrift
		f.setIconImage(topIcon.getImage());
		f.setTitle("Hilfe");

		
		f.add(new ConvertImage2Panel("resources/images/Pushy_w.png"));
		f.add(tpushy);
		
	

		f.add(new ConvertImage2Panel("resources/images/ground.png"));
		f.add(tStone);
		f.add(new ConvertImage2Panel("resources/images/Rock.png"));
		f.add(tRock);
		f.add(new ConvertImage2Panel("resources/images/wood.png"));
		f.add(tWood);
		f.add(new ConvertImage2Panel("resources/images/redBall.png"));
		f.add(tBALL);
		f.add(new ConvertImage2Panel("resources/images/redGoal.png"));
		f.add(tGoal);
		f.add(new ConvertImage2Panel("resources/images/Button.png"));
		f.add(tbutton);
		f.add(new ConvertImage2Panel("resources/images/Border.png"));
		f.add(tborder);
		f.add(new ConvertImage2Panel("resources/images/home.png"));
		f.add(thome);

	

		
		
		
		

		f.setSize(1100,600);
		f.setLocation(0, 0);
		f.setVisible(true);
		
	}

}