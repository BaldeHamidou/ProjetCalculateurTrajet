package calculateur.implementations;

import java.util.ArrayList;

import calculateur.interfaces.ILigne;

public class Ligne implements ILigne {

	private String nameLigne;
	private ArrayList<Station> listeStation;

	public Ligne(String nameLigne) {
		this.nameLigne = nameLigne;
		this.listeStation = new ArrayList<Station>();
	}

	public Ligne(String nameLigne, ArrayList<Station> listeStation) {
		this.nameLigne = nameLigne;
		this.listeStation = listeStation;
	}

	public void addStation(Station station) {
		this.listeStation.add(station);
	}

	public ArrayList<Station> getListStation() {
		return this.listeStation;
	}

	@Override
	public void setNameLigne(String name) {
		this.nameLigne = name;
	}

	@Override
	public String getNameLigne() {
		return this.nameLigne;
	}

	public int nbRelationsEntreDeuxStationsSurLaLigne(Station station1,
			Station station2) {

		if (this.listeStation.contains(station1)
				&& this.listeStation.contains(station2)) {
			// Todo
			return 0;
		}

		return -1;
	}

}
