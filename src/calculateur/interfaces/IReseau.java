package calculateur.interfaces;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import calculateur.abstracts.Relation;
import calculateur.abstracts.Station;

public interface IReseau {
 public void loadReseauFromCSV() throws FileNotFoundException, IOException;
 public Map<Station, ArrayList<Relation>> getGrapheReseau();
 public Map<String, Station> getMapStations();
}
