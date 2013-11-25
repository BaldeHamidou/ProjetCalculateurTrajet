package calculateur.implementations;

import calculateur.abstracts.Relation;
import calculateur.abstracts.Station;

public class RelationMetro extends Relation{

	public RelationMetro(Station depart, Station arrivee, String direction) {
		super(depart, arrivee, direction);
	}

	public String toString(){
		return getStationDepart()+" "+getStationArrivee()+" direction:"+getDirection();
	}
}
