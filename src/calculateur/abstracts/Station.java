package calculateur.abstracts;

import java.util.ArrayList;

import calculateur.implementations.RelationMetro;
import calculateur.interfaces.IStation;

public abstract class Station implements IStation, Comparable<Station> {

	private ArrayList<Station> stationsVoisines;
	private ArrayList<Ligne> lstLignes;
	private ArrayList<Relation> lstRelation;
	private String name;

	public Station(String name) {
		this.name = name;
		this.lstRelation = new ArrayList<Relation>();
		this.lstLignes = new ArrayList<Ligne>();
		this.stationsVoisines = new ArrayList<Station>();
	}

	public Station(String name, ArrayList<Ligne> lignes,
			ArrayList<Station> stationsVois, ArrayList<Relation> relations) {
		this.name = name;
		this.lstLignes = lignes;
		this.stationsVoisines = stationsVois;
		this.lstRelation = relations;
	}

	@Override
	public ArrayList<Relation> getRelations() {
		return this.lstRelation;
	}

	public ArrayList<Station> getStationsVoisines() {
		return this.stationsVoisines;
	}

	public ArrayList<Ligne> getLstLignes() {
		return this.lstLignes;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String nom) {
		if (nom != null && nom.trim() != "")
			this.name = nom;
	}

	/*
	 * public void setStationsVoisines(ArrayList<IStation> stationsVoisines) {
	 * this.stationsVoisines = stationsVoisines; }
	 * 
	 * public void setLstLignes(ArrayList<ILigne> lstLignes) { this.lstLignes =
	 * lstLignes; }
	 * 
	 * public void setLstRelation(ArrayList<IRelation> lstRelation) {
	 * this.lstRelation = lstRelation; }
	 */

	public void addRelation(RelationMetro relation) {
		if (relation != null && !this.lstRelation.contains(relation))
			this.lstRelation.add(relation);
	}

	public void addStationVoisine(Station stationVoisine) {
		if (stationVoisine != null
				&& !this.stationsVoisines.contains(stationVoisine))
			this.stationsVoisines.add(stationVoisine);
	}

	public void addLigne(Ligne ligne) {
		if (ligne != null && !this.lstLignes.contains(ligne))
			this.lstLignes.add(ligne);
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public int compareTo(Station o) {
		if (o.getName().equals(name))
			return 0;
		else
			return -1;
	}
}
