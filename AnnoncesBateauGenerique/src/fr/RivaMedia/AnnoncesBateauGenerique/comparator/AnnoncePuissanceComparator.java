package fr.RivaMedia.AnnoncesBateauGenerique.comparator;

import java.util.Comparator;

import fr.RivaMedia.AnnoncesBateauGenerique.model.Annonce;

public class AnnoncePuissanceComparator implements Comparator<Annonce>{

	private boolean croissant = true;

	public AnnoncePuissanceComparator(boolean croissant){
		this.croissant = croissant;
	}

	@Override
	public int compare(Annonce annonce1, Annonce annonce2) {

		if(annonce1 != null && annonce2 != null ){
			if(annonce1.getMoteur() != null && annonce1.getMoteur().getPuissanceMoteur() != null 
					&& annonce2.getMoteur() != null && annonce2.getMoteur().getPuissanceMoteur() != null ){
				Double puiss1 =  Double.parseDouble(annonce1.getMoteur().getPuissanceMoteur());
				Double puiss2 =  Double.parseDouble(annonce2.getMoteur().getPuissanceMoteur());
				
				if(croissant)
					return puiss1.compareTo(puiss2);
				else
					return puiss2.compareTo(puiss1);
			}
		}
		return 0;
	}

}
