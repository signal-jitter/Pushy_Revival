package game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PlayerStatistic {

	
	public int load() throws IOException {				//Laden des Level-Fortschritts aus Textdatei
		
		 FileReader fr = new FileReader("resources/player.txt");  // txt im Projekt-Resourcen Ordner
		 BufferedReader br = new BufferedReader(fr);
		    int data = 1;
		    data = Integer.parseInt(br.readLine());
		    br.close();
		    return data;  //Rückgabe der  Levelinfo
		
	}
	
	public void save (int save) throws IOException {  				// Schreibt neuen Level-Fortschritt nach bestandenem Level
	if (save > load() ) {  // NUR schreiben, WENN bestandenel Level größer als max. bestandenes Level
		FileWriter fw = new FileWriter("resources/player.txt");
	    BufferedWriter bw = new BufferedWriter(fw);
	    bw.write(Integer.valueOf(save).toString());  //zum Speichern in String konvertieren
	    bw.close();
		}
	}
}
