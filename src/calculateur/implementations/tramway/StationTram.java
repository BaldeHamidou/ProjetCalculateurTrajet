package calculateur.implementations.tramway;

import java.util.ArrayList;

import calculateur.abstracts.Ligne;
import calculateur.abstracts.Relation;
import calculateur.abstracts.Station;

public class StationTram extends Station implements Comparable<StationTram>{

	public StationTram(String name) {
		super(name);
	}

	public StationTram(String name, ArrayList<Ligne> lignes,
			ArrayList<Station> stationsVois, ArrayList<Relation> relations) {
		super(name, lignes, stationsVois, relations);
	}

	public String toString() {
		return getName()/*+" Ligne(s):"+getLstLignes()*/;
	}
	
	@Override
	public int compareTo(StationTram o) {
		if (o.getName().equals(getName()))
			return 0;
		else
			return -1;
	}

}
