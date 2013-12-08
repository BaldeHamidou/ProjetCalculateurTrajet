package calculateur.implementations.tramway;

import calculateur.abstracts.Modalite;
import calculateur.abstracts.Reseau;

public class ModaliteTram extends Modalite{

	public ModaliteTram(){
		setReseau(new ReseauTram());
	}
	
	public ModaliteTram(Reseau reseau) {
		super(reseau);
	}
}
