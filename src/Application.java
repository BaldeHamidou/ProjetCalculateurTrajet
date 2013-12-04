import java.io.IOException;
import java.util.ArrayList;

import calculateur.abstracts.Relation;
import calculateur.abstracts.Station;
import calculateur.implementations.Dijkstra;
import calculateur.implementations.ModaliteMetro;

public class Application {
	
	public static void main(String[] args) throws IOException {
		
		// il faudra mettre un try catch StackOverflowError sur chaque appelle de calcul de trajet
		try{
		ModaliteMetro ratpMetro = new ModaliteMetro();
		Dijkstra dijkstra = new Dijkstra(ratpMetro);
		Station depart = ratpMetro.getReseau().getMapStations().get("120");
		Station arrivee = ratpMetro.getReseau().getMapStations().get("19");
		System.out.println(depart);
		System.out.println("à");
		System.out.println(arrivee+" (plusRapide)");
		System.out.println("Trajet: "+dijkstra.plusRapideChemin(depart, arrivee));
		
		Station depart2 = ratpMetro.getReseau().getMapStations().get("120");
		Station arrivee2 = ratpMetro.getReseau().getMapStations().get("19");
		System.out.println("\n"+depart2);
		System.out.println("à");
		System.out.println(arrivee2+" (moinsDeChangement)");
		ArrayList<Relation> trajet = dijkstra.moinsDeChangement(depart2, arrivee2);
		if(trajet.size() > 0)
			System.out.println("Trajet: "+dijkstra.moinsDeChangement(depart2, arrivee2));
		else
			System.out.println("Vous y êtes déja ! ^^");
		}
		catch(StackOverflowError e){
            //System.err.println("ouch!");
			System.out.println("Ce trajet n'a pas pu être calculer.");
        }

	}
}
