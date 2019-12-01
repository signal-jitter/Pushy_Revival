package game;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL; 
public class Sound extends Thread {


    public void run () {   //Abspielen von Ziel Sound
       	int random = 0;
           random = (int)(Math.random()*5);		//zufälliger Sound
           
       	URL url = this.getClass().getResource("Sound1.wav");
       	
           	switch (random) {			//laden des Sounds
           	case 0 : url = this.getClass().getResource("Sound1.wav"); break;
           	case 1 : url = this.getClass().getResource("Sound2.wav"); break;
           	case 2 : url = this.getClass().getResource("Sound3.wav"); break;
           	case 3 : url = this.getClass().getResource("Sound4.wav"); break;
           	case 4 : url = this.getClass().getResource("Sound5.wav"); break;
        }
           	
           
            AudioClip sound = Applet.newAudioClip(url);  // Abspielen der zufälligen Sound URL
            sound.play(); 
       	
           
           	
       }	
    
          	
}