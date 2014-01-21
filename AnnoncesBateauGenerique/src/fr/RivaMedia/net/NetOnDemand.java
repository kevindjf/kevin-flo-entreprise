package fr.RivaMedia.net;


import java.util.List;

import org.apache.http.NameValuePair;

import fr.RivaMedia.Constantes;
import fr.RivaMedia.net.core.Net;

public class NetOnDemand {

	public static String onDemand(List<NameValuePair> donnees) {
		return Net.requeteGet(Constantes.URL_ON_DEMAND, donnees);
	}

}
