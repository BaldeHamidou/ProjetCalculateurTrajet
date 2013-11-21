package calculateur.interfaces;

import java.util.ArrayList;

import calculateur.abstracts.Relation;

public interface IStation {
	public ArrayList<Relation> getRelations();
	public String getName();
}
