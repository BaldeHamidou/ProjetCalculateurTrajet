package calculateur.implementations.rer;

import java.util.ArrayList;

import calculateur.abstracts.Ligne;
import calculateur.abstracts.Station;
import calculateur.implementations.tramway.LigneTram;

public class LigneRer extends Ligne implements Comparable<LigneTram>{

	public LigneRer(String nameLigne) {
		super(nameLigne);
	}

	public LigneRer(String nameLigne, ArrayList<Station> listeStation,
			ArrayList<Station> terminus, ArrayList<Ligne> correspondances) {
		super(nameLigne, listeStation, terminus, correspondances);
	}

	@Override
	public int compareTo(LigneTram arg0) {
		if (arg0.getNameLigne().equals(getNameLigne()))
			return 0;
		else
			return -1;
	}
	
	public String toString() {
		return "(RER)"+getNameLigne();
	}

}
