package Tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class CsvFileHelper {

	public static String separateurCSV = ";";

	public static ArrayList<String> readFile(File file) throws IOException {

		ArrayList<String> result = new ArrayList<String>();
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);

		for (String line = br.readLine(); line != null; line = br.readLine()) {
			result.add(line);
		}

		br.close();
		fr.close();

		return result;
	}
	
	public static ArrayList<String[]> readFileLstLignesTram() throws IOException {
		ArrayList<String> lignesFichierCSV = readFile(new File("TramReseau"+File.separator+"ListeLignes.csv"));
		ArrayList<String[]> lstDonneesLignes = new ArrayList<String[]>();
		for(int i = 0; i<lignesFichierCSV.size(); i++){
			lstDonneesLignes.add(lignesFichierCSV.get(i).split(separateurCSV));
		}
		return lstDonneesLignes;
		
	}

	public static ArrayList<String[]> readFileLstStationsTram() throws IOException {
		ArrayList<String> lignesFichierCSV = readFile(new File("TramReseau"+File.separator+"ListeStations.csv"));
		ArrayList<String[]> lstDonneesStations = new ArrayList<String[]>();
		for(int i = 0; i<lignesFichierCSV.size(); i++){
			lstDonneesStations.add(lignesFichierCSV.get(i).split(separateurCSV));
		}
		return lstDonneesStations;
	}
	
	public static ArrayList<String[]> readFilesLignesTram(String ligne) throws IOException {
		ArrayList<String> lignesFichierCSV = readFile(new File("TramReseau"+File.separator+"Ligne"+ligne+".csv"));
		ArrayList<String[]> lstDonneesLignes = new ArrayList<String[]>();
		for(int i = 0; i<lignesFichierCSV.size(); i++){
			lstDonneesLignes.add(lignesFichierCSV.get(i).split(separateurCSV));
		}
		return lstDonneesLignes;
		
	}

	public static ArrayList<String[]> readFileLstLignesMetro() throws IOException {
		ArrayList<String> lignesFichierCSV = readFile(new File("MetroReseau"+File.separator+"ListeLignes.csv"));
		ArrayList<String[]> lstDonneesLignes = new ArrayList<String[]>();
		for(int i = 0; i<lignesFichierCSV.size(); i++){
			lstDonneesLignes.add(lignesFichierCSV.get(i).split(separateurCSV));
		}
		return lstDonneesLignes;
		
	}

	public static ArrayList<String[]> readFileLstStationsMetro() throws IOException {
		ArrayList<String> lignesFichierCSV = readFile(new File("MetroReseau"+File.separator+"ListeStations.csv"));
		ArrayList<String[]> lstDonneesStations = new ArrayList<String[]>();
		for(int i = 0; i<lignesFichierCSV.size(); i++){
			lstDonneesStations.add(lignesFichierCSV.get(i).split(separateurCSV));
		}
		return lstDonneesStations;
	}
	
	public static ArrayList<String[]> readFilesLignesMetro(String ligne) throws IOException {
		ArrayList<String> lignesFichierCSV = readFile(new File("MetroReseau"+File.separator+"Ligne"+ligne+".csv"));
		ArrayList<String[]> lstDonneesLignes = new ArrayList<String[]>();
		for(int i = 0; i<lignesFichierCSV.size(); i++){
			lstDonneesLignes.add(lignesFichierCSV.get(i).split(separateurCSV));
		}
		return lstDonneesLignes;
		
	}
}
