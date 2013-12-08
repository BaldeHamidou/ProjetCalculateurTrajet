package calculateur.abstracts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import calculateur.interfaces.IReseau;
/**
 * 
 * Classe abstraite Reseau
 *
 */
public abstract class Reseau implements IReseau{

	private static Map<Station, ArrayList<Relation>> grapheReseau;
	
	public Reseau(){
		Reseau.grapheReseau = new HashMap<Station, ArrayList<Relation>>();
	}
	
	public Reseau(Map<Station, ArrayList<Relation>> grapheR){
		Reseau.grapheReseau = grapheR;
	}
	
	public Map<Station, ArrayList<Relation>> getGrapheReseau() {
		return grapheReseau;
	}

	public void setGrapheReseau(Map<Station, ArrayList<Relation>> grapheReseau) {
		if(grapheReseau != null)
		Reseau.grapheReseau = grapheReseau;
	}
	
	/**
	 * Ajoute une station au réseau
	 * @param station
	 */
	public void addMaillonStation(Station station){
		if(station != null && !Reseau.grapheReseau.containsValue(station))
		Reseau.grapheReseau.put(station, station.getRelations());
	}
	
	/**
	 * Retourne la relation associé à une station de départ, d'arrivée et la ligne
	 * @param start
	 * @param end
	 * @param ligne
	 * @return
	 */
	public static Relation getRelationByStationStartAndEnd(Station start, Station end, Ligne ligne){
		
		ArrayList<Relation> relations = grapheReseau.get(start);
		for(int i=0; i<relations.size(); i++){
		    if(relations.get(i).getStationArrivee().equals(end) && relations.get(i).getLigne().equals(ligne))
		    	return relations.get(i);
		}
		return null;
	}

	/**
	 * Permet de charger le réseau à partir des fichiers CSV
	 */
	public abstract void loadReseauFromCSV();

}
