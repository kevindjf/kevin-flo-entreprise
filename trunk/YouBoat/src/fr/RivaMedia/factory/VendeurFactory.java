package fr.RivaMedia.factory;

import java.util.ArrayList;
import java.util.List;

import fr.RivaMedia.model.News;
import fr.RivaMedia.model.Vendeur;

public class VendeurFactory {

	public static Vendeur getVendeur(){
		Vendeur vendeur = new Vendeur();
		
		vendeur.setNom("Franois Hollandes");
		vendeur.setEmail("f.hollandes@gouv.fr");
		vendeur.setAdresse("Elyse");
		vendeur.setCodePostal("75000");
		vendeur.setVille("Paris");
		vendeur.setTel1("000000000");
		vendeur.setTel2("000000000");
		vendeur.setSiteWeb("http://www.gouvernement.fr/");
		vendeur.setType("Prsident");

		vendeur.setVendeurLatitude("0.001");
		vendeur.setVendeurLongitude("-2");
		
		return vendeur;
	}
	
	public static List<Vendeur> getListeVendeurs(){
		List<Vendeur> vendeurs = new ArrayList<Vendeur>();
		for(int i=0;i<10;++i)
			vendeurs.add(getVendeur());
		return vendeurs;
	}

}
