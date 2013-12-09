package calculateur.implementations.rer;

import calculateur.abstracts.Ligne;
import calculateur.abstracts.Relation;
import calculateur.abstracts.Station;

public class RelationRer extends Relation{

	public RelationRer(Station depart, Station arrivee, String direction,
			Ligne ligne) {
		super(depart, arrivee, direction, ligne);
	}

	public String toString(){
		return getStationDepart()+" "+getStationArrivee()+" direction:"+getDirection()+" sur la ligne "+getLigne();
	}
}
