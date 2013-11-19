package fr.RivaMedia.net;

import java.util.List;

import org.apache.http.NameValuePair;

import android.util.Log;
import fr.RivaMedia.Constantes;
import fr.RivaMedia.model.Annonce;
import fr.RivaMedia.model.Modele;
import fr.RivaMedia.net.core.Net;
import fr.RivaMedia.xml.AnnonceXmlParser;

public class NetRecherche extends Net {

	public static List<Annonce> chargerListeBateaux(){

		String xml = Net.requeteGet(Constantes.RECHERCHE_BATEAU_ADRESSE, null);

		return new AnnonceXmlParser(xml).getListe();
	}
	
	
	public static List<Annonce> rechercher(String url, List<NameValuePair> donnees){

		String xml = Net.requeteGet(url,donnees);
		Log.e("NetRecherche",xml);
		return new AnnonceXmlParser(xml).getListe();
	}

	public static Annonce rechercherAnnonce(String url, List<NameValuePair> donnees) {
		List<Annonce> annonces = rechercher(url, donnees);
		if(annonces.size()>0)
			return annonces.get(0);
		else
			return null;
	}
	
}
