package calculateur.implementations;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import calculateur.interfaces.IRelation;
import calculateur.interfaces.IReseau;
import calculateur.interfaces.IStation;

public class MetroReseau implements IReseau{

	private Map<IStation, ArrayList<IRelation>> grapheReseau;
	
	public MetroReseau(){
		this.grapheReseau = new HashMap<IStation, ArrayList<IRelation>>();
	}
	
	public MetroReseau(Map<IStation, ArrayList<IRelation>> grapheR){
		this.grapheReseau = grapheR;
	}
	
	public Map<IStation, ArrayList<IRelation>> getGrapheReseau() {
		return grapheReseau;
	}

	public void setGrapheReseau(Map<IStation, ArrayList<IRelation>> grapheReseau) {
		this.grapheReseau = grapheReseau;
	}
	
	public void addMaillonStation(Station station, ArrayList<IRelation> relations){
		this.grapheReseau.put(station, relations);
	}
	
	@Override
	public void loadReseauFromCSV() {
		
		try {
			// création liste des lignes (à vide : juste avec leur nom)
			ArrayList<String> lstNomsLignes = CsvFileHelper.readFileLstLignes();
			Map<String, Ligne> lstLigne = new HashMap<String, Ligne>();
			
			for(int i = 0; i<lstNomsLignes.size(); i++){
				lstLigne.put(lstNomsLignes.get(i), new Ligne(lstNomsLignes.get(i)));
			}
			System.out.println(lstLigne);
			
			// création liste des stations (à vide : juste avec leur nom)
			ArrayList<String[]> lstDonneesStations = CsvFileHelper.readFileLstStations();
			Map<String, Station> lstStation = new HashMap<String, Station>();
			
			for(int i = 0; i<lstDonneesStations.size(); i++){
				lstStation.put(lstDonneesStations.get(i)[0], new Station(lstDonneesStations.get(i)[1]));
			}
			System.out.println(lstStation);
			
			
			// la suite arrive :)
			
		} catch (IOException e) {
			System.out.println("Problème d'ouverture fichier CSV");
		}
		
	}
	
	//public final static char SEPARATOR = ';';	
	
	/*private File file;
	private ArrayList<String> data;
	private ArrayList<String[] > lMetro;*/

/*	public MetroReseau(File listeMetro) throws IOException {
		
		this.data = new ArrayList<String>();
		this.lMetro = new ArrayList<String[]>();
		this.file = listeMetro;

        // Init
		loadReseauFromCSV();

	}*/
	
  /*
	@Override
	public void loadReseauFromCSV() throws IOException {
		
		this.data = CsvFileHelper.readFile(file);

		lMetro = new ArrayList<String[] >(this.data.size());
        String sep = new Character(SEPARATOR).toString();
        for (String line : this.data) {
            String[] oneData = line.split(sep);
            lMetro.add(oneData);
        }

        // On peut repeter cette procedure (au dessus) autant que l'on veut pour d'autre CSV
        
        
	}

	public ArrayList<String[]> getlMetro() {
		return lMetro;
	}

	public void setlMetro(ArrayList<String[]> lMetro) {
		this.lMetro = lMetro;
	}
	*/


}
