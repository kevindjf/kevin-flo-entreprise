package fr.RivaMedia.AnnoncesBateauGenerique.comparator;

import java.util.Comparator;

import fr.RivaMedia.AnnoncesBateauGenerique.model.Annonce;

public class AnnonceDateComparator implements Comparator<Annonce>{

	private boolean croissant = true;

	public AnnonceDateComparator(boolean croissant){
		this.croissant = croissant;
	}

	@Override
	public int compare(Annonce annonce1, Annonce annonce2) {

		System.err.println("AnnonceDateComparator "+croissant);
		if(annonce1 != null && annonce2 != null ){
			System.err.println("AnnonceDateComparator 2");
			if(annonce1.getPubDate() != null && annonce2.getPubDate() != null){
				System.err.println("AnnonceDateComparator 3");
				String date1 =  annonce1.getPubDate();
				String date2 =  annonce2.getPubDate();
				
				System.err.println(date1+"   "+date2);
				
				if(croissant)
					return date1.compareTo(date2);
				else
					return date2.compareTo(date1);
			}
		}
		return 0;
	}

}
