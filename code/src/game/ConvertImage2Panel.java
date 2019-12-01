package game;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;



public class ConvertImage2Panel extends JPanel {		



														
    /**
	 * 
	 */
	private static final long serialVersionUID = -1031192433775817669L;
	private Image image = null;

    public ConvertImage2Panel(String filename) {			//Der Pfad des Bildes (png) wird übergeben
        this.image = new ImageIcon(filename).getImage();    //Das Bild wird in ein Image-Element geschrieben
    }
    
    public ConvertImage2Panel(URL filename) {			//Der Pfad des Bildes (png) wird übergeben
        this.image = new ImageIcon(filename).getImage();    //Das Bild wird in ein Image-Element geschrieben
    }

    @Override
    protected void paintComponent(Graphics g) {				//das Image wird gezeichnet und in der Klassen Playscreen dem Panel hinzugefügt
        super.paintComponent(g);
        g.drawImage(image, 0, 0, image.getWidth(null), image.getHeight(null), null);
    }
}