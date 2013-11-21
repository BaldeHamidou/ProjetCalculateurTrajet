import java.io.IOException;

import calculateur.implementations.ReseauMetro;

public class Application {
	
	//private final static String LISTE_STATIONS = "/MetroReseau/ListeStations.csv";
	
	public static void main(String[] args) throws IOException {
		
		/*MetroReseau mr = new MetroReseau(CsvFileHelper.getResource(LISTE_STATIONS));
		mr.loadReseauFromCSV();		
		
		//TEST sur la première station!
		System.out.println(mr.getlMetro().get(1)[1]);
		System.out.println(mr.getlMetro().get(1)[2]);
		System.out.println(mr.getlMetro().get(1)[3]);
		System.out.println(mr.getlMetro().get(1)[4]);
		// --*/
		
		ReseauMetro ratpMetro = new ReseauMetro();
		ratpMetro.loadReseauFromCSV();
	}
}
