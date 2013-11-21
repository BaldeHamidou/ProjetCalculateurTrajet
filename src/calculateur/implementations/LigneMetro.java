package calculateur.implementations;

import java.util.ArrayList;

import calculateur.abstracts.Ligne;
import calculateur.abstracts.Station;

public class LigneMetro extends Ligne implements Comparable<LigneMetro> {

	public LigneMetro(String nameLigne) {
		super(nameLigne);
	}

	public LigneMetro(String nameLigne, ArrayList<Station> listeStation, ArrayList<Station> listeTerminus) {
		super(nameLigne, listeStation, listeTerminus);
	}

	@Override
	public String toString() {
		return "(M)"+getNameLigne();//+" Terminus:"+getListTerminus()+" Stations:"+getListStation();
	}

	@Override
	public int compareTo(LigneMetro o) {
		if(o.getNameLigne().equals(getNameLigne()))
		return 0;
		else
			return -1;
	}
}
