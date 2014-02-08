package fr.RivaMedia.AnnoncesBateauGenerique.net.core;

import java.util.List;

import org.apache.http.NameValuePair;

import fr.RivaMedia.AnnoncesBateauGenerique.Constantes;
import fr.RivaMedia.AnnoncesBateauGenerique.model.Client;
import fr.RivaMedia.AnnoncesBateauGenerique.xml.ClientXmlParser;



public class NetClient extends Net {

	public static List<Client> listeClients(List<NameValuePair> donnees){

		String xml = Net.requeteGet(Constantes.URL_CLIENTS_PRO,donnees);
		return new ClientXmlParser(xml).getClients();
	}

	
	public static Client getClient(String id) {
		String xml = Net.requeteGet(Constantes.URL_CLIENT_DETAIL,
				Net.construireDonnes(
				Constantes.CLIENT_DETAIL_ID,id)
				);
		
		return new ClientXmlParser(xml).getClient();
	}

}
