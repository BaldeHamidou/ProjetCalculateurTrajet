package Tools;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import calculateur.abstracts.Ligne;
import calculateur.abstracts.Relation;
import calculateur.abstracts.Station;
import calculateur.implementations.metro.ModaliteMetro;
import calculateur.implementations.tramway.ModaliteTram;

public class ConsoleInterface {

	private static ModaliteMetro ratpMetro = new ModaliteMetro();
	private static ModaliteTram ratpTram = new ModaliteTram();
	private static Dijkstra dijkstra;
	private static Scanner entreeClavier = new Scanner(System.in);

	public static void start() {

		boolean exit = false;

		System.out
				.println("\tBienvenue ! \nPour faire votre choix, tapez le numéro correspondant.");
		while (!exit) {

			// ajout des autres modalités par la suite.
			switch (getChoice(3)) {
			case 1:
				metroModalite();
				break;
			case 2:
				tramModalite();
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

		if (!depart.equals(arrivee)) {
			try {
				System.out.println("Trajet le plus rapide:");
				ArrayList<Relation> relationsTrajet = dijkstra
						.plusRapideChemin(depart, arrivee);
				afficheTrajet(relationsTrajet, depart, arrivee);
			} catch (StackOverflowError e) {
				System.out
						.println("Désolé, le trajet le plus rapide pour aller de "
								+ depart.getName()
								+ " à "
								+ arrivee.getName()
								+ " n'a pas pu être calculé.\n");
			}

			try {
				System.out.println("Trajet avec le moins de changement:");
				ArrayList<Relation> relationsTrajet = dijkstra
						.moinsDeChangement(depart, arrivee);
				afficheTrajet(relationsTrajet, depart, arrivee);
			} catch (StackOverflowError e) {
				System.out
						.println("Désolé, le trajet avec le moins de changement pour aller de "
								+ depart.getName()
								+ " à "
								+ arrivee.getName()
								+ " n'a pas pu être calculé.\n");
			}
		} else
			System.out.println("Vous y êtes déja ! ^^");
	}

	private static int getChoice(int numMaxChoix) {

		int choix = 0;
		do {
			try {
				System.out.println("Tapez 3 pour quitter l'application \n");
				System.out.println("\tChoix de la modalité: ");
				System.out.println("1. Metro");
				System.out.println("2. Tramway");
				System.out.println("Votre choix ?:");
				choix = entreeClavier.nextInt();
				entreeClavier.nextLine();
				if (choix < 1 || choix > numMaxChoix)
					System.out.println("Choix '" + choix + "' incorrecte.\n");
			} catch (InputMismatchException e) {
				String donneeEntree = entreeClavier.next();
				System.out
						.println("Choix '" + donneeEntree + "' incorrecte.\n");
				choix = 0;
			}

		} while (choix < 1 || choix > numMaxChoix);

		return choix;
	}

	private static Station getStationByNameMetro(boolean isDepart) {

		Station station;
		while (true) {
			if (isDepart)
				System.out.println("Entrez le nom de la station de départ:");
			else
				System.out.println("Entrez le nom de la station d'arrivée:");

			String nomStation = entreeClavier.nextLine();

			// Recherche de la station
			if (nomStation.trim().length() > 3
					&& nomStation.trim().length() < 36) {
				for (Station value : ratpMetro.getReseau().getMapStations()
						.values()) {
					if (value.getName().trim().toLowerCase()
							.equals(nomStation.trim().toLowerCase())) {
						station = value;
						return station;
					}
				}
			}
			System.out
					.println("La station '"
							+ nomStation.trim()
							+ "' est introuvable ou n'existe pas. Recommencez votre saisie.");
		}
	}

	private static Station getStationByNameTram(boolean isDepart) {

		Station station;
		while (true) {
			if (isDepart)
				System.out.println("Entrez le nom de la station de départ:");
			else
				System.out.println("Entrez le nom de la station d'arrivée:");

			String nomStation = entreeClavier.nextLine();
			
			// Recherche de la station
			if (nomStation.trim().length() > 4
					&& nomStation.trim().length() < 32) {
				for (Station value : ratpTram.getReseau().getMapStations()
						.values()) {
					if (value.getName().trim().toLowerCase()
							.equals(nomStation.trim().toLowerCase())) {
						station = value;
						return station;
					}
				}
			}
			System.out
					.println("La station '"
							+ nomStation.trim()
							+ "' est introuvable ou n'existe pas. Recommencez votre saisie.");
		}
	}
	
	private static void afficheTrajet(ArrayList<Relation> relations,
			Station depart, Station arrivee) {

		System.out.println("\tDépart: " + depart.getName() + "\tArrivée: "
				+ arrivee.getName());
		if (relations.size() > 0) {
			int i = 0;
			if (relations.size() > 1) {
				ArrayList<Integer> indicesCorrespondance = new ArrayList<Integer>();
				Ligne ligne = relations.get(0).getLigne();
				while (i < relations.size()
						&& relations.get(i).getLigne().equals(ligne)) {
					i++;
					if (i < relations.size()
							&& !relations.get(i).getLigne().equals(ligne)) {
						indicesCorrespondance.add(i - 1);
						ligne = relations.get(i).getLigne();
					}
				}
				if (indicesCorrespondance.size() > 0) {
					for (int j = 0; j < indicesCorrespondance.size(); j++) {
						System.out.println(" "
								+ relations.get(indicesCorrespondance.get(j))
										.getLigne() + ":");
						if (j == 0)
							System.out.println("   depuis " + depart.getName());
						else
							System.out.println("   depuis "
									+ relations.get(indicesCorrespondance
											.get(j) + 1));

						System.out.println("   direction "
								+ relations.get(indicesCorrespondance.get(j))
										.getDirection());
						System.out.println("   jusqu'à "
								+ relations.get(indicesCorrespondance.get(j))
										.getStationArrivee().getName() + "\n");
					}
					// affichage du dernier tronçon
					System.out.println(" " + ligne + ":");
					System.out
							.println("   depuis "
									+ relations
											.get(indicesCorrespondance
													.get(indicesCorrespondance
															.size() - 1) + 1)
											.getStationDepart().getName());
					System.out.println("   direction "
							+ relations.get(relations.size() - 1)
									.getDirection());
					System.out
							.println("   jusqu'à " + arrivee.getName() + "\n");
				} else {
					System.out.println(" " + relations.get(0).getLigne() + ":");
					System.out.println("   depuis " + depart.getName());
					System.out.println("   direction "
							+ relations.get(relations.size() - 1)
									.getDirection());
					System.out
							.println("   jusqu'à " + arrivee.getName() + "\n");
				}
			} else {
				System.out.println(relations.get(0).getLigne() + ":");
				System.out.println("   depuis " + depart.getName());
				System.out.println("   direction "
						+ relations.get(0).getDirection());
				System.out.println("   jusqu'à " + arrivee.getName() + "\n");
			}
		} else
			System.out
					.println("Désolé le calculateur à retourner un trajet vide.\nCe trajet n'est peut être pas possible en utilisant uniquement cette modalité de transport.\n");
	}
	
	private static void tramModalite() {
		
		dijkstra = new Dijkstra(ratpTram);
		Station depart = getStationByNameTram(true);
		Station arrivee = getStationByNameTram(false);

		if (!depart.equals(arrivee)) {
			try {
				System.out.println("Trajet le plus rapide:");
				ArrayList<Relation> relationsTrajet = dijkstra
						.plusRapideChemin(depart, arrivee);
				afficheTrajet(relationsTrajet, depart, arrivee);
			} catch (StackOverflowError e) {
				System.out
						.println("Désolé, le trajet le plus rapide pour aller de "
								+ depart.getName()
								+ " à "
								+ arrivee.getName()
								+ " n'a pas pu être calculé.\n");
			}

			try {
				System.out.println("Trajet avec le moins de changement:");
				ArrayList<Relation> relationsTrajet = dijkstra
						.moinsDeChangement(depart, arrivee);
				afficheTrajet(relationsTrajet, depart, arrivee);
			} catch (StackOverflowError e) {
				System.out
						.println("Désolé, le trajet avec le moins de changement pour aller de "
								+ depart.getName()
								+ " à "
								+ arrivee.getName()
								+ " n'a pas pu être calculé.\n");
			}
		} else
			System.out.println("Vous y êtes déja ! ^^");
	}
}
