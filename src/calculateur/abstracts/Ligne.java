package calculateur.abstracts;

import java.util.ArrayList;

import calculateur.interfaces.ILigne;

public abstract class Ligne implements ILigne {

	private String nameLigne;
	private ArrayList<Station> listeStation;
	private ArrayList<Station> listeTerminus;
	private ArrayList<Ligne> listeCorrespondance;

	public Ligne(String nameLigne) {
		this.nameLigne = nameLigne;
		this.listeStation = new ArrayList<Station>();
		this.listeTerminus = new ArrayList<Station>();
		this.listeCorrespondance = new ArrayList<Ligne>();
	}

	public Ligne(String nameLigne, ArrayList<Station> listeStation, ArrayList<Station> terminus, ArrayList<Ligne> correspondances) {
		this.nameLigne = nameLigne;
		this.listeStation = listeStation;
		this.listeTerminus = terminus;
		this.listeCorrespondance = correspondances;
	}

	public void addStation(Station station) {
		if(station != null && !this.listeStation.contains(station))
		this.listeStation.add(station);
	}

	public void addTerminus(Station station) {
		if(station != null && !this.listeTerminus.contains(station))
		this.listeTerminus.add(station);
	}
	
	public void addCorrespondance(Ligne ligneDeCorrespondance){
		if(ligneDeCorrespondance != null && !this.listeCorrespondance.contains(ligneDeCorrespondance) && !this.equals(ligneDeCorrespondance))
			this.listeCorrespondance.add(ligneDeCorrespondance);
	}
	
	public ArrayList<Station> getListStation() {
		return this.listeStation;
	}

	public ArrayList<Station> getListTerminus() {
		return listeTerminus;
	}
	
    public ArrayList<Ligne> getListCorrespondance(){
    	return this.listeCorrespondance;
    }
    
	@Override
	public void setNameLigne(String name) {
		if(name != null && name.trim() != "")
		this.nameLigne = name;
	}

	@Override
	public String getNameLigne() {
		return this.nameLigne;
	}

	public boolean isTerminus(Station station){
		return this.listeTerminus.contains(station);
	}
	
	public int nbRelationsEntreDeuxStationsSurLaLigne(Station station1,
			Station station2) {

		if (this.listeStation.contains(station1)
				&& this.listeStation.contains(station2)) {
			if(station1.equals(station2))
				return 0;
			else{
				if(isTerminus(station1) && isTerminus(station2))
					return this.listeStation.size()-1;
				else{
					for(int i = 0; i<station1.getStationsVoisines().size(); i++){
						if(station1.getStationsVoisines().get(i).getLstLignes().contains(this)){
							if(station1.getStationsVoisines().get(i).equals(station2))
								return 1;
							else{ 
								if(isTheGoodStationVoisineToSearch(station1, station1.getStationsVoisines().get(i), station2)){
									return countRelations(station1, station1.getStationsVoisines().get(i), station2);
								}
							}
						}
					}
				}
			}		
		}
		return -1;
	}
	
	private int countRelations(Station stationToIgnore, Station stationNext, Station stationTofind){
		
		if(stationNext.equals(stationTofind))
			return 1;
		else{
			ArrayList<Station> stationsVoisine = new ArrayList<Station>();
			for(int i=0; i<stationNext.getStationsVoisines().size(); i++){
				if(stationNext.getStationsVoisines().get(i).getLstLignes().contains(this) && !stationNext.getStationsVoisines().get(i).equals(stationToIgnore)){
					stationsVoisine.add(stationNext.getStationsVoisines().get(i));
				}
			}
			if(stationsVoisine.size() == 1)
				return 1+countRelations(stationNext, stationsVoisine.get(0), stationTofind);
			else{
				for(int j=0; j<stationsVoisine.size(); j++){
					if(isTheGoodStationVoisineToSearch(stationNext, stationsVoisine.get(j), stationTofind))
						return 1+countRelations(stationNext, stationsVoisine.get(j), stationTofind);
					}
				}
			return 0;
		}
	}
	
	
	private boolean isTheGoodStationVoisineToSearch(Station stationToIgnore, Station stationNext, Station stationTofind){
		
		if(isTerminus(stationNext) && !stationNext.equals(stationTofind))
			return false;
		else if(stationNext.equals(stationTofind))
			return true;
		else{
			ArrayList<Station> stationsVoisine = new ArrayList<Station>();
			for(int i=0; i<stationNext.getStationsVoisines().size(); i++){
				if(stationNext.getStationsVoisines().get(i).getLstLignes().contains(this) && !stationNext.getStationsVoisines().get(i).equals(stationToIgnore)){
					stationsVoisine.add(stationNext.getStationsVoisines().get(i));
				}
			}
			if(stationsVoisine.size() == 0)
				return false;
			else{
				if(stationsVoisine.size() == 1)
					return isTheGoodStationVoisineToSearch(stationNext, stationsVoisine.get(0), stationTofind);
				else{
					for(int j=0; j<stationsVoisine.size(); j++){
						if(isTheGoodStationVoisineToSearch(stationNext, stationsVoisine.get(j), stationTofind))
							return(isTheGoodStationVoisineToSearch(stationNext, stationsVoisine.get(j), stationTofind));
					}
				}
				return false;
			}	
		}
	}

}
