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

public class ReseauMetro extends Reseau {
	
	private Map<String, Station> mapStations;
	private Map<String, Ligne> mapLignes;
	
	public ReseauMetro() {
		super();
		mapStations = new HashMap<String, Station>();
		mapLignes = new HashMap<String, Ligne>();
		loadReseauFromCSV();
	}

	public ReseauMetro(Map<Station, ArrayList<Relation>> grapheR, Map<String, Station> stations, Map<String, Ligne> lignes) {
		super(grapheR);
		mapLignes = lignes;
		mapStations = stations;
	}

	public void loadReseauFromCSV() {

		try {
			ArrayList<String[]> lstDonneesLignes = CsvFileHelper
					.readFileLstLignes();
			ArrayList<String[]> lstDonneesStations = CsvFileHelper
					.readFileLstStations();

			 //------------------------------- traitement Stations begin ----------------------------------------//
			// ajout des stations à la map(à vide : juste avec leur nom)
			for (int i = 0; i < lstDonneesStations.size(); i++) {
				mapStations.put(lstDonneesStations.get(i)[0], new StationMetro(
						lstDonneesStations.get(i)[1]));
			}
          //-------------------------------traitement Stations end ----------------------------------------//
		  
		 //-------------------------------traitement Lignes begin ----------------------------------------//
			// ajout des lignes à la map (à vide : juste avec leur nom)
			for (int i = 0; i < lstDonneesLignes.size(); i++) {
				mapLignes.put(lstDonneesLignes.get(i)[0], new LigneMetro(
						lstDonneesLignes.get(i)[0]));
			}

			/* ajout des terminus, stations et ligne de correspondance de chaque Ligne */
			for (int i = 0; i < lstDonneesLignes.size(); i++) {
				String idLigne = lstDonneesLignes.get(i)[0];

				// ajout des stations terminus pour chaque ligne
				for (int j = 1; j < lstDonneesLignes.get(i).length; j++) {
					mapLignes.get(idLigne).addTerminus(
							mapStations.get(lstDonneesLignes.get(i)[j]));
				}

				// ajout stations d'une ligne
				ArrayList<String[]> DonneesLigne = CsvFileHelper
						.readFilesLignes(idLigne);
				for (int k = 0; k < DonneesLigne.size(); k++) {
					mapLignes.get(idLigne).addStation(
							mapStations.get(DonneesLigne.get(k)[0]));
				}
			}
			
			System.out.println(mapLignes);
	//-------------------------------traitement Lignes end ----------------------------------------//
		
	//-------------------------------traitment Station begin ----------------------------------------//
			// ajout lignes aux stations
			for (int i = 0; i < lstDonneesStations.size(); i++) {
				for (int j = 2; j < lstDonneesStations.get(i).length; j++) {
					mapStations.get(lstDonneesStations.get(i)[0]).addLigne(mapLignes.get(lstDonneesStations.get(i)[j]));
				}
			}
			System.out.println(mapStations);
	//-------------------------------traitment Station end ----------------------------------------//	
					
	//-------------------------------traitement Lignes begin ----------------------------------------//
			// ajout des lignes de correspondances par ligne de Metro
			for (int i = 0; i < lstDonneesLignes.size(); i++) {
				String idLigne = lstDonneesLignes.get(i)[0];
				ArrayList<String[]> DonneesLigne = CsvFileHelper
						.readFilesLignes(idLigne);
				for (int j = 0; j < DonneesLigne.size(); j++) {
					for(int k =0; k<mapStations.get(DonneesLigne.get(j)[0]).getLstLignes().size(); k++){
						mapLignes.get(idLigne).addCorrespondance(mapStations.get(DonneesLigne.get(j)[0]).getLstLignes().get(k));
					}
				}
				System.out.println("Ligne"+idLigne+" Correspondance:"+mapLignes.get(idLigne).getListCorrespondance());
			}
	//-------------------------------traitement Lignes end ----------------------------------------//
		
	//-------------------------------traitment Station begin ----------------------------------------//
			// la suite arrive :) :
			
			// ajout relations de chaque stations et stations voisines
			// cas spéciaux lignes 7, 7bis ,10 et 13
	//-------------------------------traitment Station end ----------------------------------------//
			
		} catch (IOException e) {
			System.out.println("Problème d'ouverture fichier CSV");
		}

	}

	public Map<String, Station> getMapStations() {
		return mapStations;
	}

	public Map<String, Ligne> getMapLignes() {
		return mapLignes;
	}
	
	

}
