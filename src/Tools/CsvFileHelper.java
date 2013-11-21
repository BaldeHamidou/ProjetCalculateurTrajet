package Tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class CsvFileHelper {

	public static String separateurCSV = ";";
	
	/*public static String getResourcePath(String fileName) {
		final File f = new File("");
		final String dossierPath = f.getAbsolutePath() + File.separator
				+ fileName;
		return dossierPath;
	}

	public static File getResource(String fileName) {
		final String completeFileName = getResourcePath(fileName);
		File file = new File(completeFileName);
		return file;
	}*/

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

	public static ArrayList<String[]> readFileLstLignes() throws IOException {
		ArrayList<String> lignesFichierCSV = readFile(new File("MetroReseau"+File.separator+"ListeLignes.csv"));
		ArrayList<String[]> lstDonneesLignes = new ArrayList<String[]>();
		for(int i = 0; i<lignesFichierCSV.size(); i++){
			lstDonneesLignes.add(lignesFichierCSV.get(i).split(separateurCSV));
		}
		return lstDonneesLignes;
		
	}

	public static ArrayList<String[]> readFileLstStations() throws IOException {
		ArrayList<String> lignesFichierCSV = readFile(new File("MetroReseau"+File.separator+"ListeStations.csv"));
		ArrayList<String[]> lstDonneesStations = new ArrayList<String[]>();
		for(int i = 0; i<lignesFichierCSV.size(); i++){
			lstDonneesStations.add(lignesFichierCSV.get(i).split(separateurCSV));
		}
		return lstDonneesStations;
	}
	
	public static ArrayList<String[]> readFilesLignes(String ligne) throws IOException {
		ArrayList<String> lignesFichierCSV = readFile(new File("MetroReseau"+File.separator+"Ligne"+ligne+".csv"));
		ArrayList<String[]> lstDonneesLignes = new ArrayList<String[]>();
		for(int i = 0; i<lignesFichierCSV.size(); i++){
			lstDonneesLignes.add(lignesFichierCSV.get(i).split(separateurCSV));
		}
		return lstDonneesLignes;
		
	}
}
