package calculateur.interfaces;

import calculateur.abstracts.Station;

public interface IAlgoCalcul {

	public IReseau plusRapideChemin(Station depart, Station arrivee);
	public IReseau moinsDeChangement(String depart, String arrivee);
}
