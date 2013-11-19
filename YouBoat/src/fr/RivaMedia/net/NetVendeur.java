package fr.RivaMedia.net;

import java.util.List;

import org.apache.http.NameValuePair;

import android.util.Log;
import fr.RivaMedia.Constantes;
import fr.RivaMedia.model.Annonce;
import fr.RivaMedia.model.Modele;
import fr.RivaMedia.model.Vendeur;
import fr.RivaMedia.net.core.Net;
import fr.RivaMedia.xml.AnnonceXmlParser;
import fr.RivaMedia.xml.VendeurXmlParser;

public class NetVendeur extends Net {

	public static String vendeursUrl = "xml-client-region2.php?";
	public static List<Vendeur> listeVendeurs(String url, List<NameValuePair> donnees){

		String xml = Net.requeteGet(vendeursUrl,donnees);
		Log.e("NetVendeur",xml);
		return new VendeurXmlParser(xml).getListe();
	}

	public static Vendeur vendeur(String url, List<NameValuePair> donnees) {
		List<Vendeur> vendeurs = listeVendeurs(url, donnees);
		if(vendeurs.size()>0)
			return vendeurs.get(0);
		else
			return null;
	}
	
}
