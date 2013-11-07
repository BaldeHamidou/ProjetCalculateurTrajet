package calculateur.implementations;

import calculateur.interfaces.IModalite;
import calculateur.interfaces.IReseau;

public class MetroModalite implements IModalite{

	private IReseau reseauMetro;
	
	public MetroModalite(IReseau reseau){
		this.reseauMetro = reseau;
	}
	
	@Override
	public void setReseau(IReseau reseau) {
		this.reseauMetro = reseau;
	}
	
	public IReseau getReseau(){
		return this.reseauMetro;
	}

}
