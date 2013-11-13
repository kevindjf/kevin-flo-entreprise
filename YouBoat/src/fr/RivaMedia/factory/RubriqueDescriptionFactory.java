package fr.RivaMedia.factory;

import java.util.ArrayList;
import java.util.List;

import fr.RivaMedia.model.RubriqueDescription;

public class RubriqueDescriptionFactory {
	
	public static RubriqueDescription getRubriqueDescription(){
		RubriqueDescription rb = new RubriqueDescription();
		
		rb.setUrl("www.google.fr");
		rb.setType("WEB");
		
		return rb;
	}
	
	public static List<RubriqueDescription> getListeRubriqueDescription(){
		List<RubriqueDescription> rbs = new ArrayList<RubriqueDescription>();
		for(int i=0;i<10;++i)
			rbs.add(getRubriqueDescription());
		return rbs;
	}
    
}
