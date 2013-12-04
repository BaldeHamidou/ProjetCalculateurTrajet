package calculateur.interfaces;

import java.util.ArrayList;

import calculateur.abstracts.Relation;
import calculateur.abstracts.Station;

public interface IAlgoCalcul {

	public ArrayList<Relation> plusRapideChemin(Station depart, Station arrivee);
	public ArrayList<Relation> moinsDeChangement(Station depart, Station arrivee);
}
