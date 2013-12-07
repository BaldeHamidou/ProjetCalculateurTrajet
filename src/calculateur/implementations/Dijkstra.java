package calculateur.implementations;

import java.util.ArrayList;
import java.util.Map;

import calculateur.abstracts.Ligne;
import calculateur.abstracts.Modalite;
import calculateur.abstracts.Relation;
import calculateur.abstracts.Station;
import calculateur.interfaces.IAlgoCalcul;

public class Dijkstra implements IAlgoCalcul {

	private Map<Station, ArrayList<Relation>> grapheReseau;

	public Dijkstra(Modalite modalite) {
		this.grapheReseau = modalite.getReseau().getGrapheReseau();
	}

	@Override
	public ArrayList<Relation> plusRapideChemin(Station depart, Station arrivee) {

		// uniquement sur des stations disponible dans le réseau associé à la modalité
		if (grapheReseau.containsKey(depart)
				&& grapheReseau.containsKey(arrivee)) {
			ArrayList<Relation> relations = new ArrayList<Relation>();
			if (depart.equals(arrivee)) {
				return relations;
			}

			Ligne ligne;
			// la station d'arrivée est sur la même ligne que celle de départ
			if ((ligne = isOnTheSameLineMin(depart, arrivee)) != null) {
				if (ligne.getListTerminus().size() <= 2) {
					Ligne ligneT;
					for (int j = 0; j < ligne.getListStation().size(); j++) {
						if (!(ligne.getListStation().get(j).equals(depart))
								&& !(ligne.getListStation().get(j)
										.equals(arrivee))
								&& (ligneT = isOnTheSameLineMin(ligne
										.getListStation().get(j), arrivee)) != null
								&& !ligneT.getNameLigne().equals(
										ligne.getNameLigne())) {
							if ((ligne.nbRelationsEntreDeuxStationsSurLaLigne(
									depart, ligne.getListStation().get(j)) + ligneT
									.nbRelationsEntreDeuxStationsSurLaLigne(
											ligne.getListStation().get(j),
											arrivee)) < ligne
									.nbRelationsEntreDeuxStationsSurLaLigne(
											depart, arrivee)) {
								relations = ligne.relationsBetweenTwoStations(
										depart, ligne.getListStation().get(j));
								ArrayList<Relation> relationsToAdd = ligneT
										.relationsBetweenTwoStations(ligne
												.getListStation().get(j),
												arrivee);

								if (relationsToAdd.size() > 0) {
									for (int l = 0; l < relationsToAdd.size(); l++) {
										relations.add(relationsToAdd.get(l));
									}
								}
							}
						}
					}
					if (relations.size() > 0)
						return relations;
				}
				return ligne.relationsBetweenTwoStations(depart, arrivee);
			}
			// sinon
			else {

				int nbRelations = -1;
				int nbRelationsVoisines = -1;
				Station StationVoisine = null;

				ArrayList<Ligne> lignesStationDepart = depart.getLstLignes();
				for (int i = 0; i < lignesStationDepart.size(); i++) {
					ArrayList<Station> stationsLigneEnCours = lignesStationDepart
							.get(i).getListStation();
					for (int j = 0; j < stationsLigneEnCours.size(); j++) {

						if (stationsLigneEnCours.get(j).getLstLignes().size() > 1) {
							ArrayList<Ligne> correspondanceNew = new ArrayList<Ligne>();
							ArrayList<Ligne> lignesCorrespondanceStation = stationsLigneEnCours
									.get(j).getLstLignes();
							for (int k = 0; k < lignesCorrespondanceStation
									.size(); k++) {
								/*if (!lignesStationDepart
										.contains(lignesCorrespondanceStation
												.get(k)))*/
									correspondanceNew
											.add(lignesCorrespondanceStation
													.get(k));
							}
							if (correspondanceNew.size() > 0) {

								if (nbRelationsVoisines == -1) {
									nbRelationsVoisines = lignesStationDepart
											.get(i)
											.nbRelationsEntreDeuxStationsSurLaLigne(
													depart,
													stationsLigneEnCours.get(j));
									StationVoisine = stationsLigneEnCours
											.get(j);
								} else {
									if (nbRelationsVoisines > lignesStationDepart
											.get(i)
											.nbRelationsEntreDeuxStationsSurLaLigne(
													depart,
													stationsLigneEnCours.get(j))) {
										nbRelationsVoisines = lignesStationDepart
												.get(i)
												.nbRelationsEntreDeuxStationsSurLaLigne(
														depart,
														stationsLigneEnCours
																.get(j));
										StationVoisine = stationsLigneEnCours
												.get(j);
									}
								}
							}
						}

					}

					if (StationVoisine != null) {
						for (int m = 0; m < StationVoisine.getLstLignes()
								.size(); m++) {
							for (int n = 0; n < StationVoisine.getLstLignes()
									.get(m).getListStation().size(); n++) {
								for (int p = 0; p < arrivee.getLstLignes()
										.size(); p++) {
									if (StationVoisine
											.getLstLignes()
											.get(m)
											.getListStation()
											.get(n)
											.getLstLignes()
											.contains(
													arrivee.getLstLignes().get(
															p))
											&& (isOnTheSameLineMin(
													StationVoisine
															.getLstLignes()
															.get(m)
															.getListStation()
															.get(n), arrivee) != null)) {
										ArrayList<Relation> relationsTemp = plusRapideChemin(
												depart, StationVoisine);
										ArrayList<Relation> relationsToAdd = plusRapideChemin(
												StationVoisine, StationVoisine
														.getLstLignes().get(m)
														.getListStation()
														.get(n));
										ArrayList<Relation> relationsToAdd2 = plusRapideChemin(
												StationVoisine.getLstLignes()
														.get(m)
														.getListStation()
														.get(n), arrivee);

										if (relationsToAdd.size() > 0) {
											for (int l = 0; l < relationsToAdd
													.size(); l++) {
												relationsTemp
														.add(relationsToAdd
																.get(l));
											}
										}

										if (relationsToAdd2.size() > 0) {
											for (int l = 0; l < relationsToAdd2
													.size(); l++) {
												relationsTemp
														.add(relationsToAdd2
																.get(l));
											}
										}

										if (nbRelations == -1) {
											nbRelations = relationsTemp.size();
											relations = relationsTemp;
										} else {
											if (nbRelations > relationsTemp
													.size()) {
												nbRelations = relationsTemp
														.size();
												relations = relationsTemp;
											}
										}
									}
								}
							}
						}
					}
				}
				return relations;
			}
		}
		return null;
	}

	// Retourne null si les stations ne sont pas sur la mm ligne ou la ligne où
	// les deux stations sont le moins éloignés
	public Ligne isOnTheSameLineMin(Station depart, Station arrivee) {

		int nbRelations = -1;
		Ligne ligne = null;
		for (int i = 0; i < depart.getLstLignes().size(); i++) {
			for (int j = 0; j < arrivee.getLstLignes().size(); j++) {
				if (depart.getLstLignes().get(i).getNameLigne()
						.equals(arrivee.getLstLignes().get(j).getNameLigne())) {
					if (nbRelations == -1) {
						nbRelations = depart
								.getLstLignes()
								.get(i)
								.nbRelationsEntreDeuxStationsSurLaLigne(depart,
										arrivee);
						ligne = depart.getLstLignes().get(i);
					} else {
						if (nbRelations > depart
								.getLstLignes()
								.get(i)
								.nbRelationsEntreDeuxStationsSurLaLigne(depart,
										arrivee)) {
							nbRelations = depart
									.getLstLignes()
									.get(i)
									.nbRelationsEntreDeuxStationsSurLaLigne(
											depart, arrivee);
							ligne = depart.getLstLignes().get(i);
						}
					}
				}
			}
		}
		return ligne;
	}

	@Override
	public ArrayList<Relation> moinsDeChangement(Station depart, Station arrivee) {

		ArrayList<Relation> relations = new ArrayList<Relation>();
		if (depart.equals(arrivee)) {
			return relations;
		}

		Ligne ligne;
		// la station d'arrivée est sur la même ligne que celle de départ
		if ((ligne = isOnTheSameLineMin(depart, arrivee)) != null) {
			return ligne.relationsBetweenTwoStations(depart, arrivee);
		}

		else {

			int nbRelations = -1;
			int nbRelationsVoisines = -1;
			Station StationVoisine = null;

			ArrayList<Ligne> lignesStationDepart = depart.getLstLignes();
			for (int i = 0; i < lignesStationDepart.size(); i++) {
				ArrayList<Station> stationsLigneEnCours = lignesStationDepart
						.get(i).getListStation();
				for (int j = 0; j < stationsLigneEnCours.size(); j++) {

					if (stationsLigneEnCours.get(j).getLstLignes().size() > 1) {
						ArrayList<Ligne> correspondanceNew = new ArrayList<Ligne>();
						ArrayList<Ligne> lignesCorrespondanceStation = stationsLigneEnCours
								.get(j).getLstLignes();
						for (int k = 0; k < lignesCorrespondanceStation.size(); k++) {
							/*if (!lignesStationDepart
									.contains(lignesCorrespondanceStation
											.get(k)))*/
								correspondanceNew
										.add(lignesCorrespondanceStation.get(k));
						}
						if (correspondanceNew.size() > 0) {

							if (nbRelationsVoisines == -1) {
								nbRelationsVoisines = lignesStationDepart
										.get(i)
										.nbRelationsEntreDeuxStationsSurLaLigne(
												depart,
												stationsLigneEnCours.get(j));
								StationVoisine = stationsLigneEnCours.get(j);
							} else {
								if (nbRelationsVoisines > lignesStationDepart
										.get(i)
										.nbRelationsEntreDeuxStationsSurLaLigne(
												depart,
												stationsLigneEnCours.get(j))) {
									nbRelationsVoisines = lignesStationDepart
											.get(i)
											.nbRelationsEntreDeuxStationsSurLaLigne(
													depart,
													stationsLigneEnCours.get(j));
									StationVoisine = stationsLigneEnCours
											.get(j);
								}
							}
						}
					}

				}

				if (StationVoisine != null) {
					for (int m = 0; m < StationVoisine.getLstLignes().size(); m++) {
						for (int n = 0; n < StationVoisine.getLstLignes()
								.get(m).getListStation().size(); n++) {
							for (int p = 0; p < arrivee.getLstLignes().size(); p++) {
								if (StationVoisine
										.getLstLignes()
										.get(m)
										.getListStation()
										.get(n)
										.getLstLignes()
										.contains(arrivee.getLstLignes().get(p))
										&& (isOnTheSameLineMin(StationVoisine
												.getLstLignes().get(m)
												.getListStation().get(n),
												arrivee) != null)) {
									ArrayList<Relation> relationsTemp = moinsDeChangement(
											depart, StationVoisine);
									ArrayList<Relation> relationsToAdd = moinsDeChangement(
											StationVoisine, StationVoisine
													.getLstLignes().get(m)
													.getListStation().get(n));
									ArrayList<Relation> relationsToAdd2 = moinsDeChangement(
											StationVoisine.getLstLignes()
													.get(m).getListStation()
													.get(n), arrivee);

									if (relationsToAdd.size() > 0) {
										for (int l = 0; l < relationsToAdd
												.size(); l++) {
											relationsTemp.add(relationsToAdd
													.get(l));
										}
									}

									if (relationsToAdd2.size() > 0) {
										for (int l = 0; l < relationsToAdd2
												.size(); l++) {
											relationsTemp.add(relationsToAdd2
													.get(l));
										}
									}

									if (nbRelations == -1) {
										nbRelations = relationsTemp.size();
										relations = relationsTemp;
									} else {
										if (nbRelations > relationsTemp.size()) {
											nbRelations = relationsTemp.size();
											relations = relationsTemp;
										}
									}
								}
							}
						}
					}
				}
			}
			return relations;
		}
	}

}
