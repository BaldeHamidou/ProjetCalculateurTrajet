package calculateur.implementations;

import java.util.ArrayList;

import calculateur.abstracts.Ligne;
import calculateur.abstracts.Relation;
import calculateur.abstracts.Station;

public class StationMetro extends Station{

	public StationMetro(String name) {
		super(name);
	}

	public StationMetro(String name, ArrayList<Ligne> lignes,
			ArrayList<Station> stationsVois, ArrayList<Relation> relations) {
		super(name, lignes, stationsVois, relations);
	}
	
}
