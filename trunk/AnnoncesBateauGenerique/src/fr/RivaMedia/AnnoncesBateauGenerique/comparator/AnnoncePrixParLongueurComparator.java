package fr.RivaMedia.AnnoncesBateauGenerique.comparator;

import java.util.Comparator;

import fr.RivaMedia.AnnoncesBateauGenerique.model.Annonce;

public class AnnoncePrixParLongueurComparator implements Comparator<Annonce>{

	private boolean croissant = true;

	public AnnoncePrixParLongueurComparator(boolean croissant){
		this.croissant = croissant;
	}

	@Override
	public int compare(Annonce annonce1, Annonce annonce2) {

		if(annonce1 != null && annonce2 != null ){
			if(annonce1.getLongueur() != null && annonce2.getLongueur() != null){
				float longueur1 =  Float.parseFloat(annonce1.getLongueur());
				float longueur2 =  Float.parseFloat(annonce2.getLongueur());
				if(croissant){
					if(longueur1==longueur2)
						return new AnnoncePrixComparator(croissant).compare(annonce1, annonce2);
					else if(longueur1>longueur2)
						return 1;
					else
						return -1;
				}else{
					if(longueur1==longueur2)
						return new AnnoncePrixComparator(croissant).compare(annonce1, annonce2);
					else if(longueur2>longueur1)
						return 1;
					else
						return -1;
				}
			}
		}
		return 0;
	}

}
