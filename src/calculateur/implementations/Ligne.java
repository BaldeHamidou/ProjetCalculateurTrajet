package calculateur.implementations;

import java.util.ArrayList;

import calculateur.interfaces.ILigne;

public class Ligne<T> implements ILigne<T>{

	private T nameLigne;
	private ArrayList<Station> listeStation;
	
	
	public Ligne(T nameLigne) {
		this.nameLigne = nameLigne;
		this.listeStation = new ArrayList<Station>();
	}


	public Ligne(T nameLigne, ArrayList<Station> listeStation) {
		this.nameLigne = nameLigne;
		this.listeStation = listeStation;
	}


	public void addStation(Station station){
		this.listeStation.add(station);
	}
	
	public ArrayList<Station> getListStation(){
	 return this.listeStation;	
	}
	
	@Override
	public void setNameLigne(T name) {
		this.nameLigne = name;
	}

	@Override
	public T getNameLigne() {
		return this.nameLigne;
	}
    
	
}
