package calculateur.implementations.rer;

import calculateur.abstracts.Modalite;
import calculateur.abstracts.Reseau;

public class ModaliteRer extends Modalite {

	public ModaliteRer(){
		setReseau(new ReseauRer());
	}
	
	public ModaliteRer(Reseau reseau) {
		super(reseau);
	}
}
