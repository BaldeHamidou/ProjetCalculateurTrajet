package calculateur.implementations;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import calculateur.interfaces.IReseau;

public class MetroReseau implements IReseau{
	
	public final static char SEPARATOR = ';';	
	
	private File file;
	private ArrayList<String> data;
	private ArrayList<String[] > lMetro;

	public MetroReseau(File listeMetro) throws IOException {
		
		this.data = new ArrayList<String>();
		this.lMetro = new ArrayList<String[]>();
		this.file = listeMetro;

        // Init
		loadReseauFromCSV();

	}

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
	


}
