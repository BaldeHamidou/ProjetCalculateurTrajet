package calculateur.interfaces;

import java.util.ArrayList;

import calculateur.abstracts.Relation;
import calculateur.abstracts.Station;

public interface IAlgoCalcul {

	public ArrayList<Relation> plusRapideChemin(Station depart, Station arrivee);
	public IReseau moinsDeChangement(String depart, String arrivee);
}
