package fr.RivaMedia.AnnoncesBateauGenerique.net;

import java.util.List;

import org.apache.http.NameValuePair;

import android.util.Log;
import fr.RivaMedia.AnnoncesBateauGenerique.Constantes;
import fr.RivaMedia.AnnoncesBateauGenerique.model.Vendeur;
import fr.RivaMedia.AnnoncesBateauGenerique.net.core.Net;
import fr.RivaMedia.AnnoncesBateauGenerique.xml.VendeurXmlParser;

public class NetVendeur extends Net {

	public static List<Vendeur> listeVendeurs(List<NameValuePair> donnees){

		String xml = Net.requeteGet(Constantes.URL_VENDEURS,donnees);
		Log.e("NetVendeur",xml);
		return new VendeurXmlParser(xml).getListe();
	}

	
	public static Vendeur getVendeur(String id) {
		String xml = Net.requeteGet(Constantes.URL_INFORMATIONS_VENDEUR,
				Net.construireDonnes(
				Constantes.VENDEUR_INFORMATIONS_ID_CLIENT,id)
				);
		
		List<Vendeur> vendeurs = new VendeurXmlParser(xml).getListe();
		if(vendeurs.size()>0)
			return vendeurs.get(0);
		else
			return null;
	}

}
