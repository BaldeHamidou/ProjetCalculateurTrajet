package calculateur.implementations;

import java.util.ArrayList;
import java.util.Map;

import calculateur.abstracts.Ligne;
import calculateur.abstracts.Modalite;
import calculateur.abstracts.Relation;
import calculateur.abstracts.Station;
import calculateur.interfaces.IAlgoCalcul;
import calculateur.interfaces.IReseau;


/*	ALGORITHME DE DIJKSTRA

L'algorithme d� � Dijkstra est bas� sur le principe suivant :

Si le plus court chemin reliant E � S passe par les sommets s1, s2, ..., sk alors, les diff�rentes �tapes sont aussi les plus courts chemins reliant E aux diff�rents sommets s1, s2, ..., sk.

On construit de proche en proche le chemin cherch� en choisissant � chaque it�ration de l'algorithme, un sommet si du graphe parmi ceux qui n'ont pas encore �t� trait�s, tel que la longueur connue provisoirement du plus court chemin allant de E � si soit la plus courte possible.

INITIALISATION DE L'ALGORITHME :

	�tape 1 : On affecte le poids 0 au sommet origine (E) et on attribue provisoirement un poids "inconnu" aux autres sommets.

R�P�TER LES OP�RATIONS SUIVANTES TANT QUE LE SOMMET DE SORTIE (S) N'EST PAS AFFECT� D'UN POIDS D�FINITIF

	�tape 2 : Parmi les sommets dont le poids n'est pas d�finivement fix� choisir le sommet X de poids p minimal. Marquer d�finitivement ce sommet X affect� du poids p(X).

	�tape 3 : Pour tous les sommets Y qui ne sont pas d�finitivement marqu�s, adjacents au dernier sommet fix� X :

Calculer la somme s du poids de X et du poids de l'ar�te reliant X � Y.
Si la somme s est inf�rieure au poids provisoirement affect� au sommet Y, affecter provisoirement � Y le nouveau poids s et indiquer entre parenth�ses le sommet X pour se souvenir de sa provenance.
QUAND LE SOMMET S EST D�FINTIVEMENT MARQU�

Le plus court chemin de E � S s'obtient en �crivant de gauche � droite le parcours en partant de la fin S.  */


public class Dijkstra implements IAlgoCalcul{

	private Map<Station, ArrayList<Relation>> grapheReseau;
	
	public Dijkstra (Modalite modalite){
		this.grapheReseau = modalite.getReseau().getGrapheReseau();
	}
	
	@Override
	public ArrayList<Relation> plusRapideChemin(Station depart, Station arrivee) {
		
		ArrayList<Relation> relations = new ArrayList<Relation>();
		if(depart.equals(arrivee)){
			return relations;
		}
		
		Ligne ligne;
		// la station d'arriv�e est sur la m�me ligne que celle de d�part
		if((ligne = isOnTheSameLineMin(depart, arrivee)) != null){ 
			 
			 int nbRelationsEnRestantMmLigne = ligne.nbRelationsEntreDeuxStationsSurLaLigne(depart, arrivee);
			 //System.out.println(nbRelationsEnRestantMmLigne+" relations en restant sur la m�me ligne.");
			 int nbRelations = -1;
			 Station StationCorrespondance = null;
			 // les stations de la ligne directe
			 ArrayList<Station> stationsDeLaLigne = ligne.getListStation();
			 for(int j=0; j<stationsDeLaLigne.size(); j++){
			    if(!depart.equals(stationsDeLaLigne.get(j)) && stationsDeLaLigne.get(j).getLstLignes().size() >1 && ligne.nbRelationsEntreDeuxStationsSurLaLigne(depart, stationsDeLaLigne.get(j)) < nbRelationsEnRestantMmLigne){
			    	Ligne ligneTest;
			    	if((ligneTest = isOnTheSameLineMin(stationsDeLaLigne.get(j), arrivee)) != null && (ligne.nbRelationsEntreDeuxStationsSurLaLigne(depart, stationsDeLaLigne.get(j)) + ligneTest.nbRelationsEntreDeuxStationsSurLaLigne(stationsDeLaLigne.get(j), arrivee)) < nbRelationsEnRestantMmLigne){
			    		if(nbRelations == -1){
			    			nbRelations = ligne.nbRelationsEntreDeuxStationsSurLaLigne(depart, stationsDeLaLigne.get(j)) + ligneTest.nbRelationsEntreDeuxStationsSurLaLigne(stationsDeLaLigne.get(j), arrivee);
			    			StationCorrespondance = stationsDeLaLigne.get(j);
			    		}
			    		else{
			    			if(nbRelations > ligne.nbRelationsEntreDeuxStationsSurLaLigne(depart, stationsDeLaLigne.get(j)) + ligneTest.nbRelationsEntreDeuxStationsSurLaLigne(stationsDeLaLigne.get(j), arrivee)){
			    				nbRelations = ligne.nbRelationsEntreDeuxStationsSurLaLigne(depart, stationsDeLaLigne.get(j)) + ligneTest.nbRelationsEntreDeuxStationsSurLaLigne(stationsDeLaLigne.get(j), arrivee);
			    				StationCorrespondance = stationsDeLaLigne.get(j);
			    			}
			    		}
			    	}
			    }
			 }
			 if(StationCorrespondance != null){
				System.out.println(nbRelations+ " relations en utilisant une correspondance.");
			    ArrayList<Relation> listeARetourner = ligne.relationsBetweenTwoStations(depart, StationCorrespondance);
			    ArrayList<Relation> relationsToAdd = isOnTheSameLineMin(StationCorrespondance, arrivee).relationsBetweenTwoStations(StationCorrespondance, arrivee);
			    for(int k = 0; k<relationsToAdd.size(); k++){
			    	listeARetourner.add(relationsToAdd.get(k));
			    }
			    return listeARetourner;
			 }
			 else{
			  	return ligne.relationsBetweenTwoStations(depart, arrivee);
			 } 	
		}
		
		else{
			
			int nbRelations = -1;
			  int nbRelationsVoisines = -1;
			  Station StationVoisine = null;
			  
			   ArrayList<Ligne> lignesStationDepart = depart.getLstLignes();
			  	for(int i=0; i<lignesStationDepart.size(); i++){
			  	 ArrayList<Station> stationsLigneEnCours = lignesStationDepart.get(i).getListStation();
			  	 for(int j=0; j<stationsLigneEnCours.size(); j++){
			  	  
			  	   if(stationsLigneEnCours.get(j).getLstLignes().size() >1){
			  		ArrayList<Ligne> correspondanceNew = new ArrayList<Ligne>();
			  		ArrayList<Ligne> lignesCorrespondanceStation = stationsLigneEnCours.get(j).getLstLignes();
			  		  for(int k = 0; k<lignesCorrespondanceStation.size(); k++){
			  		  	if(!lignesStationDepart.contains(lignesCorrespondanceStation.get(k)))
			  				correspondanceNew.add(lignesCorrespondanceStation.get(k));
			  		  }
			  		if(correspondanceNew.size()>0){
			  			
			  			if(nbRelationsVoisines == -1){
			  				nbRelationsVoisines = lignesStationDepart.get(i).nbRelationsEntreDeuxStationsSurLaLigne(depart, stationsLigneEnCours.get(j));
			  				StationVoisine = stationsLigneEnCours.get(j);
			  			}
			  	 		else{
			  				if(nbRelationsVoisines > lignesStationDepart.get(i).nbRelationsEntreDeuxStationsSurLaLigne(depart, stationsLigneEnCours.get(j))){
			  					nbRelationsVoisines = lignesStationDepart.get(i).nbRelationsEntreDeuxStationsSurLaLigne(depart, stationsLigneEnCours.get(j));
			  					StationVoisine = stationsLigneEnCours.get(j);
			  				} 
			  			}
			  		}
			  	  }
			  	 	
			  	}
			  	
			  	if(StationVoisine != null){
			  		//System.out.println(StationVoisine);
			  	for(int m=0; m<StationVoisine.getLstLignes().size(); m++){
			  		for(int n = 0; n<StationVoisine.getLstLignes().get(m).getListStation().size(); n++){
			  			//System.out.println(StationVoisine.getLstLignes().get(m).getListStation().get(n));
			  		for(int p =0; p<arrivee.getLstLignes().size(); p++){
			  			if(StationVoisine.getLstLignes().get(m).getListStation().get(n).getLstLignes().contains(arrivee.getLstLignes().get(p)) && (isOnTheSameLineMin(StationVoisine.getLstLignes().get(m).getListStation().get(n), arrivee)!=null)){
			  				ArrayList<Relation> relationsTemp = plusRapideChemin(depart, StationVoisine);
				  			ArrayList<Relation> relationsToAdd = plusRapideChemin(StationVoisine, StationVoisine.getLstLignes().get(m).getListStation().get(n));
				  			ArrayList<Relation> relationsToAdd2 = plusRapideChemin(StationVoisine.getLstLignes().get(m).getListStation().get(n), arrivee);
				  			if(relationsToAdd.size()>0){
				  				for(int l=0; l<relationsToAdd.size(); l++){
				  					relationsTemp.add(relationsToAdd.get(l));
				  				}
				  			}
				  			
				  			if(relationsToAdd2.size()>0){
				  				for(int l=0; l<relationsToAdd2.size(); l++){
				  					relationsTemp.add(relationsToAdd2.get(l));
				  				}
				  			}
				  			
				  			if(nbRelations == -1){
				  				nbRelations = relationsTemp.size();
				  				relations = relationsTemp;
				  			}
				  	 		else{
				  				if(nbRelations > relationsTemp.size()){
				  					nbRelations = relationsTemp.size();
				 					relations = relationsTemp;
				  				} 
				  			}
				  			//return relations;
			  			}
			  		}
			  		}
			  	}
			  		
			  		
	  			
			  	}
			  }
			  return relations;
					
		}
	}
	
	// Retourne null si les stations ne sont pas sur la mm ligne ou la ligne o� les deux stations sont le moins �loign�s
	public Ligne isOnTheSameLineMin(Station depart, Station arrivee){
		
		int nbRelations = -1;
		Ligne ligne = null;
		for(int i = 0; i<depart.getLstLignes().size(); i++){
			for(int j = 0; j<arrivee.getLstLignes().size(); j++){
				if(depart.getLstLignes().get(i).getNameLigne().equals(arrivee.getLstLignes().get(j).getNameLigne())){
					if(nbRelations == -1){
						nbRelations = depart.getLstLignes().get(i).nbRelationsEntreDeuxStationsSurLaLigne(depart, arrivee);
						ligne = depart.getLstLignes().get(i);
					}
					else{
						if(nbRelations > depart.getLstLignes().get(i).nbRelationsEntreDeuxStationsSurLaLigne(depart, arrivee)){
							nbRelations = depart.getLstLignes().get(i).nbRelationsEntreDeuxStationsSurLaLigne(depart, arrivee);
							ligne = depart.getLstLignes().get(i);
						}
					}
				}
			}
		}
		return ligne;
	}

	@Override
	public IReseau moinsDeChangement(String depart, String arrivee) {
		// TODO Auto-generated method stub
		return null;
	}


}
