package calculateur.interfaces;

import java.util.ArrayList;

import calculateur.implementations.Station;

public interface ILigne<T> {
	public void setNameLigne(T name);
	public T getNameLigne();
	public void addStation(Station station);
	public ArrayList<Station> getListStation();
}
