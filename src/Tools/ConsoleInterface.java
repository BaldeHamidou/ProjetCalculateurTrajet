package Tools;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import calculateur.abstracts.Relation;
import calculateur.abstracts.Station;
import calculateur.implementations.Dijkstra;
import calculateur.implementations.ModaliteMetro;

public class ConsoleInterface {

	private static ModaliteMetro ratpMetro = new ModaliteMetro();
	private static Dijkstra dijkstra;
	private static Scanner entreeClavier = new Scanner(System.in);

	public static void start() {
		
		boolean exit = false;
		
		System.out.println("\tBienvenue ! \nPour faire votre choix, tapez le numéro correspondant.");
		while(!exit){
		
			// ajout des autres modalités par la suite.
			switch (getChoice(2)) {
				case 1:
					metroModalite();
				break;
				default:
					exit = true;
					System.out.println("Aurevoir ;)");
			}
		}
	}

	private static void metroModalite() {
		
		dijkstra = new Dijkstra(ratpMetro);
		Station depart = getStationByNameMetro(true);
		Station arrivee = getStationByNameMetro(false);
		
		if(!depart.equals(arrivee)){
			try{
				System.out.println("\tTrajet le plus rapide:");
				ArrayList<Relation> relationsTrajet = dijkstra.plusRapideChemin(depart, arrivee);
				afficheTrajet(relationsTrajet);
				System.out.println("\n");
			}
			catch (StackOverflowError e) {
				System.out.println("Désolé, le trajet le plus rapide pour aller de "+depart.getName()+" à "+arrivee.getName()+" n'a pas pu être calculé.\n");
			}
		
			try{
				System.out.println("\tTrajet avec le moins de changement:");
				ArrayList<Relation> relationsTrajet = dijkstra.moinsDeChangement(depart, arrivee);
				afficheTrajet(relationsTrajet);
				System.out.println("\n");
			}
			catch (StackOverflowError e) {
				System.out.println("Désolé, le trajet avec le moins de changement pour aller de "+depart.getName()+" à "+arrivee.getName()+" n'a pas pu être calculé.\n");
			}
		}
		else
			System.out.println("Vous y êtes déja ! ^^");
	}
	
	private static int getChoice(int numMaxChoix) {

		int choix = 0;
		do {
			try {
				System.out.println("Tapez 2 pour quitter l'application \n");
				System.out.println("\tChoix de la modalité: ");
				System.out.println("1. Metro");
				System.out.println("Votre choix ?:");
				choix = entreeClavier.nextInt();
				entreeClavier.nextLine();
				if(choix < 1 || choix > numMaxChoix)
					System.out.println("Choix '" + choix + "' incorrecte.\n");
			} catch (InputMismatchException e) {
				String donneeEntree = entreeClavier.next();
				System.out.println("Choix '" + donneeEntree + "' incorrecte.\n");
				choix = 0;
			}
			
		} while (choix < 1 || choix > numMaxChoix);

		return choix;
	}
	
	private static Station getStationByNameMetro(boolean isDepart) {

		Station station;
		while(true) {
			if (isDepart)
				System.out.println("Entrez le nom de la station de départ:");
			else
				System.out.println("Entrez le nom de la station d'arrivée:");

			String nomStation = entreeClavier.nextLine();

			// Recherche de la station
			if (nomStation.trim().length() > 3 && nomStation.trim().length() < 36) {
				for (Station value : ratpMetro.getReseau().getMapStations().values()) {
					if (value.getName().trim().toLowerCase().equals(nomStation.trim().toLowerCase())) {
						station = value;
						return station;
					}
				}
			}
			System.out.println("La station '"+nomStation.trim()+"' est introuvable ou n'existe pas. Recommencez votre saisie.");
		} 
	}

	private static void afficheTrajet(ArrayList<Relation> relations){
		System.out.println(relations);
	}
}
