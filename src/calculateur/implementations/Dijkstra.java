package calculateur.implementations;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

import calculateur.abstracts.Relation;
import calculateur.abstracts.Reseau;
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

	private Map<Station, ArrayList<Relation>> g;
	
	public Dijkstra (Reseau r){
		this.g = r.getGrapheReseau();
	}
	
	@Override
	public IReseau plusRapideChemin(Station depart, Station arrivee) {
		
		boolean memeLigne = false;
		
		for(int i = 0; i<depart.getLstLignes().size(); i++){
			for(int j = 0; j<arrivee.getLstLignes().size(); j++){
				if(depart.getLstLignes().get(i).getNameLigne().equals(arrivee.getLstLignes().get(j).getNameLigne())){
					memeLigne=true;
				}
			}
		}
		
		if(memeLigne){ // La station d'arriv�e est sur la meme ligne que la station de depart
			
		}else{ // La station d'arriv�e est sur une ligne diff�rente
			
			Map<Station, Integer> subGraph = new HashMap<Station, Integer>();
			
			// On parcourt la ligne pour trouver des correspondances.
			for(int i = 0; i<depart.getLstLignes().size(); i++){
				for(int j = 0; j<depart.getLstLignes().get(i).getListStation().size(); j++){
					if(depart.getLstLignes().get(i).getListStation().get(j).getLstLignes().size()>1){
						// Map des prochaine correspondance possible avec leur distance par rapport au depart
						subGraph.put(depart.getLstLignes().get(i).getListStation().get(j), depart.getLstLignes().get(i).nbRelationsEntreDeuxStationsSurLaLigne(depart, depart.getLstLignes().get(i).getListStation().get(j)));
						
						// On recupere la station la plus proche
						
						// On passe le boolean station.setVisited(true)
						
						// Et on recommence de la meme fa�on jusqu'a ce que l'on arrive � l'arriv�e.
						
					
					}
				}
			}			
		}
			
		return null; 
	}

	@Override
	public IReseau moinsDeChangement(String depart, String arrivee) {
		// TODO Auto-generated method stub
		return null;
	}


}
