package calculateur.abstracts;

import calculateur.interfaces.IModalite;
import calculateur.interfaces.IReseau;

public abstract class Modalite implements IModalite{

	private IReseau reseau;
	// appliquer Dijkstra sur la modalité ? à quoi sert la modalité ?
	
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
