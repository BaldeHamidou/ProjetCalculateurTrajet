package calculateur.implementations;

import java.util.ArrayList;
import java.util.List;

import calculateur.interfaces.IRelation;
import calculateur.interfaces.IStation;

public class Station implements IStation{

	private Station stationVoisine1;
	private Station stationVoisine2;
	private ArrayList<IRelation> lr;
	private String name;
	
	
	public Station(String name) {
		this.name = name;
		this.lr = new ArrayList<IRelation>();
	}
	
	public Station(String name, Station end, int weight) {
		this.name = name;
		this.lr.add(new Relation(this, end, weight));
	}
	
	public Station(String name, IRelation relation){
		this.name = name;
		this.lr.add(relation);
	}

	@Override
	public ArrayList<IRelation> getRelations() {
		return this.lr;
	}
	public void addRelation(Relation r){
		this.lr.add(r);
	}
	
	public String getName(){
		return this.name;
	}

}
