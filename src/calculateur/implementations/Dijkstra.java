package calculateur.implementations;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import calculateur.abstracts.Relation;
import calculateur.abstracts.Reseau;
import calculateur.abstracts.Station;
import calculateur.interfaces.IAlgoCalcul;
import calculateur.interfaces.IReseau;


class DjikstraNode {
	Station node = null;
	DjikstraNode previous = null;
	int distance = -1;
	boolean visited = false;
	DjikstraNode(Station node) { this.node = node; }
	public void setDistance(int distance) { this.distance = distance; }
	public int getDistance() { return this.distance; }
	String getName() { return node.getName(); }
	
	@Override
	public String toString() { return getName() + " : " + this.distance; }
	public Station getNode() { return this.node; }
}


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
	
	private Map<Station, ArrayList<Relation>> g;
	
	public Dijkstra (Reseau r){
		this.g = r.getGrapheReseau();
	}
	
	Map<Station, DjikstraNode> subGraph = new HashMap<Station, DjikstraNode>();
	PriorityQueue<DjikstraNode> next = new PriorityQueue<DjikstraNode>(10, new Comparator<DjikstraNode>() {
		@Override
		public int compare(DjikstraNode o1, DjikstraNode o2) {
			return o1.getDistance()>o2.getDistance() ? 1
				:  o1.getDistance()<o2.getDistance() ? -1
				:  0; 
		}
	});
	
	
	// Ce n'est qu'un début.. J'essaye. Si tu as des idées hésite pas ! ;)   
	// La classe IGrapheEdge correspond en quelque sorte à la classe Relation faut donc réussir à adpater Relation sous se modele la :s.
	
	
	@Override
	public IReseau plusRapideChemin(String depart, String arrivee) {
				
		return null; 
	}

	@Override
	public IReseau moinsDeChangement(String depart, String arrivee) {
		// TODO Auto-generated method stub
		return null;
	}


}
