package calculateur.implementations.tramway;

import calculateur.abstracts.Ligne;
import calculateur.abstracts.Relation;
import calculateur.abstracts.Station;

public class RelationTram extends Relation{

	public RelationTram(Station depart, Station arrivee, String direction,
			Ligne ligne) {
		super(depart, arrivee, direction, ligne);
	}

	public String toString(){
		return getStationDepart()+" "+getStationArrivee()+" direction:"+getDirection()+" sur la ligne "+getLigne();
	}
}
