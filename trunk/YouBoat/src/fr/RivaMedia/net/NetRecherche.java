package fr.RivaMedia.net;

import java.util.List;

import org.apache.http.NameValuePair;

import android.util.Log;
import fr.RivaMedia.Constantes;
import fr.RivaMedia.net.core.Net;
import fr.RivaMedia.xml.AnnonceXmlParser;

public class NetRecherche extends Net {

	public static List<Object> chargerListeBateaux(){

		String xml = Net.requeteGet(Constantes.RECHERCHE_BATEAU_ADRESSE, null);

		return new AnnonceXmlParser(xml).getListe();
	}
	
	public static List<Object> rechercher(String url, List<NameValuePair> donnees){

		String xml = Net.requeteGet(url,donnees);
		Log.e("NetRecherche",xml);
		return new AnnonceXmlParser(xml).getListe();
	}

	public static Object rechercherAnnonce(String url, List<NameValuePair> donnees) {
		return rechercher(url, donnees).get(0);
	}
	
}
