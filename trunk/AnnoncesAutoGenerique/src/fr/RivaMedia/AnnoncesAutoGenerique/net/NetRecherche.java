package fr.RivaMedia.AnnoncesAutoGenerique.net;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;

import fr.RivaMedia.AnnoncesAutoGenerique.Constantes;
import fr.RivaMedia.AnnoncesAutoGenerique.net.core.Net;

public class NetRecherche {
	
	public static String recherche(String marque, String serie, String categorie, String budget, String energie, String departement, String nom, String email){
		return recherche(marque, serie, categorie, budget, energie, departement, nom, email, Net.construireDonnes());
	}
	
	public static String recherche(String marque, String serie, String categorie, String budget, String energie, String departement, String nom, String email, List<NameValuePair> d){
		List<NameValuePair> donnees = new ArrayList<NameValuePair>(d);
		
		Net.add(donnees,
				Constantes.RECHERCHE_MARQUE_ID,marque,
				Constantes.RECHERCHE_SERIE_ID,serie,
				Constantes.RECHERCHE_CATEGORIE_ID,categorie,
				Constantes.RECHERCHE_BUDGET,budget,
				Constantes.RECHERCHE_ENERGIE,energie,
				Constantes.RECHERCHE_DEPARTEMENT,departement,
				Constantes.RECHERCHE_NOM,nom,
				Constantes.RECHERCHE_EMAIL,email
				);
		
		return Net.requete(Constantes.URL_RECHERCHE, donnees);
	}
	
}
