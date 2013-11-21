package fr.RivaMedia.net;

import java.util.List;

import org.apache.http.NameValuePair;

import android.util.Log;
import fr.RivaMedia.Constantes;
import fr.RivaMedia.model.Annonce;
import fr.RivaMedia.net.core.Net;
import fr.RivaMedia.xml.AnnonceXmlParser;

public class NetAnnonce extends Net {	
	
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

	public static List<Annonce> getAnnoncesDe(String numero, String type) {
		String url = "";
		if(type.equals(Constantes.BATEAUX))
			url = Constantes.URL_ANNONCES_BATEAUX_DE;
		else if(type.equals(Constantes.MOTEURS))
			url = Constantes.URL_ANNONCES_MOTEURS_DE;
		else if(type.equals(Constantes.ACCESSOIRES))
			url = Constantes.URL_ANNONCES_ACCESSOIRES_DE;
		
		return rechercher(url, Net.construireDonnes(
				Constantes.ANNONCES_ID_CLIENT,numero
				));
	}
	
}
