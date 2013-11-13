package fr.RivaMedia.factory;

import java.util.ArrayList;
import java.util.List;

import fr.RivaMedia.model.*;

public class BateauFactory {
    
    public static Bateau getBateau(){
    	Bateau bateau = new Bateau();
    	
    	bateau.setNumero("1");
    	bateau.setTitle("Mon beau bateau");
    	bateau.setMoteur("Moteur du bateau");
    	bateau.setLongueur("1000");
    	bateau.setAnnee("2000");
    	bateau.setCategorie("Categie Voilier");
    	bateau.setGpsLatitude("0.1");
    	bateau.setGpsLongtitude("10.3");
    	bateau.setTypeClient("Client Pro");
    	bateau.setPhotos("http://www.homeforhome.fr/wp-content/uploads/2009/07/bateau.jpg");
    	bateau.setPrix("1000,50€");
    	bateau.setTaxePrix("10%");
    	bateau.setPubDate("10/10/2010");
        
    	
    	//bateau.setRubriquesDecscription(RubriqueDescriptionFactory.getListRubiqueDescription());
        
    	bateau.setType("Type Personnel");
        // utilisé dans le tris des annonces
        bateau.setPrixNumber(0);
        bateau.setTailleNumber(0);
        
        return bateau;        
    }
    
    public static List<Bateau> getListBateaux(){
    	List<Bateau> bateaux = new ArrayList<Bateau>();
    	for(int i=0;i<10;++i)
    		bateaux.add(getBateau());
    	return bateaux;
    }
    
}
