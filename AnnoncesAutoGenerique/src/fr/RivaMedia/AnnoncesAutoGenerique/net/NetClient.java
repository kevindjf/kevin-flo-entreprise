package fr.RivaMedia.AnnoncesAutoGenerique.net;

import java.util.List;

import org.apache.http.NameValuePair;

import fr.RivaMedia.AnnoncesAutoGenerique.Constantes;
import fr.RivaMedia.AnnoncesAutoGenerique.model.Client;
import fr.RivaMedia.AnnoncesAutoGenerique.net.core.Net;
import fr.RivaMedia.AnnoncesAutoGenerique.xml.ClientXmlParser;

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
