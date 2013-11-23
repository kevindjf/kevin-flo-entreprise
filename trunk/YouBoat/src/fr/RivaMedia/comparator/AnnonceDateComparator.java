package fr.RivaMedia.comparator;

import java.util.Comparator;
import java.util.Date;

import fr.RivaMedia.model.Annonce;

public class AnnonceDateComparator implements Comparator<Annonce>{

	private boolean croissant = true;

	public AnnonceDateComparator(boolean croissant){
		this.croissant = croissant;
	}

	@Override
	public int compare(Annonce annonce1, Annonce annonce2) {

		if(annonce1 != null && annonce2 != null ){
			if(annonce1.getPubDate() != null && annonce2.getPubDate() != null){
				String date1 =  annonce1.getPubDate();
				String date2 =  annonce2.getPubDate();
				if(croissant)
					return date1.compareTo(date2);
				else
					return date1.compareTo(date2)*-1;
			}
		}
		return 0;
	}

}
