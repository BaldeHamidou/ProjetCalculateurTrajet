package calculateur.implementations;

import java.util.ArrayList;

import calculateur.abstracts.Ligne;
import calculateur.abstracts.Station;

public class LigneMetro extends Ligne {

	public LigneMetro(String nameLigne) {
		super(nameLigne);
	}

	public LigneMetro(String nameLigne, ArrayList<Station> listeStation, ArrayList<Station> listeTerminus) {
		super(nameLigne, listeStation, listeTerminus);
	}

}
