package fr.RivaMedia.AnnoncesBateauGenerique.net;


import java.util.List;

import org.apache.http.NameValuePair;

import fr.RivaMedia.AnnoncesBateauGenerique.Constantes;
import fr.RivaMedia.AnnoncesBateauGenerique.net.core.Net;

public class NetOnDemand {

	public static String onDemand(List<NameValuePair> donnees) {
		return Net.requeteGet(Constantes.URL_ON_DEMAND, donnees);
	}

}
