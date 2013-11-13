package fr.RivaMedia.factory;

import java.util.ArrayList;
import java.util.List;

import fr.RivaMedia.model.Moteur;

public class MoteurFactory {

	public static Moteur getMoteur(){
		Moteur moteur = new Moteur();
		
		moteur.setInfoMoteur("Les informations du moteur");
		moteur.setPropulsion("La propulsion du moteur");
		moteur.setHeureMoteur("10 000");
		moteur.setMarqueMoteur("La marque du moeteur");
		moteur.setPuissanceMoteur("100 ch");
		moteur.setPuissNumber(1);
		
		return moteur;
	}
	
	public static List<Moteur> getListeMoteurs(){
		List<Moteur> moteurs = new ArrayList<Moteur>();
		for(int i=0;i<10;++i)
			moteurs.add(getMoteur());
		return moteurs;
	}
}
