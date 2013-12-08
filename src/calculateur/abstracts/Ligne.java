package calculateur.abstracts;

import java.util.ArrayList;

import calculateur.interfaces.ILigne;
/**
 * 
 * Classe abstraite d'une ligne
 *
 */
public abstract class Ligne implements ILigne {

	private String nameLigne;
	private ArrayList<Station> listeStation;
	private ArrayList<Station> listeTerminus;
	private ArrayList<Ligne> listeCorrespondance;

	/**
	 * Créé une ligne avec son nom
	 * @param nameLigne
	 */
	public Ligne(String nameLigne) {
		this.nameLigne = nameLigne;
		this.listeStation = new ArrayList<Station>();
		this.listeTerminus = new ArrayList<Station>();
		this.listeCorrespondance = new ArrayList<Ligne>();
	}

	/**
	 * Créé une ligne avec son nom, ses différentes listes: stations, terminus et lignes de correspondances
	 * @param nameLigne
	 * @param listeStation
	 * @param terminus
	 * @param correspondances
	 */
	public Ligne(String nameLigne, ArrayList<Station> listeStation,
			ArrayList<Station> terminus, ArrayList<Ligne> correspondances) {
		this.nameLigne = nameLigne;
		this.listeStation = listeStation;
		this.listeTerminus = terminus;
		this.listeCorrespondance = correspondances;
	}

	/**
	 * Ajoute une station à la ligne
	 * @param station 
	 */
	public void addStation(Station station) {
		if (station != null && !this.listeStation.contains(station))
			this.listeStation.add(station);
	}

	/**
	 * Ajoute un terminus à la ligne
	 * @param station
	 */
	public void addTerminus(Station station) {
		if (station != null && !this.listeTerminus.contains(station))
			this.listeTerminus.add(station);
	}
	/**
	 * Ajoute une ligne de correspondance
	 * @param ligneDeCorrespondance
	 */
	public void addCorrespondance(Ligne ligneDeCorrespondance) {
		if (ligneDeCorrespondance != null
				&& !this.listeCorrespondance.contains(ligneDeCorrespondance)
				&& !this.equals(ligneDeCorrespondance))
			this.listeCorrespondance.add(ligneDeCorrespondance);
	}
	/**
	 * Retourne la liste des stations de la ligne
	 * @return 
	 */
	public ArrayList<Station> getListStation() {
		return this.listeStation;
	}

	/**
	 * Retourne la liste des terminus de la ligne
	 * @return
	 */
	public ArrayList<Station> getListTerminus() {
		return listeTerminus;
	}

	/**
	 * Retourne la liste des lignes de correspondances
	 * @return
	 */
	public ArrayList<Ligne> getListCorrespondance() {
		return this.listeCorrespondance;
	}

	/**
	 * Modifie le nom de la ligne
	 * @param name
	 */
	@Override
	public void setNameLigne(String name) {
		if (name != null && name.trim() != "")
			this.nameLigne = name;
	}

	/**
	 * Retourne le nom de la ligne
	 * @return
	 */
	@Override
	public String getNameLigne() {
		return this.nameLigne;
	}

	/**
	 * Retourne vrai si la station reçu en paramètre est un terminus
	 * @param station
	 * @return
	 */
	public boolean isTerminus(Station station) {
		return this.listeTerminus.contains(station);
	}

	/**
	 * Retourne la liste des relations entre deux stations si c'est deux stations sont sur la même ligne
	 * @param station1
	 * @param station2
	 * @return
	 */
	public ArrayList<Relation> relationsBetweenTwoStations(Station station1,
			Station station2) {

		ArrayList<Relation> relations = new ArrayList<Relation>();

		if (this.listeStation.contains(station1)
				&& this.listeStation.contains(station2)) {
			if (station1.equals(station2))
				return relations;
			else {
				for (int i = 0; i < station1.getStationsVoisines().size(); i++) {
					if (station1.getStationsVoisines().get(i).getLstLignes()
							.contains(this)) {
						if (station1.getStationsVoisines().get(i)
								.equals(station2)) {
							relations.add(Reseau
									.getRelationByStationStartAndEnd(station1,
											station2, this));
							return relations;
						} else {
							if (isTheGoodStationVoisineToSearch(station1,
									station1.getStationsVoisines().get(i),
									station2)) {
								return getNextRelations(station1, station1
										.getStationsVoisines().get(i), station2);
							}
						}
					}
				}
			}
		}
		return relations;
	}

	/**
	 * Fonction récursive pour récupérer les relations jusqu'à la station à trouver 
	 * @param stationToIgnore
	 * @param stationNext
	 * @param stationTofind
	 * @return
	 */
	private ArrayList<Relation> getNextRelations(Station stationToIgnore,
			Station stationNext, Station stationTofind) {

		ArrayList<Relation> relations = new ArrayList<Relation>();
		if (stationNext.equals(stationTofind)) {
			relations.add(Reseau.getRelationByStationStartAndEnd(
					stationToIgnore, stationNext, this));
			return relations;
		} else {
			ArrayList<Station> stationsVoisine = new ArrayList<Station>();
			for (int i = 0; i < stationNext.getStationsVoisines().size(); i++) {
				if (stationNext.getStationsVoisines().get(i).getLstLignes()
						.contains(this)
						&& !stationNext.getStationsVoisines().get(i)
								.equals(stationToIgnore)) {
					stationsVoisine.add(stationNext.getStationsVoisines()
							.get(i));
				}
			}
			if (stationsVoisine.size() == 1) {
				relations.add(Reseau.getRelationByStationStartAndEnd(
						stationToIgnore, stationNext, this));
				ArrayList<Relation> relationsToAdd = getNextRelations(
						stationNext, stationsVoisine.get(0), stationTofind);
				for (int j = 0; j < relationsToAdd.size(); j++) {
					relations.add(relationsToAdd.get(j));
				}
				return relations;
			} else {
				for (int j = 0; j < stationsVoisine.size(); j++) {
					if (isTheGoodStationVoisineToSearch(stationNext,
							stationsVoisine.get(j), stationTofind)) {
						relations.add(Reseau.getRelationByStationStartAndEnd(
								stationToIgnore, stationNext, this));
						ArrayList<Relation> relationsToAdd = getNextRelations(
								stationNext, stationsVoisine.get(j),
								stationTofind);
						for (int k = 0; k < relationsToAdd.size(); k++) {
							relations.add(relationsToAdd.get(k));
						}
						return relations;
					}
				}
			}
			return relations;
		}
	}
	 /**
	  * Retourne le nombre de relations entre deux stations sur une même ligne
	  * @param station1
	  * @param station2
	  * @return
	  */
	public int nbRelationsEntreDeuxStationsSurLaLigne(Station station1,
			Station station2) {

		if (this.listeStation.contains(station1)
				&& this.listeStation.contains(station2)) {
			if (station1.equals(station2))
				return 0;
			else {
				if (isTerminus(station1) && isTerminus(station2))
					return this.listeStation.size() - 1;
				else {
					for (int i = 0; i < station1.getStationsVoisines().size(); i++) {
						if (station1.getStationsVoisines().get(i)
								.getLstLignes().contains(this)) {
							if (station1.getStationsVoisines().get(i)
									.equals(station2))
								return 1;
							else {
								if (isTheGoodStationVoisineToSearch(station1,
										station1.getStationsVoisines().get(i),
										station2)) {
									return countRelations(station1, station1
											.getStationsVoisines().get(i),
											station2);
								}
							}
						}
					}
				}
			}
		}
		return -1;
	}

	/**
	 * Fonction récursive pour compter les relations
	 * @param stationToIgnore
	 * @param stationNext
	 * @param stationTofind
	 * @return
	 */
	private int countRelations(Station stationToIgnore, Station stationNext,
			Station stationTofind) {

		if (stationNext.equals(stationTofind))
			return 1;
		else {
			ArrayList<Station> stationsVoisine = new ArrayList<Station>();
			for (int i = 0; i < stationNext.getStationsVoisines().size(); i++) {
				if (stationNext.getStationsVoisines().get(i).getLstLignes()
						.contains(this)
						&& !stationNext.getStationsVoisines().get(i)
								.equals(stationToIgnore)) {
					stationsVoisine.add(stationNext.getStationsVoisines()
							.get(i));
				}
			}
			if (stationsVoisine.size() == 1)
				return 1 + countRelations(stationNext, stationsVoisine.get(0),
						stationTofind);
			else {
				for (int j = 0; j < stationsVoisine.size(); j++) {
					if (isTheGoodStationVoisineToSearch(stationNext,
							stationsVoisine.get(j), stationTofind))
						return 1 + countRelations(stationNext,
								stationsVoisine.get(j), stationTofind);
				}
			}
			return 0;
		}
	}

	/**
	 * Retourne vrai si la station à trouver se trouve bien dans la direction de stationNext 
	 * en partant de stationToIgnore
	 * @param stationToIgnore
	 * @param stationNext
	 * @param stationTofind
	 * @return
	 */
	private boolean isTheGoodStationVoisineToSearch(Station stationToIgnore,
			Station stationNext, Station stationTofind) {

		if (isTerminus(stationNext) && !stationNext.equals(stationTofind))
			return false;
		if (stationNext.equals(stationTofind)) {
			return true;
		} else {
			ArrayList<Station> stationsVoisine = new ArrayList<Station>();
			for (int i = 0; i < stationNext.getStationsVoisines().size(); i++) {
				if (stationNext.getStationsVoisines().get(i).getLstLignes()
						.contains(this)
						&& !stationNext.getStationsVoisines().get(i)
								.equals(stationToIgnore)) {
					stationsVoisine.add(stationNext.getStationsVoisines()
							.get(i));
				}
			}
			if (stationsVoisine.size() == 0) {
				return false;
			} else {
				if (stationsVoisine.size() == 1) {
					return isTheGoodStationVoisineToSearch(stationNext,
							stationsVoisine.get(0), stationTofind);
				} else {
					for (int j = 0; j < stationsVoisine.size(); j++) {
						if (isTheGoodStationVoisineToSearch(stationNext,
								stationsVoisine.get(j), stationTofind))
							return (isTheGoodStationVoisineToSearch(
									stationNext, stationsVoisine.get(j),
									stationTofind));
					}
				}
				return false;
			}
		}
	}

}
