package calculateur.implementations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Tools.CsvFileHelper;
import calculateur.abstracts.Ligne;
import calculateur.abstracts.Relation;
import calculateur.abstracts.Reseau;
import calculateur.abstracts.Station;

public class ReseauMetro extends Reseau{
	
	public ReseauMetro(){
		super();
	}
	
	public ReseauMetro(Map<Station, ArrayList<Relation>> grapheR){
		super(grapheR);
	}
	
	public void loadReseauFromCSV() {
		
		try {
			ArrayList<String[]> lstDonneesLignes = CsvFileHelper.readFileLstLignes();
			ArrayList<String[]> lstDonneesStations = CsvFileHelper.readFileLstStations();
			
			// création de la Map des lignes (à vide : juste avec leur nom)
			Map<String, Ligne> mapLigne = new HashMap<String, Ligne>();
			
			for(int i = 0; i<lstDonneesLignes.size(); i++){
				mapLigne.put(lstDonneesLignes.get(i)[0], new LigneMetro(lstDonneesLignes.get(i)[0]));
			}
			System.out.println(mapLigne);
			
			// création de la Map des stations (à vide : juste avec leur nom)
			Map<String, Station> mapStation = new HashMap<String, Station>();
			
			for(int i = 0; i<lstDonneesStations.size(); i++){
				mapStation.put(lstDonneesStations.get(i)[0], new StationMetro(lstDonneesStations.get(i)[1]));
			}
			System.out.println(mapStation);
			
			// ajout des terminus et stations de chaque Ligne
			for(int i = 0; i<lstDonneesLignes.size(); i++){
				String idLigne = lstDonneesLignes.get(i)[0];
				
				// ajout des stations terminus pour chaque ligne
				for(int j=1; j<lstDonneesLignes.get(i).length; j++){
					mapLigne.get(idLigne).addTerminus(mapStation.get(lstDonneesLignes.get(i)[j]));
				}
				
				// ajout des stations d'une ligne
			}
			
			
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
