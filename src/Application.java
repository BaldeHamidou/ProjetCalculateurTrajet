import java.io.FileReader;
import java.io.IOException;

import au.com.bytecode.opencsv.CSVReader;
import calculateur.implementations.MetroReseau;

public class Application {
	public static void main(String[] args) throws IOException {
		
		CSVReader arretLigne = new CSVReader(new FileReader("ratp_arret_ligne_01.csv"));
		CSVReader arretGraph = new CSVReader(new FileReader("ratp_arret_graphique_01.csv"));
		
		MetroReseau mr = new MetroReseau(arretLigne, arretGraph);
		mr.loadReseauFromCSV();
		
	}
}
