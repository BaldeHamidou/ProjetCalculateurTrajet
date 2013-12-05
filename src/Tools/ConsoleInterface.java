package Tools;

import java.util.ArrayList;
import java.util.Scanner;

import calculateur.abstracts.Relation;
import calculateur.abstracts.Station;
import calculateur.implementations.Dijkstra;
import calculateur.implementations.ModaliteMetro;

public class ConsoleInterface {
	
	private ModaliteMetro ratpMetro;
	private Dijkstra dijkstra;
	private Scanner sc;
	
	public ConsoleInterface(ModaliteMetro rm, Dijkstra d){
		this.sc = new Scanner(System.in);
		this.ratpMetro = rm;
		this.dijkstra = d;
	}
	
	public void start(){
		Station depart = null;
		Station arrivee = null;
		
		// Saisie du départ
		System.out.println("Entrer la station de départ:");
		String dep = sc.nextLine();
		
		// Recherche de la station d'arrivée
		for(Station value : ratpMetro.getReseau().getMapStations().values()) {
			if(value.getName().equals(dep)){
				depart = value;
			}
		}
		
		// Saisie de l'arrivée
		System.out.println("Entrer la station d'arrivée:");
		String arr = sc.nextLine();
		
		// Recherche de la station d'arrivée
		for(Station value : ratpMetro.getReseau().getMapStations().values()) {
			if(value.getName().equals(arr)){
				arrivee = value;
			}
		}
		
		if(depart != null || arrivee != null){ // On vérifie si les stations ont été trouvées
			try{
				// Trajet plus rapide
				System.out.println(depart);
				System.out.println("à");
				System.out.println(arrivee+" (plusRapide)");
				System.out.println("Trajet: "+dijkstra.plusRapideChemin(depart, arrivee));
				
				// Trajet moins de changement
				System.out.println("\n"+depart);
				System.out.println("à");
				System.out.println(arrivee+" (moinsDeChangement)");
				ArrayList<Relation> trajet = dijkstra.moinsDeChangement(depart, arrivee);
				
				if(trajet.size() > 0){
					System.out.println("Trajet: "+dijkstra.moinsDeChangement(depart, arrivee));
				}
				else {
					System.out.println("Vous y êtes déja ! ^^\n");
				}
			} catch(StackOverflowError e){
				System.out.println("Ce trajet n'a pas pu être calculer.\n");
	        }
		} else {		
			System.out.println("Station non trouvée. \n");
		}
		start(); // On recommence
	}
}
