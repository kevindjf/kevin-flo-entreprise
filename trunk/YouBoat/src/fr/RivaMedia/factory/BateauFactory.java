package fr.RivaMedia.factory;

import java.util.ArrayList;
import java.util.List;

import fr.RivaMedia.model.Annonce;

public class BateauFactory {
    
    public static Annonce getBateau(){
    	Annonce bateau = new Annonce();
    	
    	bateau.setNumero("1");
    	bateau.setTitle("Mon beau bateau");
    	bateau.setNomMoteur("Moteur du bateau");
    	bateau.setLongueur("1000");
    	bateau.setAnnee("2000");
    	bateau.setCategorie("Categie Voilier");
    	bateau.setGpsLatitude("0.1");
    	bateau.setGpsLongtitude("10.3");
    	bateau.setTypeClient("Client Pro");
    	bateau.setPrix("1000,50 €");
    	bateau.setTaxePrix("10%");
    	bateau.setPubDate("10/10/2010");
        
    	
    	//bateau.setRubriquesDecscription(RubriqueDescriptionFactory.getListRubiqueDescription());
        
    	bateau.setType("Type Personnel");
        // utilise dans le tris des annonces
        bateau.setPrixNumber(0);
        bateau.setTailleNumber(0);
        
        return bateau;        
    }
    
    public static List<Object> getListBateaux(){
    	List<Object> bateaux = new ArrayList<Object>();
    	for(int i=0;i<10;++i)
    		bateaux.add(getBateau());
    	return bateaux;
    }
    
}
