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

	public ReseauMetro() {
		super();
	}

	public ReseauMetro(Map<Station, ArrayList<Relation>> grapheR) {
		super(grapheR);
	}

	public void loadReseauFromCSV() {

		try {
			ArrayList<String[]> lstDonneesLignes = CsvFileHelper
					.readFileLstLignes();
			ArrayList<String[]> lstDonneesStations = CsvFileHelper
					.readFileLstStations();

			 //------------------------------- traitement Stations begin ----------------------------------------//
			// création de la Map des stations (à vide : juste avec leur nom)
			Map<String, Station> mapStation = new HashMap<String, Station>();

			for (int i = 0; i < lstDonneesStations.size(); i++) {
				mapStation.put(lstDonneesStations.get(i)[0], new StationMetro(
						lstDonneesStations.get(i)[1]));
			}
          //-------------------------------traitement Stations end ----------------------------------------//
		  
		 //-------------------------------traitement Lignes begin ----------------------------------------//
			// création de la Map des lignes (à vide : juste avec leur nom)
			Map<String, Ligne> mapLigne = new HashMap<String, Ligne>();

			for (int i = 0; i < lstDonneesLignes.size(); i++) {
				mapLigne.put(lstDonneesLignes.get(i)[0], new LigneMetro(
						lstDonneesLignes.get(i)[0]));
			}

			/* ajout des terminus, stations et ligne de correspondance de chaque Ligne */
			for (int i = 0; i < lstDonneesLignes.size(); i++) {
				String idLigne = lstDonneesLignes.get(i)[0];

				// ajout des stations terminus pour chaque ligne
				for (int j = 1; j < lstDonneesLignes.get(i).length; j++) {
					mapLigne.get(idLigne).addTerminus(
							mapStation.get(lstDonneesLignes.get(i)[j]));
				}

				// ajout stations d'une ligne
				ArrayList<String[]> DonneesLigne = CsvFileHelper
						.readFilesLignes(idLigne);
				for (int k = 0; k < DonneesLigne.size(); k++) {
					mapLigne.get(idLigne).addStation(
							mapStation.get(DonneesLigne.get(k)[0]));
				}
			}
			
			System.out.println(mapLigne);
	//-------------------------------traitement Lignes end ----------------------------------------//
		
	//-------------------------------traitment Station begin ----------------------------------------//
			// ajout lignes aux stations
			for (int i = 0; i < lstDonneesStations.size(); i++) {
				for (int j = 2; j < lstDonneesStations.get(i).length; j++) {
					mapStation.get(lstDonneesStations.get(i)[0]).addLigne(mapLigne.get(lstDonneesStations.get(i)[j]));
				}
			}
			System.out.println(mapStation);
	//-------------------------------traitment Station end ----------------------------------------//	
					
	//-------------------------------traitement Lignes begin ----------------------------------------//
			// ajout des lignes de correspondances par ligne de Metro
			for (int i = 0; i < lstDonneesLignes.size(); i++) {
				String idLigne = lstDonneesLignes.get(i)[0];
				ArrayList<String[]> DonneesLigne = CsvFileHelper
						.readFilesLignes(idLigne);
				for (int j = 0; j < DonneesLigne.size(); j++) {
					for(int k =0; k<mapStation.get(DonneesLigne.get(j)[0]).getLstLignes().size(); k++){
						mapLigne.get(idLigne).addCorrespondance(mapStation.get(DonneesLigne.get(j)[0]).getLstLignes().get(k));
					}
				}
				System.out.println("Ligne"+idLigne+" Correspondance:"+mapLigne.get(idLigne).getListCorrespondance());
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

}
