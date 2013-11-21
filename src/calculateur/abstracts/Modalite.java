package calculateur.abstracts;

import calculateur.interfaces.IModalite;
import calculateur.interfaces.IReseau;

public abstract class Modalite implements IModalite{

	private IReseau reseauMetro;
	// appliquer Dijkstra sur la modalit� ? � quoi sert la modalit� ?
	
	public Modalite(IReseau reseau){
		this.reseauMetro = reseau;
	}
	
	@Override
	public void setReseau(Reseau reseau) {
		this.reseauMetro = reseau;
	}
	
	public IReseau getReseau(){
		return this.reseauMetro;
	}

}
