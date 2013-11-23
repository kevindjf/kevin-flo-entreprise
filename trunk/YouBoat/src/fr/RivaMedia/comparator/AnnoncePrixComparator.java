package fr.RivaMedia.comparator;

import java.util.Comparator;

import fr.RivaMedia.model.Annonce;

public class AnnoncePrixComparator implements Comparator<Annonce>{

	private boolean croissant = true;

	public AnnoncePrixComparator(boolean croissant){
		this.croissant = croissant;
	}

	@Override
	public int compare(Annonce annonce1, Annonce annonce2) {

		if(annonce1 != null && annonce2 != null ){
			if(annonce1.getPrix() != null && annonce2.getPrix() != null){
				float prix1 =  Float.parseFloat(annonce1.getPrix());
				float prix2 =  Float.parseFloat(annonce2.getPrix());
				if(croissant){
					if(prix1==prix2)
						return 0;
					else if(prix1>prix2)
						return 1;
					else
						return -1;
				}else{
					if(prix1==prix2)
						return 0;
					else if(prix2>prix2)
						return 1;
					else
						return -1;
				}
			}
		}
		return 0;
	}

}
