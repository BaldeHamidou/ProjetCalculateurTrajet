import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 
 *Classe de lancement de l'application
 *
 */
public class Application {
	
	public static void main(String[] args) {
		
		IHM ihm = new IHM();
		ihm.setVisible(true);
		//Tools.ConsoleInterface.start();
		/*String aEcrireT1 = "";
		String aEcrireT2 = "";
		String aEcrireT3a = "";
		String aEcrireT3b = "";
		String aEcrireT4 = "";
		String aEcrireT5 = "";
		String aEcrire = "";
		int idStation = 434;
		try {
			*/
			/*ArrayList<String> stations = Tools.CsvFileHelper.readFile(new File("tram.csv"));
			for(int i=0; i<stations.size(); i++){
				if(i<34)
					aEcrire += (idStation+i)+";"+stations.get(i).split(";")[0]+";T1\n";
				if(i==34){
					aEcrire += (idStation+i)+";"+stations.get(i).split(";")[0]+";T1\n";
					aEcrire += (idStation+i+1)+";"+stations.get(i).split(";")[1]+";T1\n";
				}
				if(i > 34 && i<57)
					aEcrire += (idStation+i+1)+";"+stations.get(i).split(";")[0]+";T2\n";
				if(i==57){
					aEcrire += (idStation+i+1)+";"+stations.get(i).split(";")[0]+";T2\n";
					aEcrire += (idStation+i+2)+";"+stations.get(i).split(";")[1]+";T2\n";
				}
				if(i>57 && i<81)
					aEcrire += (idStation+i+2)+";"+stations.get(i).split(";")[0]+";T3a\n";
				if(i==81){
					aEcrire += (idStation+i+2)+";"+stations.get(i).split(";")[0]+";T3a\n";
					aEcrire += (idStation+i+3)+";"+stations.get(i).split(";")[1]+";T3a\n";
				}
				if(i>81 && i<98)
					aEcrire += (idStation+i+3)+";"+stations.get(i).split(";")[0]+";T3b\n";
				if(i==98){
					aEcrire += (idStation+i+3)+";"+stations.get(i).split(";")[0]+";T3b\n";
					aEcrire += (idStation+i+4)+";"+stations.get(i).split(";")[1]+";T3b\n";
				}
				if(i>98 && i<108)
					aEcrire += (idStation+i+4)+";"+stations.get(i).split(";")[0]+";T4\n";
				if(i==108){
					aEcrire += (idStation+i+4)+";"+stations.get(i).split(";")[0]+";T4\n";
					aEcrire += (idStation+i+5)+";"+stations.get(i).split(";")[1]+";T4\n";
				}
				if(i>108 && i<123)
					aEcrire += (idStation+i+5)+";"+stations.get(i).split(";")[0]+";T5\n";
				if(i==123){
					aEcrire += (idStation+i+5)+";"+stations.get(i).split(";")[0]+";T5\n";
					aEcrire += (idStation+i+6)+";"+stations.get(i).split(";")[1]+";T5\n";
				}
			}		
			ecrire("ListeStations.csv", aEcrire);
			stations = Tools.CsvFileHelper.readFile(new File("ListeStations.csv"));
			for(int i=0; i<stations.size(); i++){
				if(stations.get(i).split(";")[2].equals("T1"))
					aEcrireT1 += stations.get(i).split(";")[0]+"\n";
				if(stations.get(i).split(";")[2].equals("T2"))
					aEcrireT2 += stations.get(i).split(";")[0]+"\n";
				if(stations.get(i).split(";")[2].equals("T3a"))
					aEcrireT3a += stations.get(i).split(";")[0]+"\n";
				if(stations.get(i).split(";")[2].equals("T3b"))
					aEcrireT3b += stations.get(i).split(";")[0]+"\n";
				if(stations.get(i).split(";")[2].equals("T4"))
					aEcrireT4 += stations.get(i).split(";")[0]+"\n";
				if(stations.get(i).split(";")[2].equals("T5"))
					aEcrireT5 += stations.get(i).split(";")[0]+"\n";
			}
			ecrire("LigneT1.csv", aEcrireT1);
			ecrire("LigneT2.csv", aEcrireT2);
			ecrire("LigneT3a.csv", aEcrireT3a);
			ecrire("LigneT3b.csv", aEcrireT3b);
			ecrire("LigneT4.csv", aEcrireT4);
			ecrire("LigneT5.csv", aEcrireT5);
			String lignes = "T1\nT2\nT3a\nT3b\nT4\nT5";
			ecrire("ListeLignes.csv", lignes);*/
			
		/*	String aEcrireA = "";
			String aEcrireB = "";
			String aEcrireD = "";
			String aEcrireE = "";
			
			ArrayList<String> stations = Tools.CsvFileHelper.readFile(new File("rer.csv"));
			for(int i=0; i<stations.size(); i++){
				if(i<5)
					aEcrire += (idStation+i)+";"+stations.get(i).split(";")[0]+";RER A\n";
				if(i==5){
					aEcrire += (idStation+i)+";"+stations.get(i).split(";")[0]+";RER A\n";
					aEcrire += (idStation+i+1)+";"+stations.get(i).split(";")[1]+";RER A\n";
				}
				if(i > 5 && i<20)
					aEcrire += (idStation+i+1)+";"+stations.get(i).split(";")[0]+";RER B\n";
				if(i==20){
					aEcrire += (idStation+i+1)+";"+stations.get(i).split(";")[0]+";RER B\n";
					aEcrire += (idStation+i+2)+";"+stations.get(i).split(";")[1]+";RER B\n";
				}
				if(i>42 && i<49)
					aEcrire += (idStation+i+2)+";"+stations.get(i).split(";")[0]+";RER D\n";
				if(i==49){
					aEcrire += (idStation+i+2)+";"+stations.get(i).split(";")[0]+";RER D\n";
					aEcrire += (idStation+i+3)+";"+stations.get(i).split(";")[1]+";RER D\n";
				}
				if(i>49 && i<53)
					aEcrire += (idStation+i+3)+";"+stations.get(i).split(";")[0]+";RER E\n";
				if(i==53){
					aEcrire += (idStation+i+3)+";"+stations.get(i).split(";")[0]+";RER E\n";
					aEcrire += (idStation+i+4)+";"+stations.get(i).split(";")[1]+";RER E\n";
				}
				/*if(i>98 && i<108)
					aEcrire += (idStation+i+4)+";"+stations.get(i).split(";")[0]+";T4\n";
				if(i==108){
					aEcrire += (idStation+i+4)+";"+stations.get(i).split(";")[0]+";T4\n";
					aEcrire += (idStation+i+5)+";"+stations.get(i).split(";")[1]+";T4\n";
				}
				if(i>108 && i<123)
					aEcrire += (idStation+i+5)+";"+stations.get(i).split(";")[0]+";T5\n";
				if(i==123){
					aEcrire += (idStation+i+5)+";"+stations.get(i).split(";")[0]+";T5\n";
					aEcrire += (idStation+i+6)+";"+stations.get(i).split(";")[1]+";T5\n";
				}*/
			/*}		
			ecrire("ListeStations.csv", aEcrire);
			stations = Tools.CsvFileHelper.readFile(new File("ListeStations.csv"));
			for(int i=0; i<stations.size(); i++){
				if(stations.get(i).split(";")[2].equals("RER A"))
					aEcrireA += stations.get(i).split(";")[0]+"\n";
				if(stations.get(i).split(";")[2].equals("RER B"))
					aEcrireB += stations.get(i).split(";")[0]+"\n";
				if(stations.get(i).split(";")[2].equals("RER D"))
					aEcrireD += stations.get(i).split(";")[0]+"\n";
				if(stations.get(i).split(";")[2].equals("RER E"))
					aEcrireE += stations.get(i).split(";")[0]+"\n";
			}
			ecrire("LigneRER A.csv", aEcrireA);
			ecrire("LigneRER B.csv", aEcrireB);
			ecrire("LigneRER D.csv", aEcrireD);
			ecrire("LigneRER E.csv", aEcrireE);
		
			String lignes = "RER A\nRER B\nRER D\nRER E";
			ecrire("ListeLignes.csv", lignes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
		public static void ecrire(String nomFic, String texte)
		{
			//on va chercher le chemin et le nom du fichier et on me tout ca dans un String
			String adressedufichier = System.getProperty("user.dir") + "/"+ nomFic;
		
			//on met try si jamais il y a une exception
			try
			{*/
				/*
				 * BufferedWriter a besoin d un FileWriter, 
				 * les 2 vont ensemble, on donne comme argument le nom du fichier
				 * true signifie qu on ajoute dans le fichier (append), on ne marque pas par dessus 
				 
				 */
			/*	FileWriter fw = new FileWriter(adressedufichier, true);
				
				// le BufferedWriter output auquel on donne comme argument le FileWriter fw cree juste au dessus
				BufferedWriter output = new BufferedWriter(fw);
				
				//on marque dans le fichier ou plutot dans le BufferedWriter qui sert comme un tampon(stream)
				output.write(texte);
				//on peut utiliser plusieurs fois methode write
				
				output.flush();
				//ensuite flush envoie dans le fichier, ne pas oublier cette methode pour le BufferedWriter
				
				output.close();
				//et on le ferme
				System.out.println("fichier créé");
			}
			catch(IOException ioe){
				System.out.print("Erreur : ");
				ioe.printStackTrace();
				}*/

		}
}
