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

	public ReseauMetro(Map<Station, ArrayList<Relation>> grapheR,
			Map<String, Station> stations, Map<String, Ligne> lignes) {
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

			// ------------------------------- traitement Stations begin
			// ----------------------------------------//
			// ajout des stations à la map(à vide : juste avec leur nom)
			for (int i = 0; i < lstDonneesStations.size(); i++) {
				mapStations.put(lstDonneesStations.get(i)[0], new StationMetro(
						lstDonneesStations.get(i)[1]));
			}
			// -------------------------------traitement Stations end
			// ----------------------------------------//

			// -------------------------------traitement Lignes begin
			// ----------------------------------------//
			// ajout des lignes à la map (à vide : juste avec leur nom)
			for (int i = 0; i < lstDonneesLignes.size(); i++) {
				mapLignes.put(lstDonneesLignes.get(i)[0], new LigneMetro(
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
						.readFilesLignes(idLigne);
				for (int k = 0; k < DonneesLigne.size(); k++) {
					mapLignes.get(idLigne).addStation(
							mapStations.get(DonneesLigne.get(k)[0]));
				}
				// System.out.println(idLigne+" terminus:"+mapLignes.get(idLigne).getListTerminus());
			}

			// System.out.println(mapLignes);
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
			// ajout des lignes de correspondances par ligne de Metro
			for (int i = 0; i < lstDonneesLignes.size(); i++) {
				String idLigne = lstDonneesLignes.get(i)[0];
				ArrayList<String[]> DonneesLigne = CsvFileHelper.readFilesLignes(idLigne);
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
					ArrayList<String[]> DonneesLigne = CsvFileHelper.readFilesLignes(lignes.get(j).getNameLigne());
					int k = 0;
					while (!lstDonneesStations.get(i)[0].toString().equals(DonneesLigne.get(k)[0].toString()))
						k++;
					
					// relation avec la station précédente sur la ligne en cours (si les numeros d'ordre se suivent)
					if ((k - 1) >= 0 && (Integer.parseInt(DonneesLigne.get(k - 1)[2]) == Integer.parseInt(DonneesLigne.get(k)[2]) - 1)) {
						if (!lignes.get(j).getNameLigne().equals("7bis") && !lignes.get(j).getNameLigne().equals("10")){
							mapStations.get(lstDonneesStations.get(i)[0]).addStationVoisine(mapStations.get(DonneesLigne.get(k - 1)[0]));
							mapStations.get(lstDonneesStations.get(i)[0]).addRelation(new RelationMetro(mapStations.get(lstDonneesStations.get(i)[0]), mapStations.get(DonneesLigne.get(k - 1)[0]), mapStations.get(DonneesLigne.get(0)[0]).getName()));
						}
						// ligne 7bis
						if(lignes.get(j).getNameLigne().equals("7bis")){
							if((k-1) <= 3){
								mapStations.get(lstDonneesStations.get(i)[0]).addStationVoisine(mapStations.get(DonneesLigne.get(k - 1)[0]));
								mapStations.get(lstDonneesStations.get(i)[0]).addRelation(new RelationMetro(mapStations.get(lstDonneesStations.get(i)[0]), mapStations.get(DonneesLigne.get(k - 1)[0]), mapStations.get(DonneesLigne.get(0)[0]).getName()));
							}
						}
						
						//ligne 10
						if(lignes.get(j).getNameLigne().equals("10")){
							if(((k-1) == 0) || ((k-1) >= 8)){
								mapStations.get(lstDonneesStations.get(i)[0]).addStationVoisine(mapStations.get(DonneesLigne.get(k - 1)[0]));
								mapStations.get(lstDonneesStations.get(i)[0]).addRelation(new RelationMetro(mapStations.get(lstDonneesStations.get(i)[0]), mapStations.get(DonneesLigne.get(k - 1)[0]), mapStations.get(DonneesLigne.get(0)[0]).getName()));
							}
						}
					}
					
					// relation avec la station suivante sur la ligne en cours (si les numeros d'ordre se suivent)
					if ((k + 1) < DonneesLigne.size() && (Integer.parseInt(DonneesLigne.get(k + 1)[2]) == Integer.parseInt(DonneesLigne.get(k)[2]) + 1)) {
						mapStations.get(lstDonneesStations.get(i)[0]).addStationVoisine(mapStations.get(DonneesLigne.get(k + 1)[0]));
						if (!lignes.get(j).getNameLigne().equals("7bis") && !lignes.get(j).getNameLigne().equals("10") && !lignes.get(j).getNameLigne().equals("13") && !lignes.get(j).getNameLigne().equals("7"))
							mapStations.get(lstDonneesStations.get(i)[0]).addRelation(new RelationMetro(mapStations.get(lstDonneesStations.get(i)[0]), mapStations.get(DonneesLigne.get(k + 1)[0]), mapStations.get(DonneesLigne.get(DonneesLigne.size() - 1)[0]).getName()));
						
						// ligne 7 
						if(lignes.get(j).getNameLigne().equals("7")){
						  	if((k+1) <= 28)
						 		mapStations.get(lstDonneesStations.get(i)[0]).addRelation(new RelationMetro(mapStations.get(lstDonneesStations.get(i)[0]), mapStations.get(DonneesLigne.get(k + 1)[0]), mapStations.get(DonneesLigne.get(DonneesLigne.size() - 1)[0]).getName()+" ou "+mapStations.get(DonneesLigne.get(33)[0]).getName()));
						  	if((k+1) >= 30 && (k+1) <= 33)
						  		mapStations.get(lstDonneesStations.get(i)[0]).addRelation(new RelationMetro(mapStations.get(lstDonneesStations.get(i)[0]), mapStations.get(DonneesLigne.get(k + 1)[0]), mapStations.get(DonneesLigne.get(33)[0]).getName()));
						  	if((k+1) >= 35)
						  		mapStations.get(lstDonneesStations.get(i)[0]).addRelation(new RelationMetro(mapStations.get(lstDonneesStations.get(i)[0]), mapStations.get(DonneesLigne.get(k + 1)[0]), mapStations.get(DonneesLigne.get(DonneesLigne.size() - 1)[0]).getName()));
						 } 
						 
						// ligne 7bis
						if(lignes.get(j).getNameLigne().equals("7bis")){
							if((k+1) <= 6){
								mapStations.get(lstDonneesStations.get(i)[0]).addRelation(new RelationMetro(mapStations.get(lstDonneesStations.get(i)[0]), mapStations.get(DonneesLigne.get(k + 1)[0]), mapStations.get(DonneesLigne.get(6)[0]).getName()));
							}
							else{
								mapStations.get(lstDonneesStations.get(i)[0]).addRelation(new RelationMetro(mapStations.get(lstDonneesStations.get(i)[0]), mapStations.get(DonneesLigne.get(k + 1)[0]), mapStations.get(DonneesLigne.get(0)[0]).getName()));
								mapStations.get(DonneesLigne.get(k + 1)[0]).addRelation(new RelationMetro(mapStations.get(DonneesLigne.get(k + 1)[0]), mapStations.get(DonneesLigne.get(4)[0]), mapStations.get(DonneesLigne.get(0)[0]).getName()));
								mapStations.get(DonneesLigne.get(k + 1)[0]).addStationVoisine(mapStations.get(DonneesLigne.get(4)[0]));
							}
						}
						
						// ligne 10
						if(lignes.get(j).getNameLigne().equals("10")){
							if(((k+1) <= 4) || ((k+1) >= 9))
								mapStations.get(lstDonneesStations.get(i)[0]).addRelation(new RelationMetro(mapStations.get(lstDonneesStations.get(i)[0]), mapStations.get(DonneesLigne.get(k + 1)[0]), mapStations.get(DonneesLigne.get(DonneesLigne.size() - 1)[0]).getName()));
							else
								mapStations.get(lstDonneesStations.get(i)[0]).addRelation(new RelationMetro(mapStations.get(lstDonneesStations.get(i)[0]), mapStations.get(DonneesLigne.get(k + 1)[0]), mapStations.get(DonneesLigne.get(0)[0]).getName()));
						}
						
						// ligne 13
						if(lignes.get(j).getNameLigne().equals("13")){
						  	if((k+1) <= 17)
						 		mapStations.get(lstDonneesStations.get(i)[0]).addRelation(new RelationMetro(mapStations.get(lstDonneesStations.get(i)[0]), mapStations.get(DonneesLigne.get(k + 1)[0]), mapStations.get(DonneesLigne.get(DonneesLigne.size() - 1)[0]).getName()+" ou "+mapStations.get(DonneesLigne.get(25)[0]).getName()));
						  	if((k+1) >= 19 && (k+1) <= 25)
						  		mapStations.get(lstDonneesStations.get(i)[0]).addRelation(new RelationMetro(mapStations.get(lstDonneesStations.get(i)[0]), mapStations.get(DonneesLigne.get(k + 1)[0]), mapStations.get(DonneesLigne.get(25)[0]).getName()));
						  	if((k+1) >= 27)
						  		mapStations.get(lstDonneesStations.get(i)[0]).addRelation(new RelationMetro(mapStations.get(lstDonneesStations.get(i)[0]), mapStations.get(DonneesLigne.get(k + 1)[0]), mapStations.get(DonneesLigne.get(DonneesLigne.size() - 1)[0]).getName()));
						 } 
					}
				}
			}
			
		// ligne7 cas spéciaux
			ArrayList<String[]> DonneesLigne7 = CsvFileHelper.readFilesLignes("7");
			// ajout stations voisines manquantes
			mapStations.get(DonneesLigne7.get(28)[0]).addStationVoisine(mapStations.get(DonneesLigne7.get(29)[0]));
			mapStations.get(DonneesLigne7.get(29)[0]).addStationVoisine(mapStations.get(DonneesLigne7.get(28)[0]));
			mapStations.get(DonneesLigne7.get(28)[0]).addStationVoisine(mapStations.get(DonneesLigne7.get(34)[0]));
			mapStations.get(DonneesLigne7.get(34)[0]).addStationVoisine(mapStations.get(DonneesLigne7.get(28)[0]));
			
			//ajout relations manquantes
			mapStations.get(DonneesLigne7.get(28)[0]).addRelation(new RelationMetro(mapStations.get(DonneesLigne7.get(28)[0]), mapStations.get(DonneesLigne7.get(29)[0]), mapStations.get(DonneesLigne7.get(33)[0]).getName()));
			mapStations.get(DonneesLigne7.get(29)[0]).addRelation(new RelationMetro(mapStations.get(DonneesLigne7.get(29)[0]), mapStations.get(DonneesLigne7.get(28)[0]), mapStations.get(DonneesLigne7.get(0)[0]).getName()));
			mapStations.get(DonneesLigne7.get(28)[0]).addRelation(new RelationMetro(mapStations.get(DonneesLigne7.get(28)[0]), mapStations.get(DonneesLigne7.get(34)[0]), mapStations.get(DonneesLigne7.get(DonneesLigne7.size() - 1)[0]).getName()));
			mapStations.get(DonneesLigne7.get(34)[0]).addRelation(new RelationMetro(mapStations.get(DonneesLigne7.get(34)[0]), mapStations.get(DonneesLigne7.get(28)[0]), mapStations.get(DonneesLigne7.get(0)[0]).getName()));
			
		// ligne 10 cas spéciaux
			ArrayList<String[]> DonneesLigne10 = CsvFileHelper.readFilesLignes("10");
			// ajout stations voisines manquantes
			mapStations.get(DonneesLigne10.get(1)[0]).addStationVoisine(mapStations.get(DonneesLigne10.get(2)[0]));
			mapStations.get(DonneesLigne10.get(4)[0]).addStationVoisine(mapStations.get(DonneesLigne10.get(8)[0]));
			mapStations.get(DonneesLigne10.get(8)[0]).addStationVoisine(mapStations.get(DonneesLigne10.get(5)[0]));
			mapStations.get(DonneesLigne10.get(7)[0]).addStationVoisine(mapStations.get(DonneesLigne10.get(1)[0])); 
			
			//ajout relations manquantes
			mapStations.get(DonneesLigne10.get(1)[0]).addRelation(new RelationMetro(mapStations.get(DonneesLigne10.get(1)[0]), mapStations.get(DonneesLigne10.get(2)[0]), mapStations.get(DonneesLigne10.get(DonneesLigne10.size() - 1)[0]).getName()));
			mapStations.get(DonneesLigne10.get(4)[0]).addRelation(new RelationMetro(mapStations.get(DonneesLigne10.get(4)[0]), mapStations.get(DonneesLigne10.get(8)[0]), mapStations.get(DonneesLigne10.get(DonneesLigne10.size() - 1)[0]).getName()));
			mapStations.get(DonneesLigne10.get(8)[0]).addRelation(new RelationMetro(mapStations.get(DonneesLigne10.get(8)[0]), mapStations.get(DonneesLigne10.get(5)[0]), mapStations.get(DonneesLigne10.get(0)[0]).getName()));
			mapStations.get(DonneesLigne10.get(7)[0]).addRelation(new RelationMetro(mapStations.get(DonneesLigne10.get(7)[0]), mapStations.get(DonneesLigne10.get(1)[0]), mapStations.get(DonneesLigne10.get(0)[0]).getName()));
			
		// ligne 13 cas spéciaux
			ArrayList<String[]> DonneesLigne13 = CsvFileHelper.readFilesLignes("13");
			// ajout stations voisines manquantes
			mapStations.get(DonneesLigne13.get(17)[0]).addStationVoisine(mapStations.get(DonneesLigne13.get(18)[0]));
			mapStations.get(DonneesLigne13.get(18)[0]).addStationVoisine(mapStations.get(DonneesLigne13.get(17)[0]));
			mapStations.get(DonneesLigne13.get(17)[0]).addStationVoisine(mapStations.get(DonneesLigne13.get(26)[0]));
			mapStations.get(DonneesLigne13.get(26)[0]).addStationVoisine(mapStations.get(DonneesLigne13.get(17)[0]));
						
			//ajout relations manquantes
			mapStations.get(DonneesLigne13.get(17)[0]).addRelation(new RelationMetro(mapStations.get(DonneesLigne13.get(17)[0]), mapStations.get(DonneesLigne13.get(18)[0]), mapStations.get(DonneesLigne13.get(25)[0]).getName()));
			mapStations.get(DonneesLigne13.get(18)[0]).addRelation(new RelationMetro(mapStations.get(DonneesLigne13.get(18)[0]), mapStations.get(DonneesLigne13.get(17)[0]), mapStations.get(DonneesLigne13.get(0)[0]).getName()));
			mapStations.get(DonneesLigne13.get(17)[0]).addRelation(new RelationMetro(mapStations.get(DonneesLigne13.get(17)[0]), mapStations.get(DonneesLigne13.get(26)[0]), mapStations.get(DonneesLigne13.get(DonneesLigne13.size() - 1)[0]).getName()));
			mapStations.get(DonneesLigne13.get(26)[0]).addRelation(new RelationMetro(mapStations.get(DonneesLigne13.get(26)[0]), mapStations.get(DonneesLigne13.get(17)[0]), mapStations.get(DonneesLigne13.get(0)[0]).getName()));	
			
			// -------------------------------traitment Station end ----------------------------------------//
			
			for (int i = 0; i < lstDonneesStations.size(); i++) {
				System.out.println(lstDonneesStations.get(i)[1]+": Stations voisines:"+mapStations.get(lstDonneesStations.get(i)[0]).getStationsVoisines());
				/*System.out.println(lstDonneesStations.get(i)[1]
				+ ": Relations:"
				+ mapStations.get(lstDonneesStations.get(i)[0])
						.getRelations());*/
			}
			
			// boucle pour remplir la map du réseau
			for (int i = 0; i < lstDonneesStations.size(); i++) {
				getGrapheReseau().put(mapStations.get(lstDonneesStations.get(i)[0]), mapStations.get(lstDonneesStations.get(i)[0]).getRelations());
			}
			
			// test compte nb relations
			System.out.println("De "+mapLignes.get("13").getListStation().get(4)+" à "+mapLignes.get("13").getListStation().get(22)+" il y a "+mapLignes.get("13").nbRelationsEntreDeuxStationsSurLaLigne(mapLignes.get("13").getListStation().get(4), mapLignes.get("13").getListStation().get(22))+" relations");

		} catch (IOException e) {
			System.out.println(e.getMessage()
					+ " Problème d'ouverture fichier CSV");
		}

	}

	public Map<String, Station> getMapStations() {
		return mapStations;
	}

	public Map<String, Ligne> getMapLignes() {
		return mapLignes;
	}

}
