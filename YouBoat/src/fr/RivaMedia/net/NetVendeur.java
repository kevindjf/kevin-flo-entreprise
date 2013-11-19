package fr.RivaMedia.net;

import java.util.List;

import org.apache.http.NameValuePair;

import android.util.Log;
import fr.RivaMedia.model.Vendeur;
import fr.RivaMedia.net.core.Net;
import fr.RivaMedia.xml.VendeurXmlParser;

public class NetVendeur extends Net {

	public static String vendeursUrl = "xml-client-region2.php?";
	public static List<Vendeur> listeVendeurs(List<NameValuePair> donnees){

		String xml = Net.requeteGet(vendeursUrl,donnees);
		Log.e("NetVendeur",xml);
		return new VendeurXmlParser(xml).getListe();
	}

	public static String vendeurUrl = "xml-client-info.php?";
	public static String vendeurId = "idcli";
	public static Vendeur getVendeur(String id) {
		String xml = Net.requeteGet(vendeurUrl,Net.construireDonnes(
				vendeurId,id)
				);
		List<Vendeur> vendeurs = new VendeurXmlParser(xml).getListe();
		if(vendeurs.size()>0)
			return vendeurs.get(0);
		else
			return null;
	}

}
