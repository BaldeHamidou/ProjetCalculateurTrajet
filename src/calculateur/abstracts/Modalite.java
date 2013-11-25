package calculateur.abstracts;

import calculateur.interfaces.IModalite;
import calculateur.interfaces.IReseau;

public abstract class Modalite implements IModalite{

	private IReseau reseau;
	// appliquer Dijkstra sur la modalit� ? � quoi sert la modalit� ?
	
	public Modalite(){} 
	public Modalite(IReseau reseau){
		this.reseau = reseau;
	}
	
	@Override
	public void setReseau(Reseau reseau) {
		this.reseau = reseau;
	}
	
	public IReseau getReseau(){
		return this.reseau;
	}

}
