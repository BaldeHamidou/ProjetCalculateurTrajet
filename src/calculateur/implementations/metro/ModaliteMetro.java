package calculateur.implementations.metro;

import calculateur.abstracts.Modalite;
import calculateur.abstracts.Reseau;

public class ModaliteMetro extends Modalite{

	public ModaliteMetro(){
		setReseau(new ReseauMetro());
	}
	
	public ModaliteMetro(Reseau reseau) {
		super(reseau);
	}

}
