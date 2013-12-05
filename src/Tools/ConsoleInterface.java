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
		
		// Saisie du d�part
		System.out.println("Entrer la station de d�part:");
		String dep = sc.nextLine();
		
		// Recherche de la station d'arriv�e
		for(Station value : ratpMetro.getReseau().getMapStations().values()) {
			if(value.getName().equals(dep)){
				depart = value;
			}
		}
		
		// Saisie de l'arriv�e
		System.out.println("Entrer la station d'arriv�e:");
		String arr = sc.nextLine();
		
		// Recherche de la station d'arriv�e
		for(Station value : ratpMetro.getReseau().getMapStations().values()) {
			if(value.getName().equals(arr)){
				arrivee = value;
			}
		}
		
		if(depart != null || arrivee != null){ // On v�rifie si les stations ont �t� trouv�es
			try{
				// Trajet plus rapide
				System.out.println(depart);
				System.out.println("�");
				System.out.println(arrivee+" (plusRapide)");
				System.out.println("Trajet: "+dijkstra.plusRapideChemin(depart, arrivee));
				
				// Trajet moins de changement
				System.out.println("\n"+depart);
				System.out.println("�");
				System.out.println(arrivee+" (moinsDeChangement)");
				ArrayList<Relation> trajet = dijkstra.moinsDeChangement(depart, arrivee);
				
				if(trajet.size() > 0){
					System.out.println("Trajet: "+dijkstra.moinsDeChangement(depart, arrivee));
				}
				else {
					System.out.println("Vous y �tes d�ja ! ^^\n");
				}
			} catch(StackOverflowError e){
				System.out.println("Ce trajet n'a pas pu �tre calculer.\n");
	        }
		} else {		
			System.out.println("Station non trouv�e. \n");
		}
		start(); // On recommence
	}
}
