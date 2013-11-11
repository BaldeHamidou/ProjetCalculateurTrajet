package calculateur.interfaces;

import java.io.FileNotFoundException;
import java.io.IOException;

import au.com.bytecode.opencsv.CSVReader;

public interface IReseau {
 public void loadReseauFromCSV() throws FileNotFoundException, IOException;
}
