package calculateur.abstracts;

import calculateur.interfaces.IRelation;

/**
 * 
 * Classe abstraite pour les relations
 *
 */
public abstract class Relation implements IRelation, Comparable<Relation>{

	private Station depart;
	private Station arrivee;
	private String direction;
	private Ligne ligne;
	
	public Relation(Station depart, Station arrivee, String direction, Ligne ligne) {
		this.depart = depart;
		this.arrivee = arrivee;
		this.direction = direction;
		this.ligne = ligne;
	}
	
	@Override
	public Station getStationDepart() {
		return this.depart;
	}

	@Override
	public Station getStationArrivee() {
		return this.arrivee;
	}

	public String getDirection() {
		return this.direction;
	}

	public Ligne getLigne() {
		return ligne;
	}

	public void setLigne(Ligne ligne) {
		this.ligne = ligne;
	}

	public void setDirection(String direction){
		if(direction != null && direction.trim() != "")
		this.direction = direction;
	}

	public void setDepart(Station depart) {
		if(depart != null)
		this.depart = depart;
	}

	public void setArrivee(Station arrivee) {
		if(arrivee != null)
		this.arrivee = arrivee;
	}

	@Override
	public int compareTo(Relation arg0) {
		if(arg0.getStationArrivee().equals(arrivee) && arg0.getStationDepart().equals(depart) && arg0.getDirection().equals(direction))
			return 0;
		else
			return -1;
	}
	
	
}
