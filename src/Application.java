import java.io.IOException;

import calculateur.abstracts.Station;
import calculateur.implementations.Dijkstra;
import calculateur.implementations.ModaliteMetro;

public class Application {
	
	public static void main(String[] args) throws IOException {
		
		ModaliteMetro ratpMetro = new ModaliteMetro();
		Dijkstra dijkstra = new Dijkstra(ratpMetro);
		Station depart = ratpMetro.getReseau().getMapStations().get("120");
		Station arrivee = ratpMetro.getReseau().getMapStations().get("19");
		System.out.println(depart);
		System.out.println("à");
		System.out.println(arrivee);
		System.out.println("Trajet: "+dijkstra.plusRapideChemin(depart, arrivee));
		
		Station depart2 = ratpMetro.getReseau().getMapStations().get("75");
		Station arrivee2 = ratpMetro.getReseau().getMapStations().get("48");
		System.out.println(depart2);
		System.out.println("à");
		System.out.println(arrivee2);
		System.out.println("Trajet: "+dijkstra.plusRapideChemin(depart2, arrivee2));
	}
}
