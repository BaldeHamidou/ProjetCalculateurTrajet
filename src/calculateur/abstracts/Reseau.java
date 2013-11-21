package calculateur.abstracts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import calculateur.interfaces.IReseau;

public abstract class Reseau implements IReseau{

	private Map<Station, ArrayList<Relation>> grapheReseau;
	
	public Reseau(){
		this.grapheReseau = new HashMap<Station, ArrayList<Relation>>();
	}
	
	public Reseau(Map<Station, ArrayList<Relation>> grapheR){
		this.grapheReseau = grapheR;
	}
	
	public Map<Station, ArrayList<Relation>> getGrapheReseau() {
		return grapheReseau;
	}

	public void setGrapheReseau(Map<Station, ArrayList<Relation>> grapheReseau) {
		if(grapheReseau != null)
		this.grapheReseau = grapheReseau;
	}
	
	/*public void addMaillonStation(Station station, ArrayList<IRelation> relations){
		this.grapheReseau.put(station, relations);
	}*/
	
	public void addMaillonStation(Station station){
		if(station != null && !this.grapheReseau.containsValue(station))
		this.grapheReseau.put(station, station.getRelations());
	}
	
	public abstract void loadReseauFromCSV();

}
