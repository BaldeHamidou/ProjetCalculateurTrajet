package calculateur.implementations.rer;

import java.util.ArrayList;

import calculateur.abstracts.Ligne;
import calculateur.abstracts.Relation;
import calculateur.abstracts.Station;

public class StationRer extends Station implements Comparable<StationRer>{

	public StationRer(String name, ArrayList<Ligne> lignes,
			ArrayList<Station> stationsVois, ArrayList<Relation> relations) {
		super(name, lignes, stationsVois, relations);
	}

	public StationRer(String name) {
		super(name);
	}

	@Override
	public int compareTo(StationRer o) {
		if (o.getName().equals(getName()))
			return 0;
		else
			return -1;
	}

}
