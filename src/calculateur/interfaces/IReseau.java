package calculateur.interfaces;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IReseau {
 public void loadReseauFromCSV() throws FileNotFoundException, IOException;
}
