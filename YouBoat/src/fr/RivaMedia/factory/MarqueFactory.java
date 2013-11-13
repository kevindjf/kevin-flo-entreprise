package fr.RivaMedia.factory;

import java.util.ArrayList;
import java.util.List;
import fr.RivaMedia.model.Marque;

public class MarqueFactory {
	
	public static Marque getMarque(){
		Marque marque = new Marque();
		
		marque.setIdMarque("1");
		marque.setLibelle("PetitBateau");
	    //private Map<Object,Object> modelesById;
	    //ASIFormDataRequest* requete;
	    marque.setIdType("1");
		
		return marque;
	}
	
	public static List<Marque> getListeMarque(){
		List<Marque> marques = new ArrayList<Marque>();
		for(int i=0;i<10;++i)
			marques.add(getMarque());
		return marques;
	}

}
