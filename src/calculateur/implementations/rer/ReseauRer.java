package calculateur.implementations.rer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Tools.CsvFileHelper;
import calculateur.abstracts.Ligne;
import calculateur.abstracts.Relation;
import calculateur.abstracts.Reseau;
import calculateur.abstracts.Station;

public class ReseauRer extends Reseau {

	private Map<String, Station> mapStations;
	private Map<String, Ligne> mapLignes;

	public ReseauRer() {
		super();
		mapStations = new HashMap<String, Station>();
		mapLignes = new HashMap<String, Ligne>();
		loadReseauFromCSV();
	}

	public ReseauRer(Map<Station, ArrayList<Relation>> grapheR,
			Map<String, Station> stations, Map<String, Ligne> lignes) {
		super(grapheR);
		mapLignes = lignes;
		mapStations = stations;
	}
	
	@Override
	public Map<String, Station> getMapStations() {
		return mapStations;
	}

	@Override
	public void loadReseauFromCSV() {

		try {
			ArrayList<String[]> lstDonneesLignes = CsvFileHelper
					.readFileLstLignesRer();
			ArrayList<String[]> lstDonneesStations = CsvFileHelper
					.readFileLstStationsRer();

			// ------------------------------- traitement Stations begin ----------------------------------------//
			// ajout des stations à la map(à vide : juste avec leur nom)
			for (int i = 0; i < lstDonneesStations.size(); i++) {
				mapStations.put(lstDonneesStations.get(i)[0], new StationRer(
						lstDonneesStations.get(i)[1]));
			}
			// -------------------------------traitement Stations end----------------------------------------//

			// -------------------------------traitement Lignes begin ----------------------------------------//
			// ajout des lignes à la map (à vide : juste avec leur nom)
			for (int i = 0; i < lstDonneesLignes.size(); i++) {
				mapLignes.put(lstDonneesLignes.get(i)[0], new LigneRer(
						lstDonneesLignes.get(i)[0]));
			}

			/*
			 * ajout des terminus, stations et ligne de correspondance de chaque
			 * Ligne
			 */
			for (int i = 0; i < lstDonneesLignes.size(); i++) {
				String idLigne = lstDonneesLignes.get(i)[0];

				// ajout des stations terminus pour chaque ligne
				for (int j = 1; j < lstDonneesLignes.get(i).length; j++) {
					mapLignes.get(idLigne).addTerminus(
							mapStations.get(lstDonneesLignes.get(i)[j]));
				}

				// ajout stations d'une ligne
				ArrayList<String[]> DonneesLigne = CsvFileHelper
						.readFilesLignesRer(idLigne);
				for (int k = 0; k < DonneesLigne.size(); k++) {
					mapLignes.get(idLigne).addStation(
							mapStations.get(DonneesLigne.get(k)[0]));
				}
				System.out.println(idLigne+" terminus:"+mapLignes.get(idLigne).getListTerminus());
			}

			 System.out.println(mapLignes);
			// -------------------------------traitement Lignes end-----------------------------------------//

			// -------------------------------traitment Station begin----------------------------------------//
			// ajout lignes aux stations
			for (int i = 0; i < lstDonneesStations.size(); i++) {
				for (int j = 2; j < lstDonneesStations.get(i).length; j++) {
					mapStations.get(lstDonneesStations.get(i)[0]).addLigne(mapLignes.get(lstDonneesStations.get(i)[j]));
				}
			}
			// System.out.println(mapStations);
			// -------------------------------traitment Station end----------------------------------------//

			// -------------------------------traitement Lignes begin----------------------------------------//
			// ajout des lignes de correspondances par ligne de Rer
			for (int i = 0; i < lstDonneesLignes.size(); i++) {
				String idLigne = lstDonneesLignes.get(i)[0];
				ArrayList<String[]> DonneesLigne = CsvFileHelper.readFilesLignesRer(idLigne);
				for (int j = 0; j < DonneesLigne.size(); j++) {
					for (int k = 0; k < mapStations.get(DonneesLigne.get(j)[0]).getLstLignes().size(); k++) {
						mapLignes.get(idLigne).addCorrespondance(mapStations.get(DonneesLigne.get(j)[0]).getLstLignes().get(k));
					}
				}
				// System.out.println("Ligne"+idLigne+" Correspondance:"+mapLignes.get(idLigne).getListCorrespondance());
			}
			// -------------------------------traitement Lignes end----------------------------------------//

			// -------------------------------traitment Station begin----------------------------------------//
			// ajout relations de chaque stations et stations voisines
			for (int i = 0; i < lstDonneesStations.size(); i++) {
				ArrayList<Ligne> lignes = mapStations.get(lstDonneesStations.get(i)[0]).getLstLignes();
				for (int j = 0; j < lignes.size(); j++) {
					ArrayList<String[]> DonneesLigne = CsvFileHelper.readFilesLignesRer(lignes.get(j).getNameLigne());
					System.out.println(lignes.get(j).getNameLigne());
					int k = 0;
					while (!lstDonneesStations.get(i)[0].toString().equals(DonneesLigne.get(k)[0].toString()))
						k++;
					
					// relation avec la station précédente sur la ligne en cours (si les numeros d'ordre se suivent)
					if ((k - 1) >= 0){
					mapStations.get(lstDonneesStations.get(i)[0]).addStationVoisine(mapStations.get(DonneesLigne.get(k - 1)[0]));
					mapStations.get(lstDonneesStations.get(i)[0]).addRelation(new RelationRer(mapStations.get(lstDonneesStations.get(i)[0]), mapStations.get(DonneesLigne.get(k - 1)[0]), mapStations.get(DonneesLigne.get(0)[0]).getName(), lignes.get(j)));
					}
					
					// relation avec la station suivante sur la ligne en cours (si les numeros d'ordre se suivent)
					if ((k + 1) < DonneesLigne.size()){
					mapStations.get(lstDonneesStations.get(i)[0]).addStationVoisine(mapStations.get(DonneesLigne.get(k + 1)[0]));
					mapStations.get(lstDonneesStations.get(i)[0]).addRelation(new RelationRer(mapStations.get(lstDonneesStations.get(i)[0]), mapStations.get(DonneesLigne.get(k + 1)[0]), mapStations.get(DonneesLigne.get(DonneesLigne.size() - 1)[0]).getName(), lignes.get(j)));								
					}
				}
			}
				
			// boucle pour remplir la map du réseau
			for (int i = 0; i < lstDonneesStations.size(); i++) {
				getGrapheReseau().put(mapStations.get(lstDonneesStations.get(i)[0]), mapStations.get(lstDonneesStations.get(i)[0]).getRelations());
			}
			
			for (int i = 0; i < lstDonneesStations.size(); i++) {
			//System.out.println(lstDonneesStations.get(i)[1]+": Stations voisines:"+mapStations.get(lstDonneesStations.get(i)[0]).getStationsVoisines());
			System.out.println(lstDonneesStations.get(i)[1]
			+ ": Relations:"
			+ mapStations.get(lstDonneesStations.get(i)[0])
					.getRelations());
		}

		} catch (IOException e) {
			System.out.println(e.getMessage()
					+ " Problème d'ouverture fichier CSV");
		}

	}

}
