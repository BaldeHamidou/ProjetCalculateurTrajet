package calculateur.implementations.tramway;

import java.util.ArrayList;

import calculateur.abstracts.Ligne;
import calculateur.abstracts.Station;

public class LigneTram extends Ligne implements Comparable<LigneTram> {

	public LigneTram(String nameLigne) {
		super(nameLigne);
	}

	public LigneTram(String nameLigne, ArrayList<Station> listeStation,
			ArrayList<Station> terminus, ArrayList<Ligne> correspondances) {
		super(nameLigne, listeStation, terminus, correspondances);
	}
	
	public String toString() {
		return "(T)"+getNameLigne();//+" Terminus:"+getListTerminus()+" Stations:"+getListStation();
	}
	@Override
	public int compareTo(LigneTram arg0) {
		if (arg0.getNameLigne().equals(getNameLigne()))
			return 0;
		else
			return -1;
	}

}
