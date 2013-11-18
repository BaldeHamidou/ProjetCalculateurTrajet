package calculateur.implementations;

import calculateur.interfaces.IRelation;
import calculateur.interfaces.IStation;

public class Relation implements IRelation{

	private Station depart;
	private Station arrivee;
	private String direction;
	
	public Relation(Station depart, Station arrivee, String direction) {
		this.depart = depart;
		this.arrivee = arrivee;
		this.direction = direction;
	}
	
	@Override
	public IStation getStationDepart() {
		return this.depart;
	}

	@Override
	public IStation getStationArrivee() {
		return this.arrivee;
	}

	public String getDirection() {
		return this.direction;
	}

}
