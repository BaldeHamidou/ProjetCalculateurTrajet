package calculateur.implementations;

import java.util.ArrayList;

import calculateur.interfaces.ILigne;
import calculateur.interfaces.IRelation;
import calculateur.interfaces.IStation;

public class Station implements IStation{

	private ArrayList<IStation> stationsVoisines;
	private ArrayList<ILigne> lstLignes;
	private ArrayList<IRelation> lstRelation;
	private String name;
	private boolean isTerminus;
	
	public Station(String name) {
		this.name = name;
		this.lstRelation = new ArrayList<IRelation>();
		this.isTerminus = false;
	}
	
	public Station(String name, boolean isterminus) {
		this.name = name;
		this.lstRelation = new ArrayList<IRelation>();
		this.isTerminus = isterminus;
	}
	
	public Station(String name, boolean isterminus, ArrayList<ILigne> lignes, ArrayList<IStation> stationsVois, ArrayList<IRelation> relations) {
		this.name = name;
		this.isTerminus = isterminus;
		this.lstLignes = lignes;
		this.stationsVoisines = stationsVois;
		this.lstRelation = relations;
	}
	
	public Station(String name, ArrayList<ILigne> lignes, ArrayList<IStation> stationsVois, ArrayList<IRelation> relations) {
		this.name = name;
		this.isTerminus = false;
		this.lstLignes = lignes;
		this.stationsVoisines = stationsVois;
		this.lstRelation = relations;
	}

	@Override
	public ArrayList<IRelation> getRelations() {
		return this.lstRelation;
	}
	
	public ArrayList<IStation> getStationsVoisines(){
		return this.stationsVoisines;
	}
	
	public ArrayList<ILigne> getLstLignes(){
		return this.lstLignes;
	}
	
	public String getName(){
		return this.name;
	}
	
	public boolean isTerminus(){
		return this.isTerminus;
	}
	
	public void setName(String nom){
		this.name = nom;
	}	
	
	public void setStationsVoisines(ArrayList<IStation> stationsVoisines) {
		this.stationsVoisines = stationsVoisines;
	}

	public void setLstLignes(ArrayList<ILigne> lstLignes) {
		this.lstLignes = lstLignes;
	}

	public void setLstRelation(ArrayList<IRelation> lstRelation) {
		this.lstRelation = lstRelation;
	}

	public void setIsTerminus(boolean isTerminus) {
		this.isTerminus = isTerminus;
	}

	public void addRelation(Relation relation){
		this.lstRelation.add(relation);
	}
	
	public void addStationVoisine(Station stationVoisine){
		this.stationsVoisines.add(stationVoisine);
	}
	
	public void addLigne(Ligne ligne){
		this.lstLignes.add(ligne);
	}

	@Override
	public String toString() {
		return name;
	}
}
