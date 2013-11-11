package calculateur.implementations;



import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import au.com.bytecode.opencsv.CSVReader;
import calculateur.interfaces.IReseau;

public class MetroReseau implements IReseau{
	
	private CSVReader arretLigne;
	private CSVReader arretGraph;
	private ArrayList<Station> stationID;

	public MetroReseau(CSVReader arretLigne, CSVReader arretGraph) {
		this.arretLigne = arretLigne;
		this.arretGraph = arretGraph;
	}

	@Override
	public void loadReseauFromCSV() throws IOException {
		
		
		ArrayList<Station> stationID = new ArrayList<Station>();
	    String [] nextLine;
	    String [] nextLineBis;
	    while ((nextLine = this.arretLigne.readNext()) != null) {
	    	String nom = "";
	    	String [] spliter = nextLine[0].split("#");

	    	while ((nextLineBis = this.arretGraph.readNext()) != null) {
	    		String [] spliterBis = nextLineBis[0].split("#");
	    		if(spliter[0].equals(spliterBis[0])){	    			
	    			
	    			nom = spliterBis[3];
	    			
	    		}	
	    	}
	    	
	    	if (nom != null){	    	
	    		this.stationID.add(new Station(nom));
	    	}

	    }
	    
	    
	    // pour test !
	    for (int i = 0; i< stationID.size(); i++){
	    	System.out.println(stationID.get(i).getName());
	    }
		
	}

}
