package calculateur.abstracts;

import java.util.ArrayList;

import calculateur.interfaces.ILigne;

public abstract class Ligne implements ILigne {

	private String nameLigne;
	private ArrayList<Station> listeStation;
	private ArrayList<Station> listeTerminus;

	public Ligne(String nameLigne) {
		this.nameLigne = nameLigne;
		this.listeStation = new ArrayList<Station>();
		this.listeTerminus = new ArrayList<Station>();
	}

	public Ligne(String nameLigne, ArrayList<Station> listeStation, ArrayList<Station> terminus) {
		this.nameLigne = nameLigne;
		this.listeStation = listeStation;
		this.listeTerminus = terminus;
	}

	public void addStation(Station station) {
		if(station != null && !this.listeStation.contains(station))
		this.listeStation.add(station);
	}

	public void addTerminus(Station station) {
		if(station != null && !this.listeTerminus.contains(station))
		this.listeTerminus.add(station);
	}
	
	public ArrayList<Station> getListStation() {
		return this.listeStation;
	}

	public ArrayList<Station> getListTerminus() {
		return listeTerminus;
	}

	@Override
	public void setNameLigne(String name) {
		if(name != null && name.trim() != "")
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
