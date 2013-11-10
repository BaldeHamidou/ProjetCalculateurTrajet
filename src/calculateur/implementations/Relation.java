package calculateur.implementations;

import calculateur.interfaces.IRelation;
import calculateur.interfaces.IStation;

public class Relation implements IRelation{

	private Station start;
	private Station end;
	private int distance;
	
	public Relation(Station start, Station end, int distance) {
		this.start = start;
		this.end = end;
		this.distance = distance;
	}
	
	@Override
	public IStation getStartStation() {
		return this.start;
	}

	@Override
	public IStation getEndStation() {
		return this.end;
	}

	@Override
	public int getDistance() {
		return this.distance;
	}

}
