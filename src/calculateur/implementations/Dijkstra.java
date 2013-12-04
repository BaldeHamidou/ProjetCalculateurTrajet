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

L'algorithme dû à Dijkstra est basé sur le principe suivant :

Si le plus court chemin reliant E à S passe par les sommets s1, s2, ..., sk alors, les différentes étapes sont aussi les plus courts chemins reliant E aux différents sommets s1, s2, ..., sk.

On construit de proche en proche le chemin cherché en choisissant à chaque itération de l'algorithme, un sommet si du graphe parmi ceux qui n'ont pas encore été traités, tel que la longueur connue provisoirement du plus court chemin allant de E à si soit la plus courte possible.

INITIALISATION DE L'ALGORITHME :

	Étape 1 : On affecte le poids 0 au sommet origine (E) et on attribue provisoirement un poids "inconnu" aux autres sommets.

RÉPÉTER LES OPÉRATIONS SUIVANTES TANT QUE LE SOMMET DE SORTIE (S) N'EST PAS AFFECTÉ D'UN POIDS DÉFINITIF

	Étape 2 : Parmi les sommets dont le poids n'est pas définivement fixé choisir le sommet X de poids p minimal. Marquer définitivement ce sommet X affecté du poids p(X).

	Étape 3 : Pour tous les sommets Y qui ne sont pas définitivement marqués, adjacents au dernier sommet fixé X :

Calculer la somme s du poids de X et du poids de l'arête reliant X à Y.
Si la somme s est inférieure au poids provisoirement affecté au sommet Y, affecter provisoirement à Y le nouveau poids s et indiquer entre parenthèses le sommet X pour se souvenir de sa provenance.
QUAND LE SOMMET S EST DÉFINTIVEMENT MARQUÉ

Le plus court chemin de E à S s'obtient en écrivant de gauche à droite le parcours en partant de la fin S.  */


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
		// la station d'arrivée est sur la même ligne que celle de départ
		if((ligne = isOnTheSameLineMin(depart, arrivee)) != null){ 
			 
			 int nbRelationsEnRestantMmLigne = ligne.nbRelationsEntreDeuxStationsSurLaLigne(depart, arrivee);
			 //System.out.println(nbRelationsEnRestantMmLigne+" relations en restant sur la même ligne.");
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
	
	// Retourne null si les stations ne sont pas sur la mm ligne ou la ligne où les deux stations sont le moins éloignés
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
